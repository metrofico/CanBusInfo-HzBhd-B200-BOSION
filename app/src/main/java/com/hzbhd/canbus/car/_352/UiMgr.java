package com.hzbhd.canbus.car._352;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._352.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_352_setting_zhi_nan")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan1")) {
                    UiMgr.this.send0xC9(1, 1);
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._352.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_352_setting_zhi_nan")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan2")) {
                    UiMgr.this.send0xC9(2, i3);
                }
            }
        }
    };
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._352.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.requestData(22, 0);
            UiMgr.this.requestData(113, 0);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    private void initUi() {
    }

    private void intiData() {
    }

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
        intiData();
    }

    public void makeConnetion() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send0xC9(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -55, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    public void send0xC0(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
    }

    public void send0xC2(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void send0xC3(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7});
    }

    public void send0xC4(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
    }

    public void send0xC5(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte) i});
    }

    public void send0xC8(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i, (byte) i2, (byte) i3, (byte) i4});
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
