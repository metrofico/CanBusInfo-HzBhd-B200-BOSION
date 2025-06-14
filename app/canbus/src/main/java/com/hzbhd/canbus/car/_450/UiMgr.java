package com.hzbhd.canbus.car._450;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
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
    public static int data0bit3;
    public static int data0bit4;
    public static int data0bit5;
    public static int data0bit6;
    public static int data0bit7;
    Context mContext;
    private MsgMgr mMsgMgr;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(final Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._450.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                char c;
                char c2;
                char c3;
                int i4;
                String str;
                if (i == UiMgr.this.getSettingLeftIndexes(context, "_450_bmw_car_10_left1")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_10_left1", "_450_bmw_car_11")) {
                        UiMgr.data0bit7 = i3;
                        UiMgr.this.send0x9A_0x01_bit3(i3);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_10_left1", "_450_bmw_car_12")) {
                        UiMgr.data0bit6 = i3;
                        UiMgr.this.send0x9A_0x01_bit3(i3);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_10_left1", "_450_bmw_car_13")) {
                        UiMgr.data0bit5 = i3;
                        UiMgr.this.send0x9A_0x01_bit3(i3);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_10_left1", "_450_bmw_car_14")) {
                        UiMgr.data0bit4 = i3;
                        UiMgr.this.send0x9A_0x01_bit3(i3);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_10_left1", "_450_bmw_car_15")) {
                        UiMgr.data0bit3 = i3;
                        UiMgr.this.send0x9A_0x01_bit3(i3);
                    }
                }
                if (i == UiMgr.this.getSettingLeftIndexes(context, "_450_bmw_car_18_left2")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_19")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 18, (byte) DataHandleUtils.getDecimalFrom8Bit(i3, 0, 0, 0, 0, 0, 0, 0)});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_20")) {
                        if (i3 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, 0, 0, 0)});
                        } else if (i3 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 0, 0)});
                        } else if (i3 == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 0, 0, 0, 0, 0)});
                        } else if (i3 == 3) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 1, 0, 0, 0, 0)});
                        }
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_21")) {
                        if (i3 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 24, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 0, 0)});
                        } else if (i3 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 24, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 0, 0, 0, 0, 0)});
                        } else if (i3 == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 24, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 1, 0, 0, 0, 0)});
                        }
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_22")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 25, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, i3, 0, 0)});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_23")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 26, (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, 0, 0, i3)});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_24")) {
                        c = 1;
                        c2 = 2;
                        c3 = 3;
                        i4 = 4;
                        str = "_450_bmw_car_18_left2";
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 33, (byte) DataHandleUtils.getDecimalFrom8Bit(i, 0, 0, 0, 0, 0, 0, 0)});
                    } else {
                        c = 1;
                        c2 = 2;
                        c3 = 3;
                        i4 = 4;
                        str = "_450_bmw_car_18_left2";
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_25")) {
                        byte[] bArr = new byte[i4];
                        bArr[0] = 22;
                        bArr[c] = -102;
                        bArr[c2] = 34;
                        bArr[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(0, i3, 0, 0, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_26")) {
                        byte[] bArr2 = new byte[i4];
                        bArr2[0] = 22;
                        bArr2[c] = -102;
                        bArr2[c2] = 35;
                        bArr2[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, i3, 0, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr2);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_27")) {
                        byte[] bArr3 = new byte[i4];
                        bArr3[0] = 22;
                        bArr3[c] = -102;
                        bArr3[c2] = 33;
                        bArr3[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(i3, 0, 0, 0, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr3);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_29")) {
                        byte[] bArr4 = new byte[i4];
                        bArr4[0] = 22;
                        bArr4[c] = -102;
                        bArr4[c2] = 49;
                        bArr4[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(i3, 0, 0, 0, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr4);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_30")) {
                        byte[] bArr5 = new byte[i4];
                        bArr5[0] = 22;
                        bArr5[c] = -102;
                        bArr5[c2] = 50;
                        bArr5[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(0, i3, 0, 0, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr5);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_31")) {
                        byte[] bArr6 = new byte[i4];
                        bArr6[0] = 22;
                        bArr6[c] = -102;
                        bArr6[c2] = 51;
                        bArr6[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, i3, 0, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr6);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_32")) {
                        byte[] bArr7 = new byte[i4];
                        bArr7[0] = 22;
                        bArr7[c] = -102;
                        bArr7[c2] = 52;
                        bArr7[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, i3, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr7);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_33")) {
                        byte[] bArr8 = new byte[i4];
                        bArr8[0] = 22;
                        bArr8[c] = -102;
                        bArr8[c2] = 53;
                        bArr8[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, i3, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr8);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_34")) {
                        byte[] bArr9 = new byte[i4];
                        bArr9[0] = 22;
                        bArr9[c] = -102;
                        bArr9[c2] = 54;
                        bArr9[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, i3, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr9);
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, str, "_450_bmw_car_35")) {
                        byte[] bArr10 = new byte[i4];
                        bArr10[0] = 22;
                        bArr10[c] = -102;
                        bArr10[c2] = 65;
                        bArr10[c3] = (byte) DataHandleUtils.getDecimalFrom8Bit(i3, 0, 0, 0, 0, 0, 0, 0);
                        CanbusMsgSender.sendMsg(bArr10);
                    }
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._450.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == UiMgr.this.getSettingLeftIndexes(context, "_450_bmw_car_18_left2")) {
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_28")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 36, (byte) i3});
                    }
                    if (i2 == UiMgr.this.getSettingRightIndex(context, "_450_bmw_car_18_left2", "_450_bmw_car_36")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 66, (byte) DataHandleUtils.getMsb(i3), (byte) DataHandleUtils.getLsb(i3)});
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0x9A_0x01_bit3(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) DataHandleUtils.getDecimalFrom8Bit(data0bit7, data0bit6, data0bit5, data0bit4, data0bit3, 0, 0, 0)});
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
