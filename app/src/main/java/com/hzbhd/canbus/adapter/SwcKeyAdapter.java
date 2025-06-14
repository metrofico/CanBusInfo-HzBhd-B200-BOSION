package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.activity.SwcActivity;
import com.hzbhd.canbus.config.CustomKeyConfig;
import com.hzbhd.constant.share.lcd.LcdInfoShare;

import java.util.Iterator;
import java.util.List;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class SwcKeyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<CustomKeyConfig.KeyMap> list;
    private final OnClickListener onItemClick;
    private final OnClickListener onItemLongClick;
    private int selectedIndex;

    public interface OnClickListener {

        void onClick(int position);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SwcKeyAdapter(Context context, List<CustomKeyConfig.KeyMap> list, OnClickListener onItemClick, OnClickListener onItemLongClick) {
        this.context = context;
        this.list = list;
        this.onItemClick = onItemClick;
        this.onItemLongClick = onItemLongClick;
    }

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
            View viewFindViewById = view.findViewById(R.id.iv_short_icon);
            this.ivShortIcon = (ImageView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(R.id.iv_divider);
            this.ivDivider = (ImageView) viewFindViewById2;
            View viewFindViewById3 = view.findViewById(R.id.iv_long_icon);
            this.ivLongIcon = (ImageView) viewFindViewById3;
            View viewFindViewById4 = view.findViewById(R.id.tv_short_name);
            this.tvShortName = (TextView) viewFindViewById4;
            View viewFindViewById5 = view.findViewById(R.id.tv_long_name);
            this.tvLongName = (TextView) viewFindViewById5;
            View viewFindViewById6 = view.findViewById(R.id.iv_indicator);
            this.ivIndicator = (ImageView) viewFindViewById6;
        }

        public ImageView getIvShortIcon() {
            return this.ivShortIcon;
        }

        public ImageView getIvDivider() {
            return this.ivDivider;
        }

        public ImageView getIvLongIcon() {
            return this.ivLongIcon;
        }

        public TextView getTvShortName() {
            return this.tvShortName;
        }

        public TextView getTvLongName() {
            return this.tvLongName;
        }

        public ImageView getIvIndicator() {
            return this.ivIndicator;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.layout_swc_key_item, parent, false);
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        CustomKeyConfig.KeyMap keyMap = this.list.get(position);
        setKeyUi(keyMap.getOutput().getShort(), viewHolder.getIvShortIcon(), viewHolder.getTvShortName());
        if (keyMap.getOutput().getLong() == 0) {
            viewHolder.getIvDivider().setVisibility(View.GONE);
            viewHolder.getIvLongIcon().setVisibility(View.GONE);
            viewHolder.getTvLongName().setVisibility(android.view.View.GONE);
        } else {
            setKeyUi(keyMap.getOutput().getLong(), viewHolder.getIvLongIcon(), viewHolder.getTvLongName());
            viewHolder.getIvDivider().setVisibility(android.view.View.VISIBLE);
            viewHolder.getIvLongIcon().setVisibility(View.VISIBLE);
            viewHolder.getTvLongName().setVisibility(android.view.View.VISIBLE);
        }
        viewHolder.getIvIndicator().setVisibility(keyMap.getInput() == 0 ? View.GONE : View.VISIBLE);
        View view = holder.itemView;
        view.setPadding(8, 8, 8, 10);
        view.setSelected(this.selectedIndex == position);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SwcKeyAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SwcKeyAdapter.this.onItemClick.onClick(position);
                SwcKeyAdapter.this.setSelected(position);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.adapter.SwcKeyAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                SwcKeyAdapter.this.onItemLongClick.onClick(position);
                return true;
            }
        });
    }


    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return SwcActivity.INSTANCE.getKeyUiList().size();
    }

    private void setKeyUi(int key, ImageView icon, TextView name) {
        SwcActivity.KeyUiEntity obj;
        Iterator<SwcActivity.KeyUiEntity> it = SwcActivity.INSTANCE.getKeyUiList().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (obj.getKey() == key) {
                    break;
                }
            }
        }
        SwcActivity.KeyUiEntity keyUiEntity = obj;
        if (keyUiEntity != null) {
            icon.setImageResource(keyUiEntity.getDrawableResId());
            name.setText(keyUiEntity.getStringResId());
        }
    }

    public void setSelected(int index) {
        this.selectedIndex = index;
        notifyDataSetChanged();
    }
}
