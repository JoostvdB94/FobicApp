package nl.compuplex.fobicapp.Views;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.compuplex.fobicapp.Model.RelaxationMethodAction;
import nl.compuplex.fobicapp.R;
import nl.compuplex.fobicapp.communication.RelaxationMethodActionCommunication;

/**
 * Created by joost on 21-3-15.
 */
public class RelaxationMethodDetailFragment extends ListFragment{

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
public static RelaxationMethodDetailFragment newInstance(RelaxationMethodAction relaxationMethodAction) {
        RelaxationMethodDetailFragment fragment = new RelaxationMethodDetailFragment();
        Bundle args = new Bundle();
        args.putString("Description", relaxationMethodAction.mDiscription);
        fragment.setArguments(args);
        return fragment;
        }

public RelaxationMethodDetailFragment() {
        // Required empty public constructor
        }

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //getActivity().getActionBar().setCustomView(R.menu.fobia_detail);

        }

@Override
public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelaxationMethodActionCommunication communication = new RelaxationMethodActionCommunication();

        communication.loadRelaxationMethods("http://phobicapp.compuplex.nl/api/phobia/"+mID+"/relaxationmethod",getListView(), getActivity());
        }

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relaxation_method_detail, container, false);

        TextView textView = (TextView) view.findViewById(R.id.relaxationMethodDetailDescription);
        textView.setText(getArguments().getString("Description"));

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

@Override
public void onResume() {
        super.onResume();
        mID = getArguments().getString("ID");
        mTitle = getArguments().getString("TITLE");
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
