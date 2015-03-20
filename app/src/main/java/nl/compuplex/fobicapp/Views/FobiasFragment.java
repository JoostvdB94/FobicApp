package nl.compuplex.fobicapp.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import nl.compuplex.fobicapp.Model.Fobia;
import nl.compuplex.fobicapp.Model.FobiaListAdapter;
import nl.compuplex.fobicapp.R;

/**
 * Created by Thomas on 20-3-2015.
 */
public class FobiasFragment extends ListFragment {
    ListView mFobiaList;
    RelativeLayout mDrawerPane;
    private DrawerLayout mDrawerLayout;

    private ArrayList<Fobia> mFobias = new ArrayList<Fobia>();

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mFobias.add(new Fobia("JoostFobia"));
        mFobias.add(new Fobia("DannyFobia"));
        mFobias.add(new Fobia("ThomasFobia"));

        FobiaListAdapter adapter = new FobiaListAdapter(getActivity(), mFobias);
        setListAdapter(adapter);
    }
}
