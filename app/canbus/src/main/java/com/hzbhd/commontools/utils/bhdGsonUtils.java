package com.hzbhd.commontools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzbhd.util.LogUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class bhdGsonUtils {
    private static final Gson mGson = new GsonBuilder().create();

    public static <T> T fromJson(String dir, String fileName, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromJson() called with: dir = [" + dir + "], fileName = [" + fileName + "], classOfT = [" + cls + "]");
        }
        String filePath = dir + fileName;
        if (LogUtil.log5()) {
            LogUtil.d("fromJson file path:" + filePath);
        }
        return fromFilePath(filePath, cls);
    }

    public static <T> T fromFilePath(String filePath, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath() called with: filePath = [" + filePath + "], classOfT = [" + cls + "]");
        }
        T t = fromFilePath_real(filePath, cls);
        return (t != null) ? t : fromFilePath_frombakfile_andsave(filePath, cls);
    }

    public static <T> T fromFilePath_real(String filePath, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath_real() called with: filePath = [" + filePath + "], classOfT = [" + cls + "]");
        }

        try (FileReader fileReader = new FileReader(filePath)) {
            return mGson.fromJson(fileReader, cls);
        } catch (Exception e) {
            LogUtil.d("Error reading file: " + filePath + " " + e);
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromFilePath_frombakfile_andsave(String filePath, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath_frombakfile_andsave() called with: filePath = [" + filePath + "], classOfT = [" + cls + "]");
        }
        T t = fromFilePath_real(filePath + ".bak", cls);
        if (t == null) {
            t = fromFilePath_frombackupfold_savebak(filePath, cls);
        }
        if (t != null) {
            toRealFileJson(filePath, t);
        }
        return t;
    }

    public static <T> T fromFilePath_frombackupfold_savebak(String filePath, Class<T> cls) {
        if (LogUtil.log5()) {
            LogUtil.d("fromFilePath_frombackupfold_savebak() called with: filePath = [" + filePath + "], classOfT = [" + cls + "]");
        }
        T t = fromFilePath_real(filePath.replace("/bhd/appconfig/", "/bhd/backup/appconfig/"), cls);
        if (t != null) {
            toRealFileJson(filePath + ".bak", t);
        }
        return t;
    }

    public static boolean toJson(String dir, String fileName, Object obj) {
        return toFileJson(dir + fileName, obj);
    }

    public static boolean toFileJson(String filePath, Object obj) {
        toRealFileJson(filePath, obj);
        toRealFileJson(filePath + "bak", obj);
        return true;
    }

    public static boolean toRealFileJson(String filePath, Object obj) {
        try {
            String json = mGson.toJson(obj);
            if (LogUtil.log5()) {
                LogUtil.d("toRealFileJson() called with: filePath = [" + filePath + "], JSON = " + json);
            }
            new File(filePath).getParentFile().mkdirs();
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(json);
                fileWriter.flush();
            }
            callBhdSyncUsingReflection();
            //SystemMix.bhd_sync();
            return true;
        } catch (Exception e) {
            LogUtil.d("Error writing to file: " + filePath + ", " + e);
            e.printStackTrace();
            return false;
        }
    }

    public static String toJson(Object obj) {
        try {
            return mGson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void callBhdSyncUsingReflection() {
        try {
            // Obtener la clase SystemMix
            Class<?> systemMixClass = Class.forName("com.softwinner.SystemMix");

            // Obtener el método bhd_sync (sin parámetros, método estático)
            Method method = systemMixClass.getDeclaredMethod("bhd_sync");

            // Invocar el método estático
            method.invoke(null);  // El primer parámetro es 'null' ya que es un método estático
        } catch (Exception e) {
            // Manejo de excepciones si algo sale mal
            e.printStackTrace();
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> cls) {
        return mGson.fromJson(jsonString, cls);
    }

    public static <T> T fromJson(String jsonString, Type type) {
        return mGson.fromJson(jsonString, type);
    }
}
