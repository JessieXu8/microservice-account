package com.ita.microservicetypeclient.microservicetypeclient.controllers;

import com.ita.microservicetypeclient.microservicetypeclient.entities.Type;
import com.ita.microservicetypeclient.microservicetypeclient.services.TypeService;
import net.minidev.json.JSONObject;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TypeController.class)
public class TypeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeService typeService;
    @Test
    public void should_get_AllIncomeTypes_when_call_getAllIncomeType() throws Exception {
        //given
        String type="income";
        when(typeService.getAllTypes(type)).thenReturn(null);
        //when
        //then
        mockMvc.perform(get("/" + type).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_AllOutlayTypes_when_call_getAllOutlayTypes() throws Exception {
        //given
        String type="outlay";
        when(typeService.getAllTypes(type)).thenReturn(null);
        //when
        //then
        mockMvc.perform(get("/" + type).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_addType_when_call_addType() throws Exception {
        //given
        Type type=new Type();
        Map map = Maps.newHashMap("",type);
        String requestJson = JSONObject.toJSONString(map);
        doNothing().when(typeService).addType(type);

        //when
        //then
        mockMvc.perform(post("").content(JSONObject.toJSONString(map)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_AllTypes_when_call_getAllTypes() throws Exception {
        //given
        String type = "";
        when(typeService.getAllTypes(type)).thenReturn(null);
        //when
        //then
        mockMvc.perform(get("").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }
}