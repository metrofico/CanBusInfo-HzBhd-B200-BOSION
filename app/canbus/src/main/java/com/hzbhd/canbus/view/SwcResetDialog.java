package com.hzbhd.canbus.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.hzbhd.canbus.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwcResetDialog.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/hzbhd/canbus/view/SwcResetDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "onConfirm", "Lkotlin/Function0;", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "btnCancel", "Landroid/widget/Button;", "btnConfirm", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class SwcResetDialog extends Dialog {
    private final Button btnCancel;
    private final Button btnConfirm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwcResetDialog(Context context, final Function0<Unit> onConfirm) {
        super(context, R.style.style_swc_dialog);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onConfirm, "onConfirm");
        setContentView(R.layout.layout_swc_reset_dialog);
        View viewFindViewById = findViewById(R.id.btn_confirm);
        Button button = (Button) viewFindViewById;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SwcResetDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwcResetDialog.m1184lambda1$lambda0(onConfirm, this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<Button>(R.i…    dismiss()\n        } }");
        this.btnConfirm = button;
        View viewFindViewById2 = findViewById(R.id.btn_cancel);
        Button button2 = (Button) viewFindViewById2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SwcResetDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwcResetDialog.m1185lambda3$lambda2(this.f$0, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById<Button>(R.i…kListener { dismiss() } }");
        this.btnCancel = button2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-1$lambda-0, reason: not valid java name */
    public static final void m1184lambda1$lambda0(Function0 onConfirm, SwcResetDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(onConfirm, "$onConfirm");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        onConfirm.invoke();
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-3$lambda-2, reason: not valid java name */
    public static final void m1185lambda3$lambda2(SwcResetDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }
}
