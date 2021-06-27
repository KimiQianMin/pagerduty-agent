package com.example.pagerdutyagent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.pagerdutyagent.dao.entity.IncidentEntity;

@Repository
public interface IncidentRepository extends CrudRepository<IncidentEntity, Long> {

}
