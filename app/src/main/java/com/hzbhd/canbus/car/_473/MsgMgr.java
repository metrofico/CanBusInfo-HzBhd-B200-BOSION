package com.hzbhd.canbus.car._473;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class MsgMgr extends AbstractMsgMgr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    CanDocking docking;
    Context mContext;
    private UiMgr mUiMgr;
    private SimpleDateFormat simpleDateFormat1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        if (this.docking == null) {
            this.docking = CanVm.getVm().getCanDocking();
        }
        this.docking.afterServiceNormalSetting(context);
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
    public void canbusInfoChange(Context context, final byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.docking.canbusInfoChange(context, bArr);
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car._473.MsgMgr$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m891lambda$canbusInfoChange$0$comhzbhdcanbuscar_473MsgMgr(bArr);
            }
        });
    }

    /* renamed from: lambda$canbusInfoChange$0$com-hzbhd-canbus-car-_473-MsgMgr, reason: not valid java name */
    /* synthetic */ Unit m891lambda$canbusInfoChange$0$comhzbhdcanbuscar_473MsgMgr(byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (LogUtil.log5()) {
            LogUtil.d("canbusInfoChange(): " + getMsDataType(byteArrayToIntArray));
        }
        int msDataType = getMsDataType(byteArrayToIntArray);
        if (msDataType == 792) {
            setTime0x318(byteArrayToIntArray);
        } else if (msDataType == 1376) {
            set0x560ParkingSensorInfo(byteArrayToIntArray);
        }
        getUiMgr(this.mContext).refreshRadar();
        return null;
    }

    private void setTime0x318(int[] iArr) {
        if (this.simpleDateFormat1 == null) {
            this.simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[12], 0, 5);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr[13], 0, 4);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr[9], 1, 5);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(iArr[10], 3, 5);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 6);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 1, 1);
        if (LogUtil.log5()) {
            LogUtil.d("setTime0x318(): -------" + intFromByteWithBit6);
        }
        SendKeyManager.getInstance().setAppMute(1, intFromByteWithBit6 != 0);
        try {
            SystemClock.setCurrentTimeMillis(this.simpleDateFormat1.parse((intFromByteWithBit + 2014) + "-" + intFromByteWithBit2 + "-" + intFromByteWithBit3 + " " + intFromByteWithBit4 + ":" + intFromByteWithBit5).getTime());
        } catch (ParseException e) {
            Log.e("TIME_SYNC_ERROR", e.toString());
            e.printStackTrace();
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void set0x560ParkingSensorInfo(final int[] iArr) {
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car._473.MsgMgr$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MsgMgr.lambda$set0x560ParkingSensorInfo$1(iArr);
            }
        });
    }

    static /* synthetic */ Unit lambda$set0x560ParkingSensorInfo$1(int[] iArr) {
        if (LogUtil.log5()) {
            LogUtil.d("set0x560ParkingSensorInfo(): +++" + iArr[11] + "----" + iArr[12]);
        }
        if (LogUtil.log5()) {
            LogUtil.d("set0x560ParkingSensorInfo(): +++" + iArr[13] + "----" + iArr[14]);
        }
        MdRadarData.reverse_left_rear_vertical = iArr[11];
        MdRadarData.reverse_left_rear_horizontal = iArr[12];
        MdRadarData.reverse_right_rear_vertical = iArr[13];
        MdRadarData.reverse_right_rear_horizontal = iArr[14];
        return null;
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }
}
