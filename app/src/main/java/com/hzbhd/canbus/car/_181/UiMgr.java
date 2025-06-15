package com.hzbhd.canbus.car._181;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr msgMgr;
    private OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SendAirFrontMsg((byte) 17);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.SendAirFrontMsg((byte) 19);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SendAirFrontMsg((byte) 16);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.SendAirFrontMsg((byte) 18);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SendAirRearMsg((byte) 18);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.SendAirRearMsg((byte) 16);
            }
        }
    };
    private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SendAirFrontMsg((byte) 30);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SendAirFrontMsg((byte) 31);
        }
    };
    private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SendAirFrontMsg((byte) 32);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SendAirFrontMsg((byte) 33);
        }
    };
    private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SendAirRearMsg((byte) 30);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SendAirRearMsg((byte) 31);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.SendAirFrontMsg((byte) 34);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.SendAirFrontMsg((byte) 36);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.SendAirFrontMsg((byte) 35);
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.SendAirFrontMsg((byte) 37);
        }
    };
    OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.19
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
            UiMgr.this.activeRequest(55);
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.20
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass22.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                MyLog.e("FRONT_REAR", i + "");
                UiMgr.this.sendAmplifierInfo(6, (-i) + 10);
            } else {
                if (i2 != 2) {
                    return;
                }
                MyLog.e("LEFT_RIGHT", i + "");
                UiMgr.this.sendAmplifierInfo(5, i + 10);
            }
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.21
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            String str = "0x" + i;
            int i2 = AnonymousClass22.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                UiMgr.this.sendAmplifierInfo(1, i);
                return;
            }
            if (i2 == 2) {
                UiMgr.this.sendAmplifierInfo(2, Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16));
            } else if (i2 == 3) {
                UiMgr.this.sendAmplifierInfo(3, Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16));
            } else {
                if (i2 != 4) {
                    return;
                }
                UiMgr.this.sendAmplifierInfo(4, Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16));
            }
        }
    };
    private int eachId = getEachId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.SendAirFrontMsg((byte) 29);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.SendAirFrontMsg((byte) 28);
            }
        });
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.2
            int a = 0;

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                int i = this.a;
                if (i == 0) {
                    UiMgr.this.SendAirFrontMsg((byte) 21);
                    this.a = 1;
                    return;
                }
                if (i == 1) {
                    UiMgr.this.SendAirFrontMsg((byte) 24);
                    this.a = 2;
                    return;
                }
                if (i == 2) {
                    UiMgr.this.SendAirFrontMsg((byte) 25);
                    this.a = 3;
                } else if (i == 3) {
                    UiMgr.this.SendAirFrontMsg((byte) 26);
                    this.a = 4;
                } else if (i == 4) {
                    UiMgr.this.SendAirFrontMsg((byte) 27);
                    this.a = 0;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                int i = this.a;
                if (i == 0) {
                    UiMgr.this.SendAirFrontMsg((byte) 21);
                    this.a = 1;
                    return;
                }
                if (i == 1) {
                    UiMgr.this.SendAirFrontMsg((byte) 24);
                    this.a = 2;
                    return;
                }
                if (i == 2) {
                    UiMgr.this.SendAirFrontMsg((byte) 25);
                    this.a = 3;
                } else if (i == 3) {
                    UiMgr.this.SendAirFrontMsg((byte) 26);
                    this.a = 4;
                } else if (i == 4) {
                    UiMgr.this.SendAirFrontMsg((byte) 27);
                    this.a = 0;
                }
            }
        });
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onAirTemperatureUpDownClickListenerCenter, null});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        airUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.3
            int b;

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                int i = this.b;
                if (i == 0) {
                    UiMgr.this.SendAirRearMsg((byte) 24);
                    this.b = 1;
                } else if (i == 1) {
                    UiMgr.this.SendAirRearMsg((byte) 25);
                    this.b = 2;
                } else if (i == 2) {
                    UiMgr.this.SendAirRearMsg((byte) 26);
                    this.b = 0;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                int i = this.b;
                if (i == 0) {
                    UiMgr.this.SendAirRearMsg((byte) 24);
                    this.b = 1;
                } else if (i == 1) {
                    UiMgr.this.SendAirRearMsg((byte) 25);
                    this.b = 2;
                } else if (i == 2) {
                    UiMgr.this.SendAirRearMsg((byte) 26);
                    this.b = 0;
                }
            }
        });
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.SendAirRearMsg((byte) 29);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.SendAirRearMsg((byte) 28);
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_337_setting_10":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                        break;
                    case "_337_setting_11":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                        break;
                    case "_337_setting_12":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                        break;
                    case "_337_setting_13":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                        break;
                    case "_337_setting_14":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                        break;
                    case "_337_setting_15":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                        break;
                    case "_337_setting_16":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) i3});
                        break;
                    case "_337_setting_19":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
                        break;
                    case "_337_setting_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                        break;
                    case "_337_setting_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                        break;
                    case "_337_setting_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                        break;
                    case "_337_setting_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                        break;
                    case "_337_setting_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                        break;
                    case "_337_setting_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case "_337_setting_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case "_337_setting_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._181.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_337_drive_car_info5")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        getDriverDataPageUiSet(this.mContext);
    }

    /* renamed from: com.hzbhd.canbus.car._181.UiMgr$22, reason: invalid class name */
    static /* synthetic */ class AnonymousClass22 {
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
    public void SendAirFrontMsg(byte b) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendAirRearMsg(byte b) {
        CanbusMsgSender.sendMsg(new byte[]{22, -123, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -123, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
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
    public void sendAmplifierInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte) i, (byte) i2});
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
}
