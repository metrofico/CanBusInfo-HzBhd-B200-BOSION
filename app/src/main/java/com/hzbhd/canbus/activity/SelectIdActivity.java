package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.SelectCanTypeIdAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.cantype.AllCanType;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.config.use.CanBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class SelectIdActivity extends Activity implements View.OnClickListener {
    private SelectCanTypeIdAdapter mAdapter;
    private List<Integer> mCarTypeEntityList;
    private RecyclerView mListRv;
    private TextView mSelectedTv;
    private final int COUNT = 6;
    private final SelectCanTypeIdAdapter.ItemCallBackInterface mCarModelClick = new SelectCanTypeIdAdapter.ItemCallBackInterface() { // from class: com.hzbhd.canbus.activity.SelectIdActivity.1
        @Override // com.hzbhd.canbus.adapter.SelectCanTypeIdAdapter.ItemCallBackInterface
        public void select(int i) {
            SelectCanTypeUtil.showDialogToUpdate(SelectIdActivity.this, CanTypeUtil.INSTANCE.getCanType(i).getList().get(0), null);
        }
    };

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((MyApplication) getApplication()).addActivity(this);
        setContentView(R.layout.activity_select_can_type_id);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mListRv = findViewById(R.id.rv_list);
        this.mSelectedTv = findViewById(R.id.tv_selected);
    }

    private void initViews() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mCarTypeEntityList = arrayList;
        arrayList.addAll(new AllCanType().getCanTypeList());
        Collections.sort(this.mCarTypeEntityList);
        this.mAdapter = new SelectCanTypeIdAdapter(this, this.mCarTypeEntityList, this.mCarModelClick, getFactoryCanType());
        this.mListRv.setLayoutManager(new GridLayoutManager(this, 6, RecyclerView.VERTICAL, false));
        this.mListRv.setAdapter(this.mAdapter);
        CanTypeAllEntity curCanTypeEntity = getCurCanTypeEntity();
        if (curCanTypeEntity != null) {
            this.mSelectedTv.setText(curCanTypeEntity.getProtocol_company() + " " + curCanTypeEntity.getCar_category() + " " + curCanTypeEntity.getCan_type_id());
        }
    }

    private CanTypeAllEntity getCurCanTypeEntity() {
        ArrayList<CanTypeAllEntity> list = CanTypeUtil.INSTANCE.getCanType(getFactoryCanType()).getList();
        int differentId = CanBus.INSTANCE.getDifferentId();
        if (list.size() == 0) {
            CommUtil.showToast(this, "Los datos no se han importado, por favor reinicia e inténtalo de nuevo.");
            return null;
        }
        for (CanTypeAllEntity canTypeAllEntity : list) {
            if (canTypeAllEntity.getCan_different_id() == differentId) {
                return canTypeAllEntity;
            }
        }
        return list.get(0);
    }

    private int getFactoryCanType() {
        return CanbusConfig.INSTANCE.getCanType();
    }
}
