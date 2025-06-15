package com.hzbhd.canbus.car._25;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.util.CommUtil;

import java.util.Timer;
import java.util.TimerTask;


public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
    private static Timer mTimer;
    private static TimerTask mTimerTask;
    private Button mBtnCamera;
    private Context mContext;
    private View[] mCurrentPage;
    int mDifferent;
    private ImageButton mIbDown;
    boolean mIbDownStatus;
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
    private LinearLayout mTvParkAssistIntroduce;
    private TextView mTvParkAssistTips;
    String mTvTips;

    public MyPanoramicView(Context context, int i) {
        super(context);
        this.mContext = context;
        this.mDifferent = i;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_21_view, this);
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
        Button button = (Button) viewInflate.findViewById(R.id.ib_panoramic_camera);
        this.mBtnCamera = button;
        button.setOnClickListener(this);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {

        if (view.getId() == R.id.btn_pa) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 65, 1});
        } else if (view.getId() == R.id.btn_para_park) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 67, 1});
        } else if (view.getId() == R.id.btn_up_down) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 71, 1});
        } else if (view.getId() == R.id.btn_vert_park) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 66, 1});
        } else if (view.getId() == R.id.ib_down) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 73, 1});
        } else if (view.getId() == R.id.ib_left) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 74, 1});
        } else if (view.getId() == R.id.ib_left_down) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 79, 1});
        } else if (view.getId() == R.id.ib_panoramic_camera) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
        } else if (view.getId() == R.id.ib_right) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 75, 1});
        } else if (view.getId() == R.id.ib_right_down) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 78, 1});
        } else if (view.getId() == R.id.ib_up) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 72, 1});
        } else if (view.getId() == R.id.rl_btn_back) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 70, 1});
        } else if (view.getId() == R.id.rl_btn_cancel) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 68, 1});
        } else if (view.getId() == R.id.rl_btn_start) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 69, 1});
        }

    }

    private void setCameraBtnStatus(View view, boolean z) {
        view.setAlpha(z ? 1.0f : 0.5f);
        view.setEnabled(z);
    }

    private void setParkAssistTips(String str) {
        this.mTvParkAssistTips.setVisibility(CommUtil.getStrByResId(this.mContext, "null_value").equals(str) ? View.INVISIBLE : android.view.View.VISIBLE);
        this.mTvParkAssistTips.setText(str);
    }

    private void setCurrentPage(int i) {
        this.mRlBottomBtn.setVisibility(i == 0 ? View.INVISIBLE : View.VISIBLE);
        try {
            for (View view : this.mCurrentPage) {
                view.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 0) {
            return;
        }
        View[] viewArr = this.mPageArrays[i - 1];
        this.mCurrentPage = viewArr;
        for (View view2 : viewArr) {
            view2.setVisibility(View.VISIBLE);
        }
    }

    void updatePanoramicView() {
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
        this.mIvCamera.setVisibility(View.VISIBLE);
        setParkAssistTips(this.mTvTips);
        setCurrentPage(this.mPageNumber);
    }

    private void startTimer(TimerTask timerTask, long j) {
        if (timerTask == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimerTask = timerTask;
        mTimer.schedule(timerTask, j);
    }

    private void startTimer(TimerTask timerTask, long j, int i) {
        if (timerTask == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimerTask = timerTask;
        mTimer.schedule(timerTask, j, i);
    }

    private void stopTimer() {
        TimerTask timerTask = mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            mTimerTask = null;
        }
        Timer timer = mTimer;
        if (timer != null) {
            timer.cancel();
            mTimer = null;
        }
    }
}
