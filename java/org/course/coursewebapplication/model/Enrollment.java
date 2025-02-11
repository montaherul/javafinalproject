package org.course.coursewebapplication.model;

import java.util.Date;

public class Enrollment {
    private int id;
    private int user_id;  // Updated to match snake_case
    private int course_id;  // Updated to match snake_case
    private Date enrollment_date;  // Updated to match snake_case

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {  // Updated to match snake_case
        return user_id;
    }

    public void setUser_id(int user_id) {  // Updated to match snake_case
        this.user_id = user_id;
    }

    public int getCourse_id() {  // Updated to match snake_case
        return course_id;
    }

    public void setCourse_id(int course_id) {  // Updated to match snake_case
        this.course_id = course_id;
    }

    public Date getEnrollment_date() {  // Updated to match snake_case
        return enrollment_date;
    }

    public void setEnrollment_date(Date enrollment_date) {  // Updated to match snake_case
        this.enrollment_date = enrollment_date;
    }
}
