package com.codingapi.deeplearningdemo.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WeatherMapper {

    @Select("select * from t_weather")
    List<Weather> findAll();

    @Update("update t_weather set precipitation = #{precipitation} where id = #{id}")
    int update(Weather weather);

}
