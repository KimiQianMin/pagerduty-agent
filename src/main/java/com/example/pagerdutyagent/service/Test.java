package com.example.pagerdutyagent.service;

import java.io.IOException;
import java.util.List;

import com.example.pagerdutyagent.model.IncidentRespVO;
import com.example.pagerdutyagent.model.LogEntryRespVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class Test {
	
	private final static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) throws IOException {
		
//		String incidentStr = "{\"incidents\":[],\"limit\":25,\"offset\":0,\"total\":0,\"more\":false}";
//		
//		JsonNode tree = mapper.readTree(incidentStr);
//
//		JsonNode node = tree.at("/incidents");
//
//		ObjectReader reader = mapper.readerFor(new TypeReference<List<IncidentRespVO>>(){});
//		
//		List<IncidentRespVO> list = reader.readValue(node);
//		
//		System.out.println(list);
		
		String logEntryStr = "{\"log_entries\":[{\"id\":\"R22KUKFT43ZCJZDAHXFQNOQJU8\",\"type\":\"trigger_log_entry\",\"summary\":\"Triggered through the website\",\"self\":\"https://api.pagerduty.com/log_entries/R22KUKFT43ZCJZDAHXFQNOQJU8\",\"html_url\":\"https://kimiqian.pagerduty.com/incidents/PO0XEW9/log_entries/R22KUKFT43ZCJZDAHXFQNOQJU8\",\"created_at\":\"2021-06-27T12:38:45Z\",\"agent\":{\"id\":\"PLHE8HQ\",\"type\":\"user_reference\",\"summary\":\"Kimi Qian\",\"self\":\"https://api.pagerduty.com/users/PLHE8HQ\",\"html_url\":\"https://kimiqian.pagerduty.com/users/PLHE8HQ\"},\"channel\":{\"type\":\"web_trigger\",\"summary\":\"Payment Processing issue 888\"},\"service\":{\"id\":\"PTD6VK2\",\"type\":\"service_reference\",\"summary\":\"Payment Processing\",\"self\":\"https://api.pagerduty.com/services/PTD6VK2\",\"html_url\":\"https://kimiqian.pagerduty.com/service-directory/PTD6VK2\"},\"incident\":{\"id\":\"PO0XEW9\",\"type\":\"incident_reference\",\"summary\":\"[#16] Payment Processing issue 888\",\"self\":\"https://api.pagerduty.com/incidents/PO0XEW9\",\"html_url\":\"https://kimiqian.pagerduty.com/incidents/PO0XEW9\"},\"teams\":[],\"contexts\":[],\"event_details\":{\"description\":\"Payment Processing issue 888\"}}],\"limit\":1,\"offset\":1,\"more\":true,\"total\":46}\n" + 
				"";
		
		JsonNode tree1 = mapper.readTree(logEntryStr);

		JsonNode node1 = tree1.at("/log_entries");

		ObjectReader reader1 = mapper.readerFor(new TypeReference<List<LogEntryRespVO>>(){});
		
		List<LogEntryRespVO> list1 = reader1.readValue(node1);
		
		System.out.println(list1);
		
	}

}
