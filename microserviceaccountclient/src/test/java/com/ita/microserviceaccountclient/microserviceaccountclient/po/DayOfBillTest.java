package com.ita.microserviceaccountclient.microserviceaccountclient.po;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DayOfBillTest {
    @Test
    public void should_test_getMethod() {
        //Given
        DayOfBill dayOfBill = new DayOfBill();

        //When

        //Then
        assertNull(dayOfBill.getOutlay());
        assertNull(dayOfBill.getDate());
        assertNull(dayOfBill.getIncome());
        assertThat(dayOfBill.getRecords().size(), is(0));
    }

    @Test
    public void should_test_setMethod() {
        //Given
        DayOfBill dayOfBill = new DayOfBill();
        //When
        dayOfBill.setOutlay(222.2);
        dayOfBill.setDate("date");
        dayOfBill.setIncome(12.2);
        dayOfBill.setRecords(Arrays.asList(new Record()));
        //Then
        Assert.assertEquals((Double) 222.2, dayOfBill.getOutlay());
    }

}