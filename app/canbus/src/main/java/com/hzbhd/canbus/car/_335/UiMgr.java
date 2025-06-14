package com.hzbhd.canbus.car._335;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    public static int UsbPlayPauseState;
    Context mContext;
    MsgMgr mMsgMgr;
    DecimalFormat noDecimal = new DecimalFormat("00000");
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_335_setting_data_speed")) {
                if (i2 == 1) {
                    UiMgr.this.sendSpeedInfo0x8A(i3);
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    UiMgr.this.sendSpeedInfo0x89(i3);
                }
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_330_amplifier_info")) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 3) {
                            if (i2 == 5) {
                                if (i3 == 0) {
                                    UiMgr.this.sendAmplifierInfo0x84(12, 0);
                                } else if (i3 == 1) {
                                    UiMgr.this.sendAmplifierInfo0x84(12, 1);
                                } else if (i3 == 2) {
                                    UiMgr.this.sendAmplifierInfo0x84(12, 2);
                                } else if (i3 == 3) {
                                    UiMgr.this.sendAmplifierInfo0x84(12, 3);
                                }
                            }
                        } else if (i3 == 0) {
                            UiMgr.this.sendAmplifierInfo0x84(3, 1);
                        } else {
                            UiMgr.this.sendAmplifierInfo0x84(3, 8);
                        }
                    } else if (i3 == 0) {
                        UiMgr.this.sendAmplifierInfo0x84(10, 0);
                    } else {
                        UiMgr.this.sendAmplifierInfo0x84(10, 1);
                    }
                } else if (i3 == 0) {
                    UiMgr.this.sendAmplifierInfo0x84(8, 0);
                } else {
                    UiMgr.this.sendAmplifierInfo0x84(8, 1);
                }
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getSettingLeftIndexes(uiMgr2.mContext, "_330_Mode_selection") && i2 == 0) {
                if (i3 == 0) {
                    UiMgr.this.sendWheelKeyControlInfo0xE3(0);
                } else {
                    UiMgr.this.sendWheelKeyControlInfo0xE3(1);
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_335_setting_car_data0") && i2 == 0) {
                UiMgr.this.sendCarModelInfo0xE2(i3);
            }
        }
    };
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_335_cd_data") == i) {
                if (i2 == 0) {
                    UiMgr.this.sendMediaInfo0x84(17, 0);
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    UiMgr.this.sendMediaInfo0x84(16, 0);
                }
            }
        }
    };
    private int fastForward = 0;
    private int fastBack = 0;
    private int RADIO_PLAY_PAUSE = 0;
    OnOriginalBottomBtnClickListener onOriginalCarDeviceBackPressedListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.getMsgMgr(uiMgr.mContext);
            String str = MsgMgr.mediaTag;
            UiMgr uiMgr2 = UiMgr.this;
            Objects.requireNonNull(uiMgr2.getMsgMgr(uiMgr2.mContext));
            if (str.equals("USB")) {
                if (i == 0) {
                    UiMgr.this.sendUsbInfo0xC5(3);
                } else if (i != 1) {
                    if (i == 2) {
                        UiMgr.this.sendUsbInfo0xC5(4);
                    }
                } else if (UiMgr.UsbPlayPauseState == 0) {
                    UiMgr.UsbPlayPauseState = 1;
                    UiMgr.this.sendUsbInfo0xC5(1);
                } else {
                    UiMgr.UsbPlayPauseState = 0;
                    UiMgr.this.sendUsbInfo0xC5(2);
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            uiMgr3.getMsgMgr(uiMgr3.mContext);
            String str2 = MsgMgr.mediaTag;
            UiMgr uiMgr4 = UiMgr.this;
            Objects.requireNonNull(uiMgr4.getMsgMgr(uiMgr4.mContext));
            if (str2.equals("CD/DVD")) {
                if (i == 0) {
                    UiMgr.this.sendMediaInfo0x84(22, 0);
                } else if (i == 1) {
                    UiMgr.this.sendMediaInfo0x84(20, 0);
                } else if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            UiMgr.this.sendMediaInfo0x84(19, 0);
                        } else if (i == 5) {
                            UiMgr.this.sendMediaInfo0x84(21, 0);
                        }
                    } else if (UiMgr.this.fastForward == 0) {
                        UiMgr.this.fastForward = 1;
                        UiMgr.this.sendMediaInfo0x84(24, 1);
                    } else {
                        UiMgr.this.fastForward = 0;
                        UiMgr.this.sendMediaInfo0x84(24, 0);
                    }
                } else if (UiMgr.this.fastBack == 0) {
                    UiMgr.this.fastBack = 1;
                    UiMgr.this.sendMediaInfo0x84(25, 1);
                } else {
                    UiMgr.this.fastBack = 0;
                    UiMgr.this.sendMediaInfo0x84(25, 0);
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            uiMgr5.getMsgMgr(uiMgr5.mContext);
            String str3 = MsgMgr.mediaTag;
            UiMgr uiMgr6 = UiMgr.this;
            Objects.requireNonNull(uiMgr6.getMsgMgr(uiMgr6.mContext));
            if (str3.equals("RADIO0")) {
                if (i == 0) {
                    UiMgr.this.sendMediaInfo0x84(38, 0);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.sendMediaInfo0x84(35, 0);
                    return;
                }
                if (i != 2) {
                    if (i == 3) {
                        UiMgr.this.sendMediaInfo0x84(34, 0);
                        return;
                    } else {
                        if (i != 4) {
                            return;
                        }
                        UiMgr.this.sendMediaInfo0x84(37, 0);
                        return;
                    }
                }
                if (UiMgr.this.RADIO_PLAY_PAUSE == 0) {
                    UiMgr.this.RADIO_PLAY_PAUSE = 1;
                    UiMgr.this.sendMediaInfo0x84(36, 1);
                } else {
                    UiMgr.this.RADIO_PLAY_PAUSE = 0;
                    UiMgr.this.sendMediaInfo0x84(36, 0);
                }
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData0xE0(23);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirData0xE0(25);
            } else if (i == 2) {
                UiMgr.this.sendAirData0xE0(21);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAirData0xE0(20);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData0xE0(16);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirData0xE0(1);
            }
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.sendAirData0xE0(36);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.sendAirData0xE0(36);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData0xE0(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData0xE0(2);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData0xE0(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData0xE0(4);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData0xE0(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData0xE0(10);
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass15.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                UiMgr.this.sendAmplifierInfo0x84(1, (-i) + 7);
            } else {
                if (i2 != 2) {
                    return;
                }
                UiMgr.this.sendAmplifierInfo0x84(2, i + 7);
            }
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._335.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass15.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                UiMgr.this.sendAmplifierInfo0x84(7, i);
                return;
            }
            if (i2 == 2) {
                UiMgr.this.sendAmplifierInfo0x84(5, i + 2);
            } else if (i2 == 3) {
                UiMgr.this.sendAmplifierInfo0x84(6, i + 2);
            } else {
                if (i2 != 4) {
                    return;
                }
                UiMgr.this.sendAmplifierInfo0x84(4, i + 2);
            }
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    private int getCarModelData(int i) {
        if (i == 0) {
            return 32;
        }
        if (i == 1) {
            return 33;
        }
        if (i == 2) {
            return 48;
        }
        if (i != 3) {
            return i != 4 ? 81 : 80;
        }
        return 49;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        getOriginalCarDevicePageUiSet(context).setOnOriginalBottomBtnClickListeners(this.onOriginalCarDeviceBackPressedListener);
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
    }

    private void initUi() {
        if (this.eachId != 2) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_335_setting_data_speed"});
        }
        if (this.eachId == 2) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_330_Mode_selection"});
        }
    }

    /* renamed from: com.hzbhd.canbus.car._335.UiMgr$15, reason: invalid class name */
    static /* synthetic */ class AnonymousClass15 {
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
    public void sendSpeedInfo0x8A(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) Integer.parseInt(this.noDecimal.format(i * 0.01d))});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSpeedInfo0x89(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) Integer.parseInt(this.noDecimal.format(i * 0.01d))});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUsbInfo0xC5(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte) i, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirData0xE0(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierInfo0x84(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendWheelKeyControlInfo0xE3(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -29, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMediaInfo0x84(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCarModelInfo0xE2(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte) getCarModelData(i)});
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
