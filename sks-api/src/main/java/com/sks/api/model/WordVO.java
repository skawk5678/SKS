package com.sks.api.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WordVO {
    private int id ;
    private String name;
    private int level;
    private float weight;
    private int goodbad;


    public String getName(){
        return name;
    }
    public float getWeight(){
        return weight;
    }

}
