package com.hzbhd.canbus.car._59;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private ParkPageUiSet mParkPageUiSet;
    private SettingPageUiSet mSettingPageUiSet;
    private OnSettingItemSelectListener mItemSelect = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._59.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i != 0) {
                if (i == 1 && i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 16, (byte) i3});
                    return;
                }
                return;
            }
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 4, (byte) i3});
                return;
            }
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 3, (byte) (i3 + 1)});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 108, 2, (byte) i3});
                return;
            }
            if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, 108, 1, (byte) (i3 + 1)});
                return;
            }
            if (i2 == 5) {
                CanbusMsgSender.sendMsg(new byte[]{22, 122, 3, (byte) i3});
            } else if (i2 == 6) {
                CanbusMsgSender.sendMsg(new byte[]{22, 122, 2, (byte) (i3 + 1)});
            } else {
                if (i2 != 7) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 122, 1, (byte) (i3 + 1)});
            }
        }
    };
    private OnSettingItemClickListener mItemClick = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._59.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte) (1 - i2)});
            }
        }
    };
    private OnSettingItemSeekbarSelectListener mOnSeekBar = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._59.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                if (i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, 1, (byte) (i3 + 5)});
                }
            } else {
                if (i != 1 || i2 >= 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte) (i2 + 1), (byte) i3});
            }
        }
    };
    private OnConfirmDialogListener mConfirm = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._59.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            if (i != 0) {
                if (i == 1 && i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
                }
                return;
            }
            switch (i2) {
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, 110, 3, 0});
                    Toast.makeText(UiMgr.this.mContext, R.string.reset, 0).show();
                    break;
                case 9:
                    CanbusMsgSender.sendMsg(new byte[]{22, 27, 1, 1, 1, -1});
                    Toast.makeText(UiMgr.this.mContext, R.string._59_0x1b_zero_cleared, 0).show();
                    break;
                case 10:
                    CanbusMsgSender.sendMsg(new byte[]{22, 27, 2, 1, 1, -1});
                    Toast.makeText(UiMgr.this.mContext, R.string._59_0x1b_zero_cleared, 0).show();
                    break;
            }
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        try {
            this.mContext = context;
            SettingPageUiSet settingUiSet = getSettingUiSet(context);
            this.mSettingPageUiSet = settingUiSet;
            settingUiSet.setOnSettingItemSelectListener(this.mItemSelect);
            this.mSettingPageUiSet.setOnSettingConfirmDialogListener(this.mConfirm);
            this.mSettingPageUiSet.setOnSettingItemClickListener(this.mItemClick);
            this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSeekBar);
            ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
            this.mParkPageUiSet = parkPageUiSet;
            parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._59.UiMgr.1
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
                public void onClickItem(int i) {
                    if (i == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, -1});
                    } else if (i == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, -1});
                    } else {
                        if (i != 2) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, -1});
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
