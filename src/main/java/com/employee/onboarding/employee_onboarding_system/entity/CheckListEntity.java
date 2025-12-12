package com.employee.onboarding.employee_onboarding_system.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "ipr",name = "eob_check_list")
public class CheckListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_list_id")
    private int checkListId;
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "check_list_content")
    private String checkListContent;
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "checklist_description")
    private String checklistDescription;
    @Column(name = "is_active")
    private boolean isActive;

    public int getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(int checkListId) {
        this.checkListId = checkListId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getCheckListContent() {
        return checkListContent;
    }

    public void setCheckListContent(String checkListContent) {
        this.checkListContent = checkListContent;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getChecklistDescription() {
        return checklistDescription;
    }

    public void setChecklistDescription(String checklistDescription) {
        this.checklistDescription = checklistDescription;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
