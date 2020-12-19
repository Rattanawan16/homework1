package com.practice.homework.module.topic.core.dto;

import com.practice.homework.commons.dto.custom.PageLimit;
import com.practice.homework.commons.dto.custom.PageResult;
import com.practice.homework.dto.entity.TopicEntityDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TopicListDto {

    @Data
    public static class TopicListRequest implements Serializable {
        private String id;

        private String topicSubject;
        private String topicDetail;
        private BigDecimal visitorAmount;
        private Date lastVisitorDateFrom;
        private Date lastVisitorDateTo;
        private String lastVisitorBy;

        private String status;
        private String state;
        private String createBy;
        private Date createDateFrom;
        private Date createDateTo;
        private String changeBy;
        private Date changeDateFrom;
        private Date changeDateTo;

        private PageLimit limit;

        private String sortBy;
        private String sortDirectionBy;
    }

    @Data
    public static class TopicListEntityResponse {
        private List<TopicEntityDto> data;
        private PageResult page;
    }
}
