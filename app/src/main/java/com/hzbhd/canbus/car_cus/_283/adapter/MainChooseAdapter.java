package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.bean.MainChooseBean;
import java.util.List;

/* loaded from: classes2.dex */
public class MainChooseAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<MainChooseBean> lists;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void click(int i);
    }

    public MainChooseAdapter(Context context, List<MainChooseBean> list) {
        this.mContext = context;
        this.lists = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout._283_adapter_main_choose, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(this.lists.get(i).getText());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1088x1794ba3b(i, view);
            }
        });
        if (getItemCount() - 1 == i) {
            viewHolder.lineView.setVisibility(8);
        }
    }

    /* renamed from: lambda$onBindViewHolder$0$com-hzbhd-canbus-car_cus-_283-adapter-MainChooseAdapter, reason: not valid java name */
    /* synthetic */ void m1088x1794ba3b(int i, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.click(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View lineView;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.textView);
            this.lineView = view.findViewById(R.id.lineView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
