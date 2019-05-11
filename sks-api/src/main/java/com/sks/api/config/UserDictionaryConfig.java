package com.sks.api.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Configuration
public class UserDictionaryConfig {
    Map<String, Integer> dictionary;
    private List<String> goodContent = new ArrayList<>();
	private List<String> badContent = new ArrayList<>();

	@PostConstruct
	public void init() {
		goodContent.add("기부");
		goodContent.add("선행");
		goodContent.add("공헌");
		goodContent.add("봉사");
		goodContent.add("연탄봉사");
		goodContent.add("자선기부");
		goodContent.add("자선단체");
		goodContent.add("공익실현");
		goodContent.add("사회기여");
		goodContent.add("기부액");
		goodContent.add("정직");
		goodContent.add("모범");

		badContent.add("전범");
		badContent.add("갑질");
		badContent.add("폭행");
		badContent.add("입막");
		badContent.add("위반");
		badContent.add("은폐");
		badContent.add("범죄");
		badContent.add("폭행");
		badContent.add("송치");
		badContent.add("횡령");
		badContent.add("위조");
	}

    public UserDictionaryConfig(){
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
