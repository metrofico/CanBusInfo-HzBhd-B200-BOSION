package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.car._304.MsgMgr;
import com.hzbhd.canbus.car_cus._304.MyLinearLayoutManager;
import com.hzbhd.canbus.car_cus._304.adapter.AdapterCheckedCarInfo;
import com.hzbhd.canbus.car_cus._304.bean.CheckedCarInfoBean;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class VehicleDiagnosisView extends RelativeLayout implements View.OnClickListener {
    private static final int MSG_CIRCLE_END = 2;
    private static final int MSG_CIRCLE_RUNNING = 1;
    private static final int MSG_CIRCLE_START = 0;
    private final int CHECK_RING_MAX_COUNT;
    private final int SCHEDULED_DELAY;
    private String SHARE_304_LAST_CHECK_TIME;
    private final String TAG;
    private List<CheckedCarInfoBean> checkBeanList;
    private AdapterCheckedCarInfo mAdapterCheckedCarInfo;
    private AnimationDrawable mAnimDrawable;
    private int mCheckItemCount;
    private int mCheckItemSum;
    private DecimalFormat mDecimalFormat00;
    private Handler mHandler;
    private boolean mIsCheckFinish;
    private ImageView mIvCircle;
    private ImageView mIvCursor;
    private LinearLayout mLlCheck;
    private LinearLayout mLlResult;
    private LinearLayout mLlWarn;
    private RecyclerView mRcvCheck;
    private ScheduledExecutorService mScheduledExecutorService;
    private TextView mTvCheckTime;
    private TextView mTvCircle;
    private TextView mTvWarnCount;

    static /* synthetic */ int access$408(VehicleDiagnosisView vehicleDiagnosisView) {
        int i = vehicleDiagnosisView.mCheckItemCount;
        vehicleDiagnosisView.mCheckItemCount = i + 1;
        return i;
    }

    /* renamed from: com.hzbhd.canbus.car_cus._304.view.VehicleDiagnosisView$1, reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                VehicleDiagnosisView.this.mLlCheck.setVisibility(0);
                VehicleDiagnosisView.this.mLlResult.setVisibility(4);
                VehicleDiagnosisView.this.mLlWarn.setVisibility(0);
                VehicleDiagnosisView.this.mIsCheckFinish = false;
                VehicleDiagnosisView.this.mCheckItemCount = 0;
                VehicleDiagnosisView.this.mTvCircle.setText(R.string._304_title_checking);
                VehicleDiagnosisView.this.mTvCircle.setTextColor(ContextCompat.getColor(VehicleDiagnosisView.this.getContext(), R.color._304_text_color_yellow));
                VehicleDiagnosisView.this.mScheduledExecutorService = Executors.newScheduledThreadPool(2);
                VehicleDiagnosisView.this.mScheduledExecutorService.scheduleWithFixedDelay(new Runnable() { // from class: com.hzbhd.canbus.car_cus._304.view.VehicleDiagnosisView$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m1139x3ee39d3b();
                    }
                }, 1L, 1000L, TimeUnit.MILLISECONDS);
                VehicleDiagnosisView.this.mRcvCheck.scrollToPosition(0);
                return;
            }
            if (i == 1) {
                VehicleDiagnosisView.access$408(VehicleDiagnosisView.this);
                if (VehicleDiagnosisView.this.mCheckItemCount > VehicleDiagnosisView.this.mCheckItemSum) {
                    VehicleDiagnosisView.this.mHandler.sendEmptyMessage(2);
                    VehicleDiagnosisView.this.mScheduledExecutorService.shutdownNow();
                } else {
                    VehicleDiagnosisView.this.mIvCircle.setImageLevel((VehicleDiagnosisView.this.mCheckItemCount * 38) / VehicleDiagnosisView.this.mCheckItemSum);
                }
                VehicleDiagnosisView vehicleDiagnosisView = VehicleDiagnosisView.this;
                vehicleDiagnosisView.selectPosition(vehicleDiagnosisView.mCheckItemCount);
                return;
            }
            if (i != 2) {
                return;
            }
            VehicleDiagnosisView.this.mIsCheckFinish = true;
            int intValue = SharePreUtil.getIntValue(VehicleDiagnosisView.this.getContext(), MsgMgr.SHARE_304_WARN_COUNT, 0);
            if (intValue > 0) {
                VehicleDiagnosisView.this.mTvCircle.setText(R.string.ac_diagnostic_info_item_6);
                VehicleDiagnosisView.this.mTvCircle.setTextColor(ContextCompat.getColor(VehicleDiagnosisView.this.getContext(), R.color._304_text_color_yellow));
            } else {
                VehicleDiagnosisView.this.mIvCircle.setImageLevel(0);
                VehicleDiagnosisView.this.mTvCircle.setText(R.string._304_title_health);
                VehicleDiagnosisView.this.mTvCircle.setTextColor(ContextCompat.getColor(VehicleDiagnosisView.this.getContext(), R.color._304_text_color_green));
            }
            VehicleDiagnosisView.this.stopAnimation();
            VehicleDiagnosisView.this.mTvWarnCount.setText(Integer.toString(intValue));
            VehicleDiagnosisView.this.mTvCheckTime.setText(VehicleDiagnosisView.this.getTime());
            VehicleDiagnosisView.this.mLlCheck.setVisibility(4);
            VehicleDiagnosisView.this.mLlResult.setVisibility(0);
        }

        /* renamed from: lambda$handleMessage$0$com-hzbhd-canbus-car_cus-_304-view-VehicleDiagnosisView$1, reason: not valid java name */
        /* synthetic */ void m1139x3ee39d3b() {
            VehicleDiagnosisView.this.mHandler.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTime() {
        String str = Calendar.getInstance().get(1) + "-" + this.mDecimalFormat00.format(r0.get(2)) + "-" + this.mDecimalFormat00.format(r0.get(5)) + " " + this.mDecimalFormat00.format(r0.get(11)) + ":" + this.mDecimalFormat00.format(r0.get(12));
        SharePreUtil.setStringValue(getContext(), this.SHARE_304_LAST_CHECK_TIME, str);
        return str;
    }

    public VehicleDiagnosisView(Context context) {
        super(context);
        this.TAG = "_304_VehicleDiagnosisView";
        this.CHECK_RING_MAX_COUNT = 38;
        this.SCHEDULED_DELAY = 1000;
        this.mIsCheckFinish = true;
        this.mHandler = new AnonymousClass1(Looper.getMainLooper());
        this.SHARE_304_LAST_CHECK_TIME = "share_304_last_check_time";
        this.mDecimalFormat00 = new DecimalFormat("00");
        LayoutInflater.from(context).inflate(R.layout._304_activity_checked_carinfo, this);
        findViews();
        initDatas();
        initViews(context);
    }

    private void findViews() {
        this.mTvCircle = (TextView) findViewById(R.id.tv_circle);
        this.mTvWarnCount = (TextView) findViewById(R.id.tv_warn_count);
        this.mTvCheckTime = (TextView) findViewById(R.id.tv_check_time);
        this.mIvCursor = (ImageView) findViewById(R.id.iv_cursor);
        this.mIvCircle = (ImageView) findViewById(R.id.iv_circle);
        this.mLlCheck = (LinearLayout) findViewById(R.id.ll_check);
        this.mLlResult = (LinearLayout) findViewById(R.id.ll_result);
        this.mLlWarn = (LinearLayout) findViewById(R.id.ll_warn);
        this.mRcvCheck = (RecyclerView) findViewById(R.id.rcv_check);
    }

    private void initDatas() {
        ArrayList arrayList = new ArrayList();
        this.checkBeanList = arrayList;
        arrayList.add(new CheckedCarInfoBean(" "));
        this.checkBeanList.add(new CheckedCarInfoBean("整车系统"));
        this.checkBeanList.add(new CheckedCarInfoBean("电驱动系统"));
        this.checkBeanList.add(new CheckedCarInfoBean("PDC系统"));
        this.checkBeanList.add(new CheckedCarInfoBean("安全气囊"));
        this.checkBeanList.add(new CheckedCarInfoBean("真空度"));
        this.checkBeanList.add(new CheckedCarInfoBean("制动液"));
        this.checkBeanList.add(new CheckedCarInfoBean("电机"));
        this.checkBeanList.add(new CheckedCarInfoBean("电池"));
        this.checkBeanList.add(new CheckedCarInfoBean("充电机"));
        this.checkBeanList.add(new CheckedCarInfoBean("逆变器"));
        this.checkBeanList.add(new CheckedCarInfoBean("ESC"));
        this.checkBeanList.add(new CheckedCarInfoBean("EPS"));
        this.checkBeanList.add(new CheckedCarInfoBean("压缩机"));
        this.checkBeanList.add(new CheckedCarInfoBean("转向灯"));
        this.checkBeanList.add(new CheckedCarInfoBean("制动灯"));
        this.checkBeanList.add(new CheckedCarInfoBean("雷达系统"));
        this.checkBeanList.add(new CheckedCarInfoBean("车轮胎压"));
        this.checkBeanList.add(new CheckedCarInfoBean("全景监控系统"));
        this.checkBeanList.add(new CheckedCarInfoBean(" "));
        this.mCheckItemSum = this.checkBeanList.size() - 2;
    }

    private void initViews(Context context) {
        this.mRcvCheck.setLayoutManager(new MyLinearLayoutManager(context));
        RecyclerView recyclerView = this.mRcvCheck;
        AdapterCheckedCarInfo adapterCheckedCarInfo = new AdapterCheckedCarInfo(context);
        this.mAdapterCheckedCarInfo = adapterCheckedCarInfo;
        recyclerView.setAdapter(adapterCheckedCarInfo);
        this.mRcvCheck.addOnItemTouchListener(new RecyclerViewDisabler(this, null));
        this.mAdapterCheckedCarInfo.addData(this.checkBeanList);
        this.mAnimDrawable = (AnimationDrawable) context.getDrawable(R.drawable._304_animation_list_scan);
        findViewById(R.id.btn_checked).setOnClickListener(this);
        findViewById(R.id.btn_check_details).setOnClickListener(this);
        this.mTvWarnCount.setText("--");
        this.mTvCheckTime.setText(SharePreUtil.getStringValue(getContext(), this.SHARE_304_LAST_CHECK_TIME, ""));
        this.mLlWarn.setVisibility(4);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_details /* 2131362011 */:
                Intent intent = new Intent(getContext(), (Class<?>) WarningActivity.class);
                intent.setFlags(268435456);
                getContext().startActivity(intent);
                break;
            case R.id.btn_checked /* 2131362012 */:
                if (this.mIsCheckFinish) {
                    startAnimation();
                    this.mHandler.sendEmptyMessage(0);
                    break;
                }
                break;
        }
    }

    private void startAnimation() {
        AnimationDrawable animationDrawable = this.mAnimDrawable;
        if (animationDrawable != null) {
            this.mIvCursor.setImageDrawable(animationDrawable);
            this.mAnimDrawable.stop();
            this.mAnimDrawable.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAnimation() {
        AnimationDrawable animationDrawable = this.mAnimDrawable;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        this.mIvCursor.setImageResource(R.drawable._304_examined_cursor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectPosition(int i) {
        if (i <= 0 || i >= this.mAdapterCheckedCarInfo.getItemCount() - 1) {
            return;
        }
        this.mAdapterCheckedCarInfo.setSelectedPosition(i);
        this.mRcvCheck.smoothScrollToPosition(i + 1);
        this.mAdapterCheckedCarInfo.notifyDataSetChanged();
    }

    private class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {
        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }

        private RecyclerViewDisabler() {
        }

        /* synthetic */ RecyclerViewDisabler(VehicleDiagnosisView vehicleDiagnosisView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
