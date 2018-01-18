package com.denissys.schedules.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denissys.schedules.api.controller.definitions.MessageReponse;
import com.denissys.schedules.api.service.EventTypeService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@Api("/api/v1/schedules/types")
@RequestMapping(value = "/api/v1/schedules/types", produces = APPLICATION_JSON_VALUE)
public class EventTypesRestController {

	@Autowired
	private EventTypeService eventTypeService;

	@GetMapping
	public ResponseEntity<MessageReponse> getEventTypeList() {
		log.info("BEGIN get event type list");
		
		final MessageReponse messageReponse = this.eventTypeService.list();
		ResponseEntity<MessageReponse> responseEntity = new ResponseEntity<>(messageReponse, HttpStatus.OK);

		log.info("END get event type list");
		return responseEntity;
	}
	
}
