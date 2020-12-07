package com.practice.homework.controllers;

//import com.practice.homework.commons.Constants;
import com.practice.homework.commons.Constants;
import com.practice.homework.commons.dto.custom.FlowResponse;
import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.commons.utils.DataUtils;
import com.practice.homework.commons.utils.MessageCode;
import com.practice.homework.commons.utils.RandomUtils;
import com.practice.homework.dto.entity.TopicEntityDto;
import com.practice.homework.dto.entity.UserEntityDto;
import com.practice.homework.entity.Topic;
import com.practice.homework.entity.User;
import com.practice.homework.module.topic.core.dto.TopicCreateDto;
import com.practice.homework.module.topic.core.dto.TopicListDto;
import com.practice.homework.module.topic.core.dto.TopicUpdateDto;
import com.practice.homework.module.user.core.dto.UserCreateDto;
import com.practice.homework.module.user.core.dto.UserDeleteDto;
import com.practice.homework.module.user.core.dto.UserListDto;
import com.practice.homework.module.user.core.dto.UserUpdateDto;
import com.practice.homework.repositorys.TopicRepository;
import com.practice.homework.repositorys.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.time.LocalDate;
import java.util.*;

import static com.practice.homework.commons.utils.DataUtils.isUUID;
import static com.practice.homework.commons.utils.DateUtils.getCurrentDate;
import static com.practice.homework.commons.utils.ValidationUtils.*;

@RestController
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/topics")
    public List<Topic> findAllTopic() throws Exception {
        return (List<Topic>) topicRepository.findAll();
    }

    @GetMapping("/topics/{id}")
    public Topic findById(@PathVariable String id) throws Exception {
        requiredNotWhen(isNotEmpty(id), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(id), MessageCode.E00002, "id");

        Topic topic = topicRepository.findByIdString(id);
        return topic;
    }

    @PostMapping("/topics/list")
    public TopicListDto.TopicListEntityResponse topicList(TopicListDto.TopicListRequest request) throws Exception {
        requiredLimit(request.getLimit());

        QueryResponse<List<Topic>> queryResponse = topicRepository.findByCriteria(request);
        TopicListDto.TopicListEntityResponse apiResponse = new TopicListDto.TopicListEntityResponse();
        ArrayList<TopicEntityDto> resultList = new ArrayList<>();

        if (queryResponse.getData() != null) {
            for (Topic topic : queryResponse.getData()) {
                TopicEntityDto userEntityDto = new TopicEntityDto();
                userEntityDto.setState(topic);
                resultList.add(userEntityDto);
            }
        }

        apiResponse.setData(resultList);
        apiResponse.setPage(DataUtils.getPageResult(request.getLimit(), queryResponse.getTotal()));

        return apiResponse;
    }

    @PostMapping("/topics")
    public FlowResponse createTopic(@RequestBody TopicCreateDto.TopicRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");

        String generateUUID = UUID.randomUUID().toString();
        // check duplicate UUID
        Topic existingTopic = topicRepository.findByIdString(generateUUID);
        requiredNotWhen(existingTopic == null, MessageCode.E00017, "id");

//        if (!isNotEmpty(existingTopic))
//            return ResponseEntity.notFound().build();

        Topic topic = new Topic();
        Date currentTime = getCurrentDate();

        topic.setLinearId(generateUUID);
        topic.setTopicSubject(request.getTopicSubject());
        topic.setTopicDetail(request.getTopicDetail());

        topic.setState(Constants.State.New);
        topic.setStatus(Constants.Status.Active);
        topic.setCreateBy(request.getUsername());
        topic.setCreateDate(currentTime);
        topic.setChangeBy(request.getUsername());
        topic.setChangeDate(currentTime);

        topicRepository.save(topic);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(topic.getId()).toUri();
//
//        return ResponseEntity.created(location).build();
        return new FlowResponse(true, topic.getId());
    }

    @PutMapping("/topics/{id}")
    public FlowResponse update(@RequestBody TopicUpdateDto.TopicUpdateRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getId()), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(request.getId()), MessageCode.E00002, "id");

        System.out.println("request.getId():"+request.getId());
        Topic existingTopic = topicRepository.findByIdString(request.getId());
        requiredNotWhen(existingTopic != null, MessageCode.E00017, "topicId");
        Date currentTime = getCurrentDate();

//        if (!isNotEmpty(existingTopic))
//            return ResponseEntity.notFound().build();
//        System.out.println("existingTopic.getVisitorAmount(): "+existingTopic.getVisitorAmount());
        BigDecimal updateVisitorAmount = new BigDecimal(BigInteger.ZERO);
        if (!request.isUpdateTopic()) {
            System.out.println("true :"+existingTopic.getVisitorAmount());
            existingTopic.setVisitorAmount(existingTopic.getVisitorAmount().add(BigDecimal.ONE));
            existingTopic.setLastVisitorBy(request.getLastVisitorBy() != null ? request.getLastVisitorBy() : existingTopic.getLastVisitorBy());
            existingTopic.setLastVisitorDate(request.getLastVisitorDate() != null ? request.getLastVisitorDate() : existingTopic.getLastVisitorDate());
            existingTopic.setChangeDate(currentTime);
            existingTopic.setChangeBy(request.getUsername() != null ? request.getUsername() : request.getLastVisitorBy());
            existingTopic.setNewEntity(false);
        } else {
            existingTopic.setTopicSubject(request.getTopicSubject() != null ? request.getTopicSubject() : existingTopic.getTopicSubject());
            existingTopic.setTopicDetail(request.getTopicDetail() != null ? request.getTopicDetail() : existingTopic.getTopicDetail());
            existingTopic.setVisitorAmount(request.getVisitorAmount() != null ? request.getVisitorAmount() : existingTopic.getVisitorAmount());
            existingTopic.setLastVisitorBy(request.getLastVisitorBy() != null ? request.getLastVisitorBy() : existingTopic.getLastVisitorBy());
            existingTopic.setLastVisitorDate(request.getLastVisitorDate() != null ? request.getLastVisitorDate() : existingTopic.getLastVisitorDate());

            existingTopic.setChangeDate(currentTime);
            existingTopic.setChangeBy(request.getUsername() != null ? request.getUsername() : request.getLastVisitorBy());
            existingTopic.setNewEntity(false);
        }
        topicRepository.save(existingTopic);

        return new FlowResponse(true, existingTopic.getLinearId());
    }

    @PostMapping("/topicsMock")
    public FlowResponse createMockTopic(@RequestBody TopicCreateDto.TopicMockRequest request) throws Exception {
        System.out.println(request.getAmount());
//        List<String> listUUID = new ArrayList<>();

        requiredNotWhen(request.getAmount() != 0, MessageCode.E00001, "amount");

        for (int i = 1 ; i <= request.getAmount() ; i++) {
            requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");
            requiredNotWhen(isNotEmpty(request.getAmount()), MessageCode.E00001, "amount");

//            String generateUUID = UUID.randomUUID().toString();
            // check duplicate UUID
//            Topic existingTopic = topicRepository.findByIdString(generateUUID);
//            requiredNotWhen(existingTopic == null, MessageCode.E00017, "id");

            TopicCreateDto.TopicRequest mockTopic = new TopicCreateDto.TopicRequest();

//            Topic topic = new Topic();
//            topic.setLinearId(generateUUID);
            mockTopic.setTopicSubject(RandomStringUtils.randomAlphabetic(3,6));
            mockTopic.setTopicDetail(RandomStringUtils.randomAlphabetic(10,20));
            mockTopic.setUsername(request.getUsername());
            FlowResponse flowResponse = this.createTopic(mockTopic);

//            topic.setState(Constants.State.New);
//            topic.setStatus(Constants.Status.Active);
//            topic.setCreateBy(request.getUsername());
//            topic.setCreateDate(currentTime);
//            topic.setChangeBy(request.getUsername());
//            topic.setChangeDate(currentTime);

//            topicRepository.save(topic);
//            System.out.println("UUID: "+generateUUID);

        }

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(listUUID).toUri();
//        return ResponseEntity.created(location).build();
//        return ResponseEntity.ok();
        return new FlowResponse(true);
    }

}
