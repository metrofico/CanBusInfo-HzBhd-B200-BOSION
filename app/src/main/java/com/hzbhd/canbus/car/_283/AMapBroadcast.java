package com.hzbhd.canbus.car._283;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.regex.Pattern;


public class AMapBroadcast extends BroadcastReceiver {
    private static final String INIT_TIME = "0:0";
    private static final int WHAT_ONLINE_IN = 0;
    private static final int WHAT_ONLINE_OUT = 1;
    public static boolean isOnlinePlay = false;
    private int TempIcon;
    private Context mContext;
    private int TempAllDistance = 0;
    private int TempDestinationDistance = 0;
    private int TempNextDistance = 0;
    private int TempSurplusAllTime = 0;
    private String TempPlanTime = INIT_TIME;
    private String TempName = "";
    private String TempSurplusAllTimeStr = INIT_TIME;
    private int TempCarDirection = 0;
    private boolean isStartNaving = false;
    private String playPackageNameNow = "";
    private Handler mHandler = new Handler() { // from class: com.hzbhd.canbus.car._283.AMapBroadcast.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                MeterManager.sendMediaMeterData(" ", " ", " ", " ");
                AMapBroadcast.isOnlinePlay = false;
                return;
            }
            if (AMapBroadcast.this.mContext != null) {
                MeterManager.send0xE6Null(0);
                MeterManager.sendMediaMeterData(AMapBroadcast.this.mContext.getString(R.string._283_online_play), " ", " ", " ");
                AMapBroadcast.isOnlinePlay = true;
            }
        }
    };
    private String tempTime = INIT_TIME;

    private int getCarDirection(int i) {
        double d = i;
        if (d > 337.5d && i <= 360) {
            return 7;
        }
        if (i > 0 && d < 22.5d) {
            return 7;
        }
        if (d > 22.5d && d < 67.5d) {
            return 8;
        }
        if (d > 67.5d && d < 112.5d) {
            return 1;
        }
        if (d > 112.5d && d < 157.5d) {
            return 2;
        }
        if (d > 157.5d && d < 202.5d) {
            return 3;
        }
        if (d > 202.5d && d < 247.5d) {
            return 4;
        }
        if (d <= 247.5d || d >= 292.5d) {
            return (d <= 292.5d || d >= 337.5d) ? 0 : 6;
        }
        return 5;
    }

    private int getIcon(int i) {
        if (i == 9) {
            return 1;
        }
        if (i == 5) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        if (i == 7) {
            return 4;
        }
        if (i == 19 || i == 8) {
            return 5;
        }
        if (i == 6) {
            return 6;
        }
        if (i == 2) {
            return 7;
        }
        if (i == 4) {
            return 8;
        }
        if (i == 1) {
            return 9;
        }
        if (i == 11) {
            return 10;
        }
        if (i == 17) {
            return 11;
        }
        if (i == 12) {
            return 12;
        }
        if (i == 18) {
            return 13;
        }
        return i == 15 ? 14 : 0;
    }

    private boolean isStartNai(int i) {
        return i == 8 || i == 10;
    }

    private boolean isStopNai(int i) {
        return i == 12 || i == 9;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws NumberFormatException {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equals(AMapUtils.AUTONAVI_STANDARD_BROADCAST_SEND)) {
            Bundle extras = intent.getExtras();
            int i = extras.getInt("EXTRA_STATE");
            if (isStopNai(i)) {
                this.TempAllDistance = 0;
                this.TempDestinationDistance = 0;
                this.TempNextDistance = 0;
                this.TempSurplusAllTime = 0;
                this.TempPlanTime = INIT_TIME;
                this.TempName = "";
                this.TempSurplusAllTimeStr = INIT_TIME;
                this.TempCarDirection = 0;
                this.TempIcon = 0;
                this.isStartNaving = false;
            }
            if (isStartNai(i)) {
                this.isStartNaving = true;
            }
            int i2 = extras.getInt("ROUTE_ALL_DIS");
            int i3 = extras.getInt("SEG_REMAIN_DIS");
            int i4 = extras.getInt("ROUTE_REMAIN_DIS");
            int i5 = extras.getInt("ICON");
            int i6 = extras.getInt("ROUTE_REMAIN_TIME");
            String strRemoveChinese2 = removeChinese2(extras.getString("ROUTE_REMAIN_TIME_AUTO"));
            String strRemoveChinese = removeChinese(extras.getString("ETA_TEXT"));
            int i7 = extras.getInt("CAR_DIRECTION");
            if (i2 == 0) {
                i2 = this.TempAllDistance;
            } else {
                this.TempAllDistance = i2;
            }
            if (i3 == 0) {
                i3 = this.TempNextDistance;
            } else {
                this.TempNextDistance = i3;
            }
            if (i4 == 0) {
                i4 = this.TempDestinationDistance;
            } else {
                this.TempDestinationDistance = i4;
            }
            if (i6 != 0) {
                this.TempSurplusAllTime = i6;
            }
            if (TextUtils.isEmpty(strRemoveChinese)) {
                strRemoveChinese = this.TempPlanTime;
            } else {
                this.TempPlanTime = strRemoveChinese;
            }
            if (TextUtils.isEmpty(strRemoveChinese2)) {
                strRemoveChinese2 = this.TempSurplusAllTimeStr;
            } else {
                this.TempSurplusAllTimeStr = strRemoveChinese2;
            }
            if (i7 == 0) {
                i7 = this.TempCarDirection;
            } else {
                this.TempCarDirection = i7;
            }
            if (i5 == 0) {
                i5 = this.TempIcon;
            } else {
                this.TempIcon = i5;
            }
            int i8 = Integer.parseInt(strRemoveChinese2.split(":")[0]);
            int i9 = Integer.parseInt(strRemoveChinese2.split(":")[1].replaceAll("<", ""));
            int i10 = Integer.parseInt(strRemoveChinese.split(":")[0]);
            int i11 = Integer.parseInt(strRemoveChinese.split(":")[1]);
            if (i5 != 0 && !this.isStartNaving) {
                this.isStartNaving = true;
            }
            int i12 = i3 * 10;
            int i13 = (int) (((i2 - i4) / i2) * 100.0d);
            int i14 = i4 * 10;
            CanbusMsgSender.sendMsg(new byte[]{22, -28, (byte) DataHandleUtils.setIntByteWithBit(0, 7, true), (byte) (this.isStartNaving ? DataHandleUtils.setIntByteWithBit(0, 7, true) : 0), (byte) (i12 >> 24), (byte) (i12 >> 16), (byte) (i12 >> 8), (byte) i12, (byte) i13, (byte) (i14 >> 24), (byte) (i14 >> 16), (byte) (i14 >> 8), (byte) i14, (byte) getCarDirection(i7), (byte) i10, (byte) i11, (byte) i8, (byte) i9, (byte) getIcon(i5), 0, 0});
            String string = extras.getString("NEXT_ROAD_NAME");
            if (TextUtils.isEmpty(string)) {
                string = this.TempName;
            } else {
                this.TempName = string;
            }
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -27, 0}, DataHandleUtils.makeBytesFixedLength(string.getBytes(), 30)));
            return;
        }
        if (action.equals("AUDIO_PLAY_NAME_AND_STAUS")) {
            this.mContext = context;
            boolean booleanExtra = intent.getBooleanExtra("isPlay", false);
            intent.getStringExtra("processName");
            Log.d("scyscyscy", "网络播放onReceive: " + booleanExtra + "------当前源-->" + FutureUtil.instance.getCurrentValidSource().name());
            if (booleanExtra) {
                this.mHandler.removeMessages(0);
                this.mHandler.sendEmptyMessageDelayed(0, 1000L);
                return;
            } else {
                if (FutureUtil.instance.getCurrentValidSource().name().equals(SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name())) {
                    this.mHandler.sendEmptyMessage(1);
                    return;
                }
                return;
            }
        }
        Log.d("scyscyscy", "---------->action 是空置");
    }

    private String removeChinese(String str) {
        return TextUtils.isEmpty(str) ? "" : Pattern.compile("[一-龥]").matcher(str.trim()).replaceAll("");
    }

    private String removeChinese2(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] strArrSplit = Pattern.compile("[一-龥]").matcher(str.trim()).replaceAll(":").replace("::", ":").split(":");
        if (str.contains("时") && !str.contains("分") && strArrSplit.length == 1) {
            String str2 = strArrSplit[0] + ":0";
            this.tempTime = str2;
            return str2;
        }
        if (strArrSplit.length == 2) {
            String str3 = strArrSplit[0] + ":" + strArrSplit[1];
            this.tempTime = str3;
            return str3;
        }
        if (strArrSplit.length == 1) {
            String str4 = "0:" + strArrSplit[0];
            this.tempTime = str4;
            return str4;
        }
        return this.tempTime;
    }

    private void sendSourceMsg1(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, str.getBytes()), 28));
    }

    private void sendSourceMsg2(String str, int i) {
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, (byte) i, 0}, str.getBytes()), 27));
    }
}
