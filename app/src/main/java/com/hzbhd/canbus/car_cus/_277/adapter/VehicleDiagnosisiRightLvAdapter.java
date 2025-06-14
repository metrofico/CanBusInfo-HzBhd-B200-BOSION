package com.hzbhd.canbus.car_cus._277.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleDiagnosisPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import java.util.List;

/* loaded from: classes2.dex */
public class VehicleDiagnosisiRightLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<VehicleDiagnosisPageUiSet.ListBean.ItemListBean> mList;
    private RightItemClickInterface mRightItemClickInterface;
    private RightItemTouchInterface mRightItemTouchInterface;

    public interface RightItemClickInterface {
        void onRightItemClick(int i);
    }

    public interface RightItemTouchInterface {
        void onRightItemTouch(int i, View view, MotionEvent motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public VehicleDiagnosisiRightLvAdapter(Context context, List<VehicleDiagnosisPageUiSet.ListBean.ItemListBean> list, RightItemClickInterface rightItemClickInterface, RightItemTouchInterface rightItemTouchInterface) {
        this.mRightItemClickInterface = rightItemClickInterface;
        this.mRightItemTouchInterface = rightItemTouchInterface;
        this.mList = list;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mList.get(i).getStyle();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i != 0 ? i != 1 ? i != 2 ? i != 3 ? 0 : R.layout.layout_item_setting_3_lv : R.layout.layout_item_setting_2_lv : R.layout.layout_item_setting_1_lv : R.layout.layout_item_setting_0_lv, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn()));
        Object value = this.mList.get(i).getValue();
        int itemViewType = getItemViewType(i);
        if (itemViewType != 0) {
            if (itemViewType != 1) {
                if (itemViewType == 2) {
                    if ((value instanceof String) && !TextUtils.isEmpty((String) value)) {
                        viewHolder.tvValue.setText(this.mList.get(i).getValue() + CommUtil.getStrByResId(this.mContext, this.mList.get(i).getUnit()));
                    }
                } else if (itemViewType == 3 && (value instanceof String)) {
                    String str = (String) value;
                    if (!TextUtils.isEmpty(str)) {
                        viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str));
                    }
                }
            } else if (value instanceof String) {
                String str2 = (String) value;
                if (TextUtils.isEmpty(str2)) {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getValueSrnArray().get(this.mList.get(i).getSelectIndex())));
                } else {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str2));
                }
            }
        } else if (value instanceof String) {
            String str3 = (String) value;
            if (!TextUtils.isEmpty(str3)) {
                viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str3));
                if ("geely_e6_item_1".equals(str3) || "geely_e6_item_2".equals(str3) || "geely_e6_item_3".equals(str3) || "geely_e6_item_4".equals(str3) || "_268_diagnosis_page6_item11_1".equals(str3) || "_268_diagnosis_page6_item11_9".equals(str3) || "_268_diagnosis_page6_item11_10".equals(str3) || "_268_diagnosis_page6_item11_12".equals(str3)) {
                    viewHolder.tvValue.setTextColor(SupportMenu.CATEGORY_MASK);
                } else {
                    viewHolder.tvValue.setTextColor(-1);
                }
            }
        } else if ((value instanceof Integer) || (value instanceof Float)) {
            viewHolder.tvValue.setText(value + CommUtil.getStrByResId(this.mContext, this.mList.get(i).getUnit()));
        }
        LogUtil.showLog("setOnClickListener");
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisiRightLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VehicleDiagnosisiRightLvAdapter.this.mRightItemClickInterface.onRightItemClick(i);
            }
        });
        viewHolder.relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._277.adapter.VehicleDiagnosisiRightLvAdapter.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (VehicleDiagnosisiRightLvAdapter.this.mRightItemTouchInterface == null) {
                    return false;
                }
                VehicleDiagnosisiRightLvAdapter.this.mRightItemTouchInterface.onRightItemTouch(i, view, motionEvent);
                return false;
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private TextView tvTitle;
        private TextView tvValue;

        ViewHolder(View view) {
            super(view);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.ll_layout);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }
}
