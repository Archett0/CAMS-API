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
    private int valid;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest1(String userId, int valid) {
        this.userId = userId;
        this.valid = valid;
    }

    @Parameterized.Parameters(name = "{index}: fixWorkerId = {0}")
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
        fixService = new FixService();
        rentMapper = EasyMock.createMock(FixMapper.class);
        fixService.setFixMapper(rentMapper);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    public ArrayList<FixOrderWithDetails> returnOfAllFixDetailsByWorkerId(int valid) {

        ArrayList<FixOrderWithDetails> msr = new ArrayList<>();
        if (valid == 1) {
            FixOrderWithDetails r = new FixOrderWithDetails();
            r.setFixId("");
            r.setFixDetails("demo");
            msr.add(r);
        }
        return msr;
    }

    @org.junit.Test
    public void selectAllFixDetailsByWorkerId() {

        ArrayList<FixOrderWithDetails> msr = returnOfAllFixDetailsByWorkerId(valid);

        expect(rentMapper.selectAllFixDetailsByWorkerId(userId)).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.selectAllFixDetailsByWorkerId(userId));
        if(userId != null && userId.length() != 0 && userId.length() <= 11)
            verify(rentMapper);
    }

}
