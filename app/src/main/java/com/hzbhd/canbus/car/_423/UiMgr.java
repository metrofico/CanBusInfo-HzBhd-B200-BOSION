package com.hzbhd.canbus.car._423;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;


public class UiMgr extends AbstractUiMgr {
    AirPageUiSet airPageUiSet;
    DriverDataPageUiSet driverDataPageUiSet;
    Context mContext;
    private MsgMgr msgMgr;
    ParkPageUiSet parkPageUiSet;
    int i = 26;
    private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(1);
        }
    };
    private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(5);
        }
    };
    private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(6);
        }
    };
    private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(7);
        }
    };
    private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(14);
        }
    };
    private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(14);
        }
    };

    public UiMgr(final Context context) {
        this.airPageUiSet = getAirUiSet(context);
        this.parkPageUiSet = getParkPageUiSet(context);
        this.driverDataPageUiSet = getDriverDataPageUiSet(context);
        this.mContext = context;
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                switch (UiMgr.this.i) {
                    case 26:
                    case 27:
                    case 28:
                        UiMgr.this.i++;
                        break;
                    case 29:
                        UiMgr.this.i = 26;
                        break;
                }
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(uiMgr.i);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                switch (UiMgr.this.i) {
                    case 26:
                    case 27:
                    case 28:
                        UiMgr.this.i++;
                        break;
                    case 29:
                        UiMgr.this.i = 26;
                        break;
                }
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(uiMgr.i);
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirMessage(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirMessage(11);
            }
        });
        this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i != 0) {
                    return;
                }
                byte[] bArr = new byte[4];
                bArr[0] = 22;
                bArr[1] = -3;
                bArr[2] = 4;
                bArr[3] = (byte) (UiMgr.this.getMsgMgr(context).PanoramicVideo.booleanValue() ? 3 : 7);
                CanbusMsgSender.sendMsg(bArr);
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.ActiveRequest(49);
            }
        });
        this.driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._423.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.ActiveRequest(26);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirMessage(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ActiveRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    public void sendPanoramicInfo0xFD() {
        byte[] bArr = new byte[4];
        bArr[0] = 22;
        bArr[1] = -3;
        bArr[2] = 4;
        bArr[3] = (byte) (getMsgMgr(this.mContext).PanoramicVideo.booleanValue() ? 3 : 7);
        CanbusMsgSender.sendMsg(bArr);
    }
}
