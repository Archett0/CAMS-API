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
public class UserServiceTest4 {
    private String userId;
    private int authorization;
    private int valid;

    private UserService userService;
    private UserMapper userMapper;

    public UserServiceTest4(String userId, int authorization, int valid) {
        this.userId = userId;
        this.authorization = authorization;
        this.valid = valid;
    }

    @Parameterized.Parameters(name = "{index}: userId = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"user#1", 1, 1},
                {null, 0, 0},
                {"", 0, 0},
                {"wixnhishyhde", 14, 0},
                // 边界值分析
                {"", 0, 0},
                {"1", 1, 1},
                {"11", 2, 1},
                {"1111111111", 12, 1},
                {"11111111111", 13, 1},
                {"111111111111", 14, 0},
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

    public int returnOfUpdateUserAuthorization(int valid) {

        if (valid == 1) {
            return 1;
        }
        return 0;
    }

    @org.junit.Test
    public void updateUserAuthorization() {

        int msr = returnOfUpdateUserAuthorization(valid);

        expect(userMapper.updateUserAuthorization(userId, authorization)).andReturn(msr);
        replay(userMapper);

        assertEquals(msr, userService.updateUserAuthorization(userId, authorization));
        if(msr == 1)
            verify(userMapper);
    }

}
