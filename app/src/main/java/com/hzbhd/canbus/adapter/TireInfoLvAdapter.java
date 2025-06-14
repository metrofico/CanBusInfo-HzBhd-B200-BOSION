package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

/* loaded from: classes.dex */
public class TireInfoLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<TirePageUiSet.LineItem> mList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public TireInfoLvAdapter(Context context, List<TirePageUiSet.LineItem> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_tire_info, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitle()));
        viewHolder.tvValue.setText(this.mList.get(i).getValue());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvValue;

        ViewHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }
}
