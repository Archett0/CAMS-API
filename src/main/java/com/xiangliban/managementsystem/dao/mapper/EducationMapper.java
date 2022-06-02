package com.xiangliban.managementsystem.dao.mapper;

import com.xiangliban.managementsystem.pojo.education.Course;
import com.xiangliban.managementsystem.pojo.education.CourseCategory;
import com.xiangliban.managementsystem.pojo.education.CourseDiagram;
import com.xiangliban.managementsystem.pojo.education.CoursePieDiagram;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/16/09:28
 * @Description:
 */

@Mapper     // 表示mybatis的mapper类
@Repository // 整合进spring
public interface EducationMapper {
    // 查询
    // 查询所有课程信息
    List<Course> selectAllCourses();

    // 查询用户参与的课程
    List<Course> selectAllCourseByUserId(String uid);

    // 查询所有课程类别
    List<CourseCategory> selectAllCourseCategory();

    // 根据课程类别获取相关的课程
    List<Course> selectAllCourseByCategoryId(String courseCategoryId);

    // 根据课程号获取一个课程
    List<Course> selectCourseById(String courseId);

    // 根据指定的id查看课程选课人数
    int getNumberOfPplSelected(String courseId);

    // 查看所有课程选课人数
    List<CourseDiagram> getAllCoursePplSelected();

    // 查看所有课程类别选课人数
    List<CoursePieDiagram> getAllCourseCategorySelected();

    // 添加
    // 新增一个课程
    void addNewCourse(String courseId, String courseName, String courseCover, String courseCategoryId, String courseLink, String courseStartTime, String courseEndTime, String courseTeacherName, int courseSections, String courseDetails, String courseFee, String courseStatus, int courseLike);

    // 新参与一个课程
    void addMyCourse(String courseId, String uid);

    // 新增一个课程类别
    void addCourseCategory(String courseCategoryId, String courseCategoryName);

    // 修改
    // 修改课程信息
    void alterCourseById(String courseId, String courseName, String courseCover, String courseCategoryId, String courseLink, String courseStartTime, String courseEndTime, String courseTeacherName, int courseSections, String courseDetails, String courseFee, String courseStatus, int courseLike);

    // 更改一个课程类别
    void alterCourseCategoryNameById(String courseCategoryId, String courseCategoryName);

    // 删除
    // 删除一个课程
    void deleteCourseById(String courseId);

    // 取消参与一个课程
    void deleteMyCourse(String courseId, String uid);

    // 删除一个课程类别
    void deleteCourseCategory(String courseCategoryId);
}
