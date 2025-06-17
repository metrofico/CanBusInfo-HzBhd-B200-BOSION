package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.CommUtil;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class PanelKeyLvAdapter extends RecyclerView.Adapter<PanelKeyLvAdapter.ViewHolder> {
    private final Context mContext;
    private final ItemClickInterface mItemClickInterface;
    private final ItemLongClickInterface mItemLongClickInterface;
    private final ItemTouchInterface mItemTouchInterface;
    private final ArrayList<String> mList;

    public interface ItemClickInterface {
        void onItemClick(int i);
    }

    public interface ItemLongClickInterface {
        void onItemLongClick(int i);
    }

    public interface ItemTouchInterface {
        void onItemTouch(int i, MotionEvent motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public PanelKeyLvAdapter(Context context, ArrayList<String> arrayList, ItemClickInterface itemClickInterface, ItemTouchInterface itemTouchInterface, ItemLongClickInterface itemLongClickInterface) {
        this.mItemClickInterface = itemClickInterface;
        this.mItemTouchInterface = itemTouchInterface;
        this.mItemLongClickInterface = itemLongClickInterface;
        this.mList = arrayList;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_panel_key_btn, viewGroup, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.textView.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i)));
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.PanelKeyLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PanelKeyLvAdapter.this.mItemClickInterface.onItemClick(i);
            }
        });
        viewHolder.imageButton.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.adapter.PanelKeyLvAdapter.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (PanelKeyLvAdapter.this.mItemTouchInterface == null) {
                    return false;
                }
                PanelKeyLvAdapter.this.mItemTouchInterface.onItemTouch(i, motionEvent);
                return false;
            }
        });
        viewHolder.imageButton.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.adapter.PanelKeyLvAdapter.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (PanelKeyLvAdapter.this.mItemLongClickInterface == null) {
                    return false;
                }
                PanelKeyLvAdapter.this.mItemLongClickInterface.onItemLongClick(i);
                return false;
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton imageButton;
        private final TextView textView;

        ViewHolder(View view) {
            super(view);
            this.imageButton = view.findViewById(R.id.ibt_item);
            this.textView = view.findViewById(R.id.tv_item);
        }
    }
}
