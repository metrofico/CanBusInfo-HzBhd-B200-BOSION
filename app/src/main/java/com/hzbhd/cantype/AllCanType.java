package com.hzbhd.cantype;

import androidx.core.view.InputDeviceCompat;

import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.car._0.MsgMgr;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import org.apache.log4j.net.SyslogAppender;

import java.util.ArrayList;
import java.util.Arrays;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import nfore.android.bt.res.NfDef;


public final class AllCanType {
    private final ArrayList<Integer> canTypeList = new ArrayList<>(Arrays.asList(0, 3, 4, 30, 68, 94, 118, MpegConstantsDef.MPEG_INFO_DISPLAY_CFM, 433, MpegConstantsDef.MPEG_INFO_DISCTYPE_CFM, SyslogAppender.LOG_LOCAL6, MpegConstantsDef.MPEG_VIDEO_DISP_INFO_IND, 186, Integer.valueOf(HotKeyConstant.K_SPEECH), Integer.valueOf(HotKeyConstant.K_PHONE_ON_OFF), Integer.valueOf(HotKeyConstant.K_PHONE_OFF_RETURN), 194, 220, 236, 237, 238, 239, Integer.valueOf(NfDef.STATE_3WAY_M_HOLD), 241, 242, 243, Integer.valueOf(NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD), 245, 246, 247, 250, 252, 253, 255, 256, 265, 284, 327, 332, 337, 16, 328, 345, 334, 342, 346, 347, 348, 193, 338, 339, 340, 343, 344, 374, 375, Integer.valueOf(HotKeyConstant.K_AIR_WIND_INC), 1, 2, 11, 21, 22, 24, 25, 31, 36, 37, 41, 42, 48, 60, 61, Integer.valueOf(HotKeyConstant.K_6_SHUFFLE), 72, 81, 85, 101, 102, 103, 104, 107, 111, 112, 131, 132, Integer.valueOf(MpegConstantsDef.MPEG_KEYBOARD_STATUS_INT), 156, 173, 174, 175, 178, 205, Integer.valueOf(HotKeyConstant.K_DARK_MODE), 260, 261, 262, 263, Integer.valueOf(NfDef.BT_MODE_CONNECTABLE_DISCOVERABLE), 319, 141, 351, 356, 86, 165, Integer.valueOf(HotKeyConstant.K_AVM), Integer.valueOf(MsgMgr.DVD_MODE), 280, 288, 292, 293, 294, 295, Integer.valueOf(NfDef.BT_STATE_TURNING_ON), 352, 371, 335, Integer.valueOf(HotKeyConstant.K_AIR_WIND_DEC), 349, 353, 358, 370, 372, 23, 28, 29, 56, 57, 58, 59, 97, 121, 122, 123, 143, 144, Integer.valueOf(MpegConstantsDef.MPEG_DIVX_REG_STATUS_INT), Integer.valueOf(MpegConstantsDef.MPEG_COMM_STATUS_REQ), Integer.valueOf(HotKeyConstant.K_CAN_CONFIG), 206, Integer.valueOf(HotKeyConstant.K_NEXT_HANGUP), Integer.valueOf(HotKeyConstant.K_SOS), 210, 211, 213, Integer.valueOf(NfDef.STATE_3WAY_CALL_MULTIPARTY), Integer.valueOf(HotKeyConstant.K_NEWCANBUS_AIR_ACTIVITY), Integer.valueOf(HotKeyConstant.K_BRIGHTNESS_INCREASE), 233, 248, Integer.valueOf(InputDeviceCompat.SOURCE_KEYBOARD), 258, 259, 266, 270, 271, 272, 274, 275, 276, 277, 283, 291, 298, 307, 329, 336, Integer.valueOf(HotKeyConstant.K_VOICE_HANGUP), Integer.valueOf(HotKeyConstant.K_NEWCANBUS_ACTIVITY), 96, 381, 382, 384, 390, 391, 392, 393, 394, 396, 410, 411, 412, 395, 413, 414, 415, 416, 417, 418, 426, 427, 428, 429, 430, 431, 463, Integer.valueOf(HotKeyConstant.K_TTM), 135, 437, Integer.valueOf(MpegConstantsDef.MPEG_INFO_MENU_STATUS_CFM), 181, Integer.valueOf(HotKeyConstant.K_LONG_SIRI), 267, 282, 285, 286, 287, Integer.valueOf(NfDef.BT_STATE_ON), 305, 315, 318, Integer.valueOf(NfDef.BT_SCAN_START), Integer.valueOf(NfDef.BT_SCAN_FINISH), 326, 383, 435, 439, Integer.valueOf(HotKeyConstant.K_1_PICKUP), 445, 455, 461, Integer.valueOf(MpegConstantsDef.MPEG_TEST_NUM_IND), 234, 279, 296, 299, Integer.valueOf(NfDef.BT_STATE_TURNING_OFF), Integer.valueOf(NfDef.BT_MODE_CONNECTABLE), 313, 325, 330, 350, 355, 432, 450, 453, 454, 456, Integer.valueOf(HotKeyConstant.K_MUTE_PHONE_ON_OUT), Integer.valueOf(HotKeyConstant.K_2_HANGUP), 479, Integer.valueOf(HotKeyConstant.K_AIR_TEMP_DOWN), 357, 133, Integer.valueOf(HotKeyConstant.K_SLEEP), Integer.valueOf(HotKeyConstant.K_ANDROIDAUTO), 45, 106, Integer.valueOf(HotKeyConstant.K_TS_RELEASE), 309, Integer.valueOf(NfDef.BT_MODE_NONE), 317, 341, 308, 322, 324, 373, 300, 316, 160, 452, 451, 436, 441, 442, Integer.valueOf(HotKeyConstant.K_4_REPEAT), 446, 458, 460, 447, 448, 434, 438, 443, 444, 459, 440, Integer.valueOf(SystemConstant.CAN_TYPE_PENGLING_JEEP), 273, 289, 290, 304, 331, 462, Integer.valueOf(HotKeyConstant.K_UP_PICKUP), Integer.valueOf(HotKeyConstant.K_DOWN_HANGUP), Integer.valueOf(HotKeyConstant.K_3_SHUFFLE), Integer.valueOf(HotKeyConstant.K_5_REPEAT), 473, 474, 475, Integer.valueOf(MsgMgr.RADIO_MODE), Integer.valueOf(HotKeyConstant.K_THIRD_KUWO), Integer.valueOf(HotKeyConstant.K_AIR_TEMP_UP), 401, 402, 403, 404, 368, 369, 387, 388, 5, Integer.valueOf(HotKeyConstant.K_AIR_AUTO), Integer.valueOf(HotKeyConstant.K_AIR_AC), 18, 281, 314, Integer.valueOf(MpegConstantsDef.MPEG_INFO_SUBTITLE_CFM), 55, 376, 377, 378, 379, Integer.valueOf(SyslogAppender.LOG_LOCAL5), Integer.valueOf(HotKeyConstant.K_AIR_IN_OUT_CYCLE), 380, 204, 385, 386, Integer.valueOf(HotKeyConstant.K_AIR_FRONT_DEFOG), Integer.valueOf(HotKeyConstant.K_AIR_POWER), 389, 397, 398, 399, 400, 419, 420, 421, 405, 406, 407, 422, 423, 424, 408, 409));

    public final ArrayList<Integer> getCanTypeList() {
        return this.canTypeList;
    }
}
