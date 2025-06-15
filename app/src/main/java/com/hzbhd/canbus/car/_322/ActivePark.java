package com.hzbhd.canbus.car._322;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car._322.ActivePark;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;




public final class ActivePark {
    public static final String TAG = "_322_ActivePark";
    private final ActiveParkView mActiveParkView;
    private final ActiveParkView mBackActiveParkView;
    private boolean mIsActiveViewOpen;
    private final WindowManager.LayoutParams mLayoutParams;
    private final ParkPageUiSet mParkPageUiSet;
    private final WindowManager mWindowManager;

    /* compiled from: ActivePark.kt */
    
    public enum ActiveParkElement {
        WARNING,
        FLAG,
        STOP,
        ARROW_FRONT,
        ARROW_BACK,
        ARROW_LEFTT,
        ARROW_RIGHT,
        RADAR_LEFTT,
        RADAR_RIGHT,
        WALL_PARALLEL_RIGHT,
        WALL_PARALLEL_LEFTT,
        WALL_VERTICAL_RIGHT_FORWARD,
        WALL_VERTICAL_LEFTT_FORWARD,
        WALL_VERTICAL_RIGHT_STOP_LINE,
        WALL_VERTICAL_LEFTT_STOP_LINE,
        SPACE_PARALLEL_RIGHT,
        SPACE_PARALLEL_LEFTT,
        SPACE_VERTICAL_RIGHT,
        SPACE_VERTICAL_LEFTT,
        FIND_RIGHT,
        FIND_LEFTT,
        LINE_PARALLEL_RIGHT,
        LINE_PARALLEL_LEFTT,
        LINE_VERTICAL_RIGHT,
        LINE_VERTICAL_LEFTT
    }

    public ActivePark(Context context) {

        Object systemService = context.getSystemService("window");

        this.mWindowManager = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2002;
        layoutParams.gravity = 17;
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.mLayoutParams = layoutParams;
        this.mActiveParkView = new ActiveParkView(this, context);
        this.mBackActiveParkView = new ActiveParkView(this, context);
        this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(context).getParkPageUiSet(context);
    }

    public final void updateParkTypeStatus(int value) {
        this.mActiveParkView.setParkTypeBtnStatus(value);
        this.mBackActiveParkView.setParkTypeBtnStatus(value);
    }

    public final void updateMessageAndImage(UpdateBeam beam) {

        this.mActiveParkView.clean();
        this.mActiveParkView.updateParkType(beam.getParkType());
        this.mActiveParkView.updateMessage(beam.getMessage1ResId(), beam.getMessage2ResId());
        this.mBackActiveParkView.clean();
        this.mBackActiveParkView.updateParkType(beam.getParkType());
        this.mBackActiveParkView.updateMessage(beam.getMessage1ResId(), beam.getMessage2ResId());
        ActiveParkElement[] elements = beam.getElements();
        if (elements != null) {
            this.mActiveParkView.updateImage(elements);
            this.mBackActiveParkView.updateImage(elements);
        }
    }

    public final void setActiveParkActive(boolean isActive) {
        if (isActive) {
            addActiveParkView();
            this.mParkPageUiSet.setShowCusPanoramicView(true);
        } else {
            removeActiveParkView();
            this.mParkPageUiSet.setShowCusPanoramicView(false);
        }
    }

    private final void addActiveParkView() {
        if (this.mIsActiveViewOpen) {
            return;
        }
        this.mWindowManager.addView(this.mActiveParkView, this.mLayoutParams);
        this.mIsActiveViewOpen = true;
    }

    private final void removeActiveParkView() {
        if (this.mIsActiveViewOpen) {
            this.mWindowManager.removeView(this.mActiveParkView);
            this.mIsActiveViewOpen = false;
        }
    }

    /* compiled from: ActivePark.kt */
    
    public final class ActiveParkView extends LinearLayout {
        public Map<Integer, View> _$_findViewCache;
        private final int dp100;
        private final int dp187;
        private final int dp28;
        private final int dp32;
        private final int dp42;
        private final int dp88;
        private final int dpMinus119;
        private final int dpMinus16;
        private final int dpMinus54;
        private final HashMap<ActiveParkElement, UiOption> map;
        final /* synthetic */ ActivePark this$0;

        public void _$_clearFindViewByIdCache() {
            this._$_findViewCache.clear();
        }

        public View _$_findCachedViewById(int i) {
            Map<Integer, View> map = this._$_findViewCache;
            View view = map.get(Integer.valueOf(i));
            if (view != null) {
                return view;
            }
            View viewFindViewById = findViewById(i);
            if (viewFindViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), viewFindViewById);
            return viewFindViewById;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActiveParkView(ActivePark activePark, Context context) throws Resources.NotFoundException {
            super(context);

            this.this$0 = activePark;
            this._$_findViewCache = new LinkedHashMap();
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.dp_119);
            this.dpMinus119 = dimensionPixelOffset;
            int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.dp_54);
            this.dpMinus54 = dimensionPixelOffset2;
            int dimensionPixelOffset3 = context.getResources().getDimensionPixelOffset(R.dimen.dp_16);
            this.dpMinus16 = dimensionPixelOffset3;
            int dimensionPixelOffset4 = context.getResources().getDimensionPixelOffset(R.dimen.dp32);
            this.dp32 = dimensionPixelOffset4;
            int dimensionPixelOffset5 = context.getResources().getDimensionPixelOffset(R.dimen.dp28);
            this.dp28 = dimensionPixelOffset5;
            int dimensionPixelOffset6 = context.getResources().getDimensionPixelOffset(R.dimen.dp42);
            this.dp42 = dimensionPixelOffset6;
            int dimensionPixelOffset7 = context.getResources().getDimensionPixelOffset(R.dimen.dp88);
            this.dp88 = dimensionPixelOffset7;
            int dimensionPixelOffset8 = context.getResources().getDimensionPixelOffset(R.dimen.dp100);
            this.dp100 = dimensionPixelOffset8;
            int dimensionPixelOffset9 = context.getResources().getDimensionPixelOffset(R.dimen.dp187);
            this.dp187 = dimensionPixelOffset9;
            HashMap<ActiveParkElement, UiOption> map = new HashMap<>();
            this.map = map;
            LayoutInflater.from(context).inflate(R.layout.layout_active_park_322_view, this);
            ActiveParkElement activeParkElement = ActiveParkElement.WARNING;
            ImageView iv_warning = (ImageView) _$_findCachedViewById(R.id.iv_warning);

            int i = 2;
            map.put(activeParkElement, new UiOption(iv_warning, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement2 = ActiveParkElement.FLAG;
            ImageView iv_flag = (ImageView) _$_findCachedViewById(R.id.iv_flag);

            map.put(activeParkElement2, new UiOption(iv_flag, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement3 = ActiveParkElement.STOP;
            ImageView iv_stop = (ImageView) _$_findCachedViewById(R.id.iv_stop);

            map.put(activeParkElement3, new UiOption(iv_stop, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement4 = ActiveParkElement.ARROW_FRONT;
            ImageView iv_front_arrow = (ImageView) _$_findCachedViewById(R.id.iv_front_arrow);

            map.put(activeParkElement4, new UiOption(iv_front_arrow, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement5 = ActiveParkElement.ARROW_BACK;
            ImageView iv_back_arrow = (ImageView) _$_findCachedViewById(R.id.iv_back_arrow);

            map.put(activeParkElement5, new UiOption(iv_back_arrow, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement6 = ActiveParkElement.ARROW_LEFTT;
            ImageView iv_left_arrow = (ImageView) _$_findCachedViewById(R.id.iv_left_arrow);

            map.put(activeParkElement6, new UiOption(iv_left_arrow, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement7 = ActiveParkElement.ARROW_RIGHT;
            ImageView iv_right_arrow = (ImageView) _$_findCachedViewById(R.id.iv_right_arrow);

            map.put(activeParkElement7, new UiOption(iv_right_arrow, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement8 = ActiveParkElement.RADAR_RIGHT;
            ImageView iv_right_radar = (ImageView) _$_findCachedViewById(R.id.iv_right_radar);

            map.put(activeParkElement8, new UiOption(iv_right_radar, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement9 = ActiveParkElement.RADAR_LEFTT;
            ImageView iv_left_radar = (ImageView) _$_findCachedViewById(R.id.iv_left_radar);

            map.put(activeParkElement9, new UiOption(iv_left_radar, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement10 = ActiveParkElement.WALL_PARALLEL_RIGHT;
            LinearLayout ll_right_wall = (LinearLayout) _$_findCachedViewById(R.id.ll_right_wall);

            map.put(activeParkElement10, new UiOption(ll_right_wall, null, i, 0 == true ? 1 : 0).plus(new Option.Width(dimensionPixelOffset8)).plus(new Option.MarginTop(dimensionPixelOffset4)));
            ActiveParkElement activeParkElement11 = ActiveParkElement.WALL_PARALLEL_LEFTT;
            LinearLayout ll_left_wall = (LinearLayout) _$_findCachedViewById(R.id.ll_left_wall);

            map.put(activeParkElement11, new UiOption(ll_left_wall, null, i, 0 == true ? 1 : 0).plus(new Option.Width(dimensionPixelOffset8)).plus(new Option.MarginTop(dimensionPixelOffset4)));
            ActiveParkElement activeParkElement12 = ActiveParkElement.WALL_VERTICAL_RIGHT_FORWARD;
            LinearLayout ll_right_wall2 = (LinearLayout) _$_findCachedViewById(R.id.ll_right_wall);

            map.put(activeParkElement12, new UiOption(ll_right_wall2, null, i, 0 == true ? 1 : 0).plus(new Option.Width(dimensionPixelOffset9)).plus(new Option.MarginTop(dimensionPixelOffset3)));
            ActiveParkElement activeParkElement13 = ActiveParkElement.WALL_VERTICAL_LEFTT_FORWARD;
            LinearLayout ll_left_wall2 = (LinearLayout) _$_findCachedViewById(R.id.ll_left_wall);

            map.put(activeParkElement13, new UiOption(ll_left_wall2, null, i, 0 == true ? 1 : 0).plus(new Option.Width(dimensionPixelOffset9)).plus(new Option.MarginTop(dimensionPixelOffset3)));
            ActiveParkElement activeParkElement14 = ActiveParkElement.WALL_VERTICAL_RIGHT_STOP_LINE;
            LinearLayout ll_right_wall3 = (LinearLayout) _$_findCachedViewById(R.id.ll_right_wall);

            map.put(activeParkElement14, new UiOption(ll_right_wall3, null, i, 0 == true ? 1 : 0).plus(new Option.Width(dimensionPixelOffset9)).plus(new Option.MarginTop(dimensionPixelOffset7)));
            ActiveParkElement activeParkElement15 = ActiveParkElement.WALL_VERTICAL_LEFTT_STOP_LINE;
            LinearLayout ll_left_wall3 = (LinearLayout) _$_findCachedViewById(R.id.ll_left_wall);

            map.put(activeParkElement15, new UiOption(ll_left_wall3, null, i, 0 == true ? 1 : 0).plus(new Option.Width(dimensionPixelOffset9)).plus(new Option.MarginTop(dimensionPixelOffset7)));
            ActiveParkElement activeParkElement16 = ActiveParkElement.SPACE_PARALLEL_RIGHT;
            LinearLayout ll_right_park_space = (LinearLayout) _$_findCachedViewById(R.id.ll_right_park_space);

            map.put(activeParkElement16, new UiOption(ll_right_park_space, null, i, 0 == true ? 1 : 0).plus(new Option.Height(dimensionPixelOffset9)));
            ActiveParkElement activeParkElement17 = ActiveParkElement.SPACE_PARALLEL_LEFTT;
            LinearLayout ll_left_park_space = (LinearLayout) _$_findCachedViewById(R.id.ll_left_park_space);

            map.put(activeParkElement17, new UiOption(ll_left_park_space, null, i, 0 == true ? 1 : 0).plus(new Option.Height(dimensionPixelOffset9)));
            ActiveParkElement activeParkElement18 = ActiveParkElement.SPACE_VERTICAL_RIGHT;
            LinearLayout ll_right_park_space2 = (LinearLayout) _$_findCachedViewById(R.id.ll_right_park_space);

            map.put(activeParkElement18, new UiOption(ll_right_park_space2, null, i, 0 == true ? 1 : 0).plus(new Option.Height(dimensionPixelOffset8)));
            ActiveParkElement activeParkElement19 = ActiveParkElement.SPACE_VERTICAL_LEFTT;
            LinearLayout ll_left_park_space2 = (LinearLayout) _$_findCachedViewById(R.id.ll_left_park_space);

            map.put(activeParkElement19, new UiOption(ll_left_park_space2, null, i, 0 == true ? 1 : 0).plus(new Option.Height(dimensionPixelOffset8)));
            ActiveParkElement activeParkElement20 = ActiveParkElement.FIND_RIGHT;
            ImageView iv_right_find = (ImageView) _$_findCachedViewById(R.id.iv_right_find);

            map.put(activeParkElement20, new UiOption(iv_right_find, null, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement21 = ActiveParkElement.FIND_LEFTT;
            ImageView iv_left_find = (ImageView) _$_findCachedViewById(R.id.iv_left_find);

            map.put(activeParkElement21, new UiOption(iv_left_find, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0));
            ActiveParkElement activeParkElement22 = ActiveParkElement.LINE_PARALLEL_RIGHT;
            ImageView iv_parallel_line = (ImageView) _$_findCachedViewById(R.id.iv_parallel_line);

            map.put(activeParkElement22, new UiOption(iv_parallel_line, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0).plus(new Option.MarginEnd(dimensionPixelOffset2)).plus(new Option.RotationY(0.0f)));
            ActiveParkElement activeParkElement23 = ActiveParkElement.LINE_PARALLEL_LEFTT;
            ImageView iv_parallel_line2 = (ImageView) _$_findCachedViewById(R.id.iv_parallel_line);

            map.put(activeParkElement23, new UiOption(iv_parallel_line2, null, i, 0 == true ? 1 : 0).plus(new Option.MarginEnd(dimensionPixelOffset6)).plus(new Option.RotationY(180.0f)));
            ActiveParkElement activeParkElement24 = ActiveParkElement.LINE_VERTICAL_RIGHT;
            ImageView iv_vertical_line = (ImageView) _$_findCachedViewById(R.id.iv_vertical_line);

            map.put(activeParkElement24, new UiOption(iv_vertical_line, null, i, 0 == true ? 1 : 0).plus(new Option.MarginEnd(dimensionPixelOffset)).plus(new Option.RotationY(180.0f)));
            ActiveParkElement activeParkElement25 = ActiveParkElement.LINE_VERTICAL_LEFTT;
            ImageView iv_vertical_line2 = (ImageView) _$_findCachedViewById(R.id.iv_vertical_line);

            map.put(activeParkElement25, new UiOption(iv_vertical_line2, null, i, 0 == true ? 1 : 0).plus(new Option.MarginEnd(dimensionPixelOffset5)).plus(new Option.RotationY(0.0f)));
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.car._322.ActivePark$ActiveParkView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivePark.ActiveParkView.m526_init_$lambda1(this.f$0, view);
                }
            };
            ((TextView) _$_findCachedViewById(R.id.btn_parallel_park)).setOnClickListener(onClickListener);
            ((TextView) _$_findCachedViewById(R.id.btn_vertical_park)).setOnClickListener(onClickListener);
            ((TextView) _$_findCachedViewById(R.id.btn_parallel_park_out)).setOnClickListener(onClickListener);
            ((TextView) _$_findCachedViewById(R.id.btn_close)).setOnClickListener(onClickListener);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: _init_$lambda-1, reason: not valid java name */
        public static final void m526_init_$lambda1(ActiveParkView this$0, View view) {

            if (Intrinsics.areEqual(view, (TextView) this$0._$_findCachedViewById(R.id.btn_parallel_park))) {
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 2, 0});
                return;
            }
            if (Intrinsics.areEqual(view, (TextView) this$0._$_findCachedViewById(R.id.btn_vertical_park))) {
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 3, 0});
            } else if (Intrinsics.areEqual(view, (TextView) this$0._$_findCachedViewById(R.id.btn_parallel_park_out))) {
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 4, 0});
            } else if (Intrinsics.areEqual(view, (TextView) this$0._$_findCachedViewById(R.id.btn_close))) {
                CanbusMsgSender.sendMsg(new byte[]{22, -48, 0, 0});
            }
        }

        public final void setParkTypeBtnStatus(int value) {
            ((LinearLayout) _$_findCachedViewById(R.id.ll_park_type_btns)).setVisibility(value == 0 ? 4 : 0);
            ((TextView) _$_findCachedViewById(R.id.btn_parallel_park)).setSelected(value == 2);
            ((TextView) _$_findCachedViewById(R.id.btn_vertical_park)).setSelected(value == 3);
            ((TextView) _$_findCachedViewById(R.id.btn_parallel_park_out)).setSelected(value == 4);
        }

        public final void updateParkType(int resId) {
            ((TextView) _$_findCachedViewById(R.id.tv_park_type)).setText(resId);
        }

        public final void updateMessage(int message1ResId, int message2ResId) {
            ((TextView) _$_findCachedViewById(R.id.tv_message_1)).setText(message1ResId);
            ((TextView) _$_findCachedViewById(R.id.tv_message_2)).setText(message2ResId);
        }

        public final void clean() {
            View[] viewArr = {(ImageView) _$_findCachedViewById(R.id.iv_warning), (ImageView) _$_findCachedViewById(R.id.iv_flag), (ImageView) _$_findCachedViewById(R.id.iv_stop), (ImageView) _$_findCachedViewById(R.id.iv_front_arrow), (ImageView) _$_findCachedViewById(R.id.iv_back_arrow), (ImageView) _$_findCachedViewById(R.id.iv_left_arrow), (ImageView) _$_findCachedViewById(R.id.iv_right_arrow), (ImageView) _$_findCachedViewById(R.id.iv_left_radar), (ImageView) _$_findCachedViewById(R.id.iv_right_radar), (LinearLayout) _$_findCachedViewById(R.id.ll_left_wall), (LinearLayout) _$_findCachedViewById(R.id.ll_right_wall), (ImageView) _$_findCachedViewById(R.id.iv_left_find), (ImageView) _$_findCachedViewById(R.id.iv_right_find), (ImageView) _$_findCachedViewById(R.id.iv_vertical_line), (ImageView) _$_findCachedViewById(R.id.iv_parallel_line)};
            for (int i = 0; i < 15; i++) {
                viewArr[i].setVisibility(4);
            }
        }

        public final void updateImage(ActiveParkElement[] elements) {

            for (ActiveParkElement activeParkElement : elements) {
                UiOption uiOption = this.map.get(activeParkElement);
                if (uiOption != null) {
                    for (Option option : uiOption.getOpts()) {
                        if (Intrinsics.areEqual(option, Option.Show.INSTANCE)) {
                            uiOption.getView().setVisibility(0);
                        } else if (option instanceof Option.Width) {
                            View view = uiOption.getView();
                            ViewGroup.LayoutParams layoutParams = uiOption.getView().getLayoutParams();
                            layoutParams.width = ((Option.Width) option).getValue();
                            view.setLayoutParams(layoutParams);
                        } else if (option instanceof Option.Height) {
                            View view2 = uiOption.getView();
                            ViewGroup.LayoutParams layoutParams2 = uiOption.getView().getLayoutParams();
                            layoutParams2.height = ((Option.Height) option).getValue();
                            view2.setLayoutParams(layoutParams2);
                        } else if (option instanceof Option.MarginTop) {
                            View view3 = uiOption.getView();
                            ViewGroup.LayoutParams layoutParams3 = uiOption.getView().getLayoutParams();

                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams3;
                            marginLayoutParams.topMargin = ((Option.MarginTop) option).getValue();
                            view3.setLayoutParams(marginLayoutParams);
                        } else if (option instanceof Option.MarginEnd) {
                            View view4 = uiOption.getView();
                            ViewGroup.LayoutParams layoutParams4 = uiOption.getView().getLayoutParams();

                            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams4;
                            marginLayoutParams2.setMarginEnd(((Option.MarginEnd) option).getValue());
                            view4.setLayoutParams(marginLayoutParams2);
                        } else if (option instanceof Option.RotationY) {
                            uiOption.getView().setRotationY(((Option.RotationY) option).getValue());
                        }
                    }
                }
            }
        }
    }

    /* renamed from: getActiveParkView, reason: from getter */
    public final ActiveParkView getMBackActiveParkView() {
        return this.mBackActiveParkView;
    }

    /* compiled from: ActivePark.kt */
    
    public static final /* data */ class UpdateBeam {
        private final ActiveParkElement[] elements;
        private final int message1ResId;
        private final int message2ResId;
        private final int parkType;

        public static /* synthetic */ UpdateBeam copy$default(UpdateBeam updateBeam, int i, int i2, int i3, ActiveParkElement[] activeParkElementArr, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = updateBeam.parkType;
            }
            if ((i4 & 2) != 0) {
                i2 = updateBeam.message1ResId;
            }
            if ((i4 & 4) != 0) {
                i3 = updateBeam.message2ResId;
            }
            if ((i4 & 8) != 0) {
                activeParkElementArr = updateBeam.elements;
            }
            return updateBeam.copy(i, i2, i3, activeParkElementArr);
        }

        /* renamed from: component1, reason: from getter */
        public final int getParkType() {
            return this.parkType;
        }

        /* renamed from: component2, reason: from getter */
        public final int getMessage1ResId() {
            return this.message1ResId;
        }

        /* renamed from: component3, reason: from getter */
        public final int getMessage2ResId() {
            return this.message2ResId;
        }

        /* renamed from: component4, reason: from getter */
        public final ActiveParkElement[] getElements() {
            return this.elements;
        }

        public final UpdateBeam copy(int parkType, int message1ResId, int message2ResId, ActiveParkElement[] elements) {
            return new UpdateBeam(parkType, message1ResId, message2ResId, elements);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpdateBeam)) {
                return false;
            }
            UpdateBeam updateBeam = (UpdateBeam) other;

        }

        public int hashCode() {
            int iHashCode = ((((Integer.hashCode(this.parkType) * 31) + Integer.hashCode(this.message1ResId)) * 31) + Integer.hashCode(this.message2ResId)) * 31;
            ActiveParkElement[] activeParkElementArr = this.elements;
            return iHashCode + (activeParkElementArr == null ? 0 : Arrays.hashCode(activeParkElementArr));
        }

        public String toString() {
            return "UpdateBeam(parkType=" + this.parkType + ", message1ResId=" + this.message1ResId + ", message2ResId=" + this.message2ResId + ", elements=" + Arrays.toString(this.elements) + ')';
        }

        public UpdateBeam(int i, int i2, int i3, ActiveParkElement[] activeParkElementArr) {
            this.parkType = i;
            this.message1ResId = i2;
            this.message2ResId = i3;
            this.elements = activeParkElementArr;
        }

        public final ActiveParkElement[] getElements() {
            return this.elements;
        }

        public final int getMessage1ResId() {
            return this.message1ResId;
        }

        public final int getMessage2ResId() {
            return this.message2ResId;
        }

        public final int getParkType() {
            return this.parkType;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public UpdateBeam(int i, int i2, int i3, ActiveParkElement element) {
            this(i, i2, i3, new ActiveParkElement[]{element});

        }

        public UpdateBeam(int i, int i2) {
            this(i, i2, R.string.null_value, (ActiveParkElement[]) null);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public UpdateBeam(int i, int i2, ActiveParkElement[] elements) {
            this(i, i2, R.string.null_value, elements);

        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public UpdateBeam(int i, int i2, ActiveParkElement element) {
            this(i, i2, new ActiveParkElement[]{element});

        }
    }

    /* compiled from: ActivePark.kt */
    
    public static abstract class Option {
        public /* synthetic */ Option(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: ActivePark.kt */
        
        public static final class Show extends Option {
            public static final Show INSTANCE = new Show();

            private Show() {
                super(null);
            }
        }

        private Option() {
        }

        /* compiled from: ActivePark.kt */
        
        public static final class Width extends Option {
            private final int value;

            public Width(int i) {
                super(null);
                this.value = i;
            }

            public final int getValue() {
                return this.value;
            }
        }

        /* compiled from: ActivePark.kt */
        
        public static final class Height extends Option {
            private final int value;

            public Height(int i) {
                super(null);
                this.value = i;
            }

            public final int getValue() {
                return this.value;
            }
        }

        /* compiled from: ActivePark.kt */
        
        public static final class MarginTop extends Option {
            private final int value;

            public MarginTop(int i) {
                super(null);
                this.value = i;
            }

            public final int getValue() {
                return this.value;
            }
        }

        /* compiled from: ActivePark.kt */
        
        public static final class MarginEnd extends Option {
            private final int value;

            public MarginEnd(int i) {
                super(null);
                this.value = i;
            }

            public final int getValue() {
                return this.value;
            }
        }

        /* compiled from: ActivePark.kt */
        
        public static final class RotationY extends Option {
            private final float value;

            public RotationY(float f) {
                super(null);
                this.value = f;
            }

            public final float getValue() {
                return this.value;
            }
        }
    }

    /* compiled from: ActivePark.kt */
    
    public static final class UiOption {
        private final Option[] opts;
        private final View view;

        public UiOption(View view, Option[] opts) {


            this.view = view;
            this.opts = opts;
        }

        public /* synthetic */ UiOption(View view, Option[] optionArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(view, (i & 2) != 0 ? new Option[]{Option.Show.INSTANCE} : optionArr);
        }

        public final Option[] getOpts() {
            return this.opts;
        }

        public final View getView() {
            return this.view;
        }

        public final UiOption plus(Option opt) {

            return new UiOption(this.view, (Option[]) ArraysKt.plus(this.opts, opt));
        }
    }
}
