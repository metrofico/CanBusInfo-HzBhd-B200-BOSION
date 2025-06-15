package com.hzbhd.canbus.car._188;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnBubbleClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;


public class UiMgr extends AbstractUiMgr {
    static final String SHARE_188_IS_SUPPORT_PANORAMIC = "share_188_is_support_panoramic";
    private static final String SHARE_188_LANGUAGE = "share_188_language";
    private AirActivity mActivity;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private View mMyPanoramicView;
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_frontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(3, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(3, 0);
        }
    };
    OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_frontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirData(4, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirData(4, 0);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr$$ExternalSyntheticLambda0
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m298lambda$new$1$comhzbhdcanbuscar_188UiMgr(i);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr$$ExternalSyntheticLambda1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m299lambda$new$2$comhzbhdcanbuscar_188UiMgr(i);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr$$ExternalSyntheticLambda2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m300lambda$new$3$comhzbhdcanbuscar_188UiMgr(i);
        }
    };
    OnAirBtnClickListener onAirBtnClickListener_frontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr$$ExternalSyntheticLambda3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public final void onClickItem(int i) {
            m301lambda$new$4$comhzbhdcanbuscar_188UiMgr(i);
        }
    };
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            UiMgr.this.sendAirData(0, 6);
        }
    };
    OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirData(1, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirData(1, 0);
        }
    };
    private byte[] stagedAirConditionKeyState = {0, 0, 0, 0, 0};
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.mDifferent;
        if (i == 1) {
            removeSettingLeftItemByNameList(context, new String[]{"_186_driving_aids"});
            getMsgMgr(context).updateSettingActivity(0, 0, SharePreUtil.getIntValue(context, SHARE_188_LANGUAGE, 0));
            getMsgMgr(context).updateSettingActivity(1, 0, SharePreUtil.getIntValue(context, SHARE_188_IS_SUPPORT_PANORAMIC, 0));
            getMsgMgr(context).updateSettingActivity(1, 1, "null_value", SharePreUtil.getIntValue(context, SHARE_188_IS_SUPPORT_PANORAMIC, 0) == 1);
            return;
        }
        if (i == 2) {
            removeSettingLeftItemByNameList(context, new String[]{"_186_driving_aids"});
            removeSettingLeftItemByNameList(context, new String[]{"vm_golf7_language_setup"});
            getMsgMgr(context).updateSettingActivity(0, 0, SharePreUtil.getIntValue(context, SHARE_188_IS_SUPPORT_PANORAMIC, 0));
            getMsgMgr(context).updateSettingActivity(0, 1, "null_value", SharePreUtil.getIntValue(context, SHARE_188_IS_SUPPORT_PANORAMIC, 0) == 1);
            return;
        }
        if (i == 3) {
            removeSettingLeftItemByNameList(context, new String[]{"vm_golf7_language_setup"});
            removeSettingLeftItemByNameList(context, new String[]{"panorama_setting"});
        } else {
            if (i != 4) {
                return;
            }
            removeSettingLeftItemByNameList(context, new String[]{"vm_golf7_language_setup", "_186_driving_aids"});
            getMsgMgr(context).updateSettingActivity(0, 0, SharePreUtil.getIntValue(context, SHARE_188_IS_SUPPORT_PANORAMIC, 0));
            getMsgMgr(context).updateSettingActivity(0, 1, "null_value", SharePreUtil.getIntValue(context, SHARE_188_IS_SUPPORT_PANORAMIC, 0) == 1);
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
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("support_panorama")) {
                    SharePreUtil.setIntValue(context, UiMgr.SHARE_188_IS_SUPPORT_PANORAMIC, i3);
                    UiMgr.this.getMsgMgr(context).updateSettingActivity(i, i2, i3);
                    UiMgr.this.getMsgMgr(context).updateSettingActivity(i, i2 + 1, "null_value", i3 == 1);
                    UiMgr.this.getMsgMgr(context).updateBubble(context, i3 == 1);
                    return;
                }
                if (titleSrn.equals("vm_golf7_language_setup")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte) i3});
                    SharePreUtil.setIntValue(context, UiMgr.SHARE_188_LANGUAGE, i3);
                    UiMgr.this.getMsgMgr(context).updateSettingActivity(i, i2, i3);
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_186_blind_spot_detection":
                        CanbusMsgSender.sendMsg(new byte[]{22, 83, 1});
                        break;
                    case "_186_moving_object_detection":
                        CanbusMsgSender.sendMsg(new byte[]{22, 81, 1});
                        break;
                    case "_186_lane_departure_detection":
                        CanbusMsgSender.sendMsg(new byte[]{22, 82, 1});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_55_0xE8_data4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListener_frontTop, this.onAirBtnClickListener_frontLeft, this.onAirBtnClickListener_frontRight, this.onAirBtnClickListener_frontBottom});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListener_frontLeft, null, this.onAirTemperatureUpDownClickListener_frontRight});
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._188.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                boolean z = SharePreUtil.getIntValue(context, UiMgr.SHARE_188_IS_SUPPORT_PANORAMIC, 0) == 1;
                parkPageUiSet.setShowRadar(!z);
                parkPageUiSet.setShowCusPanoramicView(z);
            }
        });
        getBubbleUiSet(context).setOnBubbleClickListener(new OnBubbleClickListener() { // from class: com.hzbhd.canbus.car._188.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnBubbleClickListener
            public final void onClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_188-UiMgr, reason: not valid java name */
    /* synthetic */ void m298lambda$new$1$comhzbhdcanbuscar_188UiMgr(int i) {
        if (i != 1) {
            return;
        }
        sendAirData(0, 7);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_188-UiMgr, reason: not valid java name */
    /* synthetic */ void m299lambda$new$2$comhzbhdcanbuscar_188UiMgr(int i) {
        if (i != 0) {
            return;
        }
        sendAirData(0, 4);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_188-UiMgr, reason: not valid java name */
    /* synthetic */ void m300lambda$new$3$comhzbhdcanbuscar_188UiMgr(int i) {
        if (i != 0) {
            return;
        }
        sendAirData(1, 2);
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_188-UiMgr, reason: not valid java name */
    /* synthetic */ void m301lambda$new$4$comhzbhdcanbuscar_188UiMgr(int i) {
        if (i == 0) {
            sendAirData(0, 1);
        } else {
            if (i != 1) {
                return;
            }
            sendAirData(1, 3);
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
}
