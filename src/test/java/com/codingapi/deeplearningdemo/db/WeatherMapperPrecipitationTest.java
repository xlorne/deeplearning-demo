package com.codingapi.deeplearningdemo.db;

import com.codingapi.deeplearningdemo.utils.PrecipitationUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class WeatherMapperPrecipitationTest {

    @Autowired
    private WeatherMapper weatherMapper;

    @SneakyThrows
    @Test
    void precipitation() {
        PrecipitationUtils precipitationUtils = new PrecipitationUtils();
        List<Weather> list = weatherMapper.findAll();
        for (Weather weather:list){
            weather.setPrecipitation(precipitationUtils.getVal(weather.getRemark()));
            weatherMapper.update(weather);
        }
    }


}