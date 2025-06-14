package com.hzbhd.ui.view.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import com.hzbhd.util.LogUtil;

/* loaded from: classes3.dex */
public class TextDrawable extends BitmapDrawable {
    private int mGravity;
    private Paint mPaint;
    private String mText;

    public TextDrawable(Context context, Bitmap bitmap, int i, Paint paint) {
        super(context.getResources(), bitmap);
        this.mGravity = i;
        this.mPaint = paint;
    }

    public void setText(String str) {
        this.mText = str;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (LogUtil.log5()) {
            LogUtil.d("draw: " + this.mText);
        }
        if (TextUtils.isEmpty(this.mText)) {
            return;
        }
        Rect rect = new Rect();
        Paint paint = this.mPaint;
        String str = this.mText;
        paint.getTextBounds(str, 0, str.length(), rect);
        if (this.mGravity == 17) {
            canvas.drawText(this.mText, getBounds().left + ((((getBounds().right - getBounds().left) - rect.right) + rect.left) / 2.0f), getBounds().bottom - ((((getBounds().bottom - getBounds().top) - rect.bottom) + rect.top) / 2.0f), this.mPaint);
        }
    }
}
