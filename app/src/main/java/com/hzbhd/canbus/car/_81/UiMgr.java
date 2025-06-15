package com.hzbhd.canbus.car._81;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._81.MsgMgr;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private AirPageUiSet mAirPageUiSet;
    private String[][] mDiagitalKeyboardActions;
    private String[][] mFeaturesKeyboardActions;
    private boolean mIsFeaturesKeyboard = true;
    private MsgMgr mMsgMgr;
    private MyPanoramicView mPanoramicView;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = new MyPanoramicView(context);
        }
        return this.mPanoramicView;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._81.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.lambda$new$0(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._81.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m928lambda$new$1$comhzbhdcanbuscar_81UiMgr(settingUiSet, context, i, i2, i3);
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.mAirPageUiSet = airUiSet;
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m929lambda$new$2$comhzbhdcanbuscar_81UiMgr(i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m930lambda$new$3$comhzbhdcanbuscar_81UiMgr(i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m931lambda$new$4$comhzbhdcanbuscar_81UiMgr(i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m932lambda$new$5$comhzbhdcanbuscar_81UiMgr(i);
            }
        }});
        this.mAirPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(26);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(27);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(28);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(29);
            }
        }});
        this.mAirPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(31);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(30);
            }
        });
        final SyncPageUiSet syncPageUiSet = getSyncPageUiSet(context);
        syncPageUiSet.setOnSyncStateChangeListener(new OnSyncStateChangeListener() { // from class: com.hzbhd.canbus.car._81.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onResume() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onStop() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
            }
        });
        syncPageUiSet.setOnSyncItemClickListener(new OnSyncItemClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onLeftIconClick(String str) {
                str.hashCode();
                switch (str) {
                    case "media":
                        if (!MsgMgr.SYNC_VERSION.VERSION_V1.equals(MsgMgr.mSyncVersion)) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -127});
                            break;
                        }
                    case "phone":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 3});
                        break;
                    case "voice":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 1});
                        break;
                    case "keyboard":
                        if (UiMgr.this.mIsFeaturesKeyboard) {
                            UiMgr.this.mIsFeaturesKeyboard = false;
                            syncPageUiSet.setKeyboardActions(UiMgr.this.getDiagitalKeyboardActions());
                        } else {
                            UiMgr.this.mIsFeaturesKeyboard = true;
                            syncPageUiSet.setKeyboardActions(UiMgr.this.getFeaturesKeyboardActions());
                        }
                        UiMgr.this.getMsgMgr(context).updateSync(null);
                        break;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onListItemClick(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) (i + 145)});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onSoftKeyClick(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) (i + 28)});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnClick(String str) {
                str.hashCode();
                switch (str) {
                    case "number_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                        break;
                    case "number_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 14});
                        break;
                    case "number_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 15});
                        break;
                    case "number_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 16});
                        break;
                    case "number_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 17});
                        break;
                    case "number_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 18});
                        break;
                    case "number_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 19});
                        break;
                    case "number_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 20});
                        break;
                    case "number_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 21});
                        break;
                    case "number_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 22});
                        break;
                    case "device":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                        break;
                    case "hangup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 4});
                        break;
                    case "pickup":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 5});
                        break;
                    case "ok":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                        break;
                    case "up":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 10});
                        break;
                    case "aux":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 27});
                        break;
                    case "down":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 11});
                        break;
                    case "info":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 6});
                        break;
                    case "left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 25});
                        break;
                    case "menu":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
                        break;
                    case "next":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 9});
                        break;
                    case "prev":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 8});
                        break;
                    case "star":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 23});
                        break;
                    case "well":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 24});
                        break;
                    case "right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 26});
                        break;
                    case "shuff":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 7});
                        break;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnLongClick(String str) {
                str.hashCode();
                switch (str) {
                    case "number_0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 48});
                        break;
                    case "number_1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 49});
                        break;
                    case "number_2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 50});
                        break;
                    case "number_3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 51});
                        break;
                    case "number_4":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 52});
                        break;
                    case "number_5":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 53});
                        break;
                    case "number_6":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 54});
                        break;
                    case "number_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 55});
                        break;
                    case "number_8":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 56});
                        break;
                    case "number_9":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 57});
                        break;
                }
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._81.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, (byte) (i + 18)});
            }
        });
    }

    static /* synthetic */ void lambda$new$0(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_81_tyre_monitor")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -87, 1});
        }
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_81-UiMgr, reason: not valid java name */
    /* synthetic */ void m928lambda$new$1$comhzbhdcanbuscar_81UiMgr(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {
        int i4;
        i4 = i3;
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_81_hill_start_assist":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, (byte) i4});
                return;
            case "ford_alert_tone":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i4 + 7)});
                return;
            case "_81_current_voice_mode":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i4 + 9)});
                return;
            case "_81_turn_signals_setup":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i4 + 3)});
                return;
            case "ford_range_unit":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i4 + 14)});
                return;
            case "_279_temperature_unit":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte) i4});
                getMsgMgr(context).updateSettings(i, i2, i4);
                return;
            case "_81_rain_sensor":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte) i4});
                break;
            case "language_setup":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte) MsgMgr.mLanguageMapArray[i4]});
                break;
            case "brightness":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i4 + 16)});
                break;
            case "_81_traction_control_system":
                if (i4 == 0) {
                    i4 = 2;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) i4});
                break;
            case "parking_assistance":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte) i4});
                break;
            case "_81_park_lock_ctrl":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, (byte) i4});
                break;
            case "ford_message_tone":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i4 + 5)});
                break;
            case "_81_interior_lighting":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte) i4});
                break;
            case "_176_car_setting_title_21":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -79, (byte) i4});
                break;
        }
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_81-UiMgr, reason: not valid java name */
    /* synthetic */ void m929lambda$new$2$comhzbhdcanbuscar_81UiMgr(int i) {
        sendAirCommand(0, i);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_81-UiMgr, reason: not valid java name */
    /* synthetic */ void m930lambda$new$3$comhzbhdcanbuscar_81UiMgr(int i) {
        sendAirCommand(1, i);
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_81-UiMgr, reason: not valid java name */
    /* synthetic */ void m931lambda$new$4$comhzbhdcanbuscar_81UiMgr(int i) {
        sendAirCommand(2, i);
    }

    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_81-UiMgr, reason: not valid java name */
    /* synthetic */ void m932lambda$new$5$comhzbhdcanbuscar_81UiMgr(int i) {
        sendAirCommand(3, i);
    }

    private void sendAirCommand(int i, int i2) {
        String str = this.mAirPageUiSet.getFrontArea().getLineBtnAction()[i][i2];
        str.hashCode();
        switch (str) {
            case "ac_max":
                sendAirCommand(4);
                break;
            case "max_front":
                sendAirCommand(25);
                break;
            case "ac":
                sendAirCommand(2);
                break;
            case "auto":
                sendAirCommand(23);
                break;
            case "dual":
                sendAirCommand(24);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "blow_window":
                sendAirCommand(32);
                break;
            case "in_out_cycle":
                sendAirCommand(3);
                break;
            case "blow_foot":
                sendAirCommand(34);
                break;
            case "blow_head":
                sendAirCommand(33);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    public String[][] getDiagitalKeyboardActions() {
        if (this.mDiagitalKeyboardActions == null) {
            this.mDiagitalKeyboardActions = new String[][]{new String[]{SyncAction.KEYBOARD_NUMBER_1, SyncAction.KEYBOARD_NUMBER_2, SyncAction.KEYBOARD_NUMBER_3}, new String[]{SyncAction.KEYBOARD_NUMBER_4, SyncAction.KEYBOARD_NUMBER_5, SyncAction.KEYBOARD_NUMBER_6}, new String[]{SyncAction.KEYBOARD_NUMBER_7, SyncAction.KEYBOARD_NUMBER_8, SyncAction.KEYBOARD_NUMBER_9}, new String[]{SyncAction.KEYBOARD_STAR, SyncAction.KEYBOARD_NUMBER_0, SyncAction.KEYBOARD_WELL}, new String[]{SyncAction.KEYBOARD_PICKUP, SyncAction.KEYBOARD_HANGUP, "null"}};
        }
        return this.mDiagitalKeyboardActions;
    }

    public String[][] getFeaturesKeyboardActions() {
        if (this.mFeaturesKeyboardActions == null) {
            this.mFeaturesKeyboardActions = new String[][]{new String[]{"null", "up", "null"}, new String[]{"left", SyncAction.KEYBOARD_OK, "right"}, new String[]{"null", "down", "null"}, new String[]{SyncAction.KEYBOARD_INFO, SyncAction.KEYBOARD_MENU, SyncAction.KEYBOARD_DEVICE}, new String[]{SyncAction.KEYBOARD_PREV, SyncAction.KEYBOARD_SHUFF, SyncAction.KEYBOARD_NEXT}};
        }
        return this.mFeaturesKeyboardActions;
    }
}
