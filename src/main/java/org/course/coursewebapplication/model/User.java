package org.course.coursewebapplication.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String hsc;             // Higher Secondary Certificate
    private String phone;
    private String parentMobile;
    private String institution;
    private String facebook;
    private String address;
    private String profile_pic;

    // Constructors
    public User() {}

    public User(int id, String name, String email, String hsc, String phone,String password,
                String parentMobile, String institution, String facebook, String address, String profile_pic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hsc = hsc;
        this.phone = phone;
        this.password = password;
        this.parentMobile = parentMobile;
        this.institution = institution;
        this.facebook = facebook;
        this.address = address;
        this.profile_pic = profile_pic;
    }

    public User(int id, String name, String email, String phone, String address, String facebook, String profilePicFileName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.facebook = facebook;
        this.profile_pic = profilePicFileName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getHsc() {
        return hsc;
    }

    public void setHsc(String hsc) {
        this.hsc = hsc;
    }

    public  String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParentMobile() {
        return parentMobile;
    }

    public void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public  String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public  String getProfile_pic() { return profile_pic; }
    public void setProfile_pic(String profile_pic) {this.profile_pic = profile_pic; }
}
