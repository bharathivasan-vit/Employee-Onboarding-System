package com.employee.onboarding.employee_onboarding_system.service.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Map<String,Object>> getOnboardingBulkRecords();
    List<Map<String,Object>> getOnboardingBulkRecordsClearView();
    List<Map<String,Object>> getOnboardingBulkRecordsByFilter(LocalDate startDate,LocalDate endDate,Boolean status,Integer roleId,String emailId);
    List<Map<String,Object>> getAllUsersByVerified();
}