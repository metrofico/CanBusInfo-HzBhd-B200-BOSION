package com.hzbhd.canbus.car._452;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._451.util.CycleRequestSpeed;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.ui.util.BaseUtil;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class UiMgr extends AbstractUiMgr {
    private static final int CONFIGURATION_camera_452 = 1;
    private static final int CONFIGURATION_defualt_452 = -1;
    private static final int CONFIGURATION_front_camera_452 = 0;
    Context mContext;
    private MsgMgr mMsgMgr;
    private final int BENZ_EACH_ID = 1;
    private final int BMW_EACH_ID = 2;
    private final int LEXUS_EACH_ID = 3;
    private final int DriverDataPageUiSet_ON_CREATE = -1;
    private final int DriverDataPageUiSet_ON_DESTROY = -2;
    DecimalFormat towDecimal = new DecimalFormat("###0.00");
    DecimalFormat oneDecimal = new DecimalFormat("###0.0");
    DecimalFormat timeFormat = new DecimalFormat("00");
    private String resolution_ratio_tag = "resolution.ratio.tag";
    public String CAMERA_FLAG_TAG_452 = "ORIGINAL.MEDIA.CAMERA.FLAG_452";
    OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._452.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_451_host_settings")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_451_host_settings", "_451_host_settings_1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -110, (byte) i3, 0});
                }
            }
            UiMgr uiMgr3 = UiMgr.this;
            if (i == uiMgr3.getSettingLeftIndexes(uiMgr3.mContext, "_451_resolving_power")) {
                UiMgr uiMgr4 = UiMgr.this;
                if (i2 == uiMgr4.getSettingRightIndex(uiMgr4.mContext, "_451_resolving_power", "_451_reverse_flag")) {
                    if (i3 == 0) {
                        CanbusConfig.INSTANCE.setCameraConfiguration(true);
                        UiMgr uiMgr5 = UiMgr.this;
                        uiMgr5.getMsgMgr(uiMgr5.mContext).updateSettingsUi(UiMgr.this.mContext, i, i2, UiMgr.this.CAMERA_FLAG_TAG_452, 0);
                    } else if (i3 == 1) {
                        CanbusConfig.INSTANCE.setCameraConfiguration(false);
                        UiMgr uiMgr6 = UiMgr.this;
                        uiMgr6.getMsgMgr(uiMgr6.mContext).updateSettingsUi(UiMgr.this.mContext, i, i2, UiMgr.this.CAMERA_FLAG_TAG_452, 1);
                    }
                }
                UiMgr uiMgr7 = UiMgr.this;
                if (i2 == uiMgr7.getSettingRightIndex(uiMgr7.mContext, "_451_resolving_power", "_451_benz_fbv")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i3});
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.this.resolution_ratio_tag, i3);
                    UiMgr uiMgr8 = UiMgr.this;
                    uiMgr8.getMsgMgr(uiMgr8.mContext).updateSettings(i, i2, i3);
                }
                UiMgr uiMgr9 = UiMgr.this;
                if (i2 == uiMgr9.getSettingRightIndex(uiMgr9.mContext, "_451_resolving_power", "_451_bmw_fbv")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i3});
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.this.resolution_ratio_tag, i3);
                    UiMgr uiMgr10 = UiMgr.this;
                    uiMgr10.getMsgMgr(uiMgr10.mContext).updateSettings(i, i2, i3);
                }
                UiMgr uiMgr11 = UiMgr.this;
                if (i2 == uiMgr11.getSettingRightIndex(uiMgr11.mContext, "_451_resolving_power", "_451_audi_fbv")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) i3});
                    SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.this.resolution_ratio_tag, i3);
                    UiMgr uiMgr12 = UiMgr.this;
                    uiMgr12.getMsgMgr(uiMgr12.mContext).updateSettings(i, i2, i3);
                }
            }
        }
    };
    OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._452.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_451_resolving_power")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_451_resolving_power", "_451_resolving_power_1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) (i3 - 1)});
                }
            }
        }
    };
    OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._452.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            UiMgr uiMgr = UiMgr.this;
            if (i == uiMgr.getSettingLeftIndexes(uiMgr.mContext, "_451_activation")) {
                UiMgr uiMgr2 = UiMgr.this;
                if (i2 == uiMgr2.getSettingRightIndex(uiMgr2.mContext, "_451_activation", "_451_activation_1")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 1});
                    BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._452.UiMgr.4.1
                        @Override // kotlin.jvm.functions.Function0
                        public Unit invoke() {
                            Toast.makeText(UiMgr.this.mContext, UiMgr.this.mContext.getString(R.string._451_activation_2), 0).show();
                            return null;
                        }
                    });
                }
            }
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingItemClickListener(this.onSettingItemClickListener);
        settingUiSet.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._452.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    CycleRequestSpeed.getInstance().start(0, new ActionCallback() { // from class: com.hzbhd.canbus.car._452.UiMgr.1.1
                        @Override // com.hzbhd.canbus.car_cus._451.Interface.ActionCallback
                        public void toDo(Object obj) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -112, 22});
                            CycleRequestSpeed.getInstance().reset(500);
                        }
                    });
                }
                if (i == -2) {
                    CycleRequestSpeed.getInstance().stop();
                }
            }
        });
        removeSettingLeftItemByNameList(context, new String[]{"_451_host_settings"});
        removeDriveDateItemForTitles(context, new String[]{"_451_acc_status", "_451_gear_status", "_451_current_oil_quantity", "_451_small_lamp_status"});
        if (getEachId() != 1) {
            removeSettingLeftItemByNameList(context, new String[]{"_451_activation"});
        }
        if (getEachId() == 1) {
            removeSettingRightItemByNameList(context, new String[]{"_451_audi_fbv", "_451_bmw_fbv"});
        } else if (getEachId() == 2) {
            removeSettingRightItemByNameList(context, new String[]{"_451_benz_fbv", "_451_audi_fbv"});
        } else {
            removeSettingRightItemByNameList(context, new String[]{"_451_benz_fbv", "_451_bmw_fbv", "_451_audi_fbv"});
        }
        initResolutionAatio();
        initCamera(context);
    }

    private void initResolutionAatio() {
        int intValue = SharePreUtil.getIntValue(this.mContext, this.resolution_ratio_tag, 0);
        CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) intValue});
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_451_resolving_power"), getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_benz_fbv"), intValue);
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_451_resolving_power"), getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_bmw_fbv"), intValue);
        getMsgMgr(this.mContext).updateSettings(getSettingLeftIndexes(this.mContext, "_451_resolving_power"), getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_audi_fbv"), intValue);
    }

    private void initCamera(Context context) {
        int intValue = SharePreUtil.getIntValue(context, this.CAMERA_FLAG_TAG_452, -1);
        if (intValue == -1) {
            getMsgMgr(context).updateSettingsUi(context, getSettingLeftIndexes(context, "_451_resolving_power"), getSettingRightIndex(context, "_451_resolving_power", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_452, 1 ^ (CanbusConfig.INSTANCE.getCameraConfiguration() ? 1 : 0));
        } else {
            getMsgMgr(context).updateSettingsUi(context, getSettingLeftIndexes(context, "_451_resolving_power"), getSettingRightIndex(context, "_451_resolving_power", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_452, intValue);
            CanbusConfig.INSTANCE.setCameraConfiguration(intValue == 0);
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    protected int getSettingRightIndex(Context context, String str, String str2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (str.equals(next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (str2.equals(it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
