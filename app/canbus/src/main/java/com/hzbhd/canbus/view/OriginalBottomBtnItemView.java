package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class OriginalBottomBtnItemView extends RelativeLayout {
    private int mCurrentClick;
    private ImageButton mIbtn;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick();
    }

    public OriginalBottomBtnItemView(Context context, String str) {
        super(context);
        this.mCurrentClick = 0;
        initViews(context, str);
    }

    public OriginalBottomBtnItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentClick = 0;
    }

    private void initViews(Context context, String str) {
        ImageButton imageButton = (ImageButton) LayoutInflater.from(context).inflate(R.layout.view_original_bottom_line_btn_item, this).findViewById(R.id.btn_bottom);
        this.mIbtn = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.OriginalBottomBtnItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (OriginalBottomBtnItemView.this.mOnItemClickListener != null) {
                    OriginalBottomBtnItemView.this.mOnItemClickListener.onClick();
                }
            }
        });
        str.hashCode();
        switch (str) {
            case "preset_scan":
                this.mIbtn.setImageResource(R.drawable.kt01_radio_preset);
                break;
            case "random":
                this.mIbtn.setImageResource(R.drawable.kt01_music_ram);
                break;
            case "repeat":
                this.mIbtn.setImageResource(R.drawable.ic_air_f_xunhuan_n);
                break;
            case "prev_disc":
                this.mIbtn.setImageResource(R.drawable.kt01_music_lastcd);
                break;
            case "auto_store":
                this.mIbtn.setImageResource(R.drawable.kt01_radio_refresh);
                break;
            case "radio_scan":
                this.mIbtn.setImageResource(R.drawable.kt01_radio_scan);
                break;
            case "up":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_fro_n);
                break;
            case "band":
                this.mIbtn.setImageResource(R.drawable.kt01_radio_bank);
                break;
            case "down":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_fast_n);
                break;
            case "left":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_last_n);
                break;
            case "mode":
                this.mIbtn.setImageResource(R.drawable.ic_air_f_mode_n);
                break;
            case "play":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_play_n);
                break;
            case "stop":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_stop_n);
                break;
            case "cycle":
                this.mIbtn.setImageResource(R.drawable.kt01_music_rep);
                break;
            case "pause":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_pause_n);
                break;
            case "right":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_next_n);
                break;
            case "next_disc":
                this.mIbtn.setImageResource(R.drawable.kt01_music_nextcd);
                break;
            case "play_pause":
                this.mIbtn.setImageResource(R.drawable.music_base_ic_play_n);
                this.mIbtn.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.OriginalBottomBtnItemView.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (OriginalBottomBtnItemView.this.mCurrentClick == 0) {
                            OriginalBottomBtnItemView.this.mIbtn.setImageResource(R.drawable.music_base_ic_pause_n);
                            OriginalBottomBtnItemView.this.mCurrentClick = 1;
                        } else {
                            OriginalBottomBtnItemView.this.mIbtn.setImageResource(R.drawable.music_base_ic_play_n);
                            OriginalBottomBtnItemView.this.mCurrentClick = 0;
                        }
                    }
                });
                break;
        }
    }

    public void turn(boolean z) {
        if (z) {
            this.mIbtn.setBackgroundResource(R.drawable.music_top_ic_p);
        } else {
            this.mIbtn.setBackgroundResource(R.drawable.original_row_btn_bottom_selector);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
