package com.denissys.schedules.api.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denissys.schedules.api.adapter.EventAdapter;
import com.denissys.schedules.api.controller.definitions.EventResponse;
import com.denissys.schedules.api.controller.definitions.MessageReponse;
import com.denissys.schedules.api.dto.EventDto;
import com.denissys.schedules.api.model.entity.Event;
import com.denissys.schedules.api.model.repository.EventRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public MessageReponse find(final Optional<String> ownerId, final Optional<String> eventId) {
		log.info("BEGIN get events: ownerId={}, eventId={}", ownerId, eventId);

		List<Event> events;
		if (eventId.isPresent()) {
			events = eventRepository.findBy(ownerId.get(), ownerId.get());
		} else {
			events = eventRepository.findBy(ownerId.get());
		}
		EventResponse eventResponse = EventResponse.builder()
				.eventDtos(EventAdapter.modelToDto(events))
				.build();
		
		log.info("END get events");
		return eventResponse;
	}
	
	public MessageReponse create(final EventDto eventDto) {
		log.info("BEGIN create event schedule: {}", eventDto);
		
		Event event = eventRepository.save(EventAdapter.dtoToModel(eventDto));
		List<EventDto> events = newArrayList(EventAdapter.modelToDto(event));
		EventResponse eventResponse = EventResponse.builder().eventDtos(events).build();
		
		log.info("END create events");
		return eventResponse;
	}

	public void update(EventDto eventDto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Optional<String> ownerId, Optional<String> eventId) {
		// TODO Auto-generated method stub
		
	}
	
}
