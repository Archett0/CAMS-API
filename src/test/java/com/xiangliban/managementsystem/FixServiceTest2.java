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

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest2(String fixDepartmentId) {
        this.fixDepartmentId = fixDepartmentId;
    }

    @Parameterized.Parameters(name = "{index}: fixDepartmentId = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {"fixDep#1"},
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

    public ArrayList<FixWorker> returnOfSelectAllWorkersByDepartment() {

        ArrayList<FixWorker> msr = new ArrayList<>();
        FixWorker r = new FixWorker();
        r.setAccount("");
        r.setAvatar("demo");
        msr.add(r);
        return msr;
    }

    @org.junit.Test
    public void selectAllWorkersByDepartment() {

        ArrayList<FixWorker> msr = returnOfSelectAllWorkersByDepartment();

        expect(rentMapper.selectAllWorkersByDepartment(fixDepartmentId)).andReturn(msr);
        replay(rentMapper);

        assertEquals(msr, fixService.selectAllWorkersByDepartment(fixDepartmentId));
        verify(rentMapper);
    }


}
