package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class SelectedListAdapter extends BaseAdapter {
    private List<CanTypeAllEntity> mList;
    private int mNormalTextColor;
    private int mSelectPosition;
    private int mSelectTextColor;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public SelectedListAdapter(Context context, List<CanTypeAllEntity> list) {
        this.mList = list;
        this.mSelectTextColor = context.getColor(R.color.black);
        this.mNormalTextColor = context.getColor(R.color.white);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mList.size();
    }

    public void setSelectPosition(int i) {
        this.mSelectPosition = i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_dialog_list_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) view.findViewById(R.id.tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (Locale.getDefault().toString().equals("en_US")) {
            viewHolder.tv.setText(this.mList.get(i).getEnglish_car_model() + " " + this.mList.get(i).getEnglish_years());
        } else {
            viewHolder.tv.setText(this.mList.get(i).getCar_model() + " " + this.mList.get(i).getYears());
        }
        if (i == this.mSelectPosition) {
            viewHolder.tv.setBackgroundResource(R.drawable.selct_can_type_list_right_sel);
            viewHolder.tv.setTextColor(this.mSelectTextColor);
        } else {
            viewHolder.tv.setBackground(null);
            viewHolder.tv.setTextColor(this.mNormalTextColor);
        }
        return view;
    }

    public final class ViewHolder {
        public TextView tv;

        public ViewHolder() {
        }
    }
}
