package jennie.umn.ac.musicplayer.ListLagu;

import java.io.Serializable;

public class LaguSource implements Serializable {
    private String path, title, artist, duration, album;

    public LaguSource(String path, String title, String artist, String duration, String album) {
        this.path = path;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
    }

    public LaguSource(){}

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
