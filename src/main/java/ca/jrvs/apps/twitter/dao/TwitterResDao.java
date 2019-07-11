package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static ca.jrvs.apps.twitter.example.JsonParser.toObjectFromJson;

@Repository
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

    @Autowired
    public TwitterResDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) {

        URI uri;
        try {
            uri = getPostUri(tweet);

        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid tweet input", e);
        }

        HttpResponse response = null;
        try {
            response = httpHelper.httpPost(uri);
            // System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parseResponseBody(response, HTTP_OK);
    }

    protected URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
        String text = tweet.getText();
        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL).append(POST_URL).append(QUERY_SYM);
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
        sb.append(key).append(EQUAL).append(value);
    }

    /**
     * Check response status code Convert Response Entity to Tweet
     */
    protected Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {

        Tweet tweet = null;

        //Check response status
        int status = response.getStatusLine().getStatusCode();
        System.out.println(status);

        if (status != expectedStatusCode && status != 403) {
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Unexpected HTTP status:" + status);
        }

        if (response.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        }

        //Convert Response Entity to str
        String jsonStr;
        try {

            jsonStr = EntityUtils.toString(response.getEntity());
            //System.out.println(jsonStr);

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
    public Tweet findbyId(String id) {
        //Construct URI
        URI uri;
        try {
            uri = getShowUri(id);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to construct URI", e);
        }

        //Execute HTTP Request
        HttpResponse response = null;
        try {
            response = httpHelper.httpGet(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Validate response and deser response to Tweet object
        return parseResponseBody(response, HTTP_OK);
    }

    @Override
    public Tweet deletebyId(String id) {
        //Construct URI
        URI uri;
        try {
            uri = getDeleteUri(id);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to construct URI", e);
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

    /**
     * e.g. https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json
     */
    protected URI getDeleteUri(String id) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append(DEL_URL)
                .append("/")
                .append(id)
                .append(".json");

        return new URI(sb.toString());
    }

    /**
     * Construct a twitter SHOW URI https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672
     *
     * @throws URISyntaxException when URI is invalid
     */
    protected URI getShowUri(String id) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL)
                .append(SHOW_URL)
                .append(QUERY_SYM);
        appendQueryParam(sb, "id", id, true);
        return new URI(sb.toString());
    }
}


