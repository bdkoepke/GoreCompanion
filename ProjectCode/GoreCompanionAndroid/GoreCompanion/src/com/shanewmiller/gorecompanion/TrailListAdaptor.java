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

import com.shanewmiller.gorecompanion.Models.Trail;

/**
 * Created by Shane on 11/17/13.
 * This is the custom list adaptor for the trails list
 */
public class TrailListAdaptor extends ArrayAdapter<Trail> {
    private final Context context;
    private List<Trail> values;

    public TrailListAdaptor(Context context, List<Trail> trails) {
        super(context, R.layout.trail_listview_row, trails);
        this.context = context;
        this.values = trails;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.trail_listview_row, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.trailTitle);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.difficultyicon);
        TextView opentext = (TextView) rowView.findViewById(R.id.isopentext);
        ImageView groomedIcon = (ImageView) rowView.findViewById(R.id.groomedicon);
        Trail currTrail = values.get(postion);
        String currTrailDifficulty = currTrail.difficulty;
        boolean isopen = currTrail.open;
        
        // Set trail title
        title.setText(currTrail.name.trim());

        // Sets the list item to the proper icon
        if(currTrailDifficulty == "easier"){
            imageView.setImageResource(R.drawable.green);
        }
        else if (currTrailDifficulty.contains("more-difficult")){
        	imageView.setImageResource(R.drawable.blue);
        }
        else if (currTrailDifficulty.contains("most-difficult")){
        	imageView.setImageResource(R.drawable.black);
        }
        else if (currTrailDifficulty.contains("dblblack")){
        	imageView.setImageResource(R.drawable.dblblack);
        }
        
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
        	imageView.setImageAlpha(90);
        	opentext.setAlpha((float) .3);
        }
        
        // sets groomed icon
        if (currTrail.groomed){
        	groomedIcon.setImageResource(R.drawable.groomed);
        }
        
        
        return rowView;
    }
}
