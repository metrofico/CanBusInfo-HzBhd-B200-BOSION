package com.hzbhd.canbus.car._331;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;


public class RadarView extends RelativeLayout {
    private ImageView img_car;
    private ImageView img_front_left;
    private ImageView img_front_left_mid;
    private ImageView img_front_right;
    private ImageView img_front_right_mid;
    private ImageView img_rear_left;
    private ImageView img_rear_left_mid;
    private ImageView img_rear_right;
    private ImageView img_rear_right_mid;
    private boolean isShowing;
    private final Context mContext;
    private WindowManager.LayoutParams mLayoutParams;
    private final Runnable mRunnable;
    private final View mView;
    private final WindowManager mWindowManager;
    public static final int[] frontRadar = {0, 0, 0, 0};
    public static final int[] rearRadar = {0, 0, 0, 0};
    private static final int[] imgFrontLeft = {R.drawable.__331__01_1, R.drawable.__331__01_2};
    private static final int[] imgFrontLeftMid = {R.drawable.__331__02_1, R.drawable.__331__02_2, R.drawable.__331__02_3};
    private static final int[] imgFrontRightMid = {R.drawable.__331__03_1, R.drawable.__331__03_2, R.drawable.__331__03_3};
    private static final int[] imgFrontRight = {R.drawable.__331__04_1, R.drawable.__331__04_2};
    private static final int[] imgRearLeft = {R.drawable.__331__05_1, R.drawable.__331__05_2, R.drawable.__331__05_3};
    private static final int[] imgRearLeftMid = {R.drawable.__331__06_1, R.drawable.__331__06_2, R.drawable.__331__06_3, R.drawable.__331__06_4, R.drawable.__331__06_5};
    private static final int[] imgRearRightMid = {R.drawable.__331__07_1, R.drawable.__331__07_2, R.drawable.__331__07_3, R.drawable.__331__07_4, R.drawable.__331__07_5};
    private static final int[] imgRearRight = {R.drawable.__331__08_1, R.drawable.__331__08_2, R.drawable.__331__08_3};

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isShowing = false;
        this.mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car._331.RadarView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.dismissView();
            }
        };
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mView = LayoutInflater.from(context).inflate(R.layout._331_radar_view, this);
        initView();
    }

    private void initView() {
        this.img_car = (ImageView) findViewById(R.id.img_car);
        this.img_front_left = (ImageView) findViewById(R.id.img_front_left);
        this.img_front_left_mid = (ImageView) findViewById(R.id.img_front_left_mid);
        this.img_front_right_mid = (ImageView) findViewById(R.id.img_front_right_mid);
        this.img_front_right = (ImageView) findViewById(R.id.img_front_right);
        this.img_rear_left = (ImageView) findViewById(R.id.img_rear_left);
        this.img_rear_left_mid = (ImageView) findViewById(R.id.img_rear_left_mid);
        this.img_rear_right_mid = (ImageView) findViewById(R.id.img_rear_right_mid);
        this.img_rear_right = (ImageView) findViewById(R.id.img_rear_right);
    }

    public void refreshUi() {
        updateUi();
        if (!(Settings.System.getInt(this.mContext.getContentResolver(), "isBackCamera", 0) == 1)) {
            addViewToWindow();
        } else {
            removeAndDismiss();
        }
    }

    public void updateUi() {
        ImageView imageView = this.img_front_left;
        int[] iArr = imgFrontLeft;
        int[] iArr2 = frontRadar;
        imageView.setImageResource(getImage(iArr, iArr2[0]));
        this.img_front_left_mid.setImageResource(getImage(imgFrontLeftMid, iArr2[1]));
        this.img_front_right_mid.setImageResource(getImage(imgFrontRightMid, iArr2[2]));
        this.img_front_right.setImageResource(getImage(imgFrontRight, iArr2[3]));
        ImageView imageView2 = this.img_rear_left;
        int[] iArr3 = imgRearLeft;
        int[] iArr4 = rearRadar;
        imageView2.setImageResource(getImageRear(iArr3, iArr4[0]));
        this.img_rear_left_mid.setImageResource(getImage(imgRearLeftMid, iArr4[1]));
        this.img_rear_right_mid.setImageResource(getImage(imgRearRightMid, iArr4[2]));
        this.img_rear_right.setImageResource(getImageRear(imgRearRight, iArr4[3]));
        if (iArr4[0] == 1 || iArr4[1] == 1 || iArr4[2] == 1 || iArr4[3] == 1 || iArr2[0] == 1 || iArr2[1] == 1 || iArr2[2] == 1 || iArr2[3] == 1) {
            this.img_car.setImageResource(R.drawable.__331__car2);
        } else {
            this.img_car.setImageResource(R.drawable.__331__car);
        }
    }

    private int getImageRear(int[] iArr, int i) {
        if (i == 1) {
            return iArr[0];
        }
        if (i == 2) {
            return iArr[1];
        }
        if (i == 3 || i == 4) {
            return iArr[2];
        }
        return 0;
    }

    private int getImage(int[] iArr, int i) {
        if (i == 6) {
            i--;
        }
        if (i <= 0 || i > iArr.length) {
            return 0;
        }
        return iArr[i - 1];
    }

    private void addViewToWindow() {
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2038;
            this.mLayoutParams.gravity = 51;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
            this.mLayoutParams.format = 1;
            this.mLayoutParams.flags = 8;
        }
        if (!this.isShowing && !isDistance()) {
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mView.removeCallbacks(this.mRunnable);
        this.mView.postDelayed(this.mRunnable, 5000L);
    }

    private boolean isDistance() {
        int[] iArr = rearRadar;
        if (iArr[0] != 0 || iArr[1] != 0 || iArr[2] != 0 || iArr[3] != 0) {
            return false;
        }
        int[] iArr2 = frontRadar;
        return iArr2[0] == 0 && iArr2[1] == 0 && iArr2[2] == 0 && iArr2[3] == 0;
    }

    private void removeAndDismiss() {
        View view = this.mView;
        if (view != null) {
            view.removeCallbacks(this.mRunnable);
            dismissView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        try {
            this.isShowing = false;
            this.mWindowManager.removeView(this.mView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
