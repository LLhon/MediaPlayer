package meizu.mediaplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.io.IOException;

import meizu.mediaplayer.IMusicService;

/**
 * Created by Administrator on 2015/10/28.by {ZM}
 */
public class MusicService extends Service {

    private MediaPlayer mPlayer;

    public void startPlay(String path) {
        if (mPlayer != null) {
            try {
                mPlayer.setDataSource(path);
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPlay() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }
    /**
     * 会执行多次
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 只执行一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        mPlayer.release();
        try {
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务销毁的时候执行
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 服务绑定的时候执行
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    class MusicBinder extends IMusicService.Stub {
        @Override
        public void startMusic(String path) throws RemoteException {
            startPlay(path);
        }

        @Override
        public void stopMusic() throws RemoteException {
            stopPlay();
        }
    }
}
