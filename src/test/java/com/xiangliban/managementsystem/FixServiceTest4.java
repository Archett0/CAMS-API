package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 20:07 2022/6/5
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
public class FixServiceTest4 {
    private String orderId;
    private int valid;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest4(String orderId, int valid) {
        this.orderId = orderId;
        this.valid = valid;
    }

    @Parameterized.Parameters(name = "{index}: order = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"order#1", 1},
                {null, 0},
                {"", 0},
                {"order#1313131312", 0},
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
        fixService = new FixService();
        rentMapper = EasyMock.createMock(FixMapper.class);
        fixService.setFixMapper(rentMapper);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    public int returnOfForceCloseOrder(int valid) {

        return valid;
    }

    @org.junit.Test
    public void forceCloseOrder() {

        int msr = returnOfForceCloseOrder(valid);

        expect(rentMapper.forceCloseOrder(orderId, "")).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.forceCloseOrder(orderId, ""));
        if (orderId != null && orderId.length() != 0 && orderId.length() <= 11)
            verify(rentMapper);
    }

}
