package com.sks.api.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근권한을 protected로 제한
@Data
@Entity
@Table(name="brand_list")
public class Brands {
	@Id
	@GeneratedValue
	private int id;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String image_url;

	@Column(length = 100)
	private String des;
}
