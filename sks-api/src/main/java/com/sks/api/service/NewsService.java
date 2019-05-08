package com.sks.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sks.api.model.NewsInfoVO;
import com.sks.api.model.SearchNewsRepVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@PropertySource("classpath:static/bigkinds.properties")
public class NewsService {

	@Value("${bigkinds.url}")
	private String bigkindsUrl;

	@Value("${bigkinds.access_key}")
	private String accessKey;

	public String getNewsData(NewsInfoVO newsInfoVO) {
		String input = "{\n" +
				"\t\"access_key\": "+accessKey+", \n" +
				"     \"argument\": {\n" +
				"       \"query\": {\n" +
				"         \"title\":"+newsInfoVO.getTitle()+"\n" +
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
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> param = new HttpEntity<String>(input, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(bigkindsUrl + "/search/news", param, String.class);

		log.info("result: {}",result);



		return "list";
	}
}