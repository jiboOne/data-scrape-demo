package org.example.datascrapedemo.controller;

import lombok.AllArgsConstructor;
import org.example.datascrapedemo.service.TwitchDataScrapeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/twitch")
@AllArgsConstructor
public class TwitchDataScrapeController {

    private TwitchDataScrapeService twitchDataScrapeService;

    @PostMapping
    public ResponseEntity<String> scrapeData() {
        String response = twitchDataScrapeService.scrapeSocialLinks();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
