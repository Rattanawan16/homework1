package com.practice.homework.module.topic.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class TopicCreateDto {
    @Data
    public static class TopicRequest implements Serializable {

        private String topicSubject;
        private String topicDetail;
        private String username;
    }

    @Data
    public static class TopicMockRequest implements Serializable {

        private int amount;
        private String username;
    }
}
