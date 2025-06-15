package com.hzbhd.canbus.car._407;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._404.MsgMgrKt;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;




public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;

    private static final int set0x41Data$restrictValue(int i) {
        if (i == 255) {
            return 0;
        }
        return i;
    }

    public final int[] getFrame() {
        int[] iArr = this.frame;
        if (iArr != null) {
            return iArr;
        }

        return null;
    }

    public final void setFrame(int[] iArr) {

        this.frame = iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        UiMgrInterface canUiMgr2 = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr2, null, 4, null);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCanDifferentId(), 19});
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final byte[] radioInfoChange$radioAscii(int r8, java.lang.String r9, int r10, java.lang.String r11) {
        /*
            int r0 = r9.hashCode()
            java.lang.String r1 = "this as java.lang.String).getBytes(charset)"
            r2 = 2
            r3 = 32
            r4 = 1
            r5 = 0
            switch(r0) {
                case 64901: goto L73;
                case 64902: goto L6a;
                case 69706: goto L23;
                case 69707: goto L19;
                case 69708: goto L10;
                default: goto Le;
            }
        Le:
            goto L9e
        L10:
            java.lang.String r0 = "FM3"
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L9e
            goto L2d
        L19:
            java.lang.String r0 = "FM2"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L2d
            goto L9e
        L23:
            java.lang.String r0 = "FM1"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L2d
            goto L9e
        L2d:
            java.lang.Double r9 = kotlin.text.StringsKt.toDoubleOrNull(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            double r6 = r9.doubleValue()
            r9 = 10
            double r6 = com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(r6, r9)
            java.lang.String r9 = java.lang.String.valueOf(r6)
            java.nio.charset.Charset r11 = kotlin.text.Charsets.US_ASCII
            byte[] r9 = r9.getBytes(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            byte[] r11 = new byte[r2]
            r11[r5] = r5
            byte r8 = (byte) r8
            r11[r4] = r8
            int r8 = r10 + (-2)
            int r8 = r8 + (-4)
            int r0 = r9.length
            int r8 = r8 - r0
            byte[] r0 = new byte[r8]
        L5a:
            if (r5 >= r8) goto L61
            r0[r5] = r3
            int r5 = r5 + 1
            goto L5a
        L61:
            byte[] r8 = kotlin.collections.ArraysKt.plus(r11, r0)
            byte[] r8 = kotlin.collections.ArraysKt.plus(r8, r9)
            goto La0
        L6a:
            java.lang.String r0 = "AM2"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L7c
            goto L9e
        L73:
            java.lang.String r0 = "AM1"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L7c
            goto L9e
        L7c:
            java.nio.charset.Charset r9 = kotlin.text.Charsets.US_ASCII
            byte[] r9 = r11.getBytes(r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)
            byte[] r11 = new byte[r2]
            r11[r5] = r5
            byte r8 = (byte) r8
            r11[r4] = r8
            byte[] r8 = new byte[r4]
        L8e:
            if (r5 >= r4) goto L95
            r8[r5] = r3
            int r5 = r5 + 1
            goto L8e
        L95:
            byte[] r8 = kotlin.collections.ArraysKt.plus(r11, r8)
            byte[] r8 = kotlin.collections.ArraysKt.plus(r8, r9)
            goto La0
        L9e:
            byte[] r8 = new byte[r5]
        La0:
            byte[] r8 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict(r8, r10, r3)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._407.MsgMgr.radioInfoChange$radioAscii(int, java.lang.String, int, java.lang.String):byte[]");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        byte b = 3;
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -111;
        if (currBand != null) {
            switch (currBand.hashCode()) {
                case 64901:
                    if (currBand.equals("AM1")) {
                        b = 4;
                        break;
                    } else {
                        return;
                    }
                case 64902:
                    if (currBand.equals("AM2")) {
                        b = 5;
                        break;
                    } else {
                        return;
                    }
                case 69706:
                    if (currBand.equals("FM1")) {
                        b = 1;
                        break;
                    } else {
                        return;
                    }
                case 69707:
                    if (currBand.equals("FM2")) {
                        b = 2;
                        break;
                    } else {
                        return;
                    }
                case 69708:
                    if (!currBand.equals("FM3")) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            bArr[2] = b;
            String currentBand = getCurrentBand();


            CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, radioInfoChange$radioAscii(currClickPresetIndex, currentBand, 12, currentFreq)));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {

        MsgMgrKt.sendTextInfo(146, songTitle, Charsets.UTF_16BE, 32, 10);

        MsgMgrKt.sendTextInfo(147, songAlbum, Charsets.UTF_16BE, 32, 10);

        MsgMgrKt.sendTextInfo(148, songArtist, Charsets.UTF_16BE, 32, 10);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            set0x11Data();
            return;
        }
        if (i == 18) {
            set0x12Data();
            return;
        }
        if (i == 33) {
            set0x21Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 69) {
            set0x45Data();
            return;
        }
        if (i == 72) {
            set0x48Data();
            return;
        }
        if (i == 120) {
            set0x78Data();
            return;
        }
        if (i == 135) {
            set0x87Data();
            return;
        }
        if (i == 240) {
            set0xF0Data(canbusInfo);
            return;
        }
        if (i == 49) {
            set0x31Data();
            return;
        }
        if (i == 50) {
            set0x32Data();
        } else if (i == 232) {
            set0xE8Data();
        } else {
            if (i != 233) {
                return;
            }
            set0xE9Data();
        }
    }

    private final void set0xE9Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S407_xE9_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(getFrame()[4]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S407_xE9_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(getFrame()[4]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S407_xE9_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 4, 2)));
        }
        updateSettingActivity(null);
    }

    private final void set0x78Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S407_x78_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(getFrame()[5]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S407_x78_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 5, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S407_x78_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 3, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S407_x78_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S407_x78_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S407_x78_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(itemListBean6.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 4, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S407_x78_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 2, 2)));
        }
        updateSettingActivity(null);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x32Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7])));
        }
        updateDriveDataActivity(null);
    }

    private final void set0x48Data() {
        GeneralTireData.isHaveSpareTire = false;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TireUpdateEntity(0, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(getFrame()[4] * 0.1d, 10) + " bar", (getFrame()[9] - 40) + " °C"}));
        arrayList.add(new TireUpdateEntity(1, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(getFrame()[5] * 0.1d, 10) + " bar", (getFrame()[10] - 40) + " °C"}));
        arrayList.add(new TireUpdateEntity(2, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(getFrame()[6] * 0.1d, 10) + " bar", (getFrame()[11] - 40) + " °C"}));
        arrayList.add(new TireUpdateEntity(3, 0, new String[]{com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(getFrame()[7] * 0.1d, 10) + " bar", (getFrame()[12] - 40) + " °C"}));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private final void set0x87Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S407_x87_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 3, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(getFrame()[5]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[5]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(itemListBean5.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(getFrame()[5]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_9");
        if (itemListBean9 != null) {
            itemListBean9.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_11");
        if (itemListBean11 != null) {
            itemListBean11.setValue(itemListBean11.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_12");
        if (itemListBean12 != null) {
            itemListBean12.setValue(itemListBean12.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 2, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_13");
        if (itemListBean13 != null) {
            itemListBean13.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_14");
        if (itemListBean14 != null) {
            itemListBean14.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_15");
        if (itemListBean15 != null) {
            itemListBean15.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_16");
        if (itemListBean16 != null) {
            itemListBean16.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_17");
        if (itemListBean17 != null) {
            itemListBean17.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean18 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_18");
        if (itemListBean18 != null) {
            itemListBean18.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean19 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_19");
        if (itemListBean19 != null) {
            itemListBean19.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean20 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_20");
        if (itemListBean20 != null) {
            itemListBean20.setProgress(DataHandleUtils.getIntFromByteWithBit(getFrame()[9], 4, 4));
            itemListBean20.setValue(String.valueOf(itemListBean20.getProgress()));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean21 = InitUtilsKt.getMSettingItemIndex().get("S407_x87_21");
        if (itemListBean21 != null) {
            itemListBean21.setProgress(DataHandleUtils.getIntFromByteWithBit(getFrame()[9], 0, 4));
            itemListBean21.setValue(String.valueOf(itemListBean21.getProgress()));
        }
        updateSettingActivity(null);
    }

    private final void set0x45Data() {
        Integer num;
        PanoramicBtnUpdateEntity panoramicBtnUpdateEntity;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 2);
        if (intFromByteWithBit == 0) {
            num = 0;
        } else if (intFromByteWithBit == 1) {
            num = 1;
        } else {
            num = intFromByteWithBit != 2 ? null : 2;
        }
        ArrayList arrayList = new ArrayList(3);
        for (int i = 0; i < 3; i++) {
            if (num != null && i == num.intValue()) {
                panoramicBtnUpdateEntity = new PanoramicBtnUpdateEntity(i, true);
            } else {
                panoramicBtnUpdateEntity = new PanoramicBtnUpdateEntity(i, false);
            }
            arrayList.add(panoramicBtnUpdateEntity);
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0xE8Data() {
        Context mContext = InitUtilsKt.getMContext();
        boolean z = getFrame()[2] == 1;
        if (z) {
            enterAuxIn2();
        } else {
            exitAuxIn2();
        }
        Unit unit = Unit.INSTANCE;
        forceReverse(mContext, z);
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(getFrame()[6]), 0, 0, set0x41Data$restrictValue(getFrame()[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(getFrame()[2]);
        boolean z = false;
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2) == 1;
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 2) {
            GeneralAirData.front_left_blow_window = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i2 = getFrame()[8];
        if (i2 == 254) {
            str = "LOW_TEMP";
        } else if (i2 == 255) {
            str = "HIGH_TEMP";
        } else {
            if (1 <= i2 && i2 < 17) {
                str = "Level " + i2;
            } else {
                if (36 <= i2 && i2 < 65) {
                    z = true;
                }
                str = z ? (i2 * 0.5d) + " °C" : "--";
            }
        }
        GeneralAirData.front_left_temperature = str;
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x21Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = 2;
        int i2 = getFrame()[2];
        if (i2 == 1) {
            i = 1;
        } else if (i2 == 2) {
            i = 21;
        } else if (i2 == 3) {
            i = 20;
        } else if (i2 == 43) {
            i = 52;
        } else if (i2 == 45) {
            i = 59;
        } else if (i2 == 69) {
            i = 7;
        } else if (i2 == 70) {
            i = 8;
        } else if (i2 != 95) {
            i = i2 != 96 ? 0 : HotKeyConstant.K_CARPLAY_SIRI;
        }
        realKeyLongClick1(mContext, i, getFrame()[3]);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[4])) == 1;
        updateDoorView(InitUtilsKt.getMContext());
    }

    private final void set0x11Data() {
        int i = 3;
        updateSpeedInfo(getFrame()[3]);
        Context mContext = InitUtilsKt.getMContext();
        int i2 = getFrame()[4];
        if (i2 == 8) {
            i = 21;
        } else if (i2 == 9) {
            i = 20;
        } else if (i2 == 12) {
            i = 2;
        } else if (i2 != 24) {
            switch (i2) {
                case 1:
                    i = 7;
                    break;
                case 2:
                    i = 8;
                    break;
                case 3:
                    break;
                case 4:
                    i = HotKeyConstant.K_SPEECH;
                    break;
                case 5:
                    i = 14;
                    break;
                case 6:
                    i = 15;
                    break;
                default:
                    i = 0;
                    break;
            }
        } else {
            i = 151;
        }
        realKeyLongClick1(mContext, i, getFrame()[5]);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }
}
