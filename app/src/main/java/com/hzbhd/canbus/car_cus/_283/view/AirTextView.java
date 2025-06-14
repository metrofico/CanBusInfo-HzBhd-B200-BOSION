package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class AirTextView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "_283_AirTextView";
    private BtnView addBtnView;
    private Context mContext;
    private OnClickListener mOnClickListener;
    private View mView;
    private MyTextView myTextView;
    private BtnView subBtnView;

    public interface OnClickListener {
        void add();

        void sub();
    }

    public AirTextView(Context context) {
        this(context, null);
    }

    public AirTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AirTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_air_textview, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.myTextView = (MyTextView) this.mView.findViewById(R.id.myTextView);
        this.subBtnView = (BtnView) this.mView.findViewById(R.id.subBtnView);
        this.addBtnView = (BtnView) this.mView.findViewById(R.id.addBtnView);
        this.subBtnView.setOnClickListener(this);
        this.addBtnView.setOnClickListener(this);
    }

    public void setText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.myTextView.setText(str);
        } else {
            Log.d(TAG, "设置的文本是空值");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnClickListener onClickListener;
        int id = view.getId();
        if (id != R.id.addBtnView) {
            if (id == R.id.subBtnView && (onClickListener = this.mOnClickListener) != null) {
                onClickListener.sub();
                return;
            }
            return;
        }
        OnClickListener onClickListener2 = this.mOnClickListener;
        if (onClickListener2 != null) {
            onClickListener2.add();
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
