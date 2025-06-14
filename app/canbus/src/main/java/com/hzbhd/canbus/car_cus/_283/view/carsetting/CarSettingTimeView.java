package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.bean.TimeSyncMode;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingView;
import com.hzbhd.canbus.receiver.DateTimeReceiver;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class CarSettingTimeView extends RelativeLayout {
    private static final String DATE_INTERVAL = "-";
    private static final String TIME_INTERVAL = ":";
    private SettingDialogView car_time_mode;
    private ExecutorService executorService;
    private List<SettingDialogBean> listDateFormat;
    private List<SettingDialogBean> listSource;
    private List<SettingDialogBean> listTimeFormat;
    private Context mContext;
    private View mView;
    private SettingDialogView sdv_date_format;
    private SettingDialogView sdv_time_format;
    private SettingDialogView sdv_time_source;
    private SettingView sv_date;
    private SettingView sv_set_time;
    private SettingView sv_time_zone;
    private List<SettingDialogBean> timeModeFormat;

    /* JADX INFO: Access modifiers changed from: private */
    public int setYear(int i) {
        return i - 2000;
    }

    public CarSettingTimeView(Context context) {
        this(context, null);
    }

    public CarSettingTimeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingTimeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listSource = new ArrayList();
        this.listTimeFormat = new ArrayList();
        this.listDateFormat = new ArrayList();
        this.timeModeFormat = new ArrayList();
        this.executorService = Executors.newSingleThreadExecutor();
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_time, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1116xdc59b944();
            }
        }, 1000L);
    }

    private void initView() {
        this.sdv_time_source = (SettingDialogView) this.mView.findViewById(R.id.sdv_time_source);
        this.car_time_mode = (SettingDialogView) this.mView.findViewById(R.id.car_time_mode);
        this.sdv_time_format = (SettingDialogView) this.mView.findViewById(R.id.sdv_time_format);
        this.sdv_date_format = (SettingDialogView) this.mView.findViewById(R.id.sdv_date_format);
        this.sv_set_time = (SettingView) this.mView.findViewById(R.id.sv_set_time);
        this.sv_time_zone = (SettingView) this.mView.findViewById(R.id.sv_time_zone);
        this.sv_date = (SettingView) this.mView.findViewById(R.id.sv_date);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.1
            @Override // java.lang.Runnable
            public void run() {
                CarSettingTimeView.this.listSource.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_time_gps)));
                CarSettingTimeView.this.listSource.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_time_manual)));
                CarSettingTimeView.this.sdv_time_source.setListFirst(CarSettingTimeView.this.listSource);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.2
            @Override // java.lang.Runnable
            public void run() {
                CarSettingTimeView.this.listTimeFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_time_12)));
                CarSettingTimeView.this.listTimeFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_time_24)));
                CarSettingTimeView.this.sdv_time_format.setListFirst(CarSettingTimeView.this.listTimeFormat);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.3
            @Override // java.lang.Runnable
            public void run() {
                CarSettingTimeView.this.listDateFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_date_format_1)));
                CarSettingTimeView.this.listDateFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_date_format_2)));
                CarSettingTimeView.this.listDateFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_date_format_3)));
                CarSettingTimeView.this.sdv_date_format.setListFirst(CarSettingTimeView.this.listDateFormat);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.4
            @Override // java.lang.Runnable
            public void run() {
                CarSettingTimeView.this.timeModeFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_new_function_0)));
                CarSettingTimeView.this.timeModeFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_new_function_1)));
                CarSettingTimeView.this.timeModeFormat.add(new SettingDialogBean(CarSettingTimeView.this.mContext.getString(R.string._283_new_function_2)));
                CarSettingTimeView.this.car_time_mode.setListFirst(CarSettingTimeView.this.timeModeFormat);
                try {
                    CarSettingTimeView.this.car_time_mode.setSelect(Settings.System.getInt(CarSettingTimeView.this.mContext.getContentResolver(), TimeSyncMode.MODE_KEY));
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initClick() {
        SettingDialogView.OnItemClickListener onItemClickListener = new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                this.f$0.m1115xefd6e3eb(view, i);
            }
        };
        this.sdv_time_source.setOnItemClickListener(onItemClickListener);
        this.sdv_time_format.setOnItemClickListener(onItemClickListener);
        this.sdv_date_format.setOnItemClickListener(onItemClickListener);
        this.car_time_mode.setOnItemClickListener(onItemClickListener);
        this.sv_set_time.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new TimePickerDialog(CarSettingTimeView.this.mContext, new TimePickerDialog.OnTimeSetListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.5.1
                    @Override // android.app.TimePickerDialog.OnTimeSetListener
                    public void onTimeSet(TimePicker timePicker, int i, int i2) {
                        MessageSender.sendMsg(new byte[]{22, -53, (byte) CarSettingTimeView.this.getTimeSource(GeneralDzData.time_source), (byte) i, (byte) i2, (byte) GeneralDzData.time_date3, (byte) GeneralDzData.time_date4, (byte) GeneralDzData.time_format, (byte) GeneralDzData.date_year, (byte) GeneralDzData.date_month, (byte) GeneralDzData.date_day, (byte) GeneralDzData.date_format});
                    }
                }, GeneralDzData.time_hour, GeneralDzData.time_minute, GeneralDzData.time_format == 1).show();
            }
        });
        this.sv_date.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new DatePickerDialog(CarSettingTimeView.this.mContext, new DatePickerDialog.OnDateSetListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView.6.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        MessageSender.sendMsg(new byte[]{22, -53, (byte) CarSettingTimeView.this.getTimeSource(GeneralDzData.time_source), (byte) GeneralDzData.time_hour, (byte) GeneralDzData.time_minute, (byte) GeneralDzData.time_date3, (byte) GeneralDzData.time_date4, (byte) GeneralDzData.time_format, (byte) CarSettingTimeView.this.setYear(i), (byte) (i2 + 1), (byte) i3, (byte) GeneralDzData.date_format});
                    }
                }, Integer.valueOf(CarSettingTimeView.this.getYear(GeneralDzData.date_year)).intValue(), GeneralDzData.date_month - 1, GeneralDzData.date_day).show();
            }
        });
    }

    /* renamed from: lambda$initClick$1$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingTimeView, reason: not valid java name */
    /* synthetic */ void m1115xefd6e3eb(View view, int i) {
        switch (view.getId()) {
            case R.id.car_time_mode /* 2131362109 */:
                if (i == 0) {
                    Settings.System.putInt(this.mContext.getContentResolver(), TimeSyncMode.MODE_KEY, TimeSyncMode.MODE_1.intValue());
                } else if (i == 1) {
                    Settings.System.putInt(this.mContext.getContentResolver(), TimeSyncMode.MODE_KEY, TimeSyncMode.MODE_2.intValue());
                } else if (i == 2) {
                    Settings.System.putInt(this.mContext.getContentResolver(), TimeSyncMode.MODE_KEY, TimeSyncMode.MODE_3.intValue());
                }
                this.car_time_mode.setSelect(i);
                new DateTimeReceiver().reportDateAndTime(this.mContext);
                break;
            case R.id.sdv_date_format /* 2131363267 */:
                MessageSender.sendMsg(new byte[]{22, -53, (byte) getTimeSource(GeneralDzData.time_source), (byte) GeneralDzData.time_hour, (byte) GeneralDzData.time_minute, (byte) GeneralDzData.time_date3, (byte) GeneralDzData.time_date4, (byte) GeneralDzData.time_format, (byte) GeneralDzData.date_year, (byte) GeneralDzData.date_month, (byte) GeneralDzData.date_day, (byte) (i + 1)});
                break;
            case R.id.sdv_time_format /* 2131363282 */:
                MessageSender.sendMsg(new byte[]{22, -53, (byte) getTimeSource(GeneralDzData.time_source), (byte) GeneralDzData.time_hour, (byte) GeneralDzData.time_minute, (byte) GeneralDzData.time_date3, (byte) GeneralDzData.time_date4, (byte) i, (byte) GeneralDzData.date_year, (byte) GeneralDzData.date_month, (byte) GeneralDzData.date_day, (byte) GeneralDzData.date_format});
                break;
            case R.id.sdv_time_source /* 2131363283 */:
                MessageSender.sendMsg(new byte[]{22, -53, (byte) getTimeSource(i), (byte) GeneralDzData.time_hour, (byte) GeneralDzData.time_minute, (byte) GeneralDzData.time_date3, (byte) GeneralDzData.time_date4, (byte) GeneralDzData.time_format, (byte) GeneralDzData.date_year, (byte) GeneralDzData.date_month, (byte) GeneralDzData.date_day, (byte) GeneralDzData.date_format});
                break;
        }
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1116xdc59b944() {
        this.sdv_time_source.setSelect(GeneralDzData.time_source);
        this.sdv_time_format.setSelect(GeneralDzData.time_format);
        if (GeneralDzData.date_format >= 1 && GeneralDzData.date_format <= 3) {
            this.sdv_date_format.setSelect(GeneralDzData.date_format - 1);
        }
        this.sv_set_time.setEnable(GeneralDzData.time_source == 1);
        this.sv_time_zone.setEnable(GeneralDzData.time_source == 1);
        this.sv_date.setEnable(GeneralDzData.time_source == 1);
        if (GeneralDzData.time_hour == 255 || GeneralDzData.time_minute == 255) {
            this.sv_set_time.setValue("");
        } else if (GeneralDzData.time_format == 1) {
            this.sv_set_time.setValue(getTwo(GeneralDzData.time_hour) + TIME_INTERVAL + getTwo(GeneralDzData.time_minute));
        } else {
            this.sv_set_time.setValue(set12Hour(getTwo(GeneralDzData.time_hour) + TIME_INTERVAL + getTwo(GeneralDzData.time_minute)));
        }
        if (GeneralDzData.date_format == 1) {
            this.sv_date.setValue(getTwo(GeneralDzData.date_day) + DATE_INTERVAL + getTwo(GeneralDzData.date_month) + DATE_INTERVAL + getYear(GeneralDzData.date_year));
        } else if (GeneralDzData.date_format == 3) {
            this.sv_date.setValue(getTwo(GeneralDzData.date_month) + DATE_INTERVAL + getTwo(GeneralDzData.date_day) + DATE_INTERVAL + getYear(GeneralDzData.date_year));
        } else {
            this.sv_date.setValue(getYear(GeneralDzData.date_year) + DATE_INTERVAL + getTwo(GeneralDzData.date_month) + DATE_INTERVAL + getTwo(GeneralDzData.date_day));
        }
    }

    private String set12Hour(String str) {
        try {
            Date date = new SimpleDateFormat("HH:mm").parse(str);
            if (date.getHours() == 0) {
                date.setHours(date.getHours() + 12);
            } else if (date.getHours() != 12 && date.getHours() > 12) {
                date.setHours(date.getHours() - 12);
            }
            return getTwo(date.getHours()) + TIME_INTERVAL + getTwo(date.getMinutes());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getYear(int i) {
        return "20" + getTwo(i);
    }

    private String getTwo(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return i + "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTimeSource(int i) {
        return DataHandleUtils.setIntByteWithBit(0, 7, i == 1);
    }
}
