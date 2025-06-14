package com.hzbhd.canbus.car._347;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private static final int CONFIGURATION_camera_452 = 1;
    private static final int CONFIGURATION_defualt_452 = -1;
    private static final int CONFIGURATION_front_camera_452 = 0;
    Context mContext;
    MsgMgr msgMgr;
    private int volume;
    private String VOL = "VOL";
    private String BALANCE = "BALANCE";
    private String FADER = "FADER";
    private String BASS = "BASS";
    private String MIDTONE = "MIDTONE";
    private String TREBLE = "TREBLE";
    public String CAMERA_FLAG_TAG_452 = "ORIGINAL.MEDIA.CAMERA.FLAG_452";
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._347.UiMgr.2
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v3 */
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            byte b;
            byte b2;
            int i4;
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_246_paring_info") && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i3});
                UiMgr uiMgr2 = UiMgr.this;
                b = 1;
                b2 = 2;
                uiMgr2.getMsgMgr(uiMgr2.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.differentId + "" + UiMgr.this.eachId + "" + i + "" + i2, i, i2, i3);
            } else {
                b = 1;
                b2 = 2;
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_246_setting_car_info32")) {
                if (i2 == 0) {
                    byte[] bArr = new byte[4];
                    bArr[0] = 22;
                    bArr[b] = -58;
                    bArr[b2] = b;
                    bArr[3] = (byte) i3;
                    CanbusMsgSender.sendMsg(bArr);
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.getMsgMgr(uiMgr4.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.differentId + "" + UiMgr.this.eachId + "" + i + "" + i2, i, i2, i3);
                } else if (i2 == b) {
                    byte[] bArr2 = new byte[4];
                    bArr2[0] = 22;
                    bArr2[b] = -58;
                    bArr2[b2] = 3;
                    bArr2[3] = (byte) (i3 + 1);
                    CanbusMsgSender.sendMsg(bArr2);
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.differentId + "" + UiMgr.this.eachId + "" + i + "" + i2, i, i2, i3);
                } else if (i2 == b2) {
                    byte[] bArr3 = new byte[4];
                    bArr3[0] = 22;
                    bArr3[b] = -58;
                    bArr3[b2] = b2;
                    bArr3[3] = (byte) i3;
                    CanbusMsgSender.sendMsg(bArr3);
                    UiMgr uiMgr6 = UiMgr.this;
                    uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettings(UiMgr.this.mContext, UiMgr.this.differentId + "" + UiMgr.this.eachId + "" + i + "" + i2, i, i2, i3);
                }
            }
            UiMgr uiMgr7 = UiMgr.this;
            if (i == uiMgr7.getSettingLeftIndexes(uiMgr7.mContext, "_347_setting_0_10") && i2 == 0) {
                byte[] bArr4 = new byte[4];
                bArr4[0] = 22;
                bArr4[b] = -58;
                bArr4[b2] = b2;
                i4 = 3;
                bArr4[3] = (byte) i3;
                CanbusMsgSender.sendMsg(bArr4);
            } else {
                i4 = 3;
            }
            UiMgr uiMgr8 = UiMgr.this;
            if (i == uiMgr8.getSettingLeftIndexes(uiMgr8.mContext, "_347_setting_0_8") && i2 == 0) {
                byte[] bArr5 = new byte[i4];
                bArr5[0] = 22;
                bArr5[b] = -60;
                bArr5[b2] = (byte) i3;
                CanbusMsgSender.sendMsg(bArr5);
            }
            UiMgr uiMgr9 = UiMgr.this;
            if (i == uiMgr9.getSettingLeftIndexes(uiMgr9.mContext, "_347_settings")) {
                UiMgr uiMgr10 = UiMgr.this;
                if (i2 == uiMgr10.getSettingRightIndex(uiMgr10.mContext, "_347_settings", "_347_settings_0")) {
                    byte[] bArr6 = new byte[4];
                    bArr6[0] = 22;
                    bArr6[b] = -58;
                    bArr6[b2] = 3;
                    bArr6[3] = (byte) (i3 + 1);
                    CanbusMsgSender.sendMsg(bArr6);
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_347_settings", "_347_settings_1")) {
                    byte[] bArr7 = new byte[4];
                    bArr7[0] = 22;
                    bArr7[b] = -58;
                    bArr7[b2] = 5;
                    bArr7[3] = (byte) (i3 + 1);
                    CanbusMsgSender.sendMsg(bArr7);
                }
                UiMgr uiMgr12 = UiMgr.this;
                if (i2 == uiMgr12.getSettingRightIndex(uiMgr12.mContext, "_347_settings", "_347_settings_2")) {
                    byte[] bArr8 = new byte[4];
                    bArr8[0] = 22;
                    bArr8[b] = -58;
                    bArr8[b2] = 6;
                    bArr8[3] = (byte) (i3 + 6);
                    CanbusMsgSender.sendMsg(bArr8);
                }
                UiMgr uiMgr13 = UiMgr.this;
                if (i2 == uiMgr13.getSettingRightIndex(uiMgr13.mContext, "_347_settings", "_451_reverse_flag")) {
                    if (i3 == 0) {
                        CanbusConfig.INSTANCE.setCameraConfiguration(b);
                        UiMgr uiMgr14 = UiMgr.this;
                        uiMgr14.getMsgMgr(uiMgr14.mContext).updateSettingsUi(UiMgr.this.mContext, i, i2, UiMgr.this.CAMERA_FLAG_TAG_452, 0);
                    } else if (i3 == b) {
                        CanbusConfig.INSTANCE.setCameraConfiguration(false);
                        UiMgr uiMgr15 = UiMgr.this;
                        uiMgr15.getMsgMgr(uiMgr15.mContext).updateSettingsUi(UiMgr.this.mContext, i, i2, UiMgr.this.CAMERA_FLAG_TAG_452, 1);
                    }
                }
            }
        }
    };
    OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._347.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass8.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                MyLog.temporaryTracking("前后" + i + "");
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte) i});
            } else {
                if (i2 != 2) {
                    return;
                }
                MyLog.temporaryTracking("左右" + i + "");
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte) i});
            }
        }
    };
    OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._347.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass8.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 0, (byte) i});
                return;
            }
            if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, (byte) i});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 5, (byte) i});
            } else {
                if (i2 != 4) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, (byte) i});
            }
        }
    };
    OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._347.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
            UiMgr.this.initAmplifierInfo();
        }
    };
    OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._347.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(33);
        }
    };
    OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._347.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.activeRequestData(20);
            UiMgr.this.activeRequestData(22);
            UiMgr.this.activeRequestData(36);
            UiMgr.this.activeRequestData(37);
            UiMgr.this.activeRequestData(65, 2);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    private int getCarModelData(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i != 4) {
            return i != 5 ? 0 : 6;
        }
        return 5;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        if (this.eachId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) getCarModelData(0)});
        }
        if (this.eachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) getCarModelData(1)});
        }
        if (this.eachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) getCarModelData(2)});
        }
        if (this.eachId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) getCarModelData(3)});
        }
        if (this.eachId == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) getCarModelData(4)});
        }
        if (this.eachId == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) getCarModelData(5)});
        }
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._347.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.getMsgMgr(uiMgr.mContext).updateSettings(i, i2, i3);
                String titleSrn = settingUiSet.getList().get(i).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_330_amplifier_info")) {
                    String titleSrn2 = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                    titleSrn2.hashCode();
                    switch (titleSrn2) {
                        case "_347_setting_0_4":
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte) i3});
                            break;
                        case "_347_setting_0_5":
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte) i3});
                            break;
                        case "_347_setting_0_6":
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte) i3});
                            break;
                        case "_347_setting_0_7":
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte) i3});
                            break;
                        case "_347_setting_0_9":
                            CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i3});
                            break;
                        case "_330_Vehicle_volume_follows_ASL":
                            CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte) i3});
                            break;
                    }
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
        getAirUiSet(this.mContext).getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
        getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 3) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        if (this.eachId != 5) {
            removeMainPrjBtnByName(context, "air");
            removeSettingRightItemByNameList(context, new String[]{"_246_paring_info1"});
        }
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_347_setting_0_10"});
        activeRequestData(48);
        initCamera(context);
    }

    public void initCamera(Context context) {
        int intValue = SharePreUtil.getIntValue(context, this.CAMERA_FLAG_TAG_452, -1);
        if (intValue == -1) {
            getMsgMgr(context).updateSettingsUi(context, getSettingLeftIndexes(context, "_347_settings"), getSettingRightIndex(context, "_347_settings", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_452, 1 ^ (CanbusConfig.INSTANCE.getCameraConfiguration() ? 1 : 0));
        } else {
            getMsgMgr(context).updateSettingsUi(context, getSettingLeftIndexes(context, "_347_settings"), getSettingRightIndex(context, "_347_settings", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_452, intValue);
            CanbusConfig.INSTANCE.setCameraConfiguration(intValue == 0);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._347.UiMgr$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 4;
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

    private void sendAmplifierInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -96, (byte) i, (byte) i2});
        saveAmplifierInfo(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activeRequestData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) i, (byte) i2});
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
    }

    private void getOtherSettingInfo() {
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 0, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "0", 0));
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 1, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "1", 0));
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 2, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "2", 0));
    }

    protected int getSettingRightIndex(Context context, String str, String str2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (str.equals(next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (str2.equals(it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    public void sendSourceInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2});
    }

    public void sendIconInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte) getDecimalFrom8Bit(0, 0, 0, 0, 0, i, i2, 0)});
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
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

    public void sendVol(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
    }

    public void sendCarType() {
        switch (this.eachId) {
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 4});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 5});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 6});
                break;
        }
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

    void initAmplifierInfo() {
        GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(this.mContext, this.BALANCE, 0);
        GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(this.mContext, this.FADER, 0);
        GeneralAmplifierData.volume = SharePreUtil.getIntValue(this.mContext, this.VOL, 28);
        GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(this.mContext, this.BASS, 0) + 9;
        GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(this.mContext, this.MIDTONE, 0) + 9;
        GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(this.mContext, this.TREBLE, 0) + 9;
        sendAmplifierInfo(0, SharePreUtil.getIntValue(this.mContext, this.VOL, 28));
        sendAmplifierInfo(1, SharePreUtil.getIntValue(this.mContext, this.BALANCE, 0));
        sendAmplifierInfo(2, SharePreUtil.getIntValue(this.mContext, this.FADER, 0));
        sendAmplifierInfo(3, SharePreUtil.getIntValue(this.mContext, this.BASS, 0));
        sendAmplifierInfo(4, SharePreUtil.getIntValue(this.mContext, this.MIDTONE, 0));
        sendAmplifierInfo(5, SharePreUtil.getIntValue(this.mContext, this.TREBLE, 0));
        getMsgMgr(this.mContext).updateAmplifier();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
