package com.hzbhd.canbus.car._348;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\u0018\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u0019J \u0010\u001a\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/hzbhd/canbus/car/_348/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getMContext", "()Landroid/content/Context;", "setMContext", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getMParkPageUiSet", "()Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "msgMgr", "Lcom/hzbhd/canbus/car/_348/MsgMgr;", "tag", "", "getTag", "()I", "setTag", "(I)V", "uiMgr", "getMsgMgr", "context", "getSettingLeftIndexes", "titleSrn", "", "getSettingRightIndex", "leftTitleSrn", "rightTitleStn", "getUiMgr", "sendAirCmd", "", "bytes", "", "sendWindMode", "updateUiByDifferentCar", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private final ParkPageUiSet mParkPageUiSet;
    private MsgMgr msgMgr;
    private int tag;
    private UiMgr uiMgr;

    public UiMgr(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(mContext);
        Intrinsics.checkNotNullExpressionValue(parkPageUiSet, "getParkPageUiSet(mContext)");
        this.mParkPageUiSet = parkPageUiSet;
        getAirUiSet(this.mContext).getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m742_init_$lambda0(this.f$0, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m743_init_$lambda1(this.f$0, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m744_init_$lambda2(this.f$0, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m745_init_$lambda3(this.f$0, i);
            }
        }});
        getAirUiSet(this.mContext).getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendWindMode();
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendWindMode();
            }
        });
        getAirUiSet(this.mContext).getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCmd(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCmd(new byte[]{22, -88, 11, 1});
            }
        });
        getAirUiSet(this.mContext).getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCmd(new byte[]{22, -88, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCmd(new byte[]{22, -88, 14, 1});
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._348.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCmd(new byte[]{22, -88, 15, 1});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCmd(new byte[]{22, -88, 16, 1});
            }
        }});
        if (getEachId() == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1});
        }
        if (getEachId() == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2});
        }
        if (getEachId() == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 3});
        }
        if (getEachId() == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4});
        }
        final SettingPageUiSet settingUiSet = getSettingUiSet(this.mContext);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._348.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i, int i2, int i3) {
                UiMgr.m746_init_$lambda4(this.f$0, settingUiSet, i, i2, i3);
            }
        });
        final int i = this.mContext.getResources().getDisplayMetrics().widthPixels;
        final int i2 = this.mContext.getResources().getDisplayMetrics().heightPixels;
        parkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener() { // from class: com.hzbhd.canbus.car._348.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener
            public final void onTouchItem(MotionEvent motionEvent) {
                UiMgr.m747_init_$lambda5(i, i2, motionEvent);
            }
        });
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    public final ParkPageUiSet getMParkPageUiSet() {
        return this.mParkPageUiSet;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (getEachId() == 2) {
            removeMainPrjBtnByName(this.mContext, "air");
        }
        if (getEachId() == 1 || getEachId() == 3) {
            return;
        }
        removeMainPrjBtnByName(this.mContext, MainAction.SETTING);
    }

    public final int getSettingLeftIndexes(Context context, String titleSrn) {
        Intrinsics.checkNotNullParameter(titleSrn, "titleSrn");
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(titleSrn, it.next().getTitleSrn())) {
                return i;
            }
        }
        return 404;
    }

    public final int getSettingRightIndex(Context context, String leftTitleSrn, String rightTitleStn) {
        Intrinsics.checkNotNullParameter(leftTitleSrn, "leftTitleSrn");
        Intrinsics.checkNotNullParameter(rightTitleStn, "rightTitleStn");
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (Intrinsics.areEqual(leftTitleSrn, next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                int size2 = itemList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    if (Intrinsics.areEqual(rightTitleStn, it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    private final MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
            Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._348.MsgMgr");
            this.msgMgr = (MsgMgr) canMsgMgr;
        }
        return this.msgMgr;
    }

    private final UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
            Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._348.UiMgr");
            this.uiMgr = (UiMgr) canUiMgr;
        }
        return this.uiMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m742_init_$lambda0(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirCmd(new byte[]{22, -88, 1, 1});
        } else {
            if (i != 1) {
                return;
            }
            this$0.sendAirCmd(new byte[]{22, -88, 0, 1});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m743_init_$lambda1(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirCmd(new byte[]{22, -88, 6, 1});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m744_init_$lambda2(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            this$0.sendAirCmd(new byte[]{22, -88, 23, 1});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m745_init_$lambda3(UiMgr this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 1) {
            this$0.sendAirCmd(new byte[]{22, -88, 2, 1});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: _init_$lambda-4, reason: not valid java name */
    public static final void m746_init_$lambda4(UiMgr this$0, SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MsgMgr msgMgr = this$0.getMsgMgr(this$0.mContext);
        Intrinsics.checkNotNull(msgMgr);
        msgMgr.updateSetting(i, i2, i3);
        String titleSrn2 = settingPageUiSet.getList().get(i).getTitleSrn();
        if (Intrinsics.areEqual(titleSrn2, "car_light_set")) {
            String titleSrn3 = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
            if (Intrinsics.areEqual(titleSrn3, "ceiling_light_delay")) {
                CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 0, (byte) i3});
                return;
            } else {
                if (Intrinsics.areEqual(titleSrn3, "power_saving_time")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 1, (byte) i3});
                    return;
                }
                return;
            }
        }
        if (!Intrinsics.areEqual(titleSrn2, "car_lock_set") || (titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn()) == null) {
            return;
        }
        switch (titleSrn.hashCode()) {
            case -1498083263:
                if (titleSrn.equals("_282_07_0_6")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 5, (byte) i3});
                    break;
                }
                break;
            case -250079211:
                if (titleSrn.equals("_1193_setting_1_8")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 6, (byte) i3});
                    break;
                }
                break;
            case 259117024:
                if (titleSrn.equals("remote_lock_feedback")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 4, (byte) i3});
                    break;
                }
                break;
            case 357471858:
                if (titleSrn.equals("automatic_relock")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 3, (byte) i3});
                    break;
                }
                break;
            case 632066595:
                if (titleSrn.equals("speed_lock")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MIN_VALUE, 2, (byte) i3});
                    break;
                }
                break;
        }
    }

    /* renamed from: lambda-5$sendPanoramaFrame, reason: not valid java name */
    private static final void m748lambda5$sendPanoramaFrame(int i, int i2, int i3, int i4, int i5) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-5, reason: not valid java name */
    public static final void m747_init_$lambda5(int i, int i2, MotionEvent motionEvent) {
        int x = (int) (motionEvent.getX() * (800.0f / i));
        int y = (int) (motionEvent.getY() * (480.0f / i2));
        if (motionEvent.getAction() == 0) {
            m748lambda5$sendPanoramaFrame(1, DataHandleUtils.getMsb(x), DataHandleUtils.getLsb(x), DataHandleUtils.getMsb(y), DataHandleUtils.getLsb(y));
        } else if (motionEvent.getAction() == 1) {
            m748lambda5$sendPanoramaFrame(0, DataHandleUtils.getMsb(x), DataHandleUtils.getLsb(x), DataHandleUtils.getMsb(y), DataHandleUtils.getLsb(y));
        }
        Log.i("lyn", " x:" + x + ", y:" + y + " \n width:" + i + ", high:" + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCmd(byte[] bytes) {
        CanbusMsgSender.sendMsg(bytes);
    }

    public final int getTag() {
        return this.tag;
    }

    public final void setTag(int i) {
        this.tag = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendWindMode() {
        int i = this.tag;
        if (i == 0) {
            sendAirCmd(new byte[]{22, -88, 7, 1});
            this.tag = 1;
            return;
        }
        if (i == 1) {
            sendAirCmd(new byte[]{22, -88, 8, 1});
            this.tag = 2;
        } else if (i == 2) {
            sendAirCmd(new byte[]{22, -88, 9, 1});
            this.tag = 3;
        } else if (i == 3) {
            sendAirCmd(new byte[]{22, -88, 10, 1});
            this.tag = 0;
        }
    }
}
