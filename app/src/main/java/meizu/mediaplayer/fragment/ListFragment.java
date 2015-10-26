package meizu.mediaplayer.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import meizu.mediaplayer.MyApplication;
import meizu.mediaplayer.R;
import meizu.mediaplayer.adapter.PagerListAdapter;
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
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        return view;
    }

    @Override
    protected void initData() {

        //初始化数据
        MediaUtils.initSongList(MyApplication.getContext());
        System.out.println("集合个数: "+MediaUtils.musicList.size());
        //设置适配器
        mRecyclerView.setAdapter(new PagerListAdapter(MyApplication.getContext()));
    }
}
