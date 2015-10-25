package meizu.mediaplayer.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import meizu.mediaplayer.bean.Music;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer.utils
 * @Date 2015/10/25 16:42
 * @description TODO
 */
public class MediaUtils {

    public static List<Music> musicList = new ArrayList<>();

    public static void initSongList(Context context) {
        //置空集合
        musicList.clear();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] strings = {
                MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(uri, strings, null, null, null);
            while (cursor.moveToNext()) {
                String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String path  = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                //封装javabean
                Music music = new Music(_id, title, artist, path);
                //添加对象到集合中
                musicList.add(music);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
