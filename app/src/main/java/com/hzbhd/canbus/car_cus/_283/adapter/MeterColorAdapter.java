package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class MeterColorAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int[] mColors;
    private Context mContext;
    private OnViewClickListener mOnViewClickListener;
    private int selectedPosition = -1;

    public interface OnViewClickListener {
        void onClick(View view, int i);
    }

    public MeterColorAdapter(Context context, int[] iArr) {
        this.mColors = iArr;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout._283_adapter_color_item, (ViewGroup) null, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.imageView.setImageResource(this.mColors[i]);
        viewHolder.itemLayout.setSelected(i == this.selectedPosition);
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1090x4f602ed1(i, view);
            }
        });
    }

    /* renamed from: lambda$onBindViewHolder$0$com-hzbhd-canbus-car_cus-_283-adapter-MeterColorAdapter, reason: not valid java name */
    /* synthetic */ void m1090x4f602ed1(int i, View view) {
        OnViewClickListener onViewClickListener = this.mOnViewClickListener;
        if (onViewClickListener != null) {
            onViewClickListener.onClick(view, i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mColors.length;
    }

    public void setSelectedItem(int i) {
        this.selectedPosition = i;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout itemLayout;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.itemLayout = (RelativeLayout) view.findViewById(R.id.itemLayout);
        }
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.mOnViewClickListener = onViewClickListener;
    }
}
