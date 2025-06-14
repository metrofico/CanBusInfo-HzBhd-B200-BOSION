package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.bean.TmpsChooseBean;
import java.util.List;

/* loaded from: classes2.dex */
public class TmpsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<TmpsChooseBean> lists;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void click(int i);
    }

    public TmpsAdapter(Context context, List<TmpsChooseBean> list) {
        this.mContext = context;
        this.lists = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout._283_tmps_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.imageView.setImageResource(this.lists.get(i).getImage());
        viewHolder.textView.setText(this.lists.get(i).getText());
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.adapter.TmpsAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1092x6fa128af(i, view);
            }
        });
    }

    /* renamed from: lambda$onBindViewHolder$0$com-hzbhd-canbus-car_cus-_283-adapter-TmpsAdapter, reason: not valid java name */
    /* synthetic */ void m1092x6fa128af(int i, View view) {
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
        private ImageView imageView;
        private RelativeLayout itemLayout;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.itemLayout = (RelativeLayout) view.findViewById(R.id.itemLayout);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.textView = (TextView) view.findViewById(R.id.textView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
