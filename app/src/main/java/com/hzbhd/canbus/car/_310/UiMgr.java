package com.hzbhd.canbus.car._310;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._310.MyPanoramicView;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private final String TAG = "_310_UiMgr";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private MsgMgr mMsgMgr;
    private MyPanoramicView myPanoramicView;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.myPanoramicView == null) {
            this.myPanoramicView = new MyPanoramicView(context);
        }
        return this.myPanoramicView;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m485lambda$new$0$comhzbhdcanbuscar_310UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m486lambda$new$1$comhzbhdcanbuscar_310UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda13
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m488lambda$new$2$comhzbhdcanbuscar_310UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda14
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m489lambda$new$3$comhzbhdcanbuscar_310UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(3);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(2);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(5);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(4);
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(10);
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda15
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m490lambda$new$4$comhzbhdcanbuscar_310UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(39);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(38);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(47);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(46);
            }
        }});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(40);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(41);
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m491lambda$new$5$comhzbhdcanbuscar_310UiMgr(settingUiSet, context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.lambda$new$6(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.lambda$new$7(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.lambda$new$8(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                UiMgr.lambda$new$9(settingUiSet, i, i2, i3);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                UiMgr.lambda$new$10(amplifierPosition, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                UiMgr.lambda$new$11(amplifierBand, i);
            }
        });
        getParkPageUiSet(context).setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public final void addViewToWindows() {
                this.f$0.m487lambda$new$12$comhzbhdcanbuscar_310UiMgr(context);
            }
        });
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public final void click(int i) {
                UiMgr.lambda$new$13(panelKeyPageUiSet, i);
            }
        });
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public final void onClickBottomBtnItem(int i) {
                UiMgr.lambda$new$14(originalCarDevicePageUiSet, i);
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_310-UiMgr, reason: not valid java name */
    /* synthetic */ void m485lambda$new$0$comhzbhdcanbuscar_310UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirBtnCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_310-UiMgr, reason: not valid java name */
    /* synthetic */ void m486lambda$new$1$comhzbhdcanbuscar_310UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirBtnCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_310-UiMgr, reason: not valid java name */
    /* synthetic */ void m488lambda$new$2$comhzbhdcanbuscar_310UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirBtnCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_310-UiMgr, reason: not valid java name */
    /* synthetic */ void m489lambda$new$3$comhzbhdcanbuscar_310UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirBtnCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_310-UiMgr, reason: not valid java name */
    /* synthetic */ void m490lambda$new$4$comhzbhdcanbuscar_310UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirBtnCommand(airPageUiSet.getRearArea().getLineBtnAction()[3][i]);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x002a  */
    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_310-UiMgr, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    /* synthetic */ void m491lambda$new$5$comhzbhdcanbuscar_310UiMgr(com.hzbhd.canbus.ui_set.SettingPageUiSet r10, android.content.Context r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._310.UiMgr.m491lambda$new$5$comhzbhdcanbuscar_310UiMgr(com.hzbhd.canbus.ui_set.SettingPageUiSet, android.content.Context, int, int, int):void");
    }

    static /* synthetic */ void lambda$new$6(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "hiworld_jeep_123_0x60_data1_65":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                break;
            case "radar_volume":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
                break;
            case "_310_auto_circulation_sensitivity":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) i3});
                break;
        }
    }

    static /* synthetic */ void lambda$new$7(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_165_clear_data")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
        } else if (titleSrn.equals("clear_data")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
        }
    }

    static /* synthetic */ void lambda$new$8(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("update_data")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
        }
    }

    static /* synthetic */ void lambda$new$9(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_18_vehicle_setting_item_1_0":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                break;
            case "_18_vehicle_setting_item_1_1":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                break;
            case "_18_vehicle_setting_item_1_4":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                break;
            case "_18_vehicle_setting_item_2_4":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                break;
            case "_18_vehicle_setting_item_2_5":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                break;
            case "_18_vehicle_setting_item_2_6":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                break;
            case "_18_vehicle_setting_item_2_7":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                break;
            case "_310_speed_auto_lock":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                break;
            case "_310_shift_auto_lock":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                break;
            case "_310_position_p_unlock":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                break;
            case "_186_asl":
                byte[] bArr = new byte[4];
                bArr[0] = 22;
                bArr[1] = -124;
                bArr[2] = 3;
                bArr[3] = (byte) (i3 == 0 ? 1 : 8);
                CanbusMsgSender.sendMsg(bArr);
                break;
            case "light_ctrl_3":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                break;
            case "_283_radar_switch":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i3});
                break;
            case "_16_title_23":
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
                break;
        }
    }

    /* renamed from: com.hzbhd.canbus.car._310.UiMgr$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static /* synthetic */ void lambda$new$10(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (7 - i)});
        } else {
            if (i2 != 2) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
        }
    }

    static /* synthetic */ void lambda$new$11(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 2)});
        } else if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 2)});
        } else {
            if (i2 != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
        }
    }

    /* renamed from: lambda$new$12$com-hzbhd-canbus-car-_310-UiMgr, reason: not valid java name */
    /* synthetic */ void m487lambda$new$12$comhzbhdcanbuscar_310UiMgr(Context context) {
        ((MyPanoramicView) getCusPanoramicView(context)).setmOnBtnClickListener(getOnPanoramicBtnCllick(context));
    }

    static /* synthetic */ void lambda$new$13(PanelKeyPageUiSet panelKeyPageUiSet, int i) {
        String str = (String) ((List) Objects.requireNonNull(panelKeyPageUiSet.getList())).get(i);
        str.hashCode();
        switch (str) {
            case "_309_panel_19":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, 0});
                break;
            case "_309_panel_20":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, 1});
                break;
            case "_310_min_0":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, 2});
                break;
        }
    }

    static /* synthetic */ void lambda$new$14(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, int i) {
        String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
        str.hashCode();
        if (str.equals("power")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, 1});
        }
    }

    private void sendAirBtnCommand(String str) {
        str.hashCode();
        switch (str) {
            case "rear_blow_positive":
                sendAirCommand(43);
                break;
            case "pollrn_removal":
                sendAirCommand(32);
                break;
            case "front_defog":
                sendAirCommand(18);
                break;
            case "clean_air":
                sendAirCommand(27);
                break;
            case "rear_auto":
                sendAirCommand(45);
                break;
            case "rear_defog":
                sendAirCommand(20);
                break;
            case "rear_power":
                sendAirCommand(44);
                break;
            case "blow_positive":
                sendAirCommand(36);
                break;
            case "blow_negative":
                sendAirCommand(37);
                break;
            case "front_window_heat":
                sendAirCommand(26);
                break;
            case "ac":
                sendAirCommand(23);
                break;
            case "auto":
                sendAirCommand(21);
                break;
            case "dual":
                sendAirCommand(16);
                break;
            case "rear":
                sendAirCommand(42);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "swing":
                sendAirCommand(29);
                break;
            case "negative_ion":
                sendAirCommand(28);
                break;
            case "in_out_cycle":
                sendAirCommand(25);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(final int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._310.UiMgr$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
            }
        }, 100L);
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private class OnPanoramicBtnCllick implements MyPanoramicView.OnBtnClickListener {
        private OnPanoramicBtnCllick() {
        }

        @Override // com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
        public void onStartTopClick() {
            Log.i("_310_UiMgr", "onStartTopClick: ");
        }

        @Override // com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
        public void onStartBottomClick() {
            Log.i("_310_UiMgr", "onStartBottomClick: ");
        }

        @Override // com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
        public void onEndTopClick() {
            Log.i("_310_UiMgr", "onEndTopClick: ");
        }

        @Override // com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
        public void onEndBottomClick() {
            Log.i("_310_UiMgr", "onEndBottomClick: ");
        }
    }

    private OnPanoramicBtnCllick getOnPanoramicBtnCllick(Context context) {
        boolean zIsBackCamera = CommUtil.isBackCamera(context);
        boolean zIsPanoramic = CommUtil.isPanoramic(context);
        if (zIsBackCamera) {
            return new OnPanoramicBtnCllick() { // from class: com.hzbhd.canbus.car._310.UiMgr.7
                @Override // com.hzbhd.canbus.car._310.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
                public void onEndBottomClick() {
                    super.onEndBottomClick();
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
                }

                @Override // com.hzbhd.canbus.car._310.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
                public void onStartBottomClick() {
                    super.onStartBottomClick();
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
                }

                @Override // com.hzbhd.canbus.car._310.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
                public void onEndTopClick() {
                    super.onEndTopClick();
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 6});
                }
            };
        }
        if (zIsPanoramic) {
            return new OnPanoramicBtnCllick() { // from class: com.hzbhd.canbus.car._310.UiMgr.8
                @Override // com.hzbhd.canbus.car._310.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
                public void onStartTopClick() {
                    super.onStartTopClick();
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
                }

                @Override // com.hzbhd.canbus.car._310.UiMgr.OnPanoramicBtnCllick, com.hzbhd.canbus.car._310.MyPanoramicView.OnBtnClickListener
                public void onStartBottomClick() {
                    super.onStartBottomClick();
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
                }
            };
        }
        return null;
    }

    private int[] getAirBtnPosition(Context context, String str) {
        int[] iArr = {-1, -1};
        String[][] lineBtnAction = getAirUiSet(context).getFrontArea().getLineBtnAction();
        for (int i = 0; i < lineBtnAction.length; i++) {
            String[] strArr = lineBtnAction[i];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (strArr[i2].equals(str)) {
                    iArr[0] = i;
                    iArr[1] = i2;
                }
            }
        }
        return iArr;
    }

    private void addAirBtn(Context context, int i, int i2, String str) {
        String[][] lineBtnAction = getAirUiSet(context).getFrontArea().getLineBtnAction();
        for (String[] strArr : lineBtnAction) {
            for (String str2 : strArr) {
                if (str2.equals(str)) {
                    return;
                }
            }
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(lineBtnAction[i]));
        arrayList.add(i2, str);
        lineBtnAction[i] = (String[]) arrayList.toArray(new String[0]);
    }
}
