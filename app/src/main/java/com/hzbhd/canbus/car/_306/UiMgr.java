package com.hzbhd.canbus.car._306;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private Context mContext;
    private View mMyPanoramicView;
    private ParkPageUiSet mParkPageUiSet;
    private SettingPageUiSet mSettingPageUiSet;
    OnPanoramicItemTouchListener mOnPanoramicItemTouchListener = new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
        public void onTouchItem(MotionEvent motionEvent) {
            int x = (((int) motionEvent.getX()) * 1024) / ((int) UiMgr.this.mContext.getResources().getDimension(R.dimen.dp1024));
            int y = (((int) motionEvent.getY()) * 600) / ((int) UiMgr.this.mContext.getResources().getDimension(R.dimen.dp600));
            int i = x & 255;
            int i2 = (x >> 8) & 255;
            int i3 = y & 255;
            int i4 = (y >> 8) & 255;
            if (motionEvent.getAction() == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte) i2, (byte) i, (byte) i4, (byte) i3});
            } else if (motionEvent.getAction() == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -116, 2, (byte) i2, (byte) i, (byte) i4, (byte) i3});
            } else if (motionEvent.getAction() == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -116, 0, (byte) i2, (byte) i, (byte) i4, (byte) i3});
            }
        }
    };
    OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            if (i == 6) {
                CanbusMsgSender.sendMsg(new byte[]{22, -117, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) (i + 1)});
            }
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            titleSrn.hashCode();
            if (titleSrn.equals("_306_value_10")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) MsgMgr.hour, (byte) i3, (byte) MsgMgr.language});
            } else if (titleSrn.equals("_306_value_9")) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i3, (byte) MsgMgr.minute, (byte) MsgMgr.language});
            }
        }
    };
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i != 0) {
                String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_306_value_11")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) MsgMgr.hour, (byte) MsgMgr.minute, (byte) i3});
                    return;
                }
                return;
            }
            if (UiMgr.this.isDX7()) {
                byte b = (byte) i3;
                CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) (i2 + 4), b});
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, b});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, b});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, b});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, b});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, b});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, b});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, b});
                        break;
                }
                return;
            }
            byte b2 = (byte) i3;
            CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) (i2 + 1), b2});
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, b2});
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, b2});
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, b2});
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirCmd(6);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirCmd(7);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd(2);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd(4);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCmd(11);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAirCmd(12);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirCmd(8);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirCmd(9);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCmd(10);
            } else if (i == 1) {
                UiMgr.this.sendAirCmd(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirCmd(13);
            }
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new MyPanoramicView(context);
        }
        return this.mMyPanoramicView;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
        airUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 40});
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._306.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 14, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 14, 1});
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.mSettingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        parkPageUiSet.setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        intiUi();
    }

    private void intiUi() {
        removeFrontAirButtonByName(this.mContext, "dual");
        if (isDX7()) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_1", "_306_value_2", "_306_value_3"});
            this.mParkPageUiSet.setOnPanoramicItemTouchListener(this.mOnPanoramicItemTouchListener);
        }
        if (isDX5()) {
            this.mParkPageUiSet.setShowRadar(false);
            removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_4", "_306_value_5", "_306_value_6", "_306_value_7", "_306_title_2", "_306_title_3", "_306_value_8"});
            removeMainPrjBtnByName(this.mContext, MainAction.DRIVE_DATA);
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
            removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
            this.mParkPageUiSet.setCusVideoStartX((int) this.mContext.getResources().getDimension(R.dimen.dp96));
        }
        if (getCurrentCarId() == 2) {
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
        }
    }

    private void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCmd(8);
                break;
            case "ac_max":
                sendAirCmd(12);
                break;
            case "rear_defog":
                sendAirCmd(9);
                break;
            case "ac":
                sendAirCmd(11);
                break;
            case "auto":
                sendAirCmd(10);
                break;
            case "dual":
                sendAirCmd(14);
                break;
            case "power":
                sendAirCmd(1);
                break;
            case "in_out_cycle":
                sendAirCmd(13);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._306.UiMgr$14] */
    public void sendAirCmd(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._306.UiMgr.14
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i, 0});
            }
        }.start();
    }

    boolean isDX7() {
        return getCurrentCarId() == 1;
    }

    boolean isDX5() {
        return getCurrentCarId() == 0;
    }
}
