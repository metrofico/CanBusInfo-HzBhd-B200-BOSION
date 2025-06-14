package com.hzbhd.commontools.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.util.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DocumentTool {
    private static String[] PERMISSON_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String TAG = "DocumentTool";

    public static void checkFilePath(File file, boolean z) {
        if (LogUtil.log5()) {
            LogUtil.d("checkFilePath file=" + (file != null) + " , !file.ex=" + (true ^ file.exists()));
        }
        if (file != null) {
            if (!z) {
                file = file.getParentFile();
            }
            if (file == null || file.exists()) {
                return;
            }
            file.mkdirs();
        }
    }

    public static void checkFilePath(String str, boolean z) {
        File file = new File(str);
        Log.d(TAG, "checkFilePath file=true , !file.ex=" + (true ^ file.exists()));
        if (!z) {
            file = file.getParentFile();
        }
        if (file == null || file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static void addFolder(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                boolean zMkdirs = file.mkdirs();
                if (LogUtil.log5()) {
                    LogUtil.d("文件夹创建状态--->" + zMkdirs);
                }
            }
            if (LogUtil.log5()) {
                LogUtil.d("文件夹所在目录：" + file.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addFile(String str) throws IOException {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            boolean zCreateNewFile = file.createNewFile();
            if (LogUtil.log5()) {
                LogUtil.d("文件创建状态--->" + zCreateNewFile);
            }
            if (LogUtil.log5()) {
                LogUtil.d("文件所在路径：" + file.toString());
            }
            deleteFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllFile(String str) {
        deleteFile(new File(str));
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                boolean zDelete = file.delete();
                if (LogUtil.log5()) {
                    LogUtil.d("文件删除状态--->" + zDelete);
                    return;
                }
                return;
            }
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteFile(file2);
                }
                boolean zDelete2 = file.delete();
                if (LogUtil.log5()) {
                    LogUtil.d("文件夹删除状态--->" + zDelete2);
                }
            }
        }
    }

    public static void deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                boolean zDelete = file.delete();
                if (LogUtil.log5()) {
                    LogUtil.d("文件删除状态--->" + zDelete);
                    return;
                }
                return;
            }
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteFile(file2);
                }
                boolean zDelete2 = file.delete();
                if (LogUtil.log5()) {
                    LogUtil.d("文件夹删除状态--->" + zDelete2);
                }
            }
        }
    }

    public static void writeData(String str, String str2) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str), false);
            fileOutputStream.write(str2.getBytes("UTF-8"));
            if (LogUtil.log5()) {
                LogUtil.d("将数据写入到文件中：" + str2);
            }
            fileOutputStream.flush();
            fileOutputStream.getFD().sync();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (LogUtil.log7()) {
                LogUtil.d("writeData: error:" + e.getMessage());
            }
        }
    }

    public static void writeData(String str, String str2, boolean z) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str), z);
            fileOutputStream.write(str2.getBytes("UTF-8"));
            if (LogUtil.log5()) {
                LogUtil.d("将数据写入到文件中：" + str2);
            }
            fileOutputStream.flush();
            fileOutputStream.getFD().sync();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (LogUtil.log7()) {
                LogUtil.d("writeData: error:" + e.getMessage());
            }
        }
    }

    public static void writtenFileData(String str, String str2) throws IOException {
        try {
            File file = new File(str);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(file.length());
            randomAccessFile.write(str2.getBytes("UTF-8"));
            if (LogUtil.log5()) {
                LogUtil.d("要续写进去的数据：" + str2);
            }
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (LogUtil.log7()) {
                LogUtil.d("writtenFileData: " + e.getMessage());
            }
        }
    }

    public static String readFileContent(String str) throws IOException {
        try {
            File file = new File(str);
            byte[] bArr = new byte[32768];
            FileInputStream fileInputStream = new FileInputStream(file);
            StringBuffer stringBuffer = new StringBuffer("");
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i > 0) {
                    stringBuffer.append(new String(bArr, 0, i));
                } else {
                    fileInputStream.close();
                    return stringBuffer.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (!LogUtil.log7()) {
                return null;
            }
            LogUtil.d("readFileContent: " + e.getMessage());
            return null;
        }
    }

    public static boolean isFileExists(String str) {
        return new File(str).exists();
    }

    public static boolean isFolderExists(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isDirectory();
    }

    public static String getFolderName(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(File.separator);
        return iLastIndexOf == -1 ? "" : str.substring(0, iLastIndexOf);
    }

    public static boolean renameFile(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    public static boolean hasFileExists(String str) {
        File file = new File(str);
        return file.exists() && file.listFiles().length > 0;
    }

    public static int copyFile(String str, String str2) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            int i = fileInputStream.read(bArr);
            if (i > 0) {
                fileOutputStream.write(bArr, 0, i);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int copyFile(File file, File file2) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            int i = fileInputStream.read(bArr);
            if (i > 0) {
                fileOutputStream.write(bArr, 0, i);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void copyFileFormAsset(Context context, String str, String str2) throws IOException {
        if (new File(str2).exists()) {
            return;
        }
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    inputStreamOpen.close();
                    fileOutputStream.flush();
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copyDir(String str, String str2) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            return -1;
        }
        File[] fileArrListFiles = file.listFiles();
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i].isDirectory()) {
                copyDir(fileArrListFiles[i].getPath() + "/", fileArrListFiles[i].getName() + "/");
            } else {
                copyFile(fileArrListFiles[i].getPath(), str2 + fileArrListFiles[i].getName());
            }
        }
        return 0;
    }

    public static List<String> getFileList(String str) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (!file.isDirectory() && !file.isHidden()) {
                    if (LogUtil.log5()) {
                        LogUtil.d("getFileList: 绝对路径=[" + file.getAbsolutePath() + "]");
                    }
                    arrayList.add(file.getAbsolutePath());
                }
            }
        } else if (LogUtil.log7()) {
            LogUtil.d("getFileList: error=空目录");
        }
        return arrayList;
    }

    public static long getFileSize(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                return file.length();
            } catch (Exception e) {
                e.printStackTrace();
                if (LogUtil.log7()) {
                    LogUtil.d("getFileSize: " + e.getMessage());
                }
            }
        } else if (LogUtil.log7()) {
            LogUtil.d("getFileSize: 获取文件大小：文件不存在！！");
        }
        return 0L;
    }

    public static String getAssetsString(Context context, String str) throws IOException {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStreamOpen.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
