package org.course.coursewebapplication.service;

import org.course.coursewebapplication.dao.EnrollmentDAO;
import org.course.coursewebapplication.model.Enrollment;


import java.util.List;

public class EnrollmentService {
    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentService() {
        this.enrollmentDAO = new EnrollmentDAO();
    }

    // Method to enroll a user in a course
    public boolean enrollUser(int userId, int courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setUser_id(userId);
        enrollment.setCourse_id(courseId);
        return enrollmentDAO.save(enrollment);
    }

    // Method to get all enrollments for a user
    public List<Enrollment> getUserEnrollments(int userId) {
        return enrollmentDAO.findByUserId(userId);
    }

    // Method to remove a user from a course
    public boolean unenrollUser(int enrollmentId) {
        return enrollmentDAO.delete(enrollmentId);
    }
}
