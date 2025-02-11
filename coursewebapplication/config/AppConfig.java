package org.course.coursewebapplication.config;


public class AppConfig {
    public static final String APP_NAME = "Course Enrollment System";
    public static final String BASE_URL = "http://localhost:8080/course_enrollment_system";
    public static final String SUPPORT_EMAIL = " ";

    // Environment-specific configurations
    public static final boolean DEBUG_MODE = true;
    public static final String FILE_UPLOAD_PATH = "src/main/resources/uploads/";

    // Helper method for logging (example)
    public static void log(String message) {
        if (DEBUG_MODE) {
            System.out.println("[LOG] " + message);
        }
    }
}
