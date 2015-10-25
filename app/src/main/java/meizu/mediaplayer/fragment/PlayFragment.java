package meizu.mediaplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import meizu.mediaplayer.R;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer.fragment
 * @Date 2015/10/25 10:48
 * @description TODO
 */
public class PlayFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.include_page_play, null);
        return view;
    }

//    private View initView() {
//        TextView view = new TextView(MyApplication.getContext());
//        view.setText("PlayFragment");
//        return view;
//    }
}
