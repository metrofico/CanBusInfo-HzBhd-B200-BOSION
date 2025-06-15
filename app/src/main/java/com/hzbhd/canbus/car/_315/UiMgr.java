package com.hzbhd.canbus.car._315;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
import java.util.Arrays;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;
    private byte[] m0xC7Data = {22, -57, 0, 0, 0, 0, 0, 0};
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(0, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(1, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(2, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(3, i);
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0xC7Data, UiMgr.this.m0xC7Data.length);
            bArrCopyOf[5] = (byte) DataHandleUtils.setIntByteWithBit(0, 1, true);
            UiMgr.this.sendAirCommand(bArrCopyOf);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0xC7Data, UiMgr.this.m0xC7Data.length);
            bArrCopyOf[5] = (byte) DataHandleUtils.setIntByteWithBit(0, 0, true);
            UiMgr.this.sendAirCommand(bArrCopyOf);
        }
    };

    public UiMgr(final Context context) {
        this.mContext = context;
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_315_light_dynamic_setup":
                        UiMgr.this.set0xC8Data(context, MpegConstantsDef.MPEG_DIVX_REG_STATUS_INT, i3);
                        break;
                    case "_315_seat_courtesy_switch":
                        UiMgr.this.set0xC8Data(context, 148, i3);
                        break;
                    case "_304_atoms_lamp_control":
                        UiMgr.this.set0xC8Data(context, 147, i3);
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_315_light_static_setup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -105, (byte) i3});
                        break;
                    case "_315_light_dynamic_sampling":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -101, (byte) i3});
                        break;
                    case "_279_temperature_unit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -110, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettingItem(i, i2, Integer.valueOf(i3));
                        SharePreUtil.setIntValue(context, Constant.SHARE_PRE_IS_USE_F_UNIT, i3);
                        break;
                    case "light_ctrl_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -106, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_250_ambient_light_brightness":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -107, (byte) i3});
                        break;
                    case "_315_light_dynamic_speed":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -100, (byte) i3});
                        break;
                    case "_315_light_static_sampling_bgr":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -104, (byte) i3});
                        break;
                    case "_315_light_static_sampling_bpr":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, -103, (byte) i3});
                        break;
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
        FrontArea frontArea = airUiSet.getFrontArea();
        OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.mOnUpDownClickListener;
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{onAirTemperatureUpDownClickListener, null, onAirTemperatureUpDownClickListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._315.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0xC7Data, UiMgr.this.m0xC7Data.length);
                bArrCopyOf[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 0, true);
                UiMgr.this.sendAirCommand(bArrCopyOf);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                byte[] bArrCopyOf = Arrays.copyOf(UiMgr.this.m0xC7Data, UiMgr.this.m0xC7Data.length);
                bArrCopyOf[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 1, true);
                UiMgr.this.sendAirCommand(bArrCopyOf);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i, int i2) {
        byte[] bArrCopyOf;
        String str = getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[i][i2];
        byte[] bArr = this.m0xC7Data;
        bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        str.hashCode();
        switch (str) {
            case "front_defog":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 4, true);
                break;
            case "rear_defog":
                bArrCopyOf[3] = (byte) DataHandleUtils.setIntByteWithBit(0, 2, true);
                break;
            case "ac":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 1, true);
                Log.i("cbc", "sendAirCommand: setup ");
                break;
            case "power":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 7, true);
                break;
            case "blow_window":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntFromByteWithBit(0, 5, 5, 3);
                break;
            case "in_out_cycle":
                bArrCopyOf[2] = (byte) DataHandleUtils.setIntByteWithBit(0, GeneralAirData.in_out_cycle ? 2 : 3, true);
                break;
            case "blow_head_foot":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntFromByteWithBit(0, 2, 5, 3);
                break;
            case "blow_foot":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntFromByteWithBit(0, 3, 5, 3);
                break;
            case "blow_head":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntFromByteWithBit(0, 1, 5, 3);
                break;
            case "blow_window_foot":
                bArrCopyOf[4] = (byte) DataHandleUtils.setIntFromByteWithBit(0, 4, 5, 3);
                break;
        }
        sendAirCommand(bArrCopyOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void set0xC8Data(Context context, int i, int i2) {
        byte[] bArr = getMsgMgr(context).get0x41Data();
        bArr[0] = 22;
        bArr[1] = -56;
        bArr[2] = (byte) i;
        bArr[3] = (byte) i2;
        CanbusMsgSender.sendMsg(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }
}
