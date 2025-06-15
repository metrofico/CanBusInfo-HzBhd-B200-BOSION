package com.hzbhd.canbus.car._304;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car_cus._304.util.Util;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Objects;


public class UiMgr extends AbstractUiMgr {
    public static final int PANO_BTN_ALL = 4;
    public static final int PANO_BTN_EXIT = 6;
    public static final int PANO_BTN_FRONT = 0;
    public static final int PANO_BTN_LEFT = 2;
    public static final int PANO_BTN_REAR = 1;
    public static final int PANO_BTN_RIGHT = 3;
    public static final int PANO_BTN_SINGLE = 7;
    public static final int PANO_BTN_VOICE = 5;
    private MsgMgr mMsgMgr;
    private CusPanoramicView mPanoramicView;
    private int mVideoMode;
    private final String TAG = "_304_UiMgr";
    private final int MSG_SEND_AIR_COMMAND_UP = 16;
    private byte[] mAmbientCommand = {22, -79, 0, 0, 0, 0, 0};
    private byte[] mAvmCommand = {22, -80, 64, 0};
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._304.UiMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 16) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) message.arg1, 0});
            }
        }
    };
    private int mDiiferentId = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mPanoramicView == null) {
            CusPanoramicView cusPanoramicView = new CusPanoramicView(context);
            this.mPanoramicView = cusPanoramicView;
            cusPanoramicView.initView(getParkPageUiSet(context));
        }
        return this.mPanoramicView;
    }

    public UiMgr(final Context context) {
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m477lambda$new$0$comhzbhdcanbuscar_304UiMgr(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                this.f$0.m478lambda$new$1$comhzbhdcanbuscar_304UiMgr(settingUiSet, i, i2, i3);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i, int i2) {
                Objects.requireNonNull(settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn());
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public final void onClickItem(int i, int i2) {
                Objects.requireNonNull(settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn());
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i) {
                this.f$0.m479lambda$new$4$comhzbhdcanbuscar_304UiMgr(i);
            }
        });
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public final void addViewToWindows() throws Resources.NotFoundException {
                this.f$0.m480lambda$new$5$comhzbhdcanbuscar_304UiMgr(context, parkPageUiSet);
            }
        });
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public final void addViewToWindows() {
                this.f$0.m481lambda$new$6$comhzbhdcanbuscar_304UiMgr();
            }
        });
        final AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._304.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m482lambda$new$7$comhzbhdcanbuscar_304UiMgr(airUiSet, i);
            }
        }, null, null, null});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._304.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(3);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(2);
            }
        }, null, null});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._304.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(10);
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_304-UiMgr, reason: not valid java name */
    /* synthetic */ void m477lambda$new$0$comhzbhdcanbuscar_304UiMgr(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        switch (titleSrn) {
            case "_304_atoms_lamp_b":
                byte[] bArr = this.mAmbientCommand;
                bArr[4] = (byte) i3;
                CanbusMsgSender.sendMsg(bArr);
                break;
            case "_304_atoms_lamp_g":
                byte[] bArr2 = this.mAmbientCommand;
                bArr2[3] = (byte) i3;
                CanbusMsgSender.sendMsg(bArr2);
                break;
            case "_304_atoms_lamp_r":
                byte[] bArr3 = this.mAmbientCommand;
                bArr3[2] = (byte) i3;
                CanbusMsgSender.sendMsg(bArr3);
                break;
            case "_250_ambient_light_brightness":
                byte[] bArr4 = this.mAmbientCommand;
                bArr4[5] = (byte) i3;
                CanbusMsgSender.sendMsg(bArr4);
                break;
        }
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_304-UiMgr, reason: not valid java name */
    /* synthetic */ void m478lambda$new$1$comhzbhdcanbuscar_304UiMgr(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_304_atoms_lamp_control")) {
            byte[] bArr = this.mAmbientCommand;
            bArr[6] = (byte) (i3 + 1);
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    /* renamed from: lambda$new$4$com-hzbhd-canbus-car-_304-UiMgr, reason: not valid java name */
    /* synthetic */ void m479lambda$new$4$comhzbhdcanbuscar_304UiMgr(int i) {
        if (i == 0) {
            sendImageView(0);
            return;
        }
        if (i == 1) {
            sendImageView(1);
            return;
        }
        if (i == 2) {
            sendImageView(2);
            return;
        }
        if (i == 3) {
            sendImageView(3);
            return;
        }
        if (i == 4) {
            sendImageMode(0);
        } else if (i == 6) {
            sendKeyAvmClose();
        } else {
            if (i != 7) {
                return;
            }
            sendImageMode(2);
        }
    }

    /* renamed from: lambda$new$5$com-hzbhd-canbus-car-_304-UiMgr, reason: not valid java name */
    /* synthetic */ void m480lambda$new$5$comhzbhdcanbuscar_304UiMgr(Context context, ParkPageUiSet parkPageUiSet) throws Resources.NotFoundException {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen._304_radar_layout_width);
        int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.dp100);
        int i = displayMetrics.widthPixels - dimensionPixelOffset;
        int i2 = (int) ((displayMetrics.heightPixels / displayMetrics.widthPixels) * i);
        Log.i("_304_UiMgr", "UiMgr: \nstartX:" + dimensionPixelOffset + "\nstartY:" + dimensionPixelOffset2 + "\nvideoWidth:" + i + "\nvideoHeight:" + i2);
        parkPageUiSet.setCusVideoStartX(dimensionPixelOffset);
        parkPageUiSet.setCusVideoStartY(dimensionPixelOffset2);
        parkPageUiSet.setCusVideoWidth(i);
        parkPageUiSet.setCusVideoHeight(i2);
    }

    /* renamed from: lambda$new$6$com-hzbhd-canbus-car-_304-UiMgr, reason: not valid java name */
    /* synthetic */ void m481lambda$new$6$comhzbhdcanbuscar_304UiMgr() {
        FutureUtil.instance.setReversingTrackSwitch(this.mDiiferentId == 0);
        FutureUtil.instance.setReversingBaseline(this.mDiiferentId == 0);
    }

    /* renamed from: lambda$new$7$com-hzbhd-canbus-car-_304-UiMgr, reason: not valid java name */
    /* synthetic */ void m482lambda$new$7$comhzbhdcanbuscar_304UiMgr(AirPageUiSet airPageUiSet, int i) {
        sendAirCommand(airPageUiSet.getFrontArea().getLineBtnAction()[0][i]);
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "ptc_heating":
                sendAirCommand(29);
                break;
            case "ac":
                sendAirCommand(23);
                break;
            case "cycle":
                if (GeneralAirData.in_out_cycle) {
                    sendAirCommand(27);
                    break;
                } else {
                    sendAirCommand(26);
                    break;
                }
            case "power":
                sendAirCommand(28);
                break;
            case "blow_head_foot":
                sendAirCommand(49);
                break;
            case "blow_foot":
                sendAirCommand(50);
                break;
            case "blow_head":
                sendAirCommand(48);
                break;
            case "blow_window_foot":
                sendAirCommand(51);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i, 1});
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private void sendImageView(int i) {
        byte[] bArr = this.mAvmCommand;
        byte b = (byte) (bArr[2] & 207);
        bArr[2] = b;
        bArr[2] = (byte) (((i << 4) & 48) | b);
        CanbusMsgSender.sendMsg(bArr);
    }

    private void sendImageSwitch(int i) {
        byte[] bArr = this.mAvmCommand;
        byte b = (byte) (bArr[2] & 191);
        bArr[2] = b;
        bArr[2] = (byte) (((i << 6) & 64) | b);
        CanbusMsgSender.sendMsg(bArr);
    }

    private void sendImageMode(int i) {
        byte[] bArr = this.mAvmCommand;
        byte b = (byte) (bArr[3] & 243);
        bArr[3] = b;
        bArr[3] = (byte) (((i << 2) & 12) | b);
        CanbusMsgSender.sendMsg(bArr);
    }

    private void sendKeyAvmClose() {
        FutureUtil.instance.reqMcuKey(new byte[]{-47, 0});
        Util.sendAvmCommand(0);
    }
}
