package com.ita.microservicetypeclient.microservicetypeclient.services;

import com.ita.microservicetypeclient.microservicetypeclient.entities.Type;
import com.ita.microservicetypeclient.microservicetypeclient.repositories.TypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeServiceTest {
    @Autowired
    private TypeService typeService;

    @MockBean
    private TypeRepository typeRepository;

    @Test
    public void should_get_types_when_call_getAllTypes_with_blank() {
        //given
        String accountKind = "";
        List<Type> types = new ArrayList<>();
        when(typeRepository.findAll()).thenReturn(types);
        //when
        List<Type> result = typeService.getAllTypes(accountKind);
        //then
        assertEquals(types, result);
    }

    @Test
    public void shoud_get_types_when_call_getAllTypes_with_income() {
        //given
        String accountKind = "支出";
        List<Type> types = new ArrayList<>();
        Type type =mock(Type.class);
        when(type.getId()).thenReturn(1);
        types.add(type);
        when(typeRepository.findAllByAccountKind(accountKind)).thenReturn(types);
        //when
        List<Type> result = typeService.getAllTypes(accountKind);
        //then
        assertEquals((Integer) 1, result.get(0).getId());
    }

    @Test
    public void should_success_when_call_addType_given_type_is_null() {
        //given
        Type type=new Type();

        //when
        typeService.addType(type);

        //then
        verify(typeRepository, times(0)).save(type);
    }

    @Test
    public void should_save_when_call_addType_given_type() {
        //given
        Type type=new Type();
        type.setType("type");
        //when

        typeService.addType(type);

        //then
        verify(typeRepository, times(1)).save(type);
    }
}