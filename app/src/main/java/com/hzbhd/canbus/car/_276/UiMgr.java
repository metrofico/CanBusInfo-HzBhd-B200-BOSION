package com.hzbhd.canbus.car._276;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._276.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, (byte) (2 - i3)});
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, (byte) (2 - i3)});
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte) (i3 + 1)});
                } else if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 7, (byte) i3});
                }
            } else {
                if (i != 1) {
                    switch (UiMgr.this.mDifferentId) {
                        case 2:
                        case 7:
                        case 9:
                        case 10:
                            if (i2 == 0) {
                                CanbusMsgSender.sendMsg(new byte[]{22, 123, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                                break;
                            } else if (i2 == 1) {
                                CanbusMsgSender.sendMsg(new byte[]{22, 123, 5, (byte) i3});
                                break;
                            } else if (i2 == 2) {
                                CanbusMsgSender.sendMsg(new byte[]{22, 123, 16, (byte) i3});
                                break;
                            }
                            break;
                        case 3:
                            switch (i2) {
                                case 0:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                                    break;
                                case 1:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 17, (byte) i3});
                                    break;
                                case 2:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 18, (byte) i3});
                                    break;
                                case 3:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 19, (byte) i3});
                                    break;
                                case 4:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 20, (byte) i3});
                                    break;
                                case 5:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 21, (byte) i3});
                                    break;
                                case 6:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 23, (byte) i3});
                                    break;
                            }
                        case 4:
                            switch (i2) {
                                case 0:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                                    break;
                                case 1:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 17, (byte) i3});
                                    break;
                                case 2:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 18, (byte) i3});
                                    break;
                                case 3:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 19, (byte) i3});
                                    break;
                                case 4:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 20, (byte) i3});
                                    break;
                                case 5:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 21, (byte) i3});
                                    break;
                                case 6:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 22, (byte) i3});
                                    break;
                                case 7:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 23, (byte) i3});
                                    break;
                                case 8:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 24, (byte) i3});
                                    break;
                            }
                        case 5:
                        case 6:
                            switch (i2) {
                                case 0:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                                    break;
                                case 1:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 5, (byte) i3});
                                    break;
                                case 2:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 16, (byte) i3});
                                    break;
                                case 3:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 19, (byte) i3});
                                    break;
                                case 4:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 20, (byte) i3});
                                    break;
                                case 5:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 25, (byte) i3});
                                    break;
                                case 6:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 26, (byte) i3});
                                    break;
                                case 7:
                                    CanbusMsgSender.sendMsg(new byte[]{22, 123, 27, (byte) i3});
                                    break;
                            }
                    }
                    return;
                }
                if (i2 == 0) {
                    switch (i3) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 1});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 3});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 4});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 5});
                            break;
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 7});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 8});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 9});
                            break;
                        case 7:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 16});
                            break;
                        case 8:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 21});
                            break;
                    }
                }
            }
        }
    };
    private int mDifferentId = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.mDifferentId;
        if (i == 0) {
            removeMainPrjBtn(this.mContext, 0, 1);
        }
        switch (i) {
            case 2:
            case 7:
            case 9:
            case 10:
                removeSettingRightItem(this.mContext, 2, 3, 3);
                removeSettingRightItem(this.mContext, 2, 3, 3);
                break;
            case 3:
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_0"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"light_ctrl_3"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_8"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_9"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_10"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_5"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_7"});
                break;
            case 4:
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_0"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"light_ctrl_3"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_8"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_9"});
                removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_10"});
                break;
            case 5:
            case 6:
                removeSettingRightItem(this.mContext, 2, 3, 12);
                removeSettingRightItem(this.mContext, 2, 5, 8);
                break;
            case 8:
                removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
                break;
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        if (!MyApplication.IS_SET) {
            updateUiByDifferentCar(context);
            MyApplication.IS_SET = true;
        }
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
        if (this.mDifferentId == 1) {
            parkPageUiSet.setShowRadar(false);
        }
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._276.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i != 3) {
                    return;
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 27, 2, 2, 1, -1});
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, 27, 3, 3, 1, -1});
                }
            }
        });
    }
}
