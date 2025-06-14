package com.hzbhd.canbus.adapter.util;

import android.app.Application;
import android.content.Context;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.proxy.mcu.core.MCUMainManager;

/* loaded from: classes.dex */
public class FutureUtil {
    public static FutureUtil instance = new FutureUtil();
    int btOn;
    int discExsit;
    int discRadom;
    int discRepeat;
    int folderLoop;
    int fullCurve;
    int micOn;
    int mute;
    int radioSt;
    int randomFolder;
    int sdCardIn;
    int singleCycle;
    int usbIn;

    public void DevicesStutasRepCanbus(Context context) {
    }

    public void audioChannelRequest(SourceConstantsDef.SOURCE_ID source_id) {
    }

    public void detectParkPanoramic(boolean z, int i) {
    }

    public void discGoto(MpegConstantsDef.K_SELECT k_select, int i) {
    }

    public boolean getAutoSearchStatus() {
        return false;
    }

    public int getBrightness() {
        return 0;
    }

    public int getCanIsAppHandleKey() {
        return 0;
    }

    public boolean getPSSwitchStatus() {
        return false;
    }

    public boolean getSeekDownStatus() {
        return false;
    }

    public boolean getSeekUpStatus() {
        return false;
    }

    public int getStereoStatus() {
        return 0;
    }

    public String[] getValidFreqList(int i) {
        return new String[0];
    }

    public int getVolumeValue() {
        return 0;
    }

    public void gotoTrack(int i) {
    }

    public void initCanbusAmp(Application application) {
    }

    public int is360External() {
        return 0;
    }

    public boolean isDiscExist() {
        return false;
    }

    public boolean isHaveAtv() {
        return false;
    }

    public boolean isHaveDtv() {
        return false;
    }

    public boolean isShowBackCameraTips() {
        return false;
    }

    public void nextMpeg() {
    }

    public void pauseMpeg() {
    }

    public void playMpeg() {
    }

    public void playPresetFreq(int i) {
    }

    public void prevMpeg() {
    }

    public void repeatMpeg() {
    }

    public void reportSmartPowerInfo(byte[] bArr) {
    }

    public void reqMcuKey(byte[] bArr) {
    }

    public void sendAppSelect(String str) {
    }

    public void sendDtvPosition(int i, int i2) {
    }

    public void sendPositionExtra(int i, byte[] bArr, int i2, int i3) {
    }

    public void setBrightness(int i) {
    }

    public void setCanIsAppHandleKey(int i) {
    }

    public void setCurrentFreq(String str) {
    }

    public void setIsOneKey(int i) {
    }

    public void setParking(boolean z) {
    }

    public void setReversingBaseline(boolean z) {
    }

    public void setReversingTrackSwitch(boolean z) {
    }

    public void setVolumeVal(int i) {
    }

    public void showBackCamera() {
    }

    public void showCenterCamera() {
    }

    public void showFrontCamera() {
    }

    public void shuffleMpeg() {
    }

    public boolean supportDisc() {
        return false;
    }

    public SourceConstantsDef.SOURCE_ID getCurrentValidSource() {
        return SourceConstantsDef.SOURCE_ID.NULL;
    }

    public void reportCanbusInfo(byte[] bArr) {
        try {
            MCUMainManager.getInstance().sendMCUCanboxData(39, bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportCanbusInfoOther(byte b, byte[] bArr) {
        MCUMainManager.getInstance().sendMCUCanboxData(b, bArr);
    }

    public void reportMcuDataExtra(byte[] bArr, Context context) {
        if (Util.isCanbusCmdPorting(context)) {
            sndCanCmd(bArr);
        } else {
            reportCanbusInfo(bArr);
        }
    }

    public void sndCanCmd(byte[] bArr) {
        reportCanbusInfo(Util.byteMerger(new byte[]{22}, bArr));
    }

    public byte[] str2Bcd(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            str = str + " ";
            length = str.length();
        }
        byte[] bArr = new byte[length];
        if (length >= 2) {
            length /= 2;
        }
        byte[] bArr2 = new byte[length];
        byte[] bytes = str.getBytes();
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            byte b = bytes[i2];
            int i3 = 11;
            int i4 = (b < 48 || b > 57) ? b == 42 ? 10 : b == 35 ? 11 : 15 : b - 48;
            byte b2 = bytes[i2 + 1];
            if (b2 >= 48 && b2 <= 57) {
                i3 = b2 - 48;
            } else if (b2 == 42) {
                i3 = 10;
            } else if (b2 != 35) {
                i3 = 15;
            }
            bArr2[i] = (byte) ((i4 << 4) + i3);
        }
        return bArr2;
    }
}
