package meizu.mediaplayer;

import android.app.Application;
import android.content.Context;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer
 * @Date 2015/10/25 11:32
 * @description TODO
 */
public class MyApplication extends Application {

    private static Context context;
    public static final int MUSIC_START = 0;
    public static final int MUSIC_STOP = 1;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
