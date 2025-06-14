package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hzbhd.canbus.view.SyncListItemView;

/* loaded from: classes2.dex */
public class SyncInfoListView extends LinearLayout {

    public interface OnListItemClickListener {
        void onItemClick(int i);
    }

    public SyncInfoListView(Context context) {
        super(context);
    }

    public SyncInfoListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initItem(Context context, int i, final OnListItemClickListener onListItemClickListener) {
        for (final int i2 = 0; i2 < i; i2++) {
            SyncListItemView syncListItemView = new SyncListItemView(context);
            addView(syncListItemView, new LinearLayout.LayoutParams(-1, -2));
            syncListItemView.setOnClickListener(new SyncListItemView.OnClickListener() { // from class: com.hzbhd.canbus.view.SyncInfoListView$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.view.SyncListItemView.OnClickListener
                public final void onItemClick() {
                    SyncInfoListView.lambda$initItem$0(onListItemClickListener, i2);
                }
            });
        }
    }

    static /* synthetic */ void lambda$initItem$0(OnListItemClickListener onListItemClickListener, int i) {
        if (onListItemClickListener != null) {
            onListItemClickListener.onItemClick(i);
        }
    }

    public SyncListItemView getItem(int i) {
        return (SyncListItemView) getChildAt(i);
    }
}
