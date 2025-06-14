package com.hzbhd.canbus.car._308;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private AmplifierPageUiSet mAmplifierPageUiSet;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SettingPageUiSet mSettingPageUiSet;
    OnOriginalCarDevicePageStatusListener mOnOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
        public void onStatusChange(int i) {
            UiMgr.this.sendOnOriginalCmd(128);
        }
    };
    OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
        public void onBackPressed() {
            UiMgr.this.sendOnOriginalCmd(0);
        }
    };
    OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
        public void onClickBottomBtnItem(int i) {
            String str = UiMgr.this.mOriginalCarDevicePageUiSet.getRowBottomBtnAction()[i];
            str.hashCode();
            switch (str) {
                case "up":
                    UiMgr.this.sendOnOriginalCmd(5);
                    break;
                case "down":
                    UiMgr.this.sendOnOriginalCmd(4);
                    break;
                case "play":
                    UiMgr.this.sendOnOriginalCmd(1);
                    break;
                case "stop":
                    UiMgr.this.sendOnOriginalCmd(3);
                    break;
                case "pause":
                    UiMgr.this.sendOnOriginalCmd(2);
                    break;
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            String str = UiMgr.this.mAmplifierPageUiSet.getLineBtnAction()[i];
            str.hashCode();
            if (str.equals(GeneralAmplifierData.bose_center)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 15, 0});
            }
        }
    };
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            switch (titleSrn) {
                case "_308_title_13":
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 2, (byte) i3});
                    break;
                case "_308_title_15":
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 4, (byte) i3});
                    break;
                case "_308_title_16":
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 5, (byte) i3});
                    break;
                case "_308_title_17":
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 7, (byte) i3});
                    break;
            }
        }
    };
    OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("_308_title_18")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -105, 8, 1});
            }
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (!titleSrn.equals("_308_title_12")) {
                if (titleSrn.equals("_308_title_14")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -105, 3, (byte) i3});
                }
            } else {
                int i4 = i3 - GeneralAmplifierData.custom2Bass;
                Log.d("scyscyscy", "---------->" + i4);
                if (i4 > 0) {
                    UiMgr.this.seekbarSpeed(11, i4);
                } else {
                    UiMgr.this.seekbarSpeed(12, -i4);
                }
            }
        }
    };
    OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            if (amplifierBand == AmplifierActivity.AmplifierBand.VOLUME_Min) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 2, 0});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.BASS_Min) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 4, 0});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE_Min) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 6, 0});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.VOLUME_Plus) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 1, 0});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.BASS_Plus) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 3, 0});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE_Plus) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 5, 0});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.CUSTOM_BASS_Min) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 14, 0});
                return;
            }
            if (amplifierBand == AmplifierActivity.AmplifierBand.CUSTOM_BASS_Plus) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
            } else if (amplifierBand == AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Min) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
            } else if (amplifierBand == AmplifierActivity.AmplifierBand.CUSTOM_BASS_2_Plus) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 11, 0});
            }
        }
    };
    OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            if (amplifierPosition == AmplifierActivity.AmplifierPosition.LEFT) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 8, 0});
                return;
            }
            if (amplifierPosition == AmplifierActivity.AmplifierPosition.RIGHT) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 7, 0});
            } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.FRONT) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 9, 0});
            } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.REAR) {
                CanbusMsgSender.sendMsg(new byte[]{22, -93, 10, 0});
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.sendAirCmd(9);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.sendAirCmd(10);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.sendAirCmd(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.sendAirCmd(11);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.sendAirCmd(14);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.sendAirCmd(13);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._308.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };

    public UiMgr(Context context) {
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.mSettingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        this.mSettingPageUiSet.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
        this.mSettingPageUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        this.mAmplifierPageUiSet = amplifierPageUiSet;
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
        this.mAmplifierPageUiSet.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
        this.mAmplifierPageUiSet.setOnAirBtnClickListeners(this.mOnAirBtnClickListener);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(this.mOnOriginalCarDevicePageStatusListener);
        this.mOriginalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.mOnOriginalCarDeviceBackPressedListener);
        this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekbarSpeed(int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte) i, 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCmd(5);
                break;
            case "rear_defog":
                sendAirCmd(6);
                break;
            case "blow_positive":
                sendAirCmd(8);
                break;
            case "ac":
                sendAirCmd(1);
                break;
            case "auto":
                sendAirCmd(4);
                break;
            case "dual":
                sendAirCmd(7);
                break;
            case "power":
                sendAirCmd(0);
                break;
            case "in_out_cycle":
                if (!GeneralAirData.in_out_cycle) {
                    sendAirCmd(3);
                    break;
                } else {
                    sendAirCmd(2);
                    break;
                }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._308.UiMgr$17] */
    public static void sendAirCmd(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._308.UiMgr.17
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte) i, 0});
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._308.UiMgr$18] */
    public void sendOnOriginalCmd(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._308.UiMgr.18
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte) i, 0});
            }
        }.start();
    }
}
