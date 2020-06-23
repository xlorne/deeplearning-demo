package com.codingapi.deeplearningdemo;

import lombok.SneakyThrows;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class DeeplearningDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeeplearningDemoApplication.class, args);
    }


    @SneakyThrows
    @Bean
    public MultiLayerNetwork multiLayerNetwork(){
        File locationToSave = new File("weather.zip");
        if(locationToSave.exists()) {
            //Where to save the network. Note: the file is in .zip format - can be opened externally
            return MultiLayerNetwork.load(locationToSave, true);
        }else{
            return null;
        }
    }
}
