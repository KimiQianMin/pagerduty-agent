package com.example.pagerdutyagent.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogEntryRespVO {

	@JsonProperty("type")
	private String type;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("incident")
	private IncidentRespVO incident;

	@JsonProperty("event_details")
	private EventDetails eventDetails;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public IncidentRespVO getIncident() {
		return incident;
	}

	public void setIncident(IncidentRespVO incident) {
		this.incident = incident;
	}

	public EventDetails getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(EventDetails eventDetails) {
		this.eventDetails = eventDetails;
	}

	@Override
	public String toString() {
		return "LogEntryRespVO [type=" + type + ", createdAt=" + createdAt + ", incident=" + incident
				+ ", eventDetails=" + eventDetails + "]";
	}

	public static class EventDetails {

		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "EventDetails [description=" + description + "]";
		}

	}

}
