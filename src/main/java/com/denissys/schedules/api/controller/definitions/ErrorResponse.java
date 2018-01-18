package com.denissys.schedules.api.controller.definitions;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@ApiModel
public class ErrorResponse implements MessageReponse {

	private static final long serialVersionUID = 1L;

	@Getter
	private boolean error = true;
	
	@Getter
	@Setter
	private List<ErrorMessage> errors = new ArrayList<>();
	
}
