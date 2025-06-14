package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.CommUtil;

import java.util.List;

/* loaded from: classes.dex */
public class PanoramiceBtnLvAdapter extends RecyclerView.Adapter<PanoramiceBtnLvAdapter.ViewHolder> {
    private final Context mContext;
    private final OnPanoramicItemClickListener mItemClickInterface;
    private final List<ParkPageUiSet.Bean> mList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public PanoramiceBtnLvAdapter(Context context, List<ParkPageUiSet.Bean> list, OnPanoramicItemClickListener onPanoramicItemClickListener) {
        this.mItemClickInterface = onPanoramicItemClickListener;
        this.mList = list;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mList.get(i).getStyle();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i != 0 ? i != 1 ? 0 : R.layout.layout_item_panoramic_1_lv : R.layout.layout_item_panoramic_0_lv, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            viewHolder.tvText.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn()));
        } else if (itemViewType == 1) {
            viewHolder.ivImage.setImageResource(CommUtil.getImgIdByResId(this.mContext, this.mList.get(i).getImgRes()));
        }
        viewHolder.relativeLayout.setSelected(this.mList.get(i).isSelect());
        if (this.mItemClickInterface != null) {
            viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.PanoramiceBtnLvAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PanoramiceBtnLvAdapter.this.mItemClickInterface.onClickItem(i);
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final RelativeLayout relativeLayout;
        private final TextView tvText;

        ViewHolder(View view) {
            super(view);
            this.relativeLayout = view.findViewById(R.id.ll_layout);
            this.tvText = view.findViewById(R.id.tv_text);
            this.ivImage = view.findViewById(R.id.iv_image);
        }
    }
}
