package meizu.mediaplayer.factory;

import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;

import meizu.mediaplayer.fragment.ListFragment;
import meizu.mediaplayer.fragment.LrcFragment;
import meizu.mediaplayer.fragment.PlayFragment;
import meizu.mediaplayer.fragment.VolumeFragment;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer.factory
 * @Date 2015/10/25 13:01
 * @description TODO
 */
public class FragmentFactory {
    private static final int FRAGMENT_PALY = 0;
    private static final int FRAGMENT_LIST = 1;
    private static final int FRAGMENT_LRC = 2;
    private static final int FRAGMENT_VOLUME = 3;

    private static SparseArrayCompat<Fragment> cacheFragments = new SparseArrayCompat<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = null;
        if (cacheFragments.get(position) != null) {
            fragment = cacheFragments.get(position);
            return fragment;
        }
        switch (position) {
            case FRAGMENT_PALY:
                fragment = new PlayFragment();
                break;
            case FRAGMENT_LIST:
                fragment = new ListFragment();
                break;
            case FRAGMENT_LRC:
                fragment = new LrcFragment();
                break;
            case FRAGMENT_VOLUME:
                fragment = new VolumeFragment();
                break;
            default:
                break;

        }
        cacheFragments.put(position, fragment);
        return fragment;
    }
}
