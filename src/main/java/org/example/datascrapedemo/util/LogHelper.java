package org.example.datascrapedemo.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@UtilityClass
@Slf4j
public class LogHelper {

    public static void printSocialLinks(Set<String> socialLinks) {
        log.info("Social Links:");
        socialLinks.forEach(log::info);
    }

    public static void printDuration(long endTime, long startTime) {
        double executionTime = (endTime - startTime) / 1000.0;
        log.debug("Execution time: " + executionTime + " seconds");
    }
}
