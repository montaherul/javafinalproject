package org.course.coursewebapplication.model;


public class Course {
    private int id;
    private String title;
    private String description;
    private String instructor;
    private double price;
    private String duration;
    private String image;
    private String instructorimage;

    // Constructors
    public Course() {}

    public Course(int id, String title, String description, String instructor, double price, String duration, String image,String instractorimage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.duration = duration;
        this.image = image;
        this.instructorimage = instractorimage;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructorimage() { return instructorimage; }

    public void setInstructorimage(String instructorimage) { this.instructorimage = instructorimage; }
}
