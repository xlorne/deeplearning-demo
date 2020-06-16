package com.codingapi.deeplearningdemo.utils;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ICsv {


    @SneakyThrows
    default Double[] toDouble(){
        List<String> columns = Arrays.asList(columns());
        List<Double> doubles = new ArrayList<>();
        PropertyDescriptor propertyDescriptors [] =  BeanUtils.getPropertyDescriptors(getClass());

        //sort
        List<PropertyDescriptor> list = new ArrayList<>();
        for(String name:columns){
            for(PropertyDescriptor propertyDescriptor:propertyDescriptors){
                if(propertyDescriptor.getName().equals(name)){
                    list.add(propertyDescriptor);
                }
            }
        }

        //csv builder
        for(int i=0;i<list.size();i++){
            PropertyDescriptor propertyDescriptor = list.get(i);
            if(columns.contains(propertyDescriptor.getName())) {
                Optional<Double> optional = Optional.ofNullable(propertyDescriptor.getReadMethod().invoke(this)).map(Object::toString).map(Double::parseDouble);
                Double value = optional.orElse(0.0d);
                doubles.add(value);
            }
        }
        return doubles.toArray(new Double[]{});
    }


    @SneakyThrows
    default String toCsv(){
        List<String> columns = Arrays.asList(columns());
        StringBuilder builder = new StringBuilder();
        PropertyDescriptor propertyDescriptors [] =  BeanUtils.getPropertyDescriptors(getClass());

        //sort
        List<PropertyDescriptor> list = new ArrayList<>();
        for(String name:columns){
            for(PropertyDescriptor propertyDescriptor:propertyDescriptors){
                if(propertyDescriptor.getName().equals(name)){
                    list.add(propertyDescriptor);
                }
            }
        }

        //csv builder
        for(int i=0;i<list.size();i++){
            PropertyDescriptor propertyDescriptor = list.get(i);
            if(columns.contains(propertyDescriptor.getName())) {
                Optional<String> optional = Optional.ofNullable(propertyDescriptor.getReadMethod().invoke(this)).map(Object::toString);
                String value = optional.orElse("");
                builder.append(value);
                if(list.size()-1!=i){
                    builder.append(",");
                }
            }
        }
        builder.append("\n");
        return builder.toString();
    }

    String[] columns();

}
