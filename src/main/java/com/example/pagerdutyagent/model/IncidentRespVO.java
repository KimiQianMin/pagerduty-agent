package com.example.pagerdutyagent.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentRespVO {

	@JsonProperty("incident_number")
	private String incidentNumber;

	@JsonProperty("title")
	private String title;

	@JsonProperty("description")
	private String description;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("status")
	private String status;

	@JsonProperty("urgency")
	private String urgency;

	@JsonProperty("id")
	private String incidentKey;

	@JsonProperty("summary")
	private String summary;

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

	public String getIncidentKey() {
		return incidentKey;
	}

	public void setIncidentKey(String incidentKey) {
		this.incidentKey = incidentKey;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IncidentRespVO [incidentNumber=" + incidentNumber + ", title=" + title + ", description=" + description
				+ ", createdAt=" + createdAt + ", status=" + status + ", urgency=" + urgency + ", incidentKey="
				+ incidentKey + ", summary=" + summary + "]";
	}

}
