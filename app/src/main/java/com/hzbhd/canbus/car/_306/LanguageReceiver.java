package com.hzbhd.canbus.car._306;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgSender;


public class LanguageReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) 0, (byte) 0, (byte) getLanguage(context)});
        MsgMgr.setLanguage(context);
    }

    public static int getLanguage(Context context) {
        if (context == null) {
            return 2;
        }
        String country = context.getResources().getConfiguration().getLocales().get(0).getCountry();
        if (country.endsWith("CN")) {
            return 0;
        }
        return (country.equals("TW") || country.equals("HK") || country.equals("MO")) ? 1 : 2;
    }
}
