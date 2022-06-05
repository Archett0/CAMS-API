package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 20:12 2022/6/5
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
import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class UserServiceTest1 {
    private String userId;

    private UserService userService;
    private UserMapper userMapper;

    public UserServiceTest1(String userId) {
        this.userId = userId;
    }

    @Parameterized.Parameters(name = "{index}: userId = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"user#1"},
                {null},
                {""},
                {"wixnhishyhde"},
                // 边界值分析
                {""},
                {"1"},
                {"11"},
                {"1111111111"},
                {"11111111111"},
                {"111111111111"},
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

    public ArrayList<User> returnOfSelectUserByUid() {

        ArrayList<User> msr = new ArrayList<>();
        User r = new User();
        r.setAccount("");
        r.setUid("demo");
        msr.add(r);
        return msr;
    }

    @org.junit.Test
    public void selectUserByUid() {

        ArrayList<User> msr = returnOfSelectUserByUid();

        expect(userMapper.selectUserByUid(userId)).andReturn(msr);
        replay(userMapper);

        assertEquals(msr, userService.selectUserByUid(userId));
        verify(userMapper);
    }

}
