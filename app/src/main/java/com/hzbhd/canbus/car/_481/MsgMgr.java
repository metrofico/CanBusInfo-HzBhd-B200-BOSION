package com.hzbhd.canbus.car._481;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.KeyEvent;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.midware.constant.HotKeyConstant;


public class MsgMgr extends AbstractMsgMgr {
    private static final String TAG = "_481_MsgMgr";

    Context mContext;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private UiMgr mUiMgr;
    private int lastDoorStatus = -1;
    private boolean updatedLanguage = false;
    private static String mLanguage;

    @Override
    public void initCommand(Context context) {
        super.initCommand(context);
        //CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        this.mContext = context;
        mUiMgr = getUiMgr(this.mContext);
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 39, 0, 0, 0, 0, 0});
        //Siempre Ingles


    }

    @Override
    public void onHandshake(Context context) {
        super.onHandshake(context);
        updatedLanguage = false;
    }

    @Override
    public void onAccOff() {
        super.onAccOff();
        updatedLanguage = false;
    }

    @Override
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1});
    }

    @Override
    public void onSleep() {
        super.onSleep();
        CanbusMsgSender.sendMsg(new byte[]{22, -91, 0, 0, 0, 0, 0, 0});
        updatedLanguage = false;
    }

    @Override
    public void onPowerOff() {
        super.onPowerOff();
        updatedLanguage = false;
    }

    static void setLanguage(Context context) {
        String country = context.getResources().getConfiguration().getLocales().get(0).getCountry();
        Log.i(TAG, "setLanguage: country " + country);
        if (country.endsWith("CN")) {
            mLanguage = "_zh";
        } else {
            mLanguage = "_en";
        }
        Log.i(TAG, "setLanguage: mLanguage " + mLanguage);
    }

    @Override
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        LanguageReceiver languageReceiver = new LanguageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        context.registerReceiver(languageReceiver, intentFilter);
        if (!updatedLanguage) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1});
            updatedLanguage = true;
        }
    }

    @Override
    public void canbusInfoChange(Context context, byte[] msgReceived) {
        super.canbusInfoChange(context, msgReceived);
        if (!updatedLanguage) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1});
            updatedLanguage = true;
        }
        this.mCanBusInfoByte = msgReceived;
        this.mCanBusInfoInt = getByteArrayToIntArray(msgReceived);
        int id = mCanBusInfoInt[1];
        if (id == 0x30) {
            set0x30VersionInfo();
        }
        if (id == 0x20) {
            set0x20WheelKey(mContext);
        }
        int doorByte = -1;

        if (id == 0x25) {
            doorByte = mCanBusInfoInt[2];
        } else if (id == 0x7D && mCanBusInfoInt[2] == 0x05) {
            doorByte = mCanBusInfoInt[3];
        }

        if (doorByte != -1 && doorByte != lastDoorStatus) {
            lastDoorStatus = doorByte;
            updateDoorState(doorByte);
            logDoorState(doorByte);
        }

        Log.d("MsgMgrSWM", "CanbusInfoChange received");
    }

    @Override
    public void dateTimeRepCanbus(int nowYear, int bYear2Dig, int nowMonth, int nowDay, int nowHours, int nowMins, int nowSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        // Enviar aviso de sincronización
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});

        if (isFormat24H) {
            //Formato comprobado que funciona correctamente con 24h
            byte[] msg = new byte[]{
                    22, -90,
                    (byte) (nowYear - 2000),
                    (byte) nowMonth,
                    (byte) nowDay,
                    (byte) bHours24H, // Ya está formateado a 24h correctamente
                    (byte) nowMins,
                    0,
                    1 // indicador de 24h
            };
            CanbusMsgSender.sendMsg(msg);
        } else {
            int sendHour = 0;
            // Corrige la hora en AM/PM
            if (isFormatPm && nowHours < 12) {
                sendHour = nowHours + 12; // 1 PM → 13
            } else {
                sendHour = nowHours;
            }

            byte[] msg = new byte[]{
                    22, (byte) 0xA6, // -90 unsigned
                    (byte) (nowYear - 2000),
                    (byte) (nowMonth + 1),
                    (byte) nowDay,
                    (byte) sendHour,
                    (byte) nowMins,
                    (byte) 0,
                    (byte) 0 // indicador de 12h
            };
            CanbusMsgSender.sendMsg(msg);
        }
    }
    /*@Override
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] msg = new byte[]{22, -90, (byte) (bYearTotal - 2000), (byte) bMonth, (byte) bDay, (byte) bHours, (byte) bMins, 0, isFormat24H ? (byte) 1 : (byte) 0};
        CanbusMsgSender.sendMsg(msg);
    }*/

    private void updateDoorState(int status) {
        GeneralDoorData.isLeftFrontDoorOpen = (status & 0x40) != 0;
        GeneralDoorData.isRightFrontDoorOpen = (status & 0x80) != 0;
        GeneralDoorData.isLeftRearDoorOpen = (status & 0x10) != 0;
        GeneralDoorData.isRightRearDoorOpen = (status & 0x20) != 0;
        GeneralDoorData.isBackOpen = (status & 0x08) != 0;
        GeneralDoorData.isFrontOpen = (status & 0x04) != 0;
        updateDoorView(mContext);
    }

    private void logDoorState(int status) {
        Log.d("DoorStatus", String.format("Estado 0x%02X | Izq. Delantera: %b | Der. Delantera: %b | Izq. Trasera: %b | Der. Trasera: %b | Maletero: %b | Capó: %b", status, GeneralDoorData.isLeftFrontDoorOpen, GeneralDoorData.isRightFrontDoorOpen, GeneralDoorData.isLeftRearDoorOpen, GeneralDoorData.isRightRearDoorOpen, GeneralDoorData.isBackOpen, GeneralDoorData.isFrontOpen));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    private void set0x30VersionInfo() {
        Log.d("Swm", "Version car received");
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void set0x20WheelKey(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int keyCode = iArr[2];
        int keyState = iArr[3];

        Log.d("WheelKey", String.format("KEY_CODE: 0x%02X (%d), STATE: 0x%02X", keyCode, keyCode, keyState));

        switch (keyCode) {
            case 0x00:
                realKeyLongClick1(context, 0x00, keyState);
                break;
            case 0x01:
                realKeyLongClick1(context, HotKeyConstant.K_VOL_UP, keyState);
                break;
            case 0x02:
                realKeyLongClick1(context, HotKeyConstant.K_VOL_DN, keyState);
                break;
            case 0x03:
                buttonKey(HotKeyConstant.K_PREV);
                break;
            case 0x04:
                buttonKey(HotKeyConstant.K_NEXT);
                break;
            case 0x05:
                realKeyClick2(context, HotKeyConstant.K_PHONE_ON, keyCode, keyState);
                break;
            case 0x06:
                realKeyClick2(context, HotKeyConstant.K_MUTE, keyCode, keyState);
                break;
            case 0x07:
                realKeyClick2(context, HotKeyConstant.K_VIDEO, keyCode, keyState);
                break;
            /*case 0x08:
                realKeyClick2(context, 0x0F, keyCode, keyState);
                break;
            case 0x09:
                realKeyClick2(context, HotKeyConstant.K_SLEEP, keyCode, keyState);
                break;
            case 0x10:
                realKeyClick1(context, 0x80, keyCode, keyState);
                break;
            case 0x11:
                realKeyClick1(context, 0x34, keyCode, keyState);
                break;
            case 0x12:
                realKeyClick1(context, 0x3D, keyCode, keyState);
                break;
            case 0x13:
                realKeyClick1(context, 0x3A, keyCode, keyState);
                break;
            case 0x14:
                realKeyClick1(context, 0x3B, keyCode, keyState);
                break;
            case 0x15:
                realKeyClick1(context, HotKeyConstant.K_SPEECH, keyCode, keyState);
                break;
            case 0x16:
                realKeyClick1(context, HotKeyConstant.K_1_PICKUP, keyCode, keyState);
                break;
            case 0x17:
                realKeyClick1(context, 0x32, keyCode, keyState);
                break;
                // Nuevo debe ir a home obligatoriamente*/
            case 0x86:
                realKeyClick1(context, HotKeyConstant.K_HOME, keyCode, keyState);
                break;
            case 0x85:
                realKeyClick1(context, HotKeyConstant.K_RETURN, keyCode, keyState);
                break;
            case 0x83:
                realKeyClick3(context, HotKeyConstant.K_VOL_DN, keyCode, keyState);
                break;
            case 0x84:
                realKeyClick3(context, HotKeyConstant.K_VOL_UP, keyCode, keyState);
                break;
            case 0x87:
                if (keyState == 0x02){
                    realKeyLongClick1(context, HotKeyConstant.K_POWER, keyCode);
                }else{
                    realKeyClick3(context, HotKeyConstant.K_ENTER, keyCode, keyState);
                }
                break;
            case 0x8A:
                realKeyClick3(context, HotKeyConstant.K_LEFT, keyCode, keyState);
                break;
            case 0x88:
                realKeyClick3(context, HotKeyConstant.K_UP, keyCode, keyState);
                break;
            case 0x89:
                realKeyClick3(context, HotKeyConstant.K_DOWN, keyCode, keyState);
                break;
            case 0x8B:
                realKeyClick3(context, HotKeyConstant.K_RIGHT, keyCode, keyState);
                break;
        }
    }


    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
