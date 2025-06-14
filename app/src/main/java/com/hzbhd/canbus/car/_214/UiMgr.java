package com.hzbhd.canbus.car._214;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private static final int CAR_ID_CX4 = 0;
    private static final int CAR_ID_CX5 = 1;
    private static final int CAR_ID_CX7_HIGH = 2;
    private static final int CAR_ID_CX7_LOW = 9;
    private static final int CAR_ID_CX9 = 8;
    private static final int CAR_ID_M3_2003 = 3;
    private static final int CAR_ID_M3_2008 = 4;
    private static final int CAR_ID_M3_AXELA = 10;
    private static final int CAR_ID_M5 = 5;
    private static final int CAR_ID_M6_ATENZA = 11;
    private static final int CAR_ID_M6_RUIYI = 12;
    private static final int CAR_ID_M7 = 6;
    private static final int CAR_ID_M8 = 7;
    private static final String SHARE_214_LANGUAGE = "share_214_language";
    private Context mContext;
    private MsgMgr mMsgMgr;
    private Timer mTimer;
    private OnPanelLongKeyPositionListener mOnPanelLongKeyPositionListener = new OnPanelLongKeyPositionListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener
        public void longClick(int i) {
            if (i == 0) {
                if (UiMgr.this.isM3()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, -126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                }
                if (UiMgr.this.isM6()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, -127, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                }
                if (UiMgr.this.isCx7()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, -125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                    return;
                }
                return;
            }
            if (i == 1) {
                if (UiMgr.this.isM3()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, -127, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                }
                if (UiMgr.this.isM6()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, -125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                }
                if (UiMgr.this.isCx7()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -111, -124, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                    return;
                }
                return;
            }
            if (i != 2) {
                return;
            }
            if (UiMgr.this.isM3()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
            if (UiMgr.this.isM6()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -124, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
            if (UiMgr.this.isCx7()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -123, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
        }
    };
    private OnPanelKeyPositionTouchListener mOnPanelKeyPositionTouchListener = new OnPanelKeyPositionTouchListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener
        public void onTouch(final int i, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                UiMgr.this.playBeep();
                UiMgr.this.mTimer = new Timer();
                UiMgr.this.mTimer.schedule(new TimerTask() { // from class: com.hzbhd.canbus.car._214.UiMgr.4.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        UiMgr.this.reportKeyDown(i);
                    }
                }, 0L, 200L);
                return;
            }
            if (motionEvent.getAction() == 1) {
                if (UiMgr.this.mTimer != null) {
                    UiMgr.this.mTimer.cancel();
                }
                try {
                    String string = Settings.System.getString(UiMgr.this.mContext.getContentResolver(), "reportAfterHangUp");
                    if (TextUtils.isEmpty(string)) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(Base64.decode(string, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private OnOriginalCarDevicePageStatusListener mOnOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.5
        /* JADX WARN: Type inference failed for: r2v3, types: [com.hzbhd.canbus.car._214.UiMgr$5$1] */
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
        public void onStatusChange(int i) {
            if (FutureUtil.instance.getCurrentValidSource() != SourceConstantsDef.SOURCE_ID.AUX2) {
                FutureUtil.instance.audioChannelRequest(SourceConstantsDef.SOURCE_ID.AUX2);
            }
            new Thread() { // from class: com.hzbhd.canbus.car._214.UiMgr.5.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    try {
                        sleep(500L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 0});
                        sleep(200L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 1});
                        sleep(200L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, 2});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    };
    private OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            switch (i) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 0});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 2});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, 1});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 0});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 1});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, 2});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
                    break;
                case 8:
                    if (!GeneralOriginalCarDeviceData.cd) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 1});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 0});
                        break;
                    }
            }
        }
    };
    private OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 1});
                return;
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 2});
            } else if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
            }
        }
    };
    private OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
        public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 1});
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i != 0) {
                if (i == 1) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 21, (byte) i3});
                        return;
                    }
                    if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 22, (byte) i3});
                        return;
                    }
                    if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 23, (byte) i3});
                        return;
                    } else if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 24, (byte) i3});
                        return;
                    } else {
                        if (i2 != 4) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 25, (byte) i3});
                        return;
                    }
                }
                if (i == 2) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 14, (byte) i3});
                        return;
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 15, (byte) i3});
                        return;
                    } else {
                        if (i2 != 2) {
                            return;
                        }
                        CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 16, (byte) i3});
                        return;
                    }
                }
                if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte) i3});
                    return;
                }
                if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                } else if (i2 == 0) {
                    UiMgr.this.getMsgMgr().updateSettings(i, i2, i3);
                    UiMgr.this.getMsgMgr().setFuelUnit(i3);
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 52, (byte) i3});
                return;
            }
            switch (i2) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 9, (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 1, (byte) i3});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 4, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 5, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 6, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 3, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 7, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 8, (byte) i3});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 2, (byte) i3});
                    break;
                case 9:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 10, (byte) i3});
                    break;
                case 10:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
                case 11:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 17, (byte) i3});
                    break;
                case 12:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 18, (byte) i3});
                    break;
                case 13:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 19, (byte) i3});
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 20, (byte) i3});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 11, (byte) i3});
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 45, (byte) i3});
                    break;
                case 18:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 27, (byte) i3});
                    break;
                case 19:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 46, (byte) i3});
                    break;
                case 20:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 28, (byte) i3});
                    break;
                case 21:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 29, (byte) i3});
                    break;
                case 22:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 30, (byte) i3});
                    break;
                case 23:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 31, (byte) i3});
                    break;
                case 24:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 32, (byte) i3});
                    break;
                case 25:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 33, (byte) i3});
                    break;
                case 26:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 34, (byte) i3});
                    break;
                case 27:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 35, (byte) i3});
                    break;
                case 28:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 36, (byte) i3});
                    break;
                case 29:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 37, (byte) i3});
                    break;
                case 30:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 38, (byte) i3});
                    break;
                case 31:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 39, (byte) i3});
                    break;
                case 32:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 47, (byte) i3});
                    break;
                case 33:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 48, (byte) i3});
                    break;
                case 34:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 50, (byte) i3});
                    break;
                case 35:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 40, (byte) i3});
                    break;
                case 36:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 41, (byte) i3});
                    break;
                case 37:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 42, (byte) i3});
                    break;
                case 38:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 43, (byte) i3});
                    break;
                case 39:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 44, (byte) i3});
                    break;
                case 40:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 49, (byte) i3});
                    break;
                case 41:
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 51, (byte) i3});
                    break;
                case 42:
                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                    UiMgr.this.getMsgMgr().updateSettings(i, i2, i3);
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.SHARE_214_LANGUAGE, i3);
                    break;
            }
        }
    };
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.10
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == -1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 120});
            }
        }
    };
    private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.11
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 5) {
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 53, (byte) (i3 + 15)});
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 54, (byte) (i3 + 5)});
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 55, (byte) (i3 + 5)});
                }
            }
        }
    };
    private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
        }
    };
    private OnAmplifierSeekBarListener mAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.13
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass15.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (i + 10)});
            } else if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (i + 10)});
            } else {
                if (i2 != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i});
            }
        }
    };
    private OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.14
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass15.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (i + 16)});
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (i + 16)});
            }
        }
    };

    public UiMgr(final Context context) {
        this.mContext = context;
        PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionTouchListener(this.mOnPanelKeyPositionTouchListener);
        panelKeyPageUiSet.setOnPanelLongKeyPositionListener(this.mOnPanelLongKeyPositionListener);
        if (isM3()) {
            panelKeyPageUiSet.setCount(2);
        }
        getMsgMgr().updateSettings(0, 42, SharePreUtil.getIntValue(context, SHARE_214_LANGUAGE, 0));
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.mAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
        getAirUiSet(context).setOnAirPageStatusListener(this.mOnAirPageStatusListener);
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 20});
                    CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 21});
                    CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 17});
                    CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 38});
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._214.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 4 && i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 126, 10, 0, 26, 1});
                    Toast.makeText(context, R.string.reset_completed, 0).show();
                }
            }
        });
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(this.mOnOriginalCarDevicePageStatusListener);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.mOnOriginalCarDeviceBackPressedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isM3() {
        return getCurrentCarId() == 3 || getCurrentCarId() == 4 || getCurrentCarId() == 5 || getCurrentCarId() == 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isM6() {
        return getCurrentCarId() == 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCx7() {
        return getCurrentCarId() == 2 || getCurrentCarId() == 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportKeyDown(int i) {
        if (i == 0) {
            if (isM3()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
            if (isM6()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -127, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
            if (isCx7()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                return;
            }
            return;
        }
        if (i == 1) {
            if (isM3()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -127, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
            if (isM6()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
            if (isCx7()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -111, -124, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                return;
            }
            return;
        }
        if (i != 2) {
            return;
        }
        if (isM3()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, -126, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
        if (isM6()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, -124, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
        if (isCx7()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, -123, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int currentCarId = getCurrentCarId();
        if (currentCarId != 2) {
            if (currentCarId == 3 || currentCarId == 4 || currentCarId == 5 || currentCarId == 6) {
                removePanelBtnKeyByName(context, "panel_btn_mazda6_set");
                removePanelBtnKeyByName(context, "panel_btn_mazda6_hour");
                removePanelBtnKeyByName(context, "panel_btn_mazda6_min");
                removePanelBtnKeyByName(context, "panel_btn_mazdacx7_hour");
                removePanelBtnKeyByName(context, "panel_btn_mazdacx7_min");
                removePanelBtnKeyByName(context, "panel_btn_mazdacx7_00");
                return;
            }
            if (currentCarId != 9) {
                if (currentCarId == 12) {
                    removePanelBtnKeyByName(context, "panel_btn_mazda3_clock");
                    removePanelBtnKeyByName(context, "panel_btn_mazda3_set");
                    removePanelBtnKeyByName(context, "panel_btn_mazdacx7_hour");
                    removePanelBtnKeyByName(context, "panel_btn_mazdacx7_min");
                    removePanelBtnKeyByName(context, "panel_btn_mazdacx7_00");
                    return;
                }
                removeMainPrjBtnByName(context, MainAction.PANEL_KEY);
                return;
            }
        }
        removePanelBtnKeyByName(context, "panel_btn_mazda3_clock");
        removePanelBtnKeyByName(context, "panel_btn_mazda3_set");
        removePanelBtnKeyByName(context, "panel_btn_mazda6_set");
        removePanelBtnKeyByName(context, "panel_btn_mazda6_hour");
        removePanelBtnKeyByName(context, "panel_btn_mazda6_min");
    }

    /* renamed from: com.hzbhd.canbus.car._214.UiMgr$15, reason: invalid class name */
    static /* synthetic */ class AnonymousClass15 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
    }

    protected int getSettingRightIndex(Context context, String str, String str2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (str.equals(next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (str2.equals(it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return 404;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return 404;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr() {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(this.mContext);
        }
        return this.mMsgMgr;
    }
}
