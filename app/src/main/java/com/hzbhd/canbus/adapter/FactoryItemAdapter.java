package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.activity.FactoryActivity;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DialogUtil;

import java.util.List;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class FactoryItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "FactoryItemAdapter";
    private final Context context;
    private final List<FactoryActivity.ItemUiSet> list;

    private final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    public FactoryItemAdapter(Context context, List<FactoryActivity.ItemUiSet> list) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(list, "list");
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.tv_title);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.tv_title)");
            this.tvTitle = (TextView) viewFindViewById;
        }

        public final TextView getTvTitle() {
            return this.tvTitle;
        }
    }

    public static class ViewHolder1 extends ViewHolder {
        private final TextView tvValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder1(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.tv_value);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.tv_value)");
            this.tvValue = (TextView) viewFindViewById;
        }

        public final TextView getTvValue() {
            return this.tvValue;
        }
    }

    public static class ViewHolder4 extends ViewHolder {
        private final Switch swStatus;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder4(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.sw_status);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.sw_status)");
            this.swStatus = (Switch) viewFindViewById;
        }

        public final Switch getSwStatus() {
            return this.swStatus;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.list.get(position).getStyle();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 1) {
            View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.layout_item_setting_1_lv, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "from(context).inflate(R.…ting_1_lv, parent, false)");
            return new ViewHolder1(viewInflate);
        }
        if (viewType == 4) {
            View viewInflate2 = LayoutInflater.from(this.context).inflate(R.layout.layout_item_setting_4_lv, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate2, "from(context).inflate(R.…ting_4_lv, parent, false)");
            return new ViewHolder4(viewInflate2);
        }
        View viewInflate3 = LayoutInflater.from(this.context).inflate(R.layout.layout_item_setting_0_lv, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate3, "from(context).inflate(R.…ting_0_lv, parent, false)");
        return new ViewHolder(viewInflate3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final FactoryActivity.ItemUiSet itemUiSet = this.list.get(position);
        ((ViewHolder) holder).getTvTitle().setText(CommUtil.getStrByResId(this.context, itemUiSet.getTitleResName()));
        int style = itemUiSet.getStyle();
        if (style == 0) {
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.FactoryItemAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    itemUiSet.getOnClick().run();
                }
            });
            return;
        }
        if (style != 1) {
            if (style != 4) {
                return;
            }
            ((ViewHolder4) holder).getSwStatus().setChecked(itemUiSet.getGetValue().get() == 1);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.FactoryItemAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewHolder4 viewHolder4 = (ViewHolder4) holder;
                    viewHolder4.getSwStatus().setChecked(!viewHolder4.getSwStatus().isChecked());
                    itemUiSet.getSetValue().accept(toInt(viewHolder4.getSwStatus().isChecked()));

                }
            });
            return;
        }
        ViewHolder1 viewHolder1 = (ViewHolder1) holder;
        final List<String> listValues = itemUiSet.getListValues();
        if (listValues != null) {
            int size = listValues.size();
            for (int i = 0; i < size; i++) {
                String strByResId = CommUtil.getStrByResId(this.context, listValues.get(i));
                listValues.set(i, strByResId);
            }
            viewHolder1.getTvValue().setText(itemUiSet.getListValues().get(itemUiSet.getGetValue().get()));
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.FactoryItemAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DialogUtil dialogUtil = DialogUtil.getInstance();

                    dialogUtil.showListDialog(context, new String[0], itemUiSet.getGetValue().get(), new DialogUtil.ListDialogCallBak() { // from class: com.hzbhd.canbus.adapter.FactoryItemAdapter$$ExternalSyntheticLambda3
                        @Override // com.hzbhd.canbus.util.DialogUtil.ListDialogCallBak
                        public void callBack(int i) {
                            ((ViewHolder1) holder).getTvValue().setText(itemUiSet.getGetValue().get());
                            itemUiSet.getSetValue().accept(i);
                        }
                    });

                }
            });
        }
    }


    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }
}
