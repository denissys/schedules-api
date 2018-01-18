package com.denissys.schedules.api.controller.definitions;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public interface MessageReponse extends Serializable {

	default public String getUuid() {
		return UUID.randomUUID().toString();
	}

	default public Calendar getGeneratedTS() {
		return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	}

}
