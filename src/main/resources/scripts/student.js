// student.js

// Enroll in a course
function enrollCourse(courseId) {
    fetch(`/enroll?courseId=${courseId}`, { method: 'POST' })
        .then(response => {
            if (response.ok) {
                showAlert('Successfully enrolled in the course!', 'success');
            } else {
                showAlert('Failed to enroll. Please try again.', 'danger');
            }
        })
        .catch(() => showAlert('An error occurred. Please try again.', 'danger'));
}

// Mark course as completed
function markAsComplete(courseId) {
    fetch(`/complete?courseId=${courseId}`, { method: 'POST' })
        .then(response => {
            if (response.ok) {
                showAlert('Course marked as complete!', 'success');
            } else {
                showAlert('Failed to update status. Please try again.', 'danger');
            }
        })
        .catch(() => showAlert('An error occurred. Please try again.', 'danger'));
}
