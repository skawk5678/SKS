package com.sks.api.service;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

public class UserDictionary {
    Map<String, Integer> dictionary;

    public UserDictionary(){
        dictionary = new HashMap<String, Integer>();
        dictionary.put("전범",-30);
        dictionary.put("갑질",-20);
        dictionary.put("폭행",-10);
        dictionary.put("입막음",-5);
        dictionary.put("위반",-10);
        dictionary.put("불법",-20);
        dictionary.put("폭언",-10);
        dictionary.put("욕설",-10);
        dictionary.put("구속",-20);
        dictionary.put("성추행",-20);
        dictionary.put("처벌",-10);
        dictionary.put("악행",-10);
        dictionary.put("자살",-20);
        dictionary.put("횡령",-10);
        dictionary.put("마약",-20);
        dictionary.put("송치",-10);
        dictionary.put("범죄",-20);
        dictionary.put("위조",-10);
        dictionary.put("은폐",-15);
        dictionary.put("기부",10);
        dictionary.put("봉사",20);
        dictionary.put("선행",10);
        dictionary.put("후원",10);
        dictionary.put("공헌",20);
        dictionary.put("자선",20);
        dictionary.put("공익실현",30);
        dictionary.put("사회기여",30);
        dictionary.put("정직",30);
        dictionary.put("모범",30);
        dictionary.put("청렴",20);
        dictionary.put("부조리",-20);
        dictionary.put("징계",-20);
    }

    public int isGoodBad(String word){
 //       System.out.println(dictionary.containsKey(word));
        if(dictionary.containsKey(word)){
            return  dictionary.get(word);
        }
        else
            return 0;
    }
    public int isGoodBad_long(String ptr){
        int result=0;
        for(String key : dictionary.keySet()){
            if(ptr.contains(key)){
                result+= dictionary.get(key);
            }
        }
        return result;
    }
}
