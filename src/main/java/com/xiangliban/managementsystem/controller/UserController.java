package com.xiangliban.managementsystem.controller;

import com.xiangliban.managementsystem.pojo.User.User;
import com.xiangliban.managementsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘元驰
 * @Date: 2021/12/19/19:20
 * @Description:
 */

@Api(tags = "用户类")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("查询所有用户信息, 无需参数")
    @GetMapping("/user/selectAllUsers")
    public List<User> selectAllUsers() {
        return userService.selectAllUsers();
    }

    @ApiOperation("根据用户uid查询用户")
    @GetMapping("/user/selectUserByUid")
    public List<User> selectUserByUid(String uid) {
        return userService.selectUserByUid(uid);
    }

    @ApiOperation("村民登录: 根据用户account查询用户, 若查无此人则自动创建用户并返回uid, 若有则返回uid")
    @GetMapping("/user/selectUserByAccount")
    public String selectUserByAccount(String account) {
        List<User> currentUser = userService.selectUserByAccount(account);
        // 查无此人
        if(currentUser.isEmpty()){
            User newUser = new User();
            newUser.setAccount(account);
            newUser.setPassword("123456");
            newUser.setAuthorization(1);
            return userService.addNewUser(newUser);
        }
        else {
            return currentUser.get(0).getUid();
        }
    }

    @ApiOperation("根据用户account&password验证用户登录, 登录成功返回身份信息, 失败返回空")
    @GetMapping("/user/userLoginByAccount")
    public List<User> userLoginByAccount(String account, String password) {
        return userService.userLoginByAccount(account, password);
    }

    @ApiOperation("查询所有村民信息")
    @GetMapping("/user/selectAllVillagers")
    public List<User> selectAllVillagers() {
        return userService.selectAllVillagers();
    }

    @ApiOperation("工作人员: 新增用户信息但account不可以重复, 自动生成并返回uid")
    @PostMapping("/user/addNewUser")
    public String addNewUser(@RequestBody User user) {
        String inputAccount = user.getAccount();    // 获取用户输入的账号名
        List<User> checkAccount = userService.selectUserByAccount(inputAccount);    // 检查用户输入的账号是否已存在
        if(checkAccount.isEmpty()){
            return userService.addNewUser(user);    // 不重复便新建用户, 返回uid
        }
        else {
            String errorMessage = "注册失败, 本用户名已经被占用, 用户未创建";
            return errorMessage;
        }
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/user/updateUser")
    public void updateUser(@RequestBody User user) {
        String inputAccount = user.getAccount();    // 获取用户输入的账号名
        List<User> checkAccount = userService.selectUserByAccount(inputAccount);    // 检查用户输入的账号是否已存在
        if(checkAccount.isEmpty()){
            userService.updateUser(user);    // 不重复便更新用户信息
        }
        else if (checkAccount.get(0).getUid().equals(user.getUid())) {
            userService.updateUser(user);    // 查到的也是本人便更新用户信息
        }
        else {
            String errorMessage = "更新失败, 本用户名已经被占用, 用户信息未更新";
            System.err.println(errorMessage);
        }
    }

    @ApiOperation("更新用户权限")
    @PostMapping("/user/updateUserAuthorization")
    public void updateUser(String uid, int authorization) {
        userService.updateUserAuthorization(uid, authorization);
    }

    @ApiOperation("根据uid删除一个用户")
    @PostMapping("/user/deleteUserByUid")
    public void deleteUserByUid(String uid) {
        userService.deleteUserByUid(uid);
    }
}
