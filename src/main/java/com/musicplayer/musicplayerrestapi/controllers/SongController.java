package com.musicplayer.musicplayerrestapi.controllers;

import com.musicplayer.musicplayerrestapi.models.Song;
import com.musicplayer.musicplayerrestapi.services.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }

    @PostMapping("/song")
    public String postSong(@RequestBody Song song){
        songService.saveSong(song);
        return "saved";
    }

    @GetMapping("/songs/{title}")
    public List<Song> getSongsByTitle(@PathVariable String title){
        return songService.findSongByTitle(title);
    }


}
