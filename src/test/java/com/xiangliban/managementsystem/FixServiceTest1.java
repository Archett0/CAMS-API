package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 16:50 2022/6/5
 * @Modified by:
 */

import com.xiangliban.managementsystem.dao.mapper.FixMapper;
import com.xiangliban.managementsystem.pojo.Fix.FixOrderWithDetails;
import com.xiangliban.managementsystem.service.FixService;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


@RunWith(value = Parameterized.class)
public class FixServiceTest1 {

    private String userId;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest1(String userId) {
        this.userId = userId;
    }

    @Parameterized.Parameters(name = "{index}: fixWorkerId = {0}")
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
        fixService = new FixService();
        rentMapper = EasyMock.createMock(FixMapper.class);
        fixService.setFixMapper(rentMapper);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    public ArrayList<FixOrderWithDetails> returnOfAllFixDetailsByWorkerId() {

        ArrayList<FixOrderWithDetails> msr = new ArrayList<>();
        FixOrderWithDetails r = new FixOrderWithDetails();
        r.setFixId("");
        r.setFixDetails("demo");
        msr.add(r);
        return msr;
    }

    @org.junit.Test
    public void selectAllFixDetailsByWorkerId() {

        ArrayList<FixOrderWithDetails> msr = returnOfAllFixDetailsByWorkerId();

        expect(rentMapper.selectAllFixDetailsByWorkerId(userId)).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.selectAllFixDetailsByWorkerId(userId));
        verify(rentMapper);
    }

}
