package com.hzbhd.ui.base;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import com.hzbhd.ui.base.BhdViewUtil$threadHandler$2;
import com.hzbhd.ui.base.BhdViewUtil$uiHandler$2;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: BhdViewUtil.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001AB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020\u0006J\u0006\u0010-\u001a\u00020*J\u0006\u0010.\u001a\u00020*J\b\u0010/\u001a\u00020\u0006H\u0002J\u000e\u00100\u001a\u00020*2\u0006\u00101\u001a\u000202J\u0010\u00103\u001a\u00020*2\u0006\u00101\u001a\u000202H\u0002J\u0010\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020\u0006H\u0002J\u0018\u00106\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020\u0006J\u0014\u00107\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*09J\u001c\u0010:\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*092\u0006\u0010;\u001a\u00020<J\u0014\u0010=\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*09J\u001c\u0010>\u001a\u00020*2\f\u00108\u001a\b\u0012\u0004\u0012\u00020*092\u0006\u0010;\u001a\u00020<J\u0016\u0010?\u001a\u00020*2\u0006\u00101\u001a\u0002022\u0006\u0010@\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R-\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0016`\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u001ej\b\u0012\u0004\u0012\u00020\u0016`\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u001b\u0010&\u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010%\u001a\u0004\b'\u0010#¨\u0006B"}, d2 = {"Lcom/hzbhd/ui/base/BhdViewUtil;", "", "()V", "COLOR_VIEW_COLOR_STYLE", "", "colorStyle", "", "getColorStyle", "()I", "setColorStyle", "(I)V", "currFocusId", "getCurrFocusId", "setCurrFocusId", "focusIds", "Ljava/util/TreeSet;", "getFocusIds", "()Ljava/util/TreeSet;", "setFocusIds", "(Ljava/util/TreeSet;)V", "focusMap", "Ljava/util/HashMap;", "Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "Lkotlin/collections/HashMap;", "getFocusMap", "()Ljava/util/HashMap;", "handlerWhatIndex", "getHandlerWhatIndex", "setHandlerWhatIndex", "listeners", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "threadHandler", "Landroid/os/Handler;", "getThreadHandler", "()Landroid/os/Handler;", "threadHandler$delegate", "Lkotlin/Lazy;", "uiHandler", "getUiHandler", "uiHandler$delegate", "addListener", "", "listener", "focusId", "focusAdd", "focusCut", "getHandlerWhat", "init", "context", "Landroid/content/Context;", "initColor", "notifyFocusChange", "index", "removeListener", "runBack", "action", "Lkotlin/Function0;", "runBackDelay", "time", "", "runUi", "runUiDelay", "updateColorStyle", "style", "ViewUtilListener", "bhdview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BhdViewUtil {
    private static int currFocusId;
    private static int handlerWhatIndex;
    public static final BhdViewUtil INSTANCE = new BhdViewUtil();
    private static final ArrayList<ViewUtilListener> listeners = new ArrayList<>();
    private static TreeSet<Integer> focusIds = new TreeSet<>();
    private static final HashMap<Integer, ViewUtilListener> focusMap = new HashMap<>();
    private static final String COLOR_VIEW_COLOR_STYLE = "color_view_style";
    private static int colorStyle = 1;

    /* renamed from: uiHandler$delegate, reason: from kotlin metadata */
    private static final Lazy uiHandler = LazyKt.lazy(new Function0<BhdViewUtil$uiHandler$2.AnonymousClass1>() { // from class: com.hzbhd.ui.base.BhdViewUtil$uiHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r1v0, types: [com.hzbhd.ui.base.BhdViewUtil$uiHandler$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            return new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.ui.base.BhdViewUtil$uiHandler$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    try {
                        Object obj = msg.obj;
                        if (obj == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>");
                        }
                        ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 0)).invoke();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    });

    /* renamed from: threadHandler$delegate, reason: from kotlin metadata */
    private static final Lazy threadHandler = LazyKt.lazy(new Function0<BhdViewUtil$threadHandler$2.AnonymousClass1>() { // from class: com.hzbhd.ui.base.BhdViewUtil$threadHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.hzbhd.ui.base.BhdViewUtil$threadHandler$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            HandlerThread handlerThread = new HandlerThread("back");
            handlerThread.start();
            return new Handler(handlerThread.getLooper()) { // from class: com.hzbhd.ui.base.BhdViewUtil$threadHandler$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    try {
                        Object obj = msg.obj;
                        if (obj == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>");
                        }
                        ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 0)).invoke();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    });

    /* compiled from: BhdViewUtil.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/hzbhd/ui/base/BhdViewUtil$ViewUtilListener;", "", "onColorChange", "", "onFocusChange", "isFocus", "", "bhdview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ViewUtilListener {
        void onColorChange();

        void onFocusChange(boolean isFocus);
    }

    private BhdViewUtil() {
    }

    public static /* synthetic */ void addListener$default(BhdViewUtil bhdViewUtil, ViewUtilListener viewUtilListener, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        bhdViewUtil.addListener(viewUtilListener, i);
    }

    public final void addListener(ViewUtilListener listener, int focusId) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ArrayList<ViewUtilListener> arrayList = listeners;
        if (!arrayList.contains(listener)) {
            arrayList.add(listener);
            listener.onColorChange();
        }
        if (focusId > 0) {
            if (!focusIds.contains(Integer.valueOf(focusId))) {
                focusIds.add(Integer.valueOf(focusId));
            }
            focusMap.put(Integer.valueOf(focusId), listener);
        }
    }

    public static /* synthetic */ void removeListener$default(BhdViewUtil bhdViewUtil, ViewUtilListener viewUtilListener, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        bhdViewUtil.removeListener(viewUtilListener, i);
    }

    public final void removeListener(ViewUtilListener listener, int focusId) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ArrayList<ViewUtilListener> arrayList = listeners;
        if (arrayList.contains(listener)) {
            arrayList.remove(listener);
        }
        if (focusId > 0) {
            if (focusIds.contains(Integer.valueOf(focusId))) {
                focusIds.remove(Integer.valueOf(focusId));
            }
            focusMap.remove(Integer.valueOf(focusId));
        }
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        initColor(context);
    }

    public final TreeSet<Integer> getFocusIds() {
        return focusIds;
    }

    public final void setFocusIds(TreeSet<Integer> treeSet) {
        Intrinsics.checkNotNullParameter(treeSet, "<set-?>");
        focusIds = treeSet;
    }

    public final HashMap<Integer, ViewUtilListener> getFocusMap() {
        return focusMap;
    }

    public final int getCurrFocusId() {
        return currFocusId;
    }

    public final void setCurrFocusId(int i) {
        currFocusId = i;
    }

    public final void focusAdd() {
        int iIndexOf = CollectionsKt.indexOf(focusIds, Integer.valueOf(currFocusId)) + 1;
        if (iIndexOf >= focusIds.size()) {
            iIndexOf = 0;
        }
        notifyFocusChange(iIndexOf);
    }

    public final void focusCut() {
        int iIndexOf = CollectionsKt.indexOf(focusIds, Integer.valueOf(currFocusId)) - 1;
        if (iIndexOf < 0) {
            iIndexOf = 0;
        }
        notifyFocusChange(iIndexOf);
    }

    private final void notifyFocusChange(int index) {
        if (!focusIds.isEmpty()) {
            Iterator<ViewUtilListener> it = listeners.iterator();
            while (it.hasNext()) {
                ViewUtilListener next = it.next();
                next.onFocusChange(Intrinsics.areEqual(next, focusMap.get(focusIds.toArray()[index])));
            }
        }
    }

    public final int getColorStyle() {
        return colorStyle;
    }

    public final void setColorStyle(int i) {
        colorStyle = i;
    }

    public final void updateColorStyle(Context context, int style) {
        Intrinsics.checkNotNullParameter(context, "context");
        Settings.System.putInt(context.getContentResolver(), COLOR_VIEW_COLOR_STYLE, style);
    }

    private final void initColor(final Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String str = COLOR_VIEW_COLOR_STYLE;
        colorStyle = Settings.System.getInt(contentResolver, str, colorStyle);
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(str), false, new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.hzbhd.ui.base.BhdViewUtil.initColor.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                int i = Settings.System.getInt(context.getContentResolver(), BhdViewUtil.COLOR_VIEW_COLOR_STYLE, 0);
                if (LogUtil.log5()) {
                    LogUtil.d("onChange: newColor = " + i + " , colorStyle=" + BhdViewUtil.INSTANCE.getColorStyle());
                }
                if (BhdViewUtil.INSTANCE.getColorStyle() != i) {
                    BhdViewUtil.INSTANCE.setColorStyle(i);
                    if (LogUtil.log5()) {
                        LogUtil.d(Intrinsics.stringPlus("onChange(): listeners.size = ", Integer.valueOf(BhdViewUtil.listeners.size())));
                    }
                    int size = BhdViewUtil.listeners.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((ViewUtilListener) BhdViewUtil.listeners.get(i2)).onColorChange();
                    }
                }
            }
        });
    }

    public final void runUi(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        getUiHandler().sendMessage(getUiHandler().obtainMessage(getHandlerWhat(), action));
    }

    public final void runUiDelay(Function0<Unit> action, long time) {
        Intrinsics.checkNotNullParameter(action, "action");
        getUiHandler().sendMessageDelayed(getUiHandler().obtainMessage(getHandlerWhat(), action), time);
    }

    public final void runBack(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        getThreadHandler().sendMessage(getThreadHandler().obtainMessage(getHandlerWhat(), action));
    }

    public final void runBackDelay(Function0<Unit> action, long time) {
        Intrinsics.checkNotNullParameter(action, "action");
        getThreadHandler().sendMessageDelayed(getThreadHandler().obtainMessage(getHandlerWhat(), action), time);
    }

    public final int getHandlerWhatIndex() {
        return handlerWhatIndex;
    }

    public final void setHandlerWhatIndex(int i) {
        handlerWhatIndex = i;
    }

    private final int getHandlerWhat() {
        int i = handlerWhatIndex + 1;
        handlerWhatIndex = i;
        return i;
    }

    private final Handler getUiHandler() {
        return (Handler) uiHandler.getValue();
    }

    private final Handler getThreadHandler() {
        return (Handler) threadHandler.getValue();
    }
}
