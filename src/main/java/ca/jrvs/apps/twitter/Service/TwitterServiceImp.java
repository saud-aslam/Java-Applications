package ca.jrvs.apps.twitter.Service;

import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;

import static ca.jrvs.apps.twitter.example.JsonParser.toJson;


public class TwitterServiceImp implements TwitterService {


    private static final int MAX_LEN_TWEET = 200;
    private CrdRepo dao;

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
            try {
                Tweet resp = (Tweet) dao.deletebyId(i);
                System.out.println(toJson(resp));
            } catch (Exception e) {
                System.out.println("Can not delete all your tweet");
            }
        }

    }


    public Tweet joinTweet(String text, Double lat, Double longi) {

        if (text.length() > MAX_LEN_TWEET || lat > 90.0 || longi > 180.0 || lat < -90.0 || longi < -180.0) {
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


    public boolean idValidation(String id) {


    }
}

