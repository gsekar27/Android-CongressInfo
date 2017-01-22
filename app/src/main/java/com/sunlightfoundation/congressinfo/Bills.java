package com.sunlightfoundation.congressinfo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by ganmanisekar on 11/16/16.
 */
public class Bills extends Fragment {


    String jsonString;
    TextView output;
    ListView lvBills;
    Context fragContext;
    public final String BILL_ITEM_ID_KEY = "BILL_TAB_DATA";
    List<BillsList> billsActiveList;
    GlobalVariables fav;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View BillView = inflater.inflate(R.layout.bills,container,false);
        fragContext = container.getContext();
        lvBills = (ListView)BillView.findViewById(R.id.bills_active_legView);

        requestData("http://congress.api.sunlightfoundation.com/bills?history.active=true&apikey=eb6c342bbcc448fb96a33f3a8dca3b98&per_page=all");
        return lvBills;

    }
    private  void requestData(String uri){
        BillTask task = new BillTask();
        task.execute(uri);
    }

    protected void updateDisplay(String message){

    }

    protected void updateConsole(String message){
        Log.i("tag",message);
    }

      class BillTask extends AsyncTask<String,String, List<BillsList>>{

        @Override
        protected void onPreExecute(){
            updateDisplay("starting task");
        }

        @Override
        protected List<BillsList> doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);

            try {
                JSONObject parentObject = new JSONObject(content);
                JSONArray parentArray = parentObject.getJSONArray("results");
                billsActiveList = new ArrayList<>();
                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);

                    BillsList billItem = gson.fromJson(finalObject.toString(), BillsList.class);
                    billsActiveList.add(billItem);
                }

                Collections.sort(billsActiveList, new Comparator<BillsList>(){
                    public int compare(BillsList m1, BillsList m2) {
                        return m2.getIntroduced_on().compareToIgnoreCase(m1.getIntroduced_on());
                    }
                });
                return billsActiveList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }

        @Override
        protected void onPostExecute(final List<BillsList> result) {

            BillsListViewAdaptar adapter = new BillsListViewAdaptar(fragContext, R.layout.bill_list_view_xml, result);
            lvBills.setAdapter(adapter);
            lvBills.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   // Toast.makeText(fragContext,"you clicked " + result.get(i).getBioguide_id(), Toast.LENGTH_SHORT).show();
                    BillsList vd_item = result.get(i);
                    Intent intent = new Intent(fragContext, BillsViewDetailsActivity.class);


                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BILL_ITEM_ID_KEY, vd_item);

                    intent.putExtras(bundle);

                    fragContext.startActivity(intent);


                 }
            });


        }

        protected void  onProgressUpdate(String... Values){

            //updateDisplay(Values[0]);
        }
    }







}


