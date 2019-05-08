package com.sks.api.controller;

import com.sks.api.model.NewsInfoVO;
import com.sks.api.model.WordVO;
import com.sks.api.service.NewsService;
import com.sks.api.service.WordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/wordcloud")
public class WordcloudContoller {
    @Autowired
    WordService wordService;


/*
    @RequestMapping(value = "/wordcloud", method = RequestMethod.GET)
    @ApiOperation("워드 클라우드")
    public ResponseEntity<List<WordVO>> wordcloud(){
        return
    }*/
}
