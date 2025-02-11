package org.course.coursewebapplication.controller;

import org.course.coursewebapplication.dao.CourseDAO;
import org.course.coursewebapplication.model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class ManageCourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        courseDAO = new CourseDAO(); // Initialize DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listCourses(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCourse(request, response);
                    break;
                default:
                    listCourses(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/courses?action=list&error=An error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("update".equals(action)) {
                updateCourse(request, response);
            } else {
                listCourses(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/courses?action=list&error=An error occurred");
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);

        // Get success or error message
        String successMessage = request.getParameter("success");
        String errorMessage = request.getParameter("error");

        if (successMessage != null && !successMessage.isEmpty()) {
            request.setAttribute("success", successMessage);
        }

        if (errorMessage != null && !errorMessage.isEmpty()) {
            request.setAttribute("error", errorMessage);
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("manage-courses.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int courseId = Integer.parseInt(request.getParameter("id"));
            Course existingCourse = courseDAO.getCourseById(courseId);

            if (existingCourse == null) {
                response.sendRedirect(request.getContextPath() + "/courses?action=list&error=Course not found");
                return;
            }

            request.setAttribute("course", existingCourse);
            request.getRequestDispatcher("edit-course.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/courses?action=list&error=Invalid course ID");
        }
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String duration = request.getParameter("duration");
            double price = Double.parseDouble(request.getParameter("price"));
            String image = request.getParameter("image");
            String instructorimage= request.getParameter("instructorimage");

            Course course = new Course();
            course.setId(id);
            course.setTitle(name);
            course.setDescription(description);
            course.setDuration(duration);
            course.setPrice(price);
            course.setImage(image);
            course.setInstructorimage(instructorimage);

            boolean isUpdated = courseDAO.updateCourse(course);
            if (isUpdated) {
                response.sendRedirect(request.getContextPath() + "/courses?action=list&success=Course updated successfully");
            } else {
                request.setAttribute("error", "Failed to update course. Please try again.");
                request.setAttribute("course", course); // Preserve course data for the form
                request.getRequestDispatcher("update-course").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input. Please check your data.");
            request.getRequestDispatcher("update-course").forward(request, response);
        }
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = courseDAO.deleteCourse(id);

            if (isDeleted) {
                response.sendRedirect(request.getContextPath() + "/courses?action=list&success=Course deleted successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/courses?action=list&error=Failed to delete course");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/courses?action=list&error=Invalid course ID");
        }
    }
}
