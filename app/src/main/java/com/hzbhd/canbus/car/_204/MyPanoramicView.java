package com.hzbhd.canbus.car._204;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;


public class MyPanoramicView extends RelativeLayout {
    private Button mBtnTest;
    private Context mContext;

    public MyPanoramicView(Context context) {
        super(context);
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_204_view, this);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.car._204.MyPanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.front) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
                } else if (view.getId() == R.id.left) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                } else if (view.getId() == R.id.rear) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
                } else if (view.getId() == R.id.right) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
                }

            }
        };
        viewInflate.findViewById(R.id.front).setOnClickListener(onClickListener);
        viewInflate.findViewById(R.id.left).setOnClickListener(onClickListener);
        viewInflate.findViewById(R.id.right).setOnClickListener(onClickListener);
        viewInflate.findViewById(R.id.rear).setOnClickListener(onClickListener);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
