package meizu.mediaplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public PagerListAdapter(Context context) {
        this.mContext = context;
        mMusicList = MediaUtils.musicList;
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
    public void onBindViewHolder(ListViewHolder holder, int position) {

        //显示歌曲名称
        holder.tv_title.setText(mMusicList.get(position).getTitle());
        //显示歌曲专辑
        holder.tv_artist.setText(mMusicList.get(position).getArtist());
    }

    /**
     * 创建自定义的ViewHolder的操作
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListViewHolder holder = new ListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false));
        return holder;
    }

    /**
     * 自定义ViewHolder的操作
     */
    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_artist;
        TextView tv_title;

        public ListViewHolder(View v) {
            super(v);
            tv_artist = (TextView) v.findViewById(R.id.tv_artist);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
        }
    }
}
