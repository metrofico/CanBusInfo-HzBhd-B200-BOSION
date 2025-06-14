package com.hzbhd.canbus.car._272;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private int mCurrentBand = 0;
    private int mCurrentScanStutas = 0;
    private int mCurrentPresetScan = 0;
    private int mCurrentAutoStore = 0;
    private int mCurrentPlayStatusCd = 0;
    private int mCurrentScanCd = 0;
    private int mCurrentRptCd = 0;
    private int mCurrentSRdmCd = 0;
    private int mCurrentPlayStatusUsb = 0;
    private int mCurrentScanUsb = 0;
    private int mCurrentRptUsb = 0;
    private int mCurrentSRdmUsb = 0;
    OnAirBtnClickListener onAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(5);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirCommand(17);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirCommand(7);
            } else if (i == 3) {
                UiMgr.this.sendAirCommand(12);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.sendAirCommand(6);
            }
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(20);
            } else if (i == 1) {
                UiMgr.this.sendAirCommand(19);
            }
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(8);
            } else if (i == 1) {
                UiMgr.this.sendAirCommand(9);
            }
        }
    };
    OnAirBtnClickListener onAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(16);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirCommand(18);
            } else if (i == 2) {
                UiMgr.this.sendAirCommand(15);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAirCommand(10);
            }
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(2);
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(4);
        }
    };

    private byte setWidgetData(boolean z) {
        return !z ? (byte) 1 : (byte) 0;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -8, 1, 0});
        final MsgMgr msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(this.mContext);
        getSettingUiSet(this.mContext).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -16, 7, (byte) i3});
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -16, 8, (byte) i3});
                    } else if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -16, 9, (byte) i3});
                    }
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerTop, this.onAirBtnClickListenerLeft, this.onAirBtnClickListenerRight, this.onAirBtnClickListenerBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, null, this.onUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirCommand(11);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirCommand(11);
            }
        });
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                if (GeneralAirData.front_wind_level != 0) {
                    UiMgr.this.sendAirCommand(14);
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                if (GeneralAirData.front_wind_level < 7) {
                    UiMgr.this.sendAirCommand(13);
                }
            }
        });
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i) {
                if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    UiMgr.this.sendSourceMsg(i + 1);
                    return;
                }
                if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    if (i <= 6) {
                        UiMgr.this.sendSourceMsg(i + 1);
                        return;
                    }
                    switch (i) {
                        case 7:
                            if (UiMgr.this.mCurrentScanCd == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, 1});
                                UiMgr.this.mCurrentScanCd = 1;
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, 0});
                                UiMgr.this.mCurrentScanCd = 0;
                                break;
                            }
                        case 8:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, 2});
                            break;
                        case 9:
                            if (UiMgr.this.mCurrentRptCd == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 1});
                                UiMgr.this.mCurrentRptCd = 1;
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 0});
                                UiMgr.this.mCurrentRptCd = 0;
                                break;
                            }
                        case 10:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 2});
                            break;
                        case 11:
                            if (UiMgr.this.mCurrentSRdmCd == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 1});
                                UiMgr.this.mCurrentSRdmCd = 1;
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 0});
                                UiMgr.this.mCurrentSRdmCd = 0;
                                break;
                            }
                        case 12:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 2});
                            break;
                    }
                }
                if (!"USB".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    if ("AUX/Navi".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                        if (i == 4) {
                            Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.steering_wheel_control_switches_to_meter), 0).show();
                        }
                        UiMgr.this.sendSourceMsg(i + 1);
                        return;
                    }
                    UiMgr.this.sendSourceMsg(i + 1);
                }
                if (i < 7) {
                    UiMgr.this.sendSourceMsg(i + 1);
                    return;
                }
                switch (i) {
                    case 7:
                        if (UiMgr.this.mCurrentScanUsb == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 4, 1});
                            UiMgr.this.mCurrentScanUsb = 1;
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 4, 0});
                            UiMgr.this.mCurrentScanUsb = 0;
                            break;
                        }
                    case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 4, 2});
                        break;
                    case 9:
                        if (UiMgr.this.mCurrentRptUsb == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 3, 1});
                            UiMgr.this.mCurrentRptUsb = 1;
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 3, 0});
                            UiMgr.this.mCurrentRptUsb = 0;
                            break;
                        }
                    case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 3, 2});
                        break;
                    case 11:
                        if (UiMgr.this.mCurrentSRdmUsb == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 5, 1});
                            UiMgr.this.mCurrentSRdmUsb = 1;
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 5, 0});
                            UiMgr.this.mCurrentSRdmUsb = 0;
                            break;
                        }
                    case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -12, 5, 2});
                        break;
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    switch (i) {
                        case 0:
                            if (UiMgr.this.mCurrentBand == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 1});
                                UiMgr.this.toastMsg(R.string._272_toast_text1);
                                UiMgr.this.mCurrentBand = 1;
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 0});
                                UiMgr.this.toastMsg(R.string._272_toast_text2);
                                UiMgr.this.mCurrentBand = 0;
                                break;
                            }
                        case 1:
                            if (UiMgr.this.mCurrentScanStutas == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 1});
                                UiMgr.this.toastMsg(R.string._272_toast_text3);
                                UiMgr.this.mCurrentScanStutas = 1;
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 0});
                                UiMgr.this.toastMsg(R.string._272_toast_text4);
                                UiMgr.this.mCurrentScanStutas = 0;
                                break;
                            }
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 1});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 1});
                            break;
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 0});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 0});
                            break;
                        case 6:
                            if (UiMgr.this.mCurrentPresetScan == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 6, 1});
                                UiMgr.this.toastMsg(R.string._272_toast_text5);
                                UiMgr.this.mCurrentPresetScan = 1;
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 6, 0});
                                UiMgr.this.toastMsg(R.string._272_toast_text6);
                                UiMgr.this.mCurrentPresetScan = 0;
                                break;
                            }
                        case 7:
                            if (UiMgr.this.mCurrentAutoStore == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 7, 1});
                                UiMgr.this.toastMsg(R.string._272_toast_text7);
                                UiMgr.this.mCurrentAutoStore = 1;
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -15, 7, 0});
                                UiMgr.this.toastMsg(R.string._272_toast_text8);
                                UiMgr.this.mCurrentAutoStore = 0;
                                break;
                            }
                    }
                }
                if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    switch (i) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
                            break;
                        case 3:
                            if (UiMgr.this.mCurrentPlayStatusCd == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 1, 0});
                                originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.PAUSE, "down", "right", OriginalBtnAction.NEXT_DISC});
                                UiMgr.this.mCurrentPlayStatusCd = 1;
                                Bundle bundle = new Bundle();
                                bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                                msgMgr.updateOriginalCarDevice(bundle);
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -14, 2, 0});
                                originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.PLAY, "down", "right", OriginalBtnAction.NEXT_DISC});
                                UiMgr.this.mCurrentPlayStatusCd = 0;
                                Bundle bundle2 = new Bundle();
                                bundle2.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                                msgMgr.updateOriginalCarDevice(bundle2);
                                break;
                            }
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
                            break;
                    }
                }
                if ("USB".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    switch (i) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 6, 1});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 7, 1});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 8, 1});
                            break;
                        case 3:
                            if (UiMgr.this.mCurrentPlayStatusUsb == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -12, 1, 0});
                                originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.PAUSE, "down", "right", OriginalBtnAction.NEXT_DISC});
                                UiMgr.this.mCurrentPlayStatusUsb = 1;
                                Bundle bundle3 = new Bundle();
                                bundle3.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                                msgMgr.updateOriginalCarDevice(bundle3);
                                break;
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -12, 2, 0});
                                originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.PLAY, "down", "right", OriginalBtnAction.NEXT_DISC});
                                UiMgr.this.mCurrentPlayStatusUsb = 0;
                                Bundle bundle4 = new Bundle();
                                bundle4.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                                msgMgr.updateOriginalCarDevice(bundle4);
                                break;
                            }
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 8, 0});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 7, 0});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, -12, 6, 0});
                            break;
                    }
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int i) {
                if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 8, (byte) msgMgr.currentPtore});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int i) {
                if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 4, (byte) msgMgr.currentPtore});
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
            public void onBackPressed() {
                UiMgr.this.sendSourceMsg(5);
            }
        });
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public void onStatusChange(int i) {
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                Log.i("ljq", "position: " + amplifierPosition.name() + ": " + i);
                if (amplifierPosition == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 2, (byte) (i + 1 + 15)});
                } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 3, (byte) (i + 1 + 15)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._272.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                if (amplifierBand == AmplifierActivity.AmplifierBand.BASS) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 4, (byte) Math.abs(i + 1)});
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.MIDDLE) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 5, (byte) Math.abs(i + 1)});
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 6, (byte) Math.abs(i + 1)});
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
    }

    private void sendAirBtnCtrl(int i, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, setWidgetData(z)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toastMsg(int i) {
        Toast.makeText(this.mContext, i, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSourceMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -13, 1, (byte) i});
    }
}
