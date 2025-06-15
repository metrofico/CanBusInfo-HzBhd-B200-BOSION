package com.hzbhd.canbus.car._240;

import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;

import kotlin.jvm.internal.Intrinsics;


public final class UiMgr extends AbstractUiMgr {
    private final Context mContext;
    private final OnAirBtnClickListener mOnAirBtnClickListenerBottom;
    private final OnAirBtnClickListener mOnAirBtnClickListenerCenter1;
    private final OnAirBtnClickListener mOnAirBtnClickListenerCenter2;
    private final OnAirBtnClickListener mOnAirBtnClickListenerTop;
    private final OnAirPageStatusListener mOnAirPageStatusListener;
    private final OnSettingItemSelectListener mOnSettingItemSelectListener;
    private final OnSettingPageStatusListener mOnSettingPageStatusListener;
    private final ParkPageUiSet mParkPageUiSet;
    private final OnAirTemperatureUpDownClickListener monUpDownClickListenerLeft;
    private final OnAirTemperatureUpDownClickListener monUpDownClickListenerRight;
    private final OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
    private final OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener;

    public UiMgr(Context mContext) {

        this.mContext = mContext;
        OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$setWindSpeedViewOnClickListener$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
            }
        };
        this.setWindSpeedViewOnClickListener = onAirWindSpeedUpDownClickListener;
        OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public final void onStatusChange(int i) {
                UiMgr.m322mOnAirPageStatusListener$lambda0(UiMgr.this, i);
            }
        };
        this.mOnAirPageStatusListener = onAirPageStatusListener;
        OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$monUpDownClickListenerLeft$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
            }
        };
        this.monUpDownClickListenerLeft = onAirTemperatureUpDownClickListener;
        OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener2 = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$monUpDownClickListenerRight$1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
            }
        };
        this.monUpDownClickListenerRight = onAirTemperatureUpDownClickListener2;
        OnAirBtnClickListener onAirBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m321mOnAirBtnClickListenerTop$lambda1(i);
            }
        };
        this.mOnAirBtnClickListenerTop = onAirBtnClickListener;
        OnAirBtnClickListener onAirBtnClickListener2 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m319mOnAirBtnClickListenerCenter1$lambda2(i);
            }
        };
        this.mOnAirBtnClickListenerCenter1 = onAirBtnClickListener2;
        OnAirBtnClickListener onAirBtnClickListener3 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m320mOnAirBtnClickListenerCenter2$lambda3(i);
            }
        };
        this.mOnAirBtnClickListenerCenter2 = onAirBtnClickListener3;
        OnAirBtnClickListener onAirBtnClickListener4 = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m318mOnAirBtnClickListenerBottom$lambda4(i);
            }
        };
        this.mOnAirBtnClickListenerBottom = onAirBtnClickListener4;
        OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public final void onStatusChange(int i) {
                UiMgr.m324mOnSettingPageStatusListener$lambda5(UiMgr.this, i);
            }
        };
        this.mOnSettingPageStatusListener = onSettingPageStatusListener;
        OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m323mOnSettingItemSelectListener$lambda6(UiMgr.this, i, i2, i3);
            }
        };
        this.mOnSettingItemSelectListener = onSettingItemSelectListener;
        OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m325onSettingItemSeekbarSelectListener$lambda7(UiMgr.this, i, i2, i3);
            }
        };
        this.onSettingItemSeekbarSelectListener = onSettingItemSeekbarSelectListener;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(mContext);

        this.mParkPageUiSet = parkPageUiSet;
        final SettingPageUiSet settingUiSet = getSettingUiSet(mContext);
        settingUiSet.setOnSettingItemSelectListener(onSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(onSettingPageStatusListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                UiMgr.m315_init_$lambda8(settingUiSet, UiMgr.this, i, i2);
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(mContext);
        airUiSet.setOnAirPageStatusListener(onAirPageStatusListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{onAirTemperatureUpDownClickListener, null, onAirTemperatureUpDownClickListener2});
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{onAirBtnClickListener, onAirBtnClickListener2, onAirBtnClickListener3, onAirBtnClickListener4});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(onAirWindSpeedUpDownClickListener);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._240.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                UiMgr.m316_init_$lambda9(UiMgr.this, i);
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {

        super.updateUiByDifferentCar(context);
        int currentCarId = getCurrentCarId();
        if (currentCarId == 0 || currentCarId == 1) {
            removeMainPrjBtn(context, 1, 1);
            return;
        }
        if (currentCarId == 2) {
            removeSettingRightItem(context, 0, 3, 3);
        } else {
            if (currentCarId != 3) {
                return;
            }
            removeSettingLeftItem(context, 1, 1);
            removeSettingRightItem(context, 0, 2, 3);
            removeMainPrjBtn(context, 0, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mOnAirPageStatusListener$lambda-0, reason: not valid java name */
    public static final void m322mOnAirPageStatusListener$lambda0(UiMgr this$0, int i) {

        if (i == 1) {
            this$0.sendData(new byte[]{22, -112, 35});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mOnAirBtnClickListenerTop$lambda-1, reason: not valid java name */
    public static final void m321mOnAirBtnClickListenerTop$lambda1(int i) {
        if (i == 0) {
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[1] = -118;
            bArr[2] = 7;
            bArr[3] = (byte) (GeneralAirData.front_defog ? 2 : 1);
            CanbusMsgSender.sendMsg(bArr);
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 0});
            return;
        }
        if (i != 1) {
            return;
        }
        byte[] bArr2 = new byte[4];
        bArr2[0] = 22;
        bArr2[1] = -118;
        bArr2[2] = 6;
        bArr2[3] = (byte) (GeneralAirData.rear_defog ? 2 : 1);
        CanbusMsgSender.sendMsg(bArr2);
        CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mOnAirBtnClickListenerCenter1$lambda-2, reason: not valid java name */
    public static final void m319mOnAirBtnClickListenerCenter1$lambda2(int i) {
        LogUtil.showLog("position:" + i);
        if (i == 0) {
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[1] = -118;
            bArr[2] = 9;
            bArr[3] = (byte) (GeneralAirData.auto ? 2 : 1);
            CanbusMsgSender.sendMsg(bArr);
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mOnAirBtnClickListenerCenter2$lambda-3, reason: not valid java name */
    public static final void m320mOnAirBtnClickListenerCenter2$lambda3(int i) {
        LogUtil.showLog("position:" + i);
        if (i == 0) {
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[1] = -118;
            bArr[2] = 0;
            if (GeneralAirData.power) {
                bArr[3] = 1;
                CanbusMsgSender.sendMsg(bArr);
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mOnAirBtnClickListenerBottom$lambda-4, reason: not valid java name */
    public static final void m318mOnAirBtnClickListenerBottom$lambda4(int i) {
        if (i == 0) {
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[1] = -118;
            bArr[2] = 4;
            bArr[3] = (byte) (GeneralAirData.ac ? 2 : 1);
            CanbusMsgSender.sendMsg(bArr);
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
            return;
        }
        if (i != 1) {
            return;
        }
        byte[] bArr2 = new byte[4];
        bArr2[0] = 22;
        bArr2[1] = -118;
        bArr2[2] = 5;
        bArr2[3] = (byte) (GeneralAirData.in_out_cycle ? 2 : 1);
        CanbusMsgSender.sendMsg(bArr2);
        CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mOnSettingPageStatusListener$lambda-5, reason: not valid java name */
    public static final void m324mOnSettingPageStatusListener$lambda5(UiMgr this$0, int i) {

        if (i == 0) {
            this$0.sendData(new byte[]{22, -112, 64});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mOnSettingItemSelectListener$lambda-6, reason: not valid java name */
    public static final void m323mOnSettingItemSelectListener$lambda6(UiMgr this$0, int i, int i2, int i3) {

        if (i != 0) {
            if (i == 1 && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte) i3});
                return;
            }
            return;
        }
        if (i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 1, (byte) DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, i3 == 1), Settings.System.getInt(this$0.mContext.getContentResolver(), "data0_06", 0), 0, 7)});
        } else {
            if (i2 != 2) {
                return;
            }
            if (this$0.getCurrentCarId() == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 2, (byte) i3});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 3, (byte) i3});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSettingItemSeekbarSelectListener$lambda-7, reason: not valid java name */
    public static final void m325onSettingItemSeekbarSelectListener$lambda7(UiMgr this$0, int i, int i2, int i3) {

        CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 1, (byte) DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, Settings.System.getInt(this$0.mContext.getContentResolver(), "data0_7", 0) == 1), i3, 0, 7)});
    }

    public final ParkPageUiSet getMParkPageUiSet() {
        return this.mParkPageUiSet;
    }

    /* renamed from: lambda-8$sendCarData, reason: not valid java name */
    private static final void m317lambda8$sendCarData(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-8, reason: not valid java name */
    public static final void m315_init_$lambda8(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn, "low_car")) {
            m317lambda8$sendCarData(0);
        } else if (Intrinsics.areEqual(titleSrn, "high_car")) {
            m317lambda8$sendCarData(1);
        }
        Toast.makeText(this$0.mContext, "Success!", Toast.LENGTH_SHORT).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-9, reason: not valid java name */
    public static final void m316_init_$lambda9(UiMgr this$0, int i) {

        switch (i + 1) {
            case 1:
                this$0.sendPanoramaData(1);
                break;
            case 2:
                this$0.sendPanoramaData(2);
                break;
            case 3:
                this$0.sendPanoramaData(3);
                break;
            case 4:
                this$0.sendPanoramaData(4);
                break;
            case 5:
                this$0.sendPanoramaData(5);
                break;
            case 6:
                this$0.sendPanoramaData(6);
                break;
            case 7:
                this$0.sendPanoramaData(7);
                break;
            case 8:
                this$0.sendPanoramaData(8);
                break;
        }
    }

    private final void sendPanoramaData(int d0) {
        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) d0});
    }
}
