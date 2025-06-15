package com.hzbhd.canbus.car._218;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;


public class UiMgr extends AbstractUiMgr {
    private static final String SHARE_218_SETTING_DATA0 = "share_218_setting_data0";
    private Context mContext;
    private MsgMgr mMsgMgr;
    private int data0 = 0;
    private int data1 = 1;
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        initSetting(context);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._218.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_218_setting_0_0":
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.data0 = DataHandleUtils.setIntByteWithBit(uiMgr.data0, 0, i3 == 1);
                        break;
                    case "_218_setting_0_1":
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.data0 = DataHandleUtils.setIntByteWithBit(uiMgr2.data0, 1, i3 == 1);
                        break;
                    case "_218_setting_0_2":
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.data0 = DataHandleUtils.setIntByteWithBit(uiMgr3.data0, 2, i3 == 1);
                        break;
                    case "_218_setting_0_3":
                        UiMgr uiMgr4 = UiMgr.this;
                        uiMgr4.data0 = DataHandleUtils.setIntByteWithBit(uiMgr4.data0, 3, i3 == 1);
                        break;
                    case "_218_setting_0_4":
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.data0 = DataHandleUtils.setIntByteWithBit(uiMgr5.data0, 4, i3 == 1);
                        break;
                    case "_218_setting_1_0":
                        UiMgr uiMgr6 = UiMgr.this;
                        uiMgr6.data1 = DataHandleUtils.setIntByteWithBit(uiMgr6.data1, 0, i3 == 1);
                        break;
                    case "_218_setting_1_1":
                        UiMgr uiMgr7 = UiMgr.this;
                        uiMgr7.data1 = DataHandleUtils.setIntByteWithBit(uiMgr7.data1, 1, i3 == 1);
                        break;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte) UiMgr.this.data0, FutureUtil.instance.isDiscExist() ? (byte) 1 : (byte) 0, 0, 0});
                UiMgr.this.getMsgMgr(context).updateSetting(i, i2, i3);
                SharePreUtil.setIntValue(context, UiMgr.SHARE_218_SETTING_DATA0, UiMgr.this.data0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private void initSetting(Context context) {
        this.data0 = SharePreUtil.getIntValue(context, SHARE_218_SETTING_DATA0, 0);
        getMsgMgr(context).updateSetting(this.data0);
    }

    int getData0() {
        return this.data0;
    }
}
