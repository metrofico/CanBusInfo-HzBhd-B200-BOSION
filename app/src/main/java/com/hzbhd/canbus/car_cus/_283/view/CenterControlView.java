package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car._283.UiMgr;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class CenterControlView extends LinearLayout implements View.OnClickListener {
    private BtnView ac;
    private BtnView add;
    private BtnView auto_cycle;
    private ExecutorService executorService;
    private ImageView image_wind_1;
    private ImageView image_wind_2;
    private ImageView image_wind_3;
    private ImageView image_wind_4;
    private ImageView image_wind_5;
    private ImageView image_wind_6;
    private ImageView image_wind_7;
    private List<ImageView> listWinds;
    private Context mContext;
    private View mView;
    private BtnView sub;
    private BtnView wind_down;
    private BtnView wind_up;
    private BtnView wind_windows;

    public CenterControlView(Context context) {
        this(context, null);
    }

    public CenterControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CenterControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listWinds = new ArrayList();
        this.executorService = Executors.newSingleThreadExecutor();
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_air_center_control, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.add = (BtnView) this.mView.findViewById(R.id.btn_wind_add);
        this.sub = (BtnView) this.mView.findViewById(R.id.btn_wind_sub);
        this.ac = (BtnView) this.mView.findViewById(R.id.btn_ac);
        this.wind_windows = (BtnView) this.mView.findViewById(R.id.btn_wind_windows);
        this.wind_up = (BtnView) this.mView.findViewById(R.id.btn_wind_up);
        this.wind_down = (BtnView) this.mView.findViewById(R.id.btn_wind_down);
        this.auto_cycle = (BtnView) this.mView.findViewById(R.id.btn_auto_cycle);
        this.image_wind_1 = (ImageView) this.mView.findViewById(R.id.image_wind_1);
        this.image_wind_2 = (ImageView) this.mView.findViewById(R.id.image_wind_2);
        this.image_wind_3 = (ImageView) this.mView.findViewById(R.id.image_wind_3);
        this.image_wind_4 = (ImageView) this.mView.findViewById(R.id.image_wind_4);
        this.image_wind_5 = (ImageView) this.mView.findViewById(R.id.image_wind_5);
        this.image_wind_6 = (ImageView) this.mView.findViewById(R.id.image_wind_6);
        this.image_wind_7 = (ImageView) this.mView.findViewById(R.id.image_wind_7);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.CenterControlView.1
            @Override // java.lang.Runnable
            public void run() {
                CenterControlView.this.listWinds.add(CenterControlView.this.image_wind_7);
                CenterControlView.this.listWinds.add(CenterControlView.this.image_wind_6);
                CenterControlView.this.listWinds.add(CenterControlView.this.image_wind_5);
                CenterControlView.this.listWinds.add(CenterControlView.this.image_wind_4);
                CenterControlView.this.listWinds.add(CenterControlView.this.image_wind_3);
                CenterControlView.this.listWinds.add(CenterControlView.this.image_wind_2);
                CenterControlView.this.listWinds.add(CenterControlView.this.image_wind_1);
            }
        });
        this.add.setOnClickListener(this);
        this.sub.setOnClickListener(this);
        this.ac.setOnClickListener(this);
        this.wind_windows.setOnClickListener(this);
        this.wind_up.setOnClickListener(this);
        this.wind_down.setOnClickListener(this);
        this.auto_cycle.setOnClickListener(this);
    }

    public void refreshUi() {
        this.ac.setSelect(GeneralAirData.ac);
        this.auto_cycle.setSelect(GeneralAirData.in_out_cycle);
        setFrontWind(GeneralDzData.air_front_wind);
        setWindPower(GeneralAirData.front_wind_level);
    }

    private void setWindPower(int i) {
        for (int i2 = 1; i2 <= this.listWinds.size(); i2++) {
            if (i2 <= i) {
                this.listWinds.get(i2 - 1).setVisibility(0);
            } else {
                this.listWinds.get(i2 - 1).setVisibility(4);
            }
        }
    }

    private void setFrontWind(int i) {
        if (i == 0 || i == 1 || i == 2) {
            this.wind_windows.setSelect(false);
            this.wind_up.setSelect(false);
            this.wind_down.setSelect(false);
        }
        if (i == 3) {
            this.wind_windows.setSelect(false);
            this.wind_up.setSelect(false);
            this.wind_down.setSelect(true);
            return;
        }
        if (i == 5) {
            this.wind_windows.setSelect(false);
            this.wind_up.setSelect(true);
            this.wind_down.setSelect(true);
            return;
        }
        if (i == 6) {
            this.wind_windows.setSelect(false);
            this.wind_up.setSelect(true);
            this.wind_down.setSelect(false);
            return;
        }
        switch (i) {
            case 11:
                this.wind_windows.setSelect(true);
                this.wind_up.setSelect(false);
                this.wind_down.setSelect(false);
                break;
            case 12:
                this.wind_windows.setSelect(true);
                this.wind_up.setSelect(false);
                this.wind_down.setSelect(true);
                break;
            case 13:
                this.wind_windows.setSelect(true);
                this.wind_up.setSelect(true);
                this.wind_down.setSelect(false);
                break;
            case 14:
                this.wind_windows.setSelect(true);
                this.wind_up.setSelect(true);
                this.wind_down.setSelect(true);
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ac) {
            sendMsg((byte) 15, GeneralAirData.ac);
            return;
        }
        if (id != R.id.btn_auto_cycle) {
            switch (id) {
                case R.id.btn_wind_add /* 2131362067 */:
                    UiMgr.addWind();
                    break;
                case R.id.btn_wind_down /* 2131362068 */:
                    sendMsg((byte) 25, GeneralDzData.fornt_left_blow_foot);
                    if (!GeneralDzData.fornt_left_blow_foot) {
                        if (!GeneralDzData.fornt_left_blow_head) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 1});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
                            break;
                        }
                    } else if (!GeneralDzData.fornt_left_blow_head) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
                        break;
                    }
                    break;
                case R.id.btn_wind_sub /* 2131362069 */:
                    UiMgr.subWind();
                    break;
                case R.id.btn_wind_up /* 2131362070 */:
                    sendMsg((byte) 24, GeneralDzData.fornt_left_blow_head);
                    if (!GeneralDzData.fornt_left_blow_head) {
                        if (!GeneralDzData.fornt_left_blow_foot) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 2});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
                            break;
                        }
                    } else if (!GeneralDzData.fornt_left_blow_foot) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
                        break;
                    }
                    break;
                case R.id.btn_wind_windows /* 2131362071 */:
                    sendMsg((byte) 26, GeneralDzData.fornt_left_blow_window);
                    break;
            }
            return;
        }
        sendMsg((byte) 19, GeneralAirData.in_out_cycle);
    }

    private void sendMsg(byte b, boolean z) {
        MessageSender.sendMsg(new byte[]{22, 58, b, (byte) (!z ? 1 : 0)});
    }
}
