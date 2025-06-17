package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.adapter.OnStartLvAdapter;
import com.hzbhd.canbus.entity.OnStartListEntity;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class OnStartMainFragment extends BaseFragment implements OnStartLvAdapter.ItemClickInterface {
    private OnStarActivity mActivity;
    private List<OnStartListEntity> mList;
    private OnStartLvAdapter mOnStartLvAdapter;
    private OnStartPageUiSet mOnStartPageUiSet;
    private RecyclerView mRecyclerView;
    private View mView;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_on_star_main, viewGroup, false);
            findViews();
            initViews();
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mView);
        }
        return this.mView;
    }

    private void findViews() {
        this.mRecyclerView = this.mView.findViewById(R.id.rv_list);
    }

    private void initViews() {
        OnStarActivity onStarActivity = (OnStarActivity) getActivity();
        this.mActivity = onStarActivity;
        this.mOnStartPageUiSet = onStarActivity.getUiMgrInterface(getActivity()).getPageUiSet(getActivity()).getOnStartPageUiSet();
        this.mList = new ArrayList<>();
        this.mOnStartLvAdapter = new OnStartLvAdapter(this.mList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), 1);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.setting_divider));
        this.mRecyclerView.addItemDecoration(dividerItemDecoration);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.mOnStartLvAdapter);
        ArrayList<OnStartListEntity> arrayList = new ArrayList<>();
        for (String str : this.mActivity.getUiMgrInterface(getActivity()).getPageUiSet(getActivity()).getOnStartPageUiSet().getBtnAction()) {
            arrayList.add(new OnStartListEntity(str));
        }
        this.mList.addAll(arrayList);
        this.mOnStartLvAdapter.notifyDataSetChanged();
    }

    @Override // com.hzbhd.canbus.adapter.OnStartLvAdapter.ItemClickInterface
    public void onItemClick(int i) {
        String action = this.mList.get(i).getAction();
        switch (action) {
            case "phone_more_info":
                this.mActivity.showFragment(OnStartPhoneMoreInfoFragment.class);
                break;
            case "wireless":
                this.mActivity.showFragment(OnStartWirelessFragment.class);
                this.mOnStartPageUiSet.getOnOnStartStatusChangeListener().onWirelessSwithc();
                break;
            case "phone":
                this.mActivity.showFragment(OnStartPhoneFragment.class);
                break;
            case "navigation":
                this.mActivity.showFragment(OnStartNavigationFragment.class);
                break;
        }
    }
}
