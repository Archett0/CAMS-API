package com.xiangliban.managementsystem;

/**
 * @Author: Cooper Liu
 * @Description:
 * @Date: Created at 16:14 2022/6/5
 * @Modified by:
 */

import com.xiangliban.managementsystem.ManagementsystemApplication;
import com.xiangliban.managementsystem.controller.FixController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)                                    //让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@SpringBootTest(classes = ManagementsystemApplication.class)    //获取启动类，加载配置，寻找主配置启动类
@AutoConfigureMockMvc                                           //用于自动配置MockMvc,配置后MockMvc类可以直接注入,相当于new MockMvc
public class FixIntegrateTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FixController fixController;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    @Rollback()
    public void save() throws Exception {
        String json = "{……}";
        //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        mockMvc.perform(MockMvcRequestBuilders
                .post("/XXX/save")
                .content(json.getBytes()) //传json参数
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer ********-****-****-****-************")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void get() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/XXX/get")
                .param("id", "**********")
                .header("Authorization", "Bearer ********-****-****-****-************")
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void selectAllFixDetailsByWorkerId() throws Exception {

        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders
                .get("/fix/allFixDetailsByWorkerId")
                .param("fixWorkerId","user#2")
                        .contentType(MediaType.APPLICATION_JSON)
                );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void selectAllWorkersByDepartment() throws Exception {

        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders
                .get("/fix/selectAllWorkersByDepartment")
                .param("fixDepartmentId","FixDepart#1")
                        .contentType(MediaType.APPLICATION_JSON)
                );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void addFixRequest() throws Exception {

        String content = "{\"fixDetails\":\"Please come fix quickly\"," +
                "\"fixExpectTime\":\"2021-12-20 20:37:40\"," +
                "\"fixId\":\"20220601143057\"," +
                "\"fixPicture\":\"url\"," +
                "\"fixSubmitTime\":\"2021-12-19 20:37:40\"," +
                "\"fixType\":\"水电类\"," +
                "\"fixUserAddress\":\"浙江杭州\"," +
                "\"fixUserDoor\":\"二楼01\"," +
                "\"fixUserId\":\"user#020\"," +
                "\"fixUserName\":\"张三\"," +
                "\"fixUserPhone\":\"18938297827\"}";

        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/fix/addFixRequest")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void takeOrderByOrderIdAndWorkerId() throws Exception {

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("fixOrderId", "Order#20211215145058");
        multiValueMap.add("fixWorkerId", "User#2");

        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/fix/takeOrderByOrderIdAndWorkerId")
                        .params(multiValueMap)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }

    @Test
    public void forceCloseOrder() throws Exception {

        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/fix/forceCloseOrder")
                        .param("fixOrderId","Order#20211214201348")
                        .contentType(MediaType.APPLICATION_JSON)
                );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void forceAssignOrder() throws Exception {

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("fixOrderId", "Order#20211215145058");
        multiValueMap.add("fixWorkerId", "User#2");

        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/fix/forceAssignOrder")
                        .params(multiValueMap)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}
