package com.ita.microserviceaccountclient.microserviceaccountclient.exceptions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BadRequestExceptionTest {
    @Test
    public void should_test_constructor() {
        //Given //When
        BadRequestException badRequestException = new BadRequestException();

        //Then
        Assert.assertNotNull(badRequestException);

    }

    @Test
    public void should_test_constructor_with_param() {
        //Given
        String message = "error";
        // When
        BadRequestException badRequestException = new BadRequestException(message);

        //Then
        Assert.assertNotNull(badRequestException);

    }
}