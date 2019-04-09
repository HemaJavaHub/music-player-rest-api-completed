package com.musicplayer.musicplayerrestapi.utils;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

@Converter
public class DurationToLongConverter implements AttributeConverter<Duration,Long> {

    @Override
    public Long convertToDatabaseColumn(Duration length) {
        return length == null? null: length.getSeconds();
    }

    @Override
    public Duration convertToEntityAttribute(Long lengthInSeconds) {
        return Duration.ofSeconds(lengthInSeconds);
    }
}