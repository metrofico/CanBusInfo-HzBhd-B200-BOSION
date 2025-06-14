package com.hzbhd.canbus.car._340;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010A\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0002J\u0018\u0010F\u001a\u00020C2\u0006\u0010G\u001a\u00020E2\u0006\u0010H\u001a\u00020IH\u0002J\u0018\u0010J\u001a\u00020C2\u0006\u0010G\u001a\u00020E2\u0006\u0010H\u001a\u00020IH\u0002J\u0018\u0010K\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010L\u001a\u00020EH\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@¨\u0006M"}, d2 = {"Lcom/hzbhd/canbus/car/_340/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAirPageUiSet", "()Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "setAirPageUiSet", "(Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "mMsgMgr", "Lcom/hzbhd/canbus/car/_340/MsgMgr;", "getMMsgMgr", "()Lcom/hzbhd/canbus/car/_340/MsgMgr;", "setMMsgMgr", "(Lcom/hzbhd/canbus/car/_340/MsgMgr;)V", "onAirBtnClickListenerFrontBottom", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "onAirBtnClickListenerFrontLeft", "onAirBtnClickListenerFrontRight", "onAirBtnClickListenerFrontTop", "onAirTemperatureUpDownClickListenerFrontLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "onAirTemperatureUpDownClickListenerFrontRight", "onAirWindSpeedUpDownClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "getOnAirWindSpeedUpDownClickListener", "()Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "setOnAirWindSpeedUpDownClickListener", "(Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;)V", "onSettingItemClickListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemClickListener;", "getOnSettingItemClickListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemClickListener;", "setOnSettingItemClickListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemClickListener;)V", "onSettingItemSeekbarSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "getOnSettingItemSeekbarSelectListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "setOnSettingItemSeekbarSelectListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;)V", "onSettingItemSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "getOnSettingItemSelectListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "setOnSettingItemSelectListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;)V", "onSettingItemSwitchListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "getOnSettingItemSwitchListener", "()Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "setOnSettingItemSwitchListener", "(Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;)V", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "setSettingPageUiSet", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMsgMgr", "sendAirData", "", "data0", "", "sendAirLeftTemData", "cmd", "isUpAction", "", "sendAirRightTemData", "sendSettingData", "data1", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private OnAirBtnClickListener onAirBtnClickListenerFrontBottom;
    private OnAirBtnClickListener onAirBtnClickListenerFrontLeft;
    private OnAirBtnClickListener onAirBtnClickListenerFrontRight;
    private OnAirBtnClickListener onAirBtnClickListenerFrontTop;
    private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerFrontLeft;
    private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerFrontRight;
    private OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener;
    private OnSettingItemClickListener onSettingItemClickListener;
    private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
    private OnSettingItemSelectListener onSettingItemSelectListener;
    private OnSettingItemSwitchListener onSettingItemSwitchListener;
    private SettingPageUiSet settingPageUiSet;

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "getSettingUiSet(context)");
        this.settingPageUiSet = settingUiSet;
        this.onAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m726onAirBtnClickListenerFrontTop$lambda0(this.f$0, i);
            }
        };
        this.onAirBtnClickListenerFrontLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m724onAirBtnClickListenerFrontLeft$lambda1(this.f$0, i);
            }
        };
        this.onAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m725onAirBtnClickListenerFrontRight$lambda2(this.f$0, i);
            }
        };
        this.onAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m723onAirBtnClickListenerFrontBottom$lambda3(this.f$0, i);
            }
        };
        this.onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$onAirWindSpeedUpDownClickListener$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirData(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirData(11);
            }
        };
        this.onAirTemperatureUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$onAirTemperatureUpDownClickListenerFrontLeft$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirLeftTemData(19, true);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirLeftTemData(19, false);
            }
        };
        this.onAirTemperatureUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$onAirTemperatureUpDownClickListenerFrontRight$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirRightTemData(20, true);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirRightTemData(20, false);
            }
        };
        this.onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m728onSettingItemSeekbarSelectListener$lambda4(this.f$0, i, i2, i3);
            }
        };
        this.onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m727onSettingItemClickListener$lambda5(this.f$0, i, i2);
            }
        };
        this.onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m729onSettingItemSelectListener$lambda6(this.f$0, i, i2, i3);
            }
        };
        this.onSettingItemSwitchListener = new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._340.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.m730onSettingItemSwitchListener$lambda7(this.f$0, i, i2, i3);
            }
        };
        this.settingPageUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        this.settingPageUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        this.settingPageUiSet.setOnSettingItemSwitchListener(this.onSettingItemSwitchListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        Intrinsics.checkNotNullExpressionValue(airUiSet, "getAirUiSet(context)");
        this.airPageUiSet = airUiSet;
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerFrontTop, this.onAirBtnClickListenerFrontLeft, this.onAirBtnClickListenerFrontRight, this.onAirBtnClickListenerFrontBottom});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerFrontLeft, null, this.onAirTemperatureUpDownClickListenerFrontRight});
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._340.UiMgr.1
            private int i;
            private final byte[] mode = {1, 2, 3, 4};

            public final byte[] getMode() {
                return this.mode;
            }

            public final int getI() {
                return this.i;
            }

            public final void setI(int i) {
                this.i = i;
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                byte[] bArr = this.mode;
                int i = this.i;
                this.i = i + 1;
                sendAirConditionerModeData(bArr[i % 4]);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                byte[] bArr = this.mode;
                int i = this.i;
                this.i = i + 1;
                sendAirConditionerModeData(bArr[i % 4]);
            }

            public final void sendAirConditionerModeData(byte d1) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new UiMgr$1$sendAirConditionerModeData$1(d1, null), 3, null);
            }
        });
        if (getCurrentCarId() == 8 || getCurrentCarId() == 9) {
            return;
        }
        removeMainPrjBtnByName(context, "air");
        removeMainPrjBtnByName(context, MainAction.SETTING);
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    public final SettingPageUiSet getSettingPageUiSet() {
        return this.settingPageUiSet;
    }

    public final void setSettingPageUiSet(SettingPageUiSet settingPageUiSet) {
        Intrinsics.checkNotNullParameter(settingPageUiSet, "<set-?>");
        this.settingPageUiSet = settingPageUiSet;
    }

    public final AirPageUiSet getAirPageUiSet() {
        return this.airPageUiSet;
    }

    public final void setAirPageUiSet(AirPageUiSet airPageUiSet) {
        Intrinsics.checkNotNullParameter(airPageUiSet, "<set-?>");
        this.airPageUiSet = airPageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontTop$lambda-0, reason: not valid java name */
    public static final void m726onAirBtnClickListenerFrontTop$lambda0(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirData(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontLeft$lambda-1, reason: not valid java name */
    public static final void m724onAirBtnClickListenerFrontLeft$lambda1(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirData(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontRight$lambda-2, reason: not valid java name */
    public static final void m725onAirBtnClickListenerFrontRight$lambda2(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirData(23);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onAirBtnClickListenerFrontBottom$lambda-3, reason: not valid java name */
    public static final void m723onAirBtnClickListenerFrontBottom$lambda3(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirData(21);
        } else if (i == 1) {
            this$0.sendAirData(2);
        } else {
            if (i != 2) {
                return;
            }
            this$0.sendAirData(3);
        }
    }

    public final OnAirWindSpeedUpDownClickListener getOnAirWindSpeedUpDownClickListener() {
        return this.onAirWindSpeedUpDownClickListener;
    }

    public final void setOnAirWindSpeedUpDownClickListener(OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener) {
        Intrinsics.checkNotNullParameter(onAirWindSpeedUpDownClickListener, "<set-?>");
        this.onAirWindSpeedUpDownClickListener = onAirWindSpeedUpDownClickListener;
    }

    public final OnSettingItemSeekbarSelectListener getOnSettingItemSeekbarSelectListener() {
        return this.onSettingItemSeekbarSelectListener;
    }

    public final void setOnSettingItemSeekbarSelectListener(OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener) {
        Intrinsics.checkNotNullParameter(onSettingItemSeekbarSelectListener, "<set-?>");
        this.onSettingItemSeekbarSelectListener = onSettingItemSeekbarSelectListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSettingItemSeekbarSelectListener$lambda-4, reason: not valid java name */
    public static final void m728onSettingItemSeekbarSelectListener$lambda4(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String titleSrn = this$0.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (i == 0 && Intrinsics.areEqual(titleSrn, "_340_adaptive_cruise_speed_setting")) {
            this$0.sendSettingData(18, i3);
        }
    }

    public final OnSettingItemClickListener getOnSettingItemClickListener() {
        return this.onSettingItemClickListener;
    }

    public final void setOnSettingItemClickListener(OnSettingItemClickListener onSettingItemClickListener) {
        Intrinsics.checkNotNullParameter(onSettingItemClickListener, "<set-?>");
        this.onSettingItemClickListener = onSettingItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSettingItemClickListener$lambda-5, reason: not valid java name */
    public static final void m727onSettingItemClickListener$lambda5(UiMgr this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            if (i2 == 0) {
                this$0.sendSettingData(0, 1);
            } else {
                if (i2 != 2) {
                    return;
                }
                this$0.sendSettingData(2, 1);
            }
        }
    }

    public final OnSettingItemSelectListener getOnSettingItemSelectListener() {
        return this.onSettingItemSelectListener;
    }

    public final void setOnSettingItemSelectListener(OnSettingItemSelectListener onSettingItemSelectListener) {
        Intrinsics.checkNotNullParameter(onSettingItemSelectListener, "<set-?>");
        this.onSettingItemSelectListener = onSettingItemSelectListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSettingItemSelectListener$lambda-6, reason: not valid java name */
    public static final void m729onSettingItemSelectListener$lambda6(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            if (i2 == 13) {
                this$0.sendSettingData(13, i3 + 1);
                return;
            }
            if (i2 == 20) {
                this$0.sendSettingData(20, i3 + 1);
                return;
            }
            if (i2 == 15) {
                this$0.sendSettingData(15, i3 + 1);
                return;
            }
            if (i2 != 16) {
                switch (i2) {
                    case 8:
                        this$0.sendSettingData(8, i3 + 1);
                        break;
                    case 9:
                        this$0.sendSettingData(9, i3 + 1);
                        break;
                    case 10:
                        this$0.sendSettingData(10, i3 + 1);
                        break;
                }
                return;
            }
            this$0.sendSettingData(16, i3 + 1);
        }
    }

    public final OnSettingItemSwitchListener getOnSettingItemSwitchListener() {
        return this.onSettingItemSwitchListener;
    }

    public final void setOnSettingItemSwitchListener(OnSettingItemSwitchListener onSettingItemSwitchListener) {
        Intrinsics.checkNotNullParameter(onSettingItemSwitchListener, "<set-?>");
        this.onSettingItemSwitchListener = onSettingItemSwitchListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSettingItemSwitchListener$lambda-7, reason: not valid java name */
    public static final void m730onSettingItemSwitchListener$lambda7(UiMgr this$0, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            if (i2 == 1) {
                this$0.sendSettingData(1, i3 + 1);
                return;
            }
            if (i2 == 14) {
                this$0.sendSettingData(14, i3 + 1);
                return;
            }
            if (i2 == 17) {
                this$0.sendSettingData(17, i3 + 1);
                return;
            }
            if (i2 == 19) {
                this$0.sendSettingData(19, i3 + 1);
                return;
            }
            if (i2 == 21) {
                this$0.sendSettingData(21, i3 + 1);
                return;
            }
            if (i2 == 3) {
                this$0.sendSettingData(3, i3 + 1);
                return;
            }
            if (i2 == 4) {
                this$0.sendSettingData(4, i3 + 1);
                return;
            }
            if (i2 == 5) {
                this$0.sendSettingData(5, i3 + 1);
                return;
            }
            if (i2 == 6) {
                this$0.sendSettingData(6, i3 + 1);
                return;
            }
            if (i2 == 7) {
                this$0.sendSettingData(7, i3 + 1);
            } else if (i2 == 11) {
                this$0.sendSettingData(11, i3 + 1);
            } else {
                if (i2 != 12) {
                    return;
                }
                this$0.sendSettingData(12, i3 + 1);
            }
        }
    }

    private final void sendSettingData(int data0, int data1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) data0, (byte) data1});
    }

    /* compiled from: UiMgr.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.hzbhd.canbus.car._340.UiMgr$sendAirData$1", f = "UiMgr.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.hzbhd.canbus.car._340.UiMgr$sendAirData$1, reason: invalid class name and case insensitive filesystem */
    static final class C00411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $data0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00411(int i, Continuation<? super C00411> continuation) {
            super(2, continuation);
            this.$data0 = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00411(this.$data0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) this.$data0, 1});
                this.label = 1;
                if (DelayKt.delay(200L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) this.$data0, 0});
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirData(int data0) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C00411(data0, null), 3, null);
    }

    public final MsgMgr getMMsgMgr() {
        return this.mMsgMgr;
    }

    public final void setMMsgMgr(MsgMgr msgMgr) {
        this.mMsgMgr = msgMgr;
    }

    private final MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
            Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._340.MsgMgr");
            this.mMsgMgr = (MsgMgr) canMsgMgr;
        }
        return this.mMsgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirLeftTemData(int cmd, boolean isUpAction) {
        Integer numValueOf;
        int iIntValue;
        Context context = this.mContext;
        int i = 40;
        if (context != null) {
            MsgMgr msgMgr = getMsgMgr(context);
            if (msgMgr != null && msgMgr.nowLeftValue == 254) {
                StringBuilder sbAppend = new StringBuilder().append("空调温度：最高");
                MsgMgr msgMgr2 = getMsgMgr(context);
                MyLog.temporaryTracking(sbAppend.append(msgMgr2 != null ? Integer.valueOf(msgMgr2.nowLeftValue) : null).toString());
                if (!isUpAction) {
                    return;
                } else {
                    iIntValue = 37;
                }
            } else {
                MsgMgr msgMgr3 = getMsgMgr(context);
                if (msgMgr3 != null && msgMgr3.nowLeftValue == 255) {
                    StringBuilder sbAppend2 = new StringBuilder().append("空调温度：最低");
                    MsgMgr msgMgr4 = getMsgMgr(context);
                    MyLog.temporaryTracking(sbAppend2.append(msgMgr4 != null ? Integer.valueOf(msgMgr4.nowLeftValue) : null).toString());
                    if (isUpAction) {
                        return;
                    } else {
                        iIntValue = 62;
                    }
                } else {
                    MsgMgr msgMgr5 = getMsgMgr(context);
                    if (msgMgr5 != null && msgMgr5.nowLeftValue == 0) {
                        StringBuilder sbAppend3 = new StringBuilder().append("空调温度：异常");
                        MsgMgr msgMgr6 = getMsgMgr(context);
                        MyLog.temporaryTracking(sbAppend3.append(msgMgr6 != null ? Integer.valueOf(msgMgr6.nowLeftValue) : null).toString());
                    } else {
                        StringBuilder sbAppend4 = new StringBuilder().append("空调温度：常值");
                        MsgMgr msgMgr7 = getMsgMgr(context);
                        MyLog.temporaryTracking(sbAppend4.append(msgMgr7 != null ? Integer.valueOf(msgMgr7.nowLeftValue) : null).append('1').toString());
                        if (isUpAction) {
                            MsgMgr msgMgr8 = getMsgMgr(context);
                            numValueOf = msgMgr8 != null ? Integer.valueOf(msgMgr8.nowLeftValue) : null;
                            Intrinsics.checkNotNull(numValueOf);
                            iIntValue = numValueOf.intValue() + 1;
                        } else {
                            MsgMgr msgMgr9 = getMsgMgr(context);
                            numValueOf = msgMgr9 != null ? Integer.valueOf(msgMgr9.nowLeftValue) : null;
                            Intrinsics.checkNotNull(numValueOf);
                            iIntValue = numValueOf.intValue() - 1;
                        }
                    }
                }
            }
            i = iIntValue;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) cmd, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirRightTemData(int cmd, boolean isUpAction) {
        Integer numValueOf;
        int iIntValue;
        Context context = this.mContext;
        int i = 40;
        if (context != null) {
            MsgMgr msgMgr = getMsgMgr(context);
            if (!(msgMgr != null && msgMgr.nowRightValue == 254)) {
                MsgMgr msgMgr2 = getMsgMgr(context);
                if (!(msgMgr2 != null && msgMgr2.nowRightValue == 255)) {
                    MsgMgr msgMgr3 = getMsgMgr(context);
                    if (!(msgMgr3 != null && msgMgr3.nowRightValue == 0)) {
                        if (isUpAction) {
                            MsgMgr msgMgr4 = getMsgMgr(context);
                            numValueOf = msgMgr4 != null ? Integer.valueOf(msgMgr4.nowRightValue) : null;
                            Intrinsics.checkNotNull(numValueOf);
                            iIntValue = numValueOf.intValue() + 1;
                        } else {
                            MsgMgr msgMgr5 = getMsgMgr(context);
                            numValueOf = msgMgr5 != null ? Integer.valueOf(msgMgr5.nowRightValue) : null;
                            Intrinsics.checkNotNull(numValueOf);
                            iIntValue = numValueOf.intValue() - 1;
                        }
                    }
                } else if (isUpAction) {
                    return;
                } else {
                    iIntValue = 62;
                }
            } else if (!isUpAction) {
                return;
            } else {
                iIntValue = 37;
            }
            i = iIntValue;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) cmd, (byte) i});
    }
}
