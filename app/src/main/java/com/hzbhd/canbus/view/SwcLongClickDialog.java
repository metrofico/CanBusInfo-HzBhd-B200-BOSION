package com.hzbhd.canbus.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.SwcLongKeyAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwcLongClickDialog.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0016\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/canbus/view/SwcLongClickDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "onItemClick", "Lkotlin/Function2;", "", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function2;)V", "ibClose", "Landroid/widget/ImageButton;", "rvLongKeys", "Landroidx/recyclerview/widget/RecyclerView;", "shortKey", "show", "longKey", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class SwcLongClickDialog extends Dialog {
    private ImageButton ibClose;
    private RecyclerView rvLongKeys;
    private int shortKey;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwcLongClickDialog(final Context context, final Function2<? super Integer, ? super Integer, Unit> onItemClick) {
        super(context, R.style.style_swc_dialog);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        this.shortKey = -1;
        setContentView(R.layout.layout_swc_long_click_dialog);
        View viewFindViewById = findViewById(R.id.rv_long_keys);
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        recyclerView.setLayoutManager(new GridLayoutManager(context) { // from class: com.hzbhd.canbus.view.SwcLongClickDialog$1$1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(new SwcLongKeyAdapter(context, new Function1<Integer, Unit>() { // from class: com.hzbhd.canbus.view.SwcLongClickDialog$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                onItemClick.invoke(Integer.valueOf(this.shortKey), Integer.valueOf(i));
                this.dismiss();
            }
        }));
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<RecyclerVie…)\n            }\n        }");
        this.rvLongKeys = recyclerView;
        View viewFindViewById2 = findViewById(R.id.ib_close);
        ImageButton imageButton = (ImageButton) viewFindViewById2;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SwcLongClickDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwcLongClickDialog.m1181lambda2$lambda1(this.f$0, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById<ImageButton…kListener { dismiss() } }");
        this.ibClose = imageButton;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-2$lambda-1, reason: not valid java name */
    public static final void m1181lambda2$lambda1(SwcLongClickDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final void show(int shortKey, int longKey) {
        this.shortKey = shortKey;
        RecyclerView.Adapter adapter = this.rvLongKeys.getAdapter();
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type com.hzbhd.canbus.adapter.SwcLongKeyAdapter");
        ((SwcLongKeyAdapter) adapter).setSelectedKey(longKey);
        RecyclerView.Adapter adapter2 = this.rvLongKeys.getAdapter();
        if (adapter2 != null) {
            adapter2.notifyDataSetChanged();
        }
        show();
    }
}
