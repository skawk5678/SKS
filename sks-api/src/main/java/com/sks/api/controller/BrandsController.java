package com.sks.api.controller;

import com.sks.api.model.BrandInfoVO;
import com.sks.api.service.BrandsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/brands")
public class BrandsController {

	@Autowired
	BrandsService brandsService;

	@RequestMapping(value="/info", method = RequestMethod.GET)
	@ApiOperation("브랜드 정보")
	public ResponseEntity<BrandInfoVO> getBrandInfo(@RequestParam String name) {
		BrandInfoVO brandInfoVO = brandsService.getBrandInfoService(name);

		if(ObjectUtils.isEmpty(brandInfoVO))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		return new ResponseEntity<>(brandInfoVO,HttpStatus.OK);
	}
}
