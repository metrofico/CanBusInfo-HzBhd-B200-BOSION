package com.hzbhd.canbus.car_cus._291;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.MainMediaAdapter;
import com.hzbhd.canbus.car_cus._283.bean.MainChooseBean;
import com.hzbhd.canbus.car_cus._283.bean.MainMediaBean;
import com.hzbhd.canbus.car_cus._291.view.DataAlarmView;
import com.hzbhd.canbus.car_cus._291.view.DataView;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.keydispatcher.constants.KeyDispatcherConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class MainActivity extends AbstractBaseActivity implements View.OnClickListener {
    public static final String BUNDLE_DEZHONG_WHAT = "bundle_dezhong_what";
    public static final int BUNDLE_DEZHONG_WHAT_DataAlarmView = 2;
    public static final int BUNDLE_DEZHONG_WHAT_DataView = 1;
    private DataAlarmView dataAlarmView;
    private DataView dataView;
    private ImageView leftImage;
    private LinearLayout linearMainChoose;
    private LinearLayout linearMainMedia;
    private LinearLayout linearMainSetting;
    private int mWhat;
    private ImageView rightImage;
    private TextView titleText;
    private int showView = 0;
    private int[] titles = {R.string._291_titls_1, R.string._291_title_2};
    private int[] titleMedia = {R.string._283_media_titls_1, R.string._283_media_titls_2, R.string._283_media_titls_3, R.string._283_media_titls_4};
    private int[] imageMedia = {R.drawable.dz_tpms_icon_bt, R.drawable.dz_tpms_icon_music, R.drawable.dz_tpms_icon_video, R.drawable.dz_tpms_icon_radio};
    private List<MainChooseBean> listChoose = new ArrayList();
    private List<MainMediaBean> listMedia = new ArrayList();
    private List<View> listViews = new ArrayList();
    private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._291_activity_main);
        initView();
        this.showView = 0;
        showThisView();
    }

    private void initView() {
        this.leftImage = (ImageView) findViewById(R.id.leftImage);
        this.rightImage = (ImageView) findViewById(R.id.rightImage);
        this.titleText = (TextView) findViewById(R.id.titleText);
        this.dataAlarmView = (DataAlarmView) findViewById(R.id.v_dataAlarmView);
        this.dataView = (DataView) findViewById(R.id.v_dataView);
        this.linearMainChoose = (LinearLayout) findViewById(R.id.linearMainChoose);
        this.linearMainMedia = (LinearLayout) findViewById(R.id.linearMainMedia);
        this.linearMainSetting = (LinearLayout) findViewById(R.id.linearMainSetting);
        this.leftImage.setOnClickListener(this);
        this.rightImage.setOnClickListener(this);
        this.linearMainChoose.setOnClickListener(this);
        this.linearMainMedia.setOnClickListener(this);
        this.linearMainSetting.setOnClickListener(this);
        this.listViews.add(this.dataView);
        this.threadExecutor.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._291.MainActivity.1
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.listViews.add(MainActivity.this.dataAlarmView);
            }
        });
        this.threadExecutor.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._291.MainActivity.2
            @Override // java.lang.Runnable
            public void run() {
                List list = MainActivity.this.listChoose;
                MainActivity mainActivity = MainActivity.this;
                list.add(new MainChooseBean(mainActivity.getString(mainActivity.titles[0])));
                List list2 = MainActivity.this.listChoose;
                MainActivity mainActivity2 = MainActivity.this;
                list2.add(new MainChooseBean(mainActivity2.getString(mainActivity2.titles[1])));
            }
        });
        this.threadExecutor.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._291.MainActivity.3
            @Override // java.lang.Runnable
            public void run() {
                List list = MainActivity.this.listMedia;
                int i = MainActivity.this.imageMedia[0];
                MainActivity mainActivity = MainActivity.this;
                list.add(new MainMediaBean(i, mainActivity.getString(mainActivity.titleMedia[0]), HzbhdComponentName.A2dpActivity));
                List list2 = MainActivity.this.listMedia;
                int i2 = MainActivity.this.imageMedia[1];
                MainActivity mainActivity2 = MainActivity.this;
                list2.add(new MainMediaBean(i2, mainActivity2.getString(mainActivity2.titleMedia[1]), HzbhdComponentName.MediaMusic));
                List list3 = MainActivity.this.listMedia;
                int i3 = MainActivity.this.imageMedia[2];
                MainActivity mainActivity3 = MainActivity.this;
                list3.add(new MainMediaBean(i3, mainActivity3.getString(mainActivity3.titleMedia[2]), HzbhdComponentName.MediaVideo));
                List list4 = MainActivity.this.listMedia;
                int i4 = MainActivity.this.imageMedia[3];
                MainActivity mainActivity4 = MainActivity.this;
                list4.add(new MainMediaBean(i4, mainActivity4.getString(mainActivity4.titleMedia[3]), HzbhdComponentName.RadioActivity));
            }
        });
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        LogUtil.showLog("291 refreshUi");
        if (bundle == null) {
            return;
        }
        int i = bundle.getInt("bundle_dezhong_what");
        this.mWhat = i;
        if (i == 1) {
            this.dataView.refreshUi();
        } else {
            if (i != 2) {
                return;
            }
            this.dataAlarmView.refreshUi();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.leftImage) {
            int i = this.showView;
            if (i > 0) {
                this.showView = i - 1;
            } else {
                this.showView = 1;
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
                DialogUtils.mainChoose(this, this.linearMainChoose, this.listChoose, new MainChooseAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._291.MainActivity$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter.OnItemClickListener
                    public final void click(int i3) {
                        this.f$0.m1126lambda$onClick$0$comhzbhdcanbuscar_cus_291MainActivity(i3);
                    }
                });
                break;
            case R.id.linearMainMedia /* 2131362763 */:
                DialogUtils.mainMedia(this, this.linearMainMedia, this.listMedia, new MainMediaAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._291.MainActivity$$ExternalSyntheticLambda1
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

    /* renamed from: lambda$onClick$0$com-hzbhd-canbus-car_cus-_291-MainActivity, reason: not valid java name */
    /* synthetic */ void m1126lambda$onClick$0$comhzbhdcanbuscar_cus_291MainActivity(int i) {
        if (i == 0) {
            this.showView = 0;
            showThisView();
        } else if (i == 1) {
            this.showView = 1;
            showThisView();
        } else {
            if (i != 2) {
                return;
            }
            this.showView = 2;
            showThisView();
        }
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
