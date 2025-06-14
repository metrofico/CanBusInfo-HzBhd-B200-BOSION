package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._290.SpaceItemDecoration;
import com.hzbhd.canbus.car_cus._290.adapter.MediaItenAdapter;
import com.hzbhd.canbus.car_cus._290.entity.MediaItemBean;
import com.hzbhd.canbus.util.RealKeyUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class OtherFunctionView extends RelativeLayout implements MediaItenAdapter.ItemClickInterface, ViewInterface {
    private int[] iconPressArray;
    private int[] iconReleaseArray;
    private Context mContext;
    private List<MediaItemBean> mList;
    private RecyclerView mLv;
    private MediaItenAdapter mMediaItenAdapter;
    private View mView;
    private int[] strResArray;
    private int[] targetArray;

    @Override // com.hzbhd.canbus.car_cus._290.view.ViewInterface
    public void onDestroy() {
    }

    @Override // com.hzbhd.canbus.car_cus._290.view.ViewInterface
    public void refreshUi(Bundle bundle) {
    }

    public OtherFunctionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.strResArray = new int[]{R.string._283_media_titls_2, R.string._283_media_titls_3, R.string._283_media_titls_4, R.string.bluetooth, R.string._283_media_titls_1, R.string.aux, R.string.eq};
        this.iconPressArray = new int[]{R.drawable.cw_meiti_mid_ic_music_p, R.drawable.cw_meiti_mid_ic_tu_p, R.drawable.cw_meiti_mid_ic_radio_p, R.drawable.cw_meiti_mid_ic_bt_p, R.drawable.cw_meiti_mid_ic_btmusic_p, R.drawable.cw_meiti_mid_ic_aux_p, R.drawable.cw_meiti_mid_ic_eq_p};
        this.iconReleaseArray = new int[]{R.drawable.cw_meiti_mid_ic_music_n, R.drawable.cw_meiti_mid_ic_tu_n, R.drawable.cw_meiti_mid_ic_radio_n, R.drawable.cw_meiti_mid_ic_bt_n, R.drawable.cw_meiti_mid_ic_btmusic_n, R.drawable.cw_meiti_mid_ic_aux_n, R.drawable.cw_meiti_mid_ic_eq_n};
        this.targetArray = new int[]{59, 61, 76, 68, 140, 141, 4};
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._273_view_other_function, this);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mLv = (RecyclerView) this.mView.findViewById(R.id.rv_list);
    }

    private void initViews() {
        this.mList = new ArrayList();
        for (int i = 0; i < this.strResArray.length; i++) {
            this.mList.add(new MediaItemBean(this.strResArray[i], this.iconPressArray[i], this.iconReleaseArray[i], this.targetArray[i]));
        }
        this.mLv.setLayoutManager(new GridLayoutManager(this.mContext, 5, 1, false));
        this.mLv.addItemDecoration(new SpaceItemDecoration(30));
        MediaItenAdapter mediaItenAdapter = new MediaItenAdapter(this.mContext, this.mList, this);
        this.mMediaItenAdapter = mediaItenAdapter;
        this.mLv.setAdapter(mediaItenAdapter);
    }

    @Override // com.hzbhd.canbus.car_cus._290.adapter.MediaItenAdapter.ItemClickInterface
    public void onItemClick(int i) {
        RealKeyUtil.realKeyClick(this.mContext, i);
    }
}
