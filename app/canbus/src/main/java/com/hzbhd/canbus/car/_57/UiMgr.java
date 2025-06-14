package com.hzbhd.canbus.car._57;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private OnConfirmDialogListener mConfirm = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._57.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, -1});
                Toast.makeText(UiMgr.this.mContext, R.string._55_0xe8_confirm_1, 0).show();
            }
        }
    };
    private Context mContext;
    private ParkPageUiSet mParkPageUiSet;
    private SettingPageUiSet mSettingPageUiSet;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        try {
            this.mContext = context;
            SettingPageUiSet settingUiSet = getSettingUiSet(context);
            this.mSettingPageUiSet = settingUiSet;
            settingUiSet.setOnSettingConfirmDialogListener(this.mConfirm);
            ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
            this.mParkPageUiSet = parkPageUiSet;
            parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._57.UiMgr.1
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
                public void onClickItem(int i) {
                    if (i == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 1, -1});
                    } else if (i == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 2, -1});
                    } else {
                        if (i != 2) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, -1});
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("cwh", "错误报告：" + e);
        }
    }

    public void setShowRadar(boolean z) {
        this.mParkPageUiSet.setShowRadar(z);
    }
}
