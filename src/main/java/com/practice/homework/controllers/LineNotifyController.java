package com.practice.homework.controllers;


import com.practice.homework.service.LineNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@RestController
public class LineNotifyController {

    @Autowired
    private LineNotifyService lineNotifyService;

    @GetMapping("/ping")
    public String ping() throws Exception {
        return "OK";
    }

    @GetMapping("/sendMessages")
    public LinkedHashMap<String, Object> sendLineNotifyMessages(@RequestParam(required = true) String msg) throws Exception {
        return lineNotifyService.sendLineNotifyMessages(msg);
    }

    @GetMapping("/sendSticker")
    public LinkedHashMap<String, Object> sendLineNotifySticker(
            @RequestParam(required = true) String msg,
            @RequestParam(required = true) int stickerPackageId,
            @RequestParam(required = true) int stickerId) throws Exception {
        return lineNotifyService.sendLineNotifySticker(msg, stickerPackageId, stickerId);
    }

    @GetMapping("/sendImagePath")
    public LinkedHashMap<String, Object> sendLineNotifyImagePath(
            @RequestParam(required = true) String msg,
            @RequestParam(required = true) String imagePath) throws Exception {
        return lineNotifyService.sendLineNotifyImagePath(msg, imagePath);
    }

    @PostMapping("/sendImage")
    public LinkedHashMap<String, Object> sendLineNotifyImage(
            @RequestParam(required = true) String msg,
            @RequestParam("file") MultipartFile file) throws Exception {
        return lineNotifyService.sendLineNotifyImage(msg, file);
    }
}
