package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 20:09 2022/6/5
 * @Modified by:
 */

import com.xiangliban.managementsystem.dao.mapper.FixMapper;
import com.xiangliban.managementsystem.service.FixService;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;


@RunWith(value = Parameterized.class)
public class FixServiceTest5 {
    private String userId;
    private String orderId;
    private int valid;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest5(String userId, String orderId, int valid) {
        this.userId = userId;
        this.orderId = orderId;
        this.valid = valid;
    }

    @Parameterized.Parameters(name = "{index}: orderId = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"user#1", "order#1", 1},
                {null, "order#1", 0},
                {"", "order#1", 0},
                {"user31231312", "order#1", 0},
                {"user#1", null, 0},
                {"user#1", "", 0},
                {"user#1", "order#112313132", 0},
                // 边界值分析
                {"", "user1", 0},
                {"1", "user1", 1},
                {"11", "user1", 1},
                {"1111111111", "user1", 1},
                {"11111111111", "user1", 1},
                {"111111111111", "user1", 0},
                {"order1", "", 0},
                {"order1", "1", 1},
                {"order1", "11", 1},
                {"order1", "1111111111", 1},
                {"order1", "11111111111", 1},
                {"order1", "111111111111", 0},
        });
    }

    @org.junit.Before
    public void setUp() throws Exception {
        fixService = new FixService();
        rentMapper = EasyMock.createMock(FixMapper.class);
        fixService.setFixMapper(rentMapper);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    public int returnOfForceAssignOrder(int valid) {

        return valid;
    }

    @org.junit.Test
    public void forceAssignOrder() {

        int msr = returnOfForceAssignOrder(valid);

        expect(rentMapper.forceAssignOrder(userId, "", orderId)).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.forceAssignOrder(userId, "", orderId));
        if ((userId != null && userId.length() != 0 && userId.length() <= 11) && (orderId != null && orderId.length() != 0 && orderId.length() <= 11))
            verify(rentMapper);
    }


}
