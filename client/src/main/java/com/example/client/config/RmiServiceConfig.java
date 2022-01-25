package com.example.client.config;

import com.example.rmi.MusicService;
import com.example.rmi.service.RMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RmiServiceConfig {
    @Autowired
    MusicService musicService;

    @Bean
    RMIService rmiService() {
        return new RMIService(musicService);
    }
}
