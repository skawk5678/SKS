package com.sks.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sks.api.config.BrandCategoryConfig;
import com.sks.api.config.RegionInfoConfig;
import com.sks.api.model.*;
import com.sks.api.model.openapi7.OpenApi7Response;
import com.sks.api.model.openapi7.Queries;
import com.sks.api.model.openapi9.CateKeyword;
import com.sks.api.model.openapi9.OpenApi9Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@PropertySource("classpath:static/bigkinds.properties")
public class KeywordService {
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	private ObjectMapper objectMapper;
	private HttpEntity<String> param;
	private Random random;
	private SimpleDateFormat dateFormat;
	private Calendar calendar;



	@Value("${bigkinds.url}")
	private String bigkindsUrl;

	@Value("${bigkinds.access_key}")
	private String accessKey;

	@Autowired
	RegionInfoConfig regionInfoConfig;

	@Autowired
	BrandCategoryConfig brandCategoryConfig;

	@PostConstruct
	public void init() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		objectMapper = new ObjectMapper();
		restTemplate = new RestTemplate();
		random = new Random();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		calendar = Calendar.getInstance();
	}

	public BusinessResponse getBusinessKeyword(BusinessRequest businessRequest, int select) {
		BusinessResponse businessResponse = new BusinessResponse();
		List<BusinessVO> list = new ArrayList<>();

		try {
			calendar.setTime(dateFormat.parse(businessRequest.getDate_time()));
			calendar.add(Calendar.DATE, 7);
		} catch (ParseException e) {
			log.error("dateParse error: {}",e);
			return null;
		}

		businessResponse.setDate_range_start(businessRequest.getDate_time());
		businessResponse.setDate_range_end(dateFormat.format(calendar.getTime()));

		SearchKewordVO searchKewordVO = new SearchKewordVO();
		searchKewordVO.setAccessKey("");
		searchKewordVO.setDate_time(businessRequest.getDate_time());
		searchKewordVO.setDate_type("weekly");
		searchKewordVO.setTopCount(businessRequest.getTop_count());
		OpenApi7Response openApi7Response = getKeyword(searchKewordVO);

		int index=1;
		for(Queries queries : openApi7Response.getReturn_object().getQueries()) {
			BusinessVO businessVO = new BusinessVO();
			switch (select) {
				case 0:
					businessVO.setUserClick(queries.getCount());
					break;
				case 1:
					businessVO.setUserClick(queries.getCount() + random.nextInt(500));
					break;
			}

			businessVO.setBandCategoryIndex("cat_00"+index);
			businessVO.setBrandCategory(brandCategoryConfig.getBrands().get(index-1));
			list.add(businessVO);

			index++;
		}
		businessResponse.setCategories(list);

		return businessResponse;
	}

	public OpenApi7Response getKeyword(SearchKewordVO searchKewordVO) {
		String until = null;
		OpenApi7Response openApi7Response = null;

		calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(searchKewordVO.getDate_time()));
		} catch (ParseException e) {
			log.error("dateParseException: {}", e);
		}
		switch (searchKewordVO.getDate_type()) {
			case "weekly":
				calendar.add(Calendar.DATE, 6);
				break;
			case "daily":
				calendar.add(Calendar.DATE, 0);
				break;
			default:
				calendar.add(Calendar.DATE, 6);
				break;
		}
		until = dateFormat.format(calendar.getTime());


		String input = "{\n" +
				"\t\"access_key\": \"283bfcdb-f768-4286-b2e7-ee340cfae57c\",\n" +
				"    \"argument\": {\n" +
				"        \"from\": "+searchKewordVO.getDate_time()+",\n" +
				"        \"until\": "+until+",\n" +
				"        \"offset\": "+searchKewordVO.getTopCount()+"\n" +
				"    }\n" +
				"}";

		String response = keywordRequest(input, "/query_rank");

		try {
			openApi7Response = objectMapper.readValue(response, OpenApi7Response.class);
		} catch (Exception e) {
			log.error("json parsing error {}", e);
		}

		return openApi7Response;
	}

	public List<Regions> getRegionKeyword(RegionKewordRequest regionKewordRequest) {
		OpenApi9Response openApi9Response = new OpenApi9Response();
		List<Regions> list = new ArrayList<>();

		String input ="{\n" +
				"\t\"access_key\": \"d3a10ae3-482c-41d0-9c31-146fe526e04d\", \n" +
				"\t\"argument\": {\n" +
				"      \"category\" :\"지역\"\t\n" +
				"    }\n" +
				"}";

		String response = keywordRequest(input, "/today_category_keyword");

		try {
			openApi9Response = objectMapper.readValue(response, OpenApi9Response.class);
		} catch (Exception e) {
			log.error("json parsing error {}", e);
		}

		int cnt=1;
		for(CateKeyword cateKeyword  : openApi9Response.getReturnObject().getCateKeyword()) {
					Regions regions = new Regions();
					regions.setNews_count(cateKeyword.getNamedEntityCount());
					regions.setRank(cnt);
					regions.setRegionIndex("0000"+cnt+"00");
					regions.setLatitude(random.nextFloat()*60+20);
					regions.setLongitude(random.nextFloat()*60+20);
					list.add(regions);
					cnt++;
					if(cnt==regionKewordRequest.getTop_count()+1)
						break;
		}
		return list;
	}
	private String keywordRequest(String input, String url) {
		param = new HttpEntity<String>(input, headers);

		return restTemplate.postForObject(bigkindsUrl + url, param, String.class);
	}
}
