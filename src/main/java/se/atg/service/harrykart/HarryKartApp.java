package se.atg.service.harrykart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HarryKartApp {
    private static final Logger logger = LoggerFactory.getLogger(HarryKartApp.class);
    public static void main(String ... args) {
        logger.info("Application Started");
        SpringApplication.run(HarryKartApp.class, args);
    }
}
