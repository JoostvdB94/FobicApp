package nl.compuplex.fobicapp.Views;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nl.compuplex.fobicapp.Model.ChangeViewCallback;
import nl.compuplex.fobicapp.Model.Fobia;
import nl.compuplex.fobicapp.Model.RelaxationMethod;
import nl.compuplex.fobicapp.R;
import nl.compuplex.fobicapp.communication.RelaxationMethodCommunication;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FobiaDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FobiaDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FobiaDetailFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    public static String mID;
    public static String mTitle;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FobiaDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FobiaDetailFragment newInstance(Fobia fobia) {
        FobiaDetailFragment fragment = new FobiaDetailFragment();
        Bundle args = new Bundle();
        args.putString("ID", fobia._ID);
        args.putString("TITLE", fobia.mTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public FobiaDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //getActivity().getActionBar().setCustomView(R.menu.fobia_detail);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        RelaxationMethod relaxationMethod = (RelaxationMethod) (l.getAdapter().getItem(position));
        ft.replace(R.id.container, RelaxationMethodDetailFragment.newInstance(relaxationMethod));
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fobia_detail, container, false);

        TextView textView = (TextView) view.findViewById(R.id.fobiaDetailTitle);
        textView.setText(getArguments().getString("TITLE"));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fobia_detail, menu);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    class ChangeViewCallbackClass implements ChangeViewCallback {
        public void changeView(RelaxationMethod bestMethod){
            if(bestMethod != null) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();

                ft.replace(R.id.container, RelaxationMethodDetailFragment.newInstance(bestMethod));
                ft.addToBackStack(null);
                ft.commit();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mID = getArguments().getString("ID");
        mTitle = getArguments().getString("TITLE");

        ArrayList<String> ids = new ArrayList<String>();
        RelaxationMethodCommunication communication = new RelaxationMethodCommunication();
        communication.loadRelaxationMethods("http://phobicapp.compuplex.nl/api/phobia/" + mID + "/relaxationmethod", getListView(), getActivity(),new ChangeViewCallbackClass());


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}


