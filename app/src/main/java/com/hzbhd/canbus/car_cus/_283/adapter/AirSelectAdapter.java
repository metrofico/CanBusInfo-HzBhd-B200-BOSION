package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.bean.AirSelectBean;
import java.util.List;

/* loaded from: classes2.dex */
public class AirSelectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<AirSelectBean> lists;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void click(int i);
    }

    public AirSelectAdapter(Context context, List<AirSelectBean> list) {
        this.mContext = context;
        this.lists = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout._283_air_adapter_select, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.view.setVisibility(this.lists.get(i).getViewVisible());
        viewHolder.imageView.setVisibility(this.lists.get(i).getDrawablwVisible());
        viewHolder.textView.setVisibility(this.lists.get(i).getTextVisible());
        viewHolder.selectView.setVisibility(this.lists.get(i).getSelectVisible());
        viewHolder.textView.setText(this.lists.get(i).getText());
        viewHolder.imageView.setImageDrawable(this.lists.get(i).getDrawable());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AirSelectAdapter.this.mOnItemClickListener != null) {
                    AirSelectAdapter.this.mOnItemClickListener.click(i);
                }
            }
        });
        if (getItemCount() - 1 == i) {
            viewHolder.lineView.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private View lineView;
        private ImageView selectView;
        private TextView textView;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this.selectView = (ImageView) view.findViewById(R.id.selectView);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.textView = (TextView) view.findViewById(R.id.textView);
            this.view = view.findViewById(R.id.view);
            this.lineView = view.findViewById(R.id.lineView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
