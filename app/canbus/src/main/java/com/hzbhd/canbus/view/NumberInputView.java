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

/* loaded from: classes2.dex */
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
    private Context mContext;
    private OnOkClickListener mOnOkClickListener;
    private View mView;
    private int maxLength;
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
        this.mView = LayoutInflater.from(context).inflate(R.layout.view_number_input, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.et_number = (EditText) this.mView.findViewById(R.id.et_number);
        this.btn_0 = (Button) this.mView.findViewById(R.id.btn_0);
        this.btn_1 = (Button) this.mView.findViewById(R.id.btn_1);
        this.btn_2 = (Button) this.mView.findViewById(R.id.btn_2);
        this.btn_3 = (Button) this.mView.findViewById(R.id.btn_3);
        this.btn_4 = (Button) this.mView.findViewById(R.id.btn_4);
        this.btn_5 = (Button) this.mView.findViewById(R.id.btn_5);
        this.btn_6 = (Button) this.mView.findViewById(R.id.btn_6);
        this.btn_7 = (Button) this.mView.findViewById(R.id.btn_7);
        this.btn_8 = (Button) this.mView.findViewById(R.id.btn_8);
        this.btn_9 = (Button) this.mView.findViewById(R.id.btn_9);
        this.btn_ok = (Button) this.mView.findViewById(R.id.btn_ok);
        this.btn_del = (Button) this.mView.findViewById(R.id.btn_del);
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
            public final boolean onLongClick(View view) {
                return this.f$0.m1180lambda$initView$0$comhzbhdcanbusviewNumberInputView(view);
            }
        });
    }

    /* renamed from: lambda$initView$0$com-hzbhd-canbus-view-NumberInputView, reason: not valid java name */
    /* synthetic */ boolean m1180lambda$initView$0$comhzbhdcanbusviewNumberInputView(View view) {
        this.text = "";
        setEtText();
        return true;
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
            if (this.text.length() > 0) {
                this.text = this.text.substring(0, r4.length() - 1);
                setEtText();
                return;
            }
            return;
        }
        if (id != R.id.btn_ok) {
            switch (id) {
                case R.id.btn_0 /* 2131361994 */:
                    setInputText("0");
                    break;
                case R.id.btn_1 /* 2131361995 */:
                    setInputText("1");
                    break;
                case R.id.btn_2 /* 2131361996 */:
                    setInputText("2");
                    break;
                case R.id.btn_3 /* 2131361997 */:
                    setInputText("3");
                    break;
                case R.id.btn_4 /* 2131361998 */:
                    setInputText("4");
                    break;
                case R.id.btn_5 /* 2131361999 */:
                    setInputText("5");
                    break;
                case R.id.btn_6 /* 2131362000 */:
                    setInputText("6");
                    break;
                case R.id.btn_7 /* 2131362001 */:
                    setInputText("7");
                    break;
                case R.id.btn_8 /* 2131362002 */:
                    setInputText("8");
                    break;
                case R.id.btn_9 /* 2131362003 */:
                    setInputText("9");
                    break;
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
                Toast.makeText(this.mContext, R.string.smart_input_pwd_fail, 0).show();
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
        Toast.makeText(this.mContext, R.string.smart_input_num, 0).show();
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
