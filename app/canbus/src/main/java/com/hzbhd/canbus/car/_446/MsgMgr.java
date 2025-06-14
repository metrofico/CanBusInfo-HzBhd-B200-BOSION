package com.hzbhd.canbus.car._446;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._446.CanObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.data.WmSharedKey;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private String airJsonStr;
    Context context;
    private int[] last0x217;
    private int[] mCanBusInfoInt;
    private UiMgr mUiMgr;
    boolean lastLogo = false;
    int lastRiJianXingChe = 0;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    private boolean lastDaoCheTiShiYin = false;
    private boolean lastDingDengMenKong = false;
    private boolean lastSouCheTianChuanZiDong = false;
    private boolean lastQianZhuangShiDeng = false;
    private int lastBwhjd = 0;
    int lastZxld = 0;
    private int lastDouPoHuanJiang = 0;
    private boolean lastZiDongZhuChe = false;
    private int lastEsp = 0;
    private boolean last0x1CA = false;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.context = context;
        CanbusMsgSender.sendMsg(new byte[]{33, 3, 35, -1, -1, 0, 0, 0, 0, 0, 0});
        WmCarData.tire_unit = SharePreUtil.getStringValue(context, WmSharedKey.KEY_TIRE_UNIT, "PSI");
        getUiMgr(context).registerCanBusAirControlListener();
        initDPHJ(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 0, 0, 0, 0, 1});
        initDPHJ(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        initDPHJ(this.context);
    }

    private void initDPHJ(Context context) {
        if (SharePreUtil.getBoolValue(context, WmSharedKey.KEY_DPHJ_SWITCH_STATE, false)) {
            Log.d("fxHouDPHJ", "ON");
            WmCarData.douPoHuanJiang = true;
            CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 1, 0, 0, 0, 0, 0, 0, 0, 1});
        } else {
            Log.d("fxHouDPHJ", "OFF");
            WmCarData.douPoHuanJiang = false;
            CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 0, 0, 0, 0, 0, 0, 0, 0, 1});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void linInfoChange(Context context, byte[] bArr) {
        super.linInfoChange(context, bArr);
        if (bArr[2] != 32) {
            return;
        }
        setLinBus0x20(bArr);
    }

    private void setLinBus0x20(byte[] bArr) {
        if (this.lastLogo != (DataHandleUtils.getIntFromByteWithBit(bArr[3], 3, 3) == 1)) {
            WmCarData.logo = DataHandleUtils.getIntFromByteWithBit(bArr[3], 3, 3) == 1;
            CanObserver.getInstance().dataChange();
            this.lastLogo = WmCarData.logo;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        switch (getMsDataType(byteArrayToIntArray)) {
            case 160:
                setTrack(this.mCanBusInfoInt);
                break;
            case 293:
                set0x125(this.mCanBusInfoInt);
                break;
            case HotKeyConstant.K_AIR_POWER /* 367 */:
                set0x16F(this.mCanBusInfoInt);
                break;
            case 371:
                set0x173(this.mCanBusInfoInt);
                break;
            case 458:
                set0x1CA(this.mCanBusInfoInt);
                break;
            case 529:
                CanbusInfoChangeListener.getInstance().reportMsBasicInfo(intArrayToJsonStr(this.mCanBusInfoInt));
                break;
            case 534:
                set0x216(this.mCanBusInfoInt);
                break;
            case 535:
                set0x217(this.mCanBusInfoInt);
                break;
            case 565:
                set0x235(this.mCanBusInfoInt);
                break;
            case 567:
                setSWC(this.mCanBusInfoInt);
                break;
            case 668:
                set0x29C(this.mCanBusInfoInt);
                break;
            case 908:
                set0x38C(this.mCanBusInfoInt);
                break;
            case 931:
                ShareCanInfo(this.mCanBusInfoInt);
                break;
        }
    }

    private void setTrack(int[] iArr) {
        int msbLsbResult = DataHandleUtils.getMsbLsbResult(iArr[7], iArr[8]);
        if (msbLsbResult > 60000) {
            GeneralParkData.trackAngle = ((65535 - msbLsbResult) / 200) + 1;
        } else {
            GeneralParkData.trackAngle = (-(msbLsbResult / 200)) + 1;
        }
        updateParkUi(null, this.context);
    }

    private void set0x29C(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[7], 5, 2);
        if (this.lastRiJianXingChe != intFromByteWithBit) {
            if (intFromByteWithBit == 1) {
                WmCarData.riJianXingCheDeng = true;
            }
            if (intFromByteWithBit == 2) {
                WmCarData.riJianXingCheDeng = false;
            }
            CanObserver.getInstance().dataChange();
            this.lastRiJianXingChe = intFromByteWithBit;
        }
    }

    private void set0x217(int[] iArr) {
        if (Arrays.equals(this.last0x217, iArr)) {
            return;
        }
        WmCarData.tire_front_left = Double.parseDouble(this.df_2Decimal.format(iArr[7] * 2.75d));
        WmCarData.tire_front_right = Double.parseDouble(this.df_2Decimal.format(iArr[8] * 2.75d));
        WmCarData.tire_rear_left = Double.parseDouble(this.df_2Decimal.format(iArr[9] * 2.75d));
        WmCarData.tire_rear_right = Double.parseDouble(this.df_2Decimal.format(iArr[10] * 2.75d));
        WmCarData.tire_temp_front_left = (iArr[11] - 60) + getTempUnitC(this.context);
        WmCarData.tire_temp_front_right = (iArr[12] - 60) + getTempUnitC(this.context);
        WmCarData.tire_temp_rear_left = (iArr[13] - 60) + getTempUnitC(this.context);
        WmCarData.tire_temp_rear_right = (iArr[14] - 60) + getTempUnitC(this.context);
        CanObserver.getInstance().dataChange();
        this.last0x217 = iArr;
    }

    private void set0x38C(int[] iArr) {
        if (this.lastDaoCheTiShiYin != DataHandleUtils.getBoolBit2(iArr[7])) {
            WmCarData.daoCheTiShiYin = DataHandleUtils.getBoolBit2(iArr[7]);
            CanObserver.getInstance().dataChange();
            this.lastDaoCheTiShiYin = WmCarData.daoCheTiShiYin;
        }
    }

    private void set0x235(int[] iArr) {
        if (this.lastDingDengMenKong != DataHandleUtils.getBoolBit3(iArr[10])) {
            WmCarData.dingDengMenKong = DataHandleUtils.getBoolBit3(iArr[10]);
            CanObserver.getInstance().dataChange();
            this.lastDingDengMenKong = WmCarData.dingDengMenKong;
        }
    }

    private void set0x173(int[] iArr) {
        if (this.lastSouCheTianChuanZiDong != (DataHandleUtils.getIntFromByteWithBit(iArr[14], 0, 2) == 2)) {
            WmCarData.suoCheTianChaungZiDongGuanBi = DataHandleUtils.getIntFromByteWithBit(iArr[14], 0, 2) == 2;
            CanObserver.getInstance().dataChange();
            this.lastSouCheTianChuanZiDong = WmCarData.suoCheTianChaungZiDongGuanBi;
        }
        boolean boolBit3 = DataHandleUtils.getBoolBit3(iArr[14]);
        if (this.lastQianZhuangShiDeng != boolBit3) {
            WmCarData.qianZhuangShiDeng = boolBit3;
            CanObserver.getInstance().dataChange();
            this.lastQianZhuangShiDeng = boolBit3;
        }
    }

    private void set0x216(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[12], 3, 3);
        if (this.lastBwhjd != intFromByteWithBit) {
            if (intFromByteWithBit == 0) {
                WmCarData.banWoHuiJiaDeng = "OFF";
            } else if (intFromByteWithBit == 1) {
                WmCarData.banWoHuiJiaDeng = "30s";
            } else if (intFromByteWithBit == 2) {
                WmCarData.banWoHuiJiaDeng = "60s";
            } else if (intFromByteWithBit == 3) {
                WmCarData.banWoHuiJiaDeng = "90s";
            } else if (intFromByteWithBit == 4) {
                WmCarData.banWoHuiJiaDeng = "120s";
            }
            CanObserver.getInstance().dataChange();
            this.lastBwhjd = intFromByteWithBit;
        }
    }

    private void set0x16F(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[9], 6, 2);
        if (this.lastZxld != intFromByteWithBit) {
            if (intFromByteWithBit == 1) {
                WmCarData.zhuanXiangLiDu = this.context.getString(R.string._446_wm_car_4);
            } else if (intFromByteWithBit == 2) {
                WmCarData.zhuanXiangLiDu = this.context.getString(R.string._446_wm_car_5);
            } else if (intFromByteWithBit == 3) {
                WmCarData.zhuanXiangLiDu = this.context.getString(R.string._446_wm_car_6);
            }
            CanObserver.getInstance().dataChange();
            this.lastZxld = intFromByteWithBit;
        }
    }

    private void set0x125(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[12], 6, 2);
        if (this.lastDouPoHuanJiang != intFromByteWithBit) {
            if (intFromByteWithBit == 1) {
                WmCarData.douPoHuanJiang = true;
            } else if (intFromByteWithBit == 0) {
                WmCarData.douPoHuanJiang = false;
            }
            CanObserver.getInstance().dataChange();
            this.lastDouPoHuanJiang = intFromByteWithBit;
        }
        if (this.lastZiDongZhuChe != (DataHandleUtils.getIntFromByteWithBit(iArr[10], 4, 2) != 0)) {
            WmCarData.ziDongZhuChe = DataHandleUtils.getIntFromByteWithBit(iArr[10], 4, 2) != 0;
            CanObserver.getInstance().dataChange();
            this.lastZiDongZhuChe = WmCarData.ziDongZhuChe;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 2);
        if (this.lastEsp != intFromByteWithBit2) {
            if (intFromByteWithBit2 == 1) {
                WmCarData.EspWengDing = this.context.getString(R.string._446_wm_car_0);
            } else if (intFromByteWithBit2 == 2) {
                WmCarData.EspWengDing = this.context.getString(R.string._446_wm_car_1);
            } else if (intFromByteWithBit2 == 3) {
                WmCarData.EspWengDing = this.context.getString(R.string._446_wm_car_2);
            }
            CanObserver.getInstance().dataChange();
            this.lastEsp = intFromByteWithBit2;
        }
    }

    private void set0x1CA(int[] iArr) {
        if (this.last0x1CA != (!DataHandleUtils.getBoolBit5(iArr[11]))) {
            WmCarData.daiSuHuanXing = !DataHandleUtils.getBoolBit5(iArr[11]);
            CanObserver.getInstance().dataChange();
            this.last0x1CA = WmCarData.daiSuHuanXing;
        }
    }

    private void setSWC(int[] iArr) {
        if (DataHandleUtils.getBoolBit4(iArr[10])) {
            Log.d("fxHouSwc", "K_DOME");
            realKeyClick4(this.context, 46);
            return;
        }
        if (DataHandleUtils.getBoolBit5(iArr[10])) {
            Log.d("fxHouSwc", "K_UP");
            realKeyClick4(this.context, 45);
            return;
        }
        if (DataHandleUtils.getBoolBit6(iArr[10])) {
            Log.d("fxHouSwc", "K_VOL_DN");
            realKeyClick4(this.context, 8);
            return;
        }
        if (DataHandleUtils.getBoolBit7(iArr[10])) {
            Log.d("fxHouSwc", "K_VOL_UP");
            realKeyClick4(this.context, 7);
        } else if (DataHandleUtils.getBoolBit0(iArr[12])) {
            Log.d("fxHouSwc", "K_HOME");
            realKeyClick4(this.context, 52);
        } else if (DataHandleUtils.getBoolBit7(iArr[13])) {
            Log.d("fxHouSwc", "K_MUTE");
            realKeyClick4(this.context, 3);
        } else {
            Log.d("fxHouSwc", "K_NONE");
            realKeyClick4(this.context, 0);
        }
    }

    private int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    public void ShareCanInfo(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportAllCanBusData(intArrayToJsonStr(iArr));
    }

    private String intArrayToJsonStr(int[] iArr) {
        this.airJsonStr = "{";
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + "}";
            } else {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + ",";
            }
        }
        return this.airJsonStr;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
