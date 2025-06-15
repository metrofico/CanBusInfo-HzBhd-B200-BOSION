package com.hzbhd.canbus.car._173;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;


public class UiMgr extends AbstractUiMgr {
    static final String _173_AMPLIFIER_BALANCE = "_173_amplifier_balance";
    static final String _173_AMPLIFIER_BASS = "_173_amplifier_bass";
    static final String _173_AMPLIFIER_FADE = "_173_amplifier_fade";
    static final String _173_AMPLIFIER_MIDDLE = "_173_amplifier_middle";
    static final String _173_AMPLIFIER_TREBLE = "_173_amplifier_treble";
    static final String _173_SAVE_LANGUAGE = "_173_SAVE_LANGUAGE";
    private int data1;
    private int data2;
    private int data3;
    private int data4;
    private int data5;
    private MsgMgr msgMgr;

    public UiMgr(final Context context) {
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._173.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) (i2 + 2), (byte) i3});
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) (i3 + 1)});
                SharePreUtil.setIntValue(context, UiMgr._173_SAVE_LANGUAGE, i3);
                UiMgr.this.msgMgr.setLanguage_recNull(context);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._173.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass6.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    UiMgr.this.data1 = 10 - i;
                    SharePreUtil.setIntValue(context, UiMgr._173_AMPLIFIER_FADE, UiMgr.this.data1);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                } else if (i2 == 2) {
                    UiMgr.this.data2 = i + 10;
                    SharePreUtil.setIntValue(context, UiMgr._173_AMPLIFIER_BALANCE, UiMgr.this.data2);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) UiMgr.this.data3, (byte) UiMgr.this.data4, (byte) UiMgr.this.data5});
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._173.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass6.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    int i3 = i + 10;
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) i3, (byte) UiMgr.this.data4, (byte) UiMgr.this.data5});
                    UiMgr.this.data3 = i3;
                    SharePreUtil.setIntValue(context, UiMgr._173_AMPLIFIER_TREBLE, UiMgr.this.data3);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                    return;
                }
                if (i2 == 2) {
                    int i4 = i + 10;
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) UiMgr.this.data3, (byte) i4, (byte) UiMgr.this.data5});
                    UiMgr.this.data4 = i4;
                    SharePreUtil.setIntValue(context, UiMgr._173_AMPLIFIER_MIDDLE, UiMgr.this.data4);
                    UiMgr.this.msgMgr.initAmplifierData(context);
                    return;
                }
                if (i2 != 3) {
                    return;
                }
                int i5 = i + 10;
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) UiMgr.this.data1, (byte) UiMgr.this.data2, (byte) UiMgr.this.data3, (byte) UiMgr.this.data4, (byte) i5});
                UiMgr.this.data5 = i5;
                SharePreUtil.setIntValue(context, UiMgr._173_AMPLIFIER_BASS, UiMgr.this.data5);
                UiMgr.this.msgMgr.initAmplifierData(context);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._173.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                int i2 = (SharePreUtil.getBoolValue(context, "_173_is_back_camera", false) || CommUtil.isBackCamera(context)) ? 1 : 5;
                Log.d("cwh", "rev = " + i2);
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (i + i2)});
            }
        });
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._173.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                boolean boolValue = SharePreUtil.getBoolValue(context, "_173_is_back_camera", false);
                boolean boolValue2 = SharePreUtil.getBoolValue(context, "_173_is_panoramic", false);
                boolean zIsBackCamera = CommUtil.isBackCamera(context);
                boolean zIsPanoramic = CommUtil.isPanoramic(context);
                if (parkPageUiSet.getPanoramicBtnList() != null) {
                    parkPageUiSet.getPanoramicBtnList().clear();
                    Log.d("cwh", " list = " + parkPageUiSet.getPanoramicBtnList());
                    ArrayList arrayList = new ArrayList();
                    if (zIsBackCamera || boolValue) {
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_all"));
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_only_rear"));
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_left_rear"));
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_right_rear"));
                    } else if (zIsPanoramic && boolValue2) {
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_all"));
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_only_front"));
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_left_front"));
                        arrayList.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_right_front"));
                    }
                    parkPageUiSet.setPanoramicBtnList(arrayList);
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._173.UiMgr$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
