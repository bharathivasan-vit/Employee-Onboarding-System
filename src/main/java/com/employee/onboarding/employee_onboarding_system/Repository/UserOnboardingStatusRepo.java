package com.employee.onboarding.employee_onboarding_system.Repository;

import com.employee.onboarding.employee_onboarding_system.Entity.OnboardingBulkRecordingKey;
import com.employee.onboarding.employee_onboarding_system.Entity.OnboardingKey;
import com.employee.onboarding.employee_onboarding_system.Entity.UserOnboardingStatusEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserOnboardingStatusRepo extends JpaRepository<UserOnboardingStatusEntity, Integer> {
    @Query(value = "SELECT u.user_id, u.check_list_id, u.status, c.role_id, c.check_list_content, c.checklist_description FROM ipr.eob_user_onboarding_status u " +
            "INNER JOIN ipr.eob_check_list c ON u.check_list_id = c.check_list_id " +
            "WHERE u.user_id = ?1 AND c.is_active = true order by c.check_list_id asc",
            nativeQuery = true)
    List<OnboardingKey> findChecklistByUserId(int id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE ipr.eob_user_onboarding_status SET status = ?3 WHERE user_id = ?1 AND check_list_id = ?2",nativeQuery = true)
    int findByUserAndChecklist(int userId,int checkListId,boolean status);

    @Query(value = "SELECT u.user_id, u.user_name, u.phone_number, u.email_id, r.role_name, c.check_list_content, c.checklist_description, o.status FROM ipr.eob_check_list AS c "+
    "INNER JOIN ipr.eob_user_onboarding_status AS o ON o.check_list_id = c.check_list_id "+
    "INNER JOIN ipr.eob_user_details AS u ON u.user_id = o.user_id "+
    "INNER JOIN ipr.eob_role AS r ON r.role_id = u.role_id", nativeQuery = true)
    List<OnboardingBulkRecordingKey> findBulkOnboardingRecords();

//    @Query(value = "SELECT u.user_id, u.user_name, u.phone_number, u.email_id, r.role_name, c.check_list_content, c.checklist_description, o.status FROM ipr.eob_check_list AS c "+
//    "INNER JOIN ipr.eob_user_onboarding_status AS o ON o.check_list_id = c.check_list_id "+
//    "INNER JOIN ipr.eob_user_details AS u ON u.user_id = o.user_id "+
//    "INNER JOIN ipr.eob_role AS r ON r.role_id = u.role_id where u.user_id = (SELECT user_id FROM ipr.eob_user_details WHERE email_id = ?1)", nativeQuery = true)
    @Query(value = "SELECT u.user_id, u.user_name, u.phone_number, u.email_id, r.role_name, c.check_list_content, c.checklist_description, o.status" +
            "FROM (SELECT * FROM ipr.eob_user_details WHERE email_id = ?1) u," +
            "ipr.eob_user_onboarding_status o," +
            "ipr.eob_check_list c," +
            "ipr.eob_role r" +
            "WHERE" +
            "u.user_id = o.user_id" +
            "AND o.check_list_id = c.check_list_id" +
            "AND u.role_id = r.role_id", nativeQuery = true)
    List<OnboardingBulkRecordingKey> findBulkOnboardingRecordsByEmailId(String email);
}