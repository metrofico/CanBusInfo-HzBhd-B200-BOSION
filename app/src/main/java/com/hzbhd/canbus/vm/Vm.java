package com.hzbhd.canbus.vm;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.hzbhd.canbus.vm.action.CanBusAction;
import com.hzbhd.canbus.vm.data.CanBusData;
import com.hzbhd.canbus.vm.listener.ReverseListener;
import com.hzbhd.canbus.vm.view.main.ReverseMainView;
import com.hzbhd.config.use.UI;
import com.hzbhd.ui.binding.BaseViewModel;
import com.hzbhd.ui.util.BaseUtil;

public class Vm extends BaseViewModel {

    private static Vm vmInstance;

    private final CanBusData data = new CanBusData(this);
    private final CanBusAction action = new CanBusAction(this);
    private final ReverseListener reverseListener = new ReverseListener();
    private final ReverseMainView reverseMainView = new ReverseMainView(BaseUtil.INSTANCE.getContext());

    // Static block to initialize the ViewModel instance
    static {
        vmInstance = createVmInstance();
    }

    // Getter methods for data, action, reverseListener, and reverseMainView
    public CanBusData getData() {
        return data;
    }

    public CanBusAction getAction() {
        return action;
    }

    public ReverseListener getReverseListener() {
        return reverseListener;
    }

    public ReverseMainView getReverseMainView() {
        return reverseMainView;
    }

    // Static method to retrieve or create the ViewModel instance
    public static Vm getVm() {
        return vmInstance;
    }

    // Static method to create the Vm instance using ViewModelProvider
    private static Vm createVmInstance() {
        ViewModelStoreOwner vmOwner = new ViewModelStoreOwner() {
            private final ViewModelStore mViewModelStore = new ViewModelStore();

            @Override
            public ViewModelStore getViewModelStore() {
                return mViewModelStore;
            }
        };

        // Use ViewModelProvider to get an instance of the Vm class
        ViewModelProvider provider = new ViewModelProvider(vmOwner);
        Class<? extends ViewModel> vmClass = getVmClass();
        return (Vm) provider.get(vmClass);
    }

    // Method to dynamically retrieve the Vm class based on the UI ID
    private static Class<? extends BaseViewModel> getVmClass() {
        try {
            String className = "com.hzbhd.canbus.vm." + UI.INSTANCE.getUIId() + ".VM";
            return (Class<? extends BaseViewModel>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Vm.class; // Fallback to Vm class if dynamic class loading fails
        }
    }
}
