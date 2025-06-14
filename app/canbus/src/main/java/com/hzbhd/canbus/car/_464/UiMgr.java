package com.hzbhd.canbus.car._464;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;
    private boolean cdFasterTag = false;
    private boolean cdSlowerTag = false;
    private boolean dvdFasterTag = false;
    private boolean dvdSlowerTag = false;
    private OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            setDeviceBottomBtnEnvent(i);
        }

        private void setDeviceBottomBtnEnvent(int i) {
            UiMgr uiMgr = UiMgr.this;
            if (uiMgr.getMsgMgr(uiMgr.mContext).nowModeTag == 255) {
                switch (i) {
                    case 0:
                        UiMgr.this.send0x84(22, 0);
                        break;
                    case 1:
                        UiMgr.this.send0x84(20, 0);
                        break;
                    case 2:
                        if (UiMgr.this.cdSlowerTag) {
                            UiMgr.this.cdSlowerTag = false;
                            UiMgr.this.send0x84(25, 0);
                            break;
                        } else {
                            UiMgr.this.cdSlowerTag = true;
                            UiMgr.this.send0x84(25, 1);
                            break;
                        }
                    case 3:
                        UiMgr.this.send0x84(26, 0);
                        break;
                    case 4:
                        if (UiMgr.this.cdFasterTag) {
                            UiMgr.this.cdFasterTag = false;
                            UiMgr.this.send0x84(24, 0);
                            break;
                        } else {
                            UiMgr.this.cdFasterTag = true;
                            UiMgr.this.send0x84(24, 1);
                            break;
                        }
                    case 5:
                        UiMgr.this.send0x84(19, 0);
                        break;
                    case 6:
                        UiMgr.this.send0x84(21, 0);
                        break;
                    case 7:
                        UiMgr.this.send0x84(16, 0);
                        break;
                    case 8:
                        UiMgr.this.send0x84(17, 0);
                        break;
                }
                return;
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (uiMgr2.getMsgMgr(uiMgr2.mContext).nowModeTag == 254) {
                switch (i) {
                    case 0:
                        UiMgr.this.send0x84(73, 0);
                        break;
                    case 1:
                        if (UiMgr.this.dvdSlowerTag) {
                            UiMgr.this.dvdSlowerTag = false;
                            UiMgr.this.send0x84(76, 0);
                            break;
                        } else {
                            UiMgr.this.dvdSlowerTag = true;
                            UiMgr.this.send0x84(76, 1);
                            break;
                        }
                    case 2:
                        UiMgr.this.send0x84(64, 0);
                        break;
                    case 3:
                        UiMgr.this.send0x84(65, 0);
                        break;
                    case 4:
                        UiMgr.this.send0x84(66, 0);
                        break;
                    case 5:
                        if (UiMgr.this.dvdFasterTag) {
                            UiMgr.this.dvdFasterTag = false;
                            UiMgr.this.send0x84(75, 0);
                            break;
                        } else {
                            UiMgr.this.dvdFasterTag = true;
                            UiMgr.this.send0x84(75, 1);
                            break;
                        }
                    case 6:
                        UiMgr.this.send0x84(74, 0);
                        break;
                }
                return;
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (uiMgr3.getMsgMgr(uiMgr3.mContext).nowModeTag == 251) {
                if (i == 0) {
                    UiMgr.this.send0x84(38, 0);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.send0x84(35, 1);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.send0x84(36, 0);
                } else if (i == 3) {
                    UiMgr.this.send0x84(34, 0);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.send0x84(37, 0);
                }
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCmd0x2E(1);
            } else if (i == 1) {
                UiMgr.this.sendAirCmd0x2E(23);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirCmd0x2E(25);
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCmd0x2E(21);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirCmd0x2E(40);
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCmd0x2E(18);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirCmd0x2E(20);
            }
        }
    };
    private OnAirBtnClickListener mOnFrontOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCmd0x2E(16);
            } else if (i == 3) {
                UiMgr.this.sendAirCmd0x2E(57);
            } else {
                if (i != 4) {
                    return;
                }
                UiMgr.this.sendAirCmd0x2E(39);
            }
        }
    };
    private OnAirSeatClickListener mOnFrontAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.sendAirCmd0x2E(36);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            UiMgr.this.sendAirCmd0x2E(37);
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd0x2E(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd0x2E(2);
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd0x2E(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd0x2E(4);
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirCmd0x2E(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirCmd0x2E(10);
        }
    };
    private OnAirTemperatureUpDownClickListener mRearLeftTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd0x2E(42);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd0x2E(41);
        }
    };
    private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd0x2E(51);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd0x2E(50);
        }
    };
    private OnAirTemperatureUpDownClickListener mRearRightTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd0x2E(49);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd0x2E(48);
        }
    };
    private OnAirBtnClickListener mOnRearAirButtomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirCmd0x2E(52);
        }
    };
    private OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                int i3 = (-i) + 7;
                Log.d("AMPL", "前后=" + i3);
                UiMgr.this.send0x84(1, i3);
            } else {
                if (i2 != 2) {
                    return;
                }
                int i4 = i + 7;
                Log.d("AMPL", "左右=" + i4);
                UiMgr.this.send0x84(1, i4);
            }
        }
    };
    private OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.15
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                Log.d("AMPL", "音量=" + i);
                UiMgr.this.send0x84(7, i);
                return;
            }
            if (i2 == 2) {
                int i3 = i + 2;
                Log.d("AMPL", "高音=" + i3);
                UiMgr.this.send0x84(5, i3);
            } else if (i2 == 3) {
                int i4 = i + 2;
                Log.d("AMPL", "中音=" + i4);
                UiMgr.this.send0x84(6, i4);
            } else {
                if (i2 != 4) {
                    return;
                }
                int i5 = i + 2;
                Log.d("AMPL", "低音=" + i5);
                UiMgr.this.send0x84(4, i5);
            }
        }
    };
    private OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.16
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
        }
    };
    private OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._464.UiMgr.17
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_464_ampl")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_464_ampl", "_464_ampl_1")) {
                    UiMgr.this.send0x84(12, i3);
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_464_ampl", "_464_ampl_4")) {
                    UiMgr.this.send0x84(8, i3);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_464_ampl", "_464_ampl_5")) {
                    UiMgr.this.send0x84(10, i3);
                }
            }
            UiMgr uiMgr5 = UiMgr.this;
            if (i == uiMgr5.getSettingLeftIndexes(uiMgr5.mContext, "_464_settings_swc")) {
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_464_settings_swc", "_464_settings_swc0")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -29, (byte) i3});
                }
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mRearLeftTempSetViewOnUpDownClickListenerCenter, this.mRearTempSetViewOnUpDownClickListenerCenter, this.mRearRightTempSetViewOnUpDownClickListenerCenter});
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirButtomBtnClickListener});
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
        getSettingUiSet(context).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        getOriginalCarDevicePageUiSet(context).setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    /* renamed from: com.hzbhd.canbus.car._464.UiMgr$18, reason: invalid class name */
    static /* synthetic */ class AnonymousClass18 {
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
    public void sendAirCmd0x2E(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 46, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 46, b, 0});
    }

    public void send0x84(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
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
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
