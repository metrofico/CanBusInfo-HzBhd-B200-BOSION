package com.hzbhd.canbus.car._267;

import android.content.Context;
import android.content.res.Resources;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.varia.ExternallyRolledFileAppender;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            keyControl0x20();
            return;
        }
        if (i == 35) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 120) {
            originalPanelKeyBtnInfo();
            return;
        }
        if (i == 121) {
            setOriginalVolumeInfo();
        } else if (i == 123) {
            setChannelInfo();
        } else {
            if (i != 124) {
                return;
            }
            setMediaInfo();
        }
    }

    private void keyControl0x20() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick1(0);
        } else if (i != 1) {
            if (i == 2) {
                realKeyClick1(8);
                return;
            }
            if (i == 3) {
                realKeyClick1(46);
                return;
            } else if (i == 4) {
                realKeyClick1(45);
                return;
            } else {
                if (i != 7) {
                    return;
                }
                realKeyClick1(15);
                return;
            }
        }
        realKeyClick1(7);
    }

    private void originalPanelKeyBtnInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick1(0);
        }
        switch (i) {
            case 17:
                realKeyClick1(76);
                break;
            case 18:
                realKeyClick1(77);
                break;
            case 19:
                realKeyClick1(141);
                break;
            case 20:
                realKeyClick1(30);
                break;
            case 21:
                realKeyClick1(49);
                break;
            default:
                switch (i) {
                    case 25:
                        realKeyClick1(27);
                        break;
                    case 26:
                        realKeyClick1(4);
                        break;
                    case 27:
                        realKeyClick1(48);
                        break;
                    case 28:
                        realKeyClick1(47);
                        break;
                    case 29:
                        realKeyClick1(20);
                        break;
                    case 30:
                        realKeyClick1(21);
                        break;
                    case 31:
                        realKeyClick1(33);
                        break;
                    case 32:
                        realKeyClick1(34);
                        break;
                    case 33:
                        realKeyClick1(35);
                        break;
                    default:
                        switch (i) {
                            case 36:
                                realKeyClick1(36);
                                break;
                            case 37:
                                realKeyClick1(37);
                                break;
                            case 38:
                                realKeyClick1(38);
                                break;
                        }
                }
        }
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[4], iArr[5]);
    }

    private void setOriginalVolumeInfo() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[3];
        String string2 = "";
        if (i == 0) {
            string2 = this.mContext.getResources().getString(R.string.mute);
        } else if (i <= 40 && i > 0) {
            string2 = this.mCanBusInfoInt[3] + "";
        }
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 0) {
            string = this.mContext.getResources().getString(R.string.energy_no_display);
        } else {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set71_1);
        }
        arrayList.add(new SettingUpdateEntity(0, 1, string));
        arrayList.add(new SettingUpdateEntity(0, 2, string2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setMediaInfo() throws Resources.NotFoundException {
        String string;
        String string2;
        String str;
        String string3;
        String str2;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.current_media_state_1);
        } else if (i == 1) {
            string = this.mContext.getResources().getString(R.string.radio);
        } else if (i == 2) {
            string = this.mContext.getResources().getString(R.string.current_media_state_3);
        } else {
            string = i != 3 ? "" : this.mContext.getResources().getString(R.string.current_media_state_4);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 0) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            string2 = sb.append(((iArr[4] * 256) + iArr[5]) * 0.01f).append("MHz").toString();
            str = "FM1";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 1) {
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            string2 = sb2.append(((iArr2[4] * 256) + iArr2[5]) * 0.01f).append("MHz").toString();
            str = "FM2";
        } else {
            StringBuilder sb3 = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            string2 = sb3.append((iArr3[4] * 256) + iArr3[5]).append("KHz").toString();
            str = "AM";
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 0) {
            string3 = this.mContext.getResources().getString(R.string.null_value);
        } else {
            string3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + "";
        }
        switch (this.mCanBusInfoInt[11]) {
            case 0:
                str2 = "LOAD";
                break;
            case 1:
                str2 = "READ";
                break;
            case 2:
                str2 = "EJECT";
                break;
            case 3:
                str2 = "NO DISC";
                break;
            case 4:
                str2 = "CHECK DISC";
                break;
            case 5:
                str2 = "BUSY";
                break;
            case 6:
                str2 = ExternallyRolledFileAppender.OK;
                break;
            default:
                str2 = "";
                break;
        }
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String str3 = decimalFormat.format(this.mCanBusInfoInt[9]) + ":" + decimalFormat.format(this.mCanBusInfoInt[10]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        arrayList.add(new DriverUpdateEntity(0, 1, str));
        arrayList.add(new DriverUpdateEntity(0, 2, string3));
        arrayList.add(new DriverUpdateEntity(0, 3, string2));
        arrayList.add(new DriverUpdateEntity(0, 4, this.mCanBusInfoInt[7] + ""));
        arrayList.add(new DriverUpdateEntity(0, 5, this.mCanBusInfoInt[8] + ""));
        arrayList.add(new DriverUpdateEntity(0, 6, str3));
        arrayList.add(new DriverUpdateEntity(0, 7, str2));
        updateGeneralDriveData(arrayList);
        updateSettingActivity(null);
    }

    private void setChannelInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[2] == 0 ? "original_car_host" : "original_car_aux"));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private String resolveLeftAndRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (31 == i) {
            return "HI";
        }
        if (1 > i || i > 28) {
            return "";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return (i + 59) + getTempUnitF(this.mContext);
        }
        return ((i + 31) * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusAirInfoCopy)) {
            return true;
        }
        this.mCanBusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
