package com.hzbhd.canbus.car._29;


public class CarState {
    private static boolean ACCState = false;
    private static boolean ILLState = false;
    private static boolean PARKState = false;
    private static boolean REVState = false;

    public static boolean isPARKState() {
        return PARKState;
    }

    public static void setPARKState(boolean z) {
        PARKState = z;
    }

    public static boolean isREVState() {
        return REVState;
    }

    public static void setREVState(boolean z) {
        REVState = z;
    }

    public static boolean isILLState() {
        return ILLState;
    }

    public static void setILLState(boolean z) {
        ILLState = z;
    }

    public static boolean isACCState() {
        return ACCState;
    }

    public static void setACCState(boolean z) {
        ACCState = z;
    }
}
