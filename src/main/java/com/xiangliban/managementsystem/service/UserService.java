package com.xiangliban.managementsystem.service;

import com.xiangliban.managementsystem.dao.mapper.UserMapper;
import com.xiangliban.managementsystem.pojo.User.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/19/19:20
 * @Description:
 */

@Repository
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 查询
    // 查询所有用户信息
    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    // 根据用户uid查询用户
    public List<User> selectUserByUid(String uid) {
        return userMapper.selectUserByUid(uid);
    }

    // 根据用户account查询用户
    public List<User> selectUserByAccount(String account) {
        return userMapper.selectUserByAccount(account);
    }

    // 根据用户account&password查询用户
    public List<User> userLoginByAccount(String account, String password) {
        return userMapper.userLoginByAccount(account, password);
    }

    // 查询所有村民信息
    public List<User> selectAllVillagers() {
        return userMapper.selectAllVillagers();
    }

    // 新增一个用户
    public String addNewUser(User user) {
        String uid = "User#";
        uid += NameGenerator.nameGenerator(6);
        user.setUid(uid);
        userMapper.addNewUser(user);
        return uid;
    }

    // 更新一个用户
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    // 更新用户权限
    public void updateUserAuthorization(String uid, int authorization) {
        userMapper.updateUserAuthorization(uid, authorization);
    }

    // 删除一个用户
    public void deleteUserByUid(String uid) {
        userMapper.deleteUserByUid(uid);
    }
}
