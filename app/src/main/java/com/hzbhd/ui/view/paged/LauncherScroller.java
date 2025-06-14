package com.hzbhd.ui.view.paged;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LauncherScroller.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u000e\u0018\u0000 Y2\u00020\u0001:\u0001YB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010;\u001a\u00020<J\u0010\u0010=\u001a\u00020\n2\u0006\u0010>\u001a\u00020\nH\u0002J\u0006\u0010?\u001a\u00020\u0007J\u000e\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020\u000eJF\u0010B\u001a\u00020<2\u0006\u00107\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000e2\u0006\u0010E\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eJ\u000e\u0010I\u001a\u00020<2\u0006\u0010J\u001a\u00020\u0007J\u0010\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\nH\u0002J\u0010\u0010N\u001a\u00020L2\u0006\u0010M\u001a\u00020\nH\u0002J\u0010\u0010O\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020\nH\u0002J\u0016\u0010P\u001a\u00020\u00072\u0006\u0010Q\u001a\u00020\n2\u0006\u0010R\u001a\u00020\nJ\u000e\u0010S\u001a\u00020<2\u0006\u0010>\u001a\u00020\nJ\u0010\u0010T\u001a\u00020<2\b\u0010\u0004\u001a\u0004\u0018\u00010,J2\u0010U\u001a\u00020<2\u0006\u00107\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\u000e2\u0006\u0010W\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u000eH\u0007J\u0006\u0010X\u001a\u00020\u000eR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R$\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u001aR\u001e\u0010\u001f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00107\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0011R\u001e\u00109\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0011¨\u0006Z"}, d2 = {"Lcom/hzbhd/ui/view/paged/LauncherScroller;", "", "context", "Landroid/content/Context;", "interpolator", "Landroid/view/animation/Interpolator;", "flywheel", "", "(Landroid/content/Context;Landroid/view/animation/Interpolator;Z)V", "currVelocity", "", "getCurrVelocity", "()F", "<set-?>", "", "currX", "getCurrX", "()I", "currY", "getCurrY", "duration", "getDuration", "newX", "finalX", "getFinalX", "setFinalX", "(I)V", "newY", "finalY", "getFinalY", "setFinalY", "isFinished", "()Z", "mCurrVelocity", "mDeceleration", "mDeltaX", "mDeltaY", "mDistance", "mDurationReciprocal", "mFinalX", "mFinalY", "mFlingFriction", "mFlywheel", "mInterpolator", "Landroid/animation/TimeInterpolator;", "mMaxX", "mMaxY", "mMinX", "mMinY", "mMode", "mPhysicalCoeff", "mPpi", "mStartTime", "", "mVelocity", "startX", "getStartX", "startY", "getStartY", "abortAnimation", "", "computeDeceleration", "friction", "computeScrollOffset", "extendDuration", "extend", "fling", "velocityX", "velocityY", "minX", "maxX", "minY", "maxY", "forceFinished", "finished", "getSplineDeceleration", "", "velocity", "getSplineFlingDistance", "getSplineFlingDuration", "isScrollingInDirection", "xvel", "yvel", "setFriction", "setInterpolator", "startScroll", "dx", "dy", "timePassed", "Companion", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class LauncherScroller {
    private static final int DEFAULT_DURATION = 250;
    private static final float END_TENSION = 1.0f;
    private static final int FLING_MODE = 1;
    private static final float INFLEXION = 0.35f;
    private static final int NB_SAMPLES = 100;
    private static final float P1 = 0.175f;
    private static final float P2 = 0.35000002f;
    private static final int SCROLL_MODE = 0;
    private static final float START_TENSION = 0.5f;
    private static float sViscousFluidNormalize;
    private static float sViscousFluidScale;
    private int currX;
    private int currY;
    private int duration;
    private boolean isFinished;
    private float mCurrVelocity;
    private float mDeceleration;
    private float mDeltaX;
    private float mDeltaY;
    private int mDistance;
    private float mDurationReciprocal;
    private int mFinalX;
    private int mFinalY;
    private float mFlingFriction;
    private final boolean mFlywheel;
    private TimeInterpolator mInterpolator;
    private int mMaxX;
    private int mMaxY;
    private int mMinX;
    private int mMinY;
    private int mMode;
    private final float mPhysicalCoeff;
    private final float mPpi;
    private long mStartTime;
    private float mVelocity;
    private int startX;
    private int startY;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float DECELERATION_RATE = (float) (Math.log(0.78d) / Math.log(0.9d));
    private static final float[] SPLINE_POSITION = new float[101];
    private static final float[] SPLINE_TIME = new float[101];

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LauncherScroller(Context context) {
        this(context, null, false, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LauncherScroller(Context context, Interpolator interpolator) {
        this(context, interpolator, false, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void startScroll(int i, int i2, int i3, int i4) {
        startScroll$default(this, i, i2, i3, i4, 0, 16, null);
    }

    public LauncherScroller(Context context, Interpolator interpolator, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.isFinished = true;
        this.mFlingFriction = ViewConfiguration.getScrollFriction();
        this.mInterpolator = interpolator;
        this.mPpi = context.getResources().getDisplayMetrics().density * 160.0f;
        this.mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
        this.mFlywheel = z;
        this.mPhysicalCoeff = computeDeceleration(0.84f);
    }

    public /* synthetic */ LauncherScroller(Context context, Interpolator interpolator, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : interpolator, (i & 4) != 0 ? context.getApplicationInfo().targetSdkVersion >= 11 : z);
    }

    public final int getStartX() {
        return this.startX;
    }

    public final int getStartY() {
        return this.startY;
    }

    public final int getCurrX() {
        return this.currX;
    }

    public final int getCurrY() {
        return this.currY;
    }

    public final int getDuration() {
        return this.duration;
    }

    /* renamed from: isFinished, reason: from getter */
    public final boolean getIsFinished() {
        return this.isFinished;
    }

    /* compiled from: LauncherScroller.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/hzbhd/ui/view/paged/LauncherScroller$Companion;", "", "()V", "DECELERATION_RATE", "", "DEFAULT_DURATION", "", "END_TENSION", "FLING_MODE", "INFLEXION", "NB_SAMPLES", "P1", "P2", "SCROLL_MODE", "SPLINE_POSITION", "", "SPLINE_TIME", "START_TENSION", "sViscousFluidNormalize", "sViscousFluidScale", "viscousFluid", "x", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final float viscousFluid(float x) {
            float fExp;
            float f = x * LauncherScroller.sViscousFluidScale;
            if (f < LauncherScroller.END_TENSION) {
                fExp = f - (LauncherScroller.END_TENSION - ((float) Math.exp(-f)));
            } else {
                fExp = ((LauncherScroller.END_TENSION - ((float) Math.exp(LauncherScroller.END_TENSION - f))) * 0.63212055f) + 0.36787945f;
            }
            return fExp * LauncherScroller.sViscousFluidNormalize;
        }
    }

    static {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9 = 0.0f;
        float f10 = 0.0f;
        for (int i = 0; i < 100; i++) {
            float f11 = i / 100;
            float f12 = 1.0f;
            while (true) {
                f = ((f12 - f9) / 2.0f) + f9;
                f2 = END_TENSION - f;
                f3 = f * 3.0f * f2;
                f4 = f * f * f;
                float f13 = (((f2 * P1) + (f * P2)) * f3) + f4;
                if (Math.abs(f13 - f11) < 1.0E-5d) {
                    break;
                } else if (f13 > f11) {
                    f12 = f;
                } else {
                    f9 = f;
                }
            }
            float[] fArr = SPLINE_POSITION;
            float f14 = START_TENSION;
            fArr[i] = (f3 * ((f2 * START_TENSION) + f)) + f4;
            float f15 = 1.0f;
            while (true) {
                f5 = ((f15 - f10) / 2.0f) + f10;
                f6 = END_TENSION - f5;
                f7 = f5 * 3.0f * f6;
                f8 = f5 * f5 * f5;
                float f16 = (((f6 * f14) + f5) * f7) + f8;
                if (Math.abs(f16 - f11) >= 1.0E-5d) {
                    if (f16 > f11) {
                        f15 = f5;
                    } else {
                        f10 = f5;
                    }
                    f14 = START_TENSION;
                }
            }
            SPLINE_TIME[i] = (f7 * ((f6 * P1) + (f5 * P2))) + f8;
        }
        SPLINE_TIME[100] = 1.0f;
        SPLINE_POSITION[100] = 1.0f;
        Companion companion = INSTANCE;
        sViscousFluidScale = 8.0f;
        sViscousFluidNormalize = END_TENSION;
        sViscousFluidNormalize = END_TENSION / companion.viscousFluid(END_TENSION);
    }

    public final void setInterpolator(TimeInterpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public final void setFriction(float friction) {
        this.mDeceleration = computeDeceleration(friction);
        this.mFlingFriction = friction;
    }

    private final float computeDeceleration(float friction) {
        return this.mPpi * 386.0878f * friction;
    }

    public final void forceFinished(boolean finished) {
        this.isFinished = finished;
    }

    public final float getCurrVelocity() {
        return this.mMode == 1 ? this.mCurrVelocity : this.mVelocity - ((this.mDeceleration * timePassed()) / 2000.0f);
    }

    /* renamed from: getFinalX, reason: from getter */
    public final int getMFinalX() {
        return this.mFinalX;
    }

    public final void setFinalX(int i) {
        this.mFinalX = i;
        this.mDeltaX = i - this.startX;
        this.isFinished = false;
    }

    /* renamed from: getFinalY, reason: from getter */
    public final int getMFinalY() {
        return this.mFinalY;
    }

    public final void setFinalY(int i) {
        this.mFinalY = i;
        this.mDeltaY = i - this.startY;
        this.isFinished = false;
    }

    public final boolean computeScrollOffset() {
        float interpolation;
        if (this.isFinished) {
            return false;
        }
        int iCurrentAnimationTimeMillis = (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
        int i = this.duration;
        if (iCurrentAnimationTimeMillis < i) {
            int i2 = this.mMode;
            if (i2 == 0) {
                float f = iCurrentAnimationTimeMillis * this.mDurationReciprocal;
                TimeInterpolator timeInterpolator = this.mInterpolator;
                if (timeInterpolator == null) {
                    interpolation = INSTANCE.viscousFluid(f);
                } else {
                    Intrinsics.checkNotNull(timeInterpolator);
                    interpolation = timeInterpolator.getInterpolation(f);
                }
                this.currX = this.startX + Math.round(this.mDeltaX * interpolation);
                this.currY = this.startY + Math.round(interpolation * this.mDeltaY);
            } else if (i2 == 1) {
                float f2 = iCurrentAnimationTimeMillis / i;
                float f3 = 100;
                int i3 = (int) (f3 * f2);
                float f4 = END_TENSION;
                float f5 = 0.0f;
                if (i3 < 100) {
                    float f6 = i3 / f3;
                    int i4 = i3 + 1;
                    float fFloatValue = ((Float) Integer.valueOf(i4)).floatValue() / f3;
                    float[] fArr = SPLINE_POSITION;
                    float f7 = fArr[i3];
                    f5 = (fArr[i4] - f7) / (fFloatValue - f6);
                    f4 = f7 + ((f2 - f6) * f5);
                }
                this.mCurrVelocity = ((f5 * this.mDistance) / this.duration) * 1000.0f;
                int iRound = this.startX + Math.round((this.mFinalX - r0) * f4);
                this.currX = iRound;
                int iMin = Math.min(iRound, this.mMaxX);
                this.currX = iMin;
                this.currX = Math.max(iMin, this.mMinX);
                int iRound2 = this.startY + Math.round(f4 * (this.mFinalY - r0));
                this.currY = iRound2;
                int iMin2 = Math.min(iRound2, this.mMaxY);
                this.currY = iMin2;
                int iMax = Math.max(iMin2, this.mMinY);
                this.currY = iMax;
                if (this.currX == this.mFinalX && iMax == this.mFinalY) {
                    this.isFinished = true;
                }
            }
        } else {
            this.currX = this.mFinalX;
            this.currY = this.mFinalY;
            this.isFinished = true;
        }
        return true;
    }

    public static /* synthetic */ void startScroll$default(LauncherScroller launcherScroller, int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 16) != 0) {
            i5 = 250;
        }
        launcherScroller.startScroll(i, i2, i3, i4, i5);
    }

    public final void startScroll(int startX, int startY, int dx, int dy, int duration) {
        this.mMode = 0;
        this.isFinished = false;
        this.duration = duration;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.startX = startX;
        this.startY = startY;
        this.mFinalX = startX + dx;
        this.mFinalY = startY + dy;
        this.mDeltaX = dx;
        this.mDeltaY = dy;
        this.mDurationReciprocal = END_TENSION / this.duration;
    }

    public final void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY) {
        int i = velocityX;
        int i2 = velocityY;
        if (this.mFlywheel && !this.isFinished) {
            float currVelocity = getCurrVelocity();
            float f = this.mFinalX - this.startX;
            float f2 = this.mFinalY - this.startY;
            float fHypot = (float) Math.hypot(f, f2);
            float f3 = (f / fHypot) * currVelocity;
            float f4 = (f2 / fHypot) * currVelocity;
            if (Math.signum((float) i) == Math.signum(f3)) {
                if (Math.signum((float) i2) == Math.signum(f4)) {
                    i += (int) f3;
                    i2 += (int) f4;
                }
            }
        }
        this.mMode = 1;
        this.isFinished = false;
        float fHypot2 = (float) Math.hypot(i, i2);
        this.mVelocity = fHypot2;
        this.duration = getSplineFlingDuration(fHypot2);
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.startX = startX;
        this.startY = startY;
        boolean z = fHypot2 == 0.0f;
        float f5 = END_TENSION;
        float f6 = z ? 1.0f : i / fHypot2;
        if (!(fHypot2 == 0.0f)) {
            f5 = i2 / fHypot2;
        }
        double splineFlingDistance = getSplineFlingDistance(fHypot2);
        this.mDistance = (int) (Math.signum(fHypot2) * splineFlingDistance);
        this.mMinX = minX;
        this.mMaxX = maxX;
        this.mMinY = minY;
        this.mMaxY = maxY;
        int iRound = startX + ((int) Math.round(f6 * splineFlingDistance));
        this.mFinalX = iRound;
        int iMin = Math.min(iRound, this.mMaxX);
        this.mFinalX = iMin;
        this.mFinalX = Math.max(iMin, this.mMinX);
        int iRound2 = ((int) Math.round(splineFlingDistance * f5)) + startY;
        this.mFinalY = iRound2;
        int iMin2 = Math.min(iRound2, this.mMaxY);
        this.mFinalY = iMin2;
        this.mFinalY = Math.max(iMin2, this.mMinY);
    }

    private final double getSplineDeceleration(float velocity) {
        return Math.log((Math.abs(velocity) * INFLEXION) / (this.mFlingFriction * this.mPhysicalCoeff));
    }

    private final int getSplineFlingDuration(float velocity) {
        return (int) (Math.exp(getSplineDeceleration(velocity) / (DECELERATION_RATE - 1.0d)) * 1000.0d);
    }

    private final double getSplineFlingDistance(float velocity) {
        double splineDeceleration = getSplineDeceleration(velocity);
        float f = DECELERATION_RATE;
        return this.mFlingFriction * this.mPhysicalCoeff * Math.exp((f / (f - 1.0d)) * splineDeceleration);
    }

    public final void abortAnimation() {
        this.currX = this.mFinalX;
        this.currY = this.mFinalY;
        this.isFinished = true;
    }

    public final void extendDuration(int extend) {
        int iTimePassed = timePassed() + extend;
        this.duration = iTimePassed;
        this.mDurationReciprocal = END_TENSION / iTimePassed;
        this.isFinished = false;
    }

    public final int timePassed() {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
    }

    public final boolean isScrollingInDirection(float xvel, float yvel) {
        if (!this.isFinished) {
            if (Math.signum(xvel) == Math.signum((float) (this.mFinalX - this.startX))) {
                if (Math.signum(yvel) == Math.signum((float) (this.mFinalY - this.startY))) {
                    return true;
                }
            }
        }
        return false;
    }
}
