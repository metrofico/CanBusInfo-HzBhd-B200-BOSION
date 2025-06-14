package com.hzbhd.canbus.car_cus._436.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView;
import com.hzbhd.canbus.car_cus._436.view.modularView.DvrStateView;
import java.util.List;

/* loaded from: classes2.dex */
public class DvrActivity extends AppCompatActivity implements View.OnClickListener {
    private int Modular_TAG = 0;
    ScrollView control_view_scr;
    DvrFileView dvrFileView;
    DvrPlayView dvrPlayView;
    DvrSettingView dvrSettingView;
    DvrStateView dvrStateView;
    LinearLayout emergency_backup_lin;
    TextView file1_txt;
    TextView file2_txt;
    TextView file3_txt;
    TextView file4_txt;
    TextView file5_txt;
    TextView file6_txt;
    FrameLayout frmContent;
    LinearLayout modular1_lin;
    LinearLayout modular2_lin;
    LinearLayout modular3_lin;
    LinearLayout modular4_lin;
    Button play1;
    Button play2;
    Button play3;
    Button play4;
    Button play5;
    Button play6;
    ImageView show_hide_img;
    private Thread threadTimer;
    View viewMode1;
    View viewMode2;
    View viewMode3;
    View viewMode4;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) throws RemoteException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sokon_dvr);
        initView();
        startRequestTime();
    }

    private void startRequestTime() {
        if (this.threadTimer == null) {
            Thread thread = new Thread(new Runnable() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity.1
                @Override // java.lang.Runnable
                public void run() throws InterruptedException, RemoteException {
                    for (int i = 0; i < 60000; i++) {
                        try {
                            Thread.sleep(1000L);
                            DvrSender.send(new byte[]{35, 0});
                            DvrSender.send(new byte[]{35, 8});
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            DvrActivity.this.threadTimer = null;
                            return;
                        }
                    }
                }
            });
            this.threadTimer = thread;
            thread.start();
        }
    }

    private void stopRequestTime() {
        if (this.threadTimer == null) {
            Log.d("soface", "线程对象NULL");
        } else {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            Log.d("soface", "线程执行终止");
            this.threadTimer.interrupt();
        }
    }

    private void initView() throws RemoteException {
        DvrSender.send(new byte[]{35, 0});
        DvrSender.send(new byte[]{35, 8});
        this.frmContent = (FrameLayout) findViewById(R.id.dvr_fragment);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this);
        this.viewMode1 = layoutInflaterFrom.inflate(R.layout._436_dvr_mudolar1_view, (ViewGroup) null);
        this.viewMode2 = layoutInflaterFrom.inflate(R.layout._436_dvr_mudolar2_view, (ViewGroup) null);
        this.viewMode3 = layoutInflaterFrom.inflate(R.layout._436_dvr_mudolar3_view, (ViewGroup) null);
        this.viewMode4 = layoutInflaterFrom.inflate(R.layout._436_dvr_mudolar4_view, (ViewGroup) null);
        this.modular1_lin = (LinearLayout) findViewById(R.id.modular1_lin);
        this.modular2_lin = (LinearLayout) findViewById(R.id.modular2_lin);
        this.modular3_lin = (LinearLayout) findViewById(R.id.modular3_lin);
        this.modular4_lin = (LinearLayout) findViewById(R.id.modular4_lin);
        this.emergency_backup_lin = (LinearLayout) findViewById(R.id.emergency_backup_lin);
        this.modular1_lin.setOnClickListener(this);
        this.modular2_lin.setOnClickListener(this);
        this.modular3_lin.setOnClickListener(this);
        this.modular4_lin.setOnClickListener(this);
        this.emergency_backup_lin.setOnClickListener(this);
        this.dvrPlayView = (DvrPlayView) this.viewMode1.findViewById(R.id.play_view);
        this.dvrSettingView = (DvrSettingView) this.viewMode2.findViewById(R.id.setting_view);
        this.dvrStateView = (DvrStateView) this.viewMode3.findViewById(R.id.state_view);
        this.dvrFileView = (DvrFileView) this.viewMode4.findViewById(R.id.file_view);
        ImageView imageView = (ImageView) this.viewMode1.findViewById(R.id.show_hide_img);
        this.show_hide_img = imageView;
        imageView.setOnClickListener(this);
        this.control_view_scr = (ScrollView) findViewById(R.id.control_view_scr);
        this.file1_txt = (TextView) this.viewMode4.findViewById(R.id.file1_txt);
        this.file2_txt = (TextView) this.viewMode4.findViewById(R.id.file2_txt);
        this.file3_txt = (TextView) this.viewMode4.findViewById(R.id.file3_txt);
        this.file4_txt = (TextView) this.viewMode4.findViewById(R.id.file4_txt);
        this.file5_txt = (TextView) this.viewMode4.findViewById(R.id.file5_txt);
        this.file6_txt = (TextView) this.viewMode4.findViewById(R.id.file6_txt);
        this.play1 = (Button) this.viewMode4.findViewById(R.id.play1);
        this.play2 = (Button) this.viewMode4.findViewById(R.id.play2);
        this.play3 = (Button) this.viewMode4.findViewById(R.id.play3);
        this.play4 = (Button) this.viewMode4.findViewById(R.id.play4);
        this.play5 = (Button) this.viewMode4.findViewById(R.id.play5);
        this.play6 = (Button) this.viewMode4.findViewById(R.id.play6);
        this.file1_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file2_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file3_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file4_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file5_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file6_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.play1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.play2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.play3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.play4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.play5.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.play6.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.activity.DvrActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() throws RemoteException {
        super.onResume();
        selectModular1();
        initData();
        registerDataListener();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.dvrPlayView.stopVideoView();
    }

    @Override // android.app.Activity
    protected void onRestart() throws RemoteException {
        super.onRestart();
        this.dvrPlayView.stopVideoView();
        selectModular1();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unRegisterDataListener();
        this.dvrPlayView.stopVideoView();
        stopRequestTime();
    }

    private void initData() {
        this.dvrPlayView.refreshUi();
        this.dvrSettingView.refreshUi();
        this.dvrStateView.refreshUi();
        this.dvrFileView.refreshUi();
    }

    private void registerDataListener() {
        NotifyBuilding.getInstance().register(this.dvrPlayView);
        NotifyBuilding.getInstance().register(this.dvrSettingView);
        NotifyBuilding.getInstance().register(this.dvrStateView);
        NotifyBuilding.getInstance().register(this.dvrFileView);
    }

    private void unRegisterDataListener() {
        NotifyBuilding.getInstance().unRegister(this.dvrPlayView);
        NotifyBuilding.getInstance().unRegister(this.dvrSettingView);
        NotifyBuilding.getInstance().unRegister(this.dvrStateView);
        NotifyBuilding.getInstance().unRegister(this.dvrFileView);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws RemoteException {
        int id = view.getId();
        if (id == R.id.emergency_backup_lin) {
            DvrSender.send(new byte[]{64, 33});
        }
        if (id == R.id.show_hide_img) {
            showOrHideControlView();
            return;
        }
        switch (id) {
            case R.id.file1_txt /* 2131362230 */:
                this.file1_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                playVideo(GeneralDvrFile.getInstance().item1);
                break;
            case R.id.file2_txt /* 2131362231 */:
                this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file2_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                playVideo(GeneralDvrFile.getInstance().item2);
                break;
            case R.id.file3_txt /* 2131362232 */:
                this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file3_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                playVideo(GeneralDvrFile.getInstance().item3);
                break;
            case R.id.file4_txt /* 2131362233 */:
                this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file4_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                playVideo(GeneralDvrFile.getInstance().item4);
                break;
            case R.id.file5_txt /* 2131362234 */:
                this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file5_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
                playVideo(GeneralDvrFile.getInstance().item5);
                break;
            case R.id.file6_txt /* 2131362235 */:
                this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
                this.file6_txt.setBackgroundColor(Color.parseColor("#1BFAE5C6"));
                playVideo(GeneralDvrFile.getInstance().item6);
                break;
            default:
                switch (id) {
                    case R.id.modular1_lin /* 2131362868 */:
                        selectModular1();
                        break;
                    case R.id.modular2_lin /* 2131362869 */:
                        selectModular2();
                        break;
                    case R.id.modular3_lin /* 2131362870 */:
                        selectModular3();
                        break;
                    case R.id.modular4_lin /* 2131362871 */:
                        selectModular4();
                        break;
                    default:
                        switch (id) {
                            case R.id.play1 /* 2131362978 */:
                                selectModular1();
                                playVideo(GeneralDvrFile.getInstance().item1);
                                break;
                            case R.id.play2 /* 2131362979 */:
                                selectModular1();
                                playVideo(GeneralDvrFile.getInstance().item2);
                                break;
                            case R.id.play3 /* 2131362980 */:
                                selectModular1();
                                playVideo(GeneralDvrFile.getInstance().item3);
                                break;
                            case R.id.play4 /* 2131362981 */:
                                selectModular1();
                                playVideo(GeneralDvrFile.getInstance().item4);
                                break;
                            case R.id.play5 /* 2131362982 */:
                                selectModular1();
                                playVideo(GeneralDvrFile.getInstance().item5);
                                break;
                            case R.id.play6 /* 2131362983 */:
                                selectModular1();
                                playVideo(GeneralDvrFile.getInstance().item6);
                                break;
                        }
                }
        }
    }

    private void showOrHideControlView() {
        if (this.control_view_scr.getVisibility() == 0) {
            this.control_view_scr.setVisibility(8);
            this.show_hide_img.setBackgroundResource(R.drawable._436_button_donw_up_show);
        } else {
            this.control_view_scr.setVisibility(0);
            this.show_hide_img.setBackgroundResource(R.drawable._436_button_donw_up_hide);
        }
    }

    public void selectModular1() throws RemoteException {
        DvrSender.send(new byte[]{35, 0});
        DvrSender.send(new byte[]{35, 8});
        if (this.viewMode1 == null || this.frmContent == null || this.Modular_TAG == 1) {
            return;
        }
        this.Modular_TAG = 1;
        this.dvrPlayView.startVideoView();
        this.frmContent.removeAllViews();
        this.frmContent.addView(this.viewMode1);
        this.modular1_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
        this.modular2_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular3_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular4_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
    }

    public void selectModular2() throws RemoteException {
        DvrSender.send(new byte[]{35, 0});
        DvrSender.send(new byte[]{35, 8});
        if (this.viewMode2 == null || this.frmContent == null || this.Modular_TAG == 2) {
            return;
        }
        this.Modular_TAG = 2;
        this.dvrPlayView.stopVideoView();
        this.frmContent.removeAllViews();
        this.frmContent.addView(this.viewMode2);
        this.modular1_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular2_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
        this.modular3_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular4_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
    }

    public void selectModular3() throws RemoteException {
        DvrSender.send(new byte[]{89, 0, 0, 0, 0});
        DvrSender.send(new byte[]{90, 0, 0, 0, 0});
        DvrSender.send(new byte[]{35, 0});
        DvrSender.send(new byte[]{35, 8});
        if (this.viewMode3 == null || this.frmContent == null || this.Modular_TAG == 3) {
            return;
        }
        this.Modular_TAG = 3;
        this.dvrPlayView.stopVideoView();
        this.frmContent.removeAllViews();
        this.frmContent.addView(this.viewMode3);
        this.modular1_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular2_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular3_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
        this.modular4_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
    }

    public void selectModular4() throws RemoteException {
        DvrSender.send(new byte[]{35, 0});
        DvrSender.send(new byte[]{35, 8});
        if (GeneralDvrPlayPage.pageState != 2 && GeneralDvrPlayPage.pageState != 3) {
            DvrSender.send(new byte[]{64, 35});
        }
        DvrSender.send(new byte[]{64, 37});
        if (this.viewMode4 == null || this.frmContent == null || this.Modular_TAG == 4) {
            return;
        }
        this.Modular_TAG = 4;
        this.dvrPlayView.stopVideoView();
        this.frmContent.removeAllViews();
        this.frmContent.addView(this.viewMode4);
        this.modular1_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular2_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular3_lin.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        this.modular4_lin.setBackgroundColor(Color.parseColor("#86FF9800"));
    }

    private void playVideo(List list) throws RemoteException {
        if (list.size() > 25) {
            DvrSender.send(new byte[]{114, -1, (byte) ((Integer) list.get(7)).intValue(), (byte) ((Integer) list.get(8)).intValue(), (byte) ((Integer) list.get(9)).intValue(), (byte) ((Integer) list.get(10)).intValue(), (byte) ((Integer) list.get(11)).intValue(), (byte) ((Integer) list.get(12)).intValue(), (byte) ((Integer) list.get(13)).intValue(), (byte) ((Integer) list.get(14)).intValue(), (byte) ((Integer) list.get(15)).intValue(), (byte) ((Integer) list.get(16)).intValue(), (byte) ((Integer) list.get(17)).intValue(), (byte) ((Integer) list.get(18)).intValue(), (byte) ((Integer) list.get(19)).intValue(), (byte) ((Integer) list.get(20)).intValue(), (byte) ((Integer) list.get(21)).intValue(), (byte) ((Integer) list.get(22)).intValue(), (byte) ((Integer) list.get(23)).intValue(), (byte) ((Integer) list.get(24)).intValue(), (byte) ((Integer) list.get(25)).intValue()});
        }
    }
}
