
package com.test.tensorflow;

/**
 *
 * @author yangzhenggang
 * @since jdk1.7
 * @version 2017年4月17日 yangzhenggang
 */
public class Recognition {
    
    /**
     * A unique identifier for what has been recognized. Specific to the class,
     * not the instance of the object.
     */
    private String id;
    
    /**
     * Display name for the recognition.
     */
    private final String title;
    
    /**
     * A sortable score for how good the recognition is relative to others.
     * Higher should be better.
     */
    private final Float confidence;
    
    /** percent */
    private final String percent;
    
    /**
     * 方法说明
     * 
     * @return 参数
     */
    public String getPercent() {
        return percent;
    }
    
    /**
     * Optional location within the source image for the location of the
     * recognized object.
     * 
     * @param id xx
     * @param title xx
     * @param confidence xx
     */
    
    public Recognition(final String id, final String title, final Float confidence) {
        this.id = id;
        this.title = title;
        this.confidence = confidence;
        this.percent = String.format("(%.1f%%) ", confidence * 100.0f);
    }
    
    
    /**
     * 方法说明
     * @param id 参数
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法说明
     * 
     * @return 参数
     */
    public String getId() {
        return id;
    }
    
    /**
     * 方法说明
     * 
     * @return 参数
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * 方法说明
     * 
     * @return 参数
     */
    public Float getConfidence() {
        return confidence;
    }
    
    @Override
    public String toString() {
        String resultString = "";
        if (id != null) {
            resultString += "[" + id + "] ";
        }
        
        if (title != null) {
            resultString += title + " ";
        }
        
        if (confidence != null) {
            resultString += String.format("(%.1f%%) ", confidence * 100.0f);
        }
        
        return resultString.trim();
    }
}
