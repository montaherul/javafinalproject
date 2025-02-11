package org.course.coursewebapplication.model;

public class CourseMaterial {
    private int id;
    private int courseId;
    private String materialType;
    private String content;
    private String filePath;
    private String date;

    // Constructor
    public CourseMaterial(int courseId, String materialType, String content, String date) {

        this.courseId = courseId;
        this.materialType = materialType;
        this.content = content;
        this.date = date;
    }
   public CourseMaterial() {

   }
   public int getId() {
        return id;
   }
   public void setId(int id) {this.id=id;}

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getContent() {
        return content;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
