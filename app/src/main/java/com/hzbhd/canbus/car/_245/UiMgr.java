package com.hzbhd.canbus.car._245;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.ByteCompanionObject;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private MsgMgr msgMgr;
    public SettingPageUiSet settingPageUiSet;
    public final Map<String, Integer> settingPageIndex = new HashMap();
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._245.UiMgr$$ExternalSyntheticLambda0
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public final void onClickItem(int i, int i2, int i3) {
            this.f$0.m326lambda$new$0$comhzbhdcanbuscar_245UiMgr(i, i2, i3);
        }
    };

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_245-UiMgr, reason: not valid java name */
    /* synthetic */ void m326lambda$new$0$comhzbhdcanbuscar_245UiMgr(int i, int i2, int i3) {
        if (i == 0) {
            if (i2 != 0) {
                return;
            }
            sendSettingData(0, i3);
            return;
        }
        if (i == 1) {
            if (i2 == 0) {
                sendSettingData(1, i3);
                return;
            }
            if (i2 == 1) {
                sendSettingData(2, i3);
                return;
            } else if (i2 == 2) {
                sendSettingData(3, i3);
                return;
            } else {
                if (i2 != 3) {
                    return;
                }
                sendSettingData(4, i3);
                return;
            }
        }
        if (i == 2) {
            if (i2 == 0) {
                sendSettingData(16, i3);
                return;
            }
            if (i2 == 1) {
                sendSettingData(17, i3);
                return;
            }
            if (i2 == 2) {
                sendSettingData(18, i3);
                return;
            }
            if (i2 == 3) {
                sendSettingData(19, i3);
                return;
            } else if (i2 == 4) {
                sendSettingData(20, i3);
                return;
            } else {
                if (i2 != 5) {
                    return;
                }
                sendSettingData(21, i3);
                return;
            }
        }
        if (i == 3) {
            if (i2 == 0) {
                sendSettingData(32, i3);
                return;
            } else {
                if (i2 != 1) {
                    return;
                }
                sendSettingData(33, i3);
                return;
            }
        }
        if (i == 4) {
            switch (i2) {
                case 0:
                    sendSettingData(48, i3);
                    break;
                case 1:
                    sendSettingData(49, i3);
                    break;
                case 2:
                    sendSettingData(50, i3);
                    break;
                case 3:
                    sendSettingData(51, i3);
                    break;
                case 4:
                    sendSettingData(52, i3);
                    break;
                case 5:
                    sendSettingData(53, i3);
                    break;
                case 6:
                    sendSettingData(54, i3);
                    break;
                case 7:
                    sendSettingData(55, i3);
                    break;
            }
            return;
        }
        if (i != 5) {
            return;
        }
        if (i2 == 0) {
            sendSettingData(NfDef.STATE_3WAY_M_HOLD, i3);
            return;
        }
        if (i2 == 1) {
            sendSettingData(241, i3);
            return;
        }
        if (i2 == 2) {
            sendSettingData(242, i3);
            return;
        }
        if (i2 == 3) {
            sendSettingData(243, i3);
        } else if (i2 == 4) {
            sendSettingData(NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD, i3);
        } else {
            if (i2 != 5) {
                return;
            }
            sendSettingData(57, i3);
        }
    }

    public UiMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._245.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(64, 0);
            }
        });
        if (getCurrentCarId() != 4 && getCurrentCarId() != 5 && getCurrentCarId() != 7 && getCurrentCarId() != 10) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        getAirUiSet(context).getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._245.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(33, 0);
            }
        });
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._245.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.activeRequest(38, 0);
                UiMgr.this.activeRequest(80, 0);
            }
        });
    }

    private void sendSettingData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, (byte) i, (byte) i2});
    }

    public Map<String, Integer> initSettingPageIndex(Context context) {
        List<SettingPageUiSet.ListBean> list = getSettingUiSet(context).getList();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, (byte) i, (byte) i2});
    }
}
