package com.hzbhd.canbus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;

import java.util.List;

/* loaded from: classes.dex */
public class SettingRightLvAdapter extends RecyclerView.Adapter<SettingRightLvAdapter.ViewHolder> {
    private final Context mContext;
    private final List<SettingPageUiSet.ListBean.ItemListBean> mList;
    private final RightItemClickInterface mRightItemClickInterface;
    private final RightItemTouchInterface mRightItemTouchInterface;

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

    public SettingRightLvAdapter(Context context, List<SettingPageUiSet.ListBean.ItemListBean> list, RightItemClickInterface rightItemClickInterface, RightItemTouchInterface rightItemTouchInterface) {
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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? 0 : R.layout.layout_item_setting_4_lv : R.layout.layout_item_setting_3_lv : R.layout.layout_item_setting_2_lv : R.layout.layout_item_setting_1_lv : R.layout.layout_item_setting_0_lv, viewGroup, false));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvTitle.setText(CommUtil.getStrIdByResId(this.mContext, this.mList.get(i).getTitleSrn()));
        Object value = this.mList.get(i).getValue();
        boolean zIsValueStr = this.mList.get(i).isValueStr();
        int itemViewType = getItemViewType(i);
        if (itemViewType != 0) {
            if (itemViewType != 1) {
                if (itemViewType != 2) {
                    if (itemViewType == 3) {
                        if (value instanceof String) {
                            String str = (String) value;
                            if (!TextUtils.isEmpty(str)) {
                                viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str));
                            }
                        }
                    } else if (itemViewType == 4 && (value instanceof Integer)) {
                        viewHolder.aSwitch.setChecked(((Integer) value).intValue() == 1);
                    }
                } else if ((value instanceof String) && !TextUtils.isEmpty((String) value)) {
                    viewHolder.tvValue.setText(value + CommUtil.getStrByResId(this.mContext, this.mList.get(i).getUnit()));
                }
            } else if (value instanceof String) {
                String str2 = (String) value;
                if (TextUtils.isEmpty(str2)) {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, (String) this.mList.get(i).getValueSrnArray().get(this.mList.get(i).getSelectIndex())));
                } else {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str2));
                }
            } else {
                viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, (String) this.mList.get(i).getValueSrnArray().get(this.mList.get(i).getSelectIndex())));
            }
        } else if (value instanceof String) {
            String str3 = (String) value;
            if (!TextUtils.isEmpty(str3)) {
                if (zIsValueStr) {
                    viewHolder.tvValue.setText(str3);
                } else {
                    viewHolder.tvValue.setText(CommUtil.getStrIdByResId(this.mContext, str3));
                }
            }
        } else if ((value instanceof Integer) || (value instanceof Float)) {
            viewHolder.tvValue.setText(value + CommUtil.getStrByResId(this.mContext, this.mList.get(i).getUnit()));
        }
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SettingRightLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingRightLvAdapter.this.mRightItemClickInterface.onRightItemClick(i);
            }
        });
        // from class: com.hzbhd.canbus.adapter.SettingRightLvAdapter.2
// android.view.View.OnTouchListener
        viewHolder.relativeLayout.setOnTouchListener((view, motionEvent) -> {
            if (SettingRightLvAdapter.this.mRightItemTouchInterface == null) {
                return false;
            }
            SettingRightLvAdapter.this.mRightItemTouchInterface.onRightItemTouch(i, view, motionEvent);
            return false;
        });
        viewHolder.setVisibility(this.mList.get(i).isEnable());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final Switch aSwitch;
        private final RelativeLayout relativeLayout;
        private final TextView tvTitle;
        private final TextView tvValue;

        ViewHolder(View view) {
            super(view);
            this.relativeLayout = view.findViewById(R.id.ll_layout);
            this.tvTitle = view.findViewById(R.id.tv_title);
            this.tvValue = view.findViewById(R.id.tv_value);
            this.aSwitch = view.findViewById(R.id.sw_status);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVisibility(boolean z) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
            if (z) {
                this.itemView.setVisibility(View.VISIBLE);
                layoutParams.height = -2;
                layoutParams.width = -1;
            } else {
                this.itemView.setVisibility(View.GONE);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.itemView.setLayoutParams(layoutParams);
        }
    }
}
