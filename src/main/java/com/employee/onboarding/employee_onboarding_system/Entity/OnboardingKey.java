package com.employee.onboarding.employee_onboarding_system.Entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "userId", "checkListId", "status", "roleId","checkListContent", "checklistDescription"
})
public interface OnboardingKey {
    Integer getUserId();
    Integer getCheckListId();
    Boolean getStatus();
    Integer getRoleId();
    String getCheckListContent();
    String getChecklistDescription();
}
