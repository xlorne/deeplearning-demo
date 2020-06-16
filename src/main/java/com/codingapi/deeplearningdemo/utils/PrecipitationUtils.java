package com.codingapi.deeplearningdemo.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrecipitationUtils {

    private Map<String,Double> precipitation = new HashMap<>();


    @SneakyThrows
    public PrecipitationUtils(){
        List<String> lines =  FileUtils.readLines(new File("precipitation.csv"), Charset.forName("utf8"));
        for(String line:lines){
            String[] vals = line.split(",");
            precipitation.put(vals[1].trim(),Double.parseDouble(vals[2].trim()));
        }
        System.out.println(precipitation);
    }


    public double getVal(String remark){
        return precipitation.get(remark);
    }

}
