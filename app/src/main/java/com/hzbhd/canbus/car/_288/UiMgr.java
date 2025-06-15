package com.hzbhd.canbus.car._288;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
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
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;


public class UiMgr extends AbstractUiMgr {
    static final String SHARE_288_LANGUAGE = "share_288_language";
    AirPageUiSet mAirPageUiSet;
    AmplifierPageUiSet mAmplifierPageUiSet;
    private Context mContext;
    private MsgMgr mMsgMgr;
    OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    SettingPageUiSet mSettingPageUiSet;
    private OnAirBtnClickListener mBtnTopClick = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMsg(1);
            } else if (i == 1) {
                UiMgr.this.sendAirMsg(25);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirMsg(23);
            }
        }
    };
    private OnAirBtnClickListener mBtnLeftClick = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirMsg(21);
        }
    };
    private OnAirBtnClickListener mBtnRightClick = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirMsg(20);
        }
    };
    private OnAirBtnClickListener mBtnButtomClick = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMsg(16);
            } else if (i == 1) {
                UiMgr.this.sendAirMsg(18);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirMsg(36);
            }
        }
    };
    private OnAirWindSpeedUpDownClickListener mWindLevChange = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirMsg(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirMsg(10);
        }
    };
    private OnAirTemperatureUpDownClickListener mLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMsg(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMsg(2);
        }
    };
    private OnAirTemperatureUpDownClickListener mRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMsg(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMsg(4);
        }
    };
    private OnSettingItemSelectListener mItemSelect = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i2 == 0) {
                UiMgr.this.sendSettingMsg(3, i3);
                return;
            }
            if (i2 == 2) {
                UiMgr.this.sendSettingMsg(12, i3);
                return;
            }
            if (i2 == 3) {
                UiMgr.this.sendSettingMsg(8, i3);
                SharePreUtil.setIntValue(UiMgr.this.mContext, "_288_amplifier_on_off", i3);
                UiMgr.this.mMsgMgr.updateAmplifier();
            } else if (i2 == 4) {
                UiMgr.this.sendSettingMsg(10, i3);
                SharePreUtil.setIntValue(UiMgr.this.mContext, "_288_amplifier_mute", i3);
                UiMgr.this.mMsgMgr.updateAmplifier();
            } else {
                if (i2 != 5) {
                    return;
                }
                UiMgr.this.sendSettingMsg(9, i3);
                SharePreUtil.setIntValue(UiMgr.this.mContext, "_288_amplifier_round", i3);
                UiMgr.this.mMsgMgr.updateAmplifier();
            }
        }
    };
    private OnSettingPageStatusListener mSettingPage = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (UiMgr.this.mMsgMgr != null) {
                UiMgr.this.mMsgMgr.updateSettingData();
            }
        }
    };
    private OnAmplifierSeekBarListener mSeekBar = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass17.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
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
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
            }
        }
    };
    private OnAmplifierPositionListener mPosition = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass17.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (7 - i)});
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
            }
        }
    };
    private OnOriginalTopBtnClickListener mTop = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.12
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            if (!"CD".equals(GeneralOriginalCarDeviceData.cdStatus) && !"Rear CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
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
    private OnOriginalBottomBtnClickListener mBottom = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.13
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
            if ("CD".equals(GeneralOriginalCarDeviceData.cdStatus) || "Rear CD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
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
    private OnOriginalSongItemClickListener mSongList = new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._288.UiMgr.14
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
    private HandlerThread mHandlerThread = new HandlerThread("MyApplication") { // from class: com.hzbhd.canbus.car._288.UiMgr.15
        {
            start();
        }
    };
    private Handler mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._288.UiMgr.16
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1 && (message.obj instanceof byte[])) {
                CanbusMsgSender.sendMsg((byte[]) message.obj);
            }
        }
    };

    public UiMgr(Context context) {
        try {
            this.mContext = context;
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
            this.mAmplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
            this.mSettingPageUiSet = getSettingUiSet(this.mContext);
            this.mAirPageUiSet = getAirUiSet(this.mContext);
            this.mOriginalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
            this.mAirPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mBtnTopClick, this.mBtnLeftClick, this.mBtnRightClick, this.mBtnButtomClick});
            this.mAirPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mWindLevChange);
            this.mAirPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeft, null, this.mRight});
            this.mSettingPageUiSet.setOnSettingItemSelectListener(this.mItemSelect);
            this.mSettingPageUiSet.setOnSettingPageStatusListener(this.mSettingPage);
            this.mAmplifierPageUiSet.setOnAmplifierSeekBarListener(this.mSeekBar);
            this.mAmplifierPageUiSet.setOnAmplifierPositionListener(this.mPosition);
            this.mOriginalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mTop);
            this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mBottom);
            this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mSongList);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("cwh", "错误类型： " + e);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._288.UiMgr$17, reason: invalid class name */
    static /* synthetic */ class AnonymousClass17 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirMsg(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        Message message = new Message();
        message.what = 1;
        message.obj = new byte[]{22, -32, b, 0};
        this.mHandler.sendMessageDelayed(message, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSourceMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }
}
