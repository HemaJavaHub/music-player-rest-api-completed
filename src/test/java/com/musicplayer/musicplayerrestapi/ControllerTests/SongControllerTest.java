package com.musicplayer.musicplayerrestapi.ControllerTests;

import TestUtils.TestSongs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicplayer.musicplayerrestapi.controllers.SongController;
import com.musicplayer.musicplayerrestapi.models.Song;
import com.musicplayer.musicplayerrestapi.services.SongService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SongController.class)
public class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void postingSong_savesTheSong() throws Exception{
        Song song = TestSongs.getSongs().get(0);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/song")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(song));
        mockMvc.perform(builder).andExpect(status().isOk());
        verify(songService, times(1)).saveSong(any(Song.class));
    }

    @Test
    public void findSongByName_returnsSongsWithThatName() throws Exception {
        //arrange
        List<Song> songs = new ArrayList<>();
        songs.add(TestSongs.getSongs().get(0));
        songs.add(TestSongs.getSongs().get(1));

        when(songService.findSongByTitle("Africa")).thenReturn(songs);

        mockMvc.perform(MockMvcRequestBuilders.get("/songs/Africa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Africa")))
                .andExpect(jsonPath("$[1].title", is("Africa")));

        verify(songService, times(1)).findSongByTitle("Africa");
        verifyNoMoreInteractions(songService);
    }

}
