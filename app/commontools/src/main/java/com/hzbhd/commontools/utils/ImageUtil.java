package com.hzbhd.commontools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.view.ViewCompat;

import com.hzbhd.util.LogUtil;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class ImageUtil {
    public static final int BOTTOM = 1;
    public static final int CENTER = 8;
    public static final int LEFT = 2;
    public static final int LEFT_BOTTOM = 5;
    public static final int LEFT_TOP = 4;
    public static final int RIGHT = 3;
    public static final int RIGHT_BOTTOM = 7;
    public static final int RIGHT_TOP = 6;
    private static final String TAG = "ImageUtil";
    public static final int TOP = 0;

    public static byte[] addBMPImageHeader(int i) {
        return new byte[]{66, 77, (byte) (i >> 0), (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24), 0, 0, 0, 0, 54, 0, 0, 0};
    }

    public static byte[] addBMPImageInfosHeader(int i, int i2) {
        return new byte[]{40, 0, 0, 0, (byte) (i >> 0), (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24), (byte) (i2 >> 0), (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24), 1, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public static Bitmap byteToBitmap(byte[] bArr) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap zoomBimtap(Bitmap bitmap, float f, float f2) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap zoomBimtap(Bitmap bitmap, float f, float f2, float f3, float f4) {
        float f5 = f / f3;
        float f6 = f2 / f4;
        if (f5 > f6) {
            f4 = f2 / f5;
        } else {
            f3 = f / f6;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) f3, (int) f4, true);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap createRoundedCornerBitmap(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        paint.setFilterBitmap(true);
        Rect rect = new Rect(0, 0, width, height);
        float f = i;
        canvas.drawRoundRect(new RectF(rect), f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    public static Drawable createSelectedTip(Context context, int i, int i2) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        Bitmap bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), i2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, paint);
        canvas.drawBitmap(bitmapDecodeResource2, bitmapDecodeResource.getWidth() - bitmapDecodeResource2.getWidth(), 0.0f, paint);
        return bitmapToDrawable(bitmapCreateBitmap);
    }

    public static Bitmap createReflectionBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = height / 2;
        int i2 = height + i + 4;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, i2, Bitmap.Config.ARGB_8888);
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmap, 0, i, width, i, matrix, true);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        float f = height + 4;
        canvas.drawBitmap(bitmapCreateBitmap2, 0.0f, f, paint);
        paint.setShader(new LinearGradient(0.0f, f, 0.0f, f + i, 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f, width, i2, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap createReflectionBitmapForSingle(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight() / 2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmap, 0, height, width, height, matrix, true);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmapCreateBitmap2, 0.0f, 0.0f, paint);
        float f = height;
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, f, 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, 0.0f, width, f, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap createGreyBitmap(Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    public static boolean saveImage(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            if (bitmap.compress(compressFormat, 100, fileOutputStream)) {
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static Bitmap createWatermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        if (i == 4) {
            float f = i2;
            canvas.drawBitmap(bitmap2, f, f, (Paint) null);
        } else if (i == 5) {
            canvas.drawBitmap(bitmap2, i2, (bitmap.getHeight() - bitmap2.getHeight()) - i2, (Paint) null);
        } else if (i == 6) {
            canvas.drawBitmap(bitmap2, (bitmap.getWidth() - bitmap2.getWidth()) - i2, i2, (Paint) null);
        } else if (i == 7) {
            canvas.drawBitmap(bitmap2, (bitmap.getWidth() - bitmap2.getWidth()) - i2, (bitmap.getHeight() - bitmap2.getHeight()) - i2, (Paint) null);
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap composeBitmap(int i, Bitmap... bitmapArr) {
        if (bitmapArr.length < 2) {
            return null;
        }
        Bitmap bitmapComposeBitmap = bitmapArr[0];
        for (Bitmap bitmap : bitmapArr) {
            bitmapComposeBitmap = composeBitmap(bitmapComposeBitmap, bitmap, i);
        }
        return bitmapComposeBitmap;
    }

    private static Bitmap composeBitmap(Bitmap bitmap, Bitmap bitmap2, int i) {
        Bitmap bitmapCreateBitmap;
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (i == 0) {
            if (width2 > width) {
                width = width2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(width, height + height2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
            canvas.drawBitmap(bitmap, 0.0f, height2, (Paint) null);
        } else if (i == 1) {
            if (width <= width2) {
                width = width2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(width, height2 + height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            canvas2.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas2.drawBitmap(bitmap2, 0.0f, height, (Paint) null);
        } else if (i == 2) {
            int i2 = width + width2;
            if (height2 > height) {
                height = height2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(i2, height, Bitmap.Config.ARGB_8888);
            Canvas canvas3 = new Canvas(bitmapCreateBitmap);
            canvas3.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
            canvas3.drawBitmap(bitmap, width2, 0.0f, (Paint) null);
        } else {
            if (i != 3) {
                return null;
            }
            int i3 = width2 + width;
            if (height <= height2) {
                height = height2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(i3, height, Bitmap.Config.ARGB_8888);
            Canvas canvas4 = new Canvas(bitmapCreateBitmap);
            canvas4.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas4.drawBitmap(bitmap2, width, 0.0f, (Paint) null);
        }
        return bitmapCreateBitmap;
    }

    public static byte[] addBMP_RGB_888(int[] iArr, int i, int i2) {
        int length = iArr.length;
        byte[] bArr = new byte[i2 * i * 4];
        int i3 = length - 1;
        int i4 = 0;
        while (i3 >= i) {
            int i5 = i3 - i;
            for (int i6 = i5 + 1; i6 <= i3; i6++) {
                int i7 = iArr[i6];
                bArr[i4] = (byte) (i7 >> 0);
                bArr[i4 + 1] = (byte) (i7 >> 8);
                bArr[i4 + 2] = (byte) (i7 >> 16);
                bArr[i4 + 3] = (byte) (i7 >> 24);
                i4 += 4;
            }
            i3 = i5;
        }
        return bArr;
    }

    private static double getScaling(int i, int i2) {
        return Math.sqrt(i2 / i);
    }

    public static byte[] decodeBitmap(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // Solo obtienes las dimensiones de la imagen
        BitmapFactory.decodeFile(filePath, options); // Llamada para obtener las dimensiones

        // Calcula el tamaño adecuado para reducir la imagen
        options.inSampleSize = computeSampleSize(options, -1, 614400); // Llama a tu función para obtener el tamaño adecuado
        options.inJustDecodeBounds = false; // Ahora sí carga la imagen completa
        options.inPurgeable = true; // Permite que la imagen se recicle si es necesario
        options.inInputShareable = true; // Permite compartir los recursos de entrada

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            // Decodifica la imagen con las opciones ajustadas
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(inputStream.getFD(), null, options);

            if (bitmap != null) {
                // Si la imagen fue cargada exitosamente, la redimensionamos
                float scalingFactor = (float) getScaling(options.outWidth * options.outHeight, 614400);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (options.outWidth * scalingFactor), (int) (options.outHeight * scalingFactor), true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // Guarda como JPEG
                return byteArrayOutputStream.toByteArray(); // Devuelve la imagen como byte array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Devuelve null si hubo un error al cargar o procesar la imagen
    }


    public static int computeSampleSize(BitmapFactory.Options options, int i, int i2) {
        int iComputeInitialSampleSize = computeInitialSampleSize(options, i, i2);
        if (iComputeInitialSampleSize > 8) {
            return 8 * ((iComputeInitialSampleSize + 7) / 8);
        }
        int i3 = 1;
        while (i3 < iComputeInitialSampleSize) {
            i3 <<= 1;
        }
        return i3;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int i, int i2) {
        int iMin;
        double d = options.outWidth;
        double d2 = options.outHeight;
        int iCeil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / i2));
        if (i == -1) {
            iMin = 128;
        } else {
            double d3 = i;
            iMin = (int) Math.min(Math.floor(d / d3), Math.floor(d2 / d3));
        }
        if (iMin < iCeil) {
            return iCeil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i == -1 ? iCeil : iMin;
    }

    public static Bitmap showImage(String str, int i) {
        if (str != null) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                options.inSampleSize = options.outHeight / i;
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                options.inPurgeable = true;
                options.inInputShareable = true;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(str, options);
            } catch (Error unused) {
            }
        }
        return null;
    }

    public static String toCopyImage(Bitmap bitmap, int i, int i2, String str, int i3) throws Exception {
        if (LogUtil.log5()) {
            LogUtil.d("toCopyImage: Ready Start");
        }
        Bitmap bitmapRotateBitmap = rotateBitmap(bitmap, i3);
        if (LogUtil.log5()) {
            LogUtil.d("toCopyImage: <rotateBitmap> [w,h] = [" + bitmapRotateBitmap.getWidth() + "," + bitmapRotateBitmap.getHeight());
        }
        Bitmap bitmapZoomBimtap = zoomBimtap(bitmapRotateBitmap, bitmapRotateBitmap.getWidth(), bitmapRotateBitmap.getHeight(), i, i2);
        if (LogUtil.log5()) {
            LogUtil.d("toCopyImage: <zoomBimtap> [w,h] = [" + bitmapZoomBimtap.getWidth() + "," + bitmapZoomBimtap.getHeight() + "]");
        }
        Bitmap bitmapPotoMix = potoMix(8, createSolidColorBitmap(i, i2, "#000000"), bitmapZoomBimtap);
        if (LogUtil.log5()) {
            LogUtil.d("toCopyImage: <potoMix> [w,h] = [" + bitmapPotoMix.getWidth() + "," + bitmapPotoMix.getHeight() + "]");
        }
        return saveBitmapToBmp(str, bitmapPotoMix);
    }

    public static void rotateFile(String str, int i) {
        String str2 = "./system/bin/convert " + str + "  -rotate " + i + "  " + str;
        Log.d("lvjinhua", str + " , z-----------rotateFile=" + i);
        try {
            execCommand(str2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        SystemClock.sleep(100L);
    }

    public static void execCommand(String str) throws IOException, InterruptedException {
        Process processExec = null;
        BufferedReader bufferedReader = null;
        try {
            processExec = Runtime.getRuntime().exec(str);
            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            // Leer la salida del comando línea por línea
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }

            // Esperar a que el proceso termine
            int exitCode = processExec.waitFor();
            if (exitCode != 0) {
                System.err.println("Exit value = " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            // Manejar excepciones específicas
            System.err.println("Error ejecutando el comando: " + e.getMessage());
            throw e;  // Volver a lanzar la excepción para que el llamador pueda manejarla
        } finally {
            // Cerrar el BufferedReader y el proceso si no es nulo
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("Error cerrando el BufferedReader: " + e.getMessage());
                }
            }
            if (processExec != null) {
                processExec.destroy();  // Liberar recursos del proceso
            }
        }
    }


    public static Bitmap rotateBitmap(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        bitmapCreateBitmap.equals(bitmap);
        return bitmapCreateBitmap;
    }

    public static void drawableToFile(Drawable drawable, String str, Bitmap.CompressFormat compressFormat) throws IOException {
        if (drawable == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ((BitmapDrawable) drawable).getBitmap().compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getPathBitmap(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    public Bitmap scaleBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    public static String saveBitmapToBmp(String str, Bitmap bitmap) throws Exception {
        if (LogUtil.log5()) {
            LogUtil.d("saveBitmap: Ready to save");
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (LogUtil.log5()) {
            LogUtil.d("saveBitmap: bitmap[w,h] = [" + width + "," + height + "]");
        }
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArrAddBMP_RGB_888 = addBMP_RGB_888(iArr, width, height);
        byte[] bArrAddBMPImageHeader = addBMPImageHeader(bArrAddBMP_RGB_888.length);
        byte[] bArrAddBMPImageInfosHeader = addBMPImageInfosHeader(width, height);
        byte[] bArr = new byte[bArrAddBMP_RGB_888.length + 54];
        System.arraycopy(bArrAddBMPImageHeader, 0, bArr, 0, bArrAddBMPImageHeader.length);
        System.arraycopy(bArrAddBMPImageInfosHeader, 0, bArr, 14, bArrAddBMPImageInfosHeader.length);
        System.arraycopy(bArrAddBMP_RGB_888, 0, bArr, 54, bArrAddBMP_RGB_888.length);
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        fileOutputStream.write(bArr);
        fileOutputStream.flush();
        fileOutputStream.close();
        return str;
    }

    public static Bitmap createSolidColorBitmap(int i, int i2, String str) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.eraseColor(Color.parseColor(str));
        return bitmapCreateBitmap;
    }

    public static Bitmap potoMix(int i, Bitmap... bitmapArr) {
        if (bitmapArr.length <= 0) {
            return null;
        }
        if (bitmapArr.length == 1) {
            return bitmapArr[0];
        }
        Bitmap bitmapCreateBitmapForFotoMix = bitmapArr[0];
        for (int i2 = 1; i2 < bitmapArr.length; i2++) {
            bitmapCreateBitmapForFotoMix = createBitmapForFotoMix(bitmapCreateBitmapForFotoMix, bitmapArr[i2], i);
        }
        return bitmapCreateBitmapForFotoMix;
    }

    private static Bitmap createBitmapForFotoMix(Bitmap bitmap, Bitmap bitmap2, int i) {
        Bitmap bitmapCreateBitmap;
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (i == 2) {
            int i2 = width + width2;
            if (height <= height2) {
                height = height2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(i2, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(bitmap, width2, 0.0f, (Paint) null);
            canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        } else if (i == 3) {
            int i3 = width2 + width;
            if (height <= height2) {
                height = height2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(i3, height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            canvas2.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas2.drawBitmap(bitmap2, width, 0.0f, (Paint) null);
        } else if (i == 0) {
            if (width2 > width) {
                width = width2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(width, height + height2, Bitmap.Config.ARGB_8888);
            Canvas canvas3 = new Canvas(bitmapCreateBitmap);
            canvas3.drawBitmap(bitmap, 0.0f, height2, (Paint) null);
            canvas3.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        } else if (i == 1) {
            if (width2 > width) {
                width = width2;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(width, height2 + height, Bitmap.Config.ARGB_8888);
            Canvas canvas4 = new Canvas(bitmapCreateBitmap);
            canvas4.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas4.drawBitmap(bitmap2, 0.0f, height, (Paint) null);
        } else {
            if (i != 8) {
                return null;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(Math.max(width, width2), Math.max(height, height2), Bitmap.Config.ARGB_8888);
            Canvas canvas5 = new Canvas(bitmapCreateBitmap);
            canvas5.drawBitmap(bitmap, (bitmapCreateBitmap.getWidth() - width) / 2.0f, (bitmapCreateBitmap.getHeight() - height) / 2.0f, (Paint) null);
            canvas5.drawBitmap(bitmap2, (bitmapCreateBitmap.getWidth() - width2) / 2.0f, (bitmapCreateBitmap.getHeight() - height2) / 2.0f, (Paint) null);
        }
        return bitmapCreateBitmap;
    }
}
