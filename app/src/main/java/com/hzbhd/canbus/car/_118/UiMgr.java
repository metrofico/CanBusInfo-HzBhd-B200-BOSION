package com.hzbhd.canbus.car._118;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._118.SetTimeView;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    int DATA3;
    int DATA4;
    int UtilFUllWeekend;
    int UtilFullWork;
    int WeekEndMinuteAndHour;
    int WeekStartMinuteAndHour;
    int WorkEndMinuteAndHour;
    int WorkStartMinuteAndHour;
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private SettingPageUiSet settingPageUiSet;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    OnAirBtnClickListener onAirBtnClickListener_rearTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr$$ExternalSyntheticLambda0
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m108lambda$new$0$comhzbhdcanbuscar_118UiMgr(i);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_rearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr$$ExternalSyntheticLambda1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m109lambda$new$1$comhzbhdcanbuscar_118UiMgr(i);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_rear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirConditionerData(11);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirConditionerData(10);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener_rear = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirConditionerData(8);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirConditionerData(9);
        }
    };
    int i = 0;
    int[] j = {5, 6, 7};
    OnAirSeatClickListener onAirSeatClickListener_rear = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr uiMgr = UiMgr.this;
            int[] iArr = uiMgr.j;
            UiMgr uiMgr2 = UiMgr.this;
            int i = uiMgr2.i;
            uiMgr2.i = i + 1;
            uiMgr.sendAirConditionerData(iArr[i % 3]);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr uiMgr = UiMgr.this;
            int[] iArr = uiMgr.j;
            UiMgr uiMgr2 = UiMgr.this;
            int i = uiMgr2.i;
            uiMgr2.i = i + 1;
            uiMgr.sendAirConditionerData(iArr[i % 3]);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.this.mCurrentCanDifferentId == 5 || UiMgr.this.mCurrentCanDifferentId == 6 || UiMgr.this.mCurrentCanDifferentId == 7) {
                UiMgr.this.sendAirCommand(48);
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.this.mCurrentCanDifferentId == 5 || UiMgr.this.mCurrentCanDifferentId == 6 || UiMgr.this.mCurrentCanDifferentId == 7) {
                UiMgr.this.sendAirCommand(50);
            }
        }
    };
    OnOriginalSongItemClickListener mOnOriginalSongItemClickListener = new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemLongClick(int i) {
        }

        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemClick(int i) {
            int i2 = i + 1;
            byte b = (byte) (65280 & i2);
            byte b2 = (byte) (i2 & 255);
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 15, b, b2});
            CanbusMsgSender.sendMsg(new byte[]{22, -96, -126, b, b2});
        }
    };
    OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.12
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 17, 0, 0});
            } else {
                if (i != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 8, 0});
            }
        }
    };
    OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, 0, 0});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, 0, 0});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 19, 0, 0});
                return;
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 20, 0, 0});
            } else if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, 0, 0});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, 0, 0});
            }
        }
    };
    OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass31.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -109, 2, (byte) (i + 10)});
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -109, 3, (byte) ((-i) + 10)});
            }
        }
    };
    OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass31.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -109, 4, (byte) (i + 1)});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -109, 5, (byte) (i + 1)});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -109, 6, (byte) (i + 1)});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -109, 1, (byte) i});
            }
        }
    };
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.16
        private byte resolvedata(int i) {
            if (i == 0) {
                return (byte) 0;
            }
            if (i == 1) {
                return (byte) 30;
            }
            return i == 2 ? (byte) 60 : (byte) 90;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            char c = 65535;
            switch (titleSrn.hashCode()) {
                case -2065696613:
                    if (titleSrn.equals("_118_setting_title_10")) {
                        c = 0;
                        break;
                    }
                    break;
                case -2065696612:
                    if (titleSrn.equals("_118_setting_title_11")) {
                        c = 1;
                        break;
                    }
                    break;
                case -2065696611:
                    if (titleSrn.equals("_118_setting_title_12")) {
                        c = 2;
                        break;
                    }
                    break;
                case -2065696610:
                    if (titleSrn.equals("_118_setting_title_13")) {
                        c = 3;
                        break;
                    }
                    break;
                case -2065696609:
                    if (titleSrn.equals("_118_setting_title_14")) {
                        c = 4;
                        break;
                    }
                    break;
                case -2065696608:
                    if (titleSrn.equals("_118_setting_title_15")) {
                        c = 5;
                        break;
                    }
                    break;
                case -2065696607:
                    if (titleSrn.equals("_118_setting_title_16")) {
                        c = 6;
                        break;
                    }
                    break;
                case -2065696606:
                    if (titleSrn.equals("_118_setting_title_17")) {
                        c = 7;
                        break;
                    }
                    break;
                case -2065696605:
                    if (titleSrn.equals("_118_setting_title_18")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -2065696604:
                    if (titleSrn.equals("_118_setting_title_19")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -2065696582:
                    if (titleSrn.equals("_118_setting_title_20")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -2065696581:
                    if (titleSrn.equals("_118_setting_title_21")) {
                        c = 11;
                        break;
                    }
                    break;
                case -2065696580:
                    if (titleSrn.equals("_118_setting_title_22")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -2065696579:
                    if (titleSrn.equals("_118_setting_title_23")) {
                        c = '\r';
                        break;
                    }
                    break;
                case -2065696578:
                    if (titleSrn.equals("_118_setting_title_24")) {
                        c = 14;
                        break;
                    }
                    break;
                case -2065696577:
                    if (titleSrn.equals("_118_setting_title_25")) {
                        c = 15;
                        break;
                    }
                    break;
                case -2065696576:
                    if (titleSrn.equals("_118_setting_title_26")) {
                        c = 16;
                        break;
                    }
                    break;
                case -2065696575:
                    if (titleSrn.equals("_118_setting_title_27")) {
                        c = 17;
                        break;
                    }
                    break;
                case -2065696574:
                    if (titleSrn.equals("_118_setting_title_28")) {
                        c = 18;
                        break;
                    }
                    break;
                case -2065696573:
                    if (titleSrn.equals("_118_setting_title_29")) {
                        c = 19;
                        break;
                    }
                    break;
                case -2065696551:
                    if (titleSrn.equals("_118_setting_title_30")) {
                        c = 20;
                        break;
                    }
                    break;
                case -2065696550:
                    if (titleSrn.equals("_118_setting_title_31")) {
                        c = 21;
                        break;
                    }
                    break;
                case -2065696548:
                    if (titleSrn.equals("_118_setting_title_33")) {
                        c = 22;
                        break;
                    }
                    break;
                case -2065696547:
                    if (titleSrn.equals("_118_setting_title_34")) {
                        c = 23;
                        break;
                    }
                    break;
                case -2065696546:
                    if (titleSrn.equals("_118_setting_title_35")) {
                        c = 24;
                        break;
                    }
                    break;
                case -2065696545:
                    if (titleSrn.equals("_118_setting_title_36")) {
                        c = 25;
                        break;
                    }
                    break;
                case -2065696544:
                    if (titleSrn.equals("_118_setting_title_37")) {
                        c = 26;
                        break;
                    }
                    break;
                case -2065696543:
                    if (titleSrn.equals("_118_setting_title_38")) {
                        c = 27;
                        break;
                    }
                    break;
                case -2065696542:
                    if (titleSrn.equals("_118_setting_title_39")) {
                        c = 28;
                        break;
                    }
                    break;
                case -2065696520:
                    if (titleSrn.equals("_118_setting_title_40")) {
                        c = 29;
                        break;
                    }
                    break;
                case -2065696519:
                    if (titleSrn.equals("_118_setting_title_41")) {
                        c = 30;
                        break;
                    }
                    break;
                case -2065696518:
                    if (titleSrn.equals("_118_setting_title_42")) {
                        c = 31;
                        break;
                    }
                    break;
                case -2065696517:
                    if (titleSrn.equals("_118_setting_title_43")) {
                        c = ' ';
                        break;
                    }
                    break;
                case -2065696516:
                    if (titleSrn.equals("_118_setting_title_44")) {
                        c = '!';
                        break;
                    }
                    break;
                case -2065696515:
                    if (titleSrn.equals("_118_setting_title_45")) {
                        c = Typography.quote;
                        break;
                    }
                    break;
                case -2065696514:
                    if (titleSrn.equals("_118_setting_title_46")) {
                        c = '#';
                        break;
                    }
                    break;
                case -2065696513:
                    if (titleSrn.equals("_118_setting_title_47")) {
                        c = Typography.dollar;
                        break;
                    }
                    break;
                case -2065696512:
                    if (titleSrn.equals("_118_setting_title_48")) {
                        c = '%';
                        break;
                    }
                    break;
                case -2065696511:
                    if (titleSrn.equals("_118_setting_title_49")) {
                        c = Typography.amp;
                        break;
                    }
                    break;
                case -2065696489:
                    if (titleSrn.equals("_118_setting_title_50")) {
                        c = '\'';
                        break;
                    }
                    break;
                case -2065696488:
                    if (titleSrn.equals("_118_setting_title_51")) {
                        c = '(';
                        break;
                    }
                    break;
                case -2065696487:
                    if (titleSrn.equals("_118_setting_title_52")) {
                        c = ')';
                        break;
                    }
                    break;
                case -2065696486:
                    if (titleSrn.equals("_118_setting_title_53")) {
                        c = '*';
                        break;
                    }
                    break;
                case -2065696485:
                    if (titleSrn.equals("_118_setting_title_54")) {
                        c = '+';
                        break;
                    }
                    break;
                case -2065696484:
                    if (titleSrn.equals("_118_setting_title_55")) {
                        c = ',';
                        break;
                    }
                    break;
                case -2065696483:
                    if (titleSrn.equals("_118_setting_title_56")) {
                        c = '-';
                        break;
                    }
                    break;
                case -2065696480:
                    if (titleSrn.equals("_118_setting_title_59")) {
                        c = '.';
                        break;
                    }
                    break;
                case -2065696458:
                    if (titleSrn.equals("_118_setting_title_60")) {
                        c = '/';
                        break;
                    }
                    break;
                case -2065696457:
                    if (titleSrn.equals("_118_setting_title_61")) {
                        c = '0';
                        break;
                    }
                    break;
                case -2065696456:
                    if (titleSrn.equals("_118_setting_title_62")) {
                        c = '1';
                        break;
                    }
                    break;
                case -2065696455:
                    if (titleSrn.equals("_118_setting_title_63")) {
                        c = '2';
                        break;
                    }
                    break;
                case -2065696454:
                    if (titleSrn.equals("_118_setting_title_64")) {
                        c = '3';
                        break;
                    }
                    break;
                case -2065696453:
                    if (titleSrn.equals("_118_setting_title_65")) {
                        c = '4';
                        break;
                    }
                    break;
                case -2065696452:
                    if (titleSrn.equals("_118_setting_title_66")) {
                        c = '5';
                        break;
                    }
                    break;
                case -2065696451:
                    if (titleSrn.equals("_118_setting_title_67")) {
                        c = '6';
                        break;
                    }
                    break;
                case -2065696450:
                    if (titleSrn.equals("_118_setting_title_68")) {
                        c = '7';
                        break;
                    }
                    break;
                case -2065696361:
                    if (titleSrn.equals("_118_setting_title_94")) {
                        c = '8';
                        break;
                    }
                    break;
                case -2065696360:
                    if (titleSrn.equals("_118_setting_title_95")) {
                        c = '9';
                        break;
                    }
                    break;
                case -2065696359:
                    if (titleSrn.equals("_118_setting_title_96")) {
                        c = ':';
                        break;
                    }
                    break;
                case -2065696357:
                    if (titleSrn.equals("_118_setting_title_98")) {
                        c = ';';
                        break;
                    }
                    break;
                case 387914494:
                    if (titleSrn.equals("_118_setting_title_109")) {
                        c = Typography.less;
                        break;
                    }
                    break;
                case 387914516:
                    if (titleSrn.equals("_118_setting_title_110")) {
                        c = '=';
                        break;
                    }
                    break;
                case 387914517:
                    if (titleSrn.equals("_118_setting_title_111")) {
                        c = Typography.greater;
                        break;
                    }
                    break;
                case 387914518:
                    if (titleSrn.equals("_118_setting_title_112")) {
                        c = '?';
                        break;
                    }
                    break;
                case 387914520:
                    if (titleSrn.equals("_118_setting_title_114")) {
                        c = '@';
                        break;
                    }
                    break;
                case 387914521:
                    if (titleSrn.equals("_118_setting_title_115")) {
                        c = 'A';
                        break;
                    }
                    break;
                case 387914522:
                    if (titleSrn.equals("_118_setting_title_116")) {
                        c = 'B';
                        break;
                    }
                    break;
                case 387914523:
                    if (titleSrn.equals("_118_setting_title_117")) {
                        c = 'C';
                        break;
                    }
                    break;
                case 1180290613:
                    if (titleSrn.equals("_118_setting_title_1")) {
                        c = 'D';
                        break;
                    }
                    break;
                case 1180290614:
                    if (titleSrn.equals("_118_setting_title_2")) {
                        c = 'E';
                        break;
                    }
                    break;
                case 1180290615:
                    if (titleSrn.equals("_118_setting_title_3")) {
                        c = 'F';
                        break;
                    }
                    break;
                case 1180290616:
                    if (titleSrn.equals("_118_setting_title_4")) {
                        c = 'G';
                        break;
                    }
                    break;
                case 1180290617:
                    if (titleSrn.equals("_118_setting_title_5")) {
                        c = 'H';
                        break;
                    }
                    break;
                case 1180290618:
                    if (titleSrn.equals("_118_setting_title_6")) {
                        c = 'I';
                        break;
                    }
                    break;
                case 1180290619:
                    if (titleSrn.equals("_118_setting_title_7")) {
                        c = 'J';
                        break;
                    }
                    break;
                case 1180290620:
                    if (titleSrn.equals("_118_setting_title_8")) {
                        c = 'K';
                        break;
                    }
                    break;
                case 1180290621:
                    if (titleSrn.equals("_118_setting_title_9")) {
                        c = 'L';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 19, (byte) (i3 + 1)});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 20, (byte) (i3 + 1)});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 21, (byte) (i3 + 1)});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -121, (byte) (i3 + 1)});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 34, (byte) (i3 + 1)});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 35, (byte) (i3 + 1)});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 36, (byte) (i3 + 1)});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 37, (byte) (i3 + 1)});
                    break;
                case '\b':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 38, (byte) (i3 + 1)});
                    break;
                case '\t':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 49, (byte) (i3 + 1)});
                    break;
                case '\n':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 50, UiMgr.this.resolvedata2(i3)});
                    break;
                case 11:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -119, (byte) (i3 + 1)});
                    break;
                case '\f':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -120, (byte) (i3 + 1)});
                    break;
                case '\r':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -95, UiMgr.this.resolvedata2(i3)});
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 65, (byte) (i3 + 1)});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 66, (byte) (i3 + 1)});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 67, (byte) (i3 + 1)});
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 68, (byte) (i3 + 1)});
                    break;
                case 18:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 69, (byte) (i3 + 1)});
                    break;
                case 19:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 70, (byte) i3});
                    break;
                case 20:
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, 71, (byte) (i3 + 1)});
                    break;
                case 21:
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, 81, (byte) (i3 + 1)});
                    break;
                case 22:
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, -125, (byte) (i3 + 1)});
                    break;
                case 23:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -127, (byte) i3});
                    break;
                case 24:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -126, (byte) i3});
                    break;
                case 25:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 8, (byte) i3});
                    break;
                case 26:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 10, (byte) (i3 + 1)});
                    break;
                case 27:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 11, (byte) (i3 + 1)});
                    break;
                case 28:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
                case 29:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 16, (byte) (i3 + 1)});
                    break;
                case 30:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 22, (byte) (i3 + 1)});
                    break;
                case 31:
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 32, (byte) (i3 + 1)});
                    break;
                case ' ':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 40, (byte) (i3 + 1)});
                    break;
                case '!':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 84, (byte) (i3 + 1)});
                    break;
                case '\"':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 18, resolvedata(i3)});
                    break;
                case '#':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    break;
                case '$':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -124, (byte) (i3 + 1)});
                    break;
                case '%':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -123, (byte) i3});
                    break;
                case '&':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 96, (byte) i3});
                    break;
                case '\'':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 113, (byte) i3});
                    break;
                case '(':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 114, (byte) i3});
                    break;
                case ')':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 81, (byte) (i3 + 1)});
                    break;
                case '*':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -112, (byte) (i3 + 1)});
                    break;
                case '+':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 82, (byte) i3});
                    break;
                case ',':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 81, (byte) (i3 + 1)});
                    break;
                case '-':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -96, (byte) i3});
                    break;
                case '.':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 115, (byte) i3});
                    break;
                case '/':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 116, (byte) i3});
                    break;
                case '0':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 97, (byte) i3});
                    break;
                case '1':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -107, (byte) (i3 + 1)});
                    break;
                case '2':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 41, (byte) (i3 + 1)});
                    break;
                case '3':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 117, (byte) (i3 + 1)});
                    break;
                case '4':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -122, (byte) (i3 + 1)});
                    break;
                case '5':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 98, (byte) i3});
                    break;
                case '6':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 99, (byte) i3});
                    break;
                case '7':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 83, (byte) (i3 + 1)});
                    break;
                case '8':
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, 10, (byte) i3});
                    break;
                case '9':
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, 11, (byte) i3});
                    break;
                case ':':
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, 9, (byte) i3});
                    break;
                case ';':
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, 7, (byte) i3});
                    break;
                case '<':
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext).updateCarModel(UiMgr.this.mContext, i3);
                    CanbusMsgSender.sendMsg(new byte[]{22, -18, 96, (byte) i3});
                    break;
                case '=':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -106, (byte) (i3 + 1)});
                    break;
                case '>':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -80, (byte) i3});
                    break;
                case '?':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -74, (byte) i3});
                    break;
                case '@':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -78, (byte) i3});
                    break;
                case 'A':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -77, (byte) i3});
                    break;
                case 'B':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -76, (byte) i3});
                    break;
                case 'C':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, -75, (byte) i3});
                    break;
                case 'D':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 1, (byte) i3});
                    break;
                case 'E':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 2, (byte) i3});
                    break;
                case 'F':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 3, (byte) i3});
                    break;
                case 'G':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 4, (byte) (i3 + 1)});
                    break;
                case 'H':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 5, (byte) i3});
                    break;
                case 'I':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 6, (byte) (i3 + 1)});
                    break;
                case 'J':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 7, (byte) (i3 + 1)});
                    break;
                case 'K':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 14, (byte) (i3 + 1)});
                    break;
                case 'L':
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 17, resolvedata(i3)});
                    break;
            }
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.17
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            switch (titleSrn) {
                case "_118_setting_title_57":
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, -109, (byte) i3});
                    break;
                case "_118_setting_title_58":
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, -110, (byte) i3});
                    break;
                case "_118_setting_title_73":
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 87, (byte) i3});
                    break;
                case "_118_setting_title_97":
                    CanbusMsgSender.sendMsg(new byte[]{22, -109, 8, (byte) i3});
                    break;
            }
        }
    };
    SetTimeView.TimeResultListener timeResultListener = new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.18
        @Override // com.hzbhd.canbus.car._118.SetTimeView.TimeResultListener
        public void result(int i, int i2, int i3, int i4, int i5) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.WorkStartMinuteAndHour = uiMgr.resolvedata4(i4, i5);
            UiMgr.this.sendtimemsg();
        }
    };
    SetTimeView.TimeResultListener timeResultListener1 = new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.19
        @Override // com.hzbhd.canbus.car._118.SetTimeView.TimeResultListener
        public void result(int i, int i2, int i3, int i4, int i5) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.WorkEndMinuteAndHour = uiMgr.resolvedata4(i4, i5);
            UiMgr.this.sendtimemsg();
        }
    };
    SetTimeView.TimeResultListener timeResultListener2 = new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.20
        @Override // com.hzbhd.canbus.car._118.SetTimeView.TimeResultListener
        public void result(int i, int i2, int i3, int i4, int i5) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.WeekStartMinuteAndHour = uiMgr.resolvedata4(i4, i5);
            UiMgr.this.sendtimemsg();
        }
    };
    SetTimeView.TimeResultListener timeResultListener3 = new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.21
        @Override // com.hzbhd.canbus.car._118.SetTimeView.TimeResultListener
        public void result(int i, int i2, int i3, int i4, int i5) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.WeekEndMinuteAndHour = uiMgr.resolvedata4(i4, i5);
            UiMgr.this.sendtimemsg();
        }
    };
    OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.22
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) throws NumberFormatException {
            String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            switch (titleSrn) {
                case "_118_setting_Title023":
                    SetTimeView setTimeView = new SetTimeView();
                    UiMgr uiMgr = UiMgr.this;
                    setTimeView.showDialog(uiMgr.getMsgMgr(uiMgr.mContext).getCurrentActivity(), UiMgr.this.mContext.getResources().getString(R.string._118_setting_Title023), false, false, false, true, true, UiMgr.this.timeResultListener);
                    break;
                case "_118_setting_Title056":
                    SetTimeView setTimeView2 = new SetTimeView();
                    UiMgr uiMgr2 = UiMgr.this;
                    setTimeView2.showDialog(uiMgr2.getMsgMgr(uiMgr2.mContext).getCurrentActivity(), UiMgr.this.mContext.getResources().getString(R.string._118_setting_Title023), false, false, false, true, true, UiMgr.this.timeResultListener1);
                    break;
                case "_118_setting_Title078":
                    SetTimeView setTimeView3 = new SetTimeView();
                    UiMgr uiMgr3 = UiMgr.this;
                    setTimeView3.showDialog(uiMgr3.getMsgMgr(uiMgr3.mContext).getCurrentActivity(), UiMgr.this.mContext.getResources().getString(R.string._118_setting_Title023), false, false, false, true, true, UiMgr.this.timeResultListener2);
                    break;
                case "_118_setting_Title1011":
                    SetTimeView setTimeView4 = new SetTimeView();
                    UiMgr uiMgr4 = UiMgr.this;
                    setTimeView4.showDialog(uiMgr4.getMsgMgr(uiMgr4.mContext).getCurrentActivity(), UiMgr.this.mContext.getResources().getString(R.string._118_setting_Title023), false, false, false, true, true, UiMgr.this.timeResultListener3);
                    break;
                case "_118_setting_Title01":
                    UiMgr uiMgr5 = UiMgr.this;
                    if (uiMgr5.getMsgMgr(uiMgr5.mContext).PlanStatus != 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 0, 0, 0, 0, 0});
                        break;
                    } else {
                        UiMgr.this.sendTimeData();
                        break;
                    }
                case "_118_setting_Title04":
                    UiMgr uiMgr6 = UiMgr.this;
                    if (uiMgr6.getMsgMgr(uiMgr6.mContext).WorkUtilFull == 1) {
                        UiMgr.this.UtilFullWork = 0;
                    } else {
                        UiMgr.this.UtilFullWork = 1;
                    }
                    UiMgr uiMgr7 = UiMgr.this;
                    uiMgr7.DATA3 = uiMgr7.getDecimalFrom8Bit(uiMgr7.UtilFullWork, 0, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendTimeData();
                    break;
                case "_118_setting_Title09":
                    UiMgr uiMgr8 = UiMgr.this;
                    if (uiMgr8.getMsgMgr(uiMgr8.mContext).WeekendUtilFull == 0) {
                        UiMgr.this.UtilFUllWeekend = 1;
                    } else {
                        UiMgr.this.UtilFUllWeekend = 0;
                    }
                    UiMgr uiMgr9 = UiMgr.this;
                    uiMgr9.DATA4 = uiMgr9.getDecimalFrom8Bit(uiMgr9.UtilFUllWeekend, 0, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendTimeData();
                    break;
                case "_118_setting_Title19":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 8, 1});
                    break;
                case "_118_setting_Title20":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 7, 1});
                    break;
                case "_118_setting_Title21":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 6, 1});
                    break;
                case "_118_setting_Title22":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 5, 1});
                    break;
                case "_118_setting_Title23":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 4, 1});
                    break;
                case "_118_setting_Title24":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 3, 1});
                    break;
                case "_118_setting_Title25":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 2, 1});
                    break;
                case "_118_setting_Title26":
                    CanbusMsgSender.sendMsg(new byte[]{22, -103, 1, 1});
                    break;
                case "compass_calibration_status":
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 86, (byte) (i2 - 2)});
                    break;
            }
        }
    };
    OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.23
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.mCurrentCanDifferentId == 2 || UiMgr.this.mCurrentCanDifferentId == 9 || UiMgr.this.mCurrentCanDifferentId == 10 || UiMgr.this.mCurrentCanDifferentId == 11 || UiMgr.this.mCurrentCanDifferentId == 12 || UiMgr.this.mCurrentCanDifferentId == 14) {
                return;
            }
            setFrontSearClick();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.mCurrentCanDifferentId == 2 || UiMgr.this.mCurrentCanDifferentId == 9 || UiMgr.this.mCurrentCanDifferentId == 10 || UiMgr.this.mCurrentCanDifferentId == 11 || UiMgr.this.mCurrentCanDifferentId == 12 || UiMgr.this.mCurrentCanDifferentId == 14) {
                return;
            }
            setFrontSearClick();
        }

        private void setFrontSearClick() {
            if (GeneralAirData.front_left_blow_head && !GeneralAirData.front_right_blow_foot && !GeneralAirData.front_right_blow_window) {
                UiMgr.this.sendAirCommand(25);
                return;
            }
            if (GeneralAirData.front_left_blow_head && GeneralAirData.front_right_blow_foot && !GeneralAirData.front_right_blow_window) {
                UiMgr.this.sendAirCommand(26);
            } else if (GeneralAirData.front_left_blow_head || !GeneralAirData.front_right_blow_foot || GeneralAirData.front_right_blow_window) {
                UiMgr.this.sendAirCommand(24);
            } else {
                UiMgr.this.sendAirCommand(27);
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.24
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirCommand(28);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirCommand(29);
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.25
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(31);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(30);
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.26
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(33);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(32);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.27
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.28
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.29
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.30
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    int mCurrentCanDifferentId = getCurrentCarId();

    /* JADX INFO: Access modifiers changed from: private */
    public byte resolvedata2(int i) {
        if (i == 0) {
            return (byte) 0;
        }
        if (i == 1) {
            return (byte) 3;
        }
        return i == 2 ? (byte) 20 : (byte) 40;
    }

    private int resolvedata3(boolean z) {
        return z ? 1 : 0;
    }

    private int resolvedata5(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 2 : 3;
    }

    private int swapVal(int i) {
        return i == 0 ? 2 : 1;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.mCurrentCanDifferentId;
        if (i == 9 || i == 10 || i == 12 || i == 14) {
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_9", "_118_setting_title_16", "_118_setting_title_54"});
        }
        int i2 = this.mCurrentCanDifferentId;
        if (i2 == 2 || i2 == 9 || i2 == 10 || i2 == 12 || i2 == 14) {
            removeMainPrjBtnByName(this.mContext, MainAction.AMPLIFIER);
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_title_93"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_16"});
        }
        int i3 = this.mCurrentCanDifferentId;
        if (i3 == 8 || i3 == 9 || i3 == 10 || i3 == 12 || i3 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_1"});
        }
        int i4 = this.mCurrentCanDifferentId;
        if (i4 != 0 && i4 != 1 && i4 != 4 && i4 != 5 && i4 != 6 && i4 != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_2"});
        }
        int i5 = this.mCurrentCanDifferentId;
        if (i5 == 8 || i5 == 9 || i5 == 10 || i5 == 12 || i5 == 7 || i5 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_3"});
        }
        int i6 = this.mCurrentCanDifferentId;
        if (i6 == 9 || i6 == 10 || i6 == 12 || i6 == 13 || i6 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_5"});
        }
        int i7 = this.mCurrentCanDifferentId;
        if (i7 != 0 && i7 != 1 && i7 != 4 && i7 != 5 && i7 != 6 && i7 != 7 && i7 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_7"});
        }
        int i8 = this.mCurrentCanDifferentId;
        if (i8 != 0 && i8 != 1 && i8 != 4 && i8 != 6 && i8 != 8 && i8 != 11 && i8 != 13) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_36"});
        }
        int i9 = this.mCurrentCanDifferentId;
        if (i9 != 0 && i9 != 1 && i9 != 4) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_37", "_118_setting_title_39"});
        }
        int i10 = this.mCurrentCanDifferentId;
        if (i10 != 0 && i10 != 1 && i10 != 3 && i10 != 4) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_38"});
        }
        int i11 = this.mCurrentCanDifferentId;
        if (i11 != 0 && i11 != 1 && i11 != 4 && i11 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_46"});
        }
        int i12 = this.mCurrentCanDifferentId;
        if (i12 != 0 && i12 != 1 && i12 != 4 && i12 != 6 && i12 != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_8"});
        }
        int i13 = this.mCurrentCanDifferentId;
        if (i13 != 0 && i13 != 1) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_40"});
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_title_91"});
        }
        int i14 = this.mCurrentCanDifferentId;
        if (i14 != 3 && i14 != 4 && i14 != 5 && i14 != 6 && i14 != 7 && i14 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_61"});
        }
        int i15 = this.mCurrentCanDifferentId;
        if (i15 != 4 && i15 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_34", "_118_setting_title_35", "_118_setting_title_33"});
        }
        int i16 = this.mCurrentCanDifferentId;
        if (i16 != 4 && i16 != 5 && i16 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_47"});
        }
        int i17 = this.mCurrentCanDifferentId;
        if (i17 != 0 && i17 != 1 && i17 != 4 && i17 != 5 && i17 != 6 && i17 != 7 && i17 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_45"});
        }
        int i18 = this.mCurrentCanDifferentId;
        if (i18 != 0 && i18 != 1 && i18 != 4 && i18 != 5 && i18 != 6 && i18 != 7 && i18 != 8 && i18 != 11 && i18 != 13) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_10", "_118_setting_title_12"});
        }
        int i19 = this.mCurrentCanDifferentId;
        if (i19 != 0 && i19 != 1 && i19 != 2 && i19 != 3 && i19 != 4 && i19 != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_11"});
        }
        int i20 = this.mCurrentCanDifferentId;
        if (i20 != 0 && i20 != 1 && i20 != 4 && i20 != 5) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_41"});
        }
        if (this.mCurrentCanDifferentId != 4) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_13", "_118_setting_title_22"});
        }
        int i21 = this.mCurrentCanDifferentId;
        if (i21 != 2 && i21 != 3) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_53", "_118_setting_title_58", "_118_setting_title_57"});
        }
        if (this.mCurrentCanDifferentId != 3) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_62"});
        }
        if (this.mCurrentCanDifferentId != 12) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_110"});
        }
        int i22 = this.mCurrentCanDifferentId;
        if (i22 != 0 && i22 != 1 && i22 != 2 && i22 != 3 && i22 != 5 && i22 != 6) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_42"});
        }
        int i23 = this.mCurrentCanDifferentId;
        if (i23 != 5 && i23 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_15"});
        }
        int i24 = this.mCurrentCanDifferentId;
        if (i24 == 6 || i24 == 9 || i24 == 10 || i24 == 12 || i24 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_17"});
        }
        int i25 = this.mCurrentCanDifferentId;
        if (i25 != 4 && i25 != 5) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_18", "_118_setting_title_19", "_118_setting_title_4", "_118_setting_title_65"});
        }
        int i26 = this.mCurrentCanDifferentId;
        if (i26 != 0 && i26 != 1 && i26 != 5 && i26 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_43"});
        }
        int i27 = this.mCurrentCanDifferentId;
        if (i27 != 3 && i27 != 4 && i27 != 7 && i27 != 8 && i27 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_63"});
        }
        int i28 = this.mCurrentCanDifferentId;
        if (i28 != 2 && i28 != 3 && i28 != 4 && i28 != 5 && i28 != 7 && i28 != 8 && i28 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_56"});
        }
        int i29 = this.mCurrentCanDifferentId;
        if (i29 != 0 && i29 != 1 && i29 != 5 && i29 != 6 && i29 != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_20"});
        }
        if (this.mCurrentCanDifferentId != 6) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_23"});
        }
        if (this.mCurrentCanDifferentId != 5) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_big_title_5"});
        }
        int i30 = this.mCurrentCanDifferentId;
        if (i30 == 5 || i30 == 9 || i30 == 10 || i30 == 12 || i30 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_68"});
        }
        int i31 = this.mCurrentCanDifferentId;
        if (i31 != 0 && i31 != 1 && i31 != 4 && i31 != 5 && i31 != 8 && i31 != 11 && i31 != 13) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_44"});
        }
        int i32 = this.mCurrentCanDifferentId;
        if (i32 != 0 && i32 != 1 && i32 != 2) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_71"});
        }
        int i33 = this.mCurrentCanDifferentId;
        if (i33 != 4 && i33 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_48"});
        }
        int i34 = this.mCurrentCanDifferentId;
        if (i34 != 3 && i34 != 4 && i34 != 8 && i34 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_66"});
        }
        int i35 = this.mCurrentCanDifferentId;
        if (i35 != 3 && i35 != 8 && i35 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_67"});
        }
        int i36 = this.mCurrentCanDifferentId;
        if (i36 == 0 || i36 == 1 || i36 == 8 || i36 == 9 || i36 == 10 || i36 == 12 || i36 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_50", "_118_setting_title_51"});
        }
        int i37 = this.mCurrentCanDifferentId;
        if (i37 == 0 || i37 == 1 || i37 == 2 || i37 == 8 || i37 == 9 || i37 == 10 || i37 == 12 || i37 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_59", "_118_setting_title_60"});
        }
        int i38 = this.mCurrentCanDifferentId;
        if (i38 == 0 || i38 == 1 || i38 == 2 || i38 == 3 || i38 == 8 || i38 == 9 || i38 == 10 || i38 == 12 || i38 == 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_64"});
        }
        int i39 = this.mCurrentCanDifferentId;
        if (i39 != 0 && i39 != 1 && i39 != 4 && i39 != 5 && i39 != 8 && i39 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_31"});
        }
        int i40 = this.mCurrentCanDifferentId;
        if (i40 != 0 && i40 != 1 && i40 != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_6"});
        }
        int i41 = this.mCurrentCanDifferentId;
        if (i41 != 4 && i41 != 8 && i41 != 11) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_21"});
        }
        if (this.mCurrentCanDifferentId != 7) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_big_title_10"});
        }
        if (this.mCurrentCanDifferentId != 11) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_DriveInfo_HeadTitle_02"});
        }
        removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_52", "_118_setting_title_55"});
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
        this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        this.settingPageUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 7});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 11});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 4});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 7});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 9});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 10});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 11});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 16});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 17});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 25});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 34});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 35});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 48});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 49});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 50});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 51});
            }
        });
        this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_118_setting_title_71")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 85, 1});
                } else if (titleSrn.equals("_118_setting_Title14")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1});
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        int i = this.mCurrentCanDifferentId;
        if (i != 4 && i != 13) {
            airUiSet.setHaveRearArea(false);
        }
        int i2 = this.mCurrentCanDifferentId;
        if (i2 != 5 && i2 != 6 && i2 != 7) {
            removeFrontAirButtonByName(this.mContext, AirBtnAction.LEFT_SET_SEAT_HEAT);
            removeFrontAirButtonByName(this.mContext, AirBtnAction.RIGHT_SET_SEAT_HEAT);
        }
        int i3 = this.mCurrentCanDifferentId;
        if (i3 == 2 || i3 == 9 || i3 == 10 || i3 == 11 || i3 == 12 || i3 == 14) {
            airUiSet.getFrontArea().setAllBtnCanClick(false);
            airUiSet.getFrontArea().setCanSetWindSpeed(false);
            airUiSet.getFrontArea().setCanSetLeftTemp(false);
            airUiSet.getFrontArea().setCanSetRightTemp(false);
        }
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListener_rearTop, null, null, this.onAirBtnClickListener_rearBottom});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onAirTemperatureUpDownClickListener_rear, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener_rear);
        airUiSet.getRearArea().setOnAirSeatClickListener(this.onAirSeatClickListener_rear);
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 5});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 6});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 21});
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mOnOriginalSongItemClickListener);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public void onStatusChange(int i4) {
            }
        });
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._118.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 10});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 50});
                CanbusMsgSender.sendMsg(new byte[]{22, -15, 51});
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_118-UiMgr, reason: not valid java name */
    /* synthetic */ void m108lambda$new$0$comhzbhdcanbuscar_118UiMgr(int i) {
        if (i == 0) {
            sendAirConditionerData(1);
        } else {
            if (i != 1) {
                return;
            }
            sendAirConditionerData(3);
        }
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_118-UiMgr, reason: not valid java name */
    /* synthetic */ void m109lambda$new$1$comhzbhdcanbuscar_118UiMgr(int i) {
        if (i == 0) {
            sendAirConditionerData(4);
        } else {
            if (i != 1) {
                return;
            }
            sendAirConditionerData(2);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._118.UiMgr$31, reason: invalid class name */
    static /* synthetic */ class AnonymousClass31 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendtimemsg() {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte) this.WorkStartMinuteAndHour, (byte) this.WorkEndMinuteAndHour, (byte) this.WeekStartMinuteAndHour, (byte) this.WeekEndMinuteAndHour});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int resolvedata4(int r11, int r12) {
        /*
            r10 = this;
            r1 = 15
            r2 = 1
            r3 = 0
            if (r12 < 0) goto L8
            if (r12 < r1) goto Lc
        L8:
            r4 = 60
            if (r12 != r4) goto Le
        Lc:
            r2 = r3
            goto L24
        Le:
            r5 = 30
            if (r12 < r1) goto L18
            if (r12 >= r5) goto L18
            r9 = r3
            r3 = r2
            r2 = r9
            goto L24
        L18:
            r1 = 45
            if (r12 < r5) goto L1f
            if (r12 >= r1) goto L1f
            goto L24
        L1f:
            if (r12 < r1) goto Lc
            if (r12 >= r4) goto Lc
            r3 = r2
        L24:
            boolean r0 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit0(r11)
            int r8 = r10.resolvedata3(r0)
            boolean r0 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit1(r11)
            int r7 = r10.resolvedata3(r0)
            boolean r0 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit2(r11)
            int r6 = r10.resolvedata3(r0)
            boolean r0 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit3(r11)
            int r5 = r10.resolvedata3(r0)
            boolean r0 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit4(r11)
            int r4 = r10.resolvedata3(r0)
            r1 = 0
            r0 = r10
            int r0 = r0.getDecimalFrom8Bit(r1, r2, r3, r4, r5, r6, r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._118.UiMgr.resolvedata4(int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTimeData() {
        int i = this.UtilFUllWeekend;
        if (i == 0 && this.UtilFullWork == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte) this.WorkStartMinuteAndHour, (byte) this.WorkEndMinuteAndHour, (byte) this.WeekStartMinuteAndHour, (byte) this.WeekEndMinuteAndHour});
            return;
        }
        if (i == 1 && this.UtilFullWork == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte) this.WorkStartMinuteAndHour, (byte) this.WorkEndMinuteAndHour, (byte) this.WeekStartMinuteAndHour, (byte) this.DATA4});
        } else if (i == 0 && this.UtilFullWork == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte) this.WorkStartMinuteAndHour, (byte) this.DATA3, (byte) this.WeekStartMinuteAndHour, (byte) this.WeekEndMinuteAndHour});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte) this.WorkStartMinuteAndHour, (byte) this.DATA3, (byte) this.WeekStartMinuteAndHour, (byte) this.DATA4});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "right_set_seat_cold":
                sendAirCommand(51);
                break;
            case "right_set_seat_heat":
                sendAirCommand(50);
                break;
            case "steering_wheel_heating":
                sendAirCommand(52);
                break;
            case "front_defog":
                sendAirCommand(21);
                break;
            case "ac_max":
                sendAirCommand(18);
                break;
            case "rear_defog":
                sendAirCommand(22);
                break;
            case "left_set_seat_cold":
                sendAirCommand(49);
                break;
            case "left_set_seat_heat":
                sendAirCommand(48);
                break;
            case "ac":
                sendAirCommand(17);
                break;
            case "auto":
                sendAirCommand(20);
                break;
            case "sync":
                sendAirCommand(23);
                break;
            case "in_out_auto_cycle":
                sendAirCommand(19);
                break;
            case "power":
                if (GeneralAirData.power) {
                    sendAirCommand(16);
                    break;
                } else {
                    sendAirCommand(9);
                    break;
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(final int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte) i, 1});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._118.UiMgr$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte) i, 0});
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirConditionerData(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -106, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -106, b, 0});
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
    public int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
