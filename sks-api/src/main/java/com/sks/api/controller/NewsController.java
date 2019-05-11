package com.sks.api.controller;

import com.sks.api.model.NewsInfoVO;
import com.sks.api.model.openapi1.openApi1Response;
import com.sks.api.service.NewsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/news")
@Slf4j
public class NewsController {

	@Autowired
	NewsService newsService;

	@PostMapping
	@RequestMapping(value = "/analyze/access_news_neg_list", method = RequestMethod.POST)
	@ApiOperation("첫번째 화면에 띄어줄 뉴스정보들")
	public ResponseEntity<openApi1Response> allNews(@RequestBody NewsInfoVO newsInfoVO){
		openApi1Response openApi1Response = newsService.getNewsData(newsInfoVO);

		return new ResponseEntity<>(openApi1Response, HttpStatus.OK);
	}

}
