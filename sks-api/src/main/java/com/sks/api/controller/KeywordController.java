package com.sks.api.controller;

import com.sks.api.model.*;
import com.sks.api.model.openapi7.OpenApi7Response;
import com.sks.api.service.KeywordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analyze")
public class KeywordController {

	@Autowired
	KeywordService keywordService;

	@PostMapping(value = "/time_keyword")
	@ApiOperation("특정 시간대 키워드")
	public ResponseEntity<OpenApi7Response> requestTimeKeyword(@RequestBody SearchKewordVO searchKewordVO) {
		return new ResponseEntity<>(keywordService.getKeyword(searchKewordVO), HttpStatus.OK);
	}

	@PostMapping(value = "/issue_map")
	@ApiOperation("실시간 이슈 지역 지도")
	public ResponseEntity<List<Regions>> requestRegionKeyword(@RequestBody RegionKewordRequest regionKewordRequest) {
		List<Regions> regions = keywordService.getRegionKeyword(regionKewordRequest);

		return new ResponseEntity<>(regions, HttpStatus.OK);
	}

	@PostMapping(value = "/issue_category")
	@ApiOperation("사업 분야별 이슈 정도")
	public ResponseEntity<BusinessResponse> requestBusinessKeyword(@RequestBody BusinessRequest businessRequest) {
		BusinessResponse businessResponse = keywordService.getBusinessKeyword(businessRequest,0);

		return new ResponseEntity<>(businessResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/category_feedback")
	@ApiOperation("사업 분야별 뉴스 피드백")
	public ResponseEntity<FeedbackResponse> requestFeedbackKeyword(@RequestBody BusinessRequest businessRequest) {
		BusinessResponse businessResponse = keywordService.getBusinessKeyword(businessRequest,1);
		FeedbackResponse feedbackResponse = new FeedbackResponse(businessResponse);

		return new ResponseEntity<>(feedbackResponse, HttpStatus.OK);
	}
}
