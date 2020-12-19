package com.practice.homework.repositorys;

import com.practice.homework.entity.Member;
import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.module.member.core.dto.MemberListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCustomRepository {
    QueryResponse<List<Member>> findByCriteria(MemberListDto.MemberListRequest request) throws Exception;

}
