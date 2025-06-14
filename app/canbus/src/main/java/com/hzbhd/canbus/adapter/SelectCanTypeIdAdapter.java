package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import java.util.List;

/* loaded from: classes.dex */
public class SelectCanTypeIdAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int mCurSelectedId;
    private ItemCallBackInterface mItemCallBackInterface;
    private List<Integer> mList;
    private int mNormalTextColor;
    private int mSelectTextColor;

    public interface ItemCallBackInterface {
        void select(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SelectCanTypeIdAdapter(Context context, List<Integer> list, ItemCallBackInterface itemCallBackInterface, int i) {
        this.mList = list;
        this.mItemCallBackInterface = itemCallBackInterface;
        this.mCurSelectedId = i;
        this.mSelectTextColor = context.getColor(R.color.black);
        this.mNormalTextColor = context.getColor(R.color.white);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_select_can_type_id, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (this.mList.get(i).intValue() == this.mCurSelectedId) {
            viewHolder.tv.setBackgroundResource(R.drawable.selct_can_type_list_right_sel);
            viewHolder.tv.setTextColor(this.mSelectTextColor);
        } else {
            viewHolder.tv.setBackground(null);
            viewHolder.tv.setTextColor(this.mNormalTextColor);
        }
        viewHolder.tv.setText(String.valueOf(this.mList.get(i)));
        viewHolder.tv.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SelectCanTypeIdAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SelectCanTypeIdAdapter.this.mItemCallBackInterface != null) {
                    SelectCanTypeIdAdapter.this.mItemCallBackInterface.select(((Integer) SelectCanTypeIdAdapter.this.mList.get(i)).intValue());
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        ViewHolder(View view) {
            super(view);
            this.tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
