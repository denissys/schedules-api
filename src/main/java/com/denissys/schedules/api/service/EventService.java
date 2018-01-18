package com.denissys.schedules.api.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denissys.schedules.api.adapter.EventAdapter;
import com.denissys.schedules.api.controller.definitions.EventResponse;
import com.denissys.schedules.api.controller.definitions.MessageReponse;
import com.denissys.schedules.api.dto.EventDto;
import com.denissys.schedules.api.model.entity.Event;
import com.denissys.schedules.api.model.repository.EventRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public MessageReponse find(final Optional<String> ownerId, final Optional<String> eventId) {
		log.info("BEGIN get events: ownerId={}, eventId={}", ownerId, eventId);

		List<Event> events = findBy(ownerId, eventId);
		EventResponse eventResponse = EventResponse.builder()
				.eventDtos(EventAdapter.modelToDto(events))
				.build();
		
		log.info("END get events");
		return eventResponse;
	}

	private List<Event> findBy(final Optional<String> ownerId, final Optional<String> eventId) {
		List<Event> events;
		if (eventId.isPresent()) {
			events = eventRepository.findBy(ownerId.get(), eventId.get());
		} else {
			events = eventRepository.findBy(ownerId.get());
		}
		return events;
	}
	
	public MessageReponse create(final EventDto eventDto) {
		log.info("BEGIN create event: {}", eventDto);
		
		Event event = eventRepository.save(EventAdapter.dtoToModel(eventDto));
		List<EventDto> events = newArrayList(EventAdapter.modelToDto(event));
		EventResponse eventResponse = EventResponse.builder().eventDtos(events).build();
		
		log.info("END create event");
		return eventResponse;
	}

	public void update(EventDto eventDto) {
		log.info("BEGIN update event: {}", eventDto);
		
		Event event = eventRepository.findOneBy(eventDto.getOwnerId(), eventDto.getEventId());
		eventRepository.save(EventAdapter.matchDtoToModel(eventDto, event));
		
		log.info("END update event");
		
	}

	public void inactivate(Optional<String> ownerId, Optional<String> eventId) {
		log.info("BEGIN inactivate events: ownerId={}, eventId={}", ownerId, eventId);
		
		List<Event> events = findBy(ownerId, eventId).stream()
			.map(event -> event.inactive())
			.collect(Collectors.toList());
		eventRepository.save(events);
		
		log.info("END inactivate events");
	}
	
}
