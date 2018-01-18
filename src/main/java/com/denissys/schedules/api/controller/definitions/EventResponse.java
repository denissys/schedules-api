package com.denissys.schedules.api.controller.definitions;

import java.util.List;

import com.denissys.schedules.api.dto.EventDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = EventResponse.EventResponseBuilder.class)
@Builder
@Value
@ApiModel("EventResponse")
public class EventResponse implements MessageReponse {

	private static final long serialVersionUID = 1L;

	private List<EventDto> eventDtos;
	
    @JsonPOJOBuilder(withPrefix = "")
    public static final class EventResponseBuilder {
    }

}
