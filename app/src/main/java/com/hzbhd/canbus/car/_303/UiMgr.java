package com.hzbhd.canbus.car._303;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private Context mContext;
    OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._303.UiMgr$$ExternalSyntheticLambda0
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public final void onConfirmClick(int i, int i2) {
            UiMgr.lambda$new$0(i, i2);
        }
    };
    OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._303.UiMgr$$ExternalSyntheticLambda1
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public final void onClickItem(int i, int i2, int i3) {
            UiMgr.lambda$new$1(i, i2, i3);
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._303.UiMgr$$ExternalSyntheticLambda2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public final void onClickItem(int i, int i2, int i3) {
            UiMgr.lambda$new$2(i, i2, i3);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    private int maxTemp = 60;
    private int minTemp = 32;
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            int i = MsgMgr.mLeftTemp;
            if (i == 254) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte) UiMgr.this.minTemp});
                return;
            }
            if (i == UiMgr.this.maxTemp) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, -1});
            } else {
                if (i < UiMgr.this.minTemp || i >= UiMgr.this.maxTemp) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte) (i + 1)});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            int i = MsgMgr.mLeftTemp;
            if (i == 255) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte) (UiMgr.this.maxTemp - 1)});
            } else if (i < UiMgr.this.minTemp) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, -2});
            } else if (i < UiMgr.this.maxTemp) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte) (i - 1)});
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            int i = MsgMgr.mRightTemp;
            if (i == 254) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte) UiMgr.this.minTemp});
                return;
            }
            if (i == UiMgr.this.maxTemp) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, -1});
            } else {
                if (i < UiMgr.this.minTemp || i >= UiMgr.this.maxTemp) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte) (i + 1)});
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            int i = MsgMgr.mRightTemp;
            if (i == 255) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte) (UiMgr.this.maxTemp - 1)});
            } else if (i < UiMgr.this.minTemp) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, -2});
            } else if (i < UiMgr.this.maxTemp) {
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte) (i - 1)});
            }
        }
    };
    OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.setWindClick(false);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.setWindClick(true);
        }
    };
    OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._303.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick()  {
            UiMgr.this.setFrontSearClick();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick()  {
            UiMgr.this.setFrontSearClick();
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
    }

    static /* synthetic */ void lambda$new$0(int i, int i2) {
        if (i == 0 && i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 26, 0});
        } else if (i == 7 && i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -52, 1, 1});
        }
    }

    static /* synthetic */ void lambda$new$1(int i, int i2, int i3) {
        switch (i) {
            case 1:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 75, 2, (byte) i3});
                    break;
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -51, 1, (byte) (i3 + 1)});
                    break;
                }
                break;
            case 2:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, (byte) i3});
                    break;
                }
                break;
            case 3:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, 19, (byte) i3});
                    break;
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, 18, (byte) i3});
                    break;
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, 16, (byte) i3});
                    break;
                } else if (i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
                } else if (i2 == 5) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 109, 14, (byte) i3});
                    break;
                }
                break;
            case 4:
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte) i3});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte) i3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte) i3});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 7, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 8, (byte) i3});
                        break;
                }
            case 5:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 122, 5, (byte) i3});
                    break;
                }
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte) (i2 + 1), (byte) (i3 + 1)});
                break;
            case 7:
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -52, 2, (byte) i3});
                    break;
                }
                break;
            case 8:
                CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (i3 + 1)});
                break;
        }
    }

    static /* synthetic */ void lambda$new$2(int i, int i2, int i3) {
        if (i == 1) {
            if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, 75, 3, (byte) i3});
                return;
            } else {
                if (i2 != 3) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -51, 2, (byte) i3});
                return;
            }
        }
        if (i == 3) {
            if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, 109, 15, (byte) i3});
                return;
            } else {
                if (i2 != 6) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 109, 17, (byte) i3});
                return;
            }
        }
        if (i != 5) {
            return;
        }
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 122, 1, (byte) i3});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 122, 2, (byte) i3});
        } else if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, 122, 3, (byte) i3});
        } else {
            if (i2 != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 122, 4, (byte) i3});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWindClick(boolean z) {
        int i = GeneralAirData.front_wind_level;
        int i2 = z ? i + 1 : i - 1;
        if (i2 < 0 || i2 > 7) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 58, 7, (byte) i2});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontSearClick()  {
        if (!GeneralAirData.front_left_blow_foot && !GeneralAirData.front_left_blow_head && !GeneralAirData.front_left_blow_window) {
            setWindMode(true, false, true);
            return;
        }
        if (GeneralAirData.front_left_blow_foot && GeneralAirData.front_left_blow_head && GeneralAirData.front_left_blow_window) {
            setWindMode(false, false, false);
            return;
        }
        if (GeneralAirData.front_left_blow_foot && GeneralAirData.front_left_blow_window) {
            setWindMode(true, false, false);
            return;
        }
        if (GeneralAirData.front_left_blow_window && GeneralAirData.front_left_blow_head) {
            setWindMode(false, true, false);
            return;
        }
        if (GeneralAirData.front_left_blow_foot && GeneralAirData.front_left_blow_head) {
            setWindMode(false, false, true);
            return;
        }
        if (GeneralAirData.front_left_blow_window) {
            setWindMode(true, true, false);
        } else if (GeneralAirData.front_left_blow_head) {
            setWindMode(false, true, true);
        } else {
            setWindMode(true, true, true);
        }
    }

    private void setWindMode(boolean z, boolean z2, boolean z3)  {
        sendAirMsg(3, z);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendAirMsg(4, z2);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        sendAirMsg(5, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "auto_wind_lv":
                if (GeneralAirData.auto_wind_lv != 0) {
                    if (GeneralAirData.auto_wind_lv != 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 0});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 2});
                        break;
                    }
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 1});
                    break;
                }
            case "auto_cycle":
                if (!GeneralAirData.auto_cycle) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 0});
                    break;
                }
            case "power":
                if (!GeneralAirData.power) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 0});
                    break;
                }
        }
    }

    private void sendAirMsg(int i, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) i, z ? (byte) 1 : (byte) 0});
    }
}
