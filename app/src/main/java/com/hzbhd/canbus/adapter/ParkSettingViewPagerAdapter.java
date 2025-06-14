package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.hzbhd.canbus.entity.ParkSettingEntity;
import java.util.List;

/* loaded from: classes.dex */
public class ParkSettingViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ParkSettingEntity> mList;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ParkSettingViewPagerAdapter(Context context, List<ParkSettingEntity> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.mList.get(i).getView(), 0);
        return this.mList.get(i).getView();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.mList.get(i).getTitle();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.mList.get(i).getView());
    }
}
