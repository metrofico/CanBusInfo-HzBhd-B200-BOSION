package com.hzbhd.canbus.car_cus._273.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._273.entity.OnOffBean;
import java.util.List;

/* loaded from: classes2.dex */
public class OnOffLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private ItemClickInterface mItemClickInterface;
    private List<OnOffBean> mList;

    public interface ItemClickInterface {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public OnOffLvAdapter(Context context, List<OnOffBean> list, ItemClickInterface itemClickInterface) {
        this.mItemClickInterface = itemClickInterface;
        this.mList = list;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._273_layout_on_off_btn, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(this.mList.get(i).getTitleRes());
        if (this.mList.get(i).isSelected()) {
            viewHolder.btnView.setImageResource(this.mList.get(i).getIconSelectRes());
        } else {
            viewHolder.btnView.setImageResource(this.mList.get(i).getIconNoSelectRes());
        }
        viewHolder.btnView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._273.adapter.OnOffLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (((OnOffBean) OnOffLvAdapter.this.mList.get(i)).isClickable()) {
                    OnOffLvAdapter.this.mItemClickInterface.onItemClick(i);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton btnView;
        private RelativeLayout relativeLayout;
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.btnView = (ImageButton) view.findViewById(R.id.ibt_item);
            this.textView = (TextView) view.findViewById(R.id.tv_item);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
        }
    }
}
