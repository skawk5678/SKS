package com.sks.api.service;

import com.sks.api.model.NewsInfoVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:static/bigkinds.properties")
public class NewsService {

	@Value("${bigkinds.url}")
	private String bigkindsUrl;

	public List<NewsInfoVO> getNewsData() {
		List<NewsInfoVO> list = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();

		/*
		restTemplate를 이용하여 news정보를 가져온다.

		list =
		 */

		return list;
	}


}
