package com.sks.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Regions {
	@JsonProperty("region_index")
	private String regionIndex;
	private float latitude;
	private float longitude;
	private int news_count;
	private int rank;
}
