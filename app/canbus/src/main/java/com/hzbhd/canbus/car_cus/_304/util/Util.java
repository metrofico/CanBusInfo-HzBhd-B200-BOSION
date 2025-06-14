package com.hzbhd.canbus.car_cus._304.util;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;

/* loaded from: classes2.dex */
public class Util {
    public static void sendAvmCommand(int i) {
        FutureUtil.instance.reqMcuKey(new byte[]{-47, (byte) i});
        CanbusMsgSender.sendMsg(new byte[]{22, -80, (byte) (i << 6), 0});
    }
}
