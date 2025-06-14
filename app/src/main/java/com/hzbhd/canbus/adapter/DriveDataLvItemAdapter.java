package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;

/* loaded from: classes.dex */
public class DriveDataLvItemAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<DriverDataPageUiSet.Page.Item> mList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DriveDataLvItemAdapter(Context context, List<DriverDataPageUiSet.Page.Item> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_drive_data_item_tv, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mList.get(i).isTitleStr()) {
            viewHolder.tvTitle.setText(this.mList.get(i).getTitleSrn());
        } else {
            viewHolder.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn()));
        }
        String value = this.mList.get(i).getValue();
        if (TextUtils.isEmpty(value)) {
            viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getDefaultValueSrn()));
        } else {
            viewHolder.tvValue.setText(value);
        }
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
