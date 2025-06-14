package com.hzbhd.ui.life;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.ui.base.BhdViewUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecyclerListAdapterView.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001(B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001aH&J\b\u0010\u001c\u001a\u00020\u001aH&J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH&J\b\u0010 \u001a\u00020!H\u0016J\u0006\u0010\"\u001a\u00020\tJ\u001a\u0010#\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010$\u001a\u00020\u001aH&J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020'H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lcom/hzbhd/ui/life/RecyclerListAdapterView;", "Lcom/hzbhd/ui/life/BaseLifeFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isAdd", "", "itemInfo", "Lcom/hzbhd/ui/life/RecyclerListAdapterView$ItemInfo;", "getItemInfo", "()Lcom/hzbhd/ui/life/RecyclerListAdapterView$ItemInfo;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "afterViewInit", "", "beforeViewInit", "clearDataWhenHide", "getAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getLayoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "getScrollStyle", "initAttr", "initData", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "ItemInfo", "lifeview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class RecyclerListAdapterView extends BaseLifeFrameLayout {
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
        Intrinsics.checkNotNullParameter(context, "context");
        this.itemInfo = new ItemInfo();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerListAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.itemInfo = new ItemInfo();
        initAttr(context, attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerListAdapterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.itemInfo = new ItemInfo();
        initAttr(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerListAdapterView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.itemInfo = new ItemInfo();
        initAttr(context, attributeSet);
    }

    public final ItemInfo getItemInfo() {
        return this.itemInfo;
    }

    private final void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.recycleViewAttrs);
        getItemInfo().setDefItemLayout(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_default_item_layout, 0));
        getItemInfo().setDefItemWidth(typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.recycleViewAttrs_default_item_width, -2));
        getItemInfo().setDefItemHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.recycleViewAttrs_default_item_height, -2));
        getItemInfo().setFirstItemLayout(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_first_item_layout, 0));
        getItemInfo().setLastItemLayout(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_last_item_layout, 0));
        getItemInfo().setScrollbarStyle(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_scroll_style, 0));
        getItemInfo().setDefaultItemLayoutChild(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_default_item_layout_child, 0));
        getItemInfo().setRecyclerViewStyle(typedArrayObtainStyledAttributes.getResourceId(R.styleable.recycleViewAttrs_recyclerview_style, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override // com.hzbhd.ui.life.BaseLifeFrameLayout
    public void onLifeCycleChange(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onLifeCycleChange(state);
        if (state == Lifecycle.State.RESUMED) {
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView != null) {
                recyclerView.setVisibility(0);
            }
            if (!this.isAdd) {
                this.isAdd = true;
                BhdViewUtil.INSTANCE.runBack(new Function0<Unit>() { // from class: com.hzbhd.ui.life.RecyclerListAdapterView.onLifeCycleChange.1
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
                        Intrinsics.checkNotNull(recyclerView3);
                        recyclerView3.setLayoutManager(RecyclerListAdapterView.this.getLayoutManager());
                        RecyclerView recyclerView4 = RecyclerListAdapterView.this.getRecyclerView();
                        Intrinsics.checkNotNull(recyclerView4);
                        recyclerView4.setAdapter(RecyclerListAdapterView.this.getAdapter());
                        RecyclerView recyclerView5 = RecyclerListAdapterView.this.getRecyclerView();
                        Intrinsics.checkNotNull(recyclerView5);
                        recyclerView5.setScrollBarStyle(RecyclerListAdapterView.this.getScrollStyle());
                        RecyclerListAdapterView.this.initData();
                        BhdViewUtil bhdViewUtil = BhdViewUtil.INSTANCE;
                        final RecyclerListAdapterView recyclerListAdapterView2 = RecyclerListAdapterView.this;
                        bhdViewUtil.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.life.RecyclerListAdapterView.onLifeCycleChange.1.1
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
                                RecyclerListAdapterView recyclerListAdapterView3 = recyclerListAdapterView2;
                                recyclerListAdapterView3.addView(recyclerListAdapterView3.getRecyclerView());
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
            recyclerView2.setVisibility(8);
        }
        clearDataWhenHide();
    }

    public final int getScrollStyle() {
        return this.itemInfo.getScrollbarStyle();
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    /* compiled from: RecyclerListAdapterView.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/hzbhd/ui/life/RecyclerListAdapterView$ItemInfo;", "", "()V", "defItemHeight", "", "getDefItemHeight", "()I", "setDefItemHeight", "(I)V", "defItemLayout", "getDefItemLayout", "setDefItemLayout", "defItemWidth", "getDefItemWidth", "setDefItemWidth", "defaultItemLayoutChild", "getDefaultItemLayoutChild", "setDefaultItemLayoutChild", "firstItemLayout", "getFirstItemLayout", "setFirstItemLayout", "lastItemLayout", "getLastItemLayout", "setLastItemLayout", "recyclerViewStyle", "getRecyclerViewStyle", "setRecyclerViewStyle", "scrollbarStyle", "getScrollbarStyle", "setScrollbarStyle", "lifeview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
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
