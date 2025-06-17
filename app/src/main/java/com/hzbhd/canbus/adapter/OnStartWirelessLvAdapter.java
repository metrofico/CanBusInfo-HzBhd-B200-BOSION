package com.hzbhd.canbus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.OnStartWirelessListEntity;

import java.util.List;

/* loaded from: classes.dex */
public class OnStartWirelessLvAdapter extends RecyclerView.Adapter<OnStartWirelessLvAdapter.ViewHolder> {
    private final List<OnStartWirelessListEntity> mList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public OnStartWirelessLvAdapter(List<OnStartWirelessListEntity> list) {
        this.mList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_on_start, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvTitle.setText(this.mList.get(i).getTitle());
        viewHolder.tvValue.setText(this.mList.get(i).getValue());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvValue;

        ViewHolder(View view) {
            super(view);
            this.tvTitle = view.findViewById(R.id.tv_title);
            this.tvValue = view.findViewById(R.id.tv_value);
        }
    }
}
