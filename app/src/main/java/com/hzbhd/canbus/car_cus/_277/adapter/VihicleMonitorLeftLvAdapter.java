package com.hzbhd.canbus.car_cus._277.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleMonitorPageUiSet;
import com.hzbhd.canbus.util.CommUtil;

import java.util.List;

/* loaded from: classes2.dex */
public class VihicleMonitorLeftLvAdapter extends RecyclerView.Adapter<VihicleMonitorLeftLvAdapter.ViewHolder> {
    private Context mContext;
    private LeftItemClickInterface mLeftItemClickInterface;
    private List<VehicleMonitorPageUiSet.ListBean> mList;

    public interface LeftItemClickInterface {
        void onLeftItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public VihicleMonitorLeftLvAdapter(Context context, List<VehicleMonitorPageUiSet.ListBean> list, LeftItemClickInterface leftItemClickInterface) {
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
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._277_layout_item_setting_cate_2, viewGroup, false);
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams(displayMetrics.widthPixels / 3, -2));
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.textView.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn()));
        if (this.mList.get(i).isIsSelected()) {
            viewHolder.textView.setBackgroundResource(R.drawable.c_268_car_set_icon_btn_n);
        } else {
            viewHolder.textView.setBackgroundResource(R.drawable.c_268_car_set_icon_btn_p);
        }
        viewHolder.textView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._277.adapter.VihicleMonitorLeftLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VihicleMonitorLeftLvAdapter.this.mLeftItemClickInterface.onLeftItemClick(i);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.tv_cate);
        }
    }
}
