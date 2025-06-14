package com.hzbhd.canbus.car._349;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._349.MsgMgr;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    int i;
    Context mContext;
    private String[][] mDiagitalKeyboardActions;
    private String[][] mFeaturesKeyboardActions;
    private MyPanoramicView mPanoramicView;
    private MsgMgr msgMgr;
    private UiMgr uiMgr;
    private boolean mIsFeaturesKeyboard = true;
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 17, 1});
            } else {
                if (i != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 18, 1});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 1, 1});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 2, 1});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 3, 1});
            } else if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 4, 1});
            } else {
                if (i != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 23, 1});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 62, 1});
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 24, 1});
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 56, 1});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 55, 1});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 57, 1});
            } else if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 5, 1});
            } else {
                if (i != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 63, 1});
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 49, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 50, 1});
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 51, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 52, 1});
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 58, 1});
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 59, 1});
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 60, 1});
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 61, 1});
        }
    };
    int eachId = getEachId();

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
        getMsgMgr(this.mContext).updateSettings(2, 0, SharePreUtil.getIntValue(this.mContext, "RotatingSpeed", 0));
        getMsgMgr(this.mContext).updateSettings(2, 1, SharePreUtil.getIntValue(this.mContext, "CarSpeed", 0));
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(108);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "ford_alert_tone":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 7)});
                        break;
                    case "_81_current_voice_mode":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 9)});
                        break;
                    case "_81_turn_signals_setup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 3)});
                        break;
                    case "ford_range_unit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 14)});
                        break;
                    case "_279_temperature_unit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte) i3});
                        break;
                    case "language_setup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte) MsgMgr.mLanguageMapArray[i3]});
                        break;
                    case "brightness":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 16)});
                        break;
                    case "_81_traction_control_system":
                        if (i3 == 0) {
                            i3 = 2;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) i3});
                        break;
                    case "ford_message_tone":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 5)});
                        break;
                    case "_349_setting_2_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -78, 0});
                        break;
                    case "_349_setting_2_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -77, 0});
                        break;
                    case "_349_setting_2_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -76, 0});
                        break;
                    case "_349_setting_2_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -75, 0});
                        break;
                    case "_349_setting_2_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -74, 0});
                        break;
                    case "_349_setting_3_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -73, 0});
                        break;
                    case "_349_setting_3_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -72, 0});
                        break;
                    case "_349_setting_3_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -71, 0});
                        break;
                    case "_349_setting_3_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -70, 0});
                        break;
                    case "_349_setting_3_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, (byte) (i3 + 1)});
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -69, 0});
                        break;
                    case "_349_setting_4_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -68, (byte) i3});
                        break;
                    case "_349_setting_4_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -67, (byte) i3});
                        break;
                    case "_349_setting_4_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -66, (byte) i3});
                        break;
                    case "_349_setting_4_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -65, (byte) i3});
                        break;
                    case "_176_car_setting_title_21":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte) (((byte) i3) + 1)});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_330_Rotating_Speed_report":
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) i3});
                        SharePreUtil.setIntValue(UiMgr.this.mContext, "RotatingSpeed", i3);
                        break;
                    case "_349_setting_5_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte) i3});
                        break;
                    case "_330_car_Speed_report":
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i3});
                        SharePreUtil.setIntValue(UiMgr.this.mContext, "CarSpeed", i3);
                        break;
                    case "_176_car_setting_title_21":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, (byte) i3});
                        break;
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(33);
                UiMgr.this.activeRequest(107);
            }
        });
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 21, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 22, 1});
            }
        });
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 54, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, 53, 1});
            }
        });
        final SyncPageUiSet syncPageUiSet = getSyncPageUiSet(this.mContext);
        syncPageUiSet.setOnSyncStateChangeListener(new OnSyncStateChangeListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onResume() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onStop() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
            }
        });
        syncPageUiSet.setOnSyncItemClickListener(new OnSyncItemClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.8
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
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._349.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte) (i + 18)});
            }
        });
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._349.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(20);
                UiMgr.this.activeRequest(34);
                UiMgr.this.activeRequest(35);
                UiMgr.this.activeRequest(36);
                UiMgr.this.activeRequest(37);
                UiMgr.this.activeRequest(103);
                UiMgr.this.activeRequest(104);
                UiMgr.this.activeRequest(106);
            }
        });
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
    }

    protected int getSettingRightIndex(Context context, String str, String str2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (str.equals(next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (str2.equals(it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    private void activeRequestData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
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

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 81, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
