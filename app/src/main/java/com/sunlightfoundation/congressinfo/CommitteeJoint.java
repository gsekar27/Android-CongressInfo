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
public class CommitteeJoint extends Fragment {


    String jsonString;
    TextView output;
    ListView lvComm;
    Context fragContext;
    public final String COMM_ITEM_ID_KEY = "COMM_TAB_DATA";
    List<CommitteeList> commJoint;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View commView = inflater.inflate(R.layout.committee_joint,container,false);
        fragContext = container.getContext();
        lvComm = (ListView)commView.findViewById(R.id.comm_joint_View);

        requestData("http://ganmanicongress.phnhp2mkum.us-west-2.elasticbeanstalk.com/congress.php?type=commjoint");
        return commView;

    }
    private  void requestData(String uri){
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay(String message){
        //output.append(message + "\n");
    }

    protected void updateConsole(String message){
        Log.i("tag",message);
    }

      class MyTask extends AsyncTask<String,String, List<CommitteeList>>{

        @Override
        protected void onPreExecute(){
            updateDisplay("starting task");
        }

        @Override
        protected List<CommitteeList> doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);

            try {
                JSONObject parentObject = new JSONObject(content);
                JSONArray parentArray = parentObject.getJSONArray("results");
                commJoint = new ArrayList<>();
                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    /**
                     * below single line of code from Gson saves you from writing the json parsing yourself which is commented below
                     */
                    CommitteeList member = gson.fromJson(finalObject.toString(), CommitteeList.class);
                    commJoint.add(member);
                }

                Collections.sort(commJoint, new Comparator<CommitteeList>(){
                    public int compare(CommitteeList m1, CommitteeList m2) {
                        return m1.getName().compareToIgnoreCase(m2.getName());
                    }
                });
                return commJoint;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;


        }

        @Override
        protected void onPostExecute(final List<CommitteeList> result) {

            CommListViewAdaptar adapter = new CommListViewAdaptar(fragContext, R.layout.comm_list_view_xml, result);
            lvComm.setAdapter(adapter);
            lvComm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   // Toast.makeText(fragContext,"you clicked " + result.get(i).getBioguide_id(), Toast.LENGTH_SHORT).show();
                    CommitteeList vd_member = result.get(i);
                    Intent intent = new Intent(fragContext, CommViewDetailsActivity.class);
                    //String str_vd_member = new Gson().toJson(vd_member);

                    //intent.putExtra(ITEM_ID_KEY, str_vd_member );

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(COMM_ITEM_ID_KEY, vd_member);

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


