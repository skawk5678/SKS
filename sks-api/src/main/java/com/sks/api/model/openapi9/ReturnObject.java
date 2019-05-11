package com.sks.api.model.openapi9;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReturnObject {
	private String date;
	@JsonProperty("cate_ratio")
	private List<CateRatio> cateRatio;
	@JsonProperty("cate_keyword")
	private List<CateKeyword> cateKeyword;
}
