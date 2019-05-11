package com.sks.api.model;

import lombok.Data;

import java.util.List;

@Data
public class BusinessResponse {
	private List<BusinessVO> categories;
	private String date_range_start;
	private String date_range_end;
}
