package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SyncBtnItemView extends RelativeLayout {
    private ImageButton mIb;
    private OnClickListener mOnClickListener;
    private TextView mTv;

    interface OnClickListener {
        void onclick();
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    public SyncBtnItemView(Context context) {
        super(context);
        initViews(context);
    }

    private void initViews(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_sync_bottom_text, this);
        this.mTv = (TextView) viewInflate.findViewById(R.id.tv_item);
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.ibt_item);
        this.mIb = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SyncBtnItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SyncBtnItemView.this.mOnClickListener.onclick();
            }
        });
    }

    public void setText(String str) {
        this.mTv.setText(str);
    }

    public void setImageResource(int i) {
        this.mIb.setImageResource(i);
    }

    public void setTextColor(int i) {
        this.mTv.setTextColor(i);
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        this.mIb.setSelected(z);
    }

    public void setVisible(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
