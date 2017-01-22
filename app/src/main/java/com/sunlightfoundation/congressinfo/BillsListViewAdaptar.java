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

public class BillsListViewAdaptar extends ArrayAdapter<BillsList> {
    Context cont;
    List<BillsList> mBillItems;
    LayoutInflater mInflater;
    int resID;
    public BillsListViewAdaptar(Context context, int resource, List<BillsList> objects) {
        super(context, resource, objects);

        mBillItems = objects;
        resID = resource;
        mInflater = LayoutInflater.from(context);
        this.cont = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = mInflater.inflate(resID,parent,false);
        }
        BillsList item = mBillItems.get(position);

        TextView txt_billID = (TextView)convertView.findViewById(R.id.txt_bill_id);
        TextView txt_short_title = (TextView)convertView.findViewById(R.id.txt_bill_title);
        TextView txt_introduced_on = (TextView)convertView.findViewById(R.id.txt_bill_date);

        try{
            String id = item.getBill_id().toUpperCase();
            txt_billID.setText(id);
        }catch (Exception e){
            txt_billID.setText("None");
        }

        try{
            String title = (item.getShort_title()==null || item.getShort_title().equals(""))? "None":item.getShort_title();
            if (title.equals("None")){
                title = item.getOfficial_title()==null? "None":item.getOfficial_title();
                txt_short_title.setText(title);
            }
            else {
                txt_short_title.setText(title);
            }

        }catch (Exception e){
            txt_short_title.setText("None");
        }

        try{
            String date = item.getIntroduced_on().toString();
            txt_introduced_on.setText(date);
        }catch (Exception e){
            txt_introduced_on.setText("None");
        }





        return convertView;
    }


}
