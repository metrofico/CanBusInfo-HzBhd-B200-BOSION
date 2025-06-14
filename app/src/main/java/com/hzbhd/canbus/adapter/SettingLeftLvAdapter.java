package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class SettingLeftLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private LeftItemClickInterface mLeftItemClickInterface;
    private List<SettingPageUiSet.ListBean> mList;

    public interface LeftItemClickInterface {
        void onLeftItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SettingLeftLvAdapter(Context context, List<SettingPageUiSet.ListBean> list, LeftItemClickInterface leftItemClickInterface) {
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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_setting_cate, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn()));
        if (!this.mList.get(i).isIsSelected()) {
            if (i % 2 == 0) {
                viewHolder.textView.setBackgroundResource(R.color.select_protocol_company_color_1);
            } else {
                viewHolder.textView.setBackgroundResource(R.color.select_protocol_company_color_2);
            }
        } else {
            viewHolder.textView.setBackgroundResource(R.drawable.ic_setlist_l_f);
        }
        viewHolder.textView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SettingLeftLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingLeftLvAdapter.this.mLeftItemClickInterface.onLeftItemClick(i);
            }
        });
        viewHolder.setVisibility(false);
        Iterator<SettingPageUiSet.ListBean.ItemListBean> it = this.mList.get(i).getItemList().iterator();
        while (it.hasNext()) {
            if (it.next().isEnable()) {
                viewHolder.setVisibility(true);
                return;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.tv_cate);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVisibility(boolean z) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
            if (z) {
                this.itemView.setVisibility(0);
                layoutParams.height = -2;
                layoutParams.width = -1;
            } else {
                this.itemView.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.itemView.setLayoutParams(layoutParams);
        }
    }
}
