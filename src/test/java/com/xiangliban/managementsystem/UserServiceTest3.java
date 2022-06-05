package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 20:17 2022/6/5
 * @Modified by:
 */

import com.xiangliban.managementsystem.dao.mapper.UserMapper;
import com.xiangliban.managementsystem.pojo.User.User;
import com.xiangliban.managementsystem.service.UserService;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class UserServiceTest3 {
    private String userAccount;
    private String userPassword;

    private UserService userService;
    private UserMapper userMapper;

    public UserServiceTest3(String userAccount,String userPassword) {

        this.userAccount = userAccount;
        this.userPassword = userPassword;
    }

    @Parameterized.Parameters(name = "{index}: userAccount = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"user1","user1"},
                {null,null},
                {"",""},
                {"wixnhishyhde","wixnhishyhde"},
                // 边界值分析
                {"",""},
                {"1","1"},
                {"11","11"},
                {"1111111111","1111111111"},
                {"11111111111","11111111111"},
                {"111111111111","111111111111"},
        });
    }

    @org.junit.Before
    public void setUp() throws Exception {
        userService = new UserService();
        userMapper = EasyMock.createMock(UserMapper.class);
        userService.setUserMapper(userMapper);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    public ArrayList<User> returnOfUserLoginByAccount() {

        ArrayList<User> msr = new ArrayList<>();
        User r = new User();
        r.setAccount("");
        r.setUid("user1");
        msr.add(r);
        return msr;
    }

    @org.junit.Test
    public void userLoginByAccount() {

        ArrayList<User> msr = returnOfUserLoginByAccount();

        expect(userMapper.userLoginByAccount(userAccount,userPassword)).andReturn(msr);
        replay(userMapper);

        assertEquals(msr, userService.userLoginByAccount(userAccount,userPassword));
        verify(userMapper);
    }

}
