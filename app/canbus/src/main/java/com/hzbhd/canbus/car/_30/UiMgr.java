package com.hzbhd.canbus.car._30;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.text.DecimalFormat;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    static final int AMPLIFIER_BALANCE_MID = 7;
    private static final String TAG = "_1030_UiMgr";
    static final String _30_IS_HYBRID_ELECTRIC_VEHICLE = "_30_is_hybrid_electric_vehicle";
    private MsgMgr mMsgMgr;
    private SparseArray<NewEnergyCommand> mNewEnergyCommandArray;
    private SparseArray<String> mSettingsItemArray;
    private MyPanoramicView myPanoramicView;
    private final byte[] m0x07Command = {22, 7, 0, 0};
    private final byte[] m0x08Command = {22, 8, 0, 0, 0};
    private int meachId = getEachId();
    private int mDifferent = getCurrentCarId();

    /* JADX INFO: Access modifiers changed from: private */
    public static int setIntByteWithBit(int i, int i2, int i3) {
        return i ^ ((i3 ^ ((i >> i2) & 1)) << i2);
    }

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
        if (this.meachId != 14) {
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
        }
    }

    public UiMgr(final Context context) {
        initUi(context);
        initNewEnergyItem();
        getMsgMgr(context).sendCarInfo();
        Log.i(TAG, "UiMgr: mDifferent " + this.mDifferent);
        if (is19Santafe(context)) {
            Log.i(TAG, "UiMgr: VEHICLE_TYPE_2019_IX45");
            ((MyPanoramicView) getCusPanoramicView(context)).showIbRearDown();
        }
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m470lambda$new$0$comhzbhdcanbuscar_30UiMgr(context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                this.f$0.m471lambda$new$1$comhzbhdcanbuscar_30UiMgr(context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m472lambda$new$2$comhzbhdcanbuscar_30UiMgr(context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                this.f$0.m473lambda$new$3$comhzbhdcanbuscar_30UiMgr(i, i2);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                this.f$0.m474lambda$new$4$comhzbhdcanbuscar_30UiMgr(context, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSetTextListener(new OnSettingItemSeekbarSetTextListener() { // from class: com.hzbhd.canbus.car._30.UiMgr.1
            final DecimalFormat df00 = new DecimalFormat("00");

            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener
            public String onSetText(int i, int i2, int i3) {
                String str = (String) UiMgr.this.mSettingsItemArray.get((i << 8) | i2);
                str.hashCode();
                switch (str) {
                    case "_30_new_energy_53_C_10":
                        return i3 < 6 ? "--" : ((i3 + 29) / 2.0f) + "℃";
                    case "_30_set_charge_amount_1":
                    case "_30_set_charge_amount_2":
                        return (i3 * 10) + "%";
                    case "_30_end_time_1":
                    case "_30_end_time_2":
                    case "_30_new_energy_53_4":
                    case "_30_new_energy_53_6":
                    case "_30_start_time_1":
                    case "_30_start_time_2":
                        return String.format("%s:%s", this.df00.format(i3 / 6), this.df00.format((i3 % 6) * 10));
                    case "_30_maximum_speed_limit":
                        if (i3 == 8) {
                            return CommUtil.getStrByResId(context, "car_set_null");
                        }
                        return (i3 * 10) + " KM/H";
                    default:
                        return String.valueOf(i3);
                }
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setShowPanoramic(is19Santafe(context));
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.lambda$new$5(parkPageUiSet, i);
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        if (is15Carnival(context)) {
            airUiSet.getFrontArea().setLineBtnAction(null);
        } else {
            airUiSet.setHaveRearArea(false);
        }
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                this.f$0.m475lambda$new$6$comhzbhdcanbuscar_30UiMgr(context, amplifierPosition, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._30.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                this.f$0.m476lambda$new$7$comhzbhdcanbuscar_30UiMgr(context, amplifierBand, i);
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0032  */
    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_30-UiMgr, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    /* synthetic */ void m470lambda$new$0$comhzbhdcanbuscar_30UiMgr(android.content.Context r22, int r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 1932
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._30.UiMgr.m470lambda$new$0$comhzbhdcanbuscar_30UiMgr(android.content.Context, int, int, int):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_30-UiMgr, reason: not valid java name */
    /* synthetic */ void m471lambda$new$1$comhzbhdcanbuscar_30UiMgr(Context context, int i, int i2, int i3) {
        char c;
        String str = this.mSettingsItemArray.get((i << 8) | i2);
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1944712214:
                if (str.equals("_30_new_energy_tuesday_1")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1944712213:
                if (str.equals("_30_new_energy_tuesday_2")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1944712212:
                if (str.equals("_30_new_energy_tuesday_3")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1838280586:
                if (str.equals("_29_steering_wheel_heater")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1381093690:
                if (str.equals("_29_seat_heat_ventil")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1228494769:
                if (str.equals("_30_back_camera_open_hold")) {
                    c2 = 5;
                    break;
                }
                break;
            case -869063945:
                if (str.equals("_30_new_energy_sunday_1")) {
                    c2 = 6;
                    break;
                }
                break;
            case -869063944:
                if (str.equals("_30_new_energy_sunday_2")) {
                    c2 = 7;
                    break;
                }
                break;
            case -869063943:
                if (str.equals("_30_new_energy_sunday_3")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -770255039:
                if (str.equals("_30_new_energy_thursday_1")) {
                    c = '\t';
                    c2 = c;
                    break;
                }
                break;
            case -770255038:
                if (str.equals("_30_new_energy_thursday_2")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -770255037:
                if (str.equals("_30_new_energy_thursday_3")) {
                    c = 11;
                    c2 = c;
                    break;
                }
                break;
            case -662544296:
                if (str.equals("keep_rear_camera_on")) {
                    c = '\f';
                    c2 = c;
                    break;
                }
                break;
            case -644872082:
                if (str.equals("_29_park_distance_warning")) {
                    c = '\r';
                    c2 = c;
                    break;
                }
                break;
            case -561451322:
                if (str.equals("_30_new_energy_friday_1")) {
                    c2 = 14;
                    break;
                }
                break;
            case -561451321:
                if (str.equals("_30_new_energy_friday_2")) {
                    c = 15;
                    c2 = c;
                    break;
                }
                break;
            case -561451320:
                if (str.equals("_30_new_energy_friday_3")) {
                    c = 16;
                    c2 = c;
                    break;
                }
                break;
            case -150838531:
                if (str.equals("_30_air_internal_circulation")) {
                    c = 17;
                    c2 = c;
                    break;
                }
                break;
            case 52118889:
                if (str.equals("_30_auto_temp_circulation")) {
                    c = 18;
                    c2 = c;
                    break;
                }
                break;
            case 84609376:
                if (str.equals("_30_new_energy_saturday_1")) {
                    c = 19;
                    c2 = c;
                    break;
                }
                break;
            case 84609377:
                if (str.equals("_30_new_energy_saturday_2")) {
                    c = 20;
                    c2 = c;
                    break;
                }
                break;
            case 84609378:
                if (str.equals("_30_new_energy_saturday_3")) {
                    c = 21;
                    c2 = c;
                    break;
                }
                break;
            case 262601715:
                if (str.equals("_30_new_energy_wednesday_1")) {
                    c2 = 22;
                    break;
                }
                break;
            case 262601716:
                if (str.equals("_30_new_energy_wednesday_2")) {
                    c = 23;
                    c2 = c;
                    break;
                }
                break;
            case 262601717:
                if (str.equals("_30_new_energy_wednesday_3")) {
                    c = 24;
                    c2 = c;
                    break;
                }
                break;
            case 528921143:
                if (str.equals("_30_new_energy_monday_1")) {
                    c = 25;
                    c2 = c;
                    break;
                }
                break;
            case 528921144:
                if (str.equals("_30_new_energy_monday_2")) {
                    c = 26;
                    c2 = c;
                    break;
                }
                break;
            case 528921145:
                if (str.equals("_30_new_energy_monday_3")) {
                    c = 27;
                    c2 = c;
                    break;
                }
                break;
            case 572844717:
                if (str.equals("_283_media_titls_2")) {
                    c = 28;
                    c2 = c;
                    break;
                }
                break;
            case 728378148:
                if (str.equals("automatic_defogging")) {
                    c = 29;
                    c2 = c;
                    break;
                }
                break;
            case 731324130:
                if (str.equals("_30_seat_status_change_tip")) {
                    c = 30;
                    c2 = c;
                    break;
                }
                break;
            case 914593453:
                if (str.equals("_213_car_set15_76")) {
                    c = 31;
                    c2 = c;
                    break;
                }
                break;
            case 1085558024:
                if (str.equals("_30_new_energy_53_B_1")) {
                    c = ' ';
                    c2 = c;
                    break;
                }
                break;
            case 1085558025:
                if (str.equals("_30_new_energy_53_B_2")) {
                    c = '!';
                    c2 = c;
                    break;
                }
                break;
            case 1085558026:
                if (str.equals("_30_new_energy_53_B_3")) {
                    c = Typography.quote;
                    c2 = c;
                    break;
                }
                break;
            case 1085558985:
                if (str.equals("_30_new_energy_53_C_1")) {
                    c = '#';
                    c2 = c;
                    break;
                }
                break;
            case 1085558986:
                if (str.equals("_30_new_energy_53_C_2")) {
                    c = Typography.dollar;
                    c2 = c;
                    break;
                }
                break;
            case 1876281142:
                if (str.equals("_29_rear_view_parking_guide_line")) {
                    c = '%';
                    c2 = c;
                    break;
                }
                break;
            case 2022671608:
                if (str.equals("automatic_dehumidification")) {
                    c = Typography.amp;
                    c2 = c;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 3, 1, i3);
                break;
            case 1:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 1, i3);
                break;
            case 2:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 1, i3);
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) (2 - i3)});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) (2 - i3)});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) (2 - i3)});
                break;
            case 6:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 3, 6, i3);
                break;
            case 7:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 6, i3);
                break;
            case '\b':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 6, i3);
                break;
            case '\t':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 3, 3, i3);
                break;
            case '\n':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 3, i3);
                break;
            case 11:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 3, i3);
                break;
            case '\f':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) i3});
                break;
            case '\r':
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, (byte) i3});
                break;
            case 14:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 3, 4, i3);
                break;
            case 15:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 4, i3);
                break;
            case 16:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 4, i3);
                break;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte) (2 - i3)});
                break;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) (2 - i3)});
                break;
            case 19:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 3, 5, i3);
                break;
            case 20:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 5, i3);
                break;
            case 21:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 5, i3);
                break;
            case 22:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 3, 2, i3);
                break;
            case 23:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 2, i3);
                break;
            case 24:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 2, i3);
                break;
            case 25:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 3, 0, i3);
                break;
            case 26:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 0, i3);
                break;
            case 27:
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 0, i3);
                break;
            case 28:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) (2 - i3)});
                break;
            case 29:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 65, (byte) i3});
                break;
            case 30:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) (2 - i3)});
                break;
            case 31:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) (2 - i3)});
                break;
            case ' ':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 2, 7, i3);
                break;
            case '!':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 2, 7, i3);
                break;
            case '\"':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(4).getBytes(context), 1, 1, i3);
                break;
            case '#':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(7).getBytes(context), 1, 7, i3);
                break;
            case '$':
                NewEnergyMethod.sendOneBitChangeCommand(this.mNewEnergyCommandArray.get(7).getBytes(context), 1, 6, i3);
                break;
            case '%':
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte) i3});
                break;
            case '&':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 64, (byte) i3});
                break;
        }
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_30-UiMgr, reason: not valid java name */
    /* synthetic */ void m472lambda$new$2$comhzbhdcanbuscar_30UiMgr(Context context, int i, int i2, int i3) {
        String str = this.mSettingsItemArray.get((i << 8) | i2);
        str.hashCode();
        switch (str) {
            case "_30_new_energy_53_C_10":
                NewEnergyMethod.sendMultiBitChangeCommand(this.mNewEnergyCommandArray.get(7).getBytes(context), 1, 0, 5, i3);
                break;
            case "service_interval_distance":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte) DataHandleUtils.getMsb(i3), (byte) DataHandleUtils.getLsb(i3)});
                break;
            case "_30_set_charge_amount_1":
                CanbusMsgSender.sendMsg(new byte[]{22, -87, 8, (byte) (i3 * 10)});
                break;
            case "_30_set_charge_amount_2":
                CanbusMsgSender.sendMsg(new byte[]{22, -87, 9, (byte) (i3 * 10)});
                break;
            case "_30_end_time_1":
                NewEnergyMethod.sendTimeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 2, i3);
                break;
            case "_30_end_time_2":
                NewEnergyMethod.sendTimeCommand(this.mNewEnergyCommandArray.get(4).getBytes(context), 3, i3);
                break;
            case "brightness":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) (i3 + 1)});
                break;
            case "service_interval_duration":
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 56, (byte) DataHandleUtils.getMsb(i3), (byte) DataHandleUtils.getLsb(i3)});
                break;
            case "_30_new_energy_53_4":
                NewEnergyMethod.sendTimeCommand(this.mNewEnergyCommandArray.get(5).getBytes(context), 1, i3);
                break;
            case "_30_new_energy_53_6":
                NewEnergyMethod.sendTimeCommand(this.mNewEnergyCommandArray.get(6).getBytes(context), 1, i3);
                break;
            case "_30_start_time_1":
                NewEnergyMethod.sendTimeCommand(this.mNewEnergyCommandArray.get(2).getBytes(context), 1, i3);
                break;
            case "_30_start_time_2":
                NewEnergyMethod.sendTimeCommand(this.mNewEnergyCommandArray.get(4).getBytes(context), 2, i3);
                break;
            case "_30_maximum_speed_limit":
                int i4 = i3 * 10;
                if (i3 == 8) {
                    i4 = com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
                }
                NewEnergyMethod.sendByteChangeCommand(this.mNewEnergyCommandArray.get(10).getBytes(context), 3, i4);
                break;
        }
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_30-UiMgr, reason: not valid java name */
    /* synthetic */ void m473lambda$new$3$comhzbhdcanbuscar_30UiMgr(int i, int i2) {
        String str = this.mSettingsItemArray.get((i << 8) | i2);
        str.hashCode();
        if (str.equals("_30_driving_mode_reset")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -102, 11, 1});
        }
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_30-UiMgr, reason: not valid java name */
    /* synthetic */ void m474lambda$new$4$comhzbhdcanbuscar_30UiMgr(Context context, int i, int i2) {
        String str = this.mSettingsItemArray.get((i << 8) | i2);
        str.hashCode();
        switch (str) {
            case "_30_eco_mode":
                getMsgMgr(context).pageSwitch("_30_eco_mode");
                break;
            case "_30_approximate_range_fast_charge":
                getMsgMgr(context).pageSwitch("_30_approximate_range_fast_charge");
                break;
            case "_30_sport_mode":
                getMsgMgr(context).pageSwitch("_30_sport_mode");
                break;
            case "_30_approximate_range_normal":
                getMsgMgr(context).pageSwitch("_30_approximate_range_normal");
                break;
            case "_30_new_energy_repeat_1":
                getMsgMgr(context).pageSwitch("_30_new_energy_53_0", "_30_new_energy_repeat_1");
                break;
            case "_30_new_energy_repeat_2":
                getMsgMgr(context).pageSwitch("_30_new_energy_53_B_1", "_30_new_energy_repeat_2");
                break;
            case "_30_new_energy_repeat_3":
                getMsgMgr(context).pageSwitch("_30_new_energy_53_B_2", "_30_new_energy_repeat_3");
                break;
            case "_30_com_mode":
                getMsgMgr(context).pageSwitch("_30_com_mode");
                break;
        }
    }

    static /* synthetic */ void lambda$new$5(ParkPageUiSet parkPageUiSet, int i) {
        String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_250_swith_3d")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 1});
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.UiMgr$9, reason: invalid class name */
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* renamed from: lambda$new$6$com-hzbhd-canbus-car-_30-UiMgr, reason: not valid java name */
    /* synthetic */ void m475lambda$new$6$comhzbhdcanbuscar_30UiMgr(Context context, AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            GeneralAmplifierData.frontRear = i;
            byte[] bArr = this.m0x07Command;
            bArr[2] = (byte) (i + 7);
            CanbusMsgSender.sendMsg(bArr);
        } else if (i2 == 2) {
            GeneralAmplifierData.leftRight = i;
            byte[] bArr2 = this.m0x07Command;
            bArr2[3] = (byte) (i + 7);
            CanbusMsgSender.sendMsg(bArr2);
        }
        getMsgMgr(context).saveAmplifierData(context);
    }

    /* renamed from: lambda$new$7$com-hzbhd-canbus-car-_30-UiMgr, reason: not valid java name */
    /* synthetic */ void m476lambda$new$7$comhzbhdcanbuscar_30UiMgr(Context context, AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            GeneralAmplifierData.bandBass = i;
            byte[] bArr = this.m0x08Command;
            bArr[2] = (byte) i;
            CanbusMsgSender.sendMsg(bArr);
        } else if (i2 == 2) {
            GeneralAmplifierData.bandMiddle = i;
            byte[] bArr2 = this.m0x08Command;
            bArr2[3] = (byte) i;
            CanbusMsgSender.sendMsg(bArr2);
        } else if (i2 == 3) {
            GeneralAmplifierData.bandTreble = i;
            byte[] bArr3 = this.m0x08Command;
            bArr3[4] = (byte) i;
            CanbusMsgSender.sendMsg(bArr3);
        }
        getMsgMgr(context).saveAmplifierData(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private void initNewEnergyItem() {
        SparseArray<NewEnergyCommand> sparseArray = new SparseArray<>();
        this.mNewEnergyCommandArray = sparseArray;
        int i = 83;
        int i2 = 2;
        int i3 = 3;
        sparseArray.append(2, new NewEnergyCommand(i, i2, i3) { // from class: com.hzbhd.canbus.car._30.UiMgr.2
            @Override // com.hzbhd.canbus.car._30.UiMgr.NewEnergyCommand
            byte[] getBytes(Context context) {
                super.getBytes(context);
                if (this.msgDatas != null) {
                    this.command[3] = (byte) this.msgDatas[3];
                    this.command[4] = (byte) this.msgDatas[4];
                    this.command[5] = (byte) this.msgDatas[5];
                    byte intByteWithBit = this.command[5];
                    for (int i4 = 0; i4 < 6; i4++) {
                        intByteWithBit = (byte) UiMgr.setIntByteWithBit(intByteWithBit, 5 - i4, (this.command[5] >> i4) & 1);
                    }
                    this.command[5] = intByteWithBit;
                }
                return this.command;
            }
        });
        int i4 = 1;
        this.mNewEnergyCommandArray.append(3, new NewEnergyCommand(i, i3, i4) { // from class: com.hzbhd.canbus.car._30.UiMgr.3
            @Override // com.hzbhd.canbus.car._30.UiMgr.NewEnergyCommand
            byte[] getBytes(Context context) {
                super.getBytes(context);
                if (this.msgDatas != null) {
                    this.command[3] = (byte) (this.msgDatas[12] & 15);
                }
                return this.command;
            }
        });
        this.mNewEnergyCommandArray.append(4, new NewEnergyCommand(i, 4, i3) { // from class: com.hzbhd.canbus.car._30.UiMgr.4
            @Override // com.hzbhd.canbus.car._30.UiMgr.NewEnergyCommand
            byte[] getBytes(Context context) {
                super.getBytes(context);
                if (this.msgDatas != null) {
                    this.command[3] = (byte) ((this.msgDatas[12] >> 4) & 3);
                    this.command[4] = (byte) this.msgDatas[10];
                    this.command[5] = (byte) this.msgDatas[11];
                }
                return this.command;
            }
        });
        this.mNewEnergyCommandArray.append(5, new NewEnergyCommand(i, 5, i2) { // from class: com.hzbhd.canbus.car._30.UiMgr.5
            @Override // com.hzbhd.canbus.car._30.UiMgr.NewEnergyCommand
            byte[] getBytes(Context context) {
                super.getBytes(context);
                if (this.msgDatas != null) {
                    this.command[3] = (byte) this.msgDatas[6];
                    this.command[4] = (byte) this.msgDatas[7];
                }
                return this.command;
            }
        });
        this.mNewEnergyCommandArray.append(6, new NewEnergyCommand(i, 6, i2) { // from class: com.hzbhd.canbus.car._30.UiMgr.6
            @Override // com.hzbhd.canbus.car._30.UiMgr.NewEnergyCommand
            byte[] getBytes(Context context) {
                super.getBytes(context);
                if (this.msgDatas != null) {
                    this.command[3] = (byte) this.msgDatas[8];
                    this.command[4] = (byte) this.msgDatas[9];
                }
                return this.command;
            }
        });
        this.mNewEnergyCommandArray.append(7, new NewEnergyCommand(i, 7, i4) { // from class: com.hzbhd.canbus.car._30.UiMgr.7
            @Override // com.hzbhd.canbus.car._30.UiMgr.NewEnergyCommand
            byte[] getBytes(Context context) {
                super.getBytes(context);
                if (this.msgDatas != null) {
                    this.command[3] = (byte) ((this.msgDatas[12] & 192) | (this.msgDatas[13] & 31));
                }
                return this.command;
            }
        });
        this.mNewEnergyCommandArray.append(10, new NewEnergyCommand(87, 10, i3) { // from class: com.hzbhd.canbus.car._30.UiMgr.8
            @Override // com.hzbhd.canbus.car._30.UiMgr.NewEnergyCommand
            byte[] getBytes(Context context) {
                super.getBytes(context);
                if (this.msgDatas != null) {
                    this.command[3] = (byte) UiMgr.setIntByteWithBit(this.command[3], 6, (this.msgDatas[7] >> 5) & 1);
                    this.command[3] = (byte) UiMgr.setIntByteWithBit(this.command[3], 4, (this.msgDatas[7] >> 1) & 1);
                    this.command[3] = (byte) UiMgr.setIntByteWithBit(this.command[3], 2, (this.msgDatas[7] >> 3) & 1);
                    this.command[4] = (byte) DataHandleUtils.setIntFromByteWithBit(this.command[4], DataHandleUtils.getIntFromByteWithBit(this.msgDatas[6], 6, 2), 4, 2);
                    this.command[4] = (byte) DataHandleUtils.setIntFromByteWithBit(this.command[4], DataHandleUtils.getIntFromByteWithBit(this.msgDatas[6], 2, 2), 2, 2);
                    this.command[4] = (byte) DataHandleUtils.setIntFromByteWithBit(this.command[4], DataHandleUtils.getIntFromByteWithBit(this.msgDatas[6], 4, 2), 0, 2);
                    this.command[5] = (byte) this.msgDatas[5];
                }
                return this.command;
            }
        });
    }

    private class NewEnergyCommand {
        byte[] command;
        private final int msgDataType;
        int[] msgDatas;

        public NewEnergyCommand(int i, int i2, int i3) {
            this.msgDataType = i;
            byte[] bArr = new byte[i3 + 3];
            this.command = bArr;
            bArr[0] = 22;
            bArr[1] = -87;
            bArr[2] = (byte) i2;
        }

        byte[] getBytes(Context context) {
            this.msgDatas = UiMgr.this.getMsgMgr(context).getMsgDatas(this.msgDataType);
            return null;
        }
    }

    private static class NewEnergyMethod {
        private NewEnergyMethod() {
        }

        public static void sendTimeCommand(byte[] bArr, int i, int i2) {
            int i3 = i2 / 6;
            int i4 = i2 % 6;
            Log.i(UiMgr.TAG, "getTimeFromProgress: hour[" + i3 + "]  minute[" + i4 + "]");
            bArr[i + 2] = (byte) (((i4 & 7) << 5) | (i3 & 31));
            CanbusMsgSender.sendMsg(bArr);
        }

        public static void sendOneBitChangeCommand(byte[] bArr, int i, int i2, int i3) {
            int i4 = i + 2;
            bArr[i4] = (byte) UiMgr.setIntByteWithBit(bArr[i4], i2, i3);
            CanbusMsgSender.sendMsg(bArr);
        }

        public static void sendMultiBitChangeCommand(byte[] bArr, int i, int i2, int i3, int i4) {
            int i5 = i + 2;
            bArr[i5] = (byte) DataHandleUtils.setIntFromByteWithBit(bArr[i5], i4, i2, i3);
            CanbusMsgSender.sendMsg(bArr);
        }

        public static void sendByteChangeCommand(byte[] bArr, int i, int i2) {
            bArr[i + 2] = (byte) i2;
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    void setSettingsItemArray(SparseArray<String> sparseArray) {
        this.mSettingsItemArray = sparseArray;
    }

    private boolean isNewEnergyVehicle(Context context) {
        return is18SonataHybrid(context) || is19K3NewEnergy(context) || is19K5NewEnergy(context) || is20EncinoElectrical(context);
    }

    private boolean isSupport0x830x02(Context context) {
        return is19Lafesta(context) || is19Santafe(context) || is1920Tucson(context) || isKx5Top(context) || is19KiaK3(context) || is19K3NewEnergy(context) || is20EncinoElectrical(context);
    }

    private void initUi(Context context) {
        if (!getMsgMgr(context).isK4() && getEachId() != 18) {
            removeSettingLeftItemByNameList(context, new String[]{"drive_assist"});
        }
        if (getEachId() != 18) {
            removeSettingLeftItemByNameList(context, new String[]{"instrument_settings"});
            removeSettingLeftItemByNameList(context, new String[]{"door"});
            removeSettingLeftItemByNameList(context, new String[]{"convenient_services"});
            removeSettingLeftItemByNameList(context, new String[]{"camera_panoramic_view"});
            removeSettingLeftItemByNameList(context, new String[]{"unit_settings"});
            removeSettingRightItemByNameList(context, new String[]{"warning_volume"});
            removeSettingRightItemByNameList(context, new String[]{"parking_safety"});
            removeSettingRightItemByNameList(context, new String[]{"surround_view"});
            removeSettingRightItemByNameList(context, new String[]{"pdw_auto_activation"});
            removeSettingRightItemByNameList(context, new String[]{"easy_seat_access"});
            removeSettingRightItemByNameList(context, new String[]{"dimmed_while_driving"});
            removeSettingRightItemByNameList(context, new String[]{"one_touch_turn_indicator"});
            removeSettingRightItemByNameList(context, new String[]{"headlight_time_out"});
            removeSettingRightItemByNameList(context, new String[]{"headlight_time_out"});
        }
        if (getEachId() == 18) {
            removeSettingRightItemByNameList(context, new String[]{"_283_media_titls_2"});
        }
        if (getEachId() != 8 && getEachId() != 18 && getEachId() != 21 && getEachId() != 24) {
            removeSettingRightItemByNameList(context, new String[]{"_30_air_internal_circulation"});
            removeSettingRightItemByNameList(context, new String[]{"automatic_dehumidification"});
            removeSettingRightItemByNameList(context, new String[]{"automatic_defogging"});
        }
        if (!isSupport0x830x02(context)) {
            removeSettingRightItemByNameList(context, new String[]{"_30_auto_temp_circulation"});
        }
        if (!is19Santafe(context) && getEachId() != 18) {
            removeSettingLeftItemByNameList(context, new String[]{"seat_set", "_29_heat_cold"});
            removeSettingRightItemByNameList(context, new String[]{"_30_back_camera_open_hold"});
        }
        if (!is20K3(context) && getEachId() != 18) {
            removeSettingLeftItemByNameList(context, new String[]{"_304_atoms_lamp_setup"});
            removeSettingRightItemByNameList(context, new String[]{"_30_air_internal_circulation"});
        }
        if (!isNewEnergyVehicle(context)) {
            removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
            removeMainPrjBtnByName(context, MainAction.HYBIRD);
            removeSettingLeftItemByNameList(context, new String[]{"_30_new_energy_53_A"});
            removeSettingLeftItemByNameList(context, new String[]{"_30_new_energy_53_B"});
            removeSettingLeftItemByNameList(context, new String[]{"_30_new_energy_53_C"});
            removeSettingLeftItemByNameList(context, new String[]{"_143_setting_11"});
            removeSettingLeftItemByNameList(context, new String[]{"_61_vehicle_type_setup"});
            return;
        }
        if (!is18SonataHybrid(context) && !is19K5NewEnergy(context)) {
            removeSettingLeftItemByNameList(context, new String[]{"_30_new_energy_53_A"});
        }
        if (!is19K3NewEnergy(context) && !is20EncinoElectrical(context)) {
            removeSettingLeftItemByNameList(context, new String[]{"_30_new_energy_53_B"});
        }
        if (!is20EncinoElectrical(context)) {
            removeSettingLeftItemByNameList(context, new String[]{"_30_new_energy_53_C"});
        }
        if (is20EncinoElectrical(context)) {
            removeDriveDateItemForTitles(context, new String[]{"_30_eco_level", "vm_golf7_vehicle_setup_mfd_avg_consumption", "_30_hybrid_fuel_consumption", "_30_motor_energy_consumption"});
        }
        if (is20EncinoElectrical(context)) {
            removeMainPrjBtnByName(context, MainAction.HYBIRD);
        }
        if (!is20EncinoElectrical(context)) {
            removeSettingRightItemByNameList(context, new String[]{"_30_approximate_range_fast_charge", "_30_set_charge_amount_1", "_30_approximate_range_normal", "_30_set_charge_amount_2"});
            removeDriveDateItemForTitles(context, new String[]{"_30_estimated_charging_time_fast_charging", "_30_traditional_system", "_30_air_conditioning_system", "_30_electronic_equipment", "_30_battery_maintenance"});
        }
        if (is20EncinoElectrical(context)) {
            return;
        }
        removeSettingLeftItemByNameList(context, new String[]{"_143_setting_11"});
        removeDriveDateItemForTitles(context, new String[]{"_30_estimated_charging_time_fast_charging", "_30_traditional_system", "_30_air_conditioning_system", "_30_electronic_equipment", "_30_battery_maintenance"});
        removeDriveDataPagesByHeadTitles(context, "_143_setting_11");
    }

    private boolean isK4(Context context) {
        return getMsgMgr(context).isK4();
    }

    private boolean is15Carnival(Context context) {
        return getMsgMgr(context).is15Carnival();
    }

    private boolean is1819KiaSportage(Context context) {
        return getMsgMgr(context).is1819KiaSportage();
    }

    private boolean is19Lafesta(Context context) {
        return getMsgMgr(context).is19Lafesta();
    }

    private boolean is19Santafe(Context context) {
        return getMsgMgr(context).is19Santafe();
    }

    private boolean is1920Tucson(Context context) {
        return getMsgMgr(context).is1920Tucson();
    }

    private boolean isKx5Top(Context context) {
        return getMsgMgr(context).isKx5Top();
    }

    private boolean is19KiaK3(Context context) {
        return getMsgMgr(context).is19KiaK3();
    }

    private boolean is18SonataHybrid(Context context) {
        return getMsgMgr(context).is18SonataHybrid();
    }

    private boolean is20K3(Context context) {
        return getMsgMgr(context).is20K3();
    }

    private boolean is19K3NewEnergy(Context context) {
        return getMsgMgr(context).is19K3NewEnergy();
    }

    private boolean is19K5NewEnergy(Context context) {
        return getMsgMgr(context).is19K5NewEnergy();
    }

    private boolean is20EncinoElectrical(Context context) {
        return getMsgMgr(context).is20EncinoElectrical();
    }
}
