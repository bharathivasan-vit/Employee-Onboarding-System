package com.employee.onboarding.employee_onboarding_system.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Configuration
public class ClientConfiguration {


	@Bean
	protected RestTemplate restTemplate() {
		return new OAuth2RestTemplate(oAuthDetails());
	}

	@Bean
	protected ClientCredentialsResourceDetails oAuthDetails() {

		ClientCredentialsResourceDetails resources = new ClientCredentialsResourceDetails();
		resources.setClientId(EOSConstants.CLIENT_ID);
		resources.setClientSecret(EOSConstants.CLIENT_SECRET);
		resources.setGrantType("client_credentials");
		resources.setAccessTokenUri(EOSConstants.TOKEN_URL);
		resources.setScope(Arrays.asList("resource-server-read", "resource-server-write"));
		resources.setAuthenticationScheme(AuthenticationScheme.form);
		return resources;
	}

    public static String getAccessToken() throws JsonParseException, JsonMappingException, IOException {

		String accessToken="";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= getMultiValueMap();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(EOSConstants.TOKEN_URL, request , String.class );

		final ObjectNode node = new ObjectMapper().readValue(response.getBody(), ObjectNode.class);

		if(node!=null && node.get("access_token")!=null) {
			accessToken= node.get("access_token").asText();
		}

		return accessToken;
	}

	public static HttpEntity getHttpHeaders(String accessToken) {
		HttpHeaders headers2 = new HttpHeaders();
		MultiValueMap<String, String> map= getMultiValueMap();

		headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers2.set("Authorization", "Bearer " + accessToken);
		headers2.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(map, headers2);

		return request2;

	}


	private static MultiValueMap getMultiValueMap() {
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		//map.add("username", "");
		//map.add("password", "");
		map.add("scope", "resource-server-read");
		map.add("scope", "resource-server-write");
		map.add("client_id", "purchaseandpayment_client");
		map.add("hrms", "secret");
		map.add("grant_type", "client_credentials");

		return map;
	}


}
