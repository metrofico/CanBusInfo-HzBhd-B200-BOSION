package com.hzbhd.canbus.car._134;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private static boolean isDoorFirst = true;
    private List<OriginalCarDeviceUpdateEntity> OriginalCarDeviceInfo0x06List = new ArrayList();
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIndexBy4Bit(int i) {
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 3;
            }
        }
        return 0;
    }

    private int getIndexBy5Bit(int i) {
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 3;
            }
            if (i == 4) {
                return 4;
            }
        }
        return 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 17});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 18});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 19});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            setSwc();
            return;
        }
        if (i == 2) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData0x02();
            return;
        }
        if (i == 4) {
            setOriginalCarDeviceInfo0x04();
            return;
        }
        if (i == 5) {
            setOriginalCarDeviceInfo0x05();
            return;
        }
        if (i == 6) {
            setOriginalCarDeviceInfo0x06();
            return;
        }
        if (i == 8) {
            setRadarInfo();
            return;
        }
        if (i == 9) {
            setSettingData0x09();
            return;
        }
        if (i == 11) {
            setDriveData0x0b();
        } else if (i == 12) {
            setDriveData0x0c();
        } else {
            if (i != 127) {
                return;
            }
            setVersionInfo();
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setSettingData0x09() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        int indexBy5Bit = getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3));
        int indexBy4Bit = getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2));
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        int indexBy3Bit2 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2));
        int indexBy4Bit2 = getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
        int indexBy2Bit10 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
        int indexBy2Bit11 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
        int indexBy3Bit3 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2));
        int indexBy3Bit4 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
        int indexBy5Bit2 = getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3));
        int indexBy5Bit3 = getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3));
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        String strValueOf = String.valueOf(this.mCanBusInfoInt[6]);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[6];
        String strValueOf2 = String.valueOf(iArr[7]);
        int[] iArr2 = this.mCanBusInfoInt;
        int i2 = iArr2[7];
        String strValueOf3 = String.valueOf(iArr2[8]);
        int i3 = this.mCanBusInfoInt[8];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy5Bit)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy4Bit)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy3Bit)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit4)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy2Bit5)));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(indexBy3Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(indexBy4Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(indexBy2Bit6)));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(indexBy2Bit10)));
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(indexBy2Bit11)));
        arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(indexBy3Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 14, Integer.valueOf(indexBy3Bit4)));
        arrayList.add(new SettingUpdateEntity(0, 15, Integer.valueOf(indexBy5Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 16, Integer.valueOf(indexBy5Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 17, Integer.valueOf(boolBit7 ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(indexBy2Bit7)));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(indexBy2Bit8)));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(indexBy2Bit9)));
        arrayList.add(new SettingUpdateEntity(1, 3, strValueOf).setProgress(i));
        arrayList.add(new SettingUpdateEntity(1, 4, strValueOf2).setProgress(i2));
        arrayList.add(new SettingUpdateEntity(1, 5, strValueOf3).setProgress(i3));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDriveData0x0b() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, String.valueOf(((float) ((((iArr[2] * 256) + iArr[3]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, String.valueOf(((float) ((((iArr2[4] * 256) + iArr2[5]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, String.valueOf(((float) ((((iArr3[6] * 256) + iArr3[7]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 3, String.valueOf(((float) ((((iArr4[8] * 256) + iArr4[9]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 4, String.valueOf(((float) ((((iArr5[10] * 256) + iArr5[11]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 5, String.valueOf(((float) ((((iArr6[12] * 256) + iArr6[13]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 6, String.valueOf(((float) ((((iArr7[14] * 256) + iArr7[15]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 7, String.valueOf(((float) ((((iArr8[16] * 256) + iArr8[17]) * 0.1d) * 10.0d)) / 10.0f)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData0x0c() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, String.valueOf(((float) ((((iArr[2] * 256) + iArr[3]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 1, String.valueOf(((float) ((((iArr2[4] * 256) + iArr2[5]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, String.valueOf(((float) ((((iArr3[6] * 256) + iArr3[7]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, String.valueOf(((float) ((((iArr4[8] * 256) + iArr4[9]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, String.valueOf(((float) ((((iArr5[10] * 256) + iArr5[11]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 5, String.valueOf(((float) ((((iArr6[12] * 256) + iArr6[13]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 6, String.valueOf(((float) ((((iArr7[14] * 256) + iArr7[15]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 7, String.valueOf(((float) ((((iArr8[16] * 256) + iArr8[17]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr9 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 8, String.valueOf(((float) ((((iArr9[18] * 256) + iArr9[19]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr10 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 9, String.valueOf(((float) ((((iArr10[20] * 256) + iArr10[21]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr11 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 10, String.valueOf(((float) ((((iArr11[22] * 256) + iArr11[23]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr12 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 11, String.valueOf(((float) ((((iArr12[24] * 256) + iArr12[25]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr13 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 12, String.valueOf(((float) ((((iArr13[26] * 256) + iArr13[27]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr14 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 13, String.valueOf(((float) ((((iArr14[28] * 256) + iArr14[29]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr15 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 14, String.valueOf(((float) ((((iArr15[30] * 256) + iArr15[31]) * 0.1d) * 10.0d)) / 10.0f)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSwc() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 3) {
            realKeyClick(20);
            return;
        }
        if (i == 4) {
            realKeyClick(21);
            return;
        }
        switch (i) {
            case 8:
                realKeyClick(3);
                break;
            case 9:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 10:
                realKeyClick(14);
                break;
            case 11:
                realKeyClick(15);
                break;
            default:
                switch (i) {
                    case 32:
                        realKeyClick(59);
                        break;
                    case 33:
                        realKeyClick(52);
                        break;
                    case 34:
                        realKeyClick(128);
                        break;
                    case 35:
                        realKeyClick(79);
                        break;
                    case 36:
                        realKeyClick(45);
                        break;
                    case 37:
                        realKeyClick(46);
                        break;
                    case 38:
                        realKeyClick(47);
                        break;
                    case 39:
                        realKeyClick(48);
                        break;
                    case 40:
                        realKeyClick(49);
                        break;
                    case 41:
                        realKeyClick(HotKeyConstant.K_SLEEP);
                        break;
                    case 42:
                        realKeyClick(50);
                        break;
                    default:
                        switch (i) {
                            case NfDef.STATE_3WAY_M_HOLD /* 240 */:
                                realKeyClick2(7);
                                break;
                            case 241:
                                realKeyClick2(8);
                                break;
                            case 242:
                                realKeyClick3(48);
                                break;
                            case 243:
                                realKeyClick3(47);
                                break;
                        }
                }
        }
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_2(context, i, iArr[2], iArr[3]);
    }

    private void setDoorData0x02() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void setOriginalCarDeviceInfo0x04() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, sb.append((iArr[5] * 256) + iArr[6]).append("").toString()));
        GeneralOriginalCarDeviceData.mList = arrayList;
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1) {
            GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.have_cd);
        } else {
            GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.have_not_cd);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 0) {
            GeneralOriginalCarDeviceData.discStatus = this.mContext.getResources().getString(R.string.cd_status_normal);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 1) {
            GeneralOriginalCarDeviceData.discStatus = this.mContext.getResources().getString(R.string.cd_status_error);
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalCarDeviceInfo0x05() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb.append((iArr[5] * 256) + iArr[6]).append("").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, sb2.append((iArr2[7] * 256) + iArr2[8]).append("").toString()));
        GeneralOriginalCarDeviceData.mList = arrayList;
        GeneralOriginalCarDeviceData.rpt_off = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 0;
        GeneralOriginalCarDeviceData.rpt_track = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 1;
        GeneralOriginalCarDeviceData.rpt_fold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 2;
        GeneralOriginalCarDeviceData.rdm_off = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 0;
        GeneralOriginalCarDeviceData.rdm_fold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 1;
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 2;
        int i = this.mCanBusInfoInt[3];
        if (i == 0) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.cd_stop_close);
        } else if (i == 1) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.cd_pause);
        } else if (i == 2) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.cd_play);
        } else if (i == 3) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.disc_out);
        } else if (i == 4) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.reading);
        }
        DecimalFormat decimalFormat = new DecimalFormat("00");
        int[] iArr3 = this.mCanBusInfoInt;
        int i2 = (iArr3[11] * 256) + iArr3[12];
        int i3 = (iArr3[9] * 256) + iArr3[10];
        if (i2 <= i3) {
            if (i2 >= 3600) {
                GeneralOriginalCarDeviceData.startTime = decimalFormat.format((i2 / 60) / 60) + ":" + decimalFormat.format((i2 % 3600) / 60) + ":" + decimalFormat.format(i2 % 60);
            } else {
                GeneralOriginalCarDeviceData.startTime = decimalFormat.format((i2 % 3600) / 60) + ":" + decimalFormat.format(i2 % 60);
            }
            if (i3 >= 3600) {
                GeneralOriginalCarDeviceData.endTime = decimalFormat.format((i3 / 60) / 60) + ":" + decimalFormat.format((i3 % 3600) / 60) + ":" + decimalFormat.format(i3 % 60);
            } else {
                GeneralOriginalCarDeviceData.endTime = decimalFormat.format((i3 % 3600) / 60) + ":" + decimalFormat.format(i3 % 60);
            }
            if (i3 == 0) {
                GeneralOriginalCarDeviceData.progress = 0;
            } else {
                GeneralOriginalCarDeviceData.progress = (i2 * 100) / i3;
            }
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalCarDeviceInfo0x06() {
        int i = 3;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        String strDecodeBytes2StrNeedSendData = this.mCanBusInfoInt[2] != 1 ? decodeBytes2StrNeedSendData(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4), DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 2, (byte) 0)) : null;
        int i2 = this.mCanBusInfoInt[2];
        if (i2 == 1) {
            return;
        }
        if (i2 == 0) {
            this.OriginalCarDeviceInfo0x06List.clear();
        } else {
            i = i2 == 2 ? 4 : 5;
        }
        Log.d("cwh", "songInfo = " + strDecodeBytes2StrNeedSendData + "    index = " + i);
        this.OriginalCarDeviceInfo0x06List.add(new OriginalCarDeviceUpdateEntity(i, strDecodeBytes2StrNeedSendData));
        GeneralOriginalCarDeviceData.mList = this.OriginalCarDeviceInfo0x06List;
        updateOriginalCarDeviceActivity(null);
    }

    private void setRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        int i2 = iArr[4];
        RadarInfoUtil.setFrontRadarLocationData(4, i, i2, i2, iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        int i3 = iArr2[6];
        int i4 = iArr2[7];
        RadarInfoUtil.setRearRadarLocationData(4, i3, i4, i4, iArr2[8]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private String decodeBytes2StrNeedSendData(int i, byte[] bArr) throws Exception {
        LogUtil.showLog("code:" + i);
        if (i == 0) {
            return new String(bArr, "ascii");
        }
        if (i == 1) {
            return DataHandleUtils.Byte2Unicode(bArr, true);
        }
        if (i != 2) {
            return i != 3 ? "" : new String(bArr, "UTF-8");
        }
        return DataHandleUtils.Byte2Unicode(bArr, false);
    }

    public static String stringToAscii(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i != charArray.length - 1) {
                stringBuffer.append((int) charArray[i]).append(",");
            } else {
                stringBuffer.append((int) charArray[i]);
            }
        }
        return stringBuffer.toString();
    }

    private int getIndexBy3Bit(int i) {
        LogUtil.showLog("getIndexBy3Bit:" + i);
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
        }
        return 0;
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }
}
