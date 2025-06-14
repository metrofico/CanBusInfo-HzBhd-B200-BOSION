package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.data.DvrMode;
import com.hzbhd.canbus.ui_set.SyncAction;
import org.apache.log4j.varia.ExternallyRolledFileAppender;

/* loaded from: classes2.dex */
public class VideoPageView extends LinearLayout implements DvrDataInterface {
    private KeyButton capture_btn;
    private TextView fast_forward;
    private KeyButton mode_btn;
    private ImageView mode_icon;
    private TextView mode_txt;
    private KeyButton next_btn;
    private KeyButton ok_btn;
    private KeyButton prev_btn;
    private TextView system_time;
    private KeyButton urgent_btn;
    private VideoInfoView video_info_view;
    private VideoStateView video_state;
    private View view;

    public VideoPageView(Context context) {
        this(context, null);
    }

    public VideoPageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoPageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.view = LayoutInflater.from(context).inflate(R.layout.__448_view_video_page, (ViewGroup) this, true);
        this.video_info_view = (VideoInfoView) findViewById(R.id.video_info_view);
        this.video_state = (VideoStateView) this.view.findViewById(R.id.video_state);
        this.system_time = (TextView) this.view.findViewById(R.id.system_time);
        this.mode_btn = (KeyButton) this.view.findViewById(R.id.mode_btn);
        this.prev_btn = (KeyButton) this.view.findViewById(R.id.prev_btn);
        this.ok_btn = (KeyButton) this.view.findViewById(R.id.ok_btn);
        this.next_btn = (KeyButton) this.view.findViewById(R.id.next_btn);
        this.urgent_btn = (KeyButton) this.view.findViewById(R.id.urgent_btn);
        this.capture_btn = (KeyButton) this.view.findViewById(R.id.capture_btn);
        this.mode_icon = (ImageView) this.view.findViewById(R.id.mode_icon);
        this.mode_txt = (TextView) this.view.findViewById(R.id.mode_txt);
        this.fast_forward = (TextView) this.view.findViewById(R.id.fast_forward);
        this.mode_btn.setTextValue("模式切换");
        this.prev_btn.setTextValue("◀◀");
        this.ok_btn.setTextValue(SyncAction.KEYBOARD_OK);
        this.next_btn.setTextValue("▶▶");
        this.urgent_btn.setTextValue("紧急录像");
        this.capture_btn.setTextValue("一键抓拍");
    }

    public void getAction(final ActionCallback actionCallback) {
        this.mode_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.VideoPageView.1
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("MODE");
            }
        });
        this.prev_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.VideoPageView.2
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("PREV");
            }
        });
        this.ok_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.VideoPageView.3
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(ExternallyRolledFileAppender.OK);
            }
        });
        this.next_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.VideoPageView.4
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("NEXT");
            }
        });
        this.urgent_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.VideoPageView.5
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("URGENT");
            }
        });
        this.capture_btn.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.VideoPageView.6
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo("CAPTURE");
            }
        });
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateUi();
        DvrObserver.getInstance().register(this);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        super.onStopNestedScroll(view);
    }

    public void startView() {
        this.video_info_view.startVideoView();
    }

    public void stopView() {
        this.video_info_view.stopVideoView();
    }

    public void updateUi() {
        this.video_state.setState(DvrData.videoStateStr);
        if (DvrData.videoStateIcon == 1) {
            this.ok_btn.setTextValue("II");
            this.video_state.setIcon(R.drawable.__448___ph8_dvr_mute);
        } else if (DvrData.videoStateIcon == 2) {
            this.ok_btn.setTextValue("▶");
            this.video_state.setIcon(R.drawable.__448___ph8_dvr_video_stop1);
        } else if (DvrData.videoStateIcon == 3) {
            this.ok_btn.setTextValue("II");
            this.video_state.setIcon(R.drawable.__448___ph8_dvr_video_stop);
        } else if (DvrData.videoStateIcon == 4) {
            this.ok_btn.setTextValue("▶");
            this.video_state.setIcon(R.drawable.__448___ph8_dvr_video_stop1);
        }
        this.video_state.setTime(DvrData.playTime);
        this.system_time.setText(DvrData.systemDate + "  " + DvrData.systemTime);
        if (DvrData.systemMode == 1) {
            this.mode_icon.setBackgroundResource(R.drawable.__448_mode_1);
            this.mode_txt.setText("回放模式");
            this.capture_btn.setVisibility(8);
            this.urgent_btn.setVisibility(8);
            this.prev_btn.setVisibility(0);
            this.next_btn.setVisibility(0);
        } else if (DvrData.systemMode == 2) {
            this.mode_icon.setBackgroundResource(R.drawable.__448_mode2);
            this.mode_txt.setText("录像模式");
            this.capture_btn.setVisibility(0);
            this.urgent_btn.setVisibility(0);
            this.prev_btn.setVisibility(8);
            this.next_btn.setVisibility(8);
        }
        this.fast_forward.setText(DvrData.fastState);
    }

    @Override // com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface
    public void dataChange(String str) {
        if (str.equals(DvrMode.VIDEO_STATE_MODE)) {
            updateUi();
        }
    }
}
