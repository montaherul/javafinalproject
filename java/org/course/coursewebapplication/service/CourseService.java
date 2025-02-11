package org.course.coursewebapplication.service;

import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.model.Course;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO=new CourseDAO();

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    // Method to get all courses
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    // Method to get a course by ID
    public Course getCourseById(int id) {
        return courseDAO.getCourseById(id);
    }


    // Method to update a course
    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }

    // Method to delete a course
    public boolean deleteCourse(int id) {
        return courseDAO.deleteCourse(id);
    }

}
