package com.sks.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsInfoVO {
	private String news_id;
	private String title;
	private String provider_link_page;
	private String published_at;
}
