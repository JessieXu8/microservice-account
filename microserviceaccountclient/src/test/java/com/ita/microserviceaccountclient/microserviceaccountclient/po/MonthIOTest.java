package com.ita.microserviceaccountclient.microserviceaccountclient.po;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MonthIOTest {
    @Test
    public void should_test_MonthIO() {
        //Given
        MonthIO monthIO = new MonthIO();
        //When
        monthIO.setBalance(222.2);
        monthIO.setIncome(222.2);
        monthIO.setOutlay(222.2);

        //Then
        Assert.assertEquals((Double) 222.2, monthIO.getBalance());
    }

    @Test
    public void should_test_getMethod() {
        //Given
        MonthIO monthIO = new MonthIO();

        //When

        //Then
        assertNull(monthIO.getBalance());
        assertNull(monthIO.getIncome());
        assertNull(monthIO.getOutlay());
    }
}
