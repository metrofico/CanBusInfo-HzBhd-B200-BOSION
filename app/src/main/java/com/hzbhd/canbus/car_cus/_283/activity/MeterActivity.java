package com.hzbhd.canbus.car_cus._283.activity;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.MeterChooseView;
import com.hzbhd.canbus.car_cus._283.view.MeterDialColorView;
import com.hzbhd.canbus.car_cus._283.view.MeterPointerColorView;
import com.hzbhd.canbus.car_cus._283.view.MeterRotateView;

/* loaded from: classes2.dex */
public class MeterActivity extends AbstractBaseActivity implements View.OnClickListener, MeterRotateView.OnUpDownClickListener {
    private static byte[] bys = {25, 0, 0, 0, 0, 32, 0, 3, 8, 0, 0};
    private AlertDialog alertDialog;
    private AlertDialog alertDialog1;
    private AlertDialog alertDialog2;
    private AlertDialog alertDialog3;
    private TextView functionTv1;
    private TextView functionTv2;
    private TextView functionTv3;
    private MeterRotateView mLeftRotateView;
    private MeterRotateView mRightRotateView;
    private MeterChooseView meterChooseView;
    private MeterChooseView meterChooseView1;
    private MeterDialColorView meterDialColorView;
    private MeterPointerColorView meterPointerColorView;

    private boolean isSame(int i, int i2) {
        return i >= 4 && i <= 7 && i2 >= 4 && i2 <= 7 && i == i2;
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._283_activity_meter);
        MessageSender.sendMsg(new byte[]{25, 0, 0, 0, 0, 32, 0, 3, 8, -1, -1});
        initView();
        initClick();
    }

    private void initView() {
        this.mLeftRotateView = (MeterRotateView) findViewById(R.id.leftRotate);
        this.mRightRotateView = (MeterRotateView) findViewById(R.id.rightRotate);
        this.functionTv1 = (TextView) findViewById(R.id.functionTv1);
        this.functionTv2 = (TextView) findViewById(R.id.functionTv2);
        this.functionTv3 = (TextView) findViewById(R.id.functionTv3);
        this.mLeftRotateView.setLeftShow();
        this.mRightRotateView.setRightShow();
        this.functionTv1.setSelected(true);
        selectedRightRotate(true);
        selectedLeftRotate(true);
    }

    private void initClick() {
        this.functionTv1.setOnClickListener(this);
        this.functionTv2.setOnClickListener(this);
        this.functionTv3.setOnClickListener(this);
        this.mLeftRotateView.setOnUpDownClickListener(this);
        this.mRightRotateView.setOnUpDownClickListener(this);
        setLeftRotateLongClick();
        setRightRotateLongClick();
        refreshUi(null);
    }

    private void setLeftRotateLongClick() {
        this.mLeftRotateView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.MeterActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f$0.m1085x5837e844(view);
            }
        });
    }

    /* renamed from: lambda$setLeftRotateLongClick$1$com-hzbhd-canbus-car_cus-_283-activity-MeterActivity, reason: not valid java name */
    /* synthetic */ boolean m1085x5837e844(View view) {
        if (this.alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.SmartDialog);
            View viewInflate = LayoutInflater.from(this).inflate(R.layout._283_dialog_meter_choose, (ViewGroup) null);
            this.meterChooseView = (MeterChooseView) viewInflate.findViewById(R.id.meterChooseView);
            TextView textView = (TextView) viewInflate.findViewById(R.id.meterChooseTv2);
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.meterChooseTv3);
            TextView textView3 = (TextView) viewInflate.findViewById(R.id.meterChooseTv4);
            builder.setView(viewInflate);
            this.alertDialog = builder.create();
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(R.drawable._a6_meter_choose_3), (Drawable) null, (Drawable) null);
            textView2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(R.drawable._a6_meter_choose_11), (Drawable) null, (Drawable) null);
            textView3.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(R.drawable._a6_meter_choose_10), (Drawable) null, (Drawable) null);
            textView.setText(R.string._283_meter_value_16);
            textView2.setText(R.string._283_meter_value_17);
            textView3.setText(R.string._283_meter_value_18);
            this.meterChooseView.setOnItemClickListener(new MeterChooseView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.MeterActivity$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.car_cus._283.view.MeterChooseView.OnItemClickListener
                public final void itemClick(View view2, int i) {
                    this.f$0.m1084x3e1c69a5(view2, i);
                }
            });
            WindowManager.LayoutParams attributes = this.alertDialog.getWindow().getAttributes();
            attributes.width = (int) getResources().getDimension(R.dimen.dp593);
            this.alertDialog.getWindow().setAttributes(attributes);
        }
        this.alertDialog.show();
        this.meterChooseView.setSelectItem(GeneralDzData.leftRotateInt);
        return true;
    }

    /* renamed from: lambda$setLeftRotateLongClick$0$com-hzbhd-canbus-car_cus-_283-activity-MeterActivity, reason: not valid java name */
    /* synthetic */ void m1084x3e1c69a5(View view, int i) {
        if (isSame(i, GeneralDzData.rightRotateInt)) {
            showTips1();
            return;
        }
        GeneralDzData.leftRotateInt = i;
        selectedLeftRotate(true);
        setLeftRatateIntSend();
        this.alertDialog.dismiss();
    }

    private void setRightRotateLongClick() {
        this.mRightRotateView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.MeterActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f$0.m1087x968df0ad(view);
            }
        });
    }

    /* renamed from: lambda$setRightRotateLongClick$3$com-hzbhd-canbus-car_cus-_283-activity-MeterActivity, reason: not valid java name */
    /* synthetic */ boolean m1087x968df0ad(View view) {
        if (this.alertDialog1 == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.SmartDialog);
            View viewInflate = LayoutInflater.from(this).inflate(R.layout._283_dialog_meter_choose, (ViewGroup) null);
            builder.setView(viewInflate);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.bgImage);
            TextView textView = (TextView) viewInflate.findViewById(R.id.meterChooseTv1);
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.meterChooseTv2);
            TextView textView3 = (TextView) viewInflate.findViewById(R.id.meterChooseTv3);
            TextView textView4 = (TextView) viewInflate.findViewById(R.id.meterChooseTv4);
            this.meterChooseView1 = (MeterChooseView) viewInflate.findViewById(R.id.meterChooseView);
            AlertDialog alertDialogCreate = builder.create();
            this.alertDialog1 = alertDialogCreate;
            alertDialogCreate.show();
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(R.drawable._a6_meter_choose_2), (Drawable) null, (Drawable) null);
            textView2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(R.drawable._a6_meter_choose_6), (Drawable) null, (Drawable) null);
            textView3.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(R.drawable._a6_meter_choose_12), (Drawable) null, (Drawable) null);
            textView4.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getDrawable(R.drawable._a6_meter_choose_7), (Drawable) null, (Drawable) null);
            textView.setText(R.string._283_meter_value_22);
            textView2.setText(R.string._283_meter_value_6);
            textView3.setText(R.string._283_meter_value_19);
            textView4.setText(R.string._283_meter_value_17);
            this.meterChooseView1.setSelectItem(GeneralDzData.rightRotateInt);
            this.meterChooseView1.setOnItemClickListener(new MeterChooseView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.MeterActivity$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car_cus._283.view.MeterChooseView.OnItemClickListener
                public final void itemClick(View view2, int i) {
                    this.f$0.m1086x7c72720e(view2, i);
                }
            });
            imageView.setRotationY(180.0f);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.setMarginStart((int) getResources().getDimension(R.dimen.dp30));
            textView.setLayoutParams(layoutParams);
            WindowManager.LayoutParams attributes = this.alertDialog1.getWindow().getAttributes();
            attributes.width = (int) getResources().getDimension(R.dimen.dp593);
            this.alertDialog1.getWindow().setAttributes(attributes);
        }
        this.alertDialog1.show();
        this.meterChooseView1.setSelectItem(GeneralDzData.rightRotateInt);
        return true;
    }

    /* renamed from: lambda$setRightRotateLongClick$2$com-hzbhd-canbus-car_cus-_283-activity-MeterActivity, reason: not valid java name */
    /* synthetic */ void m1086x7c72720e(View view, int i) {
        if (isSame(i, GeneralDzData.leftRotateInt)) {
            showTips1();
            return;
        }
        GeneralDzData.rightRotateInt = i;
        selectedRightRotate(true);
        setRightRatateIntSend();
        this.alertDialog1.dismiss();
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        selectedDialColor();
        selectedPointerColor();
        this.mLeftRotateView.setSelectLeftImage(GeneralDzData.leftRotateInt);
        this.mRightRotateView.setSelectRightImage(GeneralDzData.rightRotateInt);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.functionTv2 /* 2131362308 */:
                AlertDialog alertDialog = this.alertDialog2;
                if (alertDialog == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.SmartDialog);
                    View viewInflate = LayoutInflater.from(this).inflate(R.layout._283_dialog_meter_pointer_color, (ViewGroup) null);
                    this.meterPointerColorView = (MeterPointerColorView) viewInflate.findViewById(R.id.meterPointerColorView);
                    builder.setView(viewInflate);
                    AlertDialog alertDialogCreate = builder.create();
                    this.alertDialog2 = alertDialogCreate;
                    alertDialogCreate.show();
                    WindowManager.LayoutParams attributes = this.alertDialog2.getWindow().getAttributes();
                    attributes.width = (int) getResources().getDimension(R.dimen.dp821);
                    this.alertDialog2.getWindow().setAttributes(attributes);
                } else {
                    alertDialog.show();
                }
                selectedPointerColor();
                break;
            case R.id.functionTv3 /* 2131362309 */:
                AlertDialog alertDialog2 = this.alertDialog3;
                if (alertDialog2 == null) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this, R.style.SmartDialog);
                    View viewInflate2 = LayoutInflater.from(this).inflate(R.layout._283_dialog_meter_dial_color, (ViewGroup) null);
                    this.meterDialColorView = (MeterDialColorView) viewInflate2.findViewById(R.id.meterDialColorView);
                    builder2.setView(viewInflate2);
                    AlertDialog alertDialogCreate2 = builder2.create();
                    this.alertDialog3 = alertDialogCreate2;
                    alertDialogCreate2.show();
                    WindowManager.LayoutParams attributes2 = this.alertDialog3.getWindow().getAttributes();
                    attributes2.width = (int) getResources().getDimension(R.dimen.dp821);
                    this.alertDialog3.getWindow().setAttributes(attributes2);
                } else {
                    alertDialog2.show();
                }
                selectedDialColor();
                break;
        }
    }

    private void selectedPointerColor() {
        MeterPointerColorView meterPointerColorView = this.meterPointerColorView;
        if (meterPointerColorView != null) {
            meterPointerColorView.setSelectedItem();
        }
    }

    private void selectedDialColor() {
        MeterDialColorView meterDialColorView = this.meterDialColorView;
        if (meterDialColorView != null) {
            meterDialColorView.setSelectedItem(GeneralDzData.light_ambient - 2);
        }
    }

    @Override // com.hzbhd.canbus.car_cus._283.view.MeterRotateView.OnUpDownClickListener
    public void onUp(View view) {
        int id = view.getId();
        if (id == R.id.leftRotate) {
            GeneralDzData.leftRotateInt--;
            selectedLeftRotate(false);
        } else {
            if (id != R.id.rightRotate) {
                return;
            }
            GeneralDzData.rightRotateInt--;
            selectedRightRotate(false);
        }
    }

    @Override // com.hzbhd.canbus.car_cus._283.view.MeterRotateView.OnUpDownClickListener
    public void onDown(View view) {
        int id = view.getId();
        if (id == R.id.leftRotate) {
            GeneralDzData.leftRotateInt++;
            selectedLeftRotate(true);
        } else {
            if (id != R.id.rightRotate) {
                return;
            }
            GeneralDzData.rightRotateInt++;
            selectedRightRotate(true);
        }
    }

    @Override // com.hzbhd.canbus.car_cus._283.view.MeterRotateView.OnUpDownClickListener
    public void onCenter(View view) {
        int id = view.getId();
        if (id == R.id.leftRotate) {
            this.mLeftRotateView.startDisPlay2Animator(true);
            setLeftRatateIntSend();
        } else {
            if (id != R.id.rightRotate) {
                return;
            }
            this.mRightRotateView.startDisPlay2Animator(false);
            setRightRatateIntSend();
        }
    }

    private void setLeftRatateIntSend() {
        bys[9] = (byte) GeneralDzData.leftRotateInt;
        MessageSender.sendMsg(bys);
    }

    private void setRightRatateIntSend() {
        bys[10] = (byte) GeneralDzData.rightRotateInt;
        MessageSender.sendMsg(bys);
    }

    private void selectedLeftRotate(boolean z) {
        if (isSame(GeneralDzData.leftRotateInt, GeneralDzData.rightRotateInt)) {
            showTips1();
            if (z) {
                GeneralDzData.leftRotateInt++;
            } else {
                GeneralDzData.leftRotateInt--;
            }
        }
        int leftImagesLength = this.mLeftRotateView.getLeftImagesLength() - 1;
        if (GeneralDzData.leftRotateInt < 0) {
            GeneralDzData.leftRotateInt = leftImagesLength;
        }
        if (GeneralDzData.leftRotateInt > leftImagesLength) {
            GeneralDzData.leftRotateInt = 0;
        }
        this.mLeftRotateView.setSelectLeftImage(GeneralDzData.leftRotateInt);
    }

    private void selectedRightRotate(boolean z) {
        if (isSame(GeneralDzData.leftRotateInt, GeneralDzData.rightRotateInt)) {
            showTips1();
            if (z) {
                GeneralDzData.rightRotateInt++;
            } else {
                GeneralDzData.rightRotateInt--;
            }
        }
        int rightImagesLength = this.mLeftRotateView.getRightImagesLength() - 1;
        if (GeneralDzData.rightRotateInt < 0) {
            GeneralDzData.rightRotateInt = rightImagesLength;
        }
        if (GeneralDzData.rightRotateInt > rightImagesLength) {
            GeneralDzData.rightRotateInt = 0;
        }
        this.mRightRotateView.setSelectRightImage(GeneralDzData.rightRotateInt);
    }

    private void showTips1() {
        Toast.makeText(this, R.string._283_meter_tips_1, 0).show();
    }
}
