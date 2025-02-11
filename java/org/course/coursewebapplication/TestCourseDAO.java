package org.course.coursewebapplication;

import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.model.Course;

public class TestCourseDAO {
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();

        int courseId = 4; // Replace with an actual course ID from your database
        Course course = courseDAO.getCourseById(courseId);

        if (course != null) {
            System.out.println("Course ID: " + course.getId());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Instructor: " + course.getInstructor());
            System.out.println("Price: " + course.getPrice());
            System.out.println("Duration: " + course.getDuration());
            System.out.println("Image: " + course.getImage());
            System.out.println("Instructor Image: " + course.getInstructorimage());
        } else {
            System.out.println("Course not found.");
        }
    }
}
