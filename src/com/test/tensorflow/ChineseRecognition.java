package com.test.tensorflow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;

import org.tensorflow.Operation;
import org.tensorflow.Output;
import org.tensorflow.Shape;

/**
 * 汉字识别
 * 
 * @author yangzhenggang
 * @since jdk1.7
 * @version 2017年4月17日 yangzhenggang
 */
public class ChineseRecognition {
    
    /** MAX_RESULTS */
    private static final int MAX_RESULTS = 1;
    
    /** THRESHOLD */
    private static final float THRESHOLD = 0.00f;
    
    /** allModelTfi */
    private static Map<String, TensorFlowInferenceInterface> allModelTfi = new HashMap<String, TensorFlowInferenceInterface>();
    
    /** allModelLabels */
    private static Map<String, List<String>> allModelLabels = new HashMap<String, List<String>>();
    
    /** Project_path */
    private static final String PROJECT_PATH = "D:/tf_mode/danzi";// "/home/yzgwork/images";
    
    static {
        // load modelcode
        List<String> allmodel = readAllLines(Paths.get(PROJECT_PATH + "/test", "modelcode"));
        for (int i = 0; i < allmodel.size(); i++) {
            String modelcode = allmodel.get(i);
            if (!"".equals(modelcode) && modelcode.length() > 0) {
                allModelTfi.put(modelcode, new TensorFlowInferenceInterface(PROJECT_PATH + "/test/" + modelcode
                    + "/output_graph.pb", "ChineseSet" + (i + 1)));
                allModelLabels.put(modelcode,
                    readAllLines(Paths.get(PROJECT_PATH + "/test/" + modelcode, "output_labels.txt")));
            }
        }
    }
    
    /**
     * 图片解析为汉字
     * 
     * @param imagePath imagePath
     * @param id id
     * @param modelCode modelCode
     * @return 参数
     */
    public static ArrayList<Recognition> imageToChar(String imagePath, String id, String modelCode) {
        TensorFlowInferenceInterface tfi = allModelTfi.get(modelCode);
        final Operation operation = tfi.graphOperation("final_out_result");
        Output output = operation.output(0);
        Shape shape = output.shape();
        final int numClasses = (int) shape.size(1);
        float[] imageFloatValues = getImagePixel(imagePath);
        // 输入图片
        tfi.feed("input_x/input", imageFloatValues, 1, 64, 64);
        tfi.feed("keep_prob", new float[] { 1f }); // 1不弃权
        tfi.run(new String[] { "final_out_result" }, false);
        float[] outPuts = new float[numClasses];
        tfi.fetch("final_out_result", outPuts);
        PriorityQueue<Recognition> pq = new PriorityQueue<Recognition>(3, new Comparator<Recognition>() {
            
            @Override
            public int compare(Recognition lhs, Recognition rhs) {
                return Float.compare(rhs.getConfidence(), lhs.getConfidence());
            }
        });
        // 加载实例的labels
        List<String> modelLabels = allModelLabels.get(modelCode);
        for (int i = 0; i < outPuts.length; ++i) {
            if (outPuts[i] > THRESHOLD) {
                pq.add(new Recognition(id, modelLabels.size() > i ? modelLabels.get(i) : "unknown", outPuts[i]));
            }
        }
        final ArrayList<Recognition> recognitions = new ArrayList<Recognition>();
        int recognitionsSize = Math.min(pq.size(), MAX_RESULTS);
        for (int i = 0; i < recognitionsSize; ++i) {
            recognitions.add(pq.poll());
        }
        return recognitions;
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
     * @param image 参数
     * @return 参数
     */
    public static float[] getImagePixel(String image) {
        float[] floatValues = new float[64 * 64];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            int minx = bi.getMinX();
            int miny = bi.getMinY();
            for (int y = miny; y < height; y++) {
                for (int x = minx; x < width; x++) {
                    int pixel = bi.getRGB(x, y);
                    int rgb0 = (pixel & 0xff0000) >> 16;
                    // int rgb1 = (pixel & 0xff00) >> 8;
                    // int rgb2 = (pixel & 0xff);
                    // System.out.println(rgb0 + "," + rgb1 + "," + rgb2);
                    // 数值归一化
                    floatValues[(y * 64) + x] = rgb0 * (1f / 255f) - 0.5f;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return floatValues;
    }
    
    /**
     * 方法说明
     * 
     * @param args 参数
     */
    public static void main(String[] args) {
//        String filepath = "D:/tf_mode/danzi/5a0000o/";
//        File file = new File(filepath);
//        if (file.isDirectory()) {
//            System.out.println("文件夹");
//            String[] filelist = file.list();
//            for (int i = 0; i < filelist.length; i++) {
//                File readfile = new File(filepath + "\\" + filelist[i]);
//                String ttt = readfile.getAbsolutePath();
//                System.out.println(ChineseRecognition.imageToChar(ttt, "1000_180",
//                    "ddddd"));
//            }
//            
//        }
        
        //
        System.out.println(ChineseRecognition.imageToChar("D:/tf_mode/danzi/5a0000o/n_1225_600.jpg", "1000_180",
            "ddddd"));
    }
}
