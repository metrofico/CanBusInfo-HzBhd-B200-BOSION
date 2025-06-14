package com.hzbhd.commontools.utils;

import android.util.JsonReader;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.util.LogUtil;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes2.dex */
public class bhdJsonUtils {
    private static final String TAG = "com.hzbhd.commontools.utils.bhdJsonUtils";
    private JSONObject mJsonObject = null;

    public bhdJsonUtils(String str) {
        init(str);
    }

    public boolean checkJsonKey(String str, String str2) throws IOException {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        boolean z = false;
        try {
            jsonReader.beginObject();
            while (true) {
                if (!jsonReader.hasNext()) {
                    break;
                }
                if (jsonReader.nextName().equals(str2)) {
                    z = true;
                    break;
                }
            }
            jsonReader.endObject();
        } catch (Exception e) {
            if (LogUtil.log7()) {
                LogUtil.d("checkJsonKey: " + e.toString());
            }
        }
        return z;
    }

    public boolean checkJsonKey(String str) {
        if (this.mJsonObject != null) {
            return !r0.isNull(str);
        }
        if (LogUtil.log7()) {
            LogUtil.d("checkJsonKey: mJsonObject=NULL");
        }
        return false;
    }

    public boolean checkJsonFull(String str) throws JSONException {
        try {
            Object objNextValue = new JSONTokener(str).nextValue();
            if (!(objNextValue instanceof JSONObject)) {
                if (!(objNextValue instanceof JSONArray)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            if (!LogUtil.log5()) {
                return false;
            }
            LogUtil.d("checkJsonFull: error " + e.toString());
            return false;
        }
    }

    private void init(String str) {
        try {
            this.mJsonObject = new JSONObject(str);
        } catch (Exception e) {
            if (LogUtil.log7()) {
                LogUtil.d("init: mJsonObject=" + (this.mJsonObject == null) + ", error: " + e.toString());
            }
        }
    }

    public boolean checkMainJson() {
        return this.mJsonObject != null;
    }

    public String optString(String str, String str2) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optString: mJsonObject=" + (this.mJsonObject == null));
            }
            return str2;
        }
        return this.mJsonObject.optString(str, str2);
    }

    public int optInt(String str, int i) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optInt: mJsonObject=" + (this.mJsonObject == null));
            }
            return i;
        }
        return this.mJsonObject.optInt(str, i);
    }

    public boolean optBoolean(String str, boolean z) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optBoolean: mJsonObject=" + (this.mJsonObject == null));
            }
            return z;
        }
        return this.mJsonObject.optBoolean(str, z);
    }

    public double optDouble(String str, double d) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optDouble: mJsonObject=" + (this.mJsonObject == null));
            }
            return d;
        }
        return this.mJsonObject.optDouble(str, d);
    }

    public Long optLong(String str, int i) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optLong: mJsonObject=" + (this.mJsonObject == null));
            }
            return Long.valueOf(i);
        }
        return Long.valueOf(this.mJsonObject.optLong(str, i));
    }

    public JSONArray optArrary(String str) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("JSONArray: mJsonObject=" + (this.mJsonObject == null));
            }
            return new JSONArray();
        }
        return this.mJsonObject.optJSONArray(str);
    }

    public <T> ArrayList<T> optList(String str, Class<T> cls) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optList: mJsonObject=" + (this.mJsonObject == null));
            }
            return new ArrayList<>();
        }
        JSONArray jSONArrayOptJSONArray = this.mJsonObject.optJSONArray(str);
        GeneralDvrFile.AnonymousClass1 anonymousClass1 = (ArrayList<T>) new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            try {
                anonymousClass1.add(jSONArrayOptJSONArray.get(i));
            } catch (Exception e) {
                if (LogUtil.log5()) {
                    LogUtil.d("optList: " + e.toString());
                }
            }
        }
        return anonymousClass1;
    }

    public Object optObject(String str) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optObject: mJsonObject=" + (this.mJsonObject == null));
            }
            return new Object();
        }
        return this.mJsonObject.opt(str);
    }

    public JSONObject optJsonObject(String str) {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("optObject: mJsonObject=" + (this.mJsonObject == null));
            }
            return new JSONObject();
        }
        return this.mJsonObject.optJSONObject(str);
    }

    public void putObject(String str, Object obj) throws JSONException {
        if (!checkMainJson()) {
            if (LogUtil.log7()) {
                LogUtil.d("putObject: mJsonObject=" + (this.mJsonObject == null));
            }
        } else {
            try {
                this.mJsonObject.put(str, obj);
            } catch (Exception e) {
                if (LogUtil.log7()) {
                    LogUtil.d("putObject: error--> " + e.toString());
                }
            }
        }
    }

    public String ObjectToString() {
        if (!checkMainJson()) {
            if (!LogUtil.log7()) {
                return "";
            }
            LogUtil.d("ObjectToString: mJsonObject=" + (this.mJsonObject == null));
            return "";
        }
        return this.mJsonObject.toString();
    }
}
