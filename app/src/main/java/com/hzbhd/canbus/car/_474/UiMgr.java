package com.hzbhd.canbus.car._474;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr mMsgMgr;
    OnAirBtnClickListener topBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.6
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
                com.hzbhd.canbus.car._474.UiMgr r0 = com.hzbhd.canbus.car._474.UiMgr.this
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
                    case 3357411: goto L38;
                    case 3496356: goto L2d;
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
                java.lang.String r0 = "rear"
                boolean r4 = r4.equals(r0)
                if (r4 != 0) goto L36
                goto L20
            L36:
                r1 = 2
                goto L4c
            L38:
                java.lang.String r0 = "mono"
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
                r0 = {x00b4: FILL_ARRAY_DATA , data: [22, -58, 14, 9} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00ba: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
                goto L94
            L62:
                byte[] r0 = new byte[r4]
                r0 = {x00c0: FILL_ARRAY_DATA , data: [22, -58, 14, 20} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00c6: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
                goto L94
            L73:
                byte[] r0 = new byte[r4]
                r0 = {x00cc: FILL_ARRAY_DATA , data: [22, -58, 14, 7} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00d2: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
                goto L94
            L84:
                byte[] r0 = new byte[r4]
                r0 = {x00d8: FILL_ARRAY_DATA , data: [22, -58, 14, 8} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r0)
                byte[] r4 = new byte[r4]
                r4 = {x00de: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
            L94:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._474.UiMgr.AnonymousClass6.onClickItem(int):void");
        }
    };
    OnAirBtnClickListener leftBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            String str = uiMgr.getAirUiSet(uiMgr.mContext).getFrontArea().getLineBtnAction()[1][i];
            str.hashCode();
            if (str.equals("auto")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 10});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
            }
        }
    };
    OnAirBtnClickListener rightBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            String str = uiMgr.getAirUiSet(uiMgr.mContext).getFrontArea().getLineBtnAction()[2][i];
            str.hashCode();
            if (str.equals(AirBtnAction.AUTO_2)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 11});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
            }
        }
    };
    OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.9
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0021  */
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClickItem(int r5) {
            /*
                r4 = this;
                com.hzbhd.canbus.car._474.UiMgr r0 = com.hzbhd.canbus.car._474.UiMgr.this
                android.content.Context r1 = r0.mContext
                com.hzbhd.canbus.adapter.bean.AirPageUiSet r0 = r0.getAirUiSet(r1)
                com.hzbhd.canbus.adapter.bean.FrontArea r0 = r0.getFrontArea()
                java.lang.String[][] r0 = r0.getLineBtnAction()
                r1 = 3
                r0 = r0[r1]
                r5 = r0[r5]
                r5.hashCode()
                int r0 = r5.hashCode()
                r2 = 4
                r3 = -1
                switch(r0) {
                    case -1470045433: goto L4d;
                    case -631663038: goto L42;
                    case 341572893: goto L37;
                    case 1434490075: goto L2e;
                    case 1434539597: goto L23;
                    default: goto L21;
                }
            L21:
                r1 = r3
                goto L57
            L23:
                java.lang.String r0 = "blow_head"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L2c
                goto L21
            L2c:
                r1 = r2
                goto L57
            L2e:
                java.lang.String r0 = "blow_foot"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L57
                goto L21
            L37:
                java.lang.String r0 = "blow_window"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L40
                goto L21
            L40:
                r1 = 2
                goto L57
            L42:
                java.lang.String r0 = "rear_defog"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L4b
                goto L21
            L4b:
                r1 = 1
                goto L57
            L4d:
                java.lang.String r0 = "front_defog"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L56
                goto L21
            L56:
                r1 = 0
            L57:
                switch(r1) {
                    case 0: goto Lb7;
                    case 1: goto La6;
                    case 2: goto L8d;
                    case 3: goto L74;
                    case 4: goto L5b;
                    default: goto L5a;
                }
            L5a:
                goto Lc7
            L5b:
                byte[] r5 = new byte[r2]
                r5 = {x00ec: FILL_ARRAY_DATA , data: [22, -58, 14, 14} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x00f2: FILL_ARRAY_DATA , data: [22, -58, 14, 17} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x00f8: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                goto Lc7
            L74:
                byte[] r5 = new byte[r2]
                r5 = {x00fe: FILL_ARRAY_DATA , data: [22, -58, 14, 15} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x0104: FILL_ARRAY_DATA , data: [22, -58, 14, 18} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x010a: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                goto Lc7
            L8d:
                byte[] r5 = new byte[r2]
                r5 = {x0110: FILL_ARRAY_DATA , data: [22, -58, 14, 16} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x0116: FILL_ARRAY_DATA , data: [22, -58, 14, 19} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x011c: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                goto Lc7
            La6:
                byte[] r5 = new byte[r2]
                r5 = {x0122: FILL_ARRAY_DATA , data: [22, -58, 14, 13} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x0128: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                goto Lc7
            Lb7:
                byte[] r5 = new byte[r2]
                r5 = {x012e: FILL_ARRAY_DATA , data: [22, -58, 14, 12} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
                byte[] r5 = new byte[r2]
                r5 = {x0134: FILL_ARRAY_DATA , data: [22, -58, 14, 0} // fill-array
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
            Lc7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._474.UiMgr.AnonymousClass9.onClickItem(int):void");
        }
    };
    OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 6});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 5});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
        }
    };
    OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
        }
    };
    OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 3});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 4});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
        }
    };

    public UiMgr(final Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, this.leftBtn, this.rightBtn, this.bottomBtn});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 3});
            }
        });
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 53});
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 55});
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 57});
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 56});
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == UiMgr.this.getSettingLeftIndexes(context, "car_set") && i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i3});
                }
                if (i == UiMgr.this.getSettingLeftIndexes(context, "car_set") && i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_474_buzzer_volume")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                }
                if (i == UiMgr.this.getSettingLeftIndexes(context, "car_set") && i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_474_photosensitivity")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._474.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == UiMgr.this.getSettingLeftIndexes(context, "car_set")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_0")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_474_automatic_door_lock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_474_independent_unlocking_of_driver_door")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_474_independent_trunk_lid_unlocking")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_61_radar_switch")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_143_setting_5")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "light_ctrl_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_5")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_474_trip_reset")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    }
                }
            }
        });
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

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
