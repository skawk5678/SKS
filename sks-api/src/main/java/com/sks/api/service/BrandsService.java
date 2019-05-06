package com.sks.api.service;

import com.sks.api.domain.Brands;
import com.sks.api.domain.BrandsRepository;
import com.sks.api.model.BrandInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrandsService {

	@Autowired
	BrandsRepository brandsRepository;

	public BrandInfoVO getBrandInfoService(String name) {
		List<Brands> list = brandsRepository.findByName(name);

		if(list.size()==0) {
			return null;
		} else {
			return new BrandInfoVO(list.get(0));
		}
	}
}
