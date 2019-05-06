package com.sks.api.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandsRepository extends CrudRepository<Brands, Long> {
	List<Brands> findByName(String name);
}