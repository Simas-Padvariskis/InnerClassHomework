import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Album {
    private String name;
    private String artist;
    private SongList songlist = new SongList();

    Album(String name, String artist){
        this.name = name;
        this.artist = artist;
//        songs = new ArrayList<>();
    }

    public static class SongList {
        private ArrayList<Song> songs;

        SongList() {
            songs = new ArrayList<>();
        }

        public boolean add(String title, double duration) {
            if (findSong(title) == null) {
                songs.add(new Song(title, duration));
                return true;
            } else
                return false;
        }

        private Song findSong(String title) {
            boolean check = false;
            int index = 0;
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getTitle().equals(title)) {
                    index = i;
                    check = true;
                }
            }
            if (check == false)
                return null;
            else
                return songs.get(index);
        }


        private Song findSong(int trackNumber) {

            if (songs.get(trackNumber - 1) != null) {
                return songs.get(trackNumber - 1);
            } else {
                return null;
            }
        }

        @Override
        public String toString (){
            return Arrays.toString(songs.toArray());
        }

    }


    public void addSong(String title, double duration){
        songlist.add(title, duration);
    }

    public boolean addToPlayList (int trackNumber, LinkedList<Song> playlist){
        if (songlist.findSong(trackNumber) != null) {
            playlist.add(songlist.findSong(trackNumber));
            return true;
        } else {
            return false;
        }
    }


    public boolean addToPlayList (String title, LinkedList<Song> playlist){
        if (songlist.findSong(title) != null) {
            playlist.add(songlist.findSong(title));
            return true;
        } else {
            return false;
        }
    }

    public String print (){
        return songlist.toString();
    }
}
