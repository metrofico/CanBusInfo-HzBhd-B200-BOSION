package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SwcActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwcLongKeyAdapter.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/hzbhd/canbus/adapter/SwcLongKeyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "onItemClick", "Lkotlin/Function1;", "", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "selectedKey", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSelectedKey", "key", "ViewHolder", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SwcLongKeyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final Function1<Integer, Unit> onItemClick;
    private int selectedKey;

    /* JADX WARN: Multi-variable type inference failed */
    public SwcLongKeyAdapter(Context context, Function1<? super Integer, Unit> onItemClick) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        this.context = context;
        this.onItemClick = onItemClick;
    }

    /* compiled from: SwcLongKeyAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/hzbhd/canbus/adapter/SwcLongKeyAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "ivKeyIcon", "Landroid/widget/ImageView;", "getIvKeyIcon", "()Landroid/widget/ImageView;", "tvKeyName", "Landroid/widget/TextView;", "getTvKeyName", "()Landroid/widget/TextView;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivKeyIcon;
        private final TextView tvKeyName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View viewFindViewById = view.findViewById(R.id.iv_key_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.iv_key_icon)");
            this.ivKeyIcon = (ImageView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(R.id.tv_key_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.tv_key_name)");
            this.tvKeyName = (TextView) viewFindViewById2;
        }

        public final ImageView getIvKeyIcon() {
            return this.ivKeyIcon;
        }

        public final TextView getTvKeyName() {
            return this.tvKeyName;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.layout_swc_long_click_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "from(context).inflate(R.…lick_item, parent, false)");
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewHolder viewHolder = (ViewHolder) holder;
        SwcActivity.KeyUiEntity keyUiEntity = SwcActivity.INSTANCE.getKeyUiList().get(position);
        viewHolder.getIvKeyIcon().setImageResource(keyUiEntity.getDrawableResId());
        viewHolder.getTvKeyName().setText(keyUiEntity.getStringResId());
        View view = holder.itemView;
        view.setPadding(8, 0, 4, 0);
        view.setSelected(this.selectedKey == keyUiEntity.getKey());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SwcLongKeyAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SwcLongKeyAdapter.m45onBindViewHolder$lambda2$lambda1$lambda0(this.f$0, position, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-2$lambda-1$lambda-0, reason: not valid java name */
    public static final void m45onBindViewHolder$lambda2$lambda1$lambda0(SwcLongKeyAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onItemClick.invoke(Integer.valueOf(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return SwcActivity.INSTANCE.getKeyUiList().size();
    }

    public final void setSelectedKey(int key) {
        this.selectedKey = key;
    }
}
