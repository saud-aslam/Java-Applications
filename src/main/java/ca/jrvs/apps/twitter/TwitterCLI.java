package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.Service.TwitterService;
import ca.jrvs.apps.twitter.Service.TwitterServiceImp;
import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dao.TwitterResDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;

public class TwitterCLI {

    public static void main(String[] argssss) {
        String[] args = {"post", "My name is Saud Aslam", "20:21"};
        HttpHelper httpHelper = new ApacheHttpHelper();
        CrdRepo dao = new TwitterResDao(httpHelper);
        TwitterService service = new TwitterServiceImp(dao);


        TwitterCLIRunner runner = new TwitterCLIRunner(service);

        runner.run(args);

    }


}
