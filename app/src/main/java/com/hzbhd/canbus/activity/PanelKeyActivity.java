package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.PanelKeyLvAdapter;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kotlin.jvm.internal.Intrinsics;

public final class PanelKeyActivity extends AbstractBaseActivity implements PanelKeyLvAdapter.ItemClickInterface, PanelKeyLvAdapter.ItemLongClickInterface, PanelKeyLvAdapter.ItemTouchInterface {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap<>();
    private ArrayList<String> mLeftList;
    private PanelKeyLvAdapter mPanelKeyLvAdapter;
    private PanelKeyPageUiSet mSet;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(i, viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_key);
        initView();
    }

    private void initView() {
        List<String> list;
        PanelKeyActivity panelKeyActivity = this;
        this.mSet = getUiMgrInterface(panelKeyActivity).getPanelKeyPageUiSet(panelKeyActivity);
        this.mLeftList = new ArrayList<>();
        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet != null && (list = panelKeyPageUiSet.getList()) != null) {
            ArrayList<String> arrayList = this.mLeftList;

            arrayList.addAll(list);
        }
        this.mPanelKeyLvAdapter = new PanelKeyLvAdapter(panelKeyActivity, this.mLeftList, this, this, this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rv_list);
        PanelKeyPageUiSet panelKeyPageUiSet2 = this.mSet;

        recyclerView.setLayoutManager(new GridLayoutManager(panelKeyActivity, panelKeyPageUiSet2.getCount(), RecyclerView.VERTICAL, false));
        ((RecyclerView) _$_findCachedViewById(R.id.rv_list)).setAdapter(this.mPanelKeyLvAdapter);
    }

    @Override // com.hzbhd.canbus.adapter.PanelKeyLvAdapter.ItemClickInterface
    public void onItemClick(int position) {
        OnPanelKeyPositionListener onPanelKeyPositionListener;
        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet == null || (onPanelKeyPositionListener = panelKeyPageUiSet.getOnPanelKeyPositionListener()) == null) {
            return;
        }
        onPanelKeyPositionListener.click(position);
    }

    @Override // com.hzbhd.canbus.adapter.PanelKeyLvAdapter.ItemLongClickInterface
    public void onItemLongClick(int position) {
        OnPanelLongKeyPositionListener onPanelLongKeyPositionListener;
        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet == null || (onPanelLongKeyPositionListener = panelKeyPageUiSet.getOnPanelLongKeyPositionListener()) == null) {
            return;
        }
        onPanelLongKeyPositionListener.longClick(position);
    }

    @Override // com.hzbhd.canbus.adapter.PanelKeyLvAdapter.ItemTouchInterface
    public void onItemTouch(int position, MotionEvent event) {
        OnPanelKeyPositionTouchListener onPanelKeyPositionTouchListener;

        PanelKeyPageUiSet panelKeyPageUiSet = this.mSet;
        if (panelKeyPageUiSet == null || (onPanelKeyPositionTouchListener = panelKeyPageUiSet.getOnPanelKeyPositionTouchListener()) == null) {
            return;
        }
        onPanelKeyPositionTouchListener.onTouch(position, event);
    }
}
