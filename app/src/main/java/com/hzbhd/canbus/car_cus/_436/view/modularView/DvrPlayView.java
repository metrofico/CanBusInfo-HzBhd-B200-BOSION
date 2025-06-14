package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.childView.Breathe;
import com.hzbhd.proxy.camera.manager.CameraManager;

/* loaded from: classes2.dex */
public class DvrPlayView extends RelativeLayout implements View.OnClickListener, MdNotifyListener {
    private boolean VIDEO_VIEW_SHOW_TAG;
    TextView _436_DVR_play_page_fun_2_0;
    Breathe breathe;
    LinearLayout linearLayout2;
    Context mContext;
    ImageButton menu;
    ImageButton mode;
    ImageButton next;
    ImageButton ok;
    TextView page_state_txt;
    TextView play_state_txt;
    TextView play_time_txt;
    ImageButton prev;
    View view;

    @Override // com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener
    public void updateUi() {
        refreshUi();
    }

    public DvrPlayView(Context context) {
        this(context, null);
    }

    public DvrPlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DvrPlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.VIDEO_VIEW_SHOW_TAG = false;
        this.mContext = context;
        this.view = LayoutInflater.from(context).inflate(R.layout._436_dvr_paly_view, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        startVideoView();
        Breathe breathe = (Breathe) this.view.findViewById(R.id.breathe);
        this.breathe = breathe;
        breathe.show();
        this.linearLayout2 = (LinearLayout) this.view.findViewById(R.id.linearLayout2);
        this.page_state_txt = (TextView) this.view.findViewById(R.id.page_state_txt);
        this.play_time_txt = (TextView) this.view.findViewById(R.id.play_time_txt);
        this.play_state_txt = (TextView) this.view.findViewById(R.id.play_state_txt);
        this._436_DVR_play_page_fun_2_0 = (TextView) this.view.findViewById(R.id.play_time_txt);
        this.menu = (ImageButton) this.view.findViewById(R.id.menu);
        this.prev = (ImageButton) this.view.findViewById(R.id.prev);
        this.ok = (ImageButton) this.view.findViewById(R.id.ok);
        this.next = (ImageButton) this.view.findViewById(R.id.next);
        this.mode = (ImageButton) this.view.findViewById(R.id.mode);
        this.menu.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.prev.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.ok.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.next.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.mode.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
    }

    public void startVideoView() {
        View view = this.view;
        if (view == null || this.VIDEO_VIEW_SHOW_TAG) {
            return;
        }
        ((TextureView) view.findViewById(R.id.video_sur)).setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrPlayView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                CameraManager.INSTANCE.startPreview(2, new Surface(surfaceTexture));
                DvrPlayView.this.VIDEO_VIEW_SHOW_TAG = true;
            }
        });
    }

    public void stopVideoView() {
        CameraManager.INSTANCE.stopPreview(2);
        this.VIDEO_VIEW_SHOW_TAG = false;
    }

    public void refreshUi() {
        setPageState(GeneralDvrPlayPage.pageState);
        this._436_DVR_play_page_fun_2_0.setText(GeneralDvrPlayPage.time);
        this.play_state_txt.setText(GeneralDvrPlayPage.controlState);
    }

    private void setPageState(int i) {
        if (i == 0) {
            this.linearLayout2.setVisibility(0);
            this.breathe.setVisibility(8);
            this.page_state_txt.setVisibility(0);
            this.page_state_txt.setTextColor(-1);
            this.page_state_txt.setText(this.mContext.getString(R.string._436_DVR_play_page_fun_1_0));
            this.linearLayout2.setBackgroundResource(R.drawable._436_white_6r_tranceport_frame);
            return;
        }
        if (i == 1) {
            this.linearLayout2.setVisibility(0);
            this.breathe.setVisibility(0);
            this.page_state_txt.setVisibility(0);
            this.page_state_txt.setTextColor(SupportMenu.CATEGORY_MASK);
            this.page_state_txt.setText(this.mContext.getString(R.string._436_DVR_play_page_fun_1_1));
            this.linearLayout2.setBackgroundResource(R.drawable._436_red_6r_tranceport_frame);
            return;
        }
        if (i == 2) {
            this.linearLayout2.setVisibility(0);
            this.breathe.setVisibility(8);
            this.page_state_txt.setVisibility(0);
            this.page_state_txt.setTextColor(-1);
            this.page_state_txt.setText(this.mContext.getString(R.string._436_DVR_play_page_fun_1_2));
            this.linearLayout2.setBackgroundResource(R.drawable._436_white_6r_tranceport_frame);
            return;
        }
        if (i != 3) {
            return;
        }
        this.linearLayout2.setVisibility(0);
        this.breathe.setVisibility(8);
        this.page_state_txt.setVisibility(0);
        this.page_state_txt.setTextColor(-1);
        this.page_state_txt.setText(this.mContext.getString(R.string._436_DVR_play_page_fun_1_3));
        this.linearLayout2.setBackgroundResource(R.drawable._436_white_6r_tranceport_frame);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws RemoteException {
        switch (view.getId()) {
            case R.id.menu /* 2131362838 */:
                DvrSender.send(new byte[]{64, 34});
                break;
            case R.id.mode /* 2131362859 */:
                DvrSender.send(new byte[]{64, 35});
                break;
            case R.id.next /* 2131362896 */:
                DvrSender.send(new byte[]{64, 38});
                break;
            case R.id.ok /* 2131362918 */:
                DvrSender.send(new byte[]{64, 36});
                break;
            case R.id.prev /* 2131362992 */:
                DvrSender.send(new byte[]{64, 37});
                break;
        }
    }
}
