package com.codingapi.deeplearningdemo.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.deeplearning4j.arbiter.optimize.api.data.DataSource;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CsvUtils {

    @SneakyThrows
    public static void write(List<? extends ICsv> list){
        File file = new File("data.csv");
        for(ICsv csv:list){
            FileUtils.writeStringToFile(file,csv.toCsv(), Charset.forName("utf8"),true);
        }
    }


    @SuppressWarnings("SameParameterValue")
    public static DataSetIterator getTrainingData(List<? extends ICsv> list,int batchSize, Random rng){
        int nSamples = list.size();
        double [] ouput1 = new double[nSamples];
        double [] ouput2 = new double[nSamples];
        double [] ouput3 = new double[nSamples];

        double [] input1 = new double[nSamples];
        double [] input2 = new double[nSamples];
        double [] input3 = new double[nSamples];
        for (int i= 0; i< nSamples; i++) {
            Double [] vals = list.get(i).toDouble();
            input1[i] = vals[0];
            input2[i] = vals[1];
            input3[i] = vals[2];

            ouput1[i] = vals[3];
            ouput2[i] = vals[4];
//            ouput3[i] = vals[5];
        }
        INDArray inputNDArray1 = Nd4j.create(input1, nSamples,1);
        INDArray inputNDArray2 = Nd4j.create(input2, nSamples,1);
        INDArray inputNDArray3 = Nd4j.create(input3, nSamples,1);

        INDArray outPutNDArray1 = Nd4j.create(ouput1, nSamples,1);
        INDArray outPutNDArray2 = Nd4j.create(ouput2, nSamples,1);
//        INDArray outPutNDArray3 = Nd4j.create(ouput3, nSamples,1);

        INDArray inputNDArray = Nd4j.hstack(inputNDArray1,inputNDArray2,inputNDArray3);
        INDArray outPutNDArray = Nd4j.hstack(outPutNDArray1,outPutNDArray2);
        DataSet dataSet = new DataSet(inputNDArray, outPutNDArray);
        List<DataSet> listDs = dataSet.asList();
        Collections.shuffle(listDs,rng);
        return new ListDataSetIterator<>(listDs,batchSize);

    }
}
