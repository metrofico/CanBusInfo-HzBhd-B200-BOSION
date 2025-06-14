package com.hzbhd.canbus.adapter;

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
public class OnStartLvAdapter extends RecyclerView.Adapter<ViewHolder> {
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

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        int i2;
        String action = this.mList.get(i).getAction();
        action.hashCode();
        int i3 = 0;
        char c = 65535;
        switch (action.hashCode()) {
            case -1664453177:
                if (action.equals(OnStartAction.PHONE_MORE_INFO)) {
                    c = 0;
                    break;
                }
                break;
            case -1000044642:
                if (action.equals(OnStartAction.WIRELESS)) {
                    c = 1;
                    break;
                }
                break;
            case 106642798:
                if (action.equals("phone")) {
                    c = 2;
                    break;
                }
                break;
            case 1862666772:
                if (action.equals("navigation")) {
                    c = 3;
                    break;
                }
                break;
            case 1968882350:
                if (action.equals(OnStartAction.BLUETOOTH)) {
                    c = 4;
                    break;
                }
                break;
        }
        int i4 = R.string.on_start_phone;
        int i5 = R.drawable.ic_list_l_bt_n;
        switch (c) {
            case 0:
            case 2:
                i3 = i4;
                break;
            case 1:
                i2 = R.drawable.ic_list_l_wi_n;
                i4 = R.string.wireless_connections;
                i5 = i2;
                i3 = i4;
                break;
            case 3:
                i2 = R.drawable.ic_list_l_navi_n;
                i4 = R.string.navigation;
                i5 = i2;
                i3 = i4;
                break;
            case 4:
                i2 = R.drawable.ic_list_l_bt1_n;
                i4 = R.string.bluetooth;
                i5 = i2;
                i3 = i4;
                break;
            default:
                i5 = 0;
                break;
        }
        viewHolder.textView.setText(i3);
        viewHolder.imageView.setImageResource(i5);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.OnStartLvAdapter.1
            @Override // android.view.View.OnClickListener
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
