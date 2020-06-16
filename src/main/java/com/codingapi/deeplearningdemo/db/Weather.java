package com.codingapi.deeplearningdemo.db;

import com.codingapi.deeplearningdemo.utils.ICsv;
import lombok.Data;

@Data
public class Weather implements ICsv {

    private Long id;

    private String time;

    private  int maxT;

    private  int minT;

    private String remark;

    private String wind;

    private String api;

    private int year;

    private int mouth;

    private int day;

    private int precipitation;

    @Override
    public String[] columns() {
        return new String[]{"year","mouth","day","minT","maxT"};
    }
}
