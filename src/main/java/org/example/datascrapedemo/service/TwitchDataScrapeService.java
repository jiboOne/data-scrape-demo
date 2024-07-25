package org.example.datascrapedemo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.datascrapedemo.util.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class TwitchDataScrapeService implements DataScapeService {

    private final ApplicationContext context;

    @Override
    public String scrapeSocialLinks() {
        WebDriver driver = context.getBean(WebDriver.class);
        long startTime = System.currentTimeMillis();

        try (InputStream inputStream = TwitchDataScrapeService.class.getResourceAsStream("/twitch.streamers");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            processStreamers(reader, driver);

        } catch (IOException | InterruptedException e) {
            log.error("Exception: ", e);
        } finally {
            driver.quit();
        }

        LogHelper.printDuration(System.currentTimeMillis(), startTime);
        return "Data scraping has been completed successfully.";
    }

    private void processStreamers(BufferedReader reader, WebDriver driver) throws IOException, InterruptedException {
        String streamerName;
        int counter = 0;

        while ((streamerName = reader.readLine()) != null) {
            counter++;
            log.info(counter + " - Streamer: " + streamerName);
            String twitchAboutUrl = "https://www.twitch.tv/" + streamerName + "/about";
            driver.get(twitchAboutUrl);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[rel='noopener noreferrer']")));

            List<WebElement> content = driver.findElements(By.cssSelector("a[rel='noopener noreferrer']"));
            Set<String> socialLinks = extractSocialLinks(content);

            LogHelper.printSocialLinks(socialLinks);
        }
    }

    private Set<String> extractSocialLinks(List<WebElement> content) {
        Set<String> socialLinks = new HashSet<>();
        for (WebElement link : content) {
            String href = link.getAttribute("href");
            if (href.contains("linkedin.com") || href.contains("facebook.com") || href.contains("twitter.com") || href.contains("instagram.com") || href.contains("youtube.com") || href.contains("vimeo.com")) {
                socialLinks.add(href);
            }
        }
        return socialLinks;
    }
}
