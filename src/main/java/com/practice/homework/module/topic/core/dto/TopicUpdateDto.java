package com.practice.homework.module.topic.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TopicUpdateDto {

    @Data
    public static class TopicUpdateRequest implements Serializable {
        private String id;
        private String topicSubject;
        private String topicDetail;
        private BigDecimal visitorAmount;
        private Date lastVisitorDate;
        private String lastVisitorBy;
        private boolean isUpdateTopic;
        private String username;

    }

}
