package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzbhd.R;

import org.apache.log4j.varia.ExternallyRolledFileAppender;

/* loaded from: classes2.dex */
public class SyncKeyBoardItemView extends RelativeLayout {
    private ImageButton mIb;
    private TextView mTv;

    interface OnClickListener {
        void onClick();

        void onLongClick();
    }

    public SyncKeyBoardItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.ford_sync_keyboard_item, this, true);
        findView();
    }

    private void findView() {
        this.mIb = findViewById(R.id.ib);
        this.mTv = findViewById(R.id.tv);
    }

    public void initView(String str, final OnClickListener onClickListener) {
        this.mIb.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SyncKeyBoardItemView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick();
                }
            }
        });
        this.mIb.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.view.SyncKeyBoardItemView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (onClickListener == null) {
                    return true;
                }
                onClickListener.onLongClick();
                return true;
            }
        });
        str.hashCode();
        switch (str) {
            case "number_0":
                this.mTv.setText("0");
                break;
            case "number_1":
                this.mTv.setText("1");
                break;
            case "number_2":
                this.mTv.setText("2");
                break;
            case "number_3":
                this.mTv.setText("3");
                break;
            case "number_4":
                this.mTv.setText("4");
                break;
            case "number_5":
                this.mTv.setText("5");
                break;
            case "number_6":
                this.mTv.setText("6");
                break;
            case "number_7":
                this.mTv.setText("7");
                break;
            case "number_8":
                this.mTv.setText("8");
                break;
            case "number_9":
                this.mTv.setText("9");
                break;
            case "device":
                this.mTv.setText("DEV");
                break;
            case "hangup":
                this.mIb.setImageResource(R.drawable.ford_sync_keyboard_hang_up);
                break;
            case "pickup":
                this.mIb.setImageResource(R.drawable.ford_sync_icon_41);
                break;
            case "ok":
                this.mTv.setText(ExternallyRolledFileAppender.OK);
                break;
            case "up":
                this.mIb.setImageResource(R.drawable.ford_sync_keyboard_up);
                break;
            case "aux":
                this.mTv.setText("AUX");
                break;
            case "down":
                this.mIb.setImageResource(R.drawable.ford_sync_keyboard_down);
                break;
            case "info":
                this.mTv.setText("INFO");
                break;
            case "left":
                this.mIb.setImageResource(R.drawable.ford_sync_keyboard_left);
                break;
            case "menu":
                this.mTv.setText("MENU");
                break;
            case "next":
                this.mIb.setImageResource(R.drawable.ford_sync_keyboard_next);
                break;
            case "null":
                this.mIb.setEnabled(false);
                break;
            case "prev":
                this.mIb.setImageResource(R.drawable.ford_sync_keyboard_prev);
                break;
            case "star":
                this.mTv.setText("*");
                break;
            case "well":
                this.mTv.setText("#");
                break;
            case "right":
                this.mIb.setImageResource(R.drawable.ford_sync_keyboard_right);
                break;
            case "shuff":
                this.mIb.setImageResource(R.drawable.ford_sync_icon_116);
                break;
            case "btphone_on":
                this.mIb.setImageResource(R.drawable.ford_sync_icon_204);
                break;
            case "btphone_off":
                this.mIb.setImageResource(R.drawable.ford_sync_icon_205);
                break;
        }
    }


}
