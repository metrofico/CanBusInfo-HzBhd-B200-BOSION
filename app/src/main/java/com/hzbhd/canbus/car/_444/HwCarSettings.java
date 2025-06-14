package com.hzbhd.canbus.car._444;

import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HwCarSettings {
    public static final String ACCELERATOR_PERFORMANCE = "ACCELERATOR_PERFORMANCE";
    public static final String AUXILIARY_BRAKING = "AUXILIARY_BRAKING";
    public static final String BATTERY_WATER = "BATTERY_WATER";
    public static final String ENERGY_RECOVERY = "ENERGY_RECOVERY";
    public static final String LOCK = "LOCK";
    public static final String MOTOR_WATER = "MOTOR_WATER";
    public static final String REVERSE_SPEED = "REVERSE_SPEED";
    public static final String SPEED = "SPEED";
    public static final String VCU = "VCU";
    public static final String WARM_AIR_WATER = "WARM_AIR_WATER";
    public static final String WORK_MOTOR_WATER = "WORK_MOTOR_WATER";
    private static int acceleratorPerformance = -1;
    private static int auxiliaryBraking = -1;
    private static int batteryWater = -1;
    private static int energyRecovery = -1;
    private static int lock = -1;
    private static int motorWater = -1;
    private static int reverseSpeed = -1;
    private static int sVcu = -1;
    private static int speed = -1;
    private static int warmAirWater = 0;
    private static int workMotorWater = -1;

    public void setData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            cheData(jSONObject.getString("TAG"), Integer.parseInt(jSONObject.getString("VALUE")));
        } catch (JSONException unused) {
        }
    }

    private void cheData(String str, int i) {
        str.hashCode();
        switch (str) {
            case "ACCELERATOR_PERFORMANCE":
                acceleratorPerformance = i;
                sendCmd();
                break;
            case "BATTERY_WATER":
                batteryWater = i;
                sendCmd();
                break;
            case "WORK_MOTOR_WATER":
                workMotorWater = i;
                sendCmd();
                break;
            case "VCU":
                sVcu = i;
                sendCmd();
                break;
            case "LOCK":
                lock = i;
                sendCmd();
                break;
            case "SPEED":
                speed = i;
                sendCmd();
                break;
            case "AUXILIARY_BRAKING":
                auxiliaryBraking = i;
                sendCmd();
                break;
            case "ENERGY_RECOVERY":
                energyRecovery = i;
                sendCmd();
                break;
            case "WARM_AIR_WATER":
                warmAirWater = i;
                sendCmd();
                break;
            case "REVERSE_SPEED":
                reverseSpeed = i;
                sendCmd();
                break;
            case "MOTOR_WATER":
                motorWater = i;
                sendCmd();
                break;
        }
    }

    private void sendCmd() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        Log.d("SWITCH_STATE", "-----------------------------------------------------------");
        Log.d("SWITCH_STATE", "sVcu=" + (sVcu != -1));
        Log.d("SWITCH_STATE", "acceleratorPerformance=" + (acceleratorPerformance != -1));
        Log.d("SWITCH_STATE", "energyRecovery=" + (energyRecovery != -1));
        Log.d("SWITCH_STATE", "auxiliaryBraking=" + (auxiliaryBraking != -1));
        Log.d("SWITCH_STATE", "motorWater=" + (motorWater != -1));
        Log.d("SWITCH_STATE", "batteryWater=" + (batteryWater != -1));
        Log.d("SWITCH_STATE", "workMotorWater=" + (workMotorWater != -1));
        Log.d("SWITCH_STATE", "warmAirWater=" + (warmAirWater != -1));
        Log.d("SWITCH_STATE", "reverseSpeed=" + (reverseSpeed != -1));
        Log.d("SWITCH_STATE", "speed=" + (speed != -1));
        Log.d("SWITCH_STATE", "lock=" + (lock != -1));
        int i9 = sVcu;
        if (i9 == -1 || (i = acceleratorPerformance) == -1 || (i2 = energyRecovery) == -1 || (i3 = auxiliaryBraking) == -1 || (i4 = motorWater) == -1 || (i5 = batteryWater) == -1 || (i6 = workMotorWater) == -1 || (i7 = warmAirWater) == -1 || (i8 = reverseSpeed) == -1 || speed == -1 || lock == -1) {
            return;
        }
        int i10 = (i & 255) << 1;
        byte b = (byte) 0;
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, -13, 23, (byte) ((i9 & 255) | i10 | ((i2 & 255) << 4) | ((i3 & 255) << 6)), (byte) ((i8 & 255) << 2), (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, i7, i6, i5, i4), b, b, (byte) speed, (byte) lock});
    }
}
