package com.hzbhd.commontools.utils;

import android.util.JsonReader;

import com.hzbhd.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class bhdJsonUtils {
    private static final String TAG = "com.hzbhd.commontools.utils.bhdJsonUtils";
    private JSONObject mJsonObject;

    public bhdJsonUtils(String jsonString) {
        init(jsonString);
    }

    // Check if the given key exists in the JSON string
    public boolean checkJsonKey(String jsonString, String key) throws IOException {
        try (JsonReader jsonReader = new JsonReader(new StringReader(jsonString))) {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.nextName().equals(key)) {
                    return true;
                }
            }
            jsonReader.endObject();
        } catch (Exception e) {
            LogUtil.d("checkJsonKey: " + e.toString());
        }
        return false;
    }

    // Check if the key exists in the initialized JSONObject
    public boolean checkJsonKey(String key) {
        if (mJsonObject != null) {
            return !mJsonObject.isNull(key);
        }
        LogUtil.d("checkJsonKey: mJsonObject=NULL");
        return false;
    }

    // Check if the JSON string is a valid JSONObject or JSONArray
    public boolean checkJsonFull(String jsonString) {
        try {
            Object obj = new JSONTokener(jsonString).nextValue();
            return obj instanceof JSONObject || obj instanceof JSONArray;
        } catch (Exception e) {
            LogUtil.d("checkJsonFull: error " + e.toString());
            return false;
        }
    }

    // Initialize JSONObject from string
    private void init(String jsonString) {
        try {
            mJsonObject = new JSONObject(jsonString);
        } catch (Exception e) {
            LogUtil.d("init: error: " + e.toString());
        }
    }

    // Check if the main JSONObject is initialized
    public boolean checkMainJson() {
        return mJsonObject != null;
    }

    // Fetch the string value associated with the given key
    public String optString(String key, String defaultValue) {
        if (checkMainJson()) {
            return mJsonObject.optString(key, defaultValue);
        }
        LogUtil.d("optString: mJsonObject=NULL");
        return defaultValue;
    }

    // Fetch the integer value associated with the given key
    public int optInt(String key, int defaultValue) {
        if (checkMainJson()) {
            return mJsonObject.optInt(key, defaultValue);
        }
        LogUtil.d("optInt: mJsonObject=NULL");
        return defaultValue;
    }

    // Fetch the boolean value associated with the given key
    public boolean optBoolean(String key, boolean defaultValue) {
        if (checkMainJson()) {
            return mJsonObject.optBoolean(key, defaultValue);
        }
        LogUtil.d("optBoolean: mJsonObject=NULL");
        return defaultValue;
    }

    // Fetch the double value associated with the given key
    public double optDouble(String key, double defaultValue) {
        if (checkMainJson()) {
            return mJsonObject.optDouble(key, defaultValue);
        }
        LogUtil.d("optDouble: mJsonObject=NULL");
        return defaultValue;
    }

    // Fetch the long value associated with the given key
    public Long optLong(String key, int defaultValue) {
        if (checkMainJson()) {
            return mJsonObject.optLong(key, defaultValue);
        }
        LogUtil.d("optLong: mJsonObject=NULL");
        return Long.valueOf(defaultValue);
    }

    // Fetch the JSONArray associated with the given key
    public JSONArray optArray(String key) {
        if (checkMainJson()) {
            return mJsonObject.optJSONArray(key);
        }
        LogUtil.d("JSONArray: mJsonObject=NULL");
        return new JSONArray();
    }

    // Fetch the list of objects of type T associated with the given key
    public <T> ArrayList<T> optList(String key, Class<T> cls) {
        if (checkMainJson()) {
            JSONArray jsonArray = mJsonObject.optJSONArray(key);
            ArrayList<T> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    list.add((T) jsonArray.get(i));
                } catch (Exception e) {
                    LogUtil.d("optList: " + e.toString());
                }
            }
            return list;
        }
        LogUtil.d("optList: mJsonObject=NULL");
        return new ArrayList<>();
    }

    // Fetch the object associated with the given key
    public Object optObject(String key) {
        if (checkMainJson()) {
            return mJsonObject.opt(key);
        }
        LogUtil.d("optObject: mJsonObject=NULL");
        return new Object();
    }

    // Fetch the JSONObject associated with the given key
    public JSONObject optJsonObject(String key) {
        if (checkMainJson()) {
            return mJsonObject.optJSONObject(key);
        }
        LogUtil.d("optObject: mJsonObject=NULL");
        return new JSONObject();
    }

    // Put the object into the main JSONObject
    public void putObject(String key, Object value) {
        if (checkMainJson()) {
            try {
                mJsonObject.put(key, value);
            } catch (Throwable w) {
                LogUtil.e("putObject: " + w.getLocalizedMessage());
            }
        } else {
            LogUtil.d("putObject: mJsonObject=NULL");
        }
    }

    // Convert the JSONObject to string
    public String ObjectToString() {
        if (checkMainJson()) {
            return mJsonObject.toString();
        }
        LogUtil.d("objectToString: mJsonObject=NULL");
        return "";
    }
}
