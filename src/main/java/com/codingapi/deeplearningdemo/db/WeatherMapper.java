package com.codingapi.deeplearningdemo.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeatherMapper {

    @Select("select * from t_weather")
    List<Weather> findAll();
}
