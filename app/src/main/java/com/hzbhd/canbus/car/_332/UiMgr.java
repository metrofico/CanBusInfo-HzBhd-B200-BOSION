package com.hzbhd.canbus.car._332;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.MyLog;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private static int fullScreenState;
    Context mContext;
    MsgMgr mMsgMgr;
    int bWeek = 1;
    int bStartHour = 0;
    int bStartMinute = 0;
    int bPercentage = 1;
    int tsWeek = 1;
    int tsStartHour = 0;
    int tsStartMinute = 0;
    int tsEndHour = 0;
    int tsEndMinute = 0;
    DecimalFormat df2 = new DecimalFormat("00");
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._332.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_332_setting_1")) {
                switch (i2) {
                    case 0:
                        UiMgr.this.sendSettingsMsg(1, i3);
                        break;
                    case 1:
                        UiMgr.this.sendSettingsMsg(2, i3);
                        break;
                    case 2:
                        UiMgr.this.sendSettingsMsg(3, i3);
                        break;
                    case 3:
                        UiMgr.this.sendSettingsMsg(4, i3);
                        break;
                    case 4:
                        UiMgr.this.sendSettingsMsg(5, i3);
                        break;
                    case 5:
                        UiMgr.this.sendSettingsMsg(6, i3);
                        break;
                    case 6:
                        UiMgr.this.sendSettingsMsg(7, i3);
                        break;
                    case 7:
                        UiMgr.this.sendSettingsMsg(8, i3);
                        break;
                    case 8:
                        UiMgr.this.sendSettingsMsg(9, i3);
                        break;
                    case 10:
                        UiMgr.this.sendSettingsMsg(11, i3);
                        break;
                }
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getLeftIndexes(uiMgr2.mContext, "_327_360_camera") && i2 == 0) {
                if (i3 == 1) {
                    UiMgr.this.sendPanoramicMsg(10);
                } else {
                    UiMgr.this.sendPanoramicMsg(0);
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getLeftIndexes(uiMgr3.mContext, "_332_charge") && i2 == 0) {
                UiMgr uiMgr4 = UiMgr.this;
                uiMgr4.sendChargeData(uiMgr4.getChargeMode(i3));
                UiMgr uiMgr5 = UiMgr.this;
                uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(i, i2, i3);
            }
            UiMgr uiMgr6 = UiMgr.this;
            if (i == uiMgr6.getLeftIndexes(uiMgr6.mContext, "_332_tachograph_settings_2")) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 != 5) {
                                    if (i2 == 6) {
                                        if (i3 == 0) {
                                            UiMgr.this.sendVideoState(0);
                                            UiMgr.this.sendVideoState(14);
                                            UiMgr uiMgr7 = UiMgr.this;
                                            uiMgr7.getMsgMgr(uiMgr7.mContext).updateSettings(i, i2, 0);
                                        } else {
                                            UiMgr.this.sendVideoState(0);
                                            UiMgr.this.sendVideoState(15);
                                            UiMgr uiMgr8 = UiMgr.this;
                                            uiMgr8.getMsgMgr(uiMgr8.mContext).updateSettings(i, i2, 1);
                                        }
                                    }
                                } else if (i3 == 0) {
                                    UiMgr.this.sendVideoState(0);
                                    UiMgr.this.sendVideoState(11);
                                    UiMgr uiMgr9 = UiMgr.this;
                                    uiMgr9.getMsgMgr(uiMgr9.mContext).updateSettings(i, i2, 0);
                                } else {
                                    UiMgr.this.sendVideoState(0);
                                    UiMgr.this.sendVideoState(12);
                                    UiMgr uiMgr10 = UiMgr.this;
                                    uiMgr10.getMsgMgr(uiMgr10.mContext).updateSettings(i, i2, 1);
                                }
                            } else if (i3 == 0) {
                                UiMgr.this.sendVideoState(0);
                                UiMgr.this.sendVideoState(7);
                                UiMgr uiMgr11 = UiMgr.this;
                                uiMgr11.getMsgMgr(uiMgr11.mContext).updateSettings(i, i2, 0);
                            } else if (i3 == 1) {
                                UiMgr.this.sendVideoState(0);
                                UiMgr.this.sendVideoState(8);
                                UiMgr uiMgr12 = UiMgr.this;
                                uiMgr12.getMsgMgr(uiMgr12.mContext).updateSettings(i, i2, 1);
                            } else {
                                UiMgr.this.sendVideoState(0);
                                UiMgr.this.sendVideoState(9);
                                UiMgr uiMgr13 = UiMgr.this;
                                uiMgr13.getMsgMgr(uiMgr13.mContext).updateSettings(i, i2, 2);
                            }
                        } else if (i3 == 0) {
                            UiMgr.this.sendVideoState(0);
                            UiMgr.this.sendVideoState(5);
                            UiMgr uiMgr14 = UiMgr.this;
                            uiMgr14.getMsgMgr(uiMgr14.mContext).updateSettings(i, i2, 0);
                        } else {
                            UiMgr.this.sendVideoState(0);
                            UiMgr.this.sendVideoState(6);
                            UiMgr uiMgr15 = UiMgr.this;
                            uiMgr15.getMsgMgr(uiMgr15.mContext).updateSettings(i, i2, 1);
                        }
                    } else if (i3 == 0) {
                        UiMgr.this.sendVideoState(0);
                        UiMgr.this.sendVideoState(3);
                        UiMgr uiMgr16 = UiMgr.this;
                        uiMgr16.getMsgMgr(uiMgr16.mContext).updateSettings(i, i2, 0);
                    } else {
                        UiMgr.this.sendVideoState(0);
                        UiMgr.this.sendVideoState(4);
                        UiMgr uiMgr17 = UiMgr.this;
                        uiMgr17.getMsgMgr(uiMgr17.mContext).updateSettings(i, i2, 1);
                    }
                } else if (i3 == 0) {
                    UiMgr.this.sendTime();
                    UiMgr.this.sendVideoState(0);
                    UiMgr.this.sendVideoState(1);
                    UiMgr uiMgr18 = UiMgr.this;
                    uiMgr18.getMsgMgr(uiMgr18.mContext).updateSettings(i, i2, 0);
                    UiMgr.this.sendTime();
                } else {
                    UiMgr.this.sendTime();
                    UiMgr.this.sendVideoState(0);
                    UiMgr.this.sendVideoState(2);
                    UiMgr uiMgr19 = UiMgr.this;
                    uiMgr19.getMsgMgr(uiMgr19.mContext).updateSettings(i, i2, 1);
                    UiMgr.this.sendTime();
                }
            }
            UiMgr uiMgr20 = UiMgr.this;
            if (i == uiMgr20.getLeftIndexes(uiMgr20.mContext, "_332_tachograph_settings_4")) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i2 == 4) {
                                    if (i3 == 0) {
                                        UiMgr.this.sendTime();
                                        UiMgr.this.sendPlayBackState(0);
                                        UiMgr.this.sendPlayBackState(9);
                                        UiMgr uiMgr21 = UiMgr.this;
                                        uiMgr21.getMsgMgr(uiMgr21.mContext).updateSettings(i, i2, 0);
                                        UiMgr.this.sendTime();
                                    } else {
                                        UiMgr.this.sendTime();
                                        UiMgr.this.sendPlayBackState(0);
                                        UiMgr.this.sendPlayBackState(10);
                                        UiMgr uiMgr22 = UiMgr.this;
                                        uiMgr22.getMsgMgr(uiMgr22.mContext).updateSettings(i, i2, 1);
                                        UiMgr.this.sendTime();
                                    }
                                }
                            } else if (i3 == 0) {
                                UiMgr.this.sendPlayBackState(0);
                                UiMgr.this.sendPlayBackState(7);
                                UiMgr uiMgr23 = UiMgr.this;
                                uiMgr23.getMsgMgr(uiMgr23.mContext).updateSettings(i, i2, 0);
                            } else {
                                UiMgr.this.sendPlayBackState(0);
                                UiMgr.this.sendPlayBackState(8);
                                UiMgr uiMgr24 = UiMgr.this;
                                uiMgr24.getMsgMgr(uiMgr24.mContext).updateSettings(i, i2, 1);
                            }
                        } else if (i3 == 0) {
                            UiMgr.this.sendPlayBackState(0);
                            UiMgr.this.sendPlayBackState(5);
                            UiMgr uiMgr25 = UiMgr.this;
                            uiMgr25.getMsgMgr(uiMgr25.mContext).updateSettings(i, i2, 0);
                        } else {
                            UiMgr.this.sendPlayBackState(0);
                            UiMgr.this.sendPlayBackState(6);
                            UiMgr uiMgr26 = UiMgr.this;
                            uiMgr26.getMsgMgr(uiMgr26.mContext).updateSettings(i, i2, 1);
                        }
                    } else if (i3 == 0) {
                        UiMgr.this.sendPlayBackState(0);
                        UiMgr.this.sendPlayBackState(4);
                        UiMgr uiMgr27 = UiMgr.this;
                        uiMgr27.getMsgMgr(uiMgr27.mContext).updateSettings(i, i2, 0);
                    } else {
                        UiMgr.this.sendPlayBackState(0);
                        UiMgr.this.sendPlayBackState(3);
                        UiMgr uiMgr28 = UiMgr.this;
                        uiMgr28.getMsgMgr(uiMgr28.mContext).updateSettings(i, i2, 1);
                    }
                } else if (i3 == 0) {
                    UiMgr.this.sendTime();
                    UiMgr.this.sendPlayBackState(0);
                    UiMgr.this.sendPlayBackState(2);
                    UiMgr uiMgr29 = UiMgr.this;
                    uiMgr29.getMsgMgr(uiMgr29.mContext).updateSettings(i, i2, 0);
                    UiMgr.this.sendTime();
                } else {
                    UiMgr.this.sendTime();
                    UiMgr.this.sendPlayBackState(0);
                    UiMgr.this.sendPlayBackState(1);
                    UiMgr uiMgr30 = UiMgr.this;
                    uiMgr30.getMsgMgr(uiMgr30.mContext).updateSettings(i, i2, 1);
                    UiMgr.this.sendTime();
                }
            }
            UiMgr uiMgr31 = UiMgr.this;
            if (i == uiMgr31.getLeftIndexes(uiMgr31.mContext, "_332_Timed_charging_1") && i2 == 0) {
                switch (i3) {
                    case 0:
                        UiMgr.this.bWeek = 1;
                        break;
                    case 1:
                        UiMgr.this.bWeek = 2;
                        break;
                    case 2:
                        UiMgr.this.bWeek = 4;
                        break;
                    case 3:
                        UiMgr.this.bWeek = 8;
                        break;
                    case 4:
                        UiMgr.this.bWeek = 16;
                        break;
                    case 5:
                        UiMgr.this.bWeek = 32;
                        break;
                    case 6:
                        UiMgr.this.bWeek = 64;
                        break;
                }
                UiMgr uiMgr32 = UiMgr.this;
                uiMgr32.getMsgMgr(uiMgr32.mContext).updateSettings(i, i2, i3);
            }
            UiMgr uiMgr33 = UiMgr.this;
            if (i == uiMgr33.getLeftIndexes(uiMgr33.mContext, "_332_Timed_charging_2") && i2 == 0) {
                switch (i3) {
                    case 0:
                        UiMgr.this.tsWeek = 1;
                        break;
                    case 1:
                        UiMgr.this.tsWeek = 2;
                        break;
                    case 2:
                        UiMgr.this.tsWeek = 4;
                        break;
                    case 3:
                        UiMgr.this.tsWeek = 8;
                        break;
                    case 4:
                        UiMgr.this.tsWeek = 16;
                        break;
                    case 5:
                        UiMgr.this.tsWeek = 32;
                        break;
                    case 6:
                        UiMgr.this.tsWeek = 64;
                        break;
                }
                UiMgr uiMgr34 = UiMgr.this;
                uiMgr34.getMsgMgr(uiMgr34.mContext).updateSettings(i, i2, i3);
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._332.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_332_setting_1") && i2 == 9) {
                UiMgr.this.sendSettingsMsg(10, i3);
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getLeftIndexes(uiMgr2.mContext, "_332_Timed_charging_1")) {
                if (i2 == 1) {
                    UiMgr.this.bStartHour = i3;
                } else if (i2 == 2) {
                    UiMgr.this.bStartMinute = i3;
                } else if (i2 == 3) {
                    UiMgr.this.bPercentage = i3;
                }
                UiMgr uiMgr3 = UiMgr.this;
                uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(i, i2, i3);
            }
            UiMgr uiMgr4 = UiMgr.this;
            if (i == uiMgr4.getLeftIndexes(uiMgr4.mContext, "_332_Timed_charging_2")) {
                if (i2 == 1) {
                    UiMgr.this.tsStartHour = i3;
                } else if (i2 == 2) {
                    UiMgr.this.tsStartMinute = i3;
                } else if (i2 == 3) {
                    UiMgr.this.tsEndHour = i3;
                } else if (i2 == 4) {
                    UiMgr.this.tsEndMinute = i3;
                }
                UiMgr uiMgr5 = UiMgr.this;
                uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(i, i2, i3);
            }
        }
    };
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._332.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_327_360_camera") && i2 == 1) {
                Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getString(R.string._332_get_camera_state), 0).show();
                UiMgr.this.sendPanoramicMsg(9);
                UiMgr.this.sendPanoramicMsg(10);
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (uiMgr2.getLeftIndexes(uiMgr2.mContext, "_332_tachograph_settings_3") == i && i2 == 0) {
                UiMgr.this.sendPhotoSettingState(0);
                UiMgr.this.sendPhotoSettingState(1);
                Toast.makeText(UiMgr.this.mContext, "拍照成功", 0).show();
            }
        }
    };
    OnConfirmDialogListener onConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._332.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getLeftIndexes(uiMgr.mContext, "_332_tachograph_settings_2")) {
                if (i2 == 4) {
                    UiMgr.this.sendVideoState(0);
                    UiMgr.this.sendVideoState(10);
                    Toast.makeText(UiMgr.this.mContext, "格式化成功", 0).show();
                } else if (i2 == 7) {
                    UiMgr.this.sendVideoState(0);
                    UiMgr.this.sendVideoState(15);
                    Toast.makeText(UiMgr.this.mContext, "恢复出厂设置成功", 0).show();
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(i, 0, 0);
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(i, 1, 0);
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.getMsgMgr(uiMgr4.mContext).updateSettings(i, 2, 0);
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(i, 3, 0);
                    UiMgr uiMgr6 = UiMgr.this;
                    uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettings(i, 5, 0);
                    UiMgr uiMgr7 = UiMgr.this;
                    uiMgr7.getMsgMgr(uiMgr7.mContext).updateSettings(i, 6, 0);
                }
            }
            UiMgr uiMgr8 = UiMgr.this;
            if (i == uiMgr8.getLeftIndexes(uiMgr8.mContext, "_332_Timed_charging_1") && i2 == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -122, 1, (byte) UiMgr.this.bWeek, (byte) UiMgr.this.bStartHour, (byte) UiMgr.this.bStartMinute, (byte) UiMgr.this.bPercentage});
                Context context = UiMgr.this.mContext;
                StringBuilder sbAppend = new StringBuilder().append("定时成功！");
                UiMgr uiMgr9 = UiMgr.this;
                Toast.makeText(context, sbAppend.append(uiMgr9.getWeek(uiMgr9.bWeek)).append(UiMgr.this.df2.format(UiMgr.this.bStartHour)).append(":").append(UiMgr.this.df2.format(UiMgr.this.bStartMinute)).append(" 百分比：").append(UiMgr.this.bPercentage).append("%").toString(), 0).show();
            }
            UiMgr uiMgr10 = UiMgr.this;
            if (i == uiMgr10.getLeftIndexes(uiMgr10.mContext, "_332_Timed_charging_2") && i2 == 5) {
                if (Integer.parseInt(UiMgr.this.tsStartHour + "" + UiMgr.this.tsStartMinute) < Integer.parseInt(UiMgr.this.tsEndHour + "" + UiMgr.this.tsEndMinute)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 2, (byte) UiMgr.this.tsWeek, (byte) UiMgr.this.tsStartHour, (byte) UiMgr.this.tsStartHour, (byte) UiMgr.this.tsEndHour, (byte) UiMgr.this.tsEndMinute});
                    Context context2 = UiMgr.this.mContext;
                    StringBuilder sbAppend2 = new StringBuilder().append("定时成功！");
                    UiMgr uiMgr11 = UiMgr.this;
                    Toast.makeText(context2, sbAppend2.append(uiMgr11.getWeek(uiMgr11.tsWeek)).append(UiMgr.this.df2.format(UiMgr.this.tsStartHour)).append(":").append(UiMgr.this.df2.format(UiMgr.this.tsStartMinute)).append("至").append(UiMgr.this.df2.format(UiMgr.this.tsEndHour)).append(":").append(UiMgr.this.df2.format(UiMgr.this.tsEndMinute)).toString(), 0).show();
                } else {
                    Toast.makeText(UiMgr.this.mContext, "警告：结束时间必须在开始时间之后", 0).show();
                }
            }
            UiMgr uiMgr12 = UiMgr.this;
            if (i == uiMgr12.getLeftIndexes(uiMgr12.mContext, "_332_Timed_charging_3") && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, (byte) UiMgr.this.getNowWeekDay(), (byte) UiMgr.this.getNowHourTime(), (byte) (UiMgr.this.getNowMinuteTime() + 1)});
                Toast.makeText(UiMgr.this.mContext, "已开始充电！", 0).show();
            }
        }
    };
    OnPanoramicItemClickListener panoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._332.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            switch (i) {
                case 0:
                    UiMgr.this.sendPanoramicMsg(10);
                    break;
                case 1:
                    UiMgr.this.sendPanoramicMsg(5);
                    break;
                case 2:
                    UiMgr.this.sendPanoramicMsg(6);
                    break;
                case 3:
                    UiMgr.this.sendPanoramicMsg(7);
                    break;
                case 4:
                    UiMgr.this.sendPanoramicMsg(8);
                    break;
                case 5:
                    UiMgr.this.sendPanoramicMsg(2);
                    break;
                case 6:
                    UiMgr.this.sendPanoramicMsg(1);
                    break;
                case 7:
                    if (UiMgr.fullScreenState == 0) {
                        int unused = UiMgr.fullScreenState = 1;
                        UiMgr.this.sendPanoramicMsg(3);
                        break;
                    } else {
                        int unused2 = UiMgr.fullScreenState = 0;
                        UiMgr.this.sendPanoramicMsg(4);
                        break;
                    }
                case 8:
                    UiMgr.this.sendPanoramicMsg(0);
                    break;
            }
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    /* JADX INFO: Access modifiers changed from: private */
    public int getChargeMode(int i) {
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 1 : 8;
        }
        return 4;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        initUi();
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        settingUiSet.setOnSettingConfirmDialogListener(this.onConfirmDialogListener);
        getParkPageUiSet(context).setOnPanoramicItemClickListener(this.panoramicItemClickListener);
    }

    private void initUi() {
        if (this.eachId != 7) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_332_setting_1", "_327_360_camera"});
            getParkPageUiSet(this.mContext).setShowPanoramic(false);
        }
        if (this.differentId != 4) {
            removeDriveDataPagesByHeadTitles(this.mContext, "_332_driverData_1", "_332_Energy_information");
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_332_charge", "_332_Timed_charging_1", "_332_Timed_charging_2", "_332_Timed_charging_3"});
        }
    }

    protected int getLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSettingsMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPanoramicMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    public int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    public int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendVideoState(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPhotoSettingState(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPlayBackState(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, (byte) i});
    }

    public void sendTime() {
        String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        int i = Integer.parseInt(str.substring(0, 4)) - 2000;
        int i2 = Integer.parseInt(str.substring(4, 6));
        int i3 = Integer.parseInt(str.substring(6, 8));
        int i4 = Integer.parseInt(str.substring(8, 10));
        int i5 = Integer.parseInt(str.substring(10, 12));
        int i6 = Integer.parseInt(str.substring(12, 14));
        MyLog.d("行车记录仪时间信息", i + ":" + i2 + ":" + i3 + ":" + i4 + ":" + i5 + ":" + i6);
        CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    public void disConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendChargeData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getWeek(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._332_Timed_charging_9);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._332_Timed_charging_10);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._332_Timed_charging_11);
        }
        if (i == 8) {
            return this.mContext.getString(R.string._332_Timed_charging_12);
        }
        if (i == 16) {
            return this.mContext.getString(R.string._332_Timed_charging_13);
        }
        if (i == 32) {
            return this.mContext.getString(R.string._332_Timed_charging_14);
        }
        return this.mContext.getString(R.string._332_Timed_charging_15);
    }

    public int getNowWeekDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String strValueOf = String.valueOf(calendar.get(7));
        if ("1".equals(strValueOf)) {
            return 64;
        }
        if ("7".equals(strValueOf)) {
            return 32;
        }
        if ("3".equals(strValueOf)) {
            return 2;
        }
        if ("4".equals(strValueOf)) {
            return 4;
        }
        if ("5".equals(strValueOf)) {
            return 8;
        }
        return "6".equals(strValueOf) ? 16 : 1;
    }

    public int getNowHourTime() {
        return Integer.parseInt(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).substring(8, 10));
    }

    public int getNowMinuteTime() {
        return Integer.parseInt(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).substring(10, 12));
    }

    public void sendNowTime(int i, int i2, int i3, int i4, int i5) {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5});
    }

    public void sendMediaSourcesNoSRC() {
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 0, 0, 0, 0, 0, 0, 0, (byte) getVolLevel()});
        }
    }

    public void sendMediaSourcesRadio(String str) {
        if (getEachId() == 6) {
            if (str.contains("AM")) {
                CanbusMsgSender.sendMsg(new byte[]{22, 117, 1, 3, 0, 0, 0, 0, 0, (byte) getVolLevel()});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 117, 1, 1, 0, 0, 0, 0, 0, (byte) getVolLevel()});
            }
        }
    }

    public void sendMediaSourcesCD(int i, int i2) {
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 2, 0, 0, 0, 0, (byte) i, (byte) i2, (byte) getVolLevel()});
        }
    }

    public void sendMediaSourcesUsb(int i, int i2) {
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 3, 0, 0, 0, 0, (byte) i, (byte) i2, (byte) getVolLevel()});
        }
    }

    public void sendMediaSourcesMusic(int i, int i2) {
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 4, 0, 0, 0, 0, (byte) i, (byte) i2, (byte) getVolLevel()});
        }
    }

    public void sendMediaSourcesAux() {
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 5, 0, 0, 0, 0, 0, 0, (byte) getVolLevel()});
        }
    }

    public void sendMediaSourcesBluetooth() {
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 6, 0, 0, 0, 0, 0, 0, (byte) getVolLevel()});
        }
    }

    public void sendMediaSourcesPower() {
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, 117, 7, 0, 0, 0, 0, 0, 0, (byte) getVolLevel()});
        }
    }

    public void sendCarType() {
        if (getEachId() == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 83, 1});
        } else if (getEachId() == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 83, 2});
        }
    }

    public void sendVoiceControlInfo0x01(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, 1, (byte) i});
    }

    public void sendVoiceControlInfo0x02(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, 2, (byte) i, (byte) i2});
    }

    public void sendVoiceControlInfo0x03To0x3F(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, (byte) i, (byte) i2, (byte) i3});
    }

    public void sendVoiceControlInfoAir(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, (byte) i, (byte) i2});
    }

    public void sendVoiceControlInfoAir(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 125, (byte) i, (byte) i2, (byte) i3});
    }

    private int getVolLevel() {
        int i = MediaShareData.Volume.INSTANCE.getVolume() == 0 ? 2 : 1;
        MyLog.temporaryTracking("获取到的音量状态：" + i);
        if (i == 0) {
            return 1;
        }
        return i;
    }
}
