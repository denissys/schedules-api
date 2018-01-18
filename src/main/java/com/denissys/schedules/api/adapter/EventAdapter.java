package com.denissys.schedules.api.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.denissys.schedules.api.dto.EventDto;
import com.denissys.schedules.api.model.entity.Event;

public class EventAdapter {

	private static final String EVENT_NOT_NULL = "event must not be null";
	private static final String EVENT_DTO_NOT_NULL = "event dto must not be null";
	private static final String EVENTS_NOT_NULL = "events list must not be null";
	private static final String EVENTS_DTO_NOT_NULL = "events dto list must not be null";

	public static EventDto modelToDto(final Event event) {
		Assert.notNull(event, EVENT_NOT_NULL);
		return EventDto.builder()
				.ownerId(event.getOwnerId())
				.eventId(event.getEventId())
				.startDatetime(event.getStartDatetime())
				.endDatetime(event.getEndDatetime())
				.active(event.getActive())
				.build();
	}

	public static Event dtoToModel(final EventDto eventDto) {
		Assert.notNull(eventDto, EVENT_DTO_NOT_NULL);
		return Event.builder()
				.ownerId(eventDto.getOwnerId())
				.eventId(eventDto.getEventId())
				.startDatetime(eventDto.getStartDatetime())
				.endDatetime(eventDto.getEndDatetime())
				.active(eventDto.isActive())
				.build();
	}

	public static List<EventDto> modelToDto(final List<Event> events) {
		Assert.notNull(events, EVENTS_DTO_NOT_NULL);
		List<EventDto> eventsDto = events.stream().map(event -> modelToDto(event)).collect(Collectors.toList());
		return eventsDto;
	}

	public static List<Event> dtoToModel(final List<EventDto> eventsDto) {
		Assert.notNull(eventsDto, EVENTS_NOT_NULL);
		List<Event> events = eventsDto.stream().map(eventDto -> dtoToModel(eventDto)).collect(Collectors.toList());
		return events;
	}

	public static Event matchDtoToModel(EventDto eventDto, Event event) {
		Assert.notNull(eventDto, EVENT_DTO_NOT_NULL);
		Assert.notNull(event, EVENT_NOT_NULL);
		return Event.builder()
					.eventId(event.getEventId())
					.ownerId(event.getOwnerId())
					.startDatetime(eventDto.getStartDatetime())
					.endDatetime(eventDto.getEndDatetime())
					.type(eventDto.getType().toString())
					.active(eventDto.isActive())
					.build();
	}

}
