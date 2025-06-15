package com.hzbhd.canbus.car._55;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;




public final class UiMgr extends AbstractUiMgr {
    private final AirPageUiSet mAirPageUiSet;
    private final AmplifierHandleListener mAmplifierHandleListener;
    private final AmplifierPageUiSet mAmplifierPageUiSet;
    private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private final ParkPageUiSet mParkPageUiSet;
    private final SettingHandleListener mSettingHandleListener;
    private final SettingPageUiSet mSettingPageUiSet;
    private int windLevel;

    public UiMgr(Context context) {

        SettingPageUiSet settingUiSet = getSettingUiSet(context);

        this.mSettingPageUiSet = settingUiSet;
        AirPageUiSet airUiSet = getAirUiSet(context);

        this.mAirPageUiSet = airUiSet;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);

        this.mAmplifierPageUiSet = amplifierPageUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);

        this.mParkPageUiSet = parkPageUiSet;
        SettingHandleListener settingHandleListener = new SettingHandleListener(settingUiSet, context);
        this.mSettingHandleListener = settingHandleListener;
        AmplifierHandleListener amplifierHandleListener = new AmplifierHandleListener();
        this.mAmplifierHandleListener = amplifierHandleListener;
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m898lambda10$lambda4$lambda0(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m899lambda10$lambda4$lambda1(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m900lambda10$lambda4$lambda2(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m901lambda10$lambda4$lambda3(frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr uiMgr = this.this$0;
                uiMgr.setWindLevel(uiMgr.windLevel - 1);
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte) uiMgr.windLevel});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr uiMgr = this.this$0;
                uiMgr.setWindLevel(uiMgr.windLevel + 1);
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte) uiMgr.windLevel});
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(14);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(15);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(16);
            }
        }});
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(17);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(18);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(23);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirConditionerCmd(24);
            }
        }});
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$1$12
            private int i;
            private final int[] mode = {9, 10, 23, 24};

            public final int getI() {
                return this.i;
            }

            public final void setI(int i) {
                this.i = i;
            }

            public final int[] getMode() {
                return this.mode;
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr uiMgr = this.this$0;
                int[] iArr = this.mode;
                int i = this.i;
                this.i = i + 1;
                uiMgr.sendAirConditionerCmd(iArr[i % 4]);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr uiMgr = this.this$0;
                int[] iArr = this.mode;
                int i = this.i;
                this.i = i + 1;
                uiMgr.sendAirConditionerCmd(iArr[i % 4]);
            }
        });
        final RearArea rearArea = airUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m902lambda10$lambda9$lambda5(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m903lambda10$lambda9$lambda6(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m904lambda10$lambda9$lambda7(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m905lambda10$lambda9$lambda8(rearArea, i);
            }
        }});
        rearArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirConditionerCmd(43);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirConditionerCmd(42);
            }
        });
        rearArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$2$6[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$2$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirConditionerCmd(32);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirConditionerCmd(33);
            }
        }, null});
        rearArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$1$2$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendAirConditionerCmd(62);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                this.this$0.sendAirConditionerCmd(62);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemSwitchListener(settingHandleListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(settingHandleListener);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m906lambda12$lambda11(this.f$0, i, i2);
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(amplifierHandleListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(amplifierHandleListener);
        final int i = context.getResources().getDisplayMetrics().widthPixels;
        final int i2 = context.getResources().getDisplayMetrics().heightPixels;
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i3) {
                UiMgr.m907lambda16$lambda14(i3);
            }
        });
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public final void onTouchItem(MotionEvent motionEvent) {
                UiMgr.m908lambda16$lambda15(i, i2, motionEvent);
            }
        });
        final int[] iArr = {1, 2};
        final int[] iArr2 = {1, 2};
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        final Ref.IntRef intRef3 = new Ref.IntRef();
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i3) {
                UiMgr.m911lambda19$lambda17(originalCarDevicePageUiSet, iArr, intRef, iArr2, intRef2, intRef3, i3);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._55.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public final void onClickTopBtnItem(int i3) {
                UiMgr.m912lambda19$lambda18(originalCarDevicePageUiSet, iArr, intRef, iArr2, intRef2, intRef3, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setWindLevel(int i) {
        if (i > 7) {
            i = 7;
        } else if (i < 0) {
            i = 0;
        }
        this.windLevel = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-4$lambda-0, reason: not valid java name */
    public static final void m898lambda10$lambda4$lambda0(FrontArea frontArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = frontArea.getLineBtnAction()[0][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-4$lambda-1, reason: not valid java name */
    public static final void m899lambda10$lambda4$lambda1(FrontArea frontArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = frontArea.getLineBtnAction()[1][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-4$lambda-2, reason: not valid java name */
    public static final void m900lambda10$lambda4$lambda2(FrontArea frontArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = frontArea.getLineBtnAction()[2][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-4$lambda-3, reason: not valid java name */
    public static final void m901lambda10$lambda4$lambda3(FrontArea frontArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = frontArea.getLineBtnAction()[3][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-9$lambda-5, reason: not valid java name */
    public static final void m902lambda10$lambda9$lambda5(RearArea rearArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = rearArea.getLineBtnAction()[0][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-9$lambda-6, reason: not valid java name */
    public static final void m903lambda10$lambda9$lambda6(RearArea rearArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = rearArea.getLineBtnAction()[1][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-9$lambda-7, reason: not valid java name */
    public static final void m904lambda10$lambda9$lambda7(RearArea rearArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = rearArea.getLineBtnAction()[2][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-9$lambda-8, reason: not valid java name */
    public static final void m905lambda10$lambda9$lambda8(RearArea rearArea, int i) {
        SingletonForKt singletonForKt = SingletonForKt.INSTANCE;
        String str = rearArea.getLineBtnAction()[3][i];

        singletonForKt.sendAirBtnData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-12$lambda-11, reason: not valid java name */
    public static final void m906lambda12$lambda11(UiMgr this$0, int i, int i2) {

        if (Intrinsics.areEqual(this$0.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "S55_OriginalCarScreen_17")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, -1});
            Toast.makeText(InitUtilsKt.getMContext(), "SUCCESS!", 0).show();
        }
    }

    /* renamed from: lambda-16$sendPanoramaCmd, reason: not valid java name */
    private static final void m910lambda16$sendPanoramaCmd(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte) i, -1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-16$lambda-14, reason: not valid java name */
    public static final void m907lambda16$lambda14(int i) {
        if (i == 0) {
            m910lambda16$sendPanoramaCmd(1);
        } else if (i == 1) {
            m910lambda16$sendPanoramaCmd(2);
        } else {
            if (i != 2) {
                return;
            }
            m910lambda16$sendPanoramaCmd(3);
        }
    }

    /* renamed from: lambda-16$lambda-15$sendPanoramicItem, reason: not valid java name */
    private static final void m909lambda16$lambda15$sendPanoramicItem(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte) i, (byte) i2, (byte) i3, (byte) i4, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-16$lambda-15, reason: not valid java name */
    public static final void m908lambda16$lambda15(int i, int i2, MotionEvent motionEvent) {
        int x = (int) (motionEvent.getX() * (1560.0f / i));
        int y = (int) (motionEvent.getY() * (1900.0f / i2));
        if (motionEvent.getAction() != 0 && motionEvent.getAction() == 1) {
            m909lambda16$lambda15$sendPanoramicItem(DataHandleUtils.getMsb(x), DataHandleUtils.getLsb(y), DataHandleUtils.getMsb(y), DataHandleUtils.getLsb(y));
        }
        Log.i("lyn", " x:" + x + ", y:" + y + " \n width:" + i + ", high:" + i2);
    }

    /* renamed from: lambda-19$send$default, reason: not valid java name */
    static /* synthetic */ void m915lambda19$send$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        m914lambda19$send(i, i2);
    }

    /* renamed from: lambda-19$send, reason: not valid java name */
    private static final void m914lambda19$send(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -84, (byte) i, (byte) i2});
    }

    /* renamed from: lambda-19$sendF1$default, reason: not valid java name */
    static /* synthetic */ void m917lambda19$sendF1$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        m916lambda19$sendF1(i, i2);
    }

    /* renamed from: lambda-19$sendF1, reason: not valid java name */
    private static final void m916lambda19$sendF1(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -15, (byte) i, (byte) i2});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* renamed from: lambda-19$select, reason: not valid java name */
    private static final void m913lambda19$select(int[] iArr, Ref.IntRef intRef, int[] iArr2, Ref.IntRef intRef2, Ref.IntRef intRef3, String str) {
        switch (str.hashCode()) {
            case 3116:
                if (str.equals(OriginalBtnAction.AM)) {
                    m916lambda19$sendF1(3, 0);
                    break;
                }
                break;
            case 3271:
                if (str.equals(OriginalBtnAction.FM)) {
                    m916lambda19$sendF1(3, 1);
                    break;
                }
                break;
            case 3739:
                if (str.equals("up")) {
                    m914lambda19$send(7, 0);
                    break;
                }
                break;
            case 3089570:
                if (str.equals("down")) {
                    m914lambda19$send(7, 1);
                    break;
                }
                break;
            case 3524221:
                if (str.equals(OriginalBtnAction.SCAN)) {
                    int i = intRef2.element;
                    intRef2.element = i + 1;
                    m916lambda19$sendF1(5, iArr2[i % 2]);
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    int i2 = intRef.element;
                    intRef.element = i2 + 1;
                    m915lambda19$send$default(iArr[i2 % 2], 0, 2, null);
                    break;
                }
                break;
            case 1085444827:
                if (str.equals(OriginalBtnAction.REFRESH)) {
                    int i3 = intRef3.element;
                    intRef3.element = i3 + 1;
                    m916lambda19$sendF1(6, iArr2[i3 % 2]);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-19$lambda-17, reason: not valid java name */
    public static final void m911lambda19$lambda17(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int[] state, Ref.IntRef i, int[] anotherState, Ref.IntRef i1, Ref.IntRef i2, int i3) {





        String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i3];

        m913lambda19$select(state, i, anotherState, i1, i2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-19$lambda-18, reason: not valid java name */
    public static final void m912lambda19$lambda18(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int[] state, Ref.IntRef i, int[] anotherState, Ref.IntRef i1, Ref.IntRef i2, int i3) {





        String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i3];

        m913lambda19$select(state, i, anotherState, i1, i2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirConditionerCmd(int d0) {
        byte b = (byte) d0;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* compiled from: UiMgr.kt */
    
    public static final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
        private final Context context;
        private final SettingPageUiSet mSettingPageUiSet;

        public SettingHandleListener(SettingPageUiSet mSettingPageUiSet, Context context) {


            this.mSettingPageUiSet = mSettingPageUiSet;
            this.context = context;
        }

        public final Context getContext() {
            return this.context;
        }

        public final SettingPageUiSet getMSettingPageUiSet() {
            return this.mSettingPageUiSet;
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int leftPos, int rightPos, int progressORvalue) {
            SingletonForKt.INSTANCE.sendSettingsData(this.mSettingPageUiSet, leftPos, rightPos, progressORvalue);
            if (Intrinsics.areEqual(this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn(), "language_setup")) {
                MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(this.context);

                ((MsgMgr) canMsgMgr).updateSetting();
            }
            if (Intrinsics.areEqual(this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn(), "S18_Car_0")) {
                MsgMgrInterface canMsgMgr2 = MsgMgrFactory.getCanMsgMgr(this.context);

                ((MsgMgr) canMsgMgr2).updateSetting();
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
        public void onSwitch(int leftPos, int rightPos, int value) {
            SingletonForKt.INSTANCE.sendSettingsData(this.mSettingPageUiSet, leftPos, rightPos, value);
        }
    }

    /* compiled from: UiMgr.kt */
    
    public static final class AmplifierHandleListener implements OnAmplifierPositionListener, OnAmplifierSeekBarListener {

        /* compiled from: UiMgr.kt */
        
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
                iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
                iArr2[AmplifierActivity.AmplifierBand.VOLUME_Plus.ordinal()] = 1;
                iArr2[AmplifierActivity.AmplifierBand.VOLUME_Min.ordinal()] = 2;
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
                iArr2[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
                iArr2[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 5;
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {

            int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
            if (i == 1) {
                Integer.valueOf(value);
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (value + 9)});
            } else {
                if (i != 2) {
                    return;
                }
                Integer.valueOf(value);
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) MsgMgrKt.reverse(value + 9, 18)});
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {

            int i = WhenMappings.$EnumSwitchMapping$1[amplifierBand.ordinal()];
            if (i == 1) {
                sendAmplifierCmd(1, 1);
                return;
            }
            if (i == 2) {
                sendAmplifierCmd(1, 255);
                return;
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) progress});
            } else if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) progress});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) progress});
            }
        }

        private final void sendAmplifierCmd(int cmd, int param) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte) cmd, (byte) param});
        }
    }
}
