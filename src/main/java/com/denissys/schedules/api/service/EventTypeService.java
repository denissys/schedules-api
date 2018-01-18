package com.denissys.schedules.api.service;

import org.springframework.stereotype.Service;

import com.denissys.schedules.api.controller.definitions.EventTypeResponse;
import com.denissys.schedules.api.controller.definitions.MessageReponse;
import com.denissys.schedules.api.dto.enums.EventType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventTypeService {
	
	public MessageReponse list() {
		log.info("BEGIN get event type list");

		EventTypeResponse eventTypeResponse = EventTypeResponse.builder().types(EventType.values()).build();
		
		log.info("END get event type list");
		return eventTypeResponse;
	}
	
}
