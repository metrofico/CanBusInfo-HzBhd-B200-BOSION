package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SyncListItemView extends LinearLayout {
    private ImageView mIvLeftIcon;
    private ImageView mIvRightIcon;
    private LinearLayout mLlListItem;
    private OnClickListener mOnClickListener;
    private TextView mTvInfo;

    interface OnClickListener {
        void onItemClick();
    }

    public SyncListItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.ford_sync_list_item, this);
        findView();
        initView();
    }

    private void findView() {
        this.mLlListItem = findViewById(R.id.ll_list_item);
        this.mIvLeftIcon = findViewById(R.id.iv_left_icon);
        this.mIvRightIcon = findViewById(R.id.iv_right_icon);
        this.mTvInfo = findViewById(R.id.tv_info);
    }

    private void initView() {
        this.mLlListItem.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SyncListItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SyncListItemView.this.mOnClickListener.onItemClick();
            }
        });
    }

    public void setItem(int i, String str, int i2) {
        try {
            this.mIvLeftIcon.setImageResource(i);
            this.mTvInfo.setText(str);
            this.mIvRightIcon.setImageResource(i2);
        } catch (Exception unused) {
            this.mIvLeftIcon.setImageResource(R.color.transparent);
            this.mTvInfo.setText("");
            this.mIvRightIcon.setImageResource(R.color.transparent);
        }
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        this.mLlListItem.setSelected(z);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.mLlListItem.setEnabled(z);
    }
}
