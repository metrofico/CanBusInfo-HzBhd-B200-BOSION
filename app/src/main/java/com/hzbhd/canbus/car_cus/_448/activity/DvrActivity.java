package com.hzbhd.canbus.car_cus._448.activity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.data.DvrMode;
import com.hzbhd.canbus.car_cus._448.util.TimerModeCheck;
import com.hzbhd.canbus.car_cus._448.view.FileListView;
import com.hzbhd.canbus.car_cus._448.view.NaviView;
import com.hzbhd.canbus.car_cus._448.view.VideoPageView;
import java.util.ArrayList;
import org.apache.log4j.varia.ExternallyRolledFileAppender;

/* loaded from: classes2.dex */
public class DvrActivity extends AppCompatActivity implements DvrDataInterface {
    private FileListView file_list_view;
    private LinearLayout function_menu_lin;
    private NaviView main_navi;
    private int nowModeTag = 0;
    private NaviView open_menu_navi;
    private LinearLayout playback_lin;
    private NaviView playback_navi;
    private VideoPageView video_page;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.__448_activity_448_dvr);
        VideoPageView videoPageView = (VideoPageView) findViewById(R.id.video_page);
        this.video_page = videoPageView;
        videoPageView.startView();
        initView();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        DvrObserver.getInstance().register(new DvrActivity$$ExternalSyntheticLambda0(this));
        TimerModeCheck.getInstance().start(500, new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.activity.DvrActivity.1
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                Log.d("DvrMode", DvrData.systemMode == 1 ? "回放模式" : "录像模式");
                if (DvrData.systemMode != 1 || DvrData.videoStateIcon != 2) {
                    if (DvrActivity.this.main_navi != null) {
                        DvrActivity.this.main_navi.setItem2Gone(true);
                    }
                } else if (DvrActivity.this.main_navi != null) {
                    DvrActivity.this.main_navi.setItem2Gone(false);
                }
                TimerModeCheck.getInstance().reset(500);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.video_page.stopView();
        DvrObserver.getInstance().unRegister(new DvrActivity$$ExternalSyntheticLambda0(this));
    }

    private void initView() {
        this.main_navi = (NaviView) findViewById(R.id.main_navi);
        this.open_menu_navi = (NaviView) findViewById(R.id.open_menu_navi);
        this.playback_navi = (NaviView) findViewById(R.id.playback_navi);
        this.file_list_view = (FileListView) findViewById(R.id.file_list_view);
        this.function_menu_lin = (LinearLayout) findViewById(R.id.function_menu_lin);
        this.playback_lin = (LinearLayout) findViewById(R.id.playback_lin);
        ArrayList arrayList = new ArrayList();
        arrayList.add("设置菜单");
        arrayList.add("文件列表");
        this.main_navi.initItem(arrayList);
        this.main_navi.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.activity.DvrActivity.2
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                Integer num = (Integer) obj;
                if (num.intValue() == 1) {
                    DvrActivity.this.selectOpenMenuNavi();
                }
                if (num.intValue() == 2) {
                    DvrActivity.this.selectPlaybackNavi();
                    DvrSender.send(new byte[]{113, -1});
                }
            }
        });
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("◀  返回");
        this.open_menu_navi.initItem(arrayList2);
        this.open_menu_navi.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.activity.DvrActivity.3
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                if (((Integer) obj).intValue() == 1) {
                    DvrActivity.this.selectMainNavi();
                }
            }
        });
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("◀  返回");
        this.playback_navi.initItem(arrayList3);
        this.playback_navi.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.activity.DvrActivity.4
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                if (((Integer) obj).intValue() == 1) {
                    DvrActivity.this.selectMainNavi();
                }
            }
        });
        this.video_page.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.activity.DvrActivity.5
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                if (obj.equals("MODE")) {
                    DvrActivity.this.turnMode();
                }
                if (obj.equals("PREV")) {
                    DvrSender.send(new byte[]{64, 37});
                }
                if (obj.equals(ExternallyRolledFileAppender.OK)) {
                    DvrSender.send(new byte[]{64, 36});
                }
                if (obj.equals("NEXT")) {
                    DvrSender.send(new byte[]{64, 38});
                }
                if (obj.equals("URGENT")) {
                    DvrSender.send(new byte[]{64, 33});
                }
                if (obj.equals("CAPTURE")) {
                    DvrSender.send(new byte[]{64, 37});
                }
            }
        });
        this.file_list_view.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.activity.DvrActivity.6
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) throws RemoteException {
                if (obj.equals("PREV")) {
                    DvrSender.send(new byte[]{64, 37});
                }
                if (obj.equals("ADD_LOCK")) {
                    DvrSender.send(new byte[]{64, 33});
                    DvrSender.send(new byte[]{113, -1});
                }
                if (obj.equals("NEXT")) {
                    DvrSender.send(new byte[]{64, 38});
                }
                if (obj.equals("PLAY")) {
                    DvrActivity.this.selectMainNavi();
                    DvrSender.send(new byte[]{64, 36});
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void turnMode() throws RemoteException {
        if (DvrData.videoStateIcon == 3 || (DvrData.systemMode == 1 && DvrData.videoStateIcon == 4)) {
            DvrSender.send(new byte[]{64, 35});
            DvrSender.send(new byte[]{64, 35});
            DvrSender.send(new byte[]{92, -1});
        } else {
            DvrSender.send(new byte[]{64, 35});
            DvrSender.send(new byte[]{92, -1});
        }
        DvrData.systemMode = 2;
        DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectMainNavi() {
        this.nowModeTag = 0;
        this.main_navi.setVisibility(0);
        this.open_menu_navi.setVisibility(8);
        this.function_menu_lin.setVisibility(8);
        this.playback_navi.setVisibility(8);
        this.playback_lin.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectOpenMenuNavi() {
        this.nowModeTag = 1;
        this.main_navi.setVisibility(8);
        this.open_menu_navi.setVisibility(0);
        this.function_menu_lin.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectPlaybackNavi() {
        this.nowModeTag = 2;
        this.main_navi.setVisibility(8);
        this.playback_navi.setVisibility(0);
        this.playback_lin.setVisibility(0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.nowModeTag != 0) {
            selectMainNavi();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface
    public void dataChange(String str) {
        if (str.equals(DvrMode.SPEECH_EXIT_DVR)) {
            finish();
        }
    }
}
