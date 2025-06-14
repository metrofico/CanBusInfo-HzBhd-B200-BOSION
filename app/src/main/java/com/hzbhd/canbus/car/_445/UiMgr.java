package com.hzbhd.canbus.car._445;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
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
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private Context mContext;
    MsgMgr mMsgMgr;
    private View mMyPanoramicView;
    private ParkPageUiSet mParkPageUiSet;
    private SettingPageUiSet mSettingPageUiSet;
    public String ID383_VOICE_LANGUAGE = "ID383_VOICE_LANGUAGE";
    OnPanoramicItemTouchListener mOnPanoramicItemTouchListener = new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.3
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
    OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            if (i == 6) {
                CanbusMsgSender.sendMsg(new byte[]{22, -117, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) (i + 1)});
            }
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.5
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
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i != uiMgr.getSettingLeftIndexes(uiMgr.mContext, "car_set")) {
                String titleSrn = UiMgr.this.mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_306_value_11")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) MsgMgr.hour, (byte) MsgMgr.minute, (byte) i3});
                }
            } else if (UiMgr.this.isDX7()) {
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
            } else {
                byte b2 = (byte) i3;
                CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) (i2 + 1), b2});
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, b2});
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, b2});
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, b2});
                }
            }
            UiMgr uiMgr2 = UiMgr.this;
            if (i == uiMgr2.getSettingLeftIndexes(uiMgr2.mContext, "_306_360")) {
                UiMgr uiMgr3 = UiMgr.this;
                if (i2 == uiMgr3.getSettingRightIndex(uiMgr3.mContext, "_306_360", "_306_360")) {
                    SharePreUtil.setIntValue(UiMgr.this.mContext, "ID306_360BUTTON_VISIBILITY_STATE", i3);
                    UiMgr uiMgr4 = UiMgr.this;
                    uiMgr4.getMsgMgr(uiMgr4.mContext).updateSettings(i, i2, SharePreUtil.getIntValue(UiMgr.this.mContext, "ID306_360BUTTON_VISIBILITY_STATE", 0));
                    UiMgr uiMgr5 = UiMgr.this;
                    uiMgr5.getMsgMgr(uiMgr5.mContext).showDialogAndRestartApp(UiMgr.this.mContext.getString(R.string._306_360_dialog));
                }
            }
            UiMgr uiMgr6 = UiMgr.this;
            if (i == uiMgr6.getSettingLeftIndexes(uiMgr6.mContext, "_445_language_setting")) {
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_445_language_setting", "_306_voice_language")) {
                    if (i3 == 0) {
                        UiMgr uiMgr8 = UiMgr.this;
                        uiMgr8.getMsgMgr(uiMgr8.mContext);
                        MsgMgr.mLanguage = "_zh";
                    } else {
                        UiMgr uiMgr9 = UiMgr.this;
                        uiMgr9.getMsgMgr(uiMgr9.mContext);
                        MsgMgr.mLanguage = "_en";
                    }
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.this.ID383_VOICE_LANGUAGE, i3);
                    UiMgr uiMgr10 = UiMgr.this;
                    uiMgr10.getMsgMgr(uiMgr10.mContext).updateSettings(i, i2, SharePreUtil.getIntValue(UiMgr.this.mContext, "ID383_VOICE_LANGUAGE", 0));
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_445_language_setting", "_445_language_setting1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22});
                }
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAirCmd(6);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAirCmd(7);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd(3);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd(2);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCmd(5);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCmd(4);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.10
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
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirCmd(8);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirCmd(9);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.13
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
        if (isDx5Low()) {
            SharePreUtil.setIntValue(this.mContext, "ID306_360BUTTON_VISIBILITY_STATE", 1);
        }
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new MyPanoramicView(context);
        }
        return this.mMyPanoramicView;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        removeDriveDateItemForTitles(context, new String[]{"_306_title_4"});
        if (getCurrentCarId() == 1) {
            removeMainPrjBtnByName(context, "air");
            removeMainPrjBtnByName(context, MainAction.TIRE_INFO);
        }
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
        airUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -112, 40});
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._445.UiMgr.2
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
        this.mParkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._445.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public final void onTouchItem(MotionEvent motionEvent) {
                UiMgr.lambda$new$0(motionEvent);
            }
        });
    }

    static /* synthetic */ void lambda$new$0(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = 600 - ((int) motionEvent.getY());
        if (y >= 0 && x <= 1024) {
            int action = motionEvent.getAction();
            if (action == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte) (x >> 8), (byte) x, (byte) (y >> 8), (byte) y});
            } else if (action == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -116, 0, (byte) (x >> 8), (byte) x, (byte) (y >> 8), (byte) y});
            } else {
                if (action != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -116, 2, (byte) (x >> 8), (byte) x, (byte) (y >> 8), (byte) y});
            }
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        intiUi();
    }

    private void intiUi() {
        removeFrontAirButtonByName(this.mContext, "dual");
        if (isDX7()) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_306_voice_language"});
            removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_1", "_306_value_2", "_306_value_3"});
            this.mParkPageUiSet.setOnPanoramicItemTouchListener(this.mOnPanoramicItemTouchListener);
        }
        if (isDX5High()) {
            this.mParkPageUiSet.setShowRadar(false);
            removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_3", "_306_value_5", "_306_value_6", "_306_value_7", "_306_title_2", "_306_title_3", "_306_value_8"});
            removeMainPrjBtnByName(this.mContext, MainAction.DRIVE_DATA);
            removeMainPrjBtnByName(this.mContext, MainAction.TIRE_INFO);
            this.mParkPageUiSet.setCusVideoStartX((int) this.mContext.getResources().getDimension(R.dimen.dp96));
        }
        if (getCurrentCarId() == 2) {
            removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
        }
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_306_360"), getSettingRightIndex(this.mContext, "_306_360", "_306_360"), SharePreUtil.getIntValue(this.mContext, "ID306_360BUTTON_VISIBILITY_STATE", 0));
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
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._445.UiMgr$14] */
    public void sendAirCmd(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._445.UiMgr.14
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

    boolean isDX5High() {
        return getCurrentCarId() == 0;
    }

    boolean isDx5Low() {
        return getCurrentCarId() == 3;
    }

    boolean isDx3() {
        return getCurrentCarId() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
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
