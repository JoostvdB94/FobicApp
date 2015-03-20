package nl.compuplex.fobicapp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.compuplex.fobicapp.R;

/**
 * Created by Thomas on 20-3-2015.
 */
public class FobiaListAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Fobia> mFobias = new ArrayList<Fobia>();

    public FobiaListAdapter(Context context, ArrayList<Fobia> Fobias) {
        mContext = context;
        mFobias = Fobias;
    }

    @Override
    public int getCount() {
        return mFobias.size();
    }

    @Override
    public Object getItem(int position) {
        return mFobias.get(position);
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
            view = inflater.inflate(R.layout.fobia_list_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.title);

        Fobia fobia = mFobias.get(position);

        titleView.setText(fobia.mTitle);

        return view;
    }
}
