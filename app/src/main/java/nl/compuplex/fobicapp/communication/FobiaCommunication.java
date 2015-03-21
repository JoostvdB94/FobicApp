package nl.compuplex.fobicapp.communication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import nl.compuplex.fobicapp.Model.Fobia;
import nl.compuplex.fobicapp.Model.FobiaListAdapter;
import nl.compuplex.fobicapp.Views.FobiasFragment;
import nl.compuplex.fobicapp.Views.MainActivity;

/**
 * Created by Thomas on 21-3-2015.
 */
public class FobiaCommunication extends AbstractRestCommunication {
    private final String url = "http://phobicapp.compuplex.nl/api/phobia/";

    public void loadPhobias(final ListView phobiaList, final Context context) {
        this.get(url, new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                String jsonString;
                String match = "";
                try {
                    jsonString = EntityUtils.toString(response.getEntity());
                    JSONArray fobias = new JSONArray(jsonString);
                    ArrayList<Fobia> fobiaList = new ArrayList<Fobia>();
                    for (int i = 0; i < fobias.length(); i++) {
                        JSONObject fobiaJson = fobias.getJSONObject(i);

                        String id = fobiaJson.get("_id").toString();
                        String name = fobiaJson.get("name").toString();
                        fobiaList.add(new Fobia(id, name));
                    }
                    FobiaListAdapter adapter = new FobiaListAdapter(context, fobiaList);
                    phobiaList.setAdapter(adapter);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new ProgressCallback() {
            @Override
            public void executeCallback(Integer progress) {

            }
        });
    }

    public void postPhobia(String name, final MainActivity activity) { //, final FobiasFragment fragment) {
        JSONObject nameValuePairs = new JSONObject();
        try {
            nameValuePairs.put("name", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.post(url, nameValuePairs, new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    //fragment.loadPhobias();
                    activity.refreshFobias();
                }
            }
        }, new ProgressCallback() {
            @Override
            public void executeCallback(Integer progress) {

            }
        });
    }

    public void deletePhobia(String fobiaID, final MainActivity activity) {
        this.delete(url + fobiaID, new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                activity.refreshFobias();
            }
        }, new ProgressCallback() {
            @Override
            public void executeCallback(Integer progress) {

            }
        });
    }

    public void putPhobia(Fobia fobia, final MainActivity activity) { //, final FobiasFragment fragment) {
        JSONObject nameValuePairs = new JSONObject();
        try {
            nameValuePairs.put("name", fobia.mTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.put(url + fobia._ID, nameValuePairs, new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    activity.refreshFobias();
                }
            }
        }, new ProgressCallback() {
            @Override
            public void executeCallback(Integer progress) {

            }
        });
    }
}
