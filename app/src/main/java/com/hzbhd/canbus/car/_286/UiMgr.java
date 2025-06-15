package com.hzbhd.canbus.car._286;

import android.content.Context;
import android.provider.Settings;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;
    private TimerUtil mTimerUtil;
    private byte[] mTrackRequestCommand;
    private TimerTask mTrackRequestTimerTask;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        this.mContext = context;
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._286.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_286_settings_item_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                        break;
                    case "_286_settings_item_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                        break;
                    case "_286_settings_item_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._286.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_286_settings_item_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                        break;
                    case "_286_settings_item_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                        break;
                    case "_286_settings_item_a":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                        break;
                    case "_286_settings_item_b":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) i3});
                        break;
                    case "_286_settings_item_c":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case "_286_settings_item_d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case "_286_settings_item_e":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, (byte) i3});
                        break;
                }
            }
        });
        getParkPageUiSet(context).setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._286.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                UiMgr.this.getTimerUtil().startTimer(UiMgr.this.getTrackRequestTimerTask(context), 0L, 150L);
            }
        });
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerUtil getTimerUtil() {
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        return this.mTimerUtil;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getTrackRequestCommand() {
        if (this.mTrackRequestCommand == null) {
            this.mTrackRequestCommand = new byte[]{22, -112, 41, 0};
        }
        return this.mTrackRequestCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerTask getTrackRequestTimerTask(final Context context) {
        if (this.mTrackRequestTimerTask == null) {
            this.mTrackRequestTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._286.UiMgr.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (Settings.System.getInt(context.getContentResolver(), "gpioLevel", 1) == 0) {
                        CanbusMsgSender.sendMsg(UiMgr.this.getTrackRequestCommand());
                    } else {
                        UiMgr.this.getTimerUtil().stopTimer();
                    }
                }
            };
        }
        return this.mTrackRequestTimerTask;
    }
}
