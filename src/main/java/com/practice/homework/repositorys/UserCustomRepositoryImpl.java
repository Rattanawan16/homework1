package com.practice.homework.repositorys;

import com.google.common.base.CaseFormat;
import com.practice.homework.commons.dto.custom.PageQuery;
import com.practice.homework.commons.dto.custom.QueryResponse;
import com.practice.homework.commons.utils.DataUtils;
import com.practice.homework.commons.utils.SQLCriteriaBuilder;
import com.practice.homework.commons.utils.SQLParameter;
import com.practice.homework.entity.User;
import com.practice.homework.module.user.core.dto.UserListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    @Override
    public QueryResponse<List<User>> findByCriteria(UserListDto.UserListRequest request) throws Exception {
        QueryResponse<List<User>> queryResponse = new QueryResponse<>();
        String dbTableName = "public.user";
        SQLCriteriaBuilder cb = new SQLCriteriaBuilder();

        if ((request.getId() != null)) {
            cb.addQuery("AND linear_id = :linearId", SQLParameter.of("linearId", request.getId()));
        }

        if ((request.getIdCardNo() != null)) {
            cb.addQuery("AND id_card_no = :idCardNo", SQLParameter.of("idCardNo", request.getIdCardNo()));
        }
        if ((request.getLoginName() != null)) {
            cb.addQuery("AND login_name like :loginName", SQLParameter.of("loginName", '%' + request.getLoginName() + '%'));
        }
        if ((request.getFirstNameTh() != null)) {
            cb.addQuery("AND first_name_th like : firstNameTh", SQLParameter.of("firstNameTh", '%' + request.getFirstNameTh() + '%'));
        }
        if ((request.getFirstNameEn() != null)) {
            cb.addQuery("AND first_name_en like : firstNameEn", SQLParameter.of("firstNameEn", '%' + request.getFirstNameEn() + '%'));
        }
        if ((request.getLastNameTh() != null)) {
            cb.addQuery("AND last_name_th like : lastNameTh", SQLParameter.of("lastNameTh", '%' + request.getLastNameTh() + '%'));
        }
        if ((request.getLastNameEn() != null)) {
            cb.addQuery("AND last_name_en like : lastNameEn", SQLParameter.of("lastNameEn", '%' + request.getLastNameEn() + '%'));
        }
        if ((request.getEmail() != null)) {
            cb.addQuery("AND email like : email", SQLParameter.of("email", '%' + request.getEmail() + '%'));
        }
        if ((request.getTitle() != null)) {
            cb.addQuery("AND title like : title", SQLParameter.of("title", '%' + request.getTitle() + '%'));
        }
        if ((request.getBirthDateFrom() != null && request.getBirthDateTo() != null)) {
            cb.addQuery(" AND birth_date BETWEEN :birthDateFrom AND :birthDateTo ",
                    SQLParameter.of("birthDateFrom", request.getBirthDateFrom()),
                    SQLParameter.of("birthDateTo", request.getBirthDateTo())
            );
        }
        if ((request.getGender() != null)) {
            cb.addQuery("AND gender = :gender", SQLParameter.of("gender", request.getGender()));
        }
        if ((request.getMarriageStatus() != null)) {
            cb.addQuery("AND marriage_status = :marriageStatus", SQLParameter.of("marriageStatus", request.getMarriageStatus()));
        }
        if ((request.getNationality() != null)) {
            cb.addQuery("AND nationality = :nationality", SQLParameter.of("nationality", request.getNationality()));
        }
        if ((request.getMobile() != null)) {
            cb.addQuery("AND mobile = :mobile", SQLParameter.of("mobile", request.getMobile()));
        }
        if ((request.getTelephone() != null)) {
            cb.addQuery("AND telephone = :telephone", SQLParameter.of("telephone", request.getTelephone()));
        }
        if ((request.getOccupation() != null)) {
            cb.addQuery("AND occupation like : occupation", SQLParameter.of("occupation",  '%' + request.getOccupation() + '%'));
        }
        if ((request.getCompany() != null)) {
            cb.addQuery("AND company like : company", SQLParameter.of("company",  '%' + request.getCompany() + '%'));
        }
        if ((request.getUserType() != null)) {
            cb.addQuery("AND user_type = :userType", SQLParameter.of("userType", request.getUserType()));
        }
        if ((request.getUserSubtype() != null)) {
            cb.addQuery("AND user_subtype = :userSubtype", SQLParameter.of("userSubtype", request.getUserSubtype()));
        }
        if ((request.getLoginLatestDateFrom() != null && request.getLoginLatestDateTo() != null)) {
            cb.addQuery(" AND login_latest_date BETWEEN :loginLatestDateFrom AND :loginLatestDateTo ",
                    SQLParameter.of("loginLatestDateFrom", request.getLoginLatestDateFrom()),
                    SQLParameter.of("loginLatestDateTo", request.getLoginLatestDateTo())
            );
        }
        if ((request.getLogoutLatestDateFrom() != null && request.getLogoutLatestDateTo() != null)) {
            cb.addQuery(" AND logout_latest_date BETWEEN :logoutLatestDateFrom AND :logoutLatestDateTo ",
                    SQLParameter.of("logoutLatestDateFrom", request.getLogoutLatestDateFrom()),
                    SQLParameter.of("logoutLatestDateTo", request.getLogoutLatestDateTo())
            );
        }

        if ((request.getState() != null)) {
            cb.addQuery("AND state = :state", SQLParameter.of("state", request.getState()));
        }
        if ((request.getStatus() != null)) {
            cb.addQuery("AND status = :status", SQLParameter.of("status", request.getStatus()));
        }
        if ((request.getCreateBy() != null)) {
            cb.addQuery("AND create_by = :createBy", SQLParameter.of("createBy", request.getCreateBy()));
        }
        if ((request.getChangeBy() != null)) {
            cb.addQuery("AND change_by = :changeBy", SQLParameter.of("changeBy", request.getChangeBy()));
        }
        if ((request.getChangeDateFrom() != null && request.getChangeDateTo() != null)) {
            cb.addQuery(" AND change_date BETWEEN :changeDateFrom AND :changeDateTo ",
                    SQLParameter.of("changeDateFrom", request.getChangeDateFrom()),
                    SQLParameter.of("changeDateTo", request.getChangeDateTo())
            );
        }
        if ((request.getCreateDateFrom()!= null && request.getCreateDateTo() != null)) {
            cb.addQuery("AND create_date BETWEEN :createDateFrom AND :createDateTo ",
                    SQLParameter.of("createDateFrom", request.getCreateDateFrom()),
                    SQLParameter.of("createDateTo", request.getCreateDateTo())
            );
        }

        SQLCriteriaBuilder countQuery = new SQLCriteriaBuilder();
        countQuery.addQuery("SELECT count(*) FROM "+dbTableName+" WHERE 1 = 1");
        countQuery.addQuery(cb.getSql().toString());
        countQuery.setParameters(cb.getParameters());

        Long total = jdbcTemplate.queryForObject(countQuery.getSql().toString(), countQuery.getParameters(), Long.class);
        if (total > 0) {
            PageQuery pageQuery = DataUtils.getPageQuery(request.getLimit());

            SQLCriteriaBuilder mainQuery = new SQLCriteriaBuilder();
            mainQuery.addQuery("SELECT * FROM "+dbTableName+" WHERE 1 = 1");
            mainQuery.addQuery(cb.getSql().toString());
            mainQuery.setParameters(cb.getParameters());

            if ((request.getSortBy()!= null && request.getSortDirectionBy() != null)) {
                mainQuery.addQuery("ORDER BY " + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, request.getSortBy()) + " " + request.getSortDirectionBy());
            } else {
//                mainQuery.addQuery("ORDER BY change_date DESC");
            }

            mainQuery.addQuery("LIMIT :pageSize OFFSET :offset ",
                    SQLParameter.of("pageSize", pageQuery.getPageSize()),
                    SQLParameter.of("offset", pageQuery.getOffset()));

            List<User> queryResults = jdbcTemplate.query(mainQuery.getSql().toString(), mainQuery.getParameters(), new BeanPropertyRowMapper<>(User.class));
            queryResponse.setData(queryResults);
        }

        queryResponse.setTotal(total);
        return queryResponse;
    }
}
