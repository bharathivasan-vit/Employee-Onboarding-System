package com.employee.onboarding.employee_onboarding_system.config;

import java.math.BigDecimal;

public interface EOSConstants
{
	BigDecimal CAMPUS_ID_VLR= new BigDecimal(1);	
	BigDecimal CAMPUS_ID_CHN= new BigDecimal(2);
	String ESTATES_DIRECTOR_EMAIL_ID="xxxxx@vit.ac.in";	
	String BASE_URL="https://vconnectcc.vit.ac.in/vtopconnect/";
	String TOKEN_URL="https://vconnectcc.vit.ac.in/vtopconnect/oauth/token";

	String CLIENT_ID = "hrms";
	String CLIENT_SECRET = "secret";

//	String TOKEN_SERVER="http://172.16.0.70";
//	String HRMS_URL=TOKEN_SERVER+":"+"9001";
//	String COMMON_URL=TOKEN_SERVER+":"+"6001";
	
}
