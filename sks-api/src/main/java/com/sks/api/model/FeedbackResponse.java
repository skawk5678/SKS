package com.sks.api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedbackResponse {
	private List<FeedbackVO> categories;
	private String date_range_start;
	private String date_range_end;

	public FeedbackResponse(BusinessResponse businessResponse) {
		categories = new ArrayList<>();
		this.date_range_start = businessResponse.getDate_range_start();
		this.date_range_end = businessResponse.getDate_range_end();

		for(BusinessVO businessVO : businessResponse.getCategories()) {
			FeedbackVO feedbackVO = new FeedbackVO();
			feedbackVO.setBandCategoryIndex(businessVO.getBandCategoryIndex());
			feedbackVO.setBrandCategory(businessVO.getBrandCategory());
			feedbackVO.setFavoriteCount(businessVO.getUserClick());
			categories.add(feedbackVO);
		}
	}
}
