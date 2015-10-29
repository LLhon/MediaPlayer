package meizu.mediaplayer.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;
import meizu.mediaplayer.MyApplication;
import meizu.mediaplayer.R;
import meizu.mediaplayer.adapter.PagerListAdapter;
import meizu.mediaplayer.utils.DebugUtil;
import meizu.mediaplayer.utils.MediaUtils;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer.fragment
 * @Date 2015/10/25 10:49
 * @description 歌曲列表页面
 */
public class ListFragment extends BaseFragment {

    //@InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private PagerListAdapter mAdapter;
    private Message mMessage;
    private Handler mHandler;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.include_page_list, null);
        //ButterKnife.inject(view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置添加item, 移除item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        //mRecyclerView.addItemDecoration();

        return view;
    }

    @Override
    protected void initData() {

        mHandler = new Handler();
        mMessage = new Message();
        //初始化数据
        MediaUtils.initSongList(MyApplication.getContext());
        System.out.println("集合个数: " + MediaUtils.musicList.size());
        //设置适配器
        mAdapter = new PagerListAdapter(MyApplication.getContext());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        //监听用户的点击item事件
        mAdapter.setOnItemClickListener(new PagerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                DebugUtil.println("------------> onClick");
                //mMessage.what = MyApplication.MUSIC_START;
                //mHandler.sendMessage(mMessage);
                EventBus.getDefault().post(MyApplication.MUSIC_START);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                showDialog();
            }
        });
    }

    public void showDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(R.string.title);
        alertDialog.setMessage(R.string.delete_music);
        alertDialog.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mRecyclerView.removeViewAt(which);
                mAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}
