package com.sks.api.model.openapi1;

import lombok.Data;

@Data
public class Document {
	private String news_id;
	private String title;
	private String content;
	private String provider_news_id;
	private String provider_link_page;
	private String published_at;
	private String hilight;
	private String enveloped_at;
	private String dateline;
	private String provider;
}
