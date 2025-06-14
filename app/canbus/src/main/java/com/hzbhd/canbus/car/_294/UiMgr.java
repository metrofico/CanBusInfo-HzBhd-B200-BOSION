package com.hzbhd.canbus.car._294;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private AirPageUiSet mAirPageUiSet;
    private AmplifierPageUiSet mAmplifierPageUiSet;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private SettingPageUiSet mSettingPageUiSet;
    private OnAirBtnClickListener mTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.senAirMsg(1);
                return;
            }
            if (i == 1) {
                UiMgr.this.senAirMsg(23);
                return;
            }
            if (i == 2) {
                UiMgr.this.senAirMsg(24);
            } else if (i == 3) {
                UiMgr.this.senAirMsg(8);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.senAirMsg(7);
            }
        }
    };
    private OnAirBtnClickListener mLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                UiMgr.this.senAirMsg(13);
            } else {
                UiMgr.this.senAirMsg(11);
            }
        }
    };
    private OnAirBtnClickListener mRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                UiMgr.this.senAirMsg(11);
            } else {
                UiMgr.this.senAirMsg(13);
            }
        }
    };
    private OnAirBtnClickListener mBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.senAirMsg(25);
                return;
            }
            if (i == 1) {
                UiMgr.this.senAirMsg(42);
                return;
            }
            if (i == 2) {
                UiMgr.this.senAirMsg(19);
                return;
            }
            if (i == 3) {
                UiMgr.this.senAirMsg(20);
            } else if (i == 4) {
                UiMgr.this.senAirMsg(16);
            } else {
                if (i != 5) {
                    return;
                }
                UiMgr.this.senAirMsg(21);
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mLeftTem = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.senAirMsg(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.senAirMsg(2);
        }
    };
    private OnAirTemperatureUpDownClickListener mRightTem = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.senAirMsg(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.senAirMsg(4);
        }
    };
    private OnAirWindSpeedUpDownClickListener mWindLv = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.senAirMsg(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.senAirMsg(10);
        }
    };
    private OnAmplifierPositionListener mPosition = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            if (UiMgr.this.mMsgMgr.isNeedCanCtrlAmplifier) {
                int i2 = AnonymousClass11.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte) i});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte) i});
                }
            }
        }
    };
    private OnAmplifierSeekBarListener mSeekBar = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            if (UiMgr.this.mMsgMgr.isNeedCanCtrlAmplifier) {
                int i2 = AnonymousClass11.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, (byte) (i - 9)});
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -96, 5, (byte) (i - 9)});
                } else if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, (byte) (i - 9)});
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -96, 0, (byte) i});
                }
            }
        }
    };
    private OnSettingItemSeekbarSelectListener seekBar = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._294.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte) i3});
        }
    };

    public UiMgr(Context context) {
        try {
            this.mContext = context;
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
            this.mAirPageUiSet = getAirUiSet(this.mContext);
            this.mSettingPageUiSet = getSettingUiSet(this.mContext);
            AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
            this.mAmplifierPageUiSet = amplifierPageUiSet;
            amplifierPageUiSet.setOnAmplifierPositionListener(this.mPosition);
            this.mAmplifierPageUiSet.setOnAmplifierSeekBarListener(this.mSeekBar);
            this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.seekBar);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("cwh", "error = " + e);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._294.UiMgr$11, reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
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
    public void senAirMsg(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -32, b, 0});
    }
}
