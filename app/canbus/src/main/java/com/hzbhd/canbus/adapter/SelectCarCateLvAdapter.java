package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import java.util.List;

/* loaded from: classes.dex */
public class SelectCarCateLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ItemCallBackInterface mItemCallBackInterface;
    private List<String> mList;
    private int mNormalTextColor;
    private int mSelectTextColor;
    private String selectItem = "";

    public interface ItemCallBackInterface {
        void select(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SelectCarCateLvAdapter(Context context, List<String> list, ItemCallBackInterface itemCallBackInterface) {
        this.mList = list;
        this.mItemCallBackInterface = itemCallBackInterface;
        this.mSelectTextColor = context.getColor(R.color.white);
        this.mNormalTextColor = context.getColor(R.color.select_car_cate_tv_color);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_select_car_cate, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (this.selectItem.equals(this.mList.get(i))) {
            viewHolder.tv.setBackgroundResource(R.color.colorAccent_dark);
            viewHolder.tv.setTextColor(this.mSelectTextColor);
        } else {
            viewHolder.tv.setBackgroundResource(R.color.select_protocol_company_color_2);
            viewHolder.tv.setTextColor(this.mNormalTextColor);
        }
        viewHolder.tv.setText(this.mList.get(i));
        viewHolder.lLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SelectCarCateLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SelectCarCateLvAdapter.this.mItemCallBackInterface != null) {
                    SelectCarCateLvAdapter.this.mItemCallBackInterface.select(i);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout lLayout;
        private TextView tv;

        ViewHolder(View view) {
            super(view);
            this.tv = (TextView) view.findViewById(R.id.tv);
            this.lLayout = (LinearLayout) view.findViewById(R.id.ll_car_cate);
        }
    }

    public void setSelectItem(String str) {
        this.selectItem = str;
    }
}
