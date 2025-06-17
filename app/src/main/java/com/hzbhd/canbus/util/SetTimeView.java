package com.hzbhd.canbus.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.SystemConstant;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes2.dex */
public class SetTimeView {
    private Button cancel;
    private TextView day;
    private TextView hour;
    private ImageButton imageButtonAdd1;
    private ImageButton imageButtonAdd2;
    private ImageButton imageButtonAdd3;
    private ImageButton imageButtonAdd4;
    private ImageButton imageButtonAdd5;
    private ImageButton imageButtonReduce1;
    private ImageButton imageButtonReduce2;
    private ImageButton imageButtonReduce3;
    private ImageButton imageButtonReduce4;
    private ImageButton imageButtonReduce5;
    Context mContext;
    private TextView minute;
    private TextView month;
    private Button ok;
    private TextView title;
    private TextView year;
    private int timeYear = SystemConstant.THREAD_SLEEP_TIME_2000;
    private int timeMonth = 1;
    private int timeDay = 1;
    private int timeHour = View.VISIBLE;
    private int timeMinute = View.VISIBLE;
    DecimalFormat df_2Integer = new DecimalFormat("00");

    public interface TimeResultListener {
        void result(int i, int i2, int i3, int i4, int i5);
    }

    public void showDialog(Context context, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TimeResultListener timeResultListener) {
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._set_time_layout, null, true);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        alertDialogCreate.setCancelable(false);
        eventMethod(viewInflate, str, z, z2, z3, z4, z5, timeResultListener, alertDialogCreate);
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.show();
    }

    private void eventMethod(View view, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TimeResultListener timeResultListener, AlertDialog alertDialog) {
        intiUi(view, z, z2, z3, z4, z5);
        InitTextViewData(view, str);
        ButtonUiEvent(view);
        ButtClickEvent(view, timeResultListener, alertDialog);
    }

    private void intiUi(View view, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (this.year == null) {
            this.year = view.findViewById(R.id.year);
        }
        if (this.month == null) {
            this.month = view.findViewById(R.id.month);
        }
        if (this.day == null) {
            this.day = view.findViewById(R.id.day);
        }
        if (this.hour == null) {
            this.hour = view.findViewById(R.id.hour);
        }
        if (this.minute == null) {
            this.minute = view.findViewById(R.id.minute);
        }
        if (this.title == null) {
            this.title = view.findViewById(R.id.title);
        }
        if (this.imageButtonAdd1 == null) {
            this.imageButtonAdd1 = view.findViewById(R.id.add_1);
        }
        if (this.imageButtonAdd2 == null) {
            this.imageButtonAdd2 = view.findViewById(R.id.add_2);
        }
        if (this.imageButtonAdd3 == null) {
            this.imageButtonAdd3 = view.findViewById(R.id.add_3);
        }
        if (this.imageButtonAdd4 == null) {
            this.imageButtonAdd4 = view.findViewById(R.id.add_4);
        }
        if (this.imageButtonAdd5 == null) {
            this.imageButtonAdd5 = view.findViewById(R.id.add_5);
        }
        if (this.imageButtonReduce1 == null) {
            this.imageButtonReduce1 = view.findViewById(R.id.reduce1);
        }
        if (this.imageButtonReduce2 == null) {
            this.imageButtonReduce2 = view.findViewById(R.id.reduce2);
        }
        if (this.imageButtonReduce3 == null) {
            this.imageButtonReduce3 = view.findViewById(R.id.reduce3);
        }
        if (this.imageButtonReduce4 == null) {
            this.imageButtonReduce4 = view.findViewById(R.id.reduce4);
        }
        if (this.imageButtonReduce5 == null) {
            this.imageButtonReduce5 = view.findViewById(R.id.reduce5);
        }
        if (!z) {
            this.year.setVisibility(View.GONE);
            this.imageButtonAdd1.setVisibility(View.GONE);
            this.imageButtonReduce1.setVisibility(View.GONE);
        } else {
            this.year.setVisibility(View.VISIBLE);
            this.imageButtonAdd1.setVisibility(View.VISIBLE);
            this.imageButtonReduce1.setVisibility(View.VISIBLE);
        }
        if (!z2) {
            this.month.setVisibility(View.GONE);
            this.imageButtonAdd2.setVisibility(View.GONE);
            this.imageButtonReduce2.setVisibility(View.GONE);
        } else {
            this.month.setVisibility(View.VISIBLE);
            this.imageButtonAdd2.setVisibility(View.VISIBLE);
            this.imageButtonReduce2.setVisibility(View.VISIBLE);
        }
        if (!z3) {
            this.day.setVisibility(View.GONE);
            this.imageButtonAdd3.setVisibility(View.GONE);
            this.imageButtonReduce3.setVisibility(View.GONE);
        } else {
            this.day.setVisibility(View.VISIBLE);
            this.imageButtonAdd3.setVisibility(View.VISIBLE);
            this.imageButtonReduce3.setVisibility(View.VISIBLE);
        }
        if (!z4) {
            this.hour.setVisibility(View.GONE);
            this.imageButtonAdd4.setVisibility(View.GONE);
            this.imageButtonReduce4.setVisibility(View.GONE);
        } else {
            this.hour.setVisibility(View.VISIBLE);
            this.imageButtonAdd4.setVisibility(View.VISIBLE);
            this.imageButtonReduce4.setVisibility(View.VISIBLE);
        }
        if (!z5) {
            this.minute.setVisibility(View.GONE);
            this.imageButtonAdd5.setVisibility(View.GONE);
            this.imageButtonReduce5.setVisibility(View.GONE);
        } else {
            this.minute.setVisibility(View.VISIBLE);
            this.imageButtonAdd5.setVisibility(View.VISIBLE);
            this.imageButtonReduce5.setVisibility(View.VISIBLE);
        }
    }

    private void InitTextViewData(View view, String str) {
        if (this.year == null) {
            this.year = view.findViewById(R.id.year);
        }
        if (this.month == null) {
            this.month = view.findViewById(R.id.month);
        }
        if (this.day == null) {
            this.day = view.findViewById(R.id.day);
        }
        if (this.hour == null) {
            this.hour = view.findViewById(R.id.hour);
        }
        if (this.minute == null) {
            this.minute = view.findViewById(R.id.minute);
        }
        if (this.title == null) {
            this.title = view.findViewById(R.id.title);
        }
        String str2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        this.timeYear = Integer.parseInt(str2.substring(View.VISIBLE, 4));
        this.year.setText(this.timeYear + this.mContext.getString(R.string._206_new_function5));
        this.timeMonth = Integer.parseInt(str2.substring(4, 6));
        this.month.setText(this.timeMonth + this.mContext.getString(R.string._206_new_function6));
        this.timeDay = Integer.parseInt(str2.substring(6, View.GONE));
        this.day.setText(this.timeDay + this.mContext.getString(R.string._206_new_function7));
        this.timeHour = Integer.parseInt(str2.substring(View.GONE, 10));
        this.hour.setText(this.timeHour + this.mContext.getString(R.string._206_new_function8));
        this.timeMinute = Integer.parseInt(str2.substring(10, 12));
        this.minute.setText(this.timeMinute + this.mContext.getString(R.string._206_new_function9));
        if (str != null) {
            this.title.setText("<  " + str + "  >");
        } else {
            this.title.setText("<  Set Time  >");
        }
    }

    private void ButtClickEvent(View view, final TimeResultListener timeResultListener, final AlertDialog alertDialog) {
        if (this.imageButtonAdd1 == null) {
            this.imageButtonAdd1 = view.findViewById(R.id.add_1);
        }
        if (this.imageButtonAdd2 == null) {
            this.imageButtonAdd2 = view.findViewById(R.id.add_2);
        }
        if (this.imageButtonAdd3 == null) {
            this.imageButtonAdd3 = view.findViewById(R.id.add_3);
        }
        if (this.imageButtonAdd4 == null) {
            this.imageButtonAdd4 = view.findViewById(R.id.add_4);
        }
        if (this.imageButtonAdd5 == null) {
            this.imageButtonAdd5 = view.findViewById(R.id.add_5);
        }
        if (this.imageButtonReduce1 == null) {
            this.imageButtonReduce1 = view.findViewById(R.id.reduce1);
        }
        if (this.imageButtonReduce2 == null) {
            this.imageButtonReduce2 = view.findViewById(R.id.reduce2);
        }
        if (this.imageButtonReduce3 == null) {
            this.imageButtonReduce3 = view.findViewById(R.id.reduce3);
        }
        if (this.imageButtonReduce4 == null) {
            this.imageButtonReduce4 = view.findViewById(R.id.reduce4);
        }
        if (this.imageButtonReduce5 == null) {
            this.imageButtonReduce5 = view.findViewById(R.id.reduce5);
        }
        if (this.year == null) {
            this.year = view.findViewById(R.id.year);
        }
        if (this.month == null) {
            this.month = view.findViewById(R.id.month);
        }
        if (this.day == null) {
            this.day = view.findViewById(R.id.day);
        }
        if (this.hour == null) {
            this.hour = view.findViewById(R.id.hour);
        }
        if (this.minute == null) {
            this.minute = view.findViewById(R.id.minute);
        }
        if (this.ok == null) {
            this.ok = view.findViewById(R.id.ok);
        }
        if (this.cancel == null) {
            this.cancel = view.findViewById(R.id.cancel);
        }
        this.ok.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                timeResultListener.result(SetTimeView.this.timeYear, SetTimeView.this.timeMonth, SetTimeView.this.timeDay, SetTimeView.this.timeHour, SetTimeView.this.timeMinute);
                alertDialog.dismiss();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                alertDialog.dismiss();
            }
        });
        this.imageButtonAdd1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeYear == 2099) {
                    SetTimeView.this.timeYear = SystemConstant.THREAD_SLEEP_TIME_2000;
                } else {
                    SetTimeView.this.timeYear++;
                }
                SetTimeView.this.year.setText(SetTimeView.this.timeYear + SetTimeView.this.mContext.getString(R.string._206_new_function5));
            }
        });
        this.imageButtonReduce1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeYear == 2000) {
                    SetTimeView.this.timeYear = 2099;
                } else {
                    SetTimeView setTimeView = SetTimeView.this;
                    setTimeView.timeYear--;
                }
                SetTimeView.this.year.setText(SetTimeView.this.timeYear + SetTimeView.this.mContext.getString(R.string._206_new_function5));
            }
        });
        this.imageButtonAdd2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeMonth != 12) {
                    if (SetTimeView.this.timeMonth == 1 && SetTimeView.this.timeDay > 28) {
                        SetTimeView.this.timeMonth++;
                        SetTimeView.this.timeDay = 1;
                    } else {
                        SetTimeView.this.timeMonth++;
                    }
                } else {
                    SetTimeView.this.timeMonth = 1;
                }
                SetTimeView.this.day.setText(SetTimeView.this.timeDay + SetTimeView.this.mContext.getString(R.string._206_new_function7));
                SetTimeView.this.month.setText(SetTimeView.this.timeMonth + SetTimeView.this.mContext.getString(R.string._206_new_function6));
            }
        });
        this.imageButtonReduce2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeMonth != 1) {
                    if (SetTimeView.this.timeMonth == 3 && SetTimeView.this.timeDay > 28) {
                        SetTimeView.this.timeMonth--;
                        SetTimeView.this.timeDay = 1;
                    } else {
                        SetTimeView.this.timeMonth--;
                    }
                } else {
                    SetTimeView.this.timeMonth = 12;
                }
                SetTimeView.this.day.setText(SetTimeView.this.timeDay + SetTimeView.this.mContext.getString(R.string._206_new_function7));
                SetTimeView.this.month.setText(SetTimeView.this.timeMonth + SetTimeView.this.mContext.getString(R.string._206_new_function6));
            }
        });
        this.imageButtonAdd3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeMonth == 1 || SetTimeView.this.timeMonth == 3 || SetTimeView.this.timeMonth == 5 || SetTimeView.this.timeMonth == 7 || SetTimeView.this.timeMonth == View.GONE || SetTimeView.this.timeMonth == 10 || SetTimeView.this.timeMonth == 12) {
                    if (SetTimeView.this.timeDay == 31) {
                        SetTimeView.this.timeDay = 1;
                    } else {
                        SetTimeView.this.timeDay++;
                    }
                } else if (SetTimeView.this.timeMonth == 2) {
                    if (SetTimeView.this.timeYear % 4 == View.VISIBLE) {
                        if (SetTimeView.this.timeDay >= 29) {
                            SetTimeView.this.timeDay = 1;
                        } else {
                            SetTimeView.this.timeDay++;
                        }
                    } else if (SetTimeView.this.timeDay == 28) {
                        SetTimeView.this.timeDay = 1;
                    } else {
                        SetTimeView.this.timeDay++;
                    }
                } else if (SetTimeView.this.timeDay == 30) {
                    SetTimeView.this.timeDay = 1;
                } else {
                    SetTimeView.this.timeDay++;
                }
                SetTimeView.this.day.setText(SetTimeView.this.timeDay + SetTimeView.this.mContext.getString(R.string._206_new_function7));
            }
        });
        this.imageButtonReduce3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.View.GONE
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeMonth == 1 || SetTimeView.this.timeMonth == 3 || SetTimeView.this.timeMonth == 5 || SetTimeView.this.timeMonth == 7 || SetTimeView.this.timeMonth == View.GONE || SetTimeView.this.timeMonth == 10 || SetTimeView.this.timeMonth == 12) {
                    if (SetTimeView.this.timeDay == 1) {
                        SetTimeView.this.timeDay = 31;
                    } else {
                        SetTimeView.this.timeDay--;
                    }
                } else if (SetTimeView.this.timeMonth == 2) {
                    if (SetTimeView.this.timeYear % 4 == View.VISIBLE) {
                        if (SetTimeView.this.timeDay == 1) {
                            SetTimeView.this.timeDay = 29;
                        } else {
                            SetTimeView.this.timeDay--;
                        }
                    } else if (SetTimeView.this.timeDay == 1) {
                        SetTimeView.this.timeDay = 28;
                    } else {
                        SetTimeView.this.timeDay--;
                    }
                } else if (SetTimeView.this.timeDay == 1) {
                    SetTimeView.this.timeDay = 30;
                } else {
                    SetTimeView.this.timeDay--;
                }
                SetTimeView.this.day.setText(SetTimeView.this.timeDay + SetTimeView.this.mContext.getString(R.string._206_new_function7));
            }
        });
        this.imageButtonAdd4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeHour == 23) {
                    SetTimeView.this.timeHour = View.VISIBLE;
                } else {
                    SetTimeView.this.timeHour++;
                }
                SetTimeView.this.hour.setText(SetTimeView.this.df_2Integer.format(SetTimeView.this.timeHour) + SetTimeView.this.mContext.getString(R.string._206_new_function8));
            }
        });
        this.imageButtonReduce4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeHour == View.VISIBLE) {
                    SetTimeView.this.timeHour = 23;
                } else {
                    SetTimeView setTimeView = SetTimeView.this;
                    setTimeView.timeHour--;
                }
                SetTimeView.this.hour.setText(SetTimeView.this.df_2Integer.format(SetTimeView.this.timeHour) + SetTimeView.this.mContext.getString(R.string._206_new_function8));
            }
        });
        this.imageButtonAdd5.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeMinute == 59) {
                    SetTimeView.this.timeMinute = View.VISIBLE;
                } else {
                    SetTimeView.this.timeMinute++;
                }
                SetTimeView.this.minute.setText(SetTimeView.this.df_2Integer.format(SetTimeView.this.timeMinute) + SetTimeView.this.mContext.getString(R.string._206_new_function9));
            }
        });
        this.imageButtonReduce5.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetTimeView.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetTimeView.this.timeMinute == View.VISIBLE) {
                    SetTimeView.this.timeMinute = 59;
                } else {
                    SetTimeView setTimeView = SetTimeView.this;
                    setTimeView.timeMinute--;
                }
                SetTimeView.this.minute.setText(SetTimeView.this.df_2Integer.format(SetTimeView.this.timeMinute) + SetTimeView.this.mContext.getString(R.string._206_new_function9));
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void ButtonUiEvent(View view) {
        if (this.imageButtonAdd1 == null) {
            this.imageButtonAdd1 = view.findViewById(R.id.add_1);
        }
        if (this.imageButtonAdd2 == null) {
            this.imageButtonAdd2 = view.findViewById(R.id.add_2);
        }
        if (this.imageButtonAdd3 == null) {
            this.imageButtonAdd3 = view.findViewById(R.id.add_3);
        }
        if (this.imageButtonAdd4 == null) {
            this.imageButtonAdd4 = view.findViewById(R.id.add_4);
        }
        if (this.imageButtonAdd5 == null) {
            this.imageButtonAdd5 = view.findViewById(R.id.add_5);
        }
        if (this.imageButtonReduce1 == null) {
            this.imageButtonReduce1 = view.findViewById(R.id.reduce1);
        }
        if (this.imageButtonReduce2 == null) {
            this.imageButtonReduce2 = view.findViewById(R.id.reduce2);
        }
        if (this.imageButtonReduce3 == null) {
            this.imageButtonReduce3 = view.findViewById(R.id.reduce3);
        }
        if (this.imageButtonReduce4 == null) {
            this.imageButtonReduce4 = view.findViewById(R.id.reduce4);
        }
        if (this.imageButtonReduce5 == null) {
            this.imageButtonReduce5 = view.findViewById(R.id.reduce5);
        }
        if (this.ok == null) {
            this.ok = view.findViewById(R.id.ok);
        }
        if (this.cancel == null) {
            this.cancel = view.findViewById(R.id.cancel);
        }
        this.imageButtonAdd1.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonAdd1.setBackgroundResource(R.drawable.ic_air_m_temp1_p1);
                    return false;
                }
                SetTimeView.this.imageButtonAdd1.setBackgroundResource(R.drawable.ic_air_m_temp1_n);
                return false;
            }
        });
        this.imageButtonAdd2.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.14
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonAdd2.setBackgroundResource(R.drawable.ic_air_m_temp1_p1);
                    return false;
                }
                SetTimeView.this.imageButtonAdd2.setBackgroundResource(R.drawable.ic_air_m_temp1_n);
                return false;
            }
        });
        this.imageButtonAdd3.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.15
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonAdd3.setBackgroundResource(R.drawable.ic_air_m_temp1_p1);
                    return false;
                }
                SetTimeView.this.imageButtonAdd3.setBackgroundResource(R.drawable.ic_air_m_temp1_n);
                return false;
            }
        });
        this.imageButtonAdd4.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.16
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonAdd4.setBackgroundResource(R.drawable.ic_air_m_temp1_p1);
                    return false;
                }
                SetTimeView.this.imageButtonAdd4.setBackgroundResource(R.drawable.ic_air_m_temp1_n);
                return false;
            }
        });
        this.imageButtonAdd5.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.17
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonAdd5.setBackgroundResource(R.drawable.ic_air_m_temp1_p1);
                    return false;
                }
                SetTimeView.this.imageButtonAdd5.setBackgroundResource(R.drawable.ic_air_m_temp1_n);
                return false;
            }
        });
        this.imageButtonReduce1.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.18
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonReduce1.setBackgroundResource(R.drawable.ic_air_m_temp1_p2);
                    return false;
                }
                SetTimeView.this.imageButtonReduce1.setBackgroundResource(R.drawable.ic_air_m_temp1_n2);
                return false;
            }
        });
        this.imageButtonReduce2.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.19
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonReduce2.setBackgroundResource(R.drawable.ic_air_m_temp1_p2);
                    return false;
                }
                SetTimeView.this.imageButtonReduce2.setBackgroundResource(R.drawable.ic_air_m_temp1_n2);
                return false;
            }
        });
        this.imageButtonReduce3.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.20
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonReduce3.setBackgroundResource(R.drawable.ic_air_m_temp1_p2);
                    return false;
                }
                SetTimeView.this.imageButtonReduce3.setBackgroundResource(R.drawable.ic_air_m_temp1_n2);
                return false;
            }
        });
        this.imageButtonReduce4.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.21
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonReduce4.setBackgroundResource(R.drawable.ic_air_m_temp1_p2);
                    return false;
                }
                SetTimeView.this.imageButtonReduce4.setBackgroundResource(R.drawable.ic_air_m_temp1_n2);
                return false;
            }
        });
        this.imageButtonReduce5.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.22
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.imageButtonReduce5.setBackgroundResource(R.drawable.ic_air_m_temp1_p2);
                    return false;
                }
                SetTimeView.this.imageButtonReduce5.setBackgroundResource(R.drawable.ic_air_m_temp1_n2);
                return false;
            }
        });
        this.ok.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.23
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.ok.setBackgroundResource(R.drawable.energy_feedback_btn_p);
                    return false;
                }
                SetTimeView.this.ok.setBackgroundResource(R.drawable.energy_feedback_btn_n);
                return false;
            }
        });
        this.cancel.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.util.SetTimeView.24
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == View.VISIBLE) {
                    SetTimeView.this.cancel.setBackgroundResource(R.drawable.energy_feedback_btn_p);
                    return false;
                }
                SetTimeView.this.cancel.setBackgroundResource(R.drawable.energy_feedback_btn_n);
                return false;
            }
        });
    }
}
