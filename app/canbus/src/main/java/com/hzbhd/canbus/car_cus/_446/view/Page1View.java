package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.data.WmSharedKey;
import com.hzbhd.canbus.car_cus._446.util.SendUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class Page1View extends LinearLayout implements CanInfoObserver {
    private Context context;
    private SelectionView selection1;
    private SelectionView selection2;
    private SelectionView selection3;
    private SwitchView switch1;
    private SwitchView switch2;
    private SwitchView switch3;
    private View view;

    public Page1View(Context context) {
        this(context, null);
    }

    public Page1View(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Page1View(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446_view_page1, (ViewGroup) this, true);
        this.view = viewInflate;
        this.switch1 = (SwitchView) viewInflate.findViewById(R.id.dou_po_huan_jiang);
        this.switch2 = (SwitchView) this.view.findViewById(R.id.dai_su_huan_xing);
        this.switch3 = (SwitchView) this.view.findViewById(R.id.zi_dong_zhu_che);
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getString(R.string._446_wm_car_4));
        arrayList.add(context.getString(R.string._446_wm_car_6));
        SelectionView selectionView = (SelectionView) this.view.findViewById(R.id.fang_xiang_pan_zhuan_xiang_zhu_li);
        this.selection1 = selectionView;
        selectionView.initItem(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(context.getString(R.string._446_wm_car_0));
        arrayList2.add(context.getString(R.string._446_wm_car_1));
        arrayList2.add(context.getString(R.string._446_wm_car_2));
        SelectionView selectionView2 = (SelectionView) this.view.findViewById(R.id.che_shen_weng_ding_xi_tong);
        this.selection2 = selectionView2;
        selectionView2.initItem(arrayList2);
        initAction();
        updateUi();
    }

    private void initAction() {
        this.switch1.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page1View.1
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) {
                if (WmCarData.douPoHuanJiang) {
                    SharePreUtil.setBoolValue(Page1View.this.context, WmSharedKey.KEY_DPHJ_SWITCH_STATE, false);
                    CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 0, 0, 0, 0, 0, 0, 0, 0, 1});
                } else {
                    SharePreUtil.setBoolValue(Page1View.this.context, WmSharedKey.KEY_DPHJ_SWITCH_STATE, true);
                    CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 1, 0, 0, 0, 0, 0, 0, 0, 1});
                }
            }
        });
        this.switch2.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page1View.2
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                if (WmCarData.daiSuHuanXing) {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 2, 0, 0, 0, 0, 0, -62});
                } else {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 1, 0, 0, 0, 0, 0, 62});
                }
            }
        });
        this.switch3.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page1View.3
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                if (WmCarData.ziDongZhuChe) {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 8, 0, 0, 0, 0, 0, -108});
                } else {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 4, 0, 0, 0, 0, 0, 21});
                }
            }
        });
        this.selection1.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page1View.4
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                Integer num = (Integer) obj;
                if (num.intValue() == 0) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
                    SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 16, 0, 32});
                    SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
                } else if (num.intValue() == 1) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
                    SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 48, 0, 32});
                    SendUtil.send(new byte[]{22, 0, 0, 3, 118, 1, -64, 0, 0, -1, 0, 0, 32});
                }
            }
        });
        this.selection2.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page1View.5
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                Integer num = (Integer) obj;
                if (num.intValue() == 0) {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, 64, 0, 0, 0, 0, 0, 0, 115});
                } else if (num.intValue() == 1) {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 0, 0, 88});
                } else if (num.intValue() == 2) {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, -64, 0, 0, 0, 0, 0, 0, 65});
                }
            }
        });
    }

    public void updateUi() {
        this.switch1.select(WmCarData.douPoHuanJiang);
        this.switch2.select(WmCarData.daiSuHuanXing);
        this.switch3.select(WmCarData.ziDongZhuChe);
        this.selection1.setValue(WmCarData.zhuanXiangLiDu);
        this.selection2.setValue(WmCarData.EspWengDing);
    }

    @Override // com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver
    public void dataChange() {
        updateUi();
    }
}
