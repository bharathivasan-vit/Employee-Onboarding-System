package com.employee.onboarding.employee_onboarding_system.service;

import com.employee.onboarding.employee_onboarding_system.entity.UserDetailsEntity;
import com.employee.onboarding.employee_onboarding_system.entity.UserOnboardingStatusEntity;
import com.employee.onboarding.employee_onboarding_system.exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.repository.CheckListRepo;
import com.employee.onboarding.employee_onboarding_system.repository.UserDetailsRepo;
import com.employee.onboarding.employee_onboarding_system.repository.UserOnboardingStatusRepo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EobUserServiceImpl implements EobUserService {
    @Autowired
    UserDetailsRepo userDetailsRepo;
    @Autowired
    CheckListRepo checkListRepo;
    @Autowired
    UserOnboardingStatusRepo userOnboardingStatusRepo;
    @Autowired
    EmailService emailService;
    @Override
    public String sendOtp(UserDetailsEntity userInput, String action){
        UserDetailsEntity existingUser = userDetailsRepo.findByEmail(userInput.getEmailId());
        int otpNumber = ThreadLocalRandom.current().nextInt(900_000)+100_000;
        String otp = String.valueOf(otpNumber);
        switch (action.toLowerCase()){
            case "register":
                if (existingUser != null) {
                    if(existingUser.isOtpVerified()){
                        throw new ResourceNotFoundException("User already registered please login");
                    }
                }else {
                    existingUser = new UserDetailsEntity();
                    existingUser.setUserName(userInput.getUserName());
                    existingUser.setPhoneNumber(userInput.getPhoneNumber());
                    existingUser.setEmailId(userInput.getEmailId());
                    existingUser.setRoleId(userInput.getRoleId());
                    existingUser.setOtpType("register");
                }
                break;
            case "login":
                if(existingUser == null){
                    throw new ResourceNotFoundException("User not found please register first");
                }else {
                    existingUser.setOtpType("login");
                }
                break;
            default:
                throw new ResourceNotFoundException("Invalid request");
        }
        existingUser.setOtp(otp);
        existingUser.setOtpVerified(false);
        existingUser.setOtpGeneratedTime(LocalDateTime.now());
        userDetailsRepo.save(existingUser);
        String msgSubject = "Regarding OTP for Employee Onboarding";
        String msgBody =
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<title>VIT Help Center (Chennai Campus)</title>" +
                        "</head>" +
                        "<body style=\"font-family: Arial, sans-serif;\">" +
                        "<table style=\"border-collapse: collapse; width: 100%;\">" +
                        "<tr>" +
                        "<th style=\"background-color: #04AA6D; color: white; padding: 8px; border: 1px solid #ddd;\">" +
                        "Dear User" +
                        "</th>" +
                        "</tr>" +
                        "<tr>" +
                        "<td style=\"padding: 8px; border: 1px solid #ddd;\">" +
                        "Welcome to VIT!...." +
                        "</td>" +
                        "</tr>" +
                        "</table>" +
                        "<p style=\"margin-top: 16px; font-size: 16px;\">" +
                        "<strong>Please use the following OTP for " + action + " to Employee Onboarding:</strong>" +
                        "</p>" +
                        "<p style=\"font-size: 22px; font-weight: bold; color: #0A66C2;\">" +
                        otp +
                        "</p>" +
                        "<p style=\"margin-top: 20px; font-size: 16px;\">" +
                        "Thanks and Regards<br/>" +
                        "Software Development Cell<br/>" +
                        "Vellore Institute of Technology<br/>" +
                        "Chennai - 600127" +
                        "</p>" +
                        "<p style=\"margin-top: 20px; font-style: italic;\">" +
                        "This is an auto-generated email hence do not reply." +
                        "</p>" +
                        "</body>" +
                        "</html>";

//        emailService.sendEmail(userInput.getEmailId(), msgSubject, msgBody);
//        return "OTP sent successfully To : "+ userInput.getEmailId() ;
        return "OTP sent successfully To : "+ userInput.getEmailId() +"-"+"Your OTP :"+otp;
    }
    @Override
    public UserDetailsEntity register(UserDetailsEntity userInput,HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        UserDetailsEntity existingUser = userDetailsRepo.findByEmail(userInput.getEmailId());
        if(existingUser == null){
            throw new ResourceNotFoundException("Please sent OTP first");
        }
        if(otpValidation(existingUser.getOtp(),existingUser.getOtpGeneratedTime(),userInput.getOtp())){
            existingUser.setUserName(userInput.getUserName());
            existingUser.setPhoneNumber(userInput.getPhoneNumber());
            existingUser.setRoleId(userInput.getRoleId());
            existingUser.setOtpVerified(true);
            existingUser.setCreatedDate(LocalDateTime.now());
            existingUser.setCreatedUser("SDC");
            existingUser.setModifiedDate(LocalDateTime.now());
            existingUser.setModifiedUser("SDC");
            existingUser.setIpAddress(ipAddress);
            userDetailsRepo.save(existingUser);
        }else{
            throw new ResourceNotFoundException("Invalid or Expired OTP");
        }
        insertUserOnboarding(existingUser.getUserId(),existingUser.getRoleId(),request);
        return existingUser;
    }
    @Override
    public UserDetailsEntity login(UserDetailsEntity userInput,HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        UserDetailsEntity existingUser = userDetailsRepo.findByEmail(userInput.getEmailId());
        if(existingUser == null){
            throw new ResourceNotFoundException("User not found please register first");
        }
        if(otpValidation(existingUser.getOtp(),existingUser.getOtpGeneratedTime(),userInput.getOtp())){
            existingUser.setOtpVerified(true);
            existingUser.setCreatedUser("SDC");
            existingUser.setModifiedDate(LocalDateTime.now());
            existingUser.setModifiedUser("SDC");
            existingUser.setIpAddress(ipAddress);
            userDetailsRepo.save(existingUser);
        }else {
            throw new ResourceNotFoundException("Invalid or Expired OTP");
        }
        return existingUser;
    }
    public boolean otpValidation(String otp,LocalDateTime otpGenratedTime,String inputOtp){
        LocalDateTime expiry = otpGenratedTime.plusMinutes(1);
        return otp.equals(inputOtp) && LocalDateTime.now().isBefore(expiry);
    }
    public void insertUserOnboarding(int userId,int roleId,HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        List<Integer> listOfcheckList = checkListRepo.findCheckListByRoleId(roleId);
        List<UserOnboardingStatusEntity> onboardingStatusList = new ArrayList<>();
        for (Integer check : listOfcheckList){
            if (userOnboardingStatusRepo.existingUserAndChecklist(userId, check) <= 0) {
                System.out.println(userOnboardingStatusRepo.existingUserAndChecklist(userId, check));
                UserOnboardingStatusEntity userOnboardingStatus = new UserOnboardingStatusEntity();
                userOnboardingStatus.setUserId(userId);
                userOnboardingStatus.setCheckListId(check);
                userOnboardingStatus.setStatus(false);
                userOnboardingStatus.setCreatedDate(LocalDateTime.now());
                userOnboardingStatus.setCreatedUser("SDC");
                userOnboardingStatus.setModifiedDate(LocalDateTime.now());
                userOnboardingStatus.setModifiedUser("SDC");
                userOnboardingStatus.setIpAddress(ipAddress);
                onboardingStatusList.add(userOnboardingStatus);
            }

        }
        userOnboardingStatusRepo.saveAll(onboardingStatusList);
    }
}
