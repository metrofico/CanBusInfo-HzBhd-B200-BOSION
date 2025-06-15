package com.hzbhd.canbus.car._213;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.car._213.MsgMgr;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TouchpadEvents;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.config.constant.ClassName;
import com.hzbhd.config.constant.PackageName;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.service.ServiceConstants;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import nfore.android.bt.res.NfDef;




public final class MsgMgr extends AbstractMsgMgr {
    private static final boolean isLastLongClick = false;
    private static final int lastPanelSt = 0;
    private static final int lastPanelkey = 0;
    private static final int lastSwcKey = 0;
    private static final int lastSwcSt = 0;
    private static final int longClickCount = 0;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private int mData0_7;
    private boolean mEnable06_1;
    private boolean mEnable06_2;
    private boolean mEnable06_3;
    private boolean mEnable06_4;
    private final int mKonbCount;
    private final int mKonbSelCount;
    private final int mLastKonbCount;
    private final int mLastSelKonbCount;
    private TimerHelper mRequestTimer;
    private int mSelKnobValue;
    private int mStartTime;
    private int mVolKnobValue;
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static final boolean mIsKonbClockwise = true;
    private static final boolean mIsKonbSelClockwise = true;
    private String mFuelUnit = "mpg";
    private int mData0_26 = 15;
    private int mData2_47 = 5;
    private int mData2_03 = 5;

    private final int getIndexBy2Bit(boolean bit) {
        return bit ? 1 : 0;
    }

    private final int getMsbLsbResult(int MSB, int LSB) {
        return ((MSB & 255) << 8) | (LSB & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {

        super.initCommand(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        int currentEachCanId = getCurrentEachCanId();
        if (currentEachCanId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 8, 10});
        } else if (currentEachCanId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 14, 10});
        }
        initAmplifierData(context);
    }

    private final void initAmplifierData(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final byte[][] bArr = {new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume}, new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 16)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 16)}, new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 10)}, new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 10)}};
        final TimerHelper timerHelper = new TimerHelper();
        timerHelper.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._213.MsgMgr.initAmplifierData.1
            private int i;

            public final int getI() {
                return this.i;
            }

            public final void setI(int i) {
                this.i = i;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                } else {
                    timerHelper.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {


        super.canbusInfoChange(context, canbusInfo);
        this.mCanBusInfoByte = canbusInfo;
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        this.mCanBusInfoInt = byteArrayToIntArray;
        int[] iArr = null;
        if (byteArrayToIntArray == null) {

            byteArrayToIntArray = null;
        }
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setTrack();
            setSwcKey();
            int[] iArr2 = this.mCanBusInfoInt;
            if (iArr2 == null) {

            } else {
                iArr = iArr2;
            }
            updateSpeedInfo(iArr[3]);
            return;
        }
        if (i == 18) {
            if (isDoorMsgReturn(canbusInfo)) {
                return;
            }
            setDoorData();
            return;
        }
        if (i == 20) {
            setDriveData();
            return;
        }
        if (i == 21) {
            setDriveData2();
            return;
        }
        if (i == 33) {
            set0x21PanelKeyData(context);
            return;
        }
        if (i == 34) {
            set0x22PanelKnobData(context);
            return;
        }
        if (i == 37) {
            touch0x25();
            return;
        }
        if (i == 38) {
            setCarType();
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(canbusInfo)) {
                return;
            }
            setAirData0x31();
            return;
        }
        if (i == 50) {
            setCarInfo();
            return;
        }
        if (i == 65) {
            setRadar();
            return;
        }
        if (i == 174) {
            setOriginalCarDeviceInfo();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 120) {
            settingInfo();
            return;
        }
        if (i == 121) {
            setHudData0x79();
        } else if (i == 165) {
            setOriginalCarDeviceInfo2();
        } else {
            if (i != 166) {
                return;
            }
            setAmplifierData();
        }
    }

    private final void setCarInfo() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item != null) {
            int[] iArr = this.mCanBusInfoInt;
            if (iArr == null) {

                iArr = null;
            }
            int i = iArr[4];
            int[] iArr2 = this.mCanBusInfoInt;
            if (iArr2 == null) {

                iArr2 = null;
            }
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(i, iArr2[5])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item2 != null) {
            int[] iArr3 = this.mCanBusInfoInt;
            if (iArr3 == null) {

                iArr3 = null;
            }
            int i2 = iArr3[6];
            int[] iArr4 = this.mCanBusInfoInt;
            if (iArr4 == null) {

                iArr4 = null;
            }
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(i2, iArr4[7])));
        }
        updateDriveDataActivity(null);
    }

    private final void touch0x25() {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = null;
        if (iArr == null) {

            iArr = null;
        }
        int i = iArr[3];
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {

            iArr3 = null;
        }
        int msbLsbResult = getMsbLsbResult(i, iArr3[4]);
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {

            iArr4 = null;
        }
        int i2 = iArr4[5];
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {

        } else {
            iArr2 = iArr5;
        }
        TouchpadEvents.putXY(context, msbLsbResult, getMsbLsbResult(i2, iArr2[6]), new C00391());
    }

    /* compiled from: MsgMgr.kt */
    
    /* renamed from: com.hzbhd.canbus.car._213.MsgMgr$touch0x25$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00391 implements TouchpadEvents.Events {
        C00391() {
        }

        @Override // com.hzbhd.canbus.util.TouchpadEvents.Events
        public void goLeft() {
            MsgMgr msgMgr = MsgMgr.this;
            msgMgr.realKeyClick4(msgMgr.mContext, 45);
        }

        @Override // com.hzbhd.canbus.util.TouchpadEvents.Events
        public void goRight() {
            MsgMgr msgMgr = MsgMgr.this;
            msgMgr.realKeyClick4(msgMgr.mContext, 46);
        }

        @Override // com.hzbhd.canbus.util.TouchpadEvents.Events
        public void goUp() {
            final MsgMgr msgMgr = MsgMgr.this;
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._213.MsgMgr$touch0x25$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    MsgMgr.C00391.m308goUp$lambda0(msgMgr);
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: goUp$lambda-0, reason: not valid java name */
        public static final void m308goUp$lambda0(MsgMgr this$0) throws InterruptedException {

            for (int i = 0; i < 5; i++) {
                try {
                    this$0.realKeyClick4(this$0.mContext, 7);
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // com.hzbhd.canbus.util.TouchpadEvents.Events
        public void goDown() {
            final MsgMgr msgMgr = MsgMgr.this;
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._213.MsgMgr$touch0x25$1$goDown$1
                @Override // java.lang.Runnable
                public void run() throws InterruptedException {
                    for (int i = 0; i < 5; i++) {
                        try {
                            MsgMgr msgMgr2 = msgMgr;
                            msgMgr2.realKeyClick4(msgMgr2.mContext, 8);
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        @Override // com.hzbhd.canbus.util.TouchpadEvents.Events
        public void enter() {
            MsgMgr msgMgr = MsgMgr.this;
            msgMgr.realKeyClick4(msgMgr.mContext, 49);
        }
    }

    public final void touchpadView() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._213.MsgMgr.touchpadView.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new TouchpadEvents().showAdjustView(MsgMgr.this.getActivity());
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void setTrack() {
        /*
            r9 = this;
            byte[] r0 = r9.mCanBusInfoByte
            java.lang.String r1 = "mCanBusInfoByte"
            r2 = 0
            if (r0 != 0) goto Lb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        Lb:
            r3 = 9
            r0 = r0[r3]
            byte[] r3 = r9.mCanBusInfoByte
            if (r3 != 0) goto L17
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r3 = r2
        L17:
            r1 = 8
            r1 = r3[r1]
            r3 = 540(0x21c, float:7.57E-43)
            r4 = 16
            r5 = 0
            int r0 = com.hzbhd.canbus.util.TrackInfoUtil.getTrackAngle0(r0, r1, r5, r3, r4)
            com.hzbhd.canbus.ui_datas.GeneralParkData.trackAngle = r0
            android.content.Context r0 = r9.mContext
            r9.updateParkUi(r2, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r0 = (java.util.List) r0
            com.hzbhd.canbus.entity.DriverUpdateEntity r1 = new com.hzbhd.canbus.entity.DriverUpdateEntity
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int[] r4 = r9.mCanBusInfoInt
            java.lang.String r6 = "mCanBusInfoInt"
            if (r4 != 0) goto L43
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r4 = r2
        L43:
            r7 = 3
            r4 = r4[r7]
            java.lang.StringBuilder r3 = r3.append(r4)
            android.content.Context r4 = r9.mContext
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            android.content.res.Resources r4 = r4.getResources()
            r7 = 2131770215(0x7f103b67, float:1.9171727E38)
            java.lang.String r4 = r4.getString(r7)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = 2
            r1.<init>(r4, r5, r3)
            r0.add(r1)
            int[] r1 = r9.mCanBusInfoInt
            if (r1 != 0) goto L71
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r1 = r2
        L71:
            r3 = 7
            r1 = r1[r3]
            r5 = 100
            r7 = 1
            if (r1 <= 0) goto Lab
            int[] r1 = r9.mCanBusInfoInt
            if (r1 != 0) goto L81
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r1 = r2
        L81:
            r1 = r1[r3]
            if (r5 <= r1) goto Lab
            com.hzbhd.canbus.entity.DriverUpdateEntity r1 = new com.hzbhd.canbus.entity.DriverUpdateEntity
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            int[] r8 = r9.mCanBusInfoInt
            if (r8 != 0) goto L94
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r8 = r2
        L94:
            r3 = r8[r3]
            java.lang.StringBuilder r3 = r5.append(r3)
            java.lang.String r5 = ""
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r3 = r3.toString()
            r1.<init>(r4, r7, r3)
            r0.add(r1)
            goto Ld8
        Lab:
            int[] r1 = r9.mCanBusInfoInt
            if (r1 != 0) goto Lb3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r1 = r2
        Lb3:
            r1 = r1[r3]
            if (r1 != 0) goto Lc2
            com.hzbhd.canbus.entity.DriverUpdateEntity r1 = new com.hzbhd.canbus.entity.DriverUpdateEntity
            java.lang.String r3 = "OFF"
            r1.<init>(r4, r7, r3)
            r0.add(r1)
            goto Ld8
        Lc2:
            int[] r1 = r9.mCanBusInfoInt
            if (r1 != 0) goto Lca
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r1 = r2
        Lca:
            r1 = r1[r3]
            if (r1 != r5) goto Ld8
            com.hzbhd.canbus.entity.DriverUpdateEntity r1 = new com.hzbhd.canbus.entity.DriverUpdateEntity
            java.lang.String r3 = "MAX"
            r1.<init>(r4, r7, r3)
            r0.add(r1)
        Ld8:
            r9.updateGeneralDriveData(r0)
            r9.updateDriveDataActivity(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._213.MsgMgr.setTrack():void");
    }

    private final void setVersionInfo() {
        Context context = this.mContext;
        byte[] bArr = this.mCanBusInfoByte;
        if (bArr == null) {

            bArr = null;
        }
        updateVersionInfo(context, getVersionStr(bArr));
    }

    private final void setAirData0x31() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = null;
        if (iArr == null) {

            iArr = null;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(iArr[2]);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {

            iArr3 = null;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr3[2]);
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {

            iArr4 = null;
        }
        GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(iArr4[3], 6, 2);
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {

            iArr5 = null;
        }
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(iArr5[3]);
        int[] iArr6 = this.mCanBusInfoInt;
        if (iArr6 == null) {

            iArr6 = null;
        }
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(iArr6[3]);
        int[] iArr7 = this.mCanBusInfoInt;
        if (iArr7 == null) {

            iArr7 = null;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr7[3]);
        int[] iArr8 = this.mCanBusInfoInt;
        if (iArr8 == null) {

            iArr8 = null;
        }
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(iArr8[4]);
        int[] iArr9 = this.mCanBusInfoInt;
        if (iArr9 == null) {

            iArr9 = null;
        }
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(iArr9[4]);
        int[] iArr10 = this.mCanBusInfoInt;
        if (iArr10 == null) {

            iArr10 = null;
        }
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr10[4], 0, 2);
        int[] iArr11 = this.mCanBusInfoInt;
        if (iArr11 == null) {

            iArr11 = null;
        }
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr11[4], 2, 2);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        int[] iArr12 = this.mCanBusInfoInt;
        if (iArr12 == null) {

            iArr12 = null;
        }
        if (DataHandleUtils.getBoolBit6(iArr12[6])) {
            GeneralAirData.front_left_blow_foot = true;
        }
        int[] iArr13 = this.mCanBusInfoInt;
        if (iArr13 == null) {

            iArr13 = null;
        }
        if (DataHandleUtils.getBoolBit5(iArr13[6])) {
            GeneralAirData.front_left_blow_head = true;
        }
        int[] iArr14 = this.mCanBusInfoInt;
        if (iArr14 == null) {

            iArr14 = null;
        }
        if (DataHandleUtils.getBoolBit4(iArr14[6])) {
            GeneralAirData.front_left_blow_window = true;
        }
        int[] iArr15 = this.mCanBusInfoInt;
        if (iArr15 == null) {

            iArr15 = null;
        }
        if (DataHandleUtils.getBoolBit6(iArr15[7])) {
            GeneralAirData.front_right_blow_foot = true;
        }
        int[] iArr16 = this.mCanBusInfoInt;
        if (iArr16 == null) {

            iArr16 = null;
        }
        if (DataHandleUtils.getBoolBit5(iArr16[7])) {
            GeneralAirData.front_right_blow_head = true;
        }
        int[] iArr17 = this.mCanBusInfoInt;
        if (iArr17 == null) {

            iArr17 = null;
        }
        if (DataHandleUtils.getBoolBit4(iArr17[7])) {
            GeneralAirData.front_right_blow_window = true;
        }
        int[] iArr18 = this.mCanBusInfoInt;
        if (iArr18 == null) {

            iArr18 = null;
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr18[6], 0, 4);
        int[] iArr19 = this.mCanBusInfoInt;
        if (iArr19 == null) {

            iArr19 = null;
        }
        GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr19[7], 0, 4);
        int[] iArr20 = this.mCanBusInfoInt;
        if (iArr20 == null) {

            iArr20 = null;
        }
        GeneralAirData.front_left_temperature = resolveFrontAirTemp(iArr20[8]);
        int[] iArr21 = this.mCanBusInfoInt;
        if (iArr21 == null) {

        } else {
            iArr2 = iArr21;
        }
        GeneralAirData.front_right_temperature = resolveFrontAirTemp(iArr2[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private final void setRadar() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        if (iArr[13] == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr2 = this.mCanBusInfoInt;
            if (iArr2 == null) {

                iArr2 = null;
            }
            int i = iArr2[2];
            int[] iArr3 = this.mCanBusInfoInt;
            if (iArr3 == null) {

                iArr3 = null;
            }
            int i2 = iArr3[3];
            int[] iArr4 = this.mCanBusInfoInt;
            if (iArr4 == null) {

                iArr4 = null;
            }
            int i3 = iArr4[4];
            int[] iArr5 = this.mCanBusInfoInt;
            if (iArr5 == null) {

                iArr5 = null;
            }
            RadarInfoUtil.setRearRadarLocationData(3, i, i2, i3, iArr5[5]);
            int[] iArr6 = this.mCanBusInfoInt;
            if (iArr6 == null) {

                iArr6 = null;
            }
            int i4 = iArr6[6];
            int[] iArr7 = this.mCanBusInfoInt;
            if (iArr7 == null) {

                iArr7 = null;
            }
            int i5 = iArr7[7];
            int[] iArr8 = this.mCanBusInfoInt;
            if (iArr8 == null) {

                iArr8 = null;
            }
            int i6 = iArr8[8];
            int[] iArr9 = this.mCanBusInfoInt;
            if (iArr9 == null) {

                iArr9 = null;
            }
            RadarInfoUtil.setFrontRadarLocationData(3, i4, i5, i6, iArr9[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            return;
        }
        GeneralParkData.radar_distance_disable = new int[]{0, 255};
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr10 = this.mCanBusInfoInt;
        if (iArr10 == null) {

            iArr10 = null;
        }
        int i7 = iArr10[2];
        int[] iArr11 = this.mCanBusInfoInt;
        if (iArr11 == null) {

            iArr11 = null;
        }
        int i8 = iArr11[3];
        int[] iArr12 = this.mCanBusInfoInt;
        if (iArr12 == null) {

            iArr12 = null;
        }
        int i9 = iArr12[4];
        int[] iArr13 = this.mCanBusInfoInt;
        if (iArr13 == null) {

            iArr13 = null;
        }
        RadarInfoUtil.setRearRadarDistanceData(i7, i8, i9, iArr13[5]);
        int[] iArr14 = this.mCanBusInfoInt;
        if (iArr14 == null) {

            iArr14 = null;
        }
        int i10 = iArr14[6];
        int[] iArr15 = this.mCanBusInfoInt;
        if (iArr15 == null) {

            iArr15 = null;
        }
        int i11 = iArr15[7];
        int[] iArr16 = this.mCanBusInfoInt;
        if (iArr16 == null) {

            iArr16 = null;
        }
        int i12 = iArr16[8];
        int[] iArr17 = this.mCanBusInfoInt;
        if (iArr17 == null) {

            iArr17 = null;
        }
        RadarInfoUtil.setFrontRadarDistanceData(i10, i11, i12, iArr17[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private final void settingInfo() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit2(iArr[10]));
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {

            iArr2 = null;
        }
        boolean boolBit7 = DataHandleUtils.getBoolBit7(iArr2[5]);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {

            iArr3 = null;
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr3[9], 5, 3);
        if (intFromByteWithBit > 5) {
            intFromByteWithBit = 5;
        }
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {

            iArr4 = null;
        }
        boolean boolBit72 = DataHandleUtils.getBoolBit7(iArr4[4]);
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {

            iArr5 = null;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr5[9], 0, 2);
        int[] iArr6 = this.mCanBusInfoInt;
        if (iArr6 == null) {

            iArr6 = null;
        }
        boolean boolBit4 = DataHandleUtils.getBoolBit4(iArr6[4]);
        int[] iArr7 = this.mCanBusInfoInt;
        if (iArr7 == null) {

            iArr7 = null;
        }
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr7[10], 6, 2);
        int[] iArr8 = this.mCanBusInfoInt;
        if (iArr8 == null) {

            iArr8 = null;
        }
        boolean boolBit3 = DataHandleUtils.getBoolBit3(iArr8[4]);
        int[] iArr9 = this.mCanBusInfoInt;
        if (iArr9 == null) {

            iArr9 = null;
        }
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(iArr9[10]));
        int[] iArr10 = this.mCanBusInfoInt;
        if (iArr10 == null) {

            iArr10 = null;
        }
        boolean boolBit2 = DataHandleUtils.getBoolBit2(iArr10[4]);
        int[] iArr11 = this.mCanBusInfoInt;
        if (iArr11 == null) {

            iArr11 = null;
        }
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(iArr11[9]));
        int[] iArr12 = this.mCanBusInfoInt;
        if (iArr12 == null) {

            iArr12 = null;
        }
        boolean boolBit5 = DataHandleUtils.getBoolBit5(iArr12[4]);
        int[] iArr13 = this.mCanBusInfoInt;
        if (iArr13 == null) {

            iArr13 = null;
        }
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(iArr13[10]));
        int[] iArr14 = this.mCanBusInfoInt;
        if (iArr14 == null) {

            iArr14 = null;
        }
        boolean boolBit1 = DataHandleUtils.getBoolBit1(iArr14[4]);
        int[] iArr15 = this.mCanBusInfoInt;
        if (iArr15 == null) {

            iArr15 = null;
        }
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(iArr15[10]));
        int[] iArr16 = this.mCanBusInfoInt;
        if (iArr16 == null) {

            iArr16 = null;
        }
        boolean boolBit0 = DataHandleUtils.getBoolBit0(iArr16[4]);
        int[] iArr17 = this.mCanBusInfoInt;
        if (iArr17 == null) {

            iArr17 = null;
        }
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(iArr17[9], 3, 2);
        int[] iArr18 = this.mCanBusInfoInt;
        if (iArr18 == null) {

            iArr18 = null;
        }
        boolean boolBit6 = DataHandleUtils.getBoolBit6(iArr18[4]);
        int[] iArr19 = this.mCanBusInfoInt;
        if (iArr19 == null) {

            iArr19 = null;
        }
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(iArr19[10], 0, 2);
        int[] iArr20 = this.mCanBusInfoInt;
        if (iArr20 == null) {

            iArr20 = null;
        }
        boolean boolBit62 = DataHandleUtils.getBoolBit6(iArr20[5]);
        int[] iArr21 = this.mCanBusInfoInt;
        if (iArr21 == null) {

            iArr21 = null;
        }
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(iArr21[11]));
        int[] iArr22 = this.mCanBusInfoInt;
        if (iArr22 == null) {

            iArr22 = null;
        }
        boolean boolBit32 = DataHandleUtils.getBoolBit3(iArr22[5]);
        int[] iArr23 = this.mCanBusInfoInt;
        if (iArr23 == null) {

            iArr23 = null;
        }
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(iArr23[12]));
        int[] iArr24 = this.mCanBusInfoInt;
        if (iArr24 == null) {

            iArr24 = null;
        }
        boolean boolBit12 = DataHandleUtils.getBoolBit1(iArr24[5]);
        int[] iArr25 = this.mCanBusInfoInt;
        if (iArr25 == null) {

            iArr25 = null;
        }
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(iArr25[12]));
        int[] iArr26 = this.mCanBusInfoInt;
        if (iArr26 == null) {

            iArr26 = null;
        }
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(iArr26[12]));
        int[] iArr27 = this.mCanBusInfoInt;
        if (iArr27 == null) {

            iArr27 = null;
        }
        boolean boolBit22 = DataHandleUtils.getBoolBit2(iArr27[5]);
        int[] iArr28 = this.mCanBusInfoInt;
        if (iArr28 == null) {

            iArr28 = null;
        }
        int indexBy2Bit10 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(iArr28[12]));
        int[] iArr29 = this.mCanBusInfoInt;
        if (iArr29 == null) {

            iArr29 = null;
        }
        int indexBy2Bit11 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(iArr29[12]));
        int[] iArr30 = this.mCanBusInfoInt;
        if (iArr30 == null) {

            iArr30 = null;
        }
        boolean boolBit02 = DataHandleUtils.getBoolBit0(iArr30[5]);
        int[] iArr31 = this.mCanBusInfoInt;
        if (iArr31 == null) {

            iArr31 = null;
        }
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(iArr31[12], 0, 2);
        int[] iArr32 = this.mCanBusInfoInt;
        if (iArr32 == null) {

            iArr32 = null;
        }
        boolean boolBit73 = DataHandleUtils.getBoolBit7(iArr32[6]);
        int[] iArr33 = this.mCanBusInfoInt;
        if (iArr33 == null) {

            iArr33 = null;
        }
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(iArr33[11], 2, 3);
        int[] iArr34 = this.mCanBusInfoInt;
        if (iArr34 == null) {

            iArr34 = null;
        }
        boolean boolBit42 = DataHandleUtils.getBoolBit4(iArr34[5]);
        int[] iArr35 = this.mCanBusInfoInt;
        if (iArr35 == null) {

            iArr35 = null;
        }
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(iArr35[13], 5, 3);
        int[] iArr36 = this.mCanBusInfoInt;
        if (iArr36 == null) {

            iArr36 = null;
        }
        boolean boolBit63 = DataHandleUtils.getBoolBit6(iArr36[6]);
        int[] iArr37 = this.mCanBusInfoInt;
        if (iArr37 == null) {

            iArr37 = null;
        }
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(iArr37[11], 5, 3);
        int[] iArr38 = this.mCanBusInfoInt;
        if (iArr38 == null) {

            iArr38 = null;
        }
        boolean boolBit52 = DataHandleUtils.getBoolBit5(iArr38[5]);
        int[] iArr39 = this.mCanBusInfoInt;
        if (iArr39 == null) {

            iArr39 = null;
        }
        int indexBy2Bit12 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(iArr39[13]));
        int[] iArr40 = this.mCanBusInfoInt;
        if (iArr40 == null) {

            iArr40 = null;
        }
        boolean boolBit53 = DataHandleUtils.getBoolBit5(iArr40[6]);
        int[] iArr41 = this.mCanBusInfoInt;
        if (iArr41 == null) {

            iArr41 = null;
        }
        int indexBy2Bit13 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(iArr41[13]));
        int[] iArr42 = this.mCanBusInfoInt;
        if (iArr42 == null) {

            iArr42 = null;
        }
        boolean boolBit43 = DataHandleUtils.getBoolBit4(iArr42[6]);
        int[] iArr43 = this.mCanBusInfoInt;
        if (iArr43 == null) {

            iArr43 = null;
        }
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(iArr43[13], 1, 2);
        int[] iArr44 = this.mCanBusInfoInt;
        if (iArr44 == null) {

            iArr44 = null;
        }
        boolean boolBit33 = DataHandleUtils.getBoolBit3(iArr44[6]);
        int[] iArr45 = this.mCanBusInfoInt;
        if (iArr45 == null) {

            iArr45 = null;
        }
        int indexBy2Bit14 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(iArr45[14]));
        int[] iArr46 = this.mCanBusInfoInt;
        if (iArr46 == null) {

            iArr46 = null;
        }
        boolean boolBit23 = DataHandleUtils.getBoolBit2(iArr46[6]);
        int[] iArr47 = this.mCanBusInfoInt;
        if (iArr47 == null) {

            iArr47 = null;
        }
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(iArr47[14], 5, 2);
        int[] iArr48 = this.mCanBusInfoInt;
        if (iArr48 == null) {

            iArr48 = null;
        }
        boolean boolBit13 = DataHandleUtils.getBoolBit1(iArr48[6]);
        int[] iArr49 = this.mCanBusInfoInt;
        if (iArr49 == null) {

            iArr49 = null;
        }
        int indexBy2Bit15 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(iArr49[11]));
        int[] iArr50 = this.mCanBusInfoInt;
        if (iArr50 == null) {

            iArr50 = null;
        }
        boolean boolBit34 = DataHandleUtils.getBoolBit3(iArr50[7]);
        int[] iArr51 = this.mCanBusInfoInt;
        if (iArr51 == null) {

            iArr51 = null;
        }
        int indexBy2Bit16 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(iArr51[12]));
        int[] iArr52 = this.mCanBusInfoInt;
        if (iArr52 == null) {

            iArr52 = null;
        }
        int indexBy2Bit17 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(iArr52[13]));
        int[] iArr53 = this.mCanBusInfoInt;
        if (iArr53 == null) {

            iArr53 = null;
        }
        boolean boolBit24 = DataHandleUtils.getBoolBit2(iArr53[7]);
        int[] iArr54 = this.mCanBusInfoInt;
        if (iArr54 == null) {

            iArr54 = null;
        }
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(iArr54[14], 3, 2);
        int[] iArr55 = this.mCanBusInfoInt;
        if (iArr55 == null) {

            iArr55 = null;
        }
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(iArr55[14], 1, 2);
        int[] iArr56 = this.mCanBusInfoInt;
        if (iArr56 == null) {

            iArr56 = null;
        }
        int indexBy2Bit18 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(iArr56[14]));
        int[] iArr57 = this.mCanBusInfoInt;
        if (iArr57 == null) {

            iArr57 = null;
        }
        int intFromByteWithBit14 = DataHandleUtils.getIntFromByteWithBit(iArr57[15], 6, 2);
        int[] iArr58 = this.mCanBusInfoInt;
        if (iArr58 == null) {

            iArr58 = null;
        }
        int intFromByteWithBit15 = DataHandleUtils.getIntFromByteWithBit(iArr58[15], 4, 2);
        int[] iArr59 = this.mCanBusInfoInt;
        if (iArr59 == null) {

            iArr59 = null;
        }
        int indexBy2Bit19 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(iArr59[15]));
        int[] iArr60 = this.mCanBusInfoInt;
        if (iArr60 == null) {

            iArr60 = null;
        }
        int indexBy2Bit20 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(iArr60[15]));
        int[] iArr61 = this.mCanBusInfoInt;
        if (iArr61 == null) {

            iArr61 = null;
        }
        int intFromByteWithBit16 = DataHandleUtils.getIntFromByteWithBit(iArr61[15], 0, 2);
        int[] iArr62 = this.mCanBusInfoInt;
        if (iArr62 == null) {

            iArr62 = null;
        }
        int indexBy2Bit21 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(iArr62[16]));
        int[] iArr63 = this.mCanBusInfoInt;
        if (iArr63 == null) {

            iArr63 = null;
        }
        int indexBy2Bit22 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(iArr63[16]));
        int[] iArr64 = this.mCanBusInfoInt;
        if (iArr64 == null) {

            iArr64 = null;
        }
        int indexBy2Bit23 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(iArr64[16]));
        int[] iArr65 = this.mCanBusInfoInt;
        if (iArr65 == null) {

            iArr65 = null;
        }
        int indexBy2Bit24 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(iArr65[16]));
        int[] iArr66 = this.mCanBusInfoInt;
        if (iArr66 == null) {

            iArr66 = null;
        }
        int indexBy2Bit25 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(iArr66[16]));
        int[] iArr67 = this.mCanBusInfoInt;
        if (iArr67 == null) {

            iArr67 = null;
        }
        boolean boolBit14 = DataHandleUtils.getBoolBit1(iArr67[7]);
        int[] iArr68 = this.mCanBusInfoInt;
        if (iArr68 == null) {

            iArr68 = null;
        }
        int indexBy2Bit26 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(iArr68[16]));
        int[] iArr69 = this.mCanBusInfoInt;
        if (iArr69 == null) {

            iArr69 = null;
        }
        boolean boolBit03 = DataHandleUtils.getBoolBit0(iArr69[7]);
        int[] iArr70 = this.mCanBusInfoInt;
        if (iArr70 == null) {

            iArr70 = null;
        }
        int intFromByteWithBit17 = DataHandleUtils.getIntFromByteWithBit(iArr70[16], 0, 2);
        int[] iArr71 = this.mCanBusInfoInt;
        if (iArr71 == null) {

            iArr71 = null;
        }
        boolean boolBit64 = DataHandleUtils.getBoolBit6(iArr71[8]);
        int[] iArr72 = this.mCanBusInfoInt;
        if (iArr72 == null) {

            iArr72 = null;
        }
        int intFromByteWithBit18 = DataHandleUtils.getIntFromByteWithBit(iArr72[17], 6, 2);
        int[] iArr73 = this.mCanBusInfoInt;
        if (iArr73 == null) {

            iArr73 = null;
        }
        boolean boolBit04 = DataHandleUtils.getBoolBit0(iArr73[6]);
        int[] iArr74 = this.mCanBusInfoInt;
        if (iArr74 == null) {

            iArr74 = null;
        }
        int indexBy2Bit27 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(iArr74[17]));
        int[] iArr75 = this.mCanBusInfoInt;
        if (iArr75 == null) {

            iArr75 = null;
        }
        boolean boolBit74 = DataHandleUtils.getBoolBit7(iArr75[7]);
        int[] iArr76 = this.mCanBusInfoInt;
        if (iArr76 == null) {

            iArr76 = null;
        }
        int intFromByteWithBit19 = DataHandleUtils.getIntFromByteWithBit(iArr76[17], 3, 2);
        int[] iArr77 = this.mCanBusInfoInt;
        if (iArr77 == null) {

            iArr77 = null;
        }
        boolean boolBit65 = DataHandleUtils.getBoolBit6(iArr77[7]);
        int[] iArr78 = this.mCanBusInfoInt;
        if (iArr78 == null) {

            iArr78 = null;
        }
        int indexBy2Bit28 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(iArr78[17]));
        int[] iArr79 = this.mCanBusInfoInt;
        if (iArr79 == null) {

            iArr79 = null;
        }
        boolean boolBit54 = DataHandleUtils.getBoolBit5(iArr79[7]);
        int[] iArr80 = this.mCanBusInfoInt;
        if (iArr80 == null) {

            iArr80 = null;
        }
        int intFromByteWithBit20 = DataHandleUtils.getIntFromByteWithBit(iArr80[17], 0, 2);
        int[] iArr81 = this.mCanBusInfoInt;
        if (iArr81 == null) {

            iArr81 = null;
        }
        boolean boolBit44 = DataHandleUtils.getBoolBit4(iArr81[7]);
        int[] iArr82 = this.mCanBusInfoInt;
        if (iArr82 == null) {

            iArr82 = null;
        }
        int intFromByteWithBit21 = DataHandleUtils.getIntFromByteWithBit(iArr82[18], 5, 3);
        int[] iArr83 = this.mCanBusInfoInt;
        if (iArr83 == null) {

            iArr83 = null;
        }
        boolean boolBit75 = DataHandleUtils.getBoolBit7(iArr83[8]);
        int[] iArr84 = this.mCanBusInfoInt;
        if (iArr84 == null) {

            iArr84 = null;
        }
        int intFromByteWithBit22 = DataHandleUtils.getIntFromByteWithBit(iArr84[18], 3, 2);
        int[] iArr85 = this.mCanBusInfoInt;
        if (iArr85 == null) {

            iArr85 = null;
        }
        int[] iArr86 = {indexBy2Bit, intFromByteWithBit, intFromByteWithBit2, intFromByteWithBit3, indexBy2Bit2, indexBy2Bit3, indexBy2Bit4, indexBy2Bit5, intFromByteWithBit4, intFromByteWithBit5, indexBy2Bit6, indexBy2Bit7, indexBy2Bit11, intFromByteWithBit6, intFromByteWithBit7, intFromByteWithBit8, intFromByteWithBit9, indexBy2Bit15, indexBy2Bit16, indexBy2Bit17, indexBy2Bit18, intFromByteWithBit13, intFromByteWithBit12, intFromByteWithBit14, intFromByteWithBit15, indexBy2Bit19, indexBy2Bit20, intFromByteWithBit16, indexBy2Bit21, indexBy2Bit22, indexBy2Bit23, indexBy2Bit24, indexBy2Bit25, indexBy2Bit26, intFromByteWithBit17, intFromByteWithBit18, indexBy2Bit27, intFromByteWithBit19, indexBy2Bit28, intFromByteWithBit20, intFromByteWithBit21, intFromByteWithBit22};
        boolean[] zArr = {boolBit7, boolBit72, boolBit4, boolBit3, boolBit2, boolBit5, boolBit1, boolBit0, boolBit6, boolBit62, boolBit32, boolBit12, boolBit02, boolBit73, boolBit42, boolBit63, boolBit52, boolBit34, true, boolBit24, true, true, true, true, true, true, true, true, true, true, true, true, boolBit14, boolBit03, boolBit64, boolBit04, boolBit74, boolBit65, boolBit54, boolBit44, boolBit75, DataHandleUtils.getBoolBit5(iArr85[8])};
        int[] iArr87 = {indexBy2Bit12, indexBy2Bit13, intFromByteWithBit10, indexBy2Bit14, intFromByteWithBit11};
        boolean[] zArr2 = {boolBit53, boolBit43, boolBit33, boolBit23, boolBit13};
        int[] iArr88 = {indexBy2Bit8, indexBy2Bit9, indexBy2Bit10};
        boolean[] zArr3 = {true, boolBit22, true};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 42; i++) {
            SettingUpdateEntity enable = new SettingUpdateEntity(0, i, Integer.valueOf(iArr86[i])).setEnable(zArr[i]);

            arrayList.add(enable);
        }
        for (int i2 = 0; i2 < 5; i2++) {
            SettingUpdateEntity enable2 = new SettingUpdateEntity(1, i2, Integer.valueOf(iArr87[i2])).setEnable(zArr2[i2]);

            arrayList.add(enable2);
        }
        for (int i3 = 0; i3 < 3; i3++) {
            SettingUpdateEntity enable3 = new SettingUpdateEntity(2, i3, Integer.valueOf(iArr88[i3])).setEnable(zArr3[i3]);

            arrayList.add(enable3);
        }
        int[] iArr89 = this.mCanBusInfoInt;
        if (iArr89 == null) {

            iArr89 = null;
        }
        this.mEnable06_4 = DataHandleUtils.getBoolBit4(iArr89[8]);
        int[] iArr90 = this.mCanBusInfoInt;
        if (iArr90 == null) {

            iArr90 = null;
        }
        this.mEnable06_3 = DataHandleUtils.getBoolBit3(iArr90[8]);
        int[] iArr91 = this.mCanBusInfoInt;
        if (iArr91 == null) {

            iArr91 = null;
        }
        this.mEnable06_2 = DataHandleUtils.getBoolBit2(iArr91[8]);
        int[] iArr92 = this.mCanBusInfoInt;
        if (iArr92 == null) {

            iArr92 = null;
        }
        this.mEnable06_1 = DataHandleUtils.getBoolBit1(iArr92[8]);
        setHudData();
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private final void setHudData() {
        ArrayList arrayList = new ArrayList();
        SettingUpdateEntity enable = new SettingUpdateEntity(5, 0, Integer.valueOf(this.mData0_7)).setEnable(this.mEnable06_4);

        arrayList.add(enable);
        SettingUpdateEntity enable2 = new SettingUpdateEntity(5, 1, Integer.valueOf(this.mData0_26 - 15)).setProgress(this.mData0_26).setEnable(this.mEnable06_3);

        arrayList.add(enable2);
        SettingUpdateEntity enable3 = new SettingUpdateEntity(5, 2, Integer.valueOf(this.mData2_47 - 5)).setProgress(this.mData2_47).setEnable(this.mEnable06_2);

        arrayList.add(enable3);
        SettingUpdateEntity enable4 = new SettingUpdateEntity(5, 3, Integer.valueOf(this.mData2_03 - 5)).setProgress(this.mData2_03).setEnable(this.mEnable06_1);

        arrayList.add(enable4);
        updateGeneralSettingData(arrayList);
    }

    private final void setHudData0x79() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        this.mData0_7 = DataHandleUtils.getIntFromByteWithBit(iArr[2], 7, 1);
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {

            iArr2 = null;
        }
        this.mData0_26 = DataHandleUtils.getIntFromByteWithBit(iArr2[2], 2, 5);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {

            iArr3 = null;
        }
        this.mData2_47 = DataHandleUtils.getIntFromByteWithBit(iArr3[3], 4, 4);
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {

            iArr4 = null;
        }
        this.mData2_03 = DataHandleUtils.getIntFromByteWithBit(iArr4[3], 0, 4);
        setHudData();
        updateSettingActivity(null);
    }

    private final void setDriveData() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int[] iArr = null;
            if (i < 8) {
                StringBuilder sb = new StringBuilder();
                int[] iArr2 = this.mCanBusInfoInt;
                if (iArr2 == null) {

                    iArr2 = null;
                }
                int i2 = iArr2[(i * 2) + 2] * 256;
                int[] iArr3 = this.mCanBusInfoInt;
                if (iArr3 == null) {

                } else {
                    iArr = iArr3;
                }
                arrayList.add(new DriverUpdateEntity(0, i, sb.append(((float) (((i2 + iArr[r8 + 3]) * 0.1d) * 10)) / 10).append(' ').append(this.mFuelUnit).toString()));
                i++;
            } else {
                updateGeneralDriveData(arrayList);
                updateDriveDataActivity(null);
                return;
            }
        }
    }

    private final void setDriveData2() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int[] iArr = null;
            if (i < 15) {
                StringBuilder sb = new StringBuilder();
                int[] iArr2 = this.mCanBusInfoInt;
                if (iArr2 == null) {

                    iArr2 = null;
                }
                int i2 = iArr2[(i * 2) + 2] * 256;
                int[] iArr3 = this.mCanBusInfoInt;
                if (iArr3 == null) {

                } else {
                    iArr = iArr3;
                }
                arrayList.add(new DriverUpdateEntity(1, i, sb.append(((float) (((i2 + iArr[r7 + 3]) * 0.1d) * 10)) / 10).append(' ').append(this.mFuelUnit).toString()));
                i++;
            } else {
                updateGeneralDriveData(arrayList);
                updateDriveDataActivity(null);
                return;
            }
        }
    }

    private final void setCarType() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        arrayList.add(new DriverUpdateEntity(2, 2, carType(iArr[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private final void setAmplifierData() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        if (isDataChange(iArr)) {
            int[] iArr2 = this.mCanBusInfoInt;
            if (iArr2 == null) {

                iArr2 = null;
            }
            GeneralAmplifierData.volume = iArr2[2];
            int[] iArr3 = this.mCanBusInfoInt;
            if (iArr3 == null) {

                iArr3 = null;
            }
            GeneralAmplifierData.leftRight = iArr3[3] - 16;
            int[] iArr4 = this.mCanBusInfoInt;
            if (iArr4 == null) {

                iArr4 = null;
            }
            GeneralAmplifierData.frontRear = iArr4[4] - 16;
            int[] iArr5 = this.mCanBusInfoInt;
            if (iArr5 == null) {

                iArr5 = null;
            }
            GeneralAmplifierData.bandBass = iArr5[5] - 10;
            int[] iArr6 = this.mCanBusInfoInt;
            if (iArr6 == null) {

                iArr6 = null;
            }
            GeneralAmplifierData.bandTreble = iArr6[7] - 10;
            saveAmplifierData(this.mContext, this.mCanId);
            updateAmplifierActivity(new Bundle());
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr7 = this.mCanBusInfoInt;
        if (iArr7 == null) {

            iArr7 = null;
        }
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(iArr7[9])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private final String carType(int data) {
        Context context = this.mContext;

        String string = context.getString(CommUtil.getStrIdByResId(this.mContext, "_213_car" + data));

        return string;
    }

    private final void setDoorData() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = null;
        if (iArr == null) {

            iArr = null;
        }
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[4]);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {

            iArr3 = null;
        }
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr3[4]);
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {

            iArr4 = null;
        }
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(iArr4[4]);
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {

            iArr5 = null;
        }
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(iArr5[4]);
        int[] iArr6 = this.mCanBusInfoInt;
        if (iArr6 == null) {

        } else {
            iArr2 = iArr6;
        }
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr2[4]);
        updateDoorView(this.mContext);
    }

    private final void setOriginalCarDeviceInfo() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        int i = iArr[7] * 256;
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {

            iArr2 = null;
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, String.valueOf(i + iArr2[8])));
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {

            iArr3 = null;
        }
        int i2 = iArr3[15] * 256;
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {

            iArr4 = null;
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, String.valueOf(i2 + iArr4[16])));
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {

            iArr5 = null;
        }
        int i3 = iArr5[9] * 256;
        int[] iArr6 = this.mCanBusInfoInt;
        if (iArr6 == null) {

            iArr6 = null;
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, String.valueOf(i3 + iArr6[10])));
        GeneralOriginalCarDeviceData.mList = arrayList;
        int[] iArr7 = this.mCanBusInfoInt;
        if (iArr7 == null) {

            iArr7 = null;
        }
        if (DataHandleUtils.getBoolBit1(iArr7[6])) {
            Context context = this.mContext;

            GeneralOriginalCarDeviceData.cdStatus = context.getResources().getString(R.string.open);
        } else {
            Context context2 = this.mContext;

            GeneralOriginalCarDeviceData.cdStatus = context2.getResources().getString(R.string.close);
        }
        int[] iArr8 = this.mCanBusInfoInt;
        if (iArr8 == null) {

            iArr8 = null;
        }
        GeneralOriginalCarDeviceData.cd = DataHandleUtils.getBoolBit1(iArr8[6]);
        int[] iArr9 = this.mCanBusInfoInt;
        if (iArr9 == null) {

            iArr9 = null;
        }
        GeneralOriginalCarDeviceData.rpt_off = DataHandleUtils.getIntFromByteWithBit(iArr9[6], 5, 3) == 0;
        int[] iArr10 = this.mCanBusInfoInt;
        if (iArr10 == null) {

            iArr10 = null;
        }
        GeneralOriginalCarDeviceData.rpt_fold = DataHandleUtils.getIntFromByteWithBit(iArr10[6], 5, 3) == 1;
        int[] iArr11 = this.mCanBusInfoInt;
        if (iArr11 == null) {

            iArr11 = null;
        }
        GeneralOriginalCarDeviceData.rpt_track = DataHandleUtils.getIntFromByteWithBit(iArr11[6], 5, 3) == 2;
        int[] iArr12 = this.mCanBusInfoInt;
        if (iArr12 == null) {

            iArr12 = null;
        }
        GeneralOriginalCarDeviceData.rdm_off = DataHandleUtils.getIntFromByteWithBit(iArr12[6], 2, 3) == 0;
        int[] iArr13 = this.mCanBusInfoInt;
        if (iArr13 == null) {

            iArr13 = null;
        }
        GeneralOriginalCarDeviceData.rdm_fold = DataHandleUtils.getIntFromByteWithBit(iArr13[6], 2, 3) == 1;
        int[] iArr14 = this.mCanBusInfoInt;
        if (iArr14 == null) {

            iArr14 = null;
        }
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getIntFromByteWithBit(iArr14[6], 2, 3) == 2;
        Context context3 = this.mContext;

        Context context4 = this.mContext;
        StringBuilder sbAppend = new StringBuilder().append("_123_divice_status_");
        int[] iArr15 = this.mCanBusInfoInt;
        if (iArr15 == null) {

            iArr15 = null;
        }
        GeneralOriginalCarDeviceData.runningState = context3.getString(CommUtil.getStrIdByResId(context4, sbAppend.append(iArr15[5]).toString()));
        int[] iArr16 = this.mCanBusInfoInt;
        if (iArr16 == null) {

            iArr16 = null;
        }
        int i4 = iArr16[13] * 256;
        int[] iArr17 = this.mCanBusInfoInt;
        if (iArr17 == null) {

            iArr17 = null;
        }
        int i5 = i4 + iArr17[14];
        int[] iArr18 = this.mCanBusInfoInt;
        if (iArr18 == null) {

            iArr18 = null;
        }
        int i6 = iArr18[11] * 256;
        int[] iArr19 = this.mCanBusInfoInt;
        if (iArr19 == null) {

            iArr19 = null;
        }
        int i7 = i6 + iArr19[12];
        if (i5 == 0 && this.mStartTime != 0) {
            if (this.mRequestTimer == null) {
                this.mRequestTimer = new TimerHelper();
            }
            final byte[][] bArr = {new byte[]{22, -14, 10, 0}, new byte[]{22, -14, 10, 1}, new byte[]{22, -14, 10, 2}};
            TimerHelper timerHelper = this.mRequestTimer;

            timerHelper.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._213.MsgMgr.setOriginalCarDeviceInfo.1
                private int i;

                public final int getI() {
                    return this.i;
                }

                public final void setI(int i8) {
                    this.i = i8;
                }

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    int i8 = this.i;
                    byte[][] bArr2 = bArr;
                    if (i8 >= bArr2.length) {
                        TimerHelper timerHelper2 = this.mRequestTimer;

                        timerHelper2.stopTimer();
                    } else {
                        this.i = i8 + 1;
                        CanbusMsgSender.sendMsg(bArr2[i8]);
                    }
                }
            }, 0L, 150L);
        }
        this.mStartTime = i5;
        if (i7 > 0 && i5 <= i7) {
            GeneralOriginalCarDeviceData.startTime = startEndTimeMethod(i5);
            GeneralOriginalCarDeviceData.endTime = startEndTimeMethod(i7);
            GeneralOriginalCarDeviceData.progress = (i5 * 100) / i7;
        }
        updateOriginalCarDeviceActivity(null);
    }

    private final void setOriginalCarDeviceInfo2() {
        String str;
        try {
            byte[] bArr = this.mCanBusInfoByte;
            if (bArr == null) {

                bArr = null;
            }
            byte[] bytesEndWithAssign = DataHandleUtils.getBytesEndWithAssign(bArr, 3, (byte) 0);

            Charset charsetForName = Charset.forName("GB2312");

            str = new String(bytesEndWithAssign, charsetForName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = null;
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(iArr[2] + 2, str));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private final boolean isDoorMsgReturn(byte[] canbusInfo) {
        byte[] bArr = this.mCanbusDoorInfoCopy;
        if (bArr == null) {

            bArr = null;
        }
        if (Arrays.equals(canbusInfo, bArr)) {
            return true;
        }
        byte[] bArrCopyOf = Arrays.copyOf(canbusInfo, canbusInfo.length);

        this.mCanbusDoorInfoCopy = bArrCopyOf;
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private final boolean isAirMsgReturn(byte[] canbusInfo) {
        byte[] bArr = this.mCanbusAirInfoCopy;
        if (bArr == null) {

            bArr = null;
        }
        if (Arrays.equals(canbusInfo, bArr)) {
            return true;
        }
        byte[] bArrCopyOf = Arrays.copyOf(canbusInfo, canbusInfo.length);

        this.mCanbusAirInfoCopy = bArrCopyOf;
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    private final String resolveFrontAirTemp(int value) {
        return value != 254 ? value != 255 ? (value * 0.5f) + getTempUnitC(this.mContext) : "HI" : "LO";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        String string;



        super.radioInfoChange(currClickPresetIndex, currBand, currentFreq, psName, isStereo);
        if (currClickPresetIndex > 6) {
            currClickPresetIndex = 0;
        }
        byte radioCurrentBand = CommUtil.getRadioCurrentBand(currBand, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(currBand)) {
            if (currentFreq.length() == 4) {
                string = new DecimalFormat("00").format(currClickPresetIndex) + ' ' + currentFreq + "  Khz";
            } else {
                string = new DecimalFormat("00").format(currClickPresetIndex) + "  " + currentFreq + "  Khz";
            }
        } else if (currentFreq.length() == 5) {
            StringBuilder sbAppend = new StringBuilder().append(new DecimalFormat("00").format(currClickPresetIndex)).append("  ");
            String strSubstring = currentFreq.substring(0, currentFreq.length());

            string = sbAppend.append(strSubstring).append("Mhz").toString();
        } else {
            StringBuilder sbAppend2 = new StringBuilder().append(new DecimalFormat("00").format(currClickPresetIndex)).append(' ');
            String strSubstring2 = currentFreq.substring(0, currentFreq.length());

            string = sbAppend2.append(strSubstring2).append("Mhz").toString();
        }
        byte[] bytes = string.getBytes(Charsets.UTF_8);

        byte[] bArrByteMerger = Util.byteMerger(new byte[]{22, -111, radioCurrentBand}, bytes);

        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {

        super.btPhoneIncomingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {

        super.btPhoneOutGoingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        byte[] bytes = "AUX".getBytes(Charsets.UTF_8);

        byte[] bytes2 = "AUX".getBytes(Charsets.UTF_8);

        System.arraycopy(bytes, 0, bArr, 0, bytes2.length);
        byte[] bArrByteMerger = Util.byteMerger(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bArr);

        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        byte[] bytes = "TV".getBytes(Charsets.UTF_8);

        byte[] bytes2 = "TV".getBytes(Charsets.UTF_8);

        System.arraycopy(bytes, 0, bArr, 0, bytes2.length);
        byte[] bArrByteMerger = Util.byteMerger(new byte[]{22, -111, 8}, bArr);

        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        byte[] bytes = "TV".getBytes(Charsets.UTF_8);

        byte[] bytes2 = "TV".getBytes(Charsets.UTF_8);

        System.arraycopy(bytes, 0, bArr, 0, bytes2.length);
        byte[] bArrByteMerger = Util.byteMerger(new byte[]{22, -111, 8}, bArr);

        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {






        super.musicInfoChange(stoagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playModel, playIndex, isPlaying, totalTime, songTitle, songAlbum, songArtist, isReportFromPlay);
        String str = new DecimalFormat("00").format(Byte.valueOf(currentMinute));

        String str2 = new DecimalFormat("00").format(Byte.valueOf(currentSecond));

        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str3 = new DecimalFormat("000").format((((byte) (currentPlayingIndexHigh & (-1))) * 256) + (currentPlayingIndexLow & 255));

        String str4 = String.format(str3, Arrays.copyOf(new Object[0], 0));

        String string = sb.append(str4).append(' ').append(str).append(':').append(str2).append("   ").toString();
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -111;
        bArr[2] = stoagePath == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        byte[] bytes = string.getBytes(Charsets.UTF_8);

        byte[] bArrByteMerger = Util.byteMerger(bArr, bytes);

        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duation) {




        super.videoInfoChange(stoagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playMode, isPlaying, duation);
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = new DecimalFormat("000").format((((byte) (currentPlayingIndexHigh & (-1))) * 256) + (currentPlayingIndexLow & 255));

        String str2 = String.format(str, Arrays.copyOf(new Object[0], 0));

        String string = sb.append(str2).append("         ").toString();
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -111;
        bArr[2] = stoagePath == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        byte[] bytes = string.getBytes(Charsets.UTF_8);

        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), Util.byteMerger(bArr, bytes));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {



        super.discInfoChange(playModel, currentTime, playTitleNo, position, currentPlayNo, currentTotalNo, discType, isUnMuted, isPlaying, playStat, song, album, artist);
        String str = new DecimalFormat("00").format(Byte.valueOf((byte) ((currentTime / 60) % 60)));

        String str2 = new DecimalFormat("00").format(Byte.valueOf((byte) (currentTime % 60)));

        String str3 = new DecimalFormat("000").format(currentPlayNo) + ' ' + str + str2 + "    ";
        byte b = 7;
        if (discType != 1 && discType != 2 && discType != 3) {
            b = (discType == 6 || discType == 7) ? (byte) 6 : (byte) 0;
        }
        byte[] bytes = str3.getBytes(Charsets.UTF_8);

        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), Util.byteMerger(new byte[]{22, -111, b}, bytes));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {

        super.sourceSwitchChange(source);
        if (Intrinsics.areEqual(SourceConstantsDef.SOURCE_ID.NULL.name(), source)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        super.sourceSwitchNoMediaInfoChange(isPowerOff);
        if (isPowerOff) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int key) {

        if (key != 3) {
            return false;
        }
        realKeyClick(context, HotKeyConstant.K_SLEEP);
        return true;
    }

    private final void setSwcKey() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        switch (iArr[4]) {
            case 0:
                panelBtnKeyClick(0);
                break;
            case 1:
                panelBtnKeyClick(7);
                break;
            case 2:
                panelBtnKeyClick(8);
                break;
            case 3:
                panelBtnKeyClick(3);
                break;
            case 4:
                panelBtnKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                panelBtnKeyClick(14);
                break;
            case 6:
                panelBtnKeyClick(15);
                break;
            case 8:
                panelBtnKeyClick(45);
                break;
            case 9:
                panelBtnKeyClick(46);
                break;
            case 10:
                panelBtnKeyClick(2);
                break;
        }
    }

    private final void panelBtnKeyClick(int value) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        realKeyLongClick1(context, value, iArr[5]);
    }

    private final void set0x21PanelKeyData(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(context, 0);
        }
        if (i == 6) {
            realKeyLongClick1(context, 50);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(context, 3);
            return;
        }
        if (i == 32) {
            realKeyLongClick1(context, 128);
            return;
        }
        if (i == 43) {
            realKeyLongClick1(context, 52);
            return;
        }
        if (i == 45) {
            realKeyLongClick1(context, 59);
            return;
        }
        if (i == 84) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(PackageName.google_youtube, ClassName.google_youtube));
                intent.setFlags(268435456);
                context.startActivity(intent);
                return;
            } catch (Exception unused) {
                realKeyLongClick1(context, 79);
                return;
            }
        }
        if (i == 2) {
            realKeyLongClick1(context, 21);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 20);
            return;
        }
        switch (i) {
            case 22:
                realKeyLongClick1(context, 49);
                break;
            case 23:
                realKeyLongClick1(context, 45);
                break;
            case 24:
                realKeyLongClick1(context, 46);
                break;
            case 25:
                realKeyLongClick1(context, 47);
                break;
            case 26:
                realKeyLongClick1(context, 48);
                break;
            case 27:
                realKeyLongClick1(context, 128);
                break;
        }
    }

    private final void set0x22PanelKnobData(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = null;
        if (bArr == null) {

            bArr = null;
        }
        if (bArr[2] == 1) {
            int i = this.mVolKnobValue;
            byte[] bArr3 = this.mCanBusInfoByte;
            if (bArr3 == null) {

                bArr3 = null;
            }
            int i2 = i - bArr3[3];
            if (i2 > 0) {
                realKeyClick3_1(context, 8, Math.abs(i2));
            } else if (i2 < 0) {
                realKeyClick3_1(context, 7, Math.abs(i2));
            }
            byte[] bArr4 = this.mCanBusInfoByte;
            if (bArr4 == null) {

            } else {
                bArr2 = bArr4;
            }
            this.mVolKnobValue = bArr2[3];
            return;
        }
        byte[] bArr5 = this.mCanBusInfoByte;
        if (bArr5 == null) {

            bArr5 = null;
        }
        if (bArr5[2] == 2) {
            int i3 = this.mSelKnobValue;
            byte[] bArr6 = this.mCanBusInfoByte;
            if (bArr6 == null) {

                bArr6 = null;
            }
            int i4 = i3 - bArr6[3];
            if (i4 > 0) {
                realKeyClick3_1(context, 45, Math.abs(i4));
            } else if (i4 < 0) {
                realKeyClick3_1(context, 46, Math.abs(i4));
            }
            byte[] bArr7 = this.mCanBusInfoByte;
            if (bArr7 == null) {

            } else {
                bArr2 = bArr7;
            }
            this.mSelKnobValue = bArr2[3];
        }
    }

    private final void sendAppIconSelect(Context context, int key, int different) {
        String str;
        String deviceId = ConfigUtil.getDeviceId();

        if (StringsKt.contains$default((CharSequence) deviceId, (CharSequence) "P0R", false, 2, (Object) null)) {
            String string = Settings.System.getString(context.getContentResolver(), ServiceConstants.KEY_CURRENT_TOP_PACKAGENAME);

            Object[] array = new Regex(":;:").split(string, 0).toArray(new String[0]);

            String str2 = ((String[]) array)[1];
            if (TextUtils.isEmpty(str2) || !StringsKt.contains$default((CharSequence) str2, (CharSequence) "com.android.launcher3", false, 2, (Object) null)) {
                if (key == 49) {
                    realKeyClick(context, key);
                    return;
                } else {
                    realKeyClick3_2(context, key, different);
                    return;
                }
            }
            switch (key) {
                case 47:
                    str = "KEY_APP_SELECT_PREV";
                    break;
                case 48:
                    str = "KEY_APP_SELECT_NEXT";
                    break;
                case 49:
                    str = "KEY_APP_SELECT_ENTER";
                    break;
                default:
                    return;
            }
            FutureUtil.instance.sendAppSelect(str);
        }
    }

    private final void realKeyClick1(Context context, int key) {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = null;
        if (iArr == null) {

            iArr = null;
        }
        int i = iArr[2];
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {

        } else {
            iArr2 = iArr3;
        }
        realKeyClick1(context, key, i, iArr2[3]);
    }

    private final void realKeyLongClick1(Context context, int key) {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        realKeyLongClick1(context, key, iArr[3]);
    }

    private final void realKeyClick3_1(Context context, int key, int time) {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        realKeyClick3_1(context, key, iArr[2], time);
    }

    private final void realKeyClick3_2(Context context, int key, int time) {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {

            iArr = null;
        }
        realKeyClick3_2(context, key, iArr[2], time);
    }

    public final void updateSettings(int left, int right, int value) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(left, right, Integer.valueOf(value)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public final void setFuelUnit(int value) {
        if (value == 0) {
            this.mFuelUnit = "mpg";
        } else if (value == 1) {
            this.mFuelUnit = "km/l";
        } else {
            if (value != 2) {
                return;
            }
            this.mFuelUnit = "l/100km";
        }
    }

    /* compiled from: MsgMgr.kt */
    
    private final class TimerHelper {
        private Timer mTimer;
        private TimerTask mTimerTask;

        public TimerHelper() {
        }

        public final void startTimer(TimerTask timerTask, long delay, long period) {
            Log.i("TimerUtil", "startTimer: " + this);
            if (timerTask == null) {
                return;
            }
            this.mTimerTask = timerTask;
            if (this.mTimer == null) {
                this.mTimer = new Timer();
            }
            Timer timer = this.mTimer;

            timer.schedule(this.mTimerTask, delay, period);
        }

        public final void stopTimer() {
            Log.i("TimerUtil", "stopTimer: " + this);
            TimerTask timerTask = this.mTimerTask;
            if (timerTask != null) {

                timerTask.cancel();
                this.mTimerTask = null;
            }
            Timer timer = this.mTimer;
            if (timer != null) {

                timer.cancel();
                this.mTimer = null;
            }
        }
    }
}
