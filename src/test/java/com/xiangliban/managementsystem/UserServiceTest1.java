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
    private String userId;    private int valid;


    private UserService userService;
    private UserMapper userMapper;

    public UserServiceTest1(String userId, int valid) {
        this.userId = userId;        this.valid = valid;

    }

    @Parameterized.Parameters(name = "{index}: userId = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"user#1", 1},
                {null, 0},
                {"", 0},
                {"user#1313131312", 0},
                // 边界值分析
                {"", 0},
                {"1", 1},
                {"11", 1},
                {"1111111111", 1},
                {"11111111111", 1},
                {"111111111111", 0},
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

    public ArrayList<User> returnOfSelectUserByUid(int valid) {

        ArrayList<User> msr = new ArrayList<>();
        if (valid == 1) {
            User r = new User();
            r.setAccount("");
            r.setUid("demo");
            msr.add(r);
        }
        return msr;
    }

    @org.junit.Test
    public void selectUserByUid() {

        ArrayList<User> msr = returnOfSelectUserByUid(valid);

        expect(userMapper.selectUserByUid(userId)).andReturn(msr);
        replay(userMapper);

        assertEquals(msr, userService.selectUserByUid(userId));
        if(userId != null && userId.length() != 0 && userId.length() <= 11)
            verify(userMapper);
    }

}
