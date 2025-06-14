package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.SwcActivity;
import com.hzbhd.canbus.config.CustomKeyConfig;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwcKeyAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fBC\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\u0010\rJ\b\u0010\u000f\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016J \u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/hzbhd/canbus/adapter/SwcKeyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "onItemClick", "Lkotlin/Function1;", "", "", "onItemLongClick", "(Landroid/content/Context;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "selectedIndex", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setKeyUi", "key", "icon", "Landroid/widget/ImageView;", LcdInfoShare.MediaInfo.name, "Landroid/widget/TextView;", "setSelected", "index", "ViewHolder", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SwcKeyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<CustomKeyConfig.KeyMap> list;
    private final Function1<Integer, Unit> onItemClick;
    private final Function1<Integer, Unit> onItemLongClick;
    private int selectedIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public SwcKeyAdapter(Context context, List<CustomKeyConfig.KeyMap> list, Function1<? super Integer, Unit> onItemClick, Function1<? super Integer, Unit> onItemLongClick) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        Intrinsics.checkNotNullParameter(onItemLongClick, "onItemLongClick");
        this.context = context;
        this.list = list;
        this.onItemClick = onItemClick;
        this.onItemLongClick = onItemLongClick;
    }

    /* compiled from: SwcKeyAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/hzbhd/canbus/adapter/SwcKeyAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "ivDivider", "Landroid/widget/ImageView;", "getIvDivider", "()Landroid/widget/ImageView;", "ivIndicator", "getIvIndicator", "ivLongIcon", "getIvLongIcon", "ivShortIcon", "getIvShortIcon", "tvLongName", "Landroid/widget/TextView;", "getTvLongName", "()Landroid/widget/TextView;", "tvShortName", "getTvShortName", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivDivider;
        private final ImageView ivIndicator;
        private final ImageView ivLongIcon;
        private final ImageView ivShortIcon;
        private final TextView tvLongName;
        private final TextView tvShortName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View viewFindViewById = view.findViewById(R.id.iv_short_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.iv_short_icon)");
            this.ivShortIcon = (ImageView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(R.id.iv_divider);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.iv_divider)");
            this.ivDivider = (ImageView) viewFindViewById2;
            View viewFindViewById3 = view.findViewById(R.id.iv_long_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.iv_long_icon)");
            this.ivLongIcon = (ImageView) viewFindViewById3;
            View viewFindViewById4 = view.findViewById(R.id.tv_short_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.tv_short_name)");
            this.tvShortName = (TextView) viewFindViewById4;
            View viewFindViewById5 = view.findViewById(R.id.tv_long_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "view.findViewById(R.id.tv_long_name)");
            this.tvLongName = (TextView) viewFindViewById5;
            View viewFindViewById6 = view.findViewById(R.id.iv_indicator);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "view.findViewById(R.id.iv_indicator)");
            this.ivIndicator = (ImageView) viewFindViewById6;
        }

        public final ImageView getIvShortIcon() {
            return this.ivShortIcon;
        }

        public final ImageView getIvDivider() {
            return this.ivDivider;
        }

        public final ImageView getIvLongIcon() {
            return this.ivLongIcon;
        }

        public final TextView getTvShortName() {
            return this.tvShortName;
        }

        public final TextView getTvLongName() {
            return this.tvLongName;
        }

        public final ImageView getIvIndicator() {
            return this.ivIndicator;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.layout_swc_key_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "from(context).inflate(R.…_key_item, parent, false)");
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewHolder viewHolder = (ViewHolder) holder;
        CustomKeyConfig.KeyMap keyMap = this.list.get(position);
        setKeyUi(keyMap.getOutput().getShort(), viewHolder.getIvShortIcon(), viewHolder.getTvShortName());
        if (keyMap.getOutput().getLong() == 0) {
            viewHolder.getIvDivider().setVisibility(8);
            viewHolder.getIvLongIcon().setVisibility(8);
            viewHolder.getTvLongName().setVisibility(8);
        } else {
            setKeyUi(keyMap.getOutput().getLong(), viewHolder.getIvLongIcon(), viewHolder.getTvLongName());
            viewHolder.getIvDivider().setVisibility(0);
            viewHolder.getIvLongIcon().setVisibility(0);
            viewHolder.getTvLongName().setVisibility(0);
        }
        viewHolder.getIvIndicator().setVisibility(keyMap.getInput() == 0 ? 8 : 0);
        View view = holder.itemView;
        view.setPadding(8, 8, 8, 10);
        view.setSelected(this.selectedIndex == position);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SwcKeyAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SwcKeyAdapter.m43onBindViewHolder$lambda3$lambda2$lambda0(this.f$0, position, view2);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.adapter.SwcKeyAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return SwcKeyAdapter.m44onBindViewHolder$lambda3$lambda2$lambda1(this.f$0, position, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-3$lambda-2$lambda-0, reason: not valid java name */
    public static final void m43onBindViewHolder$lambda3$lambda2$lambda0(SwcKeyAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onItemClick.invoke(Integer.valueOf(i));
        this$0.setSelected(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-3$lambda-2$lambda-1, reason: not valid java name */
    public static final boolean m44onBindViewHolder$lambda3$lambda2$lambda1(SwcKeyAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onItemLongClick.invoke(Integer.valueOf(i));
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return SwcActivity.INSTANCE.getKeyUiList().size();
    }

    private final void setKeyUi(int key, ImageView icon, TextView name) {
        Object next;
        Iterator<T> it = SwcActivity.INSTANCE.getKeyUiList().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((SwcActivity.KeyUiEntity) next).getKey() == key) {
                    break;
                }
            }
        }
        SwcActivity.KeyUiEntity keyUiEntity = (SwcActivity.KeyUiEntity) next;
        if (keyUiEntity != null) {
            icon.setImageResource(keyUiEntity.getDrawableResId());
            name.setText(keyUiEntity.getStringResId());
        }
    }

    public final void setSelected(int index) {
        this.selectedIndex = index;
        notifyDataSetChanged();
    }
}
