package com.hzbhd.canbus.car._176;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    public int currentClickFront;
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    public Context mContext;
    MsgMgr mMsgMgr;
    OnPanoramicItemClickListener onPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -80, (byte) (i + 1)});
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                if (i2 == 12) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                    return;
                }
                return;
            }
            if (i == 2) {
                if (i2 == 11) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
                }
            } else {
                if (i != 4) {
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, (byte) i3});
                } else if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte) i3});
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, (byte) i3});
                }
            }
        }
    };
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) i3});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte) i3});
                        break;
                    case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                        break;
                    case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                        break;
                    case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) (MsgMgr.ONE + i3)});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) (MsgMgr.ONE + i3)});
                        break;
                    case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) (MsgMgr.ONE + i3)});
                        break;
                    case 14:
                        if (UiMgr.this.getCurrentCarId() != 11 && UiMgr.this.getCurrentCarId() != 12) {
                            if (i3 != 2) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, -127});
                                break;
                            }
                        } else {
                            switch (i3) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                                    break;
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                case 18:
                                case 19:
                                case 20:
                                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) (i3 + 2)});
                                    break;
                                case 21:
                                case 22:
                                case 23:
                                case 24:
                                case 25:
                                case 26:
                                case 27:
                                case 28:
                                case 29:
                                case 30:
                                case 31:
                                case 32:
                                case 33:
                                case 34:
                                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) (i3 + 3)});
                                    break;
                                case 35:
                                case 36:
                                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) (i3 + 4)});
                                    break;
                                case 37:
                                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, -127});
                                    break;
                            }
                        }
                        break;
                }
            } else if (i == 1) {
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 1});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 2});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 4});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 5});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 6});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 7});
                        break;
                }
            } else if (i == 2) {
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte) i3});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte) i3});
                        break;
                    case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i3});
                        break;
                    case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                        break;
                    case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) (MsgMgr.ONE + i3)});
                        break;
                    case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte) i3});
                        break;
                    case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) (MsgMgr.ONE + i3)});
                        break;
                    case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte) (MsgMgr.ONE + i3)});
                        break;
                    case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte) i3});
                        break;
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte) i3});
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) (MsgMgr.ONE + i3)});
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) i3});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) i3});
                        break;
                }
            } else if (i != 3) {
                if (i == 4) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, (byte) i3});
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, (byte) i3});
                    }
                }
            } else if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -112, (byte) i3});
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -111, (byte) (MsgMgr.ONE + i3)});
            } else if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -110, (byte) (MsgMgr.ONE + i3)});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, -109, (byte) (MsgMgr.ONE + i3)});
            }
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_176_add_function")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getRightIndex(uiMgr2.mContext, "_176_add_function", "_176_language_title")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getRightIndex(uiMgr3.mContext, "_176_add_function", "_176_add_function2")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, 0});
                    } else if (i3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, 6});
                    } else if (i3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, 7});
                    }
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getRightIndex(uiMgr4.mContext, "_176_add_function", "_176_add_function3")) {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, 0});
                    } else if (i3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, 6});
                    } else if (i3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, 7});
                    }
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getRightIndex(uiMgr5.mContext, "_176_add_function", "_176_add_function33")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte) i3});
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getRightIndex(uiMgr6.mContext, "_176_add_function", "_176_add_function36")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 45, (byte) i3});
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getRightIndex(uiMgr7.mContext, "_176_add_function", "_176_add_function39")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 46, (byte) i3});
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getRightIndex(uiMgr8.mContext, "_176_add_function", "_176_add_function43")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte) i3});
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getRightIndex(uiMgr9.mContext, "_176_add_function", "_176_add_function44")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte) i3});
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getRightIndex(uiMgr10.mContext, "_176_add_function", "_176_add_function45")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte) (i3 + 1)});
                }
            }
        }
    };
    OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            if (i != 1) {
                return;
            }
            switch (i2) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 1});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 2});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 4});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 5});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 6});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 7});
                    break;
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.getEachId() != 1) {
                UiMgr.this.sendAirCommand(15);
            } else {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).toast("Original car not support!");
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.getEachId() != 1) {
                UiMgr.this.sendAirCommand(16);
            } else {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).toast("Original car not support!");
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            if (GeneralAirData.front_wind_level == 15) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 8});
            } else if (GeneralAirData.front_wind_level > MsgMgr.WIND_LEVEL_LOW) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte) (GeneralAirData.front_wind_level - 1)});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            if (GeneralAirData.front_wind_level < MsgMgr.WIND_LEVEL_HIGH) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte) (GeneralAirData.front_wind_level + 1)});
            }
        }
    };
    OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.setFrontSearClick();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.setFrontSearClick();
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        switch (getCurrentCarId()) {
            case 0:
                removeMainPrjBtnByName(context, "air");
                removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33_1"});
                removeSettingLeftItem(context, 2, 0);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
                break;
            case 1:
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeMainPrjBtnByName(context, MainAction.SETTING);
                removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
                removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
                removeFrontAirButton(context, 1, 1, 0);
                removeFrontAirButton(context, 2, 1, 0);
                removeFrontAirButton(context, 0, 4, 0);
                removeFrontAirButton(context, 3, 4, 0);
                removeFrontAirButton(context, 3, 3, 0);
                removeFrontAirButton(context, 3, 2, 0);
                removeFrontAirButton(context, 3, 1, 0);
                break;
            case 2:
            case 4:
            case 9:
                removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33_1"});
                removeSettingLeftItem(context, 2, 0);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
                removeFrontAirButton(context, 3, 3, 0);
                break;
            case 3:
                removeMainPrjBtnByName(context, "air");
                removeMainPrjBtnByName(context, MainAction.SETTING);
                removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
                break;
            case 5:
            case 10:
                removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33_1"});
                removeSettingLeftItem(context, 2, 0);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
                removeFrontAirButton(context, 1, 1, 0);
                removeFrontAirButton(context, 2, 1, 0);
                removeFrontAirButton(context, 3, 3, 0);
                break;
            case 6:
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33_1"});
                removeFrontAirButton(context, 1, 1, 0);
                removeFrontAirButton(context, 2, 1, 0);
                removeSettingLeftItem(context, 3, 3);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_41", "_176_car_setting_title_42"});
                break;
            case 7:
                removeMainPrjBtnByName(context, "air");
                removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33_1"});
                removeSettingLeftItemByNameList(context, new String[]{"_176_car_setting_title_big_2", "_176_car_setting_title_44", "_176_car_setting_title_48"});
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_37", "_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_6", "_176_car_setting_title_8", "_176_car_setting_title_9", "_176_car_setting_title_10", "_176_car_setting_title_11", "_176_car_setting_title_12", "_176_car_setting_title_13", "_176_car_setting_title_14", "_176_car_setting_title_15", "_176_car_setting_title_16", "_176_car_setting_title_27", "_176_car_setting_title_28", "_176_car_setting_title_35", "_176_car_setting_title_33"});
                break;
            case 8:
                removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeFrontAirButton(context, 0, 4, 0);
                removeFrontAirButton(context, 1, 1, 0);
                removeFrontAirButton(context, 2, 1, 0);
                removeFrontAirButton(context, 3, 4, 0);
                removeFrontAirButton(context, 3, 3, 0);
                removeFrontAirButton(context, 3, 2, 0);
                removeFrontAirButton(context, 3, 1, 0);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33_1"});
                removeSettingLeftItemByNameList(context, new String[]{"_176_car_setting_title_big_2", "_176_car_setting_title_44", "_176_car_setting_title_48"});
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_37", "_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_6", "_176_car_setting_title_8", "_176_car_setting_title_9", "_176_car_setting_title_10", "_176_car_setting_title_11", "_176_car_setting_title_12", "_176_car_setting_title_13", "_176_car_setting_title_14", "_176_car_setting_title_15", "_176_car_setting_title_16", "_176_car_setting_title_27", "_176_car_setting_title_28", "_176_car_setting_title_35", "_176_car_setting_title_33"});
                break;
            case 11:
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33"});
                removeFrontAirButton(context, 1, 1, 0);
                removeFrontAirButton(context, 2, 1, 0);
                break;
            case 12:
                removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_33"});
                removeSettingLeftItem(context, 2, 0);
                removeSettingRightItemByNameList(context, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
                removeFrontAirButton(context, 0, 4, 0);
                removeFrontAirButton(context, 3, 4, 0);
                removeFrontAirButton(context, 3, 3, 0);
                removeFrontAirButton(context, 3, 2, 0);
                removeFrontAirButton(context, 3, 1, 0);
                break;
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_176_Request_vehicle_information")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 1});
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._176.UiMgr.2
            public String leftTitle;
            public String rightTitle;

            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                this.leftTitle = settingUiSet.getList().get(i).getTitleSrn();
                this.rightTitle = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                String str = this.leftTitle;
                str.hashCode();
                if (str.equals("_176_setting_0")) {
                    String str2 = this.rightTitle;
                    str2.hashCode();
                    switch (str2) {
                        case "_176_setting_0_0":
                            UiMgr.this.sendSettingSwitchInfo(43, i3);
                            break;
                        case "_176_setting_0_1":
                            UiMgr.this.sendSettingSwitchInfo(42, i3);
                            break;
                        case "_176_setting_0_2":
                            UiMgr.this.sendSettingSwitchInfo(41, i3);
                            break;
                        case "_176_setting_0_3":
                            UiMgr.this.sendSettingSwitchInfo(40, i3);
                            break;
                        case "_176_setting_0_4":
                            UiMgr.this.sendSettingSwitchInfo(39, i3);
                            break;
                        case "_176_setting_0_5":
                            UiMgr.this.sendSettingSwitchInfo(38, i3);
                            break;
                        case "_176_setting_0_6":
                            UiMgr.this.sendSettingSwitchInfo(37, i3);
                            break;
                    }
                }
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
    }

    void sendSettingSwitchInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontSearClick() {
        if (GeneralAirData.front_right_blow_window) {
            this.currentClickFront = 3;
            if (GeneralAirData.front_right_blow_foot) {
                this.currentClickFront = 4;
                if (GeneralAirData.front_right_blow_head) {
                    this.currentClickFront = 5;
                }
            }
        }
        if (!GeneralAirData.front_right_blow_window) {
            this.currentClickFront = 2;
            if (!GeneralAirData.front_right_blow_foot) {
                this.currentClickFront = 1;
                if (!GeneralAirData.front_right_blow_head) {
                    this.currentClickFront = 0;
                }
            }
        }
        int i = this.currentClickFront;
        if (i != 0) {
            if (i != 1) {
                if (i == 2 || i == 3) {
                    sendAirCommand(24);
                    return;
                } else if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                }
            }
            sendAirCommand(9);
            return;
        }
        sendAirCommand(7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(6);
                break;
            case "normal":
                sendAirCommand(27);
                break;
            case "rear_defog":
                sendAirCommand(23);
                break;
            case "blow_positive":
                sendAirCommand(28);
                break;
            case "blow_negative":
                sendAirCommand(29);
                break;
            case "auto_cycle":
                sendAirCommand(22);
                break;
            case "ac":
                sendAirCommand(1);
                break;
            case "auto":
                sendAirCommand(2);
                break;
            case "dual":
                sendAirCommand(3);
                break;
            case "fast":
                sendAirCommand(26);
                break;
            case "soft":
                sendAirCommand(25);
                break;
            case "power":
                sendAirCommand(0);
                break;
            case "in_out_cycle":
                sendAirCommand(21);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -88, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -88, b, 0});
    }

    protected int getLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    protected int getRightIndex(Context context, String str, String str2) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
