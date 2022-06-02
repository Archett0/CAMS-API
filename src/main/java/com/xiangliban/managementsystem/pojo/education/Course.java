package com.xiangliban.managementsystem.pojo.education;
import lombok.Data;

@Data
public class Course {
    // Original Data of Course Table
    private String courseId;
    private String courseName;
    private String courseCover;
    private String courseCategoryId;
    private String courseLink;
    private String courseStartTime;
    private String courseEndTime;
    private String courseTeacherName;
    private int courseSections;
    private String courseDetails;
    private String courseFee;
    private String courseStatus;
    private int courseLike;

    //Joint Query Result
    private String courseCategoryName;
}
