package com.musicplayer.musicplayerrestapi.IntegrationTests;

import TestUtils.TestPlaylist;
import com.musicplayer.musicplayerrestapi.models.Playlist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PlaylistIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;


    private Playlist myPlaylist;

    @Before
    public void testSetup() {
        myPlaylist = TestPlaylist.getTestPlaylist();

    }


    @Test
    public void postingAPlaylist_savesThePlaylist() throws Exception{

        //act
        ResponseEntity<String> response =  restTemplate.postForEntity("/playlist",myPlaylist,String.class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("saved");
    }
}
