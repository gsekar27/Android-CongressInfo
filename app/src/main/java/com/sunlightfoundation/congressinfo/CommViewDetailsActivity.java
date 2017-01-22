package com.sunlightfoundation.congressinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CommViewDetailsActivity extends AppCompatActivity {

    GlobalVariables fav;
    CommitteeList vd_mem;
    private static final String COMM_TYPE_STR = "comm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_view_details);

        setTitle(R.string.app_comm_vd);  //Set up the title of the Header Tab


        vd_mem = (CommitteeList) getIntent().getExtras().getSerializable("COMM_TAB_DATA");
        fav = (GlobalVariables) getApplicationContext();


        TextView comm_vd_id = (TextView) findViewById(R.id.txt_comm_vd_id);
        TextView comm_vd_name = (TextView) findViewById(R.id.txt_comm_vd_name);
        TextView comm_vd_chamber = (TextView) findViewById(R.id.txt_comm_vd_chamber);
        TextView comm_vd_parentComm = (TextView) findViewById(R.id.txt_comm_vd_parentComm);
        TextView comm_vd_contact = (TextView) findViewById(R.id.txt_comm_vd_contact);
        TextView comm_vd_office = (TextView) findViewById(R.id.txt_comm_vd_office);


        ImageView img_comm_chamber = (ImageView) findViewById(R.id.iv_comm_chamber);
        ImageView img_comm_fav = (ImageView) findViewById(R.id.iv_comm_fav);


        comm_vd_id.setText(vd_mem.getCommittee_id().toUpperCase());
        comm_vd_name.setText(vd_mem.getName() == null ? "None" : vd_mem.getName());
        comm_vd_chamber.setText(vd_mem.getChamber().substring(0, 1).toUpperCase() + vd_mem.getChamber().substring(1));



        try{
            comm_vd_office.setText(vd_mem.getOffice() == null ? "None" : vd_mem.getOffice());
        }catch(Exception e){
            comm_vd_office.setText("None");
        }

        try{
            comm_vd_contact.setText(vd_mem.getPhone() == null ? "None" : vd_mem.getPhone());
        }catch(Exception e){
            comm_vd_contact.setText("None");
        }


        try{
            comm_vd_parentComm.setText(vd_mem.getParent_committee_id() == null? "None":vd_mem.getParent_committee_id().toUpperCase());
        }catch(Exception e){
            comm_vd_parentComm.setText("None");
        }



        String chamber = vd_mem.getChamber();
        if (chamber.equals("house")) {
            img_comm_chamber.setImageResource(R.drawable.h);
        } else {
            img_comm_chamber.setImageResource(R.drawable.s);

        }

        if (fav.isFavComm(vd_mem.getCommittee_id())) {
            img_comm_fav.setImageResource(R.drawable.selected_star);
        } else {
            img_comm_fav.setImageResource(R.drawable.star);
        }
    }

    public void addRemCommToFav(View view) {


        ImageView img = (ImageView) view;

        if (fav.isFavComm(vd_mem.getCommittee_id())) {
            //remove from fav

            img.setImageResource(R.drawable.star);

            fav.removeFromFav(COMM_TYPE_STR, vd_mem.getCommittee_id().toString());
        } else {
            //add to fav
            img.setImageResource(R.drawable.selected_star);

            fav.addToFav(COMM_TYPE_STR, vd_mem.getCommittee_id().toString());

        }
    }

}