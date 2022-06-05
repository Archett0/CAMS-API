package com.xiangliban.managementsystem.dao.mapper;

import com.xiangliban.managementsystem.pojo.User.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/19/19:20
 * @Description:
 */

@Mapper     // 表示mybatis的mapper类
@Repository // 整合进spring

public interface UserMapper {
    // 查询
    // 查询所有用户信息
    List<User> selectAllUsers();

    // 根据用户uid查询用户
    List<User> selectUserByUid(String uid);

    // 根据用户account查询用户
    List<User> selectUserByAccount(String account);

    // 根据用户account&password验证用户
    List<User> userLoginByAccount(String account, String password);

    // 查询所有村民信息
    List<User> selectAllVillagers();

    // 新增一个用户
//    void addNewUser(String uid,String account,String password,String name,String sex,String age,String phone,String id,String political_face,String authorization,String avatar);
    void addNewUser(@Param("user") User user);

    // 更新一个用户
    void updateUser(@Param("user") User user);

    // 更新用户权限
    int updateUserAuthorization(String uid, Integer authorization);

    // 删除一个用户
    void deleteUserByUid(String uid);
}
