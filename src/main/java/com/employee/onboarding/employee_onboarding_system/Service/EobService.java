package com.employee.onboarding.employee_onboarding_system.Service;

import com.employee.onboarding.employee_onboarding_system.Entity.*;

import java.util.List;

public interface EobService {
    List<RoleListEntity> getAllRoleList();
    UserDetailsKey getAllUsersById(int id);
    List<OnboardingKey> getChecklistForUser(int id);
    String setStatus(int userId,int checkListId,boolean status);
}
