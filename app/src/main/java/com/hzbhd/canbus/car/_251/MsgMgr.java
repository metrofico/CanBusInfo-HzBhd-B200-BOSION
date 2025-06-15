package com.hzbhd.canbus.car._251;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
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
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import nfore.android.bt.res.NfDef;




public final class MsgMgr extends AbstractMsgMgr {
    private Context mContext;
    private UiMgr mUiMgr;
    private int[] mCanBusInfoInt = new int[0];
    private byte[] mCanBusInfoByte = new byte[0];
    private int i = 2;
    private int type = 1;

    /* compiled from: MsgMgr.kt */
    
    @DebugMetadata(c = "com.hzbhd.canbus.car._251.MsgMgr$initCommand$1", f = "MsgMgr.kt", i = {}, l = {43}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.hzbhd.canbus.car._251.MsgMgr$initCommand$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MsgMgr.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Path cross not found for [B:19:0x0041, B:14:0x0036], limit reached: 31 */
        /* JADX WARN: Path cross not found for [B:19:0x0041, B:15:0x0038], limit reached: 31 */
        /* JADX WARN: Path cross not found for [B:22:0x004e, B:29:0x005c], limit reached: 31 */
        /* JADX WARN: Path cross not found for [B:29:0x005c, B:22:0x004e], limit reached: 31 */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0029 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0036 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0027 -> B:12:0x002a). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 1
                if (r1 == 0) goto L18
                if (r1 != r2) goto L10
                kotlin.ResultKt.throwOnFailure(r10)
                r10 = r9
                goto L2a
            L10:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L18:
                kotlin.ResultKt.throwOnFailure(r10)
                r10 = r9
            L1c:
                r3 = 1000(0x3e8, double:4.94E-321)
                r1 = r10
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r10.label = r2
                java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r3, r1)
                if (r1 != r0) goto L2a
                return r0
            L2a:
                com.hzbhd.canbus.car._251.MsgMgr r1 = com.hzbhd.canbus.car._251.MsgMgr.this
                int r1 = com.hzbhd.canbus.car._251.MsgMgr.access$getCurrentCanDifferentId(r1)
                r3 = 12
                r4 = 9
                if (r1 == r4) goto L41
                if (r1 == r3) goto L41
                switch(r1) {
                    case 14: goto L41;
                    case 15: goto L41;
                    case 16: goto L3e;
                    case 17: goto L3e;
                    case 18: goto L3e;
                    default: goto L3b;
                }
            L3b:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            L3e:
                r1 = 161(0xa1, float:2.26E-43)
                goto L43
            L41:
                r1 = 160(0xa0, float:2.24E-43)
            L43:
                com.hzbhd.canbus.car._251.MsgMgr r5 = com.hzbhd.canbus.car._251.MsgMgr.this
                int r5 = com.hzbhd.canbus.car._251.MsgMgr.access$getCurrentCanDifferentId(r5)
                r6 = 0
                r7 = 3
                r8 = 2
                if (r5 == r4) goto L5c
                if (r5 == r3) goto L5a
                switch(r5) {
                    case 14: goto L58;
                    case 15: goto L56;
                    case 16: goto L5c;
                    case 17: goto L58;
                    case 18: goto L56;
                    default: goto L53;
                }
            L53:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            L56:
                r3 = r7
                goto L5d
            L58:
                r3 = r8
                goto L5d
            L5a:
                r3 = r6
                goto L5d
            L5c:
                r3 = r2
            L5d:
                r4 = 4
                byte[] r4 = new byte[r4]
                r5 = 22
                r4[r6] = r5
                r5 = -18
                r4[r2] = r5
                byte r1 = (byte) r1
                r4[r8] = r1
                byte r1 = (byte) r3
                r4[r7] = r1
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r4)
                goto L1c
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._251.MsgMgr.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        GeneralParkData.isShowDistanceAndShowLocationUi = true;
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        UiMgr uiMgr = (UiMgr) canUiMgr;
        this.mUiMgr = uiMgr;
        if (uiMgr == null) {

            uiMgr = null;
        }
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
        UiMgr uiMgr2 = this.mUiMgr;
        if (uiMgr2 == null) {

            uiMgr2 = null;
        }
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, uiMgr2, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {


        this.mCanBusInfoByte = canbusInfo;
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            keyControl0x21();
            return;
        }
        if (i == 48) {
            setTrackData();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        if (i == 54) {
            setAirData0x36();
            return;
        }
        if (i != 55) {
            switch (i) {
                case 35:
                    setAirData0x23();
                    break;
                case 36:
                    rearRadar();
                    break;
                case 37:
                    anotherRearRadar();
                    break;
                case 38:
                    frontRadar();
                    break;
                case 39:
                    information();
                    break;
                case 40:
                    setDoorData0x28();
                    break;
                case 41:
                    setSettingData();
                    break;
            }
            return;
        }
        setPanoramaData();
    }

    private final void setTrackData() {
        if (getCurrentCanDifferentId() == 16 || getCurrentCanDifferentId() == 17 || getCurrentCanDifferentId() == 18) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 7818, 13814, 16);
        } else {
            byte[] bArr2 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr2[3], bArr2[2], 5480, 10960, 16);
        }
        updateParkUi(null, this.mContext);
    }

    private final void setPanoramaData() {
        Integer num;
        PanoramicBtnUpdateEntity panoramicBtnUpdateEntity;
        int i = this.mCanBusInfoInt[2];
        if (i == 5) {
            num = 0;
        } else if (i == 6) {
            num = 1;
        } else if (i == 7) {
            num = 2;
        } else {
            num = i != 8 ? null : 3;
        }
        ArrayList arrayList = new ArrayList(4);
        for (int i2 = 0; i2 < 4; i2++) {
            if (num != null && i2 == num.intValue()) {
                panoramicBtnUpdateEntity = new PanoramicBtnUpdateEntity(i2, true);
            } else {
                panoramicBtnUpdateEntity = new PanoramicBtnUpdateEntity(i2, false);
            }
            arrayList.add(panoramicBtnUpdateEntity);
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private final void setSettingData() {
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S251_Other_1");
                if (itemListBean != null) {
                    itemListBean.setValue(itemListBean.getValueSrnArray().get(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
            case 2:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S251_Other_2");
                if (itemListBean2 != null) {
                    itemListBean2.setValue(Integer.valueOf(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
            case 3:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S251_Other_3");
                if (itemListBean3 != null) {
                    itemListBean3.setValue(Integer.valueOf(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
            case 4:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S251_Other_4");
                if (itemListBean4 != null) {
                    itemListBean4.setValue(itemListBean4.getValueSrnArray().get(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
            case 5:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S251_Other_5");
                if (itemListBean5 != null) {
                    itemListBean5.setValue(Integer.valueOf(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
            case 7:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S251_Other_6");
                if (itemListBean6 != null) {
                    itemListBean6.setValue(itemListBean6.getValueSrnArray().get(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
            case 8:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S251_Other_7");
                if (itemListBean7 != null) {
                    itemListBean7.setValue(itemListBean7.getValueSrnArray().get(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
            case 9:
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S251_Other_8");
                if (itemListBean8 != null) {
                    itemListBean8.setValue(Integer.valueOf(this.mCanBusInfoInt[3]));
                    break;
                }
                break;
        }
        updateSettingActivity(null);
    }

    private static final void information$setData(String str, String str2) {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get(str);
        if (item == null) {
            return;
        }
        item.setValue(str2);
    }

    private final void information() {
        if (this.mCanBusInfoInt[2] == 1) {
            information$setData("D251_OriginalCar_1", (this.mCanBusInfoInt[3] - 40) + " °C");
            information$setData("D251_OriginalCar_2", (this.mCanBusInfoInt[4] - 40) + " °C");
        }
        if (this.mCanBusInfoInt[2] == 2) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            information$setData("D251_Battery_1", sb.append(DataHandleUtils.getMsbLsbResult(iArr[3], iArr[4]) / 1000.0f).append(" V").toString());
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            information$setData("D251_Battery_2", sb2.append(DataHandleUtils.getMsbLsbResult(iArr2[5], iArr2[6]) / 1000.0f).append(" V").toString());
            information$setData("D251_Battery_3", String.valueOf(this.mCanBusInfoInt[7]));
            information$setData("D251_Battery_4", String.valueOf(this.mCanBusInfoInt[8]));
            information$setData("D251_Battery_5", (this.mCanBusInfoInt[9] - 50) + " °C");
            information$setData("D251_Battery_6", (this.mCanBusInfoInt[10] - 50) + " °C");
            information$setData("D251_Battery_7", String.valueOf(this.mCanBusInfoInt[11]));
            information$setData("D251_Battery_8", String.valueOf(this.mCanBusInfoInt[12]));
            information$setData("D251_Battery_9", String.valueOf(this.mCanBusInfoInt[13]));
        }
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3[2] == 3) {
            int i = iArr3[3];
            String str = "----";
            information$setData("D251_CarBody_1", i != 1 ? i != 2 ? i != 3 ? "----" : "慢充中" : "快充中" : "放电中");
            int i2 = this.mCanBusInfoInt[4];
            if (i2 == 1) {
                str = "停车充电";
            } else if (i2 == 2) {
                str = "行驶充电";
            } else if (i2 == 3) {
                str = "未充电";
            } else if (i2 == 4) {
                str = "充电完成";
            }
            information$setData("D251_CarBody_2", str);
        }
        updateDriveDataActivity(null);
    }

    private final void frontRadar() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private final void rearRadar() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[2], 0, 0, iArr[3]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private final void anotherRearRadar() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private final void realKeyClick(int value) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, value, iArr[2], iArr[3]);
    }

    public final int getI() {
        return this.i;
    }

    public final void setI(int i) {
        this.i = i;
    }

    private final void keyControl0x21() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 11) {
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 12) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
            return;
        }
        if (i == 50) {
            realKeyLongClick1(this.mContext, 128, iArr[3]);
            return;
        }
        if (i == 151) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
            return;
        }
        if (i != 153) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 15, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 129:
                            realKeyLongClick1(this.mContext, 52, iArr[3]);
                            break;
                        case 130:
                            realKeyLongClick1(this.mContext, 50, iArr[3]);
                            break;
                        case 131:
                            realKeyLongClick1(this.mContext, 59, iArr[3]);
                            break;
                        case 132:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_DISP, iArr[3]);
                            break;
                        case 133:
                            realKeyLongClick1(this.mContext, 47, iArr[3]);
                            break;
                        case HotKeyConstant.K_SLEEP /* 134 */:
                            realKeyLongClick1(this.mContext, 76, iArr[3]);
                            break;
                        case 135:
                            realKeyLongClick1(this.mContext, 1, iArr[3]);
                            break;
                        case 136:
                            realKeyLongClick1(this.mContext, 48, iArr[3]);
                            break;
                        case 137:
                            realKeyLongClick1(this.mContext, 77, iArr[3]);
                            break;
                        case 138:
                            realKeyLongClick1(this.mContext, 59, iArr[3]);
                            break;
                        case 139:
                            realKeyLongClick1(this.mContext, 68, iArr[3]);
                            break;
                        case 140:
                            realKeyLongClick1(this.mContext, 58, iArr[3]);
                            break;
                        case 141:
                            realKeyLongClick1(this.mContext, 4, iArr[3]);
                            break;
                        case 142:
                            int i2 = this.i;
                            this.i = i2 + 1;
                            if (i2 % 2 != 0) {
                                forceReverse(this.mContext, false);
                                break;
                            } else {
                                forceReverse(this.mContext, true);
                                break;
                            }
                        default:
                            switch (i) {
                                case 144:
                                    realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_MEDIA, iArr[3]);
                                    break;
                                case 145:
                                    realKeyLongClick1(this.mContext, 151, iArr[3]);
                                    break;
                                case 146:
                                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                                    break;
                                case 147:
                                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                                    break;
                                case 148:
                                    realKeyLongClick1(this.mContext, 47, iArr[3]);
                                    break;
                                case 149:
                                    realKeyLongClick1(this.mContext, 48, iArr[3]);
                                    break;
                                default:
                                    switch (i) {
                                        case 241:
                                            DataHandleUtils.knob(this.mContext, 8, iArr[3]);
                                            break;
                                        case 242:
                                            DataHandleUtils.knob(this.mContext, 7, iArr[3]);
                                            break;
                                        case 243:
                                            DataHandleUtils.knob(this.mContext, 47, iArr[3]);
                                            break;
                                        case NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD /* 244 */:
                                            DataHandleUtils.knob(this.mContext, 48, iArr[3]);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 95, iArr[3]);
    }

    private final void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private final void setDoorData0x28() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private final void setAirData0x23() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        MsgMgrKt.windDirectionInit();
        int i = this.mCanBusInfoInt[3];
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        int i2 = this.mCanBusInfoInt[5];
        if (i2 == 0) {
            str = "LOW";
        } else if (i2 == 255) {
            str = "HIG";
        } else {
            if (17 <= i2 && i2 < 32) {
                str = i2 + " °C";
            } else {
                if (124 <= i2 && i2 < 157) {
                    str = (16 + ((i2 - 124) * 0.5d)) + " °C";
                }
                GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
                updateAirActivity(this.mContext, 1004);
            }
        }
        GeneralAirData.front_left_temperature = str;
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1004);
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    private final void setAirData0x36() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -78, (byte) (bSecond << 2), (byte) (bMins << 2), (byte) (bHours24H << 3), (byte) (bDay << 3), (byte) (bMonth << 4), (byte) (bYear2Dig << 2)});
    }

    private final String resolveOutDoorTem() {
        String string;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            string = new StringBuilder().append('-').append(intFromByteWithBit).toString();
        } else {
            string = intFromByteWithBit + "";
        }
        return string + getTempUnitC(this.mContext);
    }

    private final String resolveLeftAndRightTemp(int value) {
        if (value == 0) {
            return "LOW";
        }
        if (value == 255) {
            return "HIG";
        }
        if (18 <= value && value < 32) {
            return value + getTempUnitC(this.mContext);
        }
        return 124 <= value && value < 157 ? (((value - 124) * 0.5d) + 16) + getTempUnitC(this.mContext) : "--";
    }

    private final void showDialog(final String showContent) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._251.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public final void callback() {
                MsgMgr.m327showDialog$lambda6(this.f$0, showContent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDialog$lambda-6, reason: not valid java name */
    public static final void m327showDialog$lambda6(MsgMgr this$0, String showContent) {


        new AlertView().showDialog(this$0.getActivity(), showContent);
    }
}
