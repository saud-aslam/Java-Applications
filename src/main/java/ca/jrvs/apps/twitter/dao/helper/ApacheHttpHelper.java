package ca.jrvs.apps.twitter.dao.helper;


import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class ApacheHttpHelper implements HttpHelper {

    private HttpClient httpClient;
    private OAuthConsumer consumer;



    //Constructor
    public ApacheHttpHelper() {


        String CONSUMER_KEY = "VSeCzfp09iTHhJ98A6dF7M8Le";
        String CONSUMER_SECRET = "wn8Pc1LiIhHgMrppig2Ss1pUPymoWgcsVbSLD9VekacJPmkiSc";
        String ACCESS_TOKEN = "1146467744860295169-ww5eOZYcJDMc2vltKSigrCxueLJPwq";
        String TOKEN_SECRET = "H1yqIDLq5T4RuglvJqovRRupiKM8JSWl20Q6RwMLfxBad";


        consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        httpClient = new DefaultHttpClient();
    }


    @Override
    public HttpResponse httpPost(URI uri) throws Exception {



        try {

            HttpPost post = new HttpPost(uri);

            consumer.sign(post);

            return httpClient.execute(post);


        } catch (IOException | OAuthException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }


    @Override
    public HttpResponse httpGet(URI uri) throws Exception {


        try {

            HttpGet get = new HttpGet(uri);
            consumer.sign(get);
            return httpClient.execute(get);


        } catch (IOException | OAuthException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
