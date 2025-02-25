package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class Logging {
    private static final Logger LOG = LoggerFactory.getLogger(Logging.class);

    @Before("execution (* com.example.demo.*.*.*(..))") 
    public void logBefore(JoinPoint jp) {
        Logging.LOG.info("Intercepted method call of " + jp.getSignature());
    }

    @After("execution (* com.example.demo.*.*.*(..))") 
    public void logAfter(JoinPoint jp) {
        Logging.LOG.info("Logging after method call of " + jp.getSignature());
    }
}