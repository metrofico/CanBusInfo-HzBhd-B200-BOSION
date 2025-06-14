package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.SwcKeyAdapter;
import com.hzbhd.canbus.config.CustomKeyConfig;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.view.SwcLongClickDialog;
import com.hzbhd.canbus.view.SwcResetDialog;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.ToastUtls;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwcActivity.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 $2\u00020\u0001:\u0002$%B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u000bJ\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0019H\u0014J\b\u0010 \u001a\u00020\u0019H\u0014J\b\u0010!\u001a\u00020\u0019H\u0002J\b\u0010\"\u001a\u00020\u0019H\u0002J\b\u0010#\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/hzbhd/canbus/activity/SwcActivity;", "Landroid/app/Activity;", "()V", "btnReset", "Landroid/widget/Button;", "btnSave", "keyConfigList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "keyLearnList", "learningPosition", "", "rvKeys", "Landroidx/recyclerview/widget/RecyclerView;", "swcLongClickDialog", "Lcom/hzbhd/canbus/view/SwcLongClickDialog;", "swcResetDialog", "Lcom/hzbhd/canbus/view/SwcResetDialog;", "tvCurrentKey", "Landroid/widget/TextView;", "getString", "", "resId", "(Ljava/lang/Integer;)Ljava/lang/String;", "initView", "", "learnKey", "key", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "reset", "save", "updateCurrentKey", "Companion", "KeyUiEntity", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SwcActivity extends Activity {
    private static final String TAG = "KeyLearnActivity";
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private Button btnReset;
    private Button btnSave;
    private final List<CustomKeyConfig.KeyMap> keyConfigList;
    private List<CustomKeyConfig.KeyMap> keyLearnList;
    private int learningPosition;
    private RecyclerView rvKeys;
    private SwcLongClickDialog swcLongClickDialog;
    private SwcResetDialog swcResetDialog;
    private TextView tvCurrentKey;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<KeyUiEntity> keyUiList = CollectionsKt.listOf((Object[]) new KeyUiEntity[]{new KeyUiEntity(52, R.drawable.swc_key_home, R.string.swc_fun_home), new KeyUiEntity(3, R.drawable.swc_key_mute, R.string.swc_fun_mute), new KeyUiEntity(7, R.drawable.swc_key_vol_up, R.string.swc_fun_voladd), new KeyUiEntity(8, R.drawable.swc_key_vol_down, R.string.swc_fun_volloss), new KeyUiEntity(2, R.drawable.swc_key_source, R.string.swc_fun_mode), new KeyUiEntity(95, R.drawable.swc_key_playorpause, R.string.swc_fun_play_pause), new KeyUiEntity(21, R.drawable.swc_key_prev, R.string.swc_fun_prev), new KeyUiEntity(20, R.drawable.swc_key_next, R.string.swc_fun_next), new KeyUiEntity(50, R.drawable.swc_key_back, R.string.swc_fun_back), new KeyUiEntity(4, R.drawable.swc_key_eq, R.string.swc_fun_eq), new KeyUiEntity(128, R.drawable.swc_key_navi, R.string.swc_fun_navi), new KeyUiEntity(14, R.drawable.swc_key_answer, R.string.swc_fun_answer), new KeyUiEntity(15, R.drawable.swc_key_handup, R.string.swc_fun_hangup), new KeyUiEntity(129, R.drawable.swc_key_radio, R.string.swc_fun_radio), new KeyUiEntity(HotKeyConstant.K_SLEEP, R.drawable.swc_key_power, R.string.swc_fun_power), new KeyUiEntity(140, R.drawable.swc_key_bt_music, R.string.swc_fun_bt_music), new KeyUiEntity(45, R.drawable.swc_key_up, R.string.swc_fun_up), new KeyUiEntity(46, R.drawable.swc_key_down, R.string.swc_fun_down), new KeyUiEntity(47, R.drawable.swc_key_left, R.string.swc_fun_left), new KeyUiEntity(48, R.drawable.swc_key_right, R.string.swc_fun_right), new KeyUiEntity(49, R.drawable.swc_key_ok, R.string.swc_fun_enter), new KeyUiEntity(59, R.drawable.swc_key_music, R.string.swc_fun_music), new KeyUiEntity(61, R.drawable.swc_key_video, R.string.swc_fun_video), new KeyUiEntity(57, R.drawable.swc_key_light, R.string.swc_fun_backlight), new KeyUiEntity(HotKeyConstant.K_SPEECH, R.drawable.swc_key_speech, R.string.swc_fun_speech), new KeyUiEntity(HotKeyConstant.K_CARPLAY_SIRI, R.drawable.swc_key_siri, R.string.swc_fun_siri)});

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

    public SwcActivity() {
        Object next;
        List<CustomKeyConfig.KeyMap> keyList = CustomKeyConfig.INSTANCE.getKeyList();
        this.keyConfigList = keyList == null ? CollectionsKt.emptyList() : keyList;
        int size = keyUiList.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            int key = keyUiList.get(i).getKey();
            Iterator<T> it = this.keyConfigList.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (((CustomKeyConfig.KeyMap) next).getOutput().getShort() == key) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            CustomKeyConfig.KeyMap keyMap = (CustomKeyConfig.KeyMap) next;
            if (keyMap == null) {
                keyMap = new CustomKeyConfig.KeyMap(0, new CustomKeyConfig.Key(key, 0, 2, null), 1, null);
            }
            arrayList.add(keyMap);
        }
        this.keyLearnList = arrayList;
    }

    /* compiled from: SwcActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/hzbhd/canbus/activity/SwcActivity$Companion;", "", "()V", "TAG", "", "keyUiList", "", "Lcom/hzbhd/canbus/activity/SwcActivity$KeyUiEntity;", "getKeyUiList", "()Ljava/util/List;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KeyUiEntity> getKeyUiList() {
            return SwcActivity.keyUiList;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swc);
        StringBuilder sbAppend = new StringBuilder().append("onCreate: ");
        Object[] array = this.keyLearnList.toArray(new CustomKeyConfig.KeyMap[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String string = Arrays.toString(array);
        Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
        Log.i(TAG, sbAppend.append(string).toString());
        initView();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        RealKeyUtil.setSwcActivity(this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        RealKeyUtil.setSwcActivity(null);
    }

    private final void initView() {
        SwcActivity swcActivity = this;
        this.swcLongClickDialog = new SwcLongClickDialog(swcActivity, new Function2<Integer, Integer, Unit>() { // from class: com.hzbhd.canbus.activity.SwcActivity.initView.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                invoke(num.intValue(), num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, int i2) {
                RecyclerView recyclerView;
                Object next;
                Iterator it = SwcActivity.this.keyLearnList.iterator();
                while (true) {
                    recyclerView = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (((CustomKeyConfig.KeyMap) next).getOutput().getShort() == i) {
                            break;
                        }
                    }
                }
                CustomKeyConfig.KeyMap keyMap = (CustomKeyConfig.KeyMap) next;
                if (keyMap != null) {
                    SwcActivity swcActivity2 = SwcActivity.this;
                    keyMap.getOutput().setLong(keyMap.getOutput().getLong() != SwcActivity.INSTANCE.getKeyUiList().get(i2).getKey() ? SwcActivity.INSTANCE.getKeyUiList().get(i2).getKey() : 0);
                    if (keyMap.getInput() != 0) {
                        swcActivity2.save();
                    }
                    if (swcActivity2.keyLearnList.indexOf(keyMap) == swcActivity2.learningPosition) {
                        swcActivity2.updateCurrentKey();
                    }
                }
                RecyclerView recyclerView2 = SwcActivity.this.rvKeys;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
                } else {
                    recyclerView = recyclerView2;
                }
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
        this.swcResetDialog = new SwcResetDialog(swcActivity, new Function0<Unit>() { // from class: com.hzbhd.canbus.activity.SwcActivity.initView.2
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
                SwcActivity.this.reset();
            }
        });
        View viewFindViewById = findViewById(R.id.rv_keys);
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        final int i = recyclerView.getContext().getResources().getConfiguration().orientation == 1 ? 4 : 7;
        recyclerView.setLayoutManager(new GridLayoutManager(this, i) { // from class: com.hzbhd.canbus.activity.SwcActivity$initView$3$1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }

            {
                SwcActivity swcActivity2 = this;
            }
        });
        recyclerView.setAdapter(new SwcKeyAdapter(swcActivity, this.keyLearnList, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.SwcActivity$initView$3$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2) {
                this.this$0.learningPosition = i2;
                this.this$0.updateCurrentKey();
            }
        }, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.activity.SwcActivity$initView$3$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2) {
                int i3 = ((CustomKeyConfig.KeyMap) this.this$0.keyLearnList.get(i2)).getOutput().getShort();
                if (i3 != 7 && i3 != 8) {
                    SwcLongClickDialog swcLongClickDialog = this.this$0.swcLongClickDialog;
                    if (swcLongClickDialog == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("swcLongClickDialog");
                        swcLongClickDialog = null;
                    }
                    swcLongClickDialog.show(i3, ((CustomKeyConfig.KeyMap) this.this$0.keyLearnList.get(i2)).getOutput().getLong());
                    return;
                }
                ToastUtls.getToast(this.this$0, R.string.swc_not_support_custom_long_click, 0).show();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<RecyclerVie…             })\n        }");
        this.rvKeys = recyclerView;
        View viewFindViewById2 = findViewById(R.id.tv_current_key);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.tv_current_key)");
        this.tvCurrentKey = (TextView) viewFindViewById2;
        updateCurrentKey();
        View viewFindViewById3 = findViewById(R.id.btn_save);
        Button button = (Button) viewFindViewById3;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.SwcActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwcActivity.m32initView$lambda4$lambda3(this.f$0, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById<Button>(R.i…ckListener { finish() } }");
        this.btnSave = button;
        View viewFindViewById4 = findViewById(R.id.btn_reset);
        Button button2 = (Button) viewFindViewById4;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.SwcActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwcActivity.m33initView$lambda6$lambda5(this.f$0, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById<Button>(R.i…swcResetDialog.show() } }");
        this.btnReset = button2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-4$lambda-3, reason: not valid java name */
    public static final void m32initView$lambda4$lambda3(SwcActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-6$lambda-5, reason: not valid java name */
    public static final void m33initView$lambda6$lambda5(SwcActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SwcResetDialog swcResetDialog = this$0.swcResetDialog;
        if (swcResetDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swcResetDialog");
            swcResetDialog = null;
        }
        swcResetDialog.show();
    }

    public final void learnKey(int key) {
        RecyclerView recyclerView;
        Object next;
        Unit unit;
        Iterator<T> it = this.keyLearnList.iterator();
        while (true) {
            recyclerView = null;
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((CustomKeyConfig.KeyMap) next).getInput() == key) {
                    break;
                }
            }
        }
        CustomKeyConfig.KeyMap keyMap = (CustomKeyConfig.KeyMap) next;
        if (keyMap != null) {
            this.keyLearnList.indexOf(keyMap);
            keyMap.setInput(0);
            if (!keyMap.equals(this.keyLearnList.get(this.learningPosition))) {
                this.keyLearnList.get(this.learningPosition).setInput(key);
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            this.keyLearnList.get(this.learningPosition).setInput(key);
        }
        RecyclerView recyclerView2 = this.rvKeys;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
        } else {
            recyclerView = recyclerView2;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        SendKeyManager.getInstance().playBeep(0);
        save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void save() {
        CustomKeyConfig customKeyConfig = CustomKeyConfig.INSTANCE;
        List<CustomKeyConfig.KeyMap> list = this.keyLearnList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((CustomKeyConfig.KeyMap) obj).getInput() != 0) {
                arrayList.add(obj);
            }
        }
        customKeyConfig.setKeyList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reset() {
        for (CustomKeyConfig.KeyMap keyMap : this.keyLearnList) {
            keyMap.setInput(0);
            keyMap.getOutput().setLong(0);
        }
        RecyclerView recyclerView = this.rvKeys;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
            recyclerView = null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type com.hzbhd.canbus.adapter.SwcKeyAdapter");
        ((SwcKeyAdapter) adapter).setSelected(0);
        this.learningPosition = 0;
        updateCurrentKey();
        RecyclerView recyclerView3 = this.rvKeys;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvKeys");
        } else {
            recyclerView2 = recyclerView3;
        }
        RecyclerView.Adapter adapter2 = recyclerView2.getAdapter();
        if (adapter2 != null) {
            adapter2.notifyDataSetChanged();
        }
        save();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCurrentKey() {
        Object next;
        Object next2;
        String string;
        CustomKeyConfig.Key output = this.keyLearnList.get(this.learningPosition).getOutput();
        TextView textView = this.tvCurrentKey;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvCurrentKey");
            textView = null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = keyUiList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((KeyUiEntity) next).getKey() == output.getShort()) {
                    break;
                }
            }
        }
        KeyUiEntity keyUiEntity = (KeyUiEntity) next;
        StringBuilder sbAppend = sb.append(getString(keyUiEntity != null ? Integer.valueOf(keyUiEntity.getStringResId()) : null));
        if (output.getLong() == 0) {
            string = "";
        } else {
            StringBuilder sbAppend2 = new StringBuilder().append(" / ");
            Iterator<T> it2 = keyUiList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    next2 = null;
                    break;
                } else {
                    next2 = it2.next();
                    if (((KeyUiEntity) next2).getKey() == output.getLong()) {
                        break;
                    }
                }
            }
            KeyUiEntity keyUiEntity2 = (KeyUiEntity) next2;
            string = sbAppend2.append(getString(keyUiEntity2 != null ? Integer.valueOf(keyUiEntity2.getStringResId()) : null)).toString();
        }
        textView.setText(sbAppend.append(string).toString());
    }

    private final String getString(Integer resId) {
        String string;
        if (resId != null) {
            resId.intValue();
            string = getString(resId.intValue());
        } else {
            string = null;
        }
        return string == null ? "" : string;
    }

    /* compiled from: SwcActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/hzbhd/canbus/activity/SwcActivity$KeyUiEntity;", "", "key", "", "drawableResId", "stringResId", "(III)V", "getDrawableResId", "()I", "getKey", "getStringResId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class KeyUiEntity {
        private final int drawableResId;
        private final int key;
        private final int stringResId;

        public static /* synthetic */ KeyUiEntity copy$default(KeyUiEntity keyUiEntity, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = keyUiEntity.key;
            }
            if ((i4 & 2) != 0) {
                i2 = keyUiEntity.drawableResId;
            }
            if ((i4 & 4) != 0) {
                i3 = keyUiEntity.stringResId;
            }
            return keyUiEntity.copy(i, i2, i3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getKey() {
            return this.key;
        }

        /* renamed from: component2, reason: from getter */
        public final int getDrawableResId() {
            return this.drawableResId;
        }

        /* renamed from: component3, reason: from getter */
        public final int getStringResId() {
            return this.stringResId;
        }

        public final KeyUiEntity copy(int key, int drawableResId, int stringResId) {
            return new KeyUiEntity(key, drawableResId, stringResId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KeyUiEntity)) {
                return false;
            }
            KeyUiEntity keyUiEntity = (KeyUiEntity) other;
            return this.key == keyUiEntity.key && this.drawableResId == keyUiEntity.drawableResId && this.stringResId == keyUiEntity.stringResId;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.key) * 31) + Integer.hashCode(this.drawableResId)) * 31) + Integer.hashCode(this.stringResId);
        }

        public String toString() {
            return "KeyUiEntity(key=" + this.key + ", drawableResId=" + this.drawableResId + ", stringResId=" + this.stringResId + ')';
        }

        public KeyUiEntity(int i, int i2, int i3) {
            this.key = i;
            this.drawableResId = i2;
            this.stringResId = i3;
        }

        public final int getDrawableResId() {
            return this.drawableResId;
        }

        public final int getKey() {
            return this.key;
        }

        public final int getStringResId() {
            return this.stringResId;
        }
    }
}
