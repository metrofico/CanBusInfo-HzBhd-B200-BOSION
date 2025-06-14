package com.hzbhd.canbus.config;

import com.hzbhd.canbus.config.CustomKeyConfig;
import com.hzbhd.commontools.utils.bhdGsonUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomKeyConfig.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u0012\u0013\u0014\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig;", "", "()V", "CONFIG_FILE_PATH", "", "TAG", "bean", "Lcom/hzbhd/canbus/config/CustomKeyConfig$CustomKey;", "getKeyList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "getLongKey", "", "key", "getShortKey", "setKeyList", "", "list", "CanIdKeyMap", "CustomKey", "Key", "KeyMap", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomKeyConfig {
    private static final String CONFIG_FILE_PATH = "/bhd/appconfig/CanBus/CustomKey.json";
    public static final CustomKeyConfig INSTANCE = new CustomKeyConfig();
    private static final String TAG = "CustomKeyConfig";
    private static final CustomKey bean;

    private CustomKeyConfig() {
    }

    static {
        CustomKey customKey = (CustomKey) bhdGsonUtils.fromFilePath(CONFIG_FILE_PATH, CustomKey.class);
        if (customKey == null) {
            customKey = new CustomKey(null, 1, 0 == true ? 1 : 0);
        }
        bean = customKey;
    }

    public final List<KeyMap> getKeyList() {
        Object next;
        Iterator<T> it = bean.getCanIdList().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((CanIdKeyMap) next).getCanId() == CanbusConfig.INSTANCE.getCanType()) {
                break;
            }
        }
        CanIdKeyMap canIdKeyMap = (CanIdKeyMap) next;
        if (canIdKeyMap != null) {
            return canIdKeyMap.getKeyList();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setKeyList$lambda-1, reason: not valid java name */
    public static final boolean m1145setKeyList$lambda1(CanIdKeyMap it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getCanId() == CanbusConfig.INSTANCE.getCanType();
    }

    public final void setKeyList(List<KeyMap> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        CustomKey customKey = bean;
        customKey.getCanIdList().removeIf(new Predicate() { // from class: com.hzbhd.canbus.config.CustomKeyConfig$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CustomKeyConfig.m1145setKeyList$lambda1((CustomKeyConfig.CanIdKeyMap) obj);
            }
        });
        if (!list.isEmpty()) {
            customKey.getCanIdList().add(new CanIdKeyMap(CanbusConfig.INSTANCE.getCanType(), list));
        }
        bhdGsonUtils.toFileJson(CONFIG_FILE_PATH, customKey);
    }

    public final int getShortKey(int key) {
        Object next;
        Key output;
        List<KeyMap> keyList = getKeyList();
        if (keyList == null) {
            return key;
        }
        Iterator<T> it = keyList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((KeyMap) next).getInput() == key) {
                break;
            }
        }
        KeyMap keyMap = (KeyMap) next;
        return (keyMap == null || (output = keyMap.getOutput()) == null) ? key : output.getShort();
    }

    public final int getLongKey(int key) {
        Object next;
        Key output;
        List<KeyMap> keyList = getKeyList();
        if (keyList == null) {
            return 0;
        }
        Iterator<T> it = keyList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((KeyMap) next).getInput() == key) {
                break;
            }
        }
        KeyMap keyMap = (KeyMap) next;
        if (keyMap == null || (output = keyMap.getOutput()) == null) {
            return 0;
        }
        return output.getLong();
    }

    /* compiled from: CustomKeyConfig.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$CustomKey;", "", "canIdList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$CanIdKeyMap;", "(Ljava/util/List;)V", "getCanIdList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class CustomKey {
        private final List<CanIdKeyMap> canIdList;

        public CustomKey() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CustomKey copy$default(CustomKey customKey, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = customKey.canIdList;
            }
            return customKey.copy(list);
        }

        public final List<CanIdKeyMap> component1() {
            return this.canIdList;
        }

        public final CustomKey copy(List<CanIdKeyMap> canIdList) {
            Intrinsics.checkNotNullParameter(canIdList, "canIdList");
            return new CustomKey(canIdList);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CustomKey) && Intrinsics.areEqual(this.canIdList, ((CustomKey) other).canIdList);
        }

        public int hashCode() {
            return this.canIdList.hashCode();
        }

        public String toString() {
            return "CustomKey(canIdList=" + this.canIdList + ')';
        }

        public CustomKey(List<CanIdKeyMap> canIdList) {
            Intrinsics.checkNotNullParameter(canIdList, "canIdList");
            this.canIdList = canIdList;
        }

        public /* synthetic */ CustomKey(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new ArrayList() : arrayList);
        }

        public final List<CanIdKeyMap> getCanIdList() {
            return this.canIdList;
        }
    }

    /* compiled from: CustomKeyConfig.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$CanIdKeyMap;", "", "canId", "", "keyList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "(ILjava/util/List;)V", "getCanId", "()I", "getKeyList", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class CanIdKeyMap {
        private final int canId;
        private final List<KeyMap> keyList;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CanIdKeyMap copy$default(CanIdKeyMap canIdKeyMap, int i, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = canIdKeyMap.canId;
            }
            if ((i2 & 2) != 0) {
                list = canIdKeyMap.keyList;
            }
            return canIdKeyMap.copy(i, list);
        }

        /* renamed from: component1, reason: from getter */
        public final int getCanId() {
            return this.canId;
        }

        public final List<KeyMap> component2() {
            return this.keyList;
        }

        public final CanIdKeyMap copy(int canId, List<KeyMap> keyList) {
            Intrinsics.checkNotNullParameter(keyList, "keyList");
            return new CanIdKeyMap(canId, keyList);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CanIdKeyMap)) {
                return false;
            }
            CanIdKeyMap canIdKeyMap = (CanIdKeyMap) other;
            return this.canId == canIdKeyMap.canId && Intrinsics.areEqual(this.keyList, canIdKeyMap.keyList);
        }

        public int hashCode() {
            return (Integer.hashCode(this.canId) * 31) + this.keyList.hashCode();
        }

        public String toString() {
            return "CanIdKeyMap(canId=" + this.canId + ", keyList=" + this.keyList + ')';
        }

        public CanIdKeyMap(int i, List<KeyMap> keyList) {
            Intrinsics.checkNotNullParameter(keyList, "keyList");
            this.canId = i;
            this.keyList = keyList;
        }

        public final int getCanId() {
            return this.canId;
        }

        public final List<KeyMap> getKeyList() {
            return this.keyList;
        }
    }

    /* compiled from: CustomKeyConfig.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "", "input", "", "output", "Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;", "(ILcom/hzbhd/canbus/config/CustomKeyConfig$Key;)V", "getInput", "()I", "setInput", "(I)V", "getOutput", "()Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;", "setOutput", "(Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class KeyMap {
        private int input;
        private Key output;

        public static /* synthetic */ KeyMap copy$default(KeyMap keyMap, int i, Key key, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = keyMap.input;
            }
            if ((i2 & 2) != 0) {
                key = keyMap.output;
            }
            return keyMap.copy(i, key);
        }

        /* renamed from: component1, reason: from getter */
        public final int getInput() {
            return this.input;
        }

        /* renamed from: component2, reason: from getter */
        public final Key getOutput() {
            return this.output;
        }

        public final KeyMap copy(int input, Key output) {
            Intrinsics.checkNotNullParameter(output, "output");
            return new KeyMap(input, output);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KeyMap)) {
                return false;
            }
            KeyMap keyMap = (KeyMap) other;
            return this.input == keyMap.input && Intrinsics.areEqual(this.output, keyMap.output);
        }

        public int hashCode() {
            return (Integer.hashCode(this.input) * 31) + this.output.hashCode();
        }

        public String toString() {
            return "KeyMap(input=" + this.input + ", output=" + this.output + ')';
        }

        public KeyMap(int i, Key output) {
            Intrinsics.checkNotNullParameter(output, "output");
            this.input = i;
            this.output = output;
        }

        public /* synthetic */ KeyMap(int i, Key key, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, key);
        }

        public final int getInput() {
            return this.input;
        }

        public final Key getOutput() {
            return this.output;
        }

        public final void setInput(int i) {
            this.input = i;
        }

        public final void setOutput(Key key) {
            Intrinsics.checkNotNullParameter(key, "<set-?>");
            this.output = key;
        }
    }

    /* compiled from: CustomKeyConfig.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;", "", "short", "", "long", "(II)V", "getLong", "()I", "setLong", "(I)V", "getShort", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class Key {
        private int long;
        private final int short;

        public static /* synthetic */ Key copy$default(Key key, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = key.short;
            }
            if ((i3 & 2) != 0) {
                i2 = key.long;
            }
            return key.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getShort() {
            return this.short;
        }

        /* renamed from: component2, reason: from getter */
        public final int getLong() {
            return this.long;
        }

        public final Key copy(int i, int i2) {
            return new Key(i, i2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }
            Key key = (Key) other;
            return this.short == key.short && this.long == key.long;
        }

        public int hashCode() {
            return (Integer.hashCode(this.short) * 31) + Integer.hashCode(this.long);
        }

        public String toString() {
            return "Key(short=" + this.short + ", long=" + this.long + ')';
        }

        public Key(int i, int i2) {
            this.short = i;
            this.long = i2;
        }

        public /* synthetic */ Key(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i3 & 2) != 0 ? 0 : i2);
        }

        public final int getLong() {
            return this.long;
        }

        public final int getShort() {
            return this.short;
        }

        public final void setLong(int i) {
            this.long = i;
        }
    }
}
