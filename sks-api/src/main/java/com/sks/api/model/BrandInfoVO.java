package com.sks.api.model;

import com.sks.api.domain.Brands;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class BrandInfoVO {
	private int id;
	private String name;
	private String image_url;
	private String des;

	public BrandInfoVO(Brands brands){
/*
		if(!StringUtils.isEmpty(brands.getId()))
			this.id = brands.getId();
		if(!StringUtils.isEmpty(brands.getName()))
			this.name = brands.getName();
		if(!StringUtils.isEmpty(brands.getImage_url()))
			this.image_url= brands.getImage_url();
		if(!StringUtils.isEmpty(brands.getDes()))
			this.des = brands.getDes();
*/
	}
}
