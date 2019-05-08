package com.sks.api.service;

import com.sks.api.domain.Brands;
import com.sks.api.domain.BrandsRepository;
import com.sks.api.model.BrandInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Slf4j
public class BrandsService {

	@Autowired
	BrandsRepository brandsRepository;

	public BrandInfoVO getBrandInfoService(String name) {
		Brands brands = brandsRepository.findByName(name);

		if(ObjectUtils.isEmpty(brands)) {
			return null;
		} else {
			return new BrandInfoVO(brands);
		}
	}
}
