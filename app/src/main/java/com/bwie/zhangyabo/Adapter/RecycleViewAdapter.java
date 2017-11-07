package com.bwie.zhangyabo.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.zhangyabo.Bean.bean;
import com.bwie.zhangyabo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * User: 张亚博
 * Date: 2017-11-06 09:48
 * Description：
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private List<bean.SongListBean> song_list;
    private Context context;

    public RecycleViewAdapter(List<bean.SongListBean> song_list, Context context) {
        this.song_list = song_list;
        this.context = context;
    }
    public void Replace(List<bean.SongListBean> song_list){
        this.song_list=song_list;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recycleview_itemview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final bean.SongListBean songListBean = song_list.get(position);

        String album_title = songListBean.getAlbum_title();
        holder.name.setText(album_title);

        String artist_id = songListBean.getArtist_id();
        holder.price.setText(artist_id);

        Uri parse = Uri.parse(songListBean.getPic_small());
        holder.imageview.setImageURI(parse);

        //设置条目的CheckBox的显隐状态
        boolean cbVisibla = songListBean.isCBVisibla();

        if (cbVisibla) {
            holder.cb.setVisibility(View.VISIBLE);
        }else {
            holder.cb.setVisibility(View.GONE);
        }

        holder.cb.setChecked(songListBean.isSelected());
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songListBean.isSelected()) {
                    holder.cb.setChecked(false);
                }else {
                    holder.cb.setChecked(true);
                    clickItemListener.onClickitem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return song_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox cb;
        private final SimpleDraweeView imageview;
        private final TextView name;
        private final TextView price;


        public MyViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cb);
            imageview = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

        }

    }

    private ClickItemListener clickItemListener;

    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public interface ClickItemListener{
        void onClickitem(int positon);
    }
}
