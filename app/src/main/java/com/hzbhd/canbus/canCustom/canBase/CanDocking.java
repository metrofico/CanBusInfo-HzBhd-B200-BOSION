package com.hzbhd.canbus.canCustom.canBase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.util.LogUtil;


public class CanDocking implements MsgMgrInterface {
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
    public void btMusicId3InfoChange(String title, String artist, String album) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int index, int type, String number) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG, int time) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int key) {
        return false;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customShortClick(Context context, int key) {
        return false;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int btOn, int discRadom, int discRepeat, int discExsit, int sdCardIn, int usbIn, int radioSt, int mute, int singleCycle, int fullCurve, int folderLoop, int randomFolder) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void getBackCameraUiService(BackCameraUiService backCameraUiService) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void linInfoChange(Context context, byte[] linInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void mcuErrorState(Context context, byte[] errorInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void medianCalibration(Context context, byte[] calibrationInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicLrcInfoChange(String lrc) {
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
    public void onKeyEvent(int hotKey, int keyStateOrArg1, int beepOrArg2, Bundle bundle) {
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
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void serialDataChange(Context context, byte[] serialData) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void setMgrRefreshUiInterface(AbstractBaseActivity mgrRefreshUiInterface) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String action) {
    }

    public void updateVersionInfo(Context context, String version) {
        if (LogUtil.log5()) {
            LogUtil.d("updateVersionInfo: " + version);
        }
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_CAN_VERSION, version);
        GeneralData.INSTANCE.setCanVersion(version);
    }

    public final int[] getByteArrayToIntArray(byte[] mCanbusInfo) {
        int[] iArr = new int[mCanbusInfo.length];
        for (int i = 0; i < mCanbusInfo.length; i++) {
            iArr[i] = mCanbusInfo[i] & 0xFF; // Aplicamos el enmascarado de 255
        }
        return iArr;
    }

    protected void realKeyLongClick1(Context context, int whatKey, int keyState) {
        if (RealKeyUtil.isCompoundKey(context, whatKey, keyState)) {
            return;
        }
        RealKeyUtil.realKeyLongClick(context, whatKey, keyState);
    }

    protected void realKeyClick4(Context context, int whatKey) {
        realKeyLongClick1(context, whatKey, 1);
        realKeyLongClick1(context, whatKey, 0);
    }

    protected void startOtherActivity(Context context, ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public MsgMgrInterface getInstance() {
        return this;
    }
}
