package com.hzbhd.canbus.car._431;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    public static int now_model1_B;
    public static int now_model1_G;
    public static int now_model1_R;
    public static int now_model2_B;
    public static int now_model2_G;
    public static int now_model2_R;
    public static int now_model3_B;
    public static int now_model3_G;
    public static int now_model3_R;
    Context mContext;
    MsgMgr mMsgMgr;
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._431.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(49);
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._431.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_431_panoramic")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_431_panoramic", "_431_panoramic1")) {
                    if (i3 == 1) {
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.getMsgMgr(uiMgr3.mContext).showButton();
                    } else {
                        UiMgr uiMgr4 = UiMgr.this;
                        uiMgr4.getMsgMgr(uiMgr4.mContext).hideButton();
                    }
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(i, i2, i3);
                }
            }
        }
    };
    OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._431.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequest(97);
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._431.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_431_setting0")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_431_setting0", "_431_setting_l")) {
                    UiMgr.this.sendLight0x6F(2, i3, 0, 0);
                }
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_431_setting0", "_431_setting_r")) {
                    UiMgr.now_model1_R = i3;
                    UiMgr.this.sendLight0x6F(1, UiMgr.now_model1_R, UiMgr.now_model1_G, UiMgr.now_model1_B);
                }
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_431_setting0", "_431_setting_g")) {
                    UiMgr.now_model1_G = i3;
                    UiMgr.this.sendLight0x6F(1, UiMgr.now_model1_R, UiMgr.now_model1_G, UiMgr.now_model1_B);
                }
                UiMgr uiMgr5 = UiMgr.this;
                if (i2 == uiMgr5.getSettingRightIndex(uiMgr5.mContext, "_431_setting0", "_431_setting_b")) {
                    UiMgr.now_model1_B = i3;
                    UiMgr.this.sendLight0x6F(1, UiMgr.now_model1_R, UiMgr.now_model1_G, UiMgr.now_model1_B);
                }
            }
            UiMgr uiMgr6 = UiMgr.this;
            if (i == uiMgr6.getSettingLeftIndexes(uiMgr6.mContext, "_431_setting1")) {
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_431_setting1", "_431_setting_l")) {
                    UiMgr.this.sendLight0x6F(4, i3, 0, 0);
                }
                UiMgr uiMgr8 = UiMgr.this;
                if (i2 == uiMgr8.getSettingRightIndex(uiMgr8.mContext, "_431_setting1", "_431_setting_r")) {
                    UiMgr.now_model2_R = i3;
                    UiMgr.this.sendLight0x6F(3, UiMgr.now_model2_R, UiMgr.now_model2_G, UiMgr.now_model2_B);
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_431_setting1", "_431_setting_g")) {
                    UiMgr.now_model2_G = i3;
                    UiMgr.this.sendLight0x6F(3, UiMgr.now_model2_R, UiMgr.now_model2_G, UiMgr.now_model2_B);
                }
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_431_setting1", "_431_setting_b")) {
                    UiMgr.now_model2_B = i3;
                    UiMgr.this.sendLight0x6F(3, UiMgr.now_model2_R, UiMgr.now_model2_G, UiMgr.now_model2_B);
                }
            }
            UiMgr uiMgr11 = UiMgr.this;
            if (i == uiMgr11.getSettingLeftIndexes(uiMgr11.mContext, "_431_setting2")) {
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_431_setting2", "_431_setting_l")) {
                    UiMgr.this.sendLight0x6F(6, i3, 0, 0);
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_431_setting2", "_431_setting_r")) {
                    UiMgr.now_model3_R = i3;
                    UiMgr.this.sendLight0x6F(5, UiMgr.now_model3_R, UiMgr.now_model3_G, UiMgr.now_model3_B);
                }
                UiMgr uiMgr14 = UiMgr.this;
                if (i2 == uiMgr14.getSettingRightIndex(uiMgr14.mContext, "_431_setting2", "_431_setting_g")) {
                    UiMgr.now_model3_G = i3;
                    UiMgr.this.sendLight0x6F(5, UiMgr.now_model3_R, UiMgr.now_model3_G, UiMgr.now_model3_B);
                }
                UiMgr uiMgr15 = UiMgr.this;
                if (i2 == uiMgr15.getSettingRightIndex(uiMgr15.mContext, "_431_setting2", "_431_setting_b")) {
                    UiMgr.now_model3_B = i3;
                    UiMgr.this.sendLight0x6F(5, UiMgr.now_model3_R, UiMgr.now_model3_G, UiMgr.now_model3_B);
                }
            }
        }
    };
    OnPanoramicItemTouchListener onPanoramicItemTouchListener = new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._431.UiMgr.5
        private int getLsb(int i) {
            return ((i & 65535) >> 0) & 255;
        }

        private int getMsb(int i) {
            return ((i & 65535) >> 8) & 255;
        }

        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
        public void onTouchItem(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            UiMgr.this.sendPanoramicXY0x2C(getMsb(x), getLsb(x), getMsb(y), getLsb(y));
        }
    };
    int now360State = 0;
    OnPanoramicItemClickListener onPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._431.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendPanoramic0xFD(2, 1);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendPanoramic0xFD(2, 2);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendPanoramic0xFD(2, 3);
                return;
            }
            if (i == 3) {
                UiMgr.this.sendPanoramic0xFD(2, 4);
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    return;
                }
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).forceReverse(false);
                return;
            }
            if (UiMgr.this.now360State == 0) {
                UiMgr.this.sendPanoramic0xFD(4, 1);
                UiMgr.this.now360State = 1;
            } else {
                UiMgr.this.sendPanoramic0xFD(4, 3);
                UiMgr.this.now360State = 0;
            }
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
        parkPageUiSet.setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
        parkPageUiSet.setOnPanoramicItemTouchListener(this.onPanoramicItemTouchListener);
        getAirUiSet(this.mContext).getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (getCurrentCarId() == 1) {
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendLight0x6F(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
    }

    public void carType(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) i, 15});
    }

    public void sendTimeInfo(int i, int i2, int i3, int i4, int i5, int i6) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i, (byte) i2, 0, 0, (byte) i3, (byte) i4, (byte) i5, (byte) i6, 0});
    }

    public void sendPanoramic0xFD(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -3, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPanoramicXY0x2C(int i, int i2, int i3, int i4) {
        byte b = (byte) i;
        byte b2 = (byte) i2;
        byte b3 = (byte) i3;
        byte b4 = (byte) i4;
        CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, b, b2, b3, b4, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, b, b2, b3, b4, 0});
    }

    public void sendMediaInfo0x91(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -111, (byte) i}, bArr));
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

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    public boolean isLandscape() {
        return this.mContext.getResources().getConfiguration().orientation == 2;
    }

    public boolean isPortrait() {
        return this.mContext.getResources().getConfiguration().orientation == 1;
    }
}
