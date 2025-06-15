package com.hzbhd.canbus.car._253;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private AirPageUiSet mAirPageUiSet;
    private int[] mCanBusInfoInt;
    private FrontArea mFrontArea;
    private MsgMgr mMsgMgr;
    private ParkPageUiSet mParkPageUiSet;
    private UiMgr mUiMgr;
    private int mWindModle = 7;
    int a = 0;
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, 1});
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, 1});
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, 1});
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 1});
            }
            if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 1});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, 1});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                if (GeneralAirData.aqs) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, 1});
                } else {
                    if (GeneralAirData.aqs) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, 1});
                }
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 1});
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            if (UiMgr.this.eachId == 12) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 15, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            if (UiMgr.this.eachId == 12) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 16, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 1});
            }
        }
    };
    private int eachId = getEachId();

    static /* synthetic */ int access$008(UiMgr uiMgr) {
        int i = uiMgr.mWindModle;
        uiMgr.mWindModle = i + 1;
        return i;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.eachId;
        if (i == 2 || i == 8) {
            removeMainPrjBtnByName(context, "air");
        }
        int i2 = this.eachId;
        if (i2 != 9 && i2 != 10) {
            removeSettingLeftItemByNameList(context, new String[]{"enter_panorama"});
        }
        if (this.eachId != 10) {
            removeSettingRightItemByNameList(context, new String[]{"_253_Lane_deviation_warning"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Blind_spot_warning"});
            removeSettingRightItemByNameList(context, new String[]{"_253_front_radar_switch"});
        }
        switch (this.eachId) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                removeMainPrjBtnByName(context, MainAction.SETTING);
                break;
        }
        if (this.eachId == 9) {
            removeSettingLeftItemByNameList(context, new String[]{"car_set"});
        }
        int i3 = this.eachId;
        if (i3 != 8 && i3 != 12 && i3 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_253_initial_perspective"});
        }
        int i4 = this.eachId;
        if (i4 != 10 && i4 != 12 && i4 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_253_rear_radar_switch"});
        }
        int i5 = this.eachId;
        if (i5 != 11 && i5 != 12 && i5 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_253_Language"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Theme"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Temperature_unit"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Unit_of_speed_and_fuel_consumption"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Tire_Pressure_Monitoring"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Parking_unlocked"});
        }
        int i6 = this.eachId;
        if (i6 != 12 && i6 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_253_HeadLight_delay"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Remote_lock_reminder"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Ambient_light_Lightness"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Automatic_folding_Rearview_mirror"});
        }
        if (this.eachId != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_253_Ambient_light_mode"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Rearview_mirror_backlight_brightness"});
            removeSettingRightItemByNameList(context, new String[]{"_253_Rearview_mirror_automatically_anti_glare"});
        }
        if (getSettingUiSet(context).getList().size() <= 0 || !"car_set".equals(getSettingUiSet(context).getList().get(0).getTitleSrn())) {
            return;
        }
        for (int i7 = 0; i7 < getSettingUiSet(context).getList().get(0).getItemList().size(); i7++) {
            if ("_253_front_radar_switch".equals(getSettingUiSet(context).getList().get(0).getItemList().get(i7).getTitleSrn())) {
                getMsgMgr(context).updateSetting(0, i7, SharePreUtil.getBoolValue(context, "share_key_253_front_radar_enable", true) ? 1 : 0);
            } else if ("_253_rear_radar_switch".equals(getSettingUiSet(context).getList().get(0).getItemList().get(i7).getTitleSrn())) {
                getMsgMgr(context).updateSetting(0, i7, SharePreUtil.getBoolValue(context, "share_key_253_rear_radar_enable", true) ? 1 : 0);
            }
        }
    }

    public UiMgr(final Context context) {
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.mAirPageUiSet = airUiSet;
        FrontArea frontArea = airUiSet.getFrontArea();
        this.mFrontArea = frontArea;
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
        this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
        this.mFrontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) UiMgr.access$008(UiMgr.this), 1});
                if (UiMgr.this.mWindModle > 10) {
                    UiMgr.this.mWindModle = 7;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte) UiMgr.access$008(UiMgr.this), 1});
                if (UiMgr.this.mWindModle > 10) {
                    UiMgr.this.mWindModle = 7;
                }
            }
        });
        this.mFrontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 11, 1});
            }
        });
        if (this.eachId == 3) {
            this.mAirPageUiSet.getFrontArea().setWindMaxLevel(6);
        }
        int i = this.eachId;
        if (i == 3 || i == 12 || i == 13) {
            this.mFrontArea.setAllBtnCanClick(true);
            this.mFrontArea.setCanSetRightTemp(true);
            this.mFrontArea.setCanSetLeftTemp(true);
            this.mFrontArea.setCanSetWindSpeed(true);
            this.mFrontArea.setDisableBtnArray(new String[]{AirBtnAction.ION, AirBtnAction.MAX_FRONT, AirBtnAction.ECO, AirBtnAction.AC_MAX, AirBtnAction.REAR});
        } else {
            this.mFrontArea.setAllBtnCanClick(false);
            this.mFrontArea.setCanSetRightTemp(false);
            this.mFrontArea.setCanSetLeftTemp(false);
            this.mFrontArea.setCanSetWindSpeed(false);
        }
        if (this.eachId == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, -96, 2});
        }
        if (this.eachId == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, -96, 3});
        }
        if (this.eachId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, -96, 4});
        }
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.3
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0073  */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r23, int r24, int r25) {
                /*
                    Method dump skipped, instructions count: 774
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._253.UiMgr.AnonymousClass3.onClickItem(int, int, int):void");
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i2, int i3, int i4) {
                UiMgr.this.getMsgMgr(context).updateSetting(i2, i3, i4);
                if (i2 != 0) {
                    return;
                }
                String titleSrn = settingUiSet.getList().get(i2).getItemList().get(i3).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_253_Rearview_mirror_backlight_brightness")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 17, (byte) i4});
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i2, int i3) {
                if (i2 == UiMgr.this.getSettingLeftIndexes(context, "enter_panorama") && i3 == UiMgr.this.getSettingRightIndex(context, "enter_panorama", "_253_click_into_the_panorama")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, 2});
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                UiMgr.this.getMsgMgr(context).initRadar();
            }
        });
        this.mParkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i2) {
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 1});
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        break;
                    case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 0, 0});
                        return;
                    default:
                        return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (i2 + 1)});
            }
        });
        this.mParkPageUiSet.setShowRadar(isSupportRadar());
        this.mParkPageUiSet.setShowPanoramic(isSupportPanoramic());
        getPanelKeyPageUiSet(context).setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._253.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int i2) {
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 2});
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 0});
                }
            }
        });
    }

    private boolean isSupportPanoramic() {
        int i = this.eachId;
        return i == 9 || i == 10;
    }

    private boolean isSupportRadar() {
        switch (this.eachId) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return false;
        }
    }

    private boolean isSupportTrack() {
        int i = this.eachId;
        if (i != 1 && i != 2 && i != 3) {
            switch (i) {
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private UiMgr getmUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void sendVoiceControlInfo0x01(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -87, 1, (byte) i});
    }

    public void sendVoiceControlInfoAir(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -87, 1, (byte) i, (byte) i2});
    }

    public void sendVoiceControlInfoAir(int i, int i2, int i3) {
        CanbusMsgSender.sendMsg(new byte[]{22, -87, (byte) i, (byte) i2, (byte) i3});
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
}
