package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.activity.SwcActivity;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.CustomKeyConfig;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.BaseUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.TimerTask;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class RealKeyUtil {
    private static final long KEY_PARAM_KNOB_INTERVAL = 150;
    public static final int K_HANGUP = 98;
    public static final int K_PICKUP = 97;
    static final String TAG = "RealKeyUtil";
    private static int downSum;
    private static boolean lastIsLongKey;
    private static boolean lastSendKeyIsNext;
    private static boolean lastSendKeyIsPre;
    private static HashMap<Integer, int[]> mCompoundKeyMap;
    private static Intent mIntent;
    private static TimerUtil mKnobTimer;
    private static int mPreCompoundKey;
    private static int mPreKeyStatus;
    private static int mPreKeyValue;
    private static int mPreWhatKey;
    private static SwcActivity mSwcActivity;
    private static int upSum;

    static {
        HashMap<Integer, int[]> map = new HashMap<>();
        mCompoundKeyMap = map;
        map.put(184, new int[]{3, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_PHONE_ON_OFF), new int[]{97, 98});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_PHONE_OFF_RETURN), new int[]{50, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_BAND_PICKUP), new int[]{62, 14});
        mCompoundKeyMap.put(200, new int[]{2, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_VOICE_PICKUP), new int[]{HotKeyConstant.K_SPEECH, 14});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_VOICE_HANGUP), new int[]{HotKeyConstant.K_SPEECH, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_BAND_HANGUP), new int[]{62, 15});
        mCompoundKeyMap.put(206, new int[]{21, 14});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_NEXT_HANGUP), new int[]{20, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_MUTE_PHONE_ON_OUT), new int[]{3, 14, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_UP_PICKUP), new int[]{45, 14});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_DOWN_HANGUP), new int[]{46, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_1_PICKUP), new int[]{33, 14});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_2_HANGUP), new int[]{34, 15});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_3_SHUFFLE), new int[]{35, 29});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_4_REPEAT), new int[]{36, 27});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_5_REPEAT), new int[]{37, 27});
        mCompoundKeyMap.put(Integer.valueOf(HotKeyConstant.K_6_SHUFFLE), new int[]{38, 29});
        upSum = 0;
        downSum = 0;
        lastSendKeyIsPre = false;
        lastSendKeyIsNext = false;
        lastIsLongKey = false;
    }

    public static void setSwcActivity(SwcActivity swcActivity) {
        mSwcActivity = swcActivity;
    }

    public static void realKeyClick1(Context context, int i, int i2, int i3) {
        RealKeyTimerManager.realKeyClick1(context, i, i2, i3);
        LogUtil.showLog("whatKey:" + i + " keyValue:" + i2 + " keyState:" + i3);
        if ((i == 8 || i == 7) && i3 == 2) {
            realKeyClick(context, i);
            return;
        }
        if (i == 45 && i3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(context, 21);
        }
        if (i == 46 && i3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(context, 20);
        }
        if (i2 == 0 && i3 == 0 && mPreKeyStatus == 1) {
            realKeyClick(context, mPreWhatKey);
        }
        mPreKeyStatus = i3;
        mPreWhatKey = i;
        mPreKeyValue = i2;
    }

    public static void realKeyClick2(Context context, int i, int i2, int i3) {
        RealKeyTimerManager.realKeyClick2(context, i, i2, i3);
        LogUtil.showLog("whatKey:" + i + " keyValue:" + i2 + " keyState:" + i3);
        if ((i == 8 || i == 7) && i3 == 2) {
            realKeyClick(context, i);
            return;
        }
        if (i == 45 && i3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(context, 21);
        }
        if (i == 46 && i3 == 2 && mPreKeyStatus == 1) {
            realKeyClick(context, 20);
        }
        if (i2 == mPreKeyValue && i3 == 0 && mPreKeyStatus == 1) {
            realKeyClick(context, mPreWhatKey);
        }
        mPreKeyStatus = i3;
        mPreWhatKey = i;
        mPreKeyValue = i2;
    }

    public static void realKeyClick3(Context context, int i, int i2, int i3) {
        RealKeyTimerManager.realKeyClick3(context, i, i2, i3);
        if (i3 == 0) {
            return;
        }
        LogUtil.showLog("whatKey:" + i + " keyValue:" + i2 + " keyState:" + i3);
        if (i3 == 1) {
            realKeyLongClick(context, i, 1);
            realKeyLongClick(context, i, 0);
        } else {
            runKnob(context, i, i3);
        }
    }

    public static void realKeyClick3_1(Context context, int i, int i2, int i3) {
        RealKeyTimerManager.realKeyClick3_1(context, i, i2, i3);
        LogUtil.showLog("whatKey:" + i + " keyValue:" + i2 + " keyState:" + i3);
        if (i == 7 || i == 8) {
            if (mKnobTimer == null) {
                mKnobTimer = new TimerUtil();
            }
            mKnobTimer.stopTimer();
            runKnob(context, i, i3);
        }
    }

    public static void realKeyClick3_2(Context context, int i, int i2, int i3) {
        RealKeyTimerManager.realKeyClick3_2(context, i, i2, i3);
        LogUtil.showLog("whatKey:" + i + " keyValue:" + i2 + " keyState:" + i3);
        if (i == 47 || i == 48) {
            if (mKnobTimer == null) {
                mKnobTimer = new TimerUtil();
            }
            mKnobTimer.stopTimer();
            runKnob(context, i, i3);
        }
    }

    private static void runKnob(final Context context, final int i, final int i2) {
        Log.i(TAG, "runKnob: in time: " + i2);
        if (mKnobTimer == null) {
            mKnobTimer = new TimerUtil();
        }
        mKnobTimer.stopTimer();
        mKnobTimer.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.util.RealKeyUtil.1
            int mTime = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i3 = this.mTime;
                this.mTime = i3 + 1;
                if (i3 >= i2) {
                    RealKeyUtil.mKnobTimer.stopTimer();
                    return;
                }
                Log.d(RealKeyUtil.TAG, "runKnob: " + i + ", " + this.mTime);
                RealKeyUtil.realKeyLongClick(context, i, 1);
                RealKeyUtil.realKeyLongClick(context, i, 0);
            }
        }, 0L, 150L);
    }

    public static void realKeyClick5(Context context, int i, int i2, int i3) {
        RealKeyTimerManager.realKeyClick5(context, i, i2, i3);
        LogUtil.showLog("fang", "5 whatKey:" + i + " keyValue:" + i2 + " keyState:" + i3);
        if (mPreWhatKey == 45 && i == 45 && i3 == 1) {
            upSum++;
            Log.d("fang", "lastSendKeyIsPre--------:" + lastSendKeyIsPre);
            if (upSum <= 5 || lastSendKeyIsPre) {
                return;
            }
            lastSendKeyIsPre = true;
            realKeyClick(context, 21);
            mPreWhatKey = 0;
            lastIsLongKey = true;
            return;
        }
        Log.d("fang", "lastSendKeyIsPre=false;");
        if (i == 0) {
            lastSendKeyIsPre = false;
        }
        int i4 = mPreWhatKey;
        if (i4 == 46 && i == 46 && i3 == 1) {
            int i5 = downSum + 1;
            downSum = i5;
            if (i5 <= 5 || lastSendKeyIsNext) {
                return;
            }
            lastSendKeyIsNext = true;
            realKeyClick(context, 20);
            mPreWhatKey = 0;
            lastIsLongKey = true;
            return;
        }
        if (i == 0) {
            lastSendKeyIsNext = false;
        }
        upSum = 0;
        downSum = 0;
        if (i4 == 45 && i == 0 && !lastIsLongKey) {
            realKeyClick(context, 45);
            lastIsLongKey = false;
        } else if (i4 == 46 && i == 0 && !lastIsLongKey) {
            realKeyClick(context, 46);
            lastIsLongKey = false;
        } else if (i3 == 1 && i != 45 && i != 46) {
            realKeyClick(context, i);
            lastIsLongKey = false;
        }
        if (i == 0 && lastIsLongKey) {
            lastIsLongKey = false;
        }
        mPreWhatKey = i;
    }

    public static void realKeyClick(Context context, int i) {
        if (i == 0) {
            return;
        }
        LogUtil.showLog("getKey:" + i);
        if (i == 196 || i == 183) {
            keyTime(context);
            return;
        }
        if (i == 187) {
            if (CanIdSpecialConfig.haveSpeechModule(CanbusConfig.INSTANCE.getCanType())) {
                KeyBroadcast.sendSpeechBroadcast(context);
                i = 187;
            } else {
                i = 3;
            }
        }
        if (i == 97) {
            i = 14;
        }
        if (i == 98) {
            i = 15;
        }
        LogUtil.showLog("setKey:" + i);
        Log.d("SWC-SEND-WHAT-KEY？", "0x" + Integer.toHexString(i));
        SendKeyManager.getInstance().setKeyEvent(i, HotKeyConstant.KeyState.CLICK, true);
    }

    public static void realKeyLongClick(Context context, int i, int i2) {
        int i3;
        RealKeyTimerManager.realKeyLongClick(context, i, i2);
        Log.i(TAG, "realKeyLongClick1 keyState:" + i2 + " mPreKeyStatus:" + mPreKeyStatus);
        if (i2 == 0 && ((i3 = mPreKeyStatus) == 1 || i3 == 2)) {
            LongClickHelper.getInstance().keyUp(context, mPreWhatKey);
        } else if ((i2 == 1 || i2 == 2) && mPreKeyStatus == 0) {
            LongClickHelper.getInstance().keyDown(context, i);
        }
        mPreWhatKey = i;
        mPreKeyStatus = i2;
    }

    public static void compoundKey(Context context, int[] iArr, int i) {
        if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("compoundKey: " + i + "," + Arrays.toString(iArr));
        }
        for (int length = iArr.length - 1; length >= 0; length--) {
            if (isSatisfied(iArr[length])) {
                realKeyLongClick(context, iArr[length], i);
                return;
            }
        }
    }

    private static boolean isSatisfied(int i) {
        if (i == 14) {
            return "IN_CALLING".equals(MediaShareData.Bluetooth.INSTANCE.getCallState());
        }
        if (i != 15) {
            if (i == 27 || i == 29) {
                SourceConstantsDef.SOURCE_ID type = SourceConstantsDef.SOURCE_ID.getType(MediaShareData.Source.INSTANCE.getId());
                return type == SourceConstantsDef.SOURCE_ID.MUSIC || type == SourceConstantsDef.SOURCE_ID.VIDEO;
            }
            if (i != 98) {
                return true;
            }
            String callState = MediaShareData.Bluetooth.INSTANCE.getCallState();
            callState.hashCode();
            return callState.equals("OUT_CALLING") || callState.equals("CALLING");
        }
        String callState2 = MediaShareData.Bluetooth.INSTANCE.getCallState();
        callState2.hashCode();
        switch (callState2) {
            case "OUT_CALLING":
            case "IN_CALLING":
            case "CALLING":
                return true;
            default:
                return false;
        }
    }

    public static boolean isCompoundKey(Context context, int i, int i2) {
        if (i2 == 1 && mCompoundKeyMap.containsKey(Integer.valueOf(i))) {
            compoundKey(context, mCompoundKeyMap.get(Integer.valueOf(i)), i2);
        } else {
            if (i2 != 0 || !mCompoundKeyMap.containsKey(Integer.valueOf(mPreCompoundKey))) {
                return false;
            }
            realKeyLongClick(context, i, i2);
        }
        mPreCompoundKey = i;
        return true;
    }

    public static void keyTime(Context context) {
        Intent intent = new Intent("android.intent.action.QUICK_CLOCK");
        mIntent = intent;
        intent.setFlags(268435456);
        context.startActivity(mIntent);
    }

    static class LongClickHelper {
        private static final long KEY_PARAM_LONG_CLICK_CONTINUE_INTERVAL = 166;
        private static final long KEY_PARAM_LONG_CLICK_TIME = 500;
        private static LongClickHelper instance;
        private boolean mLongClickFlag;
        private Runnable mRunnable;
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private final String mDeviceInfo = ConfigUtil.getDeviceId();

        LongClickHelper() {
        }

        public static LongClickHelper getInstance() {
            if (instance == null) {
                instance = new LongClickHelper();
            }
            return instance;
        }

        public void keyDown(final Context context, final int i) {
            if (RealKeyUtil.mSwcActivity == null) {
                Runnable runnable = new Runnable() { // from class: com.hzbhd.canbus.util.RealKeyUtil$LongClickHelper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m1177xdf76182a(context, i);
                    }
                };
                this.mRunnable = runnable;
                this.mHandler.postDelayed(runnable, KEY_PARAM_LONG_CLICK_TIME);
            }
        }

        public void keyUp(Context context, final int i) {
            if (RealKeyUtil.mSwcActivity == null) {
                Runnable runnable = this.mRunnable;
                if (runnable != null) {
                    this.mHandler.removeCallbacks(runnable);
                }
                if (!this.mLongClickFlag && !((MsgMgrInterfaceUtil) Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(context))).customShortClick(context, i)) {
                    RealKeyUtil.realKeyClick(context, CustomKeyConfig.INSTANCE.getShortKey(i));
                }
                this.mLongClickFlag = false;
                return;
            }
            BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.util.RealKeyUtil$LongClickHelper$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RealKeyUtil.LongClickHelper.lambda$keyUp$1(i);
                }
            });
        }

        static /* synthetic */ Unit lambda$keyUp$1(int i) {
            RealKeyUtil.mSwcActivity.learnKey(i);
            return null;
        }

        /* renamed from: startLongClick, reason: merged with bridge method [inline-methods] */
        public void m1177xdf76182a(Context context, int i) {
            if (com.hzbhd.util.LogUtil.log5()) {
                com.hzbhd.util.LogUtil.d("startLongClick: " + i);
            }
            this.mLongClickFlag = true;
            int shortKey = CustomKeyConfig.INSTANCE.getShortKey(i);
            if (((MsgMgrInterfaceUtil) Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(context))).customLongClick(context, shortKey)) {
                return;
            }
            if (shortKey == 7 || shortKey == 8 || shortKey == 22 || shortKey == 23 || shortKey == 47 || shortKey == 48) {
                runKey(context, shortKey);
                return;
            }
            int longKey = CustomKeyConfig.INSTANCE.getLongKey(i);
            RealKeyUtil.realKeyClick(context, longKey);
            this.mLongClickFlag = longKey != 0;
        }

        private void runKey(final Context context, final int i) {
            Log.i(RealKeyUtil.TAG, "runKey: in");
            final TimerUtil timerUtil = new TimerUtil();
            timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.util.RealKeyUtil.LongClickHelper.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (LongClickHelper.this.mLongClickFlag) {
                        Log.d(RealKeyUtil.TAG, "runKey: " + i);
                        RealKeyUtil.realKeyClick(context, i);
                    } else {
                        timerUtil.stopTimer();
                    }
                }
            }, 0L, KEY_PARAM_LONG_CLICK_CONTINUE_INTERVAL);
        }

        private boolean isUpDownLongClickDisable() {
            return Util.contains(this.mDeviceInfo, "XPH");
        }
    }
}
