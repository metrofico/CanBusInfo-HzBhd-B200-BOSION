package com.hzbhd.canbus.car_cus._277.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._277.ui_set.VehicleMonitorPageUiSet;
import com.hzbhd.canbus.util.CommUtil;

import java.util.List;

/* loaded from: classes2.dex */
public class VehicleMonitorRightLvAdapter extends RecyclerView.Adapter<VehicleMonitorRightLvAdapter.ViewHolder> {
    private Context mContext;
    private List<VehicleMonitorPageUiSet.ListBean.ItemListBean> mList;
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

    public VehicleMonitorRightLvAdapter(Context context, List<VehicleMonitorPageUiSet.ListBean.ItemListBean> list, RightItemClickInterface rightItemClickInterface, RightItemTouchInterface rightItemTouchInterface) {
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
        int i2 = R.layout.layout_item_setting_0_lv;
        if (i != 0) {
            if (i == 1) {
                i2 = R.layout.layout_item_setting_1_lv;
            } else if (i == 2) {
                i2 = R.layout.layout_item_setting_2_lv;
            } else if (i == 3) {
                i2 = R.layout.layout_item_setting_3_lv;
            } else if (i != 4) {
                i2 = i != 5 ? 0 : R.layout._277_layout_item_setting_5_lv;
            }
        }
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn()));
        Object value = this.mList.get(i).getValue();
        String strByResId = CommUtil.getStrByResId(this.mContext, this.mList.get(i).getUnit());
        int itemViewType = getItemViewType(i);
        if (itemViewType != 0) {
            if (itemViewType == 1) {
                String str = (String) value;
                if (TextUtils.isEmpty(str)) {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, String.valueOf(this.mList.get(i).getValueSrnArray().get(this.mList.get(i).getSelectIndex()))));
                } else {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str));
                }
            } else if (itemViewType != 2) {
                if (itemViewType != 4) {
                    if (itemViewType == 5) {
                        if (!(value instanceof Integer) && !(value instanceof Float)) {
                            if (this.mList.get(i).getValue() instanceof String) {
                                String str2 = (String) value;
                                if (!TextUtils.isEmpty(str2)) {
                                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str2));
                                }
                            }
                        } else {
                            viewHolder.tvValue.setText(String.valueOf(value) + strByResId);
                        }
                        if (i == 0) {
                            viewHolder.tvIcon.setBackground(this.mContext.getDrawable(R.drawable.__268_car_set_icon_0));
                        } else if (i == 1) {
                            viewHolder.tvIcon.setBackground(this.mContext.getDrawable(R.drawable.__268_car_set_icon_1));
                        } else if (i == 2) {
                            viewHolder.tvIcon.setBackground(this.mContext.getDrawable(R.drawable.__268_car_set_icon_2));
                        } else if (i == 3) {
                            viewHolder.tvIcon.setBackground(this.mContext.getDrawable(R.drawable.__268_car_set_icon_3));
                        }
                    }
                } else if (!(value instanceof Integer) && !(value instanceof Float)) {
                    if (this.mList.get(i).getValue() instanceof String) {
                        String str3 = (String) value;
                        if (!TextUtils.isEmpty(str3)) {
                            viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str3));
                        }
                    }
                } else {
                    viewHolder.tvTitle.setText(Integer.toString((i + 1) - 7) + ((Object) viewHolder.tvTitle.getText()));
                    viewHolder.tvValue.setText(String.valueOf(value) + strByResId);
                }
            } else if (!TextUtils.isEmpty((String) value)) {
                viewHolder.tvValue.setText(this.mList.get(i).getValue() + CommUtil.getStrByResId(this.mContext, this.mList.get(i).getUnit()));
            }
        } else if (!(value instanceof Integer) && !(value instanceof Float)) {
            if (this.mList.get(i).getValue() instanceof String) {
                String str4 = (String) value;
                if (!TextUtils.isEmpty(str4)) {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str4));
                }
            }
        } else {
            viewHolder.tvValue.setText(String.valueOf(value) + strByResId);
        }
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._277.adapter.VehicleMonitorRightLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VehicleMonitorRightLvAdapter.this.mRightItemClickInterface.onRightItemClick(i);
            }
        });
        viewHolder.relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._277.adapter.VehicleMonitorRightLvAdapter.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (VehicleMonitorRightLvAdapter.this.mRightItemTouchInterface == null) {
                    return false;
                }
                VehicleMonitorRightLvAdapter.this.mRightItemTouchInterface.onRightItemTouch(i, view, motionEvent);
                return false;
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private TextView tvIcon;
        private TextView tvTitle;
        private TextView tvValue;

        ViewHolder(View view) {
            super(view);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.ll_layout);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.tvValue = (TextView) view.findViewById(R.id.tv_value);
            this.tvIcon = (TextView) view.findViewById(R.id.tv_icon);
        }
    }
}
