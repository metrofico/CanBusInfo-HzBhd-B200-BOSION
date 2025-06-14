package com.hzbhd.canbus.util;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InitUtils.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u001a\u0016\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b\u001a\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0014\u001a>\u0010!\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u00142$\b\u0002\u0010\"\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t`\n\u001aF\u0010#\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020\u00142,\b\u0002\u0010\"\u001a&\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u0007j\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010`\n\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"6\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\">\u0010\u000f\u001a&\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u0007j\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000e\"\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006$"}, d2 = {"mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mDrivingItemIndex", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "getMDrivingItemIndex", "()Ljava/util/HashMap;", "setMDrivingItemIndex", "(Ljava/util/HashMap;)V", "mSettingItemIndex", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "getMSettingItemIndex", "setMSettingItemIndex", "mUiMgr", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;)V", "getSettingViewItemName", "left", "", "right", "init", "", "context", "uiMgr", "initDrivingItemsIndexHashMap", "hashMap", "initSettingItemsIndexHashMap", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class InitUtilsKt {
    public static Context mContext;
    private static HashMap<String, DriverDataPageUiSet.Page.Item> mDrivingItemIndex = new HashMap<>();
    private static HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> mSettingItemIndex = new HashMap<>();
    public static AbstractUiMgr mUiMgr;

    public static final HashMap<String, DriverDataPageUiSet.Page.Item> getMDrivingItemIndex() {
        return mDrivingItemIndex;
    }

    public static final void setMDrivingItemIndex(HashMap<String, DriverDataPageUiSet.Page.Item> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        mDrivingItemIndex = map;
    }

    public static final HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> getMSettingItemIndex() {
        return mSettingItemIndex;
    }

    public static final void setMSettingItemIndex(HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        mSettingItemIndex = map;
    }

    public static final AbstractUiMgr getMUiMgr() {
        AbstractUiMgr abstractUiMgr = mUiMgr;
        if (abstractUiMgr != null) {
            return abstractUiMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
        return null;
    }

    public static final void setMUiMgr(AbstractUiMgr abstractUiMgr) {
        Intrinsics.checkNotNullParameter(abstractUiMgr, "<set-?>");
        mUiMgr = abstractUiMgr;
    }

    public static final Context getMContext() {
        Context context = mContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    public static final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        mContext = context;
    }

    public static final String getSettingViewItemName(int i, int i2) {
        String titleSrn = getMUiMgr().getSettingUiSet(getMContext()).getList().get(i).getItemList().get(i2).getTitleSrn();
        Intrinsics.checkNotNullExpressionValue(titleSrn, "mUiMgr.getSettingUiSet(m….itemList[right].titleSrn");
        return titleSrn;
    }

    public static final void init(Context context, AbstractUiMgr uiMgr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        setMUiMgr(uiMgr);
        setMContext(context);
    }

    public static /* synthetic */ void initSettingItemsIndexHashMap$default(Context context, AbstractUiMgr abstractUiMgr, HashMap map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = mSettingItemIndex;
        }
        initSettingItemsIndexHashMap(context, abstractUiMgr, map);
    }

    public static final void initSettingItemsIndexHashMap(Context context, AbstractUiMgr uiMgr, HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> hashMap) {
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        Intrinsics.checkNotNullParameter(hashMap, "hashMap");
        Intrinsics.checkNotNull(context);
        init(context, uiMgr);
        List<SettingPageUiSet.ListBean> list = getMUiMgr().getSettingUiSet(context).getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = it.next().getItemList().iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                int i4 = i3 + 1;
                String titleSrn = it2.next().getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn, "itemListBean.titleSrn");
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = list.get(i).getItemList().get(i3);
                Intrinsics.checkNotNullExpressionValue(itemListBean, "this[left].itemList[right]");
                hashMap.put(titleSrn, itemListBean);
                i3 = i4;
            }
            i = i2;
        }
    }

    public static /* synthetic */ void initDrivingItemsIndexHashMap$default(Context context, AbstractUiMgr abstractUiMgr, HashMap map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = mDrivingItemIndex;
        }
        initDrivingItemsIndexHashMap(context, abstractUiMgr, map);
    }

    public static final void initDrivingItemsIndexHashMap(Context context, AbstractUiMgr uiMgr, HashMap<String, DriverDataPageUiSet.Page.Item> hashMap) {
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        Intrinsics.checkNotNullParameter(hashMap, "hashMap");
        Intrinsics.checkNotNull(context);
        init(context, uiMgr);
        List<DriverDataPageUiSet.Page> list = getMUiMgr().getDriverDataPageUiSet(context).getList();
        Iterator<DriverDataPageUiSet.Page> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            Iterator<DriverDataPageUiSet.Page.Item> it2 = it.next().getItemList().iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                int i4 = i3 + 1;
                String titleSrn = it2.next().getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn, "item.titleSrn");
                DriverDataPageUiSet.Page.Item item = list.get(i).getItemList().get(i3);
                Intrinsics.checkNotNullExpressionValue(item, "this[pageIndex].itemList[itemIndex]");
                hashMap.put(titleSrn, item);
                i3 = i4;
            }
            i = i2;
        }
    }
}
