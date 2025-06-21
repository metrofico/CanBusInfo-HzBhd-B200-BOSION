package com.hzbhd.canbus.car._481;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hzbhd.canbus.CanbusMsgSender;


public class LanguageReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) 9, (byte) getLanguage(context)});
        MsgMgr.setLanguage(context);
    }

    public static int getLanguage(Context context) {
        if (context == null) {
            return 1;
        }

        String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        return language.equalsIgnoreCase("zh") ? 0 : 1;
    }
}
