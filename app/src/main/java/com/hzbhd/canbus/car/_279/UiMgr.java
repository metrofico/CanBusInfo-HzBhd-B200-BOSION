package com.hzbhd.canbus.car._279;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean isFf = false;
    private boolean isFr = false;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(0, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(1, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(2, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(3, i);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(16);
        }
    };
    private int mCurrentCarId = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_279_temperature_unit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3, 0});
                        break;
                    case "_279_aud_obc":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3, 0});
                        break;
                    case "_279_distance_unit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3, 0});
                        break;
                    case "_279_language":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3, 0});
                        break;
                    case "_279_memo":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3, 0});
                        break;
                    case "_279_speed_limit_activation":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i3, 0});
                        break;
                    case "_279_oil_consumption_unit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3, 0});
                        break;
                    case "_279_vehicle_config":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3, 0});
                        break;
                    case "_279_sound":
                        CanbusMsgSender.sendMsg(new byte[]{22, -56, 5, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_279_speed_limit")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) ((i3 >> 8) & 255), (byte) (i3 & 255)});
                } else if (titleSrn.equals("_279_distance")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) ((i3 >> 8) & 255), (byte) (i3 & 255)});
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_279_oil_consumption_2_reset":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, 0, 0});
                        break;
                    case "_279_restore_factory_default":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, 0, 0});
                        break;
                    case "_279_oil_consumption_1_reset":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 0, 0});
                        break;
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, (byte) i});
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, (byte) i});
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, (byte) i});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 3, (byte) (i + 10)});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -56, 4, (byte) (i + 10)});
                }
            }
        });
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int i) {
                String str = panelKeyPageUiSet.getList().get(i);
                str.hashCode();
                switch (str) {
                    case "_279_panel_am":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 0});
                        break;
                    case "_279_panel_bt":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 5, 0});
                        break;
                    case "_279_panel_fm":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 0, 0});
                        break;
                    case "_279_panel_left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 7, 0});
                        break;
                    case "_279_panel_mode":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 3, 0});
                        break;
                    case "_279_panel_num1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 1});
                        break;
                    case "_279_panel_num2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 2});
                        break;
                    case "_279_panel_num3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 3});
                        break;
                    case "_279_panel_num4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 4});
                        break;
                    case "_279_panel_num5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 5});
                        break;
                    case "_279_panel_num6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, 6});
                        break;
                    case "_279_panel_eject":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 6, 0});
                        break;
                    case "_279_panel_power":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 4, 0});
                        break;
                    case "_279_panel_right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 8, 0});
                        break;
                }
            }
        });
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i) {
                String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i];
                str.hashCode();
                if (str.equals(OriginalBtnAction.RDM)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 6, (byte) (!GeneralOriginalCarDeviceData.rdm ? 1 : 0)});
                } else if (str.equals(OriginalBtnAction.SCAN)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte) (!GeneralOriginalCarDeviceData.scan ? 1 : 0)});
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
                str.hashCode();
                switch (str) {
                    case "up":
                        if (UiMgr.this.isFr) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 0});
                        } else if (UiMgr.this.isFf) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 0});
                            UiMgr.this.isFf = false;
                            UiMgr.this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._279.UiMgr.8.1
                                @Override // java.util.TimerTask, java.lang.Runnable
                                public void run() {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 1});
                                    UiMgr.this.stopTimer();
                                }
                            };
                            UiMgr.this.startTimer(100L);
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 1});
                        }
                        UiMgr.this.isFr = !r8.isFr;
                        break;
                    case "down":
                        if (UiMgr.this.isFf) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 0});
                        } else if (UiMgr.this.isFr) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, 0});
                            UiMgr.this.isFr = false;
                            UiMgr.this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._279.UiMgr.8.2
                                @Override // java.util.TimerTask, java.lang.Runnable
                                public void run() {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 1});
                                    UiMgr.this.stopTimer();
                                }
                            };
                            UiMgr.this.startTimer(100L);
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 1});
                        }
                        UiMgr.this.isFf = !r8.isFf;
                        break;
                    case "left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 7, 0});
                        break;
                    case "play":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 0, 0});
                        break;
                    case "stop":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, 0});
                        break;
                    case "right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 8, 0});
                        break;
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int i) {
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -54, 2, (byte) (i + 1)});
            }
        });
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._279.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0, 0, 0, 0});
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._279.UiMgr$18, reason: invalid class name */
    static /* synthetic */ class AnonymousClass18 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i, int i2) {
        String str = getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[i][i2];
        if (TextUtils.isEmpty(str)) {
        }
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(5);
                break;
            case "rear_defog":
                sendAirCommand(6);
                break;
            case "auto":
                sendAirCommand(4);
                break;
            case "sync":
                sendAirCommand(3);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(7);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._279.UiMgr$17] */
    public void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._279.UiMgr.17
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 0});
            }
        }.start();
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTimer(long j) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j);
    }

    private void startTimer(long j, long j2) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopTimer() {
        TimerTask timerTask = this.mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTimerTask = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }
}
