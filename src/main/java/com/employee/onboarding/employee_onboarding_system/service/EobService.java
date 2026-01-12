package com.employee.onboarding.employee_onboarding_system.service;

import java.util.List;
import java.util.Map;

public interface EobService {
    List<Map<String,Object>> getAllRoleList();
    List<Map<String, Object>> getUsersById(int id);
    List<Map<String, Object>> getChecklistForUser(int id);
    String setStatus(int userId,int checkListId,boolean status);
}
