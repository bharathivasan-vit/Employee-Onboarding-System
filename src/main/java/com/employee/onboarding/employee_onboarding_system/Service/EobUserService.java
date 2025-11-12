package com.employee.onboarding.employee_onboarding_system.Service;

import com.employee.onboarding.employee_onboarding_system.Entity.UserDetailsEntity;
import jakarta.servlet.http.HttpServletRequest;

public interface EobUserService {
    String sendOtp(UserDetailsEntity userInput,String action);
    UserDetailsEntity register(UserDetailsEntity userInput, HttpServletRequest request);
    UserDetailsEntity login(UserDetailsEntity userInput,HttpServletRequest request);
}
