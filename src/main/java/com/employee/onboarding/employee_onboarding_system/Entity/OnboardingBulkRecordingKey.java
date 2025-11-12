package com.employee.onboarding.employee_onboarding_system.Entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "userId", "userName", "phoneNumber", "emailId",
        "roleName", "checkListContent", "checklistDescription", "status"
})
public interface OnboardingBulkRecordingKey {
    Integer getUserId();
    String getUserName();
    String getPhoneNumber();
    String getEmailId();
    String getRoleName();
    String getCheckListContent();
    String getChecklistDescription();
    String getStatus();
}