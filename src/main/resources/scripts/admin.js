// admin.js

// Delete course confirmation
function confirmDelete(courseId) {
    if (confirm('Are you sure you want to delete this course?')) {
        // Replace with actual delete logic
        console.log(`Course ${courseId} deleted`);
    }
}

// Toggle sidebar
document.querySelector('.toggle-sidebar').addEventListener('click', () => {
    const sidebar = document.querySelector('.admin-sidebar');
    sidebar.classList.toggle('hidden');
});
