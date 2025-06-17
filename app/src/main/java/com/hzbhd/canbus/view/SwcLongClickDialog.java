package com.hzbhd.canbus.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.SwcLongKeyAdapter;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public final class SwcLongClickDialog extends Dialog {
    private ImageButton ibClose;
    private final RecyclerView rvLongKeys;
    private int shortKey;

    // Constructor
    public SwcLongClickDialog(final Context context, final OnItemClickListener onItemClick) {
        super(context, R.style.style_swc_dialog);
        this.shortKey = -1;
        setContentView(R.layout.layout_swc_long_click_dialog);

        // RecyclerView setup
        RecyclerView recyclerView = findViewById(R.id.rv_long_keys);
        recyclerView.setLayoutManager(new GridLayoutManager(context, -1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        // Set up adapter
        SwcLongKeyAdapter adapter = new SwcLongKeyAdapter(context, new OnKeyClickListener() {
            @Override
            public void onKeyClick(int key) {
                onItemClick.onItemClick(shortKey, key);
                dismiss();
            }
        });

        recyclerView.setAdapter(adapter);
        this.rvLongKeys = recyclerView;

        // Close button setup
        ibClose = findViewById(R.id.ib_close);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    // Method to show dialog with shortKey and longKey
    public final void show(int shortKey, int longKey) {
        this.shortKey = shortKey;

        RecyclerView.Adapter adapter = this.rvLongKeys.getAdapter();
        if (adapter != null && adapter instanceof SwcLongKeyAdapter) {
            ((SwcLongKeyAdapter) adapter).setSelectedKey(longKey);
            adapter.notifyDataSetChanged();
        }
        show();
    }

    // Interface for handling item click
    public interface OnItemClickListener {
        void onItemClick(int shortKey, int longKey);
    }

    // Interface for handling key click events
    public interface OnKeyClickListener {
        void onKeyClick(int key);
    }
}