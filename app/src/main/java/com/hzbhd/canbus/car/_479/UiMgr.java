package com.hzbhd.canbus.car._479;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr mMsgMgr;
    OnAirBtnClickListener topBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.3
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0020  */
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClickItem(int r4) {
            /*
                r3 = this;
                com.hzbhd.canbus.car._479.UiMgr r0 = com.hzbhd.canbus.car._479.UiMgr.this
                android.content.Context r1 = r0.mContext
                com.hzbhd.canbus.adapter.bean.AirPageUiSet r0 = r0.getAirUiSet(r1)
                com.hzbhd.canbus.adapter.bean.FrontArea r0 = r0.getFrontArea()
                java.lang.String[][] r0 = r0.getLineBtnAction()
                r1 = 0
                r0 = r0[r1]
                r4 = r0[r4]
                r4.hashCode()
                int r0 = r4.hashCode()
                r2 = -1
                switch(r0) {
                    case 3106: goto L43;
                    case 3005871: goto L38;
                    case 3094652: goto L2d;
                    case 756225563: goto L22;
                    default: goto L20;
                }
            L20:
                r1 = r2
                goto L4c
            L22:
                java.lang.String r0 = "in_out_cycle"
                boolean r4 = r4.equals(r0)
                if (r4 != 0) goto L2b
                goto L20
            L2b:
                r1 = 3
                goto L4c
            L2d:
                java.lang.String r0 = "dual"
                boolean r4 = r4.equals(r0)
                if (r4 != 0) goto L36
                goto L20
            L36:
                r1 = 2
                goto L4c
            L38:
                java.lang.String r0 = "auto"
                boolean r4 = r4.equals(r0)
                if (r4 != 0) goto L41
                goto L20
            L41:
                r1 = 1
                goto L4c
            L43:
                java.lang.String r0 = "ac"
                boolean r4 = r4.equals(r0)
                if (r4 != 0) goto L4c
                goto L20
            L4c:
                r4 = 4
                switch(r1) {
                    case 0: goto L84;
                    case 1: goto L73;
                    case 2: goto L62;
                    case 3: goto L51;
                    default: goto L50;
                }
            L50:
                goto L94
            L51:
                byte[] r0 = new byte[r4]
                r0 = {x00b4: FILL_ARRAY_DATA , data: [22, -59, 12, 1} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00ba: FILL_ARRAY_DATA , data: [22, -59, 12, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
                goto L94
            L62:
                byte[] r0 = new byte[r4]
                r0 = {x00c0: FILL_ARRAY_DATA , data: [22, -59, 14, 1} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00c6: FILL_ARRAY_DATA , data: [22, -59, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
                goto L94
            L73:
                byte[] r0 = new byte[r4]
                r0 = {x00cc: FILL_ARRAY_DATA , data: [22, -59, 5, 1} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00d2: FILL_ARRAY_DATA , data: [22, -59, 5, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
                goto L94
            L84:
                byte[] r0 = new byte[r4]
                r0 = {x00d8: FILL_ARRAY_DATA , data: [22, -59, 11, 1} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00de: FILL_ARRAY_DATA , data: [22, -59, 11, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
            L94:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._479.UiMgr.AnonymousClass3.onClickItem(int):void");
        }
    };
    OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            String str = uiMgr.getAirUiSet(uiMgr.mContext).getFrontArea().getLineBtnAction()[3][i];
            str.hashCode();
            if (str.equals("front_defog")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -59, 7, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -59, 7, 0});
            } else if (str.equals("rear_defog")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -59, 8, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -59, 8, 0});
            }
        }
    };
    OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 9, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 9, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 10, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 10, 0});
        }
    };
    OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 0});
        }
    };
    OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 4, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 4, 0});
        }
    };
    OnAirSeatClickListener seatClick = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }
    };
    OnSettingItemSelectListener settingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            String titleSrn = uiMgr.getSettingUiSet(uiMgr.mContext).getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("_479_car_set_1")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(i, i2, i3);
            } else if (titleSrn.equals("_479_car_set_2")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, (byte) i3});
                UiMgr uiMgr3 = UiMgr.this;
                uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(i, i2, i3);
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 6, 0});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 6, 1});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 7, 0});
                } else if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 7, 1});
                } else {
                    if (i != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 7, 2});
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._479.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
                str.hashCode();
                switch (str) {
                    case "prev_disc":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 9, 0});
                        break;
                    case "up":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 3, 0});
                        break;
                    case "down":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, 0});
                        break;
                    case "left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 4, 0});
                        break;
                    case "play":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, 0});
                        break;
                    case "pause":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, 0});
                        break;
                    case "right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 5, 0});
                        break;
                    case "next_disc":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 8, 0});
                        break;
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, null, null, this.bottomBtn});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.seatClick);
        getSettingUiSet(context).setOnSettingItemSelectListener(this.settingItemSelectListener);
        getDriverDataPageUiSet(context);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
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
