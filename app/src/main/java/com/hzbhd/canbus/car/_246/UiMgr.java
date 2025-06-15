package com.hzbhd.canbus.car._246;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    private String VOL = "VOL";
    private String BALANCE = "BALANCE";
    private String FADER = "FADER";
    private String BASS = "BASS";
    private String MIDTONE = "MIDTONE";
    private String TREBLE = "TREBLE";
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._246.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(33);
        }
    };
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._246.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_246_paring_info") && i2 == 1) {
                UiMgr.this.sendCarControlInfo(0, i3);
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getSettingLeftIndexes(uiMgr2.mContext, "_246_setting_car_info")) {
                if (i2 == 0) {
                    if (i3 == 0) {
                        UiMgr.this.mBit7 = 0;
                        UiMgr.this.mBit6 = 0;
                    } else if (i3 == 1) {
                        UiMgr.this.mBit7 = 0;
                        UiMgr.this.mBit6 = 1;
                    } else if (i3 == 2) {
                        UiMgr.this.mBit7 = 1;
                        UiMgr.this.mBit6 = 0;
                    } else if (i3 == 3) {
                        UiMgr.this.mBit7 = 1;
                        UiMgr.this.mBit6 = 1;
                    }
                    UiMgr.this.sendOtherSettingInfo();
                } else if (i2 == 1) {
                    UiMgr.this.mBit5 = i3;
                    UiMgr.this.sendOtherSettingInfo();
                } else if (i2 == 2) {
                    UiMgr.this.mBit4 = i3;
                    UiMgr.this.sendOtherSettingInfo();
                } else if (i2 == 3) {
                    UiMgr.this.mBit3 = i3;
                    UiMgr.this.sendOtherSettingInfo();
                } else if (i2 == 4) {
                    if (i3 == 0) {
                        UiMgr.this.mBit2 = 0;
                        UiMgr.this.mBit1 = 0;
                        UiMgr.this.mBit0 = 0;
                    } else if (i3 == 1) {
                        UiMgr.this.mBit2 = 0;
                        UiMgr.this.mBit1 = 0;
                        UiMgr.this.mBit0 = 1;
                    } else if (i3 == 2) {
                        UiMgr.this.mBit2 = 0;
                        UiMgr.this.mBit1 = 1;
                        UiMgr.this.mBit0 = 0;
                    } else if (i3 == 3) {
                        UiMgr.this.mBit2 = 0;
                        UiMgr.this.mBit1 = 1;
                        UiMgr.this.mBit0 = 1;
                    } else if (i3 == 4) {
                        UiMgr.this.mBit2 = 1;
                        UiMgr.this.mBit1 = 0;
                        UiMgr.this.mBit0 = 0;
                    } else if (i3 == 5) {
                        UiMgr.this.mBit2 = 1;
                        UiMgr.this.mBit1 = 0;
                        UiMgr.this.mBit0 = 1;
                    }
                    UiMgr.this.sendOtherSettingInfo();
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_246_setting_car_info32")) {
                if (i2 == 0) {
                    UiMgr.this.sendCarControlInfo(1, i3);
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.getMsgMgr(uiMgr4.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.differentId + "" + UiMgr.this.eachId + "" + i + "" + i2, i, i2, i3);
                } else if (i2 == 1) {
                    UiMgr.this.sendCarControlInfo(2, i3);
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.differentId + "" + UiMgr.this.eachId + "" + i + "" + i2, i, i2, i3);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    UiMgr.this.sendCarControlInfo(3, i3);
                    UiMgr uiMgr6 = UiMgr.this;
                    uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.differentId + "" + UiMgr.this.eachId + "" + i + "" + i2, i, i2, i3);
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._246.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_246_setting_time_info")) {
                if (i2 == 0) {
                    UiMgr.this.year = i3 - 2000;
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(i, i2, i3);
                    UiMgr.this.sendTimeInfo();
                    return;
                }
                if (i2 == 1) {
                    UiMgr.this.month = i3;
                    UiMgr uiMgr3 = UiMgr.this;
                    uiMgr3.getMsgMgr(uiMgr3.mContext).updateSettings(i, i2, i3);
                    UiMgr.this.sendTimeInfo();
                    return;
                }
                if (i2 == 2) {
                    UiMgr.this.day = i3;
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.getMsgMgr(uiMgr4.mContext).updateSettings(i, i2, i3);
                    UiMgr.this.sendTimeInfo();
                    return;
                }
                if (i2 == 3) {
                    UiMgr.this.hour = i3;
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(i, i2, i3);
                    UiMgr.this.sendTimeInfo();
                    return;
                }
                if (i2 == 4) {
                    UiMgr.this.minute = i3;
                    UiMgr uiMgr6 = UiMgr.this;
                    uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettings(i, i2, i3);
                    UiMgr.this.sendTimeInfo();
                    return;
                }
                if (i2 != 5) {
                    return;
                }
                UiMgr.this.second = i3;
                UiMgr uiMgr7 = UiMgr.this;
                uiMgr7.getMsgMgr(uiMgr7.mContext).updateSettings(i, i2, i3);
                UiMgr.this.sendTimeInfo();
            }
        }
    };
    OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._246.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_246_paring_info")) {
                UiMgr.this.activeRequestData(37);
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getSettingLeftIndexes(uiMgr2.mContext, "_246_setting_time_info")) {
                UiMgr.this.activeRequestData(65, 16);
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_246_setting_car_info")) {
                UiMgr.this.activeRequestData(65, 17);
                UiMgr.this.getOtherSettingInfo();
            }
            UiMgr uiMgr4 = UiMgr.this;
            if (i == uiMgr4.getSettingLeftIndexes(uiMgr4.mContext, "_246_setting_car_info32")) {
                UiMgr.this.getOtherSettingInfo();
            }
        }
    };
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._246.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(20);
            UiMgr.this.activeRequestData(65, 2);
            UiMgr.this.activeRequestData(22);
            UiMgr.this.activeRequestData(36);
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._246.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                UiMgr.this.sendAmplifierInfo(2, i);
            } else {
                if (i2 != 2) {
                    return;
                }
                UiMgr.this.sendAmplifierInfo(1, i);
            }
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._246.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass9.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                UiMgr.this.sendAmplifierInfo(0, i);
                return;
            }
            if (i2 == 2) {
                UiMgr.this.sendAmplifierInfo(5, i - 9);
            } else if (i2 == 3) {
                UiMgr.this.sendAmplifierInfo(3, i - 9);
            } else {
                if (i2 != 4) {
                    return;
                }
                UiMgr.this.sendAmplifierInfo(4, i - 9);
            }
        }
    };
    private int year = SystemConstant.THREAD_SLEEP_TIME_2000;
    private int month = 1;
    private int day = 1;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private int mBit7 = 0;
    private int mBit6 = 0;
    private int mBit5 = 0;
    private int mBit4 = 0;
    private int mBit3 = 0;
    private int mBit2 = 0;
    private int mBit1 = 0;
    private int mBit0 = 0;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        getAirUiSet(this.mContext).getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(this.mContext);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        initUi();
        intiData(context);
    }

    private void initUi() {
        removeMainPrjBtnByName(this.mContext, MainAction.AMPLIFIER);
        if (this.eachId != 3) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_246_setting_time_info", "_246_setting_car_info"});
        }
        int i = this.eachId;
        if (i != 8 && i != 9) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_246_setting_car_info32", "_246_paring_info"});
        }
        if (getSettingLeftIndexes(this.mContext, "_246_paring_info") == -1 && getSettingLeftIndexes(this.mContext, "_246_setting_time_info") == -1 && getSettingLeftIndexes(this.mContext, "_246_setting_car_info") == -1 && getSettingLeftIndexes(this.mContext, "_246_setting_car_info32") == -1) {
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
        }
    }

    private void intiData(Context context) {
        initAmplifierInfo(context);
        selectionCarModel();
    }

    private void selectionCarModel() {
        switch (this.eachId) {
            case 1:
                sendCarModel(1);
                break;
            case 2:
                sendCarModel(2);
                break;
            case 3:
                sendCarModel(3);
                break;
            case 4:
                sendCarModel(4);
                break;
            case 5:
                sendCarModel(4);
                break;
            case 6:
                sendCarModel(4);
                break;
            case 7:
                sendCarModel(4);
                break;
            case 8:
                sendCarModel(5);
                break;
            case 9:
                sendCarModel(6);
                break;
        }
    }

    /* renamed from: com.hzbhd.canbus.car._246.UiMgr$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCarControlInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTimeInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, 16, (byte) this.year, (byte) this.month, (byte) this.day, (byte) this.hour, (byte) this.minute, (byte) this.second});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 65, 16});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOtherSettingInfo() {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, 17, (byte) getDecimalFrom8Bit(this.mBit7, this.mBit6, this.mBit5, this.mBit4, this.mBit3, this.mBit2, this.mBit1, this.mBit0)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -96, (byte) i, (byte) i2});
        saveAmplifierInfo(i, i2);
    }

    public void sendSourceInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
    }

    public void sendIconInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte) getDecimalFrom8Bit(0, 0, 0, 0, 0, i, i2, 0)});
    }

    public void sendRadioInfo(int i, int i2, int i3, int i4) {
        CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) i, (byte) i2, (byte) i3, (byte) i4});
    }

    public void sendMediaPalyInfo(int i, int i2, int i3, int i4, int i5, int i6) {
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    public void sendPhoneNumber(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public void sendPhoneNumber() {
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 6, 1, 84, 101, 108, 32, 79, 102, 102});
    }

    private void sendCarModel(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    private void saveAmplifierInfo(int i, int i2) {
        if (i == 0) {
            SharePreUtil.setIntValue(this.mContext, this.VOL, i2);
            return;
        }
        if (i == 1) {
            SharePreUtil.setIntValue(this.mContext, this.BALANCE, i2);
            return;
        }
        if (i == 2) {
            SharePreUtil.setIntValue(this.mContext, this.FADER, i2);
            return;
        }
        if (i == 3) {
            SharePreUtil.setIntValue(this.mContext, this.BASS, i2);
        } else if (i == 4) {
            SharePreUtil.setIntValue(this.mContext, this.MIDTONE, i2);
        } else {
            if (i != 5) {
                return;
            }
            SharePreUtil.setIntValue(this.mContext, this.TREBLE, i2);
        }
    }

    protected void initAmplifierInfo(Context context) {
        if (context != null) {
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, this.BALANCE, 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, this.FADER, 0);
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, this.VOL, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, this.BASS, 0) + 9;
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, this.MIDTONE, 0) + 9;
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, this.TREBLE, 0) + 9;
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._246.UiMgr.8
            final byte[][] commands = {new byte[]{22, -96, 0, (byte) GeneralAmplifierData.volume}, new byte[]{22, -96, 1, (byte) GeneralAmplifierData.leftRight}, new byte[]{22, -96, 2, (byte) GeneralAmplifierData.frontRear}, new byte[]{22, -96, 3, (byte) (GeneralAmplifierData.bandBass - 9)}, new byte[]{22, -96, 4, (byte) (GeneralAmplifierData.bandMiddle - 9)}, new byte[]{22, -96, 5, (byte) (GeneralAmplifierData.bandTreble - 9)}};
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr = this.commands;
                if (i < bArr.length) {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr[i]);
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
        getMsgMgr(this.mContext).updateAmplifier();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getOtherSettingInfo() {
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 0, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "0", 0));
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 1, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "1", 0));
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 2, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "2", 0));
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
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

    protected int getSettingLeftIndexes(Context context, String str) {
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
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }
}
