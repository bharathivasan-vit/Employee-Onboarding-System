package com.employee.onboarding.employee_onboarding_system.repository;

import com.employee.onboarding.employee_onboarding_system.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface UserDetailsRepo extends JpaRepository<UserDetailsEntity, Integer> {
    @Query(value = "SELECT * from ipr.eob_user_details where email_id = ?1",nativeQuery = true)
    UserDetailsEntity findByEmail(String email);
    @Query(value = "SELECT u.user_id AS userId,u.user_name AS userName,u.phone_number AS phoneNumber,u.email_id AS emailId,r.role_name AS roleName from ipr.eob_user_details u,ipr.eob_role r where u.role_id = r.role_id AND user_id = ?1",nativeQuery = true)
    List<Map<String, Object>> findByUserId(int id);
    //    Admin
    @Query(value = "SELECT user_name AS userName,phone_number AS phoneNumber,email_id AS emailId,role_id AS roleId,otp_verified AS otpVerified,created_date AS createdDate from ipr.eob_user_details where otp_verified = true ORDER BY user_id ASC ",nativeQuery = true)
    List<Map<String,Object>> findUserByVerified();
    @Query(value = "SELECT user_name AS userName,phone_number AS phoneNumber,email_id AS emailId,role_id AS roleId,otp_verified AS otpVerified,created_date AS createdDate from ipr.eob_user_details where (user_name || ' ' || email_id) like %?1%",nativeQuery = true)
    List<Map<String,Object>> findUserByName(String userName);
}
