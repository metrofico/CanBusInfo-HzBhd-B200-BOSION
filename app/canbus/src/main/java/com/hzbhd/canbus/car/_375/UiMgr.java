package com.hzbhd.canbus.car._375;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    public String KEY_AUTO_PARKING_VIEW = "KEY_AUTO_PARKING_VIEW";
    public String KEY_CAR_SELECT = "KEY_CAR_SELECT";
    public String KEY_0x85_DATA0 = "KEY_0x85_DATA0";
    public String KEY_0x85_DATA1BIT7 = "KEY_0x85_DATA1BIT7";
    DecimalFormat towDecimal = new DecimalFormat("###0.00");
    DecimalFormat oneDecimal = new DecimalFormat("###0.0");
    DecimalFormat timeFormat = new DecimalFormat("00");
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.d0b1 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d0b1 = 0;
                UiMgr.this.send0x86AirInfo();
                return;
            }
            if (i != 1) {
                return;
            }
            UiMgr.this.d0b0 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d0b0 = 0;
            UiMgr.this.send0x86AirInfo();
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.d0b5 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d0b5 = 0;
            UiMgr.this.send0x86AirInfo();
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            if (!GeneralAirData.in_out_cycle) {
                UiMgr.this.d0b3 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d0b3 = 0;
                UiMgr.this.send0x86AirInfo();
                return;
            }
            UiMgr.this.d0b2 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d0b2 = 0;
            UiMgr.this.send0x86AirInfo();
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.d1b2 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d1b2 = 0;
                UiMgr.this.send0x86AirInfo();
                return;
            }
            if (i == 1) {
                UiMgr.this.d0b7 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d0b7 = 0;
                UiMgr.this.send0x86AirInfo();
                return;
            }
            if (i != 2) {
                return;
            }
            UiMgr.this.d5b2 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d5b2 = 0;
            UiMgr.this.send0x86AirInfo();
        }
    };
    int seatState = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.seatState == 0) {
                UiMgr.this.d2b4 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b4 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 1;
                return;
            }
            if (UiMgr.this.seatState == 1) {
                UiMgr.this.d2b3 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b3 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 2;
                return;
            }
            if (UiMgr.this.seatState == 2) {
                UiMgr.this.d2b2 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b2 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 3;
                return;
            }
            if (UiMgr.this.seatState == 3) {
                UiMgr.this.d2b1 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b1 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 0;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.seatState == 0) {
                UiMgr.this.d2b4 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b4 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 1;
                return;
            }
            if (UiMgr.this.seatState == 1) {
                UiMgr.this.d2b3 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b3 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 2;
                return;
            }
            if (UiMgr.this.seatState == 2) {
                UiMgr.this.d2b2 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b2 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 3;
                return;
            }
            if (UiMgr.this.seatState == 3) {
                UiMgr.this.d2b1 = 1;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.d2b1 = 0;
                UiMgr.this.send0x86AirInfo();
                UiMgr.this.seatState = 0;
            }
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (GeneralAirData.front_wind_level == 0) {
                return;
            }
            UiMgr.this.d1b7 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 3, 1);
            UiMgr.this.d1b6 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 2, 1);
            UiMgr.this.d1b5 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 1, 1);
            UiMgr.this.d1b4 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level - 1, 0, 1);
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d1b7 = 0;
            UiMgr.this.d1b6 = 0;
            UiMgr.this.d1b5 = 0;
            UiMgr.this.d1b4 = 0;
            UiMgr.this.send0x86AirInfo();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (GeneralAirData.front_wind_level == 8) {
                return;
            }
            UiMgr.this.d1b7 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 3, 1);
            UiMgr.this.d1b6 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 2, 1);
            UiMgr.this.d1b5 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 1, 1);
            UiMgr.this.d1b4 = DataHandleUtils.getIntFromByteWithBit(GeneralAirData.front_wind_level + 1, 0, 1);
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d1b7 = 0;
            UiMgr.this.d1b6 = 0;
            UiMgr.this.d1b5 = 0;
            UiMgr.this.d1b4 = 0;
            UiMgr.this.send0x86AirInfo();
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.d3b1 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d3b1 = 0;
            UiMgr.this.send0x86AirInfo();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.d3b0 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d3b0 = 0;
            UiMgr.this.send0x86AirInfo();
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.d2b4 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d2b4 = 0;
            UiMgr.this.send0x86AirInfo();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.d2b4 = 1;
            UiMgr.this.send0x86AirInfo();
            UiMgr.this.d2b4 = 0;
            UiMgr.this.send0x86AirInfo();
        }
    };
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestInfo(33, 0);
        }
    };
    OnPanoramicItemClickListener onPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            switch (i) {
                case 0:
                    UiMgr.this.send0xA7PanoramicInfo(1);
                    break;
                case 1:
                    UiMgr.this.send0xA7PanoramicInfo(3);
                    break;
                case 2:
                    UiMgr.this.send0xA7PanoramicInfo(4);
                    break;
                case 3:
                    UiMgr.this.send0xA7PanoramicInfo(2);
                    break;
                case 4:
                    UiMgr.this.send0xA7PanoramicInfo(16);
                    break;
                case 5:
                    UiMgr.this.send0xA7PanoramicInfo(17);
                    break;
                case 6:
                    UiMgr.this.send0xA7PanoramicInfo(5);
                    break;
                case 7:
                    UiMgr.this.send0xA7PanoramicInfo(16);
                    break;
                case 8:
                    UiMgr.this.send0xA7PanoramicInfo(18);
                    break;
                case 9:
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext).myForceReverse(false);
                    break;
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.11
        /* JADX WARN: Removed duplicated region for block: B:37:0x0100  */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClickItem(int r17, int r18, int r19) {
            /*
                Method dump skipped, instructions count: 1882
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._375.UiMgr.AnonymousClass11.onClickItem(int, int, int):void");
        }
    };
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.12
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_375_reservation_charging")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_375_reservation_charging", "_375_reservation_charging_start")) {
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).setStartTime();
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_375_reservation_charging", "_375_reservation_charging_end")) {
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).setEndTime();
                }
            }
            UiMgr uiMgr6 = UiMgr.this;
            if (i == uiMgr6.getSettingLeftIndexes(uiMgr6.mContext, "_375_other_setting")) {
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_375_other_setting", "_375_panoramic_trigger2")) {
                    UiMgr.this.data1Bit6_0x85 = 1;
                    UiMgr.this.send0x85Info();
                    UiMgr.this.data1Bit6_0x85 = 0;
                    UiMgr.this.send0x85Info();
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_375_other_setting", "_375_panoramic_trigger3")) {
                    UiMgr.this.data1Bit5_0x85 = 1;
                    UiMgr.this.send0x85Info();
                    UiMgr.this.data1Bit5_0x85 = 0;
                    UiMgr.this.send0x85Info();
                }
            }
            UiMgr uiMgr9 = UiMgr.this;
            if (i == uiMgr9.getSettingLeftIndexes(uiMgr9.mContext, "_375_car_setting")) {
                if (i2 == UiMgr.this.get0x83Right("_375_car_setting1")) {
                    UiMgr.this.send0x83CarInfo(128, 0);
                    UiMgr uiMgr10 = UiMgr.this;
                    uiMgr10.getMsgMgr(uiMgr10.mContext).toast("Reset success");
                }
                if (i2 == UiMgr.this.get0x83Right("_375_car_setting14")) {
                    UiMgr.this.send0x83CarInfo(128, 12);
                    UiMgr uiMgr11 = UiMgr.this;
                    uiMgr11.getMsgMgr(uiMgr11.mContext).toast("Tire pressure reset success");
                }
            }
        }
    };
    OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestInfo(83, 0);
            UiMgr.this.activeRequestInfo(82, 0);
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_375_car_setting") && i2 == UiMgr.this.get0x83Right("_375_car_setting374")) {
                UiMgr.this.send0x83CarInfo(41, i3 + 10);
                MyLog.temporaryTracking(i3 + "");
            }
        }
    };
    OnTirePageStatusListener tirePageStatusListener = new OnTirePageStatusListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnTirePageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestInfo(56, 0);
            UiMgr.this.activeRequestInfo(57, 0);
        }
    };
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._375.UiMgr.16
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestInfo(58, 0);
        }
    };
    int data0_0x85 = 0;
    int data1Bit7_0x85 = 0;
    int data1Bit6_0x85 = 0;
    int data1Bit5_0x85 = 0;
    private String mId3SongTitle = "";
    int d0b7 = 0;
    int d0b6 = 0;
    int d0b5 = 0;
    int d0b4 = 0;
    int d0b3 = 0;
    int d0b2 = 0;
    int d0b1 = 0;
    int d0b0 = 0;
    int d1b7 = 0;
    int d1b6 = 0;
    int d1b5 = 0;
    int d1b4 = 0;
    int d1b3 = 0;
    int d1b2 = 0;
    int d1b1 = 0;
    int d1b0 = 0;
    int d2b7 = 0;
    int d2b6 = 0;
    int d2b5 = 0;
    int d2b4 = 0;
    int d2b3 = 0;
    int d2b2 = 0;
    int d2b1 = 0;
    int d2b0 = 0;
    int d3b7 = 0;
    int d3b6 = 0;
    int d3b5 = 0;
    int d3b4 = 0;
    int d3b3 = 0;
    int d3b2 = 0;
    int d3b1 = 0;
    int d3b0 = 0;
    int d5b2 = 0;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    /* JADX INFO: Access modifiers changed from: private */
    public int get0x83SelectPos(int i) {
        return i == 0 ? 2 : 1;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        settingUiSet.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        getTireUiSet(this.mContext).setOnTirePageStatusListener(this.tirePageStatusListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
    }

    private void initUi() {
        MsgMgr msgMgr = getMsgMgr(this.mContext);
        Context context = this.mContext;
        int settingLeftIndexes = getSettingLeftIndexes(context, "_375_auto_park");
        int settingRightIndex = getSettingRightIndex(this.mContext, "_375_auto_park", "_375_auto_park_setting");
        String str = this.KEY_AUTO_PARKING_VIEW;
        msgMgr.updateSettings(context, settingLeftIndexes, settingRightIndex, str, SharePreUtil.getIntValue(this.mContext, str, 0));
        MsgMgr msgMgr2 = getMsgMgr(this.mContext);
        Context context2 = this.mContext;
        int settingLeftIndexes2 = getSettingLeftIndexes(context2, "_375_car_select");
        int settingRightIndex2 = getSettingRightIndex(this.mContext, "_375_car_select", "_375_car_select");
        String str2 = this.KEY_CAR_SELECT;
        msgMgr2.updateSettings(context2, settingLeftIndexes2, settingRightIndex2, str2, SharePreUtil.getIntValue(this.mContext, str2, 0));
        MsgMgr msgMgr3 = getMsgMgr(this.mContext);
        Context context3 = this.mContext;
        int settingLeftIndexes3 = getSettingLeftIndexes(context3, "_375_other_setting");
        int settingRightIndex3 = getSettingRightIndex(this.mContext, "_375_other_setting", "_375_parking_trajectory");
        String str3 = this.KEY_0x85_DATA1BIT7;
        msgMgr3.updateSettings(context3, settingLeftIndexes3, settingRightIndex3, str3, SharePreUtil.getIntValue(this.mContext, str3, 0));
        MsgMgr msgMgr4 = getMsgMgr(this.mContext);
        Context context4 = this.mContext;
        int settingLeftIndexes4 = getSettingLeftIndexes(context4, "_375_other_setting");
        int settingRightIndex4 = getSettingRightIndex(this.mContext, "_375_other_setting", "_375_panoramic_trigger1");
        String str4 = this.KEY_0x85_DATA1BIT7;
        msgMgr4.updateSettings(context4, settingLeftIndexes4, settingRightIndex4, str4, SharePreUtil.getIntValue(this.mContext, str4, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x83CarInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int get0x83Right(String str) {
        return getSettingRightIndex(this.mContext, "_375_car_setting", str);
    }

    public void activeRequestInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    public void send0x75LCD(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        CanbusMsgSender.sendMsg(new byte[]{22, 117, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x85Info() {
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) this.data0_0x85, (byte) getDecimalFrom8Bit(this.data1Bit7_0x85, this.data1Bit6_0x85, this.data1Bit5_0x85, 0, 0, 0, 0, 0)});
    }

    public void send0xC0Str(int i, String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.mId3SongTitle)) {
            return;
        }
        this.mId3SongTitle = str;
        try {
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -64, (byte) i, -1, 18, 1, (byte) str.length()}, str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public void send0xC0PhoneInfo(int i, int i2, int i3, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -64, 5, (byte) i, (byte) i2, (byte) i3, 1, 11}, bArr));
    }

    public void send0xC0MediaInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, (byte) i9, (byte) i10});
    }

    public void send0xC0RadioInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    public void getCanBoxVersion() {
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
    }

    public void sendTimeInfo(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i, (byte) i2, (byte) i3});
    }

    public void send0x84CarInfo(int i, int i2, int i3, int i4, int i5) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5});
    }

    public void send0x84CarInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    public void sendAutoParkingModel(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) i});
    }

    public void send0xA7PanoramicInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x86AirInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte) getDecimalFrom8Bit(this.d0b7, this.d0b6, this.d0b5, this.d0b4, this.d0b3, this.d0b2, this.d0b1, this.d0b0), (byte) getDecimalFrom8Bit(this.d1b7, this.d1b6, this.d1b5, this.d1b4, this.d1b3, this.d1b2, this.d1b1, this.d1b0), (byte) getDecimalFrom8Bit(this.d2b7, this.d2b6, this.d2b5, this.d2b4, this.d2b3, this.d2b2, this.d2b1, this.d2b0), (byte) getDecimalFrom8Bit(this.d3b7, this.d3b6, this.d3b5, this.d3b4, this.d3b3, this.d3b2, this.d3b1, this.d3b0), 0, (byte) getDecimalFrom8Bit(0, 0, 0, 0, 0, this.d5b2, 0, 0)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xEECarSelectInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 3, (byte) i});
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

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    public boolean isLandscape() {
        return this.mContext.getResources().getConfiguration().orientation == 2;
    }

    public boolean isPortrait() {
        return this.mContext.getResources().getConfiguration().orientation == 1;
    }
}
