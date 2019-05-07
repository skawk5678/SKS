package com.sks.api.controller;

import com.sks.api.model.NewsInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class NewsControllerTest {

	@Mock
	private RestTemplate restTemplate;

	@Test
	public void restTemplateTest(){
		String url = "http://127.0.0.1:8080/v1/news/test";

	}

}