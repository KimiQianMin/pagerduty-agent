package com.example.pagerdutyagent.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.pagerdutyagent.dao.IncidentRepositoryService;
import com.example.pagerdutyagent.dao.entity.IncidentEntity;
import com.example.pagerdutyagent.model.IncidentRespVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@Service
public class IncidentdutyService {
	
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final static ObjectMapper mapper = new ObjectMapper();

	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	
	private final static String URL_LIST_INCIDENT = "https://api.pagerduty.com/incidents";
	
	@Autowired
	private IncidentRepositoryService incidentRepositoryService;
	
	public List<IncidentRespVO> pullNewIncidents(LocalDateTime pSince, LocalDateTime pUntil) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		String lSinceParam = sdf.format(java.util.Date.from(pSince.atZone(ZoneId.systemDefault()).toInstant()));
		String lUntilParam = sdf.format(java.util.Date.from(pUntil.atZone(ZoneId.systemDefault()).toInstant()));
		
		logger.info("start to pull new incidents, lSinceParam = {}, lUntilParam = {}", lSinceParam, lUntilParam);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_LIST_INCIDENT)
				.queryParam("total", true)
				.queryParam("time_zone", "Asia/Singapore")
				.queryParam("since", lSinceParam)
				.queryParam("until", lUntilParam)
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

		JsonNode node = tree.at("/incidents");
		ObjectReader reader = mapper.readerFor(new TypeReference<List<IncidentRespVO>>(){});

		return reader.readValue(node);
	}
	
	public void saveNewIncidents(List<IncidentRespVO> pIncidents) {
		pIncidents.forEach(i -> {
			IncidentEntity incidentEntity = new IncidentEntity();
			try {
				BeanUtils.copyProperties(incidentEntity, i);
				logger.info("incidentEntity = {}", incidentEntity);
				incidentRepositoryService.save(incidentEntity);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		});
	}

}
