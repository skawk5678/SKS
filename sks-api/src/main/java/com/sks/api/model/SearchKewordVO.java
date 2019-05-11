package com.sks.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchKewordVO {
	@JsonProperty("access_key")
	private String accessKey;
	private String date_time;
	private String date_type;
	@JsonProperty("top_count")
	private String topCount;
}
