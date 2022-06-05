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
public class FixServiceTest5 {
    private String orderId;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest5(String orderId) {
        this.orderId = orderId;
    }

    @Parameterized.Parameters(name = "{index}: order = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"order#1"},
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
        fixService = new FixService();
        rentMapper = EasyMock.createMock(FixMapper.class);
        fixService.setFixMapper(rentMapper);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    public int returnOfForceCloseOrder() {

        return 1;
    }

    @org.junit.Test
    public void forceCloseOrder() {

        int msr = returnOfForceCloseOrder();

        expect(rentMapper.forceCloseOrder(orderId,"")).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.forceCloseOrder(orderId,""));
        verify(rentMapper);
    }

}
