package com.hzbhd.canbus.car._379;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    boolean CameraDelay;
    boolean DepressedView;
    boolean Force;
    AirPageUiSet airPageUiSet;
    AmplifierPageUiSet amplifierPageUiSet;
    int data003;
    int data047;
    int data101;
    int data123;
    int data147;
    Context mContext;
    private MsgMgr msgMgr;
    ParkPageUiSet parkPageUiSet;
    SettingPageUiSet settingPageUiSet;
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirMessage(17, uiMgr.resolveHeat(uiMgr.getMsgMgr(uiMgr.mContext).LeftHeat));
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirMessage(18, uiMgr.resolveHeat(uiMgr.getMsgMgr(uiMgr.mContext).RightHeat));
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirMessage(19, uiMgr.resolveHeat(uiMgr.getMsgMgr(uiMgr.mContext).LeftCold));
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirMessage(20, uiMgr.resolveHeat(uiMgr.getMsgMgr(uiMgr.mContext).RightCold));
        }
    };
    private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(2, uiMgr.ResolveTrueOrFalse(uiMgr.getMsgMgr(uiMgr.mContext).ac));
                return;
            }
            if (i == 1) {
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.sendAirMessage(3, uiMgr2.ResolveTrueOrFalse(uiMgr2.getMsgMgr(uiMgr2.mContext).ac_max));
            } else if (i == 2) {
                UiMgr uiMgr3 = UiMgr.this;
                uiMgr3.sendAirMessage(1, uiMgr3.ResolveTrueOrFalse(uiMgr3.getMsgMgr(uiMgr3.mContext).power));
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr uiMgr4 = UiMgr.this;
                uiMgr4.sendAirMessage(4, uiMgr4.ResolveTrueOrFalse(uiMgr4.getMsgMgr(uiMgr4.mContext).auto));
            }
        }
    };
    private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(9, uiMgr.ResolveTrueOrFalse(uiMgr.getMsgMgr(uiMgr.mContext).blowhead));
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.sendAirMessage(8, uiMgr2.ResolveTrueOrFalse(uiMgr2.getMsgMgr(uiMgr2.mContext).blowwindow));
            }
        }
    };
    private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirMessage(10, uiMgr.ResolveTrueOrFalse(uiMgr.getMsgMgr(uiMgr.mContext).blowfoot));
        }
    };
    private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(21, uiMgr.ResolveTrueOrFalse(uiMgr.getMsgMgr(uiMgr.mContext).steering_wheel_heating));
                return;
            }
            if (i == 1) {
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.sendAirMessage(6, uiMgr2.ResolveTrueOrFalse(uiMgr2.getMsgMgr(uiMgr2.mContext).rear_defog));
                return;
            }
            if (i == 2) {
                UiMgr uiMgr3 = UiMgr.this;
                uiMgr3.sendAirMessage(5, uiMgr3.ResolveTrueOrFalse(uiMgr3.getMsgMgr(uiMgr3.mContext).mac_heat));
            } else if (i == 3) {
                UiMgr uiMgr4 = UiMgr.this;
                uiMgr4.sendAirMessage(14, uiMgr4.ResolveTrueOrFalse(uiMgr4.getMsgMgr(uiMgr4.mContext).dual));
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr uiMgr5 = UiMgr.this;
                uiMgr5.sendAirMessage(7, uiMgr5.ResolveTrueOrFalse(uiMgr5.getMsgMgr(uiMgr5.mContext).in_out_cycle));
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirMessage(12, uiMgr.getMsgMgr(uiMgr.mContext).LeftTemp + 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(12, r0.getMsgMgr(r0.mContext).LeftTemp - 1);
        }
    };
    private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirMessage(13, uiMgr.getMsgMgr(uiMgr.mContext).RightTemp + 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(13, r0.getMsgMgr(r0.mContext).RightTemp - 1);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    /* JADX INFO: Access modifiers changed from: private */
    public int ResolveTrueOrFalse(boolean z) {
        return z ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolveHeat(int i) {
        if (i == 3) {
            return 0;
        }
        return i + 1;
    }

    private int resolveSelectPosThr(int i) {
        if (i == 7) {
            return 0;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolvedec(int i) {
        if (i == 0) {
            return 0;
        }
        return i - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolveinc(int i) {
        if (i == 7) {
            return 7;
        }
        return i + 1;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.CameraDelay = getMsgMgr(this.mContext).CameraDelay;
        this.DepressedView = getMsgMgr(this.mContext).DepressedView;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        this.airPageUiSet = airUiSet;
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(11, uiMgr.resolvedec(uiMgr.getMsgMgr(uiMgr.mContext).WindLevel));
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(11, uiMgr.resolveinc(uiMgr.getMsgMgr(uiMgr.mContext).WindLevel));
            }
        });
        this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        this.amplifierPageUiSet = amplifierPageUiSet;
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass19.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    UiMgr.this.sendAmplifier(4, i);
                    return;
                }
                if (i2 == 2) {
                    UiMgr.this.sendAmplifier(5, i);
                } else if (i2 == 3) {
                    UiMgr.this.sendAmplifier(6, i);
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    UiMgr.this.sendAmplifier(1, i);
                }
            }
        });
        this.amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                Log.d("lai", "position: " + i);
                int i2 = AnonymousClass19.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    UiMgr.this.sendAmplifier(2, i - 249);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    UiMgr.this.sendAmplifier(3, i - 249);
                }
            }
        });
        this.amplifierPageUiSet.setOnAmplifierResetPositionListener(new OnAmplifierResetPositionListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener
            public void resetClick() {
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.5
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
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                        break;
                    case "temperature_unit":
                        UiMgr.this.sendSettingMessageTwo(4, i3);
                        break;
                    case "language_setup":
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.sendSettingMessageThr(uiMgr2.resolveSelectPosFour(i3));
                        break;
                    case "_379_item_01":
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.sendSettingMessageOne(8, uiMgr3.resolveSelectPosOne(i3));
                        break;
                    case "_379_item_02":
                        UiMgr uiMgr4 = UiMgr.this;
                        uiMgr4.sendSettingMessageOne(9, uiMgr4.resolveSelectPosOne(i3));
                        break;
                    case "_379_item_03":
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.sendSettingMessageOne(10, uiMgr5.resolveSelectPosOne(i3));
                        break;
                    case "_379_item_04":
                        UiMgr uiMgr6 = UiMgr.this;
                        uiMgr6.sendSettingMessageOne(11, uiMgr6.resolveSelectPosTwo(i3));
                        break;
                    case "_379_item_05":
                        UiMgr uiMgr7 = UiMgr.this;
                        uiMgr7.sendSettingMessageOne(12, uiMgr7.resolveSelectPosTwo(i3));
                        break;
                    case "_379_item_06":
                        UiMgr uiMgr8 = UiMgr.this;
                        uiMgr8.sendSettingMessageOne(14, uiMgr8.resolveSelectPosOne(i3));
                        break;
                    case "_379_item_07":
                        UiMgr uiMgr9 = UiMgr.this;
                        uiMgr9.sendSettingMessageOne(15, uiMgr9.resolveSelectPosOne(i3));
                        break;
                    case "_379_item_08":
                        UiMgr uiMgr10 = UiMgr.this;
                        uiMgr10.sendSettingMessageOne(16, uiMgr10.resolveSelectPosOne(i3));
                        break;
                    case "_379_item_09":
                        UiMgr uiMgr11 = UiMgr.this;
                        uiMgr11.sendSettingMessageOne(17, uiMgr11.resolveSelectPosTwo(i3));
                        break;
                    case "_379_item_10":
                        UiMgr uiMgr12 = UiMgr.this;
                        uiMgr12.sendSettingMessageOne(18, uiMgr12.resolveSelectPosTwo(i3));
                        break;
                    case "_379_item_12":
                        UiMgr.this.sendSettingMessageTwo(1, i3);
                        break;
                    case "_379_item_13":
                        UiMgr.this.resolveSelectPosData047(i3 + 3);
                        break;
                    case "_379_item_14":
                        UiMgr.this.resolveSelectPosData003(i3 + 1);
                        break;
                    case "_379_item_15":
                        UiMgr.this.resolveSelectPosData147(i3 + 1);
                        break;
                    case "_379_item_16":
                        UiMgr.this.resolveSelectPosData123(i3 + 1);
                        break;
                    case "_379_item_17":
                        UiMgr.this.resolveSelectPosData101(i3 + 1);
                        break;
                    case "S96ColorTitle":
                        UiMgr.this.sendSettingMessageOne(3, i3);
                        break;
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_379_item_11")) {
                    UiMgr.this.sendSettingMessageOne(2, i3);
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_379_item_30":
                        TypeInView typeInView = new TypeInView();
                        UiMgr uiMgr = UiMgr.this;
                        typeInView.showDialog(uiMgr.getMsgMgr(uiMgr.mContext).getCurrentActivity());
                        break;
                    case "reset_balance":
                        UiMgr.this.sendAmplifier(10, 1);
                        break;
                    case "reset_all":
                        UiMgr.this.sendAmplifier(11, 1);
                        break;
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
        this.parkPageUiSet = parkPageUiSet;
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._379.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                UiMgr.this.Force = true;
                if (i == 0) {
                    UiMgr uiMgr = UiMgr.this;
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, (byte) uiMgr.ResolveTrueOrFalseThree(uiMgr.DepressedView)});
                } else if (i == 1) {
                    UiMgr uiMgr2 = UiMgr.this;
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, (byte) uiMgr2.ResolveTrueOrFalseTwo(uiMgr2.CameraDelay)});
                } else {
                    if (i != 6) {
                        return;
                    }
                    UiMgr.this.Force = false;
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).forceReverse(UiMgr.this.mContext, UiMgr.this.Force);
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._379.UiMgr$19, reason: invalid class name */
    static /* synthetic */ class AnonymousClass19 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
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
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirMessage(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifier(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingMessageOne(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingMessageTwo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingMessageThr(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) i});
    }

    private void sendSettingMessageFour() {
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) ((this.data047 * 16) + this.data003), (byte) ((this.data147 * 16) + (this.data123 * 4) + this.data101)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolveSelectPosOne(int i) {
        return new int[]{255, NfDef.STATE_3WAY_M_HOLD, 241}[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolveSelectPosTwo(int i) {
        return new int[]{255, 0, 1, 2}[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolveSelectPosFour(int i) {
        return new int[]{1, 2, 5, 7}[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveSelectPosData047(int i) {
        this.data047 = i;
        sendSettingMessageFour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveSelectPosData003(int i) {
        if (i >= 3) {
            this.data003 = i + 2;
        } else {
            this.data003 = i;
        }
        sendSettingMessageFour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveSelectPosData147(int i) {
        this.data147 = i;
        sendSettingMessageFour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveSelectPosData123(int i) {
        this.data123 = i;
        sendSettingMessageFour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveSelectPosData101(int i) {
        this.data101 = i;
        sendSettingMessageFour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ResolveTrueOrFalseTwo(boolean z) {
        if (z) {
            this.CameraDelay = false;
            return 0;
        }
        this.CameraDelay = true;
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ResolveTrueOrFalseThree(boolean z) {
        if (z) {
            this.DepressedView = false;
            return 0;
        }
        this.DepressedView = true;
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
