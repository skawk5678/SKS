package com.sks.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sks.api.config.UserDictionaryConfig;
import com.sks.api.model.NewsInfoVO;
import com.sks.api.model.openapi1.OpenApi1Request;
import com.sks.api.model.openapi1.openApi1Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@PropertySource("classpath:static/bigkinds.properties")
public class NewsService {
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	private ObjectMapper objectMapper;
	private HttpEntity<String> param;
	private StringBuilder goodContent = new StringBuilder();
	private StringBuilder badContent = new StringBuilder();

	@Value("${bigkinds.url}")
	private String bigkindsUrl;

	@Value("${bigkinds.access_key}")
	private String accessKey;

	@Autowired
	UserDictionaryConfig userDictionaryConfig;

	@Bean
	private void constructValue() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		objectMapper = new ObjectMapper();
		restTemplate = new RestTemplate();
	}

	@PostConstruct
	public void init() {
		for(String item : userDictionaryConfig.getBadContent()) {
			if(item.equals(userDictionaryConfig.getBadContent().get(userDictionaryConfig.getBadContent().size()-1)))
				badContent.append(item);
			else
				badContent.append(item+" OR ");
		}
		for(String item : userDictionaryConfig.getGoodContent()) {
			if(item.equals(userDictionaryConfig.getGoodContent().get(userDictionaryConfig.getGoodContent().size()-1)))
				goodContent.append(item);
			else
				goodContent.append(item+" OR ");
		}

		for(String item : userDictionaryConfig.getGoodContent()) {
			badContent.append(" NOT "+item);
		}
		for(String item : userDictionaryConfig.getBadContent()) {
			goodContent.append(" NOT "+item);
		}
	}

	public openApi1Response getNewsData(NewsInfoVO newsInfoVO, String good_bad) {
		OpenApi1Request openApi1Request = new OpenApi1Request(newsInfoVO);

		String categoryIncident = null;
		String category = null;
		String content = null;

		switch (good_bad) {
			case "good":
				content = goodContent.toString();
				log.info("goodContent: {}",goodContent);
				category="정치, 사회>노동_복지, 사회>장애인";
				categoryIncident = "사회";
				break;
			case "bad":
				content = badContent.toString();
				log.info("badContent: {}",badContent);
				category = "정치, 사회>여성, 사회>사건_사고, 사회>미디어";
				categoryIncident = "범죄>성범죄, 범죄>기업범죄, 범죄>정치>뇌물수수";
				break;
			case "all":
				category = "정치, 사회>여성, 사회>사건_사고, 사회>미디어, 사회>노동_복지, 사회>장애인";
				categoryIncident = "범죄>성범죄, 범죄>기업범죄, 범죄>정치>뇌물수수, 사회";
				break;
			case "default":
				return null;
		}

		String response = searchNewsRequest(openApi1Request, content, categoryIncident, category);
		openApi1Response openApi1Response = null;
		log.info("result: {}",response);
		try {
			openApi1Response = objectMapper.readValue(response, openApi1Response.class);
		} catch (Exception e) {
			log.error("json parsing error {}", e);
		}

		return openApi1Response;
	}


	private String searchNewsRequest(OpenApi1Request openApi1Request, String content, String categoryIncident, String category) {
		String input = "{\n" +
				"\t\"access_key\": "+accessKey+", \n" +
				"     \"argument\": {\n" +
				"       \"query\": {\n" +
				"         \"title\":"+openApi1Request.getBrandName()+"\n" +
				"       },\n" +
				"\t\t\"published_at\": {\n" +
				"            \"from\": "+openApi1Request.getFrom()+",\n" +
				"            \"until\": "+openApi1Request.getUntil()+"\n" +
				"        },\n" +
				"       \"category_incident\": ["+categoryIncident+"],\n" +
				"       \"category\": ["+category+"],\n" +
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