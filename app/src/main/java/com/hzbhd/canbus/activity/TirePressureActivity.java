package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.TireInfoLvAdapter;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TirePressureActivity extends AbstractBaseActivity {
    private List<TirePageUiSet.LineItem> mList0;
    private List<TirePageUiSet.LineItem> mList1;
    private List<TirePageUiSet.LineItem> mList2;
    private List<TirePageUiSet.LineItem> mList3;
    private List<TirePageUiSet.LineItem> mList4;
    private RelativeLayout mNoTirePressureInfoRl;
    private OnTirePageStatusListener mOnTirePageStatusListener;
    private ImageView mTire0Iv;
    private ImageView mTire1Iv;
    private ImageView mTire2Iv;
    private ImageView mTire3Iv;
    private ImageView mTire4Iv;
    private RecyclerView mTireInfo0Rv;
    private RecyclerView mTireInfo1Rv;
    private RecyclerView mTireInfo2Rv;
    private RecyclerView mTireInfo3Rv;
    private RecyclerView mTireInfo4Rv;
    private TireInfoLvAdapter mTireInfoLvAdapter0;
    private TireInfoLvAdapter mTireInfoLvAdapter1;
    private TireInfoLvAdapter mTireInfoLvAdapter2;
    private TireInfoLvAdapter mTireInfoLvAdapter3;
    private TireInfoLvAdapter mTireInfoLvAdapter4;
    private TirePageUiSet mTireUiSet;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_tire_pressure);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mTire0Iv = findViewById(R.id.iv_tire_0);
        this.mTire1Iv = findViewById(R.id.iv_tire_1);
        this.mTire2Iv = findViewById(R.id.iv_tire_2);
        this.mTire3Iv = findViewById(R.id.iv_tire_3);
        this.mTire4Iv = findViewById(R.id.iv_tire_4);
        this.mTireInfo0Rv = findViewById(R.id.rv_info_0);
        this.mTireInfo1Rv = findViewById(R.id.rv_info_1);
        this.mTireInfo2Rv = findViewById(R.id.rv_info_2);
        this.mTireInfo3Rv = findViewById(R.id.rv_info_3);
        this.mTireInfo4Rv = findViewById(R.id.rv_info_4);
        this.mNoTirePressureInfoRl = findViewById(R.id.rl_no_tire_pressure_info_layout);
    }

    private void initViews() {
        TirePageUiSet tireUiSet = getUiMgrInterface(this).getTireUiSet(this);
        this.mTireUiSet = tireUiSet;
        OnTirePageStatusListener onTirePageStatusListener = tireUiSet.getOnTirePageStatusListener();
        this.mOnTirePageStatusListener = onTirePageStatusListener;
        if (onTirePageStatusListener != null) {
            onTirePageStatusListener.onStatusChange(1);
        } else {
            LogUtil.showLog("mOnTirePageStatusListener==null");
        }
        this.mList0 = new ArrayList<>();
        TireInfoLvAdapter tireInfoLvAdapter = new TireInfoLvAdapter(this, this.mList0);
        this.mTireInfoLvAdapter0 = tireInfoLvAdapter;
        setListData(this.mTireInfo0Rv, tireInfoLvAdapter);
        this.mList1 = new ArrayList<>();
        TireInfoLvAdapter tireInfoLvAdapter2 = new TireInfoLvAdapter(this, this.mList1);
        this.mTireInfoLvAdapter1 = tireInfoLvAdapter2;
        setListData(this.mTireInfo1Rv, tireInfoLvAdapter2);
        this.mList2 = new ArrayList<>();
        TireInfoLvAdapter tireInfoLvAdapter3 = new TireInfoLvAdapter(this, this.mList2);
        this.mTireInfoLvAdapter2 = tireInfoLvAdapter3;
        setListData(this.mTireInfo2Rv, tireInfoLvAdapter3);
        this.mList3 = new ArrayList<>();
        TireInfoLvAdapter tireInfoLvAdapter4 = new TireInfoLvAdapter(this, this.mList3);
        this.mTireInfoLvAdapter3 = tireInfoLvAdapter4;
        setListData(this.mTireInfo3Rv, tireInfoLvAdapter4);
        this.mList4 = new ArrayList<>();
        TireInfoLvAdapter tireInfoLvAdapter5 = new TireInfoLvAdapter(this, this.mList4);
        this.mTireInfoLvAdapter4 = tireInfoLvAdapter5;
        setListData(this.mTireInfo4Rv, tireInfoLvAdapter5);
        setViewVisibility(this.mTire4Iv, this.mTireUiSet.isHaveSpareTire());
        setViewVisibility(this.mTireInfo4Rv, this.mTireUiSet.isHaveSpareTire());
        List<TirePageUiSet.TireItem> tireInfoStrList = this.mTireUiSet.getTireInfoStrList();
        if (!this.mTireUiSet.isInitResToString()) {
            Iterator<TirePageUiSet.TireItem> it = tireInfoStrList.iterator();
            while (it.hasNext()) {
                for (TirePageUiSet.LineItem lineItem : it.next().getList()) {
                    lineItem.setValue(getString(CommUtil.getStrIdByResId(this, lineItem.getValue())));
                }
            }
            this.mTireUiSet.setInitResToString(true);
        }
        for (TirePageUiSet.TireItem tireItem : tireInfoStrList) {
            if (tireItem == null) {
                return;
            }
            int whichTire = tireItem.getWhichTire();
            if (whichTire == 0) {
                this.mList0.clear();
                this.mList0.addAll(tireItem.getList());
                this.mTireInfoLvAdapter0.notifyDataSetChanged();
            } else if (whichTire == 1) {
                this.mList1.clear();
                this.mList1.addAll(tireItem.getList());
                this.mTireInfoLvAdapter1.notifyDataSetChanged();
            } else if (whichTire == 2) {
                this.mList2.clear();
                this.mList2.addAll(tireItem.getList());
                this.mTireInfoLvAdapter2.notifyDataSetChanged();
            } else if (whichTire == 3) {
                this.mList3.clear();
                this.mList3.addAll(tireItem.getList());
                this.mTireInfoLvAdapter3.notifyDataSetChanged();
            } else if (whichTire == 4) {
                this.mList4.clear();
                this.mList4.addAll(tireItem.getList());
                this.mTireInfoLvAdapter4.notifyDataSetChanged();
            }
        }
        refreshUi(null);
    }

    private void setListData(RecyclerView recyclerView, TireInfoLvAdapter tireInfoLvAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.tire_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(tireInfoLvAdapter);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            if (GeneralTireData.isNoTirePressureInfo) {
                this.mNoTirePressureInfoRl.setVisibility(View.VISIBLE);
            } else {
                this.mNoTirePressureInfoRl.setVisibility(android.view.View.GONE);
                refreshEachList(GeneralTireData.dataList);
            }
            if (GeneralTireData.isHaveSpareTire) {
                this.mTire4Iv.setVisibility(android.view.View.VISIBLE);
                this.mTireInfo4Rv.setVisibility(View.VISIBLE);
            } else {
                this.mTire4Iv.setVisibility(View.GONE);
                this.mTireInfo4Rv.setVisibility(android.view.View.GONE);
            }
        }
    }

    private void refreshEachList(List<TireUpdateEntity> list) {
        TireUpdateEntity next;
        if (list == null) {
            return;
        }
        Iterator<TireUpdateEntity> it = list.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            int whichTire = next.getWhichTire();
            int i = 0;
            if (whichTire == 0) {
                while (i < next.getList().size() && i < this.mList0.size()) {
                    this.mList0.get(i).setValue(next.getList().get(i));
                    i++;
                }
                this.mTireInfoLvAdapter0.notifyDataSetChanged();
            } else if (whichTire == 1) {
                while (i < next.getList().size() && i < this.mList1.size()) {
                    this.mList1.get(i).setValue(next.getList().get(i));
                    i++;
                }
                this.mTireInfoLvAdapter1.notifyDataSetChanged();
            } else if (whichTire == 2) {
                while (i < next.getList().size() && i < this.mList2.size()) {
                    this.mList2.get(i).setValue(next.getList().get(i));
                    i++;
                }
                this.mTireInfoLvAdapter2.notifyDataSetChanged();
            } else if (whichTire == 3) {
                while (i < next.getList().size() && i < this.mList3.size()) {
                    this.mList3.get(i).setValue(next.getList().get(i));
                    i++;
                }
                this.mTireInfoLvAdapter3.notifyDataSetChanged();
            } else if (whichTire == 4) {
                while (i < next.getList().size() && i < this.mList4.size()) {
                    this.mList4.get(i).setValue(next.getList().get(i));
                    i++;
                }
                this.mTireInfoLvAdapter4.notifyDataSetChanged();
            }
            setTireStatus(whichTire, next.getTireStatus());
        }
    }

    private void setTireStatus(int i, int i2) {
        ImageView imageView;
        if (i == 0) {
            imageView = this.mTire0Iv;
        } else if (i == 1) {
            imageView = this.mTire1Iv;
        } else if (i == 2) {
            imageView = this.mTire2Iv;
        } else if (i == 3) {
            imageView = this.mTire3Iv;
        } else {
            imageView = i != 4 ? null : this.mTire4Iv;
        }
        if (imageView == null) {
            return;
        }
        if (i2 == 0) {
            if (i == 4) {
                imageView.setImageResource(R.drawable.icon_tire_sparetire_n);
                return;
            } else {
                imageView.setImageResource(R.drawable.icon_tire_tire_n);
                return;
            }
        }
        if (i2 == 1) {
            if (i == 4) {
                imageView.setImageResource(R.drawable.icon_tire_sparetirewarn_p);
            } else {
                imageView.setImageResource(R.drawable.icon_tire_tirewarn_p);
            }
        }
    }

    private void setViewVisibility(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }
}
