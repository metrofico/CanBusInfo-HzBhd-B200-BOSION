package com.hzbhd.canbus.car_cus._304.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.cantype.CanTypeUtil;

/* loaded from: classes2.dex */
public class VehicleAssistanceView extends RelativeLayout {
    private ImageButton mIbSwitch;
    private View mView;

    public VehicleAssistanceView(Activity activity) {
        super(activity);
        this.mView = LayoutInflater.from(activity).inflate(R.layout._304_view_vehicle_assistance, this);
        findViews();
        initViews(activity);
    }

    private void findViews() {
        this.mIbSwitch = (ImageButton) this.mView.findViewById(R.id.ib_switch);
    }

    private void initViews(final Activity activity) {
        this.mIbSwitch.setSelected(MyGeneralData.mPedestrianRemind);
        this.mIbSwitch.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._304.view.VehicleAssistanceView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1137xf5ec6ea6(view);
            }
        });
        findViewById(R.id.vechiel_type_select).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.car_cus._304.view.VehicleAssistanceView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f$0.m1138x82d985c5(activity, view);
            }
        });
    }

    /* renamed from: lambda$initViews$0$com-hzbhd-canbus-car_cus-_304-view-VehicleAssistanceView, reason: not valid java name */
    /* synthetic */ void m1137xf5ec6ea6(View view) {
        if (this.mIbSwitch.isSelected()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 46, 1});
            this.mIbSwitch.setSelected(false);
            MyGeneralData.mPedestrianRemind = false;
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 46, 0});
            this.mIbSwitch.setSelected(true);
            MyGeneralData.mPedestrianRemind = true;
        }
    }

    /* renamed from: lambda$initViews$1$com-hzbhd-canbus-car_cus-_304-view-VehicleAssistanceView, reason: not valid java name */
    /* synthetic */ boolean m1138x82d985c5(Activity activity, View view) {
        SelectCanTypeUtil.showDialogToUpdate(activity, CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(activity)).getList().get(0), getResources().getString(R.string.switch_car_model));
        return false;
    }
}
