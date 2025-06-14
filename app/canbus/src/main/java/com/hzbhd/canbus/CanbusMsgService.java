package com.hzbhd.canbus;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.receiver.DateTimeReceiver;
import com.hzbhd.canbus.receiver.SpeechReceiver;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.ActionControlUtil;
import com.hzbhd.canbus.util.AppEnableUtil;
import com.hzbhd.canbus.util.ContextUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.commontools.SystemStatusDef;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.mcu.core.IMCUCanBoxControlListener;
import com.hzbhd.proxy.mcu.core.MCUMainManager;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CanbusMsgService extends Service {
    private static final int ENOUGH_LENGTH = 20;
    private static final int INITCOMMAND_DELAY = 1000;
    private static final int MSG_CANBOX_DATA = 1;
    private static final int MSG_LIN_DATA = 5;
    private static final int MSG_MCU_ERROR_DATA = 7;
    private static final int MSG_MEDIAN_CALIBRATION = 6;
    private static final int MSG_SERIAL_DATA = 4;
    private static final int MSG_STATUS_REVERSE = 2;
    private static final int ON_MCU_CONECT = 3;
    private static final String TAG = "CanbusMsgService";
    public static DecimalFormat df = new DecimalFormat("00");
    public static boolean mIsHaveDealWithReportCanTypeId = false;
    public static boolean mIsNeedSystemExit = false;
    private DateTimeReceiver mCanbusTimeReceiver;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private SpeechReceiver mSpeechReceiver;
    private final Binder myBinder = new Binder();
    byte[] lengthEnoughArray = new byte[20];
    private final IMCUCanBoxControlListener mCanBoxControlListener = new IMCUCanBoxControlListener() { // from class: com.hzbhd.canbus.CanbusMsgService.3
        @Override // com.hzbhd.proxy.mcu.core.IMCUCanBoxControlListener
        public void notifyCanboxData(int i, byte[] bArr) {
            Log.d(CanbusMsgService.TAG, "<notifyCanboxData> " + i + ": " + FgeString.bytes2HexString(bArr, bArr.length));
            if (161 == i) {
                Message message = new Message();
                message.what = 7;
                message.obj = bArr;
                CanbusMsgService.this.mHandler.sendMessage(message);
            }
            if (167 == i) {
                Message message2 = new Message();
                message2.what = 1;
                message2.obj = bArr;
                CanbusMsgService.this.mHandler.sendMessage(message2);
            }
            if (167 == i && bArr[0] == 91) {
                Message message3 = new Message();
                message3.what = 4;
                message3.obj = bArr;
                CanbusMsgService.this.mHandler.sendMessage(message3);
            }
            if (167 == i && bArr[0] == 92) {
                Message message4 = new Message();
                message4.what = 5;
                message4.obj = bArr;
                CanbusMsgService.this.mHandler.sendMessage(message4);
            }
            if (167 == i && bArr[0] == 93) {
                Message message5 = new Message();
                message5.what = 6;
                message5.obj = bArr;
                CanbusMsgService.this.mHandler.sendMessage(message5);
            }
        }

        @Override // com.hzbhd.proxy.mcu.core.IMCUCanBoxControlListener
        public void onMcuConn() {
            Log.d("CAN_STATE", "onMcuConn");
            Message message = new Message();
            message.what = 3;
            CanbusMsgService.this.mHandler.sendMessage(message);
        }
    };

    private void settingProgress(int i) {
    }

    public CanbusMsgService getService() {
        return this;
    }

    public void reportCanTypeId(int i) {
    }

    public void saveCanTypeId(int i) {
    }

    public void testCanBusInfo(Context context, byte[] bArr) {
    }

    public CanbusMsgService() {
        HandlerThread handlerThread = new HandlerThread("hzbhd.canbusinfo.msgservice") { // from class: com.hzbhd.canbus.CanbusMsgService.4
            {
                start();
            }
        };
        this.mHandlerThread = handlerThread;
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: com.hzbhd.canbus.CanbusMsgService.5
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        CanbusMsgService.this.canbusInfoChange((byte[]) message.obj);
                        break;
                    case 2:
                        int i = message.arg1;
                        break;
                    case 3:
                        if (CanbusMsgService.this.getMsgMgrInterface() != null) {
                            Log.d("CAN_STATE", "Time to shake hands");
                            CanbusMsgService.this.getMsgMgrInterface().onHandshake(CanbusMsgService.this);
                            break;
                        }
                        break;
                    case 4:
                        CanbusMsgService.this.serialDataChange((byte[]) message.obj);
                        break;
                    case 5:
                        if (CanbusMsgService.this.getMsgMgrInterface() != null) {
                            Log.d("Lin_Bus", "Lin bus data change");
                            CanbusMsgService.this.getMsgMgrInterface().linInfoChange(CanbusMsgService.this, (byte[]) message.obj);
                            break;
                        }
                        break;
                    case 6:
                        if (CanbusMsgService.this.getMsgMgrInterface() != null) {
                            Log.d("MedianCalibration", "Median calibration data change!");
                            CanbusMsgService.this.getMsgMgrInterface().medianCalibration(CanbusMsgService.this, (byte[]) message.obj);
                            break;
                        }
                        break;
                    case 7:
                        if (CanbusMsgService.this.getMsgMgrInterface() != null) {
                            CanbusMsgService.this.getMsgMgrInterface().mcuErrorState(CanbusMsgService.this, (byte[]) message.obj);
                            break;
                        }
                        break;
                }
            }
        };
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.myBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ContextUtil.getInstance().setContext(this);
        CanbusInfoChangeListener.getInstance();
        Log.i(TAG, "onCreate: branches/hy");
        this.mCanbusTimeReceiver = new DateTimeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        getBaseContext().registerReceiver(this.mCanbusTimeReceiver, intentFilter);
        SpeechReceiver speechReceiver = new SpeechReceiver();
        this.mSpeechReceiver = speechReceiver;
        speechReceiver.registerReceiver(this);
        if (isDataBaseReady()) {
            normalProgress(CanbusConfig.INSTANCE.getCanType());
            getMsgMgrInterface().initCommand(this);
        }
        registerCanboxListener();
        MediaShareData.INSTANCE.registerModuleListener(this);
        ActionControlUtil.registerHotKeyListener(this);
        registerAccStateListener();
    }

    private void registerAccStateListener() {
        ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_SYS_POWER, new IShareIntListener() { // from class: com.hzbhd.canbus.CanbusMsgService.1
            @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
            public void onInt(int i) {
                int i2 = AnonymousClass7.$SwitchMap$com$hzbhd$commontools$SystemStatusDef$POWER_STATUS[SystemStatusDef.POWER_STATUS.values()[i].ordinal()];
                if (i2 == 1) {
                    CanbusMsgService.this.getMsgMgrInterface().onAccOn();
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgService.this.getMsgMgrInterface().onAccOff();
                } else if (i2 == 3) {
                    CanbusMsgService.this.getMsgMgrInterface().onSleep();
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    CanbusMsgService.this.getMsgMgrInterface().onPowerOff();
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.CanbusMsgService$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$commontools$SystemStatusDef$POWER_STATUS;

        static {
            int[] iArr = new int[SystemStatusDef.POWER_STATUS.values().length];
            $SwitchMap$com$hzbhd$commontools$SystemStatusDef$POWER_STATUS = iArr;
            try {
                iArr[SystemStatusDef.POWER_STATUS.ACC_ON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$commontools$SystemStatusDef$POWER_STATUS[SystemStatusDef.POWER_STATUS.ACC_OFF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$commontools$SystemStatusDef$POWER_STATUS[SystemStatusDef.POWER_STATUS.SLEEP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$commontools$SystemStatusDef$POWER_STATUS[SystemStatusDef.POWER_STATUS.FAKE_POWER_OFF.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        unRegisterCanboxListener();
        if (getMsgMgrInterface() != null) {
            getMsgMgrInterface().destroyCommand();
        }
        DateTimeReceiver dateTimeReceiver = this.mCanbusTimeReceiver;
        if (dateTimeReceiver != null) {
            unregisterReceiver(dateTimeReceiver);
        }
        SpeechReceiver speechReceiver = this.mSpeechReceiver;
        if (speechReceiver != null) {
            speechReceiver.unregisterReceiver(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgrInterface getMsgMgrInterface() {
        return MsgMgrFactory.getCanMsgMgrUtil(this);
    }

    private boolean isDataBaseReady() throws NumberFormatException {
        int i = Integer.parseInt(SharePreUtil.getStringValue(this, Constant.SHARE_PRE_LAST_VERSION_CODE, "0"));
        LogUtil.showLog("isDataBaseReady -> lastVersionCode:" + i);
        if (i <= 2024031119) {
            SharePreUtil.setStringValue(this, Constant.SHARE_PRE_LAST_VERSION_CODE, String.valueOf(BuildConfig.VERSION_CODE));
        }
        return true;
    }

    private void normalProgress(int i) {
        LogUtil.showLog("normalProgress");
        CanTypeAllEntity dbCanTypeAllEntity = getDbCanTypeAllEntity(i);
        if (dbCanTypeAllEntity == null) {
            LogUtil.showLog("normalProgress entity==null");
            return;
        }
        LogUtil.showLog("CanbusMsgService, current can type:[" + dbCanTypeAllEntity.getCar_model() + "] [" + dbCanTypeAllEntity.getCan_type_id() + "] isShowApp:[" + dbCanTypeAllEntity.getIs_show_app() + "] is_use_new_camera:[" + dbCanTypeAllEntity.getIs_use_new_camera() + "] is_use_new_app:[" + dbCanTypeAllEntity.getIs_use_new_app() + "]");
        CanbusConfig.INSTANCE.setIsShowApp(dbCanTypeAllEntity.getIs_show_app() == 1);
        SharePreUtil.setBoolValue(this, Constant.SHARE_PRE_IS_USE_NEW_APP, dbCanTypeAllEntity.getIs_use_new_app() == 1);
        bindBackCameraUiService(this);
        AppEnableUtil.disableAux2Activity(this);
        AppEnableUtil.isShowApp(this, dbCanTypeAllEntity.getIs_show_app());
        AppEnableUtil.cusProjectSetting(this, i);
        if (getMsgMgrInterface() != null) {
            getMsgMgrInterface().afterServiceNormalSetting(this);
        }
    }

    private CanTypeAllEntity getDbCanTypeAllEntity(int i) {
        ArrayList<CanTypeAllEntity> list = CanTypeUtil.INSTANCE.getCanType(i).getList();
        if (ArrayUtils.isEmpty(list)) {
            Log.i(TAG, "getDbCanTypeAllEntity: canTypeId:" + i);
            CanbusConfig.INSTANCE.setCanType(0);
            return getDbCanTypeAllEntity(0);
        }
        int selectCarPosition = CanbusConfig.INSTANCE.getSelectCarPosition();
        LogUtil.showLog("getDbCanTypeAllEntity selectPosition:" + selectCarPosition);
        return selectCarPosition < list.size() ? list.get(selectCarPosition) : list.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindBackCameraUiService(final Context context) {
        context.bindService(new Intent(context, (Class<?>) BackCameraUiService.class), new ServiceConnection() { // from class: com.hzbhd.canbus.CanbusMsgService.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (componentName != null && iBinder != null) {
                    LogUtil.showLog("CanbusMsgService bindBackCameraUiService success");
                    BackCameraUiService service = ((BackCameraUiService.MyBinder) iBinder).getService();
                    MsgMgrInterface msgMgrInterface = CanbusMsgService.this.getMsgMgrInterface();
                    if (msgMgrInterface != null) {
                        msgMgrInterface.getBackCameraUiService(service);
                        return;
                    } else {
                        LogUtil.showLog("canbusMsgInterface==null");
                        return;
                    }
                }
                LogUtil.showLog("CanbusMsgService bindBackCameraUiService empty");
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                CanbusMsgService.this.bindBackCameraUiService(context);
            }
        }, 1);
    }

    public void canbusInfoChange(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            return;
        }
        if (getMsgMgrInterface() != null) {
            logCanData(bArr);
            if (bArr.length < 20) {
                if (CanIdSpecialConfig.isCanNotSupplement0x00InCanDataEnd(CanbusConfig.INSTANCE.getCanType())) {
                    getMsgMgrInterface().canbusInfoChange(this, bArr);
                    return;
                }
                Arrays.fill(this.lengthEnoughArray, (byte) 0);
                System.arraycopy(bArr, 0, this.lengthEnoughArray, 0, bArr.length);
                getMsgMgrInterface().canbusInfoChange(this, this.lengthEnoughArray);
                return;
            }
            getMsgMgrInterface().canbusInfoChange(this, bArr);
            return;
        }
        LogUtil.showLog("getMsgMgrInterface() == null");
    }

    private void logCanData(byte[] bArr) {
        if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getCanDataLogSwith()) {
            String str = "A7 55————>";
            for (int i = 1; i < bArr.length; i++) {
                String hexString = Integer.toHexString(CanbusMsgSender$$ExternalSyntheticBackport0.m(bArr[i]));
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str = str + hexString + " ";
            }
            Log.d("CAN_RX", str);
        }
    }

    public void serialDataChange(byte[] bArr) {
        if (bArr == null || bArr.length <= 1 || getMsgMgrInterface() == null) {
            return;
        }
        getMsgMgrInterface().serialDataChange(this, bArr);
    }

    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        if (getMsgMgrInterface() != null) {
            getMsgMgrInterface().deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        }
    }

    public void actionControl(String str) {
        ActionControlUtil.acControl(this, str);
    }

    public void musicLrcInfoChange(String str) {
        if (getMsgMgrInterface() != null) {
            getMsgMgrInterface().musicLrcInfoChange(str);
        }
    }

    public void cameraInfoChange() {
        if (getMsgMgrInterface() != null) {
            getMsgMgrInterface().cameraInfoChange();
        }
    }

    public void cameraDestroy() {
        if (getMsgMgrInterface() != null) {
            getMsgMgrInterface().cameraDestroy();
        }
    }

    public AirPageUiSet getAirPageUiSet() {
        if (UiMgrFactory.getCanUiMgr(this) == null) {
            return null;
        }
        return UiMgrFactory.getCanUiMgr(this).getAirUiSet(this);
    }

    public String getAirInfo() {
        return GeneralAirData.getAirInfo();
    }

    private void registerCanboxListener() {
        MCUMainManager.getInstance().registerMCUCanboxListener(this.mCanBoxControlListener);
    }

    private void unRegisterCanboxListener() {
        MCUMainManager.getInstance().unregisterMCUCanboxListener(this.mCanBoxControlListener);
    }

    private void registerReverseListener() {
        ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MISC_REVERSE, new IShareIntListener() { // from class: com.hzbhd.canbus.CanbusMsgService.6
            @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
            public void onInt(int i) {
                Message message = new Message();
                message.what = 2;
                message.arg1 = i;
                CanbusMsgService.this.mHandler.sendMessage(message);
            }
        });
    }
}
