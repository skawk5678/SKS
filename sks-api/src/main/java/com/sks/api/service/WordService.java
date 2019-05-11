package com.sks.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sks.api.model.WordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@PropertySource("classpath:static/bigkinds.properties")
@Slf4j
public class WordService {

	@Value("${bigkinds.url}")
	private String bigkindsUrl;

	private final String key="283bfcdb-f768-4286-b2e7-ee340cfae57c";

	public WordVO[] getWordData(String brand_name) {
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
		try {
			ObjectMapper om = new ObjectMapper();

			JsonNode node = om.readTree(result);
			JsonNode rtobj = node.get("return_object");
			JsonNode nodes = rtobj.get("nodes");
			String nodes1 =nodes.toString();
			WordVO[] wordVO;

			wordVO = om.readValue(nodes1, WordVO[].class);
			UserDictionary dic = new UserDictionary();
			for(WordVO w : wordVO){
				int tmp=dic.isGoodBad(w.getName());
				if(tmp==0) {
					w.setGoodbad(0);
				}
				else{
					w.setGoodbad(tmp);
				}
			}

			return wordVO;
		}catch (IOException e){
			log.error("json parsing error {}",e);
			return null;
		}
	}


}