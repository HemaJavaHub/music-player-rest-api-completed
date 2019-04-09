package com.musicplayer.musicplayerrestapi.ModelTests;

import TestUtils.TestPlaylist;
import com.musicplayer.musicplayerrestapi.models.Playlist;
import com.musicplayer.musicplayerrestapi.models.Song;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PlaylistTest {
	
	private Playlist myPlaylist;

	
	@Before
	public void testSetup() {
		myPlaylist = TestPlaylist.getTestPlaylist();
	}

	@Test
	public void newPlaylistEmptyTest() {
	    Playlist playlist = new Playlist();
		int expectedSize = 0;
		int actualSize = playlist.getSonglist().size();
		assertEquals("A new songlist should be empty",expectedSize,actualSize);
	}
	
	@Test
	public void addSongToPlaylistTest() {
        Playlist playlist = new Playlist();
        playlist.addSong(myPlaylist.getSonglist().get(1));
		int expectedSize = 1;
		int actualSize = playlist.getSonglist().size();
		assertEquals("Adding a song to the playlist should increase its size by 1",expectedSize,actualSize);
	}
	
	@Test
	public void removeSongFromPlaylistTest() {
		myPlaylist.removeSong(myPlaylist.getSonglist().get(1));
		assertEquals("Removing a song from a playlist should shorten the songlist by 1",2,myPlaylist.getSonglist().size());
		assertEquals("Removing a song from a playlist should remove only that song","Billie Jean",myPlaylist.getSonglist().get(0).getTitle());
		assertEquals("Removing a song from a playlist should remove only that song","Closer",myPlaylist.getSonglist().get(1).getTitle());

	}
	
	@Test
	public void moveSongTest() {
		myPlaylist.moveSong(2,0);
		String song0Title = myPlaylist.getSonglist().get(0).getTitle();
		String song1Title = myPlaylist.getSonglist().get(1).getTitle();
		String song2Title = myPlaylist.getSonglist().get(2).getTitle();
		assertEquals("First song is correct","Closer",song0Title);
		assertEquals("Second song is correct","Billie Jean",song1Title);
		assertEquals("Third song is correct","I Would Do Anything for Love",song2Title);
		
	}
	
	@Test
	public void getPlaylistDurationTest() {
		long expectedDurationSeconds = 1210;
		long actualDurationSeconds = myPlaylist.getPlaylistDuration().getSeconds();
		assertEquals("Total Duration of playist is equal to sum of song durations",expectedDurationSeconds,actualDurationSeconds);
	}
	
	@Test
	public void showPlaylistTest() {
		String expectedPlaylist = myPlaylist.getSonglist().get(0).getTitle()+" - "+ myPlaylist.getSonglist().get(0).getArtist()+" "+myPlaylist.getSonglist().get(0).getDurationPretty()+"\n"+
                myPlaylist.getSonglist().get(1).getTitle()+" - "+ myPlaylist.getSonglist().get(1).getArtist()+" "+myPlaylist.getSonglist().get(1).getDurationPretty()+"\n"+
                myPlaylist.getSonglist().get(2).getTitle()+" - "+ myPlaylist.getSonglist().get(2).getArtist()+" "+myPlaylist.getSonglist().get(2).getDurationPretty()+"\n";
		System.out.println(myPlaylist.getPrettyPlaylist());
		assertEquals("The showPlaylist method should produce a pretty list",expectedPlaylist,myPlaylist.getPrettyPlaylist());
	}

}
