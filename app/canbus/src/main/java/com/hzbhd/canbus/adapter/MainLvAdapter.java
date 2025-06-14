package com.hzbhd.canbus.adapter;

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
public class MainLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ItemClickInterface mItemClickInterface;
    private List<MainListEntity> mList;

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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        int i2;
        String action = this.mList.get(i).getAction();
        action.hashCode();
        int i3 = 0;
        char c = 65535;
        switch (action.hashCode()) {
            case -2077028368:
                if (action.equals(MainAction.TIME_SET)) {
                    c = 0;
                    break;
                }
                break;
            case -1888873393:
                if (action.equals(MainAction.ORIGINAL_CAR_DEVICE)) {
                    c = 1;
                    break;
                }
                break;
            case -1840463472:
                if (action.equals(MainAction.BAO_YANG)) {
                    c = 2;
                    break;
                }
                break;
            case -1821592035:
                if (action.equals(MainAction.MqbHybirdActivity)) {
                    c = 3;
                    break;
                }
                break;
            case -1812163278:
                if (action.equals(MainAction.SOUND_SET)) {
                    c = 4;
                    break;
                }
                break;
            case -1645844070:
                if (action.equals(MainAction.GENERAL_SETTINGS)) {
                    c = 5;
                    break;
                }
                break;
            case -1482148444:
                if (action.equals(MainAction.PANEL_KEY)) {
                    c = 6;
                    break;
                }
                break;
            case -1202765494:
                if (action.equals(MainAction.HYBIRD)) {
                    c = 7;
                    break;
                }
                break;
            case 96586:
                if (action.equals("air")) {
                    c = '\b';
                    break;
                }
                break;
            case 3545755:
                if (action.equals("sync")) {
                    c = '\t';
                    break;
                }
                break;
            case 3556498:
                if (action.equals(MainAction.TEST)) {
                    c = '\n';
                    break;
                }
                break;
            case 108270587:
                if (action.equals("radio")) {
                    c = 11;
                    break;
                }
                break;
            case 109641799:
                if (action.equals("speed")) {
                    c = '\f';
                    break;
                }
                break;
            case 178891557:
                if (action.equals(MainAction.TIRE_INFO)) {
                    c = '\r';
                    break;
                }
                break;
            case 577205567:
                if (action.equals(MainAction.DRIVE_DATA)) {
                    c = 14;
                    break;
                }
                break;
            case 1124446108:
                if (action.equals(MainAction.WARNING)) {
                    c = 15;
                    break;
                }
                break;
            case 1271599729:
                if (action.equals(MainAction.AMPLIFIER)) {
                    c = 16;
                    break;
                }
                break;
            case 1849102850:
                if (action.equals(MainAction.ON_START)) {
                    c = 17;
                    break;
                }
                break;
            case 1985941072:
                if (action.equals(MainAction.SETTING)) {
                    c = 18;
                    break;
                }
                break;
        }
        int i4 = R.string.function_test;
        switch (c) {
            case 0:
                i2 = R.drawable.main_time_selector;
                break;
            case 1:
                i3 = R.drawable.main_original_car_device;
                i4 = R.string.original_car_device;
                int i5 = i3;
                i3 = i4;
                i2 = i5;
                break;
            case 2:
                i2 = R.drawable.main_baoyang_selector;
                break;
            case 3:
                i3 = R.string.hybrid_view;
                i2 = R.drawable.icon_setm_mix_n;
                break;
            case 4:
                i2 = R.drawable.main_soundset_selector;
                break;
            case 5:
                i3 = R.drawable.icon_setm_generalset_n;
                i4 = R.string.general_setting;
                int i52 = i3;
                i3 = i4;
                i2 = i52;
                break;
            case 6:
                i3 = R.drawable.icon_setm_mb_n;
                i4 = R.string.panel_key;
                int i522 = i3;
                i3 = i4;
                i2 = i522;
                break;
            case 7:
                i3 = R.string.hybird;
                i2 = R.drawable.icon_setm_mix_n;
                break;
            case '\b':
                i3 = R.drawable.icon_setm_air_n;
                i4 = R.string.air_setting;
                int i5222 = i3;
                i3 = i4;
                i2 = i5222;
                break;
            case '\t':
                i3 = R.drawable.icon_setm_sync_n;
                i4 = R.string.sync;
                int i52222 = i3;
                i3 = i4;
                i2 = i52222;
                break;
            case '\n':
            default:
                int i522222 = i3;
                i3 = i4;
                i2 = i522222;
                break;
            case 11:
                i2 = R.drawable.main_radio_selector;
                break;
            case '\f':
                i2 = R.drawable.main_speed_selector;
                break;
            case '\r':
                i3 = R.drawable.icon_setm_tire_n;
                i4 = R.string.tire_data;
                int i5222222 = i3;
                i3 = i4;
                i2 = i5222222;
                break;
            case 14:
                i3 = R.drawable.icon_setm_car_n;
                i4 = R.string.drive_data;
                int i52222222 = i3;
                i3 = i4;
                i2 = i52222222;
                break;
            case 15:
                i3 = R.drawable.icon_setm_warm_n;
                i4 = R.string.warning_msg;
                int i522222222 = i3;
                i3 = i4;
                i2 = i522222222;
                break;
            case 16:
                i3 = R.drawable.icon_setm_eq_n;
                i4 = R.string.amplifier_setting;
                int i5222222222 = i3;
                i3 = i4;
                i2 = i5222222222;
                break;
            case 17:
                i3 = R.drawable.icon_setm_star1_n;
                i4 = R.string.on_start;
                int i52222222222 = i3;
                i3 = i4;
                i2 = i52222222222;
                break;
            case 18:
                i3 = R.drawable.icon_setm_set_n;
                i4 = R.string.car_setting;
                int i522222222222 = i3;
                i3 = i4;
                i2 = i522222222222;
                break;
        }
        viewHolder.textView.setText(i3);
        viewHolder.imageView.setImageResource(i2);
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.MainLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainLvAdapter.this.mItemClickInterface.onItemClick(i);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imageButton;
        private ImageView imageView;
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.imageButton = (ImageButton) view.findViewById(R.id.ib);
            this.imageView = (ImageView) view.findViewById(R.id.iv);
            this.textView = (TextView) view.findViewById(R.id.tv);
        }
    }
}
