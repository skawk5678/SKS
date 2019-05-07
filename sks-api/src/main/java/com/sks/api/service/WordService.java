package com.sks.api.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.sks.api.model.NewsInfoVO;
import com.sks.api.model.WordVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.spring.web.json.Json;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
@PropertySource("classpath:static/bigkinds.properties")
public class WordService {

	@Value("${bigkinds.url}")
	private String bigkindsUrl;


	private String key="283bfcdb-f768-4286-b2e7-ee340cfae57c";



	public String getWordData() {
		String brand_name ="삼성";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR,-3);
		Date d =new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String until = sdf.format(d);
		String from = sdf.format(cal.getTime());

		String input = "{\n" +
				"    \"access_key\": \""+key+"\",\n" +
				"    \"argument\": {\n" +
				"        \"query\": \""+brand_name+"\",\n" +
				"        \"published_at\": {\n" +
				"            \"from\": \""+from+"\",\n" +
				"            \"until\": \""+until+"\"\n" +
				"        },\n" +
				"        \"provider\": [],\n" +
				"        \"category\": [],\n" +
				"        \"category_incident\": [],\n" +
				"        \"byline\": \"\",\n" +
				"        \"provider_subject\": []\n" +
				"    }\n" +
				"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> param = new HttpEntity<String>(input, headers);

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject("http://tools.kinds.or.kr:8888/word_cloud", param, String.class);

		List<WordVO> wordVOList = new ArrayList<WordVO>();



		return result;
	}


}