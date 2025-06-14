package com.hzbhd.canbus.car._206;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;
import androidx.core.view.InputDeviceCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Config;
import com.hzbhd.canbus.control.CanbusControlAction;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SetTimeView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.VoiceControlData;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final String CAROUTTEMCHANGEACTION2 = "com.android.systemui.statusbar.action.CAROUTTEMCHANGE2";
    static int carSpeedWarning = 0;
    static int frontWindLv = 0;
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    static boolean isPanoramicRadarClose = false;
    static float leftFrontTemp = 0.0f;
    private static int outDoorTemp = 0;
    static float rearTemp = 0.0f;
    private static int rearWindBlow = 0;
    static float rightFrontTemp = 0.0f;
    private static boolean settingFirstMsg = true;
    int[] carStateIntArray;
    private CountDownTimer countDownTimer;
    int[] mAirData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusSwc0x0cInfoCopy;
    private byte[] mCanBusSwc0x0dInfoCopy;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private boolean mCurrentCaseC;
    private boolean mCurrentCaseD;
    private UiMgr mUiMgr;
    byte[] nowWayNameByte;
    byte[] otherInfoByte;
    int result;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_FRONT_OPEN = "is_front_open";
    private final String IS_BATTERY_WARNING = "is_battery_warning";
    private final String IS_FUEL_WARNING = "is_fuel_warning";
    private final String IS_SEATBELT_TIE = "is_seatbelt_tie";
    private final String IS_WASHING_FLUID_WARNING = "is_washing_fluid_warning";
    private final String IS_REAR_AIR_TEMP_CHANGE_MQB = "is_rear_air_temp_change_mqb";
    private final String IS_REAR_AIR_WIND_LV_CHANGE = "is_rear_air_wind_lv_change";
    private final String IS_REAR_AIR_WIND_BLOW = "is_rear_air_wind_blow";
    private final String IS_REAR_AIR_POWER = "is_rear_air_power";
    private final String IS_REAR_AUTO = "is_rear_auto";
    private final String IS_REAR_LEFT_HEAT = "is_rear_left_heat";
    private final String IS_REAR_RIGHT_HEAT = "is_rear_right_heat";
    private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
    private boolean isFirst = true;
    DecimalFormat mDecimalFormat = new DecimalFormat("#####00");
    private boolean lastAccState = false;
    private boolean isLockAirViewToShow = false;
    boolean isGpsTime = true;
    int bHours = 0;
    int bMins = 0;
    boolean isFormat24H = true;
    int bYear2Dig = 0;
    int bMonth = 0;
    int bDay = 0;
    int systemDateFormat = 0;
    private int[] arrReportsHw = {R.string.vm_golf7_vehicle_status_report_1, R.string.vm_golf7_vehicle_status_report_2, R.string.vm_golf7_vehicle_status_report_3, R.string.vm_golf7_vehicle_status_report_4, R.string.vm_golf7_vehicle_status_report_5, R.string.vm_golf7_vehicle_status_report_6, R.string.vm_golf7_vehicle_status_report_7, R.string.vm_golf7_vehicle_status_report_14, R.string.vm_golf7_vehicle_status_report_15, R.string.vm_golf7_vehicle_status_report_16, R.string.vm_golf7_vehicle_status_report_17, R.string.vm_golf7_vehicle_status_report_18, R.string.vm_golf7_vehicle_status_report_19, R.string.vm_golf7_vehicle_status_report_20, R.string.vm_golf7_vehicle_status_report_21, R.string.vm_golf7_vehicle_status_report_22, R.string.vm_golf7_vehicle_status_report_23, R.string.vm_golf7_vehicle_status_report_64, R.string.vm_golf7_vehicle_status_report_65, R.string.vm_golf7_vehicle_status_report_66, R.string.vm_golf7_vehicle_status_report_67, R.string.vm_golf7_vehicle_status_report_68, R.string.vm_golf7_vehicle_status_report_69, R.string.vm_golf7_vehicle_status_report_70, R.string.vm_golf7_vehicle_status_report_71, R.string.vm_golf7_vehicle_status_report_72, R.string.vm_golf7_vehicle_status_report_73, R.string.vm_golf7_vehicle_status_report_74, R.string.vm_golf7_vehicle_status_report_75, R.string.vm_golf7_vehicle_status_report_76, R.string.vm_golf7_vehicle_status_report_77, R.string.vm_golf7_vehicle_status_report_78, R.string.vm_golf7_vehicle_status_report_79, R.string.vm_golf7_vehicle_status_report_80, R.string.vm_golf7_vehicle_status_report_81, R.string.vm_golf7_vehicle_status_report_82, R.string.vm_golf7_vehicle_status_report_83, R.string.vm_golf7_vehicle_status_report_84, R.string.vm_golf7_vehicle_status_report_85, R.string.vm_golf7_vehicle_status_report_86, R.string.vm_golf7_vehicle_status_report_87, R.string.vm_golf7_vehicle_status_report_88, R.string.vm_golf7_vehicle_status_report_89, R.string.vm_golf7_vehicle_status_report_90, R.string.vm_golf7_vehicle_status_report_91, R.string.vm_golf7_vehicle_status_report_92, R.string.vm_golf7_vehicle_status_report_93, R.string.vm_golf7_vehicle_status_report_94, R.string.vm_golf7_vehicle_status_report_95, R.string.vm_golf7_vehicle_status_report_96, R.string.vm_golf7_vehicle_status_report_97, R.string.vm_golf7_vehicle_status_report_98, R.string.vm_golf7_vehicle_status_report_99, R.string.vm_golf7_vehicle_status_report_100, R.string.vm_golf7_vehicle_status_report_101, R.string.vm_golf7_vehicle_status_report_102, R.string.vm_golf7_vehicle_status_report_103, R.string.vm_golf7_vehicle_status_report_104, R.string.vm_golf7_vehicle_status_report_105, R.string.vm_golf7_vehicle_status_report_106, R.string.vm_golf7_vehicle_status_report_107, R.string.vm_golf7_vehicle_status_report_108, R.string.vm_golf7_vehicle_status_report_109, R.string.vm_golf7_vehicle_status_report_110, R.string.vm_golf7_vehicle_status_report_111, R.string.vm_golf7_vehicle_status_report_112, R.string.vm_golf7_vehicle_status_report_113, R.string.vm_golf7_vehicle_status_report_114, R.string.vm_golf7_vehicle_status_report_115, R.string.vm_golf7_vehicle_status_report_116, R.string.vm_golf7_vehicle_status_report_117, R.string.vm_golf7_vehicle_status_report_118, R.string.vm_golf7_vehicle_status_report_119, R.string.vm_golf7_vehicle_status_report_120, R.string.vm_golf7_vehicle_status_report_121, R.string.vm_golf7_vehicle_status_report_122, R.string.vm_golf7_vehicle_status_report_123, R.string.vm_golf7_vehicle_status_report_124, R.string.vm_golf7_vehicle_status_report_125, R.string.vm_golf7_vehicle_status_report_126, R.string.vm_golf7_vehicle_status_report_127, R.string.vm_golf7_vehicle_status_report_128, R.string.vm_golf7_vehicle_status_report_155, R.string.vm_golf7_vehicle_status_report_156, R.string.vm_golf7_vehicle_status_report_157, R.string.vm_golf7_vehicle_status_report_158, R.string.vm_golf7_vehicle_status_report_159, R.string.vm_golf7_vehicle_status_report_160, R.string.vm_golf7_vehicle_status_report_161, R.string.vm_golf7_vehicle_status_report_162, R.string.vm_golf7_vehicle_status_report_163, R.string.vm_golf7_vehicle_status_report_164, R.string.vm_golf7_vehicle_status_report_165, R.string.vm_golf7_vehicle_status_report_166, R.string.vm_golf7_vehicle_status_report_167, R.string.vm_golf7_vehicle_status_report_168, R.string.vm_golf7_vehicle_status_report_169, R.string.vm_golf7_vehicle_status_report_170, R.string.vm_golf7_vehicle_status_report_171, R.string.vm_golf7_vehicle_status_report_172, R.string.vm_golf7_vehicle_status_report_173, R.string.vm_golf7_vehicle_status_report_174, R.string.vm_golf7_vehicle_status_report_175, R.string.vm_golf7_vehicle_status_report_176, R.string.vm_golf7_vehicle_status_report_177, R.string.vm_golf7_vehicle_status_report_24, R.string.vm_golf7_vehicle_status_report_25, R.string.vm_golf7_vehicle_status_report_26, R.string.vm_golf7_vehicle_status_report_27, R.string.vm_golf7_vehicle_status_report_28, R.string.vm_golf7_vehicle_status_report_29, R.string.vm_golf7_vehicle_status_report_30, R.string.vm_golf7_vehicle_status_report_31, R.string.vm_golf7_vehicle_status_report_32, R.string.vm_golf7_vehicle_status_report_33, R.string.vm_golf7_vehicle_status_report_34, R.string.vm_golf7_vehicle_status_report_35, R.string.vm_golf7_vehicle_status_report_36, R.string.vm_golf7_vehicle_status_report_37, R.string.vm_golf7_vehicle_status_report_38, R.string.vm_golf7_vehicle_status_report_39, R.string.vm_golf7_vehicle_status_report_40, R.string.vm_golf7_vehicle_status_report_41, R.string.vm_golf7_vehicle_status_report_42, R.string.vm_golf7_vehicle_status_report_43, R.string.vm_golf7_vehicle_status_report_44, R.string.vm_golf7_vehicle_status_report_45, R.string.vm_golf7_vehicle_status_report_46, R.string.vm_golf7_vehicle_status_report_47, R.string.vm_golf7_vehicle_status_report_48, R.string.vm_golf7_vehicle_status_report_49, R.string.vm_golf7_vehicle_status_report_50, R.string.vm_golf7_vehicle_status_report_51, R.string.vm_golf7_vehicle_status_report_52, R.string.vm_golf7_vehicle_status_report_53, R.string.vm_golf7_vehicle_status_report_54, R.string.vm_golf7_vehicle_status_report_55, R.string.vm_golf7_vehicle_status_report_56, R.string.vm_golf7_vehicle_status_report_57, R.string.vm_golf7_vehicle_status_report_58, R.string.vm_golf7_vehicle_status_report_59, R.string.vm_golf7_vehicle_status_report_60, R.string.vm_golf7_vehicle_status_report_61, R.string.vm_golf7_vehicle_status_report_62, R.string.vm_golf7_vehicle_status_report_63, R.string.vm_golf7_vehicle_status_report_129, R.string.vm_golf7_vehicle_status_report_130, R.string.vm_golf7_vehicle_status_report_131, R.string.vm_golf7_vehicle_status_report_132, R.string.vm_golf7_vehicle_status_report_133, R.string.vm_golf7_vehicle_status_report_134, R.string.vm_golf7_vehicle_status_report_135, R.string.vm_golf7_vehicle_status_report_136, R.string.vm_golf7_vehicle_status_report_137, R.string.vm_golf7_vehicle_status_report_138, R.string.vm_golf7_vehicle_status_report_139, R.string.vm_golf7_vehicle_status_report_140, R.string.vm_golf7_vehicle_status_report_141, R.string.vm_golf7_vehicle_status_report_142, R.string.vm_golf7_vehicle_status_report_143, R.string.vm_golf7_vehicle_status_report_144, R.string.vm_golf7_vehicle_status_report_145, R.string.vm_golf7_vehicle_status_report_146, R.string.vm_golf7_vehicle_status_report_147, R.string.vm_golf7_vehicle_status_report_148, R.string.vm_golf7_vehicle_status_report_149, R.string.vm_golf7_vehicle_status_report_178, R.string.vm_golf7_vehicle_status_report_179, R.string.vm_golf7_vehicle_status_report_180, R.string.vm_golf7_vehicle_status_report_181, R.string.vm_golf7_vehicle_status_report_182, R.string.vm_golf7_vehicle_status_report_183, R.string.vm_golf7_vehicle_status_report_184, R.string.vm_golf7_vehicle_status_report_185, R.string.vm_golf7_vehicle_status_report_186, R.string.vm_golf7_vehicle_status_report_187, R.string.vm_golf7_vehicle_status_report_188, R.string.vm_golf7_vehicle_status_report_189, R.string.vm_golf7_vehicle_status_report_190, R.string.vm_golf7_vehicle_status_report_191, R.string.vm_golf7_vehicle_status_report_192, R.string.vm_golf7_vehicle_status_report_193, R.string.vm_golf7_vehicle_status_report_194, R.string.vm_golf7_vehicle_status_report_195, R.string.vm_golf7_vehicle_status_report_196, R.string.vm_golf7_vehicle_status_report_197, R.string.vm_golf7_vehicle_status_report_198, R.string.vm_golf7_vehicle_status_report_199, R.string.vm_golf7_vehicle_status_report_200, R.string.vm_golf7_vehicle_status_report_201, R.string.vm_golf7_vehicle_status_report_202, R.string.vm_golf7_vehicle_status_report_203, R.string.vm_golf7_vehicle_status_report_204, R.string.vm_golf7_vehicle_status_report_205, R.string.vm_golf7_vehicle_status_report_206, R.string.vm_golf7_vehicle_status_report_207, R.string.vm_golf7_vehicle_status_report_208, R.string.vm_golf7_vehicle_status_report_209, R.string.vm_golf7_vehicle_status_report_210, R.string.vm_golf7_vehicle_status_report_211, R.string.vm_golf7_vehicle_status_report_212, R.string.vm_golf7_vehicle_status_report_213, R.string.vm_golf7_vehicle_status_report_214};
    private int[] arrStartStopHw = {R.string.vm_golf7_vehicle_status_start_stop_prompt_1, R.string.vm_golf7_vehicle_status_start_stop_prompt_2, R.string.vm_golf7_vehicle_status_start_stop_prompt_3, R.string.vm_golf7_vehicle_status_start_stop_prompt_4, R.string.vm_golf7_vehicle_status_start_stop_prompt_5, R.string.vm_golf7_vehicle_status_start_stop_prompt_6, R.string.vm_golf7_vehicle_status_start_stop_prompt_7, R.string.vm_golf7_vehicle_status_start_stop_prompt_8, R.string.vm_golf7_vehicle_status_start_stop_prompt_9, R.string.vm_golf7_vehicle_status_start_stop_prompt_10, R.string.vm_golf7_vehicle_status_start_stop_prompt_11, R.string.vm_golf7_vehicle_status_start_stop_prompt_12, R.string.vm_golf7_vehicle_status_start_stop_prompt_33, R.string.vm_golf7_vehicle_status_start_stop_prompt_13, R.string.vm_golf7_vehicle_status_start_stop_prompt_3, R.string.vm_golf7_vehicle_status_start_stop_prompt_17, R.string.vm_golf7_vehicle_status_start_stop_prompt_18, R.string.vm_golf7_vehicle_status_start_stop_prompt_34, R.string.vm_golf7_vehicle_status_start_stop_prompt_35, R.string.vm_golf7_vehicle_status_start_stop_prompt_36, R.string.vm_golf7_vehicle_status_start_stop_prompt_14, R.string.vm_golf7_vehicle_status_start_stop_prompt_15, R.string.vm_golf7_vehicle_status_start_stop_prompt_16, R.string.vm_golf7_vehicle_status_start_stop_prompt_17, R.string.vm_golf7_vehicle_status_start_stop_prompt_18, R.string.vm_golf7_vehicle_status_start_stop_prompt_19, R.string.vm_golf7_vehicle_status_start_stop_prompt_20, R.string.vm_golf7_vehicle_status_start_stop_prompt_21, R.string.vm_golf7_vehicle_status_start_stop_prompt_22, R.string.vm_golf7_vehicle_status_start_stop_prompt_23, R.string.vm_golf7_vehicle_status_start_stop_prompt_24, R.string.vm_golf7_vehicle_status_start_stop_prompt_25, R.string.vm_golf7_vehicle_status_start_stop_prompt_26, R.string.vm_golf7_vehicle_status_start_stop_prompt_27, R.string.vm_golf7_vehicle_status_start_stop_prompt_28, R.string.vm_golf7_vehicle_status_start_stop_prompt_29, R.string.vm_golf7_vehicle_status_start_stop_prompt_30, R.string.vm_golf7_vehicle_status_start_stop_prompt_31, R.string.vm_golf7_vehicle_status_start_stop_prompt_32, R.string.vm_golf7_vehicle_status_start_stop_prompt_17, R.string.vm_golf7_vehicle_status_start_stop_prompt_38, R.string.vm_golf7_vehicle_status_start_stop_prompt_39, R.string.vm_golf7_vehicle_status_start_stop_0, R.string.vm_golf7_vehicle_status_start_stop_1, R.string.vm_golf7_vehicle_status_start_stop_2, R.string.vm_golf7_vehicle_status_start_stop_8, R.string.vm_golf7_vehicle_status_start_stop_3, R.string.vm_golf7_vehicle_status_start_stop_4, R.string.vm_golf7_vehicle_status_start_stop_5, R.string.vm_golf7_vehicle_status_start_stop_6, R.string.vm_golf7_vehicle_status_start_stop_7};
    private int[] arrConvConsumers = {R.string.vm_golf7_Conv_consumers_prompt_1, R.string.vm_golf7_Conv_consumers_prompt_2, R.string.vm_golf7_Conv_consumers_prompt_3, R.string.vm_golf7_Conv_consumers_prompt_4, R.string.vm_golf7_Conv_consumers_prompt_5, R.string.vm_golf7_Conv_consumers_prompt_6, R.string.vm_golf7_Conv_consumers_prompt_7, R.string.vm_golf7_Conv_consumers_prompt_8, R.string.vm_golf7_Conv_consumers_prompt_9, R.string.vm_golf7_Conv_consumers_prompt_10, R.string.vm_golf7_Conv_consumers_prompt_11, R.string.vm_golf7_Conv_consumers_prompt_12, R.string.vm_golf7_Conv_consumers_prompt_13, R.string.vm_golf7_Conv_consumers_prompt_14, R.string.vm_golf7_Conv_consumers_prompt_15, R.string.vm_golf7_Conv_consumers_prompt_16, R.string.vm_golf7_Conv_consumers_prompt_17, R.string.vm_golf7_Conv_consumers_prompt_18};
    private List<WarningEntity> mList1 = new ArrayList();
    private List<WarningEntity> mList2 = new ArrayList();
    private List<WarningEntity> mList3 = new ArrayList();

    private int distanceToLocation(int i) {
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

    public static int getLsb(int i) {
        return ((i & (-1)) >> 0) & 255;
    }

    public static int getMidLsb(int i) {
        return ((i & (-1)) >> 8) & 255;
    }

    public static int getMidMsb(int i) {
        return ((i & (-1)) >> 16) & 255;
    }

    public static int getMsb(int i) {
        return ((i & (-1)) >> 24) & 255;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        GeneralTireData.isHaveSpareTire = false;
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        AMapUtils.getInstance().startAMap(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
        int i;
        int i2;
        super.onAMapCallBack(aMapEntity);
        if (aMapEntity.getDestinationDistance() == 0) {
            return;
        }
        int i3 = 0;
        if (aMapEntity.getCarDirection() == 7) {
            i = 7;
        } else if (aMapEntity.getCarDirection() == 8) {
            i = 8;
        } else if (aMapEntity.getCarDirection() == 1) {
            i = 1;
        } else if (aMapEntity.getCarDirection() == 2) {
            i = 2;
        } else if (aMapEntity.getCarDirection() == 3) {
            i = 3;
        } else if (aMapEntity.getCarDirection() == 4) {
            i = 4;
        } else if (aMapEntity.getCarDirection() == 5) {
            i = 5;
        } else {
            i = aMapEntity.getCarDirection() == 6 ? 6 : 0;
        }
        if (aMapEntity.getIcon() == 9) {
            i2 = 1;
        } else if (aMapEntity.getIcon() == 5) {
            i2 = 2;
        } else if (aMapEntity.getIcon() == 3) {
            i2 = 3;
        } else if (aMapEntity.getIcon() == 7) {
            i2 = 4;
        } else if (aMapEntity.getIcon() == 6) {
            i2 = 6;
        } else if (aMapEntity.getIcon() == 2) {
            i2 = 7;
        } else {
            i2 = aMapEntity.getIcon() == 4 ? 8 : 0;
        }
        if (aMapEntity.getDestinationDistance() == 0) {
            this.result = 0;
        } else {
            this.result = Integer.parseInt(this.mDecimalFormat.format((aMapEntity.getNextDistance() * 100) / aMapEntity.getDestinationDistance()));
        }
        sendGdOtherInfo(new byte[]{22, -28, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, (byte) getMsb(aMapEntity.getNextDistance() * 10), (byte) getMidMsb(aMapEntity.getNextDistance() * 10), (byte) getMidLsb(aMapEntity.getNextDistance() * 10), (byte) getLsb(aMapEntity.getNextDistance() * 10), (byte) this.result, (byte) getMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidLsb(aMapEntity.getDestinationDistance() * 10), (byte) getLsb(aMapEntity.getDestinationDistance() * 10), (byte) i, (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(0, aMapEntity.getPlanTime().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(aMapEntity.getPlanTime().indexOf(":") + 1)), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(0, aMapEntity.getSurplusAllTimeStr().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(aMapEntity.getSurplusAllTimeStr().indexOf(":") + 1)), (byte) i2, 0, 0});
        byte[] bytes = aMapEntity.getNextWayName().trim().getBytes(StandardCharsets.UTF_8);
        byte[] bArr = new byte[30];
        if (bytes.length > 30) {
            while (i3 < 30) {
                bArr[i3] = bytes[i3];
                i3++;
            }
            sendWayName(bArr);
            return;
        }
        if (bytes.length < 30) {
            int length = bytes.length;
            while (i3 < 30 - length) {
                bytes = SplicingByte(bytes, " ".getBytes(StandardCharsets.UTF_8));
                i3++;
            }
            sendWayName(bytes);
            return;
        }
        sendWayName(bytes);
    }

    private void sendWayName(byte[] bArr) {
        if (isWayNameChange(bArr)) {
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -27, 0}, bArr));
        }
    }

    private boolean isWayNameChange(byte[] bArr) {
        if (Arrays.equals(this.nowWayNameByte, bArr)) {
            return false;
        }
        this.nowWayNameByte = bArr;
        return true;
    }

    private void sendGdOtherInfo(byte[] bArr) {
        if (isOtherInfoChange(bArr)) {
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    private boolean isOtherInfoChange(byte[] bArr) {
        if (Arrays.equals(this.otherInfoByte, bArr)) {
            return false;
        }
        this.otherInfoByte = bArr;
        return true;
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        super.voiceControlInfo(str);
        setDyVoiceAction(str);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void setDyVoiceAction(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1950015300:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_AC_MAX)) {
                    c = 0;
                    break;
                }
                break;
            case -1785642657:
                if (str.equals(CanbusControlAction.AIR_REAR_POAER_ON)) {
                    c = 1;
                    break;
                }
                break;
            case -1607857989:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_MANUAL)) {
                    c = 2;
                    break;
                }
                break;
            case -1585736007:
                if (str.equals(CanbusControlAction.AIR_REAR_RIGHT_SEAT_HEAT_TO)) {
                    c = 3;
                    break;
                }
                break;
            case -1567175431:
                if (str.equals(CanbusControlAction.AIR_WIND_BLOW_FOOT)) {
                    c = 4;
                    break;
                }
                break;
            case -1567125909:
                if (str.equals(CanbusControlAction.AIR_WIND_BLOW_HEAD)) {
                    c = 5;
                    break;
                }
                break;
            case -1470045433:
                if (str.equals("front_defog")) {
                    c = 6;
                    break;
                }
                break;
            case -1412208249:
                if (str.equals("air.ac.on")) {
                    c = 7;
                    break;
                }
                break;
            case -1409945485:
                if (str.equals(CanbusControlAction.AIR_LEFT_SEAT_HEAT_TO)) {
                    c = '\b';
                    break;
                }
                break;
            case -1326915554:
                if (str.equals("ac.temperature.max")) {
                    c = '\t';
                    break;
                }
                break;
            case -1326915316:
                if (str.equals("ac.temperature.min")) {
                    c = '\n';
                    break;
                }
                break;
            case -1256523009:
                if (str.equals(CanbusControlAction.AIR_LEFT_SEAT_COLD_TO)) {
                    c = 11;
                    break;
                }
                break;
            case -1226270570:
                if (str.equals("ac.open")) {
                    c = '\f';
                    break;
                }
                break;
            case -1092999012:
                if (str.equals(CanbusControlAction.AIR_IN_OUT_CYCLE_AUTO)) {
                    c = '\r';
                    break;
                }
                break;
            case -940325874:
                if (str.equals(CanbusControlAction.AIR_SYNC_ON)) {
                    c = 14;
                    break;
                }
                break;
            case -866529054:
                if (str.equals("air.in.out.cycle.off")) {
                    c = 15;
                    break;
                }
                break;
            case -854978899:
                if (str.equals(CanbusControlAction.AIR_REAR_LOCK_ON)) {
                    c = 16;
                    break;
                }
                break;
            case -828782905:
                if (str.equals("air.ac.off")) {
                    c = 17;
                    break;
                }
                break;
            case -734542239:
                if (str.equals(CanbusControlAction.AIR_REAR_LOCK_OFF)) {
                    c = 18;
                    break;
                }
                break;
            case -553279150:
                if (str.equals(CanbusControlAction.AIR_STEERING_WHEEL_OFF)) {
                    c = 19;
                    break;
                }
                break;
            case -112216342:
                if (str.equals(CanbusControlAction.AIR_CLEAN_ON)) {
                    c = 20;
                    break;
                }
                break;
            case 103110984:
                if (str.equals(CanbusControlAction.AIR_RIGHT_SEAT_HEAT_TO)) {
                    c = 21;
                    break;
                }
                break;
            case 256533460:
                if (str.equals(CanbusControlAction.AIR_RIGHT_SEAT_COLD_TO)) {
                    c = 22;
                    break;
                }
                break;
            case 479652335:
                if (str.equals(CanbusControlAction.AIR_REAR_POAER_OFF)) {
                    c = 23;
                    break;
                }
                break;
            case 629126444:
                if (str.equals("ac.close")) {
                    c = 24;
                    break;
                }
                break;
            case 816260548:
                if (str.equals(CanbusControlAction.AIR_CLEAN_OFF)) {
                    c = 25;
                    break;
                }
                break;
            case 890880226:
                if (str.equals(CanbusControlAction.AIR_REAR_LEFT_SEAT_HEAT_TO)) {
                    c = 26;
                    break;
                }
                break;
            case 914668832:
                if (str.equals(CanbusControlAction.AIR_SYNC_OFF)) {
                    c = 27;
                    break;
                }
                break;
            case 956867879:
                if (str.equals(CanbusControlAction.AIR_REAR_WIND_LEVEL_TO)) {
                    c = 28;
                    break;
                }
                break;
            case 1218099172:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_AUTO)) {
                    c = 29;
                    break;
                }
                break;
            case 1225772921:
                if (str.equals("ac.windlevel.to")) {
                    c = 30;
                    break;
                }
                break;
            case 1377338037:
                if (str.equals(CanbusControlAction.AIR_REAR_AUTO)) {
                    c = 31;
                    break;
                }
                break;
            case 1481217153:
                if (str.equals("ac.temperature.to")) {
                    c = ' ';
                    break;
                }
                break;
            case 1496068108:
                if (str.equals("air.in.out.cycle.on")) {
                    c = '!';
                    break;
                }
                break;
            case 1733069433:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_MAX_FRONT)) {
                    c = '\"';
                    break;
                }
                break;
            case 1921814940:
                if (str.equals(CanbusControlAction.AIR_STEERING_WHEEL_ON)) {
                    c = '#';
                    break;
                }
                break;
            case 1959044539:
                if (str.equals(CanbusControlAction.AIR_WIND_BLOW_WINDOW)) {
                    c = Typography.dollar;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
                break;
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 1});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 3});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 44, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 4:
                if (!GeneralAirData.front_left_blow_foot) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
                    break;
                }
            case 5:
                if (!GeneralAirData.front_left_blow_head) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
                    break;
                }
            case 6:
                if (!GeneralAirData.front_defog) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 0});
                    break;
                }
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 1});
                break;
            case '\b':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case '\t':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -1});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -1});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -1});
                break;
            case '\n':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -2});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -2});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -2});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case '\f':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 1});
                break;
            case '\r':
                if (GeneralAirData.in_out_auto_cycle != 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 0});
                    break;
                }
            case 14:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 1});
                break;
            case 15:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 2});
                break;
            case 16:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 1});
                break;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 0});
                break;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 0});
                break;
            case 19:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 0});
                break;
            case 20:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 1});
                break;
            case 21:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 22:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 23:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 0});
                break;
            case 24:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 0});
                break;
            case 25:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 0});
                break;
            case 26:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 43, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 27:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 0});
                break;
            case 28:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 29:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
                break;
            case 30:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 31:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 40, 1});
                break;
            case ' ':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte) (Integer.parseInt(VoiceControlData.voiceValue) * 2)});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte) (Integer.parseInt(VoiceControlData.voiceValue) * 2)});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte) (Integer.parseInt(VoiceControlData.voiceValue) * 2)});
                break;
            case '!':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 1});
                break;
            case '\"':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 2});
                break;
            case '#':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 1});
                break;
            case '$':
                if (!GeneralAirData.front_left_blow_window) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
                    break;
                }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        try {
            int i = byteArrayToIntArray[1];
            if (i == 30) {
                setCarMaintenance();
            } else if (i == 31) {
                setCarNumber();
            } else if (i == 49) {
                setFrontAirData(bArr);
            } else if (i != 100) {
                if (i != 193) {
                    if (i != 232) {
                        if (i == 240) {
                            setVersionInfo();
                        } else if (i != 53) {
                            if (i == 54) {
                                setCarLightInfo();
                            } else if (i == 65) {
                                setFrontRearRadar();
                            } else if (i != 66) {
                                switch (i) {
                                    case 17:
                                        saveAccState();
                                        keyControl0x11();
                                        setTrackInfo();
                                        setSettingInfo();
                                        if (!isSwc0x0cReturn(bArr)) {
                                            dccOffOnCtrl();
                                            setHybridInfo();
                                            updateSpeedInfo(this.mCanBusInfoInt[3]);
                                            break;
                                        } else {
                                            return;
                                        }
                                    case 18:
                                        if (!isDoorMsgReturn(bArr)) {
                                            setCarDoorInfo();
                                            setOffRoadData();
                                            setCarStatusInfo();
                                            break;
                                        } else {
                                            return;
                                        }
                                    case 19:
                                        serDriveStatusStart();
                                        break;
                                    case 20:
                                        setDriveStatusLongTime();
                                        break;
                                    case 21:
                                        setDriveStatusRefuelling();
                                        break;
                                    case 22:
                                        if (isDataChange(byteArrayToIntArray)) {
                                            setConvenienceConsumers();
                                            break;
                                        } else {
                                            return;
                                        }
                                    case 23:
                                        setDriveStatusHybrid();
                                        break;
                                    case 24:
                                        setHybridView();
                                        break;
                                    case 25:
                                        setCarDetailedInfo();
                                        break;
                                    default:
                                        switch (i) {
                                            case 69:
                                                if (isDataChange(byteArrayToIntArray)) {
                                                    setParkingAndManoeuvring();
                                                    break;
                                                } else {
                                                    return;
                                                }
                                            case 70:
                                                if (isDataChange(byteArrayToIntArray)) {
                                                    setType();
                                                    break;
                                                } else {
                                                    return;
                                                }
                                            case 71:
                                                if (isDataChange(byteArrayToIntArray)) {
                                                    setDriverAssistant();
                                                    break;
                                                } else {
                                                    return;
                                                }
                                            case 72:
                                                setTypeDataInfo();
                                                break;
                                            case 73:
                                                if (isDataChange(byteArrayToIntArray)) {
                                                    setElectricDrive();
                                                    break;
                                                } else {
                                                    return;
                                                }
                                            default:
                                                switch (i) {
                                                    case 103:
                                                        if (isDataChange(byteArrayToIntArray)) {
                                                            setEnvironmentLighting();
                                                            break;
                                                        } else {
                                                            return;
                                                        }
                                                    case 104:
                                                        if (isDataChange(byteArrayToIntArray)) {
                                                            setLightInfo();
                                                            break;
                                                        } else {
                                                            return;
                                                        }
                                                    case 105:
                                                        if (isDataChange(byteArrayToIntArray)) {
                                                            setRearviewMirrorAndWiper();
                                                            break;
                                                        } else {
                                                            return;
                                                        }
                                                    default:
                                                        switch (i) {
                                                            case 116:
                                                                setCarWarningInfo();
                                                                break;
                                                            case 117:
                                                                setCarWarningInfo0x75();
                                                                break;
                                                            case 118:
                                                                if (isDataChange(byteArrayToIntArray)) {
                                                                    setMultifunctionalDisplay();
                                                                    break;
                                                                } else {
                                                                    return;
                                                                }
                                                            case 119:
                                                                setCarWarningInfo0x77();
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case 133:
                                                                        if (isDataChange(byteArrayToIntArray)) {
                                                                            setEscSystem();
                                                                            break;
                                                                        } else {
                                                                            return;
                                                                        }
                                                                    case HotKeyConstant.K_SLEEP /* 134 */:
                                                                        if (isDataChange(byteArrayToIntArray)) {
                                                                            setDriveModeSelect();
                                                                            break;
                                                                        } else {
                                                                            return;
                                                                        }
                                                                    case 135:
                                                                        if (isDataChange(byteArrayToIntArray)) {
                                                                            CommUtil.printHexStringByTag("fang", "head", this.mCanBusInfoByte);
                                                                            setDriveModeOffload();
                                                                            break;
                                                                        } else {
                                                                            return;
                                                                        }
                                                                    case 136:
                                                                        if (!isSwc0x0dReturn(bArr)) {
                                                                            setHybrid();
                                                                            break;
                                                                        } else {
                                                                            return;
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                            } else {
                                setLeftRightRadar();
                            }
                        } else if (!isDataChange(byteArrayToIntArray)) {
                        } else {
                            airSet();
                        }
                    } else {
                        if (!isDataChange(byteArrayToIntArray)) {
                            return;
                        }
                        setReversingVideo();
                        setOriginalCameraStatusInfo();
                    }
                } else if (!isDataChange(byteArrayToIntArray)) {
                } else {
                    setUnit();
                }
            } else if (!isDataChange(byteArrayToIntArray)) {
            } else {
                setOpenAndClose();
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void saveAccState() {
        if (this.lastAccState != DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            this.isLockAirViewToShow = true;
            initLock();
            this.lastAccState = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        }
        Log.e("ooo", "上锁" + this.isLockAirViewToShow);
    }

    private void setSettingInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "electric_drive_charging_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "electric_drive_charging_settings", "_206_new_function"), get0x11Data9State()).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String get0x11Data9State() {
        int i = this.mCanBusInfoInt[11];
        if (i == 1) {
            return this.mContext.getString(R.string._206_new_function1);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._206_new_function2);
        }
        return this.mContext.getString(R.string._206_new_function0);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void dccOffOnCtrl() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[4] == 12 && iArr[5] == 0) {
            if (!this.mCurrentCaseC) {
                startSettingActivity(this.mContext, 0, 0);
                this.mCurrentCaseC = true;
                this.mCurrentCaseD = false;
            } else {
                finishActivity();
                this.mCurrentCaseC = false;
            }
        }
    }

    private void setHybridInfo() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[4] == 13 && iArr[5] == 0) {
            if (!this.mCurrentCaseD) {
                startSettingActivity(this.mContext, 16, 0);
                this.mCurrentCaseD = true;
                this.mCurrentCaseC = false;
                return;
            }
            this.mCurrentCaseD = false;
        }
    }

    private void keyControl0x11() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyLongClick(0);
                break;
            case 1:
                realKeyLongClick(7);
                break;
            case 2:
                realKeyLongClick(8);
                break;
            case 3:
                Log.d(Config.LOG_TAG, "MENU");
                realKeyLongClick(3);
                break;
            case 4:
                realKeyLongClick(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                realKeyLongClick(HotKeyConstant.K_PHONE_ON_OFF);
                break;
            case 8:
                realKeyLongClick(46);
                break;
            case 9:
                realKeyLongClick(45);
                break;
            case 10:
                realKeyLongClick(52);
                break;
            case 11:
                realKeyLongClick(2);
                break;
        }
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyLongClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private void setFrontAirData(byte[] bArr) throws Resources.NotFoundException {
        setDoorOrWindowState(this.mCanBusInfoInt[10]);
        this.mCanBusInfoInt[10] = 0;
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        GeneralAirData.seat_steering_wheel_synchronization = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[6];
        frontWindLv = iArr[7];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        GeneralAirData.front_defog = false;
        if (i == 1) {
            GeneralAirData.front_auto_wind_model = true;
        } else if (i == 2) {
            GeneralAirData.front_defog = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        int i2 = this.mCanBusInfoInt[8];
        leftFrontTemp = i2;
        rightFrontTemp = r10[9];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(i2);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
        int[] iArr2 = this.mCanBusInfoInt;
        int i3 = iArr2[12];
        rearTemp = i3 * 1.0f;
        outDoorTemp = iArr2[13];
        GeneralAirData.rear_temperature = resolveLeftAndRightTemp(i3);
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        rearWindBlow = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 4);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
        if (intFromByteWithBit == 3) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (intFromByteWithBit == 6) {
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_head = true;
        }
        boolean zIsOnlyRearAirDataChange = isOnlyRearAirDataChange();
        boolean zIsOnlyOutDoorDataChange = isOnlyOutDoorDataChange();
        if (getCurrentEachCanId() == 99) {
            Log.e("ooo", "车型匹配" + this.isLockAirViewToShow);
            if (this.isLockAirViewToShow) {
                return;
            }
        }
        if (!zIsOnlyRearAirDataChange && !zIsOnlyOutDoorDataChange) {
            updateAirActivity(this.mContext, 1001);
        } else if (zIsOnlyRearAirDataChange && !isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "is_rear_air_temp_change_mqb", rearTemp);
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_lv_change", GeneralAirData.rear_wind_level);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_air_power", GeneralAirData.rear_power);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_auto", GeneralAirData.rear_auto);
            SharePreUtil.setIntValue(this.mContext, "is_rear_left_heat", GeneralAirData.rear_left_seat_heat_level);
            SharePreUtil.setIntValue(this.mContext, "is_rear_right_heat", GeneralAirData.rear_right_seat_heat_level);
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_blow", rearWindBlow);
            updateAirActivity(this.mContext, 1003);
        }
        if (zIsOnlyOutDoorDataChange) {
            SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private void setDoorOrWindowState(int i) throws Resources.NotFoundException {
        String string;
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.door_windows_open);
        } else if (i <= 10 && i >= 1) {
            string = (i * 10) + "%";
        } else {
            string = this.mContext.getResources().getString(R.string.windows_off);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "airSetting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "airSetting", "door_windows_open_state"), string).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void initLock() {
        Log.e("ooo", "初始化时钟");
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(20000L, 1000L) { // from class: com.hzbhd.canbus.car._206.MsgMgr.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                Log.e("ooo", "倒计时中..." + MsgMgr.this.isLockAirViewToShow);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MsgMgr.this.isLockAirViewToShow = false;
                Log.e("ooo", "倒计时结束" + MsgMgr.this.isLockAirViewToShow);
            }
        };
        this.countDownTimer = countDownTimer2;
        countDownTimer2.start();
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[13];
        if (GeneralAirData.fahrenheit_celsius) {
            return ((float) ((i * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
        }
        return ((float) ((((i * 0.5d) - 40.0d) * 1.8d) + 32.0d)) + getTempUnitF(this.mContext);
    }

    private void setDriveModeSelect() {
        int i = this.mCanBusInfoInt[3];
        int i2 = i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 32 ? i != 64 ? 0 : 1 : 2 : 3 : 4 : 5 : 6 : 7;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(i2)));
        int[] iArr = {getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]))};
        for (int i3 = 0; i3 <= 5; i3++) {
            arrayList.add(new SettingUpdateEntity(1, i3, Integer.valueOf(iArr[i3])));
        }
        LogUtil.showLog("0x86");
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDriveModeOffload() {
        int[] iArr = {getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 8; i++) {
            arrayList.add(new SettingUpdateEntity(2, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setEscSystem() {
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(indexBy3Bit)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setType() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(iArr[3], 5, 2));
        carSpeedWarning = indexBy2Bit;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(i)).setProgress(i - 30));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(indexBy3Bit)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDriverAssistant() {
        int[] iArr = {getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2)), getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])), getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 5)), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10])), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10])), getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10])), getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2)), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11])), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12])), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13])), getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13])), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14])), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]))};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 12; i++) {
            arrayList.add(new SettingUpdateEntity(5, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setParkingAndManoeuvring() {
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2));
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {indexBy2Bit, indexBy2Bit2, indexBy2Bit3, indexBy2Bit4};
        int[] iArr3 = {iArr[4], iArr[5], iArr[6], iArr[7]};
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(indexBy3Bit)));
        for (int i = 1; i <= 4; i++) {
            arrayList.add(new SettingUpdateEntity(6, i, Integer.valueOf(iArr2[i - 1])));
        }
        for (int i2 = 5; i2 <= 8; i2++) {
            int i3 = i2 - 5;
            arrayList.add(new SettingUpdateEntity(6, i2, Integer.valueOf(iArr3[i3])).setProgress(iArr3[i3]));
        }
        arrayList.add(new SettingUpdateEntity(6, 11, Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) ? 1 : 0)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLightInfo() {
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2));
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]));
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {indexBy3Bit, indexBy2Bit, indexBy2Bit2, indexBy2Bit3, indexBy2Bit4, indexBy2Bit5, indexBy2Bit6};
        int[] iArr3 = {iArr[9], iArr[10], iArr[6], iArr[7], iArr[8]};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 6; i++) {
            arrayList.add(new SettingUpdateEntity(7, i, Integer.valueOf(iArr2[i])));
        }
        for (int i2 = 7; i2 <= 11; i2++) {
            int i3 = i2 - 7;
            arrayList.add(new SettingUpdateEntity(7, i2, Integer.valueOf(iArr3[i3])).setProgress(iArr3[i3]));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setEnvironmentLighting() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {iArr[4], iArr[7] - 1, iArr[3]};
        int[] iArr3 = {iArr[5], iArr[6], iArr[8]};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 2; i++) {
            arrayList.add(new SettingUpdateEntity(8, i, Integer.valueOf(iArr2[i])));
        }
        for (int i2 = 3; i2 <= 5; i2++) {
            int i3 = i2 - 3;
            arrayList.add(new SettingUpdateEntity(8, i2, Integer.valueOf(iArr3[i3])).setProgress(iArr3[i3]));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRearviewMirrorAndWiper() {
        int[] iArr = {getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]))};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 4; i++) {
            arrayList.add(new SettingUpdateEntity(9, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setOpenAndClose() {
        int[] iArr = {getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2)), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2)), getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]))};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 5; i++) {
            arrayList.add(new SettingUpdateEntity(10, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setMultifunctionalDisplay() {
        int[] iArr = {getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])), getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]))};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 9; i++) {
            arrayList.add(new SettingUpdateEntity(11, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setUnit() {
        int[] iArr = {getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])), getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])), getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)), getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2)), getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 6; i++) {
            arrayList.add(new SettingUpdateEntity(12, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarMaintenance() throws Resources.NotFoundException {
        String string;
        String string2;
        String string3;
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[4] * 256) + iArr[5];
        int i2 = (iArr[6] * 256) + iArr[7];
        int i3 = (iArr[8] * 256) + iArr[9];
        int i4 = (iArr[10] * 256) + iArr[11];
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[3], 6, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        String string4 = this.mCanBusInfoInt[2] == 0 ? this.mContext.getResources().getString(R.string.unit_km) : this.mContext.getResources().getString(R.string.unit_m);
        String string5 = "";
        if (intFromByteWithBit == 0) {
            string = this.mContext.getResources().getString(R.string.set_default);
        } else if (intFromByteWithBit == 1) {
            string = String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_days), Integer.valueOf(i));
        } else {
            string = intFromByteWithBit != 2 ? "" : String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_overdue_days), Integer.valueOf(i));
        }
        if (intFromByteWithBit2 == 0) {
            string2 = this.mContext.getResources().getString(R.string.set_default);
        } else if (intFromByteWithBit2 == 1) {
            string2 = String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_distance), Integer.valueOf(i2));
        } else {
            string2 = intFromByteWithBit2 != 2 ? "" : String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_overdue_distance), Integer.valueOf(i2));
        }
        if (intFromByteWithBit3 == 0) {
            string3 = this.mContext.getResources().getString(R.string.set_default);
        } else if (intFromByteWithBit3 == 1) {
            string3 = String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_days), Integer.valueOf(i3));
        } else {
            string3 = intFromByteWithBit3 != 2 ? "" : String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_overdue_days), Integer.valueOf(i3));
        }
        if (intFromByteWithBit4 == 0) {
            string5 = this.mContext.getResources().getString(R.string.set_default);
        } else if (intFromByteWithBit4 == 1) {
            string5 = String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_distance), Integer.valueOf(i4));
        } else if (intFromByteWithBit4 == 2) {
            string5 = String.format(this.mContext.getResources().getString(R.string.vm_golf7_vehicle_setup_service_overdue_distance), Integer.valueOf(i4));
        }
        String str = string5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(4, 0, string4));
        arrayList.add(new DriverUpdateEntity(4, 1, string));
        arrayList.add(new DriverUpdateEntity(4, 2, string2));
        arrayList.add(new DriverUpdateEntity(4, 3, string3));
        arrayList.add(new DriverUpdateEntity(4, 4, str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void airSet() {
        int[] iArr = {getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5])), getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])), getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 2; i++) {
            arrayList.add(new SettingUpdateEntity(14, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setReversingVideo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        int i2 = (i > 70 || i < 30) ? 0 : i;
        int i3 = iArr[5];
        if (i3 > 70 || i3 < 30) {
            i3 = 0;
        }
        int i4 = iArr[6];
        if (i4 > 70 || i4 < 30) {
            i4 = 0;
        }
        int[] iArr2 = {i2, i3, i4};
        String[] strArr = {String.valueOf(i), String.valueOf(this.mCanBusInfoInt[5]), String.valueOf(this.mCanBusInfoInt[6])};
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 <= 2; i5++) {
            arrayList.add(new SettingUpdateEntity(15, i5, strArr[i5]).setProgress(iArr2[i5] - 30));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setOriginalCameraStatusInfo() {
        isPanoramicRadarClose = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 2));
        arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[3] == 3));
        arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[3] == 9));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setHybrid() {
        Intent intent = new Intent(CAROUTTEMCHANGEACTION2);
        this.mContext.sendBroadcast(intent);
        int i = 3;
        int i2 = this.mCanBusInfoInt[3];
        if (i2 == 8) {
            intent.putExtra("Gte", "GTE");
            this.mContext.sendBroadcast(intent);
        } else {
            if (i2 == 16) {
                intent.putExtra("Gte", "");
                this.mContext.sendBroadcast(intent);
            } else if (i2 == 32) {
                i = 2;
                intent.putExtra("Gte", "");
                this.mContext.sendBroadcast(intent);
            } else if (i2 == 64) {
                i = 1;
                intent.putExtra("Gte", "");
                this.mContext.sendBroadcast(intent);
            } else if (i2 == 128) {
                startSettingActivity(this.mContext, 16, 0);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(16, 0, Integer.valueOf(i)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
        i = 0;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SettingUpdateEntity(16, 0, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setElectricDrive() {
        /*
            r12 = this;
            int[] r0 = r12.mCanBusInfoInt
            r1 = 6
            r1 = r0[r1]
            r2 = 5
            r3 = r0[r2]
            r4 = 3
            r5 = r0[r4]
            r6 = 1
            r7 = 255(0xff, float:3.57E-43)
            r8 = 10
            r9 = 2
            r10 = 0
            if (r5 == r2) goto L1c
            if (r5 == r8) goto L22
            r2 = 13
            if (r5 == r2) goto L20
            if (r5 == r7) goto L1e
        L1c:
            r2 = r10
            goto L23
        L1e:
            r2 = r4
            goto L23
        L20:
            r2 = r9
            goto L23
        L22:
            r2 = r6
        L23:
            r5 = 4
            r0 = r0[r5]
            r5 = 254(0xfe, float:3.56E-43)
            r11 = 15
            if (r0 != r5) goto L2e
        L2c:
            r11 = r10
            goto L3c
        L2e:
            if (r0 != r7) goto L31
            goto L3c
        L31:
            r5 = 32
            if (r0 < r5) goto L2c
            r5 = 60
            if (r0 >= r5) goto L2c
            int r0 = r0 / r9
            int r11 = r0 + (-15)
        L3c:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.hzbhd.canbus.entity.SettingUpdateEntity r5 = new com.hzbhd.canbus.entity.SettingUpdateEntity
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7 = 19
            r5.<init>(r7, r10, r2)
            r0.add(r5)
            com.hzbhd.canbus.entity.SettingUpdateEntity r2 = new com.hzbhd.canbus.entity.SettingUpdateEntity
            java.lang.Integer r5 = java.lang.Integer.valueOf(r11)
            r2.<init>(r7, r6, r5)
            r0.add(r2)
            com.hzbhd.canbus.entity.SettingUpdateEntity r2 = new com.hzbhd.canbus.entity.SettingUpdateEntity
            int r1 = r1 * r8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            r2.<init>(r7, r9, r5)
            com.hzbhd.canbus.entity.SettingUpdateEntity r1 = r2.setProgress(r1)
            r0.add(r1)
            com.hzbhd.canbus.entity.SettingUpdateEntity r1 = new com.hzbhd.canbus.entity.SettingUpdateEntity
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r1.<init>(r7, r4, r2)
            r0.add(r1)
            r12.updateGeneralSettingData(r0)
            r0 = 0
            r12.updateSettingActivity(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._206.MsgMgr.setElectricDrive():void");
    }

    private void setConvenienceConsumers() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        String str = i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "two" : "three_two" : "one" : "half" : "three_eighths" : "quarter";
        int i2 = iArr[3];
        String str2 = iArr[11] == 0 ? "feul_unit_1" : "feul_unit_2";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(17, 0, str));
        arrayList.add(new SettingUpdateEntity(17, 1, Integer.valueOf(i2)));
        arrayList.add(new SettingUpdateEntity(17, 2, str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setFrontRearRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mDisableData = 12;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(11, distanceToLocation(this.mCanBusInfoInt[2]), distanceToLocation(this.mCanBusInfoInt[3]), distanceToLocation(this.mCanBusInfoInt[4]), distanceToLocation(this.mCanBusInfoInt[5]));
        RadarInfoUtil.setFrontRadarLocationData(11, distanceToLocation(this.mCanBusInfoInt[6]), distanceToLocation(this.mCanBusInfoInt[7]), distanceToLocation(this.mCanBusInfoInt[8]), distanceToLocation(this.mCanBusInfoInt[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setLeftRightRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mDisableData = 12;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRightRadarLocationData(11, distanceToLocation(this.mCanBusInfoInt[2]), distanceToLocation(this.mCanBusInfoInt[3]), distanceToLocation(this.mCanBusInfoInt[4]), distanceToLocation(this.mCanBusInfoInt[5]));
        RadarInfoUtil.setLeftRadarLocationData(11, distanceToLocation(this.mCanBusInfoInt[6]), distanceToLocation(this.mCanBusInfoInt[7]), distanceToLocation(this.mCanBusInfoInt[8]), distanceToLocation(this.mCanBusInfoInt[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void serDriveStatusStart() {
        int[] iArr = this.mCanBusInfoInt;
        double d = ((iArr[2] * 256) + iArr[3]) * 0.1d;
        double d2 = (iArr[4] * 256) + iArr[5];
        double d3 = ((iArr[6] * 256) + iArr[7]) * 0.1d;
        double d4 = (iArr[8] * 256) + iArr[9];
        double d5 = iArr[10];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, ((float) d) + "l/km"));
        arrayList.add(new DriverUpdateEntity(0, 1, ((float) d2) + "km"));
        arrayList.add(new DriverUpdateEntity(0, 2, ((float) d3) + "km"));
        arrayList.add(new DriverUpdateEntity(0, 3, ((int) (d4 / 60.0d)) + " h " + ((int) (d4 % 60.0d)) + "min"));
        arrayList.add(new DriverUpdateEntity(0, 4, ((int) d5) + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveStatusLongTime() {
        int[] iArr = this.mCanBusInfoInt;
        double d = ((iArr[2] * 256) + iArr[3]) * 0.1d;
        double d2 = (iArr[4] * 256) + iArr[5];
        double d3 = ((iArr[6] * 256) + iArr[7]) * 0.1d;
        double d4 = (iArr[8] * 256) + iArr[9];
        double d5 = iArr[10];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(2, 0, ((float) d) + "l/km"));
        arrayList.add(new DriverUpdateEntity(2, 1, ((float) d2) + "km"));
        arrayList.add(new DriverUpdateEntity(2, 2, ((float) d3) + "km"));
        arrayList.add(new DriverUpdateEntity(2, 3, ((int) (d4 / 60.0d)) + " h " + ((int) (d4 % 60.0d)) + "min"));
        arrayList.add(new DriverUpdateEntity(2, 4, ((int) d5) + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveStatusRefuelling() {
        int[] iArr = this.mCanBusInfoInt;
        double d = ((iArr[2] * 256) + iArr[3]) * 0.1d;
        double d2 = (iArr[4] * 256) + iArr[5];
        double d3 = ((iArr[6] * 256) + iArr[7]) * 0.1d;
        double d4 = (iArr[8] * 256) + iArr[9];
        double d5 = iArr[10];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, ((float) d) + "l/km"));
        arrayList.add(new DriverUpdateEntity(1, 1, ((float) d2) + "km"));
        arrayList.add(new DriverUpdateEntity(1, 2, ((float) d3) + "km"));
        arrayList.add(new DriverUpdateEntity(1, 3, ((int) (d4 / 60.0d)) + " h " + ((int) (d4 % 60.0d)) + "min"));
        arrayList.add(new DriverUpdateEntity(1, 4, ((int) d5) + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveStatusHybrid() {
        byte[] bArr = this.mCanBusInfoByte;
        double d = (((bArr[2] << 8) & InputDeviceCompat.SOURCE_ANY) | (bArr[3] & 255)) * 0.1d;
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[4] * 256) + iArr[5];
        double d2 = (((bArr[6] << 8) & InputDeviceCompat.SOURCE_ANY) | (bArr[7] & 255)) * 0.1d;
        int i2 = (iArr[8] * 256) + iArr[9];
        double d3 = ((bArr[11] & 255) | ((bArr[10] << 8) & InputDeviceCompat.SOURCE_ANY)) * 0.1d;
        int i3 = (iArr[12] * 256) + iArr[13];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 5, ((float) d) + " kWh/100km"));
        arrayList.add(new DriverUpdateEntity(0, 6, i + "km"));
        arrayList.add(new DriverUpdateEntity(2, 5, ((float) d2) + " kWh/100km"));
        arrayList.add(new DriverUpdateEntity(2, 6, i2 + "km"));
        arrayList.add(new DriverUpdateEntity(1, 5, ((float) d3) + " kWh/100km"));
        arrayList.add(new DriverUpdateEntity(1, 6, i3 + "km"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setHybridView() {
        if (this.mCanBusInfoInt[4] == 0) {
            GeneralHybirdData.isShowMotor = false;
            GeneralHybirdData.isShowBattery = false;
            GeneralHybirdData.energyDirection = 0;
            GeneralHybirdData.wheelTrack = 0;
        } else {
            GeneralHybirdData.isShowBattery = true;
            int i = this.mCanBusInfoInt[4];
            if (i == 1 || i == 2 || i == 3 || i == 7) {
                GeneralHybirdData.isShowMotor = true;
            } else {
                GeneralHybirdData.isShowMotor = false;
            }
            int i2 = this.mCanBusInfoInt[4];
            if (i2 == 4) {
                GeneralHybirdData.energyDirection = 0;
            } else if (i2 == 1 || i2 == 3 || i2 == 6) {
                GeneralHybirdData.energyDirection = 1;
            } else {
                GeneralHybirdData.energyDirection = 2;
            }
            switch (this.mCanBusInfoInt[4]) {
                case 1:
                case 4:
                    GeneralHybirdData.wheelTrack = 0;
                    break;
                case 2:
                    GeneralHybirdData.wheelTrack = 1;
                    break;
                case 3:
                case 6:
                    GeneralHybirdData.wheelTrack = 2;
                    break;
                case 5:
                    GeneralHybirdData.wheelTrack = 3;
                    break;
                case 7:
                    GeneralHybirdData.wheelTrack = 4;
                    break;
            }
        }
        GeneralHybirdData.title = this.mContext.getString(R.string.energy_info);
        int i3 = this.mCanBusInfoInt[10];
        if (i3 == 0) {
            GeneralHybirdData.powerBatteryLevel = 0;
        } else if (i3 > 0 && i3 <= 10) {
            GeneralHybirdData.powerBatteryLevel = 1;
        } else if (i3 > 10 && i3 <= 20) {
            GeneralHybirdData.powerBatteryLevel = 2;
        } else if (i3 > 20 && i3 <= 30) {
            GeneralHybirdData.powerBatteryLevel = 3;
        } else if (i3 > 30 && i3 <= 40) {
            GeneralHybirdData.powerBatteryLevel = 4;
        } else if (i3 > 40 && i3 <= 50) {
            GeneralHybirdData.powerBatteryLevel = 5;
        } else if (i3 > 50 && i3 <= 60) {
            GeneralHybirdData.powerBatteryLevel = 6;
        } else if (i3 > 60 && i3 <= 70) {
            GeneralHybirdData.powerBatteryLevel = 7;
        } else if (i3 > 70 && i3 <= 80) {
            GeneralHybirdData.powerBatteryLevel = 8;
        } else if (i3 > 80 && i3 <= 90) {
            GeneralHybirdData.powerBatteryLevel = 9;
        } else if (i3 > 90 && i3 <= 100) {
            GeneralHybirdData.powerBatteryLevel = 10;
        }
        GeneralHybirdData.powerBatteryValue = this.mCanBusInfoInt[10];
        ArrayList arrayList = new ArrayList();
        StringBuilder sbAppend = new StringBuilder().append(this.mContext.getString(R.string.mqb_endurance_potential)).append(":");
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(sbAppend.append((iArr[2] * 256) + iArr[3]).append("km").toString());
        StringBuilder sbAppend2 = new StringBuilder().append(this.mContext.getString(R.string.driven_distance)).append(":");
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(sbAppend2.append((iArr2[5] * 256) + iArr2[6]).append("km").toString());
        StringBuilder sbAppend3 = new StringBuilder().append(this.mContext.getString(R.string.zero_emission_mileage)).append(":");
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(sbAppend3.append((iArr3[7] * 256) + iArr3[8]).append("km").toString());
        arrayList.add(this.mContext.getString(R.string.zero_emission_ratio) + ":" + this.mCanBusInfoInt[9] + "%");
        if (arrayList.size() != 0) {
            GeneralHybirdData.valueList = arrayList;
        }
        Bundle bundle = new Bundle();
        bundle.putString("head", Integer.toHexString(24));
        updateMqbHybirdActivity(bundle);
    }

    private void updateMqbHybirdActivity(Bundle bundle) {
        if (getActivity() != null && (getActivity() instanceof MqbHybirdActivity)) {
            updateActivity(bundle);
        }
    }

    private void setCarNumber() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = this.mCanBusInfoByte;
        arrayList.add(new DriverUpdateEntity(3, 8, new String(Arrays.copyOfRange(bArr, 2, bArr.length))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarDoorInfo() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowBatteryWarning = true;
        GeneralDoorData.isShowFuelWarning = true;
        GeneralDoorData.isShowWashingFluidWarning = true;
        GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        GeneralDoorData.isSeatBeltTie = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
        GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        if (isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_battery_warning", GeneralDoorData.isBatteryWarning);
            SharePreUtil.setBoolValue(this.mContext, "is_seatbelt_tie", GeneralDoorData.isSeatBeltTie);
            SharePreUtil.setBoolValue(this.mContext, "is_washing_fluid_warning", GeneralDoorData.isWashingFluidWarning);
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
            updateDoorView(this.mContext);
        }
    }

    private void setOffRoadData() {
        String str = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]) ? "open" : "close";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 9, str));
        arrayList.add(new SettingUpdateEntity(6, 10, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarStatusInfo() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[8] = blockBit(iArr[8], 6);
        int[] iArr2 = this.carStateIntArray;
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr2 == iArr3) {
            return;
        }
        this.carStateIntArray = iArr3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6] + "L/100km"));
        arrayList.add(new DriverUpdateEntity(3, 2, this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11] + "V"));
        arrayList.add(new DriverUpdateEntity(3, 3, this.mCanBusInfoInt[9] + "L"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarDetailedInfo() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 0, sb.append((iArr[10] * 256) + iArr[11]).append("RPM").toString()));
        arrayList.add(new DriverUpdateEntity(3, 4, (this.mCanBusInfoInt[8] - 40) + "℃"));
        arrayList.add(new DriverUpdateEntity(3, 5, (this.mCanBusInfoInt[9] - 40) + "℃"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarLightInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(3, 6, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? this.mContext.getResources().getString(R.string.open) : this.mContext.getResources().getString(R.string.close)));
        arrayList.add(new DriverUpdateEntity(3, 7, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]) ? this.mContext.getResources().getString(R.string.open) : this.mContext.getResources().getString(R.string.close)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTypeDataInfo() {
        ArrayList arrayList = new ArrayList();
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        String tisWarmMsg = getTisWarmMsg(boolBit7 ? 1 : 0);
        int[] iArr = this.mCanBusInfoInt;
        String tirePressure = getTirePressure(iArr[12], iArr[13]);
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(0, tisWarmMsg, tirePressure, getTirePressure(iArr2[4], iArr2[5])));
        String tisWarmMsg2 = getTisWarmMsg(boolBit6 ? 1 : 0);
        int[] iArr3 = this.mCanBusInfoInt;
        String tirePressure2 = getTirePressure(iArr3[14], iArr3[15]);
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(1, tisWarmMsg2, tirePressure2, getTirePressure(iArr4[6], iArr4[7])));
        String tisWarmMsg3 = getTisWarmMsg(boolBit5 ? 1 : 0);
        int[] iArr5 = this.mCanBusInfoInt;
        String tirePressure3 = getTirePressure(iArr5[16], iArr5[17]);
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(2, tisWarmMsg3, tirePressure3, getTirePressure(iArr6[8], iArr6[9])));
        String tisWarmMsg4 = getTisWarmMsg(boolBit4 ? 1 : 0);
        int[] iArr7 = this.mCanBusInfoInt;
        String tirePressure4 = getTirePressure(iArr7[18], iArr7[19]);
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(3, tisWarmMsg4, tirePressure4, getTirePressure(iArr8[10], iArr8[11])));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
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

    public void syncTime() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._206.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new SetTimeView().showDialog(MsgMgr.this.getActivity(), MsgMgr.this.getActivity().getString(R.string._206_new_function3), true, true, true, true, true, new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._206.MsgMgr.2.1
                    @Override // com.hzbhd.canbus.util.SetTimeView.TimeResultListener
                    public void result(int i, int i2, int i3, int i4, int i5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -53, 1, (byte) i4, (byte) i5, 0, 0, MsgMgr.this.isFormat24H ? (byte) 1 : (byte) 0, (byte) (i - 2000), (byte) i2, (byte) i3, (byte) MsgMgr.this.systemDateFormat});
                        Toast.makeText(MsgMgr.this.getActivity(), i + "-" + i2 + "-" + i3 + "  " + i4 + ":" + i5 + "(" + (MsgMgr.this.isFormat24H ? "24H" : "12H") + ")", 0).show();
                    }
                });
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        this.isGpsTime = z3;
        this.bHours = i5;
        this.bMins = i6;
        this.isFormat24H = z;
        this.bYear2Dig = i2;
        this.bMonth = i3;
        this.bDay = i4;
        this.systemDateFormat = i9;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -107}, DataHandleUtils.makeBytesFixedLength(new byte[]{0}, 25)));
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -106}, DataHandleUtils.makeBytesFixedLength(new byte[]{0}, 25)));
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
        MyLog.temporaryTracking("时间数据：" + i);
        byte[] bArr3 = {22, -106, 0};
        int i2 = i / 1000;
        byte[] bytes2 = new byte[0];
        try {
            bytes2 = ((i2 / 60) + ":" + (i2 % 60)).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(bArr3, bytes2), 27));
    }

    private String resolveLeftAndRightTemp(int i) {
        if (254 == i) {
            return "LO";
        }
        if (255 == i) {
            return "HI";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return (i * 0.5f) + getTempUnitC(this.mContext);
        }
        return ((int) (i * 0.5f)) + getTempUnitF(this.mContext);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_battery_warning", false) == GeneralDoorData.isBatteryWarning && SharePreUtil.getBoolValue(this.mContext, "is_seatbelt_tie", false) == GeneralDoorData.isSeatBeltTie && SharePreUtil.getBoolValue(this.mContext, "is_washing_fluid_warning", false) == GeneralDoorData.isWashingFluidWarning && SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) == GeneralDoorData.isFrontOpen) ? false : true;
    }

    private boolean isOnlyRearAirDataChange() {
        return (SharePreUtil.getFloatValue(this.mContext, "is_rear_air_temp_change_mqb", 0.0f) == rearTemp && SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_lv_change", 0) == GeneralAirData.rear_wind_level && SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_blow", 0) == rearWindBlow && SharePreUtil.getBoolValue(this.mContext, "is_rear_air_power", false) == GeneralAirData.rear_power && SharePreUtil.getBoolValue(this.mContext, "is_rear_auto", false) == GeneralAirData.rear_auto && SharePreUtil.getIntValue(this.mContext, "is_rear_left_heat", 0) == GeneralAirData.rear_left_seat_heat_level && SharePreUtil.getIntValue(this.mContext, "is_rear_right_heat", 0) == GeneralAirData.rear_right_seat_heat_level) ? false : true;
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy)) {
            return true;
        }
        this.mCanbusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
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

    private boolean isSwc0x0cReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwc0x0cInfoCopy)) {
            return true;
        }
        this.mCanBusSwc0x0cInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isSwc0x0dReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwc0x0dInfoCopy)) {
            return true;
        }
        this.mCanBusSwc0x0dInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!settingFirstMsg) {
            return false;
        }
        settingFirstMsg = false;
        return true;
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

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }
}
