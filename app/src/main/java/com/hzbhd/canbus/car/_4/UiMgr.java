package com.hzbhd.canbus.car._4;

import android.content.Context;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;

import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    private String PARKING_MODE_KEY = "PARKING_MODE_KEY";
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(33);
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_246_paring_info")) {
                if (i2 == 1) {
                    UiMgr.this.sendCarControlInfo(0, i3);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    UiMgr.this.sendCarControlInfo(2, i3);
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.PARKING_MODE_KEY, i, i2, i3);
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_1004_amp") && i2 == 0) {
                UiMgr.this.sendAmplifierInfo(6, i3);
            }
        }
    };
    OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_246_paring_info")) {
                UiMgr.this.activeRequestData(37);
            }
        }
    };
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(20);
            UiMgr.this.activeRequestData(65, 2);
            UiMgr.this.activeRequestData(22);
            UiMgr.this.activeRequestData(36);
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                if (i < 0) {
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext);
                    MsgMgr.frontRearTag = -1;
                } else {
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.getMsgMgr(uiMgr2.mContext);
                    MsgMgr.frontRearTag = 1;
                }
                UiMgr.this.sendAmplifierInfo(2, i);
                return;
            }
            if (i2 != 2) {
                return;
            }
            if (i < 0) {
                UiMgr uiMgr3 = UiMgr.this;
                uiMgr3.getMsgMgr(uiMgr3.mContext);
                MsgMgr.leftRightTag = -1;
            } else {
                UiMgr uiMgr4 = UiMgr.this;
                uiMgr4.getMsgMgr(uiMgr4.mContext);
                MsgMgr.leftRightTag = 1;
            }
            UiMgr.this.sendAmplifierInfo(1, i);
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                UiMgr.this.sendAmplifierInfo(0, i);
                return;
            }
            if (i2 == 2) {
                if (i < 9) {
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.getMsgMgr(uiMgr.mContext);
                    MsgMgr.bandTrebleTag = -1;
                } else {
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.getMsgMgr(uiMgr2.mContext);
                    MsgMgr.bandTrebleTag = 1;
                }
                UiMgr.this.sendAmplifierInfo(5, i - 9);
                return;
            }
            if (i2 == 3) {
                if (i < 9) {
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext);
                    MsgMgr.bandBassTag = -1;
                } else {
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.getMsgMgr(uiMgr4.mContext);
                    MsgMgr.bandBassTag = 1;
                }
                UiMgr.this.sendAmplifierInfo(3, i - 9);
                return;
            }
            if (i2 != 4) {
                return;
            }
            if (i < 9) {
                UiMgr uiMgr5 = UiMgr.this;
                uiMgr5.getMsgMgr(uiMgr5.mContext);
                MsgMgr.bandMiddleTag = -1;
            } else {
                UiMgr uiMgr6 = UiMgr.this;
                uiMgr6.getMsgMgr(uiMgr6.mContext);
                MsgMgr.bandMiddleTag = 1;
            }
            UiMgr.this.sendAmplifierInfo(4, i - 9);
        }
    };
    OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._4.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
            UiMgr.this.activeRequestData(39);
        }
    };
    private int mBit7 = 0;
    private int mBit6 = 0;
    private int mBit5 = 0;
    private int mBit4 = 0;
    private int mBit3 = 0;
    private int mBit2 = 0;
    private int mBit1 = 0;
    private int mBit0 = 0;

    private void initUi() {
    }

    public UiMgr(Context context) {
        this.mContext = context;
        getAirUiSet(context).getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
        intiData();
    }

    private void intiData() {
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_246_paring_info"), 2, SharePreUtil.getIntValue(this.mContext, this.PARKING_MODE_KEY, 0));
    }

    /* renamed from: com.hzbhd.canbus.car._4.UiMgr$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
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

    public void sendVolInfo(int i, boolean z) {
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(i, 6, 1), DataHandleUtils.getIntFromByteWithBit(i, 5, 1), DataHandleUtils.getIntFromByteWithBit(i, 4, 1), DataHandleUtils.getIntFromByteWithBit(i, 3, 1), DataHandleUtils.getIntFromByteWithBit(i, 2, 1), DataHandleUtils.getIntFromByteWithBit(i, 1, 1), DataHandleUtils.getIntFromByteWithBit(i, 0, 1))});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCarControlInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -96, (byte) i, (byte) i2});
    }

    public void sendSourceInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
    }

    public void sendIconInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte) getDecimalFrom8Bit(0, 0, 0, 0, 0, i, i2, 0)});
    }

    public void sendRadioInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) i, (byte) i2, (byte) i3, (byte) i4});
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

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return 404;
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
        return 404;
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
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

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }
}
