package com.hzbhd.canbus.car._316;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private final String TAG = "_316_MsgMgr";
    private int m0x82Data0;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private Context mContext;
    private DecimalFormat mDecimalFormat00;

    private int resolveSpeedData(int i, int i2) {
        return (i * 256) + i2;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mDecimalFormat00 = new DecimalFormat("00");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanbusInfoInt = getByteArrayToIntArray(bArr);
        this.mCanbusInfoByte = bArr;
        byte b = bArr[1];
        if (b == 1) {
            set0x01WheelKeyData(context);
            return;
        }
        if (b == 2) {
            set0x02AirInfo(context);
            return;
        }
        if (b == 101) {
            set0x65DriverData();
        } else if (b != Byte.MAX_VALUE) {
            return;
        }
        set0x7fVersionData();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void set0x01WheelKeyData(android.content.Context r7) {
        /*
            r6 = this;
            int[] r0 = r6.mCanbusInfoInt
            r1 = 2
            r2 = r0[r1]
            r3 = 1
            r4 = 7
            r5 = 3
            if (r2 == r3) goto L3b
            if (r2 == r1) goto L38
            if (r2 == r5) goto L35
            r3 = 4
            if (r2 == r3) goto L32
            if (r2 == r4) goto L3c
            switch(r2) {
                case 19: goto L30;
                case 20: goto L2d;
                case 21: goto L2a;
                default: goto L16;
            }
        L16:
            switch(r2) {
                case 32: goto L30;
                case 33: goto L27;
                case 34: goto L24;
                case 35: goto L21;
                case 36: goto L1e;
                case 37: goto L1b;
                default: goto L19;
            }
        L19:
            r1 = 0
            goto L3c
        L1b:
            r1 = 129(0x81, float:1.81E-43)
            goto L3c
        L1e:
            r1 = 128(0x80, float:1.8E-43)
            goto L3c
        L21:
            r1 = 59
            goto L3c
        L24:
            r1 = 68
            goto L3c
        L27:
            r1 = 49
            goto L3c
        L2a:
            r1 = 15
            goto L3c
        L2d:
            r1 = 14
            goto L3c
        L30:
            r1 = r5
            goto L3c
        L32:
            r1 = 45
            goto L3c
        L35:
            r1 = 46
            goto L3c
        L38:
            r1 = 8
            goto L3c
        L3b:
            r1 = r4
        L3c:
            r0 = r0[r5]
            r6.realKeyLongClick1(r7, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._316.MsgMgr.set0x01WheelKeyData(android.content.Context):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int i) {
        if (i != 3) {
            return false;
        }
        realKeyClick(context, HotKeyConstant.K_SLEEP);
        return true;
    }

    private void set0x02AirInfo(Context context) {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
        GeneralAirData.amb = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanbusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanbusInfoInt[5]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 7);
        String str = (intFromByteWithBit < 0 || intFromByteWithBit > 85) ? "" : DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 7) + getTempUnitC(context);
        if (DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6])) {
            str = "-" + str;
        }
        updateOutDoorTemp(context, str);
        updateAirActivity(this.mContext, 1001);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 255 == i ? "HI" : (i < 31 || i > 63) ? "" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private void set0x65DriverData() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanbusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(resolveSpeedData(iArr[2], iArr[3])).append(" RPM").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanbusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append(resolveSpeedData(iArr2[4], iArr2[5])).append(" Km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanbusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr3[4], iArr3[5]));
    }

    private void set0x7fVersionData() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanbusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        Log.i("cbc", "radioInfoChange: " + i2);
        if (str.contains("FM")) {
            str2 = Integer.toString((int) (Float.parseFloat(str2) * 10.0f));
        }
        String str4 = str;
        for (int length = str.length() + str2.length(); length < 12; length++) {
            str4 = str4 + " ";
        }
        String str5 = str4 + str2;
        Log.i("_316_MsgMgr", "radioInfoChange: \".." + str5 + "\"");
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(new byte[]{22, -125}, str5.getBytes()));
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 5, str.contains("FM"));
        this.m0x82Data0 = intByteWithBit;
        int intByteWithBit2 = DataHandleUtils.setIntByteWithBit(intByteWithBit, 2, i2 == 1);
        this.m0x82Data0 = intByteWithBit2;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) intByteWithBit2, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 5, false);
        this.m0x82Data0 = intByteWithBit;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) intByteWithBit, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.byteMerger(new byte[]{22, -125}, ("VIDEO  " + this.mDecimalFormat00.format(DataHandleUtils.rangeNumber((b6 << 8) | i, 99)) + "/" + this.mDecimalFormat00.format(DataHandleUtils.rangeNumber(i2, 99))).getBytes()));
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, true);
        this.m0x82Data0 = intByteWithBit;
        int intByteWithBit2 = DataHandleUtils.setIntByteWithBit(intByteWithBit, 3, b8 != 1);
        this.m0x82Data0 = intByteWithBit2;
        int intByteWithBit3 = DataHandleUtils.setIntByteWithBit(intByteWithBit2, 4, b8 == 1);
        this.m0x82Data0 = intByteWithBit3;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) intByteWithBit3, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, false);
        this.m0x82Data0 = intByteWithBit;
        int intByteWithBit2 = DataHandleUtils.setIntByteWithBit(intByteWithBit, 3, false);
        this.m0x82Data0 = intByteWithBit2;
        int intByteWithBit3 = DataHandleUtils.setIntByteWithBit(intByteWithBit2, 4, false);
        this.m0x82Data0 = intByteWithBit3;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) intByteWithBit3, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -125}, ("MUSIC  " + this.mDecimalFormat00.format(DataHandleUtils.rangeNumber((b7 << 8) | i, 99)) + "/" + this.mDecimalFormat00.format(DataHandleUtils.rangeNumber(i2, 99))).getBytes()));
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, true);
        this.m0x82Data0 = intByteWithBit;
        int intByteWithBit2 = DataHandleUtils.setIntByteWithBit(intByteWithBit, 3, b9 != 1);
        this.m0x82Data0 = intByteWithBit2;
        int intByteWithBit3 = DataHandleUtils.setIntByteWithBit(intByteWithBit2, 4, b9 == 1);
        this.m0x82Data0 = intByteWithBit3;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) intByteWithBit3, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, false);
        this.m0x82Data0 = intByteWithBit;
        int intByteWithBit2 = DataHandleUtils.setIntByteWithBit(intByteWithBit, 3, false);
        this.m0x82Data0 = intByteWithBit2;
        int intByteWithBit3 = DataHandleUtils.setIntByteWithBit(intByteWithBit2, 4, false);
        this.m0x82Data0 = intByteWithBit3;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) intByteWithBit3, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "DTV         ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, DataHandleUtils.makeBytesFixedLength(bArr, 12, 32)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, DataHandleUtils.makeBytesFixedLength(bArr, 12, 32)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "BTMUSIC     ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "AUX         ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "ATV         ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -1, ByteCompanionObject.MAX_VALUE, 0});
    }
}
