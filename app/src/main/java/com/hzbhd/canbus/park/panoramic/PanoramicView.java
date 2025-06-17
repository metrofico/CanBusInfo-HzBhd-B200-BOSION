package com.hzbhd.canbus.park.panoramic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.PanoramiceBtnLvAdapter;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

import java.util.List;

/* loaded from: classes2.dex */
public class PanoramicView extends RelativeLayout {
    private PanoramiceBtnLvAdapter mAdapter;
    private RecyclerView mBtnListRv;
    private Context mContext;
    private final View.OnClickListener mRightBtnOnClickListener;
    private ImageButton mRightHideBtn;
    private ImageButton mRightPullBtn;
    private View mView;

    public PanoramicView(Context context) {
        super(context);
        this.mRightBtnOnClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.park.panoramic.PanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.right_hide_btn) {
                    PanoramicView.this.mRightHideBtn.setVisibility(8);
                    PanoramicView.this.mBtnListRv.setVisibility(8);
                    PanoramicView.this.mRightPullBtn.setVisibility(0);
                } else {
                    if (id != R.id.right_pull_btn) {
                        return;
                    }
                    PanoramicView.this.mRightPullBtn.setVisibility(8);
                    PanoramicView.this.mBtnListRv.setVisibility(0);
                    PanoramicView.this.mRightHideBtn.setVisibility(0);
                }
            }
        };
    }

    public PanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRightBtnOnClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.park.panoramic.PanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.right_hide_btn) {
                    PanoramicView.this.mRightHideBtn.setVisibility(8);
                    PanoramicView.this.mBtnListRv.setVisibility(8);
                    PanoramicView.this.mRightPullBtn.setVisibility(0);
                } else {
                    if (id != R.id.right_pull_btn) {
                        return;
                    }
                    PanoramicView.this.mRightPullBtn.setVisibility(8);
                    PanoramicView.this.mBtnListRv.setVisibility(0);
                    PanoramicView.this.mRightHideBtn.setVisibility(0);
                }
            }
        };
        this.mContext = context;
        initViews(context);
    }

    private void initViews(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.layout_panoramic, this);
        initRightSmallView();
    }

    private void initRightSmallView() {
        this.mRightPullBtn = this.mView.findViewById(R.id.right_pull_btn);
        this.mRightHideBtn = this.mView.findViewById(R.id.right_hide_btn);
        this.mBtnListRv = this.mView.findViewById(R.id.rv_btn_list);
        this.mRightPullBtn.setOnClickListener(this.mRightBtnOnClickListener);
        this.mRightHideBtn.setOnClickListener(this.mRightBtnOnClickListener);
    }

    public void setBtnList(List<ParkPageUiSet.Bean> list, OnPanoramicItemClickListener onPanoramicItemClickListener) {
        this.mAdapter = new PanoramiceBtnLvAdapter(this.mContext, list, onPanoramicItemClickListener);
        this.mBtnListRv.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mBtnListRv.setAdapter(this.mAdapter);
    }

    public PanoramiceBtnLvAdapter getAdapter() {
        return this.mAdapter;
    }
}
