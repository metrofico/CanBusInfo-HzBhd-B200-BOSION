package com.hzbhd.canbus.car_cus._283.choosecan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.car_cus._283.choosecan.A6AdapterCar;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.config.use.CanBus;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class A6ChooseCanTypeActivity extends Activity {
    private static final int MQB = 283;
    private static final int PQ = 291;
    private static final String TAG = "A6ChooseCanTypeActivity";
    private A6AdapterCar adapterCan;
    private A6AdapterCar adapterCar;
    private A6AdapterCar adapterCarType;
    private A6AdapterCar adapterConpeny;
    private RecyclerView recyclerViewCan;
    private RecyclerView recyclerViewCar;
    private RecyclerView recyclerViewCarType;
    private RecyclerView recyclerViewCompany;
    private List<A6CarChooseBean> listCompany = new ArrayList();
    private List<A6CarChooseBean> listCar = new ArrayList();
    private List<A6CarChooseBean> listCarType = new ArrayList();
    private List<A6CarChooseBean> listCan = new ArrayList();
    private int factoryCanType = MQB;
    private String[] MQB_CAR = {"默认", "帕萨特混动"};
    private String[] PQ_CAR = {"默认"};

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.__283__activity_choose_cantype);
        this.factoryCanType = SelectCanTypeUtil.getCurrentCanTypeId(this);
        initView();
    }

    private void initView() {
        this.recyclerViewCompany = (RecyclerView) findViewById(R.id.recyclerViewCompany);
        this.recyclerViewCar = (RecyclerView) findViewById(R.id.recyclerViewCar);
        this.recyclerViewCarType = (RecyclerView) findViewById(R.id.recyclerViewCarType);
        this.recyclerViewCan = (RecyclerView) findViewById(R.id.recyclerViewCan);
        this.listCompany.add(new A6CarChooseBean(true, "德众尚杰"));
        this.recyclerViewCompany.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = this.recyclerViewCompany;
        A6AdapterCar a6AdapterCar = new A6AdapterCar(this, this.listCompany);
        this.adapterConpeny = a6AdapterCar;
        recyclerView.setAdapter(a6AdapterCar);
        this.listCar.add(new A6CarChooseBean(true, "大众"));
        this.recyclerViewCar.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = this.recyclerViewCar;
        A6AdapterCar a6AdapterCar2 = new A6AdapterCar(this, this.listCar);
        this.adapterCar = a6AdapterCar2;
        recyclerView2.setAdapter(a6AdapterCar2);
        this.listCarType.add(new A6CarChooseBean(this.factoryCanType == MQB, "MQB"));
        this.listCarType.add(new A6CarChooseBean(this.factoryCanType == PQ, "PQ"));
        this.recyclerViewCarType.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView3 = this.recyclerViewCarType;
        A6AdapterCar a6AdapterCar3 = new A6AdapterCar(this, this.listCarType);
        this.adapterCarType = a6AdapterCar3;
        recyclerView3.setAdapter(a6AdapterCar3);
        initClickCarType();
        setCarData();
        this.recyclerViewCan.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView4 = this.recyclerViewCan;
        A6AdapterCar a6AdapterCar4 = new A6AdapterCar(this, this.listCan);
        this.adapterCan = a6AdapterCar4;
        recyclerView4.setAdapter(a6AdapterCar4);
        initClickCan();
    }

    private void setCarData() {
        if (this.factoryCanType == MQB) {
            int i = 0;
            while (i < this.MQB_CAR.length) {
                this.listCan.add(new A6CarChooseBean(SelectCanTypeUtil.getCurrentCanDiffId() == i, this.MQB_CAR[i]));
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < this.PQ_CAR.length) {
            this.listCan.add(new A6CarChooseBean(SelectCanTypeUtil.getCurrentCanDiffId() == i2, this.PQ_CAR[i2]));
            i2++;
        }
    }

    private void initClickCarType() {
        this.adapterCarType.setOnItemClickListener(new A6AdapterCar.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.choosecan.A6ChooseCanTypeActivity.1
            @Override // com.hzbhd.canbus.car_cus._283.choosecan.A6AdapterCar.OnItemClickListener
            public void onClick(View view, int i) {
                if (((A6CarChooseBean) A6ChooseCanTypeActivity.this.listCarType.get(i)).isSelect()) {
                    return;
                }
                A6ChooseCanTypeActivity a6ChooseCanTypeActivity = A6ChooseCanTypeActivity.this;
                a6ChooseCanTypeActivity.setSelectPosition(a6ChooseCanTypeActivity.listCarType, i);
                A6ChooseCanTypeActivity.this.listCan.clear();
                if (i == 0) {
                    for (int i2 = 0; i2 < A6ChooseCanTypeActivity.this.MQB_CAR.length; i2++) {
                        A6ChooseCanTypeActivity.this.listCan.add(new A6CarChooseBean(false, A6ChooseCanTypeActivity.this.MQB_CAR[i2]));
                    }
                } else if (i == 1) {
                    for (int i3 = 0; i3 < A6ChooseCanTypeActivity.this.PQ_CAR.length; i3++) {
                        A6ChooseCanTypeActivity.this.listCan.add(new A6CarChooseBean(false, A6ChooseCanTypeActivity.this.PQ_CAR[i3]));
                    }
                }
                A6ChooseCanTypeActivity.this.adapterCarType.notifyDataSetChanged();
                A6ChooseCanTypeActivity.this.adapterCan.notifyDataSetChanged();
            }
        });
    }

    private void initClickCan() {
        this.adapterCan.setOnItemClickListener(new A6AdapterCar.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.choosecan.A6ChooseCanTypeActivity.2
            @Override // com.hzbhd.canbus.car_cus._283.choosecan.A6AdapterCar.OnItemClickListener
            public void onClick(View view, int i) {
                if (((A6CarChooseBean) A6ChooseCanTypeActivity.this.listCan.get(i)).isSelect()) {
                    return;
                }
                A6ChooseCanTypeActivity a6ChooseCanTypeActivity = A6ChooseCanTypeActivity.this;
                String selectName = a6ChooseCanTypeActivity.getSelectName(a6ChooseCanTypeActivity.listCompany);
                A6ChooseCanTypeActivity a6ChooseCanTypeActivity2 = A6ChooseCanTypeActivity.this;
                String selectName2 = a6ChooseCanTypeActivity2.getSelectName(a6ChooseCanTypeActivity2.listCar);
                A6ChooseCanTypeActivity a6ChooseCanTypeActivity3 = A6ChooseCanTypeActivity.this;
                A6ChooseCanTypeActivity.this.showDialogSelect(i, A6ChooseCanTypeActivity.this.getString(R.string._283_choose) + selectName + " " + selectName2 + " " + a6ChooseCanTypeActivity3.getSelectName(a6ChooseCanTypeActivity3.listCarType) + " " + ((A6CarChooseBean) A6ChooseCanTypeActivity.this.listCan.get(i)).getName());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialogSelect(final int i, final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(str);
        builder.setPositiveButton(R.string.str_ok, new DialogInterface.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.choosecan.A6ChooseCanTypeActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) throws InterruptedException {
                boolean zContains = str.contains(((A6CarChooseBean) A6ChooseCanTypeActivity.this.listCarType.get(0)).getName());
                int i3 = A6ChooseCanTypeActivity.MQB;
                if (!zContains && str.contains(((A6CarChooseBean) A6ChooseCanTypeActivity.this.listCarType.get(1)).getName())) {
                    i3 = A6ChooseCanTypeActivity.PQ;
                }
                A6ChooseCanTypeActivity.this.chooseCanType(i3, i);
            }
        });
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chooseCanType(int i, int i2) throws InterruptedException {
        if (this.factoryCanType == i) {
            if (SelectCanTypeUtil.getCurrentCanDiffId() == i2) {
                return;
            }
            CanBus.INSTANCE.setDifferentId(i2);
            CanbusConfig.INSTANCE.setCanType(i);
            System.exit(0);
            return;
        }
        setSpecifyCanTypeIdAndRestMpu(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSelectName(List<A6CarChooseBean> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                return list.get(i).getName();
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectPosition(List<A6CarChooseBean> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 == i) {
                list.get(i2).setSelect(true);
            } else {
                list.get(i2).setSelect(false);
            }
        }
    }

    void fullScreen(boolean z) {
        if (z) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags |= 2048;
            getWindow().setAttributes(attributes);
            getWindow().addFlags(512);
            return;
        }
        WindowManager.LayoutParams attributes2 = getWindow().getAttributes();
        attributes2.flags &= -2049;
        getWindow().setAttributes(attributes2);
        getWindow().clearFlags(512);
    }

    private static void setSpecifyCanTypeIdAndRestMpu(int i) throws InterruptedException {
        HzbhdLog.d(TAG, "切换can重启");
        CanbusConfig.INSTANCE.setCanType(i);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SendKeyManager.getInstance().resetMpu(HotKeyConstant.RESET_MODE.NORMAL, 1);
    }
}
