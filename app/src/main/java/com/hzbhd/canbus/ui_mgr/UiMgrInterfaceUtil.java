package com.hzbhd.canbus.ui_mgr;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.BubbleUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import com.hzbhd.canbus.ui_set.MainPageUiSet;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.ui_set.WarningPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import java.lang.reflect.InvocationTargetException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiMgrInterfaceUtil.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010*\u001a\u0004\u0018\u00010+2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010,\u001a\u00020-2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006."}, d2 = {"Lcom/hzbhd/canbus/ui_mgr/UiMgrInterfaceUtil;", "Lcom/hzbhd/canbus/interfaces/UiMgrInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mUiMgrInterface", "getMUiMgrInterface", "()Lcom/hzbhd/canbus/interfaces/UiMgrInterface;", "mUiMgrInterface$delegate", "Lkotlin/Lazy;", "getAirUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAmplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getBubbleUiSet", "Lcom/hzbhd/canbus/ui_set/BubbleUiSet;", "getCusPanoramicView", "Landroid/view/View;", "getDriverDataPageUiSet", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet;", "getHybirdPageUiSet", "Lcom/hzbhd/canbus/ui_set/HybirdPageUiSet;", "getMainUiSet", "Lcom/hzbhd/canbus/ui_set/MainPageUiSet;", "getOnStartPageUiSet", "Lcom/hzbhd/canbus/ui_set/OnStartPageUiSet;", "getOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "getPageUiSet", "Lcom/hzbhd/canbus/ui_set/PageUiSet;", "getPanelKeyPageUiSet", "Lcom/hzbhd/canbus/ui_set/PanelKeyPageUiSet;", "getParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getSettingUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSyncPageUiSet", "Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;", "getTireUiSet", "Lcom/hzbhd/canbus/ui_set/TirePageUiSet;", "getWarningPageUiSet", "Lcom/hzbhd/canbus/ui_set/WarningPageUiSet;", "updateUiByDifferentCar", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgrInterfaceUtil implements UiMgrInterface {
    private final Context context;

    /* renamed from: mUiMgrInterface$delegate, reason: from kotlin metadata */
    private final Lazy mUiMgrInterface;

    public UiMgrInterfaceUtil(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mUiMgrInterface = LazyKt.lazy(new Function0<UiMgrInterface>() { // from class: com.hzbhd.canbus.ui_mgr.UiMgrInterfaceUtil$mUiMgrInterface$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UiMgrInterface invoke() throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
                try {
                    LogUtil.showLog("getCanUiMgr:" + CanbusConfig.INSTANCE.getCanType());
                    Object objNewInstance = Class.forName("com.hzbhd.canbus.car._" + CanbusConfig.INSTANCE.getCanType() + ".UiMgr").getConstructors()[0].newInstance(this.this$0.getContext());
                    Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type com.hzbhd.canbus.interfaces.UiMgrInterface");
                    return (UiMgrInterface) objNewInstance;
                } catch (Exception e) {
                    LogUtil.showLog("getCanUiMgr:e:" + e);
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    public final Context getContext() {
        return this.context;
    }

    public final UiMgrInterface getMUiMgrInterface() {
        return (UiMgrInterface) this.mUiMgrInterface.getValue();
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                mUiMgrInterface.updateUiByDifferentCar(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getCusPanoramicView(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public PageUiSet getPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public MainPageUiSet getMainUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getMainUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public AirPageUiSet getAirUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getAirUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public SettingPageUiSet getSettingUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getSettingUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public TirePageUiSet getTireUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getTireUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public DriverDataPageUiSet getDriverDataPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getDriverDataPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public OnStartPageUiSet getOnStartPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getOnStartPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public HybirdPageUiSet getHybirdPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getHybirdPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getOriginalCarDevicePageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public ParkPageUiSet getParkPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getParkPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public AmplifierPageUiSet getAmplifierPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getAmplifierPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public SyncPageUiSet getSyncPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getSyncPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public PanelKeyPageUiSet getPanelKeyPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getPanelKeyPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public BubbleUiSet getBubbleUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getBubbleUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.UiMgrInterface
    public WarningPageUiSet getWarningPageUiSet(Context context) {
        try {
            UiMgrInterface mUiMgrInterface = getMUiMgrInterface();
            if (mUiMgrInterface != null) {
                return mUiMgrInterface.getWarningPageUiSet(context);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
