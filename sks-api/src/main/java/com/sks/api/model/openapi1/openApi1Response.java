package com.sks.api.model.openapi1;

import lombok.Data;

import java.util.List;

@Data
public class openApi1Response {
	private String result;
	private ReturnObject return_object;

	@Data
	private static class ReturnObject {
		private String total_hits;
		private List<Document> documents;
	}

	@Data
	private static class Document {
		private String news_id;
		private String title;
		private String content;
		private String provider_news_id;
		private String provider_link_page;
		private String published_at;
		private String hilight; // 검색어가 포함된 영역입니다.
		private String enveloped_at; // 언론사로부터 뉴스기사를 수집한 시간
		private String dateline; // 언론사로부터 전달받은 언론사의 뉴스 출고시간
		private String provider;
		private String images;
	}
}
