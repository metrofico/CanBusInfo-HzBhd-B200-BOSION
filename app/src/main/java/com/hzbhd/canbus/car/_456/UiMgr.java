package com.hzbhd.canbus.car._456;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private MsgMgr mMsgMgr;

    public UiMgr(final Context context) {
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._456.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 56});
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 55});
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._456.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 56});
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._456.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                int i4;
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
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_5")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_60")) {
                        i4 = 4;
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) DataHandleUtils.getDecimalFrom8Bit(i3, UiMgr.this.getMsgMgr(context).state61, UiMgr.this.getMsgMgr(context).state62, UiMgr.this.getMsgMgr(context).state63, UiMgr.this.getMsgMgr(context).state64, UiMgr.this.getMsgMgr(context).state65, UiMgr.this.getMsgMgr(context).state66, 0)});
                    } else {
                        i4 = 4;
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_61")) {
                        byte[] bArr = new byte[i4];
                        bArr[0] = 22;
                        bArr[1] = -58;
                        bArr[2] = 10;
                        bArr[3] = (byte) DataHandleUtils.getDecimalFrom8Bit(UiMgr.this.getMsgMgr(context).state60, i3, UiMgr.this.getMsgMgr(context).state62, UiMgr.this.getMsgMgr(context).state63, UiMgr.this.getMsgMgr(context).state64, UiMgr.this.getMsgMgr(context).state65, UiMgr.this.getMsgMgr(context).state66, 0);
                        CanbusMsgSender.sendMsg(bArr);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_62")) {
                        byte[] bArr2 = new byte[i4];
                        bArr2[0] = 22;
                        bArr2[1] = -58;
                        bArr2[2] = 10;
                        bArr2[3] = (byte) DataHandleUtils.getDecimalFrom8Bit(UiMgr.this.getMsgMgr(context).state60, UiMgr.this.getMsgMgr(context).state61, i3, UiMgr.this.getMsgMgr(context).state63, UiMgr.this.getMsgMgr(context).state64, UiMgr.this.getMsgMgr(context).state65, UiMgr.this.getMsgMgr(context).state66, 0);
                        CanbusMsgSender.sendMsg(bArr2);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_63")) {
                        byte[] bArr3 = new byte[i4];
                        bArr3[0] = 22;
                        bArr3[1] = -58;
                        bArr3[2] = 10;
                        bArr3[3] = (byte) DataHandleUtils.getDecimalFrom8Bit(UiMgr.this.getMsgMgr(context).state60, UiMgr.this.getMsgMgr(context).state61, UiMgr.this.getMsgMgr(context).state62, i3, UiMgr.this.getMsgMgr(context).state64, UiMgr.this.getMsgMgr(context).state65, UiMgr.this.getMsgMgr(context).state66, 0);
                        CanbusMsgSender.sendMsg(bArr3);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_64")) {
                        byte[] bArr4 = new byte[i4];
                        bArr4[0] = 22;
                        bArr4[1] = -58;
                        bArr4[2] = 10;
                        bArr4[3] = (byte) DataHandleUtils.getDecimalFrom8Bit(UiMgr.this.getMsgMgr(context).state60, UiMgr.this.getMsgMgr(context).state61, UiMgr.this.getMsgMgr(context).state62, UiMgr.this.getMsgMgr(context).state63, i3, UiMgr.this.getMsgMgr(context).state65, UiMgr.this.getMsgMgr(context).state66, 0);
                        CanbusMsgSender.sendMsg(bArr4);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_65")) {
                        byte[] bArr5 = new byte[i4];
                        bArr5[0] = 22;
                        bArr5[1] = -58;
                        bArr5[2] = 10;
                        bArr5[3] = (byte) DataHandleUtils.getDecimalFrom8Bit(UiMgr.this.getMsgMgr(context).state60, UiMgr.this.getMsgMgr(context).state61, UiMgr.this.getMsgMgr(context).state62, UiMgr.this.getMsgMgr(context).state63, UiMgr.this.getMsgMgr(context).state64, i3, UiMgr.this.getMsgMgr(context).state66, 0);
                        CanbusMsgSender.sendMsg(bArr5);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_66")) {
                        byte[] bArr6 = new byte[i4];
                        bArr6[0] = 22;
                        bArr6[1] = -58;
                        bArr6[2] = 10;
                        bArr6[3] = (byte) DataHandleUtils.getDecimalFrom8Bit(UiMgr.this.getMsgMgr(context).state60, UiMgr.this.getMsgMgr(context).state61, UiMgr.this.getMsgMgr(context).state62, UiMgr.this.getMsgMgr(context).state63, UiMgr.this.getMsgMgr(context).state64, UiMgr.this.getMsgMgr(context).state65, i3, 0);
                        CanbusMsgSender.sendMsg(bArr6);
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._456.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == UiMgr.this.getSettingLeftIndexes(context, "car_set") && i2 == UiMgr.this.getSettingRightIndex(context, "car_set", "_456_settings_1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i3});
                }
            }
        });
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
