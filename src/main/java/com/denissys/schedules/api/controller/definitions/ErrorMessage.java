package com.denissys.schedules.api.controller.definitions;

import java.io.Serializable;

public interface ErrorMessage extends Serializable {

	String getMessage();

	void setMessage(String message);

}
