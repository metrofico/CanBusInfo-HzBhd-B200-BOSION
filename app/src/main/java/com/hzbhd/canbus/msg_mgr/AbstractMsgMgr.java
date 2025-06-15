package com.hzbhd.canbus.msg_mgr;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;

import com.hzbhd.R;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.activity.HybirdActivity;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.activity.OriginalCarDeviceActivity;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.activity.TirePressureActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.constant.RadioConstantsDef;
import com.hzbhd.canbus.adapter.util.CrashReportUtils;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.adapter.util.SystemUtils;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.receiver.AMapBroadcastReceiver;
import com.hzbhd.canbus.receiver.DateTimeReceiver;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.ContextUtil;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.PKeyUtil;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.canbus.view.AirPageWindowView;
import com.hzbhd.canbus.view.AirSmallView;
import com.hzbhd.canbus.view.Bubble;
import com.hzbhd.canbus.view.DisplayMsgView;
import com.hzbhd.canbus.view.DoorView;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.config.constant.PackageName;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class AbstractMsgMgr implements MsgMgrInterface {
    private static final String SHARE_AMPLIFIER_BALANCE = "share_amplifier_balance_";
    private static final String SHARE_AMPLIFIER_BASS = "share_amplifier_bass_";
    private static final String SHARE_AMPLIFIER_FADE = "share_amplifier_fade_";
    private static final String SHARE_AMPLIFIER_HEAVY_BASS = "share_amplifier_heavy_bass_";
    private static final String SHARE_AMPLIFIER_MIDDLE = "share_amplifier_middle_";
    private static final String SHARE_AMPLIFIER_TREBLE = "share_amplifier_treble_";
    private static final String SHARE_AMPLIFIER_VOLUME = "share_amplifier_volume_";
    private static final String TAG = "AbstractMsgMgr";
    private AMapBroadcastReceiver mAMapBroadcastReceiver;
    private AbstractBaseActivity mActivity;
    private AirSmallView mAirSmallView;
    private BackCameraUiService mBackCameraUiService;
    private Bubble mBubble;
    private CallBackInterface mCallBackInterface;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private DisplayMsgView mDisplayMsgView;
    private DoorView mDoorView;
    private Handler mHandler;
    private long mRealTime;
    private Map<String, int[]> mSettingMsgMap;
    private String mTempUnitC;
    private String mTempUnitF;
    public final int DATA_TYPE = 1;
    private final DecimalFormat df = new DecimalFormat("00");
    private final SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private boolean isReverse = false;
    int nowLightLevel = 5;

    public interface CallBackInterface {
        void callback();
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void aMapCallBack(Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int i, int i2, String str) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int i) {
        return false;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customShortClick(Context context, int i) {
        return false;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public MsgMgrInterface getInstance() {
        return this;
    }

    protected int getPmValue(int i) {
        if (i >= 0 && i <= 49) {
            return 1;
        }
        if (50 <= i && i <= 99) {
            return 2;
        }
        if (100 <= i && i <= 149) {
            return 3;
        }
        if (150 <= i && i <= 199) {
            return 4;
        }
        if (200 > i || i > 299) {
            return (300 > i || i > 999) ? 0 : 6;
        }
        return 5;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void linInfoChange(Context context, byte[] bArr) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void mcuErrorState(Context context, byte[] bArr) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void medianCalibration(Context context, byte[] bArr) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicLrcInfoChange(String str) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void notifyBtStatusChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onKeyEvent(int i, int i2, int i3, Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onPowerOff() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onSleep() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
    }

    public void reverseStateChange(boolean z) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void serialDataChange(Context context, byte[] bArr) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
    }

    public void updateHandbrakeState(boolean z) {
    }

    public void updateReverseGear(boolean z) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBaseActivity getActivity() {
        return this.mActivity;
    }

    public AbstractMsgMgr() {
        registerReverse();
    }

    protected boolean isActivityFront() {
        return this.mActivity.isShouldRefreshUi();
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void setMgrRefreshUiInterface(AbstractBaseActivity abstractBaseActivity) {
        this.mActivity = abstractBaseActivity;
    }

    private void registerReverse() {
        int iRegisterShareIntListener = ShareDataManager.getInstance().registerShareIntListener("sys.Reverse", new IShareIntListener() { // from class: com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.1
            @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
            public void onInt(int i) {
                AbstractMsgMgr abstractMsgMgr = AbstractMsgMgr.this;
                boolean z = i == 1 || i == 2;
                abstractMsgMgr.isReverse = z;
                AbstractMsgMgr abstractMsgMgr2 = AbstractMsgMgr.this;
                abstractMsgMgr2.reverseStateChange(abstractMsgMgr2.isReverse);
            }
        });
        boolean z = iRegisterShareIntListener == 1 || iRegisterShareIntListener == 2;
        this.isReverse = z;
    }

    public boolean getReverseState() {
        return this.isReverse;
    }

    protected void updatePKeyRadar() {
        if (Dependency.get(CanSettingProxy.class).getPKeyRadarDispCheck()) {
            if (GeneralParkData.pKeyRadarState) {
                PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).show();
            } else {
                PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).hide();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateGeneralDriveData(List<DriverUpdateEntity> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int i2 = 0; i2 < GeneralDriveData.dataList.size(); i2++) {
                if (GeneralDriveData.dataList.get(i2).getPage() == list.get(i).getPage() && GeneralDriveData.dataList.get(i2).getIndex() == list.get(i).getIndex()) {
                    GeneralDriveData.dataList.remove(i2);
                }
            }
            GeneralDriveData.dataList.add(list.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateGeneralSettingData(List<SettingUpdateEntity> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int i2 = 0; i2 < GeneralSettingData.dataList.size(); i2++) {
                if (GeneralSettingData.dataList.get(i2).getLeftListIndex() == list.get(i).getLeftListIndex() && GeneralSettingData.dataList.get(i2).getRightListIndex() == list.get(i).getRightListIndex()) {
                    GeneralSettingData.dataList.remove(i2);
                }
            }
            GeneralSettingData.dataList.add(list.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runOnUi(CallBackInterface callBackInterface) {
        this.mCallBackInterface = callBackInterface;
        if (this.mHandler == null) {
            this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.2
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    AbstractMsgMgr.this.mCallBackInterface.callback();
                    return true;
                }
            });
        }
        this.mHandler.sendEmptyMessage(0);
    }

    protected boolean isDoorMsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    protected boolean isAirMsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy)) {
            return true;
        }
        this.mCanbusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    public void clearCanbusAirInfoCopy() {
        this.mCanbusAirInfoCopy = null;
    }

    protected void enterAuxIn2() {
        CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void enterAuxIn2(Context context, ComponentName componentName) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(componentName);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void exitAuxIn2() {
        CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
    }

    protected void enterNoSource() {
        CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CANOFF, SourceConstantsDef.DISP_ID.disp_main, null);
    }

    protected void realKeyClick1(Context context, int i, int i2, int i3) {
        realKeyLongClick1(context, i, i3);
    }

    protected void realKeyClick2(Context context, int i, int i2, int i3) {
        realKeyLongClick1(context, i, i3);
    }

    protected void realKeyClick3(Context context, int i, int i2, int i3) {
        RealKeyUtil.realKeyClick3(context, i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realKeyClick3_1(Context context, int i, int i2, int i3) {
        RealKeyUtil.realKeyClick3(context, i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realKeyClick3_2(Context context, int i, int i2, int i3) {
        RealKeyUtil.realKeyClick3(context, i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realKeyClick4(Context context, int i) {
        realKeyLongClick1(context, i, 1);
        realKeyLongClick1(context, i, 0);
    }

    protected void realKeyClick5(Context context, int i, int i2, int i3) {
        realKeyLongClick1(context, i, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realKeyLongClick1(Context context, int i, int i2) {
        if (RealKeyUtil.isCompoundKey(context, i, i2)) {
            return;
        }
        RealKeyUtil.realKeyLongClick(context, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realKeyLongClick2(Context context, int i) {
        realKeyLongClick1(context, i, i == 0 ? 0 : 1);
    }

    protected void compoundKey(Context context, int[] iArr, int i) {
        RealKeyUtil.compoundKey(context, iArr, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void realKeyClick(Context context, int i) {
        RealKeyUtil.realKeyClick(context, i);
    }

    protected void sendMediaMsg(Context context, String str, byte[] bArr) {
        String str2 = TAG;
        Log.i(str2, "sendMediaMsg: context: " + context + ", media: " + str);
        if (context == null) {
            return;
        }
        String strName = SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId()).name();
        Log.i(str2, "sendMediaMsg: frontSeat:" + strName);
        CanbusMsgSender.sendMsg(bArr);
        if (SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(str)) {
            return;
        }
        Settings.System.putString(context.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(bArr, 0));
        if (SourceConstantsDef.SOURCE_ID.MPEG.name().equals(strName)) {
            return;
        }
        Settings.System.putString(context.getContentResolver(), "reportForDiscEject", Base64.encodeToString(bArr, 0));
    }

    protected void sendAfterHangUpMsg(Context context) {
        try {
            String string = Settings.System.getString(context.getContentResolver(), "reportAfterHangUp");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            CanbusMsgSender.sendMsg(Base64.decode(string, 0));
        } catch (Exception unused) {
        }
    }

    protected void sendDiscEjectMsg(Context context) {
        if (context != null && SystemClock.elapsedRealtime() - this.mRealTime >= 1000) {
            String string = Settings.System.getString(context.getContentResolver(), "reportForDiscEject");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Base64.decode(string, 0));
            this.mRealTime = SystemClock.elapsedRealtime();
        }
    }

    private boolean canSupportMPEG() {
        return FutureUtil.instance.supportDisc();
    }

    protected void requestMpeg() {
        if (canSupportMPEG()) {
            FutureUtil.instance.audioChannelRequest(SourceConstantsDef.SOURCE_ID.MPEG);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startMainActivity(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(HzbhdComponentName.NewCanBusMainActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startSettingActivity(Context context, int i, int i2) {
        Intent intent = new Intent(context, SettingActivity.class);
        intent.setAction(Constant.SETTING_OPEN_TARGET_PAGE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.LEFT_INDEX, i);
        intent.putExtra(Constant.RIGHT_INDEX, i2);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finishActivity() {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity == null) {
            return;
        }
        abstractBaseActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startOtherActivity(Context context, ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startDrivingDataActivity(Context context, int i) {
        Intent intent = new Intent(context, DriveDataActivity.class);
        intent.setAction(Constant.DRIVE_DATA_OPEN_TARGET_PAGE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constant.CURRENT_ITEM, i);
        context.startActivity(intent);
    }

    protected void startWarningActivity(Context context) {
        Intent intent = new Intent(context, WarningActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void setBacklightLevel(int i) {
        if (this.nowLightLevel == i) {
            return;
        }
        this.nowLightLevel = i;
        SendKeyManager.getInstance().setBacklightLevel(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void forceReverse(Context context, boolean z) {
        SharePreUtil.setBoolValue(context, Constant.SHARE_IS_PANORAMIC, z);
        SendKeyManager.getInstance().forceReverse(z);
    }

    protected void switchFCamera(Context context, boolean z) {
        if (SharePreUtil.getBoolValue(context, Constant.SHARE_IS_OPEN_FRONT_CAMERA, false)) {
            try {
                if (z) {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(Constant.FCameraActivity);
                    context.startActivity(intent);
                } else if (SystemUtils.isForeground(context, Constant.FCameraActivity.getClassName())) {
                    new Thread(new Runnable() { // from class: com.hzbhd.canbus.msg_mgr.AbstractMsgMgr$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public void run() {
                            new Instrumentation().sendKeyDownUpSync(4);
                        }
                    }).start();
                }
            } catch (Exception e) {
                Log.i(TAG, "switchFCamera: " + e.getMessage());
            }
        }
    }

    protected void changeBandFm1() {
        if ("FM1".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_FM1.ordinal());
        SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, bundle);
    }

    protected void changeBandFm2() {
        if ("FM2".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_FM2.ordinal());
        SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, bundle);
    }

    protected void changeBandFm3() {
        if ("FM3".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_FM3.ordinal());
        SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, bundle);
    }

    protected void changeBandAm1() {
        if ("AM1".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_AM1.ordinal());
        SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, bundle);
    }

    protected void changeBandAm2() {
        if ("AM2".equals(MediaShareData.Radio.INSTANCE.getMBand())) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.band.name(), RadioConstantsDef.BandType.BAND_AM2.ordinal());
        SendKeyManager.getInstance().setKeyEvent(76, HotKeyConstant.KeyState.CLICK.ordinal(), 0, bundle);
    }

    protected void setCurrentFreqFm(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(HotKeyConstant.ACTION_RADIO_BUNDLE.frequency.name(), str);
        SendKeyManager.getInstance().setKeyEvent(HotKeyConstant.K_ACTION_RADIO, HotKeyConstant.ACTION_RADIO_TYPE.fmto.ordinal(), 0, bundle);
    }

    protected void setCurrentFreqAm(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(HotKeyConstant.ACTION_RADIO_BUNDLE.frequency.name(), str);
        SendKeyManager.getInstance().setKeyEvent(HotKeyConstant.K_ACTION_RADIO, HotKeyConstant.ACTION_RADIO_TYPE.amto.ordinal(), 0, bundle);
    }

    protected void playPresetFreq(int i) {
        if (i != MediaShareData.Radio.INSTANCE.getMPresetIndex()) {
            Bundle bundle = new Bundle();
            bundle.putInt(HotKeyConstant.ACTION_RADIO_BUNDLE.index.name(), i - 1);
            SendKeyManager.getInstance().setKeyEvent(HotKeyConstant.K_ACTION_RADIO, HotKeyConstant.ACTION_RADIO_TYPE.preset.ordinal(), 0, bundle);
        }
    }

    protected SourceConstantsDef.SOURCE_ID getSourceId() {
        return SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId());
    }

    protected String getCurrentBand() {
        return MediaShareData.Radio.INSTANCE.getMBand();
    }

    protected int getVolume() {
        return MediaShareData.Volume.INSTANCE.getVolume();
    }

    protected void mpegPlay(Context context) {
        FutureUtil.instance.playMpeg();
    }

    protected void mpegShuffle(Context context) {
        FutureUtil.instance.shuffleMpeg();
    }

    protected void mpegRepeat(Context context) {
        FutureUtil.instance.repeatMpeg();
    }

    protected void mpegPrev(Context context) {
        FutureUtil.instance.prevMpeg();
    }

    protected void mpegNext(Context context) {
        FutureUtil.instance.nextMpeg();
    }

    protected void mpegDiscGoto(Context context, MpegConstantsDef.K_SELECT k_select, int i) {
        FutureUtil.instance.discGoto(k_select, i);
    }

    protected int getBrightness() {
        return MediaShareData.System.INSTANCE.getBackLight();
    }

    protected void playBeep() {
        CommUtil.playBeep();
    }

    protected void updateActivity(final Bundle bundle) {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null) {
            abstractBaseActivity.runOnUiThread(new Runnable() { // from class: com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AbstractMsgMgr.this.mActivity.refreshUi(bundle);
                    } catch (Exception e) {
                        LogUtil.showLog("updateActivity:" + e);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateAirActivity(final Context context, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_air_what", i);
        if (Dependency.get(CanSettingProxy.class).getAirDisplaySetup() == 1) {
            runOnUi(() -> {
                if (SystemUtil.isForeground(context, AirActivity.class.getName())) {
                    return;
                }
                if (AbstractMsgMgr.this.mAirSmallView == null) {
                    AbstractMsgMgr.this.mAirSmallView = new AirSmallView(context);
                }
                AbstractMsgMgr.this.mAirSmallView.refreshUi();
            });
        } else if (Dependency.get(CanSettingProxy.class).getAirDisplaySetup() == 0 && !((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity.getClassName().equals("com.hzbhd.canbus.activity.AirActivity") && !getReverseState()) {
            AirPageWindowView.getInstance(context).show(bundle);
        }
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if ((abstractBaseActivity instanceof AirActivity)) {
            updateActivity(bundle);
        }
        CanbusInfoChangeListener.getInstance().reportAirInfo(context);
    }


    public void updateSettingActivity(Bundle bundle) {
        LogUtil.showLog("updateSettingActivity");
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity == null) {
            LogUtil.showLog("SettingActivity mMgrRefreshUiInterface==null");
        } else if (abstractBaseActivity instanceof SettingActivity) {
            updateActivity(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateWarningActivity(Bundle bundle) {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof WarningActivity)) {
            updateActivity(bundle);
        }
    }

    protected void updateSyncActivity(Bundle bundle) {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof SyncActivity)) {
            updateActivity(bundle);
        }
    }

    protected void updateTirePressureActivity(Bundle bundle) {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof TirePressureActivity)) {
            updateActivity(bundle);
        }
    }

    protected void updateOnStarActivity(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(OnStarActivity.BUNDLE_ONSTAR_WHAT, i);
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof OnStarActivity)) {
            updateActivity(bundle);
        }
    }

    protected void updateHybirdActivity(Bundle bundle) {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof HybirdActivity)) {
            updateActivity(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateAmplifierActivity(Bundle bundle) {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof AmplifierActivity)) {
            updateActivity(bundle);
        }
    }

    public void updateVersionInfo(Context context, String str) {
        Log.i(TAG, "updateVersionInfo: " + str);
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_CAN_VERSION, str);
        GeneralData.INSTANCE.setCanVersion(str);
    }

    public void updateSpeedInfo(int i) {
        CanbusInfoChangeListener.getInstance().reportSpeedInfo(i);
    }

    public void turnLeftLamp(boolean z) {
        CanbusInfoChangeListener.getInstance().reportLeftLampInfo(z);
    }

    public void turnRightLamp(boolean z) {
        CanbusInfoChangeListener.getInstance().reportRightLampInfo(z);
    }

    public void updateDoorView(Context context) {
        if (!PackageName.autochip_avm.equals(SystemUtil.getTopActivityPackageName(context))) {
            if (this.mDoorView == null) {
                this.mDoorView = new DoorView(context);
            }
            runOnUi(new CallBackInterface() { // from class: com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.4
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    AbstractMsgMgr.this.mDoorView.refreshUi();
                }
            });
        }
        CanbusInfoChangeListener.getInstance().reportDoorInfo(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendDisplayMsgView(Context context) {
        if (this.mDisplayMsgView == null) {
            this.mDisplayMsgView = new DisplayMsgView(context);
        }
        runOnUi(new CallBackInterface() { // from class: com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.5
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                AbstractMsgMgr.this.mDisplayMsgView.refreshUi();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateOutDoorTemp(Context context, String str) {
        GeneralAirData.outdoorTemperature = str;
        if (Dependency.get(CanSettingProxy.class).getShowOutdoorTemperature()) {
            CanbusInfoChangeListener.getInstance().reportOutdoorTemperature();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateDriveDataActivity(Bundle bundle) {
        LogUtil.showLog("updateDriveDataActivity");
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof DriveDataActivity)) {
            updateActivity(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateOriginalCarDeviceActivity(Bundle bundle) {
        AbstractBaseActivity abstractBaseActivity = this.mActivity;
        if (abstractBaseActivity != null && (abstractBaseActivity instanceof OriginalCarDeviceActivity)) {
            updateActivity(bundle);
        }
    }

    public void updateParkUi(Bundle bundle, Context context) {
        updateTrack();
        if (this.mBackCameraUiService == null) {
            bindBackCameraUiService(context);
            LogUtil.showLog("BackCameraUiService mBackCameraUiService==null");
        } else {
            Message messageObtain = Message.obtain();
            messageObtain.setData(bundle);
            messageObtain.what = 1;
            this.mBackCameraUiService.getHandler().sendMessage(messageObtain);
        }
    }

    protected void updateTrack() {
        if (Dependency.get(CanSettingProxy.class).getBackTrajectiryDispCheck()) {
            if (GeneralParkData.trackAngleRecord != GeneralParkData.trackAngle) {
                GeneralParkData.trackAngleRecord = GeneralParkData.trackAngle;
                ShareDataServiceImpl.setInt(ShareConstants.SHARE_CANBUS_ANGLE, -GeneralParkData.trackAngle);
                return;
            }
            return;
        }
        if (GeneralParkData.trackAngleRecord != GeneralParkData.trackAngle) {
            GeneralParkData.trackAngleRecord = GeneralParkData.trackAngle;
            ShareDataServiceImpl.setInt(ShareConstants.SHARE_CANBUS_ANGLE, GeneralParkData.trackAngle);
        }
    }

    protected void updateVoiceBroadcast(Bundle bundle, String str) {
        Log.i(TAG, "updateVoiceBroadcast: info <--> " + str);
        CanbusInfoChangeListener.getInstance().reportVoiceBroadcast(str);
    }

    protected void updateBubble(Context context) {
        if (this.mBubble == null) {
            this.mBubble = new Bubble(context);
        }
        this.mBubble.updateBubble();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindBackCameraUiService(final Context context) {
        context.bindService(new Intent(context, BackCameraUiService.class), new ServiceConnection() { // from class: com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.6
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (componentName != null && iBinder != null) {
                    LogUtil.showLog("CanbusMsgService bindBackCameraUiService success");
                    AbstractMsgMgr.this.getBackCameraUiService(((BackCameraUiService.MyBinder) iBinder).getService());
                    return;
                }
                LogUtil.showLog("CanbusMsgService bindBackCameraUiService empty");
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                AbstractMsgMgr.this.bindBackCameraUiService(context);
            }
        }, 1);
    }

    protected boolean isDataChange(int[] iArr) {
        if (Arrays.equals(this.mCanbusDataArray.get(iArr[1]), iArr)) {
            return false;
        }
        this.mCanbusDataArray.append(iArr[1], iArr.clone());
        return true;
    }

    private int[] subBytes(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 2, iArr2, 0, (i + 2) - 2);
        return iArr2;
    }

    protected boolean isSettingMsgRepeat2(int[] iArr) {
        String strValueOf = String.valueOf(iArr[1]);
        int[] iArrSubBytes = subBytes(iArr, iArr.length - 2);
        if (this.mSettingMsgMap == null) {
            this.mSettingMsgMap = new HashMap();
        }
        if (this.mSettingMsgMap.get(strValueOf) != null && Arrays.equals(this.mSettingMsgMap.get(strValueOf), iArrSubBytes)) {
            return true;
        }
        this.mSettingMsgMap.put(strValueOf, iArrSubBytes);
        return false;
    }

    private void reportDateAndTime(Context context) {
        new DateTimeReceiver().reportDateAndTime(context);
    }

    private void checkIsAppHandleKey(Context context) {
        int canIsAppHandleKey = FutureUtil.instance.getCanIsAppHandleKey();
        String str = TAG;
        Log.i(str, "checkIsAppHandleKey: ljq, handle key from setting: " + canIsAppHandleKey);
        CanTypeAllEntity canTypeAllEntity = CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(context)).getList().get(0);
        if (canTypeAllEntity != null) {
            int is_app_handle_key = canTypeAllEntity.getIs_app_handle_key();
            Log.i(str, "checkIsAppHandleKey: ljq, handle key front entity: " + is_app_handle_key);
            if (canIsAppHandleKey != is_app_handle_key) {
                FutureUtil.instance.setCanIsAppHandleKey(is_app_handle_key);
            }
        }
    }

    public String getVersionStr(byte[] bArr) {
        CommUtil.printHexString("getVersionStr: ", bArr);
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        return new String(bArr2);
    }

    protected int[] getByteArrayToIntArray(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if ((b & Byte.MIN_VALUE) == 0) {
                iArr[i] = b;
            } else {
                iArr[i] = b & 255;
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTempUnitC(Context context) {
        if (this.mTempUnitC == null) {
            this.mTempUnitC = context.getString(R.string.str_temp_c_unit);
        }
        return this.mTempUnitC;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTempUnitF(Context context) {
        if (this.mTempUnitF == null) {
            this.mTempUnitF = context.getString(R.string.str_temp_f_unit);
        }
        return this.mTempUnitF;
    }

    protected boolean isBandFm(String str) {
        return "FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str) || "OIRT".equals(str);
    }

    protected boolean isBandAm(String str) {
        return str.equals("AM1") || str.equals("LW") || str.equals("AM2") || str.equals("MW");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getCanId() {
        return CanbusConfig.INSTANCE.getCanType();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getCurrentCanDifferentId() {
        return SelectCanTypeUtil.getCurrentCanDiffId();
    }

    protected int getCurrentEachCanId() {
        return CanbusConfig.INSTANCE.getEachId();
    }

    protected String startEndTimeMethod(int i) {
        if (i < 3600) {
            return this.df.format((i % 3600) / 60) + ":" + this.df.format(i % 60);
        }
        return this.df.format((i / 60) / 60) + ":" + this.df.format((i % 3600) / 60) + ":" + this.df.format(i % 60);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void saveAmplifierData(Context context, int i) {
        if (context == null) {
            Log.i("ljq", "saveAmplifierData: context is null");
            return;
        }
        SharePreUtil.setIntValue(context, SHARE_AMPLIFIER_FADE + i, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(context, SHARE_AMPLIFIER_BALANCE + i, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(context, SHARE_AMPLIFIER_BASS + i, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(context, SHARE_AMPLIFIER_MIDDLE + i, GeneralAmplifierData.bandMiddle);
        SharePreUtil.setIntValue(context, SHARE_AMPLIFIER_TREBLE + i, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(context, SHARE_AMPLIFIER_VOLUME + i, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(context, SHARE_AMPLIFIER_HEAVY_BASS + i, GeneralAmplifierData.megaBass);
    }

    protected void getAmplifierData(Context context, int i, AmplifierPageUiSet amplifierPageUiSet) {
        try {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_VOLUME + i, (amplifierPageUiSet.getSeekBarVolumeMax() * 7) / 10);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_FADE + i, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_BALANCE + i, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_BASS + i, amplifierPageUiSet.getBandRange());
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_MIDDLE + i, amplifierPageUiSet.getBandRange());
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_TREBLE + i, amplifierPageUiSet.getBandRange());
            GeneralAmplifierData.megaBass = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_HEAVY_BASS + i, amplifierPageUiSet.getBandRange());
        } catch (NullPointerException e) {
            Log.i("ljq", "getAmplifierData: context[" + context + "], canId[" + i + "], set[" + amplifierPageUiSet + "]");
            e.printStackTrace();
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public void registerAMap(Context context) {
        if (this.mAMapBroadcastReceiver == null) {
            Log.d(TAG, "-------->高德地图开启广播监听");
            this.mAMapBroadcastReceiver = new AMapBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(AMapUtils.AUTONAVI_STANDARD_BROADCAST_SEND);
            context.registerReceiver(this.mAMapBroadcastReceiver, intentFilter);
        }
    }

    public void unRegisterAMap(Context context) {
        AMapBroadcastReceiver aMapBroadcastReceiver = this.mAMapBroadcastReceiver;
        if (aMapBroadcastReceiver == null || context == null) {
            return;
        }
        context.unregisterReceiver(aMapBroadcastReceiver);
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        CrashReportUtils.openCanBusBugly(context, true);
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void getBackCameraUiService(BackCameraUiService backCameraUiService) {
        this.mBackCameraUiService = backCameraUiService;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        reportDateAndTime(context);
    }
}
