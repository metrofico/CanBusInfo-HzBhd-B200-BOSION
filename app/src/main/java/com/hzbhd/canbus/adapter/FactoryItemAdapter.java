package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.FactoryActivity;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DialogUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FactoryItemAdapter.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u0016\u0017\u0018\u0019B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0016J\f\u0010\u0014\u001a\u00020\n*\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "list", "", "Lcom/hzbhd/canbus/activity/FactoryActivity$ItemUiSet;", "(Landroid/content/Context;Ljava/util/List;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "toInt", "", "Companion", "ViewHolder", "ViewHolder1", "ViewHolder4", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
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

    /* compiled from: FactoryItemAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "tvTitle", "Landroid/widget/TextView;", "getTvTitle", "()Landroid/widget/TextView;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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

    /* compiled from: FactoryItemAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder1;", "Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "tvValue", "Landroid/widget/TextView;", "getTvValue", "()Landroid/widget/TextView;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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

    /* compiled from: FactoryItemAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder4;", "Lcom/hzbhd/canbus/adapter/FactoryItemAdapter$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "swStatus", "Landroid/widget/Switch;", "getSwStatus", "()Landroid/widget/Switch;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
                    FactoryItemAdapter.m38onBindViewHolder$lambda6$lambda0(itemUiSet, view);
                }
            });
            return;
        }
        if (style != 1) {
            if (style != 4) {
                return;
            }
            ((ViewHolder4) holder).getSwStatus().setChecked(itemUiSet.getGetValue().invoke().intValue() == 1);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.FactoryItemAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FactoryItemAdapter.m41onBindViewHolder$lambda6$lambda5(holder, itemUiSet, this, view);
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
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context, get(it))");
                listValues.set(i, strByResId);
            }
            viewHolder1.getTvValue().setText(itemUiSet.getListValues().get(itemUiSet.getGetValue().invoke().intValue()));
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.FactoryItemAdapter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FactoryItemAdapter.m39onBindViewHolder$lambda6$lambda4$lambda3(this.f$0, listValues, itemUiSet, holder, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-6$lambda-0, reason: not valid java name */
    public static final void m38onBindViewHolder$lambda6$lambda0(FactoryActivity.ItemUiSet this_run, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this_run.getOnClick().invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-6$lambda-4$lambda-3, reason: not valid java name */
    public static final void m39onBindViewHolder$lambda6$lambda4$lambda3(FactoryItemAdapter this$0, final List this_run, final FactoryActivity.ItemUiSet this_run$1, final RecyclerView.ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this_run$1, "$this_run$1");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        DialogUtil dialogUtil = DialogUtil.getInstance();
        Context context = this$0.context;
        Object[] array = this_run.toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        dialogUtil.showListDialog(context, (String[]) array, this_run$1.getGetValue().invoke().intValue(), new DialogUtil.ListDialogCallBak() { // from class: com.hzbhd.canbus.adapter.FactoryItemAdapter$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.util.DialogUtil.ListDialogCallBak
            public final void callBack(int i) {
                FactoryItemAdapter.m40onBindViewHolder$lambda6$lambda4$lambda3$lambda2(holder, this_run, this_run$1, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-6$lambda-4$lambda-3$lambda-2, reason: not valid java name */
    public static final void m40onBindViewHolder$lambda6$lambda4$lambda3$lambda2(RecyclerView.ViewHolder holder, List this_run, FactoryActivity.ItemUiSet this_run$1, int i) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this_run$1, "$this_run$1");
        ((ViewHolder1) holder).getTvValue().setText((CharSequence) this_run.get(i));
        this_run$1.getSetValue().invoke(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-6$lambda-5, reason: not valid java name */
    public static final void m41onBindViewHolder$lambda6$lambda5(RecyclerView.ViewHolder holder, FactoryActivity.ItemUiSet this_run, FactoryItemAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewHolder4 viewHolder4 = (ViewHolder4) holder;
        viewHolder4.getSwStatus().setChecked(!viewHolder4.getSwStatus().isChecked());
        this_run.getSetValue().invoke(Integer.valueOf(this$0.toInt(viewHolder4.getSwStatus().isChecked())));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }
}
