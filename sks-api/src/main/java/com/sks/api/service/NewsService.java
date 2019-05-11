package com.sks.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sks.api.model.NewsInfoVO;
import com.sks.api.model.openapi1.openApi1Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@PropertySource("classpath:static/bigkinds.properties")
public class NewsService {
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	private ObjectMapper objectMapper;
	private HttpEntity<String> param;

	@Value("${bigkinds.url}")
	private String bigkindsUrl;

	@Value("${bigkinds.access_key}")
	private String accessKey;

	@Bean
	private void constructValue() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		objectMapper = new ObjectMapper();
		restTemplate = new RestTemplate();
	}

	public openApi1Response getNewsData(NewsInfoVO newsInfoVO) {
		String response = searchNewsRequest(newsInfoVO);
		openApi1Response openApi1Response = null;
		log.info("result: {}",response);
		try {
			openApi1Response = objectMapper.readValue(response, openApi1Response.class);
		} catch (Exception e) {
			log.error("json parsing error {}", e);
		}


		return openApi1Response;
	}




	private String searchNewsRequest(NewsInfoVO newsInfoVO) {
		String input = "{\n" +
				"\t\"access_key\": "+accessKey+", \n" +
				"     \"argument\": {\n" +
				"       \"query\": {\n" +
				"         \"title\":"+newsInfoVO.getBrand_name()+"\n" +
				"       },\n" +
				"\t\t\"published_at\": {\n" +
				"            \"from\": "+newsInfoVO.getFrom()+",\n" +
				"            \"until\": "+newsInfoVO.getUntil()+"\n" +
				"        },\n" +
				"       \"category_incident\": [ \"범죄\",\n" +
				"\t\t\"교통사고\", ],\n" +
				"        \"sort\": {\"date\": \"desc\"},\n" +
				"        \"hilight\": 200,\n" +
				"        \"return_from\": 0,\n" +
				"        \"return_size\": 50,\n" +
				"        \"fields\": [\n" +
				"            \"content\",\n" +
				"            \"provider_news_id\",\n" +
				"			 \"provider_link_page\",\n" +
				"			 \"published_at\",\n" +
				"\t\t]\n" +
				"     }\n" +
				"}";

		param = new HttpEntity<String>(input, headers);

		return restTemplate.postForObject(bigkindsUrl + "/search/news", param, String.class);
	}
}