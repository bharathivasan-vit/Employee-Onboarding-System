package com.employee.onboarding.employee_onboarding_system.repository;

import com.employee.onboarding.employee_onboarding_system.entity.RoleListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface RoleListRepo extends JpaRepository<RoleListEntity,Integer> {
    @Query(value = "select role_id as roleId,role_name as roleName,created_date as createdDate,modified_date as modifiedDate,is_active as isActive from ipr.eob_role where is_active = true ORDER BY role_id ASC",nativeQuery = true)
    List<Map<String,Object>> findAllVisibleRoles();


    //    Admin
    @Query(value = "select role_id AS roleId, role_name AS roleName, is_active AS isActive from ipr.eob_role ORDER BY role_id ASC",nativeQuery = true)
    List<Map<String,Object>> findAllRoles();
    @Query(value = "select role_id AS roleId, role_name AS roleName, created_user AS createdUser, modified_user AS modifiedUser, is_active AS isActive from ipr.eob_role where role_id = ?1 ORDER BY role_id ASC",nativeQuery = true)
    List<Map<String,Object>> findRolesById(int roleId);
    @Query(value = "select * from ipr.eob_role where role_id = ?1 ORDER BY role_id ASC",nativeQuery = true)
    RoleListEntity findByRoleIdUpdate(int roleId);
    @Query(value = "select role_id AS roleId, role_name AS roleName, is_active AS isActive from ipr.eob_role where role_name like %?1%",nativeQuery = true)
    List<Map<String,Object>> findRoleByName(String roleName);
}
