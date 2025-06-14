package com.hzbhd.canbus.interfaces;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.util.amap.AMapEntity;

/* loaded from: classes2.dex */
public interface MsgMgrInterface {
    void aMapCallBack(Bundle bundle);

    void afterServiceNormalSetting(Context context);

    void atvDestdroy();

    void atvInfoChange();

    void auxInDestdroy();

    void auxInInfoChange();

    void btMusicId3InfoChange(String str, String str2, String str3);

    void btMusicInfoChange();

    void btMusiceDestdroy();

    void btPhoneCallLogInfoChange(int i, int i2, String str);

    void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2);

    void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2);

    void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2);

    void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle);

    void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2);

    void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i);

    void cameraDestroy();

    void cameraInfoChange();

    void canbusInfoChange(Context context, byte[] bArr);

    void currentVolumeInfoChange(int i, boolean z);

    boolean customLongClick(Context context, int i);

    boolean customShortClick(Context context, int i);

    void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10);

    void destroyCommand();

    void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12);

    void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3);

    void dtvInfoChange();

    void getBackCameraUiService(BackCameraUiService backCameraUiService);

    default MsgMgrInterface getInstance() {
        return this;
    }

    void initCommand(Context context);

    void linInfoChange(Context context, byte[] bArr);

    void mcuErrorState(Context context, byte[] bArr);

    void medianCalibration(Context context, byte[] bArr);

    void musicDestroy();

    void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2);

    void musicLrcInfoChange(String str);

    void notifyBtStatusChange();

    void onAMapCallBack(AMapEntity aMapEntity);

    void onAccOff();

    void onAccOn();

    void onHandshake(Context context);

    void onKeyEvent(int i, int i2, int i3, Bundle bundle);

    void onPowerOff();

    void onSleep();

    void radioDestroy();

    void radioInfoChange(int i, String str, String str2, String str3, int i2);

    void serialDataChange(Context context, byte[] bArr);

    void setMgrRefreshUiInterface(AbstractBaseActivity abstractBaseActivity);

    void sourceSwitchChange(String str);

    void sourceSwitchNoMediaInfoChange(boolean z);

    void videoDestroy();

    void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4);

    void voiceControlInfo(String str);
}
