package com.hzbhd.canbus.car._295;

import android.content.Context;
import android.content.res.Resources;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static int carSpeedWarning;
    static int frontWindLv;
    static float leftFrontTemp;
    static float rearTemp;
    static float rightFrontTemp;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean isFirst = true;
    private int[] arrReportsHw = {R.string.vm_golf7_vehicle_status_report_1, R.string.vm_golf7_vehicle_status_report_2, R.string.vm_golf7_vehicle_status_report_3, R.string.vm_golf7_vehicle_status_report_4, R.string.vm_golf7_vehicle_status_report_5, R.string.vm_golf7_vehicle_status_report_6, R.string.vm_golf7_vehicle_status_report_7, R.string.vm_golf7_vehicle_status_report_14, R.string.vm_golf7_vehicle_status_report_15, R.string.vm_golf7_vehicle_status_report_16, R.string.vm_golf7_vehicle_status_report_17, R.string.vm_golf7_vehicle_status_report_18, R.string.vm_golf7_vehicle_status_report_19, R.string.vm_golf7_vehicle_status_report_20, R.string.vm_golf7_vehicle_status_report_21, R.string.vm_golf7_vehicle_status_report_22, R.string.vm_golf7_vehicle_status_report_23, R.string.vm_golf7_vehicle_status_report_64, R.string.vm_golf7_vehicle_status_report_65, R.string.vm_golf7_vehicle_status_report_66, R.string.vm_golf7_vehicle_status_report_67, R.string.vm_golf7_vehicle_status_report_68, R.string.vm_golf7_vehicle_status_report_69, R.string.vm_golf7_vehicle_status_report_70, R.string.vm_golf7_vehicle_status_report_71, R.string.vm_golf7_vehicle_status_report_72, R.string.vm_golf7_vehicle_status_report_73, R.string.vm_golf7_vehicle_status_report_74, R.string.vm_golf7_vehicle_status_report_75, R.string.vm_golf7_vehicle_status_report_76, R.string.vm_golf7_vehicle_status_report_77, R.string.vm_golf7_vehicle_status_report_78, R.string.vm_golf7_vehicle_status_report_79, R.string.vm_golf7_vehicle_status_report_80, R.string.vm_golf7_vehicle_status_report_81, R.string.vm_golf7_vehicle_status_report_82, R.string.vm_golf7_vehicle_status_report_83, R.string.vm_golf7_vehicle_status_report_84, R.string.vm_golf7_vehicle_status_report_85, R.string.vm_golf7_vehicle_status_report_86, R.string.vm_golf7_vehicle_status_report_87, R.string.vm_golf7_vehicle_status_report_88, R.string.vm_golf7_vehicle_status_report_89, R.string.vm_golf7_vehicle_status_report_90, R.string.vm_golf7_vehicle_status_report_91, R.string.vm_golf7_vehicle_status_report_92, R.string.vm_golf7_vehicle_status_report_93, R.string.vm_golf7_vehicle_status_report_94, R.string.vm_golf7_vehicle_status_report_95, R.string.vm_golf7_vehicle_status_report_96, R.string.vm_golf7_vehicle_status_report_97, R.string.vm_golf7_vehicle_status_report_98, R.string.vm_golf7_vehicle_status_report_99, R.string.vm_golf7_vehicle_status_report_100, R.string.vm_golf7_vehicle_status_report_101, R.string.vm_golf7_vehicle_status_report_102, R.string.vm_golf7_vehicle_status_report_103, R.string.vm_golf7_vehicle_status_report_104, R.string.vm_golf7_vehicle_status_report_105, R.string.vm_golf7_vehicle_status_report_106, R.string.vm_golf7_vehicle_status_report_107, R.string.vm_golf7_vehicle_status_report_108, R.string.vm_golf7_vehicle_status_report_109, R.string.vm_golf7_vehicle_status_report_110, R.string.vm_golf7_vehicle_status_report_111, R.string.vm_golf7_vehicle_status_report_112, R.string.vm_golf7_vehicle_status_report_113, R.string.vm_golf7_vehicle_status_report_114, R.string.vm_golf7_vehicle_status_report_115, R.string.vm_golf7_vehicle_status_report_116, R.string.vm_golf7_vehicle_status_report_117, R.string.vm_golf7_vehicle_status_report_118, R.string.vm_golf7_vehicle_status_report_119, R.string.vm_golf7_vehicle_status_report_120, R.string.vm_golf7_vehicle_status_report_121, R.string.vm_golf7_vehicle_status_report_122, R.string.vm_golf7_vehicle_status_report_123, R.string.vm_golf7_vehicle_status_report_124, R.string.vm_golf7_vehicle_status_report_125, R.string.vm_golf7_vehicle_status_report_126, R.string.vm_golf7_vehicle_status_report_127, R.string.vm_golf7_vehicle_status_report_128, R.string.vm_golf7_vehicle_status_report_155, R.string.vm_golf7_vehicle_status_report_156, R.string.vm_golf7_vehicle_status_report_157, R.string.vm_golf7_vehicle_status_report_158, R.string.vm_golf7_vehicle_status_report_159, R.string.vm_golf7_vehicle_status_report_160, R.string.vm_golf7_vehicle_status_report_161, R.string.vm_golf7_vehicle_status_report_162, R.string.vm_golf7_vehicle_status_report_163, R.string.vm_golf7_vehicle_status_report_164, R.string.vm_golf7_vehicle_status_report_165, R.string.vm_golf7_vehicle_status_report_166, R.string.vm_golf7_vehicle_status_report_167, R.string.vm_golf7_vehicle_status_report_168, R.string.vm_golf7_vehicle_status_report_169, R.string.vm_golf7_vehicle_status_report_170, R.string.vm_golf7_vehicle_status_report_171, R.string.vm_golf7_vehicle_status_report_172, R.string.vm_golf7_vehicle_status_report_173, R.string.vm_golf7_vehicle_status_report_174, R.string.vm_golf7_vehicle_status_report_175, R.string.vm_golf7_vehicle_status_report_176, R.string.vm_golf7_vehicle_status_report_177, R.string.vm_golf7_vehicle_status_report_24, R.string.vm_golf7_vehicle_status_report_25, R.string.vm_golf7_vehicle_status_report_26, R.string.vm_golf7_vehicle_status_report_27, R.string.vm_golf7_vehicle_status_report_28, R.string.vm_golf7_vehicle_status_report_29, R.string.vm_golf7_vehicle_status_report_30, R.string.vm_golf7_vehicle_status_report_31, R.string.vm_golf7_vehicle_status_report_32, R.string.vm_golf7_vehicle_status_report_33, R.string.vm_golf7_vehicle_status_report_34, R.string.vm_golf7_vehicle_status_report_35, R.string.vm_golf7_vehicle_status_report_36, R.string.vm_golf7_vehicle_status_report_37, R.string.vm_golf7_vehicle_status_report_38, R.string.vm_golf7_vehicle_status_report_39, R.string.vm_golf7_vehicle_status_report_40, R.string.vm_golf7_vehicle_status_report_41, R.string.vm_golf7_vehicle_status_report_42, R.string.vm_golf7_vehicle_status_report_43, R.string.vm_golf7_vehicle_status_report_44, R.string.vm_golf7_vehicle_status_report_45, R.string.vm_golf7_vehicle_status_report_46, R.string.vm_golf7_vehicle_status_report_47, R.string.vm_golf7_vehicle_status_report_48, R.string.vm_golf7_vehicle_status_report_49, R.string.vm_golf7_vehicle_status_report_50, R.string.vm_golf7_vehicle_status_report_51, R.string.vm_golf7_vehicle_status_report_52, R.string.vm_golf7_vehicle_status_report_53, R.string.vm_golf7_vehicle_status_report_54, R.string.vm_golf7_vehicle_status_report_55, R.string.vm_golf7_vehicle_status_report_56, R.string.vm_golf7_vehicle_status_report_57, R.string.vm_golf7_vehicle_status_report_58, R.string.vm_golf7_vehicle_status_report_59, R.string.vm_golf7_vehicle_status_report_60, R.string.vm_golf7_vehicle_status_report_61, R.string.vm_golf7_vehicle_status_report_62, R.string.vm_golf7_vehicle_status_report_63, R.string.vm_golf7_vehicle_status_report_129, R.string.vm_golf7_vehicle_status_report_130, R.string.vm_golf7_vehicle_status_report_131, R.string.vm_golf7_vehicle_status_report_132, R.string.vm_golf7_vehicle_status_report_133, R.string.vm_golf7_vehicle_status_report_134, R.string.vm_golf7_vehicle_status_report_135, R.string.vm_golf7_vehicle_status_report_136, R.string.vm_golf7_vehicle_status_report_137, R.string.vm_golf7_vehicle_status_report_138, R.string.vm_golf7_vehicle_status_report_139, R.string.vm_golf7_vehicle_status_report_140, R.string.vm_golf7_vehicle_status_report_141, R.string.vm_golf7_vehicle_status_report_142, R.string.vm_golf7_vehicle_status_report_143, R.string.vm_golf7_vehicle_status_report_144, R.string.vm_golf7_vehicle_status_report_145, R.string.vm_golf7_vehicle_status_report_146, R.string.vm_golf7_vehicle_status_report_147, R.string.vm_golf7_vehicle_status_report_148, R.string.vm_golf7_vehicle_status_report_149, R.string.vm_golf7_vehicle_status_report_178, R.string.vm_golf7_vehicle_status_report_179, R.string.vm_golf7_vehicle_status_report_180, R.string.vm_golf7_vehicle_status_report_181, R.string.vm_golf7_vehicle_status_report_182, R.string.vm_golf7_vehicle_status_report_183, R.string.vm_golf7_vehicle_status_report_184, R.string.vm_golf7_vehicle_status_report_185, R.string.vm_golf7_vehicle_status_report_186, R.string.vm_golf7_vehicle_status_report_187, R.string.vm_golf7_vehicle_status_report_188, R.string.vm_golf7_vehicle_status_report_189, R.string.vm_golf7_vehicle_status_report_190, R.string.vm_golf7_vehicle_status_report_191, R.string.vm_golf7_vehicle_status_report_192, R.string.vm_golf7_vehicle_status_report_193, R.string.vm_golf7_vehicle_status_report_194, R.string.vm_golf7_vehicle_status_report_195, R.string.vm_golf7_vehicle_status_report_196, R.string.vm_golf7_vehicle_status_report_197, R.string.vm_golf7_vehicle_status_report_198, R.string.vm_golf7_vehicle_status_report_199, R.string.vm_golf7_vehicle_status_report_200, R.string.vm_golf7_vehicle_status_report_201, R.string.vm_golf7_vehicle_status_report_202, R.string.vm_golf7_vehicle_status_report_203, R.string.vm_golf7_vehicle_status_report_204, R.string.vm_golf7_vehicle_status_report_205, R.string.vm_golf7_vehicle_status_report_206, R.string.vm_golf7_vehicle_status_report_207, R.string.vm_golf7_vehicle_status_report_208, R.string.vm_golf7_vehicle_status_report_209, R.string.vm_golf7_vehicle_status_report_210, R.string.vm_golf7_vehicle_status_report_211, R.string.vm_golf7_vehicle_status_report_212, R.string.vm_golf7_vehicle_status_report_213, R.string.vm_golf7_vehicle_status_report_214};
    private int[] arrStartStopHw = {R.string.vm_golf7_vehicle_status_start_stop_prompt_1, R.string.vm_golf7_vehicle_status_start_stop_prompt_2, R.string.vm_golf7_vehicle_status_start_stop_prompt_3, R.string.vm_golf7_vehicle_status_start_stop_prompt_4, R.string.vm_golf7_vehicle_status_start_stop_prompt_5, R.string.vm_golf7_vehicle_status_start_stop_prompt_6, R.string.vm_golf7_vehicle_status_start_stop_prompt_7, R.string.vm_golf7_vehicle_status_start_stop_prompt_8, R.string.vm_golf7_vehicle_status_start_stop_prompt_9, R.string.vm_golf7_vehicle_status_start_stop_prompt_10, R.string.vm_golf7_vehicle_status_start_stop_prompt_11, R.string.vm_golf7_vehicle_status_start_stop_prompt_12, R.string.vm_golf7_vehicle_status_start_stop_prompt_33, R.string.vm_golf7_vehicle_status_start_stop_prompt_13, R.string.vm_golf7_vehicle_status_start_stop_prompt_3, R.string.vm_golf7_vehicle_status_start_stop_prompt_17, R.string.vm_golf7_vehicle_status_start_stop_prompt_18, R.string.vm_golf7_vehicle_status_start_stop_prompt_34, R.string.vm_golf7_vehicle_status_start_stop_prompt_35, R.string.vm_golf7_vehicle_status_start_stop_prompt_36, R.string.vm_golf7_vehicle_status_start_stop_prompt_14, R.string.vm_golf7_vehicle_status_start_stop_prompt_15, R.string.vm_golf7_vehicle_status_start_stop_prompt_16, R.string.vm_golf7_vehicle_status_start_stop_prompt_17, R.string.vm_golf7_vehicle_status_start_stop_prompt_18, R.string.vm_golf7_vehicle_status_start_stop_prompt_19, R.string.vm_golf7_vehicle_status_start_stop_prompt_20, R.string.vm_golf7_vehicle_status_start_stop_prompt_21, R.string.vm_golf7_vehicle_status_start_stop_prompt_22, R.string.vm_golf7_vehicle_status_start_stop_prompt_23, R.string.vm_golf7_vehicle_status_start_stop_prompt_24, R.string.vm_golf7_vehicle_status_start_stop_prompt_25, R.string.vm_golf7_vehicle_status_start_stop_prompt_26, R.string.vm_golf7_vehicle_status_start_stop_prompt_27, R.string.vm_golf7_vehicle_status_start_stop_prompt_28, R.string.vm_golf7_vehicle_status_start_stop_prompt_29, R.string.vm_golf7_vehicle_status_start_stop_prompt_30, R.string.vm_golf7_vehicle_status_start_stop_prompt_31, R.string.vm_golf7_vehicle_status_start_stop_prompt_32, R.string.vm_golf7_vehicle_status_start_stop_prompt_17, R.string.vm_golf7_vehicle_status_start_stop_prompt_38, R.string.vm_golf7_vehicle_status_start_stop_prompt_39, R.string.vm_golf7_vehicle_status_start_stop_0, R.string.vm_golf7_vehicle_status_start_stop_1, R.string.vm_golf7_vehicle_status_start_stop_2, R.string.vm_golf7_vehicle_status_start_stop_8, R.string.vm_golf7_vehicle_status_start_stop_3, R.string.vm_golf7_vehicle_status_start_stop_4, R.string.vm_golf7_vehicle_status_start_stop_5, R.string.vm_golf7_vehicle_status_start_stop_6, R.string.vm_golf7_vehicle_status_start_stop_7};
    private int[] arrConvConsumers = {R.string.vm_golf7_Conv_consumers_prompt_1, R.string.vm_golf7_Conv_consumers_prompt_2, R.string.vm_golf7_Conv_consumers_prompt_3, R.string.vm_golf7_Conv_consumers_prompt_4, R.string.vm_golf7_Conv_consumers_prompt_5, R.string.vm_golf7_Conv_consumers_prompt_6, R.string.vm_golf7_Conv_consumers_prompt_7, R.string.vm_golf7_Conv_consumers_prompt_8, R.string.vm_golf7_Conv_consumers_prompt_9, R.string.vm_golf7_Conv_consumers_prompt_10, R.string.vm_golf7_Conv_consumers_prompt_11, R.string.vm_golf7_Conv_consumers_prompt_12, R.string.vm_golf7_Conv_consumers_prompt_13, R.string.vm_golf7_Conv_consumers_prompt_14, R.string.vm_golf7_Conv_consumers_prompt_15, R.string.vm_golf7_Conv_consumers_prompt_16, R.string.vm_golf7_Conv_consumers_prompt_17, R.string.vm_golf7_Conv_consumers_prompt_18};
    private List<WarningEntity> mList1 = new ArrayList();
    private List<WarningEntity> mList2 = new ArrayList();
    private List<WarningEntity> mList3 = new ArrayList();

    private int distanceToLocation0(int i) {
        if (i >= 0 && i <= 5) {
            return 1;
        }
        if (i >= 6 && i <= 10) {
            return 2;
        }
        if (i >= 11 && i <= 15) {
            return 3;
        }
        if (i >= 16 && i <= 20) {
            return 4;
        }
        if (i >= 21 && i <= 25) {
            return 5;
        }
        if (i >= 26 && i <= 30) {
            return 6;
        }
        if (i >= 31 && i <= 35) {
            return 7;
        }
        if (i >= 36 && i <= 40) {
            return 8;
        }
        if (i >= 41 && i <= 45) {
            return 9;
        }
        if (i >= 46 && i <= 50) {
            return 10;
        }
        if (i < 51 || i > 55) {
            return i >= 60 ? 12 : 0;
        }
        return 11;
    }

    private int distanceToLocation1(int i) {
        if (i >= 0 && i <= 15) {
            return 1;
        }
        if (i >= 16 && i <= 30) {
            return 2;
        }
        if (i >= 31 && i <= 45) {
            return 3;
        }
        if (i >= 46 && i <= 60) {
            return 4;
        }
        if (i >= 61 && i <= 75) {
            return 5;
        }
        if (i >= 76 && i <= 90) {
            return 6;
        }
        if (i >= 91 && i <= 105) {
            return 7;
        }
        if (i >= 106 && i <= 120) {
            return 8;
        }
        if (i >= 121 && i <= 135) {
            return 9;
        }
        if (i >= 136 && i <= 150) {
            return 10;
        }
        if (i < 151 || i > 165) {
            return i >= 166 ? 12 : 0;
        }
        return 11;
    }

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIndexBy3Bit(int i) {
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

    private void setAllow0x7f() {
    }

    private void setCarReports() {
    }

    private void setCarService() {
    }

    private void setCarStatus0x40() {
    }

    private void setCarTrack0x64() {
    }

    private void setConvenienceConsumers0x62() {
    }

    private void setDrivingData0x50() {
    }

    private void setRemind0x60() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        GeneralTireData.isHaveSpareTire = false;
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setDrive0x14();
            return;
        }
        if (i == 22) {
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(byteArrayToIntArray[3], byteArrayToIntArray[2]));
            return;
        }
        if (i == 39) {
            setEnvironmentTemp0x27();
            return;
        }
        if (i == 41) {
            setEsp0x29();
            return;
        }
        if (i == 64) {
            setCarStatus0x40();
            return;
        }
        if (i == 80) {
            setDrivingData0x50();
            return;
        }
        if (i == 127) {
            setAllow0x7f();
            return;
        }
        if (i == 47) {
            setSwc0x2f();
            return;
        }
        if (i != 48) {
            switch (i) {
                case 32:
                    setSwc0x20();
                    break;
                case 33:
                    setAir0x21();
                    break;
                case 34:
                    setRearRadar();
                    break;
                case 35:
                    setFrontRadar();
                    break;
                case 36:
                    setCarStatus0x24();
                    break;
                case 37:
                    setParkingAssistStatus0x25();
                    break;
                default:
                    switch (i) {
                        case 96:
                            setRemind0x60();
                            break;
                        case 97:
                            setCarReports();
                            break;
                        case 98:
                            setConvenienceConsumers0x62();
                            break;
                        case 99:
                            setCarService();
                            break;
                        case 100:
                            setCarTrack0x64();
                            break;
                    }
            }
            return;
        }
        setVersionInfo();
    }

    private void setDrive0x14() throws Resources.NotFoundException {
        String string;
        String string2;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_1);
        } else if (i == 255) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_2);
        } else {
            string = this.mCanBusInfoInt[2] + "";
        }
        int i2 = this.mCanBusInfoInt[3];
        if (i2 == 0) {
            string2 = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_1);
        } else if (i2 == 255) {
            string2 = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_2);
        } else {
            string2 = this.mCanBusInfoInt[3] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        arrayList.add(new DriverUpdateEntity(0, 1, string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSwc0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
            return;
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
        } else if (i == 4) {
            realKeyClick(21);
        } else {
            if (i != 6) {
                return;
            }
            realKeyClick(3);
        }
    }

    private void setSwc0x2f() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            realKeyClick2(45);
            return;
        }
        if (i == 2) {
            realKeyClick2(46);
        } else if (i == 17) {
            realKeyClick2(14);
        } else {
            if (i != 18) {
                return;
            }
            realKeyClick2(14);
        }
    }

    private void setAir0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = !DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
        boolean zIsOnlyRearAirDataChange = isOnlyRearAirDataChange();
        boolean zIsOnlyData6Change = isOnlyData6Change();
        boolean zIsOnlyData9Change = isOnlyData9Change();
        if (!zIsOnlyRearAirDataChange && !zIsOnlyData6Change && !zIsOnlyData9Change) {
            updateAirActivity(this.mContext, 1001);
        } else if (zIsOnlyRearAirDataChange && !isOnlyData6Change() && !zIsOnlyData9Change) {
            SharePreUtil.setIntValue(this.mContext, "_295_0x21_data7", this.mCanBusInfoInt[9]);
            GeneralAirData.rear_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
            updateAirActivity(this.mContext, 1002);
        }
        if (zIsOnlyData6Change) {
            SharePreUtil.setIntValue(this.mContext, "_295_0x21_data6", this.mCanBusInfoInt[8]);
            startSettingActivity(this.mContext, 0, 0);
        }
        if (zIsOnlyData9Change) {
            SharePreUtil.setIntValue(this.mContext, "_295_0x21_data9", this.mCanBusInfoInt[11]);
            updateGeneralSettingData(new ArrayList());
            updateSettingActivity(null);
        }
    }

    private void setRearRadar() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationDataType2(60, iArr[2], 165, iArr[3], 165, iArr[4], 60, iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 12;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationDataType2(11, iArr[2], 11, distanceToLocation1(iArr[3]), 11, distanceToLocation1(this.mCanBusInfoInt[4]), 60, this.mCanBusInfoInt[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setCarStatus0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 1, getResString(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? R.string.gear_not_p : R.string.gear_p)));
        arrayList.add(new DriverUpdateEntity(0, 2, getResString(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) ? R.string.open : R.string.close)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setParkingAssistStatus0x25() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 3, getResString(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? R.string.open : R.string.close)));
        arrayList.add(new DriverUpdateEntity(0, 4, getResString(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ? R.string.voice_on : R.string.voice_off)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setEnvironmentTemp0x27() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setEsp0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 19918, 16);
        updateParkUi(null, this.mContext);
    }

    private String resolveOutDoorTem() {
        byte[] bArr = this.mCanBusInfoByte;
        int i = bArr[3] + (bArr[4] * 256);
        if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            return (i / 10) + getTempUnitC(this.mContext);
        }
        return (i / 10) + getTempUnitF(this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private static <T> List<T> mergeLists(List<T>... listArr) {
        List<T> list;
        try {
            list = (List) listArr[0].getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        for (List<T> list2 : listArr) {
            list.addAll(list2);
        }
        return list;
    }

    private void setCarWarningInfo() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i > 16) {
            i = 16;
        }
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 + 3;
            if (iArr[i3] >= this.arrReportsHw.length) {
                strArr[i2] = " NULL ";
            } else {
                strArr[i2] = this.mContext.getResources().getString(this.arrReportsHw[iArr[i3]]);
                arrayList.add(new WarningEntity(strArr[i2]));
            }
        }
        this.mList1.clear();
        this.mList1.addAll(arrayList);
        GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
        updateWarningActivity(null);
    }

    private void setCarWarningInfo0x75() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i > 7) {
            i = 7;
        }
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 + 3;
            if (iArr[i3] >= this.arrStartStopHw.length) {
                strArr[i2] = " NULL ";
            } else {
                strArr[i2] = this.mContext.getResources().getString(this.arrStartStopHw[iArr[i3]]);
                arrayList.add(new WarningEntity(strArr[i2]));
            }
        }
        this.mList2.clear();
        this.mList2.addAll(arrayList);
        GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
        updateWarningActivity(null);
    }

    private void setCarWarningInfo0x77() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i > 7) {
            i = 7;
        }
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2 + 2] >= this.arrConvConsumers.length) {
                strArr[i2] = " NULL ";
            } else {
                strArr[i2] = this.mContext.getResources().getString(this.arrConvConsumers[iArr[i2 + 3]]);
                arrayList.add(new WarningEntity(strArr[i2]));
            }
        }
        this.mList3.clear();
        this.mList3.addAll(arrayList);
        GeneralWarningDataData.dataList = mergeLists(this.mList1, this.mList2, this.mList3);
        updateWarningActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendSourceMsg1("OFF");
        sendSourceMsg2(" ", 146);
        sendSourceMsg2(" ", 147);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte[] bArrCompensateZero = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, (str2 + " " + getBandUnit(str)).getBytes()), 28);
        byte[] bArrCompensateZero2 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, str.getBytes()), 27);
        CanbusMsgSender.sendMsg(bArrCompensateZero);
        CanbusMsgSender.sendMsg(bArrCompensateZero2);
        sendSourceMsg2(" ", 147);
    }

    private String getBandUnit(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return "KHz";
            case "FM1":
            case "FM2":
            case "FM3":
                return "MHz";
            default:
                return "";
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -111}, ((i6 == 1 ? "  DVD  " : "  CD  ") + Util.numCompensateZero((byte) (i / 3600)) + ":" + Util.numCompensateZero((byte) ((i / 60) % 60)) + ":" + Util.numCompensateZero((byte) (i % 60))).getBytes()), 28));
        if (this.isFirst) {
            this.isFirst = false;
            CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(new byte[]{22, -110}, 27));
            CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(new byte[]{22, -109}, 27));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendSourceMsg1("AUX");
        sendSourceMsg2(" ", 146);
        sendSourceMsg2(" ", 147);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendSourceMsg1("DTV");
        sendSourceMsg2(" ", 146);
        sendSourceMsg2(" ", 147);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendSourceMsg1("BT MUSIC");
        sendSourceMsg2(" ", 146);
        sendSourceMsg2(" ", 147);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte[] bArr = {22, -111, 0, 0};
        byte[] bArrExceptBOMHead = new byte[0];
        try {
            bArrExceptBOMHead = DataHandleUtils.exceptBOMHead(str4.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr, bArrExceptBOMHead), 28));
        byte[] bArr2 = {22, -110, 0};
        byte[] bArrExceptBOMHead2 = new byte[0];
        try {
            bArrExceptBOMHead2 = DataHandleUtils.exceptBOMHead(str6.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr2, bArrExceptBOMHead2), 27));
        byte[] bArr3 = {22, -109, 0};
        byte[] bArrExceptBOMHead3 = new byte[0];
        try {
            bArrExceptBOMHead3 = DataHandleUtils.exceptBOMHead(str5.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr3, bArrExceptBOMHead3), 27));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, (((int) b4) + ":" + ((int) b5) + "   ").getBytes()), 28));
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -110, 0}, ((((b6 & 255) * 256) + (i & 255)) + "/" + (i2 & 255)).getBytes()), 27));
        sendSourceMsg2(" ", 147);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) (!z3 ? 1 : 0), (byte) i5, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, (byte) i2, (byte) i3, (byte) i4, (byte) i9});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -107}, 27));
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -106}, 27));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) throws UnsupportedEncodingException {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        byte[] bArr2 = {22, -107, 0};
        byte[] bytes = new byte[0];
        try {
            bytes = new String(bArr).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(bArr2, bytes), 27));
        byte[] bArr3 = {22, -106, 0};
        byte[] bytes2 = new byte[0];
        try {
            bytes2 = ((i / 60) + ":" + (i % 60)).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(bArr3, bytes2), 27));
    }

    private String resolveLeftAndRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (31 == i) {
            return "HI";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return ((i * 0.5f) + 15.5d) + getTempUnitC(this.mContext);
        }
        return ((int) ((((i * 0.5f) + 15.5d) * 1.8d) + 32.0d)) + getTempUnitC(this.mContext);
    }

    private boolean isOnlyRearAirDataChange() {
        return SharePreUtil.getIntValue(this.mContext, "_295_0x21_data7", 0) != this.mCanBusInfoInt[9];
    }

    private boolean isOnlyData6Change() {
        return SharePreUtil.getIntValue(this.mContext, "_295_0x21_data6", 0) != this.mCanBusInfoInt[8];
    }

    private boolean isOnlyData9Change() {
        return SharePreUtil.getIntValue(this.mContext, "_295_0x21_data9", 0) != this.mCanBusInfoInt[11];
    }

    private String getTirePressure(int i, int i2) {
        StringBuilder sbAppend = new StringBuilder().append((float) (((i * 256) + i2) * 0.1d));
        String str = "";
        String string = sbAppend.append("").toString();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
        if (intFromByteWithBit == 0) {
            str = "kPa";
        } else if (intFromByteWithBit == 1) {
            str = "bar";
        } else if (intFromByteWithBit == 2) {
            str = "psi";
        }
        return string + str;
    }

    private TireUpdateEntity getTireEntity(int i, String str, String str2, String str3) {
        return new TireUpdateEntity(i, !str.equals(this.mContext.getResources().getString(R.string.air_set_3_2)) ? 1 : 0, new String[]{str, str2, str3});
    }

    private String getTisWarmMsg(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.air_set_3_2);
        }
        return this.mContext.getResources().getString(R.string.alarm);
    }

    void updateLanguageSet(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateAirSet(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void sendSourceMsg1(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, str.getBytes()), 28));
    }

    private void sendSourceMsg2(String str, int i) {
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, (byte) i, 0}, str.getBytes()), 27));
    }

    private void realKeyClick(int i) {
        realKeyClick2(this.mContext, i, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
    }

    private void realKeyClick2(int i) {
        realKeyClick4(this.mContext, i);
    }

    private String getResString(int i) {
        return this.mContext.getResources().getString(i);
    }
}
