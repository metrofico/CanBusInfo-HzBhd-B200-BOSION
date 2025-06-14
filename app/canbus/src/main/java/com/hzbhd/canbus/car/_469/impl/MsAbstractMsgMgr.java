package com.hzbhd.canbus.car._469.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.proxy.sourcemanager.SourceManager;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsAbstractMsgMgr implements MsgMgrInterface {
    private String airJsonStr;

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void aMapCallBack(Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
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
    public void getBackCameraUiService(BackCameraUiService backCameraUiService) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public MsgMgrInterface getInstance() {
        return this;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
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

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void serialDataChange(Context context, byte[] bArr) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void setMgrRefreshUiInterface(AbstractBaseActivity abstractBaseActivity) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
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

    public void updateVersionInfo(Context context, String str) {
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_CAN_VERSION, str);
        GeneralData.INSTANCE.setCanVersion(str);
    }

    protected int[] getByteArrayToIntArray(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if ((b & ByteCompanionObject.MIN_VALUE) == 0) {
                iArr[i] = b;
            } else {
                iArr[i] = b & 255;
            }
        }
        return iArr;
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    protected void SharedAirData(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportMsAirInfo(intArrayToJsonStr(iArr));
    }

    protected void ShareBasicInfo(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportMsBasicInfo(intArrayToJsonStr(iArr));
    }

    private String intArrayToJsonStr(int[] iArr) {
        this.airJsonStr = "{";
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + "}";
            } else {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + ",";
            }
        }
        return this.airJsonStr;
    }

    protected void updateSpeedInfo(int i) {
        CanbusInfoChangeListener.getInstance().reportSpeedInfo(i);
    }

    protected void startOtherActivity(Context context, ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    protected void requestVoiceSource() {
        SourceManager.getInstance().notifyAppAudio(SourceConstantsDef.SOURCE_ID.VOICE, "com.hzbhd.canbus.car._434.impl", 3, true);
    }

    public static void releaseVoiceSource() {
        SourceManager.getInstance().notifyAppAudio(SourceConstantsDef.SOURCE_ID.VOICE, "com.hzbhd.canbus.car._434.impl", 3, false);
    }
}
