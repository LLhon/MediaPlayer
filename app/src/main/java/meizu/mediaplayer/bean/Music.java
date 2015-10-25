package meizu.mediaplayer.bean;

/**
 * @author 刘和鸿
 * @Project MediaPlayer
 * @Package meizu.mediaplayer.bean
 * @Date 2015/10/25 16:40
 * @description TODO
 */
public class Music {
    
    //id
    private String id;
    //歌手姓名
    private String title;
    //歌曲专辑
    private String artist;
    //歌曲路径
    private String path;

    public Music(String id, String title, String artist, String path) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.path = path;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
