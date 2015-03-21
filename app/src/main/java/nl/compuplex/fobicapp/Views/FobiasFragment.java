package nl.compuplex.fobicapp.Views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import nl.compuplex.fobicapp.Model.Fobia;
import nl.compuplex.fobicapp.R;
import nl.compuplex.fobicapp.communication.FobiaCommunication;

/**
 * Created by Thomas on 20-3-2015.
 */
public class FobiasFragment extends ListFragment {
    ListView mFobiaList;
    RelativeLayout mDrawerPane;
    private DrawerLayout mDrawerLayout;



    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fobia fobia = (Fobia) (l.getAdapter().getItem(position));
        ft.replace(R.id.container, FobiaDetailFragment.newInstance(fobia));
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fobia_list_fragment, null);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadPhobias();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fobia_overview, menu);
    }

    public void loadPhobias() {
        FobiaCommunication communication = new FobiaCommunication();
        communication.loadPhobias(getListView(), getActivity());
    }

}
