package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.graphics.Color;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import java.util.List;

/* loaded from: classes2.dex */
public class DvrFileView extends LinearLayout implements View.OnClickListener, MdNotifyListener {
    TextView file1_txt;
    TextView file2_txt;
    TextView file3_txt;
    TextView file4_txt;
    TextView file5_txt;
    TextView file6_txt;
    Context mContext;
    ImageButton next_page_imb;
    TextView page_number_txt;
    ImageButton prev_page_imb;
    View view;

    @Override // com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener
    public void updateUi() {
        refreshUi();
    }

    public DvrFileView(Context context) {
        this(context, null);
    }

    public DvrFileView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DvrFileView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.view = LayoutInflater.from(context).inflate(R.layout._436_dvr_file_view, (ViewGroup) this, true);
        initData();
    }

    private void initData() {
        this.file1_txt = (TextView) this.view.findViewById(R.id.file1_txt);
        this.file2_txt = (TextView) this.view.findViewById(R.id.file2_txt);
        this.file3_txt = (TextView) this.view.findViewById(R.id.file3_txt);
        this.file4_txt = (TextView) this.view.findViewById(R.id.file4_txt);
        this.file5_txt = (TextView) this.view.findViewById(R.id.file5_txt);
        this.file6_txt = (TextView) this.view.findViewById(R.id.file6_txt);
        this.prev_page_imb = (ImageButton) this.view.findViewById(R.id.prev_page_imb);
        this.page_number_txt = (TextView) this.view.findViewById(R.id.page_number_txt);
        this.next_page_imb = (ImageButton) this.view.findViewById(R.id.next_page_imb);
        this.file1_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file2_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file3_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file4_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file5_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.file6_txt.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.prev_page_imb.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        this.next_page_imb.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.modularView.DvrFileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                this.f$0.onClick(view);
            }
        });
        refreshUi();
    }

    private String listToFileNameStr(List<Integer> list) {
        return (list.get(26).intValue() == 1 ? "EVE_" : "REC_") + (list.get(7).intValue() - 48) + "" + (list.get(8).intValue() - 48) + "" + (list.get(9).intValue() - 48) + "" + (list.get(10).intValue() - 48) + "" + (list.get(11).intValue() - 48) + "" + (list.get(12).intValue() - 48) + "" + (list.get(13).intValue() - 48) + "" + (list.get(14).intValue() - 48) + "_" + (list.get(15).intValue() - 48) + "" + (list.get(16).intValue() - 48) + "" + (list.get(17).intValue() - 48) + "" + (list.get(18).intValue() - 48) + "" + (list.get(19).intValue() - 48) + "" + (list.get(20).intValue() - 48) + "_" + (list.get(21).intValue() - 48) + "" + (list.get(22).intValue() - 48) + "" + (list.get(23).intValue() - 48) + "" + (list.get(24).intValue() - 48) + "" + (list.get(25).intValue() - 48) + ".AVI" + (list.get(26).intValue() == 1 ? "(紧急录像文件)" : "(普通录像文件)");
    }

    public void refreshUi() {
        this.page_number_txt.setText("P " + GeneralDvrFile.pageNumber);
        if (GeneralDvrFile.getInstance().item1.size() > 21) {
            this.file1_txt.setText(listToFileNameStr(GeneralDvrFile.getInstance().item1));
            if (GeneralDvrFile.getInstance().item1.get(26).intValue() == 1) {
                this.file1_txt.setTextColor(Color.parseColor("#FFB60C00"));
            } else {
                this.file1_txt.setTextColor(Color.parseColor("#ffffff"));
            }
        } else {
            this.file1_txt.setText("");
        }
        if (GeneralDvrFile.getInstance().item2.size() > 21) {
            this.file2_txt.setText(listToFileNameStr(GeneralDvrFile.getInstance().item2));
            if (GeneralDvrFile.getInstance().item2.get(26).intValue() == 1) {
                this.file2_txt.setTextColor(Color.parseColor("#FFB60C00"));
            } else {
                this.file2_txt.setTextColor(Color.parseColor("#ffffff"));
            }
        } else {
            this.file2_txt.setText("");
        }
        if (GeneralDvrFile.getInstance().item3.size() > 21) {
            this.file3_txt.setText(listToFileNameStr(GeneralDvrFile.getInstance().item3));
            if (GeneralDvrFile.getInstance().item3.get(26).intValue() == 1) {
                this.file3_txt.setTextColor(Color.parseColor("#FFB60C00"));
            } else {
                this.file3_txt.setTextColor(Color.parseColor("#ffffff"));
            }
        } else {
            this.file3_txt.setText("");
        }
        if (GeneralDvrFile.getInstance().item4.size() > 21) {
            this.file4_txt.setText(listToFileNameStr(GeneralDvrFile.getInstance().item4));
            if (GeneralDvrFile.getInstance().item4.get(26).intValue() == 1) {
                this.file4_txt.setTextColor(Color.parseColor("#FFB60C00"));
            } else {
                this.file4_txt.setTextColor(Color.parseColor("#ffffff"));
            }
        } else {
            this.file4_txt.setText("");
        }
        if (GeneralDvrFile.getInstance().item5.size() > 21) {
            this.file5_txt.setText(listToFileNameStr(GeneralDvrFile.getInstance().item5));
            if (GeneralDvrFile.getInstance().item5.get(26).intValue() == 1) {
                this.file5_txt.setTextColor(Color.parseColor("#FFB60C00"));
            } else {
                this.file5_txt.setTextColor(Color.parseColor("#ffffff"));
            }
        } else {
            this.file5_txt.setText("");
        }
        if (GeneralDvrFile.getInstance().item6.size() > 21) {
            this.file6_txt.setText(listToFileNameStr(GeneralDvrFile.getInstance().item6));
            if (GeneralDvrFile.getInstance().item6.get(26).intValue() == 1) {
                this.file6_txt.setTextColor(Color.parseColor("#FFB60C00"));
                return;
            } else {
                this.file6_txt.setTextColor(Color.parseColor("#ffffff"));
                return;
            }
        }
        this.file6_txt.setText("");
    }

    private void cleanData() {
        GeneralDvrFile.getInstance().item1.clear();
        GeneralDvrFile.getInstance().item2.clear();
        GeneralDvrFile.getInstance().item3.clear();
        GeneralDvrFile.getInstance().item4.clear();
        GeneralDvrFile.getInstance().item5.clear();
        GeneralDvrFile.getInstance().item6.clear();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws RemoteException {
        int id = view.getId();
        if (id == R.id.next_page_imb) {
            GeneralDvrFile.pageNumber++;
            cleanData();
            refreshUi();
            DvrSender.send(new byte[]{64, 38});
            this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
            return;
        }
        if (id != R.id.prev_page_imb) {
            return;
        }
        GeneralDvrFile.pageNumber--;
        cleanData();
        refreshUi();
        DvrSender.send(new byte[]{113, -1});
        this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
        this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
    }
}
