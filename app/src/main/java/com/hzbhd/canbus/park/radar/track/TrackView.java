package com.hzbhd.canbus.park.radar.track;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.text.TextUtilsCompat;
import androidx.core.view.InputDeviceCompat;

import com.hzbhd.R;
import com.hzbhd.canbus.util.LogUtil;

import java.io.IOException;

/* loaded from: classes2.dex */
public class TrackView extends View {
    private final int DATA_LENGTH;
    private int DATA_TYPE;
    private int mAngle;
    private Context mContext;
    private int mCurrentXOffSet;
    private byte[] mData;
    private int mDimenCurrentXOff;
    private int mDimenXOff;
    private int mDimenYOff;
    private Paint mPaint;
    private TrackData mTrackData;
    private int mXOff;
    private int mYOff;

    public TrackView(Context context, AttributeSet attributeSet) throws IOException {
        super(context, attributeSet);
        this.DATA_LENGTH = 200;
        this.DATA_TYPE = 20;
        this.mAngle = 0;
        this.mContext = context;
        this.mDimenXOff = (int) getResources().getDimension(R.dimen.track_view_x_off);
        this.mDimenYOff = (int) getResources().getDimension(R.dimen.track_view_y_off);
        int dimension = (int) getResources().getDimension(R.dimen.track_view_cur_x_off);
        this.mDimenCurrentXOff = dimension;
        this.mXOff = this.mDimenXOff;
        this.mYOff = this.mDimenYOff;
        this.mCurrentXOffSet = dimension;
        if (isRightToLeft()) {
            this.mXOff = (int) getResources().getDimension(R.dimen.track_view_x_off_rtl);
        }
        LogUtil.showLog("mXOff:" + this.mXOff + " mYOff:" + this.mYOff + " mCurrentXOffSet:" + this.mCurrentXOffSet + " isRightToLeft():" + isRightToLeft());
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(InputDeviceCompat.SOURCE_ANY);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(5.0f);
        this.mPaint.setAntiAlias(true);
        TrackData trackData = new TrackData();
        this.mTrackData = trackData;
        trackData.setType(this.DATA_TYPE);
        this.mTrackData.readFile(context);
        this.mData = new byte[200];
        invalidate();
    }

    public void setCurrentXOffSet(int i) {
        this.mCurrentXOffSet = i;
        invalidate();
    }

    public void setYOff(int i) {
        this.mYOff = i;
        invalidate();
    }

    public void setXOff(int i) {
        this.mXOff = i;
        invalidate();
    }

    public int getSaveCurrentXOffSet() {
        return this.mDimenCurrentXOff;
    }

    public int getSaveYOff() {
        return this.mDimenYOff;
    }

    public int getSaveXOff() {
        return this.mDimenXOff;
    }

    public boolean isRightToLeft() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(getContext().getResources().getConfiguration().getLocales().get(0)) == 1;
    }

    public void setAngle(int i) {
        this.mAngle = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        byte b;
        super.onDraw(canvas);
        this.mTrackData.readAngleData(this.mAngle, this.mData);
        byte[] bArr = this.mData;
        int i2 = bArr[1] & 255;
        int i3 = ((bArr[2] << 8) | (bArr[3] & 255)) - this.mXOff;
        int i4 = ((bArr[5] & 255) | (bArr[4] << 8)) - this.mYOff;
        int i5 = this.mAngle;
        if (i5 < 0) {
            i3 = this.mCurrentXOffSet - i3;
        }
        int i6 = i3 - i5;
        Path path = new Path();
        path.moveTo(i6, i4);
        for (int i7 = 1; i7 < i2; i7++) {
            if (this.mAngle < 0) {
                byte[] bArr2 = this.mData;
                int i8 = i7 * 2;
                i = (-bArr2[i8 + 4]) + i6;
                b = bArr2[i8 + 5];
            } else {
                byte[] bArr3 = this.mData;
                int i9 = i7 * 2;
                i = bArr3[i9 + 4] + i6;
                b = bArr3[i9 + 5];
            }
            i4 = b + i4;
            i6 = i;
            path.lineTo(i6, i4);
        }
        path.close();
        canvas.drawPath(path, this.mPaint);
    }
}
