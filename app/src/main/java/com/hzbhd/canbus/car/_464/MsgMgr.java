package com.hzbhd.canbus.car._464;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static final int AUX_MODE = 252;
    public static final int CD_DVD_PLAY_MODE = 250;
    public static final int CD_MODE = 255;
    public static final int DVD_MODE = 254;
    public static final int MEDIA_OFF_MODE = 253;
    public static final int RADIO_MODE = 251;
    public static final int REAR_DISC_MODE = 249;
    int[] mAirData;
    int[] mAmplData;
    int[] mCarDoorData;
    Context mContext;
    int[] mDeviceData;
    int[] mDiscData;
    int[] mFrontRadarData;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    int[] mRearRadarData;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatInteger2 = new DecimalFormat("00");
    public int nowModeTag = 255;

    private String getRadioRunState(int i) {
        if (i == 1) {
            return "FM1";
        }
        if (i == 2) {
            return "FM2";
        }
        switch (i) {
            case 16:
                return "AM";
            case 17:
                return "AM1";
            case 18:
                return "AM2";
            default:
                return "FM";
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initCarConfig(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalDeviceDataArray();
    }

    private void initOriginalDeviceDataArray() {
        this.mOriginalDeviceDataArray = new SparseArray<>();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_0", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_1", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_2", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_3", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_4", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_5", null));
        this.mOriginalDeviceDataArray.put(255, new OriginalDeviceData(arrayList, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.RADIO_SCAN, "down", "right", OriginalBtnAction.NEXT_DISC, OriginalBtnAction.RANDOM, OriginalBtnAction.REPEAT}));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_0", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_1", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_2", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_3", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_4", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_5", null));
        this.mOriginalDeviceDataArray.put(DVD_MODE, new OriginalDeviceData(arrayList2, new String[]{"left", "up", OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, OriginalBtnAction.STOP, "down", "right"}));
        this.mOriginalDeviceDataArray.put(253, new OriginalDeviceData(new ArrayList(), new String[0]));
        this.mOriginalDeviceDataArray.put(252, new OriginalDeviceData(new ArrayList(), new String[0]));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_0", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_1", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_2", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_3", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_4", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_5", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_6", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_7", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_radio_8", null));
        this.mOriginalDeviceDataArray.put(RADIO_MODE, new OriginalDeviceData(arrayList3, new String[]{"left", "up", OriginalBtnAction.RADIO_SCAN, "down", "right"}));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_0", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_1", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_2", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_cd_dvd_3", null));
        this.mOriginalDeviceDataArray.put(250, new OriginalDeviceData(arrayList4, new String[0]));
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_rear_disc_1", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_rear_disc_2", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_464_device_rear_disc_3", null));
        this.mOriginalDeviceDataArray.put(REAR_DISC_MODE, new OriginalDeviceData(arrayList5, new String[0]));
    }

    private void initCarConfig(Context context) {
        if (getCurrentEachCanId() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 101});
        }
        if (getCurrentEachCanId() == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 100});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            setSpeed0x16(byteArrayToIntArray);
            return;
        }
        if (i == 32) {
            setSwc0x20(byteArrayToIntArray);
            return;
        }
        if (i == 36) {
            setBasicInfo0x24(byteArrayToIntArray);
            return;
        }
        if (i == 53) {
            setDriveDate0x35(byteArrayToIntArray);
            return;
        }
        if (i == 80) {
            setSpeed0x50(byteArrayToIntArray);
            return;
        }
        if (i == 29) {
            setFrontRadarInfo(byteArrayToIntArray);
            return;
        }
        if (i == 30) {
            setRearRadarInfo(byteArrayToIntArray);
            return;
        }
        if (i == 40) {
            setAir0x28(byteArrayToIntArray);
            return;
        }
        if (i == 41) {
            setTrack0x29(byteArrayToIntArray);
            return;
        }
        if (i == 100) {
            setPanelKry0x64(byteArrayToIntArray);
            return;
        }
        if (i != 101) {
            switch (i) {
                case 48:
                    setVersionInfo0x30(bArr);
                    break;
                case 49:
                    setAmpl0x31(byteArrayToIntArray);
                    break;
                case 50:
                    setAmpl0x32(byteArrayToIntArray);
                    break;
                default:
                    switch (i) {
                        case 96:
                            setSwc0x60Mode(byteArrayToIntArray);
                            break;
                        case 97:
                            setDiscInfo0x61(byteArrayToIntArray);
                            break;
                        case 98:
                            setDevice0x62(byteArrayToIntArray);
                            break;
                    }
            }
            return;
        }
        setYGInfo0x65(byteArrayToIntArray);
    }

    private void setYGInfo0x65(int[] iArr) {
        int i = iArr[2];
        switch (i) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 48, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 48, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 48, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 47, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 47, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 47, iArr[3]);
                break;
            default:
                switch (i) {
                    case 16:
                        realKeyClick4(this.mContext, 48);
                        break;
                    case 17:
                        realKeyClick4(this.mContext, 47);
                        break;
                    case 18:
                        realKeyLongClick1(this.mContext, 49, iArr[3]);
                        break;
                    case 19:
                        realKeyLongClick1(this.mContext, 50, iArr[3]);
                        break;
                    case 20:
                        realKeyLongClick1(this.mContext, 151, iArr[3]);
                        break;
                    case 21:
                        realKeyLongClick1(this.mContext, 53, iArr[3]);
                        break;
                    case 22:
                        realKeyLongClick1(this.mContext, 128, iArr[3]);
                        break;
                }
        }
    }

    private void setPanelKry0x64(int[] iArr) {
        int i = iArr[2];
        if (i == 16) {
            realKeyLongClick1(this.mContext, 53, iArr[3]);
            return;
        }
        if (i != 17) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 77, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 76, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 76, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 141, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 128, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 41, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 151, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 39, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 57, iArr[3]);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, 40, iArr[3]);
    }

    private void setDevice0x62(int[] iArr) {
        if (isDeviceDataNoChange(iArr)) {
            return;
        }
        int i = iArr[2];
        if (i == 0) {
            setMediaOff(iArr);
            return;
        }
        if (i == 1) {
            setRadioInfo(iArr);
            return;
        }
        if (i == 2) {
            setCdDvdInfo(iArr);
        } else if (i == 3) {
            setAux(iArr);
        } else if (i == 4) {
            setRearDisc(iArr);
        }
    }

    private void setRearDisc(int[] iArr) {
        Context context;
        int i;
        String string;
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit6(iArr[6])) {
            context = this.mContext;
            i = R.string._464_device_rear_disc_11;
        } else {
            context = this.mContext;
            i = R.string._464_device_rear_disc_10;
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, context.getString(i)));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, DataHandleUtils.getMsbLsbResult(iArr[8], iArr[7]) + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.formatInteger2.format(iArr[9]) + ":" + this.formatInteger2.format(iArr[10]) + ":" + this.formatInteger2.format(iArr[11])));
        if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4) == 0) {
            string = this.mContext.getString(R.string._464_device_state_0);
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4) == 1) {
            string = this.mContext.getString(R.string._464_device_state_1);
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4) == 2) {
            string = this.mContext.getString(R.string._464_device_state_2);
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4) == 3) {
            string = this.mContext.getString(R.string._464_device_state_3);
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4) == 4) {
            string = this.mContext.getString(R.string._464_device_state_4);
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4) == 5) {
            string = this.mContext.getString(R.string._464_device_state_5);
        } else {
            string = DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4) == 15 ? this.mContext.getString(R.string._464_device_state_6) : "NONE";
        }
        GeneralOriginalCarDeviceData.mList = arrayList;
        GeneralOriginalCarDeviceData.cdStatus = this.mContext.getString(R.string._464_device_rear_disc_0);
        GeneralOriginalCarDeviceData.runningState = string;
        this.nowModeTag = REAR_DISC_MODE;
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(REAR_DISC_MODE);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setCdDvdInfo(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        GeneralOriginalCarDeviceData.mList = arrayList;
        GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
        GeneralOriginalCarDeviceData.runningState = "Disc " + DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 4);
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, getCycleState(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, getOrderState(DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4))));
        int msbLsbResult = DataHandleUtils.getMsbLsbResult(iArr[7], iArr[6]);
        int msbLsbResult2 = DataHandleUtils.getMsbLsbResult(iArr[9], iArr[8]);
        String str = this.formatInteger2.format(iArr[10]) + ":" + this.formatInteger2.format(iArr[11]) + ":" + this.formatInteger2.format(iArr[12]);
        String str2 = this.formatInteger2.format(iArr[13]) + ":" + this.formatInteger2.format(iArr[14]) + ":" + this.formatInteger2.format(iArr[15]);
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, msbLsbResult2 + "/" + msbLsbResult));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, str2 + "/" + str));
        this.nowModeTag = 250;
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(250);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private String getOrderState(int i) {
        if (i != 0) {
            return i != 1 ? "NONE" : this.mContext.getString(R.string._464_device_cd_dvd_11);
        }
        return this.mContext.getString(R.string._464_device_cd_dvd_10);
    }

    private String getCycleState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._464_device_cd_dvd_00);
        }
        if (i != 1) {
            return i != 2 ? "NONE" : this.mContext.getString(R.string._464_device_cd_dvd_02);
        }
        return this.mContext.getString(R.string._464_device_cd_dvd_01);
    }

    private void setRadioInfo(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        GeneralOriginalCarDeviceData.mList = arrayList;
        GeneralOriginalCarDeviceData.cdStatus = "RADIO";
        GeneralOriginalCarDeviceData.runningState = getRadioRunState(iArr[4]);
        if (iArr[3] == 0) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(DataHandleUtils.getBoolBit7(iArr[5]) ? R.string._464_device_radio_01 : R.string._464_device_radio_00)));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, DataHandleUtils.getBoolBit5(iArr[5]) ? "ON" : "OFF"));
            if (iArr[7] != 255 && iArr[6] != 255) {
                int i = iArr[4];
                if (i == 0 || i == 1 || i == 2) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(2, (DataHandleUtils.getMsbLsbResult(iArr[7], iArr[6]) / 100.0f) + "mHz"));
                } else if (i == 16 || i == 17 || i == 18) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(2, DataHandleUtils.getMsbLsbResult(iArr[7], iArr[6]) + "kHz"));
                }
            } else {
                arrayList.add(new OriginalCarDeviceUpdateEntity(2, "NONE"));
            }
        } else {
            int i2 = iArr[4];
            if (i2 == 0 || i2 == 1 || i2 == 2) {
                if (iArr[6] != 255 && iArr[5] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(3, (DataHandleUtils.getMsbLsbResult(iArr[6], iArr[5]) / 100.0f) + "mHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(3, "NONE"));
                }
                if (iArr[8] != 255 && iArr[7] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(4, (DataHandleUtils.getMsbLsbResult(iArr[8], iArr[7]) / 100.0f) + "mHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(4, "NONE"));
                }
                if (iArr[10] != 255 && iArr[9] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(5, (DataHandleUtils.getMsbLsbResult(iArr[10], iArr[9]) / 100.0f) + "mHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(5, "NONE"));
                }
                if (iArr[12] != 255 && iArr[11] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(6, (DataHandleUtils.getMsbLsbResult(iArr[12], iArr[11]) / 100.0f) + "mHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(6, "NONE"));
                }
                if (iArr[14] != 255 && iArr[13] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(7, (DataHandleUtils.getMsbLsbResult(iArr[14], iArr[13]) / 100.0f) + "mHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(7, "NONE"));
                }
                if (iArr[16] != 255 && iArr[15] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(8, (DataHandleUtils.getMsbLsbResult(iArr[16], iArr[15]) / 100.0f) + "mHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(8, "NONE"));
                }
            } else if (i2 == 16 || i2 == 17 || i2 == 18) {
                if (iArr[6] != 255 && iArr[5] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(3, DataHandleUtils.getMsbLsbResult(iArr[6], iArr[5]) + "kHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(3, "NONE"));
                }
                if (iArr[8] != 255 && iArr[7] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(4, DataHandleUtils.getMsbLsbResult(iArr[8], iArr[7]) + "kHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(4, "NONE"));
                }
                if (iArr[10] != 255 && iArr[9] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(5, DataHandleUtils.getMsbLsbResult(iArr[10], iArr[9]) + "kHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(5, "NONE"));
                }
                if (iArr[12] != 255 && iArr[11] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(6, DataHandleUtils.getMsbLsbResult(iArr[12], iArr[11]) + "kHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(6, "NONE"));
                }
                if (iArr[14] != 255 && iArr[13] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(7, DataHandleUtils.getMsbLsbResult(iArr[14], iArr[13]) + "kHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(7, "NONE"));
                }
                if (iArr[16] != 255 && iArr[15] != 255) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(8, DataHandleUtils.getMsbLsbResult(iArr[16], iArr[15]) + "kHz"));
                } else {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(8, "NONE"));
                }
            }
        }
        this.nowModeTag = RADIO_MODE;
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(RADIO_MODE);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setAux(int[] iArr) {
        GeneralOriginalCarDeviceData.mList = new ArrayList();
        GeneralOriginalCarDeviceData.cdStatus = "AUX";
        GeneralOriginalCarDeviceData.runningState = "Running";
        this.nowModeTag = 252;
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(252);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setMediaOff(int[] iArr) {
        GeneralOriginalCarDeviceData.mList = new ArrayList();
        GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
        GeneralOriginalCarDeviceData.runningState = "NONE";
        this.nowModeTag = 253;
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(253);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x022c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setDiscInfo0x61(int[] r25) {
        /*
            Method dump skipped, instructions count: 1017
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._464.MsgMgr.setDiscInfo0x61(int[]):void");
    }

    private static class OriginalDeviceData {
        private final String[] bottomBtns;
        private final List<OriginalCarDevicePageUiSet.Item> list;

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list) {
            this(list, null);
        }

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr) {
            this.list = list;
            this.bottomBtns = strArr;
        }

        public List<OriginalCarDevicePageUiSet.Item> getItemList() {
            return this.list;
        }

        public String[] getBottomBtns() {
            return this.bottomBtns;
        }
    }

    private void setSwc0x60Mode(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_settings_swc"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_settings_swc", "_464_settings_swc0"), Integer.valueOf(iArr[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSpeed0x50(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_5"), DataHandleUtils.getMsbLsbResult(iArr[3], iArr[2]) + "rpm"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveDate0x35(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_0"), (DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) / 10.0f) + "L/100km"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_1"), (DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) / 10.0f) + "L/100km"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_2"), DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) + "km"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_3"), DataHandleUtils.getMsbLsbResult(iArr[8], iArr[9]) + "km/h"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_464_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_464_drive_4"), DataHandleUtils.getMsbLsbResult(iArr[10], iArr[11]) + "km"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAmpl0x32(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 7, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAmpl0x31(int[] iArr) {
        Context context;
        int i;
        if (isAmplDataChange(iArr)) {
            GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 4) - 7;
            GeneralAmplifierData.frontRear = (-DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 4)) + 7;
            GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4) - 2;
            GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(iArr[4], 4, 4) - 2;
            GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 4) - 2;
            GeneralAmplifierData.volume = iArr[5];
            updateAmplifierActivity(new Bundle());
            ArrayList arrayList = new ArrayList();
            int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl");
            int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_0");
            if (DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 1) == 0) {
                context = this.mContext;
                i = R.string._464_ampl_0_0;
            } else {
                context = this.mContext;
                i = R.string._464_ampl_0_1;
            }
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, context.getString(i)).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_464_ampl"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_464_ampl", "_464_ampl_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 2))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVersionInfo0x30(byte[] bArr) {
        updateVersionInfo(this.mContext, getVersionStr(bArr));
    }

    private void setTrack0x29(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            boolean boolBit3 = DataHandleUtils.getBoolBit3(iArr[3]);
            int iBlockBit = DataHandleUtils.blockBit(iArr[3], 3);
            iArr[3] = iBlockBit;
            int iBlockBit2 = DataHandleUtils.blockBit(iBlockBit, 4);
            iArr[3] = iBlockBit2;
            int iBlockBit3 = DataHandleUtils.blockBit(iBlockBit2, 5);
            iArr[3] = iBlockBit3;
            int iBlockBit4 = DataHandleUtils.blockBit(iBlockBit3, 6);
            iArr[3] = iBlockBit4;
            int iBlockBit5 = DataHandleUtils.blockBit(iBlockBit4, 7);
            iArr[3] = iBlockBit5;
            if (boolBit3) {
                GeneralParkData.trackAngle = ((4095 - ((iArr[2] & 255) | ((iBlockBit5 & 255) << 8))) / 15) - 136;
                updateParkUi(null, this.mContext);
            } else {
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) iArr[2], (byte) iBlockBit5, 0, 380, 16);
                updateParkUi(null, this.mContext);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setAir0x28(int[] r17) {
        /*
            Method dump skipped, instructions count: 483
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._464.MsgMgr.setAir0x28(int[]):void");
    }

    private void setBasicInfo0x24(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setSwc0x20(int[] iArr) {
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[3]);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
            return;
        }
        if (i != 135) {
            switch (i) {
                case 7:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 15, iArr[3]);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, 3, iArr[3]);
    }

    private void setRearRadarInfo(int[] iArr) {
        if (isRearRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarInfo(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setSpeed0x16(int[] iArr) {
        updateSpeedInfo((int) (DataHandleUtils.getMsbLsbResult(iArr[3], iArr[2]) / 16.0f));
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isDeviceDataNoChange(int[] iArr) {
        if (Arrays.equals(this.mDeviceData, iArr)) {
            return true;
        }
        this.mDeviceData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isDiscDataNoChange(int[] iArr) {
        if (Arrays.equals(this.mDiscData, iArr)) {
            return true;
        }
        this.mDiscData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isAmplDataChange(int[] iArr) {
        if (Arrays.equals(this.mAmplData, iArr)) {
            return false;
        }
        this.mAmplData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return false;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRearRadarData, iArr)) {
            return false;
        }
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
