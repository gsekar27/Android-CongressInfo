package com.sunlightfoundation.congressinfo;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganmanisekar on 11/24/16.
 */
public class GlobalVariables extends Application {
    public static List<String> fav_leg_id = new ArrayList<String>();;
    public static List<String> fav_bill_id = new ArrayList<String>();;
    public static List<String> fav_comm_id = new ArrayList<String>();;



    public static List<String> getFav_leg_id() {
        return fav_leg_id;
    }

    public static List<String> getFav_bill_id() {
        return fav_bill_id;
    }

    public static List<String> getFav_comm_id() {
        return fav_comm_id;
    }

    public void addToFav(String type, String id){
        if (type.equals("leg")){
            fav_leg_id.add(id);
        }
        else if(type.equals("bills")){
            fav_bill_id.add(id);
        }
        else if (type.equals("comm")){
            fav_comm_id.add(id);
        }

    }

    public void removeFromFav(String type, String id){
        if (type.equals("leg")){
            fav_leg_id.remove(fav_leg_id.indexOf(id));
        }
        else if(type.equals("bills")){
            fav_bill_id.remove(fav_bill_id.indexOf(id));
        }
        else if (type.equals("comm")){
            fav_comm_id.remove(fav_comm_id.indexOf(id));
        }

    }

    public boolean isFavLeg(String id){
        return fav_leg_id.contains(id);
    }

    public boolean isFavBill(String id){
        return fav_bill_id.contains(id);
    }
    public boolean isFavComm(String id){
        return fav_comm_id.contains(id);
    }
}
