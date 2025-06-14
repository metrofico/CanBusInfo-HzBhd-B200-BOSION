package com.hzbhd.canbus.car._379;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class TypeInView implements View.OnClickListener {
    static LinearLayout add_page1;
    static LinearLayout add_page2;
    static LinearLayout enter_code;
    static LinearLayout erase_page1;
    static LinearLayout erase_page2;
    static LinearLayout error_code;
    static LinearLayout switch_add_erase;
    EditText Add_page1_edit1;
    EditText Add_page1_edit2;
    EditText Factory_code;
    Button add_page1_enter;
    Button add_page2_close;
    int[] array = new int[5];
    int[] array1 = new int[5];
    int[] array2 = new int[5];
    Activity currentActivity;
    AlertDialog dialog;
    Button enter_code_enter_btn;
    Button enter_code_return_btn;
    Button erase_page1_no;
    Button erase_page1_yes;
    Button erase_page2_close;
    Button error_code_return_btn;
    Context mContext;
    private MsgMgr msgMgr;
    Button switch_add_btn;
    Button switch_cancel;
    Button switch_erase_btn;

    public void showDialog(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._type_ln, (ViewGroup) null, true);
        this.dialog = new AlertDialog.Builder(context).setView(viewInflate).create();
        initView(viewInflate);
        this.mContext = context;
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.setCancelable(false);
        this.dialog.show();
    }

    private void TypeInFactory() {
        String string = this.Factory_code.getText().toString();
        int i = 0;
        if (string.length() < 5) {
            Toast.makeText(this.mContext, "Password error!", 0).show();
            return;
        }
        while (true) {
            int[] iArr = this.array;
            if (i >= iArr.length) {
                return;
            }
            int i2 = i + 1;
            iArr[i] = Integer.parseInt(string.substring(i, i2));
            i = i2;
        }
    }

    private void TypeInAdd() {
        String string = this.Add_page1_edit1.getText().toString();
        if (string.length() < 5) {
            Toast.makeText(this.mContext, "Password error!", 0).show();
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.array1;
            if (i >= iArr.length) {
                return;
            }
            int i2 = i + 1;
            iArr[i] = Integer.parseInt(string.substring(i, i2));
            int[] iArr2 = this.array1;
            int i3 = iArr2[i];
            if (i3 % 2 == 1) {
                iArr2[i] = i3 + 1;
            }
            if (iArr2[i] == 9) {
                iArr2[i] = 0;
            }
            i = i2;
        }
    }

    private void TypeInConfirm() {
        String string = this.Add_page1_edit2.getText().toString();
        if (string.length() < 5) {
            Toast.makeText(this.mContext, "Password error!", 0).show();
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.array2;
            if (i >= iArr.length) {
                return;
            }
            int i2 = i + 1;
            iArr[i] = Integer.parseInt(string.substring(i, i2));
            int[] iArr2 = this.array2;
            int i3 = iArr2[i];
            if (i3 % 2 == 1) {
                iArr2[i] = i3 + 1;
            }
            if (iArr2[i] == 9) {
                iArr2[i] = 0;
            }
            i = i2;
        }
    }

    private void deletePassword() {
        erase_page1.setVisibility(8);
        erase_page2.setVisibility(0);
        CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, 0, 0, 0});
    }

    private void verifyPassword() {
        TypeInFactory();
        int[] iArr = this.array;
        int i = (iArr[0] << 4) | iArr[1];
        int i2 = (iArr[2] << 4) | iArr[3];
        int i3 = (iArr[4] << 4) | 0;
        if (i == 0 && i2 == 0 && i3 == 0) {
            Toast.makeText(this.mContext, "password error！", 0);
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte) i, (byte) i2, (byte) i3});
        }
    }

    public static void toJudge(int i) {
        LinearLayout linearLayout;
        LinearLayout linearLayout2 = switch_add_erase;
        if (linearLayout2 == null || (linearLayout = error_code) == null || enter_code == null) {
            return;
        }
        if (i == 1) {
            linearLayout2.setVisibility(0);
        } else {
            linearLayout.setVisibility(0);
        }
        enter_code.setVisibility(8);
    }

    private void dismiss() {
        this.dialog.dismiss();
    }

    private void initView(View view) {
        enter_code = (LinearLayout) view.findViewById(R.id.enter_code);
        error_code = (LinearLayout) view.findViewById(R.id.Error_code);
        switch_add_erase = (LinearLayout) view.findViewById(R.id.switch_add_erase);
        add_page1 = (LinearLayout) view.findViewById(R.id.Add_page1);
        add_page2 = (LinearLayout) view.findViewById(R.id.Add_page2);
        erase_page1 = (LinearLayout) view.findViewById(R.id.Erase_page1);
        erase_page2 = (LinearLayout) view.findViewById(R.id.Erase_page2);
        Button button = (Button) view.findViewById(R.id.enter_code_enter);
        this.enter_code_enter_btn = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.enter_code_return);
        this.enter_code_return_btn = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) view.findViewById(R.id.Error_code_close);
        this.error_code_return_btn = button3;
        button3.setOnClickListener(this);
        Button button4 = (Button) view.findViewById(R.id.true_code_add);
        this.switch_add_btn = button4;
        button4.setOnClickListener(this);
        Button button5 = (Button) view.findViewById(R.id.true_code_erase);
        this.switch_erase_btn = button5;
        button5.setOnClickListener(this);
        Button button6 = (Button) view.findViewById(R.id.true_code_cancel);
        this.switch_cancel = button6;
        button6.setOnClickListener(this);
        Button button7 = (Button) view.findViewById(R.id.Add_page1_Enter);
        this.add_page1_enter = button7;
        button7.setOnClickListener(this);
        Button button8 = (Button) view.findViewById(R.id.Add_page2_close);
        this.add_page2_close = button8;
        button8.setOnClickListener(this);
        Button button9 = (Button) view.findViewById(R.id.Erase_page1_yes);
        this.erase_page1_yes = button9;
        button9.setOnClickListener(this);
        Button button10 = (Button) view.findViewById(R.id.Erase_page1_no);
        this.erase_page1_no = button10;
        button10.setOnClickListener(this);
        Button button11 = (Button) view.findViewById(R.id.Erase_page2_close);
        this.erase_page2_close = button11;
        button11.setOnClickListener(this);
        this.Factory_code = (EditText) view.findViewById(R.id.Factory_code);
        this.Add_page1_edit1 = (EditText) view.findViewById(R.id.Add_page1_edit1);
        this.Add_page1_edit2 = (EditText) view.findViewById(R.id.Add_page1_edit2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Add_page1_Enter /* 2131361795 */:
                AddPassWard();
                break;
            case R.id.Add_page2_close /* 2131361801 */:
            case R.id.Erase_page2_close /* 2131361816 */:
            case R.id.Error_code_close /* 2131361821 */:
            case R.id.enter_code_return /* 2131362215 */:
                dismiss();
                break;
            case R.id.Erase_page1_no /* 2131361813 */:
                switch_add_erase.setVisibility(0);
                erase_page1.setVisibility(8);
                break;
            case R.id.Erase_page1_yes /* 2131361814 */:
                deletePassword();
                break;
            case R.id.enter_code_enter /* 2131362214 */:
                verifyPassword();
                break;
            case R.id.true_code_add /* 2131363566 */:
                switch_add_erase.setVisibility(8);
                add_page1.setVisibility(0);
                break;
            case R.id.true_code_cancel /* 2131363567 */:
                switch_add_erase.setVisibility(8);
                enter_code.setVisibility(0);
                break;
            case R.id.true_code_erase /* 2131363569 */:
                switch_add_erase.setVisibility(8);
                erase_page1.setVisibility(0);
                break;
        }
    }

    private void AddPassWard() {
        TypeInAdd();
        TypeInConfirm();
        if (Arrays.equals(this.array1, this.array2)) {
            int[] iArr = this.array2;
            int i = (iArr[0] << 4) | iArr[1];
            int i2 = (iArr[2] << 4) | iArr[3];
            int i3 = (iArr[4] << 4) | 0;
            if (i == 0 && i2 == 0 && i3 == 0) {
                Toast.makeText(this.mContext, "The password is too simple", 0);
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte) i, (byte) i2, (byte) i3});
            add_page1.setVisibility(8);
            add_page2.setVisibility(0);
            return;
        }
        Toast.makeText(this.mContext, "Passwords do not match", 0);
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(this.mContext);
        }
        return this.msgMgr;
    }
}
