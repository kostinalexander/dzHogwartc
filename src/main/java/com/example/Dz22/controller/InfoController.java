package com.example.Dz22.controller;

import com.example.Dz22.service.InfoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final InfoServiceImpl infoService;

    public InfoController(InfoServiceImpl infoService) {
        this.infoService = infoService;
    }


    @GetMapping("/getPort")
    public int getPort()  {
     return infoService.getPort();
 }
}
