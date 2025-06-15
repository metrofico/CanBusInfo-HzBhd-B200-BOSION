package com.hzbhd.canbus.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzbhd.R;

/* loaded from: classes2.dex */
public class DialogListAdapter extends BaseAdapter {
    private final Context mContext;
    private int mSelectIndex;
    private final String[] mStrArray;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public DialogListAdapter(Context context, String[] strArr) {
        this.mContext = context;
        this.mStrArray = strArr;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mStrArray.length;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(this.mContext, R.layout.layout_dialog_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = view.findViewById(R.id.tv);
            viewHolder.imageView = view.findViewById(R.id.iv_select);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (this.mSelectIndex == i) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imageView.setVisibility(View.GONE);
        }
        viewHolder.textView.setText(this.mStrArray[i]);
        return view;
    }

    public class ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder() {
        }
    }

    public void setSelectedIndex(int i) {
        this.mSelectIndex = i;
    }
}
