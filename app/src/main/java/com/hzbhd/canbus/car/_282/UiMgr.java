package com.hzbhd.canbus.car._282;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendFrontAirCommand(0, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendFrontAirCommand(1, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendFrontAirCommand(2, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendFrontAirCommand(3, i);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(2);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(4);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendRearAirCommand(3, i);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(39);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(38);
        }
    };
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(10);
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnAirBtnClickListenerRearBottom});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_282_07_0_2":
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.sendData(uiMgr.getMsgMgr().get0x07Data(), 121, 2, i3, 2, 1);
                        break;
                    case "_282_07_0_3":
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.sendData(uiMgr2.getMsgMgr().get0x07Data(), 121, 2, i3, 3, 1);
                        break;
                    case "_282_07_0_6":
                        UiMgr uiMgr3 = UiMgr.this;
                        uiMgr3.sendData(uiMgr3.getMsgMgr().get0x07Data(), 121, 2, i3, 6, 1);
                        break;
                    case "_282_07_0_7":
                        UiMgr uiMgr4 = UiMgr.this;
                        uiMgr4.sendData(uiMgr4.getMsgMgr().get0x07Data(), 121, 2, i3, 7, 1);
                        break;
                    case "_282_19_0_2":
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.sendData(uiMgr5.getMsgMgr().get0x19Data(), 130, 2, i3, 2, 1);
                        break;
                    case "_282_19_0_3":
                        UiMgr uiMgr6 = UiMgr.this;
                        uiMgr6.sendData(uiMgr6.getMsgMgr().get0x19Data(), 130, 2, i3, 3, 1);
                        break;
                    case "_282_27_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 1, (byte) i3});
                        break;
                    case "_282_27_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 2, (byte) i3});
                        break;
                    case "_282_27_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 3, (byte) i3});
                        break;
                    case "_282_27_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 4, (byte) i3});
                        break;
                    case "_282_27_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 5, (byte) i3});
                        break;
                    case "_282_27_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 6, (byte) i3});
                        break;
                    case "_282_27_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 7, (byte) i3});
                        break;
                    case "_282_27_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 8, (byte) i3});
                        break;
                    case "_282_27_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 9, (byte) i3});
                        break;
                    case "_282_27_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 10, (byte) i3});
                        break;
                    case "_282_27_11":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case "_282_27_12":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case "_282_19_0_01":
                        UiMgr uiMgr7 = UiMgr.this;
                        uiMgr7.sendData(uiMgr7.getMsgMgr().get0x19Data(), 130, 2, i3, 0, 2);
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_282_19_1":
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.sendData(uiMgr.getMsgMgr().get0x19Data(), 130, 3, i3, 0, 8);
                        break;
                    case "_282_27_10":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 11, (byte) i3});
                        break;
                    case "_282_27_13":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 14, (byte) i3});
                        break;
                    case "_282_27_14":
                        CanbusMsgSender.sendMsg(new byte[]{22, 117, 15, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_282_14_0_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, 116, 5});
                        break;
                    case "_282_14_0_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, 116, 4});
                        break;
                    case "_282_14_0_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, 116, 3});
                        break;
                    case "_282_74_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, 116, 1});
                        break;
                    case "_282_74_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, 116, 2});
                        break;
                }
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._282.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                parkPageUiSet.setShowRadar(SharePreUtil.getBoolValue(context, "share_281_show_radar", true));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFrontAirCommand(int i, int i2) {
        sendAirCommand(getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[i][i2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRearAirCommand(int i, int i2) {
        sendAirCommand(getAirUiSet(this.mContext).getRearArea().getLineBtnAction()[i][i2]);
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(19);
                break;
            case "ac_max":
                sendAirCommand(22);
                break;
            case "rear_defog":
                sendAirCommand(20);
                break;
            case "blow_positive":
                sendAirCommand(8);
                break;
            case "blow_negative":
                sendAirCommand(7);
                break;
            case "ac":
                sendAirCommand(23);
                break;
            case "auto":
                sendAirCommand(21);
                break;
            case "dual":
                sendAirCommand(16);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(25);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendData(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        if (i2 >= bArrCopyOf.length) {
            return;
        }
        bArrCopyOf[0] = 22;
        bArrCopyOf[1] = (byte) i;
        bArrCopyOf[i2] = (byte) DataHandleUtils.setIntFromByteWithBit(bArrCopyOf[i2], i3, i4, i5);
        CanbusMsgSender.sendMsg(bArrCopyOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr() {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(this.mContext);
        }
        return this.mMsgMgr;
    }
}
