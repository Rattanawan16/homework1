package com.practice.homework.repositorys;

import com.practice.homework.entity.Member;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, String>, MemberCustomRepository {
    @Query("SELECT * FROM member WHERE linear_id = :id AND status = :status")
    Member findByIdAndStatus(@Param("id") String id, @Param("status") String status);

    default Member findByIdString(@Param("id") String id) {
        return findByIdAndStatus(id, "A");
    }

    @Query("SELECT * FROM member WHERE id_card_no = :idCardNo AND status = :status")
    Member findByIdCardNoAndStatus(@Param("idCardNo") String idCardNo, @Param("status") String status);

    default Member findByIdCardNo(@Param("idCardNo") String idCardNo) {
        return findByIdCardNoAndStatus(idCardNo, "A");
    }

}

