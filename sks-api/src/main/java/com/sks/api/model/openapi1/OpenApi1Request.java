package com.sks.api.model.openapi1;

import com.sks.api.model.NewsInfoVO;
import lombok.Data;

@Data
public class OpenApi1Request {
	private String brandName;
	private String from;
	private String until;
	private String good;
	private String bad;

	public OpenApi1Request(NewsInfoVO newsInfoVO) {
		this.brandName = newsInfoVO.getBrand_name();
		this.from = newsInfoVO.getFrom();
		this.until = newsInfoVO.getUntil();
	}
}
