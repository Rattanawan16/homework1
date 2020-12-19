package com.practice.homework.repositorys;

import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.entity.Topic;
import com.practice.homework.module.topic.core.dto.TopicListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicCustomRepository {
    QueryResponse<List<Topic>> findByCriteria(TopicListDto.TopicListRequest request) throws Exception;

}
