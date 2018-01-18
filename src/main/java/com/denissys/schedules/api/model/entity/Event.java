package com.denissys.schedules.api.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = Event.EventBuilder.class)
@Builder
@Value
@Document
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String objectId;
	
	@NotBlank
	private String eventId;

	@NotBlank
	private String ownerId;

	@NotNull
	private Date startDatetime;
	
	@NotNull
	private Date endDatetime;
	
	@NotBlank
	private String type;
	
	private boolean active;
	
    public static final class EventBuilder {
    }
	
}