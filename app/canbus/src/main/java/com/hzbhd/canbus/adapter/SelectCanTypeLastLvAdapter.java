package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import java.util.List;

/* loaded from: classes.dex */
public class SelectCanTypeLastLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ItemCallBackInterface mItemCallBackInterface;
    private List<CanTypeAllEntity> mList;
    private int mNormalTextColor;
    private int mSelectTextColor;

    public interface ItemCallBackInterface {
        void select(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SelectCanTypeLastLvAdapter(Context context, List<CanTypeAllEntity> list, ItemCallBackInterface itemCallBackInterface) {
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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_select_can_type_last, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (this.mList.get(i).isSelected()) {
            viewHolder.ll.setBackgroundResource(R.drawable.selct_can_type_list_right_sel);
            viewHolder.tv0.setTextColor(this.mSelectTextColor);
            viewHolder.tv1.setTextColor(this.mSelectTextColor);
        } else {
            viewHolder.ll.setBackground(null);
            viewHolder.tv0.setTextColor(this.mNormalTextColor);
            viewHolder.tv1.setTextColor(this.mNormalTextColor);
        }
        viewHolder.tv0.setText(this.mList.get(i).getCan_type_id() + "");
        viewHolder.tv1.setText(this.mList.get(i).getDescription());
        viewHolder.ll.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SelectCanTypeLastLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SelectCanTypeLastLvAdapter.this.mItemCallBackInterface != null) {
                    SelectCanTypeLastLvAdapter.this.mItemCallBackInterface.select(i);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private RelativeLayout ll;
        private TextView tv0;
        private TextView tv1;

        ViewHolder(View view) {
            super(view);
            this.ll = (RelativeLayout) view.findViewById(R.id.ll_layout);
            this.tv0 = (TextView) view.findViewById(R.id.tv_0);
            this.tv1 = (TextView) view.findViewById(R.id.tv_1);
            this.iv = (ImageView) view.findViewById(R.id.iv_right_icon);
        }
    }
}
