package com.musicplayer.musicplayerrestapi.repositories;

import com.musicplayer.musicplayerrestapi.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {
}
