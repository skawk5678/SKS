package com.sks.api.model.openapi9;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CateKeyword {
	@JsonProperty("category_name")
	private String categoryName;
	@JsonProperty("category_code")
	private String categoryCode;
	@JsonProperty("named_entity_count")
	private int namedEntityCount;
	@JsonProperty("named_entity")
	private String namedEntity;
	@JsonProperty("entity_step")
	private String entityStep;
	@JsonProperty("named_entity_type")
	private String namedEntityType;
}
