package com.hzbhd.canbus.car_cus._273.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._273.GeneralCwData;
import com.hzbhd.canbus.car_cus._273.MessageSender;
import com.hzbhd.canbus.car_cus._273.adapter.OnOffLvAdapter;
import com.hzbhd.canbus.car_cus._273.entity.OnOffBean;
import com.hzbhd.canbus.car_cus._273.entity.OnOffUpdateEntity;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SpecialEqView extends RelativeLayout implements OnOffLvAdapter.ItemClickInterface, ViewInterface {
    private static final int CHANG_STATE = 0;
    private static final String TAG = "SpecialEqView";
    private int[] iconResNoSelectedArray;
    private int[] iconResSelectedArray;
    private Context mContext;
    private Handler mHandler;
    private List<OnOffBean> mList;
    private RecyclerView mLv;
    private OnOffLvAdapter mOnOffLvAdapter;
    private View mView;
    private int[] strResArray;

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void onDestroy() {
    }

    public SpecialEqView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._273.view.SpecialEqView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Log.d(SpecialEqView.TAG, message.what + "<--------->" + message.obj);
                if (message.what != 0) {
                    return;
                }
                int iIntValue = ((Integer) message.obj).intValue();
                ((OnOffBean) SpecialEqView.this.mList.get(iIntValue)).setSelected(false);
                SpecialEqView.this.setCanClick(iIntValue, true);
                SpecialEqView.this.mOnOffLvAdapter.notifyItemChanged(iIntValue);
                SpecialEqView.this.setSelectBtn(iIntValue, false);
            }
        };
        this.strResArray = new int[]{R.string.special_btn_txt_0, R.string.special_btn_txt_1, R.string.special_btn_txt_2, R.string.special_btn_txt_3, R.string.special_btn_txt_4, R.string.special_btn_txt_5, R.string.special_btn_txt_6, R.string.special_btn_txt_7, R.string.special_btn_txt_8, R.string.special_btn_txt_9};
        this.iconResNoSelectedArray = new int[]{R.drawable.cw_kt04_mid_ic_css_n, R.drawable.cw_kt04_mid_ic_lews_n, R.drawable.cw_kt04_mid_ic_aebs_n, R.drawable.cw_kt04_mid_ic_abs_n, R.drawable.cw_kt04_mid_ic_xdms_n, R.drawable.cw_kt04_mid_ic_csj1_n, R.drawable.cw_kt04_mid_ic_ecass_n, R.drawable.cw_kt04_mid_ic_ecasj_n, R.drawable.cw_kt04_mid_ic_cg_n, R.drawable.cw_kt04_mid_ic_fw_n};
        this.iconResSelectedArray = new int[]{R.drawable.cw_kt04_mid_ic_css_p, R.drawable.cw_kt04_mid_ic_lews_p, R.drawable.cw_kt04_mid_ic_aebs_p, R.drawable.cw_kt04_mid_ic_abs_p, R.drawable.cw_kt04_mid_ic_xdmsp, R.drawable.cw_kt04_mid_ic_csj1_p, R.drawable.cw_kt04_mid_ic_ecass_p, R.drawable.cw_kt04_mid_ic_ecasj_p, R.drawable.cw_kt04_mid_ic_cg_p, R.drawable.cw_kt04_mid_ic_fw_p};
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._273_view_special_eq, this);
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
        OnOffLvAdapter onOffLvAdapter = new OnOffLvAdapter(this.mContext, this.mList, this);
        this.mOnOffLvAdapter = onOffLvAdapter;
        this.mLv.setAdapter(onOffLvAdapter);
    }

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void refreshUi(Bundle bundle) {
        List<OnOffUpdateEntity> list = GeneralCwData.mSpecialEqUpdateList;
        if (list == null) {
            return;
        }
        for (OnOffUpdateEntity onOffUpdateEntity : list) {
            this.mList.get(onOffUpdateEntity.getIndex()).setSelected(onOffUpdateEntity.isSelect());
        }
        this.mOnOffLvAdapter.notifyDataSetChanged();
    }

    @Override // com.hzbhd.canbus.car_cus._273.adapter.OnOffLvAdapter.ItemClickInterface
    public void onItemClick(int i) {
        LogUtil.showLog("onItemClick:" + i);
        if (this.mList.get(i).getTitleRes() == this.strResArray[4]) {
            this.mList.get(i).setSelected(!this.mList.get(i).isSelected());
            this.mOnOffLvAdapter.notifyItemChanged(i);
            MessageSender.showCommonSwitch(5, 6, this.mList.get(i).isSelected());
        } else {
            if (this.mList.get(i).isSelected()) {
                return;
            }
            this.mList.get(i).setSelected(true);
            setCanClick(i, false);
            this.mOnOffLvAdapter.notifyItemChanged(i);
            this.mHandler.sendMessageDelayed(getMessage(0, i), 5000L);
            setSelectBtn(i, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanClick(int i, boolean z) {
        this.mList.get(i).setClickable(z);
        if (i == 0) {
            this.mList.get(5).setClickable(z);
            return;
        }
        if (i == 5) {
            this.mList.get(0).setClickable(z);
            return;
        }
        if (i == 6) {
            this.mList.get(7).setClickable(z);
            return;
        }
        if (i == 7) {
            this.mList.get(6).setClickable(z);
        } else if (i == 8) {
            this.mList.get(9).setClickable(z);
        } else if (i == 9) {
            this.mList.get(8).setClickable(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setSelectBtn(int r12, boolean r13) {
        /*
            r11 = this;
            r0 = 9
            r1 = 8
            r2 = 4
            r3 = 7
            r4 = 1
            r5 = 6
            r6 = 5
            r7 = 2
            r8 = 3
            r9 = 0
            if (r12 == r4) goto L27
            if (r12 != r7) goto L11
            goto L27
        L11:
            if (r12 == 0) goto L25
            if (r12 == r8) goto L25
            if (r12 == r6) goto L25
            if (r12 != r5) goto L1a
            goto L25
        L1a:
            if (r12 == r3) goto L23
            if (r12 == r1) goto L23
            if (r12 != r0) goto L21
            goto L23
        L21:
            r10 = r9
            goto L28
        L23:
            r10 = r6
            goto L28
        L25:
            r10 = r2
            goto L28
        L27:
            r10 = r8
        L28:
            if (r12 == r8) goto L45
            if (r12 != r3) goto L2d
            goto L45
        L2d:
            if (r12 == 0) goto L43
            if (r12 != r1) goto L32
            goto L43
        L32:
            if (r12 == r4) goto L46
            if (r12 == r6) goto L46
            if (r12 != r0) goto L39
            goto L46
        L39:
            if (r12 == r7) goto L41
            if (r12 == r5) goto L41
            r0 = 11
            if (r12 != r0) goto L45
        L41:
            r2 = r5
            goto L46
        L43:
            r2 = r7
            goto L46
        L45:
            r2 = r9
        L46:
            com.hzbhd.canbus.car_cus._273.MessageSender.showCommonSwitch(r10, r2, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car_cus._273.view.SpecialEqView.setSelectBtn(int, boolean):void");
    }

    private Message getMessage(int i, int i2) {
        Message message = new Message();
        message.what = i;
        message.obj = Integer.valueOf(i2);
        return message;
    }
}
