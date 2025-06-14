package com.hzbhd.canbus.car._141;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.ActionDo;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.net.SyslogAppender;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] accInfoInt;
    private int data0xCB1;
    private int data0xCB10;
    private int data0xCB11;
    private int data0xCB12;
    private int data0xCB13;
    private int data0xCB14;
    private int data0xCB15;
    private int data0xCB16;
    private int data0xCB17;
    private int data0xCB18;
    private int data0xCB19;
    private int data0xCB2;
    private int data0xCB20;
    private int data0xCB3;
    private int data0xCB4;
    private int data0xCB5;
    private int data0xCB6;
    private int data0xCB7;
    private int data0xCB8;
    private int data0xCB9;
    private int differentId;
    private int eachId;
    int[] mAirData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    int[] mCarDoorData;
    int[] mCarDoorData2;
    Context mContext;
    int[] mFrontRadarData;
    int[] mRearRadarData;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private int radioData0;
    private int radioData1;
    private int radioData2;
    private int radioData3;
    RedoUtil redoUtil;
    private String unit;
    private int volInfoData0;
    DecimalFormat towDecimal = new DecimalFormat("###0.00");
    DecimalFormat oneDecimal = new DecimalFormat("###0.0");
    DecimalFormat timeFormat = new DecimalFormat("00");
    private String currentSource = DateLayout.NULL_DATE_FORMAT;
    private int airData7 = 0;
    private int airRearKey = 0;
    private int computerInfoTag = 1;

    private int getFuelConsumptionUnit(int i) {
        if (i != 0) {
            return i != 1 ? 2 : 1;
        }
        return 0;
    }

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getRadarDistance(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 5;
        }
        if (i != 3) {
            return i != 4 ? 0 : 10;
        }
        return 7;
    }

    private String isShowSpeedTag(boolean z) {
        return z ? "show" : "Do not show";
    }

    private void setDiagnosticInfo0x3A() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
        getmUigMgr(context);
        UiMgr.makeConnection();
        int i = this.eachId;
        if (i == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 1});
        } else if (i == 17) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 2});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        new AmpUtil().initAmpData(context);
        RedoUtil redoUtil = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car._141.MsgMgr.1
            @Override // com.hzbhd.canbus.interfaces.ActionDo
            public void todo(List<Object> list) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, 1});
                MsgMgr.this.redoUtil.startTimer(1000L);
            }
        });
        this.redoUtil = redoUtil;
        redoUtil.startTimer(1000L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        RedoUtil redoUtil = this.redoUtil;
        if (redoUtil != null) {
            redoUtil.stopTimer();
        }
        RedoUtil redoUtil2 = new RedoUtil(new ActionDo() { // from class: com.hzbhd.canbus.car._141.MsgMgr.2
            @Override // com.hzbhd.canbus.interfaces.ActionDo
            public void todo(List<Object> list) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, 1});
                MsgMgr.this.redoUtil.startTimer(1000L);
            }
        });
        this.redoUtil = redoUtil2;
        redoUtil2.startTimer(1000L);
        getmUigMgr(this.mContext).sendSourceInfo(12, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        super.onAccOff();
        getmUigMgr(this.mContext).sendSourceInfo(0, 0, 0, 0, 0, 0, 0, 0);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getmUigMgr(this.mContext).sendSourceInfo(2, 16, i4, i5, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:蓝牙电话通话中——————————");
        getmUigMgr(this.mContext).sendSourceInfo(5, 64, 0, 0, 0, 0, 0, 0);
        sendPhoneStateInfo(i2, i3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        this.currentSource = str;
        if (str.equals("FM")) {
            Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:进入收音——————————");
            getmUigMgr(this.mContext).sendSourceInfo(1, 0, 0, 0, 0, 0, 0, 0);
            return;
        }
        if (str.equals("VIDEO")) {
            Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:进入视频——————————");
            getmUigMgr(this.mContext).sendSourceInfo(8, 0, 0, 0, 0, 0, 0, 0);
            return;
        }
        if (str.equals("AUX1")) {
            Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:进入AUX——————————");
            getmUigMgr(this.mContext).sendSourceInfo(7, 0, 0, 0, 0, 0, 0, 0);
        } else if (str.equals("BTAUDIO")) {
            Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:进入蓝牙音乐——————————");
            getmUigMgr(this.mContext).sendSourceInfo(11, 0, 0, 0, 0, 0, 0, 0);
        } else if (str.equals("MUSIC")) {
            Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:进入本地音乐——————————");
            getmUigMgr(this.mContext).sendSourceInfo(8, 0, 0, 0, 0, 0, 0, 0);
        } else {
            Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:退出");
            getmUigMgr(this.mContext).sendSourceInfo(12, 0, 0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        if (this.currentSource.equals("AUX1")) {
            getmUigMgr(this.mContext).sendSourceInfo(7, 48, 0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (this.currentSource.equals("MUSIC")) {
            Log.d("5A A5 0C 27 16 C0", "(DeviceMCU.java:68)send msg:音乐信息改变");
            int i4 = i3 + 1;
            getmUigMgr(this.mContext).sendSourceInfo(8, 19, getLsb(i4), getMsb(i4), getLsb(i2), getMsb(i2), 0, 0);
            if (str4 != null) {
                int[] byteArrayToIntArray = getByteArrayToIntArray(str4.getBytes(StandardCharsets.UTF_8));
                int length = byteArrayToIntArray.length;
                MyLog.e("标题:" + str4, "长度:" + length + "");
                if (length > 0) {
                    this.data0xCB1 = byteArrayToIntArray[0];
                } else {
                    this.data0xCB1 = 32;
                }
                if (length > 1) {
                    this.data0xCB2 = byteArrayToIntArray[1];
                } else {
                    this.data0xCB2 = 32;
                }
                if (length > 2) {
                    this.data0xCB3 = byteArrayToIntArray[2];
                } else {
                    this.data0xCB3 = 32;
                }
                if (length > 3) {
                    this.data0xCB4 = byteArrayToIntArray[3];
                } else {
                    this.data0xCB4 = 32;
                }
                if (length > 4) {
                    this.data0xCB5 = byteArrayToIntArray[4];
                } else {
                    this.data0xCB5 = 32;
                }
                if (length > 5) {
                    this.data0xCB6 = byteArrayToIntArray[5];
                } else {
                    this.data0xCB6 = 32;
                }
                if (length > 6) {
                    this.data0xCB7 = byteArrayToIntArray[6];
                } else {
                    this.data0xCB7 = 32;
                }
                if (length > 7) {
                    this.data0xCB8 = byteArrayToIntArray[7];
                } else {
                    this.data0xCB8 = 32;
                }
                if (length > 8) {
                    this.data0xCB9 = byteArrayToIntArray[8];
                } else {
                    this.data0xCB9 = 32;
                }
                if (length > 9) {
                    this.data0xCB10 = byteArrayToIntArray[9];
                } else {
                    this.data0xCB10 = 32;
                }
                if (length > 10) {
                    this.data0xCB11 = byteArrayToIntArray[10];
                } else {
                    this.data0xCB11 = 32;
                }
                if (length > 11) {
                    this.data0xCB12 = byteArrayToIntArray[11];
                } else {
                    this.data0xCB12 = 32;
                }
                if (length > 12) {
                    this.data0xCB13 = byteArrayToIntArray[12];
                } else {
                    this.data0xCB13 = 32;
                }
                if (length > 13) {
                    this.data0xCB14 = byteArrayToIntArray[13];
                } else {
                    this.data0xCB14 = 32;
                }
                if (length > 14) {
                    this.data0xCB15 = byteArrayToIntArray[14];
                } else {
                    this.data0xCB15 = 32;
                }
                if (length > 15) {
                    this.data0xCB16 = byteArrayToIntArray[15];
                } else {
                    this.data0xCB16 = 32;
                }
                if (length > 16) {
                    this.data0xCB17 = byteArrayToIntArray[16];
                } else {
                    this.data0xCB17 = 32;
                }
                if (length > 17) {
                    this.data0xCB18 = byteArrayToIntArray[17];
                } else {
                    this.data0xCB18 = 32;
                }
                if (length > 18) {
                    this.data0xCB19 = byteArrayToIntArray[18];
                } else {
                    this.data0xCB19 = 32;
                }
                if (length > 19) {
                    this.data0xCB20 = byteArrayToIntArray[19];
                } else {
                    this.data0xCB20 = 32;
                }
                getmUigMgr(this.mContext).sendID3Info(2, this.data0xCB1, this.data0xCB2, this.data0xCB3, this.data0xCB4, this.data0xCB5, this.data0xCB6, this.data0xCB7, this.data0xCB8, this.data0xCB9, this.data0xCB10, this.data0xCB11, this.data0xCB12, this.data0xCB13, this.data0xCB14, this.data0xCB15, this.data0xCB16, this.data0xCB17, this.data0xCB18, this.data0xCB19, this.data0xCB20);
            }
            if (str6 == null) {
                return;
            }
            int[] byteArrayToIntArray2 = getByteArrayToIntArray(str6.getBytes(StandardCharsets.UTF_8));
            int length2 = byteArrayToIntArray2.length;
            MyLog.e("歌手:" + str6, "长度:" + length2);
            if (length2 > 0) {
                this.data0xCB1 = byteArrayToIntArray2[0];
            } else {
                this.data0xCB1 = 32;
            }
            if (length2 > 1) {
                this.data0xCB2 = byteArrayToIntArray2[1];
            } else {
                this.data0xCB2 = 32;
            }
            if (length2 > 2) {
                this.data0xCB3 = byteArrayToIntArray2[2];
            } else {
                this.data0xCB3 = 32;
            }
            if (length2 > 3) {
                this.data0xCB4 = byteArrayToIntArray2[3];
            } else {
                this.data0xCB4 = 32;
            }
            if (length2 > 4) {
                this.data0xCB5 = byteArrayToIntArray2[4];
            } else {
                this.data0xCB5 = 32;
            }
            if (length2 > 5) {
                this.data0xCB6 = byteArrayToIntArray2[5];
            } else {
                this.data0xCB6 = 32;
            }
            if (length2 > 6) {
                this.data0xCB7 = byteArrayToIntArray2[6];
            } else {
                this.data0xCB7 = 32;
            }
            if (length2 > 7) {
                this.data0xCB8 = byteArrayToIntArray2[7];
            } else {
                this.data0xCB8 = 32;
            }
            if (length2 > 8) {
                this.data0xCB9 = byteArrayToIntArray2[8];
            } else {
                this.data0xCB9 = 32;
            }
            if (length2 > 9) {
                this.data0xCB10 = byteArrayToIntArray2[9];
            } else {
                this.data0xCB10 = 32;
            }
            if (length2 > 10) {
                this.data0xCB11 = byteArrayToIntArray2[10];
            } else {
                this.data0xCB11 = 32;
            }
            if (length2 > 11) {
                this.data0xCB12 = byteArrayToIntArray2[11];
            } else {
                this.data0xCB12 = 32;
            }
            if (length2 > 12) {
                this.data0xCB13 = byteArrayToIntArray2[12];
            } else {
                this.data0xCB13 = 32;
            }
            if (length2 > 13) {
                this.data0xCB14 = byteArrayToIntArray2[13];
            } else {
                this.data0xCB14 = 32;
            }
            if (length2 > 14) {
                this.data0xCB15 = byteArrayToIntArray2[14];
            } else {
                this.data0xCB15 = 32;
            }
            if (length2 > 15) {
                this.data0xCB16 = byteArrayToIntArray2[15];
            } else {
                this.data0xCB16 = 32;
            }
            if (length2 > 16) {
                this.data0xCB17 = byteArrayToIntArray2[16];
            } else {
                this.data0xCB17 = 32;
            }
            if (length2 > 17) {
                this.data0xCB18 = byteArrayToIntArray2[17];
            } else {
                this.data0xCB18 = 32;
            }
            if (length2 > 18) {
                this.data0xCB19 = byteArrayToIntArray2[18];
            } else {
                this.data0xCB19 = 32;
            }
            if (length2 > 19) {
                this.data0xCB20 = byteArrayToIntArray2[19];
            } else {
                this.data0xCB20 = 32;
            }
            getmUigMgr(this.mContext).sendID3Info(4, this.data0xCB1, this.data0xCB2, this.data0xCB3, this.data0xCB4, this.data0xCB5, this.data0xCB6, this.data0xCB7, this.data0xCB8, this.data0xCB9, this.data0xCB10, this.data0xCB11, this.data0xCB12, this.data0xCB13, this.data0xCB14, this.data0xCB15, this.data0xCB16, this.data0xCB17, this.data0xCB18, this.data0xCB19, this.data0xCB20);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        if (i > 30) {
            i = 30;
        }
        if (z) {
            this.volInfoData0 = 128;
        } else {
            this.volInfoData0 = i;
        }
        getmUigMgr(this.mContext).sendVolInfo(this.volInfoData0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (this.currentSource.equals("FM")) {
            if (str.trim().equals("FM1")) {
                this.radioData0 = 1;
                this.radioData1 = getLsb((int) (Float.parseFloat(str2) * 100.0f));
                this.radioData2 = getMsb((int) (Float.parseFloat(str2) * 100.0f));
            } else if (str.trim().equals("FM2")) {
                this.radioData0 = 2;
                this.radioData1 = getLsb((int) (Float.parseFloat(str2) * 100.0f));
                this.radioData2 = getMsb((int) (Float.parseFloat(str2) * 100.0f));
            } else if (str.trim().equals("FMAST") || str.trim().equals("FM3")) {
                this.radioData0 = 3;
                this.radioData1 = getLsb((int) (Float.parseFloat(str2) * 100.0f));
                this.radioData2 = getMsb((int) (Float.parseFloat(str2) * 100.0f));
            } else if (str.trim().equals("AM1") || str.trim().equals("AM2")) {
                this.radioData0 = 16;
                this.radioData1 = getLsb(Integer.parseInt(str2));
                this.radioData2 = getMsb(Integer.parseInt(str2));
            }
            this.radioData3 = DataHandleUtils.getMsbLsbResult_4bit(15, i);
            getmUigMgr(this.mContext).sendRadioInfo(this.radioData0, this.radioData1, this.radioData2, this.radioData3);
            getmUigMgr(this.mContext).sendSourceInfo(1, 0, 0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        getmUigMgr(this.mContext).sendID3Info(byteArrayToIntArray[0], byteArrayToIntArray[1], byteArrayToIntArray[2], byteArrayToIntArray[3], byteArrayToIntArray[4], byteArrayToIntArray[5], byteArrayToIntArray[6], byteArrayToIntArray[7], byteArrayToIntArray[8], byteArrayToIntArray[9], byteArrayToIntArray[10]);
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit4 = 1;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit2 = 0;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit1 = 1;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit0 = 0;
        getmUigMgr(this.mContext).sendPhoneStateInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit4 = 1;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit2 = 1;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit1 = 0;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit0 = 0;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData2 = i / 3600;
        getmUigMgr(this.mContext);
        int i2 = i % 3600;
        UiMgr.phoneStateData3 = i2 / 60;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData4 = i2 % 60;
        getmUigMgr(this.mContext).sendPhoneStateInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit4 = 1;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit2 = 0;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit1 = 0;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit0 = 1;
        getmUigMgr(this.mContext).sendPhoneStateInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit4 = 1;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit2 = 1;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit1 = 0;
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit0 = 1;
        getmUigMgr(this.mContext).sendPhoneStateInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getmUigMgr(this.mContext).sendSourceInfo(11, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        try {
            setCanInfo();
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setCanInfo() {
        int i = this.mCanBusInfoInt[1];
        if (i == 1) {
            setControlCommand0x01();
            return;
        }
        if (i == 23) {
            setAmplifierInfo0x17();
            return;
        }
        if (i == 68) {
            setEconomicModelInfo0x44();
            return;
        }
        if (i == 74) {
            setDiagnosticInfo0x4A();
            return;
        }
        if (i == 113) {
            setVersionInfo0x71();
            return;
        }
        if (i == 234) {
            setAccInfo0xEA();
            return;
        }
        if (i == 32) {
            setWheelKey0x20();
            return;
        }
        if (i == 33) {
            setAirInfo0x21();
            return;
        }
        if (i == 40) {
            setAutoParking0x28();
            return;
        }
        if (i == 41) {
            setEpsInfo0x29();
            return;
        }
        if (i == 61) {
            setCruiseSpeedLimit0x3D();
            return;
        }
        if (i != 62) {
            switch (i) {
                case 50:
                    setRadarInfo0x32();
                    break;
                case 51:
                    setTripComputerInfo0x33();
                    break;
                case 52:
                    setTripComputerInfo0x34();
                    break;
                case 53:
                    setTripComputerInfo0x35();
                    break;
                case 54:
                    updateOutDoorTemp(this.mContext, getOutdoorTemperature());
                    break;
                case 55:
                    setAlertInfo0x37();
                    break;
                case 56:
                    setCarState0x38();
                    break;
                case 57:
                    setCarFactionState0x39();
                    break;
                case 58:
                    setDiagnosticInfo0x3A();
                    break;
                case 59:
                    setSpeed0x3B();
                    break;
            }
            return;
        }
        setRadarSoundInfo0x3E();
    }

    private void setDiagnosticInfo0x4A() {
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_065)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_066)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_067)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_068)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_069)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_070)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_071)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_072)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_073)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_074)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_075)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_076)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_077)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_078)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_079)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_080)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_081)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_082)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_083)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_084)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_085)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_086)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_087)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_088)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_088)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_090)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_091)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_092)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_093)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_094)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_095)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_096)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_097)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_098)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_099)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_100)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_101)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_102)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_103)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_000)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_new_item_1)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_001)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_002)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_new_item_2)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_003)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_004)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[11])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_005)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_006)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_007)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_008)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[13])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_009)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[13])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_010)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_011)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[14])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_012)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[14])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_013)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[14])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_014)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[14])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_015)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_016)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[15])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_017)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[15])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_018)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[15])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_019)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[15])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_020)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_021)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_022)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_023)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[16])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_024)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[16])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_025)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[16])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_026)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[16])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_027)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[16])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_028)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[17])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_029)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[17])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_030)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[17])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_031)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[17])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_032)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[17])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_033)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[17])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_034)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[18])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_new_item_3)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[18])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_036)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[18])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_037)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[18])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_038)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[18])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_039)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[18])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_040)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[18])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_041)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[19])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_042)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[19])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_043)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[19])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_044)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[19])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_046)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[19])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_045)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[20])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_047)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[20])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_048)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[20])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_049)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[20])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_050)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[20])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_051)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[20])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_052)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[20])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_053)));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_054)));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_055)));
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_056)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_057)));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_058)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_059)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_060)));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[21])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_061)));
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[22])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_062)));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[22])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_063)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[22])) {
            arrayList.add(new WarningEntity(this.mContext.getString(R.string._161_info_064)));
        }
        GeneralWarningDataData.dataList = arrayList;
        updateWarningActivity(null);
    }

    private void setAlertInfo0x37() {
        String str;
        int i;
        String string = this.mContext.getString(R.string._333_driveData_factionState10);
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            str = "  【  " + string + "1：" + this.mContext.getString(R.string._161_warning_0_0) + "】    ";
            i = 2;
        } else {
            str = "";
            i = 1;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_0_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_0_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_0_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_0_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_0_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_0_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_0_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_1_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_2_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_2_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_2_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_2_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_2_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + i + "：" + this.mContext.getString(R.string._161_warning_2_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_2_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_2_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_3_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_3_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_3_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_3_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_3_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_3_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_3_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_4_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_4_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_4_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_4_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_4_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_4_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_4_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_5_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_6_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_7_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_8_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_9_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_10_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_10_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_10_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_10_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_10_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_10_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_10_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12])) {
            str = str + "  【  " + i + "：" + this.mContext.getString(R.string._161_warning_10_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_11_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_2) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_3) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_4) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_5) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_6) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_12_7) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[15])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_13_0) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[15])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_13_1) + "】    ";
            i++;
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[15])) {
            str = str + "  【  " + string + i + "：" + this.mContext.getString(R.string._161_warning_13_2) + "】    ";
        }
        if (!str.equals("")) {
            showDialog(str);
        }
        int[] iArr = this.mCanBusInfoInt;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
        iArr[8] = 0;
        iArr[9] = 0;
        iArr[10] = 0;
        iArr[11] = 0;
        iArr[12] = 0;
        iArr[13] = 0;
        if (isBasicInfoChange2()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[16]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[16]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[16]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[16]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[16]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[16]);
            updateDoorView(this.mContext);
        }
    }

    private void setCarFactionState0x39() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 0, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 1, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 2, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 3, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 4, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 5, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 6, getCarStateMode(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 7, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_driveData_factionState"), 8, getSwitchState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAmplifierInfo0x17() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_amplifier_state_info"), 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 10;
        GeneralAmplifierData.frontRear = -(this.mCanBusInfoInt[3] - 10);
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 3;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[6] - 3;
        updateAmplifierActivity(null);
        AmpUtil.saveAmpUIValue(this.mContext);
    }

    private void setEconomicModelInfo0x44() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 3, getEconomicModelState(this.mCanBusInfoInt[2])).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAccInfo0xEA() {
        if (isAccInfoChange(this.mCanBusInfoInt)) {
            return;
        }
        MyLog.temporaryTracking("ACC状态发生改变");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 0, getAccState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_acc"), 2, Integer.valueOf(this.mCanBusInfoInt[3])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 0) {
            getmUigMgr(this.mContext);
            UiMgr.accState = 0;
        } else {
            getmUigMgr(this.mContext);
            UiMgr.accState = 1;
        }
        getmUigMgr(this.mContext);
        UiMgr.delayTime = this.mCanBusInfoInt[3];
    }

    private void setEpsInfo0x29() {
        if (isTrackInfoChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 5450, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setRadarSoundInfo0x3E() {
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), 3, this.mContext.getString(R.string._333_drivePage_front_radar_state_8)));
        } else {
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), 3, this.mContext.getString(R.string._333_drivePage_front_radar_state_9)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAirInfo0x21() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
            this.unit = "F";
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 0, 1));
        } else {
            this.unit = "C";
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 0, 0));
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2) == 0) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 1, 0));
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2) == 1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 1, 1));
        } else {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_unit_settings"), 1, 2));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        this.mCanBusInfoInt[8] = 0;
        if (isAirDataChange()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            if (this.eachId != 11) {
                GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
                GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
                GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            }
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 1;
            if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
                GeneralAirData.front_left_auto_wind = true;
                if (this.eachId != 11) {
                    GeneralAirData.front_right_auto_wind = true;
                }
            } else {
                GeneralAirData.front_left_auto_wind = false;
                if (this.eachId != 11) {
                    GeneralAirData.front_right_auto_wind = false;
                }
            }
            int i = this.eachId;
            if (i != 11 || i != 14) {
                GeneralAirData.front_left_temperature = getTemperature1(this.mCanBusInfoInt[4], this.unit);
                GeneralAirData.front_right_temperature = getTemperature1(this.mCanBusInfoInt[5], this.unit);
            } else {
                GeneralAirData.front_left_temperature = getTemperature2(this.mCanBusInfoInt[4], this.unit);
                GeneralAirData.front_right_temperature = getTemperature2(this.mCanBusInfoInt[5], this.unit);
            }
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
            if (this.airRearKey != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)) {
                GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
                GeneralAirData.rear_power = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
                this.airRearKey = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1);
                z = true;
            } else {
                z = false;
            }
            if (this.airData7 != this.mCanBusInfoInt[9]) {
                if (this.eachId == 5) {
                    GeneralAirData.rear_left_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 5) + "档";
                    GeneralAirData.rear_right_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 5) + "档";
                    GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3);
                    this.airData7 = this.mCanBusInfoInt[9];
                }
                this.airData7 = this.mCanBusInfoInt[9];
                z = true;
            }
            if (this.eachId == 11) {
                GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
                GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
                GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]);
                if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]) && !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]) && !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10])) {
                    GeneralAirData.front_right_auto_wind = true;
                } else {
                    GeneralAirData.front_right_auto_wind = false;
                }
            }
            if (z) {
                updateAirActivity(this.mContext, 1002);
            } else {
                updateAirActivity(this.mContext, 1001);
            }
        }
    }

    private void setCruiseSpeedLimit0x3D() {
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit7 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit6 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit5 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit4 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit3 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit2 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit1 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeedLimit_Data0Bit0 = 0;
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeed_Data1 = this.mCanBusInfoInt[2];
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeed_Data2 = this.mCanBusInfoInt[3];
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeed_Data3 = this.mCanBusInfoInt[4];
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeed_Data4 = this.mCanBusInfoInt[5];
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeed_Data5 = this.mCanBusInfoInt[6];
        getmUigMgr(this.mContext);
        UiMgr.cruiseSpeed_Data6 = this.mCanBusInfoInt[7];
        getmUigMgr(this.mContext);
        UiMgr.speedLimit_Data1 = this.mCanBusInfoInt[8];
        getmUigMgr(this.mContext);
        UiMgr.speedLimit_Data2 = this.mCanBusInfoInt[9];
        getmUigMgr(this.mContext);
        UiMgr.speedLimit_Data3 = this.mCanBusInfoInt[10];
        getmUigMgr(this.mContext);
        UiMgr.speedLimit_Data4 = this.mCanBusInfoInt[11];
        getmUigMgr(this.mContext);
        UiMgr.speedLimit_Data5 = this.mCanBusInfoInt[12];
        getmUigMgr(this.mContext);
        UiMgr.speedLimit_Data6 = this.mCanBusInfoInt[13];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 0, Integer.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 1, Integer.valueOf(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 2, Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 3, Integer.valueOf(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 4, Integer.valueOf(this.mCanBusInfoInt[6])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_cruise_speed"), 5, Integer.valueOf(this.mCanBusInfoInt[7])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 0, Integer.valueOf(this.mCanBusInfoInt[8])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 1, Integer.valueOf(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 2, Integer.valueOf(this.mCanBusInfoInt[10])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 3, Integer.valueOf(this.mCanBusInfoInt[11])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 4, Integer.valueOf(this.mCanBusInfoInt[12])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_limit"), 5, Integer.valueOf(this.mCanBusInfoInt[13])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSpeed0x3B() {
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data0Bit7 = 0;
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data0Bit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data0Bit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data0Bit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data0Bit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data0Bit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data0Bit1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data1 = this.mCanBusInfoInt[3];
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data2 = this.mCanBusInfoInt[4];
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data3 = this.mCanBusInfoInt[5];
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data4 = this.mCanBusInfoInt[6];
        getmUigMgr(this.mContext);
        UiMgr.speedMemory_data5 = this.mCanBusInfoInt[7];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 0, isShowSpeedTag(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 7, Integer.valueOf(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 8, Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 9, Integer.valueOf(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 10, Integer.valueOf(this.mCanBusInfoInt[6])));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_speed_memory"), 11, Integer.valueOf(this.mCanBusInfoInt[7])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarState0x38() {
        ArrayList arrayList = new ArrayList();
        int leftIndexes = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_carState");
        arrayList.add(new SettingUpdateEntity(leftIndexes, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 1, getFuelState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 3))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 9, getReversingState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 10, getPState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 11, getLittleLampState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 13, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 14, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 15, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 2))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 16, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 17, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 18, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 19, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 20, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 21, Integer.valueOf(getLangrage())));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 22, Integer.valueOf(getFuelConsumptionUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2)))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 23, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 24, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 25, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 26, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1))));
        arrayList.add(new SettingUpdateEntity(leftIndexes, 27, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
        iArr[2] = blockBit(iArr[2], 2);
        int[] iArr2 = this.mCanBusInfoInt;
        iArr2[2] = blockBit(iArr2[2], 0);
        if (isBasicInfoChange()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setTripComputerInfo0x35() {
        getmUigMgr(this.mContext).ComputerInfoData3Bit7 = 0;
        getmUigMgr(this.mContext).ComputerInfoData3Bit5 = 0;
        ArrayList arrayList = new ArrayList();
        int leftIndexes = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.oneDecimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes, 0, sb.append(decimalFormat.format(getMsbLsbResult(iArr[2], iArr[3]) / 10)).append(" L/100Km").toString()).setValueStr(true));
        int leftIndexes2 = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes2, 1, sb2.append(getMsbLsbResult(iArr2[4], iArr2[5])).append(" KM/H").toString()).setValueStr(true));
        int leftIndexes3 = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes3, 2, sb3.append(getMsbLsbResult(iArr3[6], iArr3[7])).append(" KM").toString()).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTripComputerInfo0x34() {
        getmUigMgr(this.mContext).ComputerInfoData3Bit7 = 0;
        getmUigMgr(this.mContext).ComputerInfoData3Bit6 = 0;
        ArrayList arrayList = new ArrayList();
        int leftIndexes = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.oneDecimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes, 0, sb.append(decimalFormat.format(getMsbLsbResult(iArr[2], iArr[3]) / 10)).append(" L/100Km").toString()).setValueStr(true));
        int leftIndexes2 = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes2, 1, sb2.append(getMsbLsbResult(iArr2[4], iArr2[5])).append(" KM/H").toString()).setValueStr(true));
        int leftIndexes3 = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes3, 2, sb3.append(getMsbLsbResult(iArr3[6], iArr3[7])).append(" KM").toString()).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTripComputerInfo0x33() {
        ArrayList arrayList = new ArrayList();
        int leftIndexes = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.oneDecimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes, 0, sb.append(decimalFormat.format(getMsbLsbResult(iArr[2], iArr[3]) / 10)).append(" L/100KM").toString()).setValueStr(true));
        int leftIndexes2 = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes2, 1, sb2.append(getMsbLsbResult(iArr2[4], iArr2[5])).append(" KM").toString()).setValueStr(true));
        int leftIndexes3 = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(leftIndexes3, 2, sb3.append(getMsbLsbResult(iArr3[6], iArr3[7])).append(" KM").toString()).setValueStr(true));
        if (this.eachId == 3) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0"), 3, this.timeFormat.format(this.mCanBusInfoInt[8]) + ":" + this.timeFormat.format(this.mCanBusInfoInt[9]) + ":" + this.timeFormat.format(this.mCanBusInfoInt[10])).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRadarInfo0x32() {
        if (isRearRadarDataChange()) {
            if (this.eachId == 14) {
                noHaveDistanceInfo();
            } else {
                haveDistanceInfo();
            }
        }
    }

    private void haveDistanceInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panoramicSwitch(false);
            return;
        }
        if (i == 1) {
            panoramicSwitch(false);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_drivePage_front_radar_left"), getRadarDistanceStr(this.mCanBusInfoInt[6])));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_drivePage_front_radar_mid"), getRadarDistanceStr(this.mCanBusInfoInt[7])));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_drivePage_front_radar_info"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_drivePage_front_radar_right"), getRadarDistanceStr(this.mCanBusInfoInt[8])));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            panoramicSwitch(false);
            return;
        }
        panoramicSwitch(true);
        if (isFrontRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, getRadarDistance(this.mCanBusInfoInt[3]), getRadarDistance(this.mCanBusInfoInt[4]), getRadarDistance(this.mCanBusInfoInt[4]), getRadarDistance(this.mCanBusInfoInt[5]));
            RadarInfoUtil.setFrontRadarLocationData(10, getRadarDistance(this.mCanBusInfoInt[6]), getRadarDistance(this.mCanBusInfoInt[7]), getRadarDistance(this.mCanBusInfoInt[7]), getRadarDistance(this.mCanBusInfoInt[8]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void noHaveDistanceInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0 || i == 1) {
            panoramicSwitch(false);
        } else if (i == 2) {
            panoramicSwitch(true);
            if (isFrontRadarDataChange()) {
                RadarInfoUtil.mMinIsClose = true;
                RadarInfoUtil.setRearRadarLocationData(10, 0, 0, 0, 0);
                RadarInfoUtil.setFrontRadarLocationData(10, 0, 0, 0, 0);
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                updateParkUi(null, this.mContext);
            }
        } else if (i == 3) {
            panoramicSwitch(false);
        }
        getmUigMgr(this.mContext).getParkPageUiSet(this.mContext).setShowRadar(false);
    }

    private void setAutoParking0x28() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_settings_0"), 0, getParkingBasicState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) + getParkingState(this.mCanBusInfoInt[3])).setValueStr(true));
        if (this.mCanBusInfoInt[2] == 0) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_settings_0"), 1, 0));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setWheelKey0x20() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 2) {
            buttonKey(151);
            return;
        }
        if (i == 48) {
            buttonKey(2);
            return;
        }
        if (i == 80) {
            buttonKey(14);
            return;
        }
        if (i == 7) {
            buttonKey(49);
            return;
        }
        if (i == 8) {
            buttonKey(50);
            return;
        }
        if (i == 129) {
            buttonKey(7);
            return;
        }
        if (i != 130) {
            switch (i) {
                case 17:
                    buttonKey(2);
                    break;
                case 18:
                    buttonKey(46);
                    break;
                case 19:
                    buttonKey(45);
                    break;
                case 20:
                    buttonKey(7);
                    break;
                case 21:
                    buttonKey(8);
                    break;
                case 22:
                    if (this.eachId != 11) {
                        buttonKey(3);
                        break;
                    } else {
                        buttonKey(HotKeyConstant.K_SLEEP);
                        break;
                    }
                case 23:
                    buttonKey(21);
                    break;
                case 24:
                    buttonKey(20);
                    break;
                case 25:
                    if (this.eachId == 4) {
                        buttonKey(7);
                        break;
                    }
                    break;
                case 26:
                    if (this.eachId == 4) {
                        buttonKey(8);
                        break;
                    }
                    break;
                default:
                    switch (i) {
                        case 31:
                            buttonKey(HotKeyConstant.K_SPEECH);
                            break;
                        case 32:
                            if (iArr[3] != 1) {
                                int i2 = this.computerInfoTag;
                                if (i2 != 1) {
                                    if (i2 != 2) {
                                        if (i2 == 3) {
                                            Context context = this.mContext;
                                            startSettingActivity(context, getmUigMgr(context).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_2"), 0);
                                            this.computerInfoTag = 1;
                                            break;
                                        }
                                    } else {
                                        Context context2 = this.mContext;
                                        startSettingActivity(context2, getmUigMgr(context2).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_1"), 0);
                                        this.computerInfoTag = 3;
                                        break;
                                    }
                                } else {
                                    Context context3 = this.mContext;
                                    startSettingActivity(context3, getmUigMgr(context3).getLeftIndexes(this.mContext, "_333_drive_DrivingPage_0"), 0);
                                    this.computerInfoTag = 2;
                                    break;
                                }
                            }
                            break;
                        case 33:
                            buttonKey(52);
                            break;
                        case 34:
                            buttonKey(31);
                            break;
                        case 35:
                            buttonKey(68);
                            break;
                        default:
                            switch (i) {
                                case 145:
                                    buttonKey(33);
                                    break;
                                case 146:
                                    buttonKey(34);
                                    break;
                                case 147:
                                    buttonKey(35);
                                    break;
                                case 148:
                                    buttonKey(36);
                                    break;
                                case 149:
                                    buttonKey(37);
                                    break;
                                case 150:
                                    buttonKey(38);
                                    break;
                                case 151:
                                    buttonKey(21);
                                    break;
                                case 152:
                                    buttonKey(20);
                                    break;
                                case 153:
                                    buttonKey(129);
                                    break;
                                case MpegConstantsDef.MPEG_DIVX_REG_STATUS_INT /* 154 */:
                                    if (iArr[3] != 1) {
                                        adjustBrightness();
                                        break;
                                    }
                                    break;
                                case MpegConstantsDef.MPEG_KEYBOARD_STATUS_INT /* 155 */:
                                    if (this.eachId == 11) {
                                        buttonKey(133);
                                        break;
                                    } else {
                                        buttonKey(151);
                                        break;
                                    }
                                case 156:
                                    buttonKey(45);
                                    break;
                                case MpegConstantsDef.MPEG_INFO_DISPLAY_CFM /* 157 */:
                                    buttonKey(46);
                                    break;
                                case MpegConstantsDef.MPEG_INFO_MENU_STATUS_CFM /* 158 */:
                                    buttonKey(52);
                                    break;
                                case MpegConstantsDef.MPEG_COMM_STATUS_REQ /* 159 */:
                                    buttonKey(HotKeyConstant.K_CAN_CONFIG);
                                    break;
                                case 160:
                                    buttonKey(2);
                                    break;
                                case MpegConstantsDef.MPEG_INFO_DISCTYPE_CFM /* 161 */:
                                    buttonKey(59);
                                    break;
                                case MpegConstantsDef.MPEG_INFO_AUDIO_CFM /* 162 */:
                                    buttonKey(49);
                                    break;
                                case MpegConstantsDef.MPEG_INFO_SUBTITLE_CFM /* 163 */:
                                    buttonKey(50);
                                    break;
                                case MpegConstantsDef.MPEG_INFO_ANGLE_CFM /* 164 */:
                                    buttonKey(3);
                                    break;
                                case 165:
                                    updateAirActivity(this.mContext, 1001);
                                    break;
                                case MpegConstantsDef.MPEG_PASSWORD_CFM /* 166 */:
                                    buttonKey(52);
                                    break;
                                default:
                                    switch (i) {
                                        case SyslogAppender.LOG_LOCAL6 /* 176 */:
                                            buttonKey(HotKeyConstant.K_1_PICKUP);
                                            break;
                                        case MpegConstantsDef.MPEG_VIDEO_DISP_INFO_IND /* 177 */:
                                            buttonKey(HotKeyConstant.K_2_HANGUP);
                                            break;
                                        case 178:
                                            buttonKey(50);
                                            break;
                                        case MpegConstantsDef.MPEG_TEST_NUM_IND /* 179 */:
                                            buttonKey(128);
                                            break;
                                        case NfDef.STATE_CALL_IS_ACTIVE /* 180 */:
                                            buttonKey(128);
                                            break;
                                        case 181:
                                            buttonKey(62);
                                            break;
                                        case HotKeyConstant.K_DISP /* 182 */:
                                            buttonKey(2);
                                            break;
                                        case HotKeyConstant.K_HOUR /* 183 */:
                                            buttonKey(58);
                                            break;
                                        case 184:
                                            buttonKey(128);
                                            break;
                                        case HotKeyConstant.K_CAN_CONFIG /* 185 */:
                                            buttonKey(45);
                                            break;
                                        case 186:
                                            buttonKey(46);
                                            break;
                                        case HotKeyConstant.K_SPEECH /* 187 */:
                                            buttonKey(21);
                                            break;
                                        case HotKeyConstant.K_PHONE_ON_OFF /* 188 */:
                                            buttonKey(20);
                                            break;
                                        case HotKeyConstant.K_PHONE_OFF_RETURN /* 189 */:
                                            buttonKey(49);
                                            break;
                                        case NfDef.STATE_3WAY_OUTGOING_CALL_S_HOLD /* 190 */:
                                            buttonKey(7);
                                            break;
                                        case 191:
                                            buttonKey(8);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        buttonKey(8);
    }

    private void setControlCommand0x01() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_setting_carState"), 33, this.mCanBusInfoInt[4] + "").setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo0x71() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 0, getCarSeries(this.mCanBusInfoInt[2])).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 1, getCarModel(this.mCanBusInfoInt[3])).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 2, ((this.mCanBusInfoInt[4] + SystemConstant.THREAD_SLEEP_TIME_2000) - 6) + "年" + this.mCanBusInfoInt[5] + "月" + this.mCanBusInfoInt[6] + "日").setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_333_drive_page_1"), 3, "V" + this.mCanBusInfoInt[8] + "." + this.mCanBusInfoInt[9] + "." + this.mCanBusInfoInt[10]).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getmUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        if (this.mCanBusInfoInt[3] == 1) {
            realKeyClick4(this.mContext, i);
        }
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    public void panoramicSwitch(boolean z) {
        forceReverse(this.mContext, z);
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

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(Context context, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, "EachCanId" + getCurrentEachCanId() + "L" + i + "R" + i2, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getCarSeries(int i) {
        if (i == 10) {
            return this.mContext.getString(R.string._333_new_string1);
        }
        return this.mContext.getString(R.string._333_new_string2);
    }

    private String getCarModel(int i) {
        if (i == 5) {
            return this.mContext.getString(R.string._333_new_string3);
        }
        return this.mContext.getString(R.string._333_new_string4);
    }

    private String getParkingBasicState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._333_parking_Basic_state_2);
        }
        return this.mContext.getString(R.string._333_parking_Basic_state_1);
    }

    private String getParkingState(int i) {
        switch (i) {
            case 1:
                return this.mContext.getString(R.string._333_parking_state_1);
            case 2:
                return this.mContext.getString(R.string._333_parking_state_2);
            case 3:
                return this.mContext.getString(R.string._333_parking_state_3);
            case 4:
                return this.mContext.getString(R.string._333_parking_state_4);
            case 5:
                return this.mContext.getString(R.string._333_parking_state_5);
            case 6:
                return this.mContext.getString(R.string._333_parking_state_6);
            case 7:
                return this.mContext.getString(R.string._333_parking_state_7);
            case 8:
                return this.mContext.getString(R.string._333_parking_state_8);
            case 9:
                return this.mContext.getString(R.string._333_parking_state_9);
            case 10:
                return this.mContext.getString(R.string._333_parking_state_a);
            case 11:
                return this.mContext.getString(R.string._333_parking_state_b);
            case 12:
                return this.mContext.getString(R.string._333_parking_state_c);
            case 13:
                return this.mContext.getString(R.string._333_parking_state_d);
            case 14:
                return this.mContext.getString(R.string._333_parking_state_e);
            case 15:
                return this.mContext.getString(R.string._333_parking_state_f);
            case 16:
                return this.mContext.getString(R.string._333_parking_state_10);
            case 17:
                return this.mContext.getString(R.string._333_parking_state_11);
            case 18:
                return this.mContext.getString(R.string._333_parking_state_12);
            case 19:
                return this.mContext.getString(R.string._333_parking_state_13);
            case 20:
                return this.mContext.getString(R.string._333_parking_state_14);
            case 21:
                return this.mContext.getString(R.string._333_parking_state_15);
            case 22:
                return this.mContext.getString(R.string._333_parking_state_16);
            case 23:
                return this.mContext.getString(R.string._333_parking_state_17);
            default:
                return this.mContext.getString(R.string._333_parking_state_0);
        }
    }

    private String getRadarDistanceStr(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._333_drivePage_front_radar_state_1);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._333_drivePage_front_radar_state_2);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._333_drivePage_front_radar_state_3);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._333_drivePage_front_radar_state_4);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._333_drivePage_front_radar_state_5);
        }
        return this.mContext.getString(R.string._333_drivePage_front_radar_state_6);
    }

    private String getOutdoorTemperature() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            return "-" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + getTempUnitC(this.mContext);
        }
        return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + getTempUnitC(this.mContext);
    }

    private String getFuelState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._333_setting_carState_2_0);
        }
        return this.mContext.getString(R.string._333_setting_carState_2_1);
    }

    private String getReversingState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._333_setting_carState_10_0);
        }
        return this.mContext.getString(R.string._333_setting_carState_10_1);
    }

    private String getPState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._333_setting_carState_11_0);
        }
        return this.mContext.getString(R.string._333_setting_carState_11_1);
    }

    private String getLittleLampState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._333_setting_carState_off);
        }
        return this.mContext.getString(R.string._333_setting_carState_on);
    }

    private int getLangrage() {
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)) {
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
            default:
                return 0;
        }
    }

    private String getTemperature1(int i, String str) {
        if (i == 0) {
            return "LO";
        }
        if (i == 255) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return this.towDecimal.format(((i - 32) * 0.5d) + 16.0d) + getTempUnitC(this.mContext);
        }
        return this.towDecimal.format(i) + getTempUnitF(this.mContext);
    }

    private String getTemperature2(int i, String str) {
        if (i == 0) {
            return "LO";
        }
        if (i == 255) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return this.towDecimal.format(((i - 32) * 0.5d) + 14.0d) + getTempUnitC(this.mContext);
        }
        return this.towDecimal.format(i) + getTempUnitF(this.mContext);
    }

    private String getAccState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._333_setting_carState_off);
        }
        return this.mContext.getString(R.string._333_setting_carState_on);
    }

    private String getEconomicModelState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._333_setting_acc_economic_model1);
        }
        return this.mContext.getString(R.string._333_setting_acc_economic_model2);
    }

    private String getHighState(int i) {
        return "High " + (i - 10);
    }

    private String getBassState(int i) {
        return "Bass " + (i - 10);
    }

    private String getFRBalanceState(int i) {
        int i2 = i - 10;
        if (i2 > 0) {
            return "Front" + i2;
        }
        return i2 == 0 ? "Middle" : "Rear" + (-i2);
    }

    private String getLRBalanceState(int i) {
        int i2 = i - 10;
        if (i2 > 0) {
            return "Left" + i2;
        }
        return i2 == 0 ? "Middle" : "Right" + (-i2);
    }

    private String getSoundEffects(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._333_amplifier_state_info42);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._333_amplifier_state_info43);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._333_amplifier_state_info44);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._333_amplifier_state_info45);
        }
        if (i == 5) {
            return this.mContext.getString(R.string._333_amplifier_state_info46);
        }
        return this.mContext.getString(R.string._333_amplifier_state_info41);
    }

    private String getLoudnessState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._333_setting_speed_memory_on);
        }
        return this.mContext.getString(R.string._333_setting_speed_memory_off);
    }

    private String getSoundDistributionState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._333_amplifier_state_info22);
        }
        return this.mContext.getString(R.string._333_amplifier_state_info21);
    }

    private String isHaveAmplifier(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._333_amplifier_state_info12);
        }
        return this.mContext.getString(R.string._333_amplifier_state_info11);
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    private String getSwitchState(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._333_driveData_factionState_on);
        }
        return i == 2 ? this.mContext.getString(R.string._333_driveData_factionState_off) : DateLayout.NULL_DATE_FORMAT;
    }

    private String getCarStateMode(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._333_driveData_factionState7_1);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._333_driveData_factionState7_2);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._333_driveData_factionState7_3);
        }
        return i == 4 ? this.mContext.getString(R.string._333_driveData_factionState7_4) : DateLayout.NULL_DATE_FORMAT;
    }

    private void showDialog(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._141.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new AlertView().showDialog(MsgMgr.this.getActivity(), str);
            }
        });
    }

    private void sendPhoneStateInfo(int i, int i2) {
        getmUigMgr(this.mContext);
        UiMgr.phoneStateData1Bit4 = 1;
        if (i == 0) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit6 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit5 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit4 = 0;
        } else if (i == 1) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit6 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit5 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit4 = 1;
        } else if (i == 2) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit6 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit5 = 1;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit4 = 0;
        } else if (i == 3) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit6 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit5 = 1;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit4 = 1;
        } else if (i == 4) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit6 = 1;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit5 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit4 = 0;
        }
        if (i2 == 0) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit2 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit1 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit0 = 0;
        } else if (i2 == 1) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit2 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit1 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit0 = 1;
        } else if (i2 == 2) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit2 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit1 = 2;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit0 = 0;
        } else if (i2 == 3) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit2 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit1 = 1;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit0 = 1;
        } else if (i2 == 4) {
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit2 = 1;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit1 = 0;
            getmUigMgr(this.mContext);
            UiMgr.phoneStateData0Bit0 = 0;
        }
        getmUigMgr(this.mContext).sendPhoneStateInfo();
    }

    private void adjustBrightness() {
        int brightness = getBrightness();
        if (brightness == 5) {
            setBacklightLevel(0);
        } else {
            setBacklightLevel(brightness + 1);
        }
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return false;
        }
        this.mAirData = this.mCanBusInfoInt;
        return true;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange2() {
        if (Arrays.equals(this.mCarDoorData2, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData2 = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isAccInfoChange(int[] iArr) {
        if (Arrays.equals(this.accInfoInt, iArr)) {
            return true;
        }
        this.accInfoInt = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    public void updateAmp() {
        updateAmplifierActivity(null);
    }
}
