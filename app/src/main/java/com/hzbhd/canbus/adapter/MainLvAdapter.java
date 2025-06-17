package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.MainListEntity;
import com.hzbhd.canbus.ui_set.MainAction;

import java.util.List;

/* loaded from: classes.dex */
public class MainLvAdapter extends RecyclerView.Adapter<MainLvAdapter.ViewHolder> {
    private final ItemClickInterface mItemClickInterface;
    private final List<MainListEntity> mList;

    public interface ItemClickInterface {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MainLvAdapter(List<MainListEntity> list, ItemClickInterface itemClickInterface) {
        this.mItemClickInterface = itemClickInterface;
        this.mList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_main, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        String action = this.mList.get(i).getAction();
        action.hashCode();

        int textResource = R.string.function_test;  // Default value for i3
        int imageResource = 0;  // Default value for i2

        switch (action) {
            case MainAction.TIME_SET:
                imageResource = R.drawable.main_time_selector;
                break;
            case MainAction.ORIGINAL_CAR_DEVICE:
                imageResource = R.drawable.main_original_car_device;
                textResource = R.string.original_car_device;
                break;
            case MainAction.BAO_YANG:
                imageResource = R.drawable.main_baoyang_selector;
                break;
            case MainAction.MqbHybirdActivity:
                imageResource = R.drawable.icon_setm_mix_n;
                textResource = R.string.hybrid_view;
                break;
            case MainAction.SOUND_SET:
                imageResource = R.drawable.main_soundset_selector;
                break;
            case MainAction.GENERAL_SETTINGS:
                imageResource = R.drawable.icon_setm_generalset_n;
                textResource = R.string.general_setting;
                break;
            case MainAction.PANEL_KEY:
                imageResource = R.drawable.icon_setm_mb_n;
                textResource = R.string.panel_key;
                break;
            case MainAction.HYBIRD:
                imageResource = R.drawable.icon_setm_mix_n;
                textResource = R.string.hybird;
                break;
            case "air":
                imageResource = R.drawable.icon_setm_air_n;
                textResource = R.string.air_setting;
                break;
            case "sync":
                imageResource = R.drawable.icon_setm_sync_n;
                textResource = R.string.sync;
                break;
            case MainAction.TEST:
                break;
            case "radio":
                imageResource = R.drawable.main_radio_selector;
                break;
            case "speed":
                imageResource = R.drawable.main_speed_selector;
                break;
            case MainAction.TIRE_INFO:
                imageResource = R.drawable.icon_setm_tire_n;
                textResource = R.string.tire_data;
                break;
            case MainAction.DRIVE_DATA:
                imageResource = R.drawable.icon_setm_car_n;
                textResource = R.string.drive_data;
                break;
            case MainAction.WARNING:
                imageResource = R.drawable.icon_setm_warm_n;
                textResource = R.string.warning_msg;
                break;
            case MainAction.AMPLIFIER:
                imageResource = R.drawable.icon_setm_eq_n;
                textResource = R.string.amplifier_setting;
                break;
            case MainAction.ON_START:
                imageResource = R.drawable.icon_setm_star1_n;
                textResource = R.string.on_start;
                break;
            case MainAction.SETTING:
                imageResource = R.drawable.icon_setm_set_n;
                textResource = R.string.car_setting;
                break;
            default:
                // Default case if none of the above matched
                break;
        }

        // Setting the values to view
        viewHolder.textView.setText(textResource);
        viewHolder.imageView.setImageResource(imageResource);

        // Setting onClickListener
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainLvAdapter.this.mItemClickInterface.onItemClick(i);
            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton imageButton;
        private final ImageView imageView;
        private final TextView textView;

        ViewHolder(View view) {
            super(view);
            this.imageButton = view.findViewById(R.id.ib);
            this.imageView = view.findViewById(R.id.iv);
            this.textView = view.findViewById(R.id.tv);
        }
    }
}
