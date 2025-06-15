package com.hzbhd.canbus.car._278;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;


public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    boolean mListBtns;
    private ImageButton m_back_rev_guide;
    private ImageButton m_broadside_rev_guide;
    private ImageButton m_h2_exit;
    private ImageButton m_h2_hide;
    private LinearLayout m_ll_haval_h2;
    private LinearLayout m_ll_haval_h2_right_list_btns;
    private ImageButton m_vertical_rev_guide;

    public MyPanoramicView(Context context) {
        super(context);
        this.mListBtns = false;
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_278_view, this);
        this.m_ll_haval_h2 = (LinearLayout) viewInflate.findViewById(R.id.ll_haval_h2);
        this.m_ll_haval_h2_right_list_btns = (LinearLayout) viewInflate.findViewById(R.id.ll_haval_h2_right_list_btns);
        hideH2Btns(this.mListBtns);
        this.m_h2_exit = (ImageButton) viewInflate.findViewById(R.id.h2_exit);
        this.m_h2_hide = (ImageButton) viewInflate.findViewById(R.id.h2_hide);
        this.m_vertical_rev_guide = (ImageButton) viewInflate.findViewById(R.id.vertical_rev_guide);
        this.m_broadside_rev_guide = (ImageButton) viewInflate.findViewById(R.id.broadside_rev_guide);
        this.m_back_rev_guide = (ImageButton) viewInflate.findViewById(R.id.back_rev_guide);
        this.m_h2_exit.setOnClickListener(this);
        this.m_h2_hide.setOnClickListener(this);
        this.m_vertical_rev_guide.setOnClickListener(this);
        this.m_broadside_rev_guide.setOnClickListener(this);
        this.m_back_rev_guide.setOnClickListener(this);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mListBtns = false;
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mListBtns = false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.back_rev_guide) { // 2131361959
            resetVisual();
            setCameraBtnStatus(this.m_back_rev_guide, false);
            sendCameraButtonCommand(3);
        } else if (view.getId() == R.id.broadside_rev_guide) { // 2131361990
            resetVisual();
            setCameraBtnStatus(this.m_broadside_rev_guide, false);
            sendCameraButtonCommand(2);
        } else if (view.getId() == R.id.h2_exit) { // 2131362364
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 0});
        } else if (view.getId() == R.id.h2_hide) { // 2131362365
            boolean z = !this.mListBtns;
            this.mListBtns = z;
            hideH2Btns(z);
        } else if (view.getId() == R.id.vertical_rev_guide) { // 2131363753
            resetVisual();
            setCameraBtnStatus(this.m_vertical_rev_guide, false);
            sendCameraButtonCommand(1);
        }

    }

    private void sendCameraButtonCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i});
    }

    private void setCameraBtnStatus(View view, boolean z) {
        view.setAlpha(z ? 1.0f : 0.0f);
        view.setEnabled(z);
    }

    private void resetVisual() {
        setCameraBtnStatus(this.m_vertical_rev_guide, true);
        setCameraBtnStatus(this.m_broadside_rev_guide, true);
        setCameraBtnStatus(this.m_back_rev_guide, true);
    }

    private void hideH2Btns(boolean z) {
        if (z) {
            this.m_ll_haval_h2_right_list_btns.setVisibility(View.VISIBLE);
        } else {
            this.m_ll_haval_h2_right_list_btns.setVisibility(View.GONE);
        }
    }
}
