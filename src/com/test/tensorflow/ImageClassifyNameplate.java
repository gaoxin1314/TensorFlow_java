
package com.test.tensorflow;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.Shape;


/**
 *
 * @author yangzhenggang
 * @since jdk1.7
 * @version 2017年4月17日 yangzhenggang
 */
public class ImageClassifyNameplate {
    
    /** MAX_RESULTS */
    // private static final int MAX_RESULTS = 3;
    
    /** JPEG_DATA_TENSOR_NAME */
    private static String JPEG_DATA_TENSOR_NAME = "DecodeJpeg/contents:0";
    
    /** tfi */
//    private static TensorFlowInferenceInterface tfi = new TensorFlowInferenceInterface(
//        TensorflowConstant.FILE_PATH_ROOT + "/output_graph.pb", " ImageType");
    
    /**
     * 方法说明
     * 
     * @param imagePath imagePath
     * @return 参数
     */
//    public static ArrayList<Recognition> ImageClassify(String imagePath) {
//        byte[] byteImg = readAllBytesOrExit(Paths.get(imagePath, "image"));
//        System.out.print("current image is " + imagePath);
//        tfi.feed(JPEG_DATA_TENSOR_NAME, byteImg);// 图片输入
//        
//        final Operation operation = tfi.graphOperation("final_result");
//        Output output = operation.output(0);
//        Shape shape = output.shape();
//        final int numClasses = (int) shape.size(1);
//        tfi.run(new String[] { "final_result" }, false);
//        float[] outPuts = new float[numClasses];
//        tfi.fetch("final_result", outPuts);
//        PriorityQueue<Recognition> pq = new PriorityQueue<Recognition>(3, new Comparator<Recognition>() {
//            
//            @Override
//            public int compare(Recognition lhs, Recognition rhs) {
//                return Float.compare(rhs.getConfidence(), lhs.getConfidence());
//            }
//        });
//        // 加载实例的labels
//        List<String> modelLabels = readAllLines(Paths.get(TensorflowConstant.FILE_PATH_ROOT, "output_labels.txt"));
//        for (int i = 0; i < outPuts.length; ++i) {
//            pq.add(new Recognition("" + i, modelLabels.size() > i ? modelLabels.get(i) : "unknown", outPuts[i]));
//        }
//        final ArrayList<Recognition> recognitions = new ArrayList<Recognition>();
//        // int recognitionsSize = Math.min(pq.size(), MAX_RESULTS);
//        for (int i = 0; i < modelLabels.size(); ++i) {
//            recognitions.add(pq.poll());
//        }
//        return recognitions;
//    }
    
    /**
     * 方法说明
     * 
     * @param imagePath imagePath
     * @param caseCode caseCode
     * @return 参数
     */
//    public static ArrayList<Recognition> modelClassify(String imagePath, String caseCode) {
//        // 具体实例的模型
//        TensorFlowInferenceInterface tempTfi = new TensorFlowInferenceInterface(TensorflowConstant.FILE_PATH_ROOT
//            + "/" + caseCode + "/output_graph.pb", " ImageType");
//        // 加载实例的labels
//        List<String> modelLabels = readAllLines(Paths.get(TensorflowConstant.FILE_PATH_ROOT + "/" + caseCode,
//            "output_labels.txt"));
//        byte[] byteImg = readAllBytesOrExit(Paths.get(imagePath, "image"));
//        tempTfi.feed(JPEG_DATA_TENSOR_NAME, byteImg);// 图片输入
//        
//        final Operation operation = tempTfi.graphOperation("final_result");
//        Output output = operation.output(0);
//        Shape shape = output.shape();
//        final int numClasses = (int) shape.size(1);
//        tempTfi.run(new String[] { "final_result" }, false);
//        float[] outPuts = new float[numClasses];
//        tempTfi.fetch("final_result", outPuts);
//        PriorityQueue<Recognition> pq = new PriorityQueue<Recognition>(3, new Comparator<Recognition>() {
//            
//            @Override
//            public int compare(Recognition lhs, Recognition rhs) {
//                return Float.compare(rhs.getConfidence(), lhs.getConfidence());
//            }
//        });
//        for (int i = 0; i < outPuts.length; ++i) {
//            pq.add(new Recognition("" + i, modelLabels.size() > i ? modelLabels.get(i) : "unknown", outPuts[i]));
//        }
//        final ArrayList<Recognition> recognitions = new ArrayList<Recognition>();
//        for (int i = 0; i < modelLabels.size(); ++i) {
//            recognitions.add(pq.poll());
//        }
//        return recognitions;
//    }
    
    /**
     * 方法说明
     * 
     * @param path 参数
     * @return 参数
     */
    private static byte[] readAllBytesOrExit(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * 方法说明
     * 
     * @param path 参数
     * @return 参数
     */
    private static List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path, Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * 方法说明
     * 
     * @param args 参数
     */
    // public static void main(String[] args) {
    // //
    // ImageClassifyNameplate.ImageClassify("D:/tf_test");
    // }
}
