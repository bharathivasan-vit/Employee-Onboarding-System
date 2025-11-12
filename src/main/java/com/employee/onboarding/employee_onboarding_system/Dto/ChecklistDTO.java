package com.employee.onboarding.employee_onboarding_system.Dto;

public class ChecklistDTO {
    private String checkListContent;
    private String checklistDescription;
    private String status;

    public ChecklistDTO(String checkListContent, String checklistDescription, String status) {
        this.checkListContent = checkListContent;
        this.checklistDescription = checklistDescription;
        this.status = status;
    }

    public ChecklistDTO() {
    }

    public String getCheckListContent() {
        return checkListContent;
    }

    public String getChecklistDescription() {
        return checklistDescription;
    }

    public String getStatus() {
        return status;
    }
}
