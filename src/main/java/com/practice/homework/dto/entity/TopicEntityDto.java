package com.practice.homework.dto.entity;

import com.practice.homework.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicEntityDto {

    private Topic state;
    private TopicEntityInfoDto info;

    public TopicEntityDto(Topic state) {
        this.state = state;
    }

    @Data
    @AllArgsConstructor
    public static class TopicEntityInfoDto {

    }
}
