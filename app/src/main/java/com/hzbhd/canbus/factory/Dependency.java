package com.hzbhd.canbus.factory;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;

import com.hzbhd.canbus.factory.proxy.CanSettingProxy;

import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class Dependency {
    private static final String TAG = "Dependency";
    private static Dependency sDependency;
    private Context mContext;
    private final ArrayMap<Object, Object> mDependencies = new ArrayMap<>();
    private final ArrayMap<Object, DependencyProvider> mProviders = new ArrayMap<>();

    public interface DependencyProvider<T> {
        T createDependency();
    }

    public Dependency(Context context) {
        this.mContext = context;
    }


    public void start() {
        // from class: com.hzbhd.canbus.factory.Dependency$$ExternalSyntheticLambda0
// com.hzbhd.canbus.factory.Dependency.DependencyProvider
        this.mProviders.put(CanSettingProxy.class, () -> new CanSettingProxy(mContext));
        sDependency = this;
    }

    public static boolean isStart() {
        return sDependency != null;
    }

    protected final <T> T getDependency(Class<T> cls) {
        return (T) getDependencyInner(cls);
    }

    protected final <T> T getDependency(DependencyKey<T> dependencyKey) {
        return (T) getDependencyInner(dependencyKey);
    }

    private synchronized <T> T getDependencyInner(Object obj) {
        T t;
        t = (T) this.mDependencies.get(obj);
        if (t == null) {
            t = (T) createDependency(obj);
            this.mDependencies.put(obj, t);
        }
        return t;
    }

    protected <T> T createDependency(Object obj) {
        checkArgument((obj instanceof DependencyKey) || (obj instanceof Class));
        DependencyProvider dependencyProvider = this.mProviders.get(obj);
        if (dependencyProvider == null) {
            throw new IllegalArgumentException("Unsupported dependency " + obj + ". " + this.mProviders.size() + " providers known.");
        }
        return (T) dependencyProvider.createDependency();
    }

    private void checkArgument(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("obj debe ser una instancia de DependencyKey o Class");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void destroyDependency(Class<T> cls, Consumer<T> consumer) {
        Object remove = this.mDependencies.remove(cls);
        if (remove == null || consumer == null) {
            return;
        }
        consumer.accept((T) remove);
    }

    public static void clearDependencies() {
        sDependency = null;
    }

    public static <T> void destroy(Class<T> cls, Consumer<T> consumer) {
        sDependency.destroyDependency(cls, consumer);
    }

    public static <T> T get(Class<T> cls) {
        Log.d(TAG, "get: sDependency:" + (sDependency != null));
        return (T) sDependency.getDependency(cls);
    }

    public static <T> T getNew(Class<T> cls) {
        return (T) sDependency.createDependency(cls);
    }

    public static <T> T get(DependencyKey<T> dependencyKey) {
        return (T) sDependency.getDependency(dependencyKey);
    }

    public static final class DependencyKey<V> {
        private final String mDisplayName;

        public DependencyKey(String str) {
            this.mDisplayName = str;
        }

        public String toString() {
            return this.mDisplayName;
        }
    }
}
