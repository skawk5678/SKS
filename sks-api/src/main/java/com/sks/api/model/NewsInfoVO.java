package com.sks.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsInfoVO {
	private String access_key;
	private String brand_name;
	private String brand_key;
	private String from;
	private String until;
	private String top_count;
}
