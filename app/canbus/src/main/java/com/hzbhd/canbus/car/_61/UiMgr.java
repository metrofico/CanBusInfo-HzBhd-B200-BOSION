package com.hzbhd.canbus.car._61;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    public static final String KEY_LANGUAGE_POS_TAG = "key.laguage.pos.tag";
    private Context mContext;
    private MsgMgr mMsgMgr;
    private TimerUtil mTimerUtil;
    private byte[] mTrackRequestCommand;
    private TimerTask mTrackRequestTimerTask;
    private final int MALIBU_MAX_WIND_SPEED = 8;
    private final int MSG_AIR_KEY_UP = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._61.UiMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 0});
        }
    };
    private int mDifferentId = getCurrentCarId();

    static /* synthetic */ void lambda$new$4() {
    }

    public UiMgr(Context context) {
        this.mContext = context;
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                Objects.requireNonNull(settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn());
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.lambda$new$1(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                UiMgr.lambda$new$2(settingUiSet, i, i2);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m920lambda$new$3$comhzbhdcanbuscar_61UiMgr(settingUiSet, i, i2, i3);
            }
        });
        getParkPageUiSet(context).setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public final void addViewToWindows() {
                UiMgr.lambda$new$4();
            }
        });
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m921lambda$new$5$comhzbhdcanbuscar_61UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m922lambda$new$6$comhzbhdcanbuscar_61UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m923lambda$new$7$comhzbhdcanbuscar_61UiMgr(airUiSet, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m924lambda$new$8$comhzbhdcanbuscar_61UiMgr(airUiSet, i);
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(4);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(5);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(20);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(21);
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(7);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(6);
            }
        });
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m925lambda$new$9$comhzbhdcanbuscar_61UiMgr(i);
            }
        }, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m919lambda$new$10$comhzbhdcanbuscar_61UiMgr(i);
            }
        }});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(97);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(98);
            }
        }, null});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(100);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(99);
            }
        });
        OnStartPageUiSet onStartPageUiSet = getOnStartPageUiSet(context);
        onStartPageUiSet.setOnOnStartStatusChangeListener(new OnOnStartStatusChangeListener() { // from class: com.hzbhd.canbus.car._61.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener
            public void onWirelessSwithc() {
                MyLog.temporaryTracking("进入安吉星");
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 65});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 66});
            }
        });
        onStartPageUiSet.setOnOnStarClickListener(new OnOnStarClickListener() { // from class: com.hzbhd.canbus.car._61.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
            public void init() {
                if (UiMgr.this.getState() == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
            public void numberClick(int i) {
                if (UiMgr.this.getState() == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) (i | 128)});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
            public void handOn(String str) {
                if (UiMgr.this.getState() == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 2});
                } else if (str.length() > 0) {
                    CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -122}, FutureUtil.instance.str2Bcd(UiMgr.this.fillSpaces(str))));
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
            public void handOff() {
                if (UiMgr.this.getState() == 2 || UiMgr.this.getState() == 3 || UiMgr.this.getState() == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOnStarClickListener
            public void exit() {
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
            }
        });
        setUiDifferent(context, airUiSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ void lambda$new$1(com.hzbhd.canbus.ui_set.SettingPageUiSet r2, int r3, int r4, int r5) {
        /*
            java.util.List r2 = r2.getList()
            java.lang.Object r2 = r2.get(r3)
            com.hzbhd.canbus.ui_set.SettingPageUiSet$ListBean r2 = (com.hzbhd.canbus.ui_set.SettingPageUiSet.ListBean) r2
            java.util.List r2 = r2.getItemList()
            java.lang.Object r2 = r2.get(r4)
            com.hzbhd.canbus.ui_set.SettingPageUiSet$ListBean$ItemListBean r2 = (com.hzbhd.canbus.ui_set.SettingPageUiSet.ListBean.ItemListBean) r2
            java.lang.String r2 = r2.getTitleSrn()
            int r3 = r2.hashCode()
            r4 = 478200384(0x1c80c240, float:8.520542E-22)
            r0 = 1
            r1 = 0
            if (r3 == r4) goto L33
            r4 = 1401027296(0x5381fae0, float:1.1165195E12)
            if (r3 == r4) goto L29
            goto L3d
        L29:
            java.lang.String r3 = "compass_zoom"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L3d
            r2 = r0
            goto L3e
        L33:
            java.lang.String r3 = "warning_volume_set"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L3d
            r2 = r1
            goto L3e
        L3d:
            r2 = -1
        L3e:
            if (r2 == 0) goto L41
            goto L53
        L41:
            r2 = 3
            byte[] r2 = new byte[r2]
            r3 = 22
            r2[r1] = r3
            r3 = -120(0xffffffffffffff88, float:NaN)
            r2[r0] = r3
            r3 = 2
            byte r4 = (byte) r5
            r2[r3] = r4
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r2)
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._61.UiMgr.lambda$new$1(com.hzbhd.canbus.ui_set.SettingPageUiSet, int, int, int):void");
    }

    static /* synthetic */ void lambda$new$2(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_279_restore_factory_default")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, ByteCompanionObject.MIN_VALUE, 1});
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_61-UiMgr, reason: not valid java name */
    /* synthetic */ void m920lambda$new$3$comhzbhdcanbuscar_61UiMgr(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        char c = 65535;
        switch (titleSrn.hashCode()) {
            case -2044134219:
                if (titleSrn.equals("personalization_by_driver")) {
                    c = 0;
                    break;
                }
                break;
            case -2003824814:
                if (titleSrn.equals("_61_radar_switch")) {
                    c = 1;
                    break;
                }
                break;
            case -1949342696:
                if (titleSrn.equals("prevent_open_door_auto_lock")) {
                    c = 2;
                    break;
                }
                break;
            case -1775953625:
                if (titleSrn.equals("remote_window_control")) {
                    c = 3;
                    break;
                }
                break;
            case -1647100313:
                if (titleSrn.equals("delay_lock")) {
                    c = 4;
                    break;
                }
                break;
            case -1627575416:
                if (titleSrn.equals("leave_car_auto_lock")) {
                    c = 5;
                    break;
                }
                break;
            case -1538471658:
                if (titleSrn.equals("convenience_get_off_option")) {
                    c = 6;
                    break;
                }
                break;
            case -1466177363:
                if (titleSrn.equals("air_launcher_mode")) {
                    c = 7;
                    break;
                }
                break;
            case -1387219473:
                if (titleSrn.equals("adaptive_forward_lighting")) {
                    c = '\b';
                    break;
                }
                break;
            case -1382834175:
                if (titleSrn.equals("remote_control_seat_auto_heat")) {
                    c = '\t';
                    break;
                }
                break;
            case -1337774785:
                if (titleSrn.equals("_61_reverse_tilt_mirror")) {
                    c = '\n';
                    break;
                }
                break;
            case -1331709104:
                if (titleSrn.equals("adaptive_cruise_start_reminder")) {
                    c = 11;
                    break;
                }
                break;
            case -1226835875:
                if (titleSrn.equals("_220_air_quality_sensor")) {
                    c = '\f';
                    break;
                }
                break;
            case -1164051160:
                if (titleSrn.equals("key_forgot_remain")) {
                    c = '\r';
                    break;
                }
                break;
            case -877543893:
                if (titleSrn.equals("_61_adapter_front_light")) {
                    c = 14;
                    break;
                }
                break;
            case -873936231:
                if (titleSrn.equals("_279_temperature_unit")) {
                    c = 15;
                    break;
                }
                break;
            case -743253170:
                if (titleSrn.equals("remote_control_unlock_light_feedback")) {
                    c = 16;
                    break;
                }
                break;
            case -703666556:
                if (titleSrn.equals("_61_rear_pass_warning")) {
                    c = 17;
                    break;
                }
                break;
            case -667783574:
                if (titleSrn.equals("air_partition_temperature")) {
                    c = 18;
                    break;
                }
                break;
            case -572185957:
                if (titleSrn.equals("_61_revers_back_wiper_auto_launcher")) {
                    c = 19;
                    break;
                }
                break;
            case -539410636:
                if (titleSrn.equals("remote_start_air")) {
                    c = 20;
                    break;
                }
                break;
            case -526007558:
                if (titleSrn.equals("flank_blind_zone_warning")) {
                    c = 21;
                    break;
                }
                break;
            case -501414644:
                if (titleSrn.equals("_1168_set_ac_system_ac_rmote_start_seat_auto_heat")) {
                    c = 22;
                    break;
                }
                break;
            case -440182422:
                if (titleSrn.equals("lock_big_light_delay_set")) {
                    c = 23;
                    break;
                }
                break;
            case -393117929:
                if (titleSrn.equals("stop_auto_unlock")) {
                    c = 24;
                    break;
                }
                break;
            case -300418790:
                if (titleSrn.equals("_61_evlevated_idle")) {
                    c = 25;
                    break;
                }
                break;
            case -174199675:
                if (titleSrn.equals("remote_start_rear_air")) {
                    c = 26;
                    break;
                }
                break;
            case -163192997:
                if (titleSrn.equals("_61_forward_collision_system")) {
                    c = 27;
                    break;
                }
                break;
            case -58415930:
                if (titleSrn.equals("seat_auto_heat")) {
                    c = 28;
                    break;
                }
                break;
            case -36611461:
                if (titleSrn.equals("auto_wiper")) {
                    c = 29;
                    break;
                }
                break;
            case 16748135:
                if (titleSrn.equals("_61_hands_free_liftgate_control")) {
                    c = 30;
                    break;
                }
                break;
            case 78971422:
                if (titleSrn.equals("start_auto_lock")) {
                    c = 31;
                    break;
                }
                break;
            case 102584022:
                if (titleSrn.equals("language_setup")) {
                    c = ' ';
                    break;
                }
                break;
            case 207472786:
                if (titleSrn.equals("auto_rear_view_mirror_fold")) {
                    c = '!';
                    break;
                }
                break;
            case 261992690:
                if (titleSrn.equals("find_car_light_function")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 364441366:
                if (titleSrn.equals("_61_rear_seat_reminder")) {
                    c = '#';
                    break;
                }
                break;
            case 371113033:
                if (titleSrn.equals("reverse_rear_view_mirror_auto_tilt")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 529054061:
                if (titleSrn.equals("auto_re_lock_doors")) {
                    c = '%';
                    break;
                }
                break;
            case 591765596:
                if (titleSrn.equals("_61_movement_steering")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 626049822:
                if (titleSrn.equals("_194_automatic_mode_wind")) {
                    c = '\'';
                    break;
                }
                break;
            case 683849548:
                if (titleSrn.equals("_61_advanced_hill_start_assist")) {
                    c = '(';
                    break;
                }
                break;
            case 708150576:
                if (titleSrn.equals("_61_vehicle_type_setup")) {
                    c = ')';
                    break;
                }
                break;
            case 1061082397:
                if (titleSrn.equals("_143_0x76_d0_b6")) {
                    c = '*';
                    break;
                }
                break;
            case 1172774528:
                if (titleSrn.equals("remote_unlock_set")) {
                    c = '+';
                    break;
                }
                break;
            case 1186455828:
                if (titleSrn.equals("left_or_right_hand_traffic")) {
                    c = ',';
                    break;
                }
                break;
            case 1296262722:
                if (titleSrn.equals("_61_alert_type")) {
                    c = '-';
                    break;
                }
                break;
            case 1321211278:
                if (titleSrn.equals("close_unlock_set")) {
                    c = '.';
                    break;
                }
                break;
            case 1439613768:
                if (titleSrn.equals("remote_start_seat_cold")) {
                    c = '/';
                    break;
                }
                break;
            case 1439752788:
                if (titleSrn.equals("remote_start_seat_heat")) {
                    c = '0';
                    break;
                }
                break;
            case 1578036129:
                if (titleSrn.equals("back_windows_auto_defog")) {
                    c = '1';
                    break;
                }
                break;
            case 1690202822:
                if (titleSrn.equals("call_memory_position")) {
                    c = '2';
                    break;
                }
                break;
            case 1700125011:
                if (titleSrn.equals("_61_line_change_warning")) {
                    c = '3';
                    break;
                }
                break;
            case 1851660334:
                if (titleSrn.equals("remote_control_unlock_light_or_horn_feedback")) {
                    c = '4';
                    break;
                }
                break;
            case 1952102175:
                if (titleSrn.equals("front_windows_auto_defog")) {
                    c = '5';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                return;
            case 1:
            case '*':
                CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i3});
                return;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                return;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) i3});
                return;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                return;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                return;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                return;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 6, (byte) i3});
                return;
            case '\b':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                return;
            case '\t':
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte) i3});
                return;
            case '\n':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                return;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte) i3});
                return;
            case '\f':
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte) i3});
                return;
            case '\r':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                return;
            case 14:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte) i3});
                return;
            case 15:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 9, (byte) i3});
                return;
            case 16:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                return;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte) i3});
                return;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 2, (byte) i3});
                return;
            case 19:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte) i3});
                return;
            case 20:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 67, (byte) i3});
                return;
            case 21:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte) i3});
                return;
            case 22:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte) i3});
                return;
            case 23:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                return;
            case 24:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                return;
            case 25:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 10, (byte) i3});
                return;
            case 26:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 68, (byte) i3});
                return;
            case 27:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte) i3});
                return;
            case 28:
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 8, (byte) i3});
                return;
            case 29:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) i3});
                return;
            case 30:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte) i3});
                return;
            case 31:
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                return;
            case ' ':
                sendLanguageCmd(i3);
                return;
            case '!':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                break;
            case '\"':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                break;
            case '#':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) i3});
                break;
            case '$':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                break;
            case '%':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                break;
            case '&':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte) i3});
                break;
            case '\'':
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 0, (byte) i3});
                break;
            case '(':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte) i3});
                break;
            case ')':
                CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte) i3});
                break;
            case '+':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) i3});
                break;
            case ',':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                break;
            case '-':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) i3});
                break;
            case '.':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                break;
            case '/':
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 66, (byte) i3});
                break;
            case '0':
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 65, (byte) i3});
                break;
            case '1':
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 3, (byte) i3});
                break;
            case '2':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte) i3});
                break;
            case '3':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte) i3});
                break;
            case '4':
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                break;
            case '5':
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 4, (byte) i3});
                break;
        }
    }

    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_61-UiMgr, reason: not valid java name */
    /* synthetic */ void m921lambda$new$5$comhzbhdcanbuscar_61UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    /* renamed from: lambda$new$6$com-hzbhd-canbus-car-_61-UiMgr, reason: not valid java name */
    /* synthetic */ void m922lambda$new$6$comhzbhdcanbuscar_61UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[1][i]);
    }

    /* renamed from: lambda$new$7$com-hzbhd-canbus-car-_61-UiMgr, reason: not valid java name */
    /* synthetic */ void m923lambda$new$7$comhzbhdcanbuscar_61UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[2][i]);
    }

    /* renamed from: lambda$new$8$com-hzbhd-canbus-car-_61-UiMgr, reason: not valid java name */
    /* synthetic */ void m924lambda$new$8$comhzbhdcanbuscar_61UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[3][i]);
    }

    /* renamed from: lambda$new$9$com-hzbhd-canbus-car-_61-UiMgr, reason: not valid java name */
    /* synthetic */ void m925lambda$new$9$comhzbhdcanbuscar_61UiMgr(int i) {
        sendAirCommand(0, i);
    }

    /* renamed from: lambda$new$10$com-hzbhd-canbus-car-_61-UiMgr, reason: not valid java name */
    /* synthetic */ void m919lambda$new$10$comhzbhdcanbuscar_61UiMgr(int i) {
        sendAirCommand(3, i);
    }

    private void setUiDifferent(Context context, AirPageUiSet airPageUiSet) {
        removeSettingLeftItemByNameList(context, new String[]{"compass"});
        if (this.mDifferentId == 20) {
            airPageUiSet.getFrontArea().setWindMaxLevel(8);
            removeFrontAirButtonByName(context, AirBtnAction.AC_AUTO);
        } else {
            removeFrontAirButtonByName(context, "sync");
        }
        if (this.mDifferentId == 21) {
            removeSettingRightItemByNameList(context, new String[]{"_143_0x76_d0_b6"});
        } else {
            removeSettingRightItemByNameList(context, new String[]{"_61_radar_switch", "_61_rear_seat_reminder", "_61_hands_free_liftgate_control", "_61_alert_type", "_61_forward_collision_system"});
        }
        if (this.mDifferentId == 22) {
            removeSettingRightItemByNameList(context, new String[]{"remote_control_seat_auto_heat", "_1168_set_ac_system_ac_rmote_start_seat_auto_heat", "adaptive_forward_lighting"});
        } else {
            removeSettingRightItemByNameList(context, new String[]{"seat_auto_heat", "_61_adapter_front_light", "_61_movement_steering", "_61_rear_pass_warning", "_61_line_change_warning"});
        }
        if (this.mDifferentId != 24) {
            removeSettingRightItemByNameList(context, new String[]{"adaptive_cruise_start_reminder"});
        }
        int i = this.mDifferentId;
        if (i != 24 && i != 21) {
            removeSettingRightItemByNameList(context, new String[]{"remote_start_seat_heat", "remote_start_seat_cold", "remote_start_air", "remote_start_rear_air", "_61_evlevated_idle"});
        }
        int i2 = this.mDifferentId;
        if (i2 == 23 || i2 == 20) {
            removeSettingRightItemByNameList(context, new String[]{"_1168_set_ac_system_ac_rmote_start_seat_auto_heat"});
        } else {
            removeSettingRightItemByNameList(context, new String[]{"remote_control_seat_auto_heat"});
        }
        int i3 = this.mDifferentId;
        if (i3 == 20 || i3 == 25) {
            removeSettingRightItemByNameList(context, new String[]{"_61_reverse_tilt_mirror"});
        } else {
            removeSettingRightItemByNameList(context, new String[]{"reverse_rear_view_mirror_auto_tilt"});
        }
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(22);
                break;
            case "ac_auto":
                sendAirCommand(2);
                break;
            case "rear_defog":
                sendAirCommand(23);
                break;
            case "ac":
                sendAirCommand(1);
                break;
            case "sync":
                sendAirCommand(13);
                break;
            case "power":
                sendAirCommand(24);
                break;
            case "setup":
                getMsgMgr(this.mContext).startSettingActivity();
                break;
            case "blow_window":
                sendAirCommand(12);
                break;
            case "in_out_cycle":
                sendAirCommand(3);
                break;
            case "blow_head_foot":
                sendAirCommand(9);
                break;
            case "blow_foot":
                sendAirCommand(11);
                break;
            case "blow_head":
                sendAirCommand(8);
                break;
            case "blow_window_foot":
                sendAirCommand(10);
                break;
        }
    }

    private void sendAirCommand(int i, int i2) {
        String str = getAirUiSet(this.mContext).getRearArea().getLineBtnAction()[i][i2];
        str.hashCode();
        switch (str) {
            case "rear_auto":
                sendAirCommand(104);
                break;
            case "rear_lock":
                sendAirCommand(106);
                break;
            case "rear_power":
                sendAirCommand(105);
                break;
            case "blow_head_foot":
                sendAirCommand(102);
                break;
            case "blow_foot":
                sendAirCommand(103);
                break;
            case "blow_head":
                sendAirCommand(101);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, (byte) i});
        this.mHandler.sendEmptyMessageDelayed(0, 100L);
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerUtil getTimerUtil() {
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        return this.mTimerUtil;
    }

    private TimerTask getTrackRequestTimerTask(Context context) {
        if (this.mTrackRequestTimerTask == null) {
            this.mTrackRequestTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._61.UiMgr.9
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (CommUtil.isMiscReverse()) {
                        CanbusMsgSender.sendMsg(UiMgr.this.getTrackRequestCommand());
                    } else {
                        UiMgr.this.getTimerUtil().stopTimer();
                    }
                }
            };
        }
        return this.mTrackRequestTimerTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getTrackRequestCommand() {
        if (this.mTrackRequestCommand == null) {
            this.mTrackRequestCommand = new byte[]{22, -112, 38};
        }
        return this.mTrackRequestCommand;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String fillSpaces(String str) {
        if (str.length() < 20) {
            int length = 20 - str.length();
            for (int i = 0; i < length; i++) {
                str = str + " ";
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getState() {
        return GeneralOnStartData.mOnStarStatus;
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

    public void sendLanguageCmd(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i});
        SharePreUtil.setIntValue(this.mContext, KEY_LANGUAGE_POS_TAG, i);
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
}
