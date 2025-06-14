package com.hzbhd.canbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.util.SystemUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* loaded from: classes2.dex */
public class DateTimeReceiver extends BroadcastReceiver {
    private int bDay;
    private int bHours;
    private int bHours24H;
    private int bMins;
    private int bMonth;
    private int bSecond;
    private int bYear2Dig;
    private int bYearTotal;
    private boolean isFormat24H;
    private boolean isFormatPm;
    private boolean isGpsTime;
    private int mDayOfWeek;
    private DateFormat mFormat;
    private MsgMgrInterface mMsgMgrInterface;
    private int systemDateFormat;
    private DateFormat yyyyMMdd = new SimpleDateFormat("yyyy/M/d");
    private DateFormat MMddyyyy = new SimpleDateFormat("M/d/yyyy");
    private DateFormat ddMMyyyy = new SimpleDateFormat("d/M/yyyy");
    private final String KEY_AUTO_GPS_TIME = "auto_gps_time";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        intent.getExtras();
        if ("android.intent.action.TIME_TICK".equals(action) || "android.intent.action.TIME_SET".equals(action)) {
            reportDateAndTime(context);
        }
    }

    private MsgMgrInterface getMsgMgrInterface(Context context) {
        if (this.mMsgMgrInterface == null) {
            this.mMsgMgrInterface = MsgMgrFactory.getCanMsgMgrUtil(context);
        }
        return this.mMsgMgrInterface;
    }

    public byte[] getCurrentDateAndTime(Context context) {
        int i;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = getDateFormat();
        dateFormat.setTimeZone(calendar.getTimeZone());
        String str = dateFormat.format(date);
        String str2 = str.split("==")[1];
        String strReplace = str.replace("/", "").replace("==", "");
        byte[] bytes = strReplace.getBytes();
        String str3 = android.text.format.DateFormat.getTimeFormat(context).format(calendar.getTime());
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        calendar.setTime(date);
        this.bYearTotal = Integer.valueOf(strReplace.substring(0, 4)).intValue();
        this.bYear2Dig = Integer.valueOf(strReplace.substring(2, 4)).intValue();
        this.bMonth = Integer.valueOf(strReplace.substring(4, 6)).intValue();
        this.bDay = Integer.valueOf(strReplace.substring(6, 8)).intValue();
        int iIntValue = Integer.valueOf(str2).intValue() / 100;
        this.bHours = iIntValue;
        this.bHours24H = iIntValue;
        this.bMins = Integer.valueOf(str2).intValue() % 100;
        this.bSecond = calendar.get(13);
        this.isFormat24H = android.text.format.DateFormat.is24HourFormat(context);
        this.isFormatPm = gregorianCalendar.get(9) == 1;
        this.isGpsTime = Settings.System.getInt(context.getContentResolver(), "auto_gps_time", 0) == 1;
        this.systemDateFormat = getSystemDateFormat(context, date);
        this.mDayOfWeek = calendar.get(7);
        if (!android.text.format.DateFormat.is24HourFormat(context)) {
            int i2 = this.bHours;
            if (i2 >= 12) {
                this.bHours = i2 - 12;
            }
            if (str3.contains("PM") && (i = this.bHours24H) < 12) {
                this.bHours24H = i + 12;
                bytes[8] = ((this.bHours24H / 10) + "").getBytes()[0];
                bytes[9] = ((this.bHours24H % 10) + "").getBytes()[0];
            }
        } else {
            this.isFormatPm = this.bHours >= 12;
        }
        return bytes;
    }

    private DateFormat getDateFormat() {
        if (this.mFormat == null) {
            this.mFormat = new SimpleDateFormat("yyyy/MM/dd==HHmm");
        }
        return this.mFormat;
    }

    public int getSystemDateFormat(Context context, Date date) {
        String str = android.text.format.DateFormat.getDateFormat(context).format(date);
        if (str.equals(this.ddMMyyyy.format(date))) {
            return 1;
        }
        if (str.equals(this.yyyyMMdd.format(date))) {
            return 2;
        }
        return str.equals(this.MMddyyyy.format(date)) ? 3 : 0;
    }

    public void reportDateAndTime(Context context) {
        getCurrentDateAndTime(context);
        if (getMsgMgrInterface(context) != null) {
            this.mMsgMgrInterface.dateTimeRepCanbus(this.bYearTotal, this.bYear2Dig, this.bMonth, this.bDay, this.bHours, this.bMins, this.bSecond, this.bHours24H, this.systemDateFormat, this.isFormat24H, this.isFormatPm, this.isGpsTime, this.mDayOfWeek);
        }
        SystemUtil.printCanbusParameter();
    }
}
