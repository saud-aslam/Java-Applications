package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dao.TwitterResDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCLI {

    public static void main(String[] args) {

        HttpHelper httpHelper = new ApacheHttpHelper();
        CrdRepo dao = new TwitterResDao(httpHelper);
        TwitterService service = new TwitterServiceImp(dao);
        TwitterCLIRunner runner = new TwitterCLIRunner(service);
        runner.run(args);

    }

}
