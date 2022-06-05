package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 19:57 2022/6/5
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

    private String userId;
    private String orderId;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest4(String userId, String orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    @Parameterized.Parameters(name = "{index}: orderId = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"user#1","order#1"},
                {"user#1",""},
                {"",""},
                {"wixnhishyhde","order#1"},
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
        fixService = new FixService();
        rentMapper = EasyMock.createMock(FixMapper.class);
        fixService.setFixMapper(rentMapper);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    public int returnOfTakeOrderByOrderIdAndWorkerId() {

        return 1;
    }

    @org.junit.Test
    public void takeOrderByOrderIdAndWorkerId() {

        int msr = returnOfTakeOrderByOrderIdAndWorkerId();

        expect(rentMapper.takeOrderByOrderIdAndWorkerId(userId,"",orderId)).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.takeOrderByOrderIdAndWorkerId(userId,"",orderId));
        verify(rentMapper);
    }

}
