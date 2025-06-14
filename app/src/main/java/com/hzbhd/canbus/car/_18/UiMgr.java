package com.hzbhd.canbus.car._18;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
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
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010\u001d\u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001e\u001a\u00020\u001fJ \u0010 \u001a\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001fJ\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001fH\u0002J \u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0006H\u0002J\u0010\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0006H\u0002J\u000e\u0010/\u001a\u00020'2\u0006\u00100\u001a\u00020\u0006J\u000e\u00101\u001a\u00020'2\u0006\u00100\u001a\u00020\u0006J \u00102\u001a\u00020'2\u0006\u0010.\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006H\u0002J\u0012\u00104\u001a\u00020'2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0004R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/hzbhd/canbus/car/_18/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airRearWindMode", "", "getAirRearWindMode", "()I", "setAirRearWindMode", "(I)V", "airWindMode", "getAirWindMode", "setAirWindMode", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "mAmplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "mMsgMgr", "Lcom/hzbhd/canbus/car/_18/MsgMgr;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getMsgMgr", "getSettingLeftIndexes", "titleSrn", "", "getSettingRightIndex", "leftTitleSrn", "rightTitleStn", "isPanoramicUseBtn", "", "isPanoramicUseCoordinate", "selectABtn", "", "btn", "selectSBtn", "leftPos", "rightPos", "param", "sendACmd", "d0", "sendCarModelData", "d1", "sendPanoramaCmd", "sendSCmd", "d2", "updateUiByDifferentCar", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class UiMgr extends AbstractUiMgr {
    private int airRearWindMode;
    private int airWindMode;
    private final AirPageUiSet mAirPageUiSet;
    private final AmplifierPageUiSet mAmplifierPageUiSet;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private final ParkPageUiSet mParkPageUiSet;
    private final SettingPageUiSet mSettingPageUiSet;

    public UiMgr(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AirPageUiSet airUiSet = getAirUiSet(context);
        Intrinsics.checkNotNullExpressionValue(airUiSet, "getAirUiSet(context)");
        this.mAirPageUiSet = airUiSet;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(amplifierPageUiSet, "getAmplifierPageUiSet(context)");
        this.mAmplifierPageUiSet = amplifierPageUiSet;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        Intrinsics.checkNotNullExpressionValue(parkPageUiSet, "getParkPageUiSet(context)");
        this.mParkPageUiSet = parkPageUiSet;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.mSettingPageUiSet = settingUiSet;
        this.mContext = context;
        if (context.getResources().getConfiguration().orientation == 2) {
            RearArea rearArea = airUiSet.getRearArea();
            rearArea.setShowCenterWheel(false);
            rearArea.setShowLeftWheel(true);
            rearArea.setShowRightWheel(true);
            rearArea.setWindMaxLevel(7);
        }
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                this.this$0.sendACmd(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                this.this$0.sendACmd(22);
            }
        });
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m290lambda5$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m291lambda5$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m292lambda5$lambda3(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m293lambda5$lambda4(this.f$0, frontArea, i);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendACmd(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendACmd(11);
            }
        });
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendACmd(13);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendACmd(14);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendACmd(15);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendACmd(16);
            }
        }});
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(17);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(18);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(23);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$2$12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(24);
            }
        }});
        final RearArea rearArea2 = airUiSet.getRearArea();
        rearArea2.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (this.this$0.getAirRearWindMode() == 0) {
                    this.this$0.sendACmd(81);
                    this.this$0.setAirRearWindMode(1);
                } else if (this.this$0.getAirRearWindMode() == 1) {
                    this.this$0.sendACmd(82);
                    this.this$0.setAirRearWindMode(2);
                } else if (this.this$0.getAirRearWindMode() == 2) {
                    this.this$0.sendACmd(83);
                    this.this$0.setAirRearWindMode(0);
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                if (this.this$0.getAirRearWindMode() == 0) {
                    this.this$0.sendACmd(81);
                    this.this$0.setAirRearWindMode(1);
                } else if (this.this$0.getAirRearWindMode() == 1) {
                    this.this$0.sendACmd(82);
                    this.this$0.setAirRearWindMode(2);
                } else if (this.this$0.getAirRearWindMode() == 2) {
                    this.this$0.sendACmd(83);
                    this.this$0.setAirRearWindMode(0);
                }
            }
        });
        rearArea2.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m280lambda10$lambda6(this.f$0, rearArea2, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m281lambda10$lambda7(this.f$0, rearArea2, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m282lambda10$lambda8(this.f$0, rearArea2, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m283lambda10$lambda9(this.f$0, rearArea2, i);
            }
        }});
        rearArea2.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendACmd(43);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendACmd(42);
            }
        });
        rearArea2.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendACmd(32);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendACmd(33);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendACmd(64);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendACmd(65);
            }
        }});
        rearArea2.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
                this.this$0.sendACmd(69);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(67);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
                this.this$0.sendACmd(70);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(68);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(69);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$3$12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendACmd(70);
            }
        }});
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$4$1

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
                    iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {
                Intrinsics.checkNotNullParameter(amplifierPosition, "amplifierPosition");
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) DataHandleUtils.rangeNumber(value + 7, 0, 14)});
                } else {
                    if (i != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(value + 7, 0, 14), 0, 2, null)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$4$2

            /* compiled from: UiMgr.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
                    iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
                    iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
                    iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {
                Intrinsics.checkNotNullParameter(amplifierBand, "amplifierBand");
                int i = WhenMappings.$EnumSwitchMapping$0[amplifierBand.ordinal()];
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) progress});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) progress});
                    return;
                }
                if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) progress});
                    return;
                }
                if (i != 4) {
                    return;
                }
                int i2 = progress - GeneralAmplifierData.volume;
                int i3 = i2 <= 5 ? i2 : 5;
                if (i3 < -5) {
                    i3 = -5;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i3});
            }
        });
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        final Ref.IntRef intRef3 = new Ref.IntRef();
        final Ref.IntRef intRef4 = new Ref.IntRef();
        final Ref.IntRef intRef5 = new Ref.IntRef();
        if (isPanoramicUseBtn() || isPanoramicUseCoordinate()) {
            m284lambda13$initPanoramicBtnPara(intRef, intRef2, intRef3, intRef4, intRef5, context);
            parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
                public final void onTouchItem(MotionEvent motionEvent) {
                    UiMgr.m285lambda13$lambda12(this.f$0, intRef2, intRef4, intRef3, intRef5, intRef, context, motionEvent);
                }
            });
        }
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m286lambda18$lambda14(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m287lambda18$lambda15(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m288lambda18$lambda16(this.f$0, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._18.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m289lambda18$lambda17(this.f$0, context, i, i2);
            }
        });
    }

    public final int getAirWindMode() {
        return this.airWindMode;
    }

    public final void setAirWindMode(int i) {
        this.airWindMode = i;
    }

    public final int getAirRearWindMode() {
        return this.airRearWindMode;
    }

    public final void setAirRearWindMode(int i) {
        this.airRearWindMode = i;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-1, reason: not valid java name */
    public static final void m290lambda5$lambda1(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-2, reason: not valid java name */
    public static final void m291lambda5$lambda2(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-3, reason: not valid java name */
    public static final void m292lambda5$lambda3(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-5$lambda-4, reason: not valid java name */
    public static final void m293lambda5$lambda4(UiMgr this$0, FrontArea frontArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-6, reason: not valid java name */
    public static final void m280lambda10$lambda6(UiMgr this$0, RearArea rearArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = rearArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-7, reason: not valid java name */
    public static final void m281lambda10$lambda7(UiMgr this$0, RearArea rearArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = rearArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-8, reason: not valid java name */
    public static final void m282lambda10$lambda8(UiMgr this$0, RearArea rearArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = rearArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        this$0.selectABtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-10$lambda-9, reason: not valid java name */
    public static final void m283lambda10$lambda9(UiMgr this$0, RearArea rearArea, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = rearArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        this$0.selectABtn(str);
    }

    /* renamed from: lambda-13$initPanoramicBtnPara, reason: not valid java name */
    private static final void m284lambda13$initPanoramicBtnPara(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.IntRef intRef5, Context context) {
        intRef.element = context.getResources().getDisplayMetrics().widthPixels;
        intRef2.element = context.getResources().getDisplayMetrics().heightPixels;
        intRef3.element = CommUtil.getDimenByResId(context, "dp140");
        intRef4.element = CommUtil.getDimenByResId(context, "dp100");
        intRef5.element = ((intRef.element * 3) / 4) - 20;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-13$lambda-12, reason: not valid java name */
    public static final void m285lambda13$lambda12(UiMgr this$0, Ref.IntRef mMaxHigh, Ref.IntRef mBtnHeight, Ref.IntRef mBtnWidth, Ref.IntRef mBtnThreeEnd, Ref.IntRef mMaxWidth, Context context, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mMaxHigh, "$mMaxHigh");
        Intrinsics.checkNotNullParameter(mBtnHeight, "$mBtnHeight");
        Intrinsics.checkNotNullParameter(mBtnWidth, "$mBtnWidth");
        Intrinsics.checkNotNullParameter(mBtnThreeEnd, "$mBtnThreeEnd");
        Intrinsics.checkNotNullParameter(mMaxWidth, "$mMaxWidth");
        Intrinsics.checkNotNullParameter(context, "$context");
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (!this$0.isPanoramicUseBtn()) {
            if (action == 1) {
                try {
                    int i = (x * 1023) / context.getResources().getDisplayMetrics().widthPixels;
                    int i2 = (y * 1000) / context.getResources().getDisplayMetrics().heightPixels;
                    CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), 0});
                    this$0.playBeep();
                    return;
                } catch (Exception e) {
                    Log.e("18UI", Log.getStackTraceString(e));
                    return;
                }
            }
            return;
        }
        if (action != 0 || y < mMaxHigh.element - mBtnHeight.element || y > mMaxHigh.element) {
            return;
        }
        if (x <= mBtnWidth.element) {
            this$0.sendPanoramaCmd(1);
            return;
        }
        if (x >= mBtnThreeEnd.element - mBtnWidth.element && x <= mBtnThreeEnd.element) {
            this$0.sendPanoramaCmd(3);
        } else if (x >= mMaxWidth.element - mBtnWidth.element) {
            this$0.sendPanoramaCmd(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-18$lambda-14, reason: not valid java name */
    public static final void m286lambda18$lambda14(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.selectSBtn(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-18$lambda-15, reason: not valid java name */
    public static final void m287lambda18$lambda15(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.selectSBtn(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-18$lambda-16, reason: not valid java name */
    public static final void m288lambda18$lambda16(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.selectSBtn(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-18$lambda-17, reason: not valid java name */
    public static final void m289lambda18$lambda17(UiMgr this$0, Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        if (i == this$0.getSettingLeftIndexes(context, "S18Title1")) {
            if (i2 == this$0.getSettingRightIndex(context, "S18Title1", "_18_park_0")) {
                this$0.sendSCmd(1, 10, 1);
            }
            if (i2 == this$0.getSettingRightIndex(context, "S18Title1", "_18_park_1")) {
                this$0.sendSCmd(1, 10, 2);
            }
        }
        if (i == this$0.getSettingLeftIndexes(context, "_18_reset")) {
            if (i2 == this$0.getSettingRightIndex(context, "_18_reset", "_18_reset_1")) {
                this$0.sendSCmd(4, 1, 1);
                Context context2 = this$0.mContext;
                Toast.makeText(context2, context2.getString(R.string._18_reset_1), 0).show();
            }
            if (i2 == this$0.getSettingRightIndex(context, "_18_reset", "_18_reset_2")) {
                this$0.sendSCmd(4, 2, 1);
                Context context3 = this$0.mContext;
                Toast.makeText(context3, context3.getString(R.string._18_reset_2), 0).show();
            }
            if (i2 == this$0.getSettingRightIndex(context, "_18_reset", "_18_reset_3")) {
                this$0.sendSCmd(4, 2, 2);
                Context context4 = this$0.mContext;
                Toast.makeText(context4, context4.getString(R.string._18_reset_3), 0).show();
            }
            if (i2 == this$0.getSettingRightIndex(context, "_18_reset", "_18_reset_4")) {
                this$0.sendSCmd(4, 4, 1);
                Context context5 = this$0.mContext;
                Toast.makeText(context5, context5.getString(R.string._18_reset_4), 0).show();
            }
            if (i2 == this$0.getSettingRightIndex(context, "_18_reset", "_18_reset_5")) {
                this$0.sendSCmd(4, 5, 1);
                Context context6 = this$0.mContext;
                Toast.makeText(context6, context6.getString(R.string._18_reset_5), 0).show();
            }
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        int i;
        int currentCarId = getCurrentCarId();
        removeSettingRightItemByNameList(context, new String[]{"S18_park_3", "S18_park_4"});
        IntRange intRange = new IntRange(1, 15);
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRange.iterator();
        while (true) {
            i = 3;
            if (!it.hasNext()) {
                break;
            }
            Integer next = it.next();
            if (SetsKt.setOf((Object[]) new Integer[]{3, 10}).contains(Integer.valueOf(next.intValue()))) {
                arrayList.add(next);
            }
        }
        if (arrayList.contains(Integer.valueOf(currentCarId))) {
            removeMainPrjBtnByName(context, "air");
        }
        ArrayList arrayList2 = new ArrayList();
        for (Integer num : intRange) {
            if (!SetsKt.setOf((Object[]) new Integer[]{6, 13, 14, 15}).contains(Integer.valueOf(num.intValue()))) {
                arrayList2.add(num);
            }
        }
        if (arrayList2.contains(Integer.valueOf(currentCarId))) {
            FrontArea frontArea = this.mAirPageUiSet.getFrontArea();
            frontArea.setAllBtnCanClick(false);
            frontArea.setCanSetLeftTemp(false);
            frontArea.setCanSetRightTemp(false);
            frontArea.setCanSetWindSpeed(false);
            frontArea.setCanSetSeatHeat(false);
            frontArea.setShowSeatCold(false);
            RearArea rearArea = this.mAirPageUiSet.getRearArea();
            rearArea.setAllBtnCanClick(false);
            rearArea.setCanSetTemp(false);
            rearArea.setCanSetWindSpeed(false);
            rearArea.setCanSetSeatHeat(false);
            rearArea.setCanSetSeatCold(false);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Integer num2 : intRange) {
            if (SetsKt.setOf(Integer.valueOf(i)).contains(Integer.valueOf(num2.intValue()))) {
                arrayList3.add(num2);
            }
            i = 3;
        }
        if (arrayList3.contains(Integer.valueOf(currentCarId))) {
            this.mParkPageUiSet.setShowRadar(false);
        }
        ArrayList arrayList4 = new ArrayList();
        for (Integer num3 : intRange) {
            if (SetsKt.setOf(14).contains(Integer.valueOf(num3.intValue()))) {
                arrayList4.add(num3);
            }
        }
        if (arrayList4.contains(Integer.valueOf(currentCarId))) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        ArrayList arrayList5 = new ArrayList();
        for (Integer num4 : intRange) {
            if (!SetsKt.setOf((Object[]) new Integer[]{1, 2, 13, 14}).contains(Integer.valueOf(num4.intValue()))) {
                arrayList5.add(num4);
            }
        }
        if (arrayList5.contains(Integer.valueOf(currentCarId))) {
            removeMainPrjBtnByName(context, MainAction.AMPLIFIER);
        }
        ArrayList arrayList6 = new ArrayList();
        for (Integer num5 : intRange) {
            if (!SetsKt.setOf((Object[]) new Integer[]{2, 4, 6, 8, 10, 11, 13, 15}).contains(Integer.valueOf(num5.intValue()))) {
                arrayList6.add(num5);
            }
        }
        if (arrayList6.contains(Integer.valueOf(currentCarId))) {
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
        }
        ArrayList arrayList7 = new ArrayList();
        for (Integer num6 : intRange) {
            if (!SetsKt.setOf((Object[]) new Integer[]{4, 14, 5, 6, 7, 11, 15}).contains(Integer.valueOf(num6.intValue()))) {
                arrayList7.add(num6);
            }
        }
        if (arrayList7.contains(Integer.valueOf(currentCarId))) {
            removeMainPrjBtnByName(context, MainAction.HYBIRD);
        }
    }

    private final boolean isPanoramicUseBtn() {
        return getCurrentCarId() == 4 || getCurrentCarId() == 10;
    }

    private final boolean isPanoramicUseCoordinate() {
        return (getCurrentCarId() == 4 && getCurrentCarId() == 10) ? false : true;
    }

    public final void sendPanoramaCmd(int d1) {
        byte b = (byte) d1;
        CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, b, 0});
        playBeep();
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
    private final void selectABtn(String btn) {
        switch (btn.hashCode()) {
            case -1470045433:
                if (btn.equals("front_defog")) {
                    sendACmd(5);
                    break;
                }
                break;
            case -1406322206:
                if (btn.equals(AirBtnAction.AUTO_R)) {
                    sendACmd(66);
                    break;
                }
                break;
            case -1274277292:
                if (btn.equals(AirBtnAction.CLEAN_AIR)) {
                    sendACmd(25);
                    break;
                }
                break;
            case -713186454:
                if (btn.equals(AirBtnAction.REAR_AUTO)) {
                    sendACmd(66);
                    break;
                }
                break;
            case -712865050:
                if (btn.equals(AirBtnAction.REAR_LOCK)) {
                    sendACmd(34);
                    break;
                }
                break;
            case -631663038:
                if (btn.equals("rear_defog")) {
                    sendACmd(6);
                    break;
                }
                break;
            case -620266838:
                if (btn.equals(AirBtnAction.REAR_POWER)) {
                    sendACmd(46);
                    break;
                }
                break;
            case 3106:
                if (btn.equals("ac")) {
                    sendACmd(2);
                    break;
                }
                break;
            case 100241:
                if (btn.equals(AirBtnAction.ECO)) {
                    sendACmd(35);
                    break;
                }
                break;
            case 3005871:
                if (btn.equals("auto")) {
                    sendACmd(4);
                    break;
                }
                break;
            case 3094652:
                if (btn.equals("dual")) {
                    sendACmd(41);
                    break;
                }
                break;
            case 3545755:
                if (btn.equals("sync")) {
                    sendACmd(3);
                    break;
                }
                break;
            case 106858757:
                if (btn.equals("power")) {
                    sendACmd(1);
                    break;
                }
                break;
            case 756225563:
                if (btn.equals("in_out_cycle")) {
                    sendACmd(7);
                    break;
                }
                break;
        }
    }

    private static final void selectSBtn$send0xADData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte) i, (byte) i2});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    private final void selectSBtn(int leftPos, int rightPos, int param) {
        String titleSrn = this.mSettingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            if (iHashCode == 748741536) {
                if (titleSrn.equals("S18_Car_0")) {
                    sendCarModelData(param);
                    MsgMgr msgMgr = getMsgMgr(this.mContext);
                    if (msgMgr != null) {
                        msgMgr.updateSettings(leftPos, rightPos, param);
                    }
                    return;
                }
                return;
            }
            if (iHashCode == 899845314) {
                if (titleSrn.equals("_18_language_setting")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (param + 1)});
                    MsgMgr msgMgr2 = getMsgMgr(this.mContext);
                    if (msgMgr2 != null) {
                        msgMgr2.updateSettings(leftPos, rightPos, param);
                        return;
                    }
                    return;
                }
                return;
            }
            switch (iHashCode) {
                case -1372525502:
                    if (titleSrn.equals("S18_lock_1")) {
                        sendSCmd(1, 1, param);
                        break;
                    }
                    break;
                case -1372525501:
                    if (titleSrn.equals("S18_lock_2")) {
                        sendSCmd(1, 2, param);
                        break;
                    }
                    break;
                case -1372525500:
                    if (titleSrn.equals("S18_lock_3")) {
                        sendSCmd(1, 3, param);
                        break;
                    }
                    break;
                case -1372525499:
                    if (titleSrn.equals("S18_lock_4")) {
                        sendSCmd(1, 4, param);
                        break;
                    }
                    break;
                case -1372525498:
                    if (titleSrn.equals("S18_lock_5")) {
                        sendSCmd(1, 5, param);
                        break;
                    }
                    break;
                case -1372525497:
                    if (titleSrn.equals("S18_lock_6")) {
                        sendSCmd(1, 6, param);
                        break;
                    }
                    break;
                case -1372525496:
                    if (titleSrn.equals("S18_lock_7")) {
                        sendSCmd(1, 7, param);
                        break;
                    }
                    break;
                default:
                    switch (iHashCode) {
                        case -1270491327:
                            if (titleSrn.equals("S18_park_1")) {
                                sendSCmd(1, 8, param);
                                break;
                            }
                            break;
                        case -1270491326:
                            if (titleSrn.equals("S18_park_2")) {
                                sendSCmd(1, 9, param);
                                break;
                            }
                            break;
                        case -1270491325:
                            if (titleSrn.equals("S18_park_3")) {
                                sendSCmd(1, 10, 1);
                                break;
                            }
                            break;
                        case -1270491324:
                            if (titleSrn.equals("S18_park_4")) {
                                sendSCmd(1, 10, 2);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case 233233859:
                                    if (titleSrn.equals("S18_light_1")) {
                                        sendSCmd(1, 11, param);
                                        break;
                                    }
                                    break;
                                case 233233860:
                                    if (titleSrn.equals("S18_light_2")) {
                                        sendSCmd(3, 3, param);
                                        break;
                                    }
                                    break;
                                case 233233861:
                                    if (titleSrn.equals("S18_light_3")) {
                                        sendSCmd(3, 2, param);
                                        break;
                                    }
                                    break;
                                case 233233862:
                                    if (titleSrn.equals("S18_light_4")) {
                                        sendSCmd(3, 1, param);
                                        break;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case 776685495:
                                            if (titleSrn.equals("S18_air_1")) {
                                                sendSCmd(1, 12, param);
                                                break;
                                            }
                                            break;
                                        case 776685496:
                                            if (titleSrn.equals("S18_air_2")) {
                                                sendSCmd(1, 13, param);
                                                break;
                                            }
                                            break;
                                        case 776685497:
                                            if (titleSrn.equals("S18_air_3")) {
                                                sendSCmd(1, 15, param);
                                                break;
                                            }
                                            break;
                                        case 776685498:
                                            if (titleSrn.equals("S18_air_4")) {
                                                sendSCmd(2, 5, param);
                                                break;
                                            }
                                            break;
                                        default:
                                            switch (iHashCode) {
                                                case 1356018684:
                                                    if (titleSrn.equals("_18_other_settings_0")) {
                                                        sendSCmd(1, 16, param);
                                                        break;
                                                    }
                                                    break;
                                                case 1356018685:
                                                    if (titleSrn.equals("_18_other_settings_1")) {
                                                        sendSCmd(1, 18, param);
                                                        break;
                                                    }
                                                    break;
                                                case 1356018686:
                                                    if (titleSrn.equals("_18_other_settings_2")) {
                                                        sendSCmd(1, 17, param);
                                                        break;
                                                    }
                                                    break;
                                                case 1356018687:
                                                    if (titleSrn.equals("_18_other_settings_3")) {
                                                        sendSCmd(1, 19, param);
                                                        break;
                                                    }
                                                    break;
                                                default:
                                                    switch (iHashCode) {
                                                        case 1430441821:
                                                            if (titleSrn.equals("S18_remote_1")) {
                                                                sendSCmd(2, 1, param);
                                                                break;
                                                            }
                                                            break;
                                                        case 1430441822:
                                                            if (titleSrn.equals("S18_remote_2")) {
                                                                sendSCmd(2, 4, param);
                                                                break;
                                                            }
                                                            break;
                                                        case 1430441823:
                                                            if (titleSrn.equals("S18_remote_3")) {
                                                                sendSCmd(2, 2, param);
                                                                break;
                                                            }
                                                            break;
                                                        case 1430441824:
                                                            if (titleSrn.equals("S18_remote_4")) {
                                                                sendSCmd(2, 3, param);
                                                                break;
                                                            }
                                                            break;
                                                        case 1430441825:
                                                            if (titleSrn.equals("S18_remote_5")) {
                                                                sendSCmd(1, 14, param);
                                                                break;
                                                            }
                                                            break;
                                                        default:
                                                            switch (iHashCode) {
                                                                case 2106546174:
                                                                    if (titleSrn.equals("S18xA61")) {
                                                                        selectSBtn$send0xADData(7, param);
                                                                        break;
                                                                    }
                                                                    break;
                                                                case 2106546175:
                                                                    if (titleSrn.equals("S18xA62")) {
                                                                        selectSBtn$send0xADData(8, param);
                                                                        break;
                                                                    }
                                                                    break;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
    }

    private final MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
            Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._18.MsgMgr");
            this.mMsgMgr = (MsgMgr) canMsgMgr;
        }
        return this.mMsgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendACmd(int d0) {
        byte b = (byte) d0;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    private final void sendSCmd(int d0, int d1, int d2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte) d0, (byte) d1, (byte) d2});
    }

    public final void sendCarModelData(int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) (d1 + 128), 1});
    }

    public final int getSettingLeftIndexes(Context context, String titleSrn) {
        Intrinsics.checkNotNullParameter(titleSrn, "titleSrn");
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(titleSrn, it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    public final int getSettingRightIndex(Context context, String leftTitleSrn, String rightTitleStn) {
        Intrinsics.checkNotNullParameter(leftTitleSrn, "leftTitleSrn");
        Intrinsics.checkNotNullParameter(rightTitleStn, "rightTitleStn");
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (Intrinsics.areEqual(leftTitleSrn, next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                int size2 = itemList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    if (Intrinsics.areEqual(rightTitleStn, it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }
}
