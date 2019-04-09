package com.musicplayer.musicplayerrestapi.services;

import com.musicplayer.musicplayerrestapi.models.Song;
import com.musicplayer.musicplayerrestapi.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }

    public List<Song> findSongByTitle(String title){
        return songRepository.findByTitle(title);
    }

}
