package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.activity.SwcActivity;
import com.hzbhd.canbus.view.SwcLongClickDialog;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwcLongKeyAdapter.kt */

public final class SwcLongKeyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final SwcLongClickDialog.OnKeyClickListener onItemClick;
    private int selectedKey;

    /* JADX WARN: Multi-variable type inference failed */
    public SwcLongKeyAdapter(Context context, SwcLongClickDialog.OnKeyClickListener onItemClick) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        this.context = context;
        this.onItemClick = onItemClick;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivKeyIcon;
        private final TextView tvKeyName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View viewFindViewById = view.findViewById(R.id.iv_key_icon);
            this.ivKeyIcon = (ImageView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(R.id.tv_key_name);
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
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewHolder viewHolder = (ViewHolder) holder;
        SwcActivity.KeyUiEntity keyUiEntity = SwcActivity.getKeyUiList().get(position);
        viewHolder.getIvKeyIcon().setImageResource(keyUiEntity.getDrawableResId());
        viewHolder.getTvKeyName().setText(keyUiEntity.getStringResId());
        View view = holder.itemView;
        view.setPadding(8, 0, 4, 0);
        view.setSelected(this.selectedKey == keyUiEntity.getKey());
        view.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SwcLongKeyAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SwcLongKeyAdapter.m45onBindViewHolder$lambda2$lambda1$lambda0(SwcLongKeyAdapter.this, position, view2);
            }
        });
    }

    public static void m45onBindViewHolder$lambda2$lambda1$lambda0(SwcLongKeyAdapter this$0, int i, View view) {
        this$0.onItemClick.onKeyClick(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return SwcActivity.getKeyUiList().size();
    }

    public final void setSelectedKey(int key) {
        this.selectedKey = key;
    }
}
