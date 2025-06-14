package com.hzbhd.canbus.car_cus._439.air.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._439.air.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._439.air.Interface.AirInfoObserver;
import com.hzbhd.canbus.car_cus._439.air.data.AirData;
import com.hzbhd.canbus.car_cus._439.air.observer.AirBuilder;
import com.hzbhd.canbus.car_cus._439.air.util.AirTimer;
import com.hzbhd.canbus.car_cus._439.air.util.SharedTag;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;

/* loaded from: classes2.dex */
public class AirView extends LinearLayout implements AirInfoObserver, View.OnClickListener {
    private ImageView ac_img;
    private ActionCallback actionCallback;
    private ConstraintLayout air_page;
    private AirSettingView air_setting_view;
    private boolean autoExitTag;
    private ImageView auto_img;
    private ImageView defog_img;
    private Button exit_img;
    private ImageView heat_img;
    private ImageView in_out_cycle_img;
    private Handler mHandler;
    private int modeTag;
    private ImageView power_img;
    private ImageView setting_icon;
    private LinearLayout setting_view;
    private ImageView temp_down;
    private TextView temp_txt;
    private ImageView temp_up;
    private View view;
    private ImageView win_down_img;
    private ImageView win_up_img;
    private WindModeView windModeView;
    private ImageView wind_level_img;

    public AirView(Context context) {
        this(context, null);
    }

    public AirView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AirView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.autoExitTag = true;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.9
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 255) {
                    return;
                }
                AirView.this.updateUi();
            }
        };
        this.modeTag = 0;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._439_air_view, (ViewGroup) this, true);
        this.view = viewInflate;
        Button button = (Button) viewInflate.findViewById(R.id.exit_img);
        this.exit_img = button;
        button.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.setting_view);
        this.setting_view = linearLayout;
        linearLayout.setVisibility(8);
        ImageView imageView = (ImageView) this.view.findViewById(R.id.setting_icon);
        this.setting_icon = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirView.this.resetTimer();
                if (AirView.this.setting_view.getVisibility() == 8) {
                    AirView.this.setting_view.setVisibility(0);
                } else {
                    AirView.this.setting_view.setVisibility(8);
                }
            }
        });
        this.setting_view.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirView.this.resetTimer();
                ((InputMethodManager) AirView.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(AirView.this.view.getWindowToken(), 0);
                AirView.this.setting_view.setVisibility(8);
            }
        });
        AirSettingView airSettingView = (AirSettingView) this.view.findViewById(R.id.air_setting_view);
        this.air_setting_view = airSettingView;
        airSettingView.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.3
            @Override // com.hzbhd.canbus.car_cus._439.air.Interface.ActionCallback
            public void toDo(Object obj) {
                AirView.this.resetTimer();
            }
        });
        initAirInfoView();
        initSetting();
    }

    public void initSetting() {
        this.air_setting_view.resetTxt();
        this.setting_view.setVisibility(8);
        if (SharePreUtil.getBoolValue(getContext(), SharedTag.KEY_AIR_SETTING_TAG, false)) {
            this.setting_icon.setVisibility(0);
        } else {
            this.setting_icon.setVisibility(8);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        AirBuilder.getInstance().register(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void getAction(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    private void initAirInfoView() {
        this.win_down_img = (ImageView) this.view.findViewById(R.id.win_down_img);
        this.win_up_img = (ImageView) this.view.findViewById(R.id.win_up_img);
        this.temp_txt = (TextView) this.view.findViewById(R.id.temp_txt);
        this.wind_level_img = (ImageView) this.view.findViewById(R.id.wind_level_img);
        this.power_img = (ImageView) this.view.findViewById(R.id.power_img);
        this.ac_img = (ImageView) this.view.findViewById(R.id.ac_img);
        this.in_out_cycle_img = (ImageView) this.view.findViewById(R.id.in_out_cycle_img);
        this.auto_img = (ImageView) this.view.findViewById(R.id.auto_img);
        this.defog_img = (ImageView) this.view.findViewById(R.id.defog_img);
        this.heat_img = (ImageView) this.view.findViewById(R.id.heat_img);
        this.temp_down = (ImageView) this.view.findViewById(R.id.temp_down);
        this.temp_up = (ImageView) this.view.findViewById(R.id.temp_up);
        this.windModeView = (WindModeView) this.view.findViewById(R.id.windModeView);
        updateUi();
        this.windModeView.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        this.power_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        this.ac_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        this.in_out_cycle_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        this.auto_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        this.defog_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        this.heat_img.setOnClickListener(new AirView$$ExternalSyntheticLambda0(this));
        initTouchEvent();
    }

    private void initTouchEvent() {
        this.win_up_img.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AirView.this.resetTimer();
                if (motionEvent.getAction() == 0) {
                    AirView.this.win_up_img.setBackgroundResource(R.drawable._439_wind_on);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                SendKeyManager.getInstance().playBeep(0);
                AirView.this.win_up_img.setBackgroundResource(R.drawable._439_wind_off);
                if (AirData.wind_level != 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 1, (byte) (AirData.wind_level + 1)});
                }
                return true;
            }
        });
        this.win_down_img.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AirView.this.resetTimer();
                if (motionEvent.getAction() == 0) {
                    AirView.this.win_down_img.setBackgroundResource(R.drawable._439_wind_on);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                SendKeyManager.getInstance().playBeep(0);
                AirView.this.win_down_img.setBackgroundResource(R.drawable._439_wind_off);
                if (AirData.wind_level != 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 1, (byte) (AirData.wind_level - 1), 0});
                }
                return true;
            }
        });
        this.temp_down.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AirView.this.resetTimer();
                if (motionEvent.getAction() == 0) {
                    AirView.this.temp_down.setBackgroundResource(R.drawable._d439_left_b);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                SendKeyManager.getInstance().playBeep(0);
                AirView.this.temp_down.setBackgroundResource(R.drawable._d439_left_w);
                if (AirData.temp > 18) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 3, (byte) (AirData.temp - 1), 0});
                }
                return true;
            }
        });
        this.temp_up.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.7
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AirView.this.resetTimer();
                if (motionEvent.getAction() == 0) {
                    AirView.this.temp_up.setBackgroundResource(R.drawable._d439_right_b);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                SendKeyManager.getInstance().playBeep(0);
                AirView.this.temp_up.setBackgroundResource(R.drawable._d439_right_w);
                if (AirData.temp < 32) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 3, (byte) (AirData.temp + 1), 0});
                }
                return true;
            }
        });
    }

    public void updateUi() {
        if (AirData.temp == 18) {
            this.temp_txt.setText("LO");
        } else if (AirData.temp == 32) {
            this.temp_txt.setText("HI");
        } else {
            this.temp_txt.setText(AirData.temp + "℃");
        }
        if (AirData.wind_level == 0) {
            this.wind_level_img.setBackgroundResource(R.drawable._439_wind_level_0);
        } else if (AirData.wind_level == 1) {
            this.wind_level_img.setBackgroundResource(R.drawable._439_wind_level_1);
        } else if (AirData.wind_level == 2) {
            this.wind_level_img.setBackgroundResource(R.drawable._439_wind_level_2);
        } else if (AirData.wind_level == 3) {
            this.wind_level_img.setBackgroundResource(R.drawable._439_wind_level_3);
        } else if (AirData.wind_level == 4) {
            this.wind_level_img.setBackgroundResource(R.drawable._439_wind_level_4);
        }
        if (AirData.ac) {
            this.ac_img.setBackgroundResource(R.drawable._439_air_ac_on);
        } else {
            this.ac_img.setBackgroundResource(R.drawable._439_air_ac_off);
        }
        if (AirData.in_out_cycle) {
            this.in_out_cycle_img.setBackgroundResource(R.drawable._439_in_out_cycle_n);
        } else {
            this.in_out_cycle_img.setBackgroundResource(R.drawable._439_in_out_cycle_p);
        }
        if (AirData.auto) {
            this.auto_img.setBackgroundResource(R.drawable._439_air_auto_on);
        } else {
            this.auto_img.setBackgroundResource(R.drawable._439_air_auto_off);
        }
        if (AirData.defog) {
            this.defog_img.setBackgroundResource(R.drawable._439_air_defog_on);
        } else {
            this.defog_img.setBackgroundResource(R.drawable._439_air_defog_off);
        }
        if (AirData.heat) {
            this.heat_img.setVisibility(0);
        } else {
            this.heat_img.setVisibility(4);
        }
        if (AirData.warm_level == 0) {
            this.heat_img.setBackgroundResource(R.drawable._439_air_heat_off);
        } else if (AirData.warm_level == 1) {
            this.heat_img.setBackgroundResource(R.drawable.__439__kongt_jiare1_p);
        } else if (AirData.warm_level == 2) {
            this.heat_img.setBackgroundResource(R.drawable.__439__kongt_jiare_p);
        } else if (AirData.warm_level == 3) {
            this.heat_img.setBackgroundResource(R.drawable.__439__kongt_jiare3_p);
        } else if (AirData.warm_level == 4) {
            this.heat_img.setBackgroundResource(R.drawable.__439__kongt_jiare4_p);
        }
        this.windModeView.setWindowWind(AirData.wind_window);
        this.windModeView.setBodyWind(AirData.wind_body);
        this.windModeView.setFootWind(AirData.wind_foot);
        if (AirData.power) {
            this.temp_txt.setBackgroundResource(R.drawable._439_round_on);
            this.power_img.setBackgroundResource(R.drawable._439_power_n);
        } else {
            this.wind_level_img.setBackgroundResource(R.drawable._439_wind_level_0);
            this.temp_txt.setBackgroundResource(R.drawable._439_round_off);
            this.power_img.setBackgroundResource(R.drawable._439_power_p);
            this.ac_img.setBackgroundResource(R.drawable._439_air_ac_off);
            this.auto_img.setBackgroundResource(R.drawable._439_air_auto_off);
            this.in_out_cycle_img.setBackgroundResource(R.drawable._439_in_out_cycle_off);
            this.defog_img.setBackgroundResource(R.drawable._439_air_defog_off);
            this.heat_img.setBackgroundResource(R.drawable._439_air_heat_off);
            this.windModeView.setWindowWind(false);
            this.windModeView.setBodyWind(false);
            this.windModeView.setFootWind(false);
        }
        resetTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetTimer() {
        AirTimer.getInstance().start(AirData.exitTime, new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._439.air.view.AirView.8
            @Override // com.hzbhd.canbus.car_cus._439.air.Interface.ActionCallback
            public void toDo(Object obj) {
                if (!AirView.this.autoExitTag || AirView.this.actionCallback == null) {
                    return;
                }
                AirView.this.actionCallback.toDo("EXIT");
            }
        });
    }

    public void setAutoExit(boolean z) {
        this.autoExitTag = z;
    }

    @Override // com.hzbhd.canbus.car_cus._439.air.Interface.AirInfoObserver
    public void infoChange() {
        if (this.autoExitTag) {
            updateUi();
            return;
        }
        Message message = new Message();
        message.what = 255;
        this.mHandler.sendMessage(message);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        resetTimer();
        switch (view.getId()) {
            case R.id.ac_img /* 2131361842 */:
                if (!AirData.ac) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 5, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 5, 0});
                    break;
                }
            case R.id.auto_img /* 2131361928 */:
                if (!AirData.auto) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 6, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 6, 0});
                    break;
                }
            case R.id.defog_img /* 2131362181 */:
                if (!AirData.defog) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 7, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 7, 0});
                    break;
                }
            case R.id.exit_img /* 2131362219 */:
                ActionCallback actionCallback = this.actionCallback;
                if (actionCallback != null) {
                    actionCallback.toDo("EXIT");
                    break;
                }
                break;
            case R.id.heat_img /* 2131362367 */:
                if (AirData.warm_level != 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 9, (byte) (AirData.warm_level + 1)});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 9, 0});
                    break;
                }
            case R.id.in_out_cycle_img /* 2131362500 */:
                if (!AirData.in_out_cycle) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 4, 0});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 4, 1});
                    break;
                }
            case R.id.power_img /* 2131362990 */:
                if (!AirData.power) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 8, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 8, 0});
                    break;
                }
            case R.id.windModeView /* 2131363789 */:
                int i = this.modeTag;
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                if (i == 4) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 5});
                                    this.modeTag = 0;
                                    break;
                                }
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 4});
                                this.modeTag = 4;
                                break;
                            }
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 3});
                            this.modeTag = 3;
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 2});
                        this.modeTag = 2;
                        break;
                    }
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, 2, 1});
                    this.modeTag = 1;
                    break;
                }
                break;
        }
    }
}
