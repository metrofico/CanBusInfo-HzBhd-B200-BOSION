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

public final class CustomKeyConfig {
    private static final String CONFIG_FILE_PATH = "/bhd/appconfig/CanBus/CustomKey.json";
    public static final CustomKeyConfig INSTANCE = new CustomKeyConfig();
    private static final String TAG = "CustomKeyConfig";
    private static final CustomKey bean;

    private CustomKeyConfig() {
    }

    static {
        CustomKey customKey = bhdGsonUtils.fromFilePath(CONFIG_FILE_PATH, CustomKey.class);
        if (customKey == null) {
            customKey = new CustomKey(new ArrayList<>());
        }
        bean = customKey;
    }


    public List<KeyMap> getKeyList() {
        CanIdKeyMap obj;
        Iterator<CanIdKeyMap> it = bean.getCanIdList().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (obj.getCanId() == CanbusConfig.INSTANCE.getCanType()) {
                break;
            }
        }
        CanIdKeyMap canIdKeyMap = obj;
        if (canIdKeyMap != null) {
            return canIdKeyMap.getKeyList();
        }
        return null;
    }

    public void setKeyList(List<KeyMap> list) {
        CustomKey customKey = bean;
        customKey.getCanIdList().removeIf(canIdKeyMap -> canIdKeyMap.getCanId() == CanbusConfig.INSTANCE.getCanType());
        if (!list.isEmpty()) {
            customKey.getCanIdList().add(new CanIdKeyMap(CanbusConfig.INSTANCE.getCanType(), list));
        }
        bhdGsonUtils.toFileJson(CONFIG_FILE_PATH, customKey);
    }

    public int getShortKey(int key) {
        KeyMap obj;
        Key output;
        List<KeyMap> keyList = getKeyList();
        if (keyList == null) {
            return key;
        }
        Iterator<KeyMap> it = keyList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (obj.getInput() == key) {
                break;
            }
        }
        KeyMap keyMap = obj;
        return (keyMap == null || (output = keyMap.getOutput()) == null) ? key : output.getShort();
    }

    public int getLongKey(int key) {
        KeyMap obj;
        Key output;
        List<KeyMap> keyList = getKeyList();
        if (keyList == null) {
            return 0;
        }
        Iterator<KeyMap> it = keyList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (obj.getInput() == key) {
                break;
            }
        }
        KeyMap keyMap = obj;
        if (keyMap == null || (output = keyMap.getOutput()) == null) {
            return 0;
        }
        return output.getLong();
    }

    public static final class CustomKey {
        private final List<CanIdKeyMap> canIdList;

        // Constructor principal
        public CustomKey(List<CanIdKeyMap> canIdList) {
            this.canIdList = canIdList;
        }

        // Constructor predeterminado con lista vacía
        public CustomKey() {
            this(new ArrayList<>());
        }

        // Getter for canIdList
        public List<CanIdKeyMap> getCanIdList() {
            return this.canIdList;
        }

        // Método copy
        public CustomKey copy(List<CanIdKeyMap> canIdList) {
            return new CustomKey(canIdList);
        }

        // Métodos equals y hashCode
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CustomKey)) {
                return false;
            }
            CustomKey customKey = (CustomKey) other;
            return true;
        }

        @Override
        public int hashCode() {
            return this.canIdList.hashCode();
        }

        // Método toString
        @Override
        public String toString() {
            return "CustomKey(canIdList=" + this.canIdList + ')';
        }
    }


    public final class CanIdKeyMap {
        private final int canId;
        private final List<KeyMap> keyList;

        // Constructor
        public CanIdKeyMap(int canId, List<KeyMap> keyList) {
            this.canId = canId;
            this.keyList = keyList;
        }

        // Getter for 'canId'
        public int getCanId() {
            return this.canId;
        }

        // Getter for 'keyList'
        public List<KeyMap> getKeyList() {
            return this.keyList;
        }

        // Copy method
        public CanIdKeyMap copy(int canId, List<KeyMap> keyList) {
            return new CanIdKeyMap(canId, keyList);
        }

        // Equals method to compare two CanIdKeyMap objects
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CanIdKeyMap)) {
                return false;
            }
            CanIdKeyMap canIdKeyMap = (CanIdKeyMap) other;
            return true;
        }

        // Hash code method
        @Override
        public int hashCode() {
            return (Integer.hashCode(this.canId) * 31) + this.keyList.hashCode();
        }

        // ToString method
        @Override
        public String toString() {
            return "CanIdKeyMap(canId=" + this.canId + ", keyList=" + this.keyList + ')';
        }
    }


    public static final class KeyMap {
        private int input;
        private Key output;

        // Constructor
        public KeyMap(int input, Key output) {

            this.input = input;
            this.output = output;
        }

        // Getter for 'input'
        public int getInput() {
            return this.input;
        }

        // Getter for 'output'
        public Key getOutput() {
            return this.output;
        }

        // Copy function
        public KeyMap copy(int input, Key output) {

            return new KeyMap(input, output);
        }

        // Function to check equality
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KeyMap)) {
                return false;
            }
            KeyMap keyMap = (KeyMap) other;
            return true;
        }

        // Hash code function
        @Override
        public int hashCode() {
            return (Integer.hashCode(this.input) * 31) + this.output.hashCode();
        }

        // String representation
        @Override
        public String toString() {
            return "KeyMap(input=" + this.input + ", output=" + this.output + ')';
        }

        // Setter for 'input'
        public void setInput(int input) {
            this.input = input;
        }

        // Setter for 'output'
        public void setOutput(Key output) {

            this.output = output;
        }
    }

    public static final /* data */ class Key {
        private int longNumber;
        private final int shortNumber;


        /* renamed from: component1, reason: from getter */
        public int getShort() {
            return this.shortNumber;
        }

        /* renamed from: component2, reason: from getter */
        public int getLong() {
            return this.longNumber;
        }

        public Key copy(int r2, int r3) {
            return new Key(r2, r3);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Key)) {
                return false;
            }
            Key key = (Key) other;
            return this.shortNumber == key.shortNumber && this.longNumber == key.longNumber;
        }

        public int hashCode() {
            return (Integer.hashCode(this.shortNumber) * 31) + Integer.hashCode(this.longNumber);
        }

        public String toString() {
            return "Key(short=" + this.shortNumber + ", long=" + this.longNumber + ')';
        }

        public Key(int i, int i2) {
            this.shortNumber = i;
            this.longNumber = i2;
        }

        public void setLong(int i) {
            this.longNumber = i;
        }
    }
}
