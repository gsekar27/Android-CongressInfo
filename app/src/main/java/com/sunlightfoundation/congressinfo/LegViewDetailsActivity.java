package com.sunlightfoundation.congressinfo;

import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Random;

public class LegViewDetailsActivity extends AppCompatActivity {

    LegislatorMemebers vd_mem;
    GlobalVariables fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_view_details);
        setTitle(R.string.app_leg_vd);  //Set up the title of the Header Tab


        vd_mem = (LegislatorMemebers) getIntent().getExtras().getSerializable("LEG_TAB_DATA");
        fav = (GlobalVariables) getApplicationContext();



        TextView lg_vd_name = (TextView) findViewById(R.id.txt_leg_vd_mem_name);
        TextView lg_vd_email = (TextView) findViewById(R.id.txt_leg_vd_mem_email);
        TextView lg_vd_chamber = (TextView) findViewById(R.id.txt_leg_vd_mem_chamber);
        TextView lg_vd_contact = (TextView) findViewById(R.id.txt_leg_vd_mem_contact);
        TextView lg_vd_startTerm = (TextView) findViewById(R.id.txt_leg_vd_mem_startTerm);
        TextView lg_vd_endTerm = (TextView) findViewById(R.id.txt_leg_vd_mem_endTerm);
        TextView lg_vd_party_name = (TextView) findViewById(R.id.txt_leg_vd_party);
        TextView lg_vd_office = (TextView) findViewById(R.id.txt_leg_vd_mem_office);
        TextView lg_vd_state = (TextView) findViewById(R.id.txt_leg_vd_mem_state);
        TextView lg_vd_fax = (TextView) findViewById(R.id.txt_leg_vd_mem_fax);
        TextView lg_vd_birthday = (TextView) findViewById(R.id.txt_leg_vd_mem_birthday);
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar_leg);
        TextView txt_progress = (TextView) findViewById(R.id.txt_progress_value);



        lg_vd_name.setText(vd_mem.getTitle() + "." + vd_mem.getLast_name() + "," + vd_mem.getFirst_name());

        lg_vd_chamber.setText(vd_mem.getchamber().substring(0, 1).toUpperCase() + vd_mem.getchamber().substring(1));

        try{
            lg_vd_email.setText(vd_mem.getOc_email()==null? "None":vd_mem.getOc_email());
        }catch(Exception e){
            lg_vd_email.setText("None");
        }

        try{
            lg_vd_contact.setText(vd_mem.getPhone()==null? "None": vd_mem.getPhone());
        }catch(Exception e){
            lg_vd_contact.setText("None");
        }

        try{


            lg_vd_startTerm.setText(vd_mem.getTerm_start()==null? "None": vd_mem.getTerm_start());
        }catch(Exception e){
            lg_vd_startTerm.setText("None");
        }

        try{
            lg_vd_endTerm.setText(vd_mem.getTerm_end()==null? "None": vd_mem.getTerm_end());
        }catch(Exception e){
            lg_vd_endTerm.setText("None");
        }

        try{
            lg_vd_state.setText(vd_mem.getState()==null? "None": vd_mem.getState().toUpperCase());
        }catch(Exception e){
            lg_vd_state.setText("None");
        }

        try{
            lg_vd_fax.setText(vd_mem.getFax()==null?"None":vd_mem.getFax());
        }catch(Exception e){
            lg_vd_fax.setText("None");
        }

        try{
            lg_vd_office.setText(vd_mem.getOffice()==null? "None": vd_mem.getOffice());
        }catch(Exception e){
            lg_vd_office.setText("None");
        }
        try{
            lg_vd_birthday.setText(vd_mem.getBirthday()==null? "None": vd_mem.getBirthday());
        }catch(Exception e){
            lg_vd_birthday.setText("None");
        }



        //Load member image
        String imgURL = "https://theunitedstates.io/images/congress/original/" + vd_mem.getBioguide_id().toString() + ".jpg";
        ImageView iv_lg_vd_mem = (ImageView) findViewById(R.id.iv_leg_vd_mem);
        Picasso.with(this).load(imgURL).resize(70,70).into(iv_lg_vd_mem);


        //handle favorites
        ImageView lg_vd_fav = (ImageView) findViewById(R.id.iv_leg_vd_fav);

        if(fav.isFavLeg(vd_mem.getBioguide_id())){
            lg_vd_fav.setImageResource(R.drawable.selected_star);
        }
        else{
            lg_vd_fav.setImageResource(R.drawable.star);
        }

        //handle party image
        ImageView iv_lg_vd_party = (ImageView) findViewById(R.id.iv_leg_vd_party);

        if (vd_mem.getParty().equals("R")) {
            iv_lg_vd_party.setImageResource(R.drawable.r);
            lg_vd_party_name.setText("Republican");
        }
        else{
            iv_lg_vd_party.setImageResource(R.drawable.d);
            lg_vd_party_name.setText("Democrat");
        }


    //Progress bar
        int val = 93;



        txt_progress.setText(val + "%");
        pb.setProgress(val);

    }

    public void addRemLegToFav(View view){

        //fav = (GlobalVariables) getApplicationContext();
        ImageView img = (ImageView) view;

        if (fav.isFavLeg(vd_mem.getBioguide_id())){
            //remove from fav

            img.setImageResource(R.drawable.star);

            fav.removeFromFav("leg",vd_mem.getBioguide_id().toString());
        }
        else{
            //add to fav
            img.setImageResource(R.drawable.selected_star);

            fav.addToFav("leg",vd_mem.getBioguide_id().toString());
        }

    }

    public void openFb(View view){

        try{

            String fb_url = "https://www.facebook.com/" + vd_mem.getFacebook_id().toString();

            if(vd_mem.getFacebook_id().equals("") || vd_mem.getFacebook_id()== null){
                Toast.makeText(getApplicationContext(), "Facebook is page not available",
                        Toast.LENGTH_LONG).show();

            }

            else {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(fb_url));
                startActivity(intent);
            }

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Facebook is page not available",
                    Toast.LENGTH_LONG).show();

        }

    }

    public void openTwitter(View view){

        try{

            String t_url = "https://www.twitter.com/" + vd_mem.getTwitter_id().toString();

            if(vd_mem.getTwitter_id().equals("") || vd_mem.getTwitter_id()== null){
                Toast.makeText(getApplicationContext(), "Twitter page not available",
                        Toast.LENGTH_LONG).show();

            }
            else{
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(t_url));
                startActivity(intent);
            }

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Twitter page not available",
                    Toast.LENGTH_LONG).show();

        }

    }




    public void openWebsite(View view){

        try {


            String w_url = vd_mem.getWebsite().toString();

            if (vd_mem.getWebsite().equals("") || vd_mem.getWebsite() == null) {
                Toast.makeText(getApplicationContext(), "Website is not available",
                        Toast.LENGTH_LONG).show();

            } else {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(w_url));
            //if(intent.resolveActivity((getPackageManager()))!= null){
            startActivity(intent);
        }

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Website is not available",
                    Toast.LENGTH_LONG).show();

        }


    }
}
