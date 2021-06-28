package com.example.pagerdutyagent.scheduler;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.pagerdutyagent.model.IncidentRespVO;
import com.example.pagerdutyagent.model.LogEntryRespVO;
import com.example.pagerdutyagent.service.IncidentdutyService;
import com.example.pagerdutyagent.service.LogEntryService;

@Configuration
@EnableScheduling
@Component
public class PagerdutyPullScheduler {

	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private IncidentdutyService incidentService;
	
	@Autowired
	private LogEntryService logEntityService;

	private final int PULL_NEW_INCIDENT_INTERVAL = 1;

	@Scheduled(cron = "${pull.new.incidents:0 */1 * * * *}")
	public void pullNewIncidents() throws IOException {

		logger.info("pullNewIncidents");

		// LocalDateTime lSince = LocalDateTime.parse("2021-06-22T15:00:00");

		LocalDateTime lJustNow = LocalDateTime.now().minusMinutes(1);
		LocalDateTime lSince = lJustNow.truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime lUntil = lJustNow.plusMinutes(PULL_NEW_INCIDENT_INTERVAL).truncatedTo(ChronoUnit.MINUTES)
				.minusNanos(1);

		List<IncidentRespVO> incidents = incidentService.pullNewIncidents(lSince, lUntil);

		incidentService.saveNewIncidents(incidents);
	}

	@Scheduled(cron = "${pull.new.log.entities:0 */1 * * * *}")
	public void pullNewLogEntities() throws IOException {

		logger.info("pullNewLogEntities");

		// LocalDateTime lSince = LocalDateTime.parse("2021-06-22T15:00:00");

		LocalDateTime lJustNow = LocalDateTime.now().minusMinutes(1);
		LocalDateTime lSince = lJustNow.truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime lUntil = lJustNow.plusMinutes(PULL_NEW_INCIDENT_INTERVAL).truncatedTo(ChronoUnit.MINUTES)
				.minusNanos(1);

		List<LogEntryRespVO> lLogEntryRespVOList = logEntityService.pullNewLogEntities(lSince, lUntil);

		lLogEntryRespVOList.forEach(l -> {
			switch (l.getType()) {
			//case "trigger_log_entry":
				//break;
			case "acknowledge_log_entry":
				//TODO: acknowledge status update, if incident is in DB, then call JIRA to update status, else create Jira
				logger.info("incident was acknowledged");
				break;
			case "resolve_log_entry":
				//TODO: resolve status update, if incident is in DB, then call JIRA to update status, else create Jira
				logger.info("incident was resolved");
				break;
			case "assign_log_entry":
				//TODO: assign incident, if incident is in DB, then call JIRA to update status, else create Jira
				logger.info("incident was assigned");
				break;
			case "status_update_log_entry":
				//TODO: incident status update, if incident is in DB, then call JIRA to update status, else create Jira
				logger.info("status update for incident");
				break;
			case "annotate_log_entry":
				//TODO: add note into incident, if incident is in DB, then call JIRA to update status, else create Jira
				logger.info("add note for incident");
				break;
			default:
			}
		});

	}

}
