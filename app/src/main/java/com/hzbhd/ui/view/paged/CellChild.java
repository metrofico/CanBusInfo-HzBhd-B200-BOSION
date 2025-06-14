package com.hzbhd.ui.view.paged;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.ui.view.lifecycle.BaseLifeFrameLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CellChild.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 :2\u00020\u0001:\u0001:B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ&\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tJ\b\u0010-\u001a\u00020.H\u0014J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020.H\u0014J\u000e\u00103\u001a\u00020.2\u0006\u00100\u001a\u000201J\u0016\u00104\u001a\u00020.2\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020\tJ\u0010\u00105\u001a\u00020.2\u0006\u00106\u001a\u00020\u0011H\u0016J\u0016\u00107\u001a\u00020.2\u0006\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR$\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0018\"\u0004\b!\u0010\u001aR$\u0010\"\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0018\"\u0004\b$\u0010\u001aR$\u0010%\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010\u001a¨\u0006;"}, d2 = {"Lcom/hzbhd/ui/view/paged/CellChild;", "Lcom/hzbhd/ui/view/lifecycle/BaseLifeFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "downX", "", "downY", "event_move", "", "isLongClick", "isThisTouch", "longClickRunnable", "Ljava/lang/Runnable;", "page", "getPage", "()I", "setPage", "(I)V", "<set-?>", "pageX", "getPageX", "setPageX", "pageY", "getPageY", "setPageY", "xsize", "getXsize", "setXsize", "ysize", "getYsize", "setYsize", "isCover", "x", "y", "xSize", "ySize", "onClick", "", "onInterceptTouchEvent", "ev", "Landroid/view/MotionEvent;", "onLongClick", "onTouch", "setLoc", "setPressed", "pressed", "setSize", "x_size", "y_size", "Companion", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CellChild extends BaseLifeFrameLayout {
    private static final String TAG = "CellChild";
    private float downX;
    private float downY;
    private boolean event_move;
    private boolean isLongClick;
    private boolean isThisTouch;
    private final Runnable longClickRunnable;
    private int page;
    private int pageX;
    private int pageY;
    private int xsize;
    private int ysize;

    protected void onClick() {
    }

    protected void onLongClick() {
    }

    public final int getPageX() {
        return this.pageX;
    }

    protected final void setPageX(int i) {
        this.pageX = i;
    }

    public final int getPageY() {
        return this.pageY;
    }

    protected final void setPageY(int i) {
        this.pageY = i;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final int getXsize() {
        return this.xsize;
    }

    protected final void setXsize(int i) {
        this.xsize = i;
    }

    public final int getYsize() {
        return this.ysize;
    }

    protected final void setYsize(int i) {
        this.ysize = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CellChild.m1208longClickRunnable$lambda0(this.f$0);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CellChild.m1208longClickRunnable$lambda0(this.f$0);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CellChild.m1208longClickRunnable$lambda0(this.f$0);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellChild(Context context, AttributeSet attrs, int i, int i2) {
        super(context, attrs, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.xsize = 1;
        this.ysize = 1;
        this.longClickRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.CellChild$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CellChild.m1208longClickRunnable$lambda0(this.f$0);
            }
        };
    }

    public final void setLoc(int x, int y) {
        this.pageX = x;
        this.pageY = y;
    }

    public final void setSize(int x_size, int y_size) {
        this.xsize = x_size;
        this.ysize = y_size;
    }

    public final boolean isCover(int x, int y, int xSize, int ySize) {
        int i = this.pageX;
        if (this.xsize + i < x + 1 || x + xSize < i + 1) {
            return false;
        }
        int i2 = this.pageY;
        return this.ysize + i2 >= y + 1 && y + ySize >= i2 + 1;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        this.isThisTouch = true;
        this.isLongClick = false;
        setPressed(true);
        postDelayed(this.longClickRunnable, 500L);
        return super.onInterceptTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: longClickRunnable$lambda-0, reason: not valid java name */
    public static final void m1208longClickRunnable$lambda0(CellChild this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isLongClick = true;
        this$0.onLongClick();
    }

    @Override // android.view.View
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        Log.d(TAG, "setPressed: " + pressed);
    }

    public final void onTouch(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        if (ev.getAction() == 0) {
            this.isThisTouch = false;
            this.event_move = false;
            this.downX = ev.getRawX();
            this.downY = ev.getRawY();
            return;
        }
        if (this.isThisTouch) {
            if (ev.getAction() != 1 && ev.getAction() != 3) {
                if (ev.getRawX() == this.downX) {
                    return;
                }
                if (ev.getRawY() == this.downY) {
                    return;
                }
                setPressed(false);
                this.event_move = true;
                removeCallbacks(this.longClickRunnable);
                return;
            }
            setPressed(false);
            removeCallbacks(this.longClickRunnable);
            if (this.event_move || !this.isThisTouch) {
                return;
            }
            if (!this.isLongClick) {
                onClick();
            }
            this.isThisTouch = false;
        }
    }
}
