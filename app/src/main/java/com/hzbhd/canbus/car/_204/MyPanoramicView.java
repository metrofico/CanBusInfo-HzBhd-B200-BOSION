package com.hzbhd.canbus.car._204;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;

/* loaded from: classes2.dex */
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
                switch (view.getId()) {
                    case R.id.front /* 2131362264 */:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
                        break;
                    case R.id.left /* 2131362718 */:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                        break;
                    case R.id.rear /* 2131363042 */:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
                        break;
                    case R.id.right /* 2131363146 */:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
                        break;
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
