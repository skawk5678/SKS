package com.sks.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class  NewsServiceTest {

	@Test
	public void jacksonTest() throws IOException {
		String input = "{\n" +
				"\t\"access_key\": \"283bfcdb-f768-4286-b2e7-ee340cfae57c\", \n" +
				"     \"argument\": {\n" +
				"       \"query\": {\n" +
				"         \"title\":\"네이버\"\n" +
				"       },\n" +
				"\t\t\"published_at\": {\n" +
				"            \"from\": \"2015-01-01\",\n" +
				"            \"until\": \"2016-01-02\"\n" +
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
				"			 \"images\",\n" +
				"\t\t]\n" +
				"     }\n" +
				"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> param = new HttpEntity<String>(input, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://tools.kinds.or.kr:8888/search/news", param, String.class);


		log.info("result: {}",result);

		ObjectMapper objectMapper = new ObjectMapper();
//		FirstResponse firstResponse = objectMapper.readValue(result, FirstResponse.class);

	}

}