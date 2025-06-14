package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.WarningEntity;
import java.util.List;

/* loaded from: classes.dex */
public class WarningLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<WarningEntity> mList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WarningLvAdapter(Context context, List<WarningEntity> list) {
        this.mList = list;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_warning_lv, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvTitle.setText(this.mList.get(i).getContent());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.ll_layout);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
