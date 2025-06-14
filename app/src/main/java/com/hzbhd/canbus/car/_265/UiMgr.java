package com.hzbhd.canbus.car._265;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr msgMgr;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        int i;
        int i2;
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        int i3 = this.differentId;
        if (i3 == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
        } else if (i3 == 14) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
        } else if (i3 == 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
        }
        int i4 = this.eachId;
        if (i4 != 12 && i4 != 13 && i4 != 14 && i4 != 15 && (i2 = this.differentId) != 12 && i2 != 13 && i2 != 14) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        int i5 = this.eachId;
        if (i5 != 1 && i5 != 2 && i5 != 10 && i5 != 11 && i5 != 12 && i5 != 13 && i5 != 14 && i5 != 15) {
            removeMainPrjBtn(context, 0, 1);
            getParkPageUiSet(context).setShowRadar(false);
        }
        int i6 = this.eachId;
        if (i6 != 2 && i6 != 10 && i6 != 11 && i6 != 12 && i6 != 13 && i6 != 14 && i6 != 15) {
            removeDriveDateItemForTitles(context, new String[]{"a_current_speed"});
            removeDriveDateItemForTitles(context, new String[]{"engine_speed"});
        }
        if (this.eachId == 1) {
            removeDriveData(context, 1, 1);
        }
        if (this.eachId == 12) {
            removeDriveDateItemForTitles(context, new String[]{"_246_car_info16"});
            removeDriveDateItemForTitles(context, new String[]{"_41_park_assist_system_status"});
            removeSettingRightItemByNameList(context, new String[]{"_246_paring_info2"});
        }
        int i7 = this.eachId;
        if (i7 != 13 && i7 != 14 && i7 != 15) {
            removeSettingLeftItemByNameList(context, new String[]{"car_set1"});
        }
        if (this.eachId != 12) {
            removeSettingLeftItemByNameList(context, new String[]{"_246_setting_car_info32"});
        }
        int i8 = this.eachId;
        if (i8 == 13 || i8 == 14 || i8 == 15) {
            removeDriveDateItemForTitles(this.mContext, new String[]{"light_info"});
            removeDriveDateItemForTitles(this.mContext, new String[]{"gear_position"});
            removeDriveDateItemForTitles(this.mContext, new String[]{"reverse_state"});
        }
        int i9 = this.eachId;
        if (i9 != 2 && i9 != 10 && i9 != 11 && i9 != 12 && i9 != 13 && i9 != 14 && i9 != 15) {
            removeDriveDateItemForTitles(this.mContext, new String[]{"a_current_speed"});
            removeDriveDateItemForTitles(this.mContext, new String[]{"engine_speed"});
        }
        int i10 = this.eachId;
        if (i10 == 12 || i10 == 13 || i10 == 14 || i10 == 15) {
            removeDriveDateItemForTitles(this.mContext, new String[]{"keyboard_backlight_adjustment"});
        }
        int i11 = this.eachId;
        if (i11 == 1 || i11 == 12) {
            removeDriveDateItemForTitles(this.mContext, new String[]{"_246_car_info16"});
        }
        int i12 = this.eachId;
        if (i12 != 10 && i12 != 12 && i12 != 13 && i12 != 14 && i12 != 15 && (i = this.differentId) != 12 && i != 13 && i != 14) {
            removeDriveDateItemForTitles(this.mContext, new String[]{"_370_Time"});
        }
        int i13 = this.eachId;
        if (i13 == 1 || i13 == 2 || i13 == 10) {
            airUiSet.setHaveRearArea(true);
        } else {
            airUiSet.setHaveRearArea(false);
        }
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._265.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i14, int i15, int i16) {
                UiMgr uiMgr = UiMgr.this;
                if (i14 == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_246_setting_car_info32")) {
                    String titleSrn = settingUiSet.getList().get(i14).getItemList().get(i15).getTitleSrn();
                    titleSrn.hashCode();
                    switch (titleSrn) {
                        case "_337_setting_42":
                            CanbusMsgSender.sendMsg(new byte[]{22, -57, 3, 1, (byte) i16});
                            break;
                        case "_332_setting_5":
                            CanbusMsgSender.sendMsg(new byte[]{22, -57, 2, 3, (byte) i16});
                            break;
                        case "_332_setting_6":
                            CanbusMsgSender.sendMsg(new byte[]{22, -57, 2, 2, (byte) i16});
                            break;
                        case "_332_setting_9":
                            CanbusMsgSender.sendMsg(new byte[]{22, -57, 2, 1, (byte) i16});
                            break;
                        case "_189_car_setting_A":
                            CanbusMsgSender.sendMsg(new byte[]{22, -57, 3, 2, (byte) i16});
                            break;
                        case "language_setup":
                            CanbusMsgSender.sendMsg(new byte[]{22, -57, 1, 1, (byte) i16});
                            break;
                    }
                }
                UiMgr uiMgr2 = UiMgr.this;
                if (i14 == uiMgr2.getSettingLeftIndexes(uiMgr2.mContext, "car_set1")) {
                    String titleSrn2 = settingUiSet.getList().get(i14).getItemList().get(i15).getTitleSrn();
                    titleSrn2.hashCode();
                    if (titleSrn2.equals("_246_setting_car_info28")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i16});
                    } else if (titleSrn2.equals("_246_paring_info2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i16});
                    }
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i14 == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_265_oreginal_camera_setting")) {
                    String titleSrn3 = settingUiSet.getList().get(i14).getItemList().get(i15).getTitleSrn();
                    titleSrn3.hashCode();
                    if (titleSrn3.equals("_265_oreginal_camera_setting1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) (i16 + 1)});
                    } else if (titleSrn3.equals("_265_oreginal_camera_setting2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) (i16 + 6)});
                    }
                }
            }
        });
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._265.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i14) {
                UiMgr.this.activeRequestData(7);
                UiMgr.this.activeRequestData(8);
                UiMgr.this.activeRequestData(20);
                UiMgr.this.activeRequestData(36);
                UiMgr.this.activeRequestData(37);
                UiMgr.this.activeRequestData(57);
                UiMgr.this.activeRequestData(65);
            }
        });
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

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
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
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
