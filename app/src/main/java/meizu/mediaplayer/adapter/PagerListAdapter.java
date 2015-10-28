package meizu.mediaplayer.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import meizu.mediaplayer.R;
import meizu.mediaplayer.bean.Music;
import meizu.mediaplayer.utils.MediaUtils;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer.adapter
 * @Date 2015/10/25 18:02
 * @description TODO
 */
public class PagerListAdapter extends RecyclerView.Adapter<PagerListAdapter.ListViewHolder> {

    private Context mContext;
    private final List<Music> mMusicList;
    private int i = 0;

    public PagerListAdapter(Context context) {
        this.mContext = context;
        mMusicList = MediaUtils.musicList;
    }

    //定义接口, 设置监听
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }

    /**
     * 绑定ViewHolder显示数据的操作
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {

        //显示歌曲名称
        holder.tv_title.setText(mMusicList.get(position).getTitle());
        //显示歌曲专辑
        holder.tv_artist.setText(mMusicList.get(position).getArtist());
        //显示歌曲索引
        holder.tv_index.setText(++i + "");

        //回调我们自己设置的监听事件
        if(mOnItemClickListener != null) {
            holder.recyclerview_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.recyclerview_item, pos);
                    Message msg = new Message();
                    msg.what = 0;

                }
            });
            holder.recyclerview_item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.recyclerview_item, pos);
                    return false;
                }
            });
        }
    }

    /**
     * 创建自定义的ViewHolder的操作
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final ListViewHolder holder = new ListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false));
        return holder;
    }

    /**
     * 自定义ViewHolder的操作
     */
    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_artist;
        TextView tv_title;
        TextView tv_index;
        RelativeLayout recyclerview_item;

        public ListViewHolder(View v) {
            super(v);
            tv_artist = (TextView) v.findViewById(R.id.tv_artist);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
            tv_index = (TextView) v.findViewById(R.id.tv_index);
            recyclerview_item = (RelativeLayout) v.findViewById(R.id.recyclerview_item);
        }
    }
}
