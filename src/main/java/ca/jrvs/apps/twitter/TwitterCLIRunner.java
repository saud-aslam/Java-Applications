package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.Service.TwitterService;
import ca.jrvs.apps.twitter.dao.CrdRepo;

public class TwitterCLIRunner {


    public static CrdRepo dao;
    private TwitterService service;

    public TwitterCLIRunner(CrdRepo dao) {
        this.dao = dao;
    }

    public TwitterCLIRunner(TwitterService service) {
        this.service = service;
    }


    public void run(String[] args) {
        if (args[0].compareTo("post") == 0) {

            parseTweet(args);
        } else if (args[0].compareTo("delete") == 0) {
            deleteTweet(args);
        } else if (args[0].compareTo("show") == 0) {
            showTheTweet(args);
        } else {
            System.out.println("Usage...post|show|delete");
        }

    }


    protected void parseTweet(String[] args) {
        if (args.length != 3) {
            throw new RuntimeException("USAGE ... post|text|lat:lon");
        }

        String text = args[1];
        String[] coord = args[2].split(":");

        double lati = Double.parseDouble(coord[0]);
        double longi = Double.parseDouble(coord[1]);

        try {
            service.postTweet(text, lati, longi);
        } catch (Exception e) {
            System.out.println("Can not post your tweet ");
        }
    }


    protected void showTheTweet(String[] args) {
        String id = null;
        if (args.length < 2) {
            throw new RuntimeException("USAGE ... show|id");
        }
        id = args[1];

        service.showTweet(id, null);
    }


    protected void deleteTweet(String[] args) {
        String[] id = null;
        if (args.length < 2) {
            throw new RuntimeException("USAGE ... delete|id");
        }
        id = args[1].split(",");
        service.deleteTweets(id);

    }

}
