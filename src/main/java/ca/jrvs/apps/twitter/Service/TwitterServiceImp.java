package ca.jrvs.apps.twitter.Service;

import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;

import static ca.jrvs.apps.twitter.example.JsonParser.toJson;


public class TwitterServiceImp implements TwitterService {


    private CrdRepo dao;
    private static final int MAX_LEN_TWEET = 200;

    public TwitterServiceImp(CrdRepo dao) {
        this.dao = dao;
    }


    @Override
    public void postTweet(String text, Double latitude, Double longitude) {
        Tweet postTweet = joinTweet(text, latitude, longitude);


        try {
            Tweet resp = (Tweet) dao.create(postTweet);
        } catch (Exception e) {
            System.out.println("Can not post your tweet");
        }


    }

    @Override
    public void showTweet(String id, String[] fields) {

    }

    @Override
    public void deleteTweets(String[] ids) {

    }


    public Tweet joinTweet(String text, Double lat, Double longi) {

        if (text.length() > MAX_LEN_TWEET || lat > 180.0 || longi > 180.0 || lat < 0 || longi < 0) {
            throw new IllegalArgumentException();
        }

        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates cordn = new Coordinates();
        cordn.setCoordinates(Arrays.asList(lat, longi));
        tweet.setCoordinates(cordn);


        try {
            System.out.println(toJson(tweet));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return tweet;
    }
    }

