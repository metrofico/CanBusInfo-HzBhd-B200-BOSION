package com.hzbhd.canbus.car._96;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private int currentClickFront;
    private int data1;
    private int data2;
    private int data3;
    private int frontRear;
    private boolean isRadio;
    private int leftRight;
    private AbstractBaseActivity mActivity;
    private String mBtStatus;
    private Context mContext;
    private int mCurrentFreq;
    private int mCurrentPlayStatusCd;
    private String[][] mDiagitalKeyboardActions;
    private String[][] mFeaturesKeyboardActions;
    private MsgMgr mMsgMgr;
    OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private int mScan;
    private SettingPageUiSet settingPageUiSet;
    private int value1;
    private int value2;
    private boolean mIsFeaturesKeyboard = true;
    private OnAirBtnClickListener mOnFrontAirTopBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 0});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
                return;
            }
            if (i == 2) {
                if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
                    return;
                }
            }
            if (i == 3) {
                if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
                    return;
                }
            }
            if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 0});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
            } else if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
            } else if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnFrontOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 0});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 0});
            } else if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
            } else {
                if (i != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 0});
            }
        }
    };
    private OnAirSeatClickListener mOnFrontAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(8);
            } else if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                UiMgr.this.sendAirClick(9);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                UiMgr.this.sendAirClick(10);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            int i = UiMgr.this.currentClickFront;
            if (i == 0) {
                UiMgr.this.currentClickFront = 1;
                UiMgr.this.sendAirClick(8);
            } else if (i == 1) {
                UiMgr.this.currentClickFront = 2;
                UiMgr.this.sendAirClick(9);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.currentClickFront = 0;
                UiMgr.this.sendAirClick(10);
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 0});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 0});
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetRearWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 0});
        }
    };
    private OnAirBtnClickListener mOnRearAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.22
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 0});
            }
        }
    };
    private OnSettingItemSelectListener mOnItemSelectedListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.23
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            switch (i) {
                case 0:
                    if (i3 != 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 27});
                        break;
                    }
                case 1:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 4, (byte) i3});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 6, (byte) i3});
                        break;
                    }
                case 2:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 108, 3, (byte) i3});
                        break;
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 108, 4, (byte) i3});
                        break;
                    } else if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 108, 5, (byte) i3});
                        break;
                    } else if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 108, 6, (byte) i3});
                        break;
                    } else if (i2 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 108, 7, (byte) i3});
                        break;
                    }
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) (i2 + 3), (byte) i3});
                    break;
                case 4:
                    if (i2 != 0) {
                        SharePreUtil.setIntValue(UiMgr.this.mContext, "_96_park_assess_item", i3);
                        UiMgr.this.mMsgMgr.updateOrignalSetting();
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, (byte) i3});
                        break;
                    }
                case 5:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte) i3, 0, 0});
                        break;
                    }
                    break;
                case 6:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte) i3});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 8, (byte) i3});
                        break;
                    }
            }
        }
    };
    private OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.24
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (MsgMgr.angleWide) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (MsgMgr.overlook) {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 1});
            }
        }
    };
    private OnAmplifierSeekBarListener mAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.25
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            Log.d("cwh", "progress = " + i);
            int i2 = AnonymousClass31.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) i});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) i});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) i});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i});
            }
        }
    };
    private OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.26
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass31.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                if (UiMgr.this.leftRight != i) {
                    UiMgr.this.leftRight = i;
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (i + 7)});
                }
                Log.d("cwh", "value  = " + i);
                Log.d("cwh", "leftRight  = " + UiMgr.this.leftRight);
                return;
            }
            if (i2 != 2) {
                return;
            }
            if (UiMgr.this.frontRear != i) {
                UiMgr.this.frontRear = i;
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (i + 7)});
            }
            Log.d("cwh", "value  = " + i);
            Log.d("cwh", "frontRear  = " + UiMgr.this.frontRear);
        }
    };
    private OnOriginalCarDeviceBackPressedListener mBackPressed = new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.27
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
        public void onBackPressed() {
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -111, 0, 2}, 16));
        }
    };
    private OnOriginalBottomBtnClickListener mBottom = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.28
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            Log.d("cwh", "onClickBottomBtnItem:  isRadio  = " + UiMgr.this.isRadio);
            if (UiMgr.this.isRadio) {
                if (i == 0) {
                    if (UiMgr.this.mCurrentFreq == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 0, 0, 0});
                        UiMgr.this.mCurrentFreq = 1;
                        return;
                    } else {
                        UiMgr.this.mCurrentFreq = 0;
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 1, 0, 0});
                        return;
                    }
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 0, 0, 0});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 0, 0, 0});
                    return;
                }
                if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 1, 0, 0});
                    return;
                }
                if (i == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 1, 0, 0});
                    return;
                }
                if (i != 5) {
                    return;
                }
                if (UiMgr.this.mScan == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 1, 0, 0});
                    UiMgr.this.mScan = 1;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 0, 0, 0});
                    UiMgr.this.mScan = 0;
                    return;
                }
            }
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -81, 7, 1});
                return;
            }
            if (i == 1) {
                if (UiMgr.this.value1 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 0});
                    UiMgr.this.value1 = 1;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 1});
                    UiMgr.this.value1 = 0;
                    return;
                }
            }
            if (i == 2) {
                if (UiMgr.this.mCurrentPlayStatusCd == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 1, 0});
                    UiMgr.this.mCurrentPlayStatusCd = 1;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 2, 0});
                    UiMgr.this.mCurrentPlayStatusCd = 0;
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -81, 7, 0});
            } else if (UiMgr.this.value2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -81, 3, 0});
                UiMgr.this.value2 = 1;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -81, 3, 1});
                UiMgr.this.value2 = 0;
            }
        }
    };
    private OnOriginalSongItemClickListener mSongItem = new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.29
        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemClick(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 4, (byte) (i + 1), 0, 0});
        }

        @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
        public void onSongItemLongClick(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 8, (byte) (i + 1), 0, 0});
        }
    };
    private OnOriginalCarDevicePageStatusListener mPageStatus = new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.30
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
        public void onStatusChange(int i) {
            UiMgr uiMgr = UiMgr.this;
            Context context = uiMgr.mContext;
            MsgMgr unused = UiMgr.this.mMsgMgr;
            uiMgr.isRadio = SharePreUtil.getIntValue(context, "original_radio_cd", 1) == 1;
            if (UiMgr.this.isRadio) {
                CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -111, -3, 2}, 16));
            } else {
                CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -111, -4, 2}, 16));
            }
        }
    };

    private byte setWidgetData(boolean z) {
        return !z ? (byte) 1 : (byte) 0;
    }

    protected AbstractBaseActivity getActivity() {
        return this.mActivity;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (getCurrentCarId() != 37) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"S96ColorTitle", "S96_Car_auxiliary_line", "S96_assit_warning"});
        }
        if (getCurrentCarId() == 37) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"light_ctrl_5"});
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        new DriverDataPageUiSet().setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 50});
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(this.mOnItemSelectedListener);
        this.settingPageUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_379_item_30")) {
                    new TypeInView().showDialog(UiMgr.this.mMsgMgr.getCurrentActivity());
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_94_atmosphere_lamp")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte) i3, 0, 0});
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("S96_assit_warning")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, (byte) i3});
                } else if (titleSrn.equals("S96_Car_auxiliary_line")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 11, (byte) i3});
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.mAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearTempSetViewOnUpDownClickListenerCenter, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.mSetRearWindSpeedViewOnClickListener);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirBottomBtnClickListener});
        this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 6) {
                    if (i2 == 2) {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 10, 1});
                    } else {
                        Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getText(R.string.reset_completed), 0).show();
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, 1});
                    }
                }
            }
        });
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.mBackPressed);
        this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mBottom);
        this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mSongItem);
        this.mOriginalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(this.mPageStatus);
        final SyncPageUiSet syncPageUiSet = getSyncPageUiSet(context);
        this.mBtStatus = SyncAction.KEYBOARD_BTPHONE_OFF;
        syncPageUiSet.setOnSyncItemClickListener(new OnSyncItemClickListener() { // from class: com.hzbhd.canbus.car._96.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnLongClick(String str) {
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onListItemClick(int i) {
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onLeftIconClick(String str) {
                str.hashCode();
                if (str.equals(SyncAction.LEFT_ICON_KEYBOARD)) {
                    if (UiMgr.this.mIsFeaturesKeyboard) {
                        UiMgr.this.mIsFeaturesKeyboard = false;
                        syncPageUiSet.setKeyboardActions(UiMgr.this.getDiagitalKeyboardActions());
                    } else {
                        UiMgr.this.mIsFeaturesKeyboard = true;
                        syncPageUiSet.setKeyboardActions(UiMgr.this.getFeaturesKeyboardActions());
                    }
                    UiMgr.this.mMsgMgr.updateSync(null);
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onSoftKeyClick(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 1, (byte) (i + 1)});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnClick(String str) {
                str.hashCode();
                switch (str) {
                    case "number_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 20});
                        break;
                    case "number_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 21});
                        break;
                    case "number_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 22});
                        break;
                    case "number_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 23});
                        break;
                    case "number_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 24});
                        break;
                    case "number_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 25});
                        break;
                    case "number_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 26});
                        break;
                    case "number_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 27});
                        break;
                    case "number_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 28});
                        break;
                    case "number_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 29});
                        break;
                    case "pickup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 18});
                        break;
                    case "ok":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 16});
                        break;
                    case "up":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                        break;
                    case "aux":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 17});
                        break;
                    case "down":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                        break;
                    case "info":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 19});
                        break;
                    case "left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 14});
                        break;
                    case "next":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 11});
                        break;
                    case "prev":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 10});
                        break;
                    case "star":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 32});
                        break;
                    case "well":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 33});
                        break;
                    case "right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 15});
                        break;
                    case "btphone_on":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 31});
                        break;
                    case "btphone_off":
                        CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte) GeneralSyncData.mSyncScreemNumber, 2, 30});
                        break;
                }
            }
        });
        if (getCurrentCarId() != 17) {
            removeMainPrjBtnByName(context, "sync");
        } else {
            removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._96.UiMgr$31, reason: invalid class name */
    static /* synthetic */ class AnonymousClass31 {
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void sendAirBtnCtrl(int i, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, setWidgetData(z)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirClick(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 1});
    }

    private void sendSeatHeatColdMsg(int i, int i2) {
        if (i >= 3) {
            i = 3;
        }
        if (i <= 0) {
            i = 0;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i2, (byte) i});
    }

    private void sendSettingResetData(int i) {
        Context context = this.mContext;
        Toast.makeText(context, context.getText(R.string.reset_completed), 0).show();
        sendData(new byte[]{22, 26, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[][] getDiagitalKeyboardActions() {
        if (this.mDiagitalKeyboardActions == null) {
            this.mDiagitalKeyboardActions = new String[][]{new String[]{SyncAction.KEYBOARD_NUMBER_1, SyncAction.KEYBOARD_NUMBER_2, SyncAction.KEYBOARD_NUMBER_3}, new String[]{SyncAction.KEYBOARD_NUMBER_4, SyncAction.KEYBOARD_NUMBER_5, SyncAction.KEYBOARD_NUMBER_6}, new String[]{SyncAction.KEYBOARD_NUMBER_7, SyncAction.KEYBOARD_NUMBER_8, SyncAction.KEYBOARD_NUMBER_9}, new String[]{SyncAction.KEYBOARD_STAR, SyncAction.KEYBOARD_NUMBER_0, SyncAction.KEYBOARD_WELL}, new String[]{SyncAction.KEYBOARD_PICKUP, this.mBtStatus}};
        }
        return this.mDiagitalKeyboardActions;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[][] getFeaturesKeyboardActions() {
        if (this.mFeaturesKeyboardActions == null) {
            this.mFeaturesKeyboardActions = new String[][]{new String[]{"null", "up", "null"}, new String[]{"left", SyncAction.KEYBOARD_OK, "right"}, new String[]{"null", "down", "null"}, new String[]{SyncAction.KEYBOARD_INFO, "aux"}, new String[]{SyncAction.KEYBOARD_PREV, SyncAction.KEYBOARD_NEXT}};
        }
        return this.mFeaturesKeyboardActions;
    }

    void updateBtStatus(Context context, int i) {
        if (i == 1) {
            this.mBtStatus = SyncAction.KEYBOARD_BTPHONE_ON;
        } else {
            this.mBtStatus = SyncAction.KEYBOARD_BTPHONE_OFF;
        }
        this.mDiagitalKeyboardActions = null;
        if (this.mIsFeaturesKeyboard) {
            return;
        }
        this.mIsFeaturesKeyboard = true;
        getSyncPageUiSet(context).getOnSyncItemClickListener().onLeftIconClick(SyncAction.LEFT_ICON_KEYBOARD);
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
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
}
