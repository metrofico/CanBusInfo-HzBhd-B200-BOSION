package com.hzbhd.canbus.car._461;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    public UiMgr(final Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._461.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == UiMgr.this.getSettingLeftIndexes(context, "_461_setting_config")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_config", "_461_setting_config_language")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i3, -1, -1, -1, -1});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_config", "_461_setting_config_fuel_unit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, (byte) i3, -1, -1, -1});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_config", "_461_setting_config_temp_unit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, -1, (byte) i3, -1, -1});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_config", "_461_setting_config_date")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, -1, -1, (byte) i3, -1});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_config", "_461_setting_config_time")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, -1, -1, -1, (byte) i3});
                    }
                }
                if (i == UiMgr.this.getSettingLeftIndexes(context, "_461_setting_set")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_three_lighting")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 4, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_running_lights")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_welcome_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 6, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_car_door_locks")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_relock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_interlock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, (byte) i3});
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._461.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i == UiMgr.this.getSettingLeftIndexes(context, "_461_setting_set")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_tire_reset")) {
                        Toast.makeText(context, "RESETTING...", 0).show();
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_measurement")) {
                        Toast.makeText(context, "MEASURING...", 0).show();
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 10, 1});
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._461.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == UiMgr.this.getSettingLeftIndexes(context, "_461_setting_set")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_speed")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_461_setting_set", "_461_setting_set_home_lighting")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, (byte) i3});
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
}
