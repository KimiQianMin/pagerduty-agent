package com.example.pagerdutyagent.scheduler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.pagerdutyagent.dao.entity.IncidentEntity;
import com.example.pagerdutyagent.model.IncidentRespVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class Tester {

	private final static String URL_INCIDENT_LIST = "https://api.pagerduty.com/incidents?total=true&time_zone=UTC";

	private final static ObjectMapper mapper = new ObjectMapper();

	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	
	public static void main(String[] args) throws IOException {

		RestTemplate restTemplate = new RestTemplate();

		LocalDateTime sinceDT = LocalDateTime.parse("2021-06-22T15:00:00");
		LocalDateTime untilDT = LocalDateTime.parse("2021-06-22T15:59:59");
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_INCIDENT_LIST)
				.queryParam("total", true)
				.queryParam("since", sdf.format(java.util.Date.from(sinceDT.atZone(ZoneId.systemDefault()).toInstant())))
				.queryParam("until", sdf.format(java.util.Date.from(untilDT.atZone(ZoneId.systemDefault()).toInstant())))
				.queryParam("time_zone", "Asia/Singapore");

		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/vnd.pagerduty+json;version=2");
		headers.set("authorization", "Token token=u+ym_snxy-jVH7Htq2mw");
		headers.set("content-type", "application/json");

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				String.class);
		System.out.println(response.getBody());

		JsonNode tree = mapper.readTree(response.getBody());

		JsonNode node = tree.at("/incidents");
		ObjectReader reader = mapper.readerFor(new TypeReference<List<IncidentRespVO>>() {
		});

		List<IncidentRespVO> list = reader.readValue(node);
		
		list.forEach(i -> {
			System.out.println(i);
			IncidentEntity incidentEntity = new IncidentEntity();
			try {
				BeanUtils.copyProperties(incidentEntity, i);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			System.out.println(incidentEntity);
		});
		
		System.out.println(list);
		
		LocalDateTime lNow = LocalDateTime.now();
		LocalDateTime lSince = lNow.truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime lUntil = lNow.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES).minusNanos(1);
		
		System.out.println(lSince);
		System.out.println(lUntil);

	}

}
