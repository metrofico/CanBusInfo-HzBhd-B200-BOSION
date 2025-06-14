package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class SettingView extends RelativeLayout {
    private ImageView iv_title;
    private Context mContext;
    private View mView;
    private TextView tv_title;
    private TextView tv_value;

    public SettingView(Context context) {
        this(context, null);
    }

    public SettingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_setting_view, (ViewGroup) this, true);
        initView();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SettingSelectStyle);
        String string = typedArrayObtainStyledAttributes.getString(0);
        String string2 = typedArrayObtainStyledAttributes.getString(2);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        if (!TextUtils.isEmpty(string)) {
            this.tv_title.setText(string);
        }
        setValue(string2);
        if (drawable != null) {
            this.iv_title.setVisibility(0);
            this.iv_title.setImageDrawable(drawable);
        } else {
            this.iv_title.setVisibility(8);
            this.iv_title.setImageDrawable(null);
        }
    }

    private void initView() {
        this.tv_title = (TextView) this.mView.findViewById(R.id.tv_title);
        this.tv_value = (TextView) this.mView.findViewById(R.id.tv_value);
        this.iv_title = (ImageView) this.mView.findViewById(R.id.iv_title);
    }

    public void setValue(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.tv_value.setVisibility(0);
            this.tv_value.setText(str);
        } else {
            this.tv_value.setVisibility(8);
            this.tv_value.setText("");
        }
    }

    public void setEnable(boolean z) {
        if (z) {
            this.tv_title.setTextColor(this.mContext.getColor(R.color.white));
            this.tv_value.setTextColor(this.mContext.getColor(R.color.white));
        } else {
            this.tv_title.setTextColor(this.mContext.getColor(R.color._283_color_gray));
            this.tv_value.setTextColor(this.mContext.getColor(R.color._283_color_gray));
        }
        setEnabled(z);
    }
}
