package com.sks.api.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
public class RegionInfoConfig {
	private List<String> regions;

	@PostConstruct
	public void init() {
		regions = new ArrayList<>();

		regions.add("강원");
		regions.add("경기");
		regions.add("경남");
		regions.add("경북");
		regions.add("광주");
		regions.add("대구");
		regions.add("대전");
		regions.add("부산");
		regions.add("울산");
		regions.add("전남");
		regions.add("전북");
		regions.add("제주");
		regions.add("충남");
		regions.add("충북");
	}
}
