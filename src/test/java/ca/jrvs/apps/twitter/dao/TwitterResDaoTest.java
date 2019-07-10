package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static ca.jrvs.apps.twitter.example.JsonParser.toJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterResDaoTest {
    private CrdRepo dao;
    private Tweet actualTweet;
    private Tweet expectedTweet;
    private String id;


    @Before
    public void setup() {
        String tweetStr = "This is tweet" + System.currentTimeMillis();
        this.actualTweet = new Tweet();
        actualTweet.setText(tweetStr);
        Coordinates coordinates = new Coordinates();
        coordinates.setType("Point");
        coordinates.setCoordinates(Arrays.asList(10.0, 20.0));
        actualTweet.setCoordinates(coordinates);

        HttpHelper httpHelper = new ApacheHttpHelper();
        this.dao = new TwitterResDao(httpHelper);

    }

    @Test
    public void createAndshow() {


        this.expectedTweet = (Tweet) dao.create(actualTweet);
        try {
            System.out.println(toJson(actualTweet));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Validate tweet Object
        assertNotNull(expectedTweet);
        assertEquals(expectedTweet.getText(), actualTweet.getText());
        assertEquals(expectedTweet.getCoordinates(), actualTweet.getCoordinates());
        //Testing ShowTweet
        this.id = expectedTweet.getIdStr();
        Tweet showTweet = null;
        showTweet = (Tweet) dao.findbyId(this.id);

        assertEquals(showTweet.getText(), actualTweet.getText());
        assertEquals(actualTweet.getText(), showTweet.getText());


    }

    
}