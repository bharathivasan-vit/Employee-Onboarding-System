package com.employee.onboarding.employee_onboarding_system.service.admin;

import com.employee.onboarding.employee_onboarding_system.entity.CheckListEntity;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface AdminChecklistService {
    List<Map<String,Object>> getAllChecklist();
    List<Map<String,Object>> getChecklistById(int checklistId);
    List<Map<String,Object>> getChecklistByRole(int roleId);
    CheckListEntity createChecklist(CheckListEntity inputChecklis, HttpServletRequest request);
    CheckListEntity updateChecklist(int checklistId, CheckListEntity inputChecklis, HttpServletRequest request);
}
