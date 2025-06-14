package com.hzbhd.canbus.car_cus._273.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._273.GeneralCwData;
import com.hzbhd.canbus.car_cus._273.SpaceItemDecoration;
import com.hzbhd.canbus.car_cus._273.adapter.OnOffLvAdapter;
import com.hzbhd.canbus.car_cus._273.entity.OnOffBean;
import com.hzbhd.canbus.car_cus._273.entity.OnOffUpdateEntity;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CarOnOffView extends RelativeLayout implements OnOffLvAdapter.ItemClickInterface, ViewInterface {
    private int[] iconResNoSelectedArray;
    private int[] iconResSelectedArray;
    private Context mContext;
    private List<OnOffBean> mList;
    private RecyclerView mLv;
    private OnOffLvAdapter mOnOffLvAdapter;
    private View mView;
    private int[] strResArray;

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void onDestroy() {
    }

    public CarOnOffView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.strResArray = new int[]{R.string.on_off_btn_txt_0, R.string.on_off_btn_txt_1, R.string.on_off_btn_txt_2, R.string.on_off_btn_txt_3, R.string.on_off_btn_txt_4, R.string.on_off_btn_txt_5, R.string.on_off_btn_txt_6, R.string.on_off_btn_txt_7, R.string.on_off_btn_txt_8, R.string.on_off_btn_txt_9, R.string.on_off_btn_txt_10, R.string.on_off_btn_txt_11, R.string.on_off_btn_txt_12, R.string.on_off_btn_txt_13};
        this.iconResNoSelectedArray = new int[]{R.drawable.cw_kt01_mid_ic_qw_n, R.drawable.cw_kt01_mid_ic_hw_n, R.drawable.cw_kt01_mid_ic_fmq_n, R.drawable.cw_kt01_mid_ic_fs_n, R.drawable.cw_kt01_mid_ic_yy_n, R.drawable.cw_kt01_mid_ic_xlc_n, R.drawable.cw_kt01_mid_ic_dsd_n, R.drawable.cw_kt01_mid_ic_led_n, R.drawable.cw_kt01_mid_ic_dd_n, R.drawable.cw_kt01_mid_ic_hsj_n, R.drawable.cw_kt01_mid_ic_sjdd_n, R.drawable.cw_kt01_mid_ic_ad_n, R.drawable.cw_kt01_mid_ic_bd_n, R.drawable.cw_kt01_mid_ic_lp_n};
        this.iconResSelectedArray = new int[]{R.drawable.cw_kt01_mid_ic_qw_p, R.drawable.cw_kt01_mid_ic_hw_p, R.drawable.cw_kt01_mid_ic_fmq_p, R.drawable.cw_kt01_mid_ic_fs_p, R.drawable.cw_kt01_mid_ic_yy_p, R.drawable.cw_kt01_mid_ic_xlc_p, R.drawable.cw_kt01_mid_ic_dsd_p, R.drawable.cw_kt01_mid_ic_led_p, R.drawable.cw_kt01_mid_ic_dd_p, R.drawable.cw_kt01_mid_ic_hsj_p, R.drawable.cw_kt01_mid_ic_sjdd_p, R.drawable.cw_kt01_mid_ic_ad_p, R.drawable.cw_kt01_mid_ic_bd_p, R.drawable.cw_kt01_mid_ic_lp_p};
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._273_view_car_on_off, this);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mLv = (RecyclerView) this.mView.findViewById(R.id.rv);
    }

    private void initViews() {
        this.mList = new ArrayList();
        for (int i = 0; i < this.strResArray.length; i++) {
            this.mList.add(new OnOffBean(this.strResArray[i], this.iconResSelectedArray[i], this.iconResNoSelectedArray[i]));
        }
        this.mLv.setLayoutManager(new GridLayoutManager(this.mContext, 5, 1, false));
        this.mLv.addItemDecoration(new SpaceItemDecoration(30));
        OnOffLvAdapter onOffLvAdapter = new OnOffLvAdapter(this.mContext, this.mList, this);
        this.mOnOffLvAdapter = onOffLvAdapter;
        this.mLv.setAdapter(onOffLvAdapter);
    }

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void refreshUi(Bundle bundle) {
        LogUtil.showLog("CarOnOffView refreshUi");
        List<OnOffUpdateEntity> list = GeneralCwData.mOnOffUpdateList;
        if (list == null) {
            return;
        }
        for (OnOffUpdateEntity onOffUpdateEntity : list) {
            this.mList.get(onOffUpdateEntity.getIndex()).setSelected(onOffUpdateEntity.isSelect());
        }
        this.mOnOffLvAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ce  */
    @Override // com.hzbhd.canbus.car_cus._273.adapter.OnOffLvAdapter.ItemClickInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onItemClick(final int r15) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car_cus._273.view.CarOnOffView.onItemClick(int):void");
    }
}
