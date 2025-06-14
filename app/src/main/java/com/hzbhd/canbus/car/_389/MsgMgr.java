package com.hzbhd.canbus.car._389;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr uiMgr;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setWheelKey0x11();
            setBacklightLevel((this.mCanBusInfoInt[7] / 20) + 1);
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 8) {
            realKeyClick0x11(21);
            return;
        }
        if (i == 9) {
            realKeyClick0x11(20);
            return;
        }
        if (i != 80) {
            switch (i) {
                case 0:
                    realKeyClick0x11(0);
                    break;
                case 1:
                    realKeyClick0x11(7);
                    break;
                case 2:
                    realKeyClick0x11(8);
                    break;
                case 3:
                    realKeyClick0x11(3);
                    break;
                case 4:
                    realKeyClick0x11(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyClick0x11(14);
                    break;
                case 6:
                    realKeyClick0x11(15);
                    break;
                default:
                    switch (i) {
                        case 12:
                            realKeyClick0x11(2);
                            break;
                        case 13:
                            realKeyClick0x11(45);
                            break;
                        case 14:
                            realKeyClick0x11(46);
                            break;
                        case 15:
                            realKeyClick0x11(49);
                            break;
                        case 16:
                            realKeyClick0x11(50);
                            break;
                    }
            }
            return;
        }
        realKeyClick0x11(1);
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 0, (byte) i5, (byte) i6, 0, 0, 1, (byte) i2, (byte) i3, (byte) i4, (byte) i9}, 12));
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -105, 9});
        getUiMgr(this.mContext).sendMediaInfo0x93(1, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -105, 7});
        getUiMgr(this.mContext).sendMediaInfo0x93(1, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -105, 6});
        getUiMgr(this.mContext).sendMediaInfo0x93(1, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -105, 8});
        getUiMgr(this.mContext).sendMediaInfo0x93(1, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 0}, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            str4 = str2 + "MHz";
        } else {
            str4 = (str.equals("AM1") || str.equals("AM2")) ? str2 + "KHz" : "";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(1, str4.getBytes());
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 1, (byte) getBandData(str)}, 34));
    }

    private int getBandData(String str) {
        if ("FM1".equals(str)) {
            return 1;
        }
        if ("FM2".equals(str)) {
            return 2;
        }
        if ("FM3".equals(str)) {
            return 3;
        }
        if ("AM1".equals(str)) {
            return 4;
        }
        return "AM2".equals(str) ? 5 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 1, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, 34));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 1, (byte) (b == 9 ? 14 : 13)}, String.format("     %04d   ", Integer.valueOf((b7 << 8) | i)).getBytes()), 34));
        String str7 = ((int) b3) + ":" + str2 + ":" + str3;
        int length = 101 - str7.length();
        for (int i4 = 0; i4 < length; i4++) {
            str7 = str7 + "";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(1, str7.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 1, (byte) (b == 9 ? 14 : 13)}, String.format("     %04d   ", Integer.valueOf((b6 << 8) | i)).getBytes()), 34));
        String str5 = ((int) b3) + ":" + str3 + ":" + str4;
        int length = 101 - str5.length();
        for (int i5 = 0; i5 < length; i5++) {
            str5 = str5 + "";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(1, str5.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 1, 11}, 34));
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        UiMgr uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        this.uiMgr = uiMgr;
        return uiMgr;
    }
}
