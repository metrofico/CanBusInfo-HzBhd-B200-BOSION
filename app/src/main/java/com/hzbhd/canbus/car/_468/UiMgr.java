package com.hzbhd.canbus.car._468;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr mMsgMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");

    public UiMgr(Context context) {
        this.mContext = context;
        getPanelKeyPageUiSet(context).setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._468.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int i) {
                switch (i) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 1});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 2});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 8});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 4});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 3});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 10});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 11});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 7});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 9});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 14});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 5});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
                    case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 6});
                        CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                        break;
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
