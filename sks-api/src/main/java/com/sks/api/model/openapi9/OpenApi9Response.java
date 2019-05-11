package com.sks.api.model.openapi9;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenApi9Response {
	private int result;
	@JsonProperty("return_object")
	public ReturnObject returnObject;
}
