package nl.compuplex.fobicapp.communication;

import android.content.Context;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                    JSONObject relaxationMethodObject= new JSONObject(response);
                    JSONArray relaxationMethodActions = new JSONArray(relaxationMethodObject.get("activities").toString());
                    ArrayList<RelaxationMethodAction> relaxationMethodList = new ArrayList<RelaxationMethodAction>();
                    for (int i = 0; i < relaxationMethodActions.length(); i++) {
                        String description = relaxationMethodActions.getString(i);
                        relaxationMethodList.add(new RelaxationMethodAction(i+1,description));
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
