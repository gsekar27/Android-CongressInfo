package com.sunlightfoundation.congressinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class BillsViewDetailsActivity extends AppCompatActivity {

    GlobalVariables fav;
    BillsList vd_bill_item;
    private static final String BILL_TYPE_STR = "bills";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_view_details);

        fav = (GlobalVariables) getApplicationContext();

        setTitle(R.string.app_bills_vd);  //Set up the title of the Header Tab

        vd_bill_item = (BillsList) getIntent().getExtras().getSerializable("BILL_TAB_DATA");



        TextView id = (TextView) findViewById(R.id.txt_bill_vd_id);
        TextView title = (TextView) findViewById(R.id.txt_bill_vd_title);
        TextView type = (TextView) findViewById(R.id.txt_bill_vd_bill_type);
        TextView sponsor_name = (TextView) findViewById(R.id.txt_bill_vd_sponsor);
        TextView chamber = (TextView) findViewById(R.id.txt_bill_vd_chamber);
        TextView status = (TextView) findViewById(R.id.txt_bill_vd_status);
        TextView introduced_on = (TextView) findViewById(R.id.txt_bill_vd_introduced_on);
        TextView cong_url = (TextView) findViewById(R.id.txt_bill_vd_cong_url);
        TextView ver_status = (TextView) findViewById(R.id.txt_bill_vd_vers);
        TextView bill_url = (TextView) findViewById(R.id.txt_bill_vd_url);



        String sponsor_firstName = vd_bill_item.getSponsor().getFirst_name();
        String sponsor_lastName = vd_bill_item.getSponsor().getLast_name();
        String sponsor_title = vd_bill_item.getSponsor().getTitle();


        id.setText(vd_bill_item.getBill_id().toUpperCase());
        try{

            title.setText(vd_bill_item.getOfficial_title()==null? "None": vd_bill_item.getOfficial_title());

        }catch (Exception e){
            title.setText("None");
        }

        type.setText(vd_bill_item.getBill_type().toUpperCase());
        sponsor_name.setText(sponsor_title + ". " + sponsor_lastName + "," + sponsor_firstName);
        chamber.setText(vd_bill_item.getChamber().substring(0, 1).toUpperCase() + vd_bill_item.getChamber().substring(1));
        status.setText(vd_bill_item.getHistory().getActive()== true? "Active":"New");
        introduced_on.setText(vd_bill_item.getIntroduced_on());

        try{
            cong_url.setText(vd_bill_item.getUrls().getCongress());
        }catch (Exception e){
            cong_url.setText("None");
        }

        try{
            ver_status.setText(vd_bill_item.getBill_id());
        }catch (Exception e){
            ver_status.setText("None");
        }

        try{
            bill_url.setText(vd_bill_item.getUrls().getOpencongress());
        }catch (Exception e){
            bill_url.setText("None");
        }

        ImageView bill_vd_fav = (ImageView) findViewById(R.id.iv_bill_fav);

        if(fav.isFavBill(vd_bill_item.getBill_id())){
            bill_vd_fav.setImageResource(R.drawable.selected_star);
        }
        else{
            bill_vd_fav.setImageResource(R.drawable.star);
        }



    }

    public void addRemBillToFav(View view) {

        //fav = (GlobalVariables) getApplicationContext();
        ImageView img = (ImageView) view;

        if (fav.isFavBill(vd_bill_item.getBill_id())) {
            //remove from fav

            img.setImageResource(R.drawable.star);

            fav.removeFromFav(BILL_TYPE_STR, vd_bill_item.getBill_id().toString());
        } else {
            //add to fav
            img.setImageResource(R.drawable.selected_star);

            fav.addToFav(BILL_TYPE_STR, vd_bill_item.getBill_id().toString());
        }
    }
}
