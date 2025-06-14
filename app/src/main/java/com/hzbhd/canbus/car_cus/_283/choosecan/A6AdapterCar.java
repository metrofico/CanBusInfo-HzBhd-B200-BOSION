package com.hzbhd.canbus.car_cus._283.choosecan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import java.util.List;

/* loaded from: classes2.dex */
public class A6AdapterCar extends RecyclerView.Adapter<ViewHolder> {
    private List<A6CarChooseBean> lists;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int i);
    }

    public A6AdapterCar(Context context, List<A6CarChooseBean> list) {
        this.mContext = context;
        this.lists = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.__283__adapter_car, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.itemLayout.setSelected(this.lists.get(i).isSelect());
        viewHolder.imageView.setSelected(this.lists.get(i).isSelect());
        viewHolder.tv_title.setText(this.lists.get(i).getName());
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.choosecan.A6AdapterCar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (A6AdapterCar.this.mOnItemClickListener != null) {
                    A6AdapterCar.this.mOnItemClickListener.onClick(view, i);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private LinearLayout itemLayout;
        private TextView tv_title;

        public ViewHolder(View view) {
            super(view);
            this.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
