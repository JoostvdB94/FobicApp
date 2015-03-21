package nl.compuplex.fobicapp.communication;

import android.content.Context;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import nl.compuplex.fobicapp.Model.ChangeViewCallback;
import nl.compuplex.fobicapp.Model.RelaxationMethod;
import nl.compuplex.fobicapp.Model.RelaxationMethodListAdapter;

/**
 * Created by joost on 21-3-15.
 */
public class RelaxationMethodCommunication extends AbstractRestCommunication {

    public void loadRelaxationMethods(String url,final ListView relaxationMethodListView, final Context context,final ChangeViewCallback callback) {
        this.get(url, new ResponseCallback() {
            @Override
            public void executeCallback(String response) {
                RelaxationMethod bestScoreObject = null;
                try {
                    JSONArray relaxationMethods = new JSONArray(response);
                    ArrayList<RelaxationMethod> relaxationMethodList = new ArrayList<RelaxationMethod>();
                    for (int i = 0; i < relaxationMethods.length(); i++) {

                        String id = relaxationMethods.getJSONObject(i).get("_id").toString();
                        String name = relaxationMethods.getJSONObject(i).get("name").toString();
                        String phobia = relaxationMethods.getJSONObject(i).get("phobia").toString();
                        String effectiveness = relaxationMethods.getJSONObject(i).get("effectiveness").toString();
                        String timesUsed = relaxationMethods.getJSONObject(i).get("times_used").toString();
                        RelaxationMethod current = new RelaxationMethod(i + 1,id, name,phobia,effectiveness,timesUsed);
                        if(bestScoreObject == null){
                            bestScoreObject = current;
                        }
                        if(Integer.parseInt(timesUsed) > 0  && Integer.parseInt(current.mTimesUsed) > 0) {
                            if (Integer.parseInt(effectiveness) / Integer.parseInt(timesUsed) > Integer.parseInt(current.mEffectiveness) / Integer.parseInt(current.mTimesUsed)) {
                                bestScoreObject = current;
                            }
                        }
                        relaxationMethodList.add(current);
                    }

                    RelaxationMethodListAdapter adapter = new RelaxationMethodListAdapter(context, relaxationMethodList);
                    relaxationMethodListView.setAdapter(adapter);
                    callback.changeView(bestScoreObject);

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
