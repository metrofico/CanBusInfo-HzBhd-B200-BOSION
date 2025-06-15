package com.hzbhd.canbus.car._359;

import android.content.Context;
import android.widget.Toast;
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
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    private int EachID;
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private MsgMgr msgMgr;
    private SettingPageUiSet settingPageUiSet;
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData(10);
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.sendAirData(36);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.sendAirData(37);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData(23);
            } else if (i == 1) {
                UiMgr.this.sendAirData(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirData(21);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirData(20);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirData(40);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirData(16);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAirData(25);
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(2);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(4);
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass14.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
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
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass14.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
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
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.getMsgMgr(uiMgr.mContext).updateSetting(i, i2, i3);
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getSettingLeftIndexes(uiMgr2.mContext, "_330_amplifier_info")) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 == 3) {
                            if (i3 == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                            } else {
                                CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                            }
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
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_330_Mode_selection") && i2 == 0) {
                if (i3 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -29, 0});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -29, 1});
                }
            }
            UiMgr uiMgr4 = UiMgr.this;
            if (i == uiMgr4.getSettingLeftIndexes(uiMgr4.mContext, "_330_setting_info") && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte) UiMgr.this.getCarModelData(i3)});
            }
        }
    };
    private int fastForward = 0;
    private int fastBack = 0;
    private int RADIO_PLAY_PAUSE = 0;
    private int play_pause_state = 0;
    OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.12
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
    OnOriginalCarDeviceBackPressedListener onOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._359.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
        public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, 5});
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public int getCarModelData(int i) {
        return i == 0 ? 32 : 33;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.onOriginalCarDeviceBackPressedListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListenerFront);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
    }

    /* renamed from: com.hzbhd.canbus.car._359.UiMgr$14, reason: invalid class name */
    static /* synthetic */ class AnonymousClass14 {
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
    public void sendAirData(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 0});
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
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
