package com.hzbhd.canbus.car_cus._290.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class TemLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int[] mArray;
    private Context mContext;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public TemLvAdapter(Context context, int[] iArr) {
        this.mArray = iArr;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mArray.length;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._273_layout_tem_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(String.valueOf(this.mArray[i]));
        if (i == 3) {
            viewHolder.textView.setBackgroundResource(R.drawable.cw_kt02_mid_ic_tempe1_p);
        } else {
            viewHolder.textView.setBackground(null);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.tv);
        }
    }
}
