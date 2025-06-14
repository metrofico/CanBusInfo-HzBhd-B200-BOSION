package com.hzbhd.canbus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;

import java.util.List;

/* loaded from: classes.dex */
public class HybirdValueLvAdapter extends RecyclerView.Adapter<HybirdValueLvAdapter.ViewHolder> {
    private final List<String> mList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public HybirdValueLvAdapter(List<String> list) {
        this.mList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_hybird, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(this.mList.get(i));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.tv_info);
        }
    }
}
