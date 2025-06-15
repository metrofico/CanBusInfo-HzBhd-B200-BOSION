package com.hzbhd.canbus.car._292;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnStartPageUiSet mOnStartPageUiSet;
    private ParkPageUiSet mParkPageUiSet;
    private SettingPageUiSet mSettingPageUiSet;
    private OnSettingItemSelectListener mItemSelect = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._292.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                if (i2 < 10) {
                    UiMgr.this.sendSettingMsg(i2, i3);
                    return;
                } else {
                    UiMgr.this.sendSettingMsg(i2 + 1, i3);
                }
            }
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i3});
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i3});
                    return;
                }
            }
            switch (i2) {
                case 0:
                    UiMgr.this.sendSettingMsg(12, i3);
                    break;
                case 1:
                    UiMgr.this.sendSettingMsg(13, i3);
                    break;
                case 2:
                    UiMgr.this.sendSettingMsg(14, i3);
                    break;
                case 3:
                    UiMgr.this.sendSettingMsg(15, i3);
                    break;
                case 4:
                    UiMgr.this.sendSettingMsg(24, i3);
                    break;
                case 5:
                    UiMgr.this.sendSettingMsg(22, i3);
                    break;
                case 6:
                    UiMgr.this.sendSettingMsg(23, i3);
                    break;
                case 7:
                    UiMgr.this.sendSettingMsg(16, i3);
                    break;
                case 8:
                    UiMgr.this.sendSettingMsg(17, i3);
                    break;
                case 9:
                    UiMgr.this.sendSettingMsg(18, i3);
                    break;
                case 10:
                    UiMgr.this.sendSettingMsg(19, i3);
                    break;
                case 11:
                    UiMgr.this.sendSettingMsg(20, i3);
                    break;
                case 12:
                    UiMgr.this.sendSettingMsg(21, i3);
                    break;
                case 13:
                    UiMgr.this.sendSettingMsg(28, i3);
                    break;
                case 14:
                    UiMgr.this.sendSettingMsg(27, i3);
                    break;
                case 15:
                    UiMgr.this.sendSettingMsg(26, i3);
                    break;
                case 16:
                    UiMgr.this.sendSettingMsg(25, i3);
                    break;
            }
        }
    };
    private OnSettingItemSeekbarSelectListener mSeekBar = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._292.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 2 && i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte) i3});
            }
        }
    };
    private OnOnStarClickListener mOnOnStarPhone = new OnOnStarClickListener() { // from class: com.hzbhd.canbus.car._292.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void init() {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void numberClick(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) (i | 128)});
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void handOn(String str) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void handOff() {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
        public void exit() {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
        }
    };
    private OnPanoramicItemTouchListener mParkTouch = new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._292.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
        public void onTouchItem(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int dimenByResId = (x * 800) / CommUtil.getDimenByResId(UiMgr.this.mContext, "dp1024");
            int dimenByResId2 = (y * 480) / CommUtil.getDimenByResId(UiMgr.this.mContext, "dp600");
            byte b = (byte) (dimenByResId & 255);
            byte b2 = (byte) ((dimenByResId >> 8) & 255);
            byte b3 = (byte) (dimenByResId2 & 255);
            byte b4 = (byte) ((dimenByResId2 >> 8) & 255);
            if (motionEvent.getAction() == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -29, 1, b, b2, b3, b4});
            } else if (motionEvent.getAction() == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -29, 0, b, b2, b3, b4});
            }
            UiMgr.this.playBeep();
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        this.mSettingPageUiSet = getSettingUiSet(context);
        this.mOnStartPageUiSet = getOnStartPageUiSet(this.mContext);
        this.mParkPageUiSet = getParkPageUiSet(this.mContext);
        this.mSettingPageUiSet.setOnSettingItemSelectListener(this.mItemSelect);
        this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mSeekBar);
        this.mOnStartPageUiSet.setOnOnStarClickListener(this.mOnOnStarPhone);
        this.mParkPageUiSet.setOnPanoramicItemTouchListener(this.mParkTouch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i2});
    }
}
