package com.hzbhd.canbus.car._353;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr msgMgr;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 23, 1});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 1, 1});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 21, 1});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 39, 1});
        }
    };
    int i = 1;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 20, 1});
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 19, 1});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 36, 1});
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 16, 1});
            } else if (UiMgr.this.i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 35, 1});
                UiMgr.this.i = 1;
            } else if (UiMgr.this.i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 34, 1});
                UiMgr.this.i = 0;
            }
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 3, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 2, 1});
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 5, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, 4, 1});
        }
    };
    int eachId = getEachId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        GeneralTireData.isHaveSpareTire = false;
        if (this.eachId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 0});
        }
        if (this.eachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
        }
        if (this.eachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 2});
        }
        if (this.eachId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 3});
        }
        if (this.eachId == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 4});
        }
        if (this.eachId == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 5});
        }
        if (this.eachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 6});
        }
        if (this.eachId == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 7});
        }
        if (this.eachId == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 8});
        }
        if (this.eachId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 9});
        }
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_353_setting_2_6":
                        UiMgr uiMgr = UiMgr.this;
                        if (uiMgr.getMsgMgr(uiMgr.mContext).speedVolume > i3) {
                            UiMgr.this.sendAmplifierMessage(38, 49);
                        }
                        UiMgr uiMgr2 = UiMgr.this;
                        if (uiMgr2.getMsgMgr(uiMgr2.mContext).speedVolume < i3) {
                            UiMgr.this.sendAmplifierMessage(38, 33);
                            break;
                        }
                        break;
                    case "_353_setting_2_8":
                        Log.d("lai", "onClickItem: " + i3);
                        UiMgr uiMgr3 = UiMgr.this;
                        int i4 = i3 + 5;
                        if (uiMgr3.getMsgMgr(uiMgr3.mContext).RoundVolume > i4) {
                            UiMgr.this.sendAmplifierMessage(40, 49);
                        }
                        UiMgr uiMgr4 = UiMgr.this;
                        if (uiMgr4.getMsgMgr(uiMgr4.mContext).RoundVolume < i4) {
                            UiMgr.this.sendAmplifierMessage(40, 33);
                            break;
                        }
                        break;
                    case "_330_Rotating_Speed_report":
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, (byte) i3});
                        break;
                    case "_330_car_Speed_report":
                        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_353_setting_2_7")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) i3});
                } else if (titleSrn.equals("_353_setting_2_9")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte) i3});
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 9, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, 10, 1});
            }
        });
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass12.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    UiMgr uiMgr = UiMgr.this;
                    if (uiMgr.getMsgMgr(uiMgr.mContext).volume > i) {
                        UiMgr.this.sendAmplifierMessage(33, 49);
                    }
                    UiMgr uiMgr2 = UiMgr.this;
                    if (uiMgr2.getMsgMgr(uiMgr2.mContext).volume < i) {
                        UiMgr.this.sendAmplifierMessage(33, 33);
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    UiMgr uiMgr3 = UiMgr.this;
                    if (uiMgr3.getMsgMgr(uiMgr3.mContext).bandBass > i) {
                        UiMgr.this.sendAmplifierMessage(34, 49);
                    }
                    UiMgr uiMgr4 = UiMgr.this;
                    if (uiMgr4.getMsgMgr(uiMgr4.mContext).bandBass < i) {
                        UiMgr.this.sendAmplifierMessage(34, 33);
                        return;
                    }
                    return;
                }
                if (i2 != 3) {
                    return;
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (uiMgr5.getMsgMgr(uiMgr5.mContext).bandTreble > i) {
                    UiMgr.this.sendAmplifierMessage(35, 49);
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (uiMgr6.getMsgMgr(uiMgr6.mContext).bandTreble < i) {
                    UiMgr.this.sendAmplifierMessage(35, 33);
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._353.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass12.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    Log.d("lai", "LEFT_RIGHT_position: " + i);
                    UiMgr uiMgr = UiMgr.this;
                    if (uiMgr.getMsgMgr(uiMgr.mContext).leftRight > i) {
                        UiMgr.this.sendAmplifierMessage(36, 49);
                    }
                    UiMgr uiMgr2 = UiMgr.this;
                    if (uiMgr2.getMsgMgr(uiMgr2.mContext).leftRight < i) {
                        UiMgr.this.sendAmplifierMessage(36, 33);
                        return;
                    }
                    return;
                }
                if (i2 != 2) {
                    return;
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (uiMgr3.getMsgMgr(uiMgr3.mContext).frontRear > i) {
                    UiMgr.this.sendAmplifierMessage(37, 33);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (uiMgr4.getMsgMgr(uiMgr4.mContext).frontRear < i) {
                    UiMgr.this.sendAmplifierMessage(37, 49);
                }
                Log.d("lai", "FRONT_REAR_position: " + i);
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._353.UiMgr$12, reason: invalid class name */
    static /* synthetic */ class AnonymousClass12 {
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
                iArr2[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierMessage(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i2});
    }

    public void sendMediaPalyInfo(int i, int i2, int i3, int i4, int i5, int i6) {
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    public void sendPhoneNumber(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void sendPhoneNumber() {
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 6, 1, 84, 101, 108, 32, 79, 102, 102});
    }

    public void sendRadioInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) i, (byte) i2, (byte) i3, (byte) i4});
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

    public void sendSourceInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
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
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(this.mContext);
        }
        return this.msgMgr;
    }
}
