package com.hzbhd.canbus.car._260;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class UiMgr extends AbstractUiMgr {
    public int[] index;
    public String leftTitle;
    private Context mContext;
    public String rightTitle;
    public HashMap<String, Integer> settingPageIndex;
    public SettingPageUiSet settingPageUiSet;
    public boolean haveProblem = true;
    private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr$$ExternalSyntheticLambda0
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m337lambda$new$0$comhzbhdcanbuscar_260UiMgr(i);
        }
    };
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr$$ExternalSyntheticLambda1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m338lambda$new$1$comhzbhdcanbuscar_260UiMgr(i);
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr$$ExternalSyntheticLambda2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m339lambda$new$2$comhzbhdcanbuscar_260UiMgr(i);
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendMsg((byte) 28);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendMsg((byte) 29);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendMsg((byte) 31);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendMsg((byte) 30);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            String str = GeneralAirData.center_wheel;
            str.hashCode();
            switch (str) {
                case "Normal":
                    UiMgr.this.sendMsg((byte) 64);
                    break;
                case "":
                    UiMgr.this.sendMsg((byte) 65);
                    break;
                case "Fast":
                    UiMgr.this.sendMsg((byte) 65);
                    break;
                case "Soft":
                    UiMgr.this.sendMsg((byte) 66);
                    break;
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            String str = GeneralAirData.center_wheel;
            str.hashCode();
            switch (str) {
                case "Normal":
                    UiMgr.this.sendMsg((byte) 66);
                    break;
                case "":
                    UiMgr.this.sendMsg((byte) 65);
                    break;
                case "Fast":
                    UiMgr.this.sendMsg((byte) 64);
                    break;
                case "Soft":
                    UiMgr.this.sendMsg((byte) 65);
                    break;
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendMsg((byte) 33);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendMsg((byte) 32);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i, (byte) i2});
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_260-UiMgr, reason: not valid java name */
    /* synthetic */ void m337lambda$new$0$comhzbhdcanbuscar_260UiMgr(int i) {
        if (i == 0) {
            sendMsg((byte) 22);
        } else if (i == 1) {
            sendMsg((byte) 21);
        } else {
            if (i != 2) {
                return;
            }
            sendMsg((byte) 23);
        }
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_260-UiMgr, reason: not valid java name */
    /* synthetic */ void m338lambda$new$1$comhzbhdcanbuscar_260UiMgr(int i) {
        sendMsg((byte) 17);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_260-UiMgr, reason: not valid java name */
    /* synthetic */ void m339lambda$new$2$comhzbhdcanbuscar_260UiMgr(int i) {
        if (i == 0) {
            sendMsg((byte) 20);
        } else if (i == 1) {
            sendMsg((byte) 19);
        } else {
            if (i != 2) {
                return;
            }
            sendMsg((byte) 34);
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._260.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                m340lambda$new$3$comhzbhdcanbuscar_260UiMgr(i, i2, i3);
            }
        });
        this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._260.UiMgr.5
            public String leftTitle;
            public String rightTitle;

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Removed duplicated region for block: B:113:0x01ea  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x00b6  */
            /* JADX WARN: Removed duplicated region for block: B:4:0x0044  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x0116  */
            /* JADX WARN: Removed duplicated region for block: B:89:0x018a  */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onSwitch(int r7, int r8, int r9) {
                /*
                    Method dump skipped, instructions count: 720
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._260.UiMgr.AnonymousClass5.onSwitch(int, int, int):void");
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, null, this.mOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, this.mTempSetViewOnUpDownClickListenerCenter, this.mTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._260.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendMsg((byte) 25);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendMsg((byte) 25);
            }
        });
        if (this.haveProblem) {
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_5_2"});
        }
        int currentCarId = getCurrentCarId();
        if (currentCarId != 2) {
            removeDriveData(context, "_260_driveInfo_1");
        }
        if (currentCarId == 1 || currentCarId == 2) {
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_0_1"});
            removeSettingLeftItemByNameList(context, new String[]{"_260_settingInfo_1"});
            removeSettingLeftItemByNameList(context, new String[]{"_260_settingInfo_2"});
            removeSettingLeftItemByNameList(context, new String[]{"_260_settingInfo_3"});
            removeSettingLeftItemByNameList(context, new String[]{"_260_settingInfo_4"});
            removeSettingLeftItemByNameList(context, new String[]{"_260_settingInfo_5"});
        }
        if (currentCarId == 4 || currentCarId == 5 || currentCarId == 6) {
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_0_0"});
        }
        if (currentCarId == 3) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        if (currentCarId != 1 && currentCarId != 2) {
            removeMainPrjBtnByName(context, "air");
        }
        if (currentCarId != 5 && currentCarId != 6) {
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_2_5"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_3_0"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_3_1"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_1_0"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_1_2"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_1_3"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_1_4"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_3_3"});
            removeSettingRightItemByNameList(context, new String[]{"_260_settingInfo_4_1"});
        }
        this.settingPageIndex = new HashMap<>();
        initSettingPageIndex();
        this.settingPageIndex.get("_260_settingInfo_0_0");
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_260-UiMgr, reason: not valid java name */
    /* synthetic */ void m340lambda$new$3$comhzbhdcanbuscar_260UiMgr(int i, int i2, int i3) {
        this.leftTitle = this.settingPageUiSet.getList().get(i).getTitleSrn();
        this.rightTitle = this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        String str = this.leftTitle;
        str.hashCode();
        switch (str) {
            case "_260_settingInfo_0":
                String str2 = this.rightTitle;
                str2.hashCode();
                if (!str2.equals("_260_settingInfo_0_0")) {
                    if (str2.equals("_260_settingInfo_0_1")) {
                        sendSettingData(1, i3);
                        break;
                    }
                } else if (i3 == 0) {
                    sendAirConditionData(0);
                    break;
                } else if (i3 == 1) {
                    sendAirConditionData(2);
                    break;
                }
                break;
            case "_260_settingInfo_1":
                String str3 = this.rightTitle;
                str3.hashCode();
                if (str3.equals("_260_settingInfo_1_0")) {
                    sendSettingData(32, i3);
                    break;
                }
                break;
            case "_260_settingInfo_2":
                String str4 = this.rightTitle;
                str4.hashCode();
                if (!str4.equals("_260_settingInfo_2_0")) {
                    if (str4.equals("_260_settingInfo_2_1")) {
                        sendSettingData(23, i3);
                        break;
                    }
                } else {
                    sendSettingData(22, i3);
                    break;
                }
                break;
            case "_260_settingInfo_5":
                String str5 = this.rightTitle;
                str5.hashCode();
                if (str5.equals("_260_settingInfo_5_2")) {
                    sendSettingData(64, i3);
                    break;
                }
                break;
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initSettingPageIndex();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(byte b) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, b, 0});
    }

    private void sendAirConditionData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i});
    }

    public Map<String, Integer> initSettingPageIndex() {
        List<SettingPageUiSet.ListBean> list = this.settingPageUiSet.getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            this.settingPageIndex.put(next.getTitleSrn(), Integer.valueOf(i));
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
            Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.settingPageIndex.put(it2.next().getTitleSrn(), Integer.valueOf(i2));
            }
        }
        return this.settingPageIndex;
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

    public void sendPhoneNumber(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void sendPhoneNumberState(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void sendMediaInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
    }
}
