package com.hzbhd.canbus.car._330;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.Priority;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;
    String L3R0_300 = "L3R0_300";
    String L3R1_300 = "L3R1_300";
    private int play_pause_state = 0;
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass27.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) ((-i) + 7)});
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
            }
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass27.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
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
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_330_amplifier_info")) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 3) {
                            if (i2 == 5) {
                                if (i3 == 0) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
                                } else if (i3 == 1) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
                                } else if (i3 == 2) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 2});
                                } else if (i3 == 3) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 3});
                                }
                            }
                        } else if (i3 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                        }
                    } else if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 0});
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 1});
                    }
                } else if (i3 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, 0});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, 1});
                }
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getLeftIndexes(uiMgr2.mContext, "_330_Mode_selection") && i2 == 0) {
                if (i3 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -29, 0});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -29, 1});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getLeftIndexes(uiMgr3.mContext, "_330_setting_info") && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte) UiMgr.this.getCarModelData(i3)});
            }
            UiMgr uiMgr4 = UiMgr.this;
            if (i == uiMgr4.getLeftIndexes(uiMgr4.mContext, "_330_media_Language_settings")) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 == 2) {
                            if (i3 == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 0});
                                return;
                            }
                            if (i3 == 1) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 1});
                                return;
                            }
                            if (i3 == 2) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 2});
                                return;
                            }
                            if (i3 == 3) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 3});
                                return;
                            } else if (i3 == 4) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 4});
                                return;
                            } else if (i3 == 5) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 79, 5});
                                return;
                            }
                        }
                    } else {
                        if (i3 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 0});
                            return;
                        }
                        if (i3 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 1});
                            return;
                        }
                        if (i3 == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 2});
                            return;
                        }
                        if (i3 == 3) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 3});
                            return;
                        } else if (i3 == 4) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 4});
                            return;
                        } else if (i3 == 5) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 78, 5});
                            return;
                        }
                    }
                } else {
                    if (i3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 0});
                        return;
                    }
                    if (i3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 1});
                        return;
                    }
                    if (i3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 2});
                        return;
                    }
                    if (i3 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 3});
                        return;
                    } else if (i3 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 4});
                        return;
                    } else if (i3 == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 77, 5});
                        return;
                    }
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            int leftIndexes = uiMgr5.getLeftIndexes(uiMgr5.mContext, "_330_unit_selection");
            if (i == leftIndexes) {
                if (i2 == 0) {
                    UiMgr uiMgr6 = UiMgr.this;
                    uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettings(UiMgr.this.mContext, "C" + UiMgr.this.getCurrentCarId() + "M" + UiMgr.this.getEachId() + "L" + i + "R0", leftIndexes, 0, i3);
                    UiMgr uiMgr7 = UiMgr.this;
                    uiMgr7.getMsgMgr(uiMgr7.mContext).updateAirInfo();
                    UiMgr.this.sendAirData(3);
                    return;
                }
                if (i2 != 1) {
                    return;
                }
                UiMgr uiMgr8 = UiMgr.this;
                uiMgr8.getMsgMgr(uiMgr8.mContext).updateSettings(UiMgr.this.mContext, "C" + UiMgr.this.getCurrentCarId() + "M" + UiMgr.this.getEachId() + "L" + i + "R1", leftIndexes, 1, i3);
                UiMgr uiMgr9 = UiMgr.this;
                uiMgr9.getMsgMgr(uiMgr9.mContext).uadateTrafficInfo();
                Intent intent = new Intent();
                intent.setAction("CAN_ID330_GX470_UNIT_TYPE");
                intent.putExtra("TYPE", i3);
                UiMgr.this.mContext.sendBroadcast(intent);
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_330_other_settings")) {
                if (i2 == 0) {
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.L3R0_300, i, i2, i3 * 100, "");
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) i3});
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.L3R1_300, i, i2, i3 * 100, "");
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i3});
                }
            }
        }
    };
    OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (uiMgr.getLeftIndexes(uiMgr.mContext, "_330_Information_reset") == i && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -27, 0, 0});
                Toast.makeText(UiMgr.this.mContext, "重设成功", 0).show();
            }
        }
    };
    private int fastForward = 0;
    private int fastBack = 0;
    private int RADIO_PLAY_PAUSE = 0;
    OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.getMsgMgr(uiMgr.mContext);
            if (MsgMgr.mediaTag.equals("USB")) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -59, 3});
                } else if (i != 1) {
                    if (i == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -59, 4});
                    }
                } else if (UiMgr.this.play_pause_state == 0) {
                    UiMgr.this.play_pause_state = 1;
                    CanbusMsgSender.sendMsg(new byte[]{22, -59, 1});
                } else {
                    UiMgr.this.play_pause_state = 0;
                    CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
                }
            }
            UiMgr uiMgr2 = UiMgr.this;
            uiMgr2.getMsgMgr(uiMgr2.mContext);
            if (MsgMgr.mediaTag.equals("CD")) {
                switch (i) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 22, 0});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 20, 0});
                        break;
                    case 2:
                        if (UiMgr.this.fastBack == 0) {
                            UiMgr.this.fastBack = 1;
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 25, 1});
                            break;
                        } else {
                            UiMgr.this.fastBack = 0;
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 25, 0});
                            break;
                        }
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 16, 0});
                        Toast.makeText(UiMgr.this.mContext, "随机播放", 0).show();
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, 0});
                        Toast.makeText(UiMgr.this.mContext, "循环播放", 0).show();
                        break;
                    case 5:
                        if (UiMgr.this.fastForward == 0) {
                            UiMgr.this.fastForward = 1;
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 24, 1});
                            break;
                        } else {
                            UiMgr.this.fastForward = 0;
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 24, 0});
                            break;
                        }
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 19, 0});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 21, 0});
                        break;
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            uiMgr3.getMsgMgr(uiMgr3.mContext);
            if (MsgMgr.mediaTag.equals("RADIO")) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 38, 0});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 35, 0});
                    return;
                }
                if (i != 2) {
                    if (i == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 34, 0});
                        return;
                    } else {
                        if (i != 4) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 37, 0});
                        return;
                    }
                }
                if (UiMgr.this.RADIO_PLAY_PAUSE == 0) {
                    UiMgr.this.RADIO_PLAY_PAUSE = 1;
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 36, 1});
                } else {
                    UiMgr.this.RADIO_PLAY_PAUSE = 0;
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 36, 0});
                }
            }
        }
    };
    OnOriginalCarDeviceBackPressedListener onOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
        public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 5});
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 1) {
                UiMgr.this.sendAirData(25);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirData(1);
            } else if (i == 3) {
                UiMgr.this.sendAirData(57);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.sendAirData(18);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData(21);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirData(60);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData(23);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirData(40);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData(16);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirData(39);
            } else if (i == 3) {
                UiMgr.this.sendAirData(58);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.sendAirData(20);
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(2);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(4);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(42);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(41);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearMiddle = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            Toast.makeText(UiMgr.this.mContext, "暂不支持切换此状态", 0).show();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            Toast.makeText(UiMgr.this.mContext, "暂不支持切换此状态", 0).show();
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(51);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(50);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData(10);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData(48);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData(49);
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.sendAirData(36);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.sendAirData(37);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirData(11);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.22
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirData(13);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatRearLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.23
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirData(12);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatRearRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.24
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirData(14);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.25
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 1) {
                UiMgr.this.sendAirData(52);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirData(59);
            }
        }
    };
    OnPanelKeyPositionListener panelKeyPositionListener = new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.26
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
        public void click(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 1});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 2});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 3});
                return;
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 4});
            } else if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 5});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 16});
            }
        }
    };
    private int eachCanId = getEachId();

    /* JADX INFO: Access modifiers changed from: private */
    public int getCarModelData(int i) {
        switch (i) {
            case 0:
                return 32;
            case 1:
                return 33;
            case 2:
                return 48;
            case 3:
                return 49;
            case 4:
                return 64;
            case 5:
                return 65;
            case 6:
                return 80;
            case 7:
                return 81;
            case 8:
                return 96;
            case 9:
                return 97;
            case 10:
                return 98;
            case 11:
                return 99;
            case 12:
                return 100;
            default:
                return 101;
        }
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.onOriginalCarDeviceBackPressedListener);
        final DriverDataPageUiSet driverDataPageUiSet = getDriverDataPageUiSet(context);
        driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._330.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 50, 0});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 22, 0});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 80, 0});
                if (i == -1) {
                    driverDataPageUiSet.setLeftPosition(UiMgr.this.getLeftIndexes(context, "_330_Information_reset"));
                    driverDataPageUiSet.setRightPosition(0);
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerFront);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerHeatRearLeft, this.mOnMinPlusClickListenerHeatRearRight});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerRearLeft, this.mOnUpDownClickListenerRearMiddle, this.mOnUpDownClickListenerRearRight});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerRear);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        getPanelKeyPageUiSet(context).setOnPanelKeyPositionListener(this.panelKeyPositionListener);
        getMsgMgr(this.mContext).SendBroadcast();
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initSettingsUi(context);
        initSettingsData(context);
    }

    private void initSettingsUi(Context context) {
        int i = this.eachCanId;
        if (i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 7 || i == 11) {
            getParkPageUiSet(context).setShowRadar(true);
        }
        int i2 = this.eachCanId;
        if (i2 != 2 && i2 != 4 && i2 != 11) {
            removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_driver_data_speed"), 6);
        }
        int i3 = this.eachCanId;
        if (i3 != 3 && i3 != 6 && i3 != 7 && i3 != 8 && i3 != 9 && i3 != 10 && i3 != 11) {
            if (i3 == 2 || i3 == 4 || i3 == 11) {
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Instantaneous_fuel_consumption"), 6);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Average_fuel_consumption_after_refueling"), 5);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Mileage_you_can_drive"), 4);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_average_velocity"), 3);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Mileage"), 2);
            } else {
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Instantaneous_fuel_consumption"), 5);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Average_fuel_consumption_after_refueling"), 4);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Mileage_you_can_drive"), 3);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_average_velocity"), 2);
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_Mileage"), 1);
            }
        }
        int i4 = this.eachCanId;
        if (i4 != 3 && i4 != 6 && i4 != 7 && i4 != 8 && i4 != 9 && i4 != 10 && i4 != 11) {
            removeSettingLeftItemByNameList(context, new String[]{"_330_Information_reset"});
        }
        int i5 = this.eachCanId;
        if (i5 != 2 && i5 != 3 && i5 != 4 && i5 != 6 && i5 != 7 && i5 != 10 && i5 != 11) {
            if (i5 == 1 || i5 == 5) {
                removeDriveDataPagesByHeadTitles(context, "_330_traffic_information");
            } else {
                removeDrivigDateItem(context, getDrivingPageIndexes(context, "_330_traffic_information"), getDrivingItemIndexes(context, "_330_rotate_speed"), 5);
            }
        }
        int i6 = this.eachCanId;
        if (i6 != 1 && i6 != 3 && i6 != 4 && i6 != 5 && i6 != 6 && i6 != 8 && i6 != 11) {
            removeSettingLeftItemByNameList(context, new String[]{"_330_Mode_selection"});
        }
        int i7 = this.eachCanId;
        if (i7 != 1 && i7 != 3 && i7 != 4 && i7 != 6 && i7 != 8 && i7 != 9 && i7 != 11) {
            removeDriveDataPagesByHeadTitles(context, "_330_Multimedia_global_state");
        }
        int i8 = this.eachCanId;
        if (i8 != 1 && i8 != 2 && i8 != 3 && i8 != 4 && i8 != 5 && i8 != 6 && i8 != 8 && i8 != 9 && i8 != 11) {
            removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        }
        int i9 = this.eachCanId;
        if (i9 != 2 && i9 != 3 && i9 != 4 && i9 != 6 && i9 != 7 && i9 != 10 && i9 != 11) {
            removeSettingRightItem(context, getLeftIndexes(context, "_330_other_settings"), 0, 1);
        }
        int i10 = this.eachCanId;
        if (i10 != 2 && i10 != 4 && i10 != 7 && i10 != 11) {
            if (i10 == 3 || i10 == 6 || i10 == 10) {
                removeSettingRightItem(context, getLeftIndexes(context, "_330_other_settings"), 1, 1);
            } else {
                removeSettingLeftItemByNameList(context, new String[]{"_330_other_settings"});
            }
        }
        int i11 = this.eachCanId;
        if (i11 != 1 && i11 != 2 && i11 != 4 && i11 != 5 && i11 != 6 && i11 != 7 && i11 != 11) {
            removeDriveDataPagesByHeadTitles(context, "_330_Radar_setting_status");
        }
        int i12 = this.eachCanId;
        if (i12 == 3 || i12 == 6 || i12 == 7 || i12 == 8 || i12 == 9 || i12 == 10 || i12 == 11) {
            getDriverDataPageUiSet(this.mContext).setHaveBtn(true);
        }
    }

    private void initSettingsData(Context context) {
        int i = this.eachCanId;
        if (i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 10 || i == 11) {
            if (i == 2 || i == 4 || i == 7) {
                if (SharePreUtil.getIntValue(this.mContext, this.L3R0_300, Priority.DEBUG_INT) == 10000) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 100});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) (SharePreUtil.getIntValue(this.mContext, this.L3R0_300, Priority.DEBUG_INT) / 100)});
                }
                if (SharePreUtil.getIntValue(this.mContext, this.L3R1_300, Priority.DEBUG_INT) == 10000) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, 100});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) (SharePreUtil.getIntValue(this.mContext, this.L3R1_300, Priority.DEBUG_INT) / 100)});
                }
                MsgMgr msgMgr = getMsgMgr(this.mContext);
                Context context2 = this.mContext;
                msgMgr.updateSettings(context2, this.L3R0_300, getLeftIndexes(context2, "_330_other_settings"), 0, SharePreUtil.getIntValue(this.mContext, this.L3R0_300, Priority.DEBUG_INT), "");
                Log.e("DODO", "_________L3R0_300___________" + SharePreUtil.getIntValue(this.mContext, this.L3R0_300, Priority.DEBUG_INT) + "_________");
                MsgMgr msgMgr2 = getMsgMgr(this.mContext);
                Context context3 = this.mContext;
                msgMgr2.updateSettings(context3, this.L3R1_300, getLeftIndexes(context3, "_330_other_settings"), 1, SharePreUtil.getIntValue(this.mContext, this.L3R1_300, Priority.DEBUG_INT), "");
                Log.e("DODO", "_________L3R1_300___________" + SharePreUtil.getIntValue(this.mContext, this.L3R1_300, Priority.DEBUG_INT) + "_________");
            } else {
                MsgMgr msgMgr3 = getMsgMgr(this.mContext);
                Context context4 = this.mContext;
                msgMgr3.updateSettings(context4, this.L3R0_300, getLeftIndexes(context4, "_330_other_settings"), 0, SharePreUtil.getIntValue(this.mContext, this.L3R0_300, Priority.DEBUG_INT), "");
                if (SharePreUtil.getIntValue(this.mContext, this.L3R0_300, Priority.DEBUG_INT) == 1000) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 100});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) (SharePreUtil.getIntValue(this.mContext, this.L3R0_300, Priority.DEBUG_INT) / 100)});
                }
            }
        }
        int leftIndexes = getLeftIndexes(this.mContext, "_330_unit_selection");
        getMsgMgr(this.mContext).updateSettings(this.mContext, "C" + getCurrentCarId() + "M" + getEachId() + "L" + leftIndexes + "R0", leftIndexes, 0, SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCarId() + "M" + getEachId() + "L" + leftIndexes + "R0", 0));
        getMsgMgr(this.mContext).updateSettings(this.mContext, "C" + getCurrentCarId() + "M" + getEachId() + "L" + leftIndexes + "R1", leftIndexes, 1, SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCarId() + "M" + getEachId() + "L" + leftIndexes + "R1", 0));
    }

    /* renamed from: com.hzbhd.canbus.car._330.UiMgr$27, reason: invalid class name */
    static /* synthetic */ class AnonymousClass27 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
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

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirData(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 0});
    }
}
