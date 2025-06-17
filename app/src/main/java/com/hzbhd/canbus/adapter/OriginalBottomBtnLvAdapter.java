package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class OriginalBottomBtnLvAdapter extends RecyclerView.Adapter<OriginalBottomBtnLvAdapter.ViewHolder> {
    private final OnOriginalBottomBtnClickListener mBtnClickInterface;
    private int mCurrentClick = 0;
    private final List<String> mDisplayList;
    private final List<String> mOriginalList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public OriginalBottomBtnLvAdapter(Context context, String[] strArr, List<String> list, OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener) {
        this.mBtnClickInterface = onOriginalBottomBtnClickListener;
        this.mOriginalList = Arrays.asList(strArr);
        this.mDisplayList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mOriginalList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_original_bottom_line_btn_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.mIb.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.OriginalBottomBtnLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int iIndexOf = OriginalBottomBtnLvAdapter.this.mOriginalList.indexOf(OriginalBottomBtnLvAdapter.this.mDisplayList.get(i));
                if (OriginalBottomBtnLvAdapter.this.mBtnClickInterface != null) {
                    OriginalBottomBtnLvAdapter.this.mBtnClickInterface.onClickBottomBtnItem(iIndexOf);
                }
            }
        });
        if (viewHolder.mIb.getDrawable() == null) {
            String str = this.mDisplayList.get(i);
            switch (str) {
                case "preset_scan":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_radio_preset);
                    break;
                case "random":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_music_ram);
                    break;
                case "repeat":
                    viewHolder.mIb.setImageResource(R.drawable.ic_air_f_xunhuan_n);
                    break;
                case "prev_disc":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_music_lastcd);
                    break;
                case "auto_store":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_radio_refresh);
                    break;
                case "radio_scan":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_radio_scan);
                    break;
                case "up":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_fro_n);
                    break;
                case "band":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_radio_bank);
                    break;
                case "down":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_fast_n);
                    break;
                case "left":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_last_n);
                    break;
                case "mode":
                    viewHolder.mIb.setImageResource(R.drawable.ic_air_f_mode_n);
                    break;
                case "play":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_play_n);
                    break;
                case "stop":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_stop_n);
                    break;
                case "cycle":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_music_rep);
                    break;
                case "pause":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_pause_n);
                    break;
                case "power":
                    viewHolder.mIb.setImageResource(R.drawable.ic_air_f_power_n);
                    break;
                case "right":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_next_n);
                    break;
                case "next_disc":
                    viewHolder.mIb.setImageResource(R.drawable.kt01_music_nextcd);
                    break;
                case "play_pause":
                    viewHolder.mIb.setImageResource(R.drawable.music_base_ic_play_n);
                    viewHolder.mIb.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.OriginalBottomBtnLvAdapter.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (OriginalBottomBtnLvAdapter.this.mCurrentClick == 0) {
                                viewHolder.mIb.setImageResource(R.drawable.music_base_ic_pause_n);
                                OriginalBottomBtnLvAdapter.this.mCurrentClick = 1;
                            } else {
                                viewHolder.mIb.setImageResource(R.drawable.music_base_ic_play_n);
                                OriginalBottomBtnLvAdapter.this.mCurrentClick = 0;
                            }
                        }
                    });
                    break;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton mIb;

        ViewHolder(View view) {
            super(view);
            this.mIb = view.findViewById(R.id.btn_bottom);
        }
    }
}
