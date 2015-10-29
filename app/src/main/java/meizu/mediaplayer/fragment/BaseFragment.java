package meizu.mediaplayer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer.fragment
 * @Date 2015/10/25 17:56
 * @description TODO
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;
    public View mView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }

    /**
     * 只执行一次
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = initView(inflater, container);
        initData();
        return mView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
