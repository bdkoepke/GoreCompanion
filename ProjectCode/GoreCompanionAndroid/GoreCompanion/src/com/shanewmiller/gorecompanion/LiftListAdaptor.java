package com.shanewmiller.gorecompanion;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanewmiller.gorecompanion.Models.Lift;
import com.shanewmiller.gorecompanion.Models.Trail;

/**
 * Created by Shane on 12/14/13.
 * This is the custom list adaptor for the lift list
 */
public class LiftListAdaptor extends ArrayAdapter<Lift> {
    private final Context context;
    private List<Lift> values;

    public LiftListAdaptor(Context context, List<Lift> lifts) {
        super(context, R.layout.lift_listview_row, lifts);
        this.context = context;
        this.values = lifts;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.lift_listview_row, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.trailTitle);
        TextView opentext = (TextView) rowView.findViewById(R.id.isopentext);     
        Lift currLift = values.get(postion);
        boolean isopen = currLift.open;
        
        // Set trail title
        title.setText(currLift.name.trim());

        // Set open/closed
        if (isopen){
        	opentext.setText("Open");
        	opentext.setTextColor(Color.parseColor("#009966"));
        }
        else {
        	opentext.setText("Closed");
        	opentext.setTextColor(Color.RED);
        	
        	// Since its closed, lets change all the opacities to half
        	title.setAlpha((float) .3);
        	opentext.setAlpha((float) .3);
        }
        
        return rowView;
    }
}
