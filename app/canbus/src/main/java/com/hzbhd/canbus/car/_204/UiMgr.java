package com.hzbhd.canbus.car._204;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    static final String SHARE_204_LANGUAGE = "share_204_language";
    static final String SHARE_204_REAR_MIRROR_FOLD = "share_204_rear_mirror_fold";
    static final String SHARE_204_SURROUND_SOUND_TYPE = "share_204_surround_sound_type";
    static final String SHARE_204_VOL_WITH_SPEED = "share_204_vol_with_speed";
    static final String SHARE_IS_SUPPORTED_FRONT_VIEW = "share_is_supported_front_view";
    static final String SHARE_IS_SUPPORTED_PANORAMIC = "share_is_supported_panoramic";
    private final String TAG = "_204_UiMgr";
    private Context mContext;
    private View mCusPanoramicView;
    private MsgMgr mMsgMgr;
    private ParkPageUiSet mParkPageUiSet;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mCusPanoramicView == null) {
            this.mCusPanoramicView = new MyPanoramicView(context);
        }
        return this.mCusPanoramicView;
    }

    public UiMgr(final Context context) {
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        this.mContext = context;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._204.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass6.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) i});
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) i});
                    return;
                }
                if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) i});
                } else if (i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, 1});
                } else {
                    if (i2 != 5) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, -1});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._204.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass6.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (i + 10)});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (i + 10)});
                }
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._204.UiMgr.3
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:4:0x0037  */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClickItem(int r20, int r21, int r22) {
                /*
                    Method dump skipped, instructions count: 542
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._204.UiMgr.AnonymousClass3.onClickItem(int, int, int):void");
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._204.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_1204_trailer_setup")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 111, 20, (byte) i3, -1, -1});
                }
            }
        });
        settingUiSet.getOnSettingItemSelectListener().onClickItem(1, 0, SharePreUtil.getIntValue(context, SHARE_204_LANGUAGE, 0));
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        this.mParkPageUiSet = parkPageUiSet;
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._204.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
                } else if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._204.UiMgr$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME_Plus.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME_Min.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
