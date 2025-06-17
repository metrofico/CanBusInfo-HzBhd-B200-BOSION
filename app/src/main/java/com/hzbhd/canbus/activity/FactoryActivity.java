package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.FactoryItemAdapter;
import com.hzbhd.canbus.car._0.CarSelectWindow;
import com.hzbhd.canbus.car._0.PanelSwitchWindow;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.GeneralSettingsConfig;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.util.TouchpadEvents;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.ui.util.BaseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;


public final class FactoryActivity extends Activity {
    public static final int STYLE_0 = 0;
    public static final int STYLE_1 = 1;
    public static final int STYLE_4 = 4;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap<>();

    private final CanSettingProxy mProxy;

    /* JADX INFO: Access modifiers changed from: private */
    public int toInt(boolean z) {
        return z ? 1 : 0;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        map.put(i, viewFindViewById);
        return viewFindViewById;
    }

    public FactoryActivity() {
        this.mProxy = Dependency.get(CanSettingProxy.class);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        initView();
    }

    private void initView() {
        FactoryActivity factoryActivity = this;
        ((RecyclerView) _$_findCachedViewById(R.id.rv_setting_items)).setLayoutManager(new LinearLayoutManager(factoryActivity));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rv_setting_items);
        ArrayList<ItemUiSet> arrayList = new ArrayList<>();
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).RADAR_SHOW_TAG) {
            arrayList.add(new ItemUiSet("radar_display", STYLE_4, integer -> mProxy.setRadarDispCheck(integer == 1), () -> mProxy.getRadarDispCheck() ? 1 : 0, null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).BACK_TRAJECTORY_REVERSAL) {
            arrayList.add(new ItemUiSet("back_trajectiry_reversal", STYLE_4, value -> mProxy.setBackTrajectiryDispCheck(value == 1), () -> toInt(mProxy.getBackTrajectiryDispCheck()), null, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).DOOR_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_door_info", STYLE_4, value -> mProxy.setShowDoorInfo(value == 1), () -> toInt(mProxy.getShowDoorInfo()), null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG) {

            arrayList.add(new ItemUiSet("swap_front_door", STYLE_4, value -> mProxy.setDoorSwapFront(value == 1), () -> toInt(mProxy.getDoorSwapFront()), null, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG) {
            arrayList.add(new ItemUiSet("swap_rear_door", STYLE_4, value -> mProxy.setDoorSwapRear(value == 1), () -> toInt(mProxy.getDoorSwapRear()), null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH) {
            arrayList.add(new ItemUiSet("_factoryActivity_add_function1", STYLE_4, value -> mProxy.setDoorCountDownTimerState(value == 1), () -> toInt(mProxy.getDoorCountDownTimerState()), null, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).THE_HOOD_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_the_hood", STYLE_4, value -> mProxy.setShowHood(value == 1), () -> toInt(mProxy.getShowHood()), null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TRUNK_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_the_trunk", STYLE_4, value -> mProxy.setShowTrunk(value == 1), () -> toInt(mProxy.getShowTrunk()), null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG) {
            arrayList.add(new ItemUiSet(BodaSysContant.Can.SwitchAcTemperature, STYLE_4, value -> mProxy.setSwitchAcTemperature(value == 1), () -> toInt(mProxy.getSwitchAcTemperature()), null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).AIR_SHOW_TAG) {
            arrayList.add(new ItemUiSet("air_display_setup", STYLE_1, mProxy::setAirDisplaySetup, mProxy::getAirDisplaySetup, new ArrayList<>(Arrays.asList("air_popup_status_0", "air_popup_status_1", "air_popup_status_2")), null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).OUT_TEMPERATURE_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_outdoor_temperature", STYLE_4, value -> mProxy.setShowOutdoorTemperature(value == 1), () -> toInt(mProxy.getShowOutdoorTemperature()), null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TEMPERATURE_UNIT_TAG) {
            arrayList.add(new ItemUiSet("temperature_unit", STYLE_1, mProxy::setTemperatureUnit, mProxy::getTemperatureUnit, new ArrayList<>(Arrays.asList("str_temp_c_unit", "str_temp_f_unit")), null));


        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SWC_STUDY_TAG) {

            arrayList.add(new ItemUiSet("swc_app_name", STYLE_0, null, null, null, () -> {
                ComponentName SwcActivity = Constant.SwcActivity;
                Intent intent = new Intent();
                intent.setComponent(SwcActivity);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).FRONT_CAMERA_TAG) {

            arrayList.add(new ItemUiSet("Factory_activity_open_camera", STYLE_0, null, null, null, () -> {
                ComponentName SwcActivity = Constant.FrontCameraSettingActivity;
                Intent intent = new Intent();
                intent.setComponent(SwcActivity);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TOUCH_PAD_SETTING_TAG) {
            arrayList.add(new ItemUiSet("car_pannale_control", STYLE_0, null, null, null, () -> {
                new TouchpadEvents().showAdjustView(FactoryActivity.this);
            }));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).CAN_BUS_TEST_TAG) {
            arrayList.add(new ItemUiSet("_open_bugly_item", STYLE_0, null, null, null, () -> {
                BaseUtil baseUtil = BaseUtil.INSTANCE;
                ComponentName CanBusDiagnosisActivity = Constant.CanBusDiagnosisActivity;
                baseUtil.startActivity(CanBusDiagnosisActivity);
            }));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SHOW_HIDE_SMART_PANEL_PAGE) {
            arrayList.add(new ItemUiSet("_439_panel_app_show_hide_switch", STYLE_0, null, null, null, () -> {
                new PanelSwitchWindow(BaseUtil.INSTANCE.getContext()).show();
            }));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SHOW_P_KEY_RADAR_WINDOW) {
            arrayList.add(new ItemUiSet("p_key_radar_switch", STYLE_4, value -> mProxy.setPKeyRadarDispCheck(value == 1), () -> toInt(mProxy.getPKeyRadarDispCheck()), null, null));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).WIKA_CAR_SELECT) {
            arrayList.add(new ItemUiSet("_439_car_select", STYLE_0, null, null, null, () -> {
                CarSelectWindow.getInstance().show();
            }));

        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).OPEN_CAN_DATA_LOG) {
            arrayList.add(new ItemUiSet("open_can_data_log_switch", STYLE_4, value -> mProxy.setCanDataLogSwith(value == 1), () -> toInt(mProxy.getCanDataLogSwith()), null, null));

        }
        recyclerView.setAdapter(new FactoryItemAdapter(factoryActivity, arrayList));
    }

    public FactoryActivity getInstance() {
        return this;
    }

    public static final class ItemUiSet {

        private final String titleResName;
        private final int style;
        private final Consumer<Integer> setValue;
        private final Supplier<Integer> getValue;
        private final List<String> listValues;
        private final Runnable onClick;

        public ItemUiSet(String titleResName, int style, Consumer<Integer> setValue, Supplier<Integer> getValue, List<String> listValues, Runnable onClick) {
            this.titleResName = titleResName;
            this.style = style;
            this.setValue = setValue;
            this.getValue = getValue;
            this.listValues = listValues;
            this.onClick = onClick;
        }

        // Getters
        public String getTitleResName() {
            return titleResName;
        }

        public int getStyle() {
            return style;
        }

        public Consumer<Integer> getSetValue() {
            return setValue;
        }

        public Supplier<Integer> getGetValue() {
            return getValue;
        }

        public List<String> getListValues() {
            return listValues;
        }

        public Runnable getOnClick() {
            return onClick;
        }

        // Copy method for immutability (like Kotlin's copy)
        public ItemUiSet copy(String titleResName, int style, Consumer<Integer> setValue, Supplier<Integer> getValue, List<String> listValues, Runnable onClick) {
            return new ItemUiSet(titleResName != null ? titleResName : this.titleResName, style, setValue != null ? setValue : this.setValue, getValue != null ? getValue : this.getValue, listValues != null ? listValues : this.listValues, onClick != null ? onClick : this.onClick);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ItemUiSet)) return false;
            ItemUiSet other = (ItemUiSet) o;
            return style == other.style && Objects.equals(titleResName, other.titleResName) && Objects.equals(setValue, other.setValue) && Objects.equals(getValue, other.getValue) && Objects.equals(listValues, other.listValues) && Objects.equals(onClick, other.onClick);
        }

        @Override
        public int hashCode() {
            return Objects.hash(titleResName, style, setValue, getValue, listValues, onClick);
        }

    }
}
