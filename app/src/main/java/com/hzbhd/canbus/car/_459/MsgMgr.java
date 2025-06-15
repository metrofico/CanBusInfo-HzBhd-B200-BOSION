package com.hzbhd.canbus.car._459;

import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._459.air.AirDataBuffer;
import com.hzbhd.canbus.car._459.air.OptionAirCmd459;
import com.hzbhd.canbus.car._459.mp5_state.TaskObserver;
import com.hzbhd.canbus.car._459.mp5_state.UDiskReceiver;
import com.hzbhd.canbus.car._459.mp5_state.WifiStateOnReceiver;
import com.hzbhd.canbus.car._459.mp5_state.WifiStateReceiver;
import com.hzbhd.canbus.car._459.mp5_time.Mp5Time;
import com.hzbhd.canbus.car._459.settings.EcoWindow;
import com.hzbhd.canbus.car._459.settings.OptionSettingsCmd459;
import com.hzbhd.canbus.car._459.settings.SettingsDataBuffer;
import com.hzbhd.canbus.car._459.settings.SharedToLauncher;
import com.hzbhd.canbus.car._459.tbox_wifi.TboxWifiState;
import com.hzbhd.canbus.car._459.tbox_wifi.WifiManager;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.config.bean.UIBean;
import com.hzbhd.config.use.UI;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.json.JSONException;
import org.json.JSONObject;


public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    int[] mCanBusInfoInt;
    private Context mContext;
    int[] mTurnData;
    private UiMgr mUiMgr;
    int[] mcuErrorStateInt;
    private WifiStateReceiver receiver;
    private WifiStateOnReceiver receiverOn;
    private SimpleDateFormat simpleDateFormat1;
    private TaskObserver taskObserver;
    private int[] timeCanInfo;
    private UDiskReceiver uDiskReceiver;
    private Handler mThreadHandler = new Handler(BaseUtil.INSTANCE.getHandlerThread().getLooper()) { // from class: com.hzbhd.canbus.car._459.MsgMgr.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    };
    private int timeSyncTag = 11;
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatDecimal0 = new DecimalFormat("###0");
    String name1 = "";
    String name2 = "";
    String name3 = "";
    String name4 = "";
    String name5 = "";
    String password1 = "";
    String password2 = "";
    String password3 = "";
    Boolean isLeft = false;
    Boolean isRight = false;
    private HashMap<String, Object> dataHash = new HashMap<>();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUiMgr(context).registerCanBusAirControlListener();
        this.mContext = context;
        registerTboxWifi(context);
        registerWifiState(context);
        registerWifiStateOn(context);
        registerBtState();
        registerUsbState(context);
        registerGpsStatusListener();
        registerTaskObserver(context);
        registerTboxTimerSwitch(context);
        Mp5Time.getInstance().start();
        registerUiState();
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [com.hzbhd.canbus.car._459.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        new CountDownTimer(5000L, 1000L) { // from class: com.hzbhd.canbus.car._459.MsgMgr.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MsgMgr.this.shareToLauncher();
            }
        }.start();
    }

    private void registerTboxTimerSwitch(Context context) {
        BaseUtil.INSTANCE.getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("settings.can.tbox.time.switch.on.off"), false, new ContentObserver(this.mThreadHandler) { // from class: com.hzbhd.canbus.car._459.MsgMgr.3
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                MsgMgr.this.refreshTboxTimeSwitch();
            }
        });
        refreshTboxTimeSwitch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTboxTimeSwitch() {
        if ("off".equals(Settings.System.getString(BaseUtil.INSTANCE.getContext().getContentResolver(), "settings.can.tbox.time.switch.on.off"))) {
            this.timeSyncTag = 11;
            return;
        }
        if (this.timeSyncTag > 10) {
            this.timeSyncTag = 0;
            int[] iArr = this.timeCanInfo;
            if (iArr == null || iArr.length == 0) {
                return;
            }
            setTimer0x18FEE64A(iArr);
        }
    }

    private void registerTaskObserver(Context context) {
        TaskObserver taskObserver = new TaskObserver();
        this.taskObserver = taskObserver;
        taskObserver.setCurrStatusListener(new TaskObserver.CurrStatusListener() { // from class: com.hzbhd.canbus.car._459.MsgMgr.4
            @Override // com.hzbhd.canbus.car._459.mp5_state.TaskObserver.CurrStatusListener
            public void onNaviRunningChange(boolean z) throws JSONException {
                Log.d("DDDD", "onNaviRunningChange=" + z);
                MsgMgr.this.sendMp5StateData("MP5_NavgtSts", z ? 1 : 0);
            }

            @Override // com.hzbhd.canbus.car._459.mp5_state.TaskObserver.CurrStatusListener
            public void onEasyRunningChange(boolean z) throws JSONException {
                Log.d("DDDD", "onEasyRunningChange=" + z);
                MsgMgr.this.sendMp5StateData("MP5_PhoneConetSts", z ? 1 : 0);
            }

            @Override // com.hzbhd.canbus.car._459.mp5_state.TaskObserver.CurrStatusListener
            public void onMusicRunningChange(boolean z) throws JSONException {
                Log.d("DDDD", "onMusicRunningChange=" + z);
                MsgMgr.this.sendMp5StateData("MP5_MediaSts", z ? 1 : 0);
            }

            @Override // com.hzbhd.canbus.car._459.mp5_state.TaskObserver.CurrStatusListener
            public void onVideoRunningChange(boolean z) throws JSONException {
                Log.d("DDDD", "onVideoRunningChange=" + z);
                MsgMgr.this.sendMp5StateData("MP5_VidoSts", z ? 1 : 0);
            }

            @Override // com.hzbhd.canbus.car._459.mp5_state.TaskObserver.CurrStatusListener
            public void onRadioRunningChange(boolean z) throws JSONException {
                Log.d("DDDD", "onRadioRunningChange=" + z);
                MsgMgr.this.sendMp5StateData("MP5_RadioSts", z ? 1 : 0);
            }

            @Override // com.hzbhd.canbus.car._459.mp5_state.TaskObserver.CurrStatusListener
            public void onEmulatedChange(float f) throws JSONException {
                Log.d("DDDD", "onEmulatedChange=" + f);
                MsgMgr.this.sendMp5StateData("MP5_RAMSts", (int) (f * 100.0f));
            }

            @Override // com.hzbhd.canbus.car._459.mp5_state.TaskObserver.CurrStatusListener
            public void onMemoryChange(float f) throws JSONException {
                Log.d("DDDD", "onMemoryChange=" + f);
                MsgMgr.this.sendMp5StateData("MP5_ROMSts", (int) (f * 100.0f));
            }
        });
    }

    private void registerTboxWifi(Context context) {
        WifiManager.getInstance().init(context);
        WifiManager.getInstance().register();
    }

    private void registerUsbState(Context context) {
        this.uDiskReceiver = new UDiskReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        context.registerReceiver(this.uDiskReceiver, intentFilter);
    }

    private void registerWifiState(Context context) {
        this.receiver = new WifiStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        context.registerReceiver(this.receiver, intentFilter);
    }

    private void registerWifiStateOn(Context context) {
        this.receiverOn = new WifiStateOnReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        context.registerReceiver(this.receiverOn, intentFilter);
    }

    private void registerGpsStatusListener() {
        LocationManager locationManager = (LocationManager) this.mContext.getSystemService("location");
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            locationManager.addGpsStatusListener(new GpsStatus.Listener() { // from class: com.hzbhd.canbus.car._459.MsgMgr.5
                @Override // android.location.GpsStatus.Listener
                public void onGpsStatusChanged(int i) throws JSONException {
                    Log.d("CAN_MSG_GPS", "Event=" + i);
                    if (i == 1) {
                        Log.e("CAN_MSG_GPS", "onGpsStatusChanged started");
                        MsgMgr.this.sendMp5StateData("MP5_GPSAntennaSts", 1);
                    } else if (i == 2) {
                        Log.e("CAN_MSG_GPS", "onGpsStatusChanged stopped");
                        MsgMgr.this.sendMp5StateData("MP5_GPSAntennaSts", 0);
                    } else if (i == 3) {
                        Log.e("CAN_MSG_GPS", "onGpsStatusChanged first fix");
                    } else {
                        if (i != 4) {
                            return;
                        }
                        Log.e("CAN_MSG_GPS", "onGpsStatusChanged status");
                    }
                }
            });
        }
    }

    public void registerBtState() {
        m875lambda$registerBtState$0$comhzbhdcanbuscar_459MsgMgr(ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_BT_STATE, new IShareStringListener() { // from class: com.hzbhd.canbus.car._459.MsgMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m875lambda$registerBtState$0$comhzbhdcanbuscar_459MsgMgr(str);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkBtJson, reason: merged with bridge method [inline-methods] */
    public void m875lambda$registerBtState$0$comhzbhdcanbuscar_459MsgMgr(String str) {
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getBoolean("ENABLE")) {
                if (jSONObject.getBoolean("HFP_CONN")) {
                    sendMp5StateData("MP5_BluetoothSts", 1);
                } else {
                    sendMp5StateData("MP5_BluetoothSts", 0);
                }
            } else {
                sendMp5StateData("MP5_BluetoothSts", 2);
            }
        } catch (JSONException unused) {
        }
    }

    public void sendMp5StateData(String str, int i) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("TAG", "MP5_Sts");
            jSONObject.put("KEY", str);
            jSONObject.put("VALUE", i);
            ShareDataManager.getInstance().reportString(ShareConstants.SHARE_REQUEST_ALL_DATA, jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        super.onAccOff();
        this.mContext.unregisterReceiver(this.receiver);
        Mp5Time.getInstance().stop();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        mcuHandshake();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        mcuHandshake();
        OptionSettingsCmd459.getInstance().setER(true);
        OptionSettingsCmd459.getInstance().setLD(true);
        OptionSettingsCmd459.getInstance().setCW(true);
        shareToLauncher();
        registerWifiState(this.mContext);
        registerWifiStateOn(this.mContext);
        registerTboxWifi(this.mContext);
        refreshTboxTimeSwitch();
        Mp5Time.getInstance().start();
    }

    private void registerUiState() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_UI_ID, new IShareStringListener() { // from class: com.hzbhd.canbus.car._459.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                MsgMgr.lambda$registerUiState$1(str);
            }
        });
    }

    static /* synthetic */ void lambda$registerUiState$1(String str) {
        Log.d("LINY_YI", "UI_CHANGE=" + str);
        if ("c8".equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, -18, -18, -18, -56});
        }
        if ("c9".equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, -18, -18, -18, -55});
        }
    }

    public void mcuHandshake() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
        OptionSettingsCmd459.getInstance().initState();
    }

    public void systemUiHandshake() {
        HashMap<String, Object> map = this.dataHash;
        if (map == null || map.size() == 0) {
            return;
        }
        notifyOtherModule("AUTO", this.dataHash.get("AUTO"));
        notifyOtherModule("FRONT_DEFOG", this.dataHash.get("FRONT_DEFOG"));
        notifyOtherModule("AC", this.dataHash.get("AC"));
        notifyOtherModule("AC_MAX", this.dataHash.get("AC_MAX"));
        notifyOtherModule("PTC", this.dataHash.get("PTC"));
        notifyOtherModule("CYCLE", this.dataHash.get("CYCLE"));
        notifyOtherModule("TEMP", this.dataHash.get("TEMP"));
        notifyOtherModule("WIND_MODE", this.dataHash.get("WIND_MODE"));
        notifyOtherModule("WIND_LEVEL", this.dataHash.get("WIND_LEVEL"));
    }

    public void launcherHandshake() {
        shareToLauncher();
    }

    public void shareToLauncher() {
        new SharedToLauncher().syncStateToLauncher();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void mcuErrorState(Context context, byte[] bArr) {
        super.mcuErrorState(context, bArr);
        this.mcuErrorStateInt = getByteArrayToIntArray(bArr);
        if (LogUtil.log5()) {
            LogUtil.d("mcuErrorState(): " + bArr);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public String getVersionStr(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[i + 2];
        }
        return new String(bArr2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws JSONException {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        if (byteArrayToIntArray[1] == 255) {
            byte[] bArr2 = new byte[17];
            for (int i = 0; i < 17; i++) {
                bArr2[i] = bArr[i + 2];
            }
            try {
                if (LogUtil.log5()) {
                    LogUtil.d("canbusInfoChange: VIN=" + FgeString.bytes2BcdString(bArr2));
                }
                String str = new String(bArr2);
                SystemProperties.set("vendor.sys.vin", str);
                ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"VIN\",\"VALUE\":\"" + str + "\"}");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        switch (getMsDataType(byteArrayToIntArray)) {
            case -858993460:
                set0xCCCCCCCC(this.mCanBusInfoInt);
                break;
            case -572662307:
                setChargeDataNone(this.mCanBusInfoInt);
                break;
            case 217067496:
                set0X0CF02FE8(this.mCanBusInfoInt);
                break;
            case 415246324:
                setChargeData0x18C027F4(this.mCanBusInfoInt);
                break;
            case 415508468:
                setChargeData0x18C427F4(this.mCanBusInfoInt);
                break;
            case 418734568:
                set0x18F561E8(this.mCanBusInfoInt);
                break;
            case 418744650:
                setWifi0x18F5894A();
                break;
            case 418751769:
                setAir0x18F5A519(this.mCanBusInfoInt);
                break;
            case 418775335:
                setEco0x18F60127(this.mCanBusInfoInt);
                break;
            case 418777895:
                setEco0x18F60B27(this.mCanBusInfoInt);
                break;
            case 419322856:
                set0x18FE5BE8(this.mCanBusInfoInt);
                break;
            case 419358282:
                setTimer0x18FEE64A(this.mCanBusInfoInt);
                break;
            case 419373601:
                set0x18FF2221(this.mCanBusInfoInt);
                break;
        }
    }

    private void set0xCCCCCCCC(int[] iArr) {
        notifyChargeState("CHARGE_ACC_OPEN", Boolean.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[13], 0, 4) == 1));
    }

    private void notifyChargeState(String str, Object obj) {
        ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"" + str + "\",\"VALUE\":\"" + obj + "\"}");
    }

    private void setTimer0x18FEE64A(int[] iArr) {
        if (iArr[7] == 255 && iArr[8] == 255 && iArr[9] == 255 && iArr[10] == 255 && iArr[11] == 255 && iArr[12] == 255) {
            return;
        }
        this.timeCanInfo = iArr;
        int i = this.timeSyncTag;
        if (i <= 10) {
            this.timeSyncTag = i + 1;
        }
        if (this.timeSyncTag > 10) {
            return;
        }
        if (this.simpleDateFormat1 == null) {
            this.simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if ((Calendar.getInstance().getTimeZone() instanceof SimpleTimeZone) && ((SimpleTimeZone) Calendar.getInstance().getTimeZone()).getRawOffset() == 0) {
                this.simpleDateFormat1.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            }
        }
        try {
            SystemClock.setCurrentTimeMillis(this.simpleDateFormat1.parse((iArr[12] + 1985) + "-" + iArr[10] + "-" + ((int) (iArr[11] * 0.25d)) + " " + iArr[9] + ":" + iArr[8] + ":" + ((int) (iArr[7] * 0.25d))).getTime());
            if (LogUtil.log5()) {
                LogUtil.d("setTimer0x18FEE64A: " + this.simpleDateFormat1.getTimeZone() + "," + iArr[9]);
            }
            Settings.Global.putInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "auto_time_synced", 3);
        } catch (ParseException e) {
            Log.e("TIME_SYNC_ERROR", e.toString());
            e.printStackTrace();
        }
    }

    private void setChargeDataNone(int[] iArr) {
        if (iArr[7] == 1) {
            notifyOtherModule("CHARGE_VIEW_SHOW", false);
            notifyOtherModule("CHARGE_STATE", false);
        }
    }

    private void setChargeData0x18C027F4(int[] iArr) {
        notifyOtherModule("CHARGE_VOLTAGE", this.formatDecimal0.format(DataHandleUtils.getMsbLsbResult(iArr[12], iArr[11]) * 0.1d) + "V");
        notifyOtherModule("CHARGE_CURRENT", this.formatDecimal0.format((DataHandleUtils.getMsbLsbResult(iArr[8], iArr[7]) * 0.1d) - 3000.0d) + "A");
        int i = iArr[13];
        if (i > 100) {
            notifyOtherModule("CHARGE_PROGRESS", 100);
        } else {
            notifyOtherModule("CHARGE_PROGRESS", Integer.valueOf(i));
        }
    }

    private void setChargeData0x18C427F4(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[13], 0, 4);
        notifyOtherModule("CHARGE_STATE", Boolean.valueOf(intFromByteWithBit == 1));
        notifyOtherModule("CHARGE_STATE_ERROR", Boolean.valueOf(intFromByteWithBit != 1));
        boolean z = DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2) == 1;
        boolean z2 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 2, 2) == 1;
        boolean z3 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 2) == 1;
        if (LogUtil.log5()) {
            LogUtil.d("setChargeData0x18C427F4(): -------" + z + "----" + z2 + "-----" + z3);
        }
        notifyOtherModule("CHARGE_VIEW_SHOW", Boolean.valueOf(z || z2 || z3));
        notifyOtherModule("CHARGE_CONNECT1", Boolean.valueOf(z));
        notifyOtherModule("CHARGE_CONNECT2", Boolean.valueOf(z2));
        notifyOtherModule("CHARGE_CONNECT3", Boolean.valueOf(z3));
        notifyOtherModule("CHARGE_TIMER", String.valueOf(iArr[12] * 60 * 1000));
    }

    private void setWifi0x18F5894A() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[7];
        if (i == 1) {
            WifiManager.getInstance().requestAction(1);
            return;
        }
        if (i == 16) {
            int i2 = iArr[8];
            int i3 = iArr[9];
            if (i3 == 0) {
                notifyOtherModule("WIFI_CONNECT", "OFF");
                TboxWifiState.connect = "OFF";
            } else if (i3 == 1) {
                notifyOtherModule("WIFI_CONNECT", "ON");
                TboxWifiState.connect = "ON";
            }
            int i4 = this.mCanBusInfoInt[10];
            if (i4 == 0) {
                WifiManager.getInstance().requestAction(1);
                return;
            }
            if (i4 == 1) {
                return;
            }
            if (i4 == 2) {
                WifiManager.getInstance().modifyWiFiName();
                return;
            }
            if (i4 == 3) {
                WifiManager.getInstance().modifyWiFiPassword();
                return;
            } else {
                if (i4 == 4) {
                    notifyOtherModule("WIFI_PROGRESS", false);
                    WifiManager.getInstance().requestAction(1);
                    return;
                }
                return;
            }
        }
        if (i == 32) {
            this.name1 = getResult(iArr);
            return;
        }
        if (i == 33) {
            this.name2 = getResult(iArr);
            return;
        }
        if (i == 34) {
            this.name3 = getResult(iArr);
            return;
        }
        if (i == 35) {
            this.name4 = getResult(iArr);
            return;
        }
        if (i == 36) {
            this.name5 = getResult(iArr);
            notifyOtherModule("WIFI_NAME", this.name1 + this.name2 + this.name3 + this.name4 + this.name5);
            TboxWifiState.name = this.name1 + this.name2 + this.name3 + this.name4 + this.name5;
        } else {
            if (i == 48) {
                this.password1 = getResult(iArr);
                return;
            }
            if (i == 49) {
                this.password2 = getResult(iArr);
            } else if (i == 50) {
                this.password3 = getResult(iArr);
                notifyOtherModule("WIFI_PASSWORD", this.password1 + this.password2 + this.password3);
                TboxWifiState.password = this.password1 + this.password2 + this.password3;
            }
        }
    }

    private String getResult(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        int i = iArr[8];
        if (i != 255) {
            arrayList.add(Integer.valueOf(i));
        }
        int i2 = iArr[9];
        if (i2 != 255) {
            arrayList.add(Integer.valueOf(i2));
        }
        int i3 = iArr[10];
        if (i3 != 255) {
            arrayList.add(Integer.valueOf(i3));
        }
        int i4 = iArr[11];
        if (i4 != 255) {
            arrayList.add(Integer.valueOf(i4));
        }
        int i5 = iArr[12];
        if (i5 != 255) {
            arrayList.add(Integer.valueOf(i5));
        }
        int i6 = iArr[13];
        if (i6 != 255) {
            arrayList.add(Integer.valueOf(i6));
        }
        int i7 = iArr[14];
        if (i7 != 255) {
            arrayList.add(Integer.valueOf(i7));
        }
        byte[] bArr = new byte[arrayList.size()];
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            bArr[i8] = (byte) ((Integer) arrayList.get(i8)).intValue();
        }
        return arrayList.size() == 0 ? "" : new String(bArr);
    }

    private void setEco0x18F60127(int[] iArr) {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[8], iArr[7]) / 256);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[13], 0, 1);
        if (((DataHandleUtils.getIntFromByteWithBit(iArr[12], 6, 2) & 255) | ((intFromByteWithBit & 255) << 2)) == 3) {
            SettingsDataBuffer.getInstance().setSpsBuffer(true);
        } else {
            SettingsDataBuffer.getInstance().setSpsBuffer(false);
        }
    }

    private void setEco0x18F60B27(int[] iArr) {
        if (DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2) == 1) {
            BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._459.MsgMgr.6
                @Override // kotlin.jvm.functions.Function0
                public Unit invoke() {
                    EcoWindow.getInstance().show(MsgMgr.this.mContext, UI.INSTANCE.getUI(BaseUtil.INSTANCE.getContext(), UIBean.AppName.launcher), new EcoWindow.ActionCallback() { // from class: com.hzbhd.canbus.car._459.MsgMgr.6.1
                        @Override // com.hzbhd.canbus.car._459.settings.EcoWindow.ActionCallback
                        public void actionDo(String str) {
                            if (str.equals("countdown")) {
                                OptionSettingsCmd459.getInstance().setEcoRequestCmd(2);
                            }
                            if (str.equals("cancel")) {
                                OptionSettingsCmd459.getInstance().setEcoRequestCmd(0);
                            }
                            if (str.equals(SyncAction.KEYBOARD_OK)) {
                                OptionSettingsCmd459.getInstance().setEcoRequestCmd(1);
                            }
                        }
                    });
                    return null;
                }
            });
        }
    }

    private void set0x18FF2221(int[] iArr) throws JSONException {
        int i = Settings.System.getInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "video_did_types_of", 0);
        int iBlockBit = DataHandleUtils.blockBit(iArr[7], 0);
        iArr[7] = iBlockBit;
        int iBlockBit2 = DataHandleUtils.blockBit(iBlockBit, 1);
        iArr[7] = iBlockBit2;
        int iBlockBit3 = DataHandleUtils.blockBit(iBlockBit2, 2);
        iArr[7] = iBlockBit3;
        iArr[7] = DataHandleUtils.blockBit(iBlockBit3, 3);
        iArr[8] = 0;
        iArr[9] = 0;
        iArr[10] = 0;
        iArr[11] = 0;
        iArr[12] = 0;
        iArr[13] = 0;
        iArr[14] = 0;
        if (isTurnDataChange(iArr)) {
            boolean z = DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 2) == 1;
            boolean z2 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 6, 2) == 1;
            this.isLeft = Boolean.valueOf(z);
            this.isRight = Boolean.valueOf(z2);
            if (!z && !z2) {
                BaseUtil.INSTANCE.runBackDelay(new Function0() { // from class: com.hzbhd.canbus.car._459.MsgMgr$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return this.f$0.m876lambda$set0x18FF2221$2$comhzbhdcanbuscar_459MsgMgr();
                    }
                }, 3000L);
            } else if (i != 1) {
                if (LogUtil.log5()) {
                    LogUtil.d("set0x18FF2221(): --------");
                }
                ShareDataManager.getInstance().reportInt(ShareConstants.SHARE_USER_REVERSE, 1);
            }
            if (i != 1) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("TAG", "TURN_SIGNAL");
                    jSONObject.put("LEFT", z);
                    jSONObject.put("RIGHT", z2);
                    ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, jSONObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: lambda$set0x18FF2221$2$com-hzbhd-canbus-car-_459-MsgMgr, reason: not valid java name */
    /* synthetic */ Unit m876lambda$set0x18FF2221$2$comhzbhdcanbuscar_459MsgMgr() {
        if (this.isLeft.booleanValue() || this.isRight.booleanValue()) {
            return null;
        }
        ShareDataManager.getInstance().reportInt(ShareConstants.SHARE_USER_REVERSE, 0);
        return null;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void reverseStateChange(boolean z) {
        super.reverseStateChange(z);
        if (LogUtil.log5()) {
            LogUtil.d("reverseStateChange(): " + z);
        }
        ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"REVERSE_STATUS\",\"VALUE\":\"" + z + "\"}");
    }

    private void set0X0CF02FE8(int[] iArr) {
        if (DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 4) != 2) {
            SettingsDataBuffer.getInstance().setCwBuffer(true);
        } else {
            SettingsDataBuffer.getInstance().setCwBuffer(false);
        }
    }

    private void set0x18FE5BE8(int[] iArr) {
        if (DataHandleUtils.getIntFromByteWithBit(iArr[7], 6, 2) == 1) {
            if (LogUtil.log5()) {
                LogUtil.d("set0x18FE5BE8(): ------------");
            }
            SettingsDataBuffer.getInstance().setLdBuffer(true);
        } else {
            if (LogUtil.log5()) {
                LogUtil.d("set0x18FE5BE8(): ------------+++");
            }
            SettingsDataBuffer.getInstance().setLdBuffer(false);
        }
    }

    private void set0x18F561E8(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2);
        if (intFromByteWithBit == 0) {
            SettingsDataBuffer.getInstance().setVdmBuffer("FAR");
        } else if (intFromByteWithBit == 1) {
            SettingsDataBuffer.getInstance().setVdmBuffer("MID");
        } else if (intFromByteWithBit == 2) {
            SettingsDataBuffer.getInstance().setVdmBuffer("NEAR");
        }
        if (DataHandleUtils.getIntFromByteWithBit(iArr[9], 2, 2) == 1) {
            if (LogUtil.log5()) {
                LogUtil.d("set0x18F561E8(): --------");
            }
            SettingsDataBuffer.getInstance().setSaBuffer(true);
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[9], 2, 2) == 0) {
            SettingsDataBuffer.getInstance().setSaBuffer(false);
        }
    }

    private void setAir0x18F5A519(int[] iArr) {
        if (isAirDataChange(iArr)) {
            ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"SHOW_AIR_WINDOW\",\"VALUE\":\"" + (DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2) == 0 || DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2) == 2) + "\"}");
            boolean z = DataHandleUtils.getIntFromByteWithBit(iArr[7], 2, 2) == 1;
            if (OptionAirCmd459.getInstance().auto != z) {
                OptionAirCmd459.getInstance().auto = z;
            }
            boolean z2 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 6, 2) == 1;
            boolean z3 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 2) == 2;
            boolean z4 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 2) == 1;
            boolean z5 = DataHandleUtils.getIntFromByteWithBit(iArr[10], 0, 2) == 1;
            boolean z6 = DataHandleUtils.getIntFromByteWithBit(iArr[8], 0, 2) == 1;
            double d = iArr[11] * 0.5d;
            if (d > 32.0d) {
                d = 32.0d;
            }
            double d2 = d < 17.0d ? 17.0d : d;
            int i = iArr[13] - 40;
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[9], 4, 4);
            AirDataBuffer.getInstance().setData(DataHandleUtils.getIntFromByteWithBit(iArr[9], 0, 4), intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? intFromByteWithBit != 3 ? intFromByteWithBit != 4 ? intFromByteWithBit != 5 ? "NONE" : "FOOT_WINDOW" : "FACE_FOOT" : "WINDOW" : "FACE" : "FOOT", d2, z6, z5, z4, z3, z2, z, i);
        }
    }

    private void notifyOtherModule(String str, Object obj) {
        ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"" + str + "\",\"VALUE\":\"" + obj + "\"}");
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTurnDataChange(int[] iArr) {
        if (Arrays.equals(this.mTurnData, iArr)) {
            return false;
        }
        this.mTurnData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
