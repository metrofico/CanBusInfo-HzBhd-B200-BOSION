package com.hzbhd.canbus.car._454;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context context;
    private MsgMgr mMsgMgr;
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._454.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.context, "_454_reset")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.context, "_454_reset", "_454_reset0")) {
                    UiMgr.this.sendData0x82(0, 0);
                    Toast.makeText(UiMgr.this.context, "Reset...", 0).show();
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.context, "_454_reset", "_454_reset1")) {
                    UiMgr.this.sendData0x82(1, 0);
                    Toast.makeText(UiMgr.this.context, "Reset...", 0).show();
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.context, "_454_reset", "_454_reset2")) {
                    UiMgr.this.sendData0x82(2, 0);
                    Toast.makeText(UiMgr.this.context, "Reset...", 0).show();
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.context, "_454_reset", "_454_reset3")) {
                    UiMgr.this.sendData0x82(3, 0);
                    Toast.makeText(UiMgr.this.context, "Reset...", 0).show();
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._454.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.context, "_454_setting")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.context, "_454_setting", "_454_setting10")) {
                    UiMgr.this.sendData0x82(6, i3);
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.context, "_454_setting", "_454_setting11")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte) DataHandleUtils.getMsb(i3), (byte) DataHandleUtils.getLsb(i3), 0, 0, 0});
                }
            }
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._454.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.context, "_454_setting")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.context, "_454_setting", "_454_setting0")) {
                    UiMgr.this.sendData0x82(5, i3);
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.context, "_454_setting", "_454_setting1")) {
                    UiMgr.this.sendData0x82(7, i3);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.context, "_454_setting", "_454_setting2")) {
                    UiMgr.this.sendData0x82(8, i3);
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.context, "_454_setting", "_454_setting3")) {
                    UiMgr.this.sendData0x82(9, i3);
                }
                UiMgr uiMgr6 = UiMgr.this;
                if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.context, "_454_setting", "_454_setting4")) {
                    UiMgr.this.sendData0x82(10, i3);
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.context, "_454_setting", "_454_setting5")) {
                    UiMgr.this.sendData0x82(11, i3);
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.context, "_454_setting", "_454_setting6")) {
                    UiMgr.this.sendData0x82(12, i3);
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.context, "_454_setting", "_454_setting7")) {
                    UiMgr.this.sendData0x82(15, i3);
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.context, "_454_setting", "_454_setting8")) {
                    UiMgr.this.sendData0x82(16, i3);
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.context, "_454_setting", "_454_setting9")) {
                    UiMgr.this.sendData0x82(14, i3);
                }
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.context, "_454_setting", "_454_setting12")) {
                    UiMgr.this.sendData0x82(13, i3);
                }
            }
        }
    };

    public UiMgr(Context context) {
        this.context = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendData0x82(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i, (byte) i2});
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
