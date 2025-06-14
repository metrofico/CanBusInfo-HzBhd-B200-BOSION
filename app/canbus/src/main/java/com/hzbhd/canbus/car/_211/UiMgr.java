package com.hzbhd.canbus.car._211;

import android.content.Context;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private static boolean isExplorer;
    private AirActivity mActivity;
    private Context mContext;
    private int mBlowMode = 19;
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendData(new byte[]{22, 60, 2, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendData(new byte[]{22, 60, 1, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendData(new byte[]{22, 60, 4, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendData(new byte[]{22, 60, 3, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerFrontCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
        }
    };
    private OnAirBtnClickListener mOnAirTopBtnClickListenerFront = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, 60, 7, 0});
            } else if (i == 1) {
                UiMgr.this.sendData(new byte[]{22, 60, 9, 0});
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendData(new byte[]{22, 60, 8, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListenerFront = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, 60, 10, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListenerFront = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, 60, 6, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListenerFront = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, 60, 15, 0});
                return;
            }
            if (i == 1) {
                UiMgr.this.sendData(new byte[]{22, 60, 14, 0});
            } else if (i == 2) {
                UiMgr.this.sendData(new byte[]{22, 60, 5, 0});
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendData(new byte[]{22, 60, 37, 0});
            }
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendData(new byte[]{22, 60, 16, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendData(new byte[]{22, 60, 17, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendData(new byte[]{22, 60, 25, 0});
        }
    };
    private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendData(new byte[]{22, 60, 32, 0});
        }
    };
    private OnAirBtnClickListener mOnAirTopBtnClickListenerRear = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListenerRear = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private OnAirBtnClickListener mOnAirBottomRightBtnClickListenerRear = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListenerRear = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, 60, 35, 0});
            } else if (i == 1 && GeneralAirData.rear) {
                UiMgr.this.sendData(new byte[]{22, 60, 36, 0});
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRearCenter = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.21
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (GeneralAirData.rear && UiMgr.isExplorer) {
                UiMgr.this.sendData(new byte[]{22, 60, 34, 0});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (GeneralAirData.rear && UiMgr.isExplorer) {
                UiMgr.this.sendData(new byte[]{22, 60, 33, 0});
            }
        }
    };

    static /* synthetic */ int access$208(UiMgr uiMgr) {
        int i = uiMgr.mBlowMode;
        uiMgr.mBlowMode = i + 1;
        return i;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int currentCarId = getCurrentCarId();
        if (currentCarId == 0) {
            isExplorer = false;
            removeRearAirButton(this.mContext, 3, new int[]{0, 1});
        } else {
            if (currentCarId != 1) {
                return;
            }
            isExplorer = true;
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListenerFront, this.mOnAirBottomLeftBtnClickListenerFront, this.mOnAirBottomRightBtnClickListenerFront, this.mOnAirBottomBtnClickListenerFront});
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerFrontLeft, this.mTempSetViewOnUpDownClickListenerFrontCenter, this.mTempSetViewOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendData(new byte[]{22, 60, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendData(new byte[]{22, 60, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendData(new byte[]{22, 60, (byte) UiMgr.access$208(uiMgr), 0});
                if (UiMgr.this.mBlowMode > 22) {
                    UiMgr.this.mBlowMode = 19;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendData(new byte[]{22, 60, (byte) UiMgr.access$208(uiMgr), 0});
                if (UiMgr.this.mBlowMode > 22) {
                    UiMgr.this.mBlowMode = 19;
                }
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListenerRear, this.mOnAirBottomLeftBtnClickListenerRear, this.mOnAirBottomRightBtnClickListenerRear, this.mOnAirBottomBtnClickListenerRear});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerRearLeft, this.mTempSetViewOnUpDownClickListenerRearCenter, this.mTempSetViewOnUpDownClickListenerRearRight});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._211.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                if (!UiMgr.isExplorer) {
                    UiMgr.this.sendData(new byte[]{22, 60, 24, 0});
                } else if (GeneralAirData.rear) {
                    UiMgr.this.sendData(new byte[]{22, 60, 24, 0});
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                if (!UiMgr.isExplorer) {
                    UiMgr.this.sendData(new byte[]{22, 60, 23, 0});
                } else if (GeneralAirData.rear) {
                    UiMgr.this.sendData(new byte[]{22, 60, 23, 0});
                }
            }
        });
    }
}
