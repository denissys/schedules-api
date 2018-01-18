package com.denissys.schedules.api.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.denissys.schedules.api.dto.enums.EventType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

/**
 * Event Data Transfer Object
 */
@JsonDeserialize(builder = EventDto.EventDtoBuilder.class)
@Builder
@Value
@ApiModel("EventDto")
public class EventDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
    @ApiModelProperty(value = "Owner id", required = true)
	private String ownerId;
	
    @ApiModelProperty(value = "Owner id", required = true)
	private String eventId;
	
	@NotNull
    @ApiModelProperty(value = "Start Date/Time", required = true)
	private Date startDatetime;
	
	@NotNull
    @ApiModelProperty(value = "End Date/Time", required = true)
	private Date endDatetime;
	
	@NotNull
    @ApiModelProperty(value = "Event types", required = true)
	private EventType type;
	
	@NotNull
    @ApiModelProperty(value = "Event active/inactive", required = true)
	private boolean active;
	
    @JsonPOJOBuilder(withPrefix = "")
    public static final class EventDtoBuilder {
    }

}
