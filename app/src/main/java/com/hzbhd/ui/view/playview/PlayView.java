package com.hzbhd.ui.view.playview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.core.view.ViewCompat;

import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout;
import com.hzbhd.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public abstract class PlayView extends BaseLifeRelativeLayout {
    private final ArrayList<CoverReason> coverReasonArray;
    private View coverView;
    private boolean fullscreen;
    private final Runnable hideCoverDelayRunnable;
    private ScalePlayViewInterface scalePlayViewInterface;
    private int showHeight;
    private int showWidth;
    private int videoHeight;
    private int videoWidth;

    public enum CoverReason {
        RELEASE, DELAY, CLICK
    }

    public enum DISPLAY_SCALE {
        FULL_SCREEN, _4_3, _16_9, ORIGINAL
    }

    public interface ScalePlayViewInterface {
        DISPLAY_SCALE getDisplayScale();

        int getVideoHeight();

        int getVideoWidth();

        void requestSurface();
    }


    public abstract Surface getPlayerSurface(boolean isSoftDecoder);

    public abstract TextureView getTexureView();

    public abstract void initSurfaceView();

    public abstract void layoutSurface(int l, int t, int r, int b);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayView(Context context, ScalePlayViewInterface scalePlayViewInterface) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scalePlayViewInterface, "scalePlayViewInterface");
        this.coverReasonArray = new ArrayList<>();
        final Context context2 = getContext();
        this.coverView = new View(context2) { // from class: com.hzbhd.ui.view.playview.PlayView$coverView$1
            {
                setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }
        };
        initSurfaceView();
        initCoverView();
        ScalePlayViewInterface scalePlayViewInterface2 = this.scalePlayViewInterface;
        this.videoWidth = scalePlayViewInterface2 != null ? scalePlayViewInterface2.getVideoWidth() : 0;
        ScalePlayViewInterface scalePlayViewInterface3 = this.scalePlayViewInterface;
        this.videoHeight = scalePlayViewInterface3 != null ? scalePlayViewInterface3.getVideoHeight() : 0;
        this.hideCoverDelayRunnable = new Runnable() { // from class: com.hzbhd.ui.view.playview.PlayView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                hideCover(CoverReason.DELAY);
            }
        };
        this.scalePlayViewInterface = scalePlayViewInterface;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.coverReasonArray = new ArrayList<>();
        final Context context2 = getContext();
        this.coverView = new View(context2) { // from class: com.hzbhd.ui.view.playview.PlayView$coverView$1
            {
                setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }
        };
        initSurfaceView();
        initCoverView();
        ScalePlayViewInterface scalePlayViewInterface = this.scalePlayViewInterface;
        this.videoWidth = scalePlayViewInterface != null ? scalePlayViewInterface.getVideoWidth() : 0;
        ScalePlayViewInterface scalePlayViewInterface2 = this.scalePlayViewInterface;
        this.videoHeight = scalePlayViewInterface2 != null ? scalePlayViewInterface2.getVideoHeight() : 0;
        this.hideCoverDelayRunnable = new Runnable() { // from class: com.hzbhd.ui.view.playview.PlayView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                hideCover(CoverReason.DELAY);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.coverReasonArray = new ArrayList<>();
        final Context context2 = getContext();
        this.coverView = new View(context2) { // from class: com.hzbhd.ui.view.playview.PlayView$coverView$1
            {
                setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }
        };
        initSurfaceView();
        initCoverView();
        ScalePlayViewInterface scalePlayViewInterface = this.scalePlayViewInterface;
        this.videoWidth = scalePlayViewInterface != null ? scalePlayViewInterface.getVideoWidth() : 0;
        ScalePlayViewInterface scalePlayViewInterface2 = this.scalePlayViewInterface;
        this.videoHeight = scalePlayViewInterface2 != null ? scalePlayViewInterface2.getVideoHeight() : 0;
        this.hideCoverDelayRunnable = new Runnable() { // from class: com.hzbhd.ui.view.playview.PlayView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                hideCover(CoverReason.DELAY);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.coverReasonArray = new ArrayList<>();
        final Context context2 = getContext();
        this.coverView = new View(context2) { // from class: com.hzbhd.ui.view.playview.PlayView$coverView$1
            {
                setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }
        };
        initSurfaceView();
        initCoverView();
        ScalePlayViewInterface scalePlayViewInterface = this.scalePlayViewInterface;
        this.videoWidth = scalePlayViewInterface != null ? scalePlayViewInterface.getVideoWidth() : 0;
        ScalePlayViewInterface scalePlayViewInterface2 = this.scalePlayViewInterface;
        this.videoHeight = scalePlayViewInterface2 != null ? scalePlayViewInterface2.getVideoHeight() : 0;
        this.hideCoverDelayRunnable = new Runnable() { // from class: com.hzbhd.ui.view.playview.PlayView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                hideCover(CoverReason.DELAY);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.coverReasonArray = new ArrayList<>();
        final Context context2 = getContext();
        this.coverView = new View(context2) { // from class: com.hzbhd.ui.view.playview.PlayView$coverView$1
            {
                setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }
        };
        initSurfaceView();
        initCoverView();
        ScalePlayViewInterface scalePlayViewInterface = this.scalePlayViewInterface;
        this.videoWidth = scalePlayViewInterface != null ? scalePlayViewInterface.getVideoWidth() : 0;
        ScalePlayViewInterface scalePlayViewInterface2 = this.scalePlayViewInterface;
        this.videoHeight = scalePlayViewInterface2 != null ? scalePlayViewInterface2.getVideoHeight() : 0;
        this.hideCoverDelayRunnable = new Runnable() { // from class: com.hzbhd.ui.view.playview.PlayView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                hideCover(CoverReason.DELAY);
            }
        };
    }

    @Override
    // com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout, android.widget.RelativeLayout, android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new RelativeLayout.LayoutParams(-1, -1);
    }

    public final ArrayList<CoverReason> getCoverReasonArray() {
        return this.coverReasonArray;
    }

    public final ScalePlayViewInterface getScalePlayViewInterface() {
        return this.scalePlayViewInterface;
    }

    public final void setScalePlayViewInterface(ScalePlayViewInterface scalePlayViewInterface) {
        this.scalePlayViewInterface = scalePlayViewInterface;
    }

    public final View getCoverView() {
        return this.coverView;
    }

    public final void setCoverView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.coverView = view;
    }

    public void initCoverView() {
        addView(this.coverView);
    }

    public final int getVideoWidth() {
        return this.videoWidth;
    }

    public final void setVideoWidth(int i) {
        this.videoWidth = i;
    }

    public final int getVideoHeight() {
        return this.videoHeight;
    }

    public final void setVideoHeight(int i) {
        this.videoHeight = i;
    }

    public final int getShowWidth() {
        return this.showWidth;
    }

    public final void setShowWidth(int i) {
        this.showWidth = i;
    }

    public final int getShowHeight() {
        return this.showHeight;
    }

    public final void setShowHeight(int i) {
        this.showHeight = i;
    }

    public final boolean getFullscreen() {
        return this.fullscreen;
    }

    public final void setFullscreen(boolean z) {
        this.fullscreen = z;
        reSizeDisplay();
    }


    public void showCover(CoverReason coverReason) {
        Intrinsics.checkNotNullParameter(coverReason, "coverReason");
        if (coverReason != CoverReason.DELAY) {
            showCover(CoverReason.DELAY);
            removeCallbacks(this.hideCoverDelayRunnable);
            postDelayed(this.hideCoverDelayRunnable, 1000L);
        }
        if (!this.coverReasonArray.contains(coverReason)) {
            this.coverReasonArray.add(coverReason);
        }
        refreshCover(!this.coverReasonArray.isEmpty());
        if (LogUtil.log5()) {
            LogUtil.d("showCover:-- " + coverReason);
        }
    }

    public void refreshCover(boolean cover) {
        if (cover) {
            BaseUtil.INSTANCE.runUi(new Runnable() {
                @Override
                public void run() {
                    PlayView.this.getCoverView().setVisibility(View.VISIBLE);
                }
            });
        } else {
            BaseUtil.INSTANCE.runUi(new Runnable() {
                @Override
                public void run() {
                    PlayView.this.getCoverView().setVisibility(View.GONE);
                }
            });
        }
    }

    public void hideCover(CoverReason coverReason) {
        Intrinsics.checkNotNullParameter(coverReason, "coverReason");
        this.coverReasonArray.remove(coverReason);
        refreshCover(!this.coverReasonArray.isEmpty());
        if (LogUtil.log5()) {
            StringBuilder sbAppend = new StringBuilder().append("hideCover:-- ").append(coverReason).append(" :: ");
            String string = Arrays.toString(this.coverReasonArray.toArray());
            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
            LogUtil.d(sbAppend.append(string).toString());
        }
    }

    public final void reSizeDisplay() {
        if (LogUtil.log5()) {
            LogUtil.d("fullscreen: " + this.fullscreen);
        }
        calculateShow();
        BaseUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                PlayView.this.requestLayout();
            }
        });
    }

    public final void setVideoSize(int videoWidth, int videoHeight) {
        if (LogUtil.log5()) {
            LogUtil.d("setVideoSize: " + videoWidth + " , " + videoHeight);
        }
        this.videoWidth = videoWidth;
        this.videoHeight = videoHeight;
        calculateShow();
        BaseUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                PlayView.this.requestLayout();
            }
        });
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculateShow();
    }

    private final void calculateShow() {
        int i;
        this.showWidth = getMeasuredWidth();
        this.showHeight = getMeasuredHeight();
        if (this.fullscreen) {
            return;
        }
        ScalePlayViewInterface scalePlayViewInterface = this.scalePlayViewInterface;
        DISPLAY_SCALE displayScale = scalePlayViewInterface != null ? scalePlayViewInterface.getDisplayScale() : null;
        int i2 = displayScale == null ? -1 : DISPLAY_SCALE.values()[displayScale.ordinal()].ordinal();
        if (i2 == 1) {
            this.showWidth = getMeasuredWidth();
            this.showHeight = getMeasuredHeight();
        } else if (i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    if (LogUtil.log5()) {
                        LogUtil.d("calculateShow: ORIGINAL = " + this.videoWidth + ',' + this.videoHeight);
                    }
                    int i3 = this.videoWidth;
                    if (i3 == 0 || (i = this.videoHeight) == 0) {
                        this.showWidth = getMeasuredWidth();
                        this.showHeight = getMeasuredHeight();
                    } else {
                        this.showWidth = i3;
                        this.showHeight = i;
                    }
                }
            } else if ((getMeasuredHeight() * 4) / 3 > getMeasuredWidth()) {
                this.showWidth = getMeasuredWidth();
                this.showHeight = (getMeasuredWidth() * 3) / 4;
            } else {
                this.showWidth = (getMeasuredHeight() * 4) / 3;
                this.showHeight = getMeasuredHeight();
            }
        } else if ((getMeasuredHeight() * 16) / 9 > getMeasuredWidth()) {
            this.showWidth = getMeasuredWidth();
            this.showHeight = (getMeasuredWidth() * 9) / 16;
        } else {
            this.showWidth = (getMeasuredHeight() * 16) / 9;
            this.showHeight = getMeasuredHeight();
        }
        if (LogUtil.log3()) {
            LogUtil.d("calculateShow: measure: " + getMeasuredWidth() + " , " + getMeasuredHeight() + " show " + this.showWidth + " , " + this.showHeight);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int iAbs = Math.abs(l - r);
        int iAbs2 = Math.abs(t - b);
        int i = this.showWidth;
        if (i < iAbs) {
            l += (iAbs - i) / 2;
            r -= (iAbs - i) / 2;
        }
        int i2 = this.showHeight;
        if (i2 < iAbs2) {
            t += (iAbs2 - i2) / 2;
            b -= (iAbs2 - i2) / 2;
        }
        layoutSurface(l, t, r, b);
        this.coverView.layout(l, t, r, b);
    }
}
