package com.hzbhd.canbus.car._104;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private MsgMgr mMsgMgr;

    public UiMgr(final Context context) {
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._104.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_279_temperature_unit":
                        UiMgr.this.set0x82Datas(context, 5, i3);
                        break;
                    case "_283_meter_value_5":
                        UiMgr.this.set0x82Datas(context, 4, i3);
                        break;
                    case "_279_distance_unit":
                        UiMgr.this.set0x82Datas(context, 2, i3);
                        break;
                    case "vm_golf7_vehicle_setup_hour_format":
                        UiMgr.this.set0x82Datas(context, 6, i3);
                        break;
                    case "_1104_window_switch":
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 4, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettingsItem(i, i2, Integer.valueOf(i3));
                        break;
                    case "_250_language":
                        UiMgr.this.set0x82Datas(context, 3, i3);
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._104.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_1104_right_seat_heat":
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, 0});
                        break;
                    case "_1104_left_seat_heat":
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, 0});
                        break;
                    case "_283_radar_switch":
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 3, 0});
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void set0x82Datas(Context context, int i, int i2) {
        byte[] bArr = getMsgMgr(context).get0x04Datas();
        bArr[0] = 22;
        bArr[1] = -126;
        bArr[i] = (byte) i2;
        CanbusMsgSender.sendMsg(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
