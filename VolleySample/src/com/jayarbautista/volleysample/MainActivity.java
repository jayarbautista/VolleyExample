package com.jayarbautista.volleysample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
    private ListView lstView;
    private RequestQueue mRequestQueue;
    private ArrayList<InfoModel> arrInfo;
    private VolleyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrInfo = new ArrayList<InfoModel>();
        adapter = new VolleyAdapter(this, arrInfo);

        lstView = (ListView) findViewById(R.id.listView);
        lstView.setAdapter(adapter);
        
        mRequestQueue = Volley.newRequestQueue(this);
        String url = "http://www.json-generator.com/api/json/get/cvrzXieiZK?indent=2";
        final ProgressDialog pd = ProgressDialog.show(this, "Please Wait...", "Please Wait...");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseJSON(response);
                adapter.notifyDataSetChanged();
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        });
        mRequestQueue.add(jr);
        
        findViewById(R.id.btnThread).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThreadSample.class));
            }
        });
    }

    private void parseJSON(JSONObject json) {
        try {
            JSONArray value = json.getJSONArray("value");
            for (int i = 0; i < value.length(); i++) {
                JSONObject item = value.getJSONObject(i);
                InfoModel nm = new InfoModel();
                nm.setId(item.optString("id"));
                nm.setName(item.optString("name"));
                nm.setProject(item.optString("project"));
                nm.setQuote(item.optString("quote"));
                arrInfo.add(nm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
