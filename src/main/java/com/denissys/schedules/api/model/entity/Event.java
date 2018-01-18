package com.denissys.schedules.api.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Getter;

@JsonDeserialize(builder = Event.EventBuilder.class)
@Builder
@Document
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@NotBlank
	@Getter
	private String eventId;

	@NotBlank
	@Getter
	private String ownerId;

	@NotNull
	@Getter
	private Date startDatetime;
	
	@NotNull
	@Getter
	private Date endDatetime;
	
	@NotBlank
	@Getter
	private String type;
	
	@NotNull
	@Getter
	private Boolean active;
	
	public Event inactive() {
		this.active = false;
		return this;
	}
	
    public static final class EventBuilder {
    }
	
}