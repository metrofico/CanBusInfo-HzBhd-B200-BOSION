package com.hzbhd.canbus.car._85;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._85.MsgMgr;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private int[] mAirAutoBtnPos;
    private int[] mAirDualBtnPos;
    private String[][] mDiagitalKeyboardActions;
    private String[][] mFeaturesKeyboardActions;
    private MsgMgr mMsgMgr;
    private OnAirAutoManualChangeListener mOnAirAutoManualChangeListener;
    private MyPanoramicView mPanoramicView;
    private final String TAG = "_85_UiMgr";
    private final int VEHICLE_TYPE_RANGER_F150 = 16;
    private final int INVAIL_VALUE = -1;
    private boolean mIsFeaturesKeyboard = true;
    private int mDifferent = getCurrentCarId();

    interface OnAirAutoManualChangeListener {
        void onChange(boolean z);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = new MyPanoramicView(context);
        }
        return this.mPanoramicView;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.lambda$new$0(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m934lambda$new$1$comhzbhdcanbuscar_85UiMgr(settingUiSet, context, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.lambda$new$2(settingUiSet, i, i2);
            }
        });
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m935lambda$new$3$comhzbhdcanbuscar_85UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m936lambda$new$4$comhzbhdcanbuscar_85UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m937lambda$new$5$comhzbhdcanbuscar_85UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m938lambda$new$6$comhzbhdcanbuscar_85UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(36);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(35);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                if (GeneralAirData.auto_manual) {
                    UiMgr.this.sendAirCommand(38);
                } else {
                    UiMgr.this.sendAirCommand(36);
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                if (GeneralAirData.auto_manual) {
                    UiMgr.this.sendAirCommand(37);
                } else {
                    UiMgr.this.sendAirCommand(35);
                }
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(39);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(40);
            }
        });
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirCommand(43);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirCommand(45);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirCommand(44);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirCommand(46);
            }
        }});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m939lambda$new$7$comhzbhdcanbuscar_85UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(20);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(19);
            }
        }, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(22);
            }
        });
        this.mAirAutoBtnPos = getAirBtnPosition(context, "auto");
        this.mAirDualBtnPos = getAirBtnPosition(context, "dual");
        Log.i("_85_UiMgr", "UiMgr: mAirAutoBtnPos:[" + this.mAirAutoBtnPos[0] + "," + this.mAirAutoBtnPos[1] + "], mAirDualBtnPos:[" + this.mAirDualBtnPos[0] + "," + this.mAirDualBtnPos[1] + "]");
        this.mOnAirAutoManualChangeListener = new OnAirAutoManualChangeListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car._85.UiMgr.OnAirAutoManualChangeListener
            public final void onChange(boolean z) {
                this.f$0.m940lambda$new$8$comhzbhdcanbuscar_85UiMgr(airUiSet, context, z);
            }
        };
        final SyncPageUiSet syncPageUiSet = getSyncPageUiSet(context);
        syncPageUiSet.setOnSyncStateChangeListener(new OnSyncStateChangeListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onResume() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 14, 0});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onStop() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
            }
        });
        syncPageUiSet.setOnSyncItemClickListener(new OnSyncItemClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr.11
            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onLeftIconClick(String str) {
                str.hashCode();
                switch (str) {
                    case "media":
                        if (!MsgMgr.SYNC_VERSION.VERSION_V1.equals(MsgMgr.mSyncVersion)) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -127});
                            break;
                        }
                    case "phone":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 3});
                        break;
                    case "voice":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 1});
                        break;
                    case "keyboard":
                        if (UiMgr.this.mIsFeaturesKeyboard) {
                            UiMgr.this.mIsFeaturesKeyboard = false;
                            syncPageUiSet.setKeyboardActions(UiMgr.this.getDiagitalKeyboardActions());
                        } else {
                            UiMgr.this.mIsFeaturesKeyboard = true;
                            syncPageUiSet.setKeyboardActions(UiMgr.this.getFeaturesKeyboardActions());
                        }
                        UiMgr.this.getMsgMgr(context).updateSync(null);
                        break;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onListItemClick(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) (i + 145)});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onSoftKeyClick(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) (i + 28)});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnClick(String str) {
                str.hashCode();
                switch (str) {
                    case "number_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                        break;
                    case "number_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 14});
                        break;
                    case "number_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 15});
                        break;
                    case "number_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 16});
                        break;
                    case "number_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 17});
                        break;
                    case "number_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 18});
                        break;
                    case "number_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 19});
                        break;
                    case "number_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 20});
                        break;
                    case "number_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 21});
                        break;
                    case "number_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 22});
                        break;
                    case "device":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                        break;
                    case "hangup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 4});
                        break;
                    case "pickup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 5});
                        break;
                    case "ok":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                        break;
                    case "up":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 10});
                        break;
                    case "aux":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                        break;
                    case "down":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 11});
                        break;
                    case "info":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 6});
                        break;
                    case "left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 25});
                        break;
                    case "menu":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                        break;
                    case "next":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 9});
                        break;
                    case "prev":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 8});
                        break;
                    case "star":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 23});
                        break;
                    case "well":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 24});
                        break;
                    case "right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 26});
                        break;
                    case "shuff":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 7});
                        break;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnLongClick(String str) {
                str.hashCode();
                switch (str) {
                    case "number_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 48});
                        break;
                    case "number_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 49});
                        break;
                    case "number_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 50});
                        break;
                    case "number_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 51});
                        break;
                    case "number_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 52});
                        break;
                    case "number_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 53});
                        break;
                    case "number_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 54});
                        break;
                    case "number_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 55});
                        break;
                    case "number_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 56});
                        break;
                    case "number_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 57});
                        break;
                }
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i + 18)});
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public final void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                UiMgr.lambda$new$10(amplifierBand, i);
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._85.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public final void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                UiMgr.lambda$new$11(amplifierPosition, i);
            }
        });
    }

    static /* synthetic */ void lambda$new$0(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_81_tyre_monitor")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, 1});
        }
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_85-UiMgr, reason: not valid java name */
    /* synthetic */ void m934lambda$new$1$comhzbhdcanbuscar_85UiMgr(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_85_passenger_seat_massage_backrest":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte) i3});
                break;
            case "_85_driver_seat_massage_backrest":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, (byte) i3});
                break;
            case "_85_driver_seat_massage_cushion":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, (byte) i3});
                break;
            case "_85_sound_mode":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -58, (byte) (i3 + 1)});
                break;
            case "ford_range_unit":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 14)});
                break;
            case "speed_linkage_volume_adjustment":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -59, (byte) (i3 + 1)});
                break;
            case "_279_temperature_unit":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte) i3});
                getMsgMgr(context).updateSettings(i, i2, i3);
                break;
            case "language_setup":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte) MsgMgr.mLanguageMapArray[i3]});
                break;
            case "_85_passenger_seat_massage_cushion":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, (byte) i3});
                break;
            case "_176_car_setting_title_21":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte) i3});
                break;
        }
    }

    static /* synthetic */ void lambda$new$2(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_85_driver_seat_adjust_middle_plus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, 2});
                break;
            case "_85_passenger_seat_adjust_upper_plus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 2});
                break;
            case "_85_driver_seat_adjust_middle_minus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, 1});
                break;
            case "_85_passenger_seat_adjust_middle_minus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 1});
                break;
            case "_85_driver_seat_adjust_upper_plus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, 2});
                break;
            case "_85_passenger_seat_adjust_lower_minus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 1});
                break;
            case "_85_passenger_seat_adjust_lower_plus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 2});
                break;
            case "_85_driver_seat_adjust_lower_minus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, 1});
                break;
            case "_85_passenger_seat_adjust_upper_minus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 1});
                break;
            case "_85_driver_seat_adjust_lower_plus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, 2});
                break;
            case "_85_passenger_seat_adjust_middle_plus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 2});
                break;
            case "_85_driver_seat_adjust_upper_minus":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, 1});
                break;
        }
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_85-UiMgr, reason: not valid java name */
    /* synthetic */ void m935lambda$new$3$comhzbhdcanbuscar_85UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_85-UiMgr, reason: not valid java name */
    /* synthetic */ void m936lambda$new$4$comhzbhdcanbuscar_85UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_85-UiMgr, reason: not valid java name */
    /* synthetic */ void m937lambda$new$5$comhzbhdcanbuscar_85UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$6$com-hzbhd-canbus-car-_85-UiMgr, reason: not valid java name */
    /* synthetic */ void m938lambda$new$6$comhzbhdcanbuscar_85UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$7$com-hzbhd-canbus-car-_85-UiMgr, reason: not valid java name */
    /* synthetic */ void m939lambda$new$7$comhzbhdcanbuscar_85UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getRearArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$8$com-hzbhd-canbus-car-_85-UiMgr, reason: not valid java name */
    /* synthetic */ void m940lambda$new$8$comhzbhdcanbuscar_85UiMgr(AirPageUiSet airPageUiSet, Context context, boolean z) {
        Log.i("_85_UiMgr", "UiMgr: isAutoAir:" + z);
        airPageUiSet.getFrontArea().setShowSeatHeat(z);
        airPageUiSet.getFrontArea().setShowSeatCold(z);
        if (z) {
            int[] iArr = this.mAirAutoBtnPos;
            addAirBtn(context, iArr[0], iArr[1], "auto");
            int[] iArr2 = this.mAirDualBtnPos;
            addAirBtn(context, iArr2[0], iArr2[1], "dual");
            return;
        }
        removeFrontAirButtonByName(context, "auto");
        removeFrontAirButtonByName(context, "dual");
    }

    /* renamed from: com.hzbhd.canbus.car._85.UiMgr$12, reason: invalid class name */
    static /* synthetic */ class AnonymousClass12 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static /* synthetic */ void lambda$new$10(AmplifierActivity.AmplifierBand amplifierBand, int i) {
        int i2 = AnonymousClass12.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -64, (byte) i});
        } else if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, (byte) i});
        } else {
            if (i2 != 3) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -62, (byte) i});
        }
    }

    static /* synthetic */ void lambda$new$11(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
        int i2 = AnonymousClass12.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -61, (byte) (i + 7)});
        } else {
            if (i2 != 2) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -60, (byte) (i + 7)});
        }
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "steering_wheel_heating":
                sendAirCommand(42);
                break;
            case "front_defog":
                sendAirCommand(41);
                break;
            case "ac_max":
                sendAirCommand(4);
                break;
            case "rear_lock":
                sendAirCommand(18);
                break;
            case "rear_defog":
                sendAirCommand(6);
                break;
            case "rear_power":
                sendAirCommand(17);
                break;
            case "blow_positive":
                sendAirCommand(7);
                break;
            case "max_front":
                sendAirCommand(5);
                break;
            case "ac":
                sendAirCommand(2);
                break;
            case "auto":
                sendAirCommand(23);
                break;
            case "dual":
                sendAirCommand(24);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "blow_window":
                sendAirCommand(32);
                break;
            case "in_out_cycle":
                sendAirCommand(3);
                break;
            case "blow_foot":
                sendAirCommand(34);
                break;
            case "blow_head":
                sendAirCommand(33);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    public String[][] getDiagitalKeyboardActions() {
        if (this.mDiagitalKeyboardActions == null) {
            this.mDiagitalKeyboardActions = new String[][]{new String[]{SyncAction.KEYBOARD_NUMBER_1, SyncAction.KEYBOARD_NUMBER_2, SyncAction.KEYBOARD_NUMBER_3}, new String[]{SyncAction.KEYBOARD_NUMBER_4, SyncAction.KEYBOARD_NUMBER_5, SyncAction.KEYBOARD_NUMBER_6}, new String[]{SyncAction.KEYBOARD_NUMBER_7, SyncAction.KEYBOARD_NUMBER_8, SyncAction.KEYBOARD_NUMBER_9}, new String[]{SyncAction.KEYBOARD_STAR, SyncAction.KEYBOARD_NUMBER_0, SyncAction.KEYBOARD_WELL}, new String[]{SyncAction.KEYBOARD_PICKUP, SyncAction.KEYBOARD_HANGUP, "null"}};
        }
        return this.mDiagitalKeyboardActions;
    }

    public String[][] getFeaturesKeyboardActions() {
        if (this.mFeaturesKeyboardActions == null) {
            this.mFeaturesKeyboardActions = new String[][]{new String[]{"null", "up", "null"}, new String[]{"left", SyncAction.KEYBOARD_OK, "right"}, new String[]{"null", "down", "null"}, new String[]{SyncAction.KEYBOARD_INFO, SyncAction.KEYBOARD_MENU, SyncAction.KEYBOARD_DEVICE}, new String[]{SyncAction.KEYBOARD_PREV, SyncAction.KEYBOARD_SHUFF, SyncAction.KEYBOARD_NEXT}};
        }
        return this.mFeaturesKeyboardActions;
    }

    OnAirAutoManualChangeListener getOnAirAutoManualChangeListener() {
        return this.mOnAirAutoManualChangeListener;
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
        if (i == -1 || i2 == -1) {
            return;
        }
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
