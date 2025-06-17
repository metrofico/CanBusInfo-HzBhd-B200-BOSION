package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.OnStartListEntity;
import com.hzbhd.canbus.ui_set.OnStartAction;

import java.util.List;

/* loaded from: classes.dex */
public class OnStartLvAdapter extends RecyclerView.Adapter<OnStartLvAdapter.ViewHolder> {
    private ItemClickInterface mItemClickInterface;
    private List<OnStartListEntity> mList;

    public interface ItemClickInterface {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public OnStartLvAdapter(List<OnStartListEntity> list, ItemClickInterface itemClickInterface) {
        this.mItemClickInterface = itemClickInterface;
        this.mList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_on_start, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        String action = this.mList.get(i).getAction();

        int i2 = 0; // Default image resource
        int i3 = 0; // Default text resource

        switch (action) {
            case OnStartAction.PHONE_MORE_INFO:
            case "phone":
                i3 = R.string.on_start_phone;
                break;
            case OnStartAction.WIRELESS:
                i2 = R.drawable.ic_list_l_wi_n;
                i3 = R.string.wireless_connections;
                break;
            case "navigation":
                i2 = R.drawable.ic_list_l_navi_n;
                i3 = R.string.navigation;
                break;
            case OnStartAction.BLUETOOTH:
                i2 = R.drawable.ic_list_l_bt1_n;
                i3 = R.string.bluetooth;
                break;
            default:
                // Default image for unmatched actions
                // Default text for unmatched actions
                break;
        }

        // Set the text and image based on the action
        viewHolder.textView.setText(i3);
        viewHolder.imageView.setImageResource(i2);

        // Set click listener on the item
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnStartLvAdapter.this.mItemClickInterface.onItemClick(i);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.ll_layout);
            this.imageView = (ImageView) view.findViewById(R.id.iv);
            this.textView = (TextView) view.findViewById(R.id.tv);
        }
    }
}
