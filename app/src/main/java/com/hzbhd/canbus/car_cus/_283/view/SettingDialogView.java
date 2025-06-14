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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import java.util.List;

/* loaded from: classes2.dex */
public class SettingDialogView extends RelativeLayout implements View.OnClickListener {
    protected int id;
    private ImageView iv_title;
    private View lineView;
    protected List<SettingDialogBean> lists;
    public Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private View mView;
    private RelativeLayout relativeLayout;
    private ImageView rightImageView;
    protected LinearLayout rightLinear;
    private TextView tv_title;
    private TextView tv_value;

    public interface OnItemClickListener {
        void onClick(View view, int i);
    }

    public SettingDialogView(Context context) {
        this(context, null);
    }

    public SettingDialogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingDialogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_setting_dialog_view, (ViewGroup) this, true);
        initView();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SettingSelectStyle);
        String string = typedArrayObtainStyledAttributes.getString(0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        if (drawable != null) {
            this.iv_title.setVisibility(0);
            this.iv_title.setImageDrawable(drawable);
        } else {
            this.iv_title.setVisibility(8);
            this.iv_title.setImageDrawable(null);
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.tv_title.setText(string);
    }

    private void initView() {
        this.relativeLayout = (RelativeLayout) this.mView.findViewById(R.id.relativeLayout);
        this.rightLinear = (LinearLayout) this.mView.findViewById(R.id.rightLinear);
        this.tv_title = (TextView) this.mView.findViewById(R.id.tv_title);
        this.tv_value = (TextView) this.mView.findViewById(R.id.tv_value);
        this.iv_title = (ImageView) this.mView.findViewById(R.id.iv_title);
        this.rightImageView = (ImageView) this.mView.findViewById(R.id.rightImageView);
        this.lineView = this.mView.findViewById(R.id.lineView);
        this.relativeLayout.setOnClickListener(this);
    }

    public void setList(List<SettingDialogBean> list) {
        this.lists = list;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                this.id = i;
                setTextValue(i, list.get(i).getValue());
                return;
            }
        }
    }

    public void setListFirst(List<SettingDialogBean> list) {
        this.lists = list;
        this.id = 0;
        setTextValue(0, list.get(0).getValue());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.relativeLayout && this.lists != null) {
            setSelect();
            DialogUtils.showSettingDialog(this.mContext, this.rightLinear, this.lists, this.id, new SettingDialogAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.SettingDialogView.1
                @Override // com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter.OnItemClickListener
                public void onClick(int i) {
                    SettingDialogView.this.id = i;
                    if (SettingDialogView.this.mOnItemClickListener != null) {
                        SettingDialogView.this.mOnItemClickListener.onClick(SettingDialogView.this, i);
                    }
                }
            });
        }
    }

    protected void setSelect() {
        for (int i = 0; i < this.lists.size(); i++) {
            if (i == this.id) {
                this.lists.get(i).setSelect(true);
            } else {
                this.lists.get(i).setSelect(false);
            }
        }
    }

    public void setSelect(int i) {
        this.id = i;
        List<SettingDialogBean> list = this.lists;
        if (list == null || list.size() <= 0 || i < 0 || i >= this.lists.size()) {
            return;
        }
        setTextValue(i, this.lists.get(i).getValue());
    }

    public void setSelectOnValue(String str) {
        List<SettingDialogBean> list = this.lists;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.lists.size(); i++) {
            if (this.lists.get(i).getValue().equals(str)) {
                this.id = i;
                setTextValue(i, this.lists.get(i).getValue());
                return;
            }
        }
    }

    private void setTextValue(int i, String str) {
        this.tv_value.setText(str + this.lists.get(i).getUnit());
    }

    public void setEnable(boolean z) {
        if (z) {
            this.tv_title.setTextColor(this.mContext.getColor(R.color.white));
            this.tv_value.setTextColor(this.mContext.getColor(R.color.white));
            this.rightImageView.setImageResource(R.drawable.dz_radio_set_list_triangle);
            this.lineView.setBackgroundResource(R.drawable.dz_radio_set_list_dividing_line);
        } else {
            this.tv_title.setTextColor(this.mContext.getColor(R.color._283_color_gray));
            this.tv_value.setTextColor(this.mContext.getColor(R.color._283_color_gray));
            this.rightImageView.setImageResource(R.drawable.dz_radio_setting_triangle_d);
            this.lineView.setBackgroundResource(R.drawable.dz_radio_setting_line_d);
        }
        this.relativeLayout.setEnabled(z);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
