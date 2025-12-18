package com.employee.onboarding.employee_onboarding_system.repository;

import com.employee.onboarding.employee_onboarding_system.entity.UserOnboardingStatusEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserOnboardingStatusRepo extends JpaRepository<UserOnboardingStatusEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE ipr.eob_user_onboarding_status SET status = ?3 , modified_date = ?4 WHERE user_id = ?1 AND check_list_id = ?2",nativeQuery = true)
    int findByUserAndChecklist(int userId, int checkListId, boolean status, LocalDateTime dateTime);

    @Query(value = "SELECT u.user_id AS userId, u.check_list_id AS checkListId, u.status AS status, c.role_id AS roleId, c.check_list_content AS checkListContent, c.checklist_description AS checklistDescription " +
            "FROM ipr.eob_user_onboarding_status u, ipr.eob_check_list c " +
            "WHERE u.check_list_id = c.check_list_id " +
            "AND u.user_id = ?1 " +
            "AND c.is_active = TRUE " +
            "ORDER BY c.check_list_id ASC",
            nativeQuery = true)
    List<Map<String, Object>> findChecklistByUserId(int id);

    @Query(value = "SELECT u.user_id AS userId, u.user_name AS userName, u.phone_number AS phoneNumber, u.email_id AS emailId, r.role_name AS roleName, " +
            "c.check_list_content AS checkListContent, c.checklist_description AS checklistDescription, o.status AS status, o.created_date AS createdDate, o.modified_date AS modifiedDate " +
            "FROM ipr.eob_check_list c, ipr.eob_user_onboarding_status o, " +
            "ipr.eob_user_details u, ipr.eob_role r " +
            "WHERE o.check_list_id = c.check_list_id " +
            "AND u.user_id = o.user_id " +
            "AND r.role_id = u.role_id", nativeQuery = true)
    List<Map<String,Object>> findBulkOnboardingRecords();

    @Query(value = "SELECT u.user_id AS userId, u.user_name AS userName, u.phone_number AS phoneNumber, u.email_id AS emailId, r.role_name AS roleName, " +
            "c.check_list_content AS checkListContent, c.checklist_description AS checklistDescription, o.status AS status, o.created_date AS createdDate, o.modified_date AS modifiedDate " +
            "FROM ipr.eob_check_list c, ipr.eob_user_onboarding_status o, " +
            "ipr.eob_user_details u, ipr.eob_role r " +
            "WHERE o.check_list_id = c.check_list_id " +
            "AND u.user_id = o.user_id " +
            "AND r.role_id = u.role_id " +
            "AND DATE(o.created_date) BETWEEN COALESCE(?1, DATE(o.created_date)) AND COALESCE(?2, DATE(o.created_date)) " +
            "AND o.status = COALESCE(?3, o.status) " +
            "AND r.role_name = COALESCE((SELECT role_name FROM ipr.eob_role WHERE role_id = ?4), r.role_name) " +
            "AND (u.user_name || ' ' || u.email_id || ' ' || c.check_list_content) like %?5%", nativeQuery = true)
    List<Map<String,Object>> findBulkOnboardingRecordsByFilter(LocalDate startDate, LocalDate endDate, Boolean status, Integer roleId, String emailId);
}