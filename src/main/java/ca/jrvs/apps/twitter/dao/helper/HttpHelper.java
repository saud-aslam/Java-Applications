package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;

import java.net.URI;

public interface HttpHelper {

    HttpResponse httpPost(URI uri) throws Exception;
    HttpResponse httpGet(URI uri) throws Exception;
}