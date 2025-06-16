package com.hzbhd.canbus.car._327;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    int currentCanDifferentId;
    int currentEachCanId;
    private byte freqHi;
    private byte freqLo;
    private byte[] m0x21AirData;
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x24Data;
    private int[] m0x25Data;
    private int[] m0x26Data;
    private int[] m0x42Data;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private boolean mPanoramicStatus2;
    private byte[] mTrackData;
    private UiMgr mUiMgr;
    private DecimalFormat df = new DecimalFormat("0.00");
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr2 = new String[10];
    private String[] arr3 = new String[10];
    int nowYear = PathInterpolatorCompat.MAX_NUM_POINTS;
    int nowMonth = 13;
    int nowDay = 32;
    int nowHours = 24;
    int nowMins = 61;
    int nowSecond = 61;
    private int mAirRearMode = 0;

    private int get0x22RadarValue(int i) {
        if (i != 0) {
            return ((int) ((i / 32.0f) * 10.0f)) + 1;
        }
        return 0;
    }

    private int get0x23RadarValue(int i) {
        if (i != 0) {
            return ((int) ((i / 15.0f) * 10.0f)) + 1;
        }
        return 0;
    }

    private int get0x25BothSidesRadarGridNumber(int i) {
        if (i >= 0 && i <= 10) {
            return 1;
        }
        if (i > 10 && i <= 20) {
            return 2;
        }
        if (i > 20 && i <= 30) {
            return 3;
        }
        if (i > 30 && i <= 40) {
            return 4;
        }
        if (i > 40 && i <= 50) {
            return 5;
        }
        if (i > 50 && i <= 60) {
            return 6;
        }
        if (i > 60 && i <= 70) {
            return 7;
        }
        if (i > 70 && i <= 97) {
            return 8;
        }
        if (i <= 97 || i > 124) {
            return (i <= 124 || i > 150) ? 1 : 10;
        }
        return 9;
    }

    private int get0x25RadarValue(int i) {
        if (i != 0) {
            return ((int) ((i / 32.0f) * 10.0f)) + 1;
        }
        return 0;
    }

    private int get0x25middleRadarGridNumber(int i) {
        if (i >= 0 && i <= 15) {
            return 1;
        }
        if (i > 15 && i <= 30) {
            return 2;
        }
        if (i > 30 && i <= 45) {
            return 3;
        }
        if (i > 45 && i <= 60) {
            return 4;
        }
        if (i > 60 && i <= 90) {
            return 5;
        }
        if (i > 90 && i <= 120) {
            return 6;
        }
        if (i <= 120 || i > 149) {
            return i > 149 ? 10 : 1;
        }
        return 7;
    }

    private int getTemperatureLevel(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;
            case 12:
                return 12;
            case 13:
                return 13;
            case 14:
                return 14;
            case 15:
                return 15;
            default:
                return 0;
        }
    }

    private void set0x49BackWarning(Context context) {
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        this.currentCanDifferentId = getCurrentCanDifferentId();
        this.currentEachCanId = getCurrentEachCanId();
        this.mContext = context;
        GeneralTireData.isHaveSpareTire = false;
        getmUigMgr(this.mContext).CartypeSend();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 48) {
            set0x30VersionInfo();
        } else if (i != 56) {
            if (i != 57) {
                switch (i) {
                    case 32:
                        set0x20WheelKey(context);
                        break;
                    case 33:
                        set0x21AirInfo(context);
                        break;
                    case 34:
                        set0x22RearRadarInfo();
                        break;
                    case 35:
                        set0x23FrontRadarInfo();
                        break;
                    case 36:
                        set0x24BaseInfo();
                        break;
                    case 37:
                        set0x25RearRadarInfo();
                        break;
                    case 38:
                        set0x26FrontRadarInfo();
                        break;
                    case 39:
                        set0x27OutDoorTemperature(context);
                        break;
                    case 40:
                        set0x28TrackData();
                        break;
                    case 41:
                        set0x29TrackData();
                        break;
                    default:
                        switch (i) {
                            case 64:
                                set0x40TireInfo();
                                break;
                            case 65:
                                set0x41SettingsInfo(context);
                                break;
                            case 66:
                                set0x42PanoramicData();
                                break;
                            case 67:
                                set0x43SettingsInfo(context);
                                break;
                            default:
                                switch (i) {
                                    case 71:
                                        set0x47OperationInfo();
                                        break;
                                    case 72:
                                        set0x48CarInfo(context);
                                        break;
                                    case 73:
                                        set0x49BackWarning(context);
                                        break;
                                }
                        }
                }
            }
            set0x39TireAlertInfo();
            return;
        }
        set0x38TireInfo();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        super.voiceControlInfo(str);
        str.hashCode();
        if (str.equals("skylight.open")) {
            sendVoiceMsg(0, true);
        } else if (str.equals("skylight.close")) {
            sendVoiceMsg(0, false);
        }
    }

    private void sendVoiceMsg(int i, boolean z) {
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{24, -88, (byte) i, 1});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{24, -88, (byte) i, 0});
        }
    }

    private void set0x48CarInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(((iArr[2] * 256) + iArr[3]) / 10).append("Kwh").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append(((iArr2[4] * 256) + iArr2[5]) / 10).append("Kwh").toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, sb3.append(((iArr3[6] * 256) + iArr3[7]) / 2).append("KM").toString()));
        arrayList.add(new DriverUpdateEntity(0, 3, (this.mCanBusInfoInt[8] - 40) + getTempUnitC(context)));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 4, sb4.append(((iArr4[9] * 256) + iArr4[10]) / 10).append("A").toString()));
        StringBuilder sb5 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 5, sb5.append(((iArr5[11] * 256) + iArr5[12]) / 10).append("V").toString()));
        arrayList.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[13] + "分"));
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 1) == 0) {
            arrayList.add(new DriverUpdateEntity(0, 7, ((this.mCanBusInfoInt[14] >> 1) * 10) + "分/保温关闭"));
        } else {
            arrayList.add(new DriverUpdateEntity(0, 7, "保温开启/" + ((this.mCanBusInfoInt[14] >> 1) * 10) + "分"));
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 3, 1) == 0) {
            arrayList.add(new DriverUpdateEntity(0, 8, "False"));
        } else {
            arrayList.add(new DriverUpdateEntity(0, 8, "True"));
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 0) {
            arrayList.add(new DriverUpdateEntity(0, 9, "No Error 无错误"));
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 1) {
            arrayList.add(new DriverUpdateEntity(0, 9, " Warning 警告运行（一级）"));
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 2) {
            arrayList.add(new DriverUpdateEntity(0, 9, "Derating 降功率运行（二级）"));
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 3) == 3) {
            arrayList.add(new DriverUpdateEntity(0, 9, "Disable 禁用 （三级）"));
        }
        int i = this.mCanBusInfoInt[16];
        if (i == 0) {
            arrayList.add(new DriverUpdateEntity(0, 10, "Normal"));
        } else if (i == 1) {
            arrayList.add(new DriverUpdateEntity(0, 10, "Grade 1"));
        } else if (i == 2) {
            arrayList.add(new DriverUpdateEntity(0, 10, "Grade 2"));
        } else if (i == 3) {
            arrayList.add(new DriverUpdateEntity(0, 10, "Grade 3"));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x47OperationInfo() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(((iArr[2] * 256) + iArr[3]) / 10).append("V").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((((iArr2[4] * 256) + iArr2[5]) - 6000) / 10).append("A").toString()));
        arrayList.add(new DriverUpdateEntity(0, 2, this.mCanBusInfoInt[6] + "%"));
        arrayList.add(new DriverUpdateEntity(0, 3, (this.mCanBusInfoInt[7] - 50) + getTempUnitC(this.mContext)));
        arrayList.add(new DriverUpdateEntity(0, 4, (this.mCanBusInfoInt[8] - 50) + getTempUnitC(this.mContext)));
        arrayList.add(new DriverUpdateEntity(0, 5, (this.mCanBusInfoInt[9] - 50) + getTempUnitC(this.mContext)));
        arrayList.add(new DriverUpdateEntity(0, 6, (this.mCanBusInfoInt[10] - 50) + getTempUnitC(this.mContext)));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 7, sb3.append(((iArr3[11] * 256) + iArr3[12]) - 200).append("KW").toString()));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 8, sb4.append(((iArr4[13] * 256) + iArr4[14]) - 32000).append("RPM").toString()));
        StringBuilder sbAppend = new StringBuilder().append("上部分值").append(this.mCanBusInfoInt[15]).append("V / 下部分值");
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 9, sbAppend.append(((iArr5[16] * 256) + iArr5[17]) / 100).append("V").toString()));
        StringBuilder sbAppend2 = new StringBuilder().append("上部分值").append(this.mCanBusInfoInt[18]).append("V / 下部分值");
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 10, sbAppend2.append(((iArr6[19] * 256) + iArr6[20]) / 100).append("V").toString()));
        arrayList.add(new DriverUpdateEntity(0, 11, this.mCanBusInfoInt[21] + getTempUnitC(this.mContext)));
        arrayList.add(new DriverUpdateEntity(0, 12, this.mCanBusInfoInt[22] + getTempUnitC(this.mContext)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x43SettingsInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        if (getmUigMgr(context).getLeftIndexes(context, "_327_function_switch") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 13, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 14, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 15, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 16, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 17, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 18, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 19, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 20, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 21, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 22, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 23, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 24, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 25, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 26, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_switch"), 27, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_function_level_setting") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_level_setting"), 0, Integer.valueOf(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_level_setting"), 1, Integer.valueOf(this.mCanBusInfoInt[10])));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection"), 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection"), 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection"), 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection"), 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 3))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_function_mode_selection"), 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3))));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_Face_recognition") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_Face_recognition"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_Face_recognition"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 2))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x42PanoramicData() {
        if (isPanoramicDataChange()) {
            ArrayList arrayList = new ArrayList();
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            } else if (i == 1) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, true));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            } else {
                arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[2] == 4));
                arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[2] == 5));
                arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[2] == 6));
                arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[2] == 7));
                arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[2] == 8));
            }
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x41SettingsInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        if (getmUigMgr(context).getLeftIndexes(context, "_327_setting_atmosphere_lamp") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_atmosphere_lamp"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_atmosphere_lamp"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_setting_chair") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_chair"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_setting_the_light_that_accompanies_me_home") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_the_light_that_accompanies_me_home"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_setting_lane_departure") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_lane_departure"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_setting_the_headlamps") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_the_headlamps"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        }
        if (getmUigMgr(context).getLeftIndexes(context, "_327_setting_environmental_lighting") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_environmental_lighting"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_environmental_lighting"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(context).getLeftIndexes(context, "_327_setting_environmental_lighting"), 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) - 1)));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x40TireInfo() {
        boolean z;
        char c;
        boolean z2;
        char c2;
        boolean z3;
        char c3;
        boolean z4;
        char c4;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            if (iArr[4] == 255) {
                this.arr0[0] = "温度：--" + getTempUnitC(this.mContext);
            } else {
                this.arr0[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
            }
            if (this.mCanBusInfoInt[3] == 255) {
                this.arr0[1] = "胎压：--KPa";
            } else {
                this.arr0[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                this.arr0[2] = "系统：故障";
                z = true;
            } else {
                this.arr0[2] = "系统：正常";
                z = false;
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
                this.arr0[3] = "信号：丢失";
                z = true;
            } else {
                this.arr0[3] = "信号：正常";
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                this.arr0[4] = "系统自检：异常";
                z = true;
            } else {
                this.arr0[4] = "系统自检：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
                this.arr0[5] = "胎压故障灯：开";
                z = true;
            } else {
                this.arr0[5] = "胎压故障灯：关";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
                this.arr0[6] = "电池：电量低";
                z = true;
            } else {
                this.arr0[6] = "电池：正常";
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
                this.arr0[7] = "胎温：高温";
                z = true;
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr0[7] = "胎温：超高温";
                z = true;
            }
            if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr0[7] = "胎温：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
                this.arr0[8] = "气压：低";
                z = true;
            }
            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr0[8] = "气压：高";
                z = true;
            }
            if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr0[8] = "气压：正常";
            }
            if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
                c = '\t';
                this.arr0[9] = "漏气：慢速";
                z = true;
            } else {
                c = '\t';
            }
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr0[c] = "漏气：快速";
                z = true;
            }
            if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr0[9] = "漏气：无";
            }
            if (!z) {
                this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
            }
        } else if (i == 1) {
            if (iArr[4] == 255) {
                this.arr1[0] = "温度：--" + getTempUnitC(this.mContext);
            } else {
                this.arr1[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
            }
            if (this.mCanBusInfoInt[3] == 255) {
                this.arr1[1] = "胎压：--KPa";
            } else {
                this.arr1[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                this.arr1[2] = "系统：故障";
                z2 = true;
            } else {
                this.arr1[2] = "系统：正常";
                z2 = false;
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
                this.arr1[3] = "信号：丢失";
                z2 = true;
            } else {
                this.arr1[3] = "信号：正常";
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                this.arr1[4] = "系统自检：异常";
                z2 = true;
            } else {
                this.arr1[4] = "系统自检：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
                this.arr1[5] = "胎压故障灯：开";
                z2 = true;
            } else {
                this.arr1[5] = "胎压故障灯：关";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
                this.arr1[6] = "电池：电量低";
                z2 = true;
            } else {
                this.arr1[6] = "电池：正常";
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
                this.arr1[7] = "胎温：高温";
                z2 = true;
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr1[7] = "胎温：超高温";
                z2 = true;
            }
            if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr1[7] = "胎温：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
                this.arr1[8] = "气压：低";
                z2 = true;
            }
            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr1[8] = "气压：高";
                z2 = true;
            }
            if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr1[8] = "气压：正常";
            }
            if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
                c2 = '\t';
                this.arr1[9] = "漏气：慢速";
                z2 = true;
            } else {
                c2 = '\t';
            }
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr1[c2] = "漏气：快速";
                z2 = true;
            }
            if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr1[9] = "漏气：无";
            }
            if (!z2) {
                this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
            }
        } else if (i == 2) {
            if (iArr[4] == 255) {
                this.arr2[0] = "温度：--" + getTempUnitC(this.mContext);
            } else {
                this.arr2[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
            }
            if (this.mCanBusInfoInt[3] == 255) {
                this.arr2[1] = "胎压：--KPa";
            } else {
                this.arr2[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                this.arr2[2] = "系统：故障";
                z3 = true;
            } else {
                this.arr2[2] = "系统：正常";
                z3 = false;
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
                this.arr2[3] = "信号：丢失";
                z3 = true;
            } else {
                this.arr2[3] = "信号：正常";
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                this.arr2[4] = "系统自检：异常";
                z3 = true;
            } else {
                this.arr2[4] = "系统自检：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
                this.arr2[5] = "胎压故障灯：开";
                z3 = true;
            } else {
                this.arr2[5] = "胎压故障灯：关";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
                this.arr2[6] = "电池：电量低";
                z3 = true;
            } else {
                this.arr2[6] = "电池：正常";
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
                this.arr2[7] = "胎温：高温";
                z3 = true;
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr2[7] = "胎温：超高温";
                z3 = true;
            }
            if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr2[7] = "胎温：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
                this.arr2[8] = "气压：低";
                z3 = true;
            }
            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr2[8] = "气压：高";
                z3 = true;
            }
            if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr2[8] = "气压：正常";
            }
            if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
                c3 = '\t';
                this.arr2[9] = "漏气：慢速";
                z3 = true;
            } else {
                c3 = '\t';
            }
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr2[c3] = "漏气：快速";
                z3 = true;
            }
            if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr2[9] = "漏气：无";
            }
            if (!z3) {
                this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
            }
        } else if (i == 3) {
            if (iArr[4] == 255) {
                this.arr3[0] = "温度：--" + getTempUnitC(this.mContext);
            } else {
                this.arr3[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
            }
            if (this.mCanBusInfoInt[3] == 255) {
                this.arr3[1] = "胎压：--KPa";
            } else {
                this.arr3[1] = "胎压：" + this.mCanBusInfoInt[3] + "KPa";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                this.arr3[2] = "系统：故障";
                z4 = true;
            } else {
                this.arr3[2] = "系统：正常";
                z4 = false;
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
                this.arr3[3] = "信号：丢失";
                z4 = true;
            } else {
                this.arr3[3] = "信号：正常";
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                this.arr3[4] = "系统自检：异常";
                z4 = true;
            } else {
                this.arr3[4] = "系统自检：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
                this.arr3[5] = "胎压故障灯：开";
                z4 = true;
            } else {
                this.arr3[5] = "胎压故障灯：关";
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
                this.arr3[6] = "电池：电量低";
                z4 = true;
            } else {
                this.arr3[6] = "电池：正常";
            }
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
                this.arr3[7] = "胎温：高温";
                z4 = true;
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr3[7] = "胎温：超高温";
                z4 = true;
            }
            if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
                this.arr3[7] = "胎温：正常";
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
                this.arr3[8] = "气压：低";
                z4 = true;
            }
            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr3[8] = "气压：高";
                z4 = true;
            }
            if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
                this.arr3[8] = "气压：正常";
            }
            if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
                c4 = '\t';
                this.arr3[9] = "漏气：慢速";
                z4 = true;
            } else {
                c4 = '\t';
            }
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr3[c4] = "漏气：快速";
                z4 = true;
            }
            if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
                this.arr3[9] = "漏气：无";
            }
            if (!z4) {
                this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
            }
        }
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x39TireAlertInfo() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            this.arr0[2] = "传感器：失效";
        } else {
            this.arr0[2] = "传感器：正常";
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            this.arr0[3] = "胎压预警：低压";
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            this.arr0[3] = "胎压预警：高压";
        }
        if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            this.arr0[3] = "胎压预警：正常";
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            this.arr1[2] = "传感器：失效";
        } else {
            this.arr1[2] = "传感器：正常";
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            this.arr1[3] = "胎压预警：低压";
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.arr1[3] = "胎压预警：高压";
        }
        if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.arr1[3] = "胎压预警：正常";
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            this.arr3[2] = "传感器：失效";
        } else {
            this.arr3[2] = "传感器：正常";
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            this.arr3[3] = "胎压预警：低压";
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            this.arr3[3] = "胎压预警：高压";
        }
        if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            this.arr3[3] = "胎压预警：正常";
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            this.arr2[2] = "传感器：失效";
        } else {
            this.arr2[2] = "传感器：正常";
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            this.arr2[3] = "胎压预警：低压";
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            this.arr2[3] = "胎压预警：高压";
        }
        if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            this.arr2[3] = "胎压预警：正常";
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            this.arr2[5] = "胎温预警：高温";
        } else {
            this.arr2[5] = "胎温预警：正常";
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            this.arr2[4] = "系统：异常";
            this.arr3[4] = "系统：异常";
        } else {
            this.arr2[4] = "系统：正常";
            this.arr3[4] = "系统：正常";
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) || DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]) || DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) || DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) || DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]) || DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) || DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) || DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        }
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x38TireInfo() {
        GeneralTireData.isHaveSpareTire = false;
        this.arr0[0] = "温度：" + (this.mCanBusInfoInt[2] - 40) + getTempUnitC(this.mContext);
        this.arr0[1] = "胎压：" + this.df.format(this.mCanBusInfoInt[6] * 1.373d) + "KPa";
        this.arr1[0] = "温度：" + (this.mCanBusInfoInt[3] - 40) + getTempUnitC(this.mContext);
        this.arr1[1] = "胎压：" + this.df.format(this.mCanBusInfoInt[7] * 1.373d) + "KPa";
        this.arr2[0] = "温度：" + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
        this.arr2[1] = "胎压：" + this.df.format(this.mCanBusInfoInt[8] * 1.373d) + "KPa";
        this.arr3[0] = "温度：" + (this.mCanBusInfoInt[5] - 40) + getTempUnitC(this.mContext);
        this.arr3[1] = "胎压：" + this.df.format(this.mCanBusInfoInt[9] * 1.373d) + "KPa";
        this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
        this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x24BaseInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_327_source_setting"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        this.mCanBusInfoInt[5] = 0;
        if (is0x24DataChange()) {
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1) == 1) {
                GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
                GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
                GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
                GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
                GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
                if (this.currentEachCanId != 1) {
                    GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
                }
            }
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralDoorData.isSubShowSeatBelt = true;
            GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            if (this.currentEachCanId == 5) {
                if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
                    if (((this.mCanBusInfoInt[3] >> 3) & 1) == 0) {
                        finishActivity();
                    }
                } else if (((this.mCanBusInfoInt[3] >> 3) & 1) == 1) {
                    Intent intent = new Intent();
                    intent.setComponent(Constant.AirActivity);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.mContext.startActivity(intent);
                }
            }
            updateDoorView(this.mContext);
        }
    }

    private void set0x28TrackData() {
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 7840, 2180, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x29TrackData() {
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 32768, 42496, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x27OutDoorTemperature(Context context) {
        updateOutDoorTemp(context, getOutdoorTemperature());
    }

    private void set0x26FrontRadarInfo() {
        if (is0x26DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(10, get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[2]), get0x25middleRadarGridNumber(this.mCanBusInfoInt[3]), get0x25middleRadarGridNumber(this.mCanBusInfoInt[4]), get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x23FrontRadarInfo() {
        if (is0x23DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(10, get0x23RadarValue(this.mCanBusInfoInt[2]), get0x23RadarValue(this.mCanBusInfoInt[3]), get0x23RadarValue(this.mCanBusInfoInt[4]), get0x23RadarValue(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x25RearRadarInfo() {
        if (is0x25DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[2]), get0x25middleRadarGridNumber(this.mCanBusInfoInt[3]), get0x25middleRadarGridNumber(this.mCanBusInfoInt[4]), get0x25BothSidesRadarGridNumber(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x22RearRadarInfo() {
        if (is0x22DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, get0x22RadarValue(this.mCanBusInfoInt[2]), get0x22RadarValue(this.mCanBusInfoInt[3]), get0x22RadarValue(this.mCanBusInfoInt[4]), get0x22RadarValue(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x21AirInfo(Context context) {
        int i;
        if (getCurrentCanDifferentId() != 3 || this.mAirRearMode == DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3)) {
            i = 0;
        } else {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3);
            this.mAirRearMode = intFromByteWithBit;
            i = 1002;
            if (intFromByteWithBit == 0) {
                GeneralAirData.rear_power = false;
                GeneralAirData.rear_auto = false;
            } else if (intFromByteWithBit == 1) {
                GeneralAirData.rear_power = true;
                GeneralAirData.rear_auto = true;
            } else if (intFromByteWithBit == 2) {
                GeneralAirData.rear_power = true;
                GeneralAirData.rear_auto = false;
            }
        }
        byte[] bArr = this.mCanBusInfoByte;
        bArr[6] = (byte) (bArr[6] & 248);
        if (isAirDataChange()) {
            i = 1001;
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0) {
                GeneralAirData.front_left_temperature = getTemperatureLevel(this.mCanBusInfoInt[4]) + "Level";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 1) {
                GeneralAirData.front_left_temperature = resolverAirTemperature(context, this.mCanBusInfoInt[4]);
            }
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 0) {
                GeneralAirData.front_right_temperature = getTemperatureLevel(this.mCanBusInfoInt[5]) + "Level";
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 1) {
                GeneralAirData.front_right_temperature = resolverAirTemperature(context, this.mCanBusInfoInt[5]);
            }
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
            GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
            GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
        }
        if (i != 0) {
            updateAirActivity(context, i);
        }
    }

    private void set0x20WheelKey(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        switch (i) {
            case 0:
                realKeyLongClick1(context, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(context, 7, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(context, 8, iArr[3]);
                break;
            case 3:
                buttonKey(45);
                break;
            case 4:
                buttonKey(46);
                break;
            case 5:
                realKeyClick2(context, 14, i, iArr[3]);
                break;
            case 6:
                realKeyClick2(context, 3, i, iArr[3]);
                break;
            case 7:
                realKeyClick2(context, 2, i, iArr[3]);
                break;
            case 8:
                realKeyClick2(context, 15, i, iArr[3]);
                break;
            case 9:
                realKeyClick2(context, HotKeyConstant.K_SLEEP, i, iArr[3]);
                break;
            default:
                switch (i) {
                    case 16:
                        realKeyClick1(context, 128, i, iArr[3]);
                        break;
                    case 17:
                        realKeyClick1(context, 52, i, iArr[3]);
                        break;
                    case 18:
                        realKeyClick1(context, 61, i, iArr[3]);
                        break;
                    case 19:
                        realKeyClick1(context, 58, i, iArr[3]);
                        break;
                    case 20:
                        realKeyClick1(context, 59, i, iArr[3]);
                        break;
                    case 21:
                        realKeyClick1(context, HotKeyConstant.K_SPEECH, i, iArr[3]);
                        break;
                    case 22:
                        realKeyClick1(context, HotKeyConstant.K_1_PICKUP, i, iArr[3]);
                        break;
                    case 23:
                        realKeyClick1(context, 50, i, iArr[3]);
                        break;
                }
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) (i - 2010), (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7});
        this.nowYear = i;
        this.nowMonth = i3;
        this.nowDay = i4;
        this.nowHours = i5;
        this.nowMins = i6;
        this.nowSecond = i7;
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        MyLog.e("ceshi", SourceConstantsDef.SOURCE_ID.FM.name());
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, allBandTypeData, this.freqLo, this.freqHi, (byte) i, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int i, int i2, String str) {
        super.btPhoneCallLogInfoChange(i, i2, str);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1});
        tokingNowTime(0);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 3, -1, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        tokingNowTime(i);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
        sendMusic(str4, str6);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        sendMusic("", "");
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, b4, b5});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 17;
            case "AM2":
                return (byte) 18;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.FreqInt = Integer.parseInt(str2);
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    private String resolverAirTemperature(Context context, int i) {
        return i == 128 ? "LO" : i == 159 ? "HI" : (128 >= i || i >= 159) ? "" : (((i + 36) * 0.5f) - 64.5d) + getTempUnitC(context);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.m0x21AirData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x21AirData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTrackDataChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private String getOutdoorTemperature() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        if (DataHandleUtils.getIntFromByteWithBit(i, 0, 1) == 0) {
            return ((i2 * 0.5d) - 35.0d) + getTempUnitC(this.mContext);
        }
        return DataHandleUtils.getIntFromByteWithBit(i, 0, 1) == 1 ? ((i2 * 0.5d) - 35.0d) + getTempUnitF(this.mContext) : "";
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicDataChange() {
        if (Arrays.equals(this.m0x42Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x42Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x26DataChange() {
        if (Arrays.equals(this.m0x26Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x26Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x25DataChange() {
        if (Arrays.equals(this.m0x25Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x25Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateAppointmentSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void countDownTimeUpdateSettings(int i, int i2, Long l) {
        ArrayList<SettingUpdateEntity> arrayList = new ArrayList<>();
        if (l == 1) {
            arrayList.add(new SettingUpdateEntity(i, i2 - 1, 0));
            arrayList.add(new SettingUpdateEntity(i, i2, "OFF"));
        } else {
            arrayList.add(new SettingUpdateEntity(i, i2, l + "秒"));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getmUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void sendMusic(String str, String str2) {
        byte[] bArr = {22, 113, 18};
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 18}, DataHandleUtils.exceptBOMHead(str.getBytes("utf-8"))));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr, DataHandleUtils.exceptBOMHead(str2.getBytes("utf-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void tokingNowTime(int i) {
        int i2 = i % 3600;
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) (i2 % 60), (byte) (i2 / 60), (byte) (i / 3600), 0});
    }

    private boolean is0x24DataChange() {
        if (Arrays.equals(this.m0x24Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x24Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
