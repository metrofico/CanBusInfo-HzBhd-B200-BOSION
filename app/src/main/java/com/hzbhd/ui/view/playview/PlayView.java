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

/* compiled from: PlayView.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b&\u0018\u00002\u00020\u0001:\u0003STUB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\b\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u00020:H\u0014J\u0012\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020\u001dH&J\b\u0010>\u001a\u00020?H&J\u0010\u0010@\u001a\u0002082\u0006\u0010A\u001a\u00020\u0012H\u0016J\b\u0010B\u001a\u000208H\u0016J\b\u0010C\u001a\u000208H&J(\u0010D\u001a\u0002082\u0006\u0010E\u001a\u00020\f2\u0006\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020\f2\u0006\u0010H\u001a\u00020\fH&J0\u0010I\u001a\u0002082\u0006\u0010J\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020\f2\u0006\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020\f2\u0006\u0010H\u001a\u00020\fH\u0014J\u0018\u0010K\u001a\u0002082\u0006\u0010L\u001a\u00020\f2\u0006\u0010M\u001a\u00020\fH\u0014J\u0006\u0010N\u001a\u000208J\u0010\u0010O\u001a\u0002082\u0006\u0010P\u001a\u00020\u001dH\u0016J\u0016\u0010Q\u001a\u0002082\u0006\u00104\u001a\u00020\f2\u0006\u00101\u001a\u00020\fJ\u0010\u0010R\u001a\u0002082\u0006\u0010A\u001a\u00020\u0012H\u0016R!\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u001a\u00101\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010+\"\u0004\b3\u0010-R\u001a\u00104\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010+\"\u0004\b6\u0010-¨\u0006V"}, d2 = {"Lcom/hzbhd/ui/view/playview/PlayView;", "Lcom/hzbhd/ui/view/lifecycle/BaseLifeRelativeLayout;", "context", "Landroid/content/Context;", "scalePlayViewInterface", "Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "(Landroid/content/Context;Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "coverReasonArray", "Ljava/util/ArrayList;", "Lcom/hzbhd/ui/view/playview/PlayView$CoverReason;", "Lkotlin/collections/ArrayList;", "getCoverReasonArray", "()Ljava/util/ArrayList;", "coverView", "Landroid/view/View;", "getCoverView", "()Landroid/view/View;", "setCoverView", "(Landroid/view/View;)V", "value", "", "fullscreen", "getFullscreen", "()Z", "setFullscreen", "(Z)V", "hideCoverDelayRunnable", "Ljava/lang/Runnable;", "getScalePlayViewInterface", "()Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "setScalePlayViewInterface", "(Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;)V", "showHeight", "getShowHeight", "()I", "setShowHeight", "(I)V", "showWidth", "getShowWidth", "setShowWidth", "videoHeight", "getVideoHeight", "setVideoHeight", "videoWidth", "getVideoWidth", "setVideoWidth", "calculateShow", "", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getPlayerSurface", "Landroid/view/Surface;", "isSoftDecoder", "getTexureView", "Landroid/view/TextureView;", "hideCover", "coverReason", "initCoverView", "initSurfaceView", "layoutSurface", "l", "t", "r", "b", "onLayout", "changed", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "reSizeDisplay", "refreshCover", "cover", "setVideoSize", "showCover", "CoverReason", "DISPLAY_SCALE", "ScalePlayViewInterface", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
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

    /* compiled from: PlayView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/hzbhd/ui/view/playview/PlayView$CoverReason;", "", "(Ljava/lang/String;I)V", "RELEASE", "DELAY", "CLICK", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum CoverReason {
        RELEASE,
        DELAY,
        CLICK
    }

    /* compiled from: PlayView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/hzbhd/ui/view/playview/PlayView$DISPLAY_SCALE;", "", "(Ljava/lang/String;I)V", "FULL_SCREEN", "_4_3", "_16_9", "ORIGINAL", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum DISPLAY_SCALE {
        FULL_SCREEN,
        _4_3,
        _16_9,
        ORIGINAL
    }

    /* compiled from: PlayView.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/hzbhd/ui/view/playview/PlayView$ScalePlayViewInterface;", "", "getDisplayScale", "Lcom/hzbhd/ui/view/playview/PlayView$DISPLAY_SCALE;", "getVideoHeight", "", "getVideoWidth", "requestSurface", "", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface ScalePlayViewInterface {
        DISPLAY_SCALE getDisplayScale();

        int getVideoHeight();

        int getVideoWidth();

        void requestSurface();
    }

    /* compiled from: PlayView.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DISPLAY_SCALE.values().length];
            iArr[DISPLAY_SCALE.FULL_SCREEN.ordinal()] = 1;
            iArr[DISPLAY_SCALE._16_9.ordinal()] = 2;
            iArr[DISPLAY_SCALE._4_3.ordinal()] = 3;
            iArr[DISPLAY_SCALE.ORIGINAL.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
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
                PlayView.m1215hideCoverDelayRunnable$lambda0(this.f$0);
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
                PlayView.m1215hideCoverDelayRunnable$lambda0(this.f$0);
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
                PlayView.m1215hideCoverDelayRunnable$lambda0(this.f$0);
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
                PlayView.m1215hideCoverDelayRunnable$lambda0(this.f$0);
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
                PlayView.m1215hideCoverDelayRunnable$lambda0(this.f$0);
            }
        };
    }

    @Override // com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout, android.widget.RelativeLayout, android.view.ViewGroup
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hideCoverDelayRunnable$lambda-0, reason: not valid java name */
    public static final void m1215hideCoverDelayRunnable$lambda0(PlayView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideCover(CoverReason.DELAY);
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
            BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.playview.PlayView.refreshCover.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    PlayView.this.getCoverView().setVisibility(0);
                }
            });
        } else {
            BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.playview.PlayView.refreshCover.2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    PlayView.this.getCoverView().setVisibility(8);
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
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.playview.PlayView.reSizeDisplay.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
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
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.playview.PlayView.setVideoSize.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
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
        int i2 = displayScale == null ? -1 : WhenMappings.$EnumSwitchMapping$0[displayScale.ordinal()];
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
