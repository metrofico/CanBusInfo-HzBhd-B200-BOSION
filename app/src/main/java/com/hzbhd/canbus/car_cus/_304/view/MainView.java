package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._304.bean.MainPageEntity;
import java.util.List;

/* loaded from: classes2.dex */
public class MainView extends LinearLayout {
    private List<MainPageEntity> mList;
    private LinearLayout mLlTitles;
    private RelativeLayout mRlContent;

    public MainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout._304_view_main, this);
        findViews();
    }

    private void findViews() {
        this.mLlTitles = (LinearLayout) findViewById(R.id.ll_top_titles);
        this.mRlContent = (RelativeLayout) findViewById(R.id.rl_contents);
    }

    public void initView(Context context, List<MainPageEntity> list) {
        this.mList = list;
        int size = list.size();
        for (final int i = 0; i < list.size(); i++) {
            final MainPageEntity mainPageEntity = list.get(i);
            Button button = getButton(context);
            button.setText(mainPageEntity.getTitle());
            button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._304.view.MainView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m1134lambda$initView$0$comhzbhdcanbuscar_cus_304viewMainView(i, mainPageEntity, view);
                }
            });
            this.mLlTitles.addView(button);
            if (i != size - 1) {
                this.mLlTitles.addView(getDivider(context));
            }
        }
        reset();
    }

    /* renamed from: lambda$initView$0$com-hzbhd-canbus-car_cus-_304-view-MainView, reason: not valid java name */
    /* synthetic */ void m1134lambda$initView$0$comhzbhdcanbuscar_cus_304viewMainView(int i, MainPageEntity mainPageEntity, View view) {
        refreshTopView(i);
        this.mRlContent.removeAllViews();
        this.mRlContent.addView(mainPageEntity.getView());
    }

    private void refreshTopView(int i) {
        int childCount = this.mLlTitles.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.mLlTitles.getChildAt(i2);
            if (childAt instanceof Button) {
                ((Button) childAt).setSelected(i2 == i);
            } else {
                i++;
            }
            i2++;
        }
    }

    public void reset() {
        this.mRlContent.removeAllViews();
        refreshTopView(0);
        this.mRlContent.addView(this.mList.get(0).getView());
    }

    private Button getButton(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        Button button = new Button(context);
        button.setBackgroundResource(R.drawable._304_selector_main_top_item);
        button.setTextSize(context.getResources().getDimension(R.dimen.dp24));
        button.setTextColor(context.getColor(R.color.white));
        button.setLayoutParams(layoutParams);
        return button;
    }

    private ImageView getDivider(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.bottomMargin = 2;
        layoutParams.setMarginEnd(2);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable._304_main_top_item_divider);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }
}
