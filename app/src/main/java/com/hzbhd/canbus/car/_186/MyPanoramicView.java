package com.hzbhd.canbus.car._186;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;

/* loaded from: classes.dex */
public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
    private Button mBtnCamera;
    private Context mContext;
    private View[] mCurrentPage;
    private ImageButton mIbDown;
    boolean mIbDownStatus;
    private ImageButton mIbExit;
    private ImageButton mIbLeft;
    private ImageButton mIbLeftDown;
    boolean mIbLeftDownStatus;
    boolean mIbLeftStatus;
    private ImageButton mIbPa;
    private ImageButton mIbParaPark;
    private ImageButton mIbRight;
    private ImageButton mIbRightDown;
    boolean mIbRightDownStatus;
    boolean mIbRightStatus;
    private ImageButton mIbUp;
    private ImageButton mIbUpDown;
    boolean mIbUpStatus;
    private ImageButton mIbVertPark;
    private ImageView mIvCamera;
    int mIvCameraStatus;
    private View[] mPage1;
    private View[] mPage2;
    private View[] mPage3;
    private View[] mPage4;
    private View[] mPage5;
    private View[] mPage6;
    private View[][] mPageArrays;
    int mPageNumber;
    private LinearLayout mRlBottomBtn;
    private RelativeLayout mRlBtnBack;
    private RelativeLayout mRlBtnCancel;
    private RelativeLayout mRlBtnStart;
    private RelativeLayout mRlSixBtn;
    private SparseIntArray mTipsArray;
    private LinearLayout mTvParkAssistIntroduce;
    private TextView mTvParkAssistTips;
    int mTvTipsStatus;

    public MyPanoramicView(Context context) {
        super(context);
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_188_view, this);
        this.mRlBottomBtn = (LinearLayout) viewInflate.findViewById(R.id.rl_bottom_buttons);
        this.mIbPa = (ImageButton) viewInflate.findViewById(R.id.btn_pa);
        this.mRlBtnCancel = (RelativeLayout) viewInflate.findViewById(R.id.rl_btn_cancel);
        this.mRlBtnStart = (RelativeLayout) viewInflate.findViewById(R.id.rl_btn_start);
        this.mIbParaPark = (ImageButton) viewInflate.findViewById(R.id.btn_para_park);
        this.mIbVertPark = (ImageButton) viewInflate.findViewById(R.id.btn_vert_park);
        this.mIbUpDown = (ImageButton) viewInflate.findViewById(R.id.btn_up_down);
        this.mRlBtnBack = (RelativeLayout) viewInflate.findViewById(R.id.rl_btn_back);
        this.mIbPa.setOnClickListener(this);
        this.mRlBtnCancel.setOnClickListener(this);
        this.mRlBtnStart.setOnClickListener(this);
        this.mIbParaPark.setOnClickListener(this);
        this.mIbVertPark.setOnClickListener(this);
        this.mIbUpDown.setOnClickListener(this);
        this.mRlBtnBack.setOnClickListener(this);
        this.mIbLeftDown = (ImageButton) viewInflate.findViewById(R.id.ib_left_down);
        this.mIbRightDown = (ImageButton) viewInflate.findViewById(R.id.ib_right_down);
        this.mIbRight = (ImageButton) viewInflate.findViewById(R.id.ib_right);
        this.mIbLeft = (ImageButton) viewInflate.findViewById(R.id.ib_left);
        this.mIbDown = (ImageButton) viewInflate.findViewById(R.id.ib_down);
        this.mIbUp = (ImageButton) viewInflate.findViewById(R.id.ib_up);
        this.mIbLeftDown.setOnClickListener(this);
        this.mIbRightDown.setOnClickListener(this);
        this.mIbRight.setOnClickListener(this);
        this.mIbLeft.setOnClickListener(this);
        this.mIbDown.setOnClickListener(this);
        this.mIbUp.setOnClickListener(this);
        this.mRlSixBtn = (RelativeLayout) viewInflate.findViewById(R.id.layout_six_buttons);
        this.mTvParkAssistTips = (TextView) viewInflate.findViewById(R.id.tv_park_assist_tips);
        this.mTvParkAssistIntroduce = (LinearLayout) viewInflate.findViewById(R.id.ll_park_assist_introduce);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_camera);
        this.mIvCamera = imageView;
        imageView.setOnClickListener(this);
        View[] viewArr = {this.mIbPa};
        this.mPage1 = viewArr;
        RelativeLayout relativeLayout = this.mRlBtnCancel;
        View[] viewArr2 = {relativeLayout, this.mIbVertPark};
        this.mPage2 = viewArr2;
        RelativeLayout relativeLayout2 = this.mRlBtnStart;
        View[] viewArr3 = {relativeLayout, relativeLayout2, this.mIbParaPark, this.mIbUpDown};
        this.mPage3 = viewArr3;
        View[] viewArr4 = {relativeLayout, relativeLayout2, this.mRlBtnBack, this.mRlSixBtn};
        this.mPage4 = viewArr4;
        View[] viewArr5 = {relativeLayout};
        this.mPage5 = viewArr5;
        View[] viewArr6 = {this.mTvParkAssistIntroduce};
        this.mPage6 = viewArr6;
        this.mPageArrays = new View[][]{viewArr, viewArr2, viewArr3, viewArr4, viewArr5, viewArr6};
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.ib_panoramic_exit);
        this.mIbExit = imageButton;
        imageButton.setOnClickListener(this);
        Button button = (Button) viewInflate.findViewById(R.id.ib_panoramic_camera);
        this.mBtnCamera = button;
        button.setOnClickListener(this);
        initHashMap();
    }

    private void initHashMap() {
        SparseIntArray sparseIntArray = new SparseIntArray(38);
        this.mTipsArray = sparseIntArray;
        sparseIntArray.put(1, R.string.nissan_infor_eiisbm);
        this.mTipsArray.append(2, R.string.nissan_infor_pana);
        this.mTipsArray.append(3, R.string.nissan_infor_utstcs);
        this.mTipsArray.append(4, R.string.nissan_infor_sfg);
        this.mTipsArray.append(5, R.string.nissan_infor_sfdf);
        this.mTipsArray.append(6, R.string.nissan_infor_snteps);
        this.mTipsArray.append(7, R.string.nissan_infor_pudtspp);
        this.mTipsArray.append(8, R.string.nissan_infor_app);
        this.mTipsArray.append(9, R.string.nissan_infor_pac);
        this.mTipsArray.append(10, R.string.nissan_infor_db);
        this.mTipsArray.append(11, R.string.nissan_infor_sasrg);
        this.mTipsArray.append(12, R.string.nissan_infor_df);
        this.mTipsArray.append(13, R.string.nissan_infor_sasfg);
        this.mTipsArray.append(14, R.string.nissan_infor_pafd);
        this.mTipsArray.append(66, R.string.nissan_infor_cs);
        this.mTipsArray.append(67, R.string.nissan_infor_sbpstspa);
        this.mTipsArray.append(69, R.string.nissan_infor_rs);
        this.mTipsArray.append(76, R.string.nissan_infor_sr);
        this.mTipsArray.append(77, R.string.nissan_infor_dfwc);
        this.mTipsArray.append(78, R.string.nissan_infor_dbwc);
        this.mTipsArray.append(79, R.string.nissan_infor_co);
        this.mTipsArray.append(18, R.string.nissan_infor_csw);
        this.mTipsArray.append(19, R.string.nissan_infor_rt);
        this.mTipsArray.append(20, R.string.nissan_infor_vmbo);
        this.mTipsArray.append(21, R.string.nissan_infor_cdts);
        this.mTipsArray.append(22, R.string.nissan_infor_sf);
        this.mTipsArray.append(23, R.string.nissan_infor_paft);
        this.mTipsArray.append(24, R.string.nissan_infor_os);
        this.mTipsArray.append(25, R.string.nissan_infor_si);
        this.mTipsArray.append(26, R.string.nissan_infor_ton);
        this.mTipsArray.append(27, R.string.nissan_infor_va);
        this.mTipsArray.append(28, R.string.nissan_infor_dobo);
        this.mTipsArray.append(29, R.string.nissan_infor_tot);
        this.mTipsArray.append(34, R.string.nissan_infor_engna);
        this.mTipsArray.append(35, R.string.nissan_infor_espna);
        this.mTipsArray.append(36, R.string.nissan_infor_fgoops);
        this.mTipsArray.append(37, R.string.nissan_infor_rnp);
        this.mTipsArray.append(39, R.string.nissan_infor_s);
        this.mTipsArray.append(40, R.string.nissan_infor_srg);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pa /* 2131362038 */:
                sendCameraButtonCommand(65);
                break;
            case R.id.btn_para_park /* 2131362040 */:
                sendCameraButtonCommand(67);
                break;
            case R.id.btn_up_down /* 2131362062 */:
                sendCameraButtonCommand(71);
                break;
            case R.id.btn_vert_park /* 2131362063 */:
                sendCameraButtonCommand(66);
                break;
            case R.id.ib_down /* 2131362387 */:
                sendCameraButtonCommand(73);
                break;
            case R.id.ib_left /* 2131362402 */:
                sendCameraButtonCommand(74);
                break;
            case R.id.ib_left_down /* 2131362403 */:
                sendCameraButtonCommand(79);
                break;
            case R.id.ib_panoramic_camera /* 2131362409 */:
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
                break;
            case R.id.ib_right /* 2131362420 */:
                sendCameraButtonCommand(75);
                break;
            case R.id.ib_right_down /* 2131362421 */:
                sendCameraButtonCommand(78);
                break;
            case R.id.ib_up /* 2131362425 */:
                sendCameraButtonCommand(72);
                break;
            case R.id.rl_btn_back /* 2131363184 */:
                sendCameraButtonCommand(70);
                break;
            case R.id.rl_btn_cancel /* 2131363185 */:
                sendCameraButtonCommand(69);
                break;
            case R.id.rl_btn_start /* 2131363186 */:
                sendCameraButtonCommand(68);
                break;
        }
    }

    private void sendCameraButtonCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i});
    }

    private void setCameraBtnStatus(View view, boolean z) {
        view.setAlpha(z ? 1.0f : 0.5f);
        view.setEnabled(z);
    }

    private void setParkAssistTips(Context context, int i) {
        if (i == 0) {
            this.mTvParkAssistTips.setVisibility(4);
        } else {
            this.mTvParkAssistTips.setVisibility(0);
            this.mTvParkAssistTips.setText(context.getString(this.mTipsArray.get(i, R.string.null_value)));
        }
    }

    private void setCurrentPage(int i) {
        if (i == 255) {
            i = this.mPageArrays.length;
        }
        if (i < 0 || i > this.mPageArrays.length) {
            return;
        }
        try {
            for (View view : this.mCurrentPage) {
                view.setVisibility(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 0) {
            this.mRlBottomBtn.setVisibility(4);
            return;
        }
        this.mRlBottomBtn.setVisibility(0);
        View[] viewArr = this.mPageArrays[i - 1];
        this.mCurrentPage = viewArr;
        for (View view2 : viewArr) {
            view2.setVisibility(0);
        }
    }

    void updatePanoramicView(Context context) {
        setCameraBtnStatus(this.mIbLeftDown, this.mIbLeftDownStatus);
        setCameraBtnStatus(this.mIbRightDown, this.mIbRightDownStatus);
        setCameraBtnStatus(this.mIbRight, this.mIbRightStatus);
        setCameraBtnStatus(this.mIbLeft, this.mIbLeftStatus);
        setCameraBtnStatus(this.mIbDown, this.mIbDownStatus);
        setCameraBtnStatus(this.mIbUp, this.mIbUpStatus);
        int i = this.mIvCameraStatus;
        if (i == 128) {
            this.mIvCamera.setImageDrawable(this.mContext.getDrawable(R.drawable.nissan_panoramic_rear_camera));
        } else if (i == 0) {
            this.mIvCamera.setImageDrawable(this.mContext.getDrawable(R.drawable.nissan_panoramic_front_camera));
        }
        this.mIvCamera.setVisibility(0);
        setParkAssistTips(context, this.mTvTipsStatus);
        setCurrentPage(this.mPageNumber);
    }
}
