package com.employee.onboarding.employee_onboarding_system.repository;

import com.employee.onboarding.employee_onboarding_system.entity.CheckListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface CheckListRepo extends JpaRepository<CheckListEntity, Integer> {
    @Query(value = "SELECT check_list_id from ipr.eob_check_list where role_id = ?1 AND is_active = true",nativeQuery = true)
    List<Integer> findCheckListByRoleId(int id);
    //    Admin
    @Query(value = "select check_list_id AS checklistId,role_id AS roleId,check_list_content AS checklistContent,checklist_description AS ChecklistDescription,is_active AS isActive from ipr.eob_check_list ORDER BY check_list_id ASC",nativeQuery = true)
    List<Map<String,Object>> findAllChecklist();
    @Query(value = "select check_list_id AS checklistId,role_id AS roleId,check_list_content AS checklistContent,checklist_description AS ChecklistDescription,created_user AS createdUser,modified_user AS modifiedUser, is_active AS isActive from ipr.eob_check_list where check_list_id = ?1 ORDER BY check_list_id ASC",nativeQuery = true)
    List<Map<String,Object>> findChecklistById(int checklistId);
    @Query(value = "select check_list_id AS checklistId,role_id AS roleId,check_list_content AS checklistContent,checklist_description AS ChecklistDescription,is_active AS isActive from ipr.eob_check_list where role_id = ?1 ORDER BY check_list_id ASC",nativeQuery = true)
    List<Map<String,Object>> findChecklistByRole(int roleId);
    @Query(value = "select * from ipr.eob_check_list where check_list_id = ?1 ORDER BY check_list_id ASC",nativeQuery = true)
    CheckListEntity findByChecklistIdUpadte(int checklistId);
}
