package com.hzbhd.canbus.car._185;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr msgMgr;
    int a = 0;
    String K_LANGUAGE_TAG = "key.language.tag";
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SendMsg(NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SendMsg((byte) 14);
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SendMsg((byte) 15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SendMsg((byte) 16);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SendMsg((byte) 2);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.SendMsg((byte) 4);
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SendMsg((byte) 1);
        }
    };
    int i = 0;
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SendMsg((byte) 7);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SendMsg((byte) 3);
            } else if (i == 1) {
                UiMgr.this.SendMsg((byte) 30);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.SendMsg((byte) 5);
            }
        }
    };
    int eachId = getEachId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 6) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_0_0"});
        }
        int i2 = this.eachId;
        if (i2 == 3 || i2 == 6) {
            removeMainPrjBtnByName(this.mContext, "air");
        }
        int i3 = this.eachId;
        if (i3 != 5 && i3 != 7) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_185_Original_vehicle_screen_status"});
        }
        int i4 = this.eachId;
        if (i4 == 1 || i4 == 2) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_1"});
        }
        int i5 = this.eachId;
        if (i5 == 1 || i5 == 2) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_185_setting_0"});
        }
        int i6 = this.eachId;
        if (i6 != 5 && i6 != 7 && i6 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_2"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_2_1"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_4_0"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_4_1"});
        }
        int i7 = this.eachId;
        if (i7 != 5 && i7 != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_2_0"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_3_0"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_3_1"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_3_2"});
        }
        if (this.eachId != 6) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_4"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_3"});
        }
        int i8 = this.eachId;
        if (i8 != 1 && i8 != 2 && i8 != 5 && i8 != 7 && i8 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_8"});
        }
        int i9 = this.eachId;
        if (i9 != 7 && i9 != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_0"});
        }
        if (this.eachId != 7) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_6"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_7"});
        }
        if (this.eachId != 5) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_9"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_A"});
        }
        if (this.eachId != 8) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_bu_2", "_1193_setting_6_0", "_1193_setting_6_1"});
        }
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr.this.getMsgMgr(context).updateSetting(i, i2, i3);
                String titleSrn = settingUiSet.getList().get(i).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_1193_setting_4_1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -116, 8, (byte) i3});
                }
                String titleSrn2 = settingUiSet.getList().get(i).getTitleSrn();
                titleSrn2.hashCode();
                if (titleSrn2.equals("_1193_setting_4")) {
                    String titleSrn3 = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                    titleSrn3.hashCode();
                    if (titleSrn3.equals("_1193_setting_1_DDD")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 3, (byte) i3});
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.2
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:116:0x02d5  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00a3  */
            /* JADX WARN: Removed duplicated region for block: B:4:0x0032  */
            /* JADX WARN: Removed duplicated region for block: B:56:0x0149  */
            /* JADX WARN: Removed duplicated region for block: B:76:0x01d5  */
            /* JADX WARN: Removed duplicated region for block: B:96:0x0250  */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r22, int r23, int r24) {
                /*
                    Method dump skipped, instructions count: 1350
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._185.UiMgr.AnonymousClass2.onClickItem(int, int, int):void");
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_1193_setting_1")) {
                    String titleSrn2 = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                    titleSrn2.hashCode();
                    if (titleSrn2.equals("_1193_setting_1_EEE")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 18, 1});
                    }
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 4});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 5});
                } else if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 6});
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 7});
                }
            }
        });
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public void onTouchItem(MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte) DataHandleUtils.getMsb(x), (byte) DataHandleUtils.getLsb(x), (byte) DataHandleUtils.getMsb(y), (byte) DataHandleUtils.getLsb(y), 0});
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.SendMsg(NfDef.AVRCP_EVENT_ID_UIDS_CHANGED);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.SendMsg((byte) 11);
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (UiMgr.this.a == 0) {
                    UiMgr.this.SendMsg((byte) 25);
                    UiMgr.this.a = 1;
                    return;
                }
                if (UiMgr.this.a == 1) {
                    UiMgr.this.SendMsg((byte) 26);
                    UiMgr.this.a = 2;
                } else if (UiMgr.this.a == 2) {
                    UiMgr.this.SendMsg((byte) 27);
                    UiMgr.this.a = 3;
                } else if (UiMgr.this.a == 3) {
                    UiMgr.this.SendMsg((byte) 28);
                    UiMgr.this.a = 0;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                if (UiMgr.this.a == 0) {
                    UiMgr.this.SendMsg((byte) 25);
                    UiMgr.this.a = 1;
                    return;
                }
                if (UiMgr.this.a == 1) {
                    UiMgr.this.SendMsg((byte) 26);
                    UiMgr.this.a = 2;
                } else if (UiMgr.this.a == 2) {
                    UiMgr.this.SendMsg((byte) 27);
                    UiMgr.this.a = 3;
                } else if (UiMgr.this.a == 3) {
                    UiMgr.this.SendMsg((byte) 28);
                    UiMgr.this.a = 0;
                }
            }
        });
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._185.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequestData(17);
                UiMgr.this.activeRequestData(18);
                UiMgr.this.activeRequestData(135);
                UiMgr.this.activeRequestData(38);
                UiMgr.this.activeRequestData(232);
            }
        });
    }

    public void initSettings() {
        int intValue = SharePreUtil.getIntValue(this.mContext, this.K_LANGUAGE_TAG, 0);
        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (((byte) intValue) + 1)});
        getMsgMgr(this.mContext).updateSetting(getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_0_0"), intValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendMsg(byte b) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
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

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
    }

    public void sendMediaInfo0x91(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -111, (byte) i}, bArr));
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
