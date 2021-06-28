package com.example.pagerdutyagent.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.pagerdutyagent.model.UserRespVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final static ObjectMapper mapper = new ObjectMapper();

	private final static String URL_LIST_USERS = "https://api.pagerduty.com/users";

	private Map<String, UserRespVO> UserRespVOMap = new HashMap<>();

	public void loadUsers() throws IOException {
		RestTemplate restTemplate = new RestTemplate();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_LIST_USERS)
				.queryParam("total", true)
				;

		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/vnd.pagerduty+json;version=2");
		headers.set("authorization", "Token token=u+ym_snxy-jVH7Htq2mw");
		headers.set("content-type", "application/json");

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);
		logger.info("response.getBody = {}", response.getBody());

		JsonNode tree = mapper.readTree(response.getBody());

		JsonNode node = tree.at("/users");

		ObjectReader reader = mapper.readerFor(new TypeReference<List<UserRespVO>>() {
		});

		List<UserRespVO> users = reader.readValue(node);

		users.forEach(u -> {
			UserRespVOMap.put(u.getName(), u);
		});
		
	}
	
}
