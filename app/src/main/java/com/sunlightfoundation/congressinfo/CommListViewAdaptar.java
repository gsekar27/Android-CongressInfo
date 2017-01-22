package com.sunlightfoundation.congressinfo;

/**
 * Created by ganmanisekar on 11/18/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CommListViewAdaptar extends ArrayAdapter<CommitteeList> {
    Context cont;
    List<CommitteeList> mCommMembers;
    LayoutInflater mInflater;
    int resID;
    public CommListViewAdaptar(Context context, int resource, List<CommitteeList> objects) {
        super(context, resource, objects);

        mCommMembers = objects;
        resID = resource;
        mInflater = LayoutInflater.from(context);
        this.cont = context;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = mInflater.inflate(resID,parent,false);
        }
        CommitteeList item = mCommMembers.get(position);
        TextView txt_id = (TextView)convertView.findViewById(R.id.txt_comm_id);
        TextView txt_name = (TextView)convertView.findViewById(R.id.txt_comm_name);
        TextView txt_chamber = (TextView)convertView.findViewById(R.id.txt_comm_chamber);


        try{
            txt_id.setText(item.getCommittee_id().toUpperCase());
        }catch (Exception e){
            txt_id.setText("None");
        }

        try{
            txt_name.setText(item.getName()== null? "None" :item.getName());
        }catch (Exception e){
            txt_name.setText("None");
        }

        try{
            txt_chamber.setText(item.getChamber().substring(0, 1).toUpperCase() + item.getChamber().substring(1));
        }catch (Exception e){
            txt_chamber.setText("None");
        }






        return convertView;
    }


}
