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
import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class UserServiceTest2 {
    private String userAccount;    private int valid;


    private UserService userService;
    private UserMapper userMapper;

    public UserServiceTest2(String userAccount, int valid) {
        this.userAccount = userAccount;        this.valid = valid;

    }

    @Parameterized.Parameters(name = "{index}: userAccount = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"user1", 1},
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

    public ArrayList<User> returnOfSelectUserByAccount(int valid) {

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
    public void selectUserByAccount() {

        ArrayList<User> msr = returnOfSelectUserByAccount(valid);

        expect(userMapper.selectUserByAccount(userAccount)).andReturn(msr);
        replay(userMapper);

        assertEquals(msr, userService.selectUserByAccount(userAccount));
        if(userAccount != null && userAccount.length() != 0 && userAccount.length() <= 11)
        verify(userMapper);
    }

}
