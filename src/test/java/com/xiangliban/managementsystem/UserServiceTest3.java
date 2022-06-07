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
    private String userPassword;    private int valid;


    private UserService userService;
    private UserMapper userMapper;

    public UserServiceTest3(String userAccount,String userPassword, int valid) {

        this.userAccount = userAccount;
        this.userPassword = userPassword;        this.valid = valid;

    }

    @Parameterized.Parameters(name = "{index}: userAccount = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划
                {"user1","user1",1},
                {null,"user1",0},
                {"","user1",0},
                {"user31231312","user1",0},
                {"user1",null,0},
                {"user1","",0},
                {"user1","user112313132",0},
                // 边界值分析
                {"","user1",0},
                {"1","user1",1},
                {"11","user1",1},
                {"1111111111","user1",1},
                {"11111111111","user1",1},
                {"111111111111","user1",0},
                {"user1","",0},
                {"user1","1",1},
                {"user1","11",1},
                {"user1","1111111111",1},
                {"user1","11111111111",1},
                {"user1","111111111111",0},
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

    public ArrayList<User> returnOfUserLoginByAccount(int valid) {

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
    public void userLoginByAccount() {

        ArrayList<User> msr = returnOfUserLoginByAccount(valid);

        expect(userMapper.userLoginByAccount(userAccount,userPassword)).andReturn(msr);
        replay(userMapper);

        assertEquals(msr, userService.userLoginByAccount(userAccount,userPassword));
        if((userAccount != null && userAccount.length() != 0 && userAccount.length() <= 11)&&(userPassword != null && userPassword.length() != 0 && userPassword.length() <= 11))
            verify(userMapper);
    }

}
