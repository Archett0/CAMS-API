package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 18:54 2022/6/5
 * @Modified by:
 */

import com.xiangliban.managementsystem.dao.mapper.FixMapper;
import com.xiangliban.managementsystem.pojo.Fix.FixWorker;
import com.xiangliban.managementsystem.service.FixService;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


@RunWith(value = Parameterized.class)
public class FixServiceTest2 {

    private String fixDepartmentId;
    private int valid;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest2(String fixDepartmentId, int valid) {
        this.fixDepartmentId = fixDepartmentId;
        this.valid = valid;
    }

    @Parameterized.Parameters(name = "{index}: fixDepartmentId = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"fixDep#1", 1},
                {null, 0},
                {"", 0},
                {"fixDep#1313131312", 0},
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

    public ArrayList<FixWorker> returnOfSelectAllWorkersByDepartment(int valid) {

        ArrayList<FixWorker> msr = new ArrayList<>();
        if (valid == 1) {

            FixWorker r = new FixWorker();
            r.setAccount("");
            r.setAvatar("demo");
            msr.add(r);
        }
        return msr;
    }

    @org.junit.Test
    public void selectAllWorkersByDepartment() {

        ArrayList<FixWorker> msr = returnOfSelectAllWorkersByDepartment(valid);

        expect(rentMapper.selectAllWorkersByDepartment(fixDepartmentId)).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.selectAllWorkersByDepartment(fixDepartmentId));
        if(fixDepartmentId != null && fixDepartmentId.length() != 0 && fixDepartmentId.length() <= 11)
            verify(rentMapper);
    }


}
