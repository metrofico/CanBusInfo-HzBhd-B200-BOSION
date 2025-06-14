package com.hzbhd.canbus.car._466;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;

/* loaded from: classes2.dex */
public class MyCameraBackView extends RelativeLayout {
    private View cameraBack;
    private Context mContext;

    public MyCameraBackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyCameraBackView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MyCameraBackView(Context context) {
        super(context);
        this.mContext = context;
        View viewFindViewById = LayoutInflater.from(context).inflate(R.layout.camera_back_466_view, (ViewGroup) this, true).findViewById(R.id.back_view_back);
        this.cameraBack = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._466.MyCameraBackView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShareDataManager.getInstance().reportInt(ShareConstants.SHARE_USER_REVERSE, 0);
            }
        });
    }
}
