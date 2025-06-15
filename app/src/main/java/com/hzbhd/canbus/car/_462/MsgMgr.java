package com.hzbhd.canbus.car._462;

import android.content.Context;
import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    CanDocking docking;
    private UiMgr mUiMgr;
    private MyPanoramicView panoramicView;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        if (this.docking == null) {
            this.docking = CanVm.getVm().getCanDocking();
        }
        this.docking.afterServiceNormalSetting(context);
        getUiMgr(context).registerEvent(context);
        new ArrayList().add("");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        if (this.docking == null) {
            this.docking = CanVm.getVm().getCanDocking();
        }
        this.docking.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.docking.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (getMsDataType(byteArrayToIntArray) == -1431655766 || getMsDataType(byteArrayToIntArray) == -1145324613) {
            ShareBasicInfo(byteArrayToIntArray);
        }
        if (getMsDataType(byteArrayToIntArray) == 418326593) {
            int i = byteArrayToIntArray[7];
            if (i == 1) {
                get360View(context).openAuto();
            } else if (i == 2) {
                get360View(context).closeAuto();
            }
        }
    }

    public MyPanoramicView get360View(Context context) {
        if (this.panoramicView == null) {
            this.panoramicView = (MyPanoramicView) getUiMgr(context).getCusPanoramicView(context);
        }
        return this.panoramicView;
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    protected void ShareBasicInfo(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportAllCanBusData(intArrayToJsonStr(iArr));
    }

    private String intArrayToJsonStr(int[] iArr) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < iArr.length; i++) {
            try {
                jSONObject.put("Data" + i, iArr[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
