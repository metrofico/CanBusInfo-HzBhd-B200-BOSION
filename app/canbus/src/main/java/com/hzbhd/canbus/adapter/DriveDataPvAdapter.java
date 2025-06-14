package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DriveDataPvAdapter extends PagerAdapter {
    private Context mContext;
    private List<DriveDataLvItemAdapter> mDriveDataLvItemAdapterList = new ArrayList();
    private List<DriverDataPageUiSet.Page> mList;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public DriveDataPvAdapter(Context context, List<DriverDataPageUiSet.Page> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_drive_data, (ViewGroup) null);
        DriveDataLvItemAdapter driveDataLvItemAdapter = new DriveDataLvItemAdapter(this.mContext, this.mList.get(i).getItemList());
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rv);
        int spanCount = this.mList.get(i).getSpanCount();
        if (spanCount == 0) {
            spanCount = 3;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(viewGroup.getContext(), spanCount, 1, false));
        recyclerView.setAdapter(driveDataLvItemAdapter);
        viewGroup.addView(viewInflate, 0);
        this.mDriveDataLvItemAdapterList.add(driveDataLvItemAdapter);
        return viewInflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        for (DriveDataLvItemAdapter driveDataLvItemAdapter : this.mDriveDataLvItemAdapterList) {
            if (driveDataLvItemAdapter != null) {
                driveDataLvItemAdapter.notifyDataSetChanged();
            } else {
                LogUtil.showLog("mDriveDataLvItemAdapter==null");
            }
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<DriverDataPageUiSet.Page> list = this.mList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
