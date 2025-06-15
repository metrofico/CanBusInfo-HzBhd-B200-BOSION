package com.hzbhd.canbus.car._236;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import kotlin.jvm.internal.ByteCompanionObject;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private static final String SHARE_236_PARK_ASSIST = "share_236_park_assist";
    private MsgMgr mMsgMgr;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        int currentCarId = getCurrentCarId();
        if (currentCarId != 0) {
            if (currentCarId == 1) {
                removeMainPrjBtn(context, 0, 1);
                return;
            } else if (currentCarId != 4 && currentCarId != 5) {
                return;
            }
        }
        removeSettingRightItem(context, 0, 11, 11);
    }

    public UiMgr(final Context context) {
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._236.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                if (i == 0) {
                    switch (i2) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                            break;
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                            break;
                        case 7:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                            break;
                        case 8:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) i3});
                            break;
                        case 9:
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte) i3});
                            break;
                        case 11:
                            SharePreUtil.setIntValue(context, UiMgr.SHARE_236_PARK_ASSIST, i3);
                            Log.i("ljq", "onClickItem: get: " + SharePreUtil.getIntValue(context, UiMgr.SHARE_236_PARK_ASSIST, 0));
                            UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                            break;
                    }
                }
                titleSrn.hashCode();
                if (titleSrn.equals("_236_Forward_collision_assist_mode")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                } else if (titleSrn.equals("_236_Sensitivity")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._236.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 10) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._236.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                if (i == 0) {
                    UiMgr.this.sendData(new byte[]{22, -112, 81});
                    UiMgr.this.sendData(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._236.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_236_Front_Collision_Assist_System")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) i3});
                }
            }
        });
        getAirUiSet(context).setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._236.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                if (i == 1) {
                    UiMgr.this.sendData(new byte[]{22, -112, 35});
                    UiMgr.this.sendData(new byte[]{22, -112, 54});
                }
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
}
