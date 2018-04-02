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
import java.util.List;
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
public class ChineseRecognition2 {
    
    /**
     * 方法说明
     * 
     * @param image 参数
     * @return 参数
     */
    public static float[] getImagePixel(String image) {
        float[] floatValues = new float[28 * 28];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
            int width = bi.getWidth();
            int height = bi.getHeight();
            int minx = bi.getMinX();
            int miny = bi.getMinY();
            for (int i = minx; i < width; i++) {
                for (int j = miny; j < height; j++) {
                	System.out.println("i="+i+"j="+j);
                    int value = (bi.getRGB(j, i) & 0xff0000) >> 16;
                     System.out.println("结束");
                    // 数值归一化6
                    
                    floatValues[(i * 28) + j] = value * (1f / 255f) - 0.5f;
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
//        float[] imageFloatValues = getImagePixel(imagePath + "/image");
//        // 输入图片
//        tempTfi.feed("input_x/input", imageFloatValues, 64, 64);
//        tempTfi.feed("keep_prob", new float[] { 1f }); // 1不弃权
//        final Operation operation = tempTfi.graphOperation("final_out_result");
//        Output output = operation.output(0);
//        Shape shape = output.shape();
//        final int numClasses = (int) shape.size(1);
//        tempTfi.run(new String[] { "final_out_result" }, false);
//        float[] outPuts = new float[numClasses];
//        tempTfi.fetch("final_out_result", outPuts);
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
    // // xxxx
    // ArrayList<Recognition> rr = ImageToChar(
    // "D:/work/ai/data/wenzihb/b_jie/d0a0a363-438b-4c22-a905-c1f8cebcf40c.jpg", "123");
    // System.out.print(rr);
    // }
}
