package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.adapter.MyAdapter;
import com.hzbhd.canbus.car_cus._283.bean.TextViewBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MyTextView extends LinearLayout {
    private List<TextViewBean> lists;
    private Context mContext;
    private MyAdapter mMyAdapter;
    private View mView;
    private int textSize;
    private int textSizeInterval;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lists = new ArrayList();
        this.textSize = 34;
        this.textSizeInterval = 0;
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_mytextview, (ViewGroup) this, true);
        this.mView = viewInflate;
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(context, this.lists);
        this.mMyAdapter = myAdapter;
        recyclerView.setAdapter(myAdapter);
    }

    public void setText(String str) {
        this.lists.clear();
        for (int i = 0; i < str.length(); i++) {
            this.lists.add(new TextViewBean(String.valueOf(str.charAt(i)), this.textSize - (this.textSizeInterval * i)));
        }
        this.mMyAdapter.notifyDataSetChanged();
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.lists.size(); i++) {
            sb.append(this.lists.get(i).getText());
        }
        return sb.toString();
    }
}
