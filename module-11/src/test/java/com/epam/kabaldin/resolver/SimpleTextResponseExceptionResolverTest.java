package com.epam.kabaldin.resolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTextResponseExceptionResolverTest {

    private SimpleTextResponseExceptionResolver resolver;

    @BeforeEach
    public void setUp() {
        resolver = new SimpleTextResponseExceptionResolver();
    }

    @Test
    public void testResolveException() throws UnsupportedEncodingException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Exception exception = new RuntimeException("Test exception");

        ModelAndView modelAndView = resolver.resolveException(request, response, null, exception);

        assertEquals("text/plain;charset=UTF-8", response.getContentType());
        assertEquals(200, response.getStatus());

        String errorMessage = "Error occurred: " + exception.getMessage();
        assertEquals(errorMessage, response.getContentAsString());


    }
}