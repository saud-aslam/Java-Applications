package ca.jrvs.apps.twitter.dao.helper;


import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;


public class ApacheHttpHelper implements HttpHelper {

    private HttpClient httpClient;
    org.apache.http.HttpResponse response;
    ss


    //Constructor
    public ApacheHttpHelper() {


        String CONSUMER_KEY = "VSeCzfp09iTHhJ98A6dF7M8Le";
        String CONSUMER_SECRET = "wn8Pc1LiIhHgMrppig2Ss1pUPymoWgcsVbSLD9VekacJPmkiSc";
        String ACCESS_TOKEN = "1146467744860295169-ww5eOZYcJDMc2vltKSigrCxueLJPwq";
        String TOKEN_SECRET = "H1yqIDLq5T4RuglvJqovRRupiKM8JSWl20Q6RwMLfxBad";


        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        httpClient = new DefaultHttpClient();
    }

   /* public static void main(String[] args) {
        ApacheHttpHelper apacheHttpHelper = new ApacheHttpHelper();

        try {
            String url = "https://api.twitter.com/1.1/statuses/update.json?status=helzlkkkkzlo2sxs";
            apacheHttpHelper.httpPost(url);
            String url1 = "https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump";
            apacheHttpHelper.httpGet(url1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/

    @Override
    public HttpResponse httpPost(URI uri) throws Exception {
        String CONSUMER_KEY = "VSeCzfp09iTHhJ98A6dF7M8Le";
        String CONSUMER_SECRET = "wn8Pc1LiIhHgMrppig2Ss1pUPymoWgcsVbSLD9VekacJPmkiSc";
        String ACCESS_TOKEN = "1146467744860295169-ww5eOZYcJDMc2vltKSigrCxueLJPwq";
        String TOKEN_SECRET = "H1yqIDLq5T4RuglvJqovRRupiKM8JSWl20Q6RwMLfxBad";
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);


        try {

            HttpPost post = new HttpPost(uri);

            consumer.sign(post);

            response = httpClient.execute(post);

            //response.getEntity().consumeContent();


        } finally {
            //Important: Close the connect\

        }

        return response;

    }

    //  @Override
    //   public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
    //  return null;
    // }

    @Override
    public HttpResponse httpGet(URI uri) throws Exception {

        String CONSUMER_KEY = "VSeCzfp09iTHhJ98A6dF7M8Le";
        String CONSUMER_SECRET = "wn8Pc1LiIhHgMrppig2Ss1pUPymoWgcsVbSLD9VekacJPmkiSc";
        String ACCESS_TOKEN = "1146467744860295169-ww5eOZYcJDMc2vltKSigrCxueLJPwq";
        String TOKEN_SECRET = "H1yqIDLq5T4RuglvJqovRRupiKM8JSWl20Q6RwMLfxBad";
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);


        try {
            // HttpPost post = new HttpPost(String.valueOf(uri));
            HttpGet get = new HttpGet(uri);


            consumer.sign(get);

            response = httpClient.execute(get);
            //response.getEntity().consumeContent();


        } finally {


        }
        return response;
    }
}
