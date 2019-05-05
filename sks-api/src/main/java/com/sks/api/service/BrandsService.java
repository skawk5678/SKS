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
		List<Brands> list = (List<Brands>) brandsRepository.findAll();

		for(Brands item : list){
			try {
				if (item.getName().equals(name)) {
					return new BrandInfoVO(item);
				}
			} catch (Exception e) {
				log.error("db error: {}", e);
			}
		}

		return null;
	}
}
