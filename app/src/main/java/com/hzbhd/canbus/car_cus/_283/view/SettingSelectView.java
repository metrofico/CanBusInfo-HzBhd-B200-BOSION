package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
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
public class SettingSelectView extends RelativeLayout implements View.OnClickListener {
    private boolean isSelect;
    private ImageView iv_select;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private View mView;
    private RelativeLayout relativeLayout;
    private TextView tv_title;

    public interface OnItemClickListener {
        void onClick(View view, boolean z);
    }

    public SettingSelectView(Context context) {
        this(context, null);
    }

    public SettingSelectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_setting_select_view, (ViewGroup) this, true);
        initView();
        String string = context.obtainStyledAttributes(attributeSet, R.styleable.SettingSelectStyle).getString(0);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.tv_title.setText(string);
    }

    private void initView() {
        this.relativeLayout = (RelativeLayout) this.mView.findViewById(R.id.relativeLayout);
        this.iv_select = (ImageView) this.mView.findViewById(R.id.iv_select);
        this.tv_title = (TextView) this.mView.findViewById(R.id.tv_title);
        this.relativeLayout.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnItemClickListener onItemClickListener;
        if (view.getId() == R.id.relativeLayout && (onItemClickListener = this.mOnItemClickListener) != null) {
            onItemClickListener.onClick(this, this.isSelect);
        }
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
        if (z) {
            this.iv_select.setImageResource(R.drawable.dz_radio_set_list_selected);
        } else {
            this.iv_select.setImageResource(R.drawable.dz_radio_set_list_not_selected);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
