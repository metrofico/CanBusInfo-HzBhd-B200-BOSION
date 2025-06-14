package com.hzbhd.canbus.car._56;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private Context mContext;
    private int mCurrentCarId;
    private MsgMgr mMsgMgr;
    private int mOrientation;
    private ParkPageUiSet mParkPageUiSet;
    private int mFrontLeftBlowModel = 9;
    private int mFrontRightBlowModel = 9;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, (byte) (1 ^ (GeneralAirData.ac ? 1 : 0))});
            } else {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(14);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(16);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(17);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.16
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAirCommand(18);
        }
    };

    static /* synthetic */ int access$008(UiMgr uiMgr) {
        int i = uiMgr.mFrontLeftBlowModel;
        uiMgr.mFrontLeftBlowModel = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(UiMgr uiMgr) {
        int i = uiMgr.mFrontRightBlowModel;
        uiMgr.mFrontRightBlowModel = i + 1;
        return i;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        int currentCarId = getCurrentCarId();
        this.mCurrentCarId = currentCarId;
        if (currentCarId == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 33, 2});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, 2});
        }
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        this.mOrientation = context.getResources().getConfiguration().orientation;
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, null, null});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                int i = GeneralAirData.front_wind_level - 1;
                if (i < 0) {
                    i = 0;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte) i});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                int i = GeneralAirData.front_wind_level + 1;
                if (i > 7) {
                    i = 7;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte) i});
            }
        });
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirCommand(UiMgr.access$008(uiMgr));
                if (UiMgr.this.mFrontLeftBlowModel > 10 && UiMgr.this.mFrontLeftBlowModel < 23) {
                    UiMgr.this.mFrontLeftBlowModel = 23;
                }
                if (UiMgr.this.mFrontLeftBlowModel > 24) {
                    UiMgr.this.mFrontLeftBlowModel = 9;
                }
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirCommand(UiMgr.access$208(uiMgr));
                if (UiMgr.this.mFrontRightBlowModel > 10 && UiMgr.this.mFrontRightBlowModel < 23) {
                    UiMgr.this.mFrontRightBlowModel = 23;
                }
                if (UiMgr.this.mFrontRightBlowModel > 24) {
                    UiMgr.this.mFrontRightBlowModel = 9;
                }
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                int id = settingUiSet.getList().get(i).getItemList().get(i2).getId();
                switch (i) {
                    case 2:
                        if (id == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 108, 1, (byte) (i3 + 1)});
                            break;
                        } else if (id == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 108, 2, (byte) i3});
                            break;
                        } else if (id == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 108, 3, (byte) i3});
                            break;
                        } else if (id == 3) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 108, 4, (byte) i3});
                            break;
                        } else if (id == 4) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 108, 5, (byte) i3});
                            break;
                        }
                        break;
                    case 3:
                        if (id == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 107, 1, (byte) i3});
                            break;
                        } else if (id == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 107, 2, (byte) i3});
                            break;
                        } else if (id == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 107, 3, (byte) i3});
                            break;
                        } else if (id == 3) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 107, 4, (byte) i3});
                            break;
                        }
                        break;
                    case 4:
                        if (id == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 1, (byte) i3});
                            break;
                        } else if (id == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 2, (byte) (i3 + 1)});
                            break;
                        } else if (id == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 109, 3, (byte) i3});
                            break;
                        }
                        break;
                    case 5:
                        if (id == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte) (i3 + 1)});
                            break;
                        } else if (id == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 110, 2, (byte) i3});
                            break;
                        } else if (id == 2) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 110, 3, (byte) i3});
                            break;
                        } else if (id == 3) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 110, 4, (byte) (i3 + 1)});
                            break;
                        } else if (id == 4) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 110, 7, (byte) i3});
                            break;
                        }
                        break;
                    case 6:
                        switch (id) {
                            case 0:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte) i3});
                                break;
                            case 1:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte) (i3 + 1)});
                                break;
                            case 2:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte) (i3 + 1)});
                                break;
                            case 3:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte) (i3 + 1)});
                                break;
                            case 4:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte) i3});
                                break;
                            case 5:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 8, (byte) i3});
                                break;
                            case 6:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 7, (byte) i3});
                                break;
                            case 7:
                                CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte) i3});
                                break;
                        }
                    case 7:
                        if (i2 == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                        }
                        SharePreUtil.setIntValue(context, "55_language", i3);
                        try {
                            if (UiMgr.this.mMsgMgr != null) {
                                UiMgr.this.mMsgMgr.updateLanguage(i3);
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                        break;
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                int id = settingUiSet.getList().get(i).getItemList().get(i2).getId();
                if (i != 0) {
                    if (i != 5) {
                        return;
                    }
                } else if (id == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
                } else if (id == 14) {
                    Toast.makeText(UiMgr.this.mContext, R.string.reset_completed, 1).show();
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, -1});
                }
                if (id == 7) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 110, 5, 1});
                } else {
                    if (id != 8) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, 110, 6, 1});
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                settingUiSet.getList().get(i).getItemList().get(i2).getId();
                if (i == 1) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, 1});
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, 0});
                    }
                }
            }
        });
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                try {
                    UiMgr.this.mMsgMgr.updateSettingData(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 1, -1});
                } else if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 2, -1});
                } else {
                    if (i != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, -1});
                }
            }
        });
        this.mParkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._56.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public void onTouchItem(MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                int dimenByResId = (x * 1560) / CommUtil.getDimenByResId(context, "dp1024");
                int dimenByResId2 = (y * 1900) / CommUtil.getDimenByResId(context, "dp600");
                CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte) ((dimenByResId >> 8) & 255), (byte) (dimenByResId & 255), (byte) ((dimenByResId2 >> 8) & 255), (byte) (dimenByResId2 & 255), 0});
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(5);
                break;
            case "rear_defog":
                sendAirCommand(6);
                break;
            case "auto":
                sendAirCommand(4);
                break;
            case "sync":
                sendAirCommand(3);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(7);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._56.UiMgr$17] */
    public void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._56.UiMgr.17
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 0});
            }
        }.start();
    }

    public void setShowRadar(boolean z) {
        this.mParkPageUiSet.setShowRadar(z);
    }
}
