package com.hzbhd.canbus.car._323;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private int mDifferent;
    private MsgMgr mMsgMgr;
    OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private OnOriginalTopBtnClickListener mTop = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            if (!"CD".equals(GeneralOriginalCarDeviceData.cdStatus) && !"Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                if (i <= 5) {
                    if (i == 4) {
                        UiMgr.this.sendSourceMsg(48, 16);
                        UiMgr.this.mMsgMgr.turnToRearCdPage();
                        return;
                    } else if (i == 5) {
                        UiMgr.this.sendSourceMsg(48, 5);
                        return;
                    } else {
                        UiMgr.this.sendSourceMsg(48, i + 1);
                        return;
                    }
                }
                return;
            }
            if (i <= 5) {
                if (i == 4) {
                    return;
                }
                if (i == 5) {
                    UiMgr.this.sendSourceMsg(48, 5);
                    return;
                } else {
                    UiMgr.this.sendSourceMsg(48, i + 1);
                    return;
                }
            }
            if (i == 6) {
                UiMgr.this.sendSourceMsg(17, 0);
            } else if (i == 7) {
                UiMgr.this.sendSourceMsg(16, 0);
            } else {
                if (i != 8) {
                    return;
                }
                UiMgr.this.sendSourceMsg(26, 0);
            }
        }
    };
    private OnOriginalBottomBtnClickListener mBottom = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                if (i == 0) {
                    UiMgr.this.sendSourceMsg(35, 0);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.sendSourceMsg(38, 0);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.sendSourceMsg(36, 0);
                    return;
                } else if (i == 3) {
                    UiMgr.this.sendSourceMsg(37, 0);
                    return;
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.sendSourceMsg(34, 0);
                    return;
                }
            }
            if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                if (i == 0) {
                    UiMgr.this.sendSourceMsg(22, 0);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.sendSourceMsg(20, 0);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.sendSourceMsg(25, 0);
                    return;
                }
                if (i == 3) {
                    UiMgr.this.sendSourceMsg(24, 0);
                } else if (i == 4) {
                    UiMgr.this.sendSourceMsg(19, 0);
                } else {
                    if (i != 5) {
                        return;
                    }
                    UiMgr.this.sendSourceMsg(21, 0);
                }
            }
        }
    };
    private OnOriginalSongItemClickListener mSongList = new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemClick(int i) {
            if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 32, (byte) (i + 1)});
            } else if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 23, (byte) (i + 1)});
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemLongClick(int i) {
            if ("Radio".equals(GeneralOriginalCarDeviceData.cdStatus)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 33, (byte) (i + 1)});
            }
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirKeyMsg(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirKeyMsg(2);
        }
    };
    OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirKeyMsg(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirKeyMsg(4);
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirKeyMsg(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirKeyMsg(10);
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());
    OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass16.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) ((-i) + 7)});
            }
        }
    };
    OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass16.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 2)});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 2)});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        int currentCarId = getCurrentCarId();
        this.mDifferent = currentCarId;
        if (currentCarId == 1 || currentCarId == 18) {
            removeDriveData(context, "mazda_binary_car_set78");
        }
        if (this.mDifferent == 5) {
            removeSettingLeftItemByNameList(context, new String[]{"_323_setting_0x60_0"});
        }
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mTop);
        this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mBottom);
        this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mSongList);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[0][i];
                str.hashCode();
                if (str.equals("auto")) {
                    UiMgr.this.sendAirKeyMsg(21);
                } else if (str.equals("in_out_cycle")) {
                    UiMgr.this.sendAirKeyMsg(25);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[1][i];
                str.hashCode();
                if (str.equals("power")) {
                    UiMgr.this.sendAirKeyMsg(1);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[2][i];
                str.hashCode();
                if (str.equals("ac")) {
                    UiMgr.this.sendAirKeyMsg(23);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                String str = airUiSet.getFrontArea().getLineBtnAction()[3][i];
                str.hashCode();
                if (str.equals("rear_defog")) {
                    UiMgr.this.sendAirKeyMsg(20);
                } else if (str.equals("dual")) {
                    UiMgr.this.sendAirKeyMsg(16);
                }
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, null, this.onUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._323.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_323_setting_0x60_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -29, (byte) i3});
                        break;
                    case "_323_amplifier_setting_1":
                        UiMgr uiMgr = UiMgr.this;
                        if (uiMgr.getMsgMgr(uiMgr.mContext).get0x31Data()[4] != 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                            break;
                        }
                    case "_323_amplifier_setting_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case "_323_amplifier_setting_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) i3});
                        break;
                    case "_323_amplifier_setting_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte) i3});
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSourceMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirKeyMsg(final int i) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._323.UiMgr.12
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
            }
        }, 30L);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._323.UiMgr.13
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
            }
        }, 30L);
    }

    /* renamed from: com.hzbhd.canbus.car._323.UiMgr$16, reason: invalid class name */
    static /* synthetic */ class AnonymousClass16 {
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
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
