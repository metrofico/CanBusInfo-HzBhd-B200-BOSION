package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import java.util.List;

/* loaded from: classes2.dex */
public class SettingDialogAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<SettingDialogBean> lists;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int i);
    }

    public SettingDialogAdapter(Context context, List<SettingDialogBean> list) {
        this.mContext = context;
        this.lists = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout._283_adapter_setting_dialog, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1091xfb63f8bb(i, view);
            }
        });
        viewHolder.tv_select.setVisibility(this.lists.get(i).getSelectVisible());
        viewHolder.tv_value.setText(this.lists.get(i).getValue() + this.lists.get(i).getUnit());
        viewHolder.tv_value.setTextColor(this.mContext.getColor(this.lists.get(i).getTextColor()));
        if (getItemCount() - 1 == i) {
            viewHolder.lineView.setVisibility(8);
        }
    }

    /* renamed from: lambda$onBindViewHolder$0$com-hzbhd-canbus-car_cus-_283-adapter-SettingDialogAdapter, reason: not valid java name */
    /* synthetic */ void m1091xfb63f8bb(int i, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onClick(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View lineView;
        private ImageView tv_select;
        private TextView tv_value;

        public ViewHolder(View view) {
            super(view);
            this.tv_select = (ImageView) view.findViewById(R.id.tv_select);
            this.tv_value = (TextView) view.findViewById(R.id.tv_value);
            this.lineView = view.findViewById(R.id.lineView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
