package com.sks.api.model.openapi1;

import lombok.Data;

import java.util.List;

@Data
public class ReturnObject {
	String total_hits;
	List<Document> documents;

}
