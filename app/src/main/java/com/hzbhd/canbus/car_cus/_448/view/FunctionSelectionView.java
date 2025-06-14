package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import java.util.List;

/* loaded from: classes2.dex */
public class FunctionSelectionView extends LinearLayout {
    private ActionCallback actionCallback;
    private LinearLayout all_lin;
    private List<String> items;
    private boolean nextPrevActionNone;
    private ImageView next_img;
    private int nowPos;
    private int posSize;
    private ImageView prev_img;
    private TextView title_txt;
    private TextView value_txt;
    private View view;

    public FunctionSelectionView(Context context) {
        this(context, null);
    }

    public FunctionSelectionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FunctionSelectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.posSize = -1;
        this.nowPos = 0;
        this.nextPrevActionNone = false;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_function_selection, (ViewGroup) this, true);
        this.view = viewInflate;
        this.prev_img = (ImageView) viewInflate.findViewById(R.id.prev_img);
        this.next_img = (ImageView) this.view.findViewById(R.id.next_img);
        this.title_txt = (TextView) this.view.findViewById(R.id.title_txt);
        this.value_txt = (TextView) this.view.findViewById(R.id.value_txt);
        this.all_lin = (LinearLayout) this.view.findViewById(R.id.all_lin);
        initAction(context);
    }

    private void initAction(Context context) {
        this.prev_img.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionSelectionView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    FunctionSelectionView.this.prev_img.setBackgroundResource(R.drawable.__448___ph8_dvr_last_p);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                FunctionSelectionView.this.prev_img.setBackgroundResource(R.drawable.__448___ph8_dvr_last_n);
                if (FunctionSelectionView.this.nowPos == 0) {
                    if (FunctionSelectionView.this.actionCallback == null) {
                        return false;
                    }
                    FunctionSelectionView.this.actionCallback.toDo(Integer.valueOf(FunctionSelectionView.this.nowPos));
                    return false;
                }
                int i = FunctionSelectionView.this.nowPos - 1;
                if (FunctionSelectionView.this.actionCallback == null) {
                    return false;
                }
                FunctionSelectionView.this.actionCallback.toDo(Integer.valueOf(i));
                return false;
            }
        });
        this.next_img.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionSelectionView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    FunctionSelectionView.this.next_img.setBackgroundResource(R.drawable.__448___ph8_dvr_next_p);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                FunctionSelectionView.this.next_img.setBackgroundResource(R.drawable.__448___ph8_dvr_next_n);
                FunctionSelectionView.this.prev_img.setBackgroundResource(R.drawable.__448___ph8_dvr_last_n);
                if (FunctionSelectionView.this.nowPos == FunctionSelectionView.this.posSize - 1) {
                    if (FunctionSelectionView.this.actionCallback == null) {
                        return false;
                    }
                    FunctionSelectionView.this.actionCallback.toDo(Integer.valueOf(FunctionSelectionView.this.nowPos));
                    return false;
                }
                int i = FunctionSelectionView.this.nowPos + 1;
                if (FunctionSelectionView.this.actionCallback == null) {
                    return false;
                }
                FunctionSelectionView.this.actionCallback.toDo(Integer.valueOf(i));
                return false;
            }
        });
        this.all_lin.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._448.view.FunctionSelectionView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!FunctionSelectionView.this.nextPrevActionNone || FunctionSelectionView.this.actionCallback == null) {
                    return;
                }
                FunctionSelectionView.this.actionCallback.toDo("ALL_ACTION");
            }
        });
    }

    public void selectItem(int i) {
        this.nowPos = i;
        List<String> list = this.items;
        if (list != null) {
            this.value_txt.setText(list.get(i).trim());
        }
    }

    public void setTitle(String str) {
        this.title_txt.setText(str);
    }

    public void setValueStr(String str) {
        this.value_txt.setText(str);
    }

    public void setItems(List<String> list) {
        this.items = list;
        this.posSize = list.size();
        selectItem(0);
    }

    public void getAction(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    public void setNextPrevActionNone(boolean z) {
        this.nextPrevActionNone = z;
        if (z) {
            this.prev_img.setVisibility(4);
            this.next_img.setVisibility(4);
        } else {
            this.prev_img.setVisibility(0);
            this.next_img.setVisibility(0);
        }
    }
}
