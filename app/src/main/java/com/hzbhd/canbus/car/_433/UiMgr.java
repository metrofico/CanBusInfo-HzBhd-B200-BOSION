package com.hzbhd.canbus.car._433;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    byte[] mMiscInfoCopy0x02;
    byte[] mMiscInfoCopy0x03;
    byte[] mMiscInfoCopy0x04;
    MsgMgr mMsgMgr;
    String KEY_BT_SWITCH = "KEY_BT_SWITCH";
    String KEY_BT_MEDIA_SWITCH = "KEY_BT_MEDIA_SWITCH";
    int byteLength = 35;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        getSettingUiSet(this.mContext).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._433.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_433_BT_Switch")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_433_BT_Switch", "_433_BT_Switch1")) {
                        if (i3 == 0) {
                            UiMgr.this.send0xCABTCmd(1, 2);
                        }
                        if (i3 == 1) {
                            UiMgr.this.send0xCABTCmd(1, 1);
                        }
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(UiMgr.this.mContext, i, i2, UiMgr.this.KEY_BT_SWITCH, i3);
                        return;
                    }
                    UiMgr uiMgr4 = UiMgr.this;
                    if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_433_BT_Switch", "_433_BT_Switch2")) {
                        if (i3 == 0) {
                            UiMgr.this.send0xCABTCmd(2, 0);
                        }
                        if (i3 == 1) {
                            UiMgr.this.send0xCABTCmd(2, 1);
                        }
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(UiMgr.this.mContext, i, i2, UiMgr.this.KEY_BT_MEDIA_SWITCH, i3);
                        return;
                    }
                    UiMgr uiMgr6 = UiMgr.this;
                    if (i2 == uiMgr6.getSettingRightIndex(uiMgr6.mContext, "_433_BT_Switch", "_433_BT_Switch3")) {
                        if (i3 == 0) {
                            UiMgr uiMgr7 = UiMgr.this;
                            uiMgr7.getMsgMgr(uiMgr7.mContext).startPanel(false);
                        } else {
                            UiMgr uiMgr8 = UiMgr.this;
                            uiMgr8.getMsgMgr(uiMgr8.mContext).startPanel(true);
                        }
                        UiMgr uiMgr9 = UiMgr.this;
                        uiMgr9.getMsgMgr(uiMgr9.mContext).updateSettings(i, i2, i3);
                    }
                }
            }
        });
        getOriginalCarDevicePageUiSet(this.mContext).setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._433.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if (i == 0) {
                    UiMgr.this.send0xC5UsbControl(5);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.send0xC5UsbControl(3);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.send0xC5UsbControl(1);
                    return;
                }
                if (i == 3) {
                    UiMgr.this.send0xC5UsbControl(2);
                } else if (i == 4) {
                    UiMgr.this.send0xC5UsbControl(4);
                } else {
                    if (i != 5) {
                        return;
                    }
                    UiMgr.this.send0xC5UsbControl(6);
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(context, "_433_BT_Switch"), getSettingRightIndex(context, "_433_BT_Switch", "_433_BT_Switch1"), SharePreUtil.getIntValue(context, this.KEY_BT_SWITCH, 0));
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(context, "_433_BT_Switch"), getSettingRightIndex(context, "_433_BT_Switch", "_433_BT_Switch2"), SharePreUtil.getIntValue(context, this.KEY_BT_MEDIA_SWITCH, 0));
    }

    public void send0xCABTCmd(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte) i, (byte) i2});
    }

    public void send0xC0SourceType(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
    }

    public void send0xC2RadioInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void send0xC3MediaInfo(int i, int i2, int i3, int i4, int i5, int i6) {
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    public void send0xC4VolInfo(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
    }

    public void send0xC5UsbControl(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte) i, 0});
    }

    public void send0xC8TimeInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public synchronized void send0xCBSendString(int i, byte[] bArr) {
        if (i == 2) {
            if (isMusicMsgRepeat0x02(bArr)) {
                return;
            }
        } else if (i == 3) {
            if (isMusicMsgRepeat0x03(bArr)) {
                return;
            }
        } else if (i == 4 && isMusicMsgRepeat0x04(bArr)) {
            return;
        }
        int length = bArr.length;
        this.byteLength = length;
        byte[] bArr2 = new byte[35];
        if (length == 53) {
            CanbusMsgSender.sendMsg(bArr);
        } else {
            if (length > 53) {
                for (int i2 = 0; i2 < 53; i2++) {
                    bArr2[i2] = bArr[i2];
                }
                CanbusMsgSender.sendMsg(bArr2);
            } else if (length < 53) {
                for (int i3 = 0; i3 < 53 - this.byteLength; i3++) {
                    bArr = SplicingByte(bArr, new byte[]{0});
                }
                CanbusMsgSender.sendMsg(bArr);
            }
        }
    }

    protected boolean isMusicMsgRepeat0x02(byte[] bArr) {
        if (Arrays.equals(bArr, this.mMiscInfoCopy0x02)) {
            return true;
        }
        this.mMiscInfoCopy0x02 = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    protected boolean isMusicMsgRepeat0x03(byte[] bArr) {
        if (Arrays.equals(bArr, this.mMiscInfoCopy0x03)) {
            return true;
        }
        this.mMiscInfoCopy0x03 = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    protected boolean isMusicMsgRepeat0x04(byte[] bArr) {
        if (Arrays.equals(bArr, this.mMiscInfoCopy0x04)) {
            return true;
        }
        this.mMiscInfoCopy0x04 = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
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
