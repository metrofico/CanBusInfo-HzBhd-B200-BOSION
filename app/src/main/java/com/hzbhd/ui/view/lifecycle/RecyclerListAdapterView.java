package com.hzbhd.ui.view.lifecycle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.ui.view.R;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public abstract class RecyclerListAdapterView extends BaseLifeRelativeLayout {
    private boolean isAdd;
    private final ItemInfo itemInfo;
    private RecyclerView recyclerView;

    public abstract void afterViewInit();

    public abstract void beforeViewInit();

    public abstract void clearDataWhenHide();

    public abstract RecyclerView.Adapter<RecyclerView.ViewHolder> getAdapter();

    public abstract void initData();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerListAdapterView(Context context) {
        super(context);

        this.itemInfo = new ItemInfo();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerListAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);


        this.itemInfo = new ItemInfo();
        initAttr(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerListAdapterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        this.itemInfo = new ItemInfo();
        initAttr(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerListAdapterView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

        this.itemInfo = new ItemInfo();
        initAttr(context, attributeSet);
    }

    public final ItemInfo getItemInfo() {
        return this.itemInfo;
    }

    private final void initAttr(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.recycleViewAttrs);
            this.itemInfo.setDefItemLayout(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_default_item_layout, 0));
            this.itemInfo.setDefItemWidth(typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.recycleViewAttrs_default_item_width, -2));
            this.itemInfo.setDefItemHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.recycleViewAttrs_default_item_height, -2));
            this.itemInfo.setFirstItemLayout(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_first_item_layout, 0));
            this.itemInfo.setLastItemLayout(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_last_item_layout, 0));
            this.itemInfo.setScrollbarStyle(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_scroll_style, 0));
            this.itemInfo.setDefaultItemLayoutChild(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_default_item_layout_child, 0));
            this.itemInfo.setRecyclerViewStyle(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_recyclerview_style, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override // com.hzbhd.ui.view.lifecycle.BaseLifeRelativeLayout
    public void onLifeCycleChange(Lifecycle.State state) {

        super.onLifeCycleChange(state);
        if (state == Lifecycle.State.RESUMED) {
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView != null) {
                recyclerView.setVisibility(View.VISIBLE);
            }
            if (!this.isAdd) {
                this.isAdd = true;
                BaseUtil.INSTANCE.runBack(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView2;
                        RecyclerListAdapterView.this.beforeViewInit();
                        RecyclerListAdapterView recyclerListAdapterView = RecyclerListAdapterView.this;
                        if (recyclerListAdapterView.getItemInfo().getRecyclerViewStyle() == 0) {
                            recyclerView2 = new RecyclerView(RecyclerListAdapterView.this.getContext());
                        } else {
                            recyclerView2 = (RecyclerView) LayoutInflater.from(RecyclerListAdapterView.this.getContext()).inflate(RecyclerListAdapterView.this.getItemInfo().getRecyclerViewStyle(), (ViewGroup) null);
                        }
                        recyclerListAdapterView.setRecyclerView(recyclerView2);
                        RecyclerView recyclerView3 = RecyclerListAdapterView.this.getRecyclerView();

                        recyclerView3.setLayoutManager(RecyclerListAdapterView.this.getLayoutManager());
                        RecyclerView recyclerView4 = RecyclerListAdapterView.this.getRecyclerView();

                        recyclerView4.setAdapter(RecyclerListAdapterView.this.getAdapter());
                        RecyclerView recyclerView5 = RecyclerListAdapterView.this.getRecyclerView();

                        recyclerView5.setScrollBarStyle(RecyclerListAdapterView.this.getScrollStyle());
                        RecyclerListAdapterView.this.initData();
                        BaseUtil baseUtil = BaseUtil.INSTANCE;
                        final RecyclerListAdapterView recyclerListAdapterView2 = RecyclerListAdapterView.this;
                        baseUtil.runUi(new Runnable() {
                            @Override
                            public void run() {
                                recyclerListAdapterView2.addView(recyclerListAdapterView2.getRecyclerView());
                                recyclerListAdapterView2.afterViewInit();
                            }
                        });
                    }
                });
                return;
            } else {
                initData();
                return;
            }
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setVisibility(View.GONE);
        }
        clearDataWhenHide();
    }

    public final int getScrollStyle() {
        return this.itemInfo.getScrollbarStyle();
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public static final class ItemInfo {
        private int defItemLayout;
        private int defaultItemLayoutChild;
        private int firstItemLayout;
        private int lastItemLayout;
        private int recyclerViewStyle;
        private int scrollbarStyle;
        private int defItemWidth = -2;
        private int defItemHeight = -2;

        public final int getDefItemWidth() {
            return this.defItemWidth;
        }

        public final void setDefItemWidth(int i) {
            this.defItemWidth = i;
        }

        public final int getDefItemHeight() {
            return this.defItemHeight;
        }

        public final void setDefItemHeight(int i) {
            this.defItemHeight = i;
        }

        public final int getDefItemLayout() {
            return this.defItemLayout;
        }

        public final void setDefItemLayout(int i) {
            this.defItemLayout = i;
        }

        public final int getFirstItemLayout() {
            return this.firstItemLayout;
        }

        public final void setFirstItemLayout(int i) {
            this.firstItemLayout = i;
        }

        public final int getLastItemLayout() {
            return this.lastItemLayout;
        }

        public final void setLastItemLayout(int i) {
            this.lastItemLayout = i;
        }

        public final int getScrollbarStyle() {
            return this.scrollbarStyle;
        }

        public final void setScrollbarStyle(int i) {
            this.scrollbarStyle = i;
        }

        public final int getDefaultItemLayoutChild() {
            return this.defaultItemLayoutChild;
        }

        public final void setDefaultItemLayoutChild(int i) {
            this.defaultItemLayoutChild = i;
        }

        public final int getRecyclerViewStyle() {
            return this.recyclerViewStyle;
        }

        public final void setRecyclerViewStyle(int i) {
            this.recyclerViewStyle = i;
        }
    }
}
