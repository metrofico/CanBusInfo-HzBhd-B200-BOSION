package com.hzbhd.canbus.car._306;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.util.SelectCanTypeUtil;


public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
    private ImageView d2_360_View;
    private ImageView d3_360_View;
    private ImageView front_360_View;
    private ImageView left_360_View;
    private View lineView;
    private Context mContext;
    private ImageView out_360_View;
    private ImageView rear_360_View;
    private RelativeLayout relativeLayout;
    private ImageView right_360_View;

    public MyPanoramicView(Context context) {
        super(context);
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_306_view, this);
        this.relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.relativeLayout);
        this.front_360_View = (ImageView) viewInflate.findViewById(R.id.front_360_View);
        this.rear_360_View = (ImageView) viewInflate.findViewById(R.id.rear_360_View);
        this.left_360_View = (ImageView) viewInflate.findViewById(R.id.left_360_View);
        this.right_360_View = (ImageView) viewInflate.findViewById(R.id.right_360_View);
        this.d2_360_View = (ImageView) viewInflate.findViewById(R.id.d2_360_View);
        this.d3_360_View = (ImageView) viewInflate.findViewById(R.id.d3_360_View);
        this.out_360_View = (ImageView) viewInflate.findViewById(R.id.out_360_View);
        this.lineView = viewInflate.findViewById(R.id.lineView);
        this.front_360_View.setOnClickListener(this);
        this.rear_360_View.setOnClickListener(this);
        this.left_360_View.setOnClickListener(this);
        this.right_360_View.setOnClickListener(this);
        this.d2_360_View.setOnClickListener(this);
        this.d3_360_View.setOnClickListener(this);
        this.out_360_View.setOnClickListener(this);
        if (SelectCanTypeUtil.getCurrentCanDiffId() == 2) {
            this.relativeLayout.setVisibility(8);
            return;
        }
        if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
            this.relativeLayout.setVisibility(0);
            this.d2_360_View.setImageResource(R.drawable._306_360_2d_view);
            this.d3_360_View.setVisibility(0);
            this.lineView.setVisibility(0);
            return;
        }
        this.relativeLayout.setVisibility(0);
        this.d3_360_View.setVisibility(8);
        this.d2_360_View.setImageResource(-1);
        this.d2_360_View.setOnClickListener(null);
        this.out_360_View.setVisibility(8);
        this.out_360_View.setImageResource(-1);
        this.out_360_View.setOnClickListener(null);
        this.lineView.setVisibility(8);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void refreshUi(int i) {
        this.front_360_View.setSelected(i == 1);
        this.rear_360_View.setSelected(i == 2);
        this.left_360_View.setSelected(i == 3);
        this.right_360_View.setSelected(i == 4);
        this.d2_360_View.setSelected(i == 5);
        this.d3_360_View.setSelected(i == 6);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d2_360_View /* 2131362166 */:
                sendCmd(5);
                break;
            case R.id.d3_360_View /* 2131362168 */:
                sendCmd(6);
                break;
            case R.id.front_360_View /* 2131362265 */:
                sendCmd(1);
                break;
            case R.id.left_360_View /* 2131362725 */:
                sendCmd(3);
                break;
            case R.id.out_360_View /* 2131362930 */:
                sendCmd(0);
                break;
            case R.id.rear_360_View /* 2131363043 */:
                sendCmd(2);
                break;
            case R.id.right_360_View /* 2131363153 */:
                sendCmd(4);
                break;
        }
    }

    private void sendCmd(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) i});
    }
}
