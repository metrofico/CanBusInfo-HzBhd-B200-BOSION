package com.hzbhd.canbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.util.amap.AMapUtils;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class AMapBroadcastReceiver extends BroadcastReceiver {
    private static int resultDirection(int i, int i2) {
        return i <= i2 ? 8 - i : i - i2;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && action.equals(AMapUtils.AUTONAVI_STANDARD_BROADCAST_SEND)) {
            MsgMgrFactory.getCanMsgMgrUtil(context).aMapCallBack(intent.getExtras());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getCarDirection(int r11, java.lang.String r12) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            r1 = 6
            r2 = 4
            r3 = 2
            r4 = 0
            if (r0 != 0) goto L31
            java.lang.String r0 = "东"
            boolean r0 = r12.equals(r0)
            if (r0 == 0) goto L13
            goto L31
        L13:
            java.lang.String r0 = "南"
            boolean r0 = r12.equals(r0)
            if (r0 == 0) goto L1d
            r12 = r3
            goto L32
        L1d:
            java.lang.String r0 = "西"
            boolean r0 = r12.equals(r0)
            if (r0 == 0) goto L27
            r12 = r2
            goto L32
        L27:
            java.lang.String r0 = "北"
            boolean r12 = r12.equals(r0)
            if (r12 == 0) goto L31
            r12 = r1
            goto L32
        L31:
            r12 = r4
        L32:
            double r5 = (double) r11
            r7 = 4644644978981601280(0x4075180000000000, double:337.5)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L40
            r0 = 360(0x168, float:5.04E-43)
            if (r11 <= r0) goto L4b
        L40:
            r9 = 4627026404658118656(0x4036800000000000, double:22.5)
            if (r11 <= 0) goto L51
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L51
        L4b:
            r11 = 7
            int r11 = resultDirection(r11, r12)
            return r11
        L51:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            r9 = 4634450307168862208(0x4050e00000000000, double:67.5)
            if (r11 <= 0) goto L65
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L65
            r11 = 8
            int r11 = resultDirection(r11, r12)
            return r11
        L65:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            r9 = 4637616900656857088(0x405c200000000000, double:112.5)
            if (r11 <= 0) goto L78
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L78
            r11 = 1
            int r11 = resultDirection(r11, r12)
            return r11
        L78:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            r9 = 4639745555168231424(0x4063b00000000000, double:157.5)
            if (r11 <= 0) goto L8a
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L8a
            int r11 = resultDirection(r3, r12)
            return r11
        L8a:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            r9 = 4641328851912228864(0x4069500000000000, double:202.5)
            if (r11 <= 0) goto L9d
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L9d
            r11 = 3
            int r11 = resultDirection(r11, r12)
            return r11
        L9d:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            r9 = 4642912148656226304(0x406ef00000000000, double:247.5)
            if (r11 <= 0) goto Laf
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto Laf
            int r11 = resultDirection(r2, r12)
            return r11
        Laf:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            r2 = 4643853330609602560(0x4072480000000000, double:292.5)
            if (r11 <= 0) goto Lc2
            int r11 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r11 >= 0) goto Lc2
            r11 = 5
            int r11 = resultDirection(r11, r12)
            return r11
        Lc2:
            int r11 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r11 <= 0) goto Lcf
            int r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r11 >= 0) goto Lcf
            int r11 = resultDirection(r1, r12)
            return r11
        Lcf:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.receiver.AMapBroadcastReceiver.getCarDirection(int, java.lang.String):int");
    }

    private String removeChinese(String str) {
        return TextUtils.isEmpty(str) ? "" : Pattern.compile("[一-龥]").matcher(str.trim()).replaceAll("");
    }

    private String getSurplus(int i) {
        int i2 = i / 3600;
        int i3 = i - (i2 * 3600);
        int i4 = i3 / 60;
        if (i3 - (i4 * 60) >= 30) {
            i4++;
        }
        return i2 + ":" + i4;
    }
}
