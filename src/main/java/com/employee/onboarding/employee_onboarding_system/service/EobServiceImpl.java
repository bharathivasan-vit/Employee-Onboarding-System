package com.employee.onboarding.employee_onboarding_system.service;

import com.employee.onboarding.employee_onboarding_system.entity.*;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.repository.RoleListRepo;
import com.employee.onboarding.employee_onboarding_system.repository.UserDetailsRepo;
import com.employee.onboarding.employee_onboarding_system.repository.UserOnboardingStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EobServiceImpl implements EobService {
    @Autowired RoleListRepo roleListRepo;
    @Autowired UserDetailsRepo userDetailsRepo;
    @Autowired UserOnboardingStatusRepo userOnboardingStatusRepo;
    @Override
    public List<Map<String,Object>> getAllRoleList() throws ResourceNotFoundException{
        List<Map<String,Object>> roles = roleListRepo.findAllVisibleRoles();
        if(roles.isEmpty()){
            throw new ResourceNotFoundException("Roles not found");
        }
        return roles;
    }
    @Override
    public List<Map<String, Object>> getUsersById(int id) throws ResourceNotFoundException{
        List<Map<String, Object>> user = userDetailsRepo.findByUserId(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User records Not found");
        }
        return user;
    }
    @Override
    public List<Map<String, Object>> getChecklistForUser(int id) throws ResourceNotFoundException{
        List<Map<String, Object>> checkList = userOnboardingStatusRepo.findChecklistByUserId(id);
        if(checkList.isEmpty()){
            throw new ResourceNotFoundException("CheckList records Not found");
        }
        return checkList;
    }
    @Override
    public String setStatus(int userId,int checkListId,boolean status) throws ResourceNotFoundException{
        LocalDateTime currentTime = LocalDateTime.now();
        int rowsUpdated = userOnboardingStatusRepo.findByUserAndChecklist(userId,checkListId,status,currentTime);
        if(rowsUpdated == 0){
            throw new ResourceNotFoundException("No onboarding records found");
        }
        return "status updated : "+status;
    }
}
