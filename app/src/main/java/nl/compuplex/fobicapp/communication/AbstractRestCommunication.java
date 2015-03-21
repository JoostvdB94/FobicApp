package nl.compuplex.fobicapp.communication;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.IOException;

abstract class AbstractRestCommunication {
    void get(String url, final ResponseCallback callbackFinished, final ProgressCallback callbackProgress) {
        final HttpClient httpclient = new DefaultHttpClient();
        final HttpGet httpget = new HttpGet(url);

        (new AsyncTask<String, Integer, HttpResponse>() {
            @Override
            protected HttpResponse doInBackground(String... params) {
                HttpResponse response = null;
                try {
                    response = httpclient.execute(httpget);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                callbackProgress.executeCallback(values[0]);
            }

            @Override
            protected void onPostExecute(HttpResponse httpResponse) {
                super.onPostExecute(httpResponse);
                callbackFinished.executeCallback(httpResponse);
            }
        }).execute();
    }

    AsyncTask<JSONObject, Double, HttpResponse> post(String url, JSONObject data, final ResponseCallback callbackFinished, final ProgressCallback callbackProgress) {
        final HttpClient httpclient = new DefaultHttpClient();
        final HttpPost httppost = new HttpPost(url);

        return new AsyncTask<JSONObject, Double, HttpResponse>() {
            @Override
            protected HttpResponse doInBackground(JSONObject... params) {
                HttpResponse response = null;
                try {
                    String entity = "";
                    for (JSONObject param : params) {
                        entity += param.toString();
                    }
                    StringEntity se = new StringEntity(entity);
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    httppost.setEntity(se);

                    // Execute HTTP Post Request
                    httppost.addHeader("Content-Type", "application/json");

                    response = httpclient.execute(httppost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(HttpResponse httpResponse) {
                super.onPostExecute(httpResponse);
                callbackFinished.executeCallback(httpResponse);
            }
        }.execute(data);
    }

    AsyncTask<JSONObject, Double, HttpResponse> put(String url, JSONObject data, final ResponseCallback callbackFinished, final ProgressCallback callbackProgress) {
        final HttpClient httpclient = new DefaultHttpClient();
        final HttpPut httpput = new HttpPut(url);

        return new AsyncTask<JSONObject, Double, HttpResponse>() {
            @Override
            protected HttpResponse doInBackground(JSONObject... params) {
                HttpResponse response = null;
                try {
                    String entity = "";
                    for (JSONObject param : params) {
                        entity += param.toString();
                    }
                    StringEntity se = new StringEntity(entity);
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    httpput.setEntity(se);

                    // Execute HTTP Post Request
                    httpput.addHeader("Content-Type", "application/json");

                    response = httpclient.execute(httpput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(HttpResponse httpResponse) {
                super.onPostExecute(httpResponse);
                callbackFinished.executeCallback(httpResponse);
            }
        }.execute(data);
    }

    void delete(String url, final ResponseCallback callbackFinished, final ProgressCallback callbackProgress) {
        final HttpClient httpclient = new DefaultHttpClient();
        final HttpDelete httpdelete = new HttpDelete(url);

        (new AsyncTask<String, Integer, HttpResponse>() {
            @Override
            protected HttpResponse doInBackground(String... params) {
                HttpResponse response = null;
                try {
                    response = httpclient.execute(httpdelete);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                callbackProgress.executeCallback(values[0]);
            }

            @Override
            protected void onPostExecute(HttpResponse httpResponse) {
                super.onPostExecute(httpResponse);
                callbackFinished.executeCallback(httpResponse);
            }
        }).execute();
    }
}
