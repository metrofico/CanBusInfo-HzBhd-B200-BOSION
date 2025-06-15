package com.hzbhd.canbus.msg_mgr;

import android.content.Context;
import android.os.Bundle;

import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;

import kotlin.jvm.internal.Intrinsics;


public final class MsgMgrInterfaceUtil implements MsgMgrInterface {
    private final Context context;

    /* renamed from: mMsgMgrInterface$delegate, reason: from kotlin metadata */
    private MsgMgrInterface mMsgMgrInterface;

    public MsgMgrInterfaceUtil(Context context) {

        this.context = context;
        this.mMsgMgrInterface = getMMsgMgrInterface();
    }

    public MsgMgrInterface getMMsgMgrInterface() {
        if (mMsgMgrInterface == null) {
            try {
                Object objNewInstance = Class.forName("com.hzbhd.canbus.car._" + CanbusConfig.INSTANCE.getCanType() + ".MsgMgr").getConstructors()[0].newInstance();
                mMsgMgrInterface = ((MsgMgrInterface) objNewInstance).getInstance();
                return mMsgMgrInterface;
            } catch (Throwable w) {
                LogUtil.showLog("MsgMgrFactory getCanMsgMgr :" + w);
            }
        }
        return mMsgMgrInterface;
    }

    public final Context getContext() {
        return this.context;
    }


    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.afterServiceNormalSetting(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void setMgrRefreshUiInterface(AbstractBaseActivity mgrRefreshUiInterface) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.setMgrRefreshUiInterface(mgrRefreshUiInterface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void mcuErrorState(Context context, byte[] errorInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.mcuErrorState(context, errorInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.canbusInfoChange(context, canbusInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void serialDataChange(Context context, byte[] serialData) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.serialDataChange(context, serialData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void getBackCameraUiService(BackCameraUiService backCameraUiService) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.getBackCameraUiService(backCameraUiService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.initCommand(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.destroyCommand();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.radioInfoChange(currClickPresetIndex, currBand, currentFreq, psName, isStereo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.radioDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.dateTimeRepCanbus(bYearTotal, bYear2Dig, bMonth, bDay, bHours, bMins, bSecond, bHours24H, systemDateFormat, isFormat24H, isFormatPm, isGpsTime, dayOfWeek);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.videoInfoChange(stoagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playMode, isPlaying, duration);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.videoDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.musicInfoChange(stoagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playModel, playIndex, isPlaying, totalTime, songTitle, songAlbum, songArtist, isReportFromPlay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.musicDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.dtvInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.discInfoChange(playModel, currentTime, playTitleNo, position, currentPlayNo, currentTotalNo, discType, isUnMuted, isPlaying, playStat, song, album, artist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int btOn, int discRadom, int discRepeat, int discExsit, int sdCardIn, int usbIn, int radioSt, int mute, int singleCycle, int fullCurve, int folderLoop, int randomFolder) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.deviceStatusInfoChange(btOn, discRadom, discRepeat, discExsit, sdCardIn, usbIn, radioSt, mute, singleCycle, fullCurve, folderLoop, randomFolder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.currentVolumeInfoChange(volValue, isMute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneIncomingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneOutGoingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneTalkingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneHangUpInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG, batteryStatus, signalValue, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG, int time) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneTalkingWithTimeInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int index, int type, String number) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneCallLogInfoChange(index, type, number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void notifyBtStatusChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.notifyBtStatusChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btMusicInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btMusiceDestdroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btMusicId3InfoChange(title, artist, album);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.auxInInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.auxInDestdroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.atvInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.atvDestdroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.sourceSwitchNoMediaInfoChange(isPowerOff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.sourceSwitchChange(source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicLrcInfoChange(String lrc) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.musicLrcInfoChange(lrc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.cameraInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.cameraDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String action) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.voiceControlInfo(action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int key) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                return mMsgMgrInterface.customLongClick(context, key);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customShortClick(Context context, int key) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                return mMsgMgrInterface.customShortClick(context, key);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void aMapCallBack(Bundle bundle) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.aMapCallBack(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onKeyEvent(int hotKey, int keyStateOrArg1, int beepOrArg2, Bundle bundle) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onKeyEvent(hotKey, keyStateOrArg1, beepOrArg2, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onAMapCallBack(aMapEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onAccOn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onAccOff();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onSleep() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onSleep();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onPowerOff() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onPowerOff();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onHandshake(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void linInfoChange(Context context, byte[] linInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.linInfoChange(context, linInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void medianCalibration(Context context, byte[] calibrationInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.medianCalibration(context, calibrationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public MsgMgrInterface getInstance() {
        return this;
    }
}
