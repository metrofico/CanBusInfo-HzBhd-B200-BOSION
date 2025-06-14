package com.hzbhd.canbus.car_cus._290.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._290.entity.MediaItemBean;
import java.util.List;

/* loaded from: classes2.dex */
public class MediaItenAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private ItemClickInterface mItemClickInterface;
    private List<MediaItemBean> mList;

    public interface ItemClickInterface {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MediaItenAdapter(Context context, List<MediaItemBean> list, ItemClickInterface itemClickInterface) {
        this.mItemClickInterface = itemClickInterface;
        this.mList = list;
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._273_layout_on_off_btn, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(this.mList.get(i).getTitleRes());
        viewHolder.btnView.setBackground(this.mContext.getDrawable(this.mList.get(i).getIconReleaseRes()));
        viewHolder.btnView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._290.adapter.MediaItenAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaItenAdapter.this.mItemClickInterface.onItemClick(((MediaItemBean) MediaItenAdapter.this.mList.get(i)).getTarget());
            }
        });
        viewHolder.btnView.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._290.adapter.MediaItenAdapter.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    viewHolder.btnView.setBackground(MediaItenAdapter.this.mContext.getDrawable(((MediaItemBean) MediaItenAdapter.this.mList.get(i)).getIconPressRes()));
                    return false;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                viewHolder.btnView.setBackground(MediaItenAdapter.this.mContext.getDrawable(((MediaItemBean) MediaItenAdapter.this.mList.get(i)).getIconReleaseRes()));
                return false;
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton btnView;
        private TextView textView;

        ViewHolder(View view) {
            super(view);
            this.btnView = (ImageButton) view.findViewById(R.id.ibt_item);
            this.textView = (TextView) view.findViewById(R.id.tv_item);
        }
    }
}
