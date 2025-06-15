package com.hzbhd.canbus.car._186;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnBubbleClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.util.LogUtil;
import java.util.Iterator;
import java.util.List;


public class UiMgr extends AbstractUiMgr {
    static final String SHARE_188_IS_SUPPORT_PANORAMIC = "share_188_is_support_panoramic";
    private static final String SHARE_188_LANGUAGE = "share_188_language";
    private int eachID;
    private AirActivity mActivity;
    private Context mContext;
    private int mDifferent;
    private MsgMgr mMsgMgr;
    private View mMyPanoramicView;
    private MsgMgr msgMgr;
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_frontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(3, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(3, 0);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_frontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(4, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(4, 0);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_rear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(5, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(5, 0);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr$$ExternalSyntheticLambda0
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            this.f$0.m294lambda$new$1$comhzbhdcanbuscar_186UiMgr(i);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr$$ExternalSyntheticLambda1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            this.f$0.m295lambda$new$2$comhzbhdcanbuscar_186UiMgr(i);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr$$ExternalSyntheticLambda2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            this.f$0.m296lambda$new$3$comhzbhdcanbuscar_186UiMgr(i);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr$$ExternalSyntheticLambda3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            this.f$0.m297lambda$new$4$comhzbhdcanbuscar_186UiMgr(i);
        }
    };
    OnAirBtnClickListener RearBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (LogUtil.log5()) {
                LogUtil.d("click this ");
            }
            UiMgr.this.sendAirData(0, 5);
            UiMgr.this.sendAirData(1, 5);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData(1, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData(1, 1);
        }
    };
    private byte[] stagedAirConditionKeyState = {0, 0, 0, 0, 0, 0};

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.eachID;
        if (i != 4 && i != 5 && i != 6 && i != 7 && i != 10 && i != 16 && i != 17 && i != 18) {
            removeMainPrjBtnByName(context, "air");
        }
        int i2 = this.eachID;
        if (i2 != 5 && i2 != 7 && i2 != 13 && i2 != 16) {
            removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
        }
        if (this.eachID == 13) {
            removeDriveDateItemForTitles(context, new String[]{"a_current_speed"});
        }
        int i3 = this.eachID;
        if (i3 != 8 && i3 != 9 && i3 != 10 && i3 != 11 && i3 != 12 && i3 != 14 && i3 != 4 && i3 != 17 && i3 != 18) {
            removeSettingRightItemByNameList(context, new String[]{"vm_golf7_language_setup"});
        }
        int i4 = this.eachID;
        if (i4 != 2 && i4 != 7 && i4 != 15 && i4 != 16) {
            removeMainPrjBtnByName(context, MainAction.AMPLIFIER);
            removeSettingLeftItemByNameList(context, new String[]{"amplifier_setting"});
        }
        if (this.eachID != 15) {
            removeSettingLeftItemByNameList(context, new String[]{"_186_Rear_Entertainment_System"});
        }
        int i5 = this.eachID;
        if (i5 != 7 && i5 != 13) {
            removeSettingLeftItemByNameList(context, new String[]{"_186_Original_car_settings", "_186_Original_car_settings2"});
        }
        if (this.eachID != 3) {
            removeSettingRightItemByNameList(context, new String[]{"_186_lane_departure_detection", "_186_blind_spot_detection"});
        }
        int i6 = this.eachID;
        if (i6 != 3 && i6 != 9 && i6 != 12) {
            removeSettingRightItemByNameList(context, new String[]{"_186_moving_object_detection"});
        }
        int i7 = this.eachID;
        if (i7 != 5 && i7 != 16) {
            removeMainPrjBtnByName(context, MainAction.ORIGINAL_CAR_DEVICE);
        }
        int i8 = this.eachID;
        if (i8 != 5 && i8 != 17 && i8 != 18) {
            removeDriveDateItemForTitles(this.mContext, new String[]{"_186_time"});
        }
        if (this.eachID != 16) {
            removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
        }
        if (this.eachID != 7) {
            removeSettingRightItemByNameList(context, new String[]{"_186_amplifier_settings"});
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new MyPanoramicView(context);
        }
        return this.mMyPanoramicView;
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        this.mDifferent = getCurrentCarId();
        this.eachID = getEachId();
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        final PauseableThread pauseableThread = new PauseableThread();
        pauseableThread.start();
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_186_bose_centerpoint":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) i3});
                        break;
                    case "_186_driver_sound_field":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte) i3});
                        break;
                    case "_186_Auto_light_off_time_setting":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 87, (byte) i3});
                        break;
                    case "_186_Auto_light_sensitivity":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 85, (byte) i3});
                        break;
                    case "support_panorama":
                        SharePreUtil.setIntValue(context, UiMgr.SHARE_188_IS_SUPPORT_PANORAMIC, i3);
                        UiMgr.this.getMsgMgr(context).updateSettingActivity(i, i2, i3);
                        UiMgr.this.getMsgMgr(context).updateSettingActivity(i, i2 + 1, "null_value", i3 == 1);
                        UiMgr.this.getMsgMgr(context).updateBubble(context, i3 == 1);
                        break;
                    case "_186_amplifier_settings":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte) i3});
                        pauseableThread.pauseThread();
                        pauseableThread.setSelectPos(i3);
                        pauseableThread.resumeThread();
                        break;
                    case "vm_golf7_language_setup":
                        if (i3 <= 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte) i3});
                            SharePreUtil.setIntValue(context, UiMgr.SHARE_188_LANGUAGE, i3);
                            UiMgr.this.getMsgMgr(context).updateSettingActivity(i, i2, i3);
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte) (i3 + 129)});
                            break;
                        }
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_186_Rear_Entertainment_System_Interface":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte) i3});
                        break;
                    case "_186_blind_spot_detection":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 83, 1});
                        break;
                    case "_186_Left_speaker_output":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 52, (byte) i3});
                        break;
                    case "_186_Unlock_and_turn_on_the_lights":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 84, (byte) i3});
                        break;
                    case "_186_auto_retreat":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 89, (byte) i3});
                        break;
                    case "_186_Left_monitor_power_supply":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 51, (byte) i3});
                        break;
                    case "_186_switch_Unlock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 91, (byte) i3});
                        break;
                    case "_186_moving_object_detection":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 81, 1});
                        break;
                    case "_186_lane_departure_detection":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 82, 1});
                        break;
                    case "_186_Right_speaker_output":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 54, (byte) i3});
                        break;
                    case "_186_Automatically_power_on_the_monitor":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte) i3});
                        break;
                    case "_186_Right_monitor_power_supply":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 53, (byte) i3});
                        break;
                    case "_186_Vehicle_speed_linkage_intermittent_wiper":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 86, (byte) i3});
                        break;
                    case "_186_Smart_key_unlock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 88, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_55_0xE8_data4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                int i4;
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_186_asl")) {
                    int i5 = i3 - UiMgr.this.mMsgMgr.mAmpAslValueNow;
                    i4 = i5 <= 0 ? i5 : 33;
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte) (i4 >= 0 ? i4 : 49)});
                    UiMgr.this.mMsgMgr.mAmpAslValueNow = i3;
                    return;
                }
                if (titleSrn.equals("_186_surround_volume")) {
                    int i6 = i3 - UiMgr.this.mMsgMgr.mAmpSurroundValueNow;
                    i4 = i6 <= 0 ? i6 : 33;
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte) (i4 >= 0 ? i4 : 49)});
                    UiMgr.this.mMsgMgr.mAmpSurroundValueNow = i3;
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                if (amplifierPosition == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
                    UiMgr.this.sendAmplifierCommand(36, i, GeneralAmplifierData.leftRight);
                } else if (amplifierPosition == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
                    UiMgr.this.sendAmplifierCommand(37, i, GeneralAmplifierData.frontRear);
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                if (amplifierBand == AmplifierActivity.AmplifierBand.VOLUME) {
                    UiMgr.this.sendAmplifierCommand(33, i, GeneralAmplifierData.volume);
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.BASS) {
                    UiMgr.this.sendAmplifierCommand(34, i, GeneralAmplifierData.bandBass);
                } else if (amplifierBand == AmplifierActivity.AmplifierBand.TREBLE) {
                    UiMgr.this.sendAmplifierCommand(35, i, GeneralAmplifierData.bandTreble);
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        int i = this.eachID;
        if (i != 4 && i != 10 && i != 17 && i != 18) {
            airUiSet.getFrontArea().setAllBtnCanClick(false);
            airUiSet.getFrontArea().setCanSetWindSpeed(false);
            airUiSet.getFrontArea().setCanSetLeftTemp(false);
            airUiSet.getFrontArea().setCanSetRightTemp(false);
        }
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListener_frontTop, this.onAirBtnClickListener_frontLeft, this.onAirBtnClickListener_frontRight, this.onAirBtnClickListener_frontBottom});
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirData(0, 6);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirData(0, 6);
            }
        });
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListener_frontLeft, null, this.onAirTemperatureUpDownClickListener_frontRight, null, this.onAirTemperatureUpDownClickListener_rear});
        airUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
            }
        });
        int i2 = this.eachID;
        airUiSet.setHaveRearArea(i2 == 17 || i2 == 18);
        airUiSet.getRearArea().setAllBtnCanClick(true);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.RearBottomBtnListener});
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                boolean z = SharePreUtil.getIntValue(context, UiMgr.SHARE_188_IS_SUPPORT_PANORAMIC, 0) == 1;
                parkPageUiSet.setShowRadar(!z);
                parkPageUiSet.setShowCusPanoramicView(z);
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 3});
            }
        });
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 40});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 104});
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 106});
            }
        });
        getBubbleUiSet(context).setOnBubbleClickListener(new OnBubbleClickListener() { // from class: com.hzbhd.canbus.car._186.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnBubbleClickListener
            public final void onClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
            }
        });
        Log.i("ljq", "UiMgr: " + getCurrentCarId());
        if (getCurrentCarId() == 1) {
            getParkPageUiSet(context).setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._186.UiMgr.11
                @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
                public void onTouchItem(MotionEvent motionEvent) {
                    int x = (((int) motionEvent.getX()) * 1024) / ((int) context.getResources().getDimension(R.dimen.dp1024));
                    int y = (((int) motionEvent.getY()) * 1024) / ((int) context.getResources().getDimension(R.dimen.dp600));
                    int i3 = x & 255;
                    int i4 = (x >> 8) & 255;
                    int i5 = y & 255;
                    int i6 = (y >> 8) & 255;
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte) i4, (byte) i3, (byte) i6, (byte) i5});
                    } else if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 0, (byte) i4, (byte) i3, (byte) i6, (byte) i5});
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierCommand(int i, int i2, int i3) {
        int i4;
        int i5 = i2 - i3;
        if (i5 > 0) {
            i4 = 33;
        } else if (i5 >= 0) {
            return;
        } else {
            i4 = 49;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, (byte) i4});
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_186-UiMgr, reason: not valid java name */
    /* synthetic */ void m294lambda$new$1$comhzbhdcanbuscar_186UiMgr(int i) {
        if (i == 0) {
            sendAirData(0, 7);
        } else {
            if (i != 1) {
                return;
            }
            sendAirData(0, 1);
        }
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_186-UiMgr, reason: not valid java name */
    /* synthetic */ void m295lambda$new$2$comhzbhdcanbuscar_186UiMgr(int i) {
        if (i != 0) {
            return;
        }
        sendAirData(0, 4);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_186-UiMgr, reason: not valid java name */
    /* synthetic */ void m296lambda$new$3$comhzbhdcanbuscar_186UiMgr(int i) {
        if (i != 0) {
            return;
        }
        sendAirData(1, 2);
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_186-UiMgr, reason: not valid java name */
    /* synthetic */ void m297lambda$new$4$comhzbhdcanbuscar_186UiMgr(int i) {
        if (i == 0) {
            sendAirData(0, 5);
            return;
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            sendAirData(1, 3);
        } else if (getMsgMgr(this.mContext).cycle == 1) {
            sendAirData(0, 3);
            sendAirData(0, 2);
        } else if (getMsgMgr(this.mContext).cycle == 2) {
            sendAirData(0, 2);
            sendAirData(0, 3);
        } else if (getMsgMgr(this.mContext).cycle == 0) {
            sendAirData(0, 3);
        }
    }

    void sendAirData(int i, int i2) {
        byte[] bArr = this.stagedAirConditionKeyState;
        bArr[i] = (byte) DataHandleUtils.setIntByteWithBit(bArr[i], i2, true);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, this.stagedAirConditionKeyState));
        byte[] bArr2 = this.stagedAirConditionKeyState;
        bArr2[i] = (byte) DataHandleUtils.setIntByteWithBit(bArr2[i], i2, false);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, this.stagedAirConditionKeyState));
    }

    void sendAirData(int i, int i2, int i3, int i4) {
        byte[] bArr = this.stagedAirConditionKeyState;
        bArr[i3] = (byte) DataHandleUtils.setIntByteWithBit(bArr[i3], i4, false);
        byte[] bArr2 = this.stagedAirConditionKeyState;
        bArr2[i] = (byte) DataHandleUtils.setIntByteWithBit(bArr2[i], i2, true);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, this.stagedAirConditionKeyState));
        byte[] bArr3 = this.stagedAirConditionKeyState;
        bArr3[i] = (byte) DataHandleUtils.setIntByteWithBit(bArr3[i], i2, false);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, this.stagedAirConditionKeyState));
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

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
