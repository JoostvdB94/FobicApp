package nl.compuplex.fobicapp.communication;

import android.content.Context;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import nl.compuplex.fobicapp.Model.RelaxationMethod;
import nl.compuplex.fobicapp.Model.RelaxationMethodListAdapter;

/**
 * Created by joost on 21-3-15.
 */
public class RelaxationMethodCommunication extends AbstractRestCommunication {
    public void loadRelaxationMethods(final ListView relaxationMethodListView, final Context context) {
//        this.get(url, new ResponseCallback() {
//            @Override
//            public void executeCallback(HttpResponse response) {
                String jsonString;
                String match = "";
                try {
//                    jsonString = EntityUtils.toString(response.getEntity());
                    jsonString = "[{\"name\":\"SomeMethod\",\"actions\":[{\"description\":\"Doe wat\"},{\"description\":\"Doe nog wat\"},{\"description\":\"Doet nog wat anders\"}],\"effectiveness\":80,\"times_used\":50},{\"name\":\"SomeOtherMethod\",\"actions\":[{\"description\":\"Doe wat\"},{\"description\":\"Doe nog wat\"},{\"description\":\"Doet nog wat anders\"}],\"effectiveness\":80,\"times_used\":50}]';";
                    JSONArray relaxationMethods = new JSONArray(jsonString);
                    ArrayList<RelaxationMethod> relaxationMethodList = new ArrayList<RelaxationMethod>();
                    for (int i = 0; i < relaxationMethods.length(); i++) {
                        String name = relaxationMethods.getJSONObject(i).get("name").toString();
                        relaxationMethodList.add(new RelaxationMethod(i+1,name));
                    }
                    RelaxationMethodListAdapter adapter = new RelaxationMethodListAdapter(context, relaxationMethodList);
                    relaxationMethodListView.setAdapter(adapter);
//                } catch (IOException e) {
//                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
//        }, new ProgressCallback() {
//            @Override
//            public void executeCallback(Integer progress) {
//
//            }
//        });
//    }
}
