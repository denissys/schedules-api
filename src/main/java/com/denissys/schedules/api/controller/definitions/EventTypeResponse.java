package com.denissys.schedules.api.controller.definitions;

import javax.validation.constraints.NotNull;

import com.denissys.schedules.api.dto.enums.EventType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = EventResponse.EventResponseBuilder.class)
@Builder
@Value
@ApiModel("EventTypeResponse")
public class EventTypeResponse implements MessageReponse {

	private static final long serialVersionUID = 1L;

	@NotNull
	private EventType[] types;
	
	@JsonPOJOBuilder(withPrefix = "")
    public static final class EventTypeResponseBuilder {
    }

}
