package com.hzbhd.canbus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.SongListEntity;
import java.util.List;

/* loaded from: classes.dex */
public class SongListLvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private boolean mIsSonglistShowIndex;
    private SongItemClickInterface mItemClickInterface;
    private List<SongListEntity> mList;

    public interface SongItemClickInterface {
        void onSongItemClick(int i);

        void onSongItemLongClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SongListLvAdapter(List<SongListEntity> list, SongItemClickInterface songItemClickInterface, boolean z) {
        this.mItemClickInterface = songItemClickInterface;
        this.mList = list;
        this.mIsSonglistShowIndex = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_song_lv, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        String str = (i + 1) + ". ";
        if (!this.mIsSonglistShowIndex) {
            str = "";
        }
        viewHolder.tvTitle.setText(str + this.mList.get(i).getTitle());
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.adapter.SongListLvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SongListLvAdapter.this.mItemClickInterface.onSongItemClick(i);
            }
        });
        viewHolder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hzbhd.canbus.adapter.SongListLvAdapter.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                SongListLvAdapter.this.mItemClickInterface.onSongItemLongClick(i);
                return true;
            }
        });
        if (this.mList.get(i).isSelected()) {
            viewHolder.ivRightIcon.setVisibility(0);
        } else {
            viewHolder.ivRightIcon.setVisibility(8);
        }
        if (this.mList.get(i).isEnable()) {
            viewHolder.relativeLayout.setEnabled(true);
            viewHolder.relativeLayout.setAlpha(1.0f);
        } else {
            viewHolder.relativeLayout.setEnabled(false);
            viewHolder.relativeLayout.setAlpha(0.1f);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivRightIcon;
        private RelativeLayout relativeLayout;
        private TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.ll_layout);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.ivRightIcon = (ImageView) view.findViewById(R.id.iv_right_icon);
        }
    }
}
