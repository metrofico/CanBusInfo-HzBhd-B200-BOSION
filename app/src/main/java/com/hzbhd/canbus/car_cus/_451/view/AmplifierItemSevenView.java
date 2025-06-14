package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class AmplifierItemSevenView extends LinearLayout {
    private String leftUnit;
    private TextView name;
    private TextView negativeFive;
    private TextView negativeFour;
    private TextView negativeOne;
    private TextView negativeSeven;
    private TextView negativeSix;
    private TextView negativeThree;
    private TextView negativeTwo;
    private TextView numberFive;
    private TextView numberFour;
    private TextView numberOne;
    private TextView numberSeven;
    private TextView numberSix;
    private TextView numberThree;
    private TextView numberTwo;
    private String rightUnit;
    private View view;
    private TextView zero;

    public AmplifierItemSevenView(Context context) {
        this(context, null);
    }

    public AmplifierItemSevenView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AmplifierItemSevenView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.leftUnit = "";
        this.rightUnit = "";
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_amplifier_item_seven, (ViewGroup) this, true);
        this.view = viewInflate;
        this.name = (TextView) viewInflate.findViewById(R.id.name);
        this.negativeSeven = (TextView) this.view.findViewById(R.id.negative_seven);
        this.negativeSix = (TextView) this.view.findViewById(R.id.negative_six);
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
        this.numberSix = (TextView) this.view.findViewById(R.id.number_six);
        this.numberSeven = (TextView) this.view.findViewById(R.id.number_seven);
    }

    public void setTitle(String str) {
        this.name.setText(str);
    }

    public void setUnit(String str, String str2) {
        this.leftUnit = str;
        this.rightUnit = str2;
    }

    public void setData(int i) {
        if (i > 0) {
            this.zero.setText(this.rightUnit + Math.abs(i));
        } else if (i < 0) {
            this.zero.setText(this.leftUnit + Math.abs(i));
        } else {
            this.zero.setText(" ");
        }
        if (i == -7) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.orange);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == -6) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.orange);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == -5) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == -4) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == -3) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == -2) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == -1) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 0) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 1) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 2) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 3) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 4) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 5) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.transport);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 6) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.orange);
            this.numberSeven.setBackgroundResource(R.color.transport);
        }
        if (i == 7) {
            this.negativeSeven.setBackgroundResource(R.color.transport);
            this.negativeSix.setBackgroundResource(R.color.transport);
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
            this.numberSix.setBackgroundResource(R.color.orange);
            this.numberSeven.setBackgroundResource(R.color.orange);
        }
    }
}
