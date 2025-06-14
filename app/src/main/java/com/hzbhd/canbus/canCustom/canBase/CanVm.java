package com.hzbhd.canbus.canCustom.canBase;

import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.ui.binding.BaseViewModel;


public class CanVm extends BaseViewModel {

    private static CanVm instance;
    private final CanData data = new CanData(this);
    private final CanAction action = new CanAction(this);
    private final CanDocking canDocking = new CanDocking();

    public CanData getData() {
        return data;
    }

    public CanAction getAction() {
        return action;
    }

    public CanDocking getCanDocking() {
        return canDocking;
    }

    public static CanVm getInstance() {
        if (instance == null) {
            instance = new CanVm();
        }
        return instance;
    }

    private CanVm() {
    }

    public static class Companion {
        private final ViewModelStoreOwner vmOwner;
        private final Class<? extends BaseViewModel> vmClass;

        public Companion() {
            vmOwner = new ViewModelStoreOwner() {
                private final ViewModelStore mViewModelStore = new ViewModelStore();

                @Override
                public ViewModelStore getViewModelStore() {
                    return mViewModelStore;
                }
            };
            vmClass = getVmClass();
        }

        private Class<? extends BaseViewModel> getVmClass() {
            try {
                return (Class<? extends BaseViewModel>) Class.forName("com.hzbhd.canbus._" + CanbusConfig.INSTANCE.getCanType() + ".Vm" + CanbusConfig.INSTANCE.getCanType());
            } catch (Exception e) {
                e.printStackTrace();
                return CanVm.class;
            }
        }

        public CanVm getVm() {
            return getInstance();
        }
    }
}