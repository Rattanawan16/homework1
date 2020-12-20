package com.practice.homework.controllers;

//import com.practice.homework.commons.Constants;
import com.practice.homework.commons.Constants;
import com.practice.homework.commons.dto.custom.FlowResponse;
import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.commons.utils.DataUtils;
import com.practice.homework.commons.utils.DateUtils;
import com.practice.homework.commons.utils.RandomUtils;
import com.practice.homework.commons.utils.MessageCode;
import com.practice.homework.dto.entity.MemberEntityDto;
import com.practice.homework.entity.Topic;
import com.practice.homework.entity.Member;
import com.practice.homework.module.topic.core.dto.TopicUpdateDto;
import com.practice.homework.module.member.core.dto.MemberCreateDto;
import com.practice.homework.module.member.core.dto.MemberDeleteDto;
import com.practice.homework.module.member.core.dto.MemberListDto;
import com.practice.homework.module.member.core.dto.MemberUpdateDto;
import com.practice.homework.repositorys.TopicRepository;
import com.practice.homework.repositorys.MemberRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.*;

import static com.practice.homework.commons.utils.DataUtils.isUUID;
import static com.practice.homework.commons.utils.DateUtils.getCurrentDate;
import static com.practice.homework.commons.utils.RandomUtils.generateRandomIndex;
import static com.practice.homework.commons.utils.ValidationUtils.*;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicController topicController;

    @GetMapping("/members")
    public List<Member> findAllMember() throws Exception {
        return (List<Member>) memberRepository.findAll();
    }

    @GetMapping("/members/{id}")
    public Member findById(@PathVariable String id) throws Exception {
        requiredNotWhen(isNotEmpty(id), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(id), MessageCode.E00002, "id");

        Member member = memberRepository.findByIdString(id);
        return member;
    }

    @PostMapping("/list")
    public MemberListDto.MemberListEntityResponse memberList(MemberListDto.MemberListRequest request) throws Exception {
        requiredLimit(request.getLimit());

        QueryResponse<List<Member>> queryResponse = memberRepository.findByCriteria(request);
        MemberListDto.MemberListEntityResponse apiResponse = new MemberListDto.MemberListEntityResponse();
        ArrayList<MemberEntityDto> resultList = new ArrayList<>();

        if (queryResponse.getData() != null) {
            for (Member member : queryResponse.getData()) {
                MemberEntityDto memberEntityDto = new MemberEntityDto();
                memberEntityDto.setState(member);
                resultList.add(memberEntityDto);
            }
        }

        apiResponse.setData(resultList);
        apiResponse.setPage(DataUtils.getPageResult(request.getLimit(), queryResponse.getTotal()));

        return apiResponse;
    }

    @PostMapping("/members")
    public FlowResponse create(@RequestBody MemberCreateDto.MemberRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");
        requiredNotWhen(isNotEmpty(request.getLoginName()), MessageCode.E00001, "loginName");
        requiredNotWhen(isNotEmpty(request.getLoginPassword()), MessageCode.E00001, "password");
        requiredNotWhen(isNotEmpty(request.getEmail()), MessageCode.E00001, "email");
        requiredNotWhen(isNotEmpty(request.getIdCardNo()), MessageCode.E00001, "idCardNo");

        String generateUUID = UUID.randomUUID().toString();

        // check duplicate idCardNo
//        Member existingMember2 = memberRepository.findByIdCardNo(request.getIdCardNo());
//        requiredNotWhen(existingMember2 == null, MessageCode.E00017, "idCardNo");

        Member member = new Member();
        Date currentTime = getCurrentDate();

        member.setLinearId(generateUUID);
        member.setFirstNameTh(request.getFirstNameTh());
        member.setFirstNameEn(request.getFirstNameEn());
        member.setLastNameTh(request.getLastNameTh());
        member.setLastNameEn(request.getLastNameEn());
        member.setBirthDate(request.getBirthDate());
        member.setEmail(request.getEmail());
        member.setLoginName(request.getLoginName());
        member.setLoginPassword(request.getLoginPassword());
        member.setTelephone(request.getTelephone());
        member.setMobile(request.getMobile());
        member.setIdCardNo(request.getIdCardNo());
        member.setTitle(request.getTitle());

        member.setNationality(request.getNationality());
        member.setGender(request.getGender());
        member.setMarriageStatus(request.getMarriageStatus());
        member.setCompany(request.getCompany());
        member.setOccupation(request.getOccupation());
        member.setMemberType(request.getMemberType());
        member.setMemberSubtype(request.getMemberSubtype());

        member.setState(Constants.State.New);
        member.setStatus(Constants.Status.Active);
        member.setCreateBy(request.getUsername());
        member.setCreateDate(currentTime);
        member.setChangeBy(request.getUsername());
        member.setChangeDate(currentTime);

        memberRepository.save(member);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(member.getId()).toUri();
//        return ResponseEntity.created(location).build();
        return new FlowResponse(true, member.getId());
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> update(@RequestBody MemberUpdateDto.MemberUpdateRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getId()), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(request.getId()), MessageCode.E00002, "id");

        Member existingMember = memberRepository.findByIdString(request.getId());

        if (!isNotEmpty(existingMember))
            return ResponseEntity.notFound().build();

        Date currentTime = getCurrentDate();
        existingMember.setFirstNameTh(request.getFirstNameTh() != null ? request.getFirstNameTh() : existingMember.getFirstNameTh());
        existingMember.setFirstNameEn(request.getFirstNameEn() != null ? request.getFirstNameEn() : existingMember.getFirstNameEn());
        existingMember.setLastNameTh(request.getLastNameTh() != null ? request.getLastNameTh() : existingMember.getLastNameTh());
        existingMember.setLastNameEn(request.getLastNameEn() != null ? request.getLastNameEn() : existingMember.getLastNameEn());
        existingMember.setBirthDate(request.getBirthDate() != null ? request.getBirthDate() : existingMember.getBirthDate());
        existingMember.setEmail(request.getEmail() != null ? request.getEmail() : existingMember.getEmail());
        existingMember.setLoginName(request.getLoginName() != null ? request.getLoginName() : existingMember.getLoginName());
        existingMember.setLoginPassword(request.getLoginPassword() != null ? request.getLoginPassword() : existingMember.getLoginPassword());
        existingMember.setTelephone(request.getTelephone() != null ? request.getTelephone() : existingMember.getTelephone());
        existingMember.setMobile(request.getMobile() != null ? request.getMobile() : existingMember.getMobile());
        existingMember.setIdCardNo(request.getIdCardNo() != null ? request.getIdCardNo() : existingMember.getIdCardNo());
        existingMember.setTitle(request.getTitle() != null ? request.getTitle() : existingMember.getTitle());

        existingMember.setNationality(request.getNationality() != null ? request.getNationality() : existingMember.getNationality());
        existingMember.setGender(request.getGender() != null ? request.getGender() : existingMember.getGender());
        existingMember.setMarriageStatus(request.getMarriageStatus() != null ? request.getMarriageStatus() : existingMember.getMarriageStatus());
        existingMember.setCompany(request.getCompany() != null ? request.getCompany() : existingMember.getCompany());
        existingMember.setOccupation(request.getOccupation() != null ? request.getOccupation() : existingMember.getOccupation());
        existingMember.setMemberType(request.getMemberType() != null ? request.getMemberType() : existingMember.getMemberType());
        existingMember.setMemberSubtype(request.getMemberSubtype() != null ? request.getMemberSubtype() : existingMember.getMemberSubtype());
        existingMember.setChangeDate(currentTime);
        existingMember.setChangeBy(request.getUsername());
        existingMember.setNewEntity(false);

        memberRepository.save(existingMember);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/members/{id}")
//    public void delete(@PathVariable MemberDeleteDto.MemberDeleteDtoRequest request) throws Exception {
    public ResponseEntity<Member> delete(@RequestBody MemberDeleteDto.MemberDeleteDtoRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getId()), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(request.getId()), MessageCode.E00002, "id");
        requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");

        Member existingMember = memberRepository.findByIdString(request.getId());
        requiredNotWhen(existingMember != null, MessageCode.E00017, "id");

        if (!isNotEmpty(existingMember))
            return ResponseEntity.notFound().build();

        Date currentTime = getCurrentDate();
        existingMember.setStatus(Constants.Status.Inactive);
        existingMember.setChangeDate(currentTime);
        existingMember.setChangeBy(request.getUsername());
        existingMember.setNewEntity(false);

        memberRepository.save(existingMember);
        return ResponseEntity.noContent().build();
        //TODO reply update status
//        memberRepository.deleteById(request.getId());
    }

    @PostMapping("/membersMock")
    public FlowResponse createMockMember(@RequestBody MemberCreateDto.MemberMockRequest request) throws Exception {
//        System.out.println(request.getAmount());
//        List<String> listUUID = new ArrayList<>();

        requiredNotWhen(request.getAmount() != 0, MessageCode.E00001, "amount");
        ResponseEntity<Object> responseEntity;
        for (int i = 1 ; i <= request.getAmount() ; i++) {
            requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");
            requiredNotWhen(isNotEmpty(request.getAmount()), MessageCode.E00001, "amount");

//            String generateUUID = UUID.randomUUID().toString();
            String generateIdCardNo = String.valueOf(RandomUtils.randomDigits(13));
            String generateEmail = RandomUtils.generateRandomEmail(20);
            String generatePwd = RandomUtils.generateRandomPwd(20);

            // check duplicate idCardNo
            Member existingDuplicateIdCardNo = memberRepository.findByIdCardNo(generateIdCardNo);
            requiredNotWhen(existingDuplicateIdCardNo == null, MessageCode.E00017, "idCardNo");

//            Member member = new Member();
            MemberCreateDto.MemberRequest mockMember = new MemberCreateDto.MemberRequest();
            Date currentTime = getCurrentDate();

            mockMember.setFirstNameTh(RandomStringUtils.randomAlphabetic(3,6));
            mockMember.setFirstNameEn(RandomStringUtils.randomAlphabetic(3,6));
            mockMember.setLastNameTh(RandomStringUtils.randomAlphabetic(3,6));
            mockMember.setLastNameEn(RandomStringUtils.randomAlphabetic(3,6));
            mockMember.setBirthDate(currentTime);
            mockMember.setEmail(generateEmail);
            mockMember.setLoginName(generateEmail);
            mockMember.setLoginPassword(generatePwd);
            mockMember.setTelephone(RandomStringUtils.randomNumeric(9,9));
            mockMember.setMobile(RandomStringUtils.randomNumeric(10,10));
            mockMember.setIdCardNo(generateIdCardNo);
            mockMember.setTitle(RandomStringUtils.randomAlphabetic(3,3));

            mockMember.setNationality(RandomStringUtils.randomAlphabetic(3,3));
            mockMember.setGender(RandomStringUtils.randomAlphabetic(3,5));
            mockMember.setMarriageStatus(RandomStringUtils.randomAlphabetic(5,5));
            mockMember.setCompany(RandomStringUtils.randomAlphabetic(5,10));
            mockMember.setOccupation(RandomStringUtils.randomAlphabetic(5,10));
            mockMember.setMemberType(RandomStringUtils.randomAlphabetic(3,3));
            mockMember.setMemberSubtype(RandomStringUtils.randomAlphabetic(3,3));
            mockMember.setUsername(request.getUsername());
            FlowResponse flowResponse = this.create(mockMember);

//            member.setState(Constants.State.New);
//            member.setStatus(Constants.Status.Active);
//            member.setCreateBy(request.getUsername());
//            member.setCreateDate(currentTime);
//            member.setChangeBy(request.getUsername());
//            member.setChangeDate(currentTime);

//            memberRepository.save(member);
//            System.out.println("UUID: "+generateUUID);
//            listUUID.add();
        }

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(listUUID).toUri();
        return new FlowResponse(true);
    }


    @PutMapping("/members/visit")
    public FlowResponse memberVisitTopic(@RequestBody MemberUpdateDto.MemberVisitTopicRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getMemberId()), MessageCode.E00001, "memberId");
        requiredNotWhen(isUUID(request.getMemberId()), MessageCode.E00002, "memberId");
        requiredNotWhen(isNotEmpty(request.getTopicId()), MessageCode.E00001, "topicId");
        requiredNotWhen(isUUID(request.getTopicId()), MessageCode.E00002, "topicId");

        Member existingMember = memberRepository.findByIdString(request.getMemberId());
        requiredNotWhen(existingMember != null, MessageCode.E00017, "memberId");

//        System.out.println(request.getTopicId());

        Topic existingTopic = topicRepository.findByIdString(request.getTopicId());
        requiredNotWhen(existingTopic != null, MessageCode.E00017, "topicId");

        TopicUpdateDto.TopicUpdateRequest visitReq = new TopicUpdateDto.TopicUpdateRequest();
        visitReq.setId(request.getTopicId());
        visitReq.setVisitorAmount(BigDecimal.ONE);
        visitReq.setLastVisitorBy(request.getMemberId());
        visitReq.setLastVisitorDate(getCurrentDate());
        topicController.update(visitReq);

        return new FlowResponse(true, request.getTopicId());
    }

    @PutMapping("/members/randomVisit")
    public FlowResponse randomMemberVisitRandomTopic(@RequestBody MemberUpdateDto.RandomMemberVisitRandomTopicRequest request) throws Exception {

        requiredNotWhen(request.getAmount() != 0, MessageCode.E00001, "amount");
        requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");

        List<String> listVisitedTopic = new ArrayList<>();
        List<String> listVisitedMember = new ArrayList<>();

        for (int i = 1 ; i <= request.getAmount() ; i++) {
            // random member
            List<Member> allMember = this.findAllMember();
            requiredNotWhen(allMember != null, MessageCode.E00017, "member");
//            System.out.println("================================= ");
//            System.out.println("allMember.size: "+allMember.size());
            int randomMember = generateRandomIndex(allMember.size());
//            System.out.println("random_int: "+randomMember);
            Member selectedMember = allMember.get(randomMember);

            // random topic
            List<Topic> allTopic = topicController.findAllTopic();
            requiredNotWhen(allTopic != null, MessageCode.E00017, "topic");

//            System.out.println("allTopic.size: "+allTopic.size());
            int randomTopic = generateRandomIndex(allTopic.size());
//            System.out.println("random_topic: "+randomTopic);
//            System.out.println("================================= ");
            Topic selectedTopic = allTopic.get(randomTopic);
            TopicUpdateDto.TopicUpdateRequest visitReq = new TopicUpdateDto.TopicUpdateRequest();
            visitReq.setId(selectedTopic.getId());
            visitReq.setVisitorAmount(BigDecimal.ONE);
            visitReq.setLastVisitorBy(selectedMember.getId());
            visitReq.setLastVisitorDate(getCurrentDate());
            visitReq.setVisitTopic(true);
            visitReq.setUsername(request.getUsername());

//            System.out.println("selectedTopic.getId():"+selectedTopic.getId());
            topicController.update(visitReq);
            listVisitedMember.add(selectedMember.getId());
            listVisitedTopic.add(selectedTopic.getId());
        }

        return new FlowResponse(true, listVisitedTopic);
    }
}
