package com.hzbhd.canbus.car._386;

import android.content.Context;
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
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.constant.disc.MpegConstantsDef;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    AirPageUiSet airPageUiSet;
    AmplifierPageUiSet amplifierPageUiSet;
    DriverDataPageUiSet driverDataPageUiSet;
    Context mContext;
    private MsgMgr msgMgr;
    ParkPageUiSet parkPageUiSet;
    SettingPageUiSet settingPageUiSet;
    private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMessage(2);
            } else if (i == 1) {
                UiMgr.this.sendAirMessage(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(4);
            }
        }
    };
    private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(6);
        }
    };
    private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(5);
        }
    };
    private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMessage(41);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirMessage(7);
            }
        }
    };
    private OnAirBtnClickListener mRearBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMessage(44);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirMessage(19);
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(14);
        }
    };
    private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(16);
        }
    };
    private OnAirTemperatureUpDownClickListener mRearCenterTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(32);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(33);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirMessage(17);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirMessage(18);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.22
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendSettingMessage(29, uiMgr.resolveHeat(uiMgr.getMsgMgr(uiMgr.mContext).memory));
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.23
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendSettingMessage(32, uiMgr.resolveHeat(uiMgr.getMsgMgr(uiMgr.mContext).memory2));
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    private int ResolveTrueOrFalse(boolean z) {
        return z ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolveHeat(int i) {
        if (i == 3) {
            return 0;
        }
        return i + 1;
    }

    private int resolvedec(int i) {
        if (i == 0) {
            return 0;
        }
        return i - 1;
    }

    private int resolveinc(int i) {
        if (i == 7) {
            return 7;
        }
        return i + 1;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (this.differentId == 2) {
            this.airPageUiSet.getFrontArea().setAllBtnCanClick(false);
            this.airPageUiSet.getRearArea().setAllBtnCanClick(false);
            this.airPageUiSet.getFrontArea().setCanSetWindSpeed(false);
            this.airPageUiSet.getRearArea().setCanSetWindSpeed(false);
            this.airPageUiSet.getFrontArea().setCanSetSeatCold(false);
            this.airPageUiSet.getFrontArea().setCanSetSeatHeat(false);
            this.airPageUiSet.getFrontArea().setCanSetLeftTemp(false);
            this.airPageUiSet.getFrontArea().setCanSetRightTemp(false);
            this.airPageUiSet.getRearArea().setShowCenterWheel(false);
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.airPageUiSet = getAirUiSet(this.mContext);
        this.settingPageUiSet = getSettingUiSet(this.mContext);
        this.amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        this.parkPageUiSet = getParkPageUiSet(this.mContext);
        this.driverDataPageUiSet = getDriverDataPageUiSet(this.mContext);
        this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.sendPageStatus(98);
                UiMgr.this.sendPageStatus(MpegConstantsDef.MPEG_PASSWORD_CFM);
            }
        });
        this.driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.sendPageStatus(50);
            }
        });
        this.settingPageUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "speed_linkage_volume_adjustment":
                        UiMgr.this.sendAmplifier(7, i3);
                        break;
                    case "surround_sound":
                        UiMgr.this.sendAmplifier(8, i3);
                        break;
                    case "language_setup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                        break;
                    case "_386_item_14":
                        UiMgr.this.sendSettingMessage(16, i3);
                        break;
                    case "_386_item_15":
                        UiMgr.this.sendSettingMessage(17, i3);
                        break;
                    case "_386_item_16":
                        UiMgr.this.sendSettingMessage(18, i3);
                        break;
                    case "_386_item_17":
                        UiMgr.this.sendSettingMessage(19, i3);
                        break;
                    case "_386_item_18":
                        UiMgr.this.sendSettingMessage(20, i3);
                        break;
                    case "_386_item_19":
                        UiMgr.this.sendSettingMessage(21, i3);
                        break;
                    case "_386_item_20":
                        UiMgr.this.sendSettingMessage(22, i3);
                        break;
                    case "_386_item_21":
                        UiMgr.this.sendSettingMessage(23, i3);
                        break;
                    case "_386_item_22":
                        UiMgr.this.sendSettingMessage(24, i3);
                        break;
                    case "_386_item_23":
                        UiMgr.this.sendSettingMessage(25, i3);
                        break;
                    case "_386_item_25":
                        UiMgr.this.sendSettingMessage(26, i3);
                        break;
                    case "_386_item_26":
                        UiMgr.this.sendSettingMessage(27, i3);
                        break;
                    case "_386_item_27":
                        UiMgr.this.sendSettingMessage(28, i3);
                        break;
                    case "_386_item_38":
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(i, i2, i3);
                        break;
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_386_seat_chirapsia_driver":
                        UiMgr.this.sendSettingMessage(30, i3);
                        break;
                    case "_386_seat_chirapsia_copilot":
                        UiMgr.this.sendSettingMessage(33, i3);
                        break;
                    case "_386_lumbar_support_copilot":
                        UiMgr.this.sendSettingMessage(34, i3);
                        break;
                    case "_386_lumbar_support_driver":
                        UiMgr.this.sendSettingMessage(31, i3);
                        break;
                }
            }
        });
        this.amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass24.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    UiMgr.this.sendAmplifier(3, i - 246);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    UiMgr.this.sendAmplifier(2, i - 246);
                }
            }
        });
        this.amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass24.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    UiMgr uiMgr = UiMgr.this;
                    if (i > uiMgr.getMsgMgr(uiMgr.mContext).volume) {
                        UiMgr.this.sendAmplifier(1, 1);
                    }
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i < uiMgr2.getMsgMgr(uiMgr2.mContext).volume) {
                        UiMgr.this.sendAmplifier(1, 255);
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    UiMgr.this.sendAmplifier(6, i);
                } else if (i2 == 3) {
                    UiMgr.this.sendAmplifier(5, i);
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    UiMgr.this.sendAmplifier(4, i);
                }
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirMessage(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirMessage(11);
            }
        });
        this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (UiMgr.this.differentId == 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                if (UiMgr.this.differentId == 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(22);
            }
        });
        this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mRearBottomBtnListener});
        this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirMessage(43);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirMessage(42);
            }
        });
        this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearCenterTempListener, null});
        this.airPageUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (UiMgr.this.differentId == 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(45);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                if (UiMgr.this.differentId == 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(45);
            }
        });
        this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._386.UiMgr.11
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr uiMgr = UiMgr.this;
                    if (uiMgr.getMsgMgr(uiMgr.mContext).Right) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettingPano(0, false);
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettingPano(0, true);
                        return;
                    }
                }
                if (i == 2) {
                    UiMgr uiMgr4 = UiMgr.this;
                    if (uiMgr4.getMsgMgr(uiMgr4.mContext).Left) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettingPano(2, false);
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                        UiMgr uiMgr6 = UiMgr.this;
                        uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettingPano(2, true);
                        return;
                    }
                }
                if (i == 3) {
                    UiMgr uiMgr7 = UiMgr.this;
                    if (uiMgr7.getMsgMgr(uiMgr7.mContext).Up) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                        UiMgr uiMgr8 = UiMgr.this;
                        uiMgr8.getMsgMgr(uiMgr8.mContext).updateSettingPano(3, false);
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
                        UiMgr uiMgr9 = UiMgr.this;
                        uiMgr9.getMsgMgr(uiMgr9.mContext).updateSettingPano(3, true);
                        return;
                    }
                }
                if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 1});
                    return;
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (uiMgr10.getMsgMgr(uiMgr10.mContext).Down) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                    UiMgr uiMgr11 = UiMgr.this;
                    uiMgr11.getMsgMgr(uiMgr11.mContext).updateSettingPano(4, false);
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
                    UiMgr uiMgr12 = UiMgr.this;
                    uiMgr12.getMsgMgr(uiMgr12.mContext).updateSettingPano(4, true);
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._386.UiMgr$24, reason: invalid class name */
    static /* synthetic */ class AnonymousClass24 {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirMessage(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifier(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPageStatus(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingMessage(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, -1, -1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(this.mContext);
        }
        return this.msgMgr;
    }
}
