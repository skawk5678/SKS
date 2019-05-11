package com.sks.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FeedbackVO {
	@JsonProperty("brand_category_index")
	private String bandCategoryIndex;
	@JsonProperty("brand_category")
	private String brandCategory;
	@JsonProperty("favorite_count")
	private int favoriteCount;
}
