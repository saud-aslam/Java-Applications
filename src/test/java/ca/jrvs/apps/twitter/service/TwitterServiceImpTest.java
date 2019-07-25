package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TwitterServiceImpTest {

    @Test
    public void postTweet() {
        CrdRepo Mockdao = Mockito.mock(CrdRepo.class);
        TwitterService service = new TwitterServiceImp(Mockdao);
        Tweet mockTweet = new Tweet();
        mockTweet.setText("Mock Tweet");
        when(Mockdao.create(any())).thenReturn(mockTweet);
        service.postTweet("Random Tweet", 0.0, 0.0);

        try {
            service.postTweet("Another Tweet", 20.0, 440.0);
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}