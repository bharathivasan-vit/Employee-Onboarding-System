package com.employee.onboarding.employee_onboarding_system.Service;

import com.employee.onboarding.employee_onboarding_system.Entity.UserDetailsEntity;
import com.employee.onboarding.employee_onboarding_system.Entity.UserOnboardingStatusEntity;
import com.employee.onboarding.employee_onboarding_system.Exception.ResourceNotFoundException;
import com.employee.onboarding.employee_onboarding_system.Repository.CheckListRepo;
import com.employee.onboarding.employee_onboarding_system.Repository.UserDetailsRepo;
import com.employee.onboarding.employee_onboarding_system.Repository.UserOnboardingStatusRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        int otpNumber = new Random().nextInt(900_000)+100_000;
        String otp = String.valueOf(otpNumber);
        switch (action.toLowerCase()){
            case "register":
                if (existingUser != null) {
                    if(existingUser.isOtpVerified()){
                        throw new ResourceNotFoundException("User already registered please login");
                    }
                }else {
                    existingUser = new UserDetailsEntity();
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
        System.out.println("OTP sent to : "+userInput.getEmailId()+"-"+otp);
        return "OTP sent successfully To : "+ userInput.getEmailId() +"-"+"Your OTP :"+otp;
//        emailService.sendSimpleEmail(
//                userInput.getEmailId(),
//                "OTP For "+action,
//                "Your One-Time Password(OTP) is : "+otp+" Please use this OTP to complete your "+action+" process. Do not share this code with anyone."
//        );
//        return "OTP sent successfully To : "+ userInput.getEmailId() +"-"+"Your OTP :"+otp;
    }
    @Override
    public UserDetailsEntity register(UserDetailsEntity userInput,HttpServletRequest request){
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
            existingUser.setIpAddress(getClientIp(request));
            userDetailsRepo.save(existingUser);
        }else{
            throw new ResourceNotFoundException("Invalid or Expired OTP");
        }
        insertUserOnboarding(existingUser.getUserId(),existingUser.getRoleId(),request);
        return existingUser;
    }
    @Override
    public UserDetailsEntity login(UserDetailsEntity userInput,HttpServletRequest request){
         UserDetailsEntity existingUser = userDetailsRepo.findByEmail(userInput.getEmailId());
        if(existingUser == null){
            throw new ResourceNotFoundException("User not found please register first");
        }
        if(otpValidation(existingUser.getOtp(),existingUser.getOtpGeneratedTime(),userInput.getOtp())){
            existingUser.setOtpVerified(true);
            existingUser.setCreatedDate(LocalDateTime.now());
            existingUser.setCreatedUser("SDC");
            existingUser.setModifiedDate(LocalDateTime.now());
            existingUser.setModifiedUser("SDC");
            existingUser.setIpAddress(getClientIp(request));
            userDetailsRepo.save(existingUser);
        }else {
            throw new ResourceNotFoundException("Invalid or Expired OTP");
        }
        return existingUser;
    }
    public boolean otpValidation(String otp,LocalDateTime otpGenratedTime,String inputOtp){
        LocalDateTime expiry = otpGenratedTime.plusMinutes(1);
        if(otp.equals(inputOtp) && LocalDateTime.now().isBefore(expiry)){
            return true;
        } else {
            return false;
        }
    }
    public void insertUserOnboarding(int userId,int roleId,HttpServletRequest request){
        List<Integer> listOfcheckList = checkListRepo.findCheckListByRoleId(roleId);
        List<UserOnboardingStatusEntity> onboardingStatusList = new ArrayList<UserOnboardingStatusEntity>();
        for (Integer check : listOfcheckList){
            UserOnboardingStatusEntity userOnboardingStatus = new UserOnboardingStatusEntity();
            userOnboardingStatus.setUserId(userId);
            userOnboardingStatus.setCheckListId(check);
            userOnboardingStatus.setStatus(false);
            userOnboardingStatus.setCreatedDate(LocalDateTime.now());
            userOnboardingStatus.setCreatedUser("SDC");
            userOnboardingStatus.setModifiedDate(LocalDateTime.now());
            userOnboardingStatus.setModifiedUser("SDC");
            userOnboardingStatus.setIpAddress(getClientIp(request));
            onboardingStatusList.add(userOnboardingStatus);
        }
        userOnboardingStatusRepo.saveAll(onboardingStatusList);
    }
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        return ip;
    }
}
