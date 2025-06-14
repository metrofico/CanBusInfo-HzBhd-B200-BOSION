package com.hzbhd.canbus.car_cus._273.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._273.entity.LeftItemBean;
import java.util.List;

/* loaded from: classes2.dex */
public class MainLeftLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LeftItemClickInterface mLeftItemClickInterface;
    private List<LeftItemBean> mList;

    public interface LeftItemClickInterface {
        void onLeftItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MainLeftLvAdapter(Context context, List<LeftItemBean> list, LeftItemClickInterface leftItemClickInterface) {
        this.mLeftItemClickInterface = leftItemClickInterface;
        this.mList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._273_layout_item_main_left, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (this.mList.get(i).isSelected()) {
            viewHolder.relativeLayout.setBackgroundResource(R.drawable.cw_kt01_left_ic_null_p);
        } else {
            viewHolder.relativeLayout.setBackgroundResource(R.drawable.cw_kt01_left_ic_null_n);
        }
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._273.adapter.MainLeftLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainLeftLvAdapter.this.mLeftItemClickInterface.onLeftItemClick(i);
            }
        });
        viewHolder.textView.setText(this.mList.get(i).getTitleRes());
        viewHolder.imageView.setImageResource(this.mList.get(i).getIconRes());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.tv);
            this.imageView = (ImageView) view.findViewById(R.id.iv);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.fl);
        }
    }
}
