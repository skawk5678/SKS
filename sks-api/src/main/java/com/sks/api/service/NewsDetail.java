package com.sks.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sks.api.config.UserDictionaryConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class NewsDetail {
    private String key="283bfcdb-f768-4286-b2e7-ee340cfae57c";

    public int NewsGoodBad(String id){
        String input = "{\n" +
                "    \"access_key\": \"283bfcdb-f768-4286-b2e7-ee340cfae57c\",\n" +
                "    \"argument\": {\n" +
                "        \"news_ids\": [\n" +
                "            \""+id+"\"\n" +
                "        ],\n" +
                "        \"fields\": [\n" +
                "            \"content\",\n" +
                "            \n" +
                "        ]\n" +
                "    }\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> param = new HttpEntity<String>(input, headers);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://tools.kinds.or.kr:8888/search/news", param, String.class);
        System.out.println(result);
        String content="";
        try {
            ObjectMapper om = new ObjectMapper();

            JsonNode node = om.readTree(result);
            JsonNode rtobj = node.get("return_object");
            JsonNode doc = rtobj.get("documents");
            JsonNode ccc = doc.findValue("content");
            content  = ccc.toString();



        }catch (IOException e){}

        UserDictionaryConfig dic = new UserDictionaryConfig();
        System.out.println(content);

        return dic.isGoodBad_long(content);
    }
}
