package com.musicplayer.musicplayerrestapi.models;

import com.musicplayer.musicplayerrestapi.utils.DurationToLongConverter;

import javax.persistence.*;
import java.time.Duration;

@Entity
public class Song{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String title;
	private String artist;

	@Column
	@Convert(converter = DurationToLongConverter.class)
	private Duration length;

	public Song() {	}
	
	public Song(String title, String artist, Duration length) {
		super();
		this.title = title;
		this.artist = artist;
		this.length = length;
	}

	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return this.artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Duration getLength() {
		return length;
	}

	public void setLength(Duration length) {
		this.length = length;
	}

	public String getDurationPretty() {
		long h  = length.getSeconds()/3600;
		long min = length.getSeconds()/60;
		long s = length.getSeconds()-(h*3600)-(min*60);
		return String.format("%d:%02d:%02d", h, min, s);
	}
	
}
