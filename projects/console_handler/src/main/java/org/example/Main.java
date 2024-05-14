package org.example;



import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started");

        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }

        logger.info("Application ended");
    }
}