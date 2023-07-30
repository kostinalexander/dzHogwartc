package com.example.Dz22.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class infoService2 implements InfoServiceImpl{

    @Value("${server.port}")
    private int serverPort;
    @Override
    public int getPort() {
        return serverPort;
    }
}
