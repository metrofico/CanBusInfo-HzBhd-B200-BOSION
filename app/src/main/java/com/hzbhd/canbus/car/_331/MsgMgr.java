package com.hzbhd.canbus.car._331;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int[] mFrontRadarDataNow;
    private CusPanoramicView mPanoramicView;
    private RadarView mRadarView;
    private int[] mRearRadarDataNow;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 34) {
            setRearRadarData0x1E();
        } else {
            if (i != 35) {
                return;
            }
            setFrontRadarData0x1D();
        }
    }

    private void setRearRadarData0x1E() {
        if (isRearRadarDataChange()) {
            RadarView.rearRadar[0] = this.mCanBusInfoInt[2];
            RadarView.rearRadar[1] = this.mCanBusInfoInt[3];
            RadarView.rearRadar[2] = this.mCanBusInfoInt[4];
            RadarView.rearRadar[3] = this.mCanBusInfoInt[5];
            refreshRadar();
        }
    }

    private void setFrontRadarData0x1D() {
        if (isFrontRadarDataChange()) {
            RadarView.frontRadar[0] = this.mCanBusInfoInt[2];
            RadarView.frontRadar[1] = this.mCanBusInfoInt[3];
            RadarView.frontRadar[2] = this.mCanBusInfoInt[4];
            RadarView.frontRadar[3] = this.mCanBusInfoInt[5];
            refreshRadar();
        }
    }

    private void refreshRadar() {
        getMyPanoramicView().refreshUi();
        updateDZRadarView();
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private CusPanoramicView getMyPanoramicView() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (CusPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
        return this.mPanoramicView;
    }

    protected void updateDZRadarView() {
        if (this.mRadarView == null) {
            this.mRadarView = new RadarView(this.mContext);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._331.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public final void callback() {
                this.f$0.m700lambda$updateDZRadarView$0$comhzbhdcanbuscar_331MsgMgr();
            }
        });
    }

    /* renamed from: lambda$updateDZRadarView$0$com-hzbhd-canbus-car-_331-MsgMgr, reason: not valid java name */
    /* synthetic */ void m700lambda$updateDZRadarView$0$comhzbhdcanbuscar_331MsgMgr() {
        this.mRadarView.refreshUi();
    }
}
