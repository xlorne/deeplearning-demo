package com.codingapi.deeplearningdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired(required = false)
    private MultiLayerNetwork multiLayerNetwork;

    @PostMapping("/predict")
    public WeatherInf predict(@RequestBody Date date){
        INDArray input = Nd4j.create(new double[] {(date.getYear()-2000d)/100d,date.getMouth()/10d,date.getDay()/30d}, 1, 3);
        INDArray out = multiLayerNetwork.output(input, false);
        out = out.mul(10);
        double[] vals = out.toDoubleVector();
        return new WeatherInf(vals[0],vals[1]);
    }


    @Data
    static class Date{
        int year;
        int mouth;
        int day;
    }

    @Data
    @AllArgsConstructor
    static class WeatherInf{
        double minT;
        double maxT;
    }
}
