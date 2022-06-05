package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 19:01 2022/6/5
 * @Modified by:
 */

import com.xiangliban.managementsystem.dao.mapper.FixMapper;
import com.xiangliban.managementsystem.pojo.Fix.FixRequest;
import com.xiangliban.managementsystem.service.FixService;
import com.xiangliban.managementsystem.service.IdConsturctor;
import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;


@RunWith(value = Parameterized.class)
public class FixServiceTest3 {

    private FixRequest fixRequest;

    private FixService fixService;
    private FixMapper rentMapper;

    public FixServiceTest3(FixRequest fixRequest) {
        this.fixRequest = fixRequest;
    }

    @Parameterized.Parameters(name = "{index}: testOrder = {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 等价类划分
                {new FixRequest("123", "2022-01-01", "#1", "url", "2022-01-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "123")},
                {new FixRequest("123", "2022-01-01", "#1", "url", "2022-01-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "123")},
                {new FixRequest("", "2022-01-01", "#1", "url", "2022-01-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("124hu1h", "", "#1", "url", "2022-03-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("123asd", "2022-01-01", "", "url", "2022-01-01", "Shanghai", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("123", "2022-03-01", "#1", "", "2022-01-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("1wqe23", "2022-01-03", "#1", "url123", "", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("123", "2022-01-01", "#1", "url", "2022-03-01", "", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("1zda23", "2022-07-01", "#1", "url2141", "2022-01-01", "Beijing", "", "#12", "Jack", "1398298398", "")},
                {new FixRequest("12XZ3", "2022-01-05", "#1", "url14131", "2022-02-02", "Zhejiang", "19th floor", "", "Jack", "1398298398", "")},
                {new FixRequest("123", "2022-02-02", "#1", "url124123", "2022-01-01", "Qinghai", "18th floor", "#12", "", "1398298398", "")},
                {new FixRequest("12zxz3", "2022-01-05", "#1", "url51432", "2022-02-02", "Dalian", "3rd floor", "#12", "Jack", "", "")},
                // 边界值分析
                {new FixRequest("123", "2022-01-01", "#1", "url", "2022-01-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("", "2022-01-01", "#1", "url", "2022-01-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("124hu1h", "", "#1", "url", "2022-03-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("123asd", "2022-01-01", "", "url", "2022-01-01", "Shanghai", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("123", "2022-03-01", "#1", "", "2022-01-01", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("1wqe23", "2022-01-03", "#1", "url123", "", "Zhejiang", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("123", "2022-01-01", "#1", "url", "2022-03-01", "", "2nd floor", "#12", "Jack", "1398298398", "")},
                {new FixRequest("1zda23", "2022-07-01", "#1", "url2141", "2022-01-01", "Beijing", "", "#12", "Jack", "1398298398", "")},
                {new FixRequest("12XZ3", "2022-01-05", "#1", "url14131", "2022-02-02", "Zhejiang", "19th floor", "", "Jack", "1398298398", "")},
                {new FixRequest("123", "2022-02-02", "#1", "url124123", "2022-01-01", "Qinghai", "18th floor", "#12", "", "1398298398", "")},
                {new FixRequest("12zxz3", "2022-01-05", "#1", "url51432", "2022-02-02", "Dalian", "3rd floor", "#12", "Jack", "", "")},
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

    public String returnOfAddFixRequest() {

        return IdConsturctor.idConsturctor();
    }

    @org.junit.Test
    public void selectAllWorkersByDepartment() {

        String msr = returnOfAddFixRequest();
        expect(rentMapper.addFixRequest(fixRequest)).andReturn(1);
        replay(rentMapper);

        assertEquals(msr, fixService.addFixRequest(fixRequest));
        verify(rentMapper);
    }
}
