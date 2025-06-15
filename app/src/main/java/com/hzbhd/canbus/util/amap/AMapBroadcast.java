package com.hzbhd.canbus.util.amap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.util.LogUtil;

import java.util.Objects;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class AMapBroadcast extends BroadcastReceiver {
    private int TempIcon;
    private AMapEntity oldAMapEntity;
    private final String INIT_TIME = "0:0";
    private int TempAllDistance = 0;
    private int TempDestinationDistance = 0;
    private int TempNextDistance = 0;
    private int TempSurplusAllTime = 0;
    private String TempPlanTime = "0:0";
    private String TempNextWayName = "";
    private String TempSurplusAllTimeStr = "0:0";
    private int TempCarDirection = 0;
    private String tempTime = "0:0";

    private int getCarDirection(int i) {
        double d = i;
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
        if (d > 247.5d && d < 292.5d) {
            return 5;
        }
        if (d > 292.5d && d < 337.5d) {
            return 6;
        }
        if (d > 337.5d && i <= 360) {
            return 7;
        }
        if (i <= 0 || d >= 22.5d) {
            return (d <= 22.5d || d >= 67.5d) ? 0 : 8;
        }
        return 7;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int i;
        int i2;
        String str;
        String str2;
        int i3;
        String str3;
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equals(AMapUtils.AUTONAVI_STANDARD_BROADCAST_SEND)) {
            Bundle extras = intent.getExtras();
            int i4 = extras.getInt("EXTRA_STATE");
            int i5 = extras.getInt("ROUTE_ALL_DIS");
            int i6 = extras.getInt("SEG_REMAIN_DIS");
            int i7 = extras.getInt("ROUTE_REMAIN_DIS");
            int i8 = extras.getInt("ICON");
            int i9 = extras.getInt("ROUTE_REMAIN_TIME");
            String strRemoveChinese2 = removeChinese2(extras.getString("ROUTE_REMAIN_TIME_AUTO"));
            String strRemoveChinese = removeChinese(extras.getString("ETA_TEXT"));
            String string = extras.getString("NEXT_ROAD_NAME");
            int i10 = extras.getInt("CAR_DIRECTION");
            if (i5 == 0) {
                i5 = this.TempAllDistance;
            } else {
                this.TempAllDistance = i5;
            }
            if (i6 == 0) {
                i6 = this.TempNextDistance;
            } else {
                this.TempNextDistance = i6;
            }
            int i11 = i6;
            if (i7 == 0) {
                i = this.TempDestinationDistance;
            } else {
                this.TempDestinationDistance = i7;
                i = i7;
            }
            if (i9 == 0) {
                i2 = this.TempSurplusAllTime;
            } else {
                this.TempSurplusAllTime = i9;
                i2 = i9;
            }
            if (TextUtils.isEmpty(strRemoveChinese)) {
                str = this.TempPlanTime;
            } else {
                this.TempPlanTime = strRemoveChinese;
                str = strRemoveChinese;
            }
            if (TextUtils.isEmpty(strRemoveChinese2)) {
                str2 = this.TempSurplusAllTimeStr;
            } else {
                this.TempSurplusAllTimeStr = strRemoveChinese2;
                str2 = strRemoveChinese2;
            }
            if (i10 == 0) {
                i10 = this.TempCarDirection;
            } else {
                this.TempCarDirection = i10;
            }
            if (i8 == 0) {
                i3 = this.TempIcon;
            } else {
                this.TempIcon = i8;
                i3 = i8;
            }
            if (TextUtils.isEmpty(string)) {
                str3 = this.TempNextWayName;
            } else {
                this.TempNextWayName = string;
                str3 = string;
            }
            AMapEntity aMapEntity = new AMapEntity(i4, i5, i11, i, i3, i2, getCarDirection(i10), str2, str, str3);
            AMapEntity aMapEntity2 = this.oldAMapEntity;
            if (aMapEntity2 == null || !aMapEntity2.equals(aMapEntity)) {
                this.oldAMapEntity = aMapEntity;
                ((MsgMgrInterfaceUtil) Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(context))).onAMapCallBack(aMapEntity);
                if (LogUtil.log5()) {
                    LogUtil.d("---------->" + aMapEntity.toString());
                    return;
                }
                return;
            }
            return;
        }
        if (LogUtil.log5()) {
            LogUtil.d("---------->action 是空置");
        }
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
}
