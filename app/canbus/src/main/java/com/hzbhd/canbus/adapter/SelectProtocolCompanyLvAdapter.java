package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import java.util.List;

/* loaded from: classes.dex */
public class SelectProtocolCompanyLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ItemCallBackInterface mItemCallBackInterface;
    private List<String> mList;
    private String selectItem = "";

    public interface ItemCallBackInterface {
        void select(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SelectProtocolCompanyLvAdapter(Context context, List<String> list, ItemCallBackInterface itemCallBackInterface) {
        this.mList = list;
        this.mItemCallBackInterface = itemCallBackInterface;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_select_protocol_company, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (this.selectItem.equals(this.mList.get(i))) {
            viewHolder.llItem.setBackgroundResource(R.drawable.list_left_ic_sel);
            viewHolder.ivDot.setVisibility(0);
        } else {
            viewHolder.ivDot.setVisibility(4);
            if (i % 2 == 0) {
                viewHolder.llItem.setBackgroundResource(R.color.select_protocol_company_color_1);
            } else {
                viewHolder.llItem.setBackgroundResource(R.color.select_protocol_company_color_2);
            }
        }
        viewHolder.tv.setText(this.mList.get(i));
        viewHolder.llItem.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SelectProtocolCompanyLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SelectProtocolCompanyLvAdapter.this.mItemCallBackInterface != null) {
                    SelectProtocolCompanyLvAdapter.this.mItemCallBackInterface.select(i);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivDot;
        private LinearLayout llItem;
        private TextView tv;

        ViewHolder(View view) {
            super(view);
            this.tv = (TextView) view.findViewById(R.id.tv);
            this.ivDot = (ImageView) view.findViewById(R.id.iv_dot);
            this.llItem = (LinearLayout) view.findViewById(R.id.ll_item);
        }
    }

    public void setSelectItem(String str) {
        this.selectItem = str;
    }
}
