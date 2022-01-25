package com.example.server.service;

import com.example.rmi.MusicService;
import com.example.server.dao.MusicDao;
import com.example.server.repository.MusicRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public String getMusicByName(String name) {
        try {
            MusicDao music = musicRepository.getByName(name);
            return new ObjectMapper().writeValueAsString(music);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAllMusic() {
        try {
            return new ObjectMapper().writeValueAsString(Lists.newArrayList(musicRepository.findAll()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
