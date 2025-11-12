package com.employee.onboarding.employee_onboarding_system.Repository;

import com.employee.onboarding.employee_onboarding_system.Entity.UserDetailsEntity;
import com.employee.onboarding.employee_onboarding_system.Entity.UserDetailsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDetailsRepo extends JpaRepository<UserDetailsEntity, Integer> {
    @Query(value = "SELECT * from ipr.eob_user_details where email_id = ?1",nativeQuery = true)
    UserDetailsEntity findByEmail(String email);
    @Query(value = "SELECT u.user_id,u.user_name,u.phone_number,u.email_id,r.role_name from ipr.eob_user_details u,ipr.eob_role r where u.role_id = r.role_id AND user_id = ?1",nativeQuery = true)
    UserDetailsKey findByUserId(int id);
}
