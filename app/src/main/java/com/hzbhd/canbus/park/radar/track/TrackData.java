package com.hzbhd.canbus.park.radar.track;

import android.content.Context;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.comm.Config;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Priority;

/* loaded from: classes2.dex */
public class TrackData {
    private int mType = 6;
    private int mTrackDataLen = Priority.INFO_INT;
    private byte[] mTrackData = new byte[Priority.INFO_INT];

    public void readFile(Context context) throws IOException {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(Config.TRACK_FILE_PATH);
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            inputStreamOpen.close();
            getTrackData(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTrackData(byte[] bArr) {
        int i = (this.mType * Priority.INFO_INT) + SystemConstant.THREAD_SLEEP_TIME_2000;
        int length = bArr.length;
        int i2 = this.mTrackDataLen;
        if (length >= i + i2) {
            System.arraycopy(bArr, i, this.mTrackData, 0, i2);
        }
    }

    public boolean readAngleData(int i, byte[] bArr) {
        if (i < 0) {
            i = -i;
        }
        try {
            System.arraycopy(this.mTrackData, (i + 54) * 200, bArr, 0, 200);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void setType(int i) {
        this.mType = i;
    }
}
