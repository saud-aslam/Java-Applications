package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.Arrays;

import static ca.jrvs.apps.twitter.example.JsonParser.toJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterResDaoTest {

    @Test
    public void create() {
        String tweetStr = "This is atest tweet";
        Tweet expectedtweet = new Tweet();
        expectedtweet.setText(tweetStr);
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(10.0, 20.0));
        expectedtweet.setCoordinates(coordinates);
        try {
            System.out.println(toJson(expectedtweet));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpHelper httpHelper = new ApacheHttpHelper();
        TwitterResDao dao = new TwitterResDao(httpHelper);
        Tweet actualTweet = dao.create(expectedtweet);


        //Validate tweet Object

        assertNotNull(actualTweet);
        assertEquals(actualTweet.getText(), expectedtweet.getText());
        assertEquals(actualTweet.getCoordinates(), expectedtweet.getCoordinates());


    }

    @Test
    public void findbyId() {
    }

    @Test
    public void deletebyId() {
    }
}