package com.musicplayer.musicplayerrestapi.ServiceTests;

import TestUtils.TestPlaylist;
import com.musicplayer.musicplayerrestapi.PlaylistNotFoundException;
import com.musicplayer.musicplayerrestapi.models.Playlist;
import com.musicplayer.musicplayerrestapi.repositories.PlaylistRepository;
import com.musicplayer.musicplayerrestapi.services.PlaylistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    private PlaylistService playlistService;

    private Playlist myPlaylist;

    @Before
    public void setUp() throws Exception{
        playlistService = new PlaylistService(playlistRepository);
        myPlaylist = TestPlaylist.getTestPlaylist();
    }

    @Test
    public void getPlaylistByUserID_returnsUsersPlaylist(){
       given(playlistRepository.findById(1)).willReturn(Optional.of(myPlaylist));

       Playlist playlist = playlistService.getPlaylistByUserID(1);

       assertThat(playlist.getName()).isEqualTo("JMoney's Top 20");
       assertThat(playlist.getSonglist().size()).isEqualTo(myPlaylist.getSonglist().size());
    }

    @Test
    public void savePlaylist_savesThePlaylist(){
        playlistService.savePlaylist(myPlaylist);
        verify(playlistRepository, times(1)).save(myPlaylist);
    }

    @Test(expected = PlaylistNotFoundException.class)
    public void getPlaylist_whenPlaylistNotFound() throws Exception{
        given(playlistRepository.findById(1)).willReturn(Optional.empty());

        playlistService.getPlaylistByUserID(1);
    }


}
