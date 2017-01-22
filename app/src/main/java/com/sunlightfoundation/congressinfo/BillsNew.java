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
public class BillsNew extends Fragment {


    String jsonString;
    TextView output;
    ListView lvBills;
    Context fragContext;
    public final String BILL_ITEM_ID_KEY = "BILL_TAB_DATA";
    List<BillsList> billsNewList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View BillView = inflater.inflate(R.layout.bills_new,container,false);
        fragContext = container.getContext();
        lvBills = (ListView)BillView.findViewById(R.id.bills_new_legView);
        //output = LegView.findViewById(R.id.leg_state_TextView);
        //output = (ListView) LegView.findViewById(R.id.leg_state_legView);
        //output.setMovementMethod(new ScrollingMovementMethod());
        //String[] menuItems = {"do something", "do something", "do something"};
        //ListView legListView = (ListView) LegView.findViewById(R.id.leg_state_listView);
        //ArrayAdapter<String> legListViewAdapter = new ArrayAdapter<String>(
                //getActivity(),android.R.layout.simple_list_item_1,menuItems);

        //legListView.setAdapter(legListViewAdapter);
        requestData("http://ganmanicongress.phnhp2mkum.us-west-2.elasticbeanstalk.com/congress.php?type=billnew");
        return lvBills;

    }
    private  void requestData(String uri){
        BillTask task = new BillTask();
        task.execute(uri);
    }

    protected void updateDisplay(String message){
        //output.append(message + "\n");
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
                billsNewList = new ArrayList<>();
                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    /**
                     * below single line of code from Gson saves you from writing the json parsing yourself which is commented below
                     */
                    BillsList billItem = gson.fromJson(finalObject.toString(), BillsList.class);
                    billsNewList.add(billItem);
                }

                Collections.sort(billsNewList, new Comparator<BillsList>(){
                    public int compare(BillsList m1, BillsList m2) {
                        return m2.getIntroduced_on().compareToIgnoreCase(m1.getIntroduced_on());
                    }
                });
                return billsNewList;



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
                    //String str_vd_member = new Gson().toJson(vd_member);

                    //intent.putExtra(ITEM_ID_KEY, str_vd_member );

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


