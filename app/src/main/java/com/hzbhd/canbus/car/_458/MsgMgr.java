package com.hzbhd.canbus.car._458;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.util.LogUtil;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final int MESSAGE_ADD_DATA = 1;
    private static final int MESSAGE_PARSE_DATA = 0;
    private static final int MSG_BUFFER_SIZE = 2048;
    private String airJsonStr;
    private Context mContext;
    int[] mFrontRadarData;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private byte[] mMsgUnHandledData;
    private UiMgr mUiMgr;
    private int mUnHandledDataLen;

    private int getResult(int i) {
        if (i == 1) {
            return 1;
        }
        if (i < 2 || i >= 8) {
            return (i < 8 || i > 13) ? 0 : 3;
        }
        return 2;
    }

    public MsgMgr() {
        HandlerThread handlerThread = new HandlerThread("hzbhd.canbusinfo.MsgMgr") { // from class: com.hzbhd.canbus.car._458.MsgMgr.1
            {
                start();
            }
        };
        this.mHandlerThread = handlerThread;
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._458.MsgMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (MsgMgr.this.parseSerialDataContinue()) {
                        MsgMgr.this.mHandler.sendEmptyMessageDelayed(0, 10L);
                    }
                } else {
                    if (i != 1) {
                        return;
                    }
                    byte[] bArr = (byte[]) message.obj;
                    System.arraycopy(bArr, 1, MsgMgr.this.mMsgUnHandledData, MsgMgr.this.mUnHandledDataLen, bArr.length - 1);
                    MsgMgr.this.mUnHandledDataLen += bArr.length - 1;
                    MsgMgr.this.mHandler.sendEmptyMessageDelayed(0, 10L);
                    Log.d("fxHou2", "mUnHandledDataLen : " + MsgMgr.this.mUnHandledDataLen);
                }
            }
        };
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mMsgUnHandledData = new byte[4096];
        this.mUnHandledDataLen = 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        ShareBasicInfo(getByteArrayToIntArray(bArr));
    }

    protected void ShareBasicInfo(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportAllCanBusData(intArrayToJsonStr(iArr));
    }

    private String intArrayToJsonStr(int[] iArr) {
        this.airJsonStr = "{";
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + "}";
            } else {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + ",";
            }
        }
        return this.airJsonStr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void serialDataChange(Context context, byte[] bArr) {
        if (bArr == null || bArr.length <= 1 || bArr[0] != 91) {
            return;
        }
        Message message = new Message();
        message.what = 1;
        message.obj = bArr;
        this.mHandler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean parseSerialDataContinue() {
        try {
            int i = this.mUnHandledDataLen;
            int i2 = 4;
            if (i < 4) {
                return false;
            }
            byte[] bArr = this.mMsgUnHandledData;
            byte b = bArr[0];
            if ((b != -86 || bArr[2] != -81) && (b != -69 || bArr[2] != -65)) {
                if (b != -52) {
                    byte[] bArr2 = new byte[i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    System.arraycopy(bArr2, 1, this.mMsgUnHandledData, 0, this.mUnHandledDataLen - 1);
                    this.mUnHandledDataLen--;
                    return true;
                }
                i2 = 8;
            }
            if (i2 == i) {
                this.mUnHandledDataLen = 0;
                byte[] bArr3 = new byte[i2];
                System.arraycopy(bArr, 0, bArr3, 0, i2);
                reportOneSerialMsg(bArr3);
                return false;
            }
            if (i2 > i || i2 >= i) {
                return false;
            }
            this.mUnHandledDataLen = i - i2;
            byte[] bArr4 = new byte[i2];
            System.arraycopy(bArr, 0, bArr4, 0, i2);
            byte[] bArr5 = this.mMsgUnHandledData;
            System.arraycopy(bArr5, i2, bArr5, 0, this.mUnHandledDataLen);
            reportOneSerialMsg(bArr4);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            this.mUnHandledDataLen = 0;
            return false;
        }
    }

    private void reportOneSerialMsg(byte[] bArr) {
        if (LogUtil.log5()) {
            LogUtil.d("reportOneSerialMsg(): " + FgeString.bytes2HexString(bArr, bArr.length));
        }
        if (serialDataCheckSum(bArr)) {
            if (LogUtil.log5()) {
                LogUtil.d("reportOneSerialMsg(): check sum");
            }
            RadarInfoChange(bArr);
        }
    }

    private boolean serialDataCheckSum(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < bArr.length - 1; i++) {
            b = (byte) (b + bArr[i]);
        }
        if (LogUtil.log5()) {
            LogUtil.d("serialDataCheckSum(): sum = " + FgeString.bytetoHexString(b));
        }
        if (bArr.length <= 1) {
            return false;
        }
        if (LogUtil.log5()) {
            LogUtil.d("serialDataCheckSum(): msg end = " + ((int) bArr[bArr.length - 1]));
        }
        return b == bArr[bArr.length - 1];
    }

    private void RadarInfoChange(byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (byteArrayToIntArray[0] != 204) {
            return;
        }
        setRadarInfo(byteArrayToIntArray);
    }

    private void setRadarInfo(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            MdRadarData.left_rear = getResult(DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 4));
            MdRadarData.mid_rear = getResult(DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 4));
            MdRadarData.right_rear = getResult(DataHandleUtils.getIntFromByteWithBit(iArr[1], 0, 4));
            getUiMgr(this.mContext).refreshRadar();
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return false;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
