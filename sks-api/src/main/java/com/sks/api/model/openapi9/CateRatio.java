package com.sks.api.model.openapi9;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class CateRatio {
	@JsonProperty("category_percent")
	private int categoryPercent;
	@JsonProperty("category_name")
	private String categoryName;
	@JsonProperty("category_code")
	private String categoryCode;
	@JsonProperty("category_count")
	private int categoryCount;

}
