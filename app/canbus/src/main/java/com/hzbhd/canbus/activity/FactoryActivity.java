package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.FactoryItemAdapter;
import com.hzbhd.canbus.car_cus._439.smartPanel.window.CarSelectWindow;
import com.hzbhd.canbus.car_cus._439.smartPanel.window.PanelSwitchWindow;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.GeneralSettingsConfig;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.util.TouchpadEvents;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FactoryActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0011H\u0002R\u001b\u0010\u0003\u001a\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/hzbhd/canbus/activity/FactoryActivity;", "Landroid/app/Activity;", "()V", "instance", "getInstance", "()Lcom/hzbhd/canbus/activity/FactoryActivity;", "instance$delegate", "Lkotlin/Lazy;", "mProxy", "Lcom/hzbhd/canbus/factory/proxy/CanSettingProxy;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "toInt", "", "", "Companion", "ItemUiSet", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class FactoryActivity extends Activity {
    public static final int STYLE_0 = 0;
    public static final int STYLE_1 = 1;
    public static final int STYLE_4 = 4;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* renamed from: instance$delegate, reason: from kotlin metadata */
    private final Lazy instance;
    private final CanSettingProxy mProxy;

    /* JADX INFO: Access modifiers changed from: private */
    public final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public FactoryActivity() {
        Object obj = Dependency.get((Class<Object>) CanSettingProxy.class);
        Intrinsics.checkNotNullExpressionValue(obj, "get(CanSettingProxy::class.java)");
        this.mProxy = (CanSettingProxy) obj;
        this.instance = LazyKt.lazy(new Function0<FactoryActivity>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$instance$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FactoryActivity invoke() {
                return this.this$0;
            }
        });
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        initView();
    }

    private final void initView() {
        FactoryActivity factoryActivity = this;
        ((RecyclerView) _$_findCachedViewById(R.id.rv_setting_items)).setLayoutManager(new LinearLayoutManager(factoryActivity));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rv_setting_items);
        ArrayList arrayList = new ArrayList();
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).RADAR_SHOW_TAG) {
            arrayList.add(new ItemUiSet("radar_display", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setRadarDispCheck(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getRadarDispCheck()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).BACK_TRAJECTORY_REVERSAL) {
            arrayList.add(new ItemUiSet("back_trajectiry_reversal", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setBackTrajectiryDispCheck(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$4
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getBackTrajectiryDispCheck()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).DOOR_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_door_info", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$5
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setShowDoorInfo(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$6
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getShowDoorInfo()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG) {
            arrayList.add(new ItemUiSet("swap_front_door", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$7
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setDoorSwapFront(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$8
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getDoorSwapFront()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG) {
            arrayList.add(new ItemUiSet("swap_rear_door", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$9
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setDoorSwapRear(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$10
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getDoorSwapRear()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH) {
            arrayList.add(new ItemUiSet("_factoryActivity_add_function1", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$11
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setDoorCountDownTimerState(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$12
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getDoorCountDownTimerState()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).THE_HOOD_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_the_hood", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$13
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setShowHood(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$14
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getShowHood()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TRUNK_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_the_trunk", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$15
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setShowTrunk(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$16
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getShowTrunk()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG) {
            arrayList.add(new ItemUiSet(BodaSysContant.Can.SwitchAcTemperature, 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$17
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setSwitchAcTemperature(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$18
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getSwitchAcTemperature()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).AIR_SHOW_TAG) {
            arrayList.add(new ItemUiSet("air_display_setup", 1, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$19
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setAirDisplaySetup(i);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$20
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(this.this$0.mProxy.getAirDisplaySetup());
                }
            }, CollectionsKt.mutableListOf("air_popup_status_0", "air_popup_status_1", "air_popup_status_2"), null, 32, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).OUT_TEMPERATURE_SHOW_TAG) {
            arrayList.add(new ItemUiSet("show_outdoor_temperature", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$21
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setShowOutdoorTemperature(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$22
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getShowOutdoorTemperature()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TEMPERATURE_UNIT_TAG) {
            arrayList.add(new ItemUiSet("temperature_unit", 1, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$23
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setTemperatureUnit(i);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$24
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(this.this$0.mProxy.getTemperatureUnit());
                }
            }, CollectionsKt.mutableListOf("str_temp_c_unit", "str_temp_f_unit"), null, 32, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SWC_STUDY_TAG) {
            arrayList.add(new ItemUiSet("swc_app_name", 0, null, null, null, new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$25
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BaseUtil baseUtil = BaseUtil.INSTANCE;
                    ComponentName SwcActivity = Constant.SwcActivity;
                    Intrinsics.checkNotNullExpressionValue(SwcActivity, "SwcActivity");
                    baseUtil.startActivity(SwcActivity);
                }
            }, 28, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).FRONT_CAMERA_TAG) {
            arrayList.add(new ItemUiSet("Factory_activity_open_camera", 0, null, null, null, new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$26
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BaseUtil baseUtil = BaseUtil.INSTANCE;
                    ComponentName FrontCameraSettingActivity = Constant.FrontCameraSettingActivity;
                    Intrinsics.checkNotNullExpressionValue(FrontCameraSettingActivity, "FrontCameraSettingActivity");
                    baseUtil.startActivity(FrontCameraSettingActivity);
                }
            }, 28, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).TOUCH_PAD_SETTING_TAG) {
            arrayList.add(new ItemUiSet("car_pannale_control", 0, null, null, null, new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$27
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    new TouchpadEvents().showAdjustView(this.this$0.getInstance());
                }
            }, 28, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).CAN_BUS_TEST_TAG) {
            arrayList.add(new ItemUiSet("_open_bugly_item", 0, null, null, null, new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$28
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BaseUtil baseUtil = BaseUtil.INSTANCE;
                    ComponentName CanBusDiagnosisActivity = Constant.CanBusDiagnosisActivity;
                    Intrinsics.checkNotNullExpressionValue(CanBusDiagnosisActivity, "CanBusDiagnosisActivity");
                    baseUtil.startActivity(CanBusDiagnosisActivity);
                }
            }, 28, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SHOW_HIDE_SMART_PANEL_PAGE) {
            arrayList.add(new ItemUiSet("_439_panel_app_show_hide_switch", 0, null, null, null, new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$29
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    new PanelSwitchWindow(BaseUtil.INSTANCE.getContext()).show();
                }
            }, 28, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).SHOW_P_KEY_RADAR_WINDOW) {
            arrayList.add(new ItemUiSet("p_key_radar_switch", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$30
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setPKeyRadarDispCheck(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$31
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getPKeyRadarDispCheck()));
                }
            }, null, null, 48, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).WIKA_CAR_SELECT) {
            arrayList.add(new ItemUiSet("_439_car_select", 0, null, null, null, new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$32
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CarSelectWindow.getInstance().show();
                }
            }, 28, null));
        }
        if (GeneralSettingsConfig.getInstance(CanbusConfig.INSTANCE.getCanType()).OPEN_CAN_DATA_LOG) {
            arrayList.add(new ItemUiSet("open_can_data_log_switch", 4, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$33
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.mProxy.setCanDataLogSwith(i == 1);
                }
            }, new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity$initView$1$34
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    FactoryActivity factoryActivity2 = this.this$0;
                    return Integer.valueOf(factoryActivity2.toInt(factoryActivity2.mProxy.getCanDataLogSwith()));
                }
            }, null, null, 48, null));
        }
        Unit unit = Unit.INSTANCE;
        recyclerView.setAdapter(new FactoryItemAdapter(factoryActivity, arrayList));
    }

    public final FactoryActivity getInstance() {
        return (FactoryActivity) this.instance.getValue();
    }

    /* compiled from: FactoryActivity.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\nHÆ\u0003Je\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\nHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lcom/hzbhd/canbus/activity/FactoryActivity$ItemUiSet;", "", "titleResName", "", "style", "", "setValue", "Lkotlin/Function1;", "", "getValue", "Lkotlin/Function0;", "listValues", "", "onClick", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "getGetValue", "()Lkotlin/jvm/functions/Function0;", "getListValues", "()Ljava/util/List;", "getOnClick", "getSetValue", "()Lkotlin/jvm/functions/Function1;", "getStyle", "()I", "getTitleResName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class ItemUiSet {
        private final Function0<Integer> getValue;
        private final List<String> listValues;
        private final Function0<Unit> onClick;
        private final Function1<Integer, Unit> setValue;
        private final int style;
        private final String titleResName;

        public static /* synthetic */ ItemUiSet copy$default(ItemUiSet itemUiSet, String str, int i, Function1 function1, Function0 function0, List list, Function0 function02, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = itemUiSet.titleResName;
            }
            if ((i2 & 2) != 0) {
                i = itemUiSet.style;
            }
            int i3 = i;
            if ((i2 & 4) != 0) {
                function1 = itemUiSet.setValue;
            }
            Function1 function12 = function1;
            if ((i2 & 8) != 0) {
                function0 = itemUiSet.getValue;
            }
            Function0 function03 = function0;
            if ((i2 & 16) != 0) {
                list = itemUiSet.listValues;
            }
            List list2 = list;
            if ((i2 & 32) != 0) {
                function02 = itemUiSet.onClick;
            }
            return itemUiSet.copy(str, i3, function12, function03, list2, function02);
        }

        /* renamed from: component1, reason: from getter */
        public final String getTitleResName() {
            return this.titleResName;
        }

        /* renamed from: component2, reason: from getter */
        public final int getStyle() {
            return this.style;
        }

        public final Function1<Integer, Unit> component3() {
            return this.setValue;
        }

        public final Function0<Integer> component4() {
            return this.getValue;
        }

        public final List<String> component5() {
            return this.listValues;
        }

        public final Function0<Unit> component6() {
            return this.onClick;
        }

        public final ItemUiSet copy(String titleResName, int style, Function1<? super Integer, Unit> setValue, Function0<Integer> getValue, List<String> listValues, Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(titleResName, "titleResName");
            Intrinsics.checkNotNullParameter(setValue, "setValue");
            Intrinsics.checkNotNullParameter(getValue, "getValue");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            return new ItemUiSet(titleResName, style, setValue, getValue, listValues, onClick);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ItemUiSet)) {
                return false;
            }
            ItemUiSet itemUiSet = (ItemUiSet) other;
            return Intrinsics.areEqual(this.titleResName, itemUiSet.titleResName) && this.style == itemUiSet.style && Intrinsics.areEqual(this.setValue, itemUiSet.setValue) && Intrinsics.areEqual(this.getValue, itemUiSet.getValue) && Intrinsics.areEqual(this.listValues, itemUiSet.listValues) && Intrinsics.areEqual(this.onClick, itemUiSet.onClick);
        }

        public int hashCode() {
            int iHashCode = ((((((this.titleResName.hashCode() * 31) + Integer.hashCode(this.style)) * 31) + this.setValue.hashCode()) * 31) + this.getValue.hashCode()) * 31;
            List<String> list = this.listValues;
            return ((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.onClick.hashCode();
        }

        public String toString() {
            return "ItemUiSet(titleResName=" + this.titleResName + ", style=" + this.style + ", setValue=" + this.setValue + ", getValue=" + this.getValue + ", listValues=" + this.listValues + ", onClick=" + this.onClick + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ItemUiSet(String titleResName, int i, Function1<? super Integer, Unit> setValue, Function0<Integer> getValue, List<String> list, Function0<Unit> onClick) {
            Intrinsics.checkNotNullParameter(titleResName, "titleResName");
            Intrinsics.checkNotNullParameter(setValue, "setValue");
            Intrinsics.checkNotNullParameter(getValue, "getValue");
            Intrinsics.checkNotNullParameter(onClick, "onClick");
            this.titleResName = titleResName;
            this.style = i;
            this.setValue = setValue;
            this.getValue = getValue;
            this.listValues = list;
            this.onClick = onClick;
        }

        public final String getTitleResName() {
            return this.titleResName;
        }

        public final int getStyle() {
            return this.style;
        }

        public /* synthetic */ ItemUiSet(String str, int i, AnonymousClass1 anonymousClass1, AnonymousClass2 anonymousClass2, List list, AnonymousClass3 anonymousClass3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, (i2 & 4) != 0 ? new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity.ItemUiSet.1
                public final void invoke(int i3) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }
            } : anonymousClass1, (i2 & 8) != 0 ? new Function0<Integer>() { // from class: com.hzbhd.canbus.activity.FactoryActivity.ItemUiSet.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return 0;
                }
            } : anonymousClass2, (i2 & 16) != 0 ? null : list, (i2 & 32) != 0 ? new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.FactoryActivity.ItemUiSet.3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : anonymousClass3);
        }

        public final Function1<Integer, Unit> getSetValue() {
            return this.setValue;
        }

        public final Function0<Integer> getGetValue() {
            return this.getValue;
        }

        public final List<String> getListValues() {
            return this.listValues;
        }

        public final Function0<Unit> getOnClick() {
            return this.onClick;
        }
    }
}
