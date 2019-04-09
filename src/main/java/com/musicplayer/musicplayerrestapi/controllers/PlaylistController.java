package com.musicplayer.musicplayerrestapi.controllers;

import com.musicplayer.musicplayerrestapi.models.Playlist;
import com.musicplayer.musicplayerrestapi.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlist-limit")
    public String getPlaylistLimit(){
        return "The playlistLimit="+playlistService.getPlaylistLimit();
    }

    @GetMapping("/playlists/{id}")
    private Playlist getPlaylistByUserID(@PathVariable Integer id){
        return playlistService.getPlaylistByUserID(id);
    }

    @PostMapping("/playlist")
    private String savePlaylist(@RequestBody Playlist playlist){
        playlistService.savePlaylist(playlist);
        return "saved";
    }

}
