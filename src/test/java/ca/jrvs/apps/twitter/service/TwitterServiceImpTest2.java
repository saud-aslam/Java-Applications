package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceImpTest2 {

    @Mock
    private CrdRepo mockDao;

    @InjectMocks
    private TwitterServiceImp service;

    @Test
    public void postTweet() {

        Tweet mockTweet = new Tweet();
        mockTweet.setText("Mock Tweet");
        when(mockDao.create(any())).thenReturn(mockTweet);
        service.postTweet("Random Tweet", 0.0, 0.0);

        try {
            service.postTweet("Another Tweet", 20.0, 440.0);
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}