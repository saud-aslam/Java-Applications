package ca.jrvs.apps.twitter.dao.helper;


import com.sun.org.apache.xml.internal.utils.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class ApacheHttpHelper implements HttpHelper {

    private HttpClient httpClient;


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

    public static void main(String[] args) {
        ApacheHttpHelper apacheHttpHelper = new ApacheHttpHelper();

        try {
            String url = "https://api.twitter.com/1.1/statuses/update.json?status=helzlkkkkzlo2sxs";
            apacheHttpHelper.httpPost(url);
            String url1 = "https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump";
            apacheHttpHelper.httpGet(url1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public HttpResponse httpPost(String url) throws Exception {
        String CONSUMER_KEY = "VSeCzfp09iTHhJ98A6dF7M8Le";
        String CONSUMER_SECRET = "wn8Pc1LiIhHgMrppig2Ss1pUPymoWgcsVbSLD9VekacJPmkiSc";
        String ACCESS_TOKEN = "1146467744860295169-ww5eOZYcJDMc2vltKSigrCxueLJPwq";
        String TOKEN_SECRET = "H1yqIDLq5T4RuglvJqovRRupiKM8JSWl20Q6RwMLfxBad";
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);


        try {
            // HttpPost post = new HttpPost(String.valueOf(uri));
            HttpPost post = new HttpPost(url);

            consumer.sign(post);

            org.apache.http.HttpResponse response = httpClient.execute(post);
            response.getEntity().consumeContent();


        } finally {
            //Important: Close the connect\

        }

        return null;

    }

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        return null;
    }

    @Override
    public HttpResponse httpGet(String url) throws Exception {

        String CONSUMER_KEY = "VSeCzfp09iTHhJ98A6dF7M8Le";
        String CONSUMER_SECRET = "wn8Pc1LiIhHgMrppig2Ss1pUPymoWgcsVbSLD9VekacJPmkiSc";
        String ACCESS_TOKEN = "1146467744860295169-ww5eOZYcJDMc2vltKSigrCxueLJPwq";
        String TOKEN_SECRET = "H1yqIDLq5T4RuglvJqovRRupiKM8JSWl20Q6RwMLfxBad";
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);


        try {
            // HttpPost post = new HttpPost(String.valueOf(uri));
            HttpGet get = new HttpGet(url);


            consumer.sign(get);

            org.apache.http.HttpResponse response = httpClient.execute(get);
            System.out.println(EntityUtils.toString(response.getEntity()));
            // System.out.println(response.getStatusLine().getStatusCode());
            response.getEntity().consumeContent();


        } finally {


        }
        return null;
    }
}
