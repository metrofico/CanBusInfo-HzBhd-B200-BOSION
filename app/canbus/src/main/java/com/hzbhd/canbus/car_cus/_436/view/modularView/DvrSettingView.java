package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrSetting;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog2;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog3;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog4;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialogAlert;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DvrSettingView extends LinearLayout implements View.OnClickListener, MdNotifyListener {
    List<String> list1;
    List<String> list2;
    List<String> list3;
    List<String> list4;
    List<String> list5;
    List<String> list6;
    List<String> list7;
    List<String> list8;
    Context mContext;
    TextView s1_item_txt;
    TextView s1_title_txt;
    TextView s2_item_txt;
    TextView s2_title_txt;
    TextView s3_item_txt;
    TextView s3_title_txt;
    TextView s4_item_txt;
    TextView s4_title_txt;
    TextView s5_item_txt;
    TextView s5_title_txt;
    TextView s6_item_txt;
    TextView s6_title_txt;
    TextView s7_item_txt;
    TextView s7_title_txt;
    TextView s8_item_txt;
    TextView s8_title_txt;
    TextView s9_item_txt;
    TextView s9_title_txt;
    View view;

    @Override // com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener
    public void updateUi() {
        refreshUi();
    }

    public DvrSettingView(Context context) {
        this(context, null);
    }

    public DvrSettingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DvrSettingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.list1 = new ArrayList();
        this.list2 = new ArrayList();
        this.list3 = new ArrayList();
        this.list4 = new ArrayList();
        this.list5 = new ArrayList();
        this.list6 = new ArrayList();
        this.list7 = new ArrayList();
        this.list8 = new ArrayList();
        this.mContext = context;
        this.view = LayoutInflater.from(context).inflate(R.layout._436_dvr_setting_view, (ViewGroup) this, true);
        initData();
    }

    private void initData() {
        this.s1_title_txt = (TextView) this.view.findViewById(R.id.s1_title_txt);
        this.s1_item_txt = (TextView) this.view.findViewById(R.id.s1_item_txt);
        this.s1_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s1_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list1.add("640x480/30");
        this.list1.add("1280x720/30");
        this.list1.add("1920x1080/30");
        this.s2_title_txt = (TextView) this.view.findViewById(R.id.s2_title_txt);
        this.s2_item_txt = (TextView) this.view.findViewById(R.id.s2_item_txt);
        this.s2_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s2_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list2.add(this.mContext.getString(R.string._436_DVR_Setting2_0));
        this.list2.add(this.mContext.getString(R.string._436_DVR_Setting2_1));
        this.list2.add(this.mContext.getString(R.string._436_DVR_Setting2_2));
        this.s3_title_txt = (TextView) this.view.findViewById(R.id.s3_title_txt);
        this.s3_item_txt = (TextView) this.view.findViewById(R.id.s3_item_txt);
        this.s3_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s3_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list3.add(this.mContext.getString(R.string._436_DVR_Setting3_0));
        this.list3.add(this.mContext.getString(R.string._436_DVR_Setting3_1));
        this.list3.add(this.mContext.getString(R.string._436_DVR_Setting3_2));
        this.list3.add(this.mContext.getString(R.string._436_DVR_Setting3_3));
        this.s4_title_txt = (TextView) this.view.findViewById(R.id.s4_title_txt);
        this.s4_item_txt = (TextView) this.view.findViewById(R.id.s4_item_txt);
        this.s4_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s4_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list4.add(this.mContext.getString(R.string._436_DVR_Setting4_0));
        this.list4.add(this.mContext.getString(R.string._436_DVR_Setting4_1));
        this.s5_title_txt = (TextView) this.view.findViewById(R.id.s5_title_txt);
        this.s5_item_txt = (TextView) this.view.findViewById(R.id.s5_item_txt);
        this.s5_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s5_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list5.add(this.mContext.getString(R.string._436_DVR_Setting5_0));
        this.list5.add(this.mContext.getString(R.string._436_DVR_Setting5_1));
        this.s6_title_txt = (TextView) this.view.findViewById(R.id.s6_title_txt);
        this.s6_item_txt = (TextView) this.view.findViewById(R.id.s6_item_txt);
        this.s6_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s6_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list6.add(this.mContext.getString(R.string._436_DVR_Setting6_0));
        this.list6.add(this.mContext.getString(R.string._436_DVR_Setting6_1));
        this.list6.add(this.mContext.getString(R.string._436_DVR_Setting6_2));
        this.list6.add(this.mContext.getString(R.string._436_DVR_Setting6_3));
        this.s7_title_txt = (TextView) this.view.findViewById(R.id.s7_title_txt);
        this.s7_item_txt = (TextView) this.view.findViewById(R.id.s7_item_txt);
        this.s7_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s7_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list7.add(this.mContext.getString(R.string._436_DVR_Setting7_0));
        this.list7.add(this.mContext.getString(R.string._436_DVR_Setting7_1));
        this.s8_title_txt = (TextView) this.view.findViewById(R.id.s8_title_txt);
        this.s8_item_txt = (TextView) this.view.findViewById(R.id.s8_item_txt);
        this.s8_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s8_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.list8.add(this.mContext.getString(R.string._436_DVR_Setting8_0));
        this.list8.add(this.mContext.getString(R.string._436_DVR_Setting8_1));
        this.list8.add(this.mContext.getString(R.string._436_DVR_Setting8_2));
        this.s9_title_txt = (TextView) this.view.findViewById(R.id.s9_title_txt);
        this.s9_item_txt = (TextView) this.view.findViewById(R.id.s9_item_txt);
        this.s9_title_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        this.s9_item_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        refreshUi();
    }

    public void refreshUi() {
        this.s1_item_txt.setText(this.list1.get(GeneralDvrSetting.resolvingPower));
        this.s2_item_txt.setText(this.list2.get(GeneralDvrSetting.timeTag));
        this.s3_item_txt.setText(this.list3.get(GeneralDvrSetting.VideoRecordingSyncTime));
        this.s4_item_txt.setText(this.list4.get(GeneralDvrSetting.VideoRecordingVoice));
        this.s5_item_txt.setText(this.list5.get(GeneralDvrSetting.dvrLanguage));
        this.s6_item_txt.setText(this.list6.get(GeneralDvrSetting.gravitySensor));
        this.s7_item_txt.setText(this.list7.get(GeneralDvrSetting.opticalFrequency));
        this.s8_item_txt.setText(this.list8.get(GeneralDvrSetting.timeFormat));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.s1_item_txt /* 2131363227 */:
            case R.id.s1_title_txt /* 2131363228 */:
                doSetting1();
                break;
            case R.id.s2_item_txt /* 2131363229 */:
            case R.id.s2_title_txt /* 2131363230 */:
                doSetting2();
                break;
            case R.id.s3_item_txt /* 2131363231 */:
            case R.id.s3_title_txt /* 2131363232 */:
                doSetting3();
                break;
            case R.id.s4_item_txt /* 2131363233 */:
            case R.id.s4_title_txt /* 2131363234 */:
                doSetting4();
                break;
            case R.id.s5_item_txt /* 2131363235 */:
            case R.id.s5_title_txt /* 2131363236 */:
                doSetting5();
                break;
            case R.id.s6_item_txt /* 2131363237 */:
            case R.id.s6_title_txt /* 2131363238 */:
                doSetting6();
                break;
            case R.id.s7_item_txt /* 2131363239 */:
            case R.id.s7_title_txt /* 2131363240 */:
                doSetting7();
                break;
            case R.id.s8_item_txt /* 2131363241 */:
            case R.id.s8_title_txt /* 2131363242 */:
                doSetting8();
                break;
            case R.id.s9_item_txt /* 2131363243 */:
            case R.id.s9_title_txt /* 2131363244 */:
                doSetting9();
                break;
        }
    }

    private void doSetting1() {
        new ItemDialog3().showDialog(this.mContext, this.list1.get(0), this.list1.get(1), this.list1.get(2), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.1
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrSender.send(new byte[]{80, (byte) i});
            }
        });
    }

    private void doSetting2() {
        new ItemDialog3().showDialog(this.mContext, this.list2.get(0), this.list2.get(1), this.list2.get(2), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.2
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrSender.send(new byte[]{81, (byte) i});
            }
        });
    }

    private void doSetting3() {
        new ItemDialog4().showDialog(this.mContext, this.list3.get(0), this.list3.get(1), this.list3.get(2), this.list3.get(3), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.3
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                if (i == 0) {
                    DvrSender.send(new byte[]{82, 0});
                    return;
                }
                if (i == 1) {
                    DvrSender.send(new byte[]{82, 1});
                } else if (i == 2) {
                    DvrSender.send(new byte[]{82, 3});
                } else {
                    if (i != 3) {
                        return;
                    }
                    DvrSender.send(new byte[]{82, 5});
                }
            }
        });
    }

    private void doSetting4() {
        new ItemDialog2().showDialog(this.mContext, this.list4.get(0), this.list4.get(1), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.4
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrSender.send(new byte[]{83, (byte) i});
            }
        });
    }

    private void doSetting5() {
        new ItemDialog2().showDialog(this.mContext, this.list5.get(0), this.list5.get(1), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.5
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrSender.send(new byte[]{84, (byte) i});
            }
        });
    }

    private void doSetting6() {
        new ItemDialog4().showDialog(this.mContext, this.list6.get(0), this.list6.get(1), this.list6.get(2), this.list6.get(3), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.6
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                if (i == 0) {
                    DvrSender.send(new byte[]{85, 0});
                    return;
                }
                if (i == 1) {
                    DvrSender.send(new byte[]{85, 1});
                } else if (i == 2) {
                    DvrSender.send(new byte[]{85, 3});
                } else {
                    if (i != 3) {
                        return;
                    }
                    DvrSender.send(new byte[]{85, 5});
                }
            }
        });
    }

    private void doSetting7() {
        new ItemDialog2().showDialog(this.mContext, this.list7.get(0), this.list7.get(1), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.7
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrSender.send(new byte[]{86, (byte) i});
            }
        });
    }

    private void doSetting8() {
        new ItemDialog3().showDialog(this.mContext, this.list8.get(0), this.list8.get(1), this.list8.get(2), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.8
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrSender.send(new byte[]{87, (byte) i});
            }
        });
    }

    private void doSetting9() {
        ItemDialogAlert itemDialogAlert = new ItemDialogAlert();
        Context context = this.mContext;
        itemDialogAlert.showDialog(context, context.getString(R.string._436_DVR_Setting9_0), new MdOnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrSettingView.9
            @Override // com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener
            public void clickPosition(int i) throws RemoteException {
                DvrSender.send(new byte[]{92, 0});
            }
        });
    }
}
