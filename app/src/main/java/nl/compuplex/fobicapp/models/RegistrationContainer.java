package nl.compuplex.fobicapp.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by joost on 19-3-15.
 */
public class RegistrationContainer {
    public String PROPERTY_REG_ID = "RegistrationId";
    public String PROPERTY_USERNAME = "Username";


    private String PREFERENCE_NAMESPACE = "RegistrationPreferences";
    private SharedPreferences prefs;
    private Context context;


    public RegistrationContainer(Context appContext) {
        this.context = appContext;
        prefs = getGCMPreferences(this.context);
    }

    private SharedPreferences getGCMPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the registration ID in your app is up to you.
        return context.getSharedPreferences(PREFERENCE_NAMESPACE, Context.MODE_PRIVATE);
    }

    public boolean isRegisteredLocally() {
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        String username = prefs.getString(PROPERTY_USERNAME, "");
        return !registrationId.equals("") && !username.equals("");
    }

    public String getRegistrationId() {
        return prefs.getString(PROPERTY_REG_ID, "");
    }

    public void storeRegistrationId(String registrationId) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, registrationId);
//        editor.putInt(PROPERTY_APP_VERSION, );
        editor.apply();
    }

    public String getUsername() {
        return prefs.getString(PROPERTY_USERNAME, "");
    }

    public void storeUsername(String username) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_USERNAME, username);
        editor.apply();
    }

}
