package nl.compuplex.fobicapp.communication;

import org.apache.http.HttpResponse;

/**
 * Created by Joost on 17-3-2015.
 */
public interface ResponseCallback {
    void executeCallback(String response);
}
