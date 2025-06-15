package com.hzbhd.canbus.car._467;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.car_cus._467.air.data.AirData;
import com.hzbhd.canbus.car_cus._467.air.observer.AirBuilder;
import com.hzbhd.canbus.car_cus._467.air.window.AirWindow;
import com.hzbhd.canbus.car_cus._467.drive.data.DriveData;
import com.hzbhd.canbus.car_cus._467.drive.util.NotifyDriveDate;
import com.hzbhd.canbus.car_cus._467.smartPanel.NotifyPanel;
import com.hzbhd.canbus.car_cus._467.smartPanel.PanelState;
import com.hzbhd.canbus.car_cus._467.smartPanel.window.PanelButton;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.ui.util.BaseUtil;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.apache.log4j.Priority;


public class MsgMgr extends AbstractMsgMgr {
    public static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    public static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private AlertWindowView alertWindowView;
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private HomeWatcherReceiver mHomeKeyReceiver;
    int[] mPanelData;
    int[] mRepairData;
    PanelButton panelButton;
    private SimpleDateFormat simpleDateFormat1;
    private final String QU_LI_TAG = "DWS.ID439.TROQUE.EXTRATION";
    DecimalFormat formatInteger2 = new DecimalFormat("00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    private int timeSyncTag = 0;
    private boolean firstTag = true;
    int nowError = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._467.MsgMgr.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 252:
                    if (Settings.System.getInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_467", 0) == 1 && !MsgMgr.this.panelButton.getShowTag()) {
                        MsgMgr.this.panelButton.show();
                        break;
                    }
                    break;
                case 253:
                    if (MsgMgr.this.panelButton != null && MsgMgr.this.panelButton.getShowTag()) {
                        MsgMgr.this.panelButton.hide();
                        break;
                    }
                    break;
                case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                    if (AirWindow.getInstance().addTag) {
                        AirWindow.getInstance().hide();
                        break;
                    }
                    break;
                case 255:
                    if (!AirWindow.getInstance().addTag) {
                        AirWindow.getInstance().setAutoExit(false);
                        AirWindow.getInstance().show();
                        break;
                    }
                    break;
            }
        }
    };

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
        this.timeSyncTag = 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(final Context context) {
        super.initCommand(context);
        AirData.exitTime = SharePreUtil.getIntValue(context, "key.air.exit.time", Priority.DEBUG_INT);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
        registerAirControlListener();
        registerHomeKey(context);
        if (Settings.System.getInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_467", 0) == 1) {
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._467.MsgMgr.1
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    if (MsgMgr.this.panelButton == null) {
                        MsgMgr.this.panelButton = new PanelButton(context, new PanelButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._467.MsgMgr.1.1
                            @Override // com.hzbhd.canbus.car_cus._467.smartPanel.window.PanelButton.PanoramaListener
                            public void clickEvent() {
                                MsgMgr.this.startOtherActivity(context, HzbhdComponentName.ID439PanelActivity1280x720);
                            }
                        });
                    }
                    MsgMgr.this.panelButton.show();
                }
            });
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 48) {
            updateVersionInfo(context, getVersionStr(bArr));
            return;
        }
        if (i == 54) {
            setTime0x36();
            checkCarConfig(this.mCanBusInfoInt);
        } else {
            if (i != 55) {
                switch (i) {
                    case 32:
                        setSwc0x20();
                        break;
                    case 33:
                        setAir0x21(byteArrayToIntArray);
                        break;
                    case 34:
                        setPanel0x22(byteArrayToIntArray);
                        break;
                }
                return;
            }
            set0x37();
        }
    }

    private void checkCarConfig(int[] iArr) {
        PanelState.carConfig = iArr[8];
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._467.MsgMgr.2
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                if (PanelState.carConfig == 0) {
                    if (MsgMgr.this.panelButton == null) {
                        return null;
                    }
                    MsgMgr.this.panelButton.hide();
                    return null;
                }
                if (MsgMgr.this.panelButton == null) {
                    return null;
                }
                MsgMgr.this.panelButton.show();
                return null;
            }
        });
    }

    private void set0x37() {
        if (isRepairDataNoChange(this.mCanBusInfoInt)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        DriveData.dataA2 = sb.append(DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3])).append("").toString();
        DriveData.dataB2 = this.mCanBusInfoInt[4] + "";
        DriveData.dataC2 = (this.mCanBusInfoInt[5] - 40) + "";
        DriveData.dataD2 = (this.mCanBusInfoInt[6] * 4) + "";
        DriveData.dataA5 = this.formatDecimal1.format(this.mCanBusInfoInt[7] * 0.4f) + "";
        DriveData.dataB5 = this.formatDecimal1.format(this.mCanBusInfoInt[11] * 0.4f) + "";
        DriveData.dataC5 = this.formatDecimal1.format(this.mCanBusInfoInt[8] * 0.1f) + "";
        DriveData.dataD5 = this.formatDecimal1.format(this.mCanBusInfoInt[9] * 0.2f) + "";
        Settings.System.putInt(this.mContext.getContentResolver(), "DWS.ID439.TROQUE.EXTRATION", this.mCanBusInfoInt[10]);
        NotifyDriveDate.getInstance().update();
    }

    private void setTime0x36() {
        int i = this.timeSyncTag + 1;
        this.timeSyncTag = i;
        if (i > 10) {
            return;
        }
        if (this.simpleDateFormat1 == null) {
            this.simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
        try {
            SystemClock.setCurrentTimeMillis(this.simpleDateFormat1.parse((this.mCanBusInfoInt[2] + SystemConstant.THREAD_SLEEP_TIME_2000) + "-" + this.mCanBusInfoInt[3] + "-" + this.mCanBusInfoInt[4] + " " + this.mCanBusInfoInt[5] + ":" + this.mCanBusInfoInt[6] + ":" + this.mCanBusInfoInt[7]).getTime());
        } catch (ParseException e) {
            Log.e("TIME_SYNC_ERROR", e.toString());
            e.printStackTrace();
        }
    }

    private void setPanel0x22(int[] iArr) {
        if (isPanelDataNoChange(iArr)) {
            return;
        }
        PanelState.sp1 = DataHandleUtils.getBoolBit7(iArr[2]);
        PanelState.sp2 = DataHandleUtils.getBoolBit6(iArr[2]);
        PanelState.sp3 = DataHandleUtils.getBoolBit5(iArr[2]);
        PanelState.sp4 = DataHandleUtils.getBoolBit4(iArr[2]);
        PanelState.sp6 = DataHandleUtils.getBoolBit3(iArr[2]);
        PanelState.sp7 = DataHandleUtils.getBoolBit2(iArr[2]);
        PanelState.sp16 = DataHandleUtils.getBoolBit1(iArr[2]);
        PanelState.sp17 = DataHandleUtils.getBoolBit0(iArr[2]);
        PanelState.sp14 = iArr[3];
        PanelState.sp11 = DataHandleUtils.getBoolBit7(iArr[4]);
        PanelState.sp12 = DataHandleUtils.getBoolBit6(iArr[4]);
        PanelState.sp13 = DataHandleUtils.getBoolBit5(iArr[4]);
        PanelState.sp15 = DataHandleUtils.getBoolBit4(iArr[4]);
        PanelState.sp10 = DataHandleUtils.getBoolBit3(iArr[4]);
        PanelState.sp5 = DataHandleUtils.getBoolBit2(iArr[4]);
        PanelState.sp9 = DataHandleUtils.getBoolBit0(iArr[4]);
        PanelState.sp8 = DataHandleUtils.getBoolBit1(iArr[4]);
        PanelState.ultraVires = DataHandleUtils.getBoolBit0(iArr[5]);
        PanelState.retarder = DataHandleUtils.getBoolBit1(iArr[5]);
        PanelState.abs = DataHandleUtils.getBoolBit2(iArr[5]);
        PanelState.sp18 = DataHandleUtils.getBoolBit3(iArr[5]);
        setBacklightLevel((iArr[6] + 1) / 2);
        NotifyPanel.getInstance().update();
    }

    private void setAir0x21(int[] iArr) {
        updateOutDoorTemp(this.mContext, (iArr[7] - 40) + getTempUnitC(this.mContext));
        setAirAlert(iArr[9]);
        DriveData.tempOut = (iArr[7] - 40) + getTempUnitC(this.mContext);
        DriveData.tempIn = (iArr[6] - 40) + getTempUnitC(this.mContext);
        DriveData.tempEvaporation = (iArr[5] - 40) + getTempUnitC(this.mContext);
        NotifyDriveDate.getInstance().update();
        iArr[7] = 0;
        iArr[9] = 0;
        iArr[6] = 0;
        iArr[5] = 0;
        if (isAirDataNoChange(iArr)) {
            return;
        }
        AirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
        AirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
        AirData.in_out_cycle = DataHandleUtils.getBoolBit5(iArr[2]);
        AirData.auto = DataHandleUtils.getBoolBit4(iArr[2]);
        AirData.defog = DataHandleUtils.getBoolBit2(iArr[2]);
        AirData.heat = DataHandleUtils.getBoolBit3(iArr[2]);
        int i = iArr[4];
        AirData.wind_window = i == 4 || i == 5;
        int i2 = iArr[4];
        AirData.wind_body = i2 == 1 || i2 == 2;
        int i3 = iArr[4];
        AirData.wind_foot = i3 == 2 || i3 == 3 || i3 == 4;
        AirData.temp = iArr[8];
        AirData.wind_level = iArr[3];
        AirData.warm_level = iArr[10];
        AirData.appointmentTime = iArr[11];
        if (this.firstTag) {
            this.firstTag = false;
            return;
        }
        if (!getReverseState() && !AirWindow.getInstance().addTag) {
            AirWindow.getInstance().setAutoExit(true);
            AirWindow.getInstance().show();
        }
        AirBuilder.getInstance().dataChange();
    }

    private void setSwc0x20() {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_NEXT_HANGUP, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 206, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(this.mContext, 3, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                break;
            case 9:
                if (!AirWindow.getInstance().hide()) {
                    realKeyLongClick1(this.mContext, 50, this.mCanBusInfoInt[3]);
                    break;
                }
                break;
        }
    }

    private void setAirAlert(int i) {
        final String string;
        if (this.nowError == i) {
            return;
        }
        this.nowError = i;
        if (i == 0) {
            return;
        }
        switch (i) {
            case 1:
                string = this.mContext.getString(R.string._439_air_alert1);
                break;
            case 2:
                string = this.mContext.getString(R.string._439_air_alert2);
                break;
            case 3:
                string = this.mContext.getString(R.string._439_air_alert3);
                break;
            case 4:
                string = this.mContext.getString(R.string._439_air_alert4);
                break;
            case 5:
                string = this.mContext.getString(R.string._439_air_alert5);
                break;
            case 6:
                string = this.mContext.getString(R.string._439_air_alert6);
                break;
            case 7:
            case 9:
            case 10:
            case 15:
            case 16:
            case 17:
            case 20:
            case 23:
            case 24:
            case 28:
            default:
                string = "DEFAULT";
                break;
            case 8:
                string = this.mContext.getString(R.string._439_air_alert8);
                break;
            case 11:
                string = this.mContext.getString(R.string._439_air_alertB);
                break;
            case 12:
                string = this.mContext.getString(R.string._439_air_alertC);
                break;
            case 13:
                string = this.mContext.getString(R.string._439_air_alertD);
                break;
            case 14:
                string = this.mContext.getString(R.string._439_air_alertE);
                break;
            case 18:
                string = this.mContext.getString(R.string._439_air_alert12);
                break;
            case 19:
                string = this.mContext.getString(R.string._439_air_alert13);
                break;
            case 21:
                string = this.mContext.getString(R.string._439_air_alert15);
                break;
            case 22:
                string = this.mContext.getString(R.string._439_air_alert16);
                break;
            case 25:
                string = this.mContext.getString(R.string._439_air_alert19);
                break;
            case 26:
                string = this.mContext.getString(R.string._439_air_alert1A);
                break;
            case 27:
                string = this.mContext.getString(R.string._439_air_alert1B);
                break;
            case 29:
                string = this.mContext.getString(R.string._439_air_alert1D);
                break;
            case 30:
                string = this.mContext.getString(R.string._439_air_alert1E);
                break;
            case 31:
                string = this.mContext.getString(R.string._439_air_alert1F);
                break;
            case 32:
                string = this.mContext.getString(R.string._439_air_alert20);
                break;
            case 33:
                string = this.mContext.getString(R.string._439_air_alert21);
                break;
            case 34:
                string = this.mContext.getString(R.string._439_air_alert22);
                break;
            case 35:
                string = this.mContext.getString(R.string._439_air_alert23);
                break;
            case 36:
                string = this.mContext.getString(R.string._439_air_alert24);
                break;
            case 37:
                string = this.mContext.getString(R.string._439_air_alert25);
                break;
            case 38:
                string = this.mContext.getString(R.string._439_air_alert26);
                break;
        }
        if (string.equals("DEFAULT")) {
            return;
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._467.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                try {
                    MsgMgr.this.getAlertView().hide();
                    MsgMgr.this.getAlertView().show(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._439_air_alert) + ":" + string);
                } catch (Exception e) {
                    Log.d("Exception", "Window overlap conflict does not affect normal use" + e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AlertWindowView getAlertView() {
        if (this.alertWindowView == null) {
            this.alertWindowView = new AlertWindowView();
        }
        return this.alertWindowView;
    }

    private boolean isAirDataNoChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return true;
        }
        this.mAirData = iArr;
        return false;
    }

    private boolean isPanelDataNoChange(int[] iArr) {
        if (Arrays.equals(this.mPanelData, iArr)) {
            return true;
        }
        this.mPanelData = iArr;
        return false;
    }

    private boolean isRepairDataNoChange(int[] iArr) {
        if (Arrays.equals(this.mRepairData, iArr)) {
            return true;
        }
        this.mRepairData = iArr;
        return false;
    }

    public void registerAirControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_REQUEST_ALL_DATA, new IShareStringListener() { // from class: com.hzbhd.canbus.car._467.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m882xde17336(str);
            }
        });
    }

    /* renamed from: lambda$registerAirControlListener$0$com-hzbhd-canbus-car-_467-MsgMgr, reason: not valid java name */
    /* synthetic */ void m882xde17336(String str) {
        if (str.equals("SHOW.ID439.AIR.PAGE")) {
            Message message = new Message();
            message.what = 255;
            this.mHandler.sendMessage(message);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void reverseStateChange(boolean z) {
        super.reverseStateChange(z);
        if (z) {
            Message message = new Message();
            message.what = com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
            this.mHandler.sendMessage(message);
        }
        if (z) {
            Message message2 = new Message();
            message2.what = 253;
            this.mHandler.sendMessage(message2);
        } else {
            Message message3 = new Message();
            message3.what = 252;
            this.mHandler.sendMessage(message3);
        }
    }

    public void registerHomeKey(Context context) {
        this.mHomeKeyReceiver = new HomeWatcherReceiver();
        context.registerReceiver(this.mHomeKeyReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }

    private class HomeWatcherReceiver extends BroadcastReceiver {
        private HomeWatcherReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && "homekey".equals(intent.getStringExtra("reason"))) {
                Message message = new Message();
                message.what = com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
                MsgMgr.this.mHandler.sendMessage(message);
            }
        }
    }
}
