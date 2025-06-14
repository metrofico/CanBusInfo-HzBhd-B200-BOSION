package com.hzbhd.canbus.car_cus._467.air.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._467.air.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._467.air.Interface.AirInfoObserver;
import com.hzbhd.canbus.car_cus._467.air.data.AirData;
import com.hzbhd.canbus.car_cus._467.air.observer.AirBuilder;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.view.colorview.ColorView;
import com.hzbhd.util.LogUtil;
import java.util.Calendar;
import org.apache.log4j.Priority;

/* loaded from: classes2.dex */
public class AirSettingView extends LinearLayout implements AirInfoObserver {
    int IntEditTimeHour;
    int IntEditTimeMinute;
    private TextView alert;
    private CheckItemView c1;
    private CheckItemView c2;
    private CheckItemView c3;
    private TextView cancel;
    private Context context;
    private TextView currentTimeTitle;
    int hour;
    int minute;
    private View parentView;
    private TextView resultTime;
    private TextView setTime;
    private TextView setTimeOpen;
    private TextView setTimeTitle;
    private FrameLayout settingTime;
    private TextView settingsTimeHour;
    private TextView settingsTimeMin;
    private TextView timeDay;
    private LinearLayout timeDayLine;
    int timeDayStart;
    private ColorView timeToday;
    private ColorView timeTomorrow;
    private TextView title;

    public AirSettingView(Context context) {
        this(context, null);
    }

    public AirSettingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AirSettingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.timeDayStart = 0;
        this.hour = 0;
        this.minute = 0;
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._467_view_air_setting1024x600, (ViewGroup) this, true);
        this.parentView = viewInflate;
        this.alert = (TextView) viewInflate.findViewById(R.id.alert);
        ((LinearLayout) this.parentView.findViewById(R.id.out_view_lin)).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((InputMethodManager) AirSettingView.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        this.resultTime = (TextView) this.parentView.findViewById(R.id.resultTime);
        this.title = (TextView) this.parentView.findViewById(R.id.title);
        this.setTimeTitle = (TextView) this.parentView.findViewById(R.id.setTimeTitle);
        this.currentTimeTitle = (TextView) this.parentView.findViewById(R.id.currentTimeTitle);
        this.setTimeOpen = (TextView) this.parentView.findViewById(R.id.set_time_open);
        this.settingTime = (FrameLayout) this.parentView.findViewById(R.id.setting_time);
        this.timeDay = (TextView) this.parentView.findViewById(R.id.time_day);
        this.timeToday = (ColorView) this.parentView.findViewById(R.id.time_today);
        this.timeTomorrow = (ColorView) this.parentView.findViewById(R.id.time_tomorrow);
        this.c1 = (CheckItemView) this.parentView.findViewById(R.id.c1);
        this.c2 = (CheckItemView) this.parentView.findViewById(R.id.c2);
        this.c3 = (CheckItemView) this.parentView.findViewById(R.id.c3);
        this.timeDayLine = (LinearLayout) this.parentView.findViewById(R.id.time_day_line);
        this.settingsTimeHour = (TextView) this.parentView.findViewById(R.id.settings_time_hour);
        this.settingsTimeMin = (TextView) this.parentView.findViewById(R.id.settings_time_min);
        if (this.timeDayStart == 0) {
            this.timeToday.setSelected(true);
            this.timeTomorrow.setSelected(false);
        } else {
            this.timeToday.setSelected(false);
            this.timeTomorrow.setSelected(true);
        }
        this.c1.setTitle("3s");
        this.c2.setTitle("5s");
        this.c3.setTitle("10s");
        if (AirData.exitTime == 3000) {
            this.c1.check(true);
        } else if (AirData.exitTime == 5000) {
            this.c2.check(true);
        } else if (AirData.exitTime == 10000) {
            this.c3.check(true);
        }
        this.timeDay.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AirSettingView.this.timeDayLine.getVisibility() == 0) {
                    AirSettingView.this.timeDayLine.setVisibility(8);
                } else {
                    AirSettingView.this.timeDayLine.setVisibility(0);
                }
            }
        });
        this.timeToday.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirSettingView.this.timeToday.setSelected(true);
                AirSettingView.this.timeTomorrow.setSelected(false);
                AirSettingView.this.timeDay.setText(R.string._439_time_tobay);
                AirSettingView.this.timeDayStart = 0;
                AirSettingView.this.timeDayLine.setVisibility(8);
            }
        });
        this.timeTomorrow.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirSettingView.this.timeToday.setSelected(false);
                AirSettingView.this.timeTomorrow.setSelected(true);
                AirSettingView.this.timeDay.setText(R.string._439_time_tomorrow);
                AirSettingView.this.timeDayStart = 1;
                AirSettingView.this.timeDayLine.setVisibility(8);
            }
        });
        this.setTimeOpen.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirSettingView.this.settingTime.setVisibility(0);
            }
        });
        this.settingTime.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AirSettingView.this.settingTime.getVisibility() == 0) {
                    AirSettingView.this.settingTime.setVisibility(8);
                } else {
                    AirSettingView.this.settingTime.setVisibility(0);
                }
            }
        });
        TextView textView = (TextView) this.parentView.findViewById(R.id.setTime);
        this.setTime = textView;
        textView.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.7
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String strValueOf;
                if (motionEvent.getAction() == 0) {
                    AirSettingView.this.setTime.setBackgroundResource(R.drawable.__439__kongt_anjian_p);
                    return true;
                }
                if (1 != motionEvent.getAction()) {
                    return false;
                }
                AirSettingView.this.alert.setVisibility(8);
                AirSettingView.this.alert.setText("");
                SendKeyManager.getInstance().playBeep(0);
                AirSettingView.this.setTime.setBackgroundResource(R.drawable.__439__kongt_anjian);
                Calendar calendar = Calendar.getInstance();
                AirSettingView.this.hour = calendar.get(11);
                AirSettingView.this.minute = calendar.get(12);
                String string = ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).getText().toString();
                String string2 = ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).getText().toString();
                if (!string.equals("") && !string2.equals("")) {
                    AirSettingView.this.IntEditTimeHour = Integer.parseInt(string);
                    AirSettingView.this.IntEditTimeMinute = Integer.parseInt(string2);
                    if (LogUtil.log5()) {
                        LogUtil.d("onTouch(): ----" + AirSettingView.this.hour + "-----" + AirSettingView.this.IntEditTimeHour + "-----" + AirSettingView.this.timeDayStart);
                    }
                    if (AirSettingView.this.timeDayStart == 0) {
                        if (AirSettingView.this.IntEditTimeHour < AirSettingView.this.hour) {
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).setText("");
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).setText("");
                            AirSettingView.this.alert.setVisibility(0);
                            AirSettingView.this.alert.setText(AirSettingView.this.getContext().getString(R.string._439_time_small_time));
                            AirSettingView.this.settingTime.setVisibility(8);
                            return true;
                        }
                        if (AirSettingView.this.IntEditTimeHour == AirSettingView.this.hour && AirSettingView.this.IntEditTimeMinute < AirSettingView.this.minute) {
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).setText("");
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).setText("");
                            AirSettingView.this.alert.setVisibility(0);
                            AirSettingView.this.alert.setText(AirSettingView.this.getContext().getString(R.string._439_time_small_time));
                            AirSettingView.this.settingTime.setVisibility(8);
                            return true;
                        }
                        float f = ((60 - AirSettingView.this.minute) + AirSettingView.this.IntEditTimeMinute) / 60.0f;
                        if (LogUtil.log5()) {
                            LogUtil.d("onTouch(): ------" + f);
                        }
                        strValueOf = String.valueOf(((AirSettingView.this.IntEditTimeHour - AirSettingView.this.hour) + f) - 1.0f);
                    } else {
                        float f2 = ((60 - AirSettingView.this.minute) + AirSettingView.this.IntEditTimeMinute) / 60.0f;
                        if (LogUtil.log5()) {
                            LogUtil.d("onTouch(): ------" + f2);
                        }
                        strValueOf = String.valueOf((((24 - AirSettingView.this.hour) + AirSettingView.this.IntEditTimeHour) + f2) - 1.0f);
                    }
                    if (strValueOf.length() == 0) {
                        return true;
                    }
                    try {
                        float fFloatValue = Float.valueOf(strValueOf).floatValue();
                        if (AirSettingView.this.IntEditTimeHour > 24) {
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).setText("");
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).setText("");
                            AirSettingView.this.alert.setVisibility(0);
                            AirSettingView.this.alert.setText(AirSettingView.this.getContext().getString(R.string._439_fun_02));
                            return true;
                        }
                        if (fFloatValue > 24.0d) {
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).setText("");
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).setText("");
                            AirSettingView.this.alert.setVisibility(0);
                            AirSettingView.this.alert.setText(AirSettingView.this.getContext().getString(R.string._439_fun_03));
                            AirSettingView.this.settingTime.setVisibility(8);
                            return true;
                        }
                        if (AirSettingView.this.IntEditTimeMinute > 60) {
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).setText("");
                            ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).setText("");
                            AirSettingView.this.alert.setVisibility(0);
                            AirSettingView.this.alert.setText(AirSettingView.this.getContext().getString(R.string._439_fun_02));
                            return true;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -110, 10, (byte) Math.ceil(fFloatValue * 10.0f)});
                        ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).setText("");
                        ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).setText("");
                        AirSettingView.this.settingsTimeHour.setText(string);
                        AirSettingView.this.settingsTimeMin.setText(string2);
                        AirSettingView.this.settingTime.setVisibility(8);
                        ((InputMethodManager) AirSettingView.this.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                        return true;
                    } catch (Exception unused) {
                        ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_hour)).setText("");
                        ((EditText) AirSettingView.this.parentView.findViewById(R.id.time_minute)).setText("");
                        AirSettingView.this.alert.setVisibility(0);
                        AirSettingView.this.alert.setText(AirSettingView.this.getContext().getString(R.string._439_fun_02));
                    }
                }
                return true;
            }
        });
        TextView textView2 = (TextView) this.parentView.findViewById(R.id.cancel);
        this.cancel = textView2;
        textView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.8
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    AirSettingView.this.cancel.setBackgroundResource(R.drawable.__439__kongt_anjian_p);
                    return true;
                }
                if (1 != motionEvent.getAction()) {
                    return false;
                }
                AirSettingView.this.cancel.setBackgroundResource(R.drawable.__439__kongt_anjian);
                CanbusMsgSender.sendMsg(new byte[]{22, -110, 10, -1});
                return true;
            }
        });
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        AirBuilder.getInstance().register(this);
    }

    public void getAction(final ActionCallback actionCallback) {
        this.c1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharePreUtil.setIntValue(AirSettingView.this.context, "key.air.exit.time", PathInterpolatorCompat.MAX_NUM_POINTS);
                AirData.exitTime = PathInterpolatorCompat.MAX_NUM_POINTS;
                actionCallback.toDo("C1");
                AirSettingView.this.c1.check(true);
                AirSettingView.this.c2.check(false);
                AirSettingView.this.c3.check(false);
            }
        });
        this.c2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharePreUtil.setIntValue(AirSettingView.this.context, "key.air.exit.time", 5000);
                AirData.exitTime = 5000;
                actionCallback.toDo("C2");
                AirSettingView.this.c1.check(false);
                AirSettingView.this.c2.check(true);
                AirSettingView.this.c3.check(false);
            }
        });
        this.c3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.air.view.AirSettingView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharePreUtil.setIntValue(AirSettingView.this.context, "key.air.exit.time", Priority.DEBUG_INT);
                AirData.exitTime = Priority.DEBUG_INT;
                actionCallback.toDo("C3");
                AirSettingView.this.c1.check(false);
                AirSettingView.this.c2.check(false);
                AirSettingView.this.c3.check(true);
            }
        });
    }

    @Override // com.hzbhd.canbus.car_cus._467.air.Interface.AirInfoObserver
    public void infoChange() {
        if (AirData.appointmentTime == 0) {
            this.resultTime.setText("--");
        } else if (AirData.appointmentTime == 255) {
            this.resultTime.setText("--");
        } else {
            this.resultTime.setText((AirData.appointmentTime / 10.0f) + "H");
        }
    }

    public void resetTxt() {
        this.title.setText(getContext().getString(R.string._439_air_title_01));
        this.setTimeTitle.setText(getContext().getString(R.string._439_air_title_02));
        this.setTime.setText(getContext().getString(R.string._439_air_title_03));
        this.currentTimeTitle.setText(getContext().getString(R.string._439_air_title_04));
        this.cancel.setText(getContext().getString(R.string.cancel));
        this.timeToday.setText(R.string._439_time_tobay);
        this.timeTomorrow.setText(R.string._439_time_tomorrow);
        this.setTimeOpen.setText(R.string._375_car_setting613);
        if (this.timeDayStart == 0) {
            this.timeDay.setText(R.string._439_time_tobay);
        } else {
            this.timeDay.setText(R.string._439_time_tomorrow);
        }
        if (AirData.appointmentTime == 0) {
            this.resultTime.setText("--");
        } else if (AirData.appointmentTime == 255) {
            this.resultTime.setText("--");
        } else {
            this.resultTime.setText((AirData.appointmentTime / 10.0f) + "H");
        }
    }
}
