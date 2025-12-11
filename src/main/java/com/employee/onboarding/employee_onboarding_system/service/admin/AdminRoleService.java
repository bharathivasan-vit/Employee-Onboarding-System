package com.employee.onboarding.employee_onboarding_system.service.admin;

import com.employee.onboarding.employee_onboarding_system.entity.RoleListEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface AdminRoleService {
    List<Map<String,Object>> getAllRole();
    List<Map<String,Object>> getRoleById(int roleId);
    RoleListEntity createRole(RoleListEntity inputRole, HttpServletRequest request);
    RoleListEntity updateRole(int roleId, RoleListEntity inputRole, HttpServletRequest request);
}
