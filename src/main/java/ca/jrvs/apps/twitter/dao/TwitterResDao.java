package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static ca.jrvs.apps.twitter.example.JsonParser.toObjectFromJson;


public class TwitterResDao implements CrdRepo<Tweet, String> {

    public static final String POST_URL = "/1.1/statuses/update.json";
    public static final String SHOW_URL = "/1.1/statuses/show.json";
    public static final String DEL_URL = "/1.1/statuses/destroy";
    private static final String BASE_URL = "https://api.twitter.com";
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";


    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;


    public TwitterResDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }


    @Override
    public Tweet create(Tweet tweet) {
        System.out.println("enter");
        //Construct URI
        URI uri;
        try {
            uri = getPostUri(tweet);
            System.out.println("tryexit");
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid tweet input", e);
        }

        //Execute HTTP Request
        HttpResponse response = null;
        try {
            response = httpHelper.httpPost(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Validate response and deser response to Tweet object
        return parseResponseBody(response, HTTP_OK);
    }


    protected URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
        String text = tweet.getText();
        // Double longitude=20.1;
        //Double latitude=20.2;
        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append(POST_URL)
                .append(QUERY_SYM);

        appendQueryParam(sb, "status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()), true);
        appendQueryParam(sb, "long", longitude.toString(), false);
        appendQueryParam(sb, "lat", latitude.toString(), false);

        return new URI(sb.toString());
    }

    private void appendQueryParam(StringBuilder sb, String key, String value,
                                  boolean firstParam) {
        if (!firstParam) {
            sb.append(AMPERSAND);
        }
        sb.append(key)
                .append(EQUAL)
                .append(value);
    }


    /**
     * Check response status code Convert Response Entity to Tweet
     */
    protected Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
        Tweet tweet = null;

        //Check response status
        int status = response.getStatusLine().getStatusCode();
        if (status != expectedStatusCode) {
            throw new RuntimeException("Unexpected HTTP status:" + status);
        }

        if (response.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        }

        //Convert Response Entity to str
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity to String", e);
        }

        //Deser JSON string to Tweet object
        try {
            tweet = (Tweet) toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert JSON str to Object", e);
        }

        return tweet;
    }


    @Override
    public Tweet findbyId(String s) {
        return null;
    }

    @Override
    public Tweet deletebyId(String s) {
        return null;
    }
}
