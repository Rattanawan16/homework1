package com.practice.homework.repositorys;

import com.practice.homework.entity.User;
import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.module.user.core.dto.UserListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCustomRepository {
    QueryResponse<List<User>> findByCriteria(UserListDto.UserListRequest request) throws Exception;

}
