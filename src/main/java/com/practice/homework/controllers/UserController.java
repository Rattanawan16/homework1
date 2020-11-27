package com.practice.homework.controllers;

//import com.practice.homework.commons.Constants;
import com.practice.homework.commons.Constants;
import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.commons.utils.DataUtils;
import com.practice.homework.commons.utils.MessageCode;
import com.practice.homework.dto.entity.UserEntityDto;
import com.practice.homework.entity.User;
import com.practice.homework.module.user.core.dto.UserCreateDto;
import com.practice.homework.module.user.core.dto.UserDeleteDto;
import com.practice.homework.module.user.core.dto.UserListDto;
import com.practice.homework.module.user.core.dto.UserUpdateDto;
import com.practice.homework.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

import static com.practice.homework.commons.utils.DataUtils.isUUID;
import static com.practice.homework.commons.utils.DateUtils.getCurrentDate;
import static com.practice.homework.commons.utils.ValidationUtils.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAllUser() throws Exception {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable String id) throws Exception {
        requiredNotWhen(isNotEmpty(id), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(id), MessageCode.E00002, "id");



        User user = userRepository.findByIdString(id);
        return user;
    }

    @PostMapping("/list")
    public UserListDto.UserListEntityResponse userList(UserListDto.UserListRequest request) throws Exception {
        requiredLimit(request.getLimit());

        QueryResponse<List<User>> queryResponse = userRepository.findByCriteria(request);
        UserListDto.UserListEntityResponse apiResponse = new UserListDto.UserListEntityResponse();
        ArrayList<UserEntityDto> resultList = new ArrayList<>();

        if (queryResponse.getData() != null) {
            for (User user : queryResponse.getData()) {
                UserEntityDto userEntityDto = new UserEntityDto();
                userEntityDto.setState(user);
                resultList.add(userEntityDto);
            }
        }

        apiResponse.setData(resultList);
        apiResponse.setPage(DataUtils.getPageResult(request.getLimit(), queryResponse.getTotal()));

        return apiResponse;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> create(@RequestBody UserCreateDto.UserRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");
        requiredNotWhen(isNotEmpty(request.getLoginName()), MessageCode.E00001, "loginName");
        requiredNotWhen(isNotEmpty(request.getLoginPassword()), MessageCode.E00001, "password");
        requiredNotWhen(isNotEmpty(request.getEmail()), MessageCode.E00001, "email");
        requiredNotWhen(isNotEmpty(request.getIdCardNo()), MessageCode.E00001, "idCardNo");

        String generateUUID = UUID.randomUUID().toString();
        // check duplicate UUID
        User existingUser = userRepository.findByIdString(generateUUID);
        requiredNotWhen(existingUser != null, MessageCode.E00017, "id");

        if (!isNotEmpty(existingUser))
            return ResponseEntity.notFound().build();

        // check duplicate idCardNo
        User existingUser2 = userRepository.findByIdCardNo(request.getIdCardNo());
        requiredNotWhen(existingUser2 != null, MessageCode.E00017, "idCardNo");

        if (!isNotEmpty(existingUser2))
            return ResponseEntity.notFound().build();

        User user = new User();
        Date currentTime = getCurrentDate();

        user.setLinearId(generateUUID);
        user.setFirstNameTh(request.getFirstNameTh());
        user.setFirstNameEn(request.getFirstNameEn());
        user.setLastNameTh(request.getLastNameTh());
        user.setLastNameEn(request.getLastNameEn());
        user.setBirthDate(request.getBirthDate());
        user.setEmail(request.getEmail());
        user.setLoginName(request.getLoginName());
        user.setLoginPassword(request.getLoginPassword());
        user.setTelephone(request.getTelephone());
        user.setMobile(request.getMobile());
        user.setIdCardNo(request.getIdCardNo());
        user.setTitle(request.getTitle());

        user.setNationality(request.getNationality());
        user.setGender(request.getGender());
        user.setMarriageStatus(request.getMarriageStatus());
        user.setCompany(request.getCompany());
        user.setOccupation(request.getOccupation());
        user.setUserType(request.getUserType());
        user.setUserSubtype(request.getUserSubtype());

        user.setState(Constants.State.New);
        user.setStatus(Constants.Status.Active);
        user.setCreateBy(request.getUsername());
        user.setCreateDate(currentTime);
        user.setChangeBy(request.getUsername());
        user.setChangeDate(currentTime);

        userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@RequestBody UserUpdateDto.UserUpdateRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getId()), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(request.getId()), MessageCode.E00002, "id");

        User existingUser = userRepository.findByIdString(request.getId());

        if (!isNotEmpty(existingUser))
            return ResponseEntity.notFound().build();

        Date currentTime = getCurrentDate();
        existingUser.setFirstNameTh(request.getFirstNameTh() != null ? request.getFirstNameTh() : existingUser.getFirstNameTh());
        existingUser.setFirstNameEn(request.getFirstNameEn() != null ? request.getFirstNameEn() : existingUser.getFirstNameEn());
        existingUser.setLastNameTh(request.getLastNameTh() != null ? request.getLastNameTh() : existingUser.getLastNameTh());
        existingUser.setLastNameEn(request.getLastNameEn() != null ? request.getLastNameEn() : existingUser.getLastNameEn());
        existingUser.setBirthDate(request.getBirthDate() != null ? request.getBirthDate() : existingUser.getBirthDate());
        existingUser.setEmail(request.getEmail() != null ? request.getEmail() : existingUser.getEmail());
        existingUser.setLoginName(request.getLoginName() != null ? request.getLoginName() : existingUser.getLoginName());
        existingUser.setLoginPassword(request.getLoginPassword() != null ? request.getLoginPassword() : existingUser.getLoginPassword());
        existingUser.setTelephone(request.getTelephone() != null ? request.getTelephone() : existingUser.getTelephone());
        existingUser.setMobile(request.getMobile() != null ? request.getMobile() : existingUser.getMobile());
        existingUser.setIdCardNo(request.getIdCardNo() != null ? request.getIdCardNo() : existingUser.getIdCardNo());
        existingUser.setTitle(request.getTitle() != null ? request.getTitle() : existingUser.getTitle());

        existingUser.setNationality(request.getNationality() != null ? request.getNationality() : existingUser.getNationality());
        existingUser.setGender(request.getGender() != null ? request.getGender() : existingUser.getGender());
        existingUser.setMarriageStatus(request.getMarriageStatus() != null ? request.getMarriageStatus() : existingUser.getMarriageStatus());
        existingUser.setCompany(request.getCompany() != null ? request.getCompany() : existingUser.getCompany());
        existingUser.setOccupation(request.getOccupation() != null ? request.getOccupation() : existingUser.getOccupation());
        existingUser.setUserType(request.getUserType() != null ? request.getUserType() : existingUser.getUserType());
        existingUser.setUserSubtype(request.getUserSubtype() != null ? request.getUserSubtype() : existingUser.getUserSubtype());
        existingUser.setChangeDate(currentTime);
        existingUser.setChangeBy(request.getUsername());
        existingUser.setNewEntity(false);

        userRepository.save(existingUser);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/users/{id}")
//    public void delete(@PathVariable UserDeleteDto.UserDeleteDtoRequest request) throws Exception {
    public ResponseEntity<User> delete(@RequestBody UserDeleteDto.UserDeleteDtoRequest request) throws Exception {

        requiredNotWhen(isNotEmpty(request.getId()), MessageCode.E00001, "id");
        requiredNotWhen(isUUID(request.getId()), MessageCode.E00002, "id");
        requiredNotWhen(isNotEmpty(request.getUsername()), MessageCode.E00001, "username");

        User existingUser = userRepository.findByIdString(request.getId());
        requiredNotWhen(existingUser != null, MessageCode.E00017, "idCardNo");

        if (!isNotEmpty(existingUser))
            return ResponseEntity.notFound().build();

        Date currentTime = getCurrentDate();
        existingUser.setStatus(Constants.Status.Inactive);
        existingUser.setChangeDate(currentTime);
        existingUser.setChangeBy(request.getUsername());
        existingUser.setNewEntity(false);

        userRepository.save(existingUser);
        return ResponseEntity.noContent().build();
        //TODO reply update status
//        userRepository.deleteById(request.getId());
    }
}
