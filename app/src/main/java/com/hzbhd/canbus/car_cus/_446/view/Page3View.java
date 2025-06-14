package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.util.SendUtil;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class Page3View extends LinearLayout implements CanInfoObserver {
    private CarLogoView logo_icon;
    private SelectionView selection1;
    private SwitchView switch1;
    private SwitchView switch2;
    private SwitchView switch3;
    private SwitchView switch4;
    private View view;

    public Page3View(Context context) {
        this(context, null);
    }

    public Page3View(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Page3View(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446_view_page3, (ViewGroup) this, true);
        this.view = viewInflate;
        this.logo_icon = (CarLogoView) viewInflate.findViewById(R.id.logo_icon);
        this.switch1 = (SwitchView) this.view.findViewById(R.id.ding_deng_men_kong);
        this.switch2 = (SwitchView) this.view.findViewById(R.id.ri_jian_xing_che_deng);
        this.switch3 = (SwitchView) this.view.findViewById(R.id.qian_zhuang_shi_deng);
        this.switch4 = (SwitchView) this.view.findViewById(R.id.dao_che_ti_shi_yin);
        ArrayList arrayList = new ArrayList();
        arrayList.add("OFF");
        arrayList.add("30s");
        arrayList.add("60s");
        arrayList.add("90s");
        arrayList.add("120s");
        SelectionView selectionView = (SelectionView) this.view.findViewById(R.id.ban_wo_hui_jia_deng);
        this.selection1 = selectionView;
        selectionView.initItem(arrayList);
        initAction();
    }

    private void initAction() {
        this.logo_icon.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page3View.1
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) {
                if (obj.equals("TURN_ON")) {
                    CanbusMsgSender.sendMsg(new byte[]{33, 3, 33, -64, -120, -14, -79, -32, -1, -1, -1});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{33, 3, 33, -64, -120, -14, -79, -32, -2, -1, -1});
                }
            }
        });
        this.switch1.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page3View.2
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                if (WmCarData.dingDengMenKong) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 64, -16, 0, 0, 0});
                } else {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, ByteCompanionObject.MIN_VALUE, -16, 0, 0, 0});
                }
            }
        });
        this.switch2.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page3View.3
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                if (WmCarData.riJianXingCheDeng) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 97, 0, 0, 0});
                } else {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 98, 0, 0, 0});
                }
            }
        });
        this.selection1.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page3View.4
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                Integer num = (Integer) obj;
                if (num.intValue() == 0) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0});
                    return;
                }
                if (num.intValue() == 1) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 32, 0, 0, 0});
                    return;
                }
                if (num.intValue() == 2) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 64, 0, 0, 0});
                } else if (num.intValue() == 3) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 96, 0, 0, 0});
                } else if (num.intValue() == 4) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0});
                }
            }
        });
        this.switch3.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page3View.5
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                if (WmCarData.qianZhuangShiDeng) {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 16, 0, 0, 0, 0, 0, 106});
                } else {
                    SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 32, 0, 0, 0, 0, 0, 106});
                }
            }
        });
        this.switch4.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page3View.6
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) throws InterruptedException {
                if (WmCarData.daoCheTiShiYin) {
                    SendUtil.send(new byte[]{22, 0, 0, 3, -115, 0, -64, 30, 16, 0, 20, 0, 0});
                } else {
                    SendUtil.send(new byte[]{22, 0, 0, 3, -115, 0, -64, 30, 16, 0, 24, 0, 0});
                }
            }
        });
    }

    public void updateUi() {
        this.switch1.select(WmCarData.dingDengMenKong);
        this.switch2.select(WmCarData.riJianXingCheDeng);
        this.selection1.setValue(WmCarData.banWoHuiJiaDeng);
        this.switch3.select(WmCarData.qianZhuangShiDeng);
        this.switch4.select(WmCarData.daoCheTiShiYin);
        this.logo_icon.updateLogo();
    }

    @Override // com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver
    public void dataChange() {
        updateUi();
    }
}
