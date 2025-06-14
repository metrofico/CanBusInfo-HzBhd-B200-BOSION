package com.hzbhd.canbus.car._209;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    static final String SHARE_209_FRONT_CAMERA_SWITCH = "share_209_front_camera_switch";
    static final String SHARE_209_FRONT_LINK_TURN_SIGNAL = "share_209_front_link_turn_signal";
    private AirActivity mActivity;
    private Context mContext;
    private boolean mIsSearching;
    private MsgMgr mMsgMgr;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 34, 0, 0, 0, 0, 0, 0, 0});
            }
        });
        originalCarDevicePageUiSet.setOnOriginalSongItemClickListener(new OnOriginalSongItemClickListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemClick(int i) {
                int presetListSize = UiMgr.this.getMsgMgr(context).getPresetListSize();
                if (i > 0 && i <= presetListSize - 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte) (i - 1)});
                } else if (i > presetListSize) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte) ((i - 1) - presetListSize)});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener
            public void onSongItemLongClick(int i) {
                int presetListSize = UiMgr.this.getMsgMgr(context).getPresetListSize();
                if (i <= 0 || i > presetListSize) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte) (i - 1)});
            }
        });
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i) {
                String str = originalCarDevicePageUiSet.getRowTopBtnAction()[i];
                str.hashCode();
                switch (str) {
                    case "am":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, 2});
                        break;
                    case "fm":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, 1});
                        break;
                    case "scan":
                        byte[] bArr = new byte[4];
                        bArr[0] = 22;
                        bArr[1] = -58;
                        bArr[2] = 49;
                        bArr[3] = (byte) (GeneralOriginalCarDeviceData.scan ? 2 : 1);
                        CanbusMsgSender.sendMsg(bArr);
                        break;
                    case "refresh":
                        byte[] bArr2 = new byte[4];
                        bArr2[0] = 22;
                        bArr2[1] = -58;
                        bArr2[2] = 52;
                        bArr2[3] = (byte) (GeneralOriginalCarDeviceData.refresh ? 2 : 1);
                        CanbusMsgSender.sendMsg(bArr2);
                        break;
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
                str.hashCode();
                switch (str) {
                    case "prev_disc":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, 2});
                        break;
                    case "up":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, 2});
                        break;
                    case "down":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, 1});
                        break;
                    case "left":
                        byte[] bArr = new byte[4];
                        bArr[0] = 22;
                        bArr[1] = -58;
                        bArr[2] = 48;
                        bArr[3] = (byte) (UiMgr.this.mIsSearching ? 3 : 2);
                        CanbusMsgSender.sendMsg(bArr);
                        UiMgr.this.mIsSearching = !r9.mIsSearching;
                        break;
                    case "right":
                        byte[] bArr2 = new byte[4];
                        bArr2[0] = 22;
                        bArr2[1] = -58;
                        bArr2[2] = 48;
                        bArr2[3] = (byte) (UiMgr.this.mIsSearching ? 3 : 1);
                        CanbusMsgSender.sendMsg(bArr2);
                        UiMgr.this.mIsSearching = !r9.mIsSearching;
                        break;
                    case "next_disc":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, 1});
                        break;
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
            public void onBackPressed() {
                CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
            }
        });
        getMsgMgr(context).updateSettings(6, 1, SharePreUtil.getIntValue(context, SHARE_209_FRONT_LINK_TURN_SIGNAL, 1));
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "contrast":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte) (i3 + 5)});
                        break;
                    case "saturation":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, (byte) (i3 + 5)});
                        break;
                    case "brightness":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte) (i3 + 5)});
                        break;
                    case "_55_0x69_data1_bit20":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) (i3 + 5)});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_41_default_all":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, 0});
                        break;
                    case "front_camera_switch":
                        boolean boolValue = SharePreUtil.getBoolValue(context, UiMgr.SHARE_209_FRONT_CAMERA_SWITCH, false);
                        Log.i("ljq", "onConfirmClick: frontCameraSwitch -->" + boolValue);
                        SharePreUtil.setBoolValue(context, UiMgr.SHARE_209_FRONT_CAMERA_SWITCH, !boolValue);
                        break;
                    case "_55_0x6E_0x06":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
                        break;
                    case "_41_delete_fuel_record":
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_55_0x65_data1_bit21":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) i3});
                        break;
                    case "_55_0x65_data1_bit54":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                        break;
                    case "_55_0x65_data1_bit76":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                        break;
                    case "_209_32_1_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                        break;
                    case "_209_32_3_7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case "_209_guide_tips":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -31, (byte) i3});
                        break;
                    case "_41_key_remote_unlock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                        break;
                    case "_209_screen_background_color":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        break;
                    case "_55_0x69_data1_bit43":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3});
                        break;
                    case "_55_0x69_data1_bit65":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                        break;
                    case "_194_unlock_mode":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte) i3});
                        break;
                    case "_55_0x68_data1_bit10":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte) i3});
                        break;
                    case "_55_0x68_data1_bit54":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte) i3});
                        break;
                    case "_209_screen_display":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte) i3});
                        break;
                    case "_55_0x67_data1_bit10":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                        break;
                    case "_55_0x67_data1_bit32":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                        break;
                    case "_55_0x67_data1_bit64":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                        break;
                    case "_55_0x65_data1_bit0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                        break;
                    case "_55_0x66_data1_bit0":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, (byte) i3});
                        break;
                    case "_55_0x66_data1_bit1":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte) i3});
                        break;
                    case "_55_0x66_data1_bit2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte) i3});
                        break;
                    case "_55_0x66_data1_bit3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case "_55_0x68_data1_bit2":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i3});
                        break;
                    case "_55_0x68_data1_bit3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) i3});
                        break;
                    case "_55_0x69_data0_bit3":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte) i3});
                        break;
                    case "_55_0x69_data1_bit7":
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -32, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._209.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                this.f$0.m306lambda$new$0$comhzbhdcanbuscar_209UiMgr(settingUiSet, context, i, i2, i3);
            }
        });
        final DriverDataPageUiSet driverDataPageUiSet = getDriverDataPageUiSet(context);
        driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.9
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    int[] settingItemPosition = UiMgr.this.getSettingItemPosition(settingUiSet, "_41_delete_fuel_record");
                    driverDataPageUiSet.setLeftPosition(settingItemPosition[0]);
                    driverDataPageUiSet.setRightPosition(settingItemPosition[1]);
                }
            }
        });
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._209.UiMgr.10
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, 64, (byte) i});
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_209-UiMgr, reason: not valid java name */
    /* synthetic */ void m306lambda$new$0$comhzbhdcanbuscar_209UiMgr(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_157_front_link_turn")) {
            SharePreUtil.setIntValue(context, SHARE_209_FRONT_LINK_TURN_SIGNAL, i3);
            getMsgMgr(context).updateSettings(6, 1, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] getSettingItemPosition(SettingPageUiSet settingPageUiSet, String str) {
        int[] iArr = {-1, -1};
        for (int i = 0; i < settingPageUiSet.getList().size(); i++) {
            SettingPageUiSet.ListBean listBean = settingPageUiSet.getList().get(i);
            for (int i2 = 0; i2 < listBean.getItemList().size(); i2++) {
                if (listBean.getItemList().get(i2).getTitleSrn().equals(str)) {
                    iArr[0] = i;
                    iArr[1] = i2;
                }
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
