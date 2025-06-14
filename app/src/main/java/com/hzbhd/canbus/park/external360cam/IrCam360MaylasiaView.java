package com.hzbhd.canbus.park.external360cam;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class IrCam360MaylasiaView extends RelativeLayout implements View.OnClickListener {
    public static boolean isHaveView = false;
    private ImageButton btn_exit;
    private ImageButton btn_next;
    public ImageButton cam_360_pull_btn;
    private byte currentPage;
    private ImageButton four_region;
    private ImageButton front_all;
    private ImageButton front_only;
    private boolean isBtnsShow;
    private byte lastRecPage;
    private ImageButton left_all;
    private ImageButton left_only;
    private ImageButton left_right_front_all;
    private ImageButton left_right_rear_all;
    private LinearLayout lo_360_main_btns;
    private LinearLayout lo_cam_btn_part_1;
    private LinearLayout lo_cam_btn_part_2;
    private LinearLayout lo_cam_btn_part_3;
    private LinearLayout lo_cam_btn_part_4;
    private ImageButton rear_all;
    private ImageButton rear_only;
    private ImageButton right_all;
    private ImageButton right_only;
    View view;

    public IrCam360MaylasiaView(Context context) {
        this(context, null);
    }

    public IrCam360MaylasiaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IrCam360MaylasiaView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public IrCam360MaylasiaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.isBtnsShow = false;
        this.currentPage = (byte) 0;
        this.lastRecPage = (byte) 0;
        this.view = LayoutInflater.from(context).inflate(R.layout.ir_cam_360_malaysia, this);
        initUI(getContext());
    }

    private ImageButton setOnclickAll(int i) {
        ImageButton imageButton = (ImageButton) this.view.findViewById(i);
        if (imageButton != null) {
            imageButton.setOnClickListener(this);
        }
        return imageButton;
    }

    private void setVisual(LinearLayout linearLayout, boolean z) {
        if (linearLayout.getVisibility() == 0 && z) {
            linearLayout.setVisibility(8);
        }
        if (linearLayout.getVisibility() != 8 || z) {
            return;
        }
        linearLayout.setVisibility(0);
    }

    public void setImgBtnsGone() {
        if (this.isBtnsShow) {
            setVisual(this.lo_cam_btn_part_1, true);
            setVisual(this.lo_cam_btn_part_2, true);
            setVisual(this.lo_cam_btn_part_3, true);
            setVisual(this.lo_cam_btn_part_4, true);
            this.isBtnsShow = false;
            return;
        }
        this.currentPage = this.lastRecPage;
        changeBtnPage();
        setVisual(this.lo_cam_btn_part_4, false);
        this.isBtnsShow = true;
    }

    public void initUI(Context context) {
        this.front_all = setOnclickAll(R.id.front_all);
        this.rear_all = setOnclickAll(R.id.rear_all);
        this.left_all = setOnclickAll(R.id.left_all);
        this.right_all = setOnclickAll(R.id.right_all);
        this.front_only = setOnclickAll(R.id.front_only);
        this.rear_only = setOnclickAll(R.id.rear_only);
        this.left_only = setOnclickAll(R.id.left_only);
        this.right_only = setOnclickAll(R.id.right_only);
        this.left_right_front_all = setOnclickAll(R.id.left_right_front_all);
        this.four_region = setOnclickAll(R.id.four_region);
        this.left_right_rear_all = setOnclickAll(R.id.left_right_rear_all);
        this.cam_360_pull_btn = (ImageButton) this.view.findViewById(R.id.cam_360_pull_btn);
        this.btn_next = (ImageButton) this.view.findViewById(R.id.btn_next);
        this.btn_exit = (ImageButton) this.view.findViewById(R.id.btn_exit);
        this.cam_360_pull_btn.setOnClickListener(this);
        this.btn_next.setOnClickListener(this);
        this.btn_exit.setOnClickListener(this);
        this.lo_cam_btn_part_1 = (LinearLayout) this.view.findViewById(R.id.lo_cam_btn_part_1);
        this.lo_cam_btn_part_2 = (LinearLayout) this.view.findViewById(R.id.lo_cam_btn_part_2);
        this.lo_cam_btn_part_3 = (LinearLayout) this.view.findViewById(R.id.lo_cam_btn_part_3);
        this.lo_360_main_btns = (LinearLayout) this.view.findViewById(R.id.lo_360_main_btns);
        this.lo_cam_btn_part_4 = (LinearLayout) this.view.findViewById(R.id.lo_cam_btn_part_4);
        this.currentPage = (byte) 0;
    }

    private void changeBtnPage() {
        byte b = this.currentPage;
        this.lastRecPage = b;
        if (b == 0) {
            this.currentPage = (byte) 1;
            setVisual(this.lo_cam_btn_part_1, false);
            setVisual(this.lo_cam_btn_part_2, true);
            setVisual(this.lo_cam_btn_part_3, true);
            return;
        }
        if (b == 1) {
            this.currentPage = (byte) 2;
            setVisual(this.lo_cam_btn_part_1, true);
            setVisual(this.lo_cam_btn_part_2, false);
            setVisual(this.lo_cam_btn_part_3, true);
            return;
        }
        if (b != 2) {
            return;
        }
        this.currentPage = (byte) 0;
        setVisual(this.lo_cam_btn_part_1, true);
        setVisual(this.lo_cam_btn_part_2, true);
        setVisual(this.lo_cam_btn_part_3, false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit /* 2131362022 */:
                External360CamCmds.getInstance().getCmds().exit();
                break;
            case R.id.btn_next /* 2131362034 */:
                if (this.isBtnsShow) {
                    changeBtnPage();
                    break;
                }
                break;
            case R.id.cam_360_pull_btn /* 2131362082 */:
                setImgBtnsGone();
                break;
            case R.id.four_region /* 2131362262 */:
                External360CamCmds.getInstance().getCmds().fourRegion();
                break;
            case R.id.front_all /* 2131362266 */:
                External360CamCmds.getInstance().getCmds().frontAll();
                break;
            case R.id.front_only /* 2131362272 */:
                External360CamCmds.getInstance().getCmds().frontOnly();
                break;
            case R.id.left_all /* 2131362726 */:
                External360CamCmds.getInstance().getCmds().leftAll();
                break;
            case R.id.left_only /* 2131362729 */:
                External360CamCmds.getInstance().getCmds().leftOnly();
                break;
            case R.id.left_right_front_all /* 2131362746 */:
                External360CamCmds.getInstance().getCmds().allFrontLeftRight();
                break;
            case R.id.left_right_rear_all /* 2131362747 */:
                External360CamCmds.getInstance().getCmds().allRearLeftRight();
                break;
            case R.id.rear_all /* 2131363044 */:
                External360CamCmds.getInstance().getCmds().rearAll();
                break;
            case R.id.rear_only /* 2131363056 */:
                External360CamCmds.getInstance().getCmds().rearOnly();
                break;
            case R.id.right_all /* 2131363154 */:
                External360CamCmds.getInstance().getCmds().rightAll();
                break;
            case R.id.right_only /* 2131363158 */:
                External360CamCmds.getInstance().getCmds().rightOnly();
                break;
        }
    }
}
