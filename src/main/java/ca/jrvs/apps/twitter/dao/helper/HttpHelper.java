package ca.jrvs.apps.twitter.dao.helper;


import com.sun.org.apache.xml.internal.utils.URI;
import oauth.signpost.http.HttpResponse;
import org.apache.http.entity.StringEntity;

public interface HttpHelper {
    HttpResponse httpPost(String url) throws Exception;

    HttpResponse httpPost(URI uri, StringEntity
            stringEntity);

    HttpResponse httpGet(String url) throws Exception;
}