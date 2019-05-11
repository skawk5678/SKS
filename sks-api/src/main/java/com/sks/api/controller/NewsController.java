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
@Slf4j
public class NewsController {

	@Autowired
	NewsService newsService;

	@PostMapping(value = "/analyze/access_news_list/{good_bad}")
	@ApiOperation("브랜드 뉴스 리스트-good or bad or all")
	public ResponseEntity<openApi1Response> allNews(@RequestBody NewsInfoVO newsInfoVO, @PathVariable("good_bad")String good_bad){
		openApi1Response openApi1Response = newsService.getNewsData(newsInfoVO, good_bad);

		return new ResponseEntity<>(openApi1Response, HttpStatus.OK);
	}
}
