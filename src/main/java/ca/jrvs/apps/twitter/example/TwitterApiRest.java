package ca.jrvs.apps.twitter.example;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;

public class TwitterApiRest {
    private static String CONSUMER_KEY = "VSeCzfp09iTHhJ98A6dF7M8Le";
    private static String CONSUMER_SECRET = "wn8Pc1LiIhHgMrppig2Ss1pUPymoWgcsVbSLD9VekacJPmkiSc";
    private static String ACCESS_TOKEN = "1146467744860295169-ww5eOZYcJDMc2vltKSigrCxueLJPwq";
    private static String TOKEN_SECRET = "H1yqIDLq5T4RuglvJqovRRupiKM8JSWl20Q6RwMLfxBad";

    public static void main(String[] args) throws
            Exception {
//setup oauth
        OAuthConsumer consumer = new
                CommonsHttpOAuthConsumer(CONSUMER_KEY,
                CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN,
                TOKEN_SECRET);
// create an HTTP GET request
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump");


        // sign the request (add headers)
        consumer.sign(request);
        System.out.println("Http Request Headers:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);
// send/execute the request
        HttpClient httpClient = new
                DefaultHttpClient();
        HttpResponse response =
                httpClient.execute(request);
        System.out.println("Http Request UTIL");
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
