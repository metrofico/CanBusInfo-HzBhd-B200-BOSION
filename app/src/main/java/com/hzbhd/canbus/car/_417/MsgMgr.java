package com.hzbhd.canbus.car._417;

import android.content.Context;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.SystemButton;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr mUiMgr;
    private SystemButton systemButton;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setSwc0x11();
        } else if (i == 232) {
            setPanoramic0xE8();
        } else {
            if (i != 240) {
                return;
            }
            setVersion0xF0();
        }
    }

    private void setPanoramic0xE8() {
        forceReverse(this.mContext, this.mCanBusInfoInt[5] == 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[3] == 4));
        arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 5));
        arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 6));
        arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[3] == 7));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setSwc0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 8) {
            buttonKey(45);
        } else if (i == 9) {
            buttonKey(46);
        } else {
            if (i != 11) {
                return;
            }
            buttonKey(2);
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void showButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._417.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "P", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._417.MsgMgr.1.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramicInfo0xFD(1);
                        }
                    });
                }
                MsgMgr.this.systemButton.show();
            }
        });
    }

    public void hideButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._417.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "P", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._417.MsgMgr.2.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramicInfo0xFD(1);
                        }
                    });
                }
                MsgMgr.this.systemButton.hide();
            }
        });
    }
}
