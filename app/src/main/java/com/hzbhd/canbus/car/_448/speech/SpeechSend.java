package com.hzbhd.canbus.car._448.speech;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.car._448.speech.SpeechAction;
import com.hzbhd.canbus.util.ContextUtil;


public class SpeechSend {
    private static String speech_rx_can = "speech.rx.can";
    private static String type = "type";
    private static String value = "value";

    public static void sendSpeechTTsBroadcast(Context context, String str) {
        Intent intent = new Intent(speech_rx_can);
        intent.putExtra("tx_action", "speech.speak");
        intent.putExtra("value", str);
        context.sendBroadcast(intent);
    }

    public static void windChange(int i) {
        sendSpeech("ac.windlevel.to", null, String.valueOf(i));
    }

    public static void tempChange(int i) {
        sendSpeech("ac.temperature.to", null, String.valueOf(i));
    }

    public static void acStatus(Boolean bool) {
        if (bool.booleanValue()) {
            sendSpeech("air.ac.on", null, null);
        } else {
            sendSpeech("air.ac.off", null, null);
        }
    }

    public static void acMode(SpeechAction.WindValueEnum windValueEnum) {
        sendSpeech("ac.mode", String.valueOf(windValueEnum), null);
    }

    public static void frontDefrost(Boolean bool) {
        if (bool.booleanValue()) {
            sendSpeech(SpeechAction.AC_DEFROST_FRONT_OPEN, null, null);
        } else {
            sendSpeech(SpeechAction.AC_DEFROST_FRONT_CLOSE, null, null);
        }
    }

    public static void behindDefrost(Boolean bool) {
        if (bool.booleanValue()) {
            sendSpeech(SpeechAction.AC_DEFROST_BEHIND_OPEN, null, null);
        } else {
            sendSpeech(SpeechAction.AC_DEFROST_BEHIND_CLOSE, null, null);
        }
    }

    public static void acLoop(Boolean bool) {
        if (bool.booleanValue()) {
            sendSpeech("air.in.out.cycle.on", null, null);
        } else {
            sendSpeech("air.in.out.cycle.off", null, null);
        }
    }

    private static void sendSpeech(String str, String str2, String str3) {
        Intent intent = new Intent(speech_rx_can);
        intent.putExtra("tx_action", str);
        if (str2 != null) {
            intent.putExtra(type, str2);
        }
        if (str3 != null) {
            intent.putExtra(value, str3);
        }
        ContextUtil.getInstance().getContext().sendBroadcast(intent);
    }
}
