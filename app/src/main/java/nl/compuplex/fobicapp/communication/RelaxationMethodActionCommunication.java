package nl.compuplex.fobicapp.communication;

import android.content.Context;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import nl.compuplex.fobicapp.Model.RelaxationMethodAction;
import nl.compuplex.fobicapp.Model.RelaxationMethodActionDetailAdapter;

/**
 * Created by joost on 21-3-15.
 */
public class RelaxationMethodActionCommunication extends AbstractRestCommunication {
    public void loadRelaxationMethods(String url,final ListView relaxationMethodListView, final Context context) {
        this.get(url, new ResponseCallback() {
            @Override
            public void executeCallback(String response) {
                String jsonString;
                String match = "";
                try {
                    JSONArray relaxationMethodActions = new JSONArray(response);
                    ArrayList<RelaxationMethodAction> relaxationMethodList = new ArrayList<RelaxationMethodAction>();
                    for (int i = 0; i < relaxationMethodActions.length(); i++) {
                        String description = relaxationMethodActions.getJSONObject(i).toString();
                        relaxationMethodList.add(new RelaxationMethodAction(description));
                    }
                    RelaxationMethodActionDetailAdapter adapter = new RelaxationMethodActionDetailAdapter(context, relaxationMethodList);
                    relaxationMethodListView.setAdapter(adapter);
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
