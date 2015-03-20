package nl.compuplex.fobicapp.communication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import nl.compuplex.fobicapp.models.RegistrationContainer;


public class PushRestCommunication extends AbstractRestCommunication {
    //    private static final String SENDER_ID = "981927407915";
    private final String url = "http://phobicapp.compuplex.nl:10030";
    private GoogleCloudMessaging gcm;
    private RegistrationContainer registrationContainer;
    private Context appContext;

    public void registerInBackground(Context appContext) {
        this.appContext = appContext;
        registrationContainer = new RegistrationContainer(appContext);

        if (!registrationContainer.isRegisteredLocally()) {
            (new GcmRegister()).execute("981927407915");
        }

    }

    private void sendRegistrationIfNotRegisteredYet() {
        this.get(url + "/users/" + registrationContainer.getUsername() + "/associations/", new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                String jsonString;
                String match = "";
                try {
                    jsonString = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObj = new JSONObject(jsonString);
                    String assocString = jsonObj.get("associations").toString();
                    JSONArray assoc = new JSONArray(assocString);
                    for (int i = 0; i < assoc.length(); i++) {
                        String type = assoc.getJSONObject(i).get("type").toString();
                        if (type.equals("android")) {
                            String token = assoc.getJSONObject(i).get("token").toString();
                            if (token.equals(registrationContainer.getRegistrationId())) {
                                match = token;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (match.equals("")) {
                    sendRegistrationIdToBackend();
                } else {
                    System.out.println("Already registered on backend");
                }
            }
        }, new ProgressCallback() {
            @Override
            public void executeCallback(Integer progress) {

            }
        });
    }

    private void sendRegistrationIdToBackend() {
        // Add data for rest request
        JSONObject nameValuePairs = new JSONObject();
        try {
            nameValuePairs.put("user", registrationContainer.getUsername());
            nameValuePairs.put("type", "android");
            nameValuePairs.put("token", registrationContainer.getRegistrationId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.post(url + "/subscribe", nameValuePairs, new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    CharSequence text = "Registered!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(appContext, text, duration);
                    toast.show();
                }
            }
        }, new ProgressCallback() {
            @Override
            public void executeCallback(Integer progress) {

            }
        });
    }

    public void unregisterThisDevice() {
        JSONObject nameValuePairs = new JSONObject();
        try {
            nameValuePairs.put("token", registrationContainer.getRegistrationId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.post(url + "/unsubscribe", nameValuePairs, new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                //Check for statuscode 200?
            }
        }, new ProgressCallback() {
            @Override
            public void executeCallback(Integer progress) {

            }
        });
    }

    class GcmRegister extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(appContext);
            }
            String registrationId = "";
            try {
                registrationId = gcm.register(params[0]);
                registrationContainer.storeRegistrationId(registrationId);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendRegistrationIfNotRegisteredYet();
            return registrationId;
        }
    }
}
