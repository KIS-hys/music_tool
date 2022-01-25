package com.example.client.config;

import com.example.rmi.MusicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.text.MessageFormat;

@Configuration
public class ClientConfig {
    @Value("${RMIServerUrl:localhost:1099}")
    public String RMIServerUrl;

    @Bean(name = "music")
    RmiProxyFactoryBean music() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl(MessageFormat.format("rmi://{0}/MusicService", this.RMIServerUrl));
        rmiProxyFactory.setServiceInterface(MusicService.class);
        return rmiProxyFactory;
    }
}
