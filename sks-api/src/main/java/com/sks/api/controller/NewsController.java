package com.sks.api.controller;

import com.sks.api.model.BrandInfoVO;
import com.sks.api.model.NewsInfoVO;
import com.sks.api.service.NewsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/news")
@Slf4j
public class NewsController {

	@Autowired
	NewsService newsService;



	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ApiOperation("첫번째 화면에 띄어줄 뉴스정보들")
	public ResponseEntity<List<NewsInfoVO>> allNews(){
		List<NewsInfoVO> newsInfoVOList = newsService.getNewsData();

		return new ResponseEntity<>(newsInfoVOList, HttpStatus.OK);
	}

}
