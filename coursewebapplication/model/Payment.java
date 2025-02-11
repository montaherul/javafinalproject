package org.course.coursewebapplication.model;

public class Payment {
    private int id;
    private String email;
    private int courseId;
    private String txnId;
    private String status;

    public Payment() {}

    public Payment(String email, int courseId, String txnId, String status) {
        this.email = email;
        this.courseId = courseId;
        this.txnId = txnId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
