package com.hzbhd.canbus.car_cus._446.activity;

import android.app.Activity;
import android.os.Bundle;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._446.CanObserver;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.data.WmSharedKey;
import com.hzbhd.canbus.car_cus._446.view.SelectionView;
import com.hzbhd.canbus.car_cus._446.view.TireView;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class WmTireInfoActivity extends Activity {
    private TireView tire_info;
    private SelectionView tire_unit;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wm_tire_info);
        this.tire_info = (TireView) findViewById(R.id.tire_info);
        this.tire_unit = (SelectionView) findViewById(R.id.tire_unit);
        ArrayList arrayList = new ArrayList();
        arrayList.add("PSI");
        arrayList.add("KPA");
        this.tire_unit.initItem(arrayList);
        this.tire_unit.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.activity.WmTireInfoActivity.1
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) {
                Integer num = (Integer) obj;
                if (num.intValue() == 0) {
                    SharePreUtil.setStringValue(WmTireInfoActivity.this, WmSharedKey.KEY_TIRE_UNIT, "PSI");
                    WmCarData.tire_unit = "PSI";
                    WmTireInfoActivity.this.tire_unit.setValue("PSI");
                    CanObserver.getInstance().dataChange();
                }
                if (num.intValue() == 1) {
                    SharePreUtil.setStringValue(WmTireInfoActivity.this, WmSharedKey.KEY_TIRE_UNIT, "KPA");
                    WmCarData.tire_unit = "KPA";
                    WmTireInfoActivity.this.tire_unit.setValue("KPA");
                    CanObserver.getInstance().dataChange();
                }
            }
        });
        this.tire_unit.setValue(WmCarData.tire_unit);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        CanObserver.getInstance().register(this.tire_info);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CanObserver.getInstance().unRegister(this.tire_info);
    }
}
