package com.hzbhd.canbus.car._123;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    static final String SHARE_123_COMPASS_DIRECT = "share_123_compass_direct";
    private static int mBlowMode = 8;
    private AirActivity mActivity;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private MsgMgr msgMgr;
    int i = 1;
    int nowZhiNanZheMode = 0;
    int nowOtherCarMode = 0;
    private OnAirBtnClickListener RearBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 66, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 66, 0});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 0});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 1});
            }
        }
    };
    private OnAirTemperatureUpDownClickListener RearTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 1});
        }
    };
    private OnAirBtnClickListener onAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
            }
        }
    };
    private OnAirBtnClickListener onAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 1});
        }
    };
    private OnAirBtnClickListener onAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.22
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
        }
    };
    private OnAirBtnClickListener onAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.23
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 21, 1});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
            }
        }
    };
    private OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.24
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
        }
    };
    private OnAirTemperatureUpDownClickListener onUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.25
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
        }
    };
    private OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.26
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.27
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.28
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
        }
    };
    private int mDifferentID = getCurrentCarId();
    private int mEachID = getEachId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCarId(), 0});
        if (getCurrentCarId() == 5) {
            mBlowMode = 26;
        }
        if (this.mEachID == 1) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_76"});
        }
        if (this.mEachID != 1) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_0x43_data3_76s"});
        }
        int i = this.mDifferentID;
        if (i == 6 || i == 7) {
            return;
        }
        removeSettingLeftItemByNameList(this.mContext, new String[]{"hiworld_jeep_123_item_53"});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void turnWindMode() {
        if (getCurrentCarId() == 5) {
            int i = this.nowZhiNanZheMode;
            if (i == 0) {
                mBlowMode = 26;
                this.nowZhiNanZheMode = 1;
            } else if (i == 1) {
                mBlowMode = 27;
                this.nowZhiNanZheMode = 2;
            } else if (i == 2) {
                mBlowMode = 28;
                this.nowZhiNanZheMode = 3;
            } else if (i == 3) {
                mBlowMode = 29;
                this.nowZhiNanZheMode = 0;
            }
        } else {
            int i2 = this.nowOtherCarMode;
            if (i2 == 0) {
                mBlowMode = 8;
                this.nowOtherCarMode = 1;
            } else if (i2 == 1) {
                mBlowMode = 9;
                this.nowOtherCarMode = 2;
            } else if (i2 == 2) {
                mBlowMode = 10;
                this.nowOtherCarMode = 3;
            } else if (i2 == 3) {
                mBlowMode = 23;
                this.nowOtherCarMode = 4;
            } else if (i2 == 4) {
                mBlowMode = 24;
                this.nowOtherCarMode = 0;
            }
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) mBlowMode, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) mBlowMode, 0});
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        AirPageUiSet airUiSet = getAirUiSet(context);
        if (this.mEachID != 1) {
            airUiSet.setHaveRearArea(false);
        }
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerTop, this.onAirBtnClickListenerLeft, this.onAirBtnClickListenerRight, this.onAirBtnClickListenerBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, this.onUpDownClickListenerCenter, this.onUpDownClickListenerRight});
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 1});
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.turnWindMode();
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.turnWindMode();
            }
        });
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.RearTempListener, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 1});
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.RearBottomBtnListener});
        airUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (UiMgr.this.i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 81, 1});
                    UiMgr.this.i = 2;
                } else if (UiMgr.this.i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 82, 1});
                    UiMgr.this.i = 3;
                } else if (UiMgr.this.i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 83, 1});
                    UiMgr.this.i = 1;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                if (UiMgr.this.i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 81, 1});
                    UiMgr.this.i++;
                } else if (UiMgr.this.i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 82, 1});
                    UiMgr.this.i++;
                } else if (UiMgr.this.i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 61, 83, 1});
                    UiMgr.this.i = 1;
                }
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 0) {
                    if (i2 == 13) {
                        Toast.makeText(UiMgr.this.mContext, R.string.service_start, 0).show();
                        CanbusMsgSender.sendMsg(new byte[]{22, 68, 16, 1});
                        return;
                    }
                    return;
                }
                if (i == 5 && i2 == 2) {
                    Toast.makeText(UiMgr.this.mContext, R.string.compass_run_calibration, 0).show();
                    CanbusMsgSender.sendMsg(new byte[]{22, -99, 2, 1});
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                UiMgr uiMgr = UiMgr.this;
                if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "hiworld_jeep_123_0x62_title")) {
                    UiMgr uiMgr2 = UiMgr.this;
                    if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "hiworld_jeep_123_0x62_title", "_123_rear_mirror")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 1});
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 0});
                    }
                }
                if (i != 7) {
                    return;
                }
                if (i2 == 0 || i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) UiMgr.this.getCurrentCarId(), 0});
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 1) {
                    if (i2 == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 99, 11, (byte) i3});
                    }
                } else if (i == 5 && i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -101, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.9
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:37:0x0094 A[PHI: r1
              0x0094: PHI (r1v15 int) = (r1v2 int), (r1v8 int), (r1v17 int) binds: [B:66:0x0106, B:58:0x00ee, B:33:0x008a] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:43:0x00c3 A[PHI: r1 r3
              0x00c3: PHI (r1v10 int) = (r1v2 int), (r1v8 int), (r1v13 int) binds: [B:66:0x0106, B:58:0x00ee, B:42:0x00b0] A[DONT_GENERATE, DONT_INLINE]
              0x00c3: PHI (r3v3 int) = (r3v0 int), (r3v0 int), (r3v4 int) binds: [B:66:0x0106, B:58:0x00ee, B:42:0x00b0] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:60:0x00f3 A[PHI: r1
              0x00f3: PHI (r1v7 int) = (r1v2 int), (r1v8 int) binds: [B:66:0x0106, B:58:0x00ee] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:61:0x00f6 A[PHI: r1
              0x00f6: PHI (r1v6 int) = (r1v2 int), (r1v8 int) binds: [B:66:0x0106, B:58:0x00ee] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:62:0x00f9 A[PHI: r1
              0x00f9: PHI (r1v5 int) = (r1v2 int), (r1v8 int) binds: [B:66:0x0106, B:58:0x00ee] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:63:0x00fc A[PHI: r1
              0x00fc: PHI (r1v4 int) = (r1v2 int), (r1v8 int) binds: [B:66:0x0106, B:58:0x00ee] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:64:0x0100 A[PHI: r1
              0x0100: PHI (r1v3 int) = (r1v2 int), (r1v8 int) binds: [B:66:0x0106, B:58:0x00ee] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r21, int r22, int r23) {
                /*
                    Method dump skipped, instructions count: 480
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._123.UiMgr.AnonymousClass9.onClickItem(int, int, int):void");
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "hiworld_jeep_123_item_50":
                        CanbusMsgSender.sendMsg(new byte[]{22, 68, 23, (byte) i3});
                        break;
                    case "hiworld_jeep_123_item_51":
                        CanbusMsgSender.sendMsg(new byte[]{22, 68, 24, (byte) i3});
                        break;
                    case "hiworld_jeep_123_item_52":
                        CanbusMsgSender.sendMsg(new byte[]{22, 68, 25, (byte) i3});
                        break;
                }
            }
        });
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.11
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public void onStatusChange(int i) {
            }
        });
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.12
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i) {
                if (i == 0) {
                    UiMgr uiMgr = UiMgr.this;
                    if (uiMgr.getmMsgMgr(uiMgr.mContext).rpt) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 0, 0});
                        return;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 1, 0});
                        return;
                    }
                }
                if (i != 1) {
                    return;
                }
                UiMgr uiMgr2 = UiMgr.this;
                if (uiMgr2.getmMsgMgr(uiMgr2.mContext).rdm) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 6, 0, 0});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 6, 1, 0});
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.13
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 3, 0, 0});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 1, 0, 0});
                } else if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 2, 0, 0});
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -81, 4, 0, 0});
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.14
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
            public void onBackPressed() {
                CanbusMsgSender.sendMsg(new byte[]{22, -81, 2, 0, 0});
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.15
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int i) {
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int i) {
                int i2 = i + 1;
                CanbusMsgSender.sendMsg(new byte[]{22, -81, 7, (byte) (65280 & i2), (byte) (i2 & 255)});
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.16
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                if (amplifierBand == AmplifierActivity.AmplifierBand.VOLUME) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i});
                    return;
                }
                if (amplifierBand == AmplifierActivity.AmplifierBand.BASS) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (i - 9)});
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.MIDDLE) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) (i - 9)});
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (i - 9)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._123.UiMgr.17
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                if (amplifierPosition == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) i});
                } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) i});
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int resolveSelectPos(int i) {
        return new int[]{1, 2, 3, 4, 5, 7, 8, 9, 16, 21}[i];
    }

    private void blowModeCirculation() {
        if (getCurrentCarId() != 5) {
            int i = mBlowMode;
            if (i == 10) {
                mBlowMode = 23;
                return;
            } else if (i == 24) {
                mBlowMode = 8;
                return;
            } else {
                mBlowMode = i + 1;
                return;
            }
        }
        int i2 = mBlowMode;
        if (i2 != 29) {
            mBlowMode = i2 + 1;
        }
        if (mBlowMode == 29) {
            mBlowMode = 26;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getmMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
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
