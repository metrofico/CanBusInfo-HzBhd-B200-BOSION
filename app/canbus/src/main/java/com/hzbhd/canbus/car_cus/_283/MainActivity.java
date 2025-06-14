package com.hzbhd.canbus.car_cus._283;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingActivity;
import com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.MainMediaAdapter;
import com.hzbhd.canbus.car_cus._283.bean.MainChooseBean;
import com.hzbhd.canbus.car_cus._283.bean.MainMediaBean;
import com.hzbhd.canbus.car_cus._283.view.DataView;
import com.hzbhd.canbus.car_cus._283.view.DrivingPatternView;
import com.hzbhd.canbus.car_cus._283.view.HybridDataView1;
import com.hzbhd.canbus.car_cus._283.view.HybridDataView2;
import com.hzbhd.canbus.car_cus._283.view.HybridModeView;
import com.hzbhd.canbus.car_cus._283.view.TmpsView;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.keydispatcher.constants.KeyDispatcherConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MainActivity extends AbstractBaseActivity implements View.OnClickListener {
    public static final String BUNDLE_DEZHONG_WHAT = "bundle_dezhong_what";
    public static final int BUNDLE_DEZHONG_WHAT_DrivingPattern = 4;
    public static final int BUNDLE_DEZHONG_WHAT_HYBRID_DATA_1 = 7;
    public static final int BUNDLE_DEZHONG_WHAT_HYBRID_DATA_2 = 8;
    public static final int BUNDLE_DEZHONG_WHAT_HYBRID_MODE = 9;
    public static final int BUNDLE_DEZHONG_WHAT_Launch = 1;
    public static final int BUNDLE_DEZHONG_WHAT_LongTime = 2;
    public static final int BUNDLE_DEZHONG_WHAT_Refuel = 3;
    public static final int BUNDLE_DEZHONG_WHAT_TMPS = 5;
    public static final int BUNDLE_DEZHONG_WHAT_TMPS_Data = 6;
    public static final int BUNDLE_DEZHONG_WHAT_personal = 10;
    private DataView dataView_Launch;
    private DataView dataView_LongTime;
    private DataView dataView_Refuel;
    private DrivingPatternView drivingPatternView;
    private HybridDataView1 hybridDataView1;
    private HybridDataView2 hybridDataView2;
    private HybridModeView hybridModeView;
    private ImageView leftImage;
    private LinearLayout linearMainChoose;
    private LinearLayout linearMainMedia;
    private LinearLayout linearMainSetting;
    private int mWhat;
    private ImageView rightImage;
    private TextView titleText;
    private TmpsView tmpsView;
    private int showView = 0;
    private int[] titles = {R.string._283_titls_1, R.string._283_title_4, R.string._283_title_5, R.string._283_title_2, R.string._283_title_3};
    private int[] titlesHybrid = {R.string._283_title_6, R.string._283_title_7, R.string._283_title_8, R.string._283_title_3};
    private int[] titleMedia = {R.string._283_media_titls_1, R.string._283_media_titls_2, R.string._283_media_titls_3, R.string._283_media_titls_4};
    private int[] imageMedia = {R.drawable.dz_tpms_icon_bt, R.drawable.dz_tpms_icon_music, R.drawable.dz_tpms_icon_video, R.drawable.dz_tpms_icon_radio};
    private List<MainChooseBean> listChoose = new ArrayList();
    private List<MainMediaBean> listMedia = new ArrayList();
    private List<View> listViews = new ArrayList();

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._283_activity_main);
        initView();
        this.showView = 0;
        showThisView();
        intentStartView(getIntent());
        MessageSender.getMsg(HotKeyConstant.K_SLEEP);
        MessageSender.getMsg(70);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        intentStartView(intent);
    }

    private void intentStartView(Intent intent) {
        if (TextUtils.isEmpty(intent.getStringExtra("type")) || !intent.getStringExtra("type").equals("4")) {
            return;
        }
        this.showView = 3;
        showThisView();
    }

    private void initView() {
        this.leftImage = (ImageView) findViewById(R.id.leftImage);
        this.rightImage = (ImageView) findViewById(R.id.rightImage);
        this.titleText = (TextView) findViewById(R.id.titleText);
        this.dataView_Launch = (DataView) findViewById(R.id.v_dataView1);
        this.dataView_Refuel = (DataView) findViewById(R.id.v_dataView2);
        this.dataView_LongTime = (DataView) findViewById(R.id.v_dataView3);
        this.drivingPatternView = (DrivingPatternView) findViewById(R.id.v_drivingPattern);
        this.hybridDataView1 = (HybridDataView1) findViewById(R.id.hybridDataView1);
        this.hybridDataView2 = (HybridDataView2) findViewById(R.id.hybridDataView2);
        this.hybridModeView = (HybridModeView) findViewById(R.id.hybridModeView);
        this.tmpsView = (TmpsView) findViewById(R.id.v_tmpsView);
        this.linearMainChoose = (LinearLayout) findViewById(R.id.linearMainChoose);
        this.linearMainMedia = (LinearLayout) findViewById(R.id.linearMainMedia);
        this.linearMainSetting = (LinearLayout) findViewById(R.id.linearMainSetting);
        this.leftImage.setOnClickListener(this);
        this.rightImage.setOnClickListener(this);
        this.linearMainChoose.setOnClickListener(this);
        this.linearMainMedia.setOnClickListener(this);
        this.linearMainSetting.setOnClickListener(this);
        if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
            this.titles = this.titlesHybrid;
            this.listViews.add(this.hybridDataView1);
        } else {
            this.listViews.add(this.dataView_Launch);
        }
        if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
            this.listViews.add(this.hybridDataView2);
            this.listViews.add(this.hybridModeView);
        } else {
            this.listViews.add(this.dataView_Refuel);
            this.listViews.add(this.dataView_LongTime);
            this.listViews.add(this.drivingPatternView);
        }
        this.listViews.add(this.tmpsView);
        this.listChoose.add(new MainChooseBean(getString(this.titles[0])));
        if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
            this.listChoose.add(new MainChooseBean(getString(this.titles[1])));
            this.listChoose.add(new MainChooseBean(getString(this.titles[2])));
        } else {
            this.listChoose.add(new MainChooseBean(getString(this.titles[3])));
            this.listChoose.add(new MainChooseBean(getString(this.titles[4])));
        }
        this.listMedia.add(new MainMediaBean(this.imageMedia[0], getString(this.titleMedia[0]), HzbhdComponentName.MediaMusic));
        this.listMedia.add(new MainMediaBean(this.imageMedia[1], getString(this.titleMedia[1]), HzbhdComponentName.MediaMusic));
        this.listMedia.add(new MainMediaBean(this.imageMedia[2], getString(this.titleMedia[2]), HzbhdComponentName.MediaMusic));
        this.listMedia.add(new MainMediaBean(this.imageMedia[3], getString(this.titleMedia[3]), HzbhdComponentName.MediaMusic));
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        LogUtil.showLog("283 refreshUi");
        if (bundle == null) {
        }
        int i = bundle.getInt("bundle_dezhong_what");
        this.mWhat = i;
        switch (i) {
            case 1:
                this.dataView_Launch.refreshUi(1);
                break;
            case 2:
                this.dataView_LongTime.refreshUi(2);
                break;
            case 3:
                this.dataView_Refuel.refreshUi(3);
                break;
            case 4:
                this.drivingPatternView.refreshUi();
                break;
            case 5:
                this.tmpsView.m1101lambda$new$0$comhzbhdcanbuscar_cus_283viewTmpsView();
                break;
            case 6:
                this.tmpsView.refreshUiData();
                break;
            case 7:
                this.hybridDataView1.refreshUi();
                break;
            case 8:
                this.hybridDataView2.refreshUi();
                break;
            case 9:
                this.hybridModeView.refreshUi();
                break;
        }
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.dataView_Launch.refreshUi(1);
        this.dataView_LongTime.refreshUi(2);
        this.dataView_Refuel.refreshUi(3);
        this.drivingPatternView.refreshUi();
        this.tmpsView.m1101lambda$new$0$comhzbhdcanbuscar_cus_283viewTmpsView();
        this.tmpsView.refreshUiData();
        this.hybridDataView1.refreshUi();
        this.hybridDataView2.refreshUi();
        this.hybridModeView.refreshUi();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.leftImage) {
            int i = this.showView;
            if (i > 0) {
                this.showView = i - 1;
            } else {
                this.showView = this.titles.length - 1;
            }
            showThisView();
        }
        if (id == R.id.rightImage) {
            int i2 = this.showView;
            if (i2 < this.titles.length - 1) {
                this.showView = i2 + 1;
            } else {
                this.showView = 0;
            }
            showThisView();
            return;
        }
        switch (id) {
            case R.id.linearMainChoose /* 2131362762 */:
                DialogUtils.mainChoose(this, this.linearMainChoose, this.listChoose, new MainChooseAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.MainActivity$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter.OnItemClickListener
                    public final void click(int i3) {
                        this.f$0.m1079lambda$onClick$0$comhzbhdcanbuscar_cus_283MainActivity(i3);
                    }
                });
                break;
            case R.id.linearMainMedia /* 2131362763 */:
                DialogUtils.mainMedia(this, this.linearMainMedia, this.listMedia, new MainMediaAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.MainActivity$$ExternalSyntheticLambda1
                    @Override // com.hzbhd.canbus.car_cus._283.adapter.MainMediaAdapter.OnItemClickListener
                    public final void click(int i3, ComponentName componentName) {
                        MainActivity.lambda$onClick$1(i3, componentName);
                    }
                });
                break;
            case R.id.linearMainSetting /* 2131362764 */:
                startActivity(new Intent(this, (Class<?>) MainSettingActivity.class));
                break;
        }
    }

    /* renamed from: lambda$onClick$0$com-hzbhd-canbus-car_cus-_283-MainActivity, reason: not valid java name */
    /* synthetic */ void m1079lambda$onClick$0$comhzbhdcanbuscar_cus_283MainActivity(int i) {
        if (i == 0) {
            this.showView = 0;
            showThisView();
            return;
        }
        if (i == 1) {
            if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
                this.showView = 1;
            } else {
                this.showView = 3;
            }
            showThisView();
            return;
        }
        if (i != 2) {
            return;
        }
        if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
            this.showView = 2;
        } else {
            this.showView = 4;
        }
        showThisView();
    }

    static /* synthetic */ void lambda$onClick$1(int i, ComponentName componentName) {
        if (i == 0) {
            SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.BTAUDIO));
            return;
        }
        if (i == 1) {
            SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.MUSIC));
        } else if (i == 2) {
            SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.VIDEO));
        } else if (i == 3) {
            SendKeyManager.getInstance().startSource(KeyDispatcherConstant.getAppId(SourceConstantsDef.SOURCE_ID.FM));
        }
    }

    private void showThisView() {
        int i = this.showView;
        if (i >= 0) {
            int[] iArr = this.titles;
            if (i < iArr.length) {
                this.titleText.setText(iArr[i]);
            }
        }
        for (int i2 = 0; i2 < this.listViews.size(); i2++) {
            if (this.showView == i2) {
                this.listViews.get(i2).setVisibility(0);
            } else {
                this.listViews.get(i2).setVisibility(8);
            }
        }
    }
}
