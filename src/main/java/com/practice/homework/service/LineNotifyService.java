package com.practice.homework.service;

import java.util.LinkedHashMap;
import org.springframework.web.multipart.MultipartFile;

public interface LineNotifyService {
    LinkedHashMap<String, Object> sendLineNotifyMessages(String msg) throws Exception;
    LinkedHashMap<String, Object> sendLineNotifySticker(String msg, int stickerPackageId, int stickerId) throws Exception;
    LinkedHashMap<String, Object> sendLineNotifyImagePath(String msg, String imagePath) throws Exception;
    LinkedHashMap<String, Object> sendLineNotifyImage(String msg, MultipartFile file) throws Exception;
}
