package nl.compuplex.fobicapp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.compuplex.fobicapp.R;

/**
 * Created by joost on 21-3-15.
 */
public class RelaxationMethodActionDetailAdapter extends BaseAdapter {
    Context mContext;
    public ArrayList<RelaxationMethodAction> mRelaxationMethodActions;

    public RelaxationMethodActionDetailAdapter(Context context, ArrayList<RelaxationMethodAction> relaxationMethodActions){
        mContext = context;
        mRelaxationMethodActions = relaxationMethodActions;
    }

    @Override
    public int getCount() {
        return mRelaxationMethodActions.size();
    }

    @Override
    public Object getItem(int position) {
        return mRelaxationMethodActions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_relaxation_method_detail_list_item, null);
        }
        else {
            view = convertView;
        }

        TextView descriptionView = (TextView) view.findViewById(R.id.relaxationMethodDetailDescription);
        TextView numberView = (TextView) view.findViewById(R.id.relaxationMethodDetailNumber);

        RelaxationMethodAction relaxationMethodAction = mRelaxationMethodActions.get(position);

        descriptionView.setText(relaxationMethodAction.mDiscription);
        numberView.setText(String.valueOf(relaxationMethodAction.mNumber));

        return view;
    }
}
