package com.sunlightfoundation.congressinfo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by ganmanisekar on 11/16/16.
 */
public class FavLeg extends Fragment {


    String jsonString;
    TextView output;
    ListView lvLeg;
    Context fragContext;
    public final String ITEM_ID_KEY = "LEG_TAB_DATA";
    LinkedHashMap<String, Integer> mapIndex;
    List<LegislatorMemebers> LegStateMembers;
    ViewGroup vg;
    View LegView;
    TextView sideItem;
    LayoutInflater inf;
    GlobalVariables fav;
    //LinearLayout indexLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        LegView = inflater.inflate(R.layout.legislators,container,false);
        vg = container;
        inf = inflater;

        fragContext = container.getContext();
        lvLeg = (ListView)LegView.findViewById(R.id.leg_state_legView);


        requestData("http://congress.api.sunlightfoundation.com/legislators?apikey=eb6c342bbcc448fb96a33f3a8dca3b98&per_page=all");
        return LegView;

    }
    private  void requestData(String uri){
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay(String message){
        //output.append(message + "\n");
    }

    private void getIndexList(List<LegislatorMemebers> memList) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < memList.size(); i++) {
            String state = memList.get(i).getState_name();
            String index = state.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex() {

        LinearLayout indexLayout = (LinearLayout) LegView.findViewById(R.id.state_side_index);

        //sideItem =
        //LinearLayout indexLayout = (LinearLayout) LegView.findViewById(R.id.state_side_index);

        //TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            //TextView textview = (TextView) inf.inflate(R.layout.side_index_item,vg,false);
            TextView textview = (TextView) getActivity().getLayoutInflater().inflate(R.layout.side_index_item,null);
            textview.setText(index);
            textview.setTextSize(10);
            textview.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView selectedIndex = (TextView) view;
                    lvLeg.setSelection(mapIndex.get(selectedIndex.getText()));

                }
            });

            indexLayout.addView(textview);

        }

    }



    protected void updateConsole(String message){

    }

      class MyTask extends AsyncTask<String,String, List<LegislatorMemebers>>{

        @Override
        protected void onPreExecute(){
            updateDisplay("starting task");
        }

        @Override
        protected List<LegislatorMemebers> doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);

            try {
                JSONObject parentObject = new JSONObject(content);
                JSONArray parentArray = parentObject.getJSONArray("results");
                LegStateMembers = new ArrayList<>();
                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);

                    LegislatorMemebers member = gson.fromJson(finalObject.toString(), LegislatorMemebers.class);

                    fav = (GlobalVariables) getActivity().getApplicationContext();
                    if(fav.isFavLeg(member.getBioguide_id())){
                        LegStateMembers.add(member);
                    }

                }

                Collections.sort(LegStateMembers, new Comparator<LegislatorMemebers>(){
                    public int compare(LegislatorMemebers m1, LegislatorMemebers m2) {
                        return m1.getState_name().compareToIgnoreCase(m2.getState_name());
                    }
                });

                getIndexList(LegStateMembers);
                return LegStateMembers;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }

        @Override
        protected void onPostExecute(final List<LegislatorMemebers> result) {

            LegislatorsListViewAdaptar adapter = new LegislatorsListViewAdaptar(fragContext, R.layout.leg_list_view_xml, result);
            lvLeg.setAdapter(adapter);


            displayIndex();
            lvLeg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    LegislatorMemebers vd_member = result.get(i);
                    Intent intent = new Intent(fragContext, LegViewDetailsActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ITEM_ID_KEY, vd_member);

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


