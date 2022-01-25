package com.example.rmi.service;

import com.example.rmi.MusicService;
import com.example.rmi.data.Music;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RMIService {
    private final MusicService musicService;
    public RMIService(MusicService musicService) {
        this.musicService = musicService;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Music> getAllMusic() {
        try {
            return objectMapper.readValue(musicService.getAllMusic(), new TypeReference<List<Music>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Music getMusicByName(String musicName) {
        try {
            return objectMapper.readValue(this.musicService.getMusicByName(musicName), Music.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
