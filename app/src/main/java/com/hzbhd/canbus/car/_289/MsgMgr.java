package com.hzbhd.canbus.car._289;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private static final int RADAR_PARAM_MAX = 24;
    private static final int RADAR_PARAM_RANGE = 10;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int[] mRadarData;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        StringBuilder sbAppend = new StringBuilder().append("canbusInfoChange: ");
        byte[] bArr2 = this.mCanBusInfoByte;
        Log.i("ljq", sbAppend.append(DataHandleUtils.bytes2HexString(bArr2, bArr2.length)).toString());
        if (this.mCanBusInfoInt[0] != 86) {
            return;
        }
        setRearRadarData();
    }

    private void setRearRadarData() {
        if (isRadarDataChange()) {
            RadarInfoUtil.setRearRadarLocationData(10, resolveRadarData(this.mCanBusInfoInt[1]), resolveRadarData(this.mCanBusInfoInt[2]), resolveRadarData(this.mCanBusInfoInt[3]), resolveRadarData(this.mCanBusInfoInt[4]));
            RadarInfoUtil.setFrontRadarLocationData(10, resolveRadarData(this.mCanBusInfoInt[5]), resolveRadarData(this.mCanBusInfoInt[6]), resolveRadarData(this.mCanBusInfoInt[7]), resolveRadarData(this.mCanBusInfoInt[8]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private int resolveRadarData(int i) {
        return (int) Math.ceil((i / 24.0f) * 10.0f);
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
