package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.After;
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
    private Tweet showTweet;
    private String id;


    @Before
    public void setup() {
        //setup actualTweet i.e createTweet
        String tweetStr = "This is tweet" + System.currentTimeMillis();
        this.actualTweet = new Tweet();
        actualTweet.setText(tweetStr);
        Coordinates coordinates = new Coordinates();
        coordinates.setType("Point");
        coordinates.setCoordinates(Arrays.asList(10.0, 20.0));
        actualTweet.setCoordinates(coordinates);
        //Setup showTweet
        this.showTweet = new Tweet();
        //setup dao
        HttpHelper httpHelper = new ApacheHttpHelper();
        this.dao = new TwitterResDao(httpHelper);

    }


    @After
    public void cleanup() {
        System.out.println("Deleting " + this.id);
        //remove tweet
        dao.deletebyId(this.id);
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
        assertStatement(actualTweet, expectedTweet);

        //getting id of the expectedTweet
        this.id = expectedTweet.getIdStr();

        this.showTweet = (Tweet) dao.findbyId(this.id);

        //Validating ShowTweet
        assertStatement(actualTweet, showTweet);


    }

    public void assertStatement(Tweet actual, Tweet expected) {
        assertEquals(actual.getText(), expected.getText());
        assertEquals(actual.getCoordinates(), expected.getCoordinates());
        assertNotNull(expected);

    }


}