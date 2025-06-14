package com.hzbhd.canbus.car._441;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.car._441.DvrDataKAdapter;
import com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrSetting;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrState;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final int MESSAGE_ADD_DATA = 1;
    private static final int MESSAGE_PARSE_DATA = 0;
    private static final int MSG_BUFFER_SIZE = 2048;
    DecimalFormat df_1Decimal;
    DecimalFormat df_2Decimal;
    DecimalFormat df_2Integer;
    int[] dvrInt;
    private Context mContext;
    int[] mFrontRadarData;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private byte[] mMsgUnHandledData;
    private UiMgr mUiMgr;
    private int mUnHandledDataLen;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

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
        HandlerThread handlerThread = new HandlerThread("hzbhd.canbusinfo.MsgMgr") { // from class: com.hzbhd.canbus.car._441.MsgMgr.1
            {
                start();
            }
        };
        this.mHandlerThread = handlerThread;
        this.mHandler = new Handler(handlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._441.MsgMgr.2
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
                }
            }
        };
        this.df_2Decimal = new DecimalFormat("###0.00");
        this.df_1Decimal = new DecimalFormat("###0.0");
        this.df_2Integer = new DecimalFormat("00");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(final Context context) throws RemoteException {
        super.initCommand(context);
        this.mContext = context;
        this.mMsgUnHandledData = new byte[4096];
        this.mUnHandledDataLen = 0;
        DvrDataKAdapter.INSTANCE.registerOnDataReadCallback(new DvrDataKAdapter.OnDvrDataReadListener() { // from class: com.hzbhd.canbus.car._441.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car._441.DvrDataKAdapter.OnDvrDataReadListener
            public final void onDataRead(byte[] bArr) {
                this.f$0.m865lambda$initCommand$0$comhzbhdcanbuscar_441MsgMgr(context, bArr);
            }
        });
    }

    private void RadarInfoChange(byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (byteArrayToIntArray[0] != 204) {
            return;
        }
        setRadarInfo(byteArrayToIntArray);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
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

    private void setRadarInfo(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            Log.d("RADAR:Data", "前左=" + DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 4));
            Log.d("RADAR:Data", "前右=" + DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4));
            Log.d("RADAR:Data", "后左=" + DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 4));
            Log.d("RADAR:Data", "后左中=" + DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 4));
            Log.d("RADAR:Data", "后右中=" + DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 4));
            Log.d("RADAR:Data", "后右=" + DataHandleUtils.getIntFromByteWithBit(iArr[1], 0, 4));
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: DvrModular, reason: merged with bridge method [inline-methods] */
    public void m865lambda$initCommand$0$comhzbhdcanbuscar_441MsgMgr(Context context, byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(SplicingByte(new byte[]{0}, bArr));
        if (byteArrayToIntArray[1] == 170 && byteArrayToIntArray[2] == 77 && byteArrayToIntArray[3] == 68) {
            int i = byteArrayToIntArray[5];
            if (i == 95) {
                set0x5F(byteArrayToIntArray);
                return;
            }
            if (i == 112) {
                set0x70(byteArrayToIntArray);
                return;
            }
            if (i != 255) {
                switch (i) {
                    case 32:
                        set0x20(byteArrayToIntArray);
                        break;
                    case 33:
                        set0x21(byteArrayToIntArray);
                        break;
                    case 34:
                        set0x22();
                        break;
                    default:
                        switch (i) {
                            case 80:
                                set0x50(byteArrayToIntArray[6]);
                                break;
                            case 81:
                                set0x51(byteArrayToIntArray[6]);
                                break;
                            case 82:
                                set0x52(byteArrayToIntArray[6]);
                                break;
                            case 83:
                                set0x53(byteArrayToIntArray[6]);
                                break;
                            case 84:
                                set0x54(byteArrayToIntArray[6]);
                                break;
                            case 85:
                                set0x55(byteArrayToIntArray[6]);
                                break;
                            case 86:
                                set0x56(byteArrayToIntArray[6]);
                                break;
                            case 87:
                                set0x57(byteArrayToIntArray[6]);
                                break;
                            case 88:
                                set0x58(byteArrayToIntArray[6], byteArrayToIntArray[7], byteArrayToIntArray[8], byteArrayToIntArray[9]);
                                break;
                            case 89:
                                set0x59(byteArrayToIntArray[6], byteArrayToIntArray[7], byteArrayToIntArray[8], byteArrayToIntArray[9]);
                                break;
                            case 90:
                                set0x5A(byteArrayToIntArray);
                                break;
                            case 91:
                                set0x5B();
                                break;
                            case 92:
                                set0x5C(byteArrayToIntArray);
                                break;
                        }
                }
                return;
            }
            set0xFF();
        }
    }

    private void set0x70(int[] iArr) {
        int i = 0;
        switch (iArr[6]) {
            case 1:
                GeneralDvrFile.getInstance().item1.clear();
                int length = iArr.length;
                while (i < length) {
                    GeneralDvrFile.getInstance().item1.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 2:
                GeneralDvrFile.getInstance().item2.clear();
                int length2 = iArr.length;
                while (i < length2) {
                    GeneralDvrFile.getInstance().item2.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 3:
                GeneralDvrFile.getInstance().item3.clear();
                int length3 = iArr.length;
                while (i < length3) {
                    GeneralDvrFile.getInstance().item3.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 4:
                GeneralDvrFile.getInstance().item4.clear();
                int length4 = iArr.length;
                while (i < length4) {
                    GeneralDvrFile.getInstance().item4.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 5:
                GeneralDvrFile.getInstance().item5.clear();
                int length5 = iArr.length;
                while (i < length5) {
                    GeneralDvrFile.getInstance().item5.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 6:
                GeneralDvrFile.getInstance().item6.clear();
                int length6 = iArr.length;
                while (i < length6) {
                    GeneralDvrFile.getInstance().item6.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
        }
    }

    private void set0xFF() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._441.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_finish), 0).show();
            }
        });
    }

    private void set0x22() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._441.MsgMgr.4
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.dvrInt[6] == 1) {
                    Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_connect1), 0).show();
                }
            }
        });
    }

    private void set0x5B() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._441.MsgMgr.5
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                switch (MsgMgr.this.dvrInt[6]) {
                    case 128:
                        Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_sd_format0), 0).show();
                        GeneralDvrState.formatSdFail = true;
                        break;
                    case 129:
                        Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_sd_format1), 0).show();
                        GeneralDvrState.formatSdFail = false;
                        break;
                    case 130:
                        Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_sd_format2), 0).show();
                        GeneralDvrState.formatSdFail = false;
                        break;
                }
            }
        });
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x5A(int[] iArr) {
        Log.d("fxHou0x5A", "收到数据");
        try {
            GeneralDvrState.version = (iArr[6] + SystemConstant.THREAD_SLEEP_TIME_2000) + "-" + this.df_2Integer.format(iArr[7]) + "-" + this.df_2Integer.format(iArr[8]) + " V" + iArr[9];
        } catch (Exception e) {
            Log.d("fxHou0x5A", e.toString());
        }
        Log.d("fxHou0x5A", GeneralDvrState.version);
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x59(int i, int i2, int i3, int i4) {
        if (i == 2) {
            GeneralDvrState.time = this.df_2Integer.format(i2) + ":" + this.df_2Integer.format(i3) + ":" + this.df_2Integer.format(i4);
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x58(int i, int i2, int i3, int i4) {
        if (i == 2) {
            GeneralDvrState.date = (i2 + SystemConstant.THREAD_SLEEP_TIME_2000) + "-" + this.df_2Decimal.format(i3) + "-" + this.df_2Decimal.format(i4);
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x5F(int[] iArr) {
        set0x50(iArr[6]);
        set0x51(iArr[7]);
        set0x52(iArr[8]);
        set0x53(iArr[9]);
        set0x54(iArr[10]);
        set0x55(iArr[11]);
        set0x56(iArr[12]);
        set0x57(iArr[13]);
        set0x58(2, iArr[14], iArr[15], iArr[16]);
        set0x59(2, iArr[17], iArr[18], iArr[19]);
    }

    private void set0x5C(int[] iArr) {
        if (iArr[6] == 128) {
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._441.MsgMgr.6
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    Toast.makeText(MsgMgr.this.mContext, "Reset Fail!", 0).show();
                }
            });
        }
    }

    private void set0x57(int i) {
        switch (i) {
            case 128:
                GeneralDvrSetting.timeFormat = 0;
                break;
            case 129:
                GeneralDvrSetting.timeFormat = 1;
                break;
            case 130:
                GeneralDvrSetting.timeFormat = 2;
                break;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x56(int i) {
        if (i == 128) {
            GeneralDvrSetting.opticalFrequency = 0;
        } else if (i == 129) {
            GeneralDvrSetting.opticalFrequency = 1;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x55(int i) {
        if (i == 128) {
            GeneralDvrSetting.gravitySensor = 0;
        } else if (i == 129) {
            GeneralDvrSetting.gravitySensor = 1;
        } else if (i == 131) {
            GeneralDvrSetting.gravitySensor = 2;
        } else if (i == 133) {
            GeneralDvrSetting.gravitySensor = 3;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x54(int i) {
        if (i == 128) {
            GeneralDvrSetting.dvrLanguage = 0;
        } else if (i == 129) {
            GeneralDvrSetting.dvrLanguage = 1;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x53(int i) {
        if (i == 128) {
            GeneralDvrSetting.VideoRecordingVoice = 0;
        } else if (i == 129) {
            GeneralDvrSetting.VideoRecordingVoice = 1;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x52(int i) {
        if (i == 128) {
            GeneralDvrSetting.VideoRecordingSyncTime = 0;
        } else if (i == 129) {
            GeneralDvrSetting.VideoRecordingSyncTime = 1;
        } else if (i == 131) {
            GeneralDvrSetting.VideoRecordingSyncTime = 2;
        } else if (i == 133) {
            GeneralDvrSetting.VideoRecordingSyncTime = 3;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x51(int i) {
        switch (i) {
            case 128:
                GeneralDvrSetting.timeTag = 0;
                break;
            case 129:
                GeneralDvrSetting.timeTag = 1;
                break;
            case 130:
                GeneralDvrSetting.timeTag = 2;
                break;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x50(int i) {
        switch (i) {
            case 128:
                GeneralDvrSetting.resolvingPower = 0;
                break;
            case 129:
                GeneralDvrSetting.resolvingPower = 1;
                break;
            case 130:
                GeneralDvrSetting.resolvingPower = 2;
                break;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x21(int[] iArr) {
        int i = iArr[6];
        if (i == 209) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_6);
        } else if (i == 210) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_7);
        } else if (i == 212) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_8);
        } else if (i == 216) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_9);
        } else if (i == 228) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_4);
        } else if (i == 232) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_5);
        } else if (i == 225) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_2);
        } else if (i == 226) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_3);
        } else if (i == 240) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_0);
        } else if (i == 241) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_1);
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x20(int[] iArr) {
        GeneralDvrState.lock = DataHandleUtils.getBoolBit7(iArr[6]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[6], 5, 2);
        if (intFromByteWithBit == 0) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag0);
        } else if (intFromByteWithBit == 1) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag1);
        } else if (intFromByteWithBit == 2) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag2);
        } else if (intFromByteWithBit == 3) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag3);
        }
        GeneralDvrPlayPage.pageState = DataHandleUtils.getIntFromByteWithBit(iArr[6], 3, 2);
        GeneralDvrState.sd = DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 2);
        int i = iArr[7];
        if (i == 0) {
            GeneralDvrPlayPage.time = this.mContext.getString(R.string._436_DVR_play_page_fun_2_1) + this.df_2Integer.format(iArr[8]) + ":" + this.df_2Integer.format(iArr[9]) + ":" + this.df_2Integer.format(iArr[10]);
        } else if (i == 1) {
            GeneralDvrPlayPage.time = this.mContext.getString(R.string._436_DVR_play_page_fun_2_0) + this.df_2Integer.format(iArr[8]) + ":" + this.df_2Integer.format(iArr[9]) + ":" + this.df_2Integer.format(iArr[10]);
        } else if (i == 255) {
            GeneralDvrPlayPage.time = " ";
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
