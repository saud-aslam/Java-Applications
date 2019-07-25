package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dto.Tweet;

public interface TwitterService {
    Tweet postTweet(String text, Double latitude,
                    Double longitude);

    void showTweet(String id, String[] fields);

    void deleteTweets(String[] ids);
}
