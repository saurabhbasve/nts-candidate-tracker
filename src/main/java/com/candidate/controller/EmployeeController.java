package com.candidate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public void method1() {
        logger.info("Entering method1");
        // Method implementation
        logger.info("Exiting method1");
    }

    public void method2() {
        logger.info("Entering method2");
        // Method implementation
        logger.info("Exiting method2");
    }

    // Add loggers to all other methods in a similar manner
}
