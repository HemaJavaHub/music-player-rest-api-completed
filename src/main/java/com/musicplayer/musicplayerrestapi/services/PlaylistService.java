package com.musicplayer.musicplayerrestapi.services;

import com.musicplayer.musicplayerrestapi.PlaylistNotFoundException;
import com.musicplayer.musicplayerrestapi.models.Playlist;
import com.musicplayer.musicplayerrestapi.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService{

    private int playlistLimit;
    private int songLimit;

    public int getPlaylistLimit() {
        return playlistLimit;
    }

    public void setPlaylistLimit(int playlistLimit) {
        this.playlistLimit = playlistLimit;
    }

    public int getSongLimit() {
        return songLimit;
    }

    public void setSongLimit(int songLimit) {
        this.songLimit = songLimit;
    }


    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }

    public Playlist getPlaylistByUserID(Integer id)  {
        Playlist playlist = playlistRepository.findById(id).orElse(null);

        if(playlist == null){
            throw new PlaylistNotFoundException();
        }
        return playlist;
    }

    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }
}
