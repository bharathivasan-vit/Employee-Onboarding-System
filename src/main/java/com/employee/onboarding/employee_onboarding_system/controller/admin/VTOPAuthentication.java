package com.employee.onboarding.employee_onboarding_system.controller.admin;

import javax.servlet.http.HttpSession;

import com.employee.onboarding.employee_onboarding_system.config.EOSConstants;
import com.employee.onboarding.employee_onboarding_system.entity.User;
import com.employee.onboarding.employee_onboarding_system.entity.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.vtop.crypto.core.VTOPCryptEngine;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class VTOPAuthentication {

	@Autowired
	RestTemplate restTemplate;

	@Autowired private User user;

    @PostMapping("/eobs/adminLogin")
	public UserAuthentication doLoginVtop(UserAuthentication userAuth , HttpSession session) {

		String userId = userAuth.getUserId();
		String password = userAuth.getPasswd();

		ObjectMapper objMap = new  ObjectMapper();
		JsonNode result = null;
		try{

			String plainText=userId.trim() + ":" + password.trim();
			VTOPCryptEngine engine = new VTOPCryptEngine();
			engine.setPublicKeyPath("KeyPair/publicKey");
			engine.setActionMode(VTOPCryptEngine.ENCRYPTION); engine.initializeKeys();

			byte[] encryptedText= engine.encryptText(plainText);

			String baseUrl = EOSConstants.BASE_URL +"cas/authenticate/user";

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			httpHeaders.setContentLength(encryptedText.length);
			HttpEntity<byte[]> entity = new HttpEntity<byte[]>(encryptedText, httpHeaders);


			ResponseEntity<String> responseString = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);

//			System.out.println("responseString ==> "+ responseString.getBody());

			if(null!=responseString.getBody())
			{
				result = objMap.readTree(responseString.getBody());
				userAuth.setUserId(result.get("userId").asText());
				userAuth.setAuthenticated(result.get("authentication").asBoolean());
			}
			userId=userAuth.getUserId();
			user.setUserId(userId);
			session.setAttribute("user", user);
		}
		catch(Exception ex)
		{
//			logger.info(ex);
		}
		return userAuth;
	}


	}
