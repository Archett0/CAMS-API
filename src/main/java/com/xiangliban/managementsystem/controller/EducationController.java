package com.xiangliban.managementsystem.controller;

import com.xiangliban.managementsystem.pojo.education.Course;
import com.xiangliban.managementsystem.pojo.education.CourseCategory;
import com.xiangliban.managementsystem.pojo.education.CourseDiagram;
import com.xiangliban.managementsystem.pojo.education.CoursePieDiagram;
import com.xiangliban.managementsystem.service.EducationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/16/09:28
 * @Description:
 */

@Api(tags = "老年大学接口类")
@RestController
@CrossOrigin
public class EducationController {

    @Autowired
    private EducationService educationService;

    @ApiOperation("查询所有课程信息 [Worker and Villager]")
    @GetMapping("/education/selectAllCourses")
    public List<Course> selectAllFixDetailsByWorkerId() {
        return educationService.selectAllCourses();
    }

    @ApiOperation("查询用户参与的课程 [Villager Only]")
    @GetMapping("/education/selectAllCourseByUserId")
    public List<Course> selectAllCourseByUserId(String uid) {
        return educationService.selectAllCourseByUserId(uid);
    }

    @ApiOperation("查询所有课程类别 [Admin Only]")
    @GetMapping("/education/selectAllCourseCategory")
    public List<CourseCategory> selectAllCourseByUserId() {
        return educationService.selectAllCourseCategory();
    }

    @ApiOperation("查询特定课程类别的课程 [All]")
    @GetMapping("/education/selectAllCourseByCategoryId")
    public List<Course> selectAllCourseByCategoryId(String courseCategoryId) {
        return educationService.selectAllCourseByCategoryId(courseCategoryId);
    }

    @ApiOperation("根据课程号获取一个课程 [All]")
    @GetMapping("/education/selectCourseById")
    public List<Course> selectCourseById(String courseId) {
        return educationService.selectCourseById(courseId);
    }

    @ApiOperation("根据指定的课程号查看课程选课人数 [Admin&Worker]")
    @GetMapping("/education/getNumberOfPplSelected")
    public int getNumberOfPplSelected(String courseId) {
        return educationService.getNumberOfPplSelected(courseId);
    }

    @ApiOperation("查看所有课程选课人数 [Admin&Worker]")
    @GetMapping("/education/getAllCoursePplSelected")
    public List<CourseDiagram> getAllCoursePplSelected() {
        return educationService.getAllCoursePplSelected();
    }

    @ApiOperation("查看所有课程类别选课人数 [Admin&Worker]")
    @GetMapping("/education/getAllCourseCategorySelected")
    public List<CoursePieDiagram> getAllCourseCategorySelected() {
        return educationService.getAllCourseCategorySelected();
    }

    @ApiOperation("新增一个课程, 课程号自动生成 [Worker Only]")
    @PostMapping("/education/addNewCourse")
    public void addNewCourse(String courseName, String courseCover, String courseCategoryId, String courseLink, String courseStartTime, String courseEndTime, String courseTeacherName, int courseSections, String courseDetails, String courseFee, String courseStatus, int courseLike) {
        educationService.addNewCourse(courseName,courseCover,courseCategoryId,courseLink,courseStartTime,courseEndTime,courseTeacherName,courseSections,courseDetails,courseFee,courseStatus,courseLike);
    }

    @ApiOperation("新参与一个课程 [Villager Only]")
    @PostMapping("/education/addMyCourse")
    public void addMyCourse(String courseId, String uid) {
        educationService.addMyCourse(courseId, uid);
    }

    @ApiOperation("新增一个课程类别, 课程类别号自动生成 [Admin Only]")
    @PostMapping("/education/addCourseCategory")
    public void addCourseCategory(String courseCategoryName) {
        educationService.addCourseCategory(courseCategoryName);
    }

    @ApiOperation("修改课程信息 [Worker Only]")
    @PostMapping("/education/alterCourseById")
    public void alterCourseById(String courseId, String courseName, String courseCover, String courseCategoryId, String courseLink, String courseStartTime, String courseEndTime, String courseTeacherName, int courseSections, String courseDetails, String courseFee, String courseStatus, int courseLike) {
        educationService.alterCourseById(courseId,courseName,courseCover,courseCategoryId,courseLink,courseStartTime,courseEndTime,courseTeacherName,courseSections,courseDetails,courseFee,courseStatus,courseLike);
    }

    @ApiOperation("更改一个课程类别 [Admin Only]")
    @PostMapping("/education/alterCourseCategoryNameById")
    public void alterCourseCategoryNameById(String courseCategoryId, String courseCategoryName) {
        educationService.alterCourseCategoryNameById(courseCategoryId, courseCategoryName);
    }

    @ApiOperation("删除一个课程 [Worker Only]")
    @PostMapping("/education/deleteCourseById")
    public void deleteCourseById(String courseId) {
        educationService.deleteCourseById(courseId);
    }

    @ApiOperation("取消参与一个课程 [Villager Only]")
    @PostMapping("/education/deleteMyCourse")
    public void forceCloseOrder(String courseId, String uid) {
        educationService.deleteMyCourse(courseId, uid);
    }

    @ApiOperation("删除一个课程类别 [Admin Only]")
    @PostMapping("/education/deleteCourseCategory")
    public void deleteCourseCategory(String courseCategoryId) {
        educationService.deleteCourseCategory(courseCategoryId);
    }
}