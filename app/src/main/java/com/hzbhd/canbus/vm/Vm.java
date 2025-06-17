package com.hzbhd.canbus.vm;

import android.util.Log;

import androidx.annotation.NonNull;
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

    // Instancia singleton
    private static Vm instance;

    // Singleton seguro para hilos
    public static Vm getVm() {
        if (instance == null) {
            // Crear el ViewModelStoreOwner
            ViewModelStoreOwner vmOwner = createViewModelStoreOwner();

            // Obtener la clase dinámica del ViewModel
            Class<? extends Vm> vmClass = getVmClass();
            Vm createdVm = new ViewModelProvider(vmOwner, new ViewModelProvider.Factory() {
                @NonNull
                @Override
                public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                    try {
                        return modelClass.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException("Cannot create ViewModel instance", e);
                    }
                }
            }).get(vmClass);
            instance = createdVm;
        }
        return instance;
    }

    // Componentes del ViewModel
    private final CanBusData data;
    private final CanBusAction action;
    private final ReverseListener reverseListener;
    private ReverseMainView reverseMainView;


    public Vm() {
        this.data = new CanBusData(this);
        this.action = new CanBusAction(this);
        this.reverseListener = new ReverseListener();

        // La creación de ReverseMainView se pospone hasta que se necesite
    }

    // Métodos de acceso a los componentes
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
        if (reverseMainView == null) {
            initializeReverseMainView();
        }
        return reverseMainView;
    }

    // Inicialización segura de ReverseMainView
    private void initializeReverseMainView() {
        if (BaseUtil.INSTANCE.getContext() != null) {
            reverseMainView = new ReverseMainView(BaseUtil.INSTANCE.getContext());
        } else {
            throw new IllegalStateException("Context is not available for ReverseMainView");
        }
    }


    // Crea un ViewModelStoreOwner para la instancia estática
    private static ViewModelStoreOwner createViewModelStoreOwner() {
        return new ViewModelStoreOwner() {
            private final ViewModelStore viewModelStore = new ViewModelStore();

            @NonNull
            @Override
            public ViewModelStore getViewModelStore() {
                return viewModelStore;
            }
        };
    }

    // Obtiene dinámicamente la clase del ViewModel
    private static Class<? extends Vm> getVmClass() {
        Log.d("Vm.java", "getVmClass() " + UI.INSTANCE.getUIId());
        String className = "com.hzbhd.canbus.vm" + UI.INSTANCE.getUIId() + ".VM";
        try {
            Class<?> clazz = Class.forName(className);
            if (Vm.class.isAssignableFrom(clazz)) {
                return (Class<? extends Vm>) clazz;
            }
        } catch (Exception e) {
            Log.e("Vm.java", "Error al cargar la clase dinámica: " + className, e);
        }
        return Vm.class;
    }
}
