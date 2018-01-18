package com.denissys.schedules.api.config;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Value("${build.version:''}")
	private String version;

	@Bean
	public Docket docket() {
		final ResponseMessage status200 = getResponseMessage(200, "OK. The request has succeeded", new ModelRef("EventrResponse"));
		final ResponseMessage status201 = getResponseMessage(201, "Created. The request has been fulfilled and " 
				+ "resulted in a new resource being created", null);
		final ResponseMessage status204Deleted = getResponseMessage(204, "No Content. The resource was deleted "
				+ "successfully but the server does not need to return an entity-body", null);
		final ResponseMessage status204Updated = getResponseMessage(204, "No Content. The resource was updated "
				+ "successfully but the server does not need to return an entity-body", null);
		final ResponseMessage status400 = getResponseMessage(400, "Bad Request. One or more invalid parameters", null);
		final ResponseMessage status404 = getResponseMessage(404, "Not Found. The specified resource was not found", null);
		final ResponseMessage status422 = getResponseMessage(422, "Unprocessable Entity. The parameters are correct " 
				+ "but it was unable to process the request", null);
		final ResponseMessage status500 = getResponseMessage(500, "Internal ErrorResponse. The server encountered an "
				+ "unexpected condition which prevented it from fulfilling the request.", new ModelRef("ErrorResponse"));

		
		// common status code for GET, POST, PUT and DELETE methods
		final List<ResponseMessage> baseStatusCode = newArrayList(status400, status422, status500);

		final List<ResponseMessage> getStatusCode = new ArrayList<>(baseStatusCode);
		getStatusCode.add(status200);
		getStatusCode.add(status404);

		final List<ResponseMessage> postStatusCode = new ArrayList<>(baseStatusCode);
		postStatusCode.add(status201);

		final List<ResponseMessage> putStatusCode = new ArrayList<>(baseStatusCode);
		putStatusCode.add(status204Updated);
		putStatusCode.add(status404);

		final List<ResponseMessage> deleteStatusCode = new ArrayList<>(baseStatusCode);
		deleteStatusCode.add(status204Deleted);
		deleteStatusCode.add(status404);

		return new Docket(DocumentationType.SWAGGER_2).groupName("EVA-ORDER").apiInfo(apiInfo()).select()
				.paths(regex("/api/v1/*.*")).build().useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, getStatusCode)
				.globalResponseMessage(RequestMethod.PUT, putStatusCode)
				.globalResponseMessage(RequestMethod.POST, postStatusCode)
				.globalResponseMessage(RequestMethod.DELETE, deleteStatusCode);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Schedules Rest API")
				.description("Scheudle events")
				.version(version)
				.build();
	}

	private ResponseMessage getResponseMessage(final int code, final String message,
			final ModelReference responseModel) {
		return new ResponseMessageBuilder().code(code).message(message).responseModel(responseModel).build();
	}

}
