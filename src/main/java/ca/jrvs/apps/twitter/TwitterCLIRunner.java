package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.Service.TwitterService;
import ca.jrvs.apps.twitter.dao.CrdRepo;

public class TwitterCLIRunner {


    private static final String sep = ":";
    public static CrdRepo dao;
    private TwitterService service;

    public TwitterCLIRunner(CrdRepo dao) {
        this.dao = dao;
    }

    public TwitterCLIRunner(TwitterService service) {
        this.service = service;
    }


    public void run(String[] args) {
        if (args[0] == "post") {

            parseTweet(args);
        } else if (args[0] == "delete") {
            deleteTweet(args);
        } else if (args[0] == "show") {
            showTheTweet(args);
        } else {
            throw new IllegalArgumentException();
        }

    }


    protected void parseTweet(String[] args) {
        if (args.length != 3) {
            throw new RuntimeException("USAGE ... post|text|lat:lon");
        }

        String text = args[1];
        String[] coord = args[2].split(":");

        double lat = Double.parseDouble(coord[0]);
        double longi = Double.parseDouble(coord[1]);

        try {
            service.postTweet(text, lat, longi);
        } catch (Exception e) {
            System.out.println("Can not post your tweet ");
        }


    }

}
