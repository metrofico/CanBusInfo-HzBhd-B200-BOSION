package com.hzbhd.commontools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzbhd.util.LogUtil;
import com.softwinner.SystemMix;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public class bhdGsonUtils {
    private static final Gson mGson = new GsonBuilder().create();

    public static <T> T fromJson(String str, String str2, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromJson() called with: dir = [" + str + "], fileName = [" + str2 + "], classOfT = [" + cls + "]");
        }
        String str3 = str + str2;
        if (LogUtil.log5()) {
            LogUtil.d("fromjson file path:" + str3);
        }
        return (T) fromFilePath(str3, cls);
    }

    public static <T> T fromFilePath(String str, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath() called with: filePath = [" + str + "], classOfT = [" + cls + "]");
        }
        T t = (T) fromFilePath_real(str, cls);
        return t == null ? (T) fromFilePath_frombakfile_andsave(str, cls) : t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.FileReader] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.FileReader] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static <T> T fromFilePath_real(String str, Class<T> cls) throws Throwable {
        T t;
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath_real() called with: filePath = [" + str + "], classOfT = [" + cls + "]");
        }
        ?? r0 = (T) null;
        try {
            try {
                FileReader fileReader = new FileReader(str);
                try {
                    Gson gson = mGson;
                    synchronized (gson) {
                        r0 = (T) gson.fromJson((Reader) fileReader, (Class) cls);
                    }
                    try {
                        fileReader.close();
                        return r0;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return r0;
                    }
                } catch (Exception e2) {
                    e = e2;
                    t = r0;
                    r0 = (T) fileReader;
                    if (LogUtil.log5()) {
                        LogUtil.d("error,filePath=" + str + "," + e);
                    }
                    e.printStackTrace();
                    if (r0 != 0) {
                        try {
                            ((FileReader) r0).close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return t;
                } catch (Throwable th) {
                    th = th;
                    r0 = fileReader;
                    if (r0 != 0) {
                        try {
                            r0.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
            t = null;
            r0 = r0;
        }
    }

    public static <T> T fromFilePath_frombakfile_andsave(String str, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath_frombakfile_andsave() called with: filePath = [" + str + "], classOfT = [" + cls + "]");
        }
        T t = (T) fromFilePath_real(str + ".bak", cls);
        if (t == null) {
            t = (T) fromFilePath_frombackupfold_savebak(str, cls);
        }
        if (t != null) {
            toRealFileJson(str, t);
        }
        return t;
    }

    public static <T> T fromFilePath_frombackupfold_savebak(String str, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath_frombackupfold_baksave() called with: filePath = [" + str + "], classOfT = [" + cls + "]");
        }
        String str2 = str + ".bak";
        T t = (T) fromFilePath_real(str.replace("/bhd/appconfig/", "/bhd/backup/appconfig/"), cls);
        if (t != null) {
            toRealFileJson(str2, t);
        }
        return t;
    }

    public static boolean toJson(String str, String str2, Object obj) {
        return toFileJson(str + str2, obj);
    }

    public static boolean toFileJson(String str, Object obj) {
        toRealFileJson(str, obj);
        toRealFileJson(str + "bak", obj);
        return true;
    }

    public static boolean toRealFileJson(String str, Object obj) {
        String json;
        try {
            Gson gson = mGson;
            synchronized (gson) {
                json = gson.toJson(obj);
            }
            if (LogUtil.log5()) {
                LogUtil.d("toRealFileJson() called with: filePath = [" + str + "], JSON = " + json);
            }
            new File(str).getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(str);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
            SystemMix.bhd_sync();
            return true;
        } catch (Exception e) {
            if (LogUtil.log5()) {
                LogUtil.d("error, toFileJson " + str + "," + e);
            }
            e.printStackTrace();
            return false;
        }
    }

    public static String toJson(Object obj) {
        String json;
        try {
            Gson gson = mGson;
            synchronized (gson) {
                json = gson.toJson(obj);
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        T t;
        Gson gson = mGson;
        synchronized (gson) {
            t = (T) gson.fromJson(str, (Class) cls);
        }
        return t;
    }

    public static <T> T fromJson(String str, Type type) {
        T t;
        Gson gson = mGson;
        synchronized (gson) {
            t = (T) gson.fromJson(str, type);
        }
        return t;
    }
}
