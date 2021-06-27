package com.example.pagerdutyagent.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "incident")
@DynamicUpdate
public class IncidentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "incident_number", length = 100)
	private String incidentNumber;

	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "description", length = 100)
	private String description;

	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "status")
	private String status;

	@Column(name = "urgency")
	private String urgency;

	@Column(name = "summary")
	private String summary;

	@Column(name = "incidentKey")
	private String incidentKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIncidentNumber() {
		return incidentNumber;
	}

	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIncidentKey() {
		return incidentKey;
	}

	public void setIncidentKey(String incidentKey) {
		this.incidentKey = incidentKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IncidentEntity [id=" + id + ", incidentNumber=" + incidentNumber + ", title=" + title + ", description="
				+ description + ", createdAt=" + createdAt + ", status=" + status + ", urgency=" + urgency
				+ ", summary=" + summary + ", incidentKey=" + incidentKey + "]";
	}

}
