package com.codingapi.deeplearningdemo.db;

import com.codingapi.deeplearningdemo.utils.CsvUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class WeatherMappeCvsTest {

    @Autowired
    private WeatherMapper weatherMapper;

    @SneakyThrows
    @Test
    void precipitation() {
        CsvUtils.write(weatherMapper.findAll());
    }


}