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
public class RelaxationMethodListAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<RelaxationMethod> mRelaxationMethods;

    public RelaxationMethodListAdapter(Context context, ArrayList<RelaxationMethod> relaxationMethods){
        mContext = context;
        mRelaxationMethods = relaxationMethods;
    }

    @Override
    public int getCount() {
        return mRelaxationMethods.size();
    }

    @Override
    public Object getItem(int position) {
        return mRelaxationMethods.get(position);
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
            view = inflater.inflate(R.layout.fragment_fobia_detail_list_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.fobiaDetailListItemName);
        TextView numberView = (TextView) view.findViewById(R.id.fobiaDetailListItemNumber);

        RelaxationMethod relaxationMethod = mRelaxationMethods.get(position);

        titleView.setText(relaxationMethod.mName);
        numberView.setText(String.valueOf(relaxationMethod.mNumber));

        return view;
    }
}
