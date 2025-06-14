package com.hzbhd.canbus.car._263;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnSettingItemClickListener mOnSettingItemSelectListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._263.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemClickListener(this.mOnSettingItemSelectListener);
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._263.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    UiMgr.this.sendData(new byte[]{22, -112, 20});
                    UiMgr.this.sendData(new byte[]{22, -112, 51});
                    UiMgr.this.sendData(new byte[]{22, -112, 36});
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._263.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 0 && i2 == 0) {
                    Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                    UiMgr.this.sendData(new byte[]{22, -58, 1, 1});
                }
            }
        });
    }
}
