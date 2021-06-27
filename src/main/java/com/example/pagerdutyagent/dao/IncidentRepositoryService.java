package com.example.pagerdutyagent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pagerdutyagent.dao.entity.IncidentEntity;

@Service
public class IncidentRepositoryService {

	@Autowired
	private IncidentRepository incidentRepository;

	public IncidentEntity save(IncidentEntity pIncidentEntity) {
		return incidentRepository.save(pIncidentEntity);
	}
	
}
