package com.denissys.schedules.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denissys.schedules.api.controller.definitions.MessageReponse;
import com.denissys.schedules.api.dto.EventDto;
import com.denissys.schedules.api.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@Api("/api/v1/schedules")
@RequestMapping(value = "/api/v1/schedules", produces = APPLICATION_JSON_VALUE)
public class SchedulesRestController {

	@Autowired
	private EventService eventService;

	@GetMapping
	public ResponseEntity<MessageReponse> getEvents(
			@ApiParam(name = "ownerId", required = true)
			@RequestParam(value = "ownerId", required = true) Optional<String> ownerId,
			
			@ApiParam(name = "eventId", required = false)
			@RequestParam(value = "eventId", required = false) Optional<String> eventId) {

		final MessageReponse messageReponse = this.eventService.find(ownerId, eventId);
		return new ResponseEntity<>(messageReponse, HttpStatus.OK);
	}
	
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create event", nickname = "event")
    public void createEvent(@ApiParam(name = "EventDto",
        value = "Create Event Request Body") @RequestBody @Valid final EventDto eventDto) {
    	
        log.debug("BEGIN createEvent: {}", eventDto);
        eventService.create(eventDto);
        log.debug("END createEvent.");
    }
    
    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create event", nickname = "event")
    public void updateEvent(@ApiParam(name = "EventDto",
        value = "Create Event Request Body") @RequestBody @Valid final EventDto eventDto) {
    	
        log.debug("BEGIN createEvent: {}", eventDto);
        eventService.update(eventDto);
        log.debug("END createEvent.");
    }
    
    @DeleteMapping
	public void deleteEvents(
			@ApiParam(name = "ownerId", required = true)
			@RequestParam(value = "ownerId", required = true) Optional<String> ownerId,
			
			@ApiParam(name = "eventId", required = true)
			@RequestParam(value = "eventId", required = true) Optional<String> eventId) {

		this.eventService.inactivate(ownerId, eventId);
	}

}
