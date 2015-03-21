package nl.compuplex.fobicapp.communication;

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
                        String name = fobias.getJSONObject(i).get("name").toString();
                        fobiaList.add(new Fobia(name));
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

    public void postPhobia() {

    }
}
