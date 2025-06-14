package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;

/* loaded from: classes2.dex */
public class AirSeatView {
    private static final int LEFT_FRONT_HOT = 3;
    private static final int LEFT_FRONT_VENTILATE = 1;
    private static final int RIGHT_FRONT_HOT = 4;
    private static final int RIGHT_FRONT_VENTILATE = 2;
    private BtnView btn_left_front_hot_1;
    private BtnView btn_left_front_hot_2;
    private BtnView btn_left_front_hot_3;
    private BtnView btn_left_front_ventilate_1;
    private BtnView btn_left_front_ventilate_2;
    private BtnView btn_left_front_ventilate_3;
    private BtnView btn_right_front_hot_1;
    private BtnView btn_right_front_hot_2;
    private BtnView btn_right_front_hot_3;
    private BtnView btn_right_front_ventilate_1;
    private BtnView btn_right_front_ventilate_2;
    private BtnView btn_right_front_ventilate_3;
    private Context mContext;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private WindowManager mWindowManager;
    private TextView tv_left_front_hot;
    private TextView tv_left_front_ventilate;
    private TextView tv_right_front_hot;
    private TextView tv_right_front_ventilate;
    private boolean isShowing = false;
    private Runnable mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.AirSeatView.1
        @Override // java.lang.Runnable
        public void run() {
            AirSeatView.this.dismissView();
        }
    };

    public AirSeatView(Context context) {
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_air_seat_view, (ViewGroup) null);
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        initView();
    }

    private void initView() {
        this.tv_left_front_ventilate = (TextView) this.mView.findViewById(R.id.tv_left_front_ventilate);
        this.tv_right_front_ventilate = (TextView) this.mView.findViewById(R.id.tv_right_front_ventilate);
        this.tv_left_front_hot = (TextView) this.mView.findViewById(R.id.tv_left_front_hot);
        this.tv_right_front_hot = (TextView) this.mView.findViewById(R.id.tv_right_front_hot);
        this.btn_left_front_ventilate_1 = (BtnView) this.mView.findViewById(R.id.btn_left_front_ventilate_1);
        this.btn_left_front_ventilate_2 = (BtnView) this.mView.findViewById(R.id.btn_left_front_ventilate_2);
        this.btn_left_front_ventilate_3 = (BtnView) this.mView.findViewById(R.id.btn_left_front_ventilate_3);
        this.btn_right_front_ventilate_1 = (BtnView) this.mView.findViewById(R.id.btn_right_front_ventilate_1);
        this.btn_right_front_ventilate_2 = (BtnView) this.mView.findViewById(R.id.btn_right_front_ventilate_2);
        this.btn_right_front_ventilate_3 = (BtnView) this.mView.findViewById(R.id.btn_right_front_ventilate_3);
        this.btn_left_front_hot_1 = (BtnView) this.mView.findViewById(R.id.btn_left_front_hot_1);
        this.btn_left_front_hot_2 = (BtnView) this.mView.findViewById(R.id.btn_left_front_hot_2);
        this.btn_left_front_hot_3 = (BtnView) this.mView.findViewById(R.id.btn_left_front_hot_3);
        this.btn_right_front_hot_1 = (BtnView) this.mView.findViewById(R.id.btn_right_front_hot_1);
        this.btn_right_front_hot_2 = (BtnView) this.mView.findViewById(R.id.btn_right_front_hot_2);
        this.btn_right_front_hot_3 = (BtnView) this.mView.findViewById(R.id.btn_right_front_hot_3);
        setClick(1, this.tv_left_front_ventilate, this.btn_left_front_ventilate_1, this.btn_left_front_ventilate_2, this.btn_left_front_ventilate_3);
        setClick(2, this.tv_right_front_ventilate, this.btn_right_front_ventilate_1, this.btn_right_front_ventilate_2, this.btn_right_front_ventilate_3);
        setClick(3, this.tv_left_front_hot, this.btn_left_front_hot_1, this.btn_left_front_hot_2, this.btn_left_front_hot_3);
        setClick(4, this.tv_right_front_hot, this.btn_right_front_hot_1, this.btn_right_front_hot_2, this.btn_right_front_hot_3);
    }

    public void refreshUi() {
        setGrade(GeneralDzData.left_front_ventilate, this.tv_left_front_ventilate, this.btn_left_front_ventilate_1, this.btn_left_front_ventilate_2, this.btn_left_front_ventilate_3);
        setGrade(GeneralDzData.right_front_ventilate, this.tv_right_front_ventilate, this.btn_right_front_ventilate_1, this.btn_right_front_ventilate_2, this.btn_right_front_ventilate_3);
        setGrade(GeneralDzData.left_front_hot, this.tv_left_front_hot, this.btn_left_front_hot_1, this.btn_left_front_hot_2, this.btn_left_front_hot_3);
        setGrade(GeneralDzData.right_front_hot, this.tv_right_front_hot, this.btn_right_front_hot_1, this.btn_right_front_hot_2, this.btn_right_front_hot_3);
        addViewToWindow();
    }

    private void setGrade(int i, TextView textView, BtnView btnView, BtnView btnView2, BtnView btnView3) {
        if (i == 0) {
            textView.setText(R.string._283_OFF);
            btnView.setSelect(false);
            btnView2.setSelect(false);
            btnView3.setSelect(false);
            return;
        }
        if (i == 1) {
            textView.setText(R.string._283_ON);
            btnView.setSelect(true);
            btnView2.setSelect(false);
            btnView3.setSelect(false);
            return;
        }
        if (i == 2) {
            textView.setText(R.string._283_ON);
            btnView.setSelect(true);
            btnView2.setSelect(true);
            btnView3.setSelect(false);
            return;
        }
        if (i != 3) {
            return;
        }
        textView.setText(R.string._283_ON);
        btnView.setSelect(true);
        btnView2.setSelect(true);
        btnView3.setSelect(true);
    }

    private void setClick(final int i, TextView textView, BtnView btnView, BtnView btnView2, BtnView btnView3) {
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.AirSeatView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AirSeatView.lambda$setClick$0(i, view);
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.AirSeatView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AirSeatView.lambda$setClick$1(i, view);
            }
        });
        btnView2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.AirSeatView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AirSeatView.lambda$setClick$2(i, view);
            }
        });
        btnView3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.AirSeatView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AirSeatView.lambda$setClick$3(i, view);
            }
        });
    }

    static /* synthetic */ void lambda$setClick$0(int i, View view) {
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 0});
            return;
        }
        if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 0});
        } else if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 0});
        } else {
            if (i != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 0});
        }
    }

    static /* synthetic */ void lambda$setClick$1(int i, View view) {
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 1});
            return;
        }
        if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 1});
        } else if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 1});
        } else {
            if (i != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 1});
        }
    }

    static /* synthetic */ void lambda$setClick$2(int i, View view) {
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 2});
            return;
        }
        if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 2});
        } else if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 2});
        } else {
            if (i != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 2});
        }
    }

    static /* synthetic */ void lambda$setClick$3(int i, View view) {
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 3});
            return;
        }
        if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 3});
        } else if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 3});
        } else {
            if (i != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 3});
        }
    }

    private void addViewToWindow() {
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2038;
            this.mLayoutParams.gravity = 80;
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = -2;
            this.mLayoutParams.format = 1;
            this.mLayoutParams.flags = 8;
        }
        if (!this.isShowing) {
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mView.removeCallbacks(this.mRunnable);
        this.mView.postDelayed(this.mRunnable, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        View view;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (view = this.mView) == null) {
            return;
        }
        windowManager.removeView(view);
        this.isShowing = false;
    }
}
