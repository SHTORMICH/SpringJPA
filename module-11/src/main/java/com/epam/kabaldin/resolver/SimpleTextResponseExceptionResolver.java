package com.epam.kabaldin.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleTextResponseExceptionResolver implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTextResponseExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String errorMessage = "Error occurred: " + ex.getMessage();

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().write(errorMessage);
        } catch (IOException e) {
            logger.warn("Unable to send response to client. Response not sent for client due to IOException. Check logs for more details!");
        }

        return new ModelAndView();
    }
}
