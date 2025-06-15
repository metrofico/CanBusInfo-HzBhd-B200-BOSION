package com.hzbhd.canbus.car_cus._277.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleDiagnosisPageUiSet;
import com.hzbhd.canbus.util.CommUtil;

import java.util.List;

/* loaded from: classes2.dex */
public class VehicleDiagnosisLeftLvAdapter extends RecyclerView.Adapter<VehicleDiagnosisLeftLvAdapter.ViewHolder> {
    private Context mContext;
    private LeftItemClickInterface mLeftItemClickInterface;
    private List<VehicleDiagnosisPageUiSet.ListBean> mList;

    public interface LeftItemClickInterface {
        void onLeftItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public VehicleDiagnosisLeftLvAdapter(Context context, List<VehicleDiagnosisPageUiSet.ListBean> list, LeftItemClickInterface leftItemClickInterface) {
        this.mLeftItemClickInterface = leftItemClickInterface;
        this.mList = list;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._277_layout_item_setting_cate, viewGroup, false));
    }

    @SuppressLint("RestrictedApi")
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        int strIdByResId = CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn());
        int strIdByResId2 = CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getValue());
        viewHolder.textView.setText(strIdByResId);
        viewHolder.textValue.setText(strIdByResId2);
        if (!"geely_e6_item_4".equals(this.mList.get(i).getValue())) {
            if ("geely_e6_item_0".equals(this.mList.get(i).getValue())) {
                viewHolder.textValue.setTextColor(-1);
            }
        } else {
            viewHolder.textValue.setTextColor(SupportMenu.CATEGORY_MASK);
        }
        if (this.mList.get(i).isIsSelected()) {
            viewHolder.textView.setBackground(null);
        } else {
            viewHolder.textView.setBackground(null);
        }
        viewHolder.textView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisLeftLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VehicleDiagnosisLeftLvAdapter.this.mLeftItemClickInterface.onLeftItemClick(i);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textValue;
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.tv_cate);
            this.textValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }
}
