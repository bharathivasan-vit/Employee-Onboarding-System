package com.employee.onboarding.employee_onboarding_system.Dto;

import java.util.ArrayList;
import java.util.List;

public class OnboardingDTO {
   private Integer userId;
   private String userName;
   private String phoneNumber;
   private String emailId;
   private String roleName;
   private List<ChecklistDTO> checklists;

    public OnboardingDTO(Integer userId, String userName, String phoneNumber, String emailId, String roleName, List<ChecklistDTO> checklists) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.roleName = roleName;
        this.checklists = checklists;
    }

    public OnboardingDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<ChecklistDTO> getChecklists() {
        return checklists;
    }
}
