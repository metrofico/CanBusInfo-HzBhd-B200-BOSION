package com.hzbhd.ui.view.paged;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CellLayout.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\u001e\u0010%\u001a\u00020)2\u0006\u0010'\u001a\u00020(2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005J&\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005R\u001a\u0010\u000f\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\u001a\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001a\u0010\u001d\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013R\u001a\u0010 \u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0011\"\u0004\b\"\u0010\u0013R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0011¨\u00061"}, d2 = {"Lcom/hzbhd/ui/view/paged/CellLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "page", "", "(Landroid/content/Context;I)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mCellHeight", "getMCellHeight", "()I", "setMCellHeight", "(I)V", "mCellWidth", "getMCellWidth", "setMCellWidth", "mMaxHeight", "getMMaxHeight", "setMMaxHeight", "mMaxWidth", "getMMaxWidth", "setMMaxWidth", "mXSize", "getMXSize", "setMXSize", "mYSize", "getMYSize", "setMYSize", "<set-?>", "getPage", "addChild", "", "child", "Lcom/hzbhd/ui/view/paged/CellChild;", "", "x", "y", "setGridSize", "xSize", "ySize", "maxWidth", "maxHeight", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public class CellLayout extends FrameLayout {
    private int mCellHeight;
    private int mCellWidth;
    private int mMaxHeight;
    private int mMaxWidth;
    private int mXSize;
    private int mYSize;
    private int page;

    public final int getPage() {
        return this.page;
    }

    protected final int getMXSize() {
        return this.mXSize;
    }

    protected final void setMXSize(int i) {
        this.mXSize = i;
    }

    protected final int getMYSize() {
        return this.mYSize;
    }

    protected final void setMYSize(int i) {
        this.mYSize = i;
    }

    protected final int getMMaxWidth() {
        return this.mMaxWidth;
    }

    protected final void setMMaxWidth(int i) {
        this.mMaxWidth = i;
    }

    protected final int getMMaxHeight() {
        return this.mMaxHeight;
    }

    protected final void setMMaxHeight(int i) {
        this.mMaxHeight = i;
    }

    protected final int getMCellWidth() {
        return this.mCellWidth;
    }

    protected final void setMCellWidth(int i) {
        this.mCellWidth = i;
    }

    protected final int getMCellHeight() {
        return this.mCellHeight;
    }

    protected final void setMCellHeight(int i) {
        this.mCellHeight = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellLayout(Context context, int i) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mXSize = 5;
        this.mYSize = 2;
        this.mMaxWidth = 1920;
        this.mMaxHeight = 1080;
        this.mCellWidth = 1920 / 5;
        this.mCellHeight = 1080 / 2;
        this.page = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mXSize = 5;
        this.mYSize = 2;
        this.mMaxWidth = 1920;
        this.mMaxHeight = 1080;
        this.mCellWidth = 1920 / 5;
        this.mCellHeight = 1080 / 2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mXSize = 5;
        this.mYSize = 2;
        this.mMaxWidth = 1920;
        this.mMaxHeight = 1080;
        this.mCellWidth = 1920 / 5;
        this.mCellHeight = 1080 / 2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mXSize = 5;
        this.mYSize = 2;
        this.mMaxWidth = 1920;
        this.mMaxHeight = 1080;
        this.mCellWidth = 1920 / 5;
        this.mCellHeight = 1080 / 2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CellLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mXSize = 5;
        this.mYSize = 2;
        this.mMaxWidth = 1920;
        this.mMaxHeight = 1080;
        this.mCellWidth = 1920 / 5;
        this.mCellHeight = 1080 / 2;
    }

    public final void setGridSize(int xSize, int ySize, int maxWidth, int maxHeight) {
        this.mXSize = xSize;
        this.mYSize = ySize;
        this.mMaxWidth = maxWidth;
        this.mMaxHeight = maxHeight;
        this.mCellWidth = maxWidth / xSize;
        this.mCellHeight = maxHeight / ySize;
    }

    public final void addChild(CellChild child, int x, int y) {
        Intrinsics.checkNotNullParameter(child, "child");
        child.setLoc(x, y);
        addChild(child);
    }

    public final boolean addChild(CellChild child) {
        Intrinsics.checkNotNullParameter(child, "child");
        if (child.getPageX() + child.getXsize() > this.mXSize || child.getPageY() + child.getYsize() > this.mYSize) {
            if (!LogUtil.log5()) {
                return false;
            }
            LogUtil.d("addChild: " + child.getPageX() + ',' + child.getXsize() + ',' + this.mXSize + "::" + child.getPageY() + ',' + child.getYsize() + ',' + this.mYSize + " == return false");
            return false;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mCellWidth * child.getXsize(), this.mCellHeight * child.getYsize());
        layoutParams.leftMargin = this.mCellWidth * child.getPageX();
        layoutParams.topMargin = this.mCellHeight * child.getPageY();
        addView(child, layoutParams);
        return true;
    }
}
