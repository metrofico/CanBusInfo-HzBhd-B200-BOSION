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


public final class UiMgrInterfaceUtil implements UiMgrInterface {
    private Context context;
    private UiMgrInterface mUiMgrInterface;

    public UiMgrInterfaceUtil(Context context) {
        this.context = context;
        this.mUiMgrInterface = getMUiMgrInterface();
    }

    public Context getContext() {
        return this.context;
    }

    public UiMgrInterface getMUiMgrInterface() {
        if (this.mUiMgrInterface == null) {
            LogUtil.showLog("getCanUiMgr:" + CanbusConfig.INSTANCE.getCanType());
            try {
                Object objNewInstance = Class.forName("com.hzbhd.canbus.car._" + CanbusConfig.INSTANCE.getCanType() + ".UiMgr").getConstructors()[0].newInstance(getContext());
                mUiMgrInterface = (UiMgrInterface) objNewInstance;
                return mUiMgrInterface;
            } catch (Throwable e) {
                LogUtil.showLog("getCanUiMgr:e:" + e);
                e.printStackTrace();
            }
        }
        return this.mUiMgrInterface;
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
