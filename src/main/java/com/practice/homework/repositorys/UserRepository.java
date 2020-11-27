package com.practice.homework.repositorys;

import com.practice.homework.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>, UserCustomRepository {
    @Query("SELECT * FROM public.user WHERE linear_id = :id AND status = :status")
    User findByIdAndStatus(@Param("id") String id, @Param("status") String status);

    default User findByIdString(@Param("id") String id) {
        return findByIdAndStatus(id, "A");
    }

    @Query("SELECT * FROM public.user WHERE id_card_no = :idCardNo AND status = :status")
    User findByIdCardNoAndStatus(@Param("idCardNo") String idCardNo, @Param("status") String status);

    default User findByIdCardNo(@Param("idCardNo") String idCardNo) {
        return findByIdCardNoAndStatus(idCardNo, "A");
    }

}

