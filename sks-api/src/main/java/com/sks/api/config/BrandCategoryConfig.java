package com.sks.api.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
public class BrandCategoryConfig {
	private List<String> brands;

	@PostConstruct
	public void init() {
		brands = new ArrayList<>();

		brands.add("카페");
		brands.add("맛집");
		brands.add("여가");
		brands.add("공부");
		brands.add("뷰티");
		brands.add("건강");
		brands.add("패션");
		brands.add("술집");
		brands.add("기타");
	}
}
