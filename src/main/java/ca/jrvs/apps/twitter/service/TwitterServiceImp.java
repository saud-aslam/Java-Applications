package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static ca.jrvs.apps.twitter.example.JsonParser.toJson;

@Component
public class TwitterServiceImp implements TwitterService {

    private static final int MAX_LEN_TWEET = 200;
    private static final double MAX_LAT = 90.0;
    private static final double MIN_LAT = -90.0;
    private static final double MAX_LONGI = 180.0;
    private static final double MIN_LONGI = -180.0;
    private CrdRepo dao;

    @Autowired
    public TwitterServiceImp(CrdRepo dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {
        Tweet postTweet = joinTweet(text, latitude, longitude);

        try {
            Tweet resp = (Tweet) dao.create(postTweet);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return postTweet;

    }

    @Override
    public void showTweet(String id, String[] fields) {

        if (!idValidation(id)) {
            throw new IllegalArgumentException("ID should be all numbers");
        }
        try {
            Tweet resp = (Tweet) dao.findbyId(id);
            System.out.println(toJson(resp));
        } catch (Exception e) {
            System.out.println("Can not show your tweet");
        }

    }

    @Override
    public void deleteTweets(String[] id) {
        for (String i : id) {
            if (!idValidation(i)) {
                throw new IllegalArgumentException("ID should be all numbers");
            }
            try {
                Tweet resp = (Tweet) dao.deletebyId(i);
                System.out.println(toJson(resp));
            } catch (Exception e) {
                System.out.println("Can not delete all your tweet");
            }
        }

    }

    public Tweet joinTweet(String text, Double lat, Double longi) {

        if (text.toCharArray().length > MAX_LEN_TWEET) {
            throw new IllegalArgumentException("Maximum length of Tweet allowed is 200: You have posted: " + text.length());
        }

        if (lat > MAX_LAT || longi > MAX_LONGI || lat < MIN_LAT || longi < MIN_LONGI) {
            throw new IllegalArgumentException("Geo Location is Invalid:ALlowed 90|-90|180|-180. You posted:" + lat + "|" + longi);
        }

        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates cordn = new Coordinates();
        cordn.setCoordinates(Arrays.asList(lat, longi));
        cordn.setType("Point");
        tweet.setCoordinates(cordn);

        try {
            System.out.println(toJson(tweet));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    public boolean idValidation(String s) {
        return s != null && s.chars().noneMatch(character -> character < '0' || character > '9');
    }
}

