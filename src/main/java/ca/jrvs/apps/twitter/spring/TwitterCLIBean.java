package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIRunner;
import ca.jrvs.apps.twitter.dao.CrdRepo;
import ca.jrvs.apps.twitter.dao.TwitterResDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//@Configuration
public class TwitterCLIBean {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIRunner runner = context.getBean(TwitterCLIRunner.class);
        runner.run(args);
    }

    @Bean
    public TwitterCLIRunner twitterCLIRunner(TwitterService service) {
        return new TwitterCLIRunner(service);
    }

    @Bean
    public TwitterService twitterService(CrdRepo dao) {
        return new TwitterServiceImp(dao);
    }

    @Bean
    public CrdRepo twitterDao(HttpHelper httpHelper) {
        return new TwitterResDao(httpHelper);
    }

    @Bean
    HttpHelper helper() {
        return new ApacheHttpHelper();
    }

}
