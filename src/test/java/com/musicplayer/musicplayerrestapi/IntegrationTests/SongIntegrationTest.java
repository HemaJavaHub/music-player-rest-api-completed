package com.musicplayer.musicplayerrestapi.IntegrationTests;

import TestUtils.TestSongs;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.musicplayer.musicplayerrestapi.models.Song;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SongIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postingSong_savesTheSong() throws JsonProcessingException {
        //act
        ResponseEntity<String> response = restTemplate.postForEntity("/song", TestSongs.getSongs().get(1),String.class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("saved");
    }

    @Test
    public void searchForSongByName_returnsSongsWithMatchingNames() throws Exception {
        //arrange
        restTemplate.postForEntity("/song", TestSongs.getSongs().get(0),String.class);

        //act
        ResponseEntity<Song[]> response = restTemplate.getForEntity("/songs/africa", Song[].class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        for (Song song : response.getBody()) {
            assertThat(song.getTitle()).contains("Africa");
        }
    }
}




