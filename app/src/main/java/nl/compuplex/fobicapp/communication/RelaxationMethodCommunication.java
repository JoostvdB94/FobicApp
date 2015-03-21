package nl.compuplex.fobicapp.communication;

import android.content.Context;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import nl.compuplex.fobicapp.Model.RelaxationMethod;
import nl.compuplex.fobicapp.Model.RelaxationMethodListAdapter;

/**
 * Created by joost on 21-3-15.
 */
public class RelaxationMethodCommunication extends AbstractRestCommunication {

    public void loadRelaxationMethods(String url,final ListView relaxationMethodListView, final Context context) {
        this.get(url, new ResponseCallback() {
            @Override
            public void executeCallback(HttpResponse response) {
                try {
                    String jsonString = EntityUtils.toString(response.getEntity());
                    JSONArray relaxationMethods = new JSONArray(jsonString);
                    ArrayList<RelaxationMethod> relaxationMethodList = new ArrayList<RelaxationMethod>();
                    for (int i = 0; i < relaxationMethods.length(); i++) {
                        String name = relaxationMethods.getJSONObject(i).get("name").toString();
                        relaxationMethodList.add(new RelaxationMethod(i + 1, name));
                    }
                    RelaxationMethodListAdapter adapter = new RelaxationMethodListAdapter(context, relaxationMethodList);
                    relaxationMethodListView.setAdapter(adapter);
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
}
