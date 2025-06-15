package com.hzbhd.ui.view.paged;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


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
