package com.hzbhd.canbus.car._25;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Timer;
import java.util.TimerTask;


public class UiMgr extends AbstractUiMgr {
    static final String SHARE_25_IS_SUPPORT_PANORAMIC = "share_25_is_support_panoramic";
    private static Timer mTimer;
    private static TimerTask mTimerTask;
    private Context mContext;
    private MsgMgr mMsgMgr;
    private View mMyPanoramicView;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(0, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(1, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(2, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(3, i);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(31);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(30);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(33);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(32);
        }
    };
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new MyPanoramicView(context, getCurrentCarId());
        }
        return this.mMyPanoramicView;
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(28);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(29);
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirCommand(25);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirCommand(25);
            }
        });
        getMsgMgr(context).updateSetting(0, 0, SharePreUtil.getIntValue(context, "share_25_language", 0));
        getMsgMgr(context).updateSetting(1, 0, SharePreUtil.getBoolValue(context, SHARE_25_IS_SUPPORT_PANORAMIC, false) ? 1 : 0);
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte) i3});
                        SharePreUtil.setIntValue(UiMgr.this.mContext, "share_25_language", i3);
                        UiMgr.this.getMsgMgr(context).updateSetting(i, i2, i3);
                        return;
                    }
                    return;
                }
                if (i == 1 && i2 == 0) {
                    SharePreUtil.setBoolValue(context, UiMgr.SHARE_25_IS_SUPPORT_PANORAMIC, i3 == 1);
                    UiMgr.this.getMsgMgr(context).updateSetting(i, i2, i3);
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_55_0xE8_data4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
                }
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._25.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                boolean boolValue = SharePreUtil.getBoolValue(context, UiMgr.SHARE_25_IS_SUPPORT_PANORAMIC, false);
                parkPageUiSet.setShowRadar(!boolValue);
                parkPageUiSet.setShowCusPanoramicView(boolValue);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveAirBtn(int i, int i2) {
        String str = getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[i][i2];
        if (TextUtils.isEmpty(str)) {
        }
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(21);
                break;
            case "rear_defog":
                sendAirCommand(22);
                break;
            case "blow_positive":
                sendAirCommand(25);
                break;
            case "ac":
                sendAirCommand(17);
                break;
            case "auto":
                sendAirCommand(20);
                break;
            case "dual":
                sendAirCommand(23);
                break;
            case "power":
                sendAirCommand(16);
                break;
            case "in_out_cycle":
                sendAirCommand(19);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        byte b = (byte) i;
        sendUpDownCommand(new byte[]{22, -126, b, 1}, new byte[]{22, -126, b, 0}, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private void sendUpDownCommand(byte[] bArr, final byte[] bArr2, long j) {
        CanbusMsgSender.sendMsg(bArr);
        startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._25.UiMgr.12
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(bArr2);
                UiMgr.this.stopTimer();
            }
        }, j);
    }

    private void startTimer(TimerTask timerTask, long j) {
        if (timerTask == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimerTask = timerTask;
        mTimer.schedule(timerTask, j);
    }

    private void startTimer(TimerTask timerTask, long j, int i) {
        if (timerTask == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimerTask = timerTask;
        mTimer.schedule(timerTask, j, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopTimer() {
        TimerTask timerTask = mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            mTimerTask = null;
        }
        Timer timer = mTimer;
        if (timer != null) {
            timer.cancel();
            mTimer = null;
        }
    }
}
