package com.hzbhd.canbus.car._193;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private int eachId;
    private AirPageUiSet mAirPageUiSet;
    private FrontArea mFrontArea;
    private ParkPageUiSet mParkPageUiSet;
    private MsgMgr msgMgr;
    private UiMgr uiMgr;
    int a = 0;
    int b = 0;
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, 0});
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, 0});
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, 0});
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, 0});
            }
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (UiMgr.this.b == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, 0});
                UiMgr.this.b = 1;
            } else if (UiMgr.this.b == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, 0});
                UiMgr.this.b = 0;
            }
        }
    };
    int c = 0;
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 0});
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 17, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 17, 0});
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, 0});
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 20, 0});
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 14, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 15, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 15, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 16, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 16, 0});
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.eachId;
        if (i == 1 || i == 4 || i == 12 || i == 15) {
            removeMainPrjBtnByName(context, "air");
        }
        int i2 = this.eachId;
        if (i2 == 10 || i2 == 15) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        int i3 = this.eachId;
        if (i3 == 3 || i3 == 4 || i3 == 7 || i3 == 10 || i3 == 14 || i3 == 15 || i3 == 18) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_0"});
        }
        int i4 = this.eachId;
        if (i4 != 7 && i4 != 1) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_1"});
        }
        int i5 = this.eachId;
        if (i5 == 1 || i5 == 3 || i5 == 4 || i5 == 10 || i5 == 14 || i5 == 15 || i5 == 18) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_2"});
        }
        int i6 = this.eachId;
        if (i6 != 2 && i6 != 5 && i6 != 8 && i6 != 9 && i6 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_2_0"});
        }
        int i7 = this.eachId;
        if (i7 == 1 || i7 == 3 || i7 == 4 || i7 == 7 || i7 == 10 || i7 == 14 || i7 == 15 || i7 == 18) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_2_1"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_4_0"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_4_1"});
        }
        int i8 = this.eachId;
        if (i8 != 2 && i8 != 5 && i8 != 6 && i8 != 8 && i8 != 9) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_3_0"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_3_1"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_3_2"});
        }
        if (this.eachId != 4) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_2_2"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_4"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_5"});
        }
        int i9 = this.eachId;
        if (i9 != 4 && i9 != 7) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_3"});
        }
        int i10 = this.eachId;
        if (i10 != 5 && i10 != 7 && i10 != 8 && i10 != 9) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_6"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_7"});
        }
        int i11 = this.eachId;
        if (i11 != 5 && i11 != 7 && i11 != 8 && i11 != 9 && i11 != 6 && i11 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_8"});
        }
        if (this.eachId != 6) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_9"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_A"});
        }
        if (this.eachId != 7) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_2_3"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_2_4"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_2_5"});
        }
        int i12 = this.eachId;
        if (i12 != 7 && i12 != 9) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_B"});
        }
        int i13 = this.eachId;
        if (i13 != 7 && i13 != 11 && i13 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_5_0"});
        }
        int i14 = this.eachId;
        if (i14 != 9 && i14 != 13 && i14 != 16 && i14 != 17) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_6_0"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_6_1"});
        }
        int i15 = this.eachId;
        if (i15 != 9 && i15 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_C"});
        }
        int i16 = this.eachId;
        if (i16 != 11 && i16 != 13 && i16 != 16 && i16 != 17) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_7_0"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_7_1"});
        }
        int i17 = this.eachId;
        if (i17 != 11 && i17 != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_5_1"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_5_2"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_5_3"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_5_4"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_8_0"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_8_1"});
        }
        if (this.eachId != 13) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_8_2"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_8_3"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_8_4"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_8_5"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_8_5_3"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_6_2"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_7_2"});
        }
        int i18 = this.eachId;
        if (i18 == 1 || i18 == 4 || i18 == 7 || i18 == 10 || i18 == 12 || i18 == 14 || i18 == 15 || i18 == 18) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_6_3"});
        }
        int i19 = this.eachId;
        if (i19 != 5 && i19 != 6 && i19 != 8 && i19 != 9 && i19 != 11 && i19 != 13 && i19 != 16 && i19 != 17) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_9"});
        }
        int i20 = this.eachId;
        if (i20 != 2 && i20 != 5 && i20 != 6 && i20 != 7 && i20 != 8 && i20 != 9) {
            removeSettingRightItemByNameList(context, new String[]{"_253_click_into_the_panorama"});
        }
        int i21 = this.eachId;
        if (i21 != 1 && i21 != 14) {
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_0_0"});
        }
        int i22 = this.eachId;
        if (i22 != 18 && i22 != 18) {
            removeMainPrjBtnByName(context, MainAction.DRIVE_DATA);
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_D"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_E"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_1_F"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_4_2"});
            removeSettingLeftItemByNameList(context, new String[]{"_1193_setting_10"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_6_4"});
            removeSettingRightItemByNameList(context, new String[]{"_1193_setting_6_5"});
        }
        if (getSettingUiSet(context).getList().size() <= 0 || !"car_set".equals(getSettingUiSet(context).getList().get(0).getTitleSrn())) {
            return;
        }
        for (int i23 = 0; i23 < getSettingUiSet(context).getList().get(0).getItemList().size(); i23++) {
            if ("_253_front_radar_switch".equals(getSettingUiSet(context).getList().get(0).getItemList().get(i23).getTitleSrn())) {
                getMsgMgr(context).updateSetting(0, i23, SharePreUtil.getBoolValue(context, "share_key_253_front_radar_enable", true) ? 1 : 0);
            } else if ("_253_rear_radar_switch".equals(getSettingUiSet(context).getList().get(0).getItemList().get(i23).getTitleSrn())) {
                getMsgMgr(context).updateSetting(0, i23, SharePreUtil.getBoolValue(context, "share_key_253_rear_radar_enable", true) ? 1 : 0);
            }
        }
    }

    public UiMgr(final Context context) {
        int eachId = getEachId();
        this.eachId = eachId;
        if (eachId == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 1});
        }
        if (this.eachId == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 2});
        }
        if (this.eachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 3});
        }
        if (this.eachId == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 4});
        }
        if (this.eachId == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 5});
        }
        if (this.eachId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 6});
        }
        if (this.eachId == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 7});
        }
        if (this.eachId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 8});
        }
        if (this.eachId == 14) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 9});
        }
        if (this.eachId == 16) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 10});
        }
        if (this.eachId == 17) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 11});
        }
        if (this.eachId == 18) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
        }
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.mAirPageUiSet = airUiSet;
        FrontArea frontArea = airUiSet.getFrontArea();
        this.mFrontArea = frontArea;
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
        this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
        this.mFrontArea.setWindMaxLevel(7);
        this.mFrontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 11, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -88, 11, 0});
            }
        });
        this.mFrontArea.setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (UiMgr.this.a == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 7, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 7, 0});
                    UiMgr.this.a = 1;
                    return;
                }
                if (UiMgr.this.a == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 0});
                    UiMgr.this.a = 2;
                } else if (UiMgr.this.a == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 9, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 9, 0});
                    UiMgr.this.a = 3;
                } else if (UiMgr.this.a == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 10, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 10, 0});
                    UiMgr.this.a = 0;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                if (UiMgr.this.a == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 7, 1});
                    UiMgr.this.a = 1;
                    return;
                }
                if (UiMgr.this.a == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 1});
                    UiMgr.this.a = 2;
                } else if (UiMgr.this.a == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 9, 1});
                    UiMgr.this.a = 3;
                } else if (UiMgr.this.a == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 10, 1});
                    UiMgr.this.a = 0;
                }
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.3
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
            java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
             */
            /* JADX WARN: Removed duplicated region for block: B:247:0x05be  */
            /* JADX WARN: Removed duplicated region for block: B:4:0x002d  */
            /* JADX WARN: Removed duplicated region for block: B:53:0x0117  */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r22, int r23, int r24) {
                /*
                    Method dump skipped, instructions count: 2212
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._193.UiMgr.AnonymousClass3.onClickItem(int, int, int):void");
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_1193_setting_1":
                        String titleSrn2 = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                        titleSrn2.hashCode();
                        if (!titleSrn2.equals("_1193_setting_1_C")) {
                            if (titleSrn2.equals("_1193_setting_1_F")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -57, 3, (byte) i3});
                                break;
                            }
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte) i3});
                            break;
                        }
                        break;
                    case "_1193_setting_4":
                        String titleSrn3 = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                        titleSrn3.hashCode();
                        if (titleSrn3.equals("_1193_setting_4_1")) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) (((byte) i3) + 1)});
                            break;
                        }
                        break;
                    case "_1193_setting_5":
                        String titleSrn4 = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                        titleSrn4.hashCode();
                        if (titleSrn4.equals("_1193_setting_5_3")) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i3});
                            break;
                        }
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("enter_panorama")) {
                    String titleSrn2 = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                    titleSrn2.hashCode();
                    if (titleSrn2.equals("_253_click_into_the_panorama")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -89, 17, 1});
                    }
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_1193_setting_9")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -1, 1});
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        parkPageUiSet.setShowRadar(true);
        this.mParkPageUiSet.setShowTrack(true);
        this.mParkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                UiMgr.this.getMsgMgr(context).initRadar();
            }
        });
        this.mParkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._193.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                UiMgr.this.getMsgMgr(context).updateParkingBtn(i);
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 1});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 2});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 3});
                } else if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 4});
                } else {
                    if (i != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -89, 16});
                }
            }
        });
        this.mParkPageUiSet.setShowRadar(true);
        this.mParkPageUiSet.setShowPanoramic(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
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
