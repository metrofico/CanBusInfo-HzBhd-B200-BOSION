package com.hzbhd.canbus.car._287;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;


public class UiMgr extends AbstractUiMgr {
    static final String SHARE_287_IS_SUPPORT_PANORAMIC = "share_287_is_support_panoramic";
    private Context mContext;
    private MsgMgr mMsgMgr;
    private View mMyPanoramicView;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new MyPanoramicView(context);
        }
        return this.mMyPanoramicView;
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        getMsgMgr(context).updateSetting(0, 0, SharePreUtil.getBoolValue(context, SHARE_287_IS_SUPPORT_PANORAMIC, false) ? 1 : 0);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._287.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 0) {
                    SharePreUtil.setBoolValue(context, UiMgr.SHARE_287_IS_SUPPORT_PANORAMIC, i3 == 1);
                    UiMgr.this.getMsgMgr(context).updateSetting(i, i2, i3);
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._287.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i == 0 && i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
                }
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._287.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                boolean boolValue = SharePreUtil.getBoolValue(context, UiMgr.SHARE_287_IS_SUPPORT_PANORAMIC, false);
                parkPageUiSet.setShowRadar(!boolValue);
                parkPageUiSet.setShowCusPanoramicView(boolValue);
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
