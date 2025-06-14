package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class AmplifierItemView extends LinearLayout {
    private TextView name;
    private TextView negativeFive;
    private TextView negativeFour;
    private TextView negativeOne;
    private TextView negativeThree;
    private TextView negativeTwo;
    private TextView numberFive;
    private TextView numberFour;
    private TextView numberOne;
    private TextView numberThree;
    private TextView numberTwo;
    private View view;
    private TextView zero;

    public AmplifierItemView(Context context) {
        this(context, null);
    }

    public AmplifierItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AmplifierItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_amplifier_item, (ViewGroup) this, true);
        this.view = viewInflate;
        this.name = (TextView) viewInflate.findViewById(R.id.name);
        this.negativeFive = (TextView) this.view.findViewById(R.id.negative_five);
        this.negativeFour = (TextView) this.view.findViewById(R.id.negative_four);
        this.negativeThree = (TextView) this.view.findViewById(R.id.negative_three);
        this.negativeTwo = (TextView) this.view.findViewById(R.id.negative_two);
        this.negativeOne = (TextView) this.view.findViewById(R.id.negative_one);
        this.zero = (TextView) this.view.findViewById(R.id.zero);
        this.numberOne = (TextView) this.view.findViewById(R.id.number_one);
        this.numberTwo = (TextView) this.view.findViewById(R.id.number_two);
        this.numberThree = (TextView) this.view.findViewById(R.id.number_three);
        this.numberFour = (TextView) this.view.findViewById(R.id.number_four);
        this.numberFive = (TextView) this.view.findViewById(R.id.number_five);
    }

    public void setTitle(String str) {
        this.name.setText(str);
    }

    public void setData(int i) {
        this.zero.setText(String.valueOf(i));
        if (i == -5) {
            this.negativeFive.setBackgroundResource(R.color.orange);
            this.negativeFour.setBackgroundResource(R.color.orange);
            this.negativeThree.setBackgroundResource(R.color.orange);
            this.negativeTwo.setBackgroundResource(R.color.orange);
            this.negativeOne.setBackgroundResource(R.color.orange);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.transport);
            this.numberTwo.setBackgroundResource(R.color.transport);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == -4) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.orange);
            this.negativeThree.setBackgroundResource(R.color.orange);
            this.negativeTwo.setBackgroundResource(R.color.orange);
            this.negativeOne.setBackgroundResource(R.color.orange);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.transport);
            this.numberTwo.setBackgroundResource(R.color.transport);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == -3) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.orange);
            this.negativeTwo.setBackgroundResource(R.color.orange);
            this.negativeOne.setBackgroundResource(R.color.orange);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.transport);
            this.numberTwo.setBackgroundResource(R.color.transport);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == -2) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.orange);
            this.negativeOne.setBackgroundResource(R.color.orange);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.transport);
            this.numberTwo.setBackgroundResource(R.color.transport);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == -1) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.transport);
            this.negativeOne.setBackgroundResource(R.color.orange);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.transport);
            this.numberTwo.setBackgroundResource(R.color.transport);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == 0) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.transport);
            this.negativeOne.setBackgroundResource(R.color.transport);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.transport);
            this.numberTwo.setBackgroundResource(R.color.transport);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == 1) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.transport);
            this.negativeOne.setBackgroundResource(R.color.transport);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.orange);
            this.numberTwo.setBackgroundResource(R.color.transport);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == 2) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.transport);
            this.negativeOne.setBackgroundResource(R.color.transport);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.orange);
            this.numberTwo.setBackgroundResource(R.color.orange);
            this.numberThree.setBackgroundResource(R.color.transport);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == 3) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.transport);
            this.negativeOne.setBackgroundResource(R.color.transport);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.orange);
            this.numberTwo.setBackgroundResource(R.color.orange);
            this.numberThree.setBackgroundResource(R.color.orange);
            this.numberFour.setBackgroundResource(R.color.transport);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == 4) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.transport);
            this.negativeOne.setBackgroundResource(R.color.transport);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.orange);
            this.numberTwo.setBackgroundResource(R.color.orange);
            this.numberThree.setBackgroundResource(R.color.orange);
            this.numberFour.setBackgroundResource(R.color.orange);
            this.numberFive.setBackgroundResource(R.color.transport);
        }
        if (i == 5) {
            this.negativeFive.setBackgroundResource(R.color.transport);
            this.negativeFour.setBackgroundResource(R.color.transport);
            this.negativeThree.setBackgroundResource(R.color.transport);
            this.negativeTwo.setBackgroundResource(R.color.transport);
            this.negativeOne.setBackgroundResource(R.color.transport);
            this.zero.setBackgroundResource(R.color.red);
            this.numberOne.setBackgroundResource(R.color.orange);
            this.numberTwo.setBackgroundResource(R.color.orange);
            this.numberThree.setBackgroundResource(R.color.orange);
            this.numberFour.setBackgroundResource(R.color.orange);
            this.numberFive.setBackgroundResource(R.color.orange);
        }
    }
}
