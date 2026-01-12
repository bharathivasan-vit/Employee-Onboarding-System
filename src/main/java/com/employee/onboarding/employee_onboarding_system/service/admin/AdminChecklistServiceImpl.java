package com.employee.onboarding.employee_onboarding_system.service.admin;

import com.employee.onboarding.employee_onboarding_system.entity.CheckListEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.repository.CheckListRepo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AdminChecklistServiceImpl implements AdminChecklistService{
    @Autowired CheckListRepo checkListRepo;
    public List<Map<String,Object>> getAllChecklist() throws ResourceNotFoundException{
        List<Map<String, Object>> checklist = checkListRepo.findAllChecklist();
        if(checklist.isEmpty()){
            throw new ResourceNotFoundException("Checklist not found");
        }
        return checklist;
    }
    public List<Map<String,Object>> getChecklistById(int checklistId) throws ResourceNotFoundException{
        List<Map<String, Object>> checklist = checkListRepo.findChecklistById(checklistId);
        if(checklist.isEmpty()){
            throw new ResourceNotFoundException("Checklist not found");
        }
        return checklist;
    }
    public List<Map<String,Object>> getChecklistByName(String checklistName) throws ResourceNotFoundException{
        List<Map<String, Object>> checklist = checkListRepo.findChecklistByName(checklistName);
        if(checklist.isEmpty()){
            throw new ResourceNotFoundException("Checklist not found");
        }
        return checklist;
    }
    public List<Map<String,Object>> getChecklistByRole(int roleId) throws ResourceNotFoundException{
        List<Map<String, Object>> checklist = checkListRepo.findChecklistByRole(roleId);
        if(checklist.isEmpty()){
            throw new ResourceNotFoundException("Checklist Id not found for the give role Id");
        }
        return checklist;
    }
    public CheckListEntity createChecklist(CheckListEntity inputChecklis, HttpServletRequest request) throws NullPointerException{
        String ipAddress = request.getRemoteAddr();
        if (inputChecklis.getCheckListContent().isEmpty()){
            throw new NullPointerException("Checklist input is null");
        }
        CheckListEntity checkList = new CheckListEntity();
        checkList.setRoleId(inputChecklis.getRoleId());
        checkList.setCheckListContent(inputChecklis.getCheckListContent());
        checkList.setChecklistDescription(inputChecklis.getChecklistDescription());
        checkList.setCreatedUser(inputChecklis.getCreatedUser());
        checkList.setCreatedDate(LocalDateTime.now());
        checkList.setModifiedUser(inputChecklis.getCreatedUser());
        checkList.setModifiedDate(LocalDateTime.now());
        checkList.setIpAddress(ipAddress);
        checkList.setActive(inputChecklis.isActive());

        return checkListRepo.save(checkList);
    }
    public CheckListEntity updateChecklist(int checklistId, CheckListEntity inputChecklis, HttpServletRequest request) throws NullPointerException,ResourceNotFoundException{
        String ipAddress = request.getRemoteAddr();
        CheckListEntity existingChecklist = checkListRepo.findByChecklistIdUpadte(checklistId);
        if(null != existingChecklist){
            if (inputChecklis.getCheckListContent().isEmpty()){
                throw new NullPointerException("Checklist input is null");
            }
            existingChecklist.setRoleId(inputChecklis.getRoleId());
            existingChecklist.setCheckListContent(inputChecklis.getCheckListContent());
            existingChecklist.setChecklistDescription(inputChecklis.getChecklistDescription());
            existingChecklist.setModifiedUser(inputChecklis.getModifiedUser());
            existingChecklist.setModifiedDate(LocalDateTime.now());
            existingChecklist.setIpAddress(ipAddress);
            existingChecklist.setActive(inputChecklis.isActive());
        }else {
            throw new ResourceNotFoundException("CheckList not found");
        }
        return checkListRepo.save(existingChecklist);
    }
}
