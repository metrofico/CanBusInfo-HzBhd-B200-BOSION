package com.hzbhd.canbus.activity;

import static com.hzbhd.midware.constant.HotKeyConstant.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
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
import java.util.Collections;
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


public final class SwcActivity extends Activity {
    private static final String TAG = "KeyLearnActivity";
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap<>();
    private Button btnReset;
    private Button btnSave;
    private final List<CustomKeyConfig.KeyMap> keyConfigList;
    private final List<CustomKeyConfig.KeyMap> keyLearnList;
    private int learningPosition;
    private RecyclerView rvKeys;
    private SwcLongClickDialog swcLongClickDialog;
    private SwcResetDialog swcResetDialog;
    private TextView tvCurrentKey;

    private static final List<KeyUiEntity> keyUiList = Arrays.asList(new KeyUiEntity(52, R.drawable.swc_key_home, R.string.swc_fun_home), new KeyUiEntity(K_MUTE, R.drawable.swc_key_mute, R.string.swc_fun_mute), new KeyUiEntity(K_VOL_UP, R.drawable.swc_key_vol_up, R.string.swc_fun_voladd), new KeyUiEntity(K_VOL_DN, R.drawable.swc_key_vol_down, R.string.swc_fun_volloss), new KeyUiEntity(MUTE_TYPE_AUDIO_SWITCH_MUTE, R.drawable.swc_key_source, R.string.swc_fun_mode), new KeyUiEntity(K_PLAY_PAUSE, R.drawable.swc_key_playorpause, R.string.swc_fun_play_pause), new KeyUiEntity(K_PREV, R.drawable.swc_key_prev, R.string.swc_fun_prev), new KeyUiEntity(K_NEXT, R.drawable.swc_key_next, R.string.swc_fun_next), new KeyUiEntity(K_RETURN, R.drawable.swc_key_back, R.string.swc_fun_back), new KeyUiEntity(K_EQ, R.drawable.swc_key_eq, R.string.swc_fun_eq), new KeyUiEntity(K_NAVI, R.drawable.swc_key_navi, R.string.swc_fun_navi), new KeyUiEntity(K_PHONE_ON, R.drawable.swc_key_answer, R.string.swc_fun_answer), new KeyUiEntity(K_PHONE_OFF, R.drawable.swc_key_handup, R.string.swc_fun_hangup), new KeyUiEntity(K_TUNER, R.drawable.swc_key_radio, R.string.swc_fun_radio), new KeyUiEntity(K_SLEEP, R.drawable.swc_key_power, R.string.swc_fun_power), new KeyUiEntity(K_BT_MUSIC, R.drawable.swc_key_bt_music, R.string.swc_fun_bt_music), new KeyUiEntity(K_UP, R.drawable.swc_key_up, R.string.swc_fun_up), new KeyUiEntity(K_DOWN, R.drawable.swc_key_down, R.string.swc_fun_down), new KeyUiEntity(K_LEFT, R.drawable.swc_key_left, R.string.swc_fun_left), new KeyUiEntity(K_RIGHT, R.drawable.swc_key_right, R.string.swc_fun_right), new KeyUiEntity(K_ENTER, R.drawable.swc_key_ok, R.string.swc_fun_enter), new KeyUiEntity(K_MUSIC, R.drawable.swc_key_music, R.string.swc_fun_music), new KeyUiEntity(K_VIDEO, R.drawable.swc_key_video, R.string.swc_fun_video), new KeyUiEntity(K_DISPLAY, R.drawable.swc_key_light, R.string.swc_fun_backlight), new KeyUiEntity(K_SPEECH, R.drawable.swc_key_speech, R.string.swc_fun_speech), new KeyUiEntity(K_CARPLAY_SIRI, R.drawable.swc_key_siri, R.string.swc_fun_siri));


    public static List<KeyUiEntity> getKeyUiList() {
        return keyUiList;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(i, viewFindViewById);
        return viewFindViewById;
    }

    public SwcActivity() {
        List<CustomKeyConfig.KeyMap> keyList = CustomKeyConfig.INSTANCE.getKeyList();
        this.keyConfigList = (keyList == null) ? Collections.emptyList() : keyList; // Usa el método adecuado para obtener una lista vacía

        int size = keyUiList.size();
        ArrayList<CustomKeyConfig.KeyMap> arrayList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            int key = keyUiList.get(i).getKey();
            CustomKeyConfig.KeyMap keyMap = findMatchingKeyMap(key);

            // Si no encontramos un KeyMap, lo creamos
            if (keyMap == null) {
                keyMap = new CustomKeyConfig.KeyMap(0, new CustomKeyConfig.Key(key, 0));
            }

            arrayList.add(keyMap);
        }

        this.keyLearnList = arrayList; // Asigna el resultado a keyLearnList
    }

    private CustomKeyConfig.KeyMap findMatchingKeyMap(int key) {
        for (CustomKeyConfig.KeyMap keyMap : this.keyConfigList) {
            if (keyMap.getOutput().getShort() == key) {
                return keyMap;
            }
        }
        return null;
    }

    private CustomKeyConfig.KeyMap findKeyMapByShortKey(int key) {
        for (CustomKeyConfig.KeyMap keyMap : keyLearnList) {
            if (keyMap.getOutput().getShort() == key) {
                return keyMap;
            }
        }
        return null;
    }


    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swc);
        StringBuilder sbAppend = new StringBuilder().append("onCreate: ");
        Object[] array = this.keyLearnList.toArray(new CustomKeyConfig.KeyMap[0]);

        String string = Arrays.toString(array);

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

    private void initView() {
        SwcActivity swcActivity = this;
        this.swcLongClickDialog = new SwcLongClickDialog(this, (i, i2) -> {
            CustomKeyConfig.KeyMap keyMap = findKeyMapByShortKey(i);
            if (keyMap != null) {
                keyMap.getOutput().setLong(keyMap.getOutput().getLong() != keyUiList.get(i2).getKey() ? keyUiList.get(i2).getKey() : 0);
                if (keyMap.getInput() != 0) {
                    save();
                }
                if (keyLearnList.indexOf(keyMap) == learningPosition) {
                    updateCurrentKey();
                }
            }
            // Notify RecyclerView
            if (rvKeys != null) {
                RecyclerView.Adapter adapter = rvKeys.getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });

        this.swcResetDialog = new SwcResetDialog(swcActivity, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwcActivity.this.reset();
            }
        });

        View viewFindViewById = findViewById(R.id.rv_keys);
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        final int i = recyclerView.getContext().getResources().getConfiguration().orientation == 1 ? 4 : 7;
        recyclerView.setLayoutManager(new GridLayoutManager(this, i) {

            @Override
            // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new SwcKeyAdapter(swcActivity, this.keyLearnList, new SwcKeyAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                SwcActivity.this.learningPosition = position;
                SwcActivity.this.updateCurrentKey();
            }
        }, new SwcKeyAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                CustomKeyConfig.KeyMap keyMap = SwcActivity.this.keyLearnList.get(position);

                // Obtener el valor short de la clave
                int i3 = keyMap.getOutput().getShort();

                // Si el valor short no es 7 ni 8, mostramos el dialogo con el longKey
                if (i3 != 7 && i3 != 8) {
                    SwcLongClickDialog swcLongClickDialog = SwcActivity.this.swcLongClickDialog;
                    if (swcLongClickDialog != null) {
                        swcLongClickDialog.show(i3, keyMap.getOutput().getLong());
                    }
                } else {
                    // Si el valor short es 7 o 8, mostramos un Toast con un mensaje
                    ToastUtls.getToast(SwcActivity.this, R.string.swc_not_support_custom_long_click, 0).show();
                }
            }
        }));

        this.rvKeys = recyclerView;
        View viewFindViewById2 = findViewById(R.id.tv_current_key);
        this.tvCurrentKey = (TextView) viewFindViewById2;
        updateCurrentKey();
        View viewFindViewById3 = findViewById(R.id.btn_save);
        Button button = (Button) viewFindViewById3;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.SwcActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                finish();
            }
        });
        this.btnSave = button;
        View viewFindViewById4 = findViewById(R.id.btn_reset);
        Button button2 = (Button) viewFindViewById4;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.SwcActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (swcResetDialog != null) {
                    swcResetDialog.show();
                }

            }
        });
        this.btnReset = button2;
    }


    public void learnKey(int key) {
        RecyclerView recyclerView;
        CustomKeyConfig.KeyMap next;
        Unit unit;
        Iterator<CustomKeyConfig.KeyMap> it = this.keyLearnList.iterator();
        while (true) {
            recyclerView = null;
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.getInput() == key) {
                    break;
                }
            }
        }
        CustomKeyConfig.KeyMap keyMap = next;
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

    public void save() {
        CustomKeyConfig customKeyConfig = CustomKeyConfig.INSTANCE;
        ArrayList<CustomKeyConfig.KeyMap> arrayList = new ArrayList<>();
        for (CustomKeyConfig.KeyMap obj : this.keyLearnList) {
            if (obj.getInput() != 0) {
                arrayList.add(obj);
            }
        }
        customKeyConfig.setKeyList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        for (CustomKeyConfig.KeyMap keyMap : this.keyLearnList) {
            keyMap.setInput(0);
            keyMap.getOutput().setLong(0);
        }
        RecyclerView recyclerView = this.rvKeys;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {

            recyclerView = null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        ((SwcKeyAdapter) adapter).setSelected(0);
        this.learningPosition = 0;
        updateCurrentKey();
        RecyclerView recyclerView3 = this.rvKeys;
        if (recyclerView3 == null) {

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
    public void updateCurrentKey() {
        CustomKeyConfig.Key output = this.keyLearnList.get(this.learningPosition).getOutput();
        TextView textView = this.tvCurrentKey;

        if (textView == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        // Buscar KeyUiEntity para short
        KeyUiEntity keyUiEntity = null;
        for (KeyUiEntity entity : keyUiList) {
            if (entity.getKey() == output.getShort()) {
                keyUiEntity = entity;
                break;
            }
        }

        // Añadir el texto para shortKey
        sb.append(getString(keyUiEntity != null ? keyUiEntity.getStringResId() : null));

        // Buscar KeyUiEntity para long
        String string = "";
        if (output.getLong() != 0) {
            KeyUiEntity keyUiEntity2 = null;
            for (KeyUiEntity entity : keyUiList) {
                if (entity.getKey() == output.getLong()) {
                    keyUiEntity2 = entity;
                    break;
                }
            }

            string = " / " + getString(keyUiEntity2 != null ? keyUiEntity2.getStringResId() : null);
        }

        // Establecer el texto en la vista
        textView.setText(sb.append(string).toString());
    }

    private String getString(Integer resId) {
        String string;
        if (resId != null) {
            string = getString(resId.intValue());
        } else {
            string = null;
        }
        return string == null ? "" : string;
    }

    public static final class KeyUiEntity {
        private final int drawableResId;
        private final int key;
        private final int stringResId;

        /* renamed from: component1, reason: from getter */
        public int getKey() {
            return this.key;
        }

        /* renamed from: component2, reason: from getter */
        public int getDrawableResId() {
            return this.drawableResId;
        }

        /* renamed from: component3, reason: from getter */
        public int getStringResId() {
            return this.stringResId;
        }

        public KeyUiEntity copy(int key, int drawableResId, int stringResId) {
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
    }
}
