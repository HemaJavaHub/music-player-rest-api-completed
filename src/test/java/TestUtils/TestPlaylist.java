package TestUtils;

import com.musicplayer.musicplayerrestapi.models.Playlist;
import com.musicplayer.musicplayerrestapi.models.Song;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPlaylist{

    private static Playlist playlist;
    private static List<Song> songs = new ArrayList<Song>(Arrays.asList(
            new Song("Billie Jean","Michael Jackson", Duration.ofSeconds(210)),
            new Song("I Would Do Anything for Love","Meatloaf",Duration.ofSeconds(756)),
            new Song("Closer","The Chainsmokers",Duration.ofSeconds(244))));


    public static Playlist getTestPlaylist(){
        playlist = new Playlist();
        playlist.setName("JMoney's Top 20");
        songs.forEach((song) -> playlist.addSong(song));
        return playlist;
    }

}
