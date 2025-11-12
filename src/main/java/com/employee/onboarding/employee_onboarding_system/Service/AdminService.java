package com.employee.onboarding.employee_onboarding_system.Service;

import com.employee.onboarding.employee_onboarding_system.Dto.OnboardingDTO;
import com.employee.onboarding.employee_onboarding_system.Entity.OnboardingBulkRecordingKey;

import java.util.List;

public interface AdminService {
    List<OnboardingBulkRecordingKey> getOnboardingBulkRecords();
    List<OnboardingBulkRecordingKey> getOnboardingBulkRecordsByEmailId(String emailId);
    List<OnboardingDTO> getOnboardingBulkRecordsClearView();
}