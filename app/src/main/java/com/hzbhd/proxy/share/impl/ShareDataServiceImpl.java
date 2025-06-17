package com.hzbhd.proxy.share.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.share.aidl.IShareBundleCallback;
import com.hzbhd.proxy.share.aidl.IShareDataService;
import com.hzbhd.proxy.share.aidl.IShareIntCallback;
import com.hzbhd.proxy.share.aidl.IShareStringCallback;
import com.hzbhd.util.LogUtil;
import com.hzbhd.util.ServiceManagerReflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class ShareDataServiceImpl extends IShareDataService.Stub {
    private static final int MSG_BUNDLE_DATA = 3;
    private static final int MSG_INT_DATA = 1;
    private static final int MSG_STRING_DATA = 2;
    private static ShareDataServiceImpl sShareDataServiceImpl;
    private ArrayList<ShareListener> mShareListener = new ArrayList<>();
    private HashMap<String, String> mStringData = new HashMap<>();
    private HashMap<String, Integer> mIntegerData = new HashMap<>();
    private HashMap<String, Bundle> mBundleData = new HashMap<>();
    private HashMap<String, HashMap<IBinder, IShareIntCallback>> mIShareIntCallbackMaps = new HashMap<>();
    private HashMap<String, HashMap<IBinder, IShareStringCallback>> mIShareStringCallbackMaps = new HashMap<>();
    private HashMap<String, HashMap<IBinder, IShareBundleCallback>> mIShareBundleCallbackMaps = new HashMap<>();
    private HandlerThread mHandlerThread = new HandlerThread("hzbhd.ShareDataHandlerThread") { // from class: com.hzbhd.proxy.share.impl.ShareDataServiceImpl.1
        {
            start();
        }
    };
    private Handler mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.hzbhd.proxy.share.impl.ShareDataServiceImpl.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                int i2 = message.arg1;
                String str = (String) message.obj;
                if (LogUtil.log5()) {
                    LogUtil.d("MSG_INT_DATA " + str + ": " + i2);
                }
                synchronized (ShareDataServiceImpl.this.mIShareIntCallbackMaps) {
                    if (ShareDataServiceImpl.this.mIShareIntCallbackMaps.containsKey(str)) {
                        HashMap map = (HashMap) ShareDataServiceImpl.this.mIShareIntCallbackMaps.get(str);
                        Iterator it = map.keySet().iterator();
                        while (it.hasNext()) {
                            try {
                                ((IShareIntCallback) map.get((IBinder) it.next())).onInt(i2, message.arg2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                return;
            }
            if (i == 2) {
                Bundle bundle = (Bundle) message.obj;
                String string = bundle.getString("key");
                String string2 = bundle.getString("value");
                if (LogUtil.log5()) {
                    LogUtil.d("MSG_STRING_DATA " + string + ": " + string2);
                }
                synchronized (ShareDataServiceImpl.this.mIShareStringCallbackMaps) {
                    if (ShareDataServiceImpl.this.mIShareStringCallbackMaps.containsKey(string)) {
                        HashMap map2 = (HashMap) ShareDataServiceImpl.this.mIShareStringCallbackMaps.get(string);
                        Iterator it2 = map2.keySet().iterator();
                        while (it2.hasNext()) {
                            try {
                                ((IShareStringCallback) map2.get((IBinder) it2.next())).onString(string2, message.arg2);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                return;
            }
            if (i != 3) {
                return;
            }
            Bundle bundle2 = (Bundle) message.obj;
            String string3 = bundle2.getString("key");
            Bundle bundle3 = bundle2.getBundle("value");
            if (LogUtil.log5()) {
                LogUtil.d("MSG_BUNDLE_DATA " + string3 + ": " + bundle3);
            }
            synchronized (ShareDataServiceImpl.this.mIShareBundleCallbackMaps) {
                if (ShareDataServiceImpl.this.mIShareBundleCallbackMaps.containsKey(string3)) {
                    HashMap map3 = (HashMap) ShareDataServiceImpl.this.mIShareBundleCallbackMaps.get(string3);
                    Iterator it3 = map3.keySet().iterator();
                    while (it3.hasNext()) {
                        try {
                            ((IShareBundleCallback) map3.get((IBinder) it3.next())).onBundle(bundle3, message.arg2);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        }
    };

    public interface ShareListener {
        Bundle getBundle(String str);

        int getInt(String str);

        String getString(String str);
    }

    public static class ShareListeners implements ShareListener {
        @Override // com.hzbhd.proxy.share.impl.ShareDataServiceImpl.ShareListener
        public Bundle getBundle(String str) {
            return null;
        }

        @Override // com.hzbhd.proxy.share.impl.ShareDataServiceImpl.ShareListener
        public int getInt(String str) {
            return 0;
        }

        @Override // com.hzbhd.proxy.share.impl.ShareDataServiceImpl.ShareListener
        public String getString(String str) {
            return null;
        }
    }

    public static ShareDataServiceImpl getInstance() {
        synchronized (ShareDataServiceImpl.class) {
            if (sShareDataServiceImpl == null) {
                sShareDataServiceImpl = new ShareDataServiceImpl();
            }
        }
        return sShareDataServiceImpl;
    }

    public static void init(SourceConstantsDef.MODULE_ID module_id) {
        ServiceManagerReflection.addService("share_" + module_id, getInstance());
    }

    public static void setInt(String str, int i) {
        getInstance().reportInt(str, i);
    }

    public static void setString(String str, String str2) {
        getInstance().reportString(str, str2);
    }

    public static void setBundle(String str, Bundle bundle) {
        getInstance().reportBundle(str, bundle);
    }

    public static ShareDataServiceImpl addShareListeners(ShareListener shareListener) {
        if (!getInstance().mShareListener.contains(shareListener)) {
            getInstance().mShareListener.add(shareListener);
        }
        return getInstance();
    }

    private class MyShareIntDeathRecipient implements IBinder.DeathRecipient {
        private IShareIntCallback mDiedCallback;
        private String mKey;

        public MyShareIntDeathRecipient(String str, IShareIntCallback iShareIntCallback) {
            this.mKey = str;
            this.mDiedCallback = iShareIntCallback;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ShareDataServiceImpl.this.unregisterShareIntCallback(this.mKey, this.mDiedCallback);
        }
    }

    private class MyShareBundleDeathRecipient implements IBinder.DeathRecipient {
        private IShareBundleCallback mDiedCallback;
        private String mKey;

        public MyShareBundleDeathRecipient(String str, IShareBundleCallback iShareBundleCallback) {
            this.mKey = str;
            this.mDiedCallback = iShareBundleCallback;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ShareDataServiceImpl.this.unregisterShareBundleCallback(this.mKey, this.mDiedCallback);
        }
    }

    private class MyShareStringDeathRecipient implements IBinder.DeathRecipient {
        private IShareStringCallback mDiedCallback;
        private String mKey;

        public MyShareStringDeathRecipient(String str, IShareStringCallback iShareStringCallback) {
            this.mKey = str;
            this.mDiedCallback = iShareStringCallback;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ShareDataServiceImpl.this.unregisterShareStringCallback(this.mKey, this.mDiedCallback);
        }
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public int registerShareIntCallback(String str, IShareIntCallback iShareIntCallback) throws RemoteException {
        if (LogUtil.log5()) {
            LogUtil.d("<registerShareIntCallback> " + str + ": " + iShareIntCallback);
        }
        synchronized (this.mIShareIntCallbackMaps) {
            if (!this.mIShareIntCallbackMaps.containsKey(str)) {
                this.mIShareIntCallbackMaps.put(str, new HashMap<>());
            }
            if (!this.mIShareIntCallbackMaps.get(str).containsKey(iShareIntCallback.asBinder())) {
                this.mIShareIntCallbackMaps.get(str).put(iShareIntCallback.asBinder(), iShareIntCallback);
                iShareIntCallback.asBinder().linkToDeath(new MyShareIntDeathRecipient(str, iShareIntCallback), 0);
            }
        }
        int i = getInt(str);
        if (LogUtil.log5()) {
            LogUtil.d("registerShareIntCallback: " + str + " :: " + i);
        }
        return i;
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void unregisterShareIntCallback(String str, IShareIntCallback iShareIntCallback) {
        if (LogUtil.log5()) {
            LogUtil.d("<unregisterShareIntCallback> " + str + ": " + iShareIntCallback);
        }
        synchronized (this.mIShareIntCallbackMaps) {
            if (this.mIShareIntCallbackMaps.containsKey(str)) {
                if (this.mIShareIntCallbackMaps.get(str).containsKey(iShareIntCallback.asBinder())) {
                    this.mIShareIntCallbackMaps.get(str).remove(iShareIntCallback.asBinder());
                    if (this.mIShareIntCallbackMaps.get(str).isEmpty()) {
                        this.mIShareIntCallbackMaps.remove(str);
                    }
                }
            }
        }
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public int getInt(String str) {
        if (this.mIntegerData.containsKey(str)) {
            int iIntValue = this.mIntegerData.get(str).intValue();
            if (LogUtil.log5()) {
                LogUtil.d("getInt: " + str + " = " + iIntValue);
            }
            return iIntValue;
        }
        if (this.mShareListener.size() == 1) {
            return this.mShareListener.get(0).getInt(str);
        }
        for (int i = 0; i < this.mShareListener.size(); i++) {
            if (this.mShareListener.get(i).getInt(str) != -2) {
                return this.mShareListener.get(i).getInt(str);
            }
        }
        return -1;
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void reportInt(String str, int i) {
        this.mIntegerData.put(str, Integer.valueOf(i));
        notifyShareInt(str, i, 0);
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void reportOtherInt(String str, int i, int i2) {
        this.mIntegerData.put(str, Integer.valueOf(i));
        notifyShareInt(str, i, i2);
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public String registerShareStringCallback(String str, IShareStringCallback iShareStringCallback) throws RemoteException {
        if (LogUtil.log5()) {
            LogUtil.d("<registerShareStringCallback> " + str + ": " + iShareStringCallback);
        }
        synchronized (this.mIShareStringCallbackMaps) {
            if (!this.mIShareStringCallbackMaps.containsKey(str)) {
                this.mIShareStringCallbackMaps.put(str, new HashMap<>());
            }
            if (!this.mIShareStringCallbackMaps.get(str).containsKey(iShareStringCallback.asBinder())) {
                this.mIShareStringCallbackMaps.get(str).put(iShareStringCallback.asBinder(), iShareStringCallback);
                iShareStringCallback.asBinder().linkToDeath(new MyShareStringDeathRecipient(str, iShareStringCallback), 0);
            }
        }
        return getString(str);
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void unregisterShareStringCallback(String str, IShareStringCallback iShareStringCallback) {
        if (LogUtil.log5()) {
            LogUtil.d("<unregisterShareStringCallback> " + str + ": " + iShareStringCallback);
        }
        synchronized (this.mIShareStringCallbackMaps) {
            if (this.mIShareStringCallbackMaps.containsKey(str)) {
                if (this.mIShareStringCallbackMaps.get(str).containsKey(iShareStringCallback.asBinder())) {
                    this.mIShareStringCallbackMaps.get(str).remove(iShareStringCallback.asBinder());
                    if (this.mIShareStringCallbackMaps.get(str).isEmpty()) {
                        this.mIShareStringCallbackMaps.remove(str);
                    }
                }
            }
        }
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public String getString(String str) {
        if (this.mStringData.containsKey(str)) {
            String str2 = this.mStringData.get(str);
            if (LogUtil.log5()) {
                LogUtil.d("getString: " + str + " = " + str2);
            }
            return str2;
        }
        if (this.mShareListener.size() == 1) {
            return this.mShareListener.get(0).getString(str);
        }
        for (int i = 0; i < this.mShareListener.size(); i++) {
            if (this.mShareListener.get(i).getString(str) != null) {
                return this.mShareListener.get(i).getString(str);
            }
        }
        return null;
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void reportString(String str, String str2) {
        this.mStringData.put(str, str2);
        notifyShareString(str, str2, 0);
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void reportOtherString(String str, String str2, int i) {
        this.mStringData.put(str, str2);
        notifyShareString(str, str2, i);
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public Bundle registerShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) throws RemoteException {
        if (LogUtil.log5()) {
            LogUtil.d("<registerShareBundleCallback> " + str + ": " + iShareBundleCallback);
        }
        synchronized (this.mIShareBundleCallbackMaps) {
            if (!this.mIShareBundleCallbackMaps.containsKey(str)) {
                this.mIShareBundleCallbackMaps.put(str, new HashMap<>());
            }
            if (!this.mIShareBundleCallbackMaps.get(str).containsKey(iShareBundleCallback.asBinder())) {
                this.mIShareBundleCallbackMaps.get(str).put(iShareBundleCallback.asBinder(), iShareBundleCallback);
                iShareBundleCallback.asBinder().linkToDeath(new MyShareBundleDeathRecipient(str, iShareBundleCallback), 0);
            }
        }
        Bundle bundle = getBundle(str);
        if (LogUtil.log5()) {
            LogUtil.d("registerShareBundleCallback: " + str + " :: " + bundle);
        }
        return bundle;
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void unregisterShareBundleCallback(String str, IShareBundleCallback iShareBundleCallback) {
        if (LogUtil.log5()) {
            LogUtil.d("<unregisterShareBundleCallback> " + str + ": " + iShareBundleCallback);
        }
        synchronized (this.mIShareBundleCallbackMaps) {
            if (this.mIShareBundleCallbackMaps.containsKey(str)) {
                if (this.mIShareBundleCallbackMaps.get(str).containsKey(iShareBundleCallback.asBinder())) {
                    this.mIShareBundleCallbackMaps.get(str).remove(iShareBundleCallback.asBinder());
                    if (this.mIShareBundleCallbackMaps.get(str).isEmpty()) {
                        this.mIShareBundleCallbackMaps.remove(str);
                    }
                }
            }
        }
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public Bundle getBundle(String str) {
        if (this.mBundleData.containsKey(str)) {
            Bundle bundle = this.mBundleData.get(str);
            if (LogUtil.log5()) {
                LogUtil.d("getBundle: " + str + " = " + bundle);
            }
            return bundle;
        }
        if (this.mShareListener.size() == 1) {
            return this.mShareListener.get(0).getBundle(str);
        }
        for (int i = 0; i < this.mShareListener.size(); i++) {
            if (this.mShareListener.get(i).getBundle(str) != null) {
                return this.mShareListener.get(i).getBundle(str);
            }
        }
        return null;
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void reportBundle(String str, Bundle bundle) {
        this.mBundleData.put(str, bundle);
        notifyShareBundle(str, bundle, 0);
    }

    @Override // com.hzbhd.proxy.share.aidl.IShareDataService
    public void reportOtherBundle(String str, Bundle bundle, int i) {
        this.mBundleData.put(str, bundle);
        reportOtherBundle(str, bundle, i);
    }

    public void notifyShareInt(String str, int i, int i2) {
        Message message = new Message();
        message.what = 1;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = str;
        this.mHandler.sendMessage(message);
    }

    public void notifyShareString(String str, String str2, int i) {
        Message message = new Message();
        message.what = MSG_STRING_DATA;
        message.arg2 = i;
        Bundle bundle = new Bundle();
        bundle.putString("key", str);
        bundle.putString("value", str2);
        message.obj = bundle;
        this.mHandler.sendMessage(message);
    }

    public void notifyShareBundle(String str, Bundle bundle, int i) {
        Message message = new Message();
        message.what = 3;
        message.arg2 = i;
        Bundle bundle2 = new Bundle();
        bundle2.putString("key", str);
        bundle2.putBundle("value", bundle);
        message.obj = bundle2;
        this.mHandler.sendMessage(message);
    }
}
