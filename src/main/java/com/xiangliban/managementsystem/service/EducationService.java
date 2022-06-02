package com.xiangliban.managementsystem.service;

import com.xiangliban.managementsystem.dao.mapper.EducationMapper;
import com.xiangliban.managementsystem.pojo.education.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/16/09:28
 * @Description:
 */

@Repository
public class EducationService {
    @Autowired
    private EducationMapper educationMapper;

    // 查询
    // 查询所有课程信息
    public List<Course> selectAllCourses() {
        return educationMapper.selectAllCourses();
    }

    // 查询用户参与的课程
    public List<Course> selectAllCourseByUserId(String uid) {
        return educationMapper.selectAllCourseByUserId(uid);
    }

    // 查询所有课程类别
    public List<CourseCategory> selectAllCourseCategory() {
        return educationMapper.selectAllCourseCategory();
    }

    // 根据课程类别获取相关的课程
    public List<Course> selectAllCourseByCategoryId(String courseCategoryId) {
        return educationMapper.selectAllCourseByCategoryId(courseCategoryId);
    }

    // 根据课程号获取一个课程
    public List<Course> selectCourseById(String courseId) {
        return educationMapper.selectCourseById(courseId);
    }

    // 根据指定的id查看课程选课人数
    public int getNumberOfPplSelected(String courseId){
        return educationMapper.getNumberOfPplSelected(courseId);
    }

    // 查看所有课程选课人数
    public List<CourseDiagram> getAllCoursePplSelected() {
        return educationMapper.getAllCoursePplSelected();
    }

    // 查看所有课程类别选课人数
    public List<CoursePieDiagram> getAllCourseCategorySelected() {
        return educationMapper.getAllCourseCategorySelected();
    }

    // 添加
    // 新增一个课程
    public void addNewCourse(String courseName, String courseCover, String courseCategoryId, String courseLink, String courseStartTime, String courseEndTime, String courseTeacherName, int courseSections, String courseDetails, String courseFee, String courseStatus, int courseLike) {
        String courseId = "Course#";
        courseId += NameGenerator.nameGenerator(5);
        educationMapper.addNewCourse(courseId,courseName,courseCover,courseCategoryId,courseLink,courseStartTime,courseEndTime,courseTeacherName,courseSections,courseDetails,courseFee,courseStatus,courseLike);
    }

    // 新参与一个课程
    public void addMyCourse(String courseId, String uid) {
        educationMapper.addMyCourse(courseId, uid);
    }

    // 新增一个课程类别
    public void addCourseCategory(String courseCategoryName) {
        String courseCategoryId = "CCategory#";
        courseCategoryId += NameGenerator.nameGenerator(5);
        educationMapper.addCourseCategory(courseCategoryId, courseCategoryName);
    }

    // 修改
    // 修改课程信息
    public void alterCourseById(String courseId, String courseName, String courseCover, String courseCategoryId, String courseLink, String courseStartTime, String courseEndTime, String courseTeacherName, int courseSections, String courseDetails, String courseFee, String courseStatus, int courseLike) {
        educationMapper.alterCourseById(courseId,courseName,courseCover,courseCategoryId,courseLink,courseStartTime,courseEndTime,courseTeacherName,courseSections,courseDetails,courseFee,courseStatus,courseLike);
    }

    // 更改一个课程类别
    public void alterCourseCategoryNameById(String courseCategoryId, String courseCategoryName) {
        educationMapper.alterCourseCategoryNameById(courseCategoryId, courseCategoryName);
    }

    // 删除
    // 删除一个课程
    public void deleteCourseById(String courseId) {
        educationMapper.deleteCourseById(courseId);
    }

    // 取消参与一个课程
    public void deleteMyCourse(String courseId, String uid) {
        educationMapper.deleteMyCourse(courseId, uid);
    }

    // 删除一个课程类别
    public void deleteCourseCategory(String courseCategoryId) {
        educationMapper.deleteCourseCategory(courseCategoryId);
    }

}
