package com.musicplayer.musicplayerrestapi.ModelTests;


import com.musicplayer.musicplayerrestapi.models.Song;
import org.junit.Test;

import java.time.Duration;

public class SongTest {
	@Test
	public void getPrettyDurationTest() {
		Song mySong = new Song("Title","Artist",Duration.ofSeconds(244));
		String prettyResult = mySong.getDurationPretty();
		String expectedResult = String.format("%d:%02d:%02d", 0,4,4);
		junit.framework.Assert.assertEquals("getDurationPretty should convert a song's duration in seconds into the format mm:ss",prettyResult,expectedResult);
	}
		
}
