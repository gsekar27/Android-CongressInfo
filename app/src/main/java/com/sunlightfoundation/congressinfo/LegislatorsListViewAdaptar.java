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

public class LegislatorsListViewAdaptar extends ArrayAdapter<LegislatorMemebers> {
    Context cont;
    List<LegislatorMemebers> mLegMembers;
    LayoutInflater mInflater;
    int resID;
    public LegislatorsListViewAdaptar(Context context, int resource, List<LegislatorMemebers> objects) {
        super(context, resource, objects);

        mLegMembers = objects;
        resID = resource;
        mInflater = LayoutInflater.from(context);
        this.cont = context;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = mInflater.inflate(resID,parent,false);
        }
        LegislatorMemebers item = mLegMembers.get(position);
        String txt_chamber = item.getchamber();
        String txt_state_name = item.getState_name();
        String txt_district = item.getDistrict()==null? "0":item.getDistrict();
        String txt_party = item.getParty();
        String txt_firstName = item.getFirst_name();
        String txt_last_name = item.getLast_name();
        String memImgUrl = "https://theunitedstates.io/images/congress/original/" + item.getBioguide_id().toString() + ".jpg";

        ImageView mem_imgV = (ImageView)convertView.findViewById(R.id.ivImage) ;
        ImageView img_forward = (ImageView)convertView.findViewById(R.id.iv_leg_forwrd) ;
        TextView txt_name = (TextView)convertView.findViewById(R.id.txt_name);
        TextView txt_details = (TextView) convertView.findViewById(R.id.txt_details);




        img_forward.setImageResource(R.drawable.forward);
        //mem_img.setText(item.getBioguide_id());

        try{
            txt_details.setText("(" + txt_party + ")" + txt_state_name + " - " + "District " + txt_district);
        }
        catch(Exception e){
            txt_details.setText("None");
        }

        try{
            txt_name.setText(txt_last_name + ", " + txt_firstName);
        }
        catch(Exception e){
            txt_name.setText("None");
        }


       try{
           Picasso.with(cont).load(memImgUrl).resize(70,70).into(mem_imgV);

       }catch(Exception e){


       }

        return convertView;
    }

}
