package com.hzbhd.canbus.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hzbhd.canbus.R;

public class NumberInputView extends RelativeLayout implements View.OnClickListener {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_del;
    private Button btn_ok;
    private EditText et_number;
    private String firstPassword;
    private boolean isTwoPassword;
    private final Context mContext;
    private OnOkClickListener mOnOkClickListener;
    private final View mView;
    private final int maxLength;
    private String text;

    public interface OnOkClickListener {
        void click(String str);
    }

    public NumberInputView(Context context) {
        this(context, null);
    }

    public NumberInputView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NumberInputView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.text = "";
        this.maxLength = 18;
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout.view_number_input, this, true);
        initView();
    }

    private void initView() {
        this.et_number = this.mView.findViewById(R.id.et_number);
        this.btn_0 = this.mView.findViewById(R.id.btn_0);
        this.btn_1 = this.mView.findViewById(R.id.btn_1);
        this.btn_2 = this.mView.findViewById(R.id.btn_2);
        this.btn_3 = this.mView.findViewById(R.id.btn_3);
        this.btn_4 = this.mView.findViewById(R.id.btn_4);
        this.btn_5 = this.mView.findViewById(R.id.btn_5);
        this.btn_6 = this.mView.findViewById(R.id.btn_6);
        this.btn_7 = this.mView.findViewById(R.id.btn_7);
        this.btn_8 = this.mView.findViewById(R.id.btn_8);
        this.btn_9 = this.mView.findViewById(R.id.btn_9);
        this.btn_ok = this.mView.findViewById(R.id.btn_ok);
        this.btn_del = this.mView.findViewById(R.id.btn_del);
        this.btn_0.setOnClickListener(this);
        this.btn_1.setOnClickListener(this);
        this.btn_2.setOnClickListener(this);
        this.btn_3.setOnClickListener(this);
        this.btn_4.setOnClickListener(this);
        this.btn_5.setOnClickListener(this);
        this.btn_6.setOnClickListener(this);
        this.btn_7.setOnClickListener(this);
        this.btn_8.setOnClickListener(this);
        this.btn_9.setOnClickListener(this);
        this.btn_ok.setOnClickListener(this);
        this.btn_del.setOnClickListener(this);
        this.btn_del.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.view.NumberInputView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                text = "";
                setEtText();
                return true;
            }
        });
    }


    public void setBtnImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        this.btn_0.setBackgroundResource(i);
        this.btn_1.setBackgroundResource(i2);
        this.btn_2.setBackgroundResource(i3);
        this.btn_3.setBackgroundResource(i4);
        this.btn_4.setBackgroundResource(i5);
        this.btn_5.setBackgroundResource(i6);
        this.btn_6.setBackgroundResource(i7);
        this.btn_7.setBackgroundResource(i8);
        this.btn_8.setBackgroundResource(i9);
        this.btn_9.setBackgroundResource(i10);
        this.btn_ok.setBackgroundResource(i11);
        this.btn_del.setBackgroundResource(i12);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_del) {
            if (!this.text.isEmpty()) {
                this.text = this.text.substring(0, this.text.length() - 1);
                setEtText();
                return;
            }
            return;
        }
        if (id != R.id.btn_ok) {
            if (id == R.id.btn_0) {
                setInputText("0");
            } else if (id == R.id.btn_1) {
                setInputText("1");
            } else if (id == R.id.btn_2) {
                setInputText("2");
            } else if (id == R.id.btn_3) {
                setInputText("3");
            } else if (id == R.id.btn_4) {
                setInputText("4");
            } else if (id == R.id.btn_5) {
                setInputText("5");
            } else if (id == R.id.btn_6) {
                setInputText("6");
            } else if (id == R.id.btn_7) {
                setInputText("7");
            } else if (id == R.id.btn_8) {
                setInputText("8");
            } else if (id == R.id.btn_9) {
                setInputText("9");
            }
            return;
        }
        if (this.isTwoPassword) {
            this.isTwoPassword = false;
            if (!TextUtils.isEmpty(this.firstPassword) && !TextUtils.isEmpty(this.text) && this.firstPassword.equals(this.text)) {
                this.et_number.setText("");
                this.text = "";
                this.et_number.setHint(R.string.smart_input_num);
            } else {
                Toast.makeText(this.mContext, R.string.smart_input_pwd_fail, Toast.LENGTH_SHORT).show();
            }
        }
        if (!TextUtils.isEmpty(this.text)) {
            OnOkClickListener onOkClickListener = this.mOnOkClickListener;
            if (onOkClickListener != null) {
                onOkClickListener.click(this.text);
                return;
            }
            return;
        }
        Toast.makeText(this.mContext, R.string.smart_input_num, Toast.LENGTH_SHORT).show();
    }

    private void setInputText(String str) {
        if (this.text.length() < this.maxLength) {
            this.text += str;
            setEtText();
        }
    }

    private void setEtText() {
        String str = this.text;
        if (str != null) {
            this.et_number.setText(str);
        }
    }

    public void setOnOkClickListener(boolean z, String str, OnOkClickListener onOkClickListener) {
        this.isTwoPassword = z;
        this.firstPassword = str;
        this.mOnOkClickListener = onOkClickListener;
    }
}