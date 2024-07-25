package org.example.datascrapedemo.job;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.datascrapedemo.service.TwitchDataScrapeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class TwitchDataScrapeScheduler {
    private TwitchDataScrapeService twitchDataScrapeService;


    @Scheduled(fixedDelay = 10000)
    public void scrapeSocialLinksJob() {
        log.info("The Twitch social links scraping job has begun.");
        twitchDataScrapeService.scrapeSocialLinks();
    }
}
